package com.iat.actions;

import com.iat.controller.QuestionController;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import io.restassured.response.ValidatableResponse;

public class QuestionActions {

    private QuestionController questionController = new QuestionController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();

    public String getQuestionsList(int code) {
        ValidatableResponse validatableResponse = questionController.getQuestionsList(code);
        return validatableResponse.extract().response().asString();
    }

    public String createQuestion(String jsonBody, int code) {
        ValidatableResponse validatableResponse = questionController.createQuestion(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String updateQuestion(String jsonBody, int code) {
        ValidatableResponse validatableResponse = questionController.updateQuestion(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String getQuestionById(String id, int code) {
        ValidatableResponse validatableResponse = questionController.getQuestionById(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String deleteQuestionById(String id, int code) {
        ValidatableResponse validatableResponse = questionController.deleteQuestionById(id, code);
        return validatableResponse.extract().response().asString();
    }

}