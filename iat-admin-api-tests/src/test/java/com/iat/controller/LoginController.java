package com.iat.controller;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class LoginController {

    private ValidatableResponse userLogInRequest(String credentials, int status) {
        String path = "/api/login";

        log.info("Path: POST {}", path);
        log.info("BODY: user={}&password={}", credentials.split(",")[0], credentials.split(",")[1]);

        return given()
                .contentType(ContentType.URLENC)
                .body("user=" + credentials.split(",")[0] + "&password=" + credentials.split(",")[1])
                .when()
                .post(path)
                .then();
//                .statusCode(status);
    }

    public ValidatableResponse userLogIn(String credentials, int status) {
        return userLogInRequest(credentials, status);
    }
}