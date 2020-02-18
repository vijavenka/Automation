package com.iat.steps.userManagement;

import com.iat.actions.userDetails.UserPasswordActions;
import com.iat.utils.ResponseHolder;
import com.iat.validators.ErrorsValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserPasswordSteps {

    private UserPasswordActions userPasswordActions = new UserPasswordActions();
    private ResponseHolder responseHolder = ResponseHolder.getInstance();
    private ErrorsValidator errorsValidator = new ErrorsValidator();

    //Scenario Outline: User details - reset password mail
    @When("^User request to reset his account '(.+?)', '(.+?)' password$")
    public void resetUserAccountPassword(String email, String apiKey) throws Throwable {
        responseHolder.setResponse(userPasswordActions.resetUserAccountPassword(email, apiKey, 200));
    }

    @Then("^Reset password email properly resend$")
    public void checkIfResetEmailLinkWasSent() throws Throwable {
        assertThat("Reset password EMAIL was not resend", responseHolder.getResponse().toString(), is("true"));
    }

    //Scenario Outline: User details - reset password with invalid parameters
    @When("^User tries to reset his password with wrong parameters '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void tryResetUserAccountPasswordUsingInvalidParameters(String email, String apiKey, String active, String verified, int expResponseCode) throws Throwable {
        responseHolder.setResponse(userPasswordActions.resetUserAccountPassword(email, apiKey, expResponseCode));
    }

    @Then("^User password should remain due to '(\\d+)', '(.+?)', '(.+?)'$")
    public void checkIfResetOfUserPasswordFailed(int expResponseCode, String expErrorCode, String expErrorMsg) throws Throwable {
        errorsValidator.validateErrorResponse(responseHolder.getResponse(), expResponseCode, expErrorCode, expErrorMsg, true);
    }
}
