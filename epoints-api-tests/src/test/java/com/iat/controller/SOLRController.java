package com.iat.controller;

import com.iat.contracts.SOLRContract;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class SOLRController {

    private SOLRContract solrContract = new SOLRContract();

    private ValidatableResponse getSolrRedemptionsByQueryRequest(String query, int status) {
        String path = solrContract.dealsByQueryPath(query);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getRedemptionsByQuery(String query, int status) {
        return getSolrRedemptionsByQueryRequest(query, status);
    }

    private ValidatableResponse getSolrVouchersByQuery(String voucherId, int status) {
        String path = solrContract.vouchersByIdQueryPath(voucherId);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getVouchersForQuery(String voucherId, int status) {
        return getSolrVouchersByQuery(voucherId, status);
    }

}