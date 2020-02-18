package com.iat.controller;

import com.iat.contracts.QuestionContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class QuestionController {

    private QuestionContract questionContract = new QuestionContract();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse getQuestionsListRequest(int code) {
        String path = questionContract.questionsPath();
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getQuestionsList(int code) {
        ValidatableResponse validatableResponse = getQuestionsListRequest(code);
        System.out.println("\nGet Questions list RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        contractValidator.validateResponseWithContract("/question/200-get-questions-list.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse createQuestionRequest(String jsonBody, int code) {
        String path = questionContract.questionsPath();
        System.out.println("\nPath: POST " + path + "\n");
        System.out.println("\nBody " + jsonBody + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse createQuestion(String jsonBody, int code) {
        ValidatableResponse validatableResponse = createQuestionRequest(jsonBody, code);
        System.out.println("\nCreate Question RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/question/200-get-question.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse updateQuestionRequest(String jsonBody, int code) {
        String path = questionContract.questionsPath();
        System.out.println("\nPath: PUT " + path + "\n");
        System.out.println("\nBody " + jsonBody + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse updateQuestion(String jsonBody, int code) {
        ValidatableResponse validatableResponse = updateQuestionRequest(jsonBody, code);
        System.out.println("\nUpdate Question RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/question/200-get-question.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse getQuestionByIdRequest(String id, int code) {
        String path = questionContract.questionsByIdPath(id);
        System.out.println("\nPath: GET " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .get(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse getQuestionById(String id, int code) {
        ValidatableResponse validatableResponse = getQuestionByIdRequest(id, code);
        System.out.println("\nGet Question by id RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        if (code == 200 || code == 201)
            contractValidator.validateResponseWithContract("/question/200-get-question.json", validatableResponse.extract().response().asString());
        else
            contractValidator.validateResponseWithContract("/ErrorResponse.json", validatableResponse.extract().response().asString());
        return validatableResponse;
    }


    private ValidatableResponse deleteQuestionByIdRequest(String id, int code) {
        String path = questionContract.questionsByIdPath(id);
        System.out.println("\nPath: DELETE " + path + "\n");

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + bearer.get())
                .when()
                .delete(path)
                .then()
                .statusCode(code);
    }

    public ValidatableResponse deleteQuestionById(String id, int code) {
        ValidatableResponse validatableResponse = deleteQuestionByIdRequest(id, code);
        System.out.println("\nDelete Question by id RESPONSE: ");
        validatableResponse.extract().response().prettyPrint();
        return validatableResponse;
    }

}