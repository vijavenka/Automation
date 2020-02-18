package com.iat.controller.points;

import com.iat.contracts.points.MerchantsContract;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class MerchantsController {

    private MerchantsContract merchantsContract = new MerchantsContract();
    private ContractValidator contractValidator = new ContractValidator();
    public ValidatableResponse validatableResponse;

    private ValidatableResponse getMerchantDetailsRequest(String merchantId, int status) {
        String path = merchantsContract.getMerchantDetailsPath(merchantId);
        log.info("Search Merchant Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getMerchantDetails(String merchantId, int status) {
        ValidatableResponse validatableResponse = getMerchantDetailsRequest(merchantId, status);
        contractValidator.validateResponseWithContract("/points/GET-200-MerchantDetails-schema.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getPromotedMerchantsDetailsRequest(String ids, int status) {
        String path = merchantsContract.getPromotedMerchantDetailsPath(ids);
        log.info("Promoted Merchant Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getPromotedMerchantsDetails(String ids, int status) {
        ValidatableResponse validatableResponse = getPromotedMerchantsDetailsRequest(ids, status);
        contractValidator.validateResponseWithContract("/points/GET-200-PromotedMerchant-schema.json", validatableResponse, status);
        return validatableResponse;
    }

}