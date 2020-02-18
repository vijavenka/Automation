package com.iat.steps.groupManagement;

import com.iat.actions.groupManagement.GroupActions;
import com.iat.utils.ResponseHolder;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static java.lang.String.format;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GroupSteps {

    private GroupActions groupActions = new GroupActions();
    private ResponseHolder responseHolder = ResponseHolder.getInstance();

    /*Get list of partners for specific group shortName - check response*/
    @When("^Get group partners request is made for group shortName '(.+?)'$")
    public void getGroupPartners(String shortName) throws Throwable {
        responseHolder.setResponse(groupActions.getGroupPartnersProperly(shortName, 200));
    }

    @Then("^Get group partners response for request match contract$")
    public void getGroupPartnersRequestMatchContract() throws Throwable {
        //contract validation checked in controller after refactoring
    }

    /*Get list of partners for specific group shortName - check errors messages*/
    @Given("^Get group partners request is made with incorrect group shortName '(.+?)' '(\\d+)'$")
    public void getGroupPartnersIncorrectGroupShortName(String shortName, int status) throws Throwable {
        responseHolder.setResponse(groupActions.getGroupPartnersProperly(shortName, status));
    }

    @Then("^Get group partners response contains proper error messages for incorrect group shortName '(.+?)' '(.+?)'$")
    public void getGroupPartnersIncorrectGroupShortNameErrorMessages(String shortName, String message) throws Throwable {
        assertThat("Incorrect error message!", responseHolder.getResponse().getString("message"), is(format("Invalid partnersgroup with name=[%s]", shortName)));
    }

}
