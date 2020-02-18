package com.iat.utils;

import org.apache.commons.pool.BasePoolableObjectFactory;

import java.sql.DriverManager;

public class JdbcConnectionPool extends BasePoolableObjectFactory {

    public enum DB {mysql,postgresql}
    private DB dbtype;
    private String host;
    private int port;
    private String schema;
    private String user;
    private String password;
    private String driver;

    public JdbcConnectionPool(DB dbtype, String driver, String host, int port, String schema, String user, String password) {
        this.host = host;
        this.port = port;
        this.schema = schema;
        this.user = user;
        this.password = password;
        this.driver = driver;
        this.dbtype = dbtype;
    }

    public Object makeObject() throws Exception {
        Class.forName(driver).newInstance();
        String url = "jdbc:"+dbtype+"://" + host + ":" + port + "/" + schema + "?autoReconnectForPools=true";
        return DriverManager.getConnection(url, user, password);
    }
}
