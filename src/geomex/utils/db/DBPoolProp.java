package geomex.utils.db;

import java.util.Properties;

/**
 * JDBC Connection Pool설정정보 클래스
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class DBPoolProp {

    public String name; // 풀 이름
    private int minCapacity; // 초기 생성하는 풀 객체 수
    private int maxCapacity; // 최대 생성가능한 풀 객체 수
    private int waitTimeout; // 최대 반환 대기 시간 초단위
    private int checkTimeout; // DBPool유효성 체크간격 초 단위
    private String driver; // JDBC Driver
    private String url; // JDBC URL
    private Properties prop; // connection arguments
    private String checkQuery; // DBConnection의 유효성을 체크하기 위한 테스트 쿼리 문
    private String loggerName; // 로거 이름

    public DBPoolProp() {}

    /**
     * 생성자
     * 
     * @param name 풀 이름
     * @param minCapacity 초기 생성하는 풀 객체 수
     * @param maxCapacity 최대 생성가능한 풀 객체 수
     * @param waitTimeout 최대 반환 대기 시간 초단위
     * @param checkTimeout DBPool유효성 체크간격 초 단위
     * @param driver JDBC Driver
     * @param url JDBC URL
     * @param prop Connection Arguments
     * @param testSql DBConnection의 유효성을 체크하기 위한 테스트 쿼리 문
     * @param loggerName 로거 이름
     */
    public DBPoolProp(String name, int minCapacity, int maxCapacity,
        int waitTimeout, int checkTimeout, String driver, String url,
        Properties prop, String testSql, String loggerName) {

        this.name = name;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.waitTimeout = waitTimeout;
        this.checkTimeout = checkTimeout;
        this.driver = driver;
        this.url = url;
        this.prop = prop;
        this.checkQuery = testSql;
        this.loggerName = loggerName;
    }

    /**
     * Connection Arguments 를 가지는 Properties을 얻는다.
     * 
     * @return Properties : user, password
     */
    public Properties getProperties() {
        return prop;
    }

    /**
     * Connection Arguments 를 가지는 Properties을 설정한다.
     * 
     * @param prop Properties : user, password
     */
    public void setProperties(Properties prop) {
        this.prop = prop;
    }

    /**
     * 유효성 체크를 위한 쿼리문을 얻는다.
     * 
     * @return String 쿼리문
     */
    public String getCheckQuery() {
        return checkQuery;
    }

    /**
     * 유효성 체크를 위한 쿼리문을 설정한다.
     * 
     * @param checkQuery 쿼리문
     */
    public void setCheckQuery(String checkQuery) {
        this.checkQuery = checkQuery;
    }

    /**
     * JDBC Driver문자열을 얻는다.
     * 
     * @return String JDBC Driver문자열
     */
    public String getDriver() {
        return driver;
    }

    /**
     * JDBC Driver문자열을 설정한다.
     * 
     * @param driver JDBC Driver문자열
     */
    public void setDriver(String driver) {
        this.driver = driver;
    }

    /**
     * 로거 이름을 얻는다.
     * 
     * @return String 로거 이름
     */
    public String getLoggerName() {
        return loggerName;
    }

    /**
     * 로거 이름을 등록한다.
     * 
     * @param loggerName 로거 이름
     */
    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    /**
     * 최대 연결하여 관리할 수 있는 Connection 수를 얻는다.
     * 
     * @return int Connection 수
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * 최대 연결하여 관리할 수 있는 Connection 수를 설정한다.
     * 
     * @param maxCapacity Connection 수
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * Connection 반환을 대기하는 최대 시간을 얻는다.
     * 
     * @return int 초
     */
    public int getWaitTimeout() {
        return waitTimeout;
    }

    /**
     * Connection 반환을 대기하는 최대 시간을 얻는다.
     * 
     * @param waitTimeout 초
     */
    public void setWaitTimeout(int waitTimeout) {
        this.waitTimeout = waitTimeout;
    }

    /**
     * 초기 유지하는 Connection 수를 얻는다.
     * 
     * @return int Connection 수
     */
    public int getMinCapacity() {
        return minCapacity;
    }

    /**
     * 초기 유지하는 Connection 수를 설정한다.
     * 
     * @param minCapacity Connection 수
     */
    public void setMinCapacity(int minCapacity) {
        this.minCapacity = minCapacity;
    }

    /**
     * 풀 이름을 얻는다.
     * 
     * @return String 풀이름
     */
    public String getName() {
        return name;
    }

    /**
     * 풀이름을 설정한다.
     * 
     * @param name 풀이름
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * JDBC 연결 URL을 얻는다.
     * 
     * @return String URL문자열
     */
    public String getUrl() {
        return url;
    }

    /**
     * JDBC 연결 URL을 설정한다.
     * 
     * @param url URL문자열
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * JDBC 연결시 사용하는 Arguments를 등록한다. <br>
     * user, password 는 반드시 포함되어야 하낟.
     * 
     * @param key Property 명
     * @param value Property 값
     */
    public void setProperty(String key, String value) {
        if (prop == null) {
            this.prop = new Properties();
        }
        this.prop.setProperty(key, value);
    }

    /**
     * DBConnectionPool 체크 주기를 얻는다.
     * 
     * @return int 초
     */
    public int getCheckTimeout() {
        return checkTimeout;
    }

    /**
     * DBConnectionPool 체크 주기를 설정한다.
     * 
     * @param checkTimeout 체크주기(초)
     */
    public void setCheckTimeout(int checkTimeout) {
        this.checkTimeout = checkTimeout;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("name           = ").append(name).append("\r\n");
        sb.append("minCapacity    = ").append(minCapacity).append("\r\n");
        sb.append("maxCapacity    = ").append(maxCapacity).append("\r\n");
        sb.append("waitTimeout    = ").append(waitTimeout).append("\r\n");
        sb.append("checkTimeout   = ").append(checkTimeout).append("\r\n");
        sb.append("driver         = ").append(driver).append("\r\n");
        sb.append("url            = ").append(url).append("\r\n");
        sb.append("prop           = ").append(prop.toString()).append("\r\n");
        sb.append("testSQL        = ").append(checkQuery).append("\r\n");
        sb.append("loggerName     = ").append(loggerName);
        return sb.toString();
    }
}
