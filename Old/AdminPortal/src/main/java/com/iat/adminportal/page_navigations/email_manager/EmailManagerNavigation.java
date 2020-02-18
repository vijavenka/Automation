package com.iat.adminportal.page_navigations.email_manager;

import com.iat.adminportal.Database.JDBC;
import com.iat.adminportal.domain.UserDetails;
import com.iat.adminportal.locators.AdminPortalHomePageLocators;
import com.iat.adminportal.locators.email_manager.EmailManagerLocators;
import com.iat.adminportal.page_navigations.AbstractPage;
import com.iat.adminportal.repository.UserRepository;
import com.iat.adminportal.repository.impl.UserRepositoryImpl;
import com.iat.adminportal.steps.utils.Utils;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.iat.adminportal.steps.utils.DateTimeUtil.*;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 14.03.14
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public class EmailManagerNavigation extends AbstractPage {

    private EmailManagerLocators locator_email = new EmailManagerLocators();
    protected AdminPortalHomePageLocators locator_home = new AdminPortalHomePageLocators();

    public EmailManagerNavigation() {
        super("");
    }

    private Utils utils = new Utils();
    private UserRepository userRepository = new UserRepositoryImpl();

    public void open() {
        super.open();
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    // EMAIL MANAGEMENT - create email management list screen for "lists" (RD-1972).
    public void checkIfEmailManagementTabIsAvailable() {
        assertTrue("Email management tab is no available", executor.is_element_present(locator_home.email_man_button));
    }

    public void checkIfEmailManagementPageWasOpened() {
        assertEquals("Page title is incorrect", "Mailing Lists", executor.getText(locator_email.emailManagerPageTitle));
    }

    public void checkContentOfEmailManagerPage() throws InterruptedException {
        //columns
        Thread.sleep(2000);
        assertTrue("Name column is no available", executor.is_element_present(locator_email.nameColumn));
        assertTrue("Schedule column is no available", executor.is_element_present(locator_email.scheduleColumn));
        assertTrue("Created date column is no available", executor.is_element_present(locator_email.createdDateColumn));
        assertTrue("Modified date column is no available", executor.is_element_present(locator_email.modifiedDateColumn));
        assertTrue("View column is no available", executor.is_element_present(locator_email.viewColumn));
        assertTrue("Active column is no available", executor.is_element_present(locator_email.activeColumn));
        assertTrue("Copy column is no available", executor.is_element_present(locator_email.copyColumn));
        assertTrue("Edit column is no available", executor.is_element_present(locator_email.editColumn));
        //elements for particular rows
        int rowsNumber = Integer.parseInt(executor.getText(locator_email.numberOfRows));
        assertEquals("Names number is incorrect", executor.get_elements(locator_email.nameBasicMainPage).size(), rowsNumber);
        assertEquals("Schedules number is incorrect", executor.get_elements(locator_email.scheduleBasicMainPage).size(), rowsNumber);
        assertEquals("Created dates number is incorrect", executor.get_elements(locator_email.createdDateBasicMainPage).size(), rowsNumber);
        assertEquals("Modified dates number is incorrect", executor.get_elements(locator_email.modifiedDateBasicMainPage).size(), rowsNumber);
        assertEquals("Views number is incorrect", executor.get_elements(locator_email.viewButtonBasicMainPage).size(), rowsNumber);
        assertEquals("Pauses number is incorrect", executor.get_elements(locator_email.pauseActivateButtonBasicMainPage).size(), rowsNumber);
        assertEquals("Copies number is incorrect", executor.get_elements(locator_email.copyButtonBasicMainPage).size(), rowsNumber);
        assertEquals("Edites number is incorrect", executor.get_elements(locator_email.editButtonBasicMainPage).size(), rowsNumber);

    }

    // EMAIL MANAGEMENT - create email management create/edit screen for "lists" (RD-1984).
    public void checkContenOfCreatingRulePopup() throws InterruptedException {
        Thread.sleep(2000);
        assertTrue("Name field is not visible", executor.is_element_present(locator_email.nameCreateNew));
        assertTrue("Schedule is not visible", executor.is_element_present(locator_email.scheduleCreateNew));
        assertTrue("Add rule button is not visible", executor.is_element_present(locator_email.addRuleButtonBasicCreateNew));
        assertTrue("Save button is not visible", executor.is_element_present(locator_email.saveButtonCreateNew));
        assertTrue("Cancel button is not visible", executor.is_element_present(locator_email.cancelButtonCreateNew));
    }

    public void checkVisibilityOfNameAlert() {
        assertTrue("Name alert is invisible", executor.is_element_present(locator_email.nameRequired));
    }

    // EMAIL MANAGEMENT - create/edit page list rule - " account status" (RD-2307).
    public void clickCreateNewRuleButton() {
        executor.click(locator_email.createNewButtonMainPage);
    }

    public void clickAddNewRuleButton() {
        executor.click(locator_email.addRuleButtonBasicCreateNew);
    }

    public void checkIfStatusRuleIsAvailable() {
        executor.selectOptionByValue(locator_email.ruleTypeCreateNew, "ACCOUNT_STATUS");
        executor.waitAbsolute(200);
        assertTrue("Filter value for status rule is no available", executor.is_element_present(locator_email.accountStstusCreateNew));
        executor.selectOptionByValue(locator_email.accountStstusCreateNew, "VARIFIED");
        executor.selectOptionByValue(locator_email.accountStstusCreateNew, "UNVERIFIED");
    }

    public void closeCreatingRuleWindow() {
        executor.click(locator_email.closedWindowCreateNew);
    }

    public void searchProperExistingRule(String ruleName) throws Throwable {
        executor.send_keys(locator_email.searchFieldMainPage, ruleName);
        go_sleep(500);
        executor.click(locator_email.searchButtonMainPage);
    }

    public void setActiveValueToFalse() throws SQLException {
        JDBC jdbc = new JDBC("admin_portal");
        jdbc.execute_delete_update_query("Update admin_ui.SubscribersList SET active = 1 WHERE name = 'AutomatedTestDoNotEdit'");
        jdbc.close();
    }

    public void clckEditRuleButton() throws InterruptedException {
        executor.click(locator_email.editButtonBasicMainPage);
        Thread.sleep(2000);
    }

    public void removeAllCurrentRules() {
        List<WebElement> removeRuleButtons = executor.get_elements(locator_email.deleteButtonCreateNew);
        for (int i = 0; i < removeRuleButtons.size() - 1; i++) {
            executor.click(removeRuleButtons.get(removeRuleButtons.size() - i - 2));
        }
    }

    public void clickAddRuleButton() throws InterruptedException {
        Thread.sleep(1000);


        executor.click(locator_email.addRuleButtonBasicCreateNew);
        Thread.sleep(1000);
    }

    public void selectProperRuleOption(String rule) {
        executor.selectOptionByValue(locator_email.ruleTypeCreateNew, rule);
    }

    int random;

    public void selectFilterValueForAccountStstus() throws InterruptedException {
        random = executor.return_random_value(2);
        Thread.sleep(500);
        if (random == 0) {
            executor.selectOptionByValue(locator_email.accountStstusCreateNew, "VERIFIED");
        } else if (random == 1) {
            executor.selectOptionByValue(locator_email.accountStstusCreateNew, "UNVERIFIED");
        }
    }

    public void clickSaveRuleButton() throws InterruptedException {
        executor.click(locator_email.saveButtonCreateNew);
        Thread.sleep(2000);
    }

    public void checkIfStatusRuleIsEnabled() throws SQLException, InterruptedException {
        Thread.sleep(1000);
        assertTrue("Main rule DDL should be visible", executor.is_element_present(locator_email.ruleTypeCreateNew));
        assertTrue("Filter value should be visible", executor.is_element_present(locator_email.accountStstusCreateNew));
        JDBC jdbc = new JDBC("admin_portal");
        if (random == 0) {
            assertEquals("Filter value was not correctly stored in DB", "VERIFIED", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'ACCOUNT_STATUS' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        } else if (random == 1) {
            assertEquals("Filter value was not correctly stored in DB", "UNVERIFIED", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'ACCOUNT_STATUS' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        }
        jdbc.close();
    }

    public void deleteRule() {
        executor.click(locator_email.deleteButtonCreateNew);
        executor.click(locator_email.saveButtonCreateNew);
    }

    // EMAIL MANAGEMENT - EMAIL MANAGEMENT - create/edit page list rule - " complete profile " (RD-2296).
    public void checkIfCompleteProfileRuleIsAvailable() {
        executor.selectOptionByValue(locator_email.ruleTypeCreateNew, "PROFILE_COMPLETED");
        executor.waitAbsolute(200);
        assertTrue("Filter value for status rule is no available", executor.is_element_present(locator_email.profileCompletedCreateNew));
        executor.selectOptionByValue(locator_email.profileCompletedCreateNew, "CONTACT");
        executor.selectOptionByValue(locator_email.profileCompletedCreateNew, "PERSONAL");
        executor.selectOptionByValue(locator_email.profileCompletedCreateNew, "BOTH");
        executor.selectOptionByValue(locator_email.profileCompletedCreateNew, "NONE");
    }

    public void selectFilterValueForCompleteProfile() throws InterruptedException {
        random = executor.return_random_value(4);
        Thread.sleep(500);
        if (random == 0) {
            executor.selectOptionByValue(locator_email.profileCompletedCreateNew, "CONTACT");
        } else if (random == 1) {
            executor.selectOptionByValue(locator_email.profileCompletedCreateNew, "PERSONAL");
        } else if (random == 2) {
            executor.selectOptionByValue(locator_email.profileCompletedCreateNew, "BOTH");
        } else if (random == 3) {
            executor.selectOptionByValue(locator_email.profileCompletedCreateNew, "NONE");
        }
    }

    public void checkIfProfileCompletedRuleIsEnabled() throws SQLException, InterruptedException {
        Thread.sleep(1000);
        assertTrue("Main rule DDL should be visible", executor.is_element_present(locator_email.ruleTypeCreateNew));
        assertTrue("Filter value should be visible", executor.is_element_present(locator_email.profileCompletedCreateNew));
        JDBC jdbc = new JDBC("admin_portal");
        if (random == 0) {
            assertEquals("Filter value was not correctly stored in DB", "CONTACT", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'PROFILE_COMPLETED' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        } else if (random == 1) {
            assertEquals("Filter value was not correctly stored in DB", "PERSONAL", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'PROFILE_COMPLETED' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        } else if (random == 2) {
            assertEquals("Filter value was not correctly stored in DB", "BOTH", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'PROFILE_COMPLETED' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        } else if (random == 3) {
            assertEquals("Filter value was not correctly stored in DB", "NONE", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'PROFILE_COMPLETED' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        }
        jdbc.close();
    }

    // EMAIL MANAGEMENT - create/edit page list rule - "gender " (RD-2284).
    public void checkIfGenderRuleIsAvailable() {
        executor.selectOptionByValue(locator_email.ruleTypeCreateNew, "GENDER");
        executor.waitAbsolute(200);
        assertTrue("Filter value for status rule is no available", executor.is_element_present(locator_email.genderCreateNew));
        executor.selectOptionByValue(locator_email.genderCreateNew, "MALE");
        executor.selectOptionByValue(locator_email.genderCreateNew, "FEMALE");
        executor.selectOptionByValue(locator_email.genderCreateNew, "NOT_SPECIFIED");
    }

    public void selectFilterValueForGender() throws InterruptedException {
        random = executor.return_random_value(3);
        Thread.sleep(500);
        if (random == 0) {
            executor.selectOptionByValue(locator_email.genderCreateNew, "MALE");
        } else if (random == 1) {
            executor.selectOptionByValue(locator_email.genderCreateNew, "FEMALE");
        } else {
            executor.selectOptionByValue(locator_email.genderCreateNew, "NOT_SPECIFIED");
        }
    }

    public void checkIfGenderdRuleIsEnabled() throws SQLException, InterruptedException {
        Thread.sleep(1000);
        assertTrue("Main rule DDL should be visible", executor.is_element_present(locator_email.ruleTypeCreateNew));
        assertTrue("Filter value should be visible", executor.is_element_present(locator_email.genderCreateNew));
        JDBC jdbc = new JDBC("admin_portal");
        if (random == 0) {
            assertEquals("Filter value was not correctly stored in DB", "MALE", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'GENDER' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        } else if (random == 1) {
            assertEquals("Filter value was not correctly stored in DB", "FEMALE", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'GENDER' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        } else if (random == 2) {
            assertEquals("Filter value was not correctly stored in DB", "NOT_SPECIFIED", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'GENDER' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        }
        jdbc.close();
    }

    // EMAIL MANAGEMENT - create/edit page list rule - "logged in " (RD-2274) - three options
    public void checkIfLoggedInRuleIsAvailable() {
        executor.selectOptionByValue(locator_email.ruleTypeCreateNew, "LAST_LOGGED_IN");
        executor.waitAbsolute(200);
        assertTrue("Filter value for status rule is no available", executor.is_element_present(locator_email.lastLoggedInCreateNew));
        executor.selectOptionByValue(locator_email.lastLoggedInCreateNew, "WEEK");
        executor.selectOptionByValue(locator_email.lastLoggedInCreateNew, "MONTH");
        executor.selectOptionByValue(locator_email.lastLoggedInCreateNew, "QUARTER");
    }

    public void selectFilterValueForLoggedIn() throws InterruptedException {
        random = executor.return_random_value(3);
        Thread.sleep(500);
        if (random == 0) {
            executor.selectOptionByValue(locator_email.lastLoggedInCreateNew, "WEEK");
        } else if (random == 1) {
            executor.selectOptionByValue(locator_email.lastLoggedInCreateNew, "MONTH");
        } else if (random == 2) {
            executor.selectOptionByValue(locator_email.lastLoggedInCreateNew, "QUARTER");
        }
    }

    public void checkIfLoggedInRuleIsEnabled() throws SQLException, InterruptedException {
        Thread.sleep(1000);
        assertTrue("Main rule DDL should be visible", executor.is_element_present(locator_email.ruleTypeCreateNew));
        assertTrue("Filter value should be visible", executor.is_element_present(locator_email.lastLoggedInCreateNew));
        JDBC jdbc = new JDBC("admin_portal");
        if (random == 0) {
            assertEquals("Filter value was not correctly stored in DB", "WEEK", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'LAST_LOGGED_IN' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        } else if (random == 1) {
            assertEquals("Filter value was not correctly stored in DB", "MONTH", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'LAST_LOGGED_IN' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        } else if (random == 2) {
            assertEquals("Filter value was not correctly stored in DB", "QUARTER", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'LAST_LOGGED_IN' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        }
        jdbc.close();
    }

    // EMAIL MANAGEMENT - create/edit page list rule - "logged in " (RD-2274) - from to option
    public void checkIfLoggedFromRuleIsAvailable() {
        executor.selectOptionByValue(locator_email.ruleTypeCreateNew, "LAST_LOGGED_IN_DATE");
        executor.waitAbsolute(200);
        assertTrue("Filter value for status rule is no available - from", executor.is_element_present(locator_email.creationTimeIntervalFromCreateNew));
        assertTrue("Filter value for status rule is no available - to", executor.is_element_present(locator_email.creationTimeIntervalToCreateNew));
        executor.send_keys(locator_email.creationTimeIntervalFromCreateNew, "03/17/2014");
        executor.send_keys(locator_email.creationTimeIntervalToCreateNew, "03/18/2015");
    }

    public void selectFilterValueForLoggedFrom() {
        executor.send_keys(locator_email.creationTimeIntervalFromCreateNew, "03/17/2014");
        executor.send_keys(locator_email.creationTimeIntervalToCreateNew, "03/18/2015");
    }

    public void checkIfLoggedFromRuleIsEnabled() throws SQLException, InterruptedException {
        Thread.sleep(1000);
        assertTrue("Main rule DDL should be visible", executor.is_element_present(locator_email.ruleTypeCreateNew));
        assertTrue("Filter value from is no correct", executor.is_element_present(locator_email.creationTimeIntervalFromCreateNew));
        assertTrue("Filter value to is no correct", executor.is_element_present(locator_email.creationTimeIntervalToCreateNew));
        JDBC jdbc = new JDBC("admin_portal");
        assertEquals("Filter value was not correctly stored in DB", "03/17/2014|03/18/2015", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'LAST_LOGGED_IN_DATE' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        jdbc.close();
    }

    // EMAIL MANAGEMENT - create/edit page list rule - "created at"(RD-2264).
    public void checkIfCreatedAtRuleIsAvailable() {
        executor.selectOptionByValue(locator_email.ruleTypeCreateNew, "CREATED_AT");
        executor.waitAbsolute(200);
        assertTrue("Filter value for status rule is no available", executor.is_element_present(locator_email.accountCreatedAtCreateNew));
        executor.selectOptionByValue(locator_email.accountCreatedAtCreateNew, "WEEK");
        executor.selectOptionByValue(locator_email.accountCreatedAtCreateNew, "MONTH");
        executor.selectOptionByValue(locator_email.accountCreatedAtCreateNew, "QUARTER");
    }

    public void selectCreatedAtForLoggedFrom() throws InterruptedException {
        random = executor.return_random_value(3);
        Thread.sleep(500);
        if (random == 0) {
            executor.selectOptionByValue(locator_email.accountCreatedAtCreateNew, "WEEK");
        } else if (random == 1) {
            executor.selectOptionByValue(locator_email.accountCreatedAtCreateNew, "MONTH");
        } else if (random == 2) {
            executor.selectOptionByValue(locator_email.accountCreatedAtCreateNew, "QUARTER");
        }
    }

    public void checkIfCreatedAtRuleIsEnabled() throws SQLException, InterruptedException {
        Thread.sleep(1000);
        assertTrue("Main rule DDL should be visible", executor.is_element_present(locator_email.ruleTypeCreateNew));
        assertTrue("Filter value should be visible", executor.is_element_present(locator_email.accountCreatedAtCreateNew));
        JDBC jdbc = new JDBC("admin_portal");
        if (random == 0) {
            assertEquals("Filter value was not correctly stored in DB", "WEEK", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'CREATED_AT' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        } else if (random == 1) {
            assertEquals("Filter value was not correctly stored in DB", "MONTH", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'CREATED_AT' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        } else if (random == 2) {
            assertEquals("Filter value was not correctly stored in DB", "QUARTER", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'CREATED_AT' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        }
        jdbc.close();
    }

    // EMAIL MANAGEMENT - create/edit page list rule - "created at"(RD-2264) - from/to options.
    public void checkIfCreatedFromRuleIsAvailable() {
        executor.selectOptionByValue(locator_email.ruleTypeCreateNew, "CREATED_AT_DATE");
        executor.waitAbsolute(200);
        assertTrue("Filter value for status rule is no available - from", executor.is_element_present(locator_email.creationTimeIntervalFromCreateNew));
        assertTrue("Filter value for status rule is no available - to", executor.is_element_present(locator_email.creationTimeIntervalToCreateNew));
        executor.send_keys(locator_email.creationTimeIntervalFromCreateNew, "08/17/2013");
        executor.send_keys(locator_email.creationTimeIntervalToCreateNew, "03/18/2014");
    }

    public void selectCreatedFromForLoggedFrom() {
        executor.send_keys(locator_email.creationTimeIntervalFromCreateNew, "08/17/2013");
        executor.send_keys(locator_email.creationTimeIntervalToCreateNew, "03/18/2014");
    }

    public void checkIfCreatedFromRuleIsEnabled() throws SQLException, InterruptedException {
        Thread.sleep(1000);
        assertTrue("Main rule DDL should be visible", executor.is_element_present(locator_email.ruleTypeCreateNew));
        assertTrue("Filter value from is no correct", executor.is_element_present(locator_email.creationTimeIntervalFromCreateNew));
        assertTrue("Filter value to is no correct", executor.is_element_present(locator_email.creationTimeIntervalToCreateNew));
        JDBC jdbc = new JDBC("admin_portal");
        assertEquals("Filter value was not correctly stored in DB", "08/17/2013|03/18/2014", jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'CREATED_AT_DATE' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        jdbc.close();
    }

    // EMAIL MANAGEMENT - create/edit page list rule - "registration site" (RD-2317).
    public void checkIfCreatedRegistrationSiteIsAvailable() {
        executor.selectOptionByValue(locator_email.ruleTypeCreateNew, "REGISTRATION_SITE");
        executor.waitAbsolute(200);
        assertTrue("Filter value for registration site rule is no available", executor.is_element_present(locator_email.registrationSite));
    }

    String chosenRegistrationSite;

    public void selectRegistrationSiteForLoggedFrom() {
        executor.click(locator_email.registrationSite);
        List<WebElement> choices = executor.get_elements(locator_email.registrationSiteChoice);
        int random = executor.return_random_value(choices.size());
        chosenRegistrationSite = executor.getText(choices.get(random));
        executor.click(choices.get(random));
    }

    public void checkIfRegistrationSiteRuleIsEnabled() throws InterruptedException, SQLException {
        Thread.sleep(1000);
        assertTrue("Main rule DDL should be visible", executor.is_element_present(locator_email.ruleTypeCreateNew));
        assertTrue("Registration site DDL is no visible", executor.is_element_present(locator_email.registrationSiteAfterChoce));

        JDBC jdbc = new JDBC("points_manager");
        String shortName = jdbc.return_proper_db_value("SELECT shortName FROM points_manager.Partner WHERE name = '" + chosenRegistrationSite + "'", 1);
        jdbc.close();
        Thread.sleep(500);
        jdbc = new JDBC("admin_portal");
        assertEquals("Registration site was not correctly stored in DB", shortName, jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'REGISTRATION_SITE' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1) + "'", 1));
        jdbc.close();
    }

    // EMAIL MANAGEMENT - list screen functionality "Pause" (Rd-2254).
    public void clickPauseActivateButton() {
        executor.click(locator_email.pauseActivateButtonBasicMainPage);
    }

    public void confirmDecision() {
        if (executor.is_element_present(locator_email.pauseActivateConfirmWindowMainPage)) {
            executor.click(locator_email.pauseActivateConfirmYesButtonMainPage);
        } else {
        }
    }

    public void checkIfPauseInformationisCorrect(boolean ifActivated) {
        if (ifActivated) {
            assertEquals("Pause information is incorrect", "Active", executor.getText(locator_email.activePausedInformationMainPage));
        } else if (!ifActivated) {
            assertEquals("Pause information is incorrect", "Paused", executor.getText(locator_email.activePausedInformationMainPage));
        }
    }

    public void checkIfDatabaseFieldWasUpdated(boolean howChanged) throws SQLException {
        JDBC jdbc = new JDBC("admin_portal");
        if (howChanged) {
            assertEquals("Database value is incorrect", "1", jdbc.return_proper_db_value("SELECT active FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1));
        } else if (!howChanged) {
            assertEquals("Database value is incorrect", "0", jdbc.return_proper_db_value("SELECT active FROM admin_ui.SubscribersList WHERE name='AutomatedTestDoNotEdit'", 1));
        }
        jdbc.close();
    }

    // EMAIL MANAGEMENT - list screen functionality "Copy" (RD-2244).
    String createdWithin;
    String createdBetween;
    String lastLoggedInWithin;
    String lastLoggedInBetween;
    String gender;
    String profileCompleated;
    String accountStatus;
    String chosenName;

    public void chooseWhatShouldBeCopied() throws SQLException {
        random = executor.return_random_value(executor.get_elements(locator_email.nameBasicMainPage).size());
        chosenName = executor.getText(executor.get_elements(locator_email.nameBasicMainPage).get(random));
        JDBC jdbc = new JDBC("admin_portal");
        createdWithin = jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'CREATED_AT' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + chosenName + "'", 1) + "'", 1);
        createdBetween = jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'CREATED_AT_DATE' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + chosenName + "'", 1) + "'", 1);
        lastLoggedInWithin = jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'LAST_LOGGED_IN' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + chosenName + "'", 1) + "'", 1);
        lastLoggedInBetween = jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'LAST_LOGGED_IN_DATE' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + chosenName + "'", 1) + "'", 1);
        gender = jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'GENDER' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + chosenName + "'", 1) + "'", 1);
        profileCompleated = jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'PROFILE_COMPLETED' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + chosenName + "'", 1) + "'", 1);
        accountStatus = jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'ACCOUNT_STATUS' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + chosenName + "'", 1) + "'", 1);
        jdbc.close();
    }

    public void clickCopyButtonOfChosen() {
        executor.click(executor.get_elements(locator_email.copyButtonBasicMainPage).get(random));
    }

    String name;
    String schedule;

    public void enterRuleName(String name_n) {
        name = name_n;
        executor.send_keys(locator_email.nameCreateNew, name);
    }

    public void enterRuleSchedule() {
        schedule = "0 25 12 * * ?";
        executor.send_keys(locator_email.scheduleCreateNew, schedule);
    }

    public void checkIfresultWasFound() throws InterruptedException {
        Thread.sleep(1000);
        assertTrue("Element was not found", executor.getText(locator_email.nameBasicMainPage).contains(name));
    }

    public void compareCopiedRuleWithOriginal() throws SQLException {
        JDBC jdbc = new JDBC("admin_portal");
        if (createdWithin.length() < 30)
            assertEquals("Created within are not equals for " + chosenName + " name of copy " + name, createdWithin, jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'CREATED_AT' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + name + "'", 1) + "'", 1));
        if (createdBetween.length() < 30)
            assertEquals("Created between are not equals for " + chosenName + " name of copy " + name, createdBetween, jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'CREATED_AT_DATE' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + name + "'", 1) + "'", 1));
        if (lastLoggedInWithin.length() < 30)
            assertEquals("Last logged in within are not equals for " + chosenName + " name of copy " + name, lastLoggedInWithin, jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'LAST_LOGGED_IN' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + name + "'", 1) + "'", 1));
        if (lastLoggedInBetween.length() < 30)
            assertEquals("Last logged in between are not equals for " + chosenName + " name of copy " + name, lastLoggedInBetween, jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'LAST_LOGGED_IN_DATE' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + name + "'", 1) + "'", 1));
        if (gender.length() < 30)
            assertEquals("Gender are not equals for " + chosenName + " name of copy " + name, gender, jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'GENDER' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + name + "'", 1) + "'", 1));
        if (profileCompleated.length() < 30)
            assertEquals("Profile completed are not equals for " + chosenName + " name of copy " + name, profileCompleated, jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'PROFILE_COMPLETED' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + name + "'", 1) + "'", 1));
        if (accountStatus.length() < 30)
            assertEquals("Account status are not equals for " + chosenName + " name of copy " + name, accountStatus, accountStatus = jdbc.return_proper_db_value("SELECT value FROM admin_ui.UserFilterRule WHERE filter = 'ACCOUNT_STATUS' AND subscribersList_id='" + jdbc.return_proper_db_value("SELECT id FROM admin_ui.SubscribersList WHERE name='" + name + "'", 1) + "'", 1));
        jdbc.close();
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - created within option
    public void clickViewButton() throws InterruptedException {
        executor.click(locator_email.viewButtonBasicMainPage);
        Thread.sleep(2000);
        executor.waitUntilElementDisappears(locator_email.emailsPreviewProcessing);
    }

    public void set100PerPage() {
        executor.selectOptionByValue(locator_email.emailsNumberPerPage, "100");
    }

    public void checIfDisplayedListIsCorrectCreatedWithin() throws SQLException, ParseException {
        List<WebElement> emails = executor.get_elements(locator_email.emailBasicEmaiView);
        assertTrue("No results found", !(emails.size() == 0));
        // take date from rule according to chosen rule
        Date dateWanted = new Date();
        if (random == 0) {
            dateWanted = adjustDateByDays(dateWanted, -7);
        } else if (random == 1) {
            dateWanted = adjustDateByMonths(dateWanted, -1);
        } else if (random == 2) {
            dateWanted = adjustDateByMonths(dateWanted, -3);
        }

        UserDetails userDetails;
        Date createdAtDate;
        for (WebElement email : emails) {
            userDetails = userRepository.findByEmail(executor.getText(email));
            createdAtDate = utils.findUserCreatedDate(userDetails);
            // compare dates
            assertTrue("Date is to early according to rule " + random + " date " + createdAtDate, createdAtDate.after(dateWanted));
        }
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - logged within option
    public void checIfDisplayedListIsCorrectLoggedWithin() throws SQLException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<WebElement> emails = executor.get_elements(locator_email.emailBasicEmaiView);
        assertTrue("No results found", !(emails.size() == 0));
        // take date from rule according to chosen rule
        Date dateWanted = new Date();
        if (random == 0) {
            dateWanted = adjustDateByDays(dateWanted, -7);
        } else if (random == 1) {
            dateWanted = adjustDateByMonths(dateWanted, -1);
        } else if (random == 2) {
            dateWanted = adjustDateByMonths(dateWanted, -3);
        }

        UserDetails userDetails;
        for (WebElement email : emails) {
            userDetails = userRepository.findByEmail(executor.getText(email));
            // compare dates
            assertTrue("Date is to early according to rule " + random + " date " + userDetails.getLastLoginAt(), userDetails.getLastLoginAt().after(dateWanted));
        }
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - account status option
    public void checIfDisplayedListIsCorrectAccountStatus() throws SQLException, ParseException {
        List<WebElement> emails = executor.get_elements(locator_email.emailBasicEmaiView);
        assertTrue("No results found", !(emails.size() == 0));
        Date breakpointDate = convertToDate("2013-08-01 00:00:01", "yyyy-MM-dd HH:mm:ss");
        String ifVerified = null;
        JDBC jdbc = new JDBC("points_manager");
        UserDetails userDetails;
        for (WebElement email : emails) {

            userDetails = userRepository.findByEmail(executor.getText(email));

            Date dateFromDB = utils.findUserCreatedDate(userDetails);
            if (dateFromDB.after(breakpointDate)) {
                if (random == 0) {
                    ifVerified = "true";
                } else if (random == 1) {
                    ifVerified = "false";
                }
                assertEquals("Account status is not correct for email " + executor.getText(email) + " with needed status " + ifVerified, ifVerified, userDetails.getVerified());
            } else if (dateFromDB.before(breakpointDate)) {
                assertTrue("Account status is not correct for email " + executor.getText(email) + " with needed status (before 2013-08-01 00:00:01)", userDetails.getPasswordChangedAt() != 0);
            }
        }
        jdbc.close();
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - complete profile option
    public void checIfDisplayedListIsCorrectCompleteProfile() throws SQLException {
        JDBC jdbc = new JDBC("points_manager");
        int personalFilledValue = 0;
        int contactFillledValue = 0;
        List<WebElement> emails = executor.get_elements(locator_email.emailBasicEmaiView);
        assertTrue("No results found", !(emails.size() == 0));

        UserDetails userDetails;
        for (WebElement email : emails) {
            userDetails = userRepository.findByEmail(executor.getText(email));
            personalFilledValue = 0;
            contactFillledValue = 0;
            // personal
            if (userDetails.getFirstName() != null && !userDetails.getFirstName().equals("")) {
                personalFilledValue++;
            }
            if (userDetails.getLastName() != null && !userDetails.getLastName().equals("")) {
                personalFilledValue++;
            }
            if (userDetails.getTitle() != null && !userDetails.getTitle().equals("")) {
                personalFilledValue++;
            }
            if (userDetails.getGender() != null && !userDetails.getGender().equals("")) {
                personalFilledValue++;
            }
            if (userDetails.getBirthDate() != 0) {
                personalFilledValue++;
            }
            //contact
            if (userDetails.getMobileNumber() != null && !userDetails.getMobileNumber().equals("")) {
                contactFillledValue++;
            }
            if (userDetails.getHouse() != null && !userDetails.getHouse().equals("")) {
                contactFillledValue++;
            }
            if (userDetails.getStreet() != null && !userDetails.getStreet().equals("")) {
                contactFillledValue++;
            }
            if (userDetails.getCity() != null && !userDetails.getCity().equals("")) {
                contactFillledValue++;
            }
            if (userDetails.getCounty() != null && !userDetails.getCounty().equals("")) {
                contactFillledValue++;
            }
            if (userDetails.getCountry() != null && !userDetails.getCountry().equals("")) {
                contactFillledValue++;
            }
            if (userDetails.getPostcode() != null && !userDetails.getPostcode().equals("")) {
                contactFillledValue++;
            }

            if (random == 0) {
                //"CONTACT"
                assertTrue("Email " + executor.getText(email) + " should not be in filter CONTACT ONLY", contactFillledValue == 7 && personalFilledValue < 5);
            } else if (random == 1) {
                //"PERSONAL"
                assertTrue("Email " + executor.getText(email) + " should not be in filter PERSONAL ONLY", contactFillledValue < 7 && personalFilledValue == 5);
            } else if (random == 2) {
                //"BOTH"
                assertTrue("Email " + executor.getText(email) + " should not be in filter CONTACT AND PERSONAL", contactFillledValue == 7 && personalFilledValue == 5);
            } else if (random == 3) {
                //"NONE"
                assertTrue("Email " + executor.getText(email) + " should not be in filter NONE", contactFillledValue < 7 && personalFilledValue < 5);
            }
        }
        jdbc.close();
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - gender option
    public void checIfDisplayedListIsCorrectGender() throws SQLException {
        List<WebElement> emails = executor.get_elements(locator_email.emailBasicEmaiView);
        assertTrue("No results found", !(emails.size() == 0));
        String wantedGender = null;
        if (random == 0) {
            wantedGender = "MALE";
        } else if (random == 1) {
            wantedGender = "FEMALE";
        } else {
            wantedGender = "NOT_SPECIFIED";
        }

        UserDetails userDetails;
        for (WebElement email : emails) {
            userDetails = userRepository.findByEmail(executor.getText(email));
            if (wantedGender.equals("NOT_SPECIFIED")) {
                assertTrue("Gender should not be specified for email " + executor.getText(email), userDetails.getGender() == null);
            } else {
                assertEquals("Gender is incorrect for email " + executor.getText(email) + " should be " + wantedGender, userDetails.getGender(), wantedGender);
            }
        }
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - registration site option
    public void checIfDisplayedListIsRegistrationSite() throws SQLException {
        List<WebElement> emails = executor.get_elements(locator_email.emailBasicEmaiView);
        assertTrue("No results found", !(emails.size() == 0));
        UserDetails userDetails;
        for (WebElement email : emails) {
            userDetails = userRepository.findByEmail(executor.getText(email));
            assertEquals("Registration site is incorrect for email " + executor.getText(email), chosenRegistrationSite, userDetails.getRegistrationSiteName());
        }

    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - logged from/to option
    public void checIfDisplayedListIsCreatedFrom() throws SQLException, ParseException {
        List<WebElement> emails = executor.get_elements(locator_email.emailBasicEmaiView);
        assertTrue("No results found", !(emails.size() == 0));

        Date fromData = convertToDate("2013-08-17 23:59:59", "yyyy-MM-dd HH:mm:ss");
        Date toData = convertToDate("2014-03-18 23:59:59", "yyyy-MM-dd HH:mm:ss");

        // take date from rule according to chosen rule
        UserDetails userDetails;
        Date createdAtDate;
        for (WebElement email : emails) {
            userDetails = userRepository.findByEmail(executor.getText(email));
            createdAtDate = utils.findUserCreatedDate(userDetails);
            // take date from db
            assertTrue("Date is not int ranges " + fromData + " --- " + toData + " date from DB -- " + createdAtDate + " email -- " + executor.getText(email), createdAtDate.after(fromData) && createdAtDate.before(toData));
        }
    }

    // EMAIL MANAGEMENT - list screen functionality "View list details" (RD-2234) - logged from/to option
    public void checIfDisplayedListIsLoggedFrom() throws ParseException, SQLException {
        List<WebElement> emails = executor.get_elements(locator_email.emailBasicEmaiView);
        assertTrue("No results found", !(emails.size() == 0));

        Date fromData = convertToDate("2013-08-17 23:59:59", "yyyy-MM-dd HH:mm:ss");
        Date toData = new Date();

        // take date from rule according to chosen rule
        UserDetails userDetails;
        for (WebElement email : emails) {
            userDetails = userRepository.findByEmail(executor.getText(email));
            // compare dates
            assertTrue("Date is not int ranges " + fromData + " --- " + toData + " date from DB -- " + userDetails.getLastLoginAt() + " email -- " + executor.getText(email), userDetails.getLastLoginAt().after(fromData) && userDetails.getLastLoginAt().before(toData));
        }
    }

    //Admin Panel - email manager - mailing list - new rule (RD-4208)
    public void selectMaleValueForGender() throws InterruptedException {
        Thread.sleep(500);
        executor.selectOptionByValue(locator_email.genderCreateNew, "MALE");
    }

    String emailForForeign;
    String emailForSpam;

    public void updateTwoEmailsAsSpamAndForeignInDatabase() throws InterruptedException, SQLException {
        Thread.sleep(1000);
        List<WebElement> emails = executor.get_elements(locator_email.emailBasicEmaiView);
        emailForForeign = executor.getText(emails.get(0));
        emailForSpam = executor.getText(emails.get(1));
        JDBC jdbc = new JDBC("points_manager");
        jdbc.execute_delete_update_query("Update points_manager.User SET foreignUser = 1 WHERE email = '" + emailForForeign + "'");
        jdbc.execute_delete_update_query("Update points_manager.User SET spam = 1 WHERE email = '" + emailForSpam + "'");
        jdbc.close();
    }

    public void checkIfOnListViewSpamAndForeignEmailsAreInvisible() {
        boolean ifFound = false;
        List<WebElement> emails = executor.get_elements(locator_email.emailBasicEmaiView);
        for (WebElement email : emails) {
            if (executor.getText(email).equals(emailForForeign) || executor.getText(email).equals(emailForSpam)) {
                ifFound = true;
            }
        }
        assertFalse("Spam or foreigner email found on emails list", ifFound);
    }

    public void updateTwoEmailsAsNoSpamAndForeignInDatabase() throws SQLException {
        JDBC jdbc = new JDBC("points_manager");
        jdbc.execute_delete_update_query("Update points_manager.User SET foreignUser = 0 WHERE email = '" + emailForForeign + "'");
        jdbc.execute_delete_update_query("Update points_manager.User SET spam = 0 WHERE email = '" + emailForSpam + "'");
        jdbc.close();
    }

    public void checkIfOnListViewNoSpamAndForeignEmailsAreVisible() {
        int ifFound = 0;
        List<WebElement> emails = executor.get_elements(locator_email.emailBasicEmaiView);
        for (WebElement email : emails) {
            if (executor.getText(email).equals(emailForForeign) || executor.getText(email).equals(emailForSpam)) {
                ifFound++;
            }
        }
        assertTrue("Emails did not return on list after spam and foreignUser flags reset", (ifFound == 2));
    }

    public void closeViewListWindow() {
        executor.click(locator_email.closedViewListWindow);
    }
}
