package geomex.utils.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 최근 사용시간 정보를 가지는 DBConnection 클래스
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class DBConnection {

    private Connection conn; // JDBC Connection
    private long recentTime; // 최근에 사용된 timestamp

    /**
     * 생성자
     * 
     * @param con java.sql.Connection
     */
    public DBConnection(Connection con) {
        this.conn = con;
        reset();
    }

    /**
     * 사용된 시간을 재설정한다.
     */
    public void reset() {
        recentTime = System.currentTimeMillis();
        try {
            conn.clearWarnings();
        } catch (Exception e) {}
        try {
            conn.setAutoCommit(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 사용된 시간을 얻는다.
     * 
     * @return long timestamp : 최근 Connection이 사용된 시간
     */
    public long getCheckTime() {
        return recentTime;
    }

    /**
     * Connection을 얻는다.
     * 
     * @return Connection java.sql.Connection
     */
    public Connection connection() {
        return conn;
    }

    /**
     * Connection이 유효한지를 체크한다.<br>
     * 체크방법 conn == null || conn.isClosed()
     * 
     * @return true Connection 이 유효하다.<br>
     *         false Connection is null or closed
     */
    public boolean isAvailable() {
        try {
            if (conn == null || conn.isClosed())
                return false;
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * java.sql.PreparedStatement를 얻는다.
     * 
     * @param sql quary문
     * @return PreparedStatement
     * @throws SQLException java.sql.SQLException
     */
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    /**
     * Connecton을 닫는다.
     */
    public void close() {
        try {
            conn.close();
        } catch (Exception e) {};
    }

    @Override
    public synchronized String toString() {
        int passedTime = (int) ((System.currentTimeMillis() - getCheckTime()) / (1000 * 60));
        StringBuilder sb = new StringBuilder(50);
        sb.append("lazyTime= ").append(passedTime);
        sb.append(",available= ").append(isAvailable());
        return sb.toString();
    }
}
