package com.iat.restful_services.actions;

import com.iat.restful_services.jdbc.JDBCActions;
import com.iat.restful_services.json.JSONBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.json.JsonPath;

import java.sql.SQLException;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: LPanusz
 * Date: 11.06.13
 * Time: 09:13
 * To change this template use File | Settings | File Templates.
 */

public class RetrievingPersonalDetailsWebserviceActions {

    JSONBuilder buildJson = new JSONBuilder();
    JDBCActions jdbcActions = new JDBCActions();

    String response;

    public RetrievingPersonalDetailsWebserviceActions() {
        RestAssured.baseURI = "http://10.10.31.144";
        RestAssured.port = 8913;
        RestAssured.basePath = "";
    }

    public void verifyUserInDatabase() throws SQLException {
        if(jdbcActions.checkUserInDataBase("John")) {
            assert true;
        } else {
            jdbcActions.createUserInDataBase( 0, "2013-05-31 10:42", "http://testlink.iatlimited.com/!@#!=?12893189jhd", 1, "1998-05-31", "Poland", "Dolnyslask", "2013-05-30 0:01", "john.doe@iatlimited.com",
                    "John", "MALE", "1", "2013-05-31 12:09", "Doe", "5554554", "Polish", 1, 1, "test123", "55-228", "Poland", "P1", "2013-05-31 10:31", "Ruska", 0, "Mr", "Wroclaw", "2013-05-31 10:32",
                    "john.doe@iatlimited.com", "2013-05-31 10:31", 1);
            jdbcActions.createUserInDataBase( 0, "2013-05-31 10:42", "http://testlink.iatlimited.com/!@#!=?12893189jhd", 0, "1998-05-31", "Poland", "Dolnyslask", "2013-05-30 0:01", "eddie.doe@iatlimited.com",
                    "Eddie", "MALE", "1", "2013-05-31 12:09", "Doe", "5554554", "Polish", 1, 1, "test123", "55-228", "Poland", "P1", "2013-05-31 10:31", "Ruska", 0, "Mr", "Wroclaw", "2013-05-31 10:32",
                    "john.doe@iatlimited.com", "2013-05-31 10:31", 1);
            jdbcActions.createUserIdInDataBase(0, "2013-06-05 15:31:56", "2013-06-05 15:31:56", "john.doe@iatlimited.com", "EMAIL", jdbcActions.getUserId("John"));
            jdbcActions.createUserIdInDataBase(0, "2013-06-05 15:31:56", "2013-06-05 15:31:56", "eddie.doe@iatlimited.com", "EMAIL", jdbcActions.getUserId("Eddie"));
        }
    }

    public void removeEntriesForUserInDatabase() {
        jdbcActions.deleteAllEntriesForUserInDataBase();
    }

    public void callForUserDetails(String userId, String accessKey) {
        String path = "/users/" + userId + "/?idType=" + "EMAIL&" + "apiKey=" + accessKey;
        System.out.println(path);
        response = given().
                            contentType("application/json").
                   expect().
                           response().
                    when().
                            get(path).asString();
        System.out.println(response);
    }

    public void verifyUserDetails() {
        JsonPath jsonPath = new JsonPath(response);
        assertEquals("Doe", jsonPath.get("lastName"));
        assertEquals("Ruska", jsonPath.get("street"));
        assertEquals("55-228", jsonPath.get("postcode"));
        assertEquals("test123", jsonPath.get("password"));
        assertEquals("1", jsonPath.get("houseNumberOrName"));
        assertEquals(true, jsonPath.get("emailVerified"));
        assertEquals("EMAIL", jsonPath.get("userIdType"));
        assertEquals("Poland", jsonPath.get("country"));
        assertEquals("Mr", jsonPath.get("title"));
        assertEquals("john.doe@iatlimited.com", jsonPath.get("email"));
        assertEquals("Dolnyslask", jsonPath.get("county"));
        assertEquals("john.doe@iatlimited.com", jsonPath.get("userId"));
        assertEquals("MALE", jsonPath.get("gender"));
        assertEquals("5554554", jsonPath.get("mobileNumber"));
        assertEquals("John", jsonPath.get("firstName"));
        assertEquals("Wroclaw", jsonPath.get("townOrCity"));
        //TODO: Check dates and timestamps
    }

    public void callForSpecificUserDetails(String userId, String accessKey, String[] fields) {
        String path = "/users/" + userId + "/?idType=" + "EMAIL&" + "apiKey=" + accessKey + "&fields=";
        System.out.println(path);

            StringBuffer Fields = new StringBuffer();
            for(int i=0; i<fields.length; i++) {
                Fields.append(fields[i]);
                Fields.append(",");
            }

            String fieldsCall = Fields.toString();

        response = given().
                        contentType("application/json").
                   expect().
                        response().
                   when().
                        get(path + fieldsCall).asString();
        System.out.println(response);
    }

    public void verifySpecificUserDetails(String[] fields) {
        JsonPath jsonPath = new JsonPath(response);
        String[] value = {"Doe", "john.doe@iatlimited.com", "John", "test123"};

            for(int i=0; i<fields.length; i++) {
                assertEquals(value[i], jsonPath.get(fields[i]));
            }
    }

    public void invalidCallForUserDetails(String userId, String accessKey) {
        String path = "/users/" + userId + "/?idType=" + "EMAIL&" + "apiKey=" + accessKey;
        System.out.println(path);
        response = given().
                        contentType("application/json").
                    expect().
                        statusCode(404).
                        response().
                    when().
                        get(path).asString();
        System.out.println(response);
    }

    public void verifyEmptyResponse() {
        assertEquals("", response);
    }
}
