package com.iat.controller;

import com.iat.Config;
import com.iat.contract.TransactionSearchContract;
import com.iat.validator.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;

public class TransactionSearchController {

    private TransactionSearchContract transactionSearchContract = new TransactionSearchContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getTransactionSearchRequest(String network, String networkTransactionId, String userId, Long start, Long end, String orderBy, boolean ascending, int page, int pageSize, int status) {
        String path = Config.affiliateManagerUrl() + transactionSearchContract.getTransactionSearch(network, networkTransactionId, userId, start, end, orderBy, ascending, page, pageSize);
        System.out.println("\nPath: GET " + path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getTransactionSearch(String network, String networkTransactionId, String userId, Long start, Long end, String orderBy, boolean ascending, int page, int pageSize, int status) throws FileNotFoundException {
        ValidatableResponse validatableResponse = getTransactionSearchRequest(network, networkTransactionId, userId, start, end, orderBy, ascending, page, pageSize, status);
        contractValidator.validateResponseWithContract("/GET-200-transactionSearch.json", validatableResponse.extract().response().asString(), status);
        return validatableResponse;
    }
}