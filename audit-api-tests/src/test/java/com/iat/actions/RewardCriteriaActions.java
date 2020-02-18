package com.iat.actions;


import com.iat.controller.RewardCriteriaController;
import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import io.restassured.response.ValidatableResponse;


public class RewardCriteriaActions {

    private RewardCriteriaController rewardCriteriaController = new RewardCriteriaController();
    private AuditsActions auditsActions = new AuditsActions();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();


    public String getRewardCriteriaList(int code) {
        ValidatableResponse validatableResponse = rewardCriteriaController.getRewardCriteriaList(code);
        return validatableResponse.extract().response().asString();
    }


    public String getRewardCriteriaById(String id, int code) {
        ValidatableResponse validatableResponse = rewardCriteriaController.getRewardCriteriaById(id, code);
        return validatableResponse.extract().response().asString();
    }

    public String createRewardCriteria(String jsonBody, int code) {
        ValidatableResponse validatableResponse = rewardCriteriaController.createRewardCriteria(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String updateRewardCriteria(String jsonBody, int code) {
        ValidatableResponse validatableResponse = rewardCriteriaController.updateRewardCriteria(jsonBody, code);
        return validatableResponse.extract().response().asString();
    }

    public String deleteRewardCriteria(String id, int code) {
        ValidatableResponse validatableResponse = rewardCriteriaController.deleteRewardCriteria(id, code);
        return validatableResponse.extract().response().asString();
    }

}
