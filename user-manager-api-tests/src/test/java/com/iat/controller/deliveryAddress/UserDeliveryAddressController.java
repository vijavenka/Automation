package com.iat.controller.deliveryAddress;

import com.iat.contracts.deliveryAddress.UserDeliveryAddressContract;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserDeliveryAddressController {

    private UserDeliveryAddressContract deliveryAddressContract = new UserDeliveryAddressContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse createDeliveryAddressRequest(String userId, String apiKey, String deliveryAddressParams, int status) {
        String path = deliveryAddressContract.getDeliveryAddressPath(userId, apiKey, "null", "null");
        String jsonBody = deliveryAddressContract.createDeliveryAddressRequestBody(deliveryAddressParams);
        log.info("Delivery Address Path: POST {}{}\nBody: {}", RestAssured.baseURI, path, jsonBody);

        return given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    private ValidatableResponse getDeliveryAddressRequest(String userId, String apiKey, String limit, String offset, int status) {
        String path = deliveryAddressContract.getDeliveryAddressPath(userId, apiKey, limit, offset);
        log.info("Delivery Address Path: POST {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse createDeliveryAddress(String userId, String apiKey, String deliveryAddressParams, int status) {
        return createDeliveryAddressRequest(userId, apiKey, deliveryAddressParams, status);
    }

    public ValidatableResponse getDeliveryAddress(String userId, String apiKey, String limit, String offset, int status) {
        ValidatableResponse validatableResponse = getDeliveryAddressRequest(userId, apiKey, limit, offset, status);
        contractValidator.validateResponseWithContract("deliveryAddress/deliveryAddressList-response-schema.json", validatableResponse, status);
        return validatableResponse;
    }

}