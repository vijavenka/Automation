package com.iat.actions;

import com.iat.controller.ProductController;
import com.iat.utils.JsonParserUtils;
import io.restassured.response.ValidatableResponse;


public class ProductActions {

    private ProductController productController = new ProductController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();


    public String getProductsList(int code) {
        ValidatableResponse validatableResponse = productController.getProductsList(code);
        return validatableResponse.extract().response().asString();
    }

    public String createProduct(String jsonBody, int code) {
        ValidatableResponse validatableResponse = productController.createProduct(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String updateProduct(String jsonBody, int code) {
        ValidatableResponse validatableResponse = productController.updateProduct(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String getProductById(String id, int code) {
        ValidatableResponse validatableResponse = productController.getProductById(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String deleteProductById(String id, int code) {
        ValidatableResponse validatableResponse = productController.deleteProductById(id, code);
        return validatableResponse.extract().response().asString();
    }


}