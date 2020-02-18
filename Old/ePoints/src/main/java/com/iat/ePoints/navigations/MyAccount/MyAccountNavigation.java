package com.iat.ePoints.Navigations.MyAccount;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.MyAccount.MyProfileLocators;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.*;

public class MyAccountNavigation extends AbstractPage {

    private MyProfileLocators locators_myprofile = new MyProfileLocators();
    private HeaderLocators locators_header = new HeaderLocators();

    public MyAccountNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    //Scenario: Checking if all links to My account tabs are available /////////////////////////////////////////////////
    public boolean checkMyAccount_menu_isAvailable() {
        return executor.is_element_present(locators_header.myAccount);
    }

    public void openMyAccount() {
        executor.click(locators_header.myAccount);
    }


    public boolean checkDasboard_menu_isAvailable() {
        return executor.is_element_present(locators_myprofile.myAccountSection);
    }

    public boolean checkMyEpoints_menu_isAvailable() {
        return executor.is_element_present(locators_myprofile.myEpoints);
    }

    public boolean checkProfile_menu_isAvailable() {
        return executor.is_element_present(locators_myprofile.myProfile);
    }

    public boolean checkActivity_menu_isavailable() {
        return executor.is_element_present(locators_myprofile.myActivity);
    }

    public boolean checkHistory_menu_isavailable() {
        return executor.is_element_present(locators_myprofile.myRewardsHistory);
    }

    public boolean checkInviteFriends_menu_isavailable() {
        return executor.is_element_present(locators_myprofile.inviteFriend);
    }

    //Scenario: Checking if all My accounts links redirect to proper tabs //////////////////////////////////////////////
    public void clickInMyepoints() {
        executor.click(locators_myprofile.myEpoints);
    }

    public void clickInProfile() {
        executor.click(locators_myprofile.myProfile);
    }

    public void clickInActivity() {
        executor.click(locators_myprofile.myActivity);
    }

    public void clickInHistory() {
        executor.click(locators_myprofile.myRewardsHistory);
    }

    public void clickInInviteFriends() {
        executor.click(locators_myprofile.inviteFriend);
    }

    public void checkingMyEpoints_isOpened() {
        assertTrue("My epoints tab is currently not active ", executor.is_element_present(locators_myprofile.basicTabsPageTitle));
        assertEquals("My epoints page is not properly opened", "My epoints", executor.getText(locators_myprofile.basicTabsPageTitle));
    }

    public void checkingProfile_isOpened() {
        assertTrue("My profile tab is currently not active ", executor.is_element_present(locators_myprofile.basicTabsPageTitle));
        assertEquals("My profile page is not properly opened", "Profile", executor.getText(locators_myprofile.basicTabsPageTitle));
    }

    public void checkingActivity_isOpened() {
        assertTrue("Activity tab is currently not active ", executor.is_element_present(locators_myprofile.basicTabsPageTitle));
        assertEquals("Activity page is not properly opened", "Activity", executor.getText(locators_myprofile.basicTabsPageTitle));
    }

    public void checkingHistory_isOpened() {
        assertTrue("Rewards history tab is currently not active ", executor.is_element_present(locators_myprofile.basicTabsPageTitle));
        assertEquals("Rewards history page is not properly opened", "Rewards history", executor.getText(locators_myprofile.basicTabsPageTitle));
    }

    public void checkingInviteFriends_isOpened() {
        assertTrue("Invite friends tab is currently not active", executor.is_element_present(locators_myprofile.basicTabsPageTitle));
        assertTrue("Invite friends page is not properly opened", executor.getText(locators_myprofile.basicTabsPageTitle).contains("Invite friends"));
    }

}
