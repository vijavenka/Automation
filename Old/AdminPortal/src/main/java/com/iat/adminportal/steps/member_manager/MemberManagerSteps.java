package com.iat.adminportal.steps.member_manager;

import com.iat.adminportal.page_navigations.AdminPortalHomePageNavigation;
import com.iat.adminportal.page_navigations.member_manager.MemberManagerNavigation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 26.03.14
 * Time: 08:47
 * To change this template use File | Settings | File Templates.
 */
public class MemberManagerSteps {

    private MemberManagerNavigation memberNavi = new MemberManagerNavigation();
    private AdminPortalHomePageNavigation homeNavi = new AdminPortalHomePageNavigation();

    public MemberManagerSteps() {

    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    // MEMBER MANAGER - main page
    @Then("^He can see member management tab$")
    public void He_can_see_member_management_tab() throws Throwable {
        memberNavi.checkIfMemberManagerTabiIsAvailable();
    }

    @When("^He click members tab$")
    public void He_click_members_tab() throws Throwable {
        homeNavi.goToPage("Member Manager");
        go_sleep(2000);
    }

    @Then("^He will see member manager main page$")
    public void He_will_see_member_manager_main_page() throws Throwable {
        memberNavi.checkIfMemberManagerPageIsOpened();
    }

    @Then("^Member manager page has all needed elements$")
    public void Member_manager_page_has_all_needed_elements() throws Throwable {
        memberNavi.checkIfMemberManagerMainPageHasAllNeededElements();
    }

    // MEMBER MANAGER - create personnel details tab (RD-2035) - by Email
    @Given("^Member manager page is opened$")
    public void Member_manager_page_is_opened() throws Throwable {
        homeNavi.goToPage("Member Manager");
    }

    @When("^User will use search with email '(.+)'$")
    public void User_will_use_search_with_email(String email) throws Throwable {
        memberNavi.typeTextIntoSearch(email);
    }

    @When("^User will use search with uuid of email '(.+)'$")
    public void User_will_use_search_with_uuid_of_email(String email) throws Throwable {
        memberNavi.typeUUIDIntoSearch(email);
    }

    @When("^He want search using email$")
    public void He_want_search_using_email() throws Throwable {
        memberNavi.selectDDLsearchOption(true);
    }

    @When("^He press search button$")
    public void He_press_search_button() throws Throwable {
        go_sleep(1000);
        memberNavi.pressSearchButton();
    }

    @Then("^He can choose personal detail tab and results will be displayed$")
    public void He_can_choose_personal_detail_tab_and_results_will_be_displayed() throws Throwable {
        memberNavi.selectTab("Personal Detail");
        go_sleep(2000);
        memberNavi.checkIfSomeResultsWereDisplayed();
    }

    @Then("^Results are correct comparing with DB email on personal details tab '(.+)'$")
    public void Results_are_correct_comparing_with_DB_email(String email) throws Throwable {
        memberNavi.comparePersonalResultsWithDB(email, "email");
    }

    // MEMBER MANAGER - create personnel details tab (RD-2035) - by UUID
    @When("^He want search using uuid$")
    public void He_want_search_using_uuid() throws Throwable {
        memberNavi.selectDDLsearchOption(false);
    }

    @Then("^Results are correct comparing with DB uuid on personal details tab '(.+)'$")
    public void Results_are_correct_comparing_with_DB_uuid(String uuid) throws Throwable {
        memberNavi.comparePersonalResultsWithDB(uuid, "userKey");
    }

    // MEMBER MANAGER - create click out history tab (RD-2065) - by Email
    @Then("^He can choose clickout history tab and results will be displayed$")
    public void He_can_choose_clickout_history_tab_and_results_will_be_displayed() throws Throwable {
        memberNavi.selectTab("Clickout History");
        go_sleep(2000);
        //memberNavi.checkIfSomeResultsWereDisplayedOnClickoutHistoryTab();
    }

    @Then("^Results are correct comparing with DB email on clickout history tab '(.+)'$")
    public void Results_are_correct_comparing_with_DB_email_on_clickout_history_tab(String email) throws Throwable {
        memberNavi.setDisplayedResultsPerPage();
        go_sleep(4000);
        memberNavi.compareClickoutResultsWithDB(email, "email");
    }

    // MEMBER MANAGER - create click out history tab (RD-2065) - by UUID
    @Then("^Results are correct comparing with DB uuid on clickout history tab '(.+)'$")
    public void Results_are_correct_comparing_with_DB_uuid_on_clickout_history_tab(String uuid) throws Throwable {
        memberNavi.setDisplayedResultsPerPage();
        go_sleep(2000);
        memberNavi.compareClickoutResultsWithDB(uuid, "userKey");
    }

    // MEMBER MANAGER - create redemption history tab (RD-2055) - by Email
    @Then("^He can choose redemptions history tab and results will be displayed$")
    public void He_can_choose_redemptions_history_tab_and_results_will_be_displayed() throws Throwable {
        memberNavi.selectTab("Redemptions History");
        go_sleep(2000);
        //memberNavi.checkIfSomeResultsWereDisplayedOnRedemptionsHistoryTab();
    }

    @Then("^Results are correct comparing with DB email on redemptions history tab '(.+)'$")
    public void Results_are_correct_comparing_with_DB_email_on_redemptions_history_tab_emailwybitnietestowy_gmail_com(String email) throws Throwable {
        memberNavi.setDisplayedResultsPerPage();
        go_sleep(2000);
        memberNavi.compareRedemptionsResultsWithDB(email, "email");
    }

    // MEMBER MANAGER - create redemption history tab (RD-2055) - by uuid
    @Then("^Results are correct comparing with DB uuid on redemptions history tab '(.+)'$")
    public void Results_are_correct_comparing_with_DB_uuid_on_redemptions_history_tab(String uuid) throws Throwable {
        memberNavi.setDisplayedResultsPerPage();
        go_sleep(2000);
        memberNavi.compareRedemptionsResultsWithDB(uuid, "userKey");
    }

    // MEMBER MANAGER - create points history tab (RD-2045) - by Email
    @Then("^He can choose points history tab and results will be displayed$")
    public void He_can_choose_points_history_tab_and_results_will_be_displayed() throws Throwable {
        memberNavi.selectTab("Points History");
        go_sleep(2000);
        memberNavi.checkIfSomeResultsWereDisplayedOnpointsHistoryTab();
    }

    @Then("^Results are correct comparing with DB email on points history tab '(.+)'$")
    public void Results_are_correct_comparing_with_DB_email_on_points_history_tab_emailwybitnietestowy_gmail_com(String email) throws Throwable {
        memberNavi.setDisplayedResultsPerPage();
        go_sleep(2000);
        memberNavi.comparePointsResultsWithDB(email, "email");
    }

    // MEMBER MANAGER - create points history tab (RD-2045) - by UUID
    @Then("^Results are correct comparing with DB uuid on points history tab '(.+)'$")
    public void Results_are_correct_comparing_with_DB_uuid_on_points_history_tab(String uuid) throws Throwable {
        memberNavi.setDisplayedResultsPerPage();
        go_sleep(2000);
        memberNavi.comparePointsResultsWithDB(uuid, "userKey");
    }

    // EPOINTS - add deactivate user option to member manager (NBO-1).
    @When("^User check user deactivated checkbox for email '(.+)'$")
    public void user_check_user_deactivated_checkbox(String email) throws Throwable {
        memberNavi.clickUserDeactivatedCheckboxIfNeeded(email);
    }

    @Then("^Active flag will be set to '(.+?)' in points manager for email '(.+)'$")
    public void active_flag_will_be_set_to_in_points_manager(String activeFlag, String email) throws Throwable {
        memberNavi.checkIfActiveFlagWasProperlySet(activeFlag, email);
    }

    @When("^User uncheck user deactivated checkbox$")
    public void user_uncheck_user_deactivated_checkbox() throws Throwable {
        memberNavi.clickUserDeactivatedCheckbox();
    }
}
