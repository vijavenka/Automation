package com.iat.steps.registration;

import com.iat.actions.registration.UserStoreVerificationActions;
import com.iat.utils.ResponseHolder;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserStoreVerificationSteps {

    private UserStoreVerificationActions userStoreVerificationActions = new UserStoreVerificationActions();
    private ResponseHolder responseHolder = ResponseHolder.getInstance();

    //Scenario Outline: User verification - new store/user verification
    @When("^User store verification call is made with following data '(.+?)', '(.+?)', '(\\d+)', '(.+?)'$")
    public void checkPossibilityOfNewUserStoreCreation(String userEmail, String userLastName, String partnerId, String apiKey) throws Throwable {
        responseHolder.setResponse(userStoreVerificationActions.verifyNewStore(userEmail, userLastName, partnerId, apiKey, 200));
    }

    @Then("^'(.+?)' will contain information about possibility of new user/store creation$")
    public void checkIfNewStoreCanBeCreated(String expectedResponse) throws Throwable {
        assertThat("Wrong validation response!", responseHolder.getResponse().toString().replaceAll("\n", "").trim(), is(expectedResponse));
    }

    //Scenario Outline: User registration - new store/user creation with invalid parameters
    @When("^User store verification call is made with incorrect data '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void checkPossibilityOfNewUserStoreCreationWithInvalidParametersData(String email, String lastName, String partnerId, String apiKey, int expResponseCode) throws Throwable {
        responseHolder.setResponse(userStoreVerificationActions.verifyNewStore(email, lastName, partnerId, apiKey, expResponseCode));
    }
}
