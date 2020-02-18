package com.iat.actions;

import com.iat.controller.CategoryController;
import com.iat.utils.JsonParserUtils;
import io.restassured.response.ValidatableResponse;

public class CategoryActions {

    private CategoryController categoryController = new CategoryController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();


    public String getCategoriesList(int code) {
        ValidatableResponse validatableResponse = categoryController.getCategoriesList(code);
        return validatableResponse.extract().response().asString();
    }

    public String createCategory(String jsonBody, int code) {
        ValidatableResponse validatableResponse = categoryController.createCategory(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String updateCategory(String jsonBody, int code) {
        ValidatableResponse validatableResponse = categoryController.updateCategory(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String getCategoryById(String id, int code) {
        ValidatableResponse validatableResponse = categoryController.getCategoryById(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String deleteCategoryById(String id, int code) {
        ValidatableResponse validatableResponse = categoryController.deleteCategoryById(id, code);
        return validatableResponse.extract().response().asString();
    }


}