package com.iat.controller.specsaversManagement;

import com.iat.contracts.SpecsaversContract;
import com.iat.utils.HMACHeader;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class SpecsaversController {

    private HMACHeader hmacHeader = new HMACHeader();
    private SpecsaversContract specsaversContract = new SpecsaversContract();

    public ValidatableResponse getReasonsForGroup(String shortName, String apiKey, String clientId, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = specsaversContract.getReasonsForGroupPath(shortName, apiKey, clientId);

        log.info("Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse getReasonsForPartner(String apiKey, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = specsaversContract.getReasonsForPartnerPath(apiKey);

        log.info("Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);

    }


    public ValidatableResponse getUsersInfoForPartner(String shortName, String apiKey, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = specsaversContract.getUsersInfoForPartnerPath(shortName, apiKey);

        log.info("Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse getReportOverviewForPartner(String shortName, String apiKey, String startDate, String endDate, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = specsaversContract.getReportOverviewForPartnerPath(shortName, apiKey, startDate, endDate);

        log.info("Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse getAwardedPointsForPartner(String shortName, String apiKey, String page, String pageSize, String startDate, String endDate, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = specsaversContract.getAwardedPointsForPartnerPath(shortName, apiKey, page, pageSize, startDate, endDate);

        log.info("Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getRedemeedPointsForPartner(String shortName, String apiKey, String page, String pageSize, String startDate, String endDate, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = specsaversContract.getRedemeedPointsForPartnerPath(shortName, apiKey, page, pageSize, startDate, endDate);

        log.info("Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse getNetsPointsForPartner(String shortName, String apiKey, String currency, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = specsaversContract.getNetsValuesForPartnerPath(shortName, apiKey, currency);

        log.info("Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }
}