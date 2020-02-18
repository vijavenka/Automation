package com.iat.ePoints.Database;

import com.iat.ePoints.EnvironmentVariables;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.01.14
 * Time: 09:39
 * To change this template use File | Settings | File Templates.
 */
public class JDBC {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet resultSet = null;

    EnvironmentVariables envVariables = new EnvironmentVariables();
    // epoints /////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String dbUrlEP = envVariables.dbUrlEP;
    private String userEP = envVariables.userEP;
    private String passEP = envVariables.passEP;
    // admin ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String dbUrlAD = envVariables.dbUrlAD;
    private String userAD = envVariables.userAD;
    private String passAD = envVariables.passAD;

    private String query;
    private String result = "";

    public JDBC(String whichDatabase) {
        //create connection
        try {
            //set mysql driver
            Class.forName("com.mysql.jdbc.Driver");
            //set connection
            if (whichDatabase.equals("points_manager")) {
                this.conn = DriverManager.getConnection(dbUrlEP, userEP, passEP);
            } else if (whichDatabase.equals("affiliate_manager")) {
                this.conn = DriverManager.getConnection(dbUrlAD, userAD, passAD);
            }
        } catch (ClassNotFoundException ex) {
            //System.out.println("Driver failure");
        } catch (SQLException ex) {
            //System.out.println("Problem with login\nPlease check:\n user name, password, Database name or server URL");
            //System.out.println("SQLException: " + ex.getMessage());
            //System.out.println("SQLState: " + ex.getSQLState());
            //System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    // most common queries used in tests, another will be introduced directly in navigation classes
    public String return_proper_query(String action, String email) {
        // basic common queries for User ePoints user
        if (action.equals("returnUser")) {
            return query = "SELECT * FROM points_manager.User WHERE email='" + email + "'";
        } else if (action.equals("returnId")) {
            return query = "SELECT id FROM points_manager.User WHERE email='" + email + "'";
        } else if (action.equals("returnUserKey")) {
            return query = "SELECT userKey FROM points_manager.User WHERE email='" + email + "'";
        } else if (action.equals("deleteRequest")) {
            return query = "DELETE FROM points_manager.Request WHERE userId='" + return_proper_db_value(return_proper_query("returnUserKey", email), 1) + "'";
        } else if (action.equals("deletePoints")) {
            return query = "DELETE FROM points_manager.Points WHERE userId='" + return_proper_db_value(return_proper_query("returnId", email), 1) + "'";
        } else if (action.equals("deleteUserToken")) {
            return query = "DELETE FROM points_manager.UserToken WHERE user_id='" + return_proper_db_value(return_proper_query("returnId", email), 1) + "'";
        } else if (action.equals("deleteUserId")) {
            return query = "DELETE FROM points_manager.UserId WHERE user_id='" + return_proper_db_value(return_proper_query("returnId", email), 1) + "'";
        } else if (action.equals("deleteUser")) {
            return query = "DELETE FROM points_manager.User WHERE id='" + return_proper_db_value(return_proper_query("returnId", email), 1) + "'";
        }
        //delete facebook flag /////////////////////////////////////////////////////////////////////////////////////////
        else if (action.equals("deleteFacebookFlag")) {
            return query = "DELETE FROM points_manager.UserId WHERE user_id='" + return_proper_db_value("SELECT id FROM points_manager.User WHERE email='iat.epoints.test@gmail.com'", 1) + "' AND userIdType='FACEBOOK'";
        }
        return null;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    // get information from data base
    public String return_proper_db_value(String query, int pos) {
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            result = rs.getString(pos);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // update or delete row in data base
    public void execute_delete_update_query(String query) {
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() throws SQLException {
        this.conn.close();
    }

    ResultSet rs;
    public void get_all_query_content(String query){
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String get_value_of_proper_row(boolean switchToNextRow, int pos) throws SQLException {
        if(switchToNextRow){
            rs.next();
        }
        return rs.getString(pos);
    }

}


/**
 *
 while (rs.next()) {
 System.out.print(rs.getInt(1));
 System.out.print(": ");
 System.out.println(rs.getString(2));
 }
 */

