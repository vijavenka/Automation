package com.iat.ePoints.Steps.Checkout;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Checkout.CompleteNavigation;
import com.iat.ePoints.Navigations.Checkout.OrderSummaryNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 05.12.13
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
public class CompleteSteps {

    private IExecutor executor;
    private CompleteNavigation completeNavi;
    private OrderSummaryNavigation summaryNavi;


    public CompleteSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    @Before
    public void set_up() {
        executor.start();
        completeNavi = new CompleteNavigation(executor);
        summaryNavi = new OrderSummaryNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    //Scenario: User opens complete page and checks working of all links and buttons////////////////////////////////////

    @Then("^User click place order button$")
    public void User_click_place_order_button() throws Throwable {
        summaryNavi.clickPlaceOrderButton();
    }

    @Then("^He is re-directed to Complete page$")
    public void He_is_re_directed_to_Complete_page() throws Throwable {
        completeNavi.checkIfComplitePageWasOpenedProperly();
    }

    @Then("^Faq link works properly$")
    public void Faq_link_works_properly() throws Throwable {
        completeNavi.clickFAQLink();
        completeNavi.checkFAQPageTitle();
        completeNavi.backToComplite();
    }

    @Then("^My account button works properly$")
    public void My_account_button_works_properly() throws Throwable {
        completeNavi.clickMyAccountButton();
        completeNavi.checkMyAccountPageTitle();
        completeNavi.backToComplite();
    }

    @Then("^Get epoints button works properly$")
    public void Get_epoints_button_works_properly() throws Throwable {
        completeNavi.clickGetEpointsLink();
        completeNavi.checkGetEpointsPageTitle();
        completeNavi.backToComplite();
    }
}
