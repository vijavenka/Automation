package com.iat.controller.userDetails;

import com.iat.contracts.userDetails.UserDetailsContract;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserVerificationController {

    private UserDetailsContract userDetailsContract = new UserDetailsContract();

    private ValidatableResponse resendChangeOrRegistrationEmailRequest(String userId, String idType, String apiKey, String resendType, int status) {
        String path = userDetailsContract.resendVerificationEmail(userId, idType, apiKey, resendType);
        log.info("Path: GET {}{}", RestAssured.baseURI, path);
        return given().
                contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse resendChangeOrRegistrationEmail(String userId, String idType, String apiKey, String resendType, int status) {
        return resendChangeOrRegistrationEmailRequest(userId, idType, apiKey, resendType, status);
    }
}