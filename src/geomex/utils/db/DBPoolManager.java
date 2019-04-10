package geomex.utils.db;

import geomex.utils.LogManager;
import geomex.utils.StrUtil;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

;
/**
 * 동시에 여러 DBConnectionPool을 관리하는 클래스<br>
 * Geomex엔진전용으로 클래스 이름을 바꾸어 사용한다.
 * 
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class DBPoolManager {
    static private volatile DBPoolManager manager = null; // volatile 반드시..
    private Map<String, DBConnectionPool> pools = null;

    /**
     * DBPoolManager 얻는다. <br>
     * getManager()호출전에 반드시 create(DBPoolProp[] props)가 호출되어야 한다.
     * 
     * @return DBPoolManager
     */
    static public DBPoolManager getManager() {
        return manager;
    }

    /**
     * DBPoolManager을 생성한다. <br>
     * 최소 한번 실행되어야 하면 다수의 Connection Pool을 동시에 관리한다.
     * 
     * @param props DBConnection Pool설정화일들
     */
    public static void create(DBPoolProp props[]) {
        if (manager == null) {
            synchronized (DBPoolManager.class) {
                if (manager == null) {
                    manager = new DBPoolManager(props);
                } else {
                    manager.createPool(props);
                }
            }
        }
    }

    /**
     * 생성자 DBPool설정 정보를 가지고 각 DBConnectionPool을 생성한다
     * 
     * @param props DBPool설정화일들
     */
    private DBPoolManager(DBPoolProp props[]) {
        pools = Collections
            .synchronizedMap(new HashMap<String, DBConnectionPool>(
                props.length));
        this.createPool(props);
    }

    /**
     * DBConnectionPool을 생성한다.
     * 
     * @param props
     */
    private void createPool(DBPoolProp props[]) {
        for (DBPoolProp prop : props) {
            this.createPool(prop);
        }
    }

    /**
     * DBConnectionPool을 생성한다.
     * 
     * @param prop
     */
    private void createPool(DBPoolProp prop) {
        for (String key : pools.keySet()) {
            if (key.equalsIgnoreCase(prop.getName())) {
                System.out.println("Already exist DBPool name: "
                    + prop.getName());
                return;
            }
        }

        try {
            loadDriver(prop.getDriver());
        } catch (Exception e) {
            e.printStackTrace();
        }
        DBConnectionPool pool = new DBConnectionPool(prop.getName(),
            prop.getUrl(), prop.getProperties(), prop.getMinCapacity(),
            prop.getMaxCapacity(), prop.getWaitTimeout(),
            prop.getCheckTimeout(), prop.getCheckQuery(), LogManager
                .getManager().getLogger(prop.getLoggerName()));
        pools.put(prop.getName(), pool);
    }

    /**
     * DBConnectionPool을 얻는다.
     * 
     * @param name
     * @return DBConnectionPool
     */
    public DBConnectionPool getPool(String name) {
        return pools.get(name);
    }

    /**
     * 풀이름 ConnectionPool에서 Connection을 얻는다.
     * 
     * @param name 풀이름
     * @return DBConnection PooledConnection
     */
    public DBConnection getConnection(String name) {
        return pools.get(name).getConnection();
    }

    /**
     * 사용한 DBConnection을 Connection Pool에 반환한다.
     * 
     * @param obj 반환할 DBConnection
     * @param name DBConnection이 속한 풀 이름
     */
    public void freeConnection(DBConnection obj, String name) {
        pools.get(name).freeConnection(obj);
    }

    /**
     * 모든 DBConnection Pool을 Destroy한다.
     */
    public void destroy() {
        if (pools == null)
            return;
        synchronized (pools) {
            for (DBConnectionPool pool : pools.values()) {
                if (pool != null)
                    pool.destroy();
            }
            try {
                notifyAll();
            } catch (Exception e) {}
        }
        pools.clear();
        pools = null;
        System.out.println("DBPoolManager Destroyed.");
    }

    /**
     * Driver를 로드한다. 이미 존재하면 Pass
     * 
     * @param d
     * @throws Exception
     */
    private void loadDriver(String d) throws Exception {
        boolean exist = false;
        for (Enumeration<Driver> e = DriverManager.getDrivers(); e
            .hasMoreElements();) {
            Driver dname = e.nextElement();
            String chk = StrUtil.left(dname.toString(), "Driver") + "Driver";
            if (d.equalsIgnoreCase(chk)) {
                exist = true;
                break;
            }
        }

        if (!exist) {
            Driver driver = (Driver) Class.forName(d).newInstance();
            DriverManager.registerDriver(driver);
        }
    }

}
