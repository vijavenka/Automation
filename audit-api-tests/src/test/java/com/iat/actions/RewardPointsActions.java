package com.iat.actions;

import com.iat.controller.RewardPointsController;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import gherkin.deps.com.google.gson.JsonArray;
import io.restassured.response.ValidatableResponse;

import java.util.Date;

public class RewardPointsActions {

    private RewardPointsController rewardPointsController = new RewardPointsController();
    private RewardCriteriaActions rewardCriteriaActions = new RewardCriteriaActions();
    private StoreActions storeActions = new StoreActions();
    private QuestionActions questionActions = new QuestionActions();
    private ProductActions productActions = new ProductActions();
    private BrandActions brandActions = new BrandActions();
    private SuppliersActions suppliersActions = new SuppliersActions();

    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    Date dateTime = new Date();


    public String getRewardPointsList(String params, int code) {
        ValidatableResponse validatableResponse = rewardPointsController.getRewardPointsList(params, code, true);
        return validatableResponse.extract().response().asString();
    }


    public String getRewardPointsListWithoutPrint(String params, int code) {
        ValidatableResponse validatableResponse = rewardPointsController.getRewardPointsList(params, code, false);
        return validatableResponse.extract().response().asString();
    }


    public String getRewardPointsById(String id, int code) {
        ValidatableResponse validatableResponse = rewardPointsController.getRewardPointsById(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String deleteRewardPointsById(String id, int code) {
        ValidatableResponse validatableResponse = rewardPointsController.deleteRewardPointsById(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String filterRewardPointsForAudit(String response, String auditId) {
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