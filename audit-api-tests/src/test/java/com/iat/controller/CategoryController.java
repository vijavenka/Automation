package com.iat.controller;

import com.iat.contracts.CategoryContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class CategoryController {

    private CategoryContract categoryContract = new CategoryContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse getCategoriesListRequest(int code) {
        String path = categoryContract.categoriesPath();
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getCategoriesList(int code) {
        ValidatableResponse validatableResponse = getCategoriesListRequest(code);
        System.out.println("\nGet Categories list RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        contractValidator.validateResponseWithContract("/category/200-get-categories-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse createCategoryRequest(String jsonBody, int code) {
        String path = categoryContract.categoriesPath();
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

    public ValidatableResponse createCategory(String jsonBody, int code) {
        ValidatableResponse validatableResponse = createCategoryRequest(jsonBody, code);
        System.out.println("\nCreate Category RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/category/200-get-category.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());

        return validatableResponse;
    }


    private ValidatableResponse updateCategoryRequest(String jsonBody, int code) {
        String path = categoryContract.categoriesPath();
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

    public ValidatableResponse updateCategory(String jsonBody, int code) {
        ValidatableResponse validatableResponse = updateCategoryRequest(jsonBody, code);
        System.out.println("\nUpdate Category RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        contractValidator.validateResponseWithContract("/category/200-get-category.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse getCategoryByIdRequest(String id, int code) {
        String path = categoryContract.categoryByIdPath(id);
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getCategoryById(String id, int code) {
        ValidatableResponse validatableResponse = getCategoryByIdRequest(id, code);
        System.out.println("\nGet Category by id RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        contractValidator.validateResponseWithContract("/category/200-get-category.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse deleteCategoryByIdRequest(String id, int code) {
        String path = categoryContract.categoryByIdPath(id);
        System.out.println("\nPath: DELETE " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse deleteCategoryById(String id, int code) {
        ValidatableResponse validatableResponse = deleteCategoryByIdRequest(id, code);
        System.out.println("\nDelete Category by id RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        return validatableResponse;
    }


}