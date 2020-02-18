package com.iat.restful_services.steps;

import com.iat.restful_services.actions.AddingConfirmedPointsWebserviceActions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AddingConfirmedPointsWebserviceSteps {
	private AddingConfirmedPointsWebserviceActions restfulServiceAction = new AddingConfirmedPointsWebserviceActions();

    @Given("^That user has an ePoints account$")
    public void userHasAnEPointsAccount() {
        restfulServiceAction.checkApplicationHealth();
    }

    @When("^The user performs some earning action that awards confirmed ePoints$")
    public void theUserPerformsEarningAction() {
        restfulServiceAction.performEarningAction(10, "1", "1", "BLOG", "CONFIRMED", "0", "1", "Test Message");
    }

    @Then("^His balance of confirmed ePoints will increase$")
    public void balanceOfConfirmedEPointsIncrease() {

    }
}
