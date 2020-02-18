package com.iat.controller;

import com.iat.contracts.RewardCriteriaContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class RewardCriteriaController {

    private RewardCriteriaContract rewardCriteriaContract = new RewardCriteriaContract();
    private ContractValidator contractValidator = new ContractValidator();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();

    private ValidatableResponse getRewardCriteriaListRequest(int code) {
        String path = rewardCriteriaContract.rewardCriteriaPath();
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getRewardCriteriaList(int code) {
        ValidatableResponse validatableResponse = getRewardCriteriaListRequest(code);
        System.out.println("\nGet Rewards Criteria list RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200)
            contractValidator.validateResponseWithContract("/rewardCriteria/200-get-reward-criteria.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }

    private ValidatableResponse getRewardCriteriaByIdRequest(String id, int code) {
        String path = rewardCriteriaContract.rewardCriteriaByIdPath(id);
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getRewardCriteriaById(String id, int code) {
        ValidatableResponse validatableResponse = getRewardCriteriaByIdRequest(id, code);
        System.out.println("\nGet Rewards Criteria by id RESPONSE: \n");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200)
            contractValidator.validateResponseWithContract("/rewardCriteria/200-get-reward-criteria.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }


    private ValidatableResponse createRewardCriteriaRequest(String jsonBody, int code) {
        String path = rewardCriteriaContract.rewardCriteriaPath();
        System.out.println("\nPath: POST " + path + "\n");
        System.out.println("\nBODY: " + jsonBody + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse createRewardCriteria(String jsonBody, int code) {
        ValidatableResponse validatableResponse = createRewardCriteriaRequest(jsonBody, code);
        System.out.println("\nCreate Rewards Criteria RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 201)
            contractValidator.validateResponseWithContract("/rewardCriteria/200-get-reward-criteria.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }


    private ValidatableResponse updateRewardCriteriaRequest(String jsonBody, int code) {
        String path = rewardCriteriaContract.rewardCriteriaPath();
        System.out.println("\nPath: POST " + path + "\n");
        System.out.println("\nBODY: " + jsonBody + "\n");
        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .body(jsonBody)
                .when()
                .put(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse updateRewardCriteria(String jsonBody, int code) {
        ValidatableResponse validatableResponse = updateRewardCriteriaRequest(jsonBody, code);
        System.out.println("\nUpdate Rewards Criteria RESPONSE: \n");
        validatableResponse.extract().response().prettyPrint();
        if (code == 201)
            contractValidator.validateResponseWithContract("/rewardCriteria/200-get-reward-criteria.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }


    private ValidatableResponse deleteRewardCriteriaRequest(String id, int code) {
        String path = rewardCriteriaContract.rewardCriteriaByIdPath(id);
        System.out.println("\nPath: DELETE " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then()
                .statusCode(code);

    }

    public ValidatableResponse deleteRewardCriteria(String id, int code) {
        ValidatableResponse validatableResponse = deleteRewardCriteriaRequest(id, code);
        System.out.println("\nDelete Rewards Criteria RESPONSE: \n");
        validatableResponse.extract().response().prettyPrint();
        return validatableResponse;
    }

}