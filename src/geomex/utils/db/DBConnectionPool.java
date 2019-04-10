package geomex.utils.db;

import geomex.utils.IOUtil;
import geomex.utils.Logger;
import geomex.utils.Queue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * JDBC Connecton Pool을 관리하는 클래스 <br>
 * 1)Connection을 미리 생성하여 관리하고 Applicatin에서 사용 후 반환하여 재사용 할수 있도록 한다.<br>
 * 2)주기적으로 Thread가 유효성을 체크하여 유효하지 않은 connection을 제거한다.
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class DBConnectionPool implements Runnable {
    private List<DBConnection> allGroup = null; // 생성된 모든 Connection저장
    private Queue<DBConnection> poolGroup = null; // 풀 객체를 저장하는 장소

    private String name; // 풀 이름
    private String url; // URL
    private Properties prop; // connection arguments
    private final int minCapacity; // 초기 생성하는 풀 객체 수
    private final int maxCapacity; // 최대 생성가능한 풀 객체 수
    private int waitTimeout; // Connection반환 대기 시간 초단위
    private int checkTimeout; // Connection check 주기 초단위
    private String checkQuery; // Statement유효성 검사용 쿼리문
    private Logger logger;
    private Thread monitor;

    private volatile boolean isRun = true; // Thread구동 여부

    /**
     * 생성자
     * 
     * @param name Connection Pool 이름
     * @param url JDBC 접속 URL
     * @param p Connection Arguments
     * @param minCapacity Connection 최소 유지 갯수
     * @param maxCapacity Connection 최대 유지 갯수
     * @param waitTimeout Connection 반환 대기하는 최대 시간.(초 단위)
     * @param checkTimeout Connection 유효성 체크주기(초 단위)
     * @param checkQuery Connection 유효성 체크용 쿼리문
     * @param logger 로거
     */
    public DBConnectionPool(String name, String url, Properties p,
        int minCapacity, int maxCapacity, int waitTimeout,
        int checkTimeout, String checkQuery, Logger logger) {

        this.name = name;
        this.url = url;
        this.prop = p;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.waitTimeout = waitTimeout * 1000;
        this.logger = logger;
        this.checkTimeout = checkTimeout * 1000;
        this.checkQuery = checkQuery;
        init();
        if (poolGroup.size() > 0) {
            monitor = new Thread(this, "JDBCPool[" + name + "]Monitor");
            monitor.start();
        }
    }

    /**
     * 초기화 한다.
     */
    private void init() {
        poolGroup = new Queue<DBConnection>(maxCapacity);
        allGroup = Collections.synchronizedList(new ArrayList<DBConnection>(
            maxCapacity));
        for (int i = 0; i < minCapacity; i++) {
            newConnection();
        }
    }

    /**
     * Connection을 생성하여 풀 저장소에 보관한다.
     */
    private void newConnection() {
        try {
            Connection conn = null;
            if (prop == null || prop.isEmpty()) {
                conn = java.sql.DriverManager.getConnection(url);
            } else {
                conn = java.sql.DriverManager.getConnection(url, prop);
            }
            DBConnection pooledObject = new DBConnection(conn);
            poolGroup.add(pooledObject);
            allGroup.add(pooledObject);
            if (Logger.canLog(logger, Logger.INFO)) {
                logger.info(new StringBuilder(100).append("[").append(name)
                    .append("]").append(" newConnection : ")
                    .append(status()).toString());
            }
        } catch (Exception e) {
            logger.error(new StringBuilder(500).append("[").append(name)
                .append("] ").append(IOUtil.getStackTrace(e)).toString());
        }
    }

    /**
     * Pool저장소에서 Connection를 꺼내어 반환한다. <br>
     * 사용가능한 Connectiondl 없으나 추가생성이 가능하면 추가생성하여 반환한다. <br>
     * 생성된 Connection이 최대값과 같고, waitTimeout 동안 반환되는 Connection을 기다린다.
     * 
     * @param waitTimeout 사용된 Connection이 반환되기를 기다리는 최대 시간 milliseconds
     * @return DBConnection : 저장소에서 꺼낸 Connection<br>
     *         null : waitTimeout 동안 반환되는 Connection이 없다.
     */
    private synchronized DBConnection getConnection(long waitTimeout) {
        // 놀고 있는 connection있으면 즉시 꺼내 즉시 반환한다.
        if (poolGroup.size() > 0) {
            return poolGroup.removeNoWait();
        }
        // 이미 생성된 모든 Connection이 작업중에 있다.
        // 작업중인 Connection수가 최대 허용치 보다 작으면
        // Connection을 추가 생성하고 즉시 꺼내서 반환한다.
        if (allGroup.size() < maxCapacity) {
            newConnection();
            return poolGroup.removeNoWait();
        }

        // 반환되는 Connection을 기다려야 하니까 경고 로그를 기록한다.
        if (Logger.canLog(logger, Logger.WARN)) {
            logger.warn(new StringBuilder(200).append("[").append(name)
                .append("]").append(" waitConnection : ").append(status())
                .toString());
        }
        // waitTimeout까지 기다린 후 반환된 Connection이 있는지 확인한다.
        // 기다린후 반환된 Connection이 없으면 null이 반환된다.
        return poolGroup.remove(waitTimeout);
    }

    /**
     * Pool 저장소에서 Connection을 가져와 반환한다.<br>
     * 
     * @return DBConnection : DBConnection <br>
     *         null : 사용할수 있는 Connection이 없고 waitTimeout 때까지도 반환되는 Connection이
     *         없다
     */
    public DBConnection getConnection() {
        DBConnection pObject = getConnection(waitTimeout);
        if (Logger.canLog(logger, Logger.DEBUG)) {
            logger.debug(new StringBuilder(200).append("[").append(name)
                .append("]").append(" GET : ").append(status()).toString());
        }
        return pObject;
    }

    /**
     * 사용된 Connection을 재사용을 위해 Pool저장소에 저장한다. <br>
     * 반환된 객체가 무효할 경우 제거한다. 유효한 객체만 Pool저장소에 저장한다.
     * 
     * @param conn 사용하고 반환되는 Connection
     */
    public void freeConnection(DBConnection conn) {
        if (conn == null || !conn.isAvailable()) {
            allGroup.remove(conn);// 유효하지 않으면 제거한다.
            if (Logger.canLog(logger, Logger.WARN)) {
                logger.warn(new StringBuilder(100).append("[").append(name)
                    .append("]")
                    .append(" freeConnection : remove invalid Connection.")
                    .toString());
            }
        } else {
            conn.reset();
            poolGroup.add(conn);
            if (Logger.canLog(logger, Logger.DEBUG)) {
                logger.debug(new StringBuilder(200).append("[").append(name)
                    .append("]").append(" PUT : ").append(status())
                    .toString());
            }
        }
    }

    /**
     * Connection Pool 관리 쓰레드호출 메쏘드 <br>
     * Pool 저장소에 있는 Connection의 유효성을 체크한다.
     */

    @Override
    public void run() {
        long recentTime = System.currentTimeMillis();
        while (isRun && Thread.currentThread() == monitor) {
            try {
                if ((System.currentTimeMillis() - recentTime) >= checkTimeout) {
                    checkConnection();
                    recentTime = System.currentTimeMillis();
                }
                Thread.sleep(500L);
            } catch (Exception e) {}
        }
    }

    /**
     * Pool에 있는 Connection이 유효한지 체크하고 유효하지 않은 것은 close한다.
     */
    private void checkConnection() {
        if (Logger.canLog(logger, Logger.DEBUG)) {
            logger.debug(new StringBuilder(200).append("[").append(name)
                .append("]").append(" checkConnection ").append(status())
                .toString());
        }
        DBConnection conn = poolGroup.removeNoWait();
        if (conn == null)
            return;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(checkQuery);
            rs = stmt.executeQuery();
            rs.next();
            rs.getString(1);
            conn.reset();
            poolGroup.add(conn); // 유효하면 재사용을 위해 다시 저장한다.
        } catch (Exception e) {
            conn.close();
            allGroup.remove(conn);// 유효하지 않으면 제거한다.
            if (Logger.canLog(logger, Logger.WARN)) {
                logger.warn(new StringBuilder(100)
                    .append("[")
                    .append(name)
                    .append("]")
                    .append(" checkConnection : remove invalid Connection.")
                    .toString());
            }
        } finally {
            closeConn(rs, stmt);
        }
        //
        if (allGroup.size() < minCapacity) {
            synchronized (this) {
                newConnection();
            }
        }
    }

    /**
     * 유효성 체크에 사용된 ResultSet, Statement를 닫는다.
     * 
     * @param rs ResultSet
     * @param stmt PreparedStatement
     */
    private void closeConn(ResultSet rs, PreparedStatement stmt) {
        try {
            rs.clearWarnings();
        } catch (Exception e) {}
        try {
            rs.close();
        } catch (Exception e) {}
        try {
            stmt.clearWarnings();
        } catch (Exception e) {}
        try {
            stmt.close();
        } catch (Exception e) {}
    }

    /**
     * 모든 Connection을 닫는다
     */
    public synchronized void destroy() {
        isRun = false;
        try {
            notifyAll();
        } catch (Exception e) {}
        poolGroup.clear();
        //
        for (int x = 0; x < allGroup.size(); x++) {
            DBConnection con = allGroup.get(x);
            if (con != null)
                con.close();
        }
        allGroup.clear();
        if (Logger.canLog(logger, Logger.INFO)) {
            logger.info(new StringBuilder(100).append("[").append(name)
                .append("]").append(" DBConnectionPool Destroyed")
                .toString());
        }
        poolGroup = null;
        allGroup = null;
    }

    /**
     * 상태 문자열을 얻는다.
     * 
     * @return String : 상태 문자열
     */
    public String status() {
        int poolSize = poolGroup.size();
        int allSize = allGroup.size();
        int activeCount = allSize - poolSize;
        return new StringBuffer(50).append("name=").append(name)
            .append(",min=").append(minCapacity).append(",max=")
            .append(maxCapacity).append(",all=").append(allSize)
            .append(",pool=").append(poolSize).append(",active=")
            .append(activeCount).toString();
    }

    /**
     * 소유자를 얻는다.
     * 
     * @return Owner
     */
    public String getOwner() {
        return prop.getProperty("user");
    }

    @Override
    public String toString() {
        return new StringBuilder(20).append("total= ").append(allGroup.size())
            .append(",pool= ").append(poolGroup.size()).toString();
    }
}
