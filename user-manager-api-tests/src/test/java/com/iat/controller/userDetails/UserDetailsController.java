package com.iat.controller.userDetails;

import com.iat.Config;
import com.iat.contracts.userDetails.UserDetailsContract;
import com.iat.domain.UserDetails;
import com.iat.utils.DataExchanger;
import com.iat.utils.auth.ContentApiAuthentication;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserDetailsController {

    private UserDetailsContract userDetailsContract = new UserDetailsContract();
    private ContractValidator contractValidator = new ContractValidator();
    private ContentApiAuthentication authentication = new ContentApiAuthentication();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    private ValidatableResponse updateUserAccountDetailsByJsonRequest(String userId, String idType, String apiKey, UserDetails userDetails, int status) {
        String path = userDetailsContract.getUserDetailsPath(userId, idType, apiKey);

        log.info("Update details Path: PUT {}{}\n{}", RestAssured.baseURI, path, userDetails.toString());
        return given()
                .contentType(ContentType.JSON)
                .body(userDetails)
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    private ValidatableResponse updateUserAccountDetailsByJsonEwsRequest(String userId, String idType, String apiKey, UserDetails userDetails, int status) {
        String path = Config.getEwsUrl() + userDetailsContract.getUserDetailsPath(userId, idType, apiKey);

        log.info("Update details Path: PUT{}\nBody: {}", path, userDetails.toString());
        return given()
                .contentType(ContentType.JSON)
                .headers(authentication.auth(path, "PUT"))
                .port(443)
                .body(userDetails)
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse updateUserAccountDetailsByJson(String userId, String idType, String apiKey, UserDetails userDetails, int status) {
        ValidatableResponse validatableResponse = dataExchanger.isEwsRequest() ?
                updateUserAccountDetailsByJsonEwsRequest(userId, idType, apiKey, userDetails, status)
                :
                updateUserAccountDetailsByJsonRequest(userId, idType, apiKey, userDetails, status);
        contractValidator.validateResponseWithContract("userDetailsGET-response-schema.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getUserAccountDetailsRequest(String userId, String idType, String apiKey, int status) {
        String path = userDetailsContract.getUserDetailsPath(userId, idType, apiKey);
        log.info("Details Path: GET {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    private ValidatableResponse getUserAccountDetailsEwsRequest(String userId, String idType, String apiKey, int status) {
        String path = Config.getEwsUrl() + userDetailsContract.getUserDetailsPath(userId, idType, apiKey);
        log.info("Details Path: GET {}", path);
        return given()
                .contentType(ContentType.JSON)
                .headers(authentication.auth(path, "GET"))
                .port(443)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUserAccountDetails(String userId, String idType, String apiKey, int status) {
        ValidatableResponse validatableResponse = dataExchanger.isEwsRequest() ?
                getUserAccountDetailsEwsRequest(userId, idType, apiKey, status)
                :
                getUserAccountDetailsRequest(userId, idType, apiKey, status);
        contractValidator.validateResponseWithContract("userDetailsGET-response-schema.json", validatableResponse, status);
        return validatableResponse;
    }


    private ValidatableResponse getUserAccountDetailsByEpointsTokenRequest(String epointsToken, String apiKey, int status) {
        String path = userDetailsContract.getUserDetailsByEpointsTokenPath(epointsToken, apiKey);

        log.info("Details Path: GET {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUserAccountDetailsByEpointsToken(String epointsToken, String apiKey, int status) {
        ValidatableResponse validatableResponse = getUserAccountDetailsByEpointsTokenRequest(epointsToken, apiKey, status);
        contractValidator.validateResponseWithContract("userDetailsByEpointsTokenGET-response-schema.json", validatableResponse, status);
        return validatableResponse;
    }
}
