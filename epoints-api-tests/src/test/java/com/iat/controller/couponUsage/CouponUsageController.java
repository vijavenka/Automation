package com.iat.controller.couponUsage;

import com.iat.contracts.couponUsage.CouponUsageContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class CouponUsageController {

    private SessionIdKeeper sessionIdKeeper = SessionIdKeeper.getInstance();
    private CouponUsageContract couponUsageContract = new CouponUsageContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getCouponUsageRequest(String page, String size, int status) {
        String path = couponUsageContract.couponListPath(page, size);

        log.info("Coupon Usage Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionIdKeeper.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getCouponUsageList(String page, String size, int status) {
        ValidatableResponse validatableResponse = getCouponUsageRequest(page, size, status);
        contractValidator.validateResponseWithContract("/couponUsage/GET-coupon-usage.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse cashInRequest(String jsonBody, int status) {
        String path = couponUsageContract.cashInPath();

        log.info("Cash In Path: {}{}", RestAssured.baseURI, path);
        log.info("BODU: {}", jsonBody);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionIdKeeper.get())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse cashIn(String jsonBody, int status) {
        return cashInRequest(jsonBody, status);
    }

    private ValidatableResponse getCouponUsageSummaryRequest(int status) {
        String path = couponUsageContract.couponUsageSummaryPath();

        log.info("Coupon Usage Summary Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionIdKeeper.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getCouponUsagePointsSummary(int status) {
        ValidatableResponse validatableResponse = getCouponUsageSummaryRequest(status);
        contractValidator.validateResponseWithContract("/couponUsage/GET-coupon-usage-summary.json", validatableResponse, status);
        return validatableResponse;
    }
}