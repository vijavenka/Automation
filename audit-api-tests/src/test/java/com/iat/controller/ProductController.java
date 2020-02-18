package com.iat.controller;

import com.iat.contracts.ProductContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ProductController {

    private ProductContract productContract = new ProductContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse getProductsListRequest(int code) {
        String path = productContract.productsPath();
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getProductsList(int code) {
        ValidatableResponse validatableResponse = getProductsListRequest(code);
        System.out.println("\nGet Products list RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        contractValidator.validateResponseWithContract("/product/200-get-products-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse createProductRequest(String jsonBody, int code) {
        String path = productContract.productsPath();
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

    public ValidatableResponse createProduct(String jsonBody, int code) {
        ValidatableResponse validatableResponse = createProductRequest(jsonBody, code);
        System.out.println("\nCreate Product RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/product/200-get-product.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());

        return validatableResponse;
    }


    private ValidatableResponse updateProductRequest(String jsonBody, int code) {
        String path = productContract.productsPath();
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

    public ValidatableResponse updateProduct(String jsonBody, int code) {
        ValidatableResponse validatableResponse = updateProductRequest(jsonBody, code);
        System.out.println("\nUpdate Product RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/product/200-get-product.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse getProductByIdRequest(String id, int code) {
        String path = productContract.productsByIdPath(id);
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getProductById(String id, int code) {
        ValidatableResponse validatableResponse = getProductByIdRequest(id, code);
        System.out.println("\nGet Product by id RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/product/200-get-product.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse deleteProductByIdRequest(String id, int code) {
        String path = productContract.productsByIdPath(id);
        System.out.println("\nPath: DELETE " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse deleteProductById(String id, int code) {
        ValidatableResponse validatableResponse = deleteProductByIdRequest(id, code);
        System.out.println("\nDelete Product by id RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        return validatableResponse;
    }


}