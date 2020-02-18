package com.iat.controller.redemptionOrderManagement;

import com.iat.contracts.RedemptionOrderContract;
import com.iat.utils.HMACHeader;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class RedemptionOrderController {

    private HMACHeader hmacHeader = new HMACHeader();
    private RedemptionOrderContract redemptionOrderContract = new RedemptionOrderContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse createRedemptionOrderRequest(String userId, String idType, String apiKey, String orderJsonObject, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = redemptionOrderContract.getCreateRedemptionOrderPath(userId, idType, apiKey);

        log.info("Path POST: {}", path);
        log.info("TEST BODY: {}" + orderJsonObject);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("POST", null, null, path, date))
                .header("X-IAT-Date", date)
                .body(orderJsonObject)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse createRedemptionOrder(String userId, String idType, String apiKey, String orderJsonObject, int status) {
        return createRedemptionOrderRequest(userId, idType, apiKey, orderJsonObject, status);
    }

    private ValidatableResponse getRedemptionOrderRequest(String apiKey, String orderId, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = redemptionOrderContract.getRedemptionOrderPath(apiKey, orderId);

        log.info("Path GET: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);

    }

    public ValidatableResponse getRedemptionOrder(String apiKey, String orderId, int status) {
        ValidatableResponse validatableResponse = getRedemptionOrderRequest(apiKey, orderId, status);
        if (status == 200)
            contractValidator.validateResponseWithContract("redemptionOrderById-response-schema.json", validatableResponse);
        return validatableResponse;
    }

    private ValidatableResponse getRecentlyRedeemedRequest(String apiKey, String region, String zone, String offset, String limit, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = redemptionOrderContract.getRecentlyRedeemedPath(apiKey, region, zone, offset, limit);

        log.info("Path GET: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getRecentlyRedeemed(String apiKey, String region, String zone, String offset, String limit, int status) {
        ValidatableResponse validatableResponse = getRecentlyRedeemedRequest(apiKey, region, zone, offset, limit, status);
        if (status == 200)
            contractValidator.validateResponseWithContract("recentlyRedeemed-response-schema.json", validatableResponse);
        return validatableResponse;
    }

    public ValidatableResponse getRedemptionOrderHistoryRequest(String userId, String idType, String apiKey, String ascending, String offset, String limit, String startDate, String endDate, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = redemptionOrderContract.getRedemptionOrderHistoryPath(userId, idType, apiKey, ascending, offset, limit, startDate, endDate);

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

    public ValidatableResponse getRedemptionOrderHistory(String userId, String idType, String apiKey, String ascending, String offset, String limit, String startDate, String endDate, int status) {
        ValidatableResponse validatableResponse = getRedemptionOrderHistoryRequest(userId, idType, apiKey, ascending, offset, limit, startDate, endDate, status);
        if (status == 200)
            contractValidator.validateResponseWithContract("redemptionOrderHistory-response-schema.json", validatableResponse, status);
        return validatableResponse;
    }
}