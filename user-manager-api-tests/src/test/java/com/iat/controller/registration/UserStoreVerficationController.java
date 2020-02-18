package com.iat.controller.registration;

import com.iat.contracts.registration.UserStoreVerificationContract;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserStoreVerficationController {

    private UserStoreVerificationContract userStoreVerificationContract = new UserStoreVerificationContract();

    private ValidatableResponse verifyNewStoreRequest(String userEmail, String userLastName, String partnerId, String apiKey, int status) {
        String path = userStoreVerificationContract.getUserStoreVerificationPath(userEmail, userLastName, partnerId, apiKey);
        log.info("VerifyStore Path: GET {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse verifyNewStoreName(String userEmail, String userLastName, String partnerId, String apiKey, int status) {
        return verifyNewStoreRequest(userEmail, userLastName, partnerId, apiKey, status);
    }
}
