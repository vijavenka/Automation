package com.iat.steps;

import com.iat.actions.MerchantsActions;
import com.iat.actions.HealthCheckActions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class MerchantsSteps {

    private HealthCheckActions healthCheckActions = new HealthCheckActions();
    private MerchantsActions getMerchantsActions = new MerchantsActions();

    @Given("^Conent manager is responding to healthchecks$")
    public void isContentManagerHealthy() {
        healthCheckActions.checkIfContentManagerIsResponding();
    }

    @When("Call for merchants list is made")
    public void getAllMerchants() {
        getMerchantsActions.getListOfMerchants();
    }

    @Then("List of active merchants is returned")
    public void validateListOfMerchants() {

    }
}
