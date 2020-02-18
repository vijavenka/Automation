package com.iat.steps.United.rewardsCriteria;

import com.iat.Config;
import com.iat.actions.United.rewardsCriteria.RewardsCriteriaActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.rewardsCriteria.RewardsCriteriaValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RewardsCriteriaSteps {

    private RewardsCriteriaActions rewardsCriteriaActions = new RewardsCriteriaActions();
    private RewardsCriteriaValidator rewardsCriteriaValidator = new RewardsCriteriaValidator();
    private ResponseContainer response;


    @When("^File '(.+?)' with rewards criteria list will be processed '(\\d+)'$")
    public void bulkUploadRewardsCriteriaFromFile(String googleDocId, int code) throws Throwable {
        response = rewardsCriteriaActions.bulkUploadRewardsCriteria(googleDocId, code);
    }

    @Then("^Rewards criteria will be properly saved in points manager$")
    public void validateRewardsCriteriaBulkUploadCorrectness() throws Throwable {
        rewardsCriteriaValidator.validateRewardsCriteriaBulkUploadCorrectness(Config.getBulkUploadRewardsCriteriaData());
    }

    @Then("^Tag will be created for created reward criteria$")
    public void validateRelatedTagCreation() throws Throwable {
        rewardsCriteriaValidator.validateIfNewTagsWereCreatedForRewardCriteria(Config.getBulkUploadRewardsCriteriaData());
    }

    @Then("^Correct error message will be returned for file upload '(.+?)', '(.+?)', '(.+?)'$")
    public void validateErrorMessageCorrectness(String error, String message, String items) throws Throwable {
        rewardsCriteriaValidator.validatePartnersBulkUploadErrorMessage(response, error, message, items);
    }

    @Then("^All added during bulk upload criteria will be rolled back$")
    public void checkIfProcessedRewardsCriteriaAreRolledBack() throws Throwable {
        rewardsCriteriaValidator.validateRewardsCriteriaWereRolledBack(Config.getBulkUploadRewardsCriteriaData());
    }

}