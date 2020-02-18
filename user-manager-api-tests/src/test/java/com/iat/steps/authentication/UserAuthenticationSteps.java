package com.iat.steps.authentication;


import com.iat.actions.authentication.UserAuthenticationActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.authentication.UserAuthenticationValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class UserAuthenticationSteps {

    private UserAuthenticationActions userAuthenticationActions = new UserAuthenticationActions();
    private UserAuthenticationValidator userAuthenticationValidator = new UserAuthenticationValidator();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private ResponseContainer response;
    private String accessTokenExtractedManually = "EAACZCYb4fFPEBAMgHS74Us0tQZBCYEt1S8dZByHZAJtOxdEUg8N5r4kvfeZCH82wZCZBZByM9Mzpca250LrrJ65wslyFcmmvU0sDSZCU4U65ZAYhls822T68IOHZBtqAFoY7ZCHkvoAGloGnc7e7kUMAZAZB2TCTb0qsU2ZCXxjsZBwWk9DIfPmM3kPhuyvJ7x8PZBPMZAeTPom1Co3hI2ey5jiuSrWjwbm6FRZAZC6tZCK0ZD";

    @When("^User is authorizing with following data '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void authorizeUserWithCredentials(String email, String password, String apiKey, int status) throws Throwable {
        response = userAuthenticationActions.authenticateUser(email, password, apiKey, status);
    }

    @Then("^Correct user '(.+?)' epoints uuid is returned$")
    public void validateUserUuid(String email) throws Throwable {
        assertThat("UUID was not returned!", response.getString("uuid"), is(notNullValue()));
        userAuthenticationValidator.checkUserUuidCorrectness(email, response.getString("uuid"));
    }

    @Then("^Following error response with following data is returned '(\\d+)', '(.+?)', '(.+?)'$")
    public void validateUserAuthenticationErrorResponse(int status, String error, String message) throws Throwable {
        errorsValidator.validateErrorResponse(response, status, error, message);
    }

    @When("^Epoints user log in with facebook '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void authorizeUserWithFacebook(String accessToken, String facebookId, String apiKey, int status) throws Throwable {
        if (accessToken.equals("extractedManuallyAndPasteInStepDefs"))
            accessToken = accessTokenExtractedManually;
        response = userAuthenticationActions.authenticateUserWithFacebook(accessToken, facebookId, apiKey, status);
    }

}