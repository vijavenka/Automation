package com.iat.steps.integration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.integration.BraintreeActions;
import com.iat.domain.integration.ConvertToEPoints;
import com.iat.utils.ResponseContainer;
import com.iat.validators.integration.BraintreeValidator;
import cucumber.api.java.en.Then;

public class BraintreeSteps {
    private BraintreeActions braintreeActions = new BraintreeActions();
    private ConvertToEPoints convertToEpoints;
    private ObjectMapper mapper = new ObjectMapper();
    private ResponseContainer response;
    private BraintreeValidator braintreeValidator = new BraintreeValidator();

    @Then("^Based on Braintree integration user will convert cash into epoints '(.+?)' , '(.+?)' , '(.+?)' for successful transaction$")
    public void getTrnsactionIdForSuccess(String jsonTransactionBody,int transactionStatus,String actualStatus) throws Throwable {
        convertToEpoints = mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES).readValue( jsonTransactionBody , ConvertToEPoints.class);
        response = braintreeActions.getTransactions(convertToEpoints, transactionStatus);
        if(transactionStatus == 200)
        braintreeValidator.validateBraintreeStatus(response.toString(),actualStatus);
    }

}
