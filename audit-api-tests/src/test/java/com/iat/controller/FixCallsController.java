package com.iat.controller;

import com.iat.contracts.SuppliersContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class FixCallsController {

    private SuppliersContract suppliersContract = new SuppliersContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse fixMissingSuppliersPartnerIdsRequest(int code) {
        String path = "/api/fix/update-missing-supplier-partner-api-key";
        System.out.println("\nPath: POST " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .post(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse fixMissingSuppliersPartnerIds(int code) {
        ValidatableResponse validatableResponse = fixMissingSuppliersPartnerIdsRequest(code);
        System.out.println("\nFix missing supplier partner api key RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
        return validatableResponse;
    }


}