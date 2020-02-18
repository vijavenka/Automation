package com.iat.adminportal.steps.network_manager;

import com.iat.adminportal.page_navigations.AdminPortalHomePageNavigation;
import com.iat.adminportal.page_navigations.network_manager.NetworkManagerStatsNavigation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 10.04.14
 * Time: 12:07
 * To change this template use File | Settings | File Templates.
 */
public class NetworkManagerStatsSteps {

    private AdminPortalHomePageNavigation homeNavi = new AdminPortalHomePageNavigation();
    private NetworkManagerStatsNavigation statsNavi = new NetworkManagerStatsNavigation();

    public NetworkManagerStatsSteps() {
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    // ADMIN MANAGER - cross channel sales reporting view in network manager (RD-3019) - columns availability
    @Given("^Affiliate window stats page is opened$")
    public void Affiliate_window_stats_page_is_opened() throws Throwable {
        homeNavi.goToPage("Network Manager");
        statsNavi.openAffiliateWindowStatsPage();
    }

    @When("^User add (.+) column$")
    public void User_add_P_column(String columnName) throws Throwable {
        statsNavi.addProperColumn(columnName);
        System.out.println(columnName);
    }

    @Then("^(.+) colum should be visible$")
    public void P_colum_should_be_visible(String columnName) throws Throwable {
        statsNavi.checkIfProperColumnIsVisible(columnName, true);
    }

    @When("^User remove (.+) column$")
    public void User_remove_P_column(String columnName) throws Throwable {
        statsNavi.addProperColumn(columnName);
    }

    @Then("^(.+) column should be removed$")
    public void P_column_should_be_removed(String columnName) throws Throwable {
        statsNavi.checkIfProperColumnIsVisible(columnName, false);
    }

    // ADMIN MANAGER - cross channel sales reporting view in network manager (RD-3019) - columns content correctness
    @Given("^Data in clickout database is modified$")
    public void Data_in_clickout_database_is_modified() throws Throwable {
        statsNavi.setNewDataInLastestClickout();
    }

    @Then("^User can see that P columns data is correct according to database$")
    public void User_can_see_that_P_columns_data_is_correct_accordin_to_database() throws Throwable {
        statsNavi.checkCorrectnessOfPColumns();
    }
}