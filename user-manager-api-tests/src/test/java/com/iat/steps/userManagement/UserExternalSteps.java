package com.iat.steps.userManagement;


import com.iat.Config;
import com.iat.actions.userDetails.UserExternalActions;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserExternalSteps {

    private UserExternalActions userExternalActions = new UserExternalActions();
    private ResponseContainer response;
    private String externalId;

    @When("^User looks up for retailer with externalId ([^\"]*) of type ([^\"]*), apiKey: ([^\"]*), status: (\\d+)$$")
    public void userLooksUpForRetailerWithExternalIdOfTypeApiKey(String externalId, String externalIdType, String apiKey, int status) throws Throwable {
        this.externalId = Config.getExternalId(externalId);
        response = userExternalActions.getRetailer(this.externalId, externalIdType, apiKey, status);
    }

    @Then("^Retailer \"([^\"]*)\" is retrieved$")
    public void retailerIsRetrieved(String retailer) throws Throwable {
        String[] retailerTable = retailer.split(", ");
        String expectedFirstName = retailerTable[0];
        if (expectedFirstName.equals("null")) expectedFirstName = null;
        String expectedLastName = retailerTable[1];
        if (expectedLastName.equals("null")) expectedLastName = null;
        String expectedEmail = retailerTable[2];
        String expectedUserKey = retailerTable[3];

        assertThat("Invalid userKey!", response.getString("userKey"), is(expectedUserKey));
        assertThat("Invalid email!", response.getString("email"), is(expectedEmail));
        assertThat("Invalid firstName!", response.getString("firstName"), is(expectedFirstName));
        assertThat("Invalid lastName!", response.getString("lastName"), is(expectedLastName));
    }

    @Then("^Error message is retrieved \"([^\"]*)\" \"([^\"]*)\"$")
    public void errorMessageIsRetrieved(String error, String message) throws Throwable {
        message = message.replace("$EXTERNAL_ID", externalId);
        assertThat("Invalid error!", response.getString("error"), is(error));
        assertThat("Invalid error message!", response.getString("message"), is(message));
    }
}
