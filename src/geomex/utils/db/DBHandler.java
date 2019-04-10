package geomex.utils.db;

import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * JDBC 작업을 위한 Helper 클래스
 * 
 * @author 김 경 호(geovlet@naver.com)
 */
public class DBHandler {

    private String queryString = null; // 수행될 query문
    private DBConnection dbConn = null; // dbconnection pool에서 얻어옴
    private ResultSet resultSet = null; // select문에서얻은 resultSet
    private PreparedStatement stmt = null;
    private DBPoolManager manager = null;
    private String nodeName = null;

    /** Creates a new instance of DBHandler */
    public DBHandler() {}

    /**
     * DBHandler를 close한다.
     * 
     * @param db
     */
    public static void close(DBHandler db) {
        if (db == null) return;
        db.close();
    }

    /**
     * 풀에서 Connection을 가져와 작업준비를 한다.
     * 
     * @param name 풀이름
     * @throws Exception
     */
    public void open(String name) throws SQLException {
        if (manager == null) {
            manager = DBPoolManager.getManager();
        }
        this.nodeName = name;
        dbConn = manager.getConnection(nodeName);
        dbConn.connection().setAutoCommit(true);
    }

    /**
     * 작업후 관련 리소스를 닫고 사용한 Connection을 반환한다.
     */
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.clearWarnings();
            }
        } catch (Exception e) {}
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {}

        try {
            if (stmt != null) {
                stmt.clearParameters();
            }
        } catch (Exception e) {}
        try {
            if (stmt != null) {
                stmt.clearWarnings();
            }
        } catch (Exception e) {}
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {}

        manager.freeConnection(dbConn, nodeName);
        resultSet = null;
        stmt = null;
        dbConn = null;
        manager = null;
        nodeName = null;
    }

    /**
     * 작업후 관련 리소스를 clear하지만 사용한 Connection을 반환하지 않는다.
     */
    public void reset() {
        try {
            if (resultSet != null) {
                resultSet.clearWarnings();
            }
        } catch (Exception e) {}
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {}
        try {
            if (stmt != null) {
                stmt.clearWarnings();
            }
        } catch (Exception e) {}
        try {
            if (stmt != null) {
                stmt.clearParameters();
            }
        } catch (Exception e) {}
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {}
        resultSet = null;
        stmt = null;
    }

    /**
     * ResultSet 정보를 지우고 닫는다
     */
    public void closeResultSet() {
        try {
            if (resultSet != null) {
                resultSet.clearWarnings();
            }
        } catch (Exception e) {}
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {}
    }

    /**
     * PreparedStatement 정보를 clear하지만 close하지는 않는다.
     */
    public void clearStatement() {
        try {
            if (stmt != null) {
                stmt.clearWarnings();
            }
        } catch (Exception e) {}
        try {
            if (stmt != null) {
                stmt.clearParameters();
            }
        } catch (Exception e) {}
    }

    /**
     * 쿼리문을 실행한다.
     * 
     * @return int 작업 반영 결과 수
     * @throws SQLException
     */
    public int execute() throws SQLException {
        if (queryString == null) {
            throw new SQLException("QueryString Is Null!.");
        }
        if (stmt.execute()) {
            if (resultSet != null) {
                closeResultSet();
            }
            resultSet = stmt.getResultSet();
        }
        return stmt.getUpdateCount();
    }

    /**
     * commit
     * 
     * @throws SQLException
     */
    public void commit() throws SQLException {
        dbConn.connection().commit();
    }

    /**
     * rollback
     * 
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        dbConn.connection().rollback();
    }

    /**
     * ResultSet.next()
     * 
     * @return true : select한 결과셋이 있다. false : 더이상 결과 셋이 없다.
     * @throws SQLException
     */
    public boolean next() throws SQLException {
        return resultSet.next();
    }

    /**
     * ResultSet 결과를 idx만큼 Skip한다.
     * 
     * @param idx skip할 값
     * @throws SQLException
     */
    public void skip(int idx) throws SQLException {
        if (resultSet == null) {
            throw new SQLException("ResultSet Is Null!.");
        }
        for (int i = 0; i < idx; i++) {
            resultSet.next();
        }
    }

    /**
     * 쿼리문을 얻는다.
     * 
     * @return String 쿼리문
     */
    public String getQuery() {
        return this.queryString;
    }

    /**
     * ResultSet객체를 얻는다.
     * 
     * @return ResultSet
     * @throws SQLException
     */
    public ResultSet getResultSet() throws SQLException {
        return resultSet;
    }

    /**
     * PreparedStatement 객체를 얻는다.
     * 
     * @return PreparedStatement
     * @throws SQLException
     */
    public PreparedStatement getStatement() throws SQLException {
        return stmt;
    }

    /**
     * ResultSetMetaData객체를 얻는다.
     * 
     * @return ResultSetMetaData
     * @throws SQLException
     */
    public ResultSetMetaData getMetaData() throws SQLException {
        return stmt.getMetaData();
    }

    /**
     * PreparedStatement.setMaxRows
     * 
     * @param mRows
     * @throws SQLException
     */
    public void setMaxRows(int mRows) throws SQLException {
        stmt.setMaxRows(mRows);
    }

    /**
     * Connection.setAutoCommit
     * 
     * @param auto
     * @throws SQLException
     */
    public void setAutoCommit(boolean auto) throws SQLException {
        dbConn.connection().setAutoCommit(auto);
    }

    /**
     * 수행할 쿼리문을 등록하고 prepareStatement객체를 생성한다.
     * 
     * @param query 쿼리문
     * @throws SQLException
     */
    public void setQuery(String query) throws SQLException {
        this.queryString = query;
        if (stmt != null) {
            clearStatement();
        }
        stmt = dbConn.prepareStatement(query);
    }

    /**
     * PreparedStatement.setQueryTimeout
     * 
     * @param sec
     * @throws SQLException
     */
    public void setQueryTimeout(int sec) throws SQLException {
        stmt.setQueryTimeout(sec);
    }

    /**
     * ResultSet.getBinaryStream
     * 
     * @param idx
     * @return InputStream
     * @throws SQLException
     */
    public InputStream getBinaryStream(int idx) throws SQLException {
        return resultSet.getBinaryStream(idx);
    }

    /**
     * ResultSet.getBinaryStream
     * 
     * @param idx
     * @return InputStream
     * @throws SQLException
     */
    public InputStream getBinaryStream(String idx) throws SQLException {
        return resultSet.getBinaryStream(idx);
    }

    /**
     * ResultSet.getBlob
     * 
     * @param idx
     * @return Blob
     * @throws SQLException
     */
    public Blob getBlob(String idx) throws SQLException {
        return resultSet.getBlob(idx);
    }

    /**
     * ResultSet.getBlob
     * 
     * @param idx
     * @return Blob
     * @throws SQLException
     */
    public Blob getBlob(int idx) throws SQLException {
        return resultSet.getBlob(idx);
    }

    /**
     * ResultSet.getClob
     * 
     * @param idx
     * @return Clob
     * @throws SQLException
     */
    public Clob getClob(String idx) throws SQLException {
        return resultSet.getClob(idx);
    }

    /**
     * ResultSet.getClob
     * 
     * @param idx
     * @return Clob
     * @throws SQLException
     */
    public Clob getClob(int idx) throws SQLException {
        return resultSet.getClob(idx);
    }

    /**
     * ResultSet.getCharacterStream
     * 
     * @param idx
     * @return Reader
     * @throws SQLException
     */
    public Reader getCharacterStream(String idx) throws SQLException {
        return resultSet.getCharacterStream(idx);
    }

    /**
     * ResultSet.getCharacterStream
     * 
     * @param idx
     * @return Reader
     * @throws SQLException
     */
    public Reader getCharacterStream(int idx) throws SQLException {
        return resultSet.getCharacterStream(idx);
    }

    /**
     * ResultSet.getDate
     * 
     * @param idx
     * @return Date
     * @throws SQLException
     */
    public Date getDate(String idx) throws SQLException {
        return resultSet.getDate(idx);
    }

    /**
     * ResultSet.getDate
     * 
     * @param idx
     * @return Date
     * @throws SQLException
     */
    public Date getDate(int idx) throws SQLException {
        return resultSet.getDate(idx);
    }

    /**
     * ResultSet.getDouble
     * 
     * @param idx
     * @return double
     * @throws SQLException
     */
    public double getDouble(int idx) throws SQLException {
        return resultSet.getDouble(idx);
    }

    /**
     * ResultSet.getDouble
     * 
     * @param idx
     * @return double
     * @throws SQLException
     */
    public double getDouble(String idx) throws SQLException {
        return resultSet.getDouble(idx);
    }

    /**
     * ResultSet.getFloat
     * 
     * @param idx
     * @return float
     * @throws SQLException
     */
    public float getFloat(int idx) throws SQLException {
        return resultSet.getFloat(idx);
    }

    /**
     * ResultSet.getFloat
     * 
     * @param idx
     * @return float
     * @throws SQLException
     */
    public float getFloat(String idx) throws SQLException {
        return resultSet.getFloat(idx);
    }

    /**
     * ResultSet.getInt
     * 
     * @param idx
     * @return int
     * @throws SQLException
     */
    public int getInt(int idx) throws SQLException {
        return resultSet.getInt(idx);
    }

    /**
     * ResultSet.getInt
     * 
     * @param idx
     * @return int
     * @throws SQLException
     */
    public int getInt(String idx) throws SQLException {
        return resultSet.getInt(idx);
    }

    /**
     * ResultSet.getLong
     * 
     * @param idx
     * @return long
     * @throws SQLException
     */
    public long getLong(int idx) throws SQLException {
        return resultSet.getLong(idx);
    }

    /**
     * ResultSet.getLong
     * 
     * @param idx
     * @return long
     * @throws SQLException
     */
    public long getLong(String idx) throws SQLException {
        return resultSet.getLong(idx);
    }

    /**
     * ResultSet.getObject
     * 
     * @param idx
     * @return Object
     * @throws SQLException
     */
    public Object getObject(int idx) throws SQLException {
        return resultSet.getObject(idx);
    }

    /**
     * ResultSet.getObject
     * 
     * @param idx
     * @return Object
     * @throws SQLException
     */
    public Object getObject(String idx) throws SQLException {
        return resultSet.getObject(idx);
    }

    /**
     * ResultSet.getShort
     * 
     * @param idx
     * @return short
     * @throws SQLException
     */
    public short getShort(int idx) throws SQLException {
        return resultSet.getShort(idx);
    }

    /**
     * ResultSet.getShort
     * 
     * @param idx
     * @return short
     * @throws SQLException
     */
    public short getShort(String idx) throws SQLException {
        return resultSet.getShort(idx);
    }

    /**
     * ResultSet.getString
     * 
     * @param idx
     * @return String
     * @throws SQLException
     */
    public String getString(int idx) throws SQLException {
        return resultSet.getString(idx);
    }

    /**
     * ResultSet.getString
     * 
     * @param idx
     * @return String
     * @throws SQLException
     */
    public String getString(String idx) throws SQLException {
        return resultSet.getString(idx);
    }

    /**
     * ResultSet.getTime
     * 
     * @param idx
     * @return Time
     * @throws SQLException
     */
    public Time getTime(String idx) throws SQLException {
        return resultSet.getTime(idx);
    }

    /**
     * ResultSet.getTime
     * 
     * @param idx
     * @return Time
     * @throws SQLException
     */
    public Time getTime(int idx) throws SQLException {
        return resultSet.getTime(idx);
    }

    /**
     * ResultSet.getTimestamp
     * 
     * @param idx
     * @return Timestamp
     * @throws SQLException
     */
    public Timestamp getTimestamp(String idx) throws SQLException {
        return resultSet.getTimestamp(idx);
    }

    /**
     * ResultSet.getTimestamp
     * 
     * @param idx
     * @return Timestamp
     * @throws SQLException
     */
    public Timestamp getTimestamp(int idx) throws SQLException {
        return resultSet.getTimestamp(idx);
    }

    /**
     * 
     * @param idx
     * @param x
     * @param length
     * @throws SQLException
     */
    public void setBinaryStream(int idx, InputStream x, int length)
        throws SQLException {
        stmt.setBinaryStream(idx, x, length);
    }

    /**
     * PreparedStatement.setBlob
     * 
     * @param idx
     * @param x
     * @throws SQLException
     */
    public void setBlob(int idx, Blob x) throws SQLException {
        stmt.setBlob(idx, x);
    }

    /**
     * PreparedStatement.setCharacterStream
     * 
     * @param idx
     * @param reader
     * @param length
     * @throws SQLException
     */
    public void setCharacterStream(int idx, Reader reader, int length)
        throws SQLException {
        stmt.setCharacterStream(idx, reader, length);
    }

    /**
     * PreparedStatement.setClob
     * 
     * @param idx
     * @param x
     * @throws SQLException
     */
    public void setClob(int idx, Clob x) throws SQLException {
        stmt.setClob(idx, x);
    }

    /**
     * PreparedStatement.setDate
     * 
     * @param idx
     * @param x
     * @throws SQLException
     */
    public void setDate(int idx, Date x) throws SQLException {
        stmt.setDate(idx, x);
    }

    /**
     * PreparedStatement.setDouble
     * 
     * @param idx
     * @param x
     * @throws SQLException
     */
    public void setDouble(int idx, double x) throws SQLException {
        stmt.setDouble(idx, x);
    }

    /**
     * PreparedStatement.setFloat
     * 
     * @param idx
     * @param x
     * @throws SQLException
     */
    public void setFloat(int idx, float x) throws SQLException {
        stmt.setFloat(idx, x);
    }

    /**
     * PreparedStatement.setInt
     * 
     * @param idx
     * @param x
     * @throws SQLException
     */
    public void setInt(int idx, int x) throws SQLException {
        stmt.setInt(idx, x);
    }

    /**
     * PreparedStatement.setLong
     * 
     * @param idx
     * @param x
     * @throws SQLException
     */
    public void setLong(int idx, long x) throws SQLException {
        stmt.setLong(idx, x);
    }

    /**
     * PreparedStatement.setNull
     * 
     * @param idx
     * @param sqlType
     * @throws SQLException
     */
    public void setNull(int idx, int sqlType) throws SQLException {
        stmt.setNull(idx, sqlType);
    }

    /**
     * PreparedStatement.setObject
     * 
     * @param idx
     * @param obj
     * @throws SQLException
     */
    public void setObject(int idx, Object obj) throws SQLException {
        stmt.setObject(idx, obj);
    }

    /*
     * 8 PreparedStatement.setShort
     */
    public void setShort(int idx, short x) throws SQLException {
        stmt.setShort(idx, x);
    }

    /**
     * PreparedStatement.setString
     * 
     * @param idx
     * @param x
     * @throws SQLException
     */
    public void setString(int idx, String x) throws SQLException {
        stmt.setString(idx, x);
    }

    /**
     * PreparedStatement.setTime
     * 
     * @param idx
     * @param x
     * @throws SQLException
     */
    public void setTime(int idx, Time x) throws SQLException {
        stmt.setTime(idx, x);
    }

    /**
     * PreparedStatement.setTimestamp
     * 
     * @param idx
     * @param x
     * @throws SQLException
     */
    public void setTimestamp(int idx, Timestamp x) throws SQLException {
        stmt.setTimestamp(idx, x);
    }
}
