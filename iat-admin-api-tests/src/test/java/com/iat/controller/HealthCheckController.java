package com.iat.controller;

import com.iat.contracts.HealthCheckContract;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class HealthCheckController {

    private HealthCheckContract healthCheckContract = new HealthCheckContract();

    public ValidatableResponse doHealthCheck() {
        String path = healthCheckContract.getHealthCheckPath();

        log.info("Path: {}", path);

        return given().
                contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(200);
    }
}