package com.iat.steps.accountManagement;

import com.iat.Config;
import com.iat.actions.accountManagement.AccountActivationActions;
import com.iat.actions.accountManagement.AccountBalanceActions;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.utils.ResponseHolder;
import com.iat.validators.usersManagement.UserBalanceValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;

public class AccountBalanceSteps {

    private AccountBalanceActions accountBalanceActions = new AccountBalanceActions();
    private AccountActivationActions accountActivationActions = new AccountActivationActions();
    private UserRepository userRepository = new UserRepositoryImpl();
    private UserBalanceValidator userBalanceValidator = new UserBalanceValidator();
    private ResponseHolder responseHolder = ResponseHolder.getInstance();

    //Scenario Outline: Balance for active account - check of the call
    @Given("^'(.+?)' has active account with points$")
    public void getActivationStatusOfAccount(String email) throws Throwable {
        //Take user email and transform it to UUID to handle the account calls
        responseHolder.setUser(userRepository.findByEmail(email));
        assertThat("Account is not active!", accountActivationActions.getAccountActivationStatus(responseHolder.getUser().getUuid(), "UUID"));
    }

    @When("^Call for account balance is made '(.+?)'$")
    public void requestAccountBalance(String idType) throws Throwable {
        if (idType.equals("UUID"))
            responseHolder.setResponse(accountBalanceActions.getAccountBalance(responseHolder.getUser().getUuid(), idType, 200));
        else if (idType.equals("EMAIL"))
            responseHolder.setResponse(accountBalanceActions.getAccountBalance(responseHolder.getUser().getEmail(), idType, 200));
    }

    @Then("^Points balance should be returned$")
    public void validateAccountBalance() throws Throwable {
        userBalanceValidator.checkIfBalanceValuesAreCorrect(responseHolder.getResponse(), responseHolder.getUser().getUuid());
    }

    //Scenario Outline: Account balance for active account - invalid parameter values
    @When("^'(.+?)' makes account balance call with invalid parameters '(.+?)', '(.+?)', '(\\d+)'$")
    public void requestAccountBalanceUsingInvalidParams(String userId, String idType, String apiKey, int status) throws Throwable {
        responseHolder.setUserIdBasedOnType(userId, idType);
        responseHolder.setResponse(accountBalanceActions.getAccountBalance(userId, idType, apiKey.replace("envDepends", Config.getPartnerAccessKey()), status));
    }

    @Then("^His account balance should not be returned due to '(.+?)', '(.+?)'$")
    public void checkIfBalanceWasNotReturned(String expErrorCode, String expErrorMsg) throws Throwable {
        userBalanceValidator.checkIfBallanceWasNotReturned(expErrorCode, expErrorMsg);
    }
}