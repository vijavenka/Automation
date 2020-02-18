package com.iat.controller;

import com.iat.contracts.BasketContract;
import com.iat.domain.basket.Basket;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class BasketController {

    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();
    private BasketContract basketContract = new BasketContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse updateBasketRequest(String uuid, Basket basket, int status) {
        String path = basketContract.userBasketPath(uuid);

        log.info("PUT Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .body(basket)
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    private ValidatableResponse updateBasketRequestOAuth(String uuid, Basket basket, String access_token, int status) {
        String path = basketContract.userBasketPath(uuid);

        log.info("PUT Path: {}{}", RestAssured.baseURI, path);

        Header header = null;
        if (!access_token.equals("null"))
            header = new Header("Authorization", "Bearer " + access_token);

        return given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(basket)
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse updateBasket(String uuid, Basket basket, String access_token, int status) {
        ValidatableResponse validatableResponse;
        if (access_token.equals("null"))
            validatableResponse = updateBasketRequest(uuid, basket, status);
        else
            validatableResponse = updateBasketRequestOAuth(uuid, basket, access_token, status);
        contractValidator.validateResponseWithContract("/users/GET-200-userBasket.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getBasketRequest(String uuid, int status) {
        String path = basketContract.userBasketPath(uuid);

        log.info("GET Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    private ValidatableResponse getBasketRequestOAuth(String uuid, String access_token, int status) {
        String path = basketContract.userBasketPath(uuid);

        log.info("GET Path: {}{}", RestAssured.baseURI, path);

        Header header = null;
        if (!access_token.equals("null"))
            header = new Header("Authorization", "Bearer " + access_token);

        return given()
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getBasket(String uuid, String access_token, int status) {
        ValidatableResponse validatableResponse;
        if (access_token.equals("null"))
            validatableResponse = getBasketRequest(uuid, status);
        else
            validatableResponse = getBasketRequestOAuth(uuid, access_token, status);
        contractValidator.validateResponseWithContract("/users/GET-200-userBasket.json", validatableResponse, status);
        return validatableResponse;
    }
}