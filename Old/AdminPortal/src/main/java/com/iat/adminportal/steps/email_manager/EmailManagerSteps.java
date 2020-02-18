package com.iat.adminportal.steps.email_manager;

import com.iat.adminportal.executors.IExecutor;
import com.iat.adminportal.page_navigations.AdminPortalHomePageNavigation;
import com.iat.adminportal.page_navigations.email_manager.EmailManagerNavigation;
import com.iat.adminportal.steps.DataExchanger;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 14.03.14
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class EmailManagerSteps {

    DataExchanger dataExchanger = DataExchanger.getInstance();

    public IExecutor executor = dataExchanger.getExecutor();

    private EmailManagerNavigation emailNavi = new EmailManagerNavigation();
    private AdminPortalHomePageNavigation homeNavi = new AdminPortalHomePageNavigation();

    public EmailManagerSteps() {

    }


    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    // EMAIL MANAGEMENT - create email management list screen for "lists" (RD-1972).
    @When("^User look at admin portal main page$")
    public void User_look_at_admin_portal_main_page() throws Throwable {
        // leave empty
    }

    @Then("^He can see Email Management tab$")
    public void He_can_see_Email_Management_tab() throws Throwable {
        emailNavi.checkIfEmailManagementTabIsAvailable();
    }

    @When("^He click this tab$")
    public void He_click_this_tab() throws Throwable {
        homeNavi.goToPage("Email Manager");
    }

    @Then("^He will see email management list screen$")
    public void He_will_see_email_management_list_screen() throws Throwable {
        emailNavi.checkIfEmailManagementPageWasOpened();
    }

    @Then("^This page has all needed elements and information$")
    public void This_page_has_all_needed_elements_and_information() throws Throwable {
        emailNavi.checkContentOfEmailManagerPage();
    }

    //EMAIL MANAGEMENT - create email management create/edit screen for "lists" (RD-1984).
    @Then("^He can see all needed elements on popup window$")
    public void He_can_see_all_needed_elements_on_popup_window() throws Throwable {
        emailNavi.checkContenOfCreatingRulePopup();
    }

    @When("^Duplicated name Will be used$")
    public void Duplicated_name_Will_be_used() throws Throwable {
        emailNavi.enterRuleName("AutomatedTestDoNotEdit");
        emailNavi.enterRuleSchedule();
    }

    @When("^Save button pressed$")
    public void Save_button_pressed() throws Throwable {
        emailNavi.clickSaveRuleButton();
    }

    @Then("^Name alert will be shown$")
    public void Name_alert_will_be_shown() throws Throwable {
        emailNavi.checkVisibilityOfNameAlert();
    }

    // EMAIL MANAGEMENT - create/edit page list rule - " account status" (RD-2307).
    @Given("^Email manager is opened$")
    public void Email_manager_is_opened() throws Throwable {
        emailNavi.setActiveValueToFalse();
        homeNavi.goToPage("Email Manager");
    }

    @When("^User want to create new rule$")
    public void User_want_to_create_new_rule() throws Throwable {
        emailNavi.clickCreateNewRuleButton();
        go_sleep(1000); // popup opening animation
    }

    @Then("^He is able to add new account rule$")
    public void He_is_able_to_add_new_account_status_rule() throws Throwable {
        emailNavi.clickAddNewRuleButton();
    }

    @Then("^Account status rule is available$")
    public void Status_rule_is_available() throws Throwable {
        emailNavi.checkIfStatusRuleIsAvailable();
        emailNavi.closeCreatingRuleWindow();
    }

    @When("^User want to edit existing rule$")
    public void User_want_to_edit_existing_rule() throws Throwable {
        emailNavi.searchProperExistingRule("AutomatedTestDoNotEdit");
        go_sleep(500);
        emailNavi.clckEditRuleButton();
        go_sleep(1000); // popup opening animation
    }

    @Then("^He is able to edit existing rule and add account status$")
    public void He_is_able_to_edit_existing_rule_and_add_account_status() throws Throwable {
        emailNavi.removeAllCurrentRules();
        emailNavi.clickAddRuleButton();
        emailNavi.selectProperRuleOption("ACCOUNT_STATUS");
        emailNavi.selectFilterValueForAccountStstus();
    }

    @Then("^After saving information are saved correctly - account status$")
    public void After_saving_information_are_saved_correctly() throws Throwable {
        emailNavi.clickSaveRuleButton();
        go_sleep(1500);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.checkIfStatusRuleIsEnabled();
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - EMAIL MANAGEMENT - create/edit page list rule - " complete profile " (RD-2296).
    @Then("^Complete account rule is available$")
    public void Complete_account_rule_is_available() throws Throwable {
        emailNavi.checkIfCompleteProfileRuleIsAvailable();
        emailNavi.closeCreatingRuleWindow();
    }

    @Then("^He is able to edit existing rule and add complete profile rule$")
    public void He_is_able_to_edit_existing_rule_and_add_complete_profile_rule() throws Throwable {
        emailNavi.removeAllCurrentRules();
        emailNavi.clickAddRuleButton();
        emailNavi.selectProperRuleOption("PROFILE_COMPLETED");
        emailNavi.selectFilterValueForCompleteProfile();
    }

    @Then("^After saving information are saved correctly - complete profile$")
    public void After_saving_information_are_saved_correctly_complete_profile() throws Throwable {
        emailNavi.clickSaveRuleButton();
        go_sleep(1500);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.checkIfProfileCompletedRuleIsEnabled();
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - create/edit page list rule - "gender " (RD-2284).
    @Then("^Gender rule is available$")
    public void Gender_rule_is_available() throws Throwable {
        emailNavi.checkIfGenderRuleIsAvailable();
        emailNavi.closeCreatingRuleWindow();
    }

    @Then("^He is able to edit existing rule and add gender rule$")
    public void He_is_able_to_edit_existing_rule_and_add_gender_rule() throws Throwable {
        emailNavi.removeAllCurrentRules();
        emailNavi.clickAddRuleButton();
        emailNavi.selectProperRuleOption("GENDER");
        emailNavi.selectFilterValueForGender();
    }

    @Then("^After saving information are saved correctly - gender$")
    public void After_saving_information_are_saved_correctly_gender() throws Throwable {
        emailNavi.clickSaveRuleButton();
        go_sleep(1500);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.checkIfGenderdRuleIsEnabled();
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - create/edit page list rule - "logged in " (RD-2274) - three options
    @Then("^Logged in rule is available$")
    public void Logged_in_rule_is_available() throws Throwable {
        emailNavi.checkIfLoggedInRuleIsAvailable();
        emailNavi.closeCreatingRuleWindow();
    }

    @Then("^He is able to edit existing rule and add logged in rule$")
    public void He_is_able_to_edit_existing_rule_and_add_logged_in_rule() throws Throwable {
        emailNavi.removeAllCurrentRules();
        emailNavi.clickAddRuleButton();
        emailNavi.selectProperRuleOption("LAST_LOGGED_IN");
        emailNavi.selectFilterValueForLoggedIn();
    }

    @Then("^After saving information are saved correctly - logged in$")
    public void After_saving_information_are_saved_correctly_logged_in() throws Throwable {
        emailNavi.clickSaveRuleButton();
        go_sleep(1500);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.checkIfLoggedInRuleIsEnabled();
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - create/edit page list rule - "logged in " (RD-2274) - from to option
    @Then("^Logged from rule is available$")
    public void Logged_from_rule_is_available() throws Throwable {
        emailNavi.checkIfLoggedFromRuleIsAvailable();
        emailNavi.closeCreatingRuleWindow();
    }

    @Then("^He is able to edit existing rule and add logged from rule$")
    public void He_is_able_to_edit_existing_rule_and_add_logged_from_rule() throws Throwable {
        emailNavi.removeAllCurrentRules();
        emailNavi.clickAddRuleButton();
        emailNavi.selectProperRuleOption("LAST_LOGGED_IN_DATE");
        emailNavi.selectFilterValueForLoggedFrom();
    }

    @Then("^After saving information are saved correctly - logged from$")
    public void After_saving_information_are_saved_correctly_logged_from() throws Throwable {
        emailNavi.clickSaveRuleButton();
        go_sleep(1500);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.checkIfLoggedFromRuleIsEnabled();
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - create/edit page list rule - "created at"(RD-2264).
    @Then("^Created at rule is available$")
    public void Created_at_rule_is_available() throws Throwable {
        emailNavi.checkIfCreatedAtRuleIsAvailable();
        emailNavi.closeCreatingRuleWindow();
    }

    @Then("^He is able to edit existing rule and add created at rule$")
    public void He_is_able_to_edit_existing_rule_and_add_created_at_rule() throws Throwable {
        emailNavi.removeAllCurrentRules();
        emailNavi.clickAddRuleButton();
        emailNavi.selectProperRuleOption("CREATED_AT");
        emailNavi.selectCreatedAtForLoggedFrom();
    }

    @Then("^After saving information are saved correctly - created at$")
    public void After_saving_information_are_saved_correctly_created_at() throws Throwable {
        emailNavi.clickSaveRuleButton();
        go_sleep(1500);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.checkIfCreatedAtRuleIsEnabled();
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - create/edit page list rule - "created at"(RD-2264) - from/to options.
    @Then("^Created from rule is available$")
    public void Created_from_rule_is_available() throws Throwable {
        emailNavi.checkIfCreatedFromRuleIsAvailable();
        emailNavi.closeCreatingRuleWindow();
    }

    @Then("^He is able to edit existing rule and add created from rule$")
    public void He_is_able_to_edit_existing_rule_and_add_created_from_rule() throws Throwable {
        emailNavi.removeAllCurrentRules();
        emailNavi.clickAddRuleButton();
        emailNavi.selectProperRuleOption("CREATED_AT_DATE");
        emailNavi.selectCreatedFromForLoggedFrom();
    }

    @Then("^After saving information are saved correctly - created from$")
    public void After_saving_information_are_saved_correctly_created_from() throws Throwable {
        emailNavi.clickSaveRuleButton();
        go_sleep(1500);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.checkIfCreatedFromRuleIsEnabled();
        emailNavi.deleteRule();
    }

    //EMAIL MANAGEMENT - create/edit page list rule - "registration site" (RD-2317).
    @Then("^Registration site rule is available$")
    public void Registration_site_rule_is_available() throws Throwable {
        emailNavi.checkIfCreatedRegistrationSiteIsAvailable();
        emailNavi.closeCreatingRuleWindow();
    }

    @Then("^He is able to edit existing rule and add registration site rule$")
    public void He_is_able_to_edit_existing_rule_and_add_registration_site_rule() throws Throwable {
        emailNavi.removeAllCurrentRules();
        emailNavi.clickAddRuleButton();
        emailNavi.selectProperRuleOption("REGISTRATION_SITE");
        emailNavi.selectRegistrationSiteForLoggedFrom();
    }

    @Then("^After saving information are saved correctly - registration site$")
    public void After_saving_information_are_saved_correctly_registration_site() throws Throwable {
        emailNavi.clickSaveRuleButton();
        go_sleep(1500);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.checkIfRegistrationSiteRuleIsEnabled();
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - list screen functionality "Pause" (Rd-2254).
    @When("^User decide to stop some mailing rule$")
    public void Used_Decide_to_stop_some_mailing_rule() throws Throwable {
        emailNavi.searchProperExistingRule("AutomatedTestDoNotEdit");
        go_sleep(500);
    }

    @Then("^User click pause button next to chosen mailing rule$")
    public void User_click_pause_button_next_to_chosen_mailing_rule() throws Throwable {
        emailNavi.clickPauseActivateButton();
        go_sleep(1000);
        emailNavi.confirmDecision();
        go_sleep(3000);
    }

    @Then("^Value next to button is set to paused$")
    public void Value_next_to_button_is_set_to_paused() throws Throwable {
        emailNavi.checkIfPauseInformationisCorrect(false);
    }

    @Then("^Database field is update to false$")
    public void Database_field_is_update_to_false() throws Throwable {
        emailNavi.checkIfDatabaseFieldWasUpdated(false);
    }

    @When("^User want to start mailing rule again$")
    public void user_want_to_start_mailing_rule_again() throws Throwable {
        // leave empty
    }

    @Then("^User click start button next to chosen mailing rule$")
    public void User_click_start_button_next_to_chosen_mailing_rule() throws Throwable {
        emailNavi.clickPauseActivateButton();
        go_sleep(1000);
        emailNavi.confirmDecision();
        go_sleep(3000);
    }

    @Then("^Value next to button is set to active$")
    public void Value_next_to_button_is_set_to_active() throws Throwable {
        emailNavi.checkIfPauseInformationisCorrect(true);
    }

    @Then("^Database field is update to true$")
    public void Database_field_is_update_to_true() throws Throwable {
        emailNavi.checkIfDatabaseFieldWasUpdated(true);
    }

    // EMAIL MANAGEMENT - list screen functionality "Copy" (RD-2244).
    @When("^User want to copy existing rule$")
    public void User_want_to_copy_existing_rule() throws Throwable {
        emailNavi.chooseWhatShouldBeCopied();
    }

    @Then("^He should click copy button of chosen one$")
    public void He_should_click_copy_button_of_chosen_one() throws Throwable {
        emailNavi.clickCopyButtonOfChosen();
    }

    String name;

    @Then("^Enter new name$")
    public void Enter_new_name_and_schedule() throws Throwable {
        name = "AutomatedCopyTest" + executor.return_random_value(1000000);
        emailNavi.enterRuleName(name);
        Thread.sleep(2000);
    }

    @Then("^Click save button$")
    public void Click_save_button() throws Throwable {
        emailNavi.clickSaveRuleButton();
        Thread.sleep(1500);
    }

    @When("^User search for created rule$")
    public void User_search_for_created_rule() throws Throwable {
        emailNavi.searchProperExistingRule(name);
    }

    @Then("^He can see that rule was created correctly$")
    public void He_can_see_that_rule_was_created_correctly() throws Throwable {
        emailNavi.checkIfresultWasFound();
    }

    @Then("^Be sure that all rules were copied properly$")
    public void Be_sure_that_all_rules_were_copied_properly() throws Throwable {
        emailNavi.compareCopiedRuleWithOriginal();
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - created within option
    @Then("^Save created rule$")
    public void Save_created_rule() throws Throwable {
        emailNavi.clickSaveRuleButton();
    }

    @When("^User want to see email list according to created rule$")
    public void User_want_to_see_email_list_according_to_created_rule() throws Throwable {
        // leave empty
    }

    @Then("^He can press view button$")
    public void He_can_press_view_button() throws Throwable {
        emailNavi.clickViewButton();
        Thread.sleep(1000);
        emailNavi.set100PerPage();
        Thread.sleep(3000);
    }

    @Then("^Proper list will be displayed according to rule created within$")
    public void Proper_list_will_be_displayed() throws Throwable {
        emailNavi.checIfDisplayedListIsCorrectCreatedWithin();
        emailNavi.closeCreatingRuleWindow();
        go_sleep(1000);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - logged within option
    @Then("^Proper list will be displayed according to rule logged within$")
    public void Proper_list_will_be_displayed_according_to_rule_logged_within() throws Throwable {
        emailNavi.checIfDisplayedListIsCorrectLoggedWithin();
        emailNavi.closeCreatingRuleWindow();
        go_sleep(1000);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - account status option
    @Then("^Proper list will be displayed according to rule account status$")
    public void Proper_list_will_be_displayed_according_to_rule_account_status() throws Throwable {
        emailNavi.checIfDisplayedListIsCorrectAccountStatus();
        emailNavi.closeCreatingRuleWindow();
        go_sleep(1000);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - complete profile option
    @Then("^Proper list will be displayed according to rule complete profile rule$")
    public void Proper_list_will_be_displayed_according_to_rule_complete_profile_rule() throws Throwable {
        emailNavi.checIfDisplayedListIsCorrectCompleteProfile();
        emailNavi.closeCreatingRuleWindow();
        go_sleep(1000);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - gender option
    @Then("^Proper list will be displayed according to rule gender rule$")
    public void Proper_list_will_be_displayed_according_to_rule_gender_rule() throws Throwable {
        emailNavi.checIfDisplayedListIsCorrectGender();
        emailNavi.closeCreatingRuleWindow();
        go_sleep(1000);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - registration site option
    @Then("^Proper list will be displayed according to rule registration site rule$")
    public void Proper_list_will_be_displayed_according_to_rule_registration_site_rule() throws Throwable {
        emailNavi.checIfDisplayedListIsRegistrationSite();
        emailNavi.closeCreatingRuleWindow();
        go_sleep(1000);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - logged from/to option
    @Then("^Proper list will be displayed according to rule created from rule$")
    public void Proper_list_will_be_displayed_according_to_rule_created_from_rule() throws Throwable {
        emailNavi.checIfDisplayedListIsCreatedFrom();
        emailNavi.closeCreatingRuleWindow();
        go_sleep(1000);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.deleteRule();
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - logged from/to option
    @Then("^Proper list will be displayed according to rule logged from rule$")
    public void Proper_list_will_be_displayed_according_to_rule_logged_from_rule() throws Throwable {
        emailNavi.checIfDisplayedListIsLoggedFrom();
        emailNavi.closeCreatingRuleWindow();
        go_sleep(1000);
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.deleteRule();
    }

    //Admin Panel - email manager - mailing list - new rule (RD-4208)
    @Given("^Gender male rule is set$")
    public void Gender_male_rule_is_set() throws Throwable {
        emailNavi.searchProperExistingRule("AutomatedTestDoNotEdit");
        go_sleep(500);
        emailNavi.clckEditRuleButton();
        emailNavi.clickAddRuleButton();
        emailNavi.selectProperRuleOption("GENDER");
        emailNavi.selectMaleValueForGender();
        emailNavi.clickSaveRuleButton();
    }

    @Given("^Two specified emails are set as 'spam' and 'foreigner'$")
    public void Two_specified_emails_are_set_as_spam_and_foreigner() throws Throwable {
        emailNavi.clickViewButton();
        emailNavi.updateTwoEmailsAsSpamAndForeignInDatabase();
    }

    @Then("^On the list 'spam' and 'foreigner' email will not be included$")
    public void On_the_list_spam_and_foreigner_email_will_not_be_included() throws Throwable {
        emailNavi.closeViewListWindow();
        emailNavi.clickViewButton();
        emailNavi.checkIfOnListViewSpamAndForeignEmailsAreInvisible();
    }

    @When("^Used before emails 'spam' and 'foreigner' fields will be reset$")
    public void Used_before_emails_spam_and_foreigner_fields_will_be_reset() throws Throwable {
        emailNavi.updateTwoEmailsAsNoSpamAndForeignInDatabase();
    }

    @When("^List view will be opened once again$")
    public void List_view_will_be_opened_once_again() throws Throwable {
        emailNavi.closeViewListWindow();
        emailNavi.clickViewButton();
    }

    @Then("^Used before emails will be visible$")
    public void Used_before_emails_will_be_visible() throws Throwable {
        emailNavi.checkIfOnListViewNoSpamAndForeignEmailsAreVisible();
        emailNavi.closeViewListWindow();
        //deleting rule
        emailNavi.clckEditRuleButton();
        go_sleep(1500);
        emailNavi.clckEditRuleButton();
        emailNavi.deleteRule();
    }
}
