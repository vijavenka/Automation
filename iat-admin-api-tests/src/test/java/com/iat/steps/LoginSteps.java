package com.iat.steps;

import com.iat.actions.LoginActions;
import com.iat.actions.UsersActions;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.UsersValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class LoginSteps {

    private LoginActions loginActions = new LoginActions();
    private UsersActions usersActions = new UsersActions();
    private UsersValidator usersValidator = new UsersValidator();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ResponseContainer response;


    @When("^IAT Admin user is logged in with credentials '(.+?)'$")
    public void iatAdminUserLogIn(String credentials) throws Throwable {
        response = loginActions.userLogIn(credentials);
        dataExchanger.setSessionId(response.getCookie("SESSION"));
        System.out.println("SESSION=" + dataExchanger.getSessionId());
    }

    @When("^IAT Admin user is trying log in with credentials '(.+?)', '(.+?)'$")
    public void iatAdminUserLogIn(String credentials, int status) throws Throwable {
        response = loginActions.userLogIn(credentials, status);
        dataExchanger.setSessionId(response.getCookie("SESSION"));
        System.out.println("SESSION=" + dataExchanger.getSessionId());
    }

    @Then("^IAT Admin log in response returns error message '(.+?)', '(.+?)'$")
    public void authorizationValidateErrorResponse(String error, String message) throws Throwable {
        assertThat("Wrong error ", response.get("error"), is(error));
        assertThat("Wrong message ", response.get("message"), is(message));
    }


    @Then("^IAT Admin login response should return sessionID$")
    public void iatAdminUserLogInReturnedSessionID() throws Throwable {
        assertThat("SessionId is null", dataExchanger.getSessionId(), not(isEmptyOrNullString()));
    }

    @When("^IAT Admin request for his profile details$")
    public void getAdminProfile() throws Throwable {
        response = usersActions.getUserProfile();
    }

    @Then("^IAT Admin profile role are properly set '(.+?)'$")
    public void getAdminProfileMatchContract(String credentials) throws Throwable {
        usersValidator.validateUserProfile(credentials, response);
    }

    @Then("^IAT Admin profile response contains authority list$")
    public void getAdminProfileAuthorityValidation() throws Throwable {
        usersValidator.validateUserAuthorities(response);
    }


}
