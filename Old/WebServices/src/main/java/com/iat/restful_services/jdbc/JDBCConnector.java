package com.iat.restful_services.jdbc;

import java.sql.*;

public class JDBCConnector {
	
	private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

	public JDBCConnector() {
		String dbUrl = "jdbc:mysql://10.10.31.144/points_manager";
		String dbClass = "com.mysql.jdbc.Driver";
        String user = "dev";
        String pass = "muppet";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(dbUrl, user, pass);
            assert this.connection !=  null;
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public void executeStatementQuery(String query) throws SQLException {
        statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    public void executeQuery(String query) throws SQLException {
        statement = connection.createStatement();
        setResultSet(statement.executeQuery(query));
    }

    public void performDelete(String query) {
        try {
            statement.execute("SET FOREIGN_KEY_CHECKS=0");
                executeStatementQuery(query);
            statement.execute("SET FOREIGN_KEY_CHECKS=1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void performSelect(String query) {
        try {
            executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

/*

-- user password is: 1qazxsw2, sha-256 encrypted, 1024 iterations, base64 encoded

insert into points(id, createdAt, activityInfo, delta, status, tagId, partnerId, userId, balance) values(1, '2013-05-11 15:41:56', 'Comment on page', 2, 'CONFIRMED', 1, 1, 1, 2);
insert into points(id, createdAt, activityInfo, delta, status, tagId, partnerId, userId, balance) values(2, '2013-05-12 15:41:56', 'Comment on page', 2, 'CONFIRMED', 1, 1, 1, 4);
insert into points(id, createdAt, activityInfo, delta, externalTransactionId, status, partnerId, userId, balance) values(3, '2013-05-13 15:41:56', 'Buought a DVD', 26, '123456','PENDING', 1, 1, 4);
insert into points(id, createdAt, updatedAt, activityInfo, delta, externalTransactionId, status, partnerId, userId, balance) values(4, '2013-05-13 16:41:56', '2013-05-23 16:41:56',
'Buought a Book', 16, '123456','CONFIRMED', 1, 1, 20);

insert into user_pointsmap(User_id, pointsMap, pointsMap_KEY) values(1, 20, 'CONFIRMED');
insert into user_pointsmap(User_id, pointsMap, pointsMap_KEY) values(1, 26, 'PENDING');

insert into request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId) values(1, '2013-05-11 15:41:56', 'accessKey', 'ADD_POINTS', true, 1, 'email@domain.com');
insert into request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId) values(2, '2013-05-12 15:41:56', 'accessKey', 'ADD_POINTS', true, 2, 'email@domain.com');
insert into request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId, message) values(3, '2013-05-13 15:41:56', 'invalidKey', 'ADD_POINTS', false, null, 'email@domain.com', 'Partner with apiKey invalidKey not exists');
insert into request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId) values(4, '2013-05-13 15:41:56', 'accessKey', 'ADD_POINTS', true, 3, 'email@domain.com');
insert into request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId) values(5, '2013-05-13 16:41:56', 'accessKey', 'ADD_POINTS', true, 4, 'email@domain.com');
insert into request(id, createdAt, apiAccessKey, requestType, success, pointsId, userId) values(6, '2013-05-23 16:41:56', 'accessKey', 'CHANGE_PENDING_TO_CONFIRMED', true, 4, 'email@domain.com');
*/