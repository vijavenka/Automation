package com.iat.controller;

import com.iat.Config;
import com.iat.contract.HealthCheckContract;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class HealthCheckController {

    private HealthCheckContract healthCheckContract = new HealthCheckContract();

    private ValidatableResponse getHealthCheckRequest() {
        String path = Config.affiliateManagerUrl() + healthCheckContract.getHealthCheckPath();

        System.out.println("\nPath: GET " + path);

        return given()
                .when()
                .get(path)
                .then()
                .statusCode(200);
    }

    public ValidatableResponse getHealthCheck() {
        return getHealthCheckRequest();
    }

}