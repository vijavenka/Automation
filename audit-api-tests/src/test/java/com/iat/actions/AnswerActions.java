package com.iat.actions;

import com.iat.controller.AnswerController;
import com.iat.utils.JsonParserUtils;
import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.response.ValidatableResponse;

public class AnswerActions {

    private AnswerController answerController = new AnswerController();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();


    public String getAnswersList(String params, int code) {
        ValidatableResponse validatableResponse = answerController.getAnswersList(params, code, true);
        return validatableResponse.extract().response().asString();
    }

    public String getAnswersListWithoutPrint(String params, int code) {
        ValidatableResponse validatableResponse = answerController.getAnswersList(params, code, false);
        return validatableResponse.extract().response().asString();
    }

    public String getAnswerByIdProperly(String id, int code) {
        ValidatableResponse validatableResponse = answerController.getAnswerById(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String deleteAnswerByIdProperly(String id, int code) {
        ValidatableResponse validatableResponse = answerController.deleteAnswerById(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String filterAnswersForAudit(String response, String auditId) {
        JsonArray jsonArray = jsonParserUtils.convertStringToJsonArray(response);
        JsonArray filteredAnswersArray = new JsonArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonParserUtils.extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), "auditId").equals(auditId)) {
                filteredAnswersArray.add(jsonArray.get(i).getAsJsonObject());
            }
        }
        return filteredAnswersArray.toString();
    }
}