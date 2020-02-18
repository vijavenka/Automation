package com.iat.controller.ecardsManagement.ECardsTemplates;

import com.iat.contracts.ecardsManagement.ECardsTemplates.ECardsTemplatesContract;
import com.iat.domain.EcardsConfig.Template;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class ECardsTemplatesController {

    private ECardsTemplatesContract eCardsTemplatesContract = new ECardsTemplatesContract();
    private ContractValidator contractValidator = new ContractValidator();
    private DataExchanger sessionId = DataExchanger.getInstance();

    private ValidatableResponse listAllTemplatesRequest(int status) {
        String path = eCardsTemplatesContract.getAllTemplatesPath();

        log.info("Path: GET {}", path);

        return given().
                contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse listAllTemplates(int status) {
        ValidatableResponse validatableResponse = listAllTemplatesRequest(status);
        if (status == 200)
            contractValidator.validateResponseWithContract("templates/GETtemplates-response-schema.json", validatableResponse, status);
        else
            System.out.println("\n\nCONTRACT VALIDATION IS MISSING");
        return validatableResponse;
    }


    private ValidatableResponse listValidTemplatesRequest(int status) {
        String path = eCardsTemplatesContract.getValidTemplatesPath();

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse listValidTemplates(int status) {
        ValidatableResponse validatableResponse = listValidTemplatesRequest(status);
        if (status == 200)
            contractValidator.validateResponseWithContract("templates/GET valid-template-response-schema.json", validatableResponse, status);
        else
            System.out.println("\n\nCONTRACT VALIDATION IS MISSING");
        return validatableResponse;
    }


    private ValidatableResponse getTemplateRequest(String templateId, int status) {
        String path = eCardsTemplatesContract.getSingleTemplatesPath(templateId);

        log.info("Path: GET {}", path);

        return given().
                contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse getTemplate(String templateId, int status) {
        ValidatableResponse validatableResponse = getTemplateRequest(templateId, status);

        switch (status) {
            case 200:
                contractValidator.validateResponseWithContract("templates/GETtemplate-response-schema.json", validatableResponse);
                break;
            case 404:
                contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
                break;
            default:
                contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);
        }

        return validatableResponse;
    }


    private ValidatableResponse addNewTemplateRequest(Template template, int status) {
        String path = eCardsTemplatesContract.createTemplatesPath();

        log.info("Path: PUT {}", path);
        log.info("BODY: {}", template.toString());

        return given().
                contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .body(template)
                .put(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse addNewTemplate(Template template, int status) {
        ValidatableResponse validatableResponse = addNewTemplateRequest(template, status);
        //if status == 200   //id in response
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        return validatableResponse;
    }


    private ValidatableResponse updateTemplateRequest(String templateId, Template template, int status) {
        String path = eCardsTemplatesContract.updateTemplatesPath(templateId);

        log.info("Path: POST {}", path);
        log.info("BODY: {}", template.toString());

        return given().
                contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .body(template)
                .post(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse updateTemplate(String templateId, Template template, int status) {
        ValidatableResponse validatableResponse = updateTemplateRequest(templateId, template, status);
        //if status == 200   //id in response
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        return validatableResponse;
    }


    private ValidatableResponse deleteTemplateRequest(String templateId, int status) {
        String path = eCardsTemplatesContract.deleteTemplatesPath(templateId);

        log.info("Path: DELETE {}", path);

        return given().
                contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .delete(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse deleteTemplate(String templateId, int status) {
        ValidatableResponse validatableResponse = deleteTemplateRequest(templateId, status);
        //if status == 200   //no response
        if (status == 404)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 200)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);
        return validatableResponse;
    }
}