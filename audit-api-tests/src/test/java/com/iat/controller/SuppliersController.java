package com.iat.controller;

import com.iat.contracts.SuppliersContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class SuppliersController {

    private SuppliersContract suppliersContract = new SuppliersContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getSuppliersListRequest(int code) {
        String path = suppliersContract.suppliersPath();
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getSuppliersList(int code) {
        ValidatableResponse validatableResponse = getSuppliersListRequest(code);
        System.out.println("\nGet suppliers list RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
        contractValidator.validateResponseWithContract("/suppliers/200-get-suppliers-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse createSupplierRequest(String jsonBody, int code) {
        String path = suppliersContract.suppliersPath();
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

    public ValidatableResponse createSupplier(String jsonBody, int code) {
        ValidatableResponse validatableResponse = createSupplierRequest(jsonBody, code);
        System.out.println("\nCreate supplier RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/suppliers/200-get-supplier.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

    private ValidatableResponse updateSupplierRequest(String jsonBody, int code) {
        String path = suppliersContract.suppliersPath();
        System.out.println("\nPath: POST " + path + "\n");
        System.out.println("\nBODY: " + jsonBody + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .body(jsonBody)
                .when()
                .put(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse updateSupplier(String jsonBody, int code) {
        ValidatableResponse validatableResponse = updateSupplierRequest(jsonBody, code);
        System.out.println("\nCreate supplier RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/suppliers/200-get-supplier.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }

    private ValidatableResponse getSupplierByIdRequest(String id, int code) {
        String path = suppliersContract.suppliersByIdPath(id);
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getSupplierById(String id, int code) {
        ValidatableResponse validatableResponse = getSupplierByIdRequest(id, code);
        System.out.println("\nGet supplier RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/suppliers/200-get-supplier.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse deleteSupplierByIdRequest(String id, int code) {
        String path = suppliersContract.suppliersByIdPath(id);
        System.out.println("\nPath: DELETE " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse deleteSupplierById(String id, int code) {
        ValidatableResponse validatableResponse = deleteSupplierByIdRequest(id, code);
        System.out.println("\nDelete supplier RESPONSE:");
        validatableResponse.extract().response().prettyPrint();
        return validatableResponse;
    }
}