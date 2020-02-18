package com.iat.controller;

import com.iat.contracts.StatisticsContract;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class StatisticsController {

    private StatisticsContract statisticsContract = new StatisticsContract();
    private DataExchanger sessionId = DataExchanger.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse getStatisticsConfigRequest(int status) {
        String path = statisticsContract.getStatisticsConfigPath();

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getStatisticsConfig(int status) {
        ValidatableResponse validatableResponse = getStatisticsConfigRequest(status);
        contractValidator.validateResponseWithContract("statistics/GET-200-statisticsConfig.json", validatableResponse, status);
        return validatableResponse;

    }


    private ValidatableResponse getStatisticByIdRequest(String statId, String dateRange, String groupBy, String filters, String previousPeriod, int status) {
        String path = statisticsContract.getStatisticsPath(statId, dateRange, groupBy, filters, previousPeriod);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getStatisticById(String statId, String dateRange, String groupBy, String filters, String previousPeriod, int status) {
        ValidatableResponse validatableResponse = getStatisticByIdRequest(statId, dateRange, groupBy, filters, previousPeriod, status);
        contractValidator.validateResponseWithContract("statistics/GET-200-statisticById.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse postEcardsUsageBreakdownExportRequest(String dateRange, int status) {
        String path = statisticsContract.getEcardsUsageBreakdownExportPath(dateRange, "null");

        log.info("Path: POST ecard usage breakdown export {}", path);

        return given()
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse postEcardsUsageBreakdownExport(String dateRange, int status) {
        return postEcardsUsageBreakdownExportRequest(dateRange, status);
    }

}