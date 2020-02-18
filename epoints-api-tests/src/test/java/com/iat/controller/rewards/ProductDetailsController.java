package com.iat.controller.rewards;

import com.iat.contracts.rewards.ProductDetailsContract;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class ProductDetailsController {

    private ProductDetailsContract productDetailsContract = new ProductDetailsContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getRedemptionItemDetailsRequest(String id, int status) {
        String path = productDetailsContract.getProductDetailsByIdPath(id);
        log.info("Product Details Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getRedemptionItemDetails(String id, int status) {
        ValidatableResponse validatableResponse = getRedemptionItemDetailsRequest(id, status);
        contractValidator.validateResponseWithContract("/rewards/GET-200-RedemptionItemDetails.json", validatableResponse, status);
        return validatableResponse;
    }


    private ValidatableResponse getRedemptionItemRelatedProductsRequest(String id, String page, String pageSize, int status) {
        String path = productDetailsContract.getRedemptionItemRelatedProductsPath(id, page, pageSize);
        log.info("Product Details Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getRedemptionItemRelatedProducts(String id, String page, String pageSize, int status) {
        ValidatableResponse validatableResponse = getRedemptionItemRelatedProductsRequest(id, page, pageSize, status);
        contractValidator.validateResponseWithContract("/rewards/GET-200-SimilarRedemptions.json", validatableResponse, status);
        return validatableResponse;
    }
}