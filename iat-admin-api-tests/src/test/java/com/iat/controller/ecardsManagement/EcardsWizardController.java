package com.iat.controller.ecardsManagement;

import com.iat.contracts.ecardsManagement.EcardsWizardContract;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class EcardsWizardController {

    private EcardsWizardContract ecardsWizardContract = new EcardsWizardContract();
    private DataExchanger sessionId = DataExchanger.getInstance();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getEcardsWizardStatusRequest(int status) {
        String path = ecardsWizardContract.getEcardsWizardPath();

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsWizardStatus(int status) {
        ValidatableResponse validatableResponse = getEcardsWizardStatusRequest(status);
        contractValidator.validateResponseWithContract("ecards/GET-wizard.json", validatableResponse, status);

        return validatableResponse;
    }


    private ValidatableResponse setEcardsWizardStepRequest(String step, int status) {
        String path = ecardsWizardContract.setEcardsWizardStepPath();
        String jsonBody = "{\"wizardLastStep\": " + step + "}";

        log.info("Path: POST {}", path);
        log.info("Body: {}", jsonBody);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse setEcardsWizardStep(String step, int status) {
        ValidatableResponse validatableResponse = setEcardsWizardStepRequest(step, status);
        if (status != 200 && status != 201)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);

        return validatableResponse;
    }

}