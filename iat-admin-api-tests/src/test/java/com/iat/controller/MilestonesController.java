package com.iat.controller;

import com.iat.contracts.MilestonesContract;
import com.iat.domain.EcardsConfig.milestones.MilestoneValue;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class MilestonesController {

    private MilestonesContract milestonesContract = new MilestonesContract();
    private DataExchanger sessionId = DataExchanger.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse getMilestonesTypeRequest(String milestoneType, int status) {
        String path = milestonesContract.getMilestonesPath(milestoneType);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then().statusCode(status);

    }

    public ValidatableResponse getMilestonesForType(String milestoneType, int status) {
        ValidatableResponse validatableResponse = getMilestonesTypeRequest(milestoneType, status);

        if (status == 200)
            contractValidator.validateResponseWithContract("milestones/GET-200-milestones.json", validatableResponse.extract().response().getBody().asString());
        else
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse.extract().response().getBody().asString());

        return validatableResponse;
    }

    private ValidatableResponse postMilestonesTypeRequest(String milestoneType, MilestoneValue milestoneValue, int status) {
        String path = milestonesContract.getMilestonesPath(milestoneType);

        log.info("Path: POST {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .body(milestoneValue)
                .when()
                .post(path)
                .then().statusCode(status);

    }

    public ValidatableResponse postMilestonesForType(String milestoneType, MilestoneValue milestoneValue, int status) {
        ValidatableResponse validatableResponse = postMilestonesTypeRequest(milestoneType, milestoneValue, status);
        if (status != 200)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);
        return validatableResponse;
    }

    private ValidatableResponse deleteMilestonesTypeRequest(String milestoneType, String milestoneId, int status) {
        String path = milestonesContract.getMilestonesByIdPath(milestoneType, milestoneId);

        log.info("Path: POST {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .delete(path)
                .then()
                .statusCode(status);

    }

    public ValidatableResponse deleteMilestonesForType(String milestoneType, String milestoneId, int status) {
        ValidatableResponse validatableResponse = deleteMilestonesTypeRequest(milestoneType, milestoneId, status);
        if (status != 200)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);
        return validatableResponse;
    }

    private ValidatableResponse getUsersForMilestonesWithType(String milestoneType, String allEvents, int status) {
        String path = milestonesContract.getUsersListForMilestonesWithTypePath(milestoneType, allEvents);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then().statusCode(status);

    }

    public ValidatableResponse getUsersListForMilestonesWithType(String milestoneType, String allEvents, int status) {
        ValidatableResponse validatableResponse = getUsersForMilestonesWithType(milestoneType, allEvents, status);
        contractValidator.validateResponseWithContract("milestones/GET-200-milestones-list.json", validatableResponse, status);
        return validatableResponse;
    }

}