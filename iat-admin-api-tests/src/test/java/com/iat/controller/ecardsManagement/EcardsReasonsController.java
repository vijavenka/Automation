package com.iat.controller.ecardsManagement;

import com.iat.contracts.ecardsManagement.EcardsReasonsContract;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class EcardsReasonsController {

    private EcardsReasonsContract ecardsReasonsContract = new EcardsReasonsContract();
    private DataExchanger sessionId = DataExchanger.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse getEcardsReasonsListRequest(int status) {
        String path = ecardsReasonsContract.getEcardsReasonsPath();
        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsReasonsList(int status) {
        ValidatableResponse validatableResponse = getEcardsReasonsListRequest(status);
        if (status == 400 || status == 404)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("reasons/GET-200-ecard-reasons.json", validatableResponse, status);
        return validatableResponse;
    }


    private ValidatableResponse createEcardsReasonRequest(String jsonBody, int status) {
        String path = ecardsReasonsContract.getEcardsReasonsPath();
        log.info("Path: PUT {}", path);
        log.info("Body: PUT: {}", jsonBody);

        return given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse createEcardsReason(String jsonBody, int status) {
        ValidatableResponse validatableResponse = createEcardsReasonRequest(jsonBody, status);
        //if (status == 200 || status == 201) //just id returned nothing to validate in contract validator
        if (status == 400 || status == 404)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 200 && status != 201)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);

        return validatableResponse;
    }


    private ValidatableResponse getEcardsReasonByIdRequest(String id, int status) {
        String path = ecardsReasonsContract.getEcardsReasonsByIdPath(id);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsReasonById(String id, int status) {
        ValidatableResponse validatableResponse = getEcardsReasonByIdRequest(id, status);
        if (status == 400 || status == 404)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("reasons/GET-ecard-reasonById.json", validatableResponse, status);

        return validatableResponse;
    }


    private ValidatableResponse deleteEcardsReasonByIdRequest(String id, int status) {
        String path = ecardsReasonsContract.getEcardsReasonsByIdPath(id);

        log.info("Path: DELETE {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .delete(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse deleteEcardsReasonById(String id, int status) {
        ValidatableResponse validatableResponse = deleteEcardsReasonByIdRequest(id, status);
        //if (status == 200 || status == 201) //no response
        if (status == 400 || status == 404)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 200 && status != 201)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);

        return validatableResponse;
    }

}