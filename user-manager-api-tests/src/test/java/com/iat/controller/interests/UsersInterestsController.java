package com.iat.controller.interests;

import com.iat.contracts.interests.UsersInterestsContract;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UsersInterestsController {

    private UsersInterestsContract usersInterestsContract = new UsersInterestsContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getUserInterestRequest(String userId, String userIdType, String apiKey, int status) {
        String path = usersInterestsContract.getUserInterestsPath(userId, userIdType, apiKey);
        log.info("GET Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status)
                .contentType(ContentType.JSON);
    }

    private ValidatableResponse postUserInterestRequest(String merchantId, String userId, String userIdType, String apiKey, int status) {
        String path = usersInterestsContract.getPostDeleteInterestsPath(merchantId, userId, userIdType, apiKey);
        log.info("POST Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .post(path)
                .then()
                .statusCode(status)
                .contentType(ContentType.JSON);
    }

    private ValidatableResponse deleteUserInterestRequest(String merchantId, String userId, String userIdType, String apiKey, int status) {
        String path = usersInterestsContract.getPostDeleteInterestsPath(merchantId, userId, userIdType, apiKey);
        log.info("DELETE Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .delete(path)
                .then()
                .statusCode(status)
                .contentType(ContentType.JSON);
    }

    public ValidatableResponse getListOfUserInterests(String userId, String userIdType, String apiKey, int status) {
        ValidatableResponse validatableResponse = getUserInterestRequest(userId, userIdType, apiKey, status);
        contractValidator.validateResponseWithContract("userInterests-response-schema.json", validatableResponse, status);
        return validatableResponse;
    }

    public ValidatableResponse postNewUserInterest(String merchantId, String userId, String userIdType, String apiKey, int status) {
        return postUserInterestRequest(merchantId, userId, userIdType, apiKey, status);
    }

    public ValidatableResponse deleteUserInterests(String merchantId, String userId, String userIdType, String apiKey, int status) {
        return deleteUserInterestRequest(merchantId, userId, userIdType, apiKey, status);
    }
}