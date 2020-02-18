package com.iat.steps.configuration;

import com.iat.actions.configuration.ConfigurationActions;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ConfigurationSteps {

    private ConfigurationActions configurationActions = new ConfigurationActions();
    private ResponseContainer response;

    @When("^Config file '(.+?)' will be requested$")
    public void getConfigurationFile(String filePath) throws Throwable {
        response = configurationActions.getEpointsConfiguration(filePath, 200);
    }

    @Then("^File '(.+?)' is properly returned$")
    public void checkConfigurationFileCorrectness(String filePath) throws Throwable {
        assertThat("Returned file " + filePath + " is empty", response.toString(), not(isEmptyOrNullString()));
    }

    @When("^Config file '(.+?)' will be requested with wrong file path$")
    public void getConfigurationOfNOtExistingFile(String filePath) throws Throwable {
        response = configurationActions.getEpointsConfiguration(filePath, 404);
    }

    @Then("^Error message will be thrown with '(\\d+)', '(.+?)', '(.+?)'$")
    public void checkCorrectnessErrorMessage(int status, String error, String message) throws Throwable {
        assertThat("Wrong response status", response.getInt("status"), is(status));
        assertThat("Wrong response error", response.getString("error"), is(error));
        assertThat("Wrong response message", response.getString("message"), is(message));
    }
}