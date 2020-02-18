package com.iat.controller;

import com.iat.contracts.AnswerContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class AnswerController {

    private AnswerContract answerContract = new AnswerContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getAnswersListRequest(String params, int code) {
        String path = answerContract.getAnswersPath(params);

        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then();
    }

    public ValidatableResponse getAnswersList(String params, int code, boolean print) {
        ValidatableResponse validatableResponse = getAnswersListRequest(params, code);

        if (print) {
            System.out.println("\nGet Answers list RESPONSE: ");
            validatableResponse.extract().response().prettyPrint();
        }

//        if (code == 200)
//            contractValidator.validateResponseWithContract("/audit/200-get-audits-list.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }


    private ValidatableResponse getAnswerByIdRequest(String id, int code) {
        String path = answerContract.getAnswersByIdPath(id);

        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getAnswerById(String id, int code) {
        ValidatableResponse validatableResponse = getAnswerByIdRequest(id, code);
        System.out.println("\nGet Answer by id RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
//        if (code == 200)
//            contractValidator.validateResponseWithContract("/audit/200-get-audits-list.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }


    private ValidatableResponse deleteAnswerByIdRequest(String id, int code) {
        String path = answerContract.getAnswersByIdPath(id);

        System.out.println("\nPath: DELETE " + path + "\n");

        return given()
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse deleteAnswerById(String id, int code) {
        ValidatableResponse validatableResponse = deleteAnswerByIdRequest(id, code);
        System.out.println("\nDelete answer by id RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
//        if (code == 200)
//            contractValidator.validateResponseWithContract("/audit/200-get-audits-list.json", validatableResponse.extract().response().body().asString());
        return validatableResponse;
    }

}