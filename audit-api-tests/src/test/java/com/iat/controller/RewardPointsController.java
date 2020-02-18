package com.iat.controller;

import com.iat.contracts.RewardPointsContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class RewardPointsController {

    private RewardPointsContract rewardPointsContract = new RewardPointsContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse getRewardPointsListRequest(String params, int code) {
        String path = rewardPointsContract.getRewardPointsPath(params);

        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getRewardPointsList(String params, int code, boolean print) {
        ValidatableResponse validatableResponse = getRewardPointsListRequest(params, code);
        if (print) {
            System.out.println("\nGet Reward Points list RESPONSE: ");
            validatableResponse.extract().response().prettyPrint();
        }
//        if (code == 200)
//            contractValidator.validateResponseWithContract("/audit/200-get-audits-list.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }


    private ValidatableResponse getRewardPointsByIdRequest(String id, int code) {
        String path = rewardPointsContract.getRewardPointsByIdPath(id);

        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getRewardPointsById(String id, int code) {
        ValidatableResponse validatableResponse = getRewardPointsByIdRequest(id, code);
        System.out.println("\nGet Reward Points by id RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();

//        if (code == 201)
//            contractValidator.validateResponseWithContract("/audit/200-get-audits-list.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }


    private ValidatableResponse deleteRewardPointsByIdRequest(String id, int code) {
        String path = rewardPointsContract.getRewardPointsByIdPath(id);

        System.out.println("\nPath: DELETE " + path + "\n");

        return given()
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse deleteRewardPointsById(String id, int code) {
        ValidatableResponse validatableResponse = deleteRewardPointsByIdRequest(id, code);
        System.out.println("\nDelete Reward Points RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();

//        if (code == 200)
//            contractValidator.validateResponseWithContract("/audit/200-get-audits-list.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }

}