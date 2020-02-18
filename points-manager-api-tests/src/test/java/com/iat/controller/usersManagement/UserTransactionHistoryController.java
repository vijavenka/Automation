package com.iat.controller.usersManagement;

import com.iat.contracts.usersManagement.UserTransactionHistoryContract;
import com.iat.utils.HMACHeader;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Deque;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserTransactionHistoryController {

    private HMACHeader hmacHeader = new HMACHeader();
    private UserTransactionHistoryContract userTransactionHistoryContract = new UserTransactionHistoryContract();
    private ContractValidator contractValidator = new ContractValidator();

    public ValidatableResponse getUserTransactionHistoryByClient(String client, String apiKey, String user, String idType, Deque<String> otherParams, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = userTransactionHistoryContract.getUsersTransactionHistoryForCient(client, apiKey, user, idType, otherParams);
        log.info("PATH: {}", path);
        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUserTransactionHistoryByGroup(String group, String clientId, String apiKey, String user, String idType, Deque<String> otherParams, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = userTransactionHistoryContract.getUsersTransactionHistoryForGroup(group, clientId, apiKey, user, idType, otherParams);
        log.info("PATH: {}", path);
        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUserRewardsHistory(String apiKey, String user, String idType, Deque<String> otherParams, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = userTransactionHistoryContract.getUsersRewardsHistory(apiKey, user, idType, otherParams);
        log.info("PATH: {}", path);
        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUserTransactionHistoryRequest(String user, String apiKey, String idType, Deque<String> otherParams, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = userTransactionHistoryContract.getUserTransactionsHistory(user, apiKey, idType, otherParams);
        log.info("PATH: {}", path);
        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUserTransactionHistory(String user, String apiKey, String idType, Deque<String> otherParams, int status) {
        ValidatableResponse validatableResponse = getUserTransactionHistoryRequest(user, apiKey, idType, otherParams, status);
        if (status == 200)
            contractValidator.validateResponseWithContract("userTransactionHistory-response-schema.json", validatableResponse);
        return validatableResponse;
    }

    public ValidatableResponse getUserLastTransactionHistory(String apiKey, String user, String idType, String tag, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = userTransactionHistoryContract.getLastPointsSet(apiKey, user, idType, tag);
        log.info("PATH: {}", path);
        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

}