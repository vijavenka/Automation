package com.iat.controller.configuration;

import com.iat.contracts.configuration.ConfigurationContract;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class ConfigurationController {

    private ConfigurationContract configurationContract = new ConfigurationContract();

    private ValidatableResponse getEpointsConfigurationRequest(String filePath, int status) {
        String path = configurationContract.getConfigurationPath(filePath);
        log.info("Join Path: GET {}{}", RestAssured.baseURI, path);

        return given()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEpointsConfiguration(String filePath, int status) {
        return getEpointsConfigurationRequest(filePath, status);
    }

}