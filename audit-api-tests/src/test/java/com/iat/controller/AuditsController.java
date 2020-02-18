package com.iat.controller;

import com.iat.contracts.AuditsContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class AuditsController {

    private AuditsContract auditsContract = new AuditsContract();
    private ContractValidator contractValidator = new ContractValidator();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();

    private ValidatableResponse getAuditsListRequest(int code) {
        String path = auditsContract.auditsPath();
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getAuditsList(int code) {
        ValidatableResponse validatableResponse = getAuditsListRequest(code);
        System.out.println("\nGet Audit list RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200)
            contractValidator.validateResponseWithContract("/audit/200-get-audits-list.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }


    private ValidatableResponse createAuditRequest(String jsonBody, int code) {
        String path = auditsContract.auditsPath();
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

    public ValidatableResponse createAudit(String jsonBody, int code) {
        ValidatableResponse validatableResponse = createAuditRequest(jsonBody, code);
        System.out.println("\nCreate Audit RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 201)
            contractValidator.validateResponseWithContract("/audit/200-get-audit.json", validatableResponse.extract().response().body().asString());

        return validatableResponse;
    }


    private ValidatableResponse deleteAuditByIdRequest(String id, int code) {
        String path = auditsContract.auditsByIdPath(id);
        System.out.println("\nPath: DELETE " + path + "\n");

        return given()
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse deleteAuditById(String id, int code) {
        ValidatableResponse validatableResponse = deleteAuditByIdRequest(id, code);
        System.out.println("\nDelete Audit RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        return validatableResponse;
    }

}