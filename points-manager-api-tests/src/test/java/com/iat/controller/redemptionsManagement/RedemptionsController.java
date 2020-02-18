package com.iat.controller.redemptionsManagement;

import com.iat.contracts.RedemptionsContract;
import com.iat.utils.HMACHeader;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class RedemptionsController {

    private HMACHeader hmacHeader = new HMACHeader();
    private RedemptionsContract redemptionsContract = new RedemptionsContract();
    private ContractValidator contractValidator = new ContractValidator();

    public ValidatableResponse getRedemptions(String apiKey, String ascending, String orderField, String offset, String limit, String startDate, String endDate, String searchField, String keyword, String withAddictivityInfo, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = redemptionsContract.getRedemptionsPath(apiKey, ascending, orderField, offset, limit, startDate, endDate, searchField, keyword, withAddictivityInfo);

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

    public ValidatableResponse getRedemptionsById(String apiKey, String redemptionId, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = redemptionsContract.getRedemptionsByIdPath(apiKey, redemptionId);

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

    public ValidatableResponse createRedemptionsUpdate(String apiKey, String redemptionId, String fulfill, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = redemptionsContract.getRedemptionsByIdPath(apiKey, redemptionId);
        String requestBody = redemptionsContract.createRedemptionUpdateRequestBody(fulfill);

        log.info("Path: {}", path);
        log.info("TEST BODY: {}", requestBody);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("POST", null, null, path, date))
                .header("X-IAT-Date", date)
                .body(requestBody)
                .when()
                .post(path)
                .then()
                .statusCode(status);

    }

    private ValidatableResponse createRedemptionsRefund(String apiKey, String jsonBody, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = redemptionsContract.getRedemptionsRefundPath(apiKey);

        log.info("Path: {}", path);
        log.info("TEST BODY: {}", jsonBody);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("POST", null, null, path, date))
                .header("X-IAT-Date", date)
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse createRedemptionsRefundProperly(String apiKey, String jsonBody, int status) {
        ValidatableResponse validatableResponse = createRedemptionsRefund(apiKey, jsonBody, status);
        contractValidator.validateResponseWithContract("redemptionsRefund-response-schema.json", validatableResponse, status);
        return validatableResponse;
    }

}