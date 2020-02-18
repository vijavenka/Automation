package com.iat.controller;

import com.iat.contracts.HealthCheckContract;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class HealthCheckController {

    private HealthCheckContract healthCheckContract = new HealthCheckContract();

    private ValidatableResponse isHealthCheckStatusOK(int code) {
        String path = healthCheckContract.getHealthCheckPath();

        System.out.print("\nPath: " + path + "\n");

        return given().
                contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public void doHealthCheck(int code) {
        System.out.println("\nRESPONSE:");
        isHealthCheckStatusOK(code).extract().response().prettyPrint();
    }
}