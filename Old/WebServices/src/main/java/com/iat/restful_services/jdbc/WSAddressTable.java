package com.iat.restful_services.jdbc;

/**
 * Created with IntelliJ IDEA.
 * User: LPanusz
 * Date: 31.05.13
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class WSAddressTable {

    //Address Table fields definition
    private int id;
    private String country;
    private String county;
    private String createdAt;
    private String houseNumberOrName;
    private String keyName;
    private String postCode;
    private String street;
    private String townOrCity;
    private String updatedAt;
    private int user_id;

    //Default constructor
    public WSAddressTable(int Id, String Country, String County, String CreatedAt, String HouseNumberOrName, String KeyName, String  PostCode,
                         String Street, String TownOrCity, String UpdatedAt, int UserId) {
        this.id = Id;
        this.country = Country;
        this.county = County;
        this.createdAt = CreatedAt;
        this.houseNumberOrName = HouseNumberOrName;
        this.keyName = KeyName;
        this.postCode = PostCode;
        this.street = Street;
        this.townOrCity = TownOrCity;
        this.updatedAt = UpdatedAt;
        this.user_id = UserId;
    }

    public int getId() {
        return this.id;
    }

    public String getCountry() {
        return this.country;
    }

    public String getCounty() {
        return this.county;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public String getHouseNumberOrName() {
        return this.houseNumberOrName;
    }

    public String getKeyName() {
        return this.keyName;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public String getStreet() {
        return this.street;
    }

    public String getTownOrCity() {
        return this.townOrCity;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public int getUser_id() {
        return this.user_id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public void setCountry(String Country) {
        this.country = Country;
    }

    public void setCounty(String County) {
        this.county = County;
    }

    public void setCreatedAt(String CreatedAt) {
        this.createdAt = CreatedAt;
    }

    public void setHouseNumberOrName(String HouseNumberOrName) {
        this.houseNumberOrName = HouseNumberOrName;
    }

    public void setKeyName(String KeyName) {
        this.keyName = KeyName;
    }

    public void setPostCode(String PostCode) {
        this.postCode = PostCode;
    }

    public void setStreet(String Street) {
        this.street = Street;
    }

    public void setTownOrCity(String TownOrCity) {
        this.townOrCity = TownOrCity;
    }

    public void setUpdatedAt(String UpdatedAt) {
        this.updatedAt = UpdatedAt;
    }

    public void setUser_id(int UserId) {
        this.user_id = UserId;
    }

    public String buildWSAddressTableInsertQuery() {
        String query = "INSERT INTO `points_manager`.`Address`(`id`,`country`,`county`,`createdAt`,`houseNumberOrName`,`keyName`,`postcode`,`street`,`townOrCity`," +
                "`updatedAt`,`user_id`)" + "VALUES" + "(" + this.id + ",'" + this.country + "','" + this.county + "','" + this.createdAt + "','" + this.houseNumberOrName +
                "','" + this.keyName + "','" + this.postCode + "','" + this.street + "','" + this.townOrCity + "','" + this.updatedAt + "'," + this.user_id + ");";

        return query;
    }

    public String buildWSAddressTableSelectQuery() {
        String query = "SELECT * FROM points_manager.Address;";
        return query;
    }
}
