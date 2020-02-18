package com.iat.steps.userManagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.actions.userDetails.UserDetailsActions;
import com.iat.actions.userDetails.UserGroupActions;
import com.iat.domain.UserGroup;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Date;

import static java.lang.Thread.sleep;
import static java.time.temporal.ChronoUnit.MINUTES;
import static org.exparity.hamcrest.date.DateMatchers.within;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UserGroupSteps {

    private UserGroupActions userGroupActions = new UserGroupActions();
    private UserDetailsActions userDetailsActions = new UserDetailsActions();
    private ResponseContainer response;

    @When("^User sends adding group call with data \"(.+?)\" and \"([^\"]*)\"$")
    public void userSendsAddingGroupCallWithDataAnd(String jsonBody, String apiKey) throws Throwable {
        jsonBody = jsonBody.replace("$AUTO_TEST_GROUP", "AUTO_TEST_GROUP_" + DataExchanger.getInstance().getUuid());
        UserGroup userGroup = new ObjectMapper().readValue(jsonBody, UserGroup.class);

        response = userGroupActions.postGroup(DataExchanger.getInstance().getUuid(), apiKey, userGroup);
        sleep(2000);
        DataExchanger.getInstance().setUserGroup(userGroup);
    }

    @Then("^User's group is matching the one which was sent, apiKey: ([^\"]*)$")
    public void usersGroupIsMatchingTheOneWhichWasSent(String apiKey) throws Throwable {
        UserGroup expectedUserGroup = DataExchanger.getInstance().getUserGroup();

        UserGroup userGroupFromPost =
                response.getAsObject(UserGroup.class, "groups.find{it.externalIdType == '" +
                        expectedUserGroup.getExternalIdType() + "'}");

        assertThat("UserGroup retrieved from post's response should match the one which was sent!",
                userGroupFromPost, samePropertyValuesAs(expectedUserGroup));

        response = userGroupActions.getGroups(DataExchanger.getInstance().getUuid(), apiKey);
        UserGroup userGroupFromGet =
                response.getAsObject(UserGroup.class, "groups.find{it.externalIdType == '" +
                        expectedUserGroup.getExternalIdType() + "'}");

        assertThat("UserGroup retrieved from get's response should match the one which was sent!",
                userGroupFromGet, samePropertyValuesAs(expectedUserGroup));
    }

    @Then("^User's externalIdUnited parameter is set properly, apiKey: ([^\"]*)$")
    public void usersExternalIdUnitedParameterIsSetProperly(String apiKey) throws Throwable {
        String uuid = DataExchanger.getInstance().getUuid();
        UserGroup userGroup = DataExchanger.getInstance().getUserGroup();
        response = userDetailsActions.getUserAccountDetailsById(uuid, apiKey, 200);

        assertThat("Invalid externalIdUnited!", response.getString("externalIdUnited"), is(userGroup.getExternalId()));
    }

    @Then("^User's externalIdUnited parameter is not set, apiKey: ([^\"]*)$")
    public void userSExternalIdUnitedParameterIsNotSet(String apiKey) throws Throwable {
        String uuid = DataExchanger.getInstance().getUuid();
        response = userDetailsActions.getUserAccountDetailsById(uuid, apiKey, 200);

        assertThat("externalIdUnited should not be set!", response.getString("externalIdUnited"), isEmptyOrNullString());
    }

    @When("^User sends deleting that group call, apiKey: ([^\"]*)$")
    public void userSendsDeletingThatGroupCallApiKeyAccessKey(String apiKey) throws Throwable {
        response = userGroupActions.deleteGroup(DataExchanger.getInstance().getUuid(), apiKey, DataExchanger.getInstance().getUserGroup());
        sleep(2000);
    }

    @Then("^User's group status is properly changed, apiKey: ([^\"]*)$")
    public void usersGroupStatusIsProperlyChangedApiKeyAccessKey(String apiKey) throws Throwable {
        UserGroup expectedUserGroup = DataExchanger.getInstance().getUserGroup();
        expectedUserGroup.setStatus("deleted");

        UserGroup userGroupFromDelete =
                response.getAsObject(UserGroup.class, "groups.find{it.externalIdType == '" +
                        expectedUserGroup.getExternalIdType() + "'}");

        assertThat("Group deleted date should be close to current date!", userGroupFromDelete.getDeleteDate(), is(within(1, MINUTES, new Date())));
        expectedUserGroup.setDeleteDate(userGroupFromDelete.getDeleteDate());
        assertThat("UserGroup retrieved from post's response should match the one which was sent!",
                userGroupFromDelete, samePropertyValuesAs(expectedUserGroup));

        response = userGroupActions.getGroups(DataExchanger.getInstance().getUuid(), apiKey);
        UserGroup userGroupFromGet =
                response.getAsObject(UserGroup.class, "groups.find{it.externalIdType == '" +
                        expectedUserGroup.getExternalIdType() + "'}");

        assertThat("UserGroup retrieved from get's response should match the one which was sent!",
                userGroupFromGet, samePropertyValuesAs(expectedUserGroup));
    }

    @When("^User sends adding group call with incorrect data '(.+?)' and '(.+?)', '(\\d+)'$")
    public void requestGroupCreationForInvalidParameters(String jsonBody, String apiKey, int status) throws Throwable {
        jsonBody = jsonBody.replace("$AUTO_TEST_GROUP", "AUTO_TEST_GROUP_" + DataExchanger.getInstance().getUuid());
        UserGroup userGroup = new ObjectMapper().readValue(jsonBody, UserGroup.class);

        response = userGroupActions.postGroup(DataExchanger.getInstance().getUuid(), apiKey, userGroup, status);
        sleep(2000);
    }

    @Then("^Correct group creation error message will be returned '(.+?)', '(.+?)'$")
    public void validateErrorResponse(String error, String message) throws Throwable {
        assertThat("Wrong error ", response.getString("error"), is(error));
        assertThat("Wrong message ", response.getString("message"), is(message));
    }

    @When("^User sends deleting group call with incorrect data '(.+?)' and '(.+?)', '(\\d+)'$")
    public void requestGroupDeletionForInvalidParameters(String jsonBody, String apiKey, int status) throws Throwable {
        jsonBody = jsonBody.replace("$AUTO_TEST_GROUP", "AUTO_TEST_GROUP_" + DataExchanger.getInstance().getUuid());
        UserGroup userGroup = new ObjectMapper().readValue(jsonBody, UserGroup.class);

        response = userGroupActions.deleteGroup(DataExchanger.getInstance().getUuid(), apiKey, userGroup, status);
        sleep(2000);
    }
}
