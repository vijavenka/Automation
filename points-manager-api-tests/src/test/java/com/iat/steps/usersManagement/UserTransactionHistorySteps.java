package com.iat.steps.usersManagement;

import com.iat.actions.usersManagement.UserTransactionHistoryActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.usersManagement.UserTransactionHistoryValidator;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class UserTransactionHistorySteps {

    private UserTransactionHistoryActions userTransactionHistoryActions = new UserTransactionHistoryActions();
    private UserTransactionHistoryValidator userTransactionHistoryValidator = new UserTransactionHistoryValidator();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private ResponseContainer response;
    private ResponseContainer responseTemp;

    //Scenario Outline: User transaction history - get user transaction history for specified client
    @When("^Users transaction history will be pulled for specified client with following data '(.+?)'$")
    public void pullUserTransactionHistoryForSpecifiedClient(String requestParameters) throws Throwable {
        response = userTransactionHistoryActions.getUserTransactionHistoryByClient(requestParameters, 200);
    }

    @Then("^Users transaction history for specified client will be properly returned '(.+?)'$")
    public void checkIfUsersTransactionHistoryForSpecifiedClientWasReturned(String requestParameters) throws Throwable {
        response.validateContract("userTransactionHistory-response-schema.json", 200);
        userTransactionHistoryValidator.checkIfReturnedTransactionsByClientAreAsExpectedAccordingToParameters(requestParameters, response);
    }

    //Scenario Outline: User transaction history - get user transaction history for specified client using invalid parameters
    @When("^Users transaction history will be pulled for specified client with following invalid data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void pullUserTransactionHistoryForSpecifiedClientUsingInvalidParameters(String requestParameters, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = userTransactionHistoryActions.getUserTransactionHistoryByClient(requestParameters, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg);
    }

    @Then("^Users transaction history for specified client will not be properly returned$")
    public void checkIfUsersTransactionHistoryForSpecifiedClientWasNotReturned() throws Throwable {
        assertThat("User transactions were returned", response.toString(), isEmptyOrNullString());
    }

    //Scenario Outline: User transaction history - get user transaction history for specified group
    @When("^Users transaction history will be pulled for specified group with following data '(.+?)'$")
    public void pullUserTransactionHistoryForSpecifiedGroup(String requestParameters) throws Throwable {
        response = userTransactionHistoryActions.getUserTransactionHistoryByGroup(requestParameters, 200);
    }

    @Then("^Users transaction history for specified group will be properly returned '(.+?)'$")
    public void checkIfUsersTransactionHistoryForSpecifiedGroupWasReturned(String requestParameters) throws Throwable {
        response.validateContract("userTransactionHistory-response-schema.json", 200);
        userTransactionHistoryValidator.checkIfReturnedTransactionsByGroupAreAsExpectedAccordingToParameters(requestParameters, response);
    }

    //Scenario Outline: User transaction history - get user transaction history for specified group using invalid parameters
    @When("^Users transaction history will be pulled for specified group with following invalid data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void pullUserTransactionHistoryForSpecifiedGroupUsingInvalidParameters(String requestParameters, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = userTransactionHistoryActions.getUserTransactionHistoryByGroup(requestParameters, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg);
    }

    @Then("^Users transaction history for specified group will not be properly returned$")
    public void checkIfUsersTransactionHistoryForSpecifiedGroupWasNotReturned() throws Throwable {
        assertThat("User transactions were returned", response.toString(), isEmptyOrNullString());
    }

    //Scenario Outline: User transaction history - get user rewards history
    @When("^User rewards history will be pulled with following data '(.+?)'$")
    public void pullUserRewardsHistory(String requestParameters) throws Throwable {
        response = userTransactionHistoryActions.getUserRewardsHistory(requestParameters, 200);
    }

    @Then("^Users rewards history will be properly returned '(.+?)'$")
    public void checkIfUsersRewardsHistoryWasReturned(String requestParameters) throws Throwable {
        response.validateContract("userTransactionHistory-response-schema.json", 200);
        userTransactionHistoryValidator.checkIfReturnedRewardsAreAsExpectedAccordingToParameters(requestParameters, response);
    }

    //Scenario Outline: User transaction history - get user rewards history using invalid parameters
    @When("^User rewards history will be pulled with following invalid data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void pullUserRewardsHistoryUsingInvalidParameters(String requestParameters, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = userTransactionHistoryActions.getUserRewardsHistory(requestParameters, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg);
    }

    @Then("^User rewards history will not be properly returned$")
    public void checkIfUserRewardsHistoryWasNotReturned() throws Throwable {
        assertThat("User rewards were returned", response.toString(), isEmptyOrNullString());
    }

    //Scenario Outline: User transaction history - get user all transactions history
    @When("^User transaction history will be pulled with following data '(.+?)'$")
    public void pullUserTransactionHistory(String requestParameters) throws Throwable {
        response = userTransactionHistoryActions.getUserTransactionHistory(requestParameters, 200);
    }

    @Then("^Users transactions history will be properly returned '(.+?)'$")
    public void checkIfUsersTransactionHistoryWasReturned(String requestParameters) throws Throwable {
        response.validateContract("userTransactionHistory-response-schema.json", 200);
        userTransactionHistoryValidator.checkIfReturnedTransactionsAreAsExpectedAccordingToParameters(requestParameters, response);
    }

    //Scenario Outline: User transaction history - get user all transactions history using invalid parameters
    @When("^User transaction history will be pulled with following invalid data '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void pullUserTransactionHistoryUsingInvalidParameters(String requestParameters, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = userTransactionHistoryActions.getUserTransactionHistory(requestParameters, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg);
    }

    @Then("^User transactions history will not be properly returned$")
    public void checkIfUsersTransactionHistoryWasNotReturned() throws Throwable {
        assertThat("User transactions were returned", response.toString(), isEmptyOrNullString());
    }

    //Scenario Outline: User transaction history - get user last transaction detaisls
    @Given("^All user transactions were pulled in createdAt desc order '(.+?)'$")
    public void pullUserTransactionHistoryCreatedAtDesc(String requestParameters) throws Throwable {
        responseTemp = userTransactionHistoryActions.getUserTransactionHistory(requestParameters, 200);
    }

    //Scenario Outline: User transaction history - get user last transaction details
    @Given("^Get last user transaction request will be done '(.+?)'$")
    public void pullUserLastTransactionDetails(String requestParameters) throws Throwable {
        String[] parameters = requestParameters.split(",");
        String requestParams = parameters[1] + "," + parameters[0] + "," + parameters[2] + "," + parameters[5];
        response = userTransactionHistoryActions.getUserLastTransactionHistory(requestParams, 200);
    }

    @Then("^Last user transaction will be returned$")
    public void pullLastUserTransactions() throws Throwable {
        response.validateContract("userTransactionHistory-response-schema.json", 200);
    }

    @And("^Last user transaction data are as expected according to first element on transactions list '(.+?)'$")
    public void checkIfLastUserTransactionWasReturnedAndAreFromProperInterval(String requestParameters) throws Throwable {
        userTransactionHistoryValidator.checkIfAllLastTransactionsAreFromLastTransactionDate(responseTemp, response);
    }

    //Scenario Outline: User transaction history - get user last transaction details using invalid parameters
    @When("^Get last user transaction request will be done with invalid parameters '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void pullUserLastTransactionDetailsUsingInvalidParameters(String requestParameters, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = userTransactionHistoryActions.getUserLastTransactionHistory(requestParameters, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg);
    }

    @Then("^Last user transaction will not be returned$")
    public void checkIfLastUserTransactionWasNotReturned() throws Throwable {
        assertThat("Last transaction data was returned", response.toString(), isEmptyOrNullString());
    }

}