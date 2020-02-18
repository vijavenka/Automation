package com.iat.restful_services.jdbc;

/**
 * Created with IntelliJ IDEA.
 * User: LPanusz
 * Date: 11.06.13
 * Time: 07:37
 * To change this template use File | Settings | File Templates.
 */
public class WSUserIdTable {

    private int id;
    private String createdAt;
    private String updatedAt;
    private String userId;
    private String userIdType;
    private int user_id;

    public WSUserIdTable(int id, String createdAt, String updatedAt, String userId, String userIdType, int user_id) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.userIdType = userIdType;
        this.user_id = user_id;
    }

    public WSUserIdTable() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdType() {
        return userIdType;
    }

    public void setUserIdType(String userIdType) {
        this.userIdType = userIdType;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String buildWSUserIdTableInsertQuery() {
        String query = "INSERT INTO `points_manager`.`UserId` (`id`,`createdAt`,`updatedAt`,`userId`,`userIdType`,`user_id`)" + "VALUES" + "(" + this.id + ",'" + this.createdAt + "','" + this.updatedAt  + "','" +
                this.userId + "','" + this.userIdType + "'," + this.user_id + ");";

        return query;
    }

    public String buildWSUserIdTableStaticInsertQuery() {
        String query ="insert into userId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:41:56', 'email@domain.com', 'EMAIL', 1);\n" +
                "insert into userId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'abc@domain.com', 'EMAIL', 1);\n" +
                "insert into userId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'email100@domain.com', 'EMAIL', 3);\n" +
                "insert into userId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'email200@domain.com', 'EMAIL', 4);\n" +
                "insert into userId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'email300@domain.com', 'EMAIL', 5);\n" +
                "insert into userId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'email400@domain.com', 'EMAIL', 6);\n" +
                "insert into userId(id, createdAt, userId, userIdType, user_id) values(id, '2013-05-10 15:31:56', 'email500@domain.com', 'EMAIL', 7);";

        return query;
    }

    public String buildWSUserIdTableSelectQuery() {
        String query = "SELECT * FROM points_manager.UserId;";
        return query;
    }

    public String buildWSDeleteAllUsersIdQuery() {
        String query = "DELETE FROM points_manager.UserId;";
        return query;
    }
}
