package com.iat.steps.activationStatus;

import com.iat.actions.HealthCheckActions;
import com.iat.actions.activationStatus.UserActivationStatusActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.activation.UserActivationValidator;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserActivationStatusSteps {

    private UserActivationStatusActions userActivationActions = new UserActivationStatusActions();
    private HealthCheckActions healthCheckActions = new HealthCheckActions();
    private UserActivationValidator userActivationValidator = new UserActivationValidator();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private ResponseContainer response;

    //Scenario Outline: User activation - check if user activation/deactivation works
    @Given("^User Manager API is responding to requests$")
    public void checkIfUserManagerIsResponding() throws Throwable {
        response = healthCheckActions.getHealthCheck(200);
        response.getValidatableResponse()
                .assertThat().statusLine(is("HTTP/1.1 200 OK"));
    }

    @When("^User '(.+?)', '(.+?)' activation status will be changed to '(.+?)'$")
    public void changeUserActivateStatus(String userIdString, String apiKey, String newActivateStatus) throws Throwable {
        response = userActivationActions.setNewUserActivationStatus(userIdString, "EMAIL", apiKey, newActivateStatus, 200);
        assertThat("Incorrect response to change of activation state!", response.toString(), is("true"));
    }

    @And("^'(.+?)' account activation status should be changed to '(.+?)'$")
    public void checkIfUserActivationIsSyncWithAccount(String userId, String activationStatus) {
        userActivationValidator.checkIfAccountActiveStateIsValid(userId, activationStatus);
    }

    //Scenario Outline: User activation status - not existing user or not allowed status parameters
    @When("^'(.+?)' tries to change user status with wrong parameters '(.+?)', '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void changeUserActivationStatusWithWrongParameter(String userId, String idType, String apiKey, String newActivateStatus, int status, String errorCode, String errorMsg) throws Throwable {
        response = userActivationActions.setNewUserActivationStatus(userId, idType, apiKey, newActivateStatus, status);
    }

    @Then("^User activation status should not be changed due to '(\\d+)', '(.+?)', '(.+?)'$")
    public void checkIfUserActivationStatusWasNotChanged(int status, String expErrorCode, String expErrorMsg) throws Throwable {
        errorsValidator.validateErrorResponse(response, status, expErrorCode, expErrorMsg, true);
    }
}