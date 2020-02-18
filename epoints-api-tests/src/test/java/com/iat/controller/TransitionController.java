package com.iat.controller;

import com.iat.Config;
import com.iat.contracts.TransitionContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class TransitionController {

    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();
    private TransitionContract transitionContract = new TransitionContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getTransitionToRequest(String merchantId, int status) {
        String path = transitionContract.getTransitionToPath(merchantId) + "&p1=api_automation";

        log.info("Transition-to Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);

    }

    public ValidatableResponse getTransitionTo(String merchantId, int status) {
        ValidatableResponse validatableResponse = getTransitionToRequest(merchantId, status);
        contractValidator.validateResponseWithContract("/transition/GET-transition-to.json", validatableResponse, status);
        return validatableResponse;
    }


    private ValidatableResponse getTransitionToLeadsRequest(String offerId, int status) {
        String path = transitionContract.getTransitionToLeadsPath(offerId);

        log.info("Transition-to for leads Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);

    }

    public ValidatableResponse getTransitionToLeads(String offerId, int status) {
        ValidatableResponse validatableResponse = getTransitionToLeadsRequest(offerId, status);
        contractValidator.validateResponseWithContract("/transition/GET-transition-to.json", validatableResponse, status);
        return validatableResponse;
    }


    private ValidatableResponse getClickoutRequest(String path, int status) {

        log.info("Clickout Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);

    }

    public ValidatableResponse getClickout(String path, int status) {
        return getClickoutRequest(path, status);
    }


    private ValidatableResponse triggerAffiliateManagerReportsForRequest(String networkCode, int status) {
        String path = "http://test-proxy-" + Config.testEnvironment + "-0.iatlimited.com:8925/test/run-out-of-schedule/" + networkCode;
        log.info("Trigger reports Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse triggerAffiliateManagerReportsFor(String networkCode, int status) {
        return triggerAffiliateManagerReportsForRequest(networkCode, status);
    }
}

