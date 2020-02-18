package com.iat.steps;

import com.iat.actions.HealthCheckActions;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HealthCheckSteps {

    private HealthCheckActions healthCheckActions = new HealthCheckActions();
    private ResponseContainer response;

    @Given("^Epoints API is up and running$")
    public void epointsIsUpAndRunning() {
        //nothing to do there
    }

    @When("^User makes healthcheck call$")
    public void getHealthCheckCall() {
        response = healthCheckActions.getHealthCHeck(200);
    }

    @Then("^He should receive 200 OK response$")
    public void verifyResponseFromEpointsAPI() {
        assertThat("Incorrect status line!", response.getStatusLine(), is("HTTP/1.1 200 OK"));
    }

    @Then("^Epoints API is responding to requests$")
    public void epointsIsResponding() {
        healthCheckActions.getHeartBeat(200);
    }
}
