package com.iat.actions;

import com.iat.controller.BrandController;
import com.iat.utils.JsonParserUtils;
import io.restassured.response.ValidatableResponse;

public class BrandActions {

    private BrandController brandController = new BrandController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();


    public String getBrandsList(int code) {
        ValidatableResponse validatableResponse = brandController.getBrandsList(code);
        return validatableResponse.extract().response().asString();
    }

    public String createBrand(String jsonBody, int code) {
        ValidatableResponse validatableResponse = brandController.createBrand(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String updateBrand(String jsonBody, int code) {
        ValidatableResponse validatableResponse = brandController.updateBrand(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String getBrandById(String id, int code) {
        ValidatableResponse validatableResponse = brandController.getBrandById(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String deleteBrandById(String id, int code) {
        ValidatableResponse validatableResponse = brandController.deleteBrandById(id, code);
        return validatableResponse.extract().response().asString();
    }

}