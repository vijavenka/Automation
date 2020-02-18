package com.iat.controller.ecardsManagement;


import com.iat.contracts.ecardsManagement.EcardsPointsAllocationContract;
import com.iat.domain.EcardsConfig.PointsAllocations;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class EcardsPointsAllocationController {

    private EcardsPointsAllocationContract ecardsPointsAllocationContract = new EcardsPointsAllocationContract();
    private DataExchanger sessionId = DataExchanger.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse getEcardsPartnersRequest(int status) {
        String path = ecardsPointsAllocationContract.getEcardsPartnersPath();

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsPartners(int status) {
        ValidatableResponse validatableResponse = getEcardsPartnersRequest(status);
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("structure/200-ecard-partners.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getEcardsAllocationsStatsRequest(String level, String sortField, String ascending, String page,
                                                                 String maxResults, String who, String from, String to, String dateFrom,
                                                                 String dateTo, String description, String amount, String points, int status) {

        String path = ecardsPointsAllocationContract.getEcardsAllocationStatsPath(level, sortField, ascending,
                page, maxResults, who, from, to, dateFrom, dateTo, description, amount, points);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsAllocationsStats(String level, String sortField, String ascending, String page,
                                                         String maxResults, String who, String from, String to, String dateFrom,
                                                         String dateTo, String description, String amount, String points, int status) {
        ValidatableResponse validatableResponse = getEcardsAllocationsStatsRequest(level, sortField, ascending,
                page, maxResults, who, from, to, dateFrom, dateTo, description, amount, points, status);
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else {
            if (level.equals("partner"))
                contractValidator.validateResponseWithContract("ecards/GET-ecard-partner-allocations-stats.json", validatableResponse, status);
            else
                contractValidator.validateResponseWithContract("ecards/GET-ecard-department-allocations-stats.json", validatableResponse, status);
        }

        return validatableResponse;
    }


    private ValidatableResponse getEcardsAllocationLimitRequest(int status) {
        String path = ecardsPointsAllocationContract.getEcardsAllocationLimitPath();

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsAllocationLimit(int status) {
        ValidatableResponse validatableResponse = getEcardsAllocationLimitRequest(status);
        //if (status == 200 || status == 201) ////no json just Long value {
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 200 && status != 201)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);

        return validatableResponse;
    }

    private ValidatableResponse getEcardsAllocationLandingRequest(int status, String ToValidate) {
        String path = ecardsPointsAllocationContract.getEcardsAllocationLandingPath(ToValidate);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsAllocationLanding(int status, String ToValidate) {
        ValidatableResponse validatableResponse = getEcardsAllocationLandingRequest(status, ToValidate);
        //if (status == 200 || status == 201) ////no json just Long value {
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 200 && status != 201)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);

        return validatableResponse;
    }

    private ValidatableResponse getEcardsAllocationsRequest(String level, String sortField, String ascending, String page,
                                                            String maxResults, String who, String from, String to, String dateFrom,
                                                            String dateTo, String description, String amount, String points, int status) {

        String path = ecardsPointsAllocationContract.getEcardsAllocationPath(level, sortField, ascending,
                page, maxResults, who, from, to, dateFrom, dateTo, description, amount, points);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsAllocations(String level, String sortField, String ascending, String page, String maxResults,
                                                    String who, String from, String to, String dateFrom, String dateTo,
                                                    String description, String amount, String points, int status) {

        ValidatableResponse validatableResponse = getEcardsAllocationsRequest(level, sortField, ascending,
                page, maxResults, who, from, to, dateFrom, dateTo, description, amount, points, status);

        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else {
            if (level.equals("department"))
                contractValidator.validateResponseWithContract("ecards/GET-ecard-department-allocations.json", validatableResponse, status);
            if (level.equals("partner"))
                contractValidator.validateResponseWithContract("ecards/GET-ecard-partner-allocations.json", validatableResponse, status);
        }

        return validatableResponse;
    }


    private ValidatableResponse setEcardsAllocationsRequest(String level, PointsAllocations pointsAllocations, int status) {
        String path = ecardsPointsAllocationContract.setEcardsAllocationPath(level);

        log.info("Path: PUT {}", path);
        log.info("JSON_BODY: {}", pointsAllocations.toString());
        return given()
                .contentType(ContentType.JSON)
                .body(pointsAllocations)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse setEcardsAllocations(String level, PointsAllocations pointsAllocations, int status) {
        ValidatableResponse validatableResponse = setEcardsAllocationsRequest(level, pointsAllocations, status);
        //if (status == 200 || status == 201) //empty response
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 200 && status != 201)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);

        return validatableResponse;
    }


    private ValidatableResponse postEcardsAwardExportRequest(String dateFrom, String dateTo, int status) {
        String path = ecardsPointsAllocationContract.getEcardsAwardExportPath(dateFrom, dateTo, "null");

        log.info("Path: POST {}", path);

        return given()
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse postEcardsAwardExport(String dateFrom, String dateTo, int status) {
        ValidatableResponse validatableResponse = postEcardsAwardExportRequest(dateFrom, dateTo, status);
        //if (status == 202) //just filename returned
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 202)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);
        return validatableResponse;
    }

    private ValidatableResponse postEcardsAllocPartnerExportRequest(String dateFrom, String dateTo, int status) {
        String path = ecardsPointsAllocationContract.getEcardsAllocPartnerExport(dateFrom, dateTo, "null");

        log.info("Path: POST {}", path);

        return given()
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse postEcardsAllocPartnerExport(String dateFrom, String dateTo, int status) {
        ValidatableResponse validatableResponse = postEcardsAllocPartnerExportRequest(dateFrom, dateTo, status);
        //if (status == 202) //just filename returned
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 202)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);

        return validatableResponse;
    }

    private ValidatableResponse postEcardsAllocDepartmentExportRequest(String dateFrom, String dateTo, int status) {
        String path = ecardsPointsAllocationContract.getEcardsAllocDepartmentExport(dateFrom, dateTo, "null");

        log.info("Path: POST {}", path);

        return given()
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse postEcardsAllocDepartmentExport(String dateFrom, String dateTo, int status) {
        ValidatableResponse validatableResponse = postEcardsAllocDepartmentExportRequest(dateFrom, dateTo, status);
        //if (status == 202) //just filename returned
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 202)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);

        return validatableResponse;
    }

    private ValidatableResponse getExportFileRequest(String key, int status) {
        String path = ecardsPointsAllocationContract.getExportFile(key);

        log.info("Path: GET {}", path);

        return given()
                .contentType("application/download")
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getExportFile(String key, int status) {
        ValidatableResponse validatableResponse = getExportFileRequest(key, status);
        //if (status == 200 || status == 201) //xls file content
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 200 && status != 201)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);

        return validatableResponse;
    }

}