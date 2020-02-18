package com.iat.adminportal.steps.brand_manager;

import com.iat.adminportal.page_navigations.AdminPortalHomePageNavigation;
import com.iat.adminportal.page_navigations.AdminPortalLoginPageNavigation;
import com.iat.adminportal.page_navigations.brand_manager.BrandManagerNavigation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 14.03.14
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
public class BrandManagerSteps {

    private BrandManagerNavigation brandNavi = new BrandManagerNavigation();
    private AdminPortalLoginPageNavigation loginNavi = new AdminPortalLoginPageNavigation();
    private AdminPortalHomePageNavigation homeNavi = new AdminPortalHomePageNavigation();

    public BrandManagerSteps() {
    }

    // BRAND MANAGER - extend search functionality to include synonyms (RD-1549).
    @Given("^Admin portal is opened$")
    public void Admin_portal_is_opened() throws Throwable {
        loginNavi = new AdminPortalLoginPageNavigation();
        loginNavi.open();
        loginNavi.enterLogin("i.superuser");
        loginNavi.enterPassword("theMightyBlues");
        loginNavi.clickLoginButton();
        assertTrue(loginNavi.checkLoginSuccess());
    }

    @Given("^Brand manager is opened$")
    public void Bran_Manager_is_opened() throws Throwable {
        homeNavi.goToPage("Brand Manager");
        Thread.sleep(4000);
    }

    @Given("^Synonyms column is available$")
    public void Synonyms_column_is_available() throws Throwable {
        brandNavi.checkIfSynonymsColumnIsAvailable();
    }

    @When("^User type phrase '(.+)' into search$")
    public void User_User_type_phrase_into_search(String phrase) throws Throwable {
        brandNavi.typePhraseIntoSearch(phrase);
        brandNavi.clickSearchButton();
    }

    @Then("^Proper results will be displayed according to phrase '(.+)'$")
    public void Proper_results_will_be_displayed_according_to_phrase_john(String phrase) throws Throwable {
        brandNavi.checkIfWereFoundProperBrands(phrase);
    }

}