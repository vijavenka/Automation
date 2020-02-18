package com.iat.controller.ecardsSection;

import com.iat.contracts.ecardsSection.EcardsContract;
import com.iat.domain.Ecards.EcardsSent;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class EcardsController {

    private EcardsContract ecardsContract = new EcardsContract();
    private ContractValidator contractValidator = new ContractValidator();
    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();

    private ValidatableResponse getCompanyActivityRequest(int status) {
        String path = ecardsContract.getEcardsCompanyActivityPath();

        log.info("Company Activity Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);

    }

    public ValidatableResponse getCompanyActivity(int status) {
        ValidatableResponse validatableResponse = getCompanyActivityRequest(status);
        contractValidator.validateResponseWithContract("/ecards/GET-ecards-companyActivity.json", validatableResponse, status);
        return validatableResponse;
    }


    private ValidatableResponse getEcardsHistoryRequest(String sentOrReceived, int status) {
        String path = ecardsContract.getEcardsHistoryPath(sentOrReceived);

        log.info("eCards History Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);

    }

    public ValidatableResponse getEcardsHistory(String sentOrReceived, int status) {
        ValidatableResponse validatableResponse = getEcardsHistoryRequest(sentOrReceived, status);
        if (sentOrReceived.equals("sent"))
            contractValidator.validateResponseWithContract("/ecards/GET-ecards-history-sent.json", validatableResponse, status);
        else
            contractValidator.validateResponseWithContract("/ecards/GET-ecards-history-received.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getIndividualEcardDetailsRequest(String ecardId, int status) {
        String path = ecardsContract.getIndividualEcard(ecardId);

        log.info("Individual eCard Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);

    }

    public ValidatableResponse getIndividualEcardDetails(String ecardId, int status) {
        ValidatableResponse validatableResponse = getIndividualEcardDetailsRequest(ecardId, status);
        contractValidator.validateResponseWithContract("/ecards/GET-ecards-individualEcard.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getEcardsUsersRequest(String search, int status) {
        String path = ecardsContract.getEcardsUsersPath(search);

        log.info("eCards User: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse getEcardsUsers(String search, int status) {
        ValidatableResponse validatableResponse = getEcardsUsersRequest(search, status);
        contractValidator.validateResponseWithContract("/ecards/GET-ecards-users-search.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getEcardsReasonsListRequest(int status) {
        String path = ecardsContract.getReasonsPath();

        log.info("eCards Reasons List Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsReasonsList(int status) {
        ValidatableResponse validatableResponse = getEcardsReasonsListRequest(status);
        contractValidator.validateResponseWithContract("/ecards/GET-ecards-reasons.json", validatableResponse, status);
        return validatableResponse;
    }


    private ValidatableResponse getEcardsTemplatesListRequest(int status) {
        String path = ecardsContract.getTemplatesPath();

        log.info("eCards Template List Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsTemplatesList(int status) {
        ValidatableResponse validatableResponse = getEcardsTemplatesListRequest(status);
        contractValidator.validateResponseWithContract("/ecards/GET-ecards-templates.json", validatableResponse, status);
        return validatableResponse;
    }


    private ValidatableResponse sendEcardRequest(EcardsSent ecardsSentObject, int status) {
        String path = ecardsContract.getSendEcardPath();

        log.info("Send eCard Path: {}{}", RestAssured.baseURI, path);
        log.info("BODY: {}", ecardsSentObject);

        return given()
                .contentType(ContentType.JSON)
                .body(ecardsSentObject)
                .sessionId("SESSION", sessionId.get())
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse sendEcard(EcardsSent ecardsSentObject, int status) {
        return sendEcardRequest(ecardsSentObject, status);
    }
}

