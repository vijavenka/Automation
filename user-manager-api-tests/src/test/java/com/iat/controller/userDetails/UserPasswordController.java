package com.iat.controller.userDetails;

import com.iat.contracts.userDetails.UserPasswordContract;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserPasswordController {

    private UserPasswordContract userPasswordContract = new UserPasswordContract();

    private ValidatableResponse resetUserPasswordRequest(String email, String apiKey, int status) {
        String path = userPasswordContract.resetUserPasswordPath(email, apiKey);
        log.info("Reset Path: GET {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse resetUserAccountPassword(String email, String apiKey, int status) {
        return resetUserPasswordRequest(email, apiKey, status);
    }
}
