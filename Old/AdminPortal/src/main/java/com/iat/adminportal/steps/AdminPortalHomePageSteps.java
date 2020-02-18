package com.iat.adminportal.steps;

import com.iat.adminportal.executors.IExecutor;
import com.iat.adminportal.page_navigations.AdminPortalHomePageNavigation;
import com.iat.adminportal.page_navigations.AdminPortalLoginPageNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class AdminPortalHomePageSteps {

    DataExchanger dataExchanger = DataExchanger.getInstance();

    public IExecutor executor = dataExchanger.getExecutor();

    private AdminPortalLoginPageNavigation loginPage;
    private AdminPortalHomePageNavigation homePage;
    private String cLocation;

    public AdminPortalHomePageSteps() {
    }

    @Given("^Admin user is on ([^']+) page$")
    public void adminUserIsOnSpecificPage(String page) throws InterruptedException {
        homePage = new AdminPortalHomePageNavigation();
        homePage.goToPage(page);
    }

    @And("^He knows his current location$")
    public void adminChecksCurrentLocation() {
        cLocation = homePage.getCurrentUrl();
    }

    @When("^He logs out$")
    public void adminLogsOut() {
        loginPage = new AdminPortalLoginPageNavigation();
        homePage.logOut();
        assertTrue(loginPage.checkLogoutSuccess());
    }

    @And("^He goes directly to the location$")
    public void adminGoesToLocationByUrl() {
        executor.open(cLocation);
    }

    @Given("^Admin portal home page is opened$")
    public void Admin_portal_home_page_is_opened() {
        homePage = new AdminPortalHomePageNavigation();
        assertTrue("Home page not opened", homePage.check_if_homePage_opened());
    }

    @When("^He checks admin portal navigation menu$")
    public void Check_admin_portal_navigation_menu() throws Throwable {
        assertTrue("Navi panel on left is not available", homePage.check_if_navi_panel_available());
    }


    @Then("^Link for '([^']+)' is available$")
    public void Link_for_Voucher_Manager_is_available(String manager_name) throws Throwable {
        assertTrue(manager_name + " navi option not available", homePage.check_is_managers_navi_available(manager_name));
    }

    @When("^He clicks on '([^']+)' link$")
    public void Click_in_Voucher_manager_link(String manager_name) throws Throwable {
        homePage.goToPage(manager_name);
    }

}
