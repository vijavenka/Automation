package com.iat.controller;

import com.iat.contracts.AuditResultContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.response.ValidatableResponse;

import java.io.File;

import static io.restassured.RestAssured.given;


public class AuditResultController {

    private AuditResultContract auditResultContract = new AuditResultContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse auditResultRequest(String chainId, String auditId, String fileName, int code) {
        String path = auditResultContract.getAuditResultPath(chainId, auditId);

        System.out.println("\nPath: POST " + path + "\n");

        return given()
                .multiPart("file", new File("src//bulkUploadFiles//" + fileName))
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .post(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse auditResult(String chainId, String auditId, String fileName, int code) {
        ValidatableResponse validatableResponse = auditResultRequest(chainId, auditId, fileName, code);
        System.out.println("\nAudit results processing RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
//        if (code == 201)
//            contractValidator.validateResponseWithContract("/audit/200-get-audits-list.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }

}