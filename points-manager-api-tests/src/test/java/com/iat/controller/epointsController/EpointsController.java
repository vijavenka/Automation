package com.iat.controller.epointsController;

import com.iat.Config;
import com.iat.contracts.epointsContract.EpointsContract;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class EpointsController {

    private EpointsContract epointsContract = new EpointsContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getRedemptionProductListRequest(int status) {
        String path = Config.getEpointsUrl() + epointsContract.getEpointsRedemptionItemsList();

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getRedemptionProductList(int status) {
        ValidatableResponse validatableResponse = getRedemptionProductListRequest(status);
        contractValidator.validateResponseWithContract("epointsRedemptionList-response-schema.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getRedemptionIndividualProductRequest(String seoSlug, String productId, int status) {
        String path = Config.getEpointsUrl() + epointsContract.getEpointsRedemptionSingleItem(seoSlug, productId);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getRedemptionIndividualProduct(String seoSlug, String productId, int status) {
        ValidatableResponse validatableResponse = getRedemptionIndividualProductRequest(seoSlug, productId, status);
        contractValidator.validateResponseWithContract("epointsSingleRedemptionItem-response-schema.json", validatableResponse);
        return validatableResponse;
    }
}