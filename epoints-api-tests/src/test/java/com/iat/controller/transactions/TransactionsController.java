package com.iat.controller.transactions;

import com.iat.contracts.transactions.IdType;
import com.iat.contracts.transactions.TransactionsContract;
import com.iat.domain.transactions.PointsDetails;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class TransactionsController {

    private TransactionsContract transactionsContract = new TransactionsContract();

    private ValidatableResponse postTransactionsRequest(PointsDetails pointsDetails, String id, IdType idType, int status) {
        String path = transactionsContract.getTransactionsPath(id, idType);
        log.info("Award roulette prize Path: {}", path);
        log.info("BODY: {}", pointsDetails.toString());
        return given()
                .contentType(ContentType.JSON)
                .body(pointsDetails)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse postTransactions(PointsDetails pointsDetails, String id, IdType idType, int status) {
        return postTransactionsRequest(pointsDetails, id, idType, status);
    }
}