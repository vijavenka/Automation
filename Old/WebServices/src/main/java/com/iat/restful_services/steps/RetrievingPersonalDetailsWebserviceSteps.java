package com.iat.restful_services.steps;

import com.iat.restful_services.actions.RetrievingPersonalDetailsWebserviceActions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

/**
 * Created with IntelliJ IDEA.
 * User: LPanusz
 * Date: 11.06.13
 * Time: 09:09
 * To change this template use File | Settings | File Templates.
 */

public class RetrievingPersonalDetailsWebserviceSteps {

    RetrievingPersonalDetailsWebserviceActions personalDetailsWebserviceActions = new RetrievingPersonalDetailsWebserviceActions();

    /*************************************************/
    /* *** BACKGROUND - Common for all scenarios *** */
    /*************************************************/

    @Given("^I know who my ePoints user is$")
    public void I_know_who_my_ePoints_user_is() throws Throwable {
        personalDetailsWebserviceActions.verifyUserInDatabase();
    }

    /*************************************************/
    /*     *** BACKGROUND - Get all details ***      */
    /*************************************************/

    @When("^I make simple call to get his information$")
    public void I_make_simple_call_to_get_his_information() throws Throwable {
        personalDetailsWebserviceActions.callForUserDetails("john.doe@iatlimited.com", "accessKey");
    }

    @Then("^All non-sensitive details will be returned$")
    public void All_non_sensitive_details_will_be_returned() throws Throwable {
        personalDetailsWebserviceActions.verifyUserDetails();
        //TODO: CHECK REQUEST REGISTRATION IN DB
        //At the end of test clean users entities
        //personalDetailsWebserviceActions.removeEntriesForUserInDatabase();
    }

    /*************************************************/
    /* *** BACKGROUND - Get only crucial details *** */
    /*************************************************/

    @When("^I make call to get information from fields$")
    public void I_make_call_to_get_information_from_firstName_lastName_email() throws Throwable {
        String[] fields = {"lastName", "email", "firstName", "password"};
        personalDetailsWebserviceActions.callForSpecificUserDetails("john.doe@iatlimited.com", "accessKey", fields);
        //TODO: Provide bigger flexibility
    }

    @Then("^I will receive all requested details$")
    public void I_will_receive_all_requested_details() throws Throwable {
        String[] fields = {"lastName", "email", "firstName", "password"};
        personalDetailsWebserviceActions.verifySpecificUserDetails(fields);
    }

    /*************************************************/
    /* *** BACKGROUND - Common for all scenarios *** */
    /*************************************************/

    @When("^I make call to get information with invalid userId$")
    public void I_make_call_to_get_information_with_invalid_userId() throws Throwable {
        personalDetailsWebserviceActions.invalidCallForUserDetails("john.doe@iat.com", "accessKey");
    }

    @When("^I make call to get information with invalid accessKey$")
    public void I_make_call_to_get_information_with_invalid_accessKey() throws Throwable {
        personalDetailsWebserviceActions.invalidCallForUserDetails("john.doe@iatlimited.com", "Key-1");
    }

    @When("^I make call to get information about inactive user$")
    public void I_make_call_to_get_information_about_inactive_user() throws Throwable {
        personalDetailsWebserviceActions.invalidCallForUserDetails("eddie.doe@iatlimited.com", "accessKey");
    }

    @When("^I make call to get information of user from inactive client$")
    public void I_make_call_to_get_information_of_user_from_inactive_client() throws Throwable {
        personalDetailsWebserviceActions.invalidCallForUserDetails("john.doe@iatlimited.com", "accessKey1");
    }

    /*************************************************/
    /* *** BACKGROUND - Common for all scenarios *** */
    /*************************************************/

    @Then("^I will not receive any details$")
    public void I_will_not_receive_any_details() throws Throwable {
        personalDetailsWebserviceActions.verifyEmptyResponse();
    }

}
