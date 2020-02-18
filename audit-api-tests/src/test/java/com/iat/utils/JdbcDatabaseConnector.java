package com.iat.utils;

import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class JdbcDatabaseConnector {

    private final JdbcConnectionPool connPool;

    public JdbcDatabaseConnector(JdbcConnectionPool connPool) {
        this.connPool = connPool;
    }

    public String getSingleResult(String sql) {
        Connection conn = null;
        Statement st = null;
        ResultSet res = null;
        try {
            conn = (Connection) connPool.makeObject();
            st = conn.createStatement();
            res = st.executeQuery(sql);
            boolean isRow = res.next();
            if (isRow)
                return res.getString(1);
            else
                return null;
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            DbUtils.closeQuietly(res);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        return null;
    }

    public HashMap<Integer, List> getResult(String sql, List<String> columnLabels) {
        Connection conn = null;
        Statement st = null;
        ResultSet res = null;
        HashMap<Integer, List> result = new HashMap<>();
        try {
            conn = (Connection) connPool.makeObject();
            st = conn.createStatement();
            res = st.executeQuery(sql);
            Integer counter = 0;
            while (res.next()) {
                List<Object> row = new LinkedList<>();
                for (String columnLabel : columnLabels) {
                    Object object = res.getObject(columnLabel);
                    if (object == null)
                        row.add("null");
                    else
                        row.add(object);
                }
                result.put(counter, row);
                counter++;
            }
            return result;

        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            DbUtils.closeQuietly(res);
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
        return null;
    }

    public void execute(String query) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = (Connection) connPool.makeObject();
            st = conn.createStatement();
            st.execute(query);
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            DbUtils.closeQuietly(st);
            DbUtils.closeQuietly(conn);
        }
    }
}
