package com.iat.ePoints.Steps.StaticPages;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.StaticPages.HomeNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 20.01.14
 * Time: 12:56
 * To change this template use File | Settings | File Templates.
 */
public class HomeSteps {

    private IExecutor executor;
    private HomeNavigation homeNavi;


    public HomeSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        homeNavi = new HomeNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // Checking if home page has proper content ////////////////////////////////////////////////////////////////////////
    @Given("^He can see that page contains all needed sections$")
    public void He_can_see_that_page_contains_all_needed_section() throws Throwable {
        homeNavi.checkHomePageContent();
    }

    // Checking if home page links works correctly /////////////////////////////////////////////////////////////////////
    @Given("^He can use all links from home page$")
    public void He_can_use_all_links_from_home_page() throws Throwable {
        homeNavi.checkWorkingOfHomePageLinks();
    }

    // EPOINTS & WLS - make footer changes (RD-1920)
    @When("^User look at page footer$")
    public void User_look_at_page_footer() throws Throwable {
        // leave empty
    }

    @Then("^He can see partners  button$")
    public void He_can_see_partners_button() throws Throwable {
        homeNavi.checkVisibilityOfPartnersButton();
    }

    @When("^User use partner button$")
    public void User_use_partner_button() throws Throwable {
        homeNavi.clickPartnerButton();
    }

    @Then("^He will be redirected to for partner page$")
    public void He_will_be_redirected_to_for_partner_page() throws Throwable {
        homeNavi.checkIfPartnersPageWasopened();
    }
}
