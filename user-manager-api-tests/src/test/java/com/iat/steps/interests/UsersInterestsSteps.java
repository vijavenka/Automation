package com.iat.steps.interests;

import com.iat.actions.interests.UsersInterestsActions;
import com.iat.utils.ResponseHolder;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.interests.UsersInterestsValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UsersInterestsSteps {

    private UsersInterestsActions usersInterestsActions = new UsersInterestsActions();
    private UsersInterestsValidator usersInterestsValidator = new UsersInterestsValidator();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private ResponseHolder responseHolder = ResponseHolder.getInstance();

    //TODO: Base all requests only on email of an user - pull uuid/facebook by email before request

    //Scenario Outline: Users interests - saving merchant in user interests
    @Given("^Merchant '(.+?)' is added to user '(.+?)', '(.+?)', '(.+?)' interests$")
    public void addMerchantIdToUserInterests(String merchantId, String userId, String userIdType, String apiKey) throws Throwable {
        responseHolder.setResponse(usersInterestsActions.addMerchantIdToUserInterests(merchantId, userId, userIdType, apiKey, 201));
        assertThat("Merchant was not added to user interests", responseHolder.getResponse().toString(), is("true"));
    }

    @When("^List of user '(.+?)', '(.+?)', '(.+?)' interests will be pulled$")
    public void returnCompleteListOfInterestsForSelectedUser(String userId, String userIdType, String apiKey) throws Throwable {
        responseHolder.setResponse(usersInterestsActions.getUserInterests(userId, userIdType, apiKey, 200));
    }

    @Then("^Liked before merchant '(.+?)' can be found on user '(.+?)', '(.+?)' interests list$")
    public void checkIfAddedMerchantIsOnListOfInterests(String merchantId, String userId, String userIdType) throws Throwable {
        usersInterestsValidator.checkIfMerchantWasReturnedOnInterestsList(responseHolder.getResponse().toString(), merchantId, true);
    }

    //Scenario Outline: Users interests - deleting merchants from user interests
    @When("^Merchant '(.+?)' will be deleted from user '(.+?)', '(.+?)', '(.+?)' interests$")
    public void deleteMerchantIdFromUserInterests(String merchantId, String userId, String userIdType, String apiKey) throws Throwable {
        responseHolder.setResponse(usersInterestsActions.deleteMerchantIdFromUserInterests(merchantId, userId, userIdType, apiKey, 200));
        assertThat("Merchant was not deleted from  user interests", responseHolder.getResponse().toString(), is("true"));
    }

    @Then("^Liked before merchant '(.+?)' cannot be found on user interests list$")
    public void checkIfDeletedMerchantIsNotAvailableOnListOfInterests(String merchantId) throws Throwable {
        usersInterestsValidator.checkIfMerchantWasReturnedOnInterestsList(responseHolder.getResponse().toString(), merchantId, false);
    }

    //Scenario: Users interests - not existing user provided
    @When("^'(.+?)' is trying to call for interests with '(.+?)', '(.+?)', '(\\d+)'$")
    public void pullUserInterestsForNotExistingUserId(String user, String idType, String apiKey, int responseCode) throws Throwable {
        responseHolder.setResponse(usersInterestsActions.getUserInterests(user, idType, apiKey, responseCode));
    }

    @Then("^Response with '(\\d+)', '(.+?)', '(.+?)' should be returned$")
    public void checkIfResponsMessageContainsValidError(int expResponseCode, String expErrorCode, String expErrorMsg) throws Throwable {
        errorsValidator.validateErrorResponse(responseHolder.getResponse(), expResponseCode, expErrorCode, expErrorMsg, true);
    }

    //Scenario Outline: Users interests - incorrect parameters for add
    @When("^User adds interests with invalid data '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void tryCreateUserInterestsEntityWithWrongData(String merchantId, String userId, String userIdType, String apiKey, int responseCode) throws Throwable {
        responseHolder.setResponse(usersInterestsActions.addMerchantIdToUserInterests(merchantId, userId, userIdType, apiKey, responseCode));
    }

    //Scenario Outline: Users interests - check behaviour of system when incorrect parameters data provided for deleting from interests list
    @When("^User deletes interests with invalid data '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)'$")
    public void tryDeleteUserInterestsEntityWithWrongData(String merchantId, String userId, String userIdType, String apiKey, int responseCode) throws Throwable {
        responseHolder.setResponse(usersInterestsActions.deleteMerchantIdFromUserInterests(merchantId, userId, userIdType, apiKey, responseCode));
    }

}