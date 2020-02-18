package com.iat.controller.registration;

import com.iat.Config;
import com.iat.contracts.registration.UserRegistrationContract;
import com.iat.domain.UserDetails;
import com.iat.utils.DataExchanger;
import com.iat.utils.auth.ContentApiAuthentication;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserRegistrationController {

    private UserRegistrationContract usersRegistrationContract = new UserRegistrationContract();
    private ContentApiAuthentication authentication = new ContentApiAuthentication();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    private ValidatableResponse createUserAccountRequest(String apiKey, UserDetails userDetails, int status) {
        String path = usersRegistrationContract.getUserAccountCreationPath(apiKey);
        log.info("Create user Path: POST {}{}", RestAssured.baseURI, path);
        log.info("Body: " + userDetails.toString());
        return given()
                .contentType(ContentType.JSON)
                .body(userDetails)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    private ValidatableResponse createUserAccountEwsRequest(String apiKey, UserDetails userDetails, int status) {
        String path = Config.getEwsUrl() + usersRegistrationContract.getUserAccountCreationPath(apiKey);
        log.info("CreateUser Path: POST {}", path);
        log.info("Body: {}", userDetails.toString());
        return given()
                .contentType(ContentType.JSON)
                .headers(authentication.auth(path, "POST"))
                .port(443)
                .body(userDetails)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse createUserAccountByJson(UserDetails jsonBody, String apiKey, int status) {
        return dataExchanger.isEwsRequest() ?
                createUserAccountEwsRequest(apiKey, jsonBody, status)
                :
                createUserAccountRequest(apiKey, jsonBody, status);
    }
}