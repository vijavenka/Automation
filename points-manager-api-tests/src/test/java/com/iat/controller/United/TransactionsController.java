package com.iat.controller.United;

import com.iat.contracts.United.TransactionsContract;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class TransactionsController {

    private TransactionsContract transactionsContract = new TransactionsContract();

    private ValidatableResponse processTransactionsRequest(String dataUrl, String externalId, String transactionDate, int status) {
        String path = transactionsContract.getTransactionsProcessingPath(dataUrl, externalId, transactionDate);

        log.info("POST Path: {}:{}{}", RestAssured.baseURI, RestAssured.port, path);

        return given()
                .urlEncodingEnabled(false)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse processTransactions(String dataUrl, String externalId, String transactionDate, int status) {
        return processTransactionsRequest(dataUrl, externalId, transactionDate, status);
    }

}