package com.iat.controller.authentication;

import com.iat.Config;
import com.iat.contracts.authentication.UserAuthenticationContract;
import com.iat.utils.DataExchanger;
import com.iat.utils.auth.ContentApiAuthentication;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserAuthenticationController {

    private UserAuthenticationContract userAuthenticationContract = new UserAuthenticationContract();
    private ContractValidator contractValidator = new ContractValidator();
    private ContentApiAuthentication authentication = new ContentApiAuthentication();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    private ValidatableResponse authenticateUserRequest(String email, String password, String apiKey, int status) {
        String path = userAuthenticationContract.authenticateUser(email, password, apiKey);
        log.info("Authorization Path: POST {}{}", RestAssured.baseURI, path);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    private ValidatableResponse authenticateUserEwsRequest(String email, String password, String apiKey, int status) {
        String path = Config.getEwsUrl() + userAuthenticationContract.authenticateUser(email, password, apiKey);
        log.info("Login Path: " + path);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .headers(authentication.auth(path, "POST"))
                .port(443)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse authenticateUser(String email, String password, String apiKey, int status) {
        ValidatableResponse response = dataExchanger.isEwsRequest() ?
                authenticateUserEwsRequest(email, password, apiKey, status)
                :
                authenticateUserRequest(email, password, apiKey, status);
        contractValidator.validateResponseWithContract("authentication/POST-201-userAuthentication.json", response, status);
        return response;
    }

    private ValidatableResponse authenticateUserWithFacebookRequest(String accessToken, String facebookId, String apiKey, int status) {
        String path = userAuthenticationContract.authenticateUserWithFacebook(accessToken, facebookId, apiKey);
        log.info("Authorization Path: POST {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }


    private ValidatableResponse authenticateUserWithFacebookEwsRequest(String accessToken, String facebookId, String apiKey, int status) {
        String path = Config.getEwsUrl() + userAuthenticationContract.authenticateUserWithFacebook(accessToken, facebookId, apiKey);
        log.info("Login Path: " + path);

        return given()
                .contentType("application/x-www-form-urlencoded;charset=utf-8")
                .headers(authentication.auth(path, "POST"))
                .port(443)
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse authenticateUserWithFacebook(String accessToken, String facebookId, String apiKey, int status) {
        ValidatableResponse response = dataExchanger.isEwsRequest() ?
                authenticateUserWithFacebookEwsRequest(accessToken, facebookId, apiKey, status)
                :
                authenticateUserWithFacebookRequest(accessToken, facebookId, apiKey, status);
        contractValidator.validateResponseWithContract("authentication/POST-201-userAuthentication.json", response, status);
        return response;
    }

}