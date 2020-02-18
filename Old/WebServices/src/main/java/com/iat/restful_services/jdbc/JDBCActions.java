package com.iat.restful_services.jdbc;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: LPanusz
 * Date: 11.06.13
 * Time: 07:55
 * To change this template use File | Settings | File Templates.
 */
public class JDBCActions {

    private JDBCConnector mySqlConnector = new JDBCConnector();

    public void createStaticPartnerInDataBase() {
        WSPartnerTable partnerTable = new WSPartnerTable();

        try {
            mySqlConnector.executeStatementQuery(partnerTable.buildWSPartnerStaticInsertQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPartnerInDataBase(int id, String createdAt, String updatedAt, int currentTotal, String accessKey, String name, String paymentType, long pendingAutoConfirmTimeout, String shortName,
                                        int active, String siteUrl) {

        WSPartnerTable partnerTable = new WSPartnerTable(id, createdAt, updatedAt, currentTotal, accessKey, name, paymentType, pendingAutoConfirmTimeout, shortName, active, siteUrl);

        try {
            mySqlConnector.executeStatementQuery(partnerTable.buildWSPartnerTableInsertQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteAllEntriesForUserInDataBase() {
        WSUserTable userTable = new WSUserTable();
        WSUserIdTable userIdTable = new WSUserIdTable();

            mySqlConnector.performDelete(userIdTable.buildWSDeleteAllUsersIdQuery());
            mySqlConnector.performDelete(userTable.buildWSDeleteAllUsersQuery());
    }

    public int getUserId(String name) throws SQLException {
        WSUserTable userTable = new WSUserTable();
        int Id = 0;

            mySqlConnector.performSelect(userTable.buildWSUserTableSelectFilterQuery("id", name));

            while(mySqlConnector.getResultSet().next()) {
                Id = mySqlConnector.getResultSet().getInt("id");
            }

        return Id;
    }

    public void createStaticUserInDataBase() {
        WSUserTable userTable = new WSUserTable();
        try {
            mySqlConnector.executeStatementQuery(userTable.buildWSUserTableStaticInsertQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUserInDataBase(int id, String activationAt, String activationLink, int active, String birthDate, String country, String county, String createdAt, String email,
                                     String firstName, String gender, String houseNumberOrName, String lastLoginAt, String lastName, String mobileNumber, String nationality,
                                     int optInContactByOthers, int optInContactByUs, String password, String postcode, String region, String registerPartnerShortName,
                                     String registrationAt, String street, int testUser, String title, String townOrCity, String updatedAt, String originalEmail, String passwordChangedAt,
                                     int emailVerified) {

        WSUserTable userTable = new WSUserTable(id, activationAt, activationLink, active, birthDate, country, county, createdAt, email, firstName, gender, houseNumberOrName, lastLoginAt, lastName,
                mobileNumber, nationality, optInContactByOthers, optInContactByUs, password, postcode, region, registerPartnerShortName, registrationAt, street, testUser, title, townOrCity,
                updatedAt, originalEmail, passwordChangedAt, emailVerified);

        try {
            mySqlConnector.executeStatementQuery(userTable.buildWSUserTableInsertQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUserInDataBase(String name) throws SQLException {
        Boolean exists = false;
        WSUserTable userTable = new WSUserTable();

            mySqlConnector.performSelect(userTable.buildWSUserTableSelectQuery("*"));

        while(mySqlConnector.getResultSet().next()) {
            if(name.equalsIgnoreCase(mySqlConnector.getResultSet().getString("firstName"))) {
                    exists = true;
                    break;
            }
        }

        return exists;
    }

    public void createStaticUserIdInDataBase () {
        WSUserIdTable userIdTable = new WSUserIdTable();
        try {
            mySqlConnector.executeStatementQuery(userIdTable.buildWSUserIdTableStaticInsertQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUserIdInDataBase(int id, String createdAt, String updatedAt, String userId, String userIdType, int user_id) {

        WSUserIdTable userIdTable = new WSUserIdTable(id, createdAt, updatedAt, userId, userIdType, user_id);

        try {
            mySqlConnector.executeStatementQuery(userIdTable.buildWSUserIdTableInsertQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTagInDataBase(int id, String createdAt, String updatedAt, int cap, String frequency, long pendingAutoConfirmTimeout, String tagKey, String tagStatus, int partnerId) {

        WSTagTable tagTable = new WSTagTable(id, createdAt, updatedAt, cap, frequency, pendingAutoConfirmTimeout, tagKey, tagStatus, partnerId);

        try {
            mySqlConnector.executeStatementQuery(tagTable.buildWSTagTableInsertQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createUserAddressInDataBase(int Id, String Country, String County,String CreatedAt, String HouseNumberOrName, String KeyName, String  PostCode,
                                            String Street, String TownOrCity, String UpdatedAt, int UserId) {

        WSAddressTable addressTable = new WSAddressTable(Id, Country, County, CreatedAt, HouseNumberOrName, KeyName, PostCode, Street, TownOrCity, UpdatedAt, UserId);

        try {
            mySqlConnector.executeQuery(addressTable.buildWSAddressTableInsertQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}