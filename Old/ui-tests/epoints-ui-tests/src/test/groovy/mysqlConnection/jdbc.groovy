package mysqlConnection;

import stepdefs.envVariables;

import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.01.14
 * Time: 09:39
 * To change this template use File | Settings | File Templates.
 */
public class jdbc {
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet resultSet = null;

    def envVar = new envVariables();
    // epoints /////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String dbUrlEP = envVar.dbUrlEP;
    private String userEP = envVar.userEP;
    private String passEP = envVar.passEP;
    // admin ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String dbUrlAD = envVar.dbUrlAD;
    private String userAD = envVar.userAD;
    private String passAD = envVar.passAD;
    // new admin ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private String dbUrlAD2 = envVar.dbUrlAD2;
    private String userAD2 = envVar.userAD2;
    private String passAD2 = envVar.passAD2;

    private String query;
    private String result = "";

    public jdbc(String whichDatabase) {
        //create connection
        try {
            //set mysql driver
            Class.forName("com.mysql.jdbc.Driver");
            //set connection
            if (whichDatabase.equals("points")) {
                this.conn = DriverManager.getConnection(dbUrlEP, userEP, passEP);
            } else if (whichDatabase.equals("admin")) {
                this.conn = DriverManager.getConnection(dbUrlAD, userAD, passAD);
            } else if (whichDatabase.equals("adminNew")) {
                this.conn = DriverManager.getConnection(dbUrlAD2, userAD2, passAD2);
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


    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    // get information from data base
    public String get(String query, int pos) {
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
    public void update(String query) {
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