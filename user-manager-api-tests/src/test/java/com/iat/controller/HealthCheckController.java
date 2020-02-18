package com.iat.controller;

import com.iat.contracts.HealthCheckContract;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class HealthCheckController {

    private HealthCheckContract healthCheckContract = new HealthCheckContract();

    private ValidatableResponse getHealthCheckRequest(int status) {
        String path = healthCheckContract.getHealthCheckPath();
        log.info("Path: GET {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getHealthCheck(int status) {
        return getHealthCheckRequest(status);
    }
}