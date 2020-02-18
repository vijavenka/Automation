package com.iat.steps;

import com.iat.utils.DataExchanger;
import com.iat.utils.JsonParserUtils;
import com.iat.validators.RewardPointsValidator;
import cucumber.api.java.en.Then;

public class RewardPointsSteps {

    private RewardPointsValidator rewardPointsValidator = new RewardPointsValidator();
    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
    DataExchanger dataExchanger = DataExchanger.getInstance();
    String response;

    @Then("^Reward points was properly stored for '(.+?)' audit when stores '(.+?)' retailers$")
    public void validateIfRewardPointsProperlyStored(String partnerShortName, String retailersAvailable) throws Throwable {
        boolean retailersAvailableFlag = retailersAvailable.equals("with") ? true : false;

//        if (partnerShortName.toLowerCase().contains("todays"))
//            rewardPointsValidator.validateTodaysProperRewardPointsRecorded(retailersAvailableFlag);
//        else
        rewardPointsValidator.validateProperRewardPointsRecorded(retailersAvailableFlag);
    }


//    @Then("^Reward points was updated with retailerId for '(.+?)' audit$")
//    public void validateIfRewardPointsProperlyUpdatedAfterAddRetailer(String partnerShortName) throws Throwable {
//        validateIfRewardPointsProperlyStored(partnerShortName, "with");
//    }
}