package com.iat.steps;

import com.iat.Config;
import com.iat.actions.LoginActions;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class LoginSteps {

    private LoginActions loginActions = new LoginActions();
    SessionIdKeeper bearer = SessionIdKeeper.getInstance();
    private ContractValidator contractValidator = new ContractValidator();
    String response;

    @When("^Audit admin user is logged in with credentials '(.+?)'$")
    public void iatAdminUserLogIn(String credentials) throws Throwable {
        if (credentials.equals("DEFAULT"))
            credentials = Config.iatCmsAdminCredentials;
        System.out.println("\n!!!!>>>> NEW TEST STARTED <<<<!!!!");
        loginActions.userLogIn(credentials);
    }

    @Then("^Audit admin login response should return bearer$")
    public void iatAdminUserLogInReturnedSessionID() throws Throwable {
        assertTrue("bearer is null", bearer.get().length() != 0);
    }

}