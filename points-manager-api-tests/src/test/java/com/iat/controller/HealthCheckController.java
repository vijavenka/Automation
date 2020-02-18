package com.iat.controller;

import com.iat.contracts.HealthCheckContract;
import com.iat.utils.HMACHeader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class HealthCheckController {

    private HMACHeader hmacHeader = new HMACHeader();
    private HealthCheckContract healthCheckContract = new HealthCheckContract();

    private ValidatableResponse isHealthCheckStatusOKRequest(int status) {
        String date = hmacHeader.generateHttpDate();
        String path = healthCheckContract.getHealthCheckPath();

        log.info("Path: {}{}", RestAssured.baseURI, path);

        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse checkIfHealthCheckStatusOK(int status) {
        return isHealthCheckStatusOKRequest(status);
    }
}