package com.iat.controller;

import com.iat.contracts.EcardsConfigContract;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertThat;

@Slf4j
public class EcardsConfigController {

    private EcardsConfigContract ecardsConfigContract = new EcardsConfigContract();
    private DataExchanger sessionId = DataExchanger.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse getEcardsSettingsForTypeRequest(String settingsType, int status) {
        String path = ecardsConfigContract.getEcardsSettingsPath(settingsType);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then().statusCode(status);

    }

    public ValidatableResponse getEcardsSettingsForType(String settingsType, int status) {
        ValidatableResponse validatableResponse = getEcardsSettingsForTypeRequest(settingsType, status);

        switch (settingsType) {
            case "reasons":
                contractValidator.validateResponseWithContract("config/GET-ecard-settings-reasons.json", validatableResponse, status);
                break;
            case "pointsSharing":
                contractValidator.validateResponseWithContract("config/GET-ecard-settings-points-sharing.json", validatableResponse, status);
                break;
            case "templates":
                contractValidator.validateResponseWithContract("config/GET-ecard-settings-templates.json", validatableResponse, status);
                break;
            case "milestones":
                contractValidator.validateResponseWithContract("config/GET-ecard-settings-milestones.json", validatableResponse, status);
                break;
            default:
                assertThat("Wrong settingsType!", settingsType, isOneOf("reasons", "pointsSharing", "templates", "milestones"));
                break;
        }

        return validatableResponse;
    }


    private ValidatableResponse setEcardsSettingsForTypeRequest(String settingsType, String jsonBody, int status) {
        String path = ecardsConfigContract.getEcardsSettingsPath(settingsType);

        log.info("Path: POST {}", path);
        log.info("BODY: {}", jsonBody);
        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse setEcardsSettingsForType(String settingsType, String jsonBody, int status) {
        ValidatableResponse validatableResponse = setEcardsSettingsForTypeRequest(settingsType, jsonBody, status);
        //if (status == 200) //empty response
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 200)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);
        return validatableResponse;
    }

}