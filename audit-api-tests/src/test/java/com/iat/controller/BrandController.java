package com.iat.controller;

import com.iat.contracts.BrandContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class BrandController {

    private BrandContract brandContract = new BrandContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse getBrandsListRequest(int code) {
        String path = brandContract.brandsPath();
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getBrandsList(int code) {
        ValidatableResponse validatableResponse = getBrandsListRequest(code);
        System.out.println("\nGet Brands list RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        contractValidator.validateResponseWithContract("/brand/200-get-brands-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse createBrandRequest(String jsonBody, int code) {
        String path = brandContract.brandsPath();
        System.out.println("\nPath: POST " + path + "\n");
        System.out.println("\nBody " + jsonBody + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse createBrand(String jsonBody, int code) {
        ValidatableResponse validatableResponse = createBrandRequest(jsonBody, code);
        System.out.println("\nCreate Brand RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/brand/200-get-brand.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());

        return validatableResponse;
    }


    private ValidatableResponse updateBrandRequest(String jsonBody, int code) {
        String path = brandContract.brandsPath();
        System.out.println("\nPath: PUT " + path + "\n");
        System.out.println("\nBody " + jsonBody + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse updateBrand(String jsonBody, int code) {
        ValidatableResponse validatableResponse = updateBrandRequest(jsonBody, code);
        System.out.println("\nUpdate Brand RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/brand/200-get-brand.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());

        return validatableResponse;
    }


    private ValidatableResponse getBrandByIdRequest(String id, int code) {
        String path = brandContract.brandsByIdPath(id);
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getBrandById(String id, int code) {
        ValidatableResponse validatableResponse = getBrandByIdRequest(id, code);
        System.out.println("\nGet Brand by id RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/brand/200-get-brand.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());

        return validatableResponse;
    }


    private ValidatableResponse deleteBrandByIdRequest(String id, int code) {
        String path = brandContract.brandsByIdPath(id);
        System.out.println("\nPath: DELETE " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse deleteBrandById(String id, int code) {
        ValidatableResponse validatableResponse = deleteBrandByIdRequest(id, code);
        System.out.println("\nDelete Brand by id RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        return validatableResponse;
    }


}