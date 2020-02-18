package com.iat.steps.purchase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.purchase.EpointsPurchaseActions;
import com.iat.domain.purchase.BuyEpoints;
import com.iat.domain.purchase.BuyEpointsDetails;
import com.iat.utils.ResponseContainer;
import com.iat.validators.purchase.EpointsPurchaseValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class EpointsPurchaseSteps {

    private ResponseContainer response;
    private ObjectMapper mapper = new ObjectMapper();
    private BuyEpoints buyEpoints;
    private EpointsPurchaseActions epointsPurchaseActions = new EpointsPurchaseActions();
    private EpointsPurchaseValidator epointsPurchaseValidator = new EpointsPurchaseValidator();


    @When("^User request '(.+?)' epoints buy call$")
    public void buyEpoints(String jsonBody) throws Throwable {
        buyEpoints = mapper.readValue(jsonBody, BuyEpoints.class);
        response = epointsPurchaseActions.purchaseEpoints(buyEpoints, 200);
    }

    @Then("^In response he will receive transaction details including tax, fee$")
    public void validateBuyEpointsCallResponse() throws Throwable {
        BuyEpointsDetails buyEpointsDetails = response.getAsObject(BuyEpointsDetails.class);
        epointsPurchaseValidator.validateBuyEpointsResponseParameters(buyEpoints, buyEpointsDetails);
    }

}