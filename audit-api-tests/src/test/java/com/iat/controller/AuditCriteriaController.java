package com.iat.controller;

import com.iat.contracts.AuditCriteriaContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class AuditCriteriaController {

    private AuditCriteriaContract auditCriteriaContract = new AuditCriteriaContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse createAuditCriteriaRequest(String jsonBody, int code) {
        String path = auditCriteriaContract.getAuditCriteriaPath();

        System.out.println("\nPath: POST " + path + "\n");
        System.out.println("\nBODY: " + jsonBody + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse createAuditCriteria(String jsonBody, int code) {
        ValidatableResponse validatableResponse = createAuditCriteriaRequest(jsonBody, code);
//        System.out.println("\nCreate audit criteria RESPONSE: ");
//        validatableResponse.extract().response().prettyPrint();
//        if (code == 201)
//            contractValidator.validateResponseWithContract("/audit/200-get-audits-list.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }


    private ValidatableResponse exportAuditCriteriaRequest(String jsonBody, int code) {
        String path = auditCriteriaContract.getAuditCriteriaExportPath();

        System.out.println("\nPath: POST " + path + "\n");
        System.out.println("\nBODY: " + jsonBody + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse exportAuditCriteria(String jsonBody, int code) {
        ValidatableResponse validatableResponse = exportAuditCriteriaRequest(jsonBody, code);
        System.out.println("\nExport audit criteria RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
//        if (code == 201)
//            contractValidator.validateResponseWithContract("/audit/200-get-audits-list.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }

}