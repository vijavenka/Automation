package com.iat.steps.usersManagement;

import com.iat.actions.usersManagement.UsersListingActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ErrorsValidator;
import com.iat.validators.usersManagement.UsersListingValidator;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersListingSteps {

    private UsersListingActions usersListingActions = new UsersListingActions();
    private UsersListingValidator usersListingValidator = new UsersListingValidator();
    private ErrorsValidator errorsValidator = new ErrorsValidator();
    private ResponseContainer response;

    //Scenario: Return users - check default limit, sorting and correctness of contract
    @When("^User search will be used without any parameter$")
    public void getDefaultUsersSearchResultsData() throws Throwable {
        response = usersListingActions.getUserSearchData("", "", "", "", 200);
    }

    @Then("^Users search request response data has proper structure$")
    public void checkIfUsersSearchContractHasCorrectStructure() throws Throwable {
        response.validateContract("usersListing-response-schema.json", 200);
    }

    @And("^(\\d+) users details data should be returned according to default limit$")
    public void validateIfReturnedUsersNumberIsAsExpected(int defaultLimit) throws Throwable {
        usersListingValidator.checkIfNumberOfReturnedUsersIsAsExpected(response, defaultLimit);
    }

    @And("^Returned user details data should be sorted by default in (.+?) (.+?) order$")
    public void checkIfUsersResultsAreCorrectlySortedByDefault(String sortField, String order) throws Throwable {
        usersListingValidator.checkIfUsersDataProperlySorted(response, sortField, order);
    }

    //Scenario Outline: Return users - check working of limit/sort parameters
    @When("^User search will be used with set '(.+?)', '(.+?)' and '(.+?)' with '(.+?)'$")
    public void getSortedUsersSearchResultsData(String limit, String sortField, String sortOrder, String offset) throws Throwable {
        response = usersListingActions.getUserSearchData(limit, sortField, sortOrder, offset, 200);
    }

    @And("^'(\\d+)' users details data should be returned according to set limit$")
    public void users_details_data_should_be_returned_according_to_set_limit(int limit) throws Throwable {
        usersListingValidator.checkIfNumberOfReturnedUsersIsAsExpected(response, limit);
    }

    @And("^Returned user details data should be sorted by '(.+?)' in '(.+?)' order$")
    public void checkIfUsersResultsAreCorrectlySorted(String sortField, String order) throws Throwable {
        usersListingValidator.checkIfUsersDataProperlySorted(response, sortField, order);
    }

    //Scenario Outline: Return users - check system response in case of not allowed parameter values
    @When("^Users list will be pulled with provided data '(.+?)', '(.+?)', '(.+?)', '(.+?)', '(\\d+)', '(.+?)', '(.+?)'$")
    public void getUsersListWithOneNotAllowedParameterAndCheckResponseCode(String limit, String sortField, String sortOrder, String offset, int status, String expErrorCode, String expErrorMsg) throws Throwable {
        response = usersListingActions.getUserSearchData(limit, sortField, sortOrder, offset, status);
        errorsValidator.validateHeaderErrorResponse(response, expErrorCode, expErrorMsg);
    }

    @Then("^Users list data should not be returned for provided data$")
    public void checkIfResponseContainsNoData() throws Throwable {
        assertThat("User list response data is not empty", response.toString(), anyOf(
                isEmptyOrNullString(),
                containsString("error")));
    }
}