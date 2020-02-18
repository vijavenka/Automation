package com.iat.steps.rewards;

import com.iat.actions.rewards.RecentlyRedeemedActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.Rewards.RecentlyRedeemedValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class RecentlyRedeemedSteps {

    private ResponseContainer response;
    private RecentlyRedeemedActions recentlyRedeemedActions = new RecentlyRedeemedActions();
    private RecentlyRedeemedValidator recentlyRedeemedValidator = new RecentlyRedeemedValidator();

    @When("^Recently redeemed products list is requested$")
    public void getRecentlyRedeemedProductsFromRewardsHome() throws Throwable {
        response = recentlyRedeemedActions.getRecentlyRedeemed(200);
    }

    @Then("^Recently redeemed products response data is same as in contract$")
    public void checkResponseCorrectnessOfRecentlyRedeemedProductsFromRewardsHome() throws Throwable {
        //nothing to do there
    }

    @When("^Recently redeemed products list is requested for specified department '(.+?)'$")
    public void getRecentlyRedeemedProductsFromRewardsDepartmentPage(String departmentSeoSlug) throws Throwable {
        response = recentlyRedeemedActions.getRecentlyRedeemedFromDepartments(departmentSeoSlug, 200);
    }

    @Then("^Returned products are only from specified department '(.+?)'$")
    public void checkIfRecentlyRedeemedProductsAreFromSelectedDepartment(String departmentSeoSlug) throws Throwable {
        recentlyRedeemedValidator.checkIfRecentlyRedeemedProductsAreFromSelectedDepartment(response, departmentSeoSlug);
    }

}
