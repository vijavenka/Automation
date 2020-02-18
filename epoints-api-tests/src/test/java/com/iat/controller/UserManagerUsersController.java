package com.iat.controller;

import com.iat.contracts.UserManagerUsersContract;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserManagerUsersController {

    private UserManagerUsersContract userManagerUsersContract = new UserManagerUsersContract();

    public ValidatableResponse getUserAccountDetailsRequest(String userId, String idType, String apiKey, int status) {
        String path = userManagerUsersContract.getUserDetailsPath(userId, idType, apiKey);
        log.info("nGet details Path: {}", path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUserAccountDetails(String userId, String idType, String apiKey, int status) {
        return getUserAccountDetailsRequest(userId, idType, apiKey, status);
    }

}