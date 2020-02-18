package com.iat.controller.activationStatus;

import com.iat.contracts.activationStatus.UserActivationStatusContract;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserActivationStatusController {

    private UserActivationStatusContract userActivationContract = new UserActivationStatusContract();

    private ValidatableResponse setActivationStatusRequest(String userId, String idType, String apiKey, String activationStatus, int status) {
        String path = userActivationContract.setUserActivationStatus(userId, idType, apiKey, activationStatus);
        log.info("Activation status Path: GET {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse setActivationStatus(String userId, String idType, String apiKey, String newActivateStatus, int status) {
        return setActivationStatusRequest(userId, idType, apiKey, newActivateStatus, status);
    }

}