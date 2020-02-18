package com.iat.ePoints.Steps.StaticPages;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.StaticPages.CookiesNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 28.11.13
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */
public class CookiesSteps {

    private IExecutor executor;
    private CookiesNavigation cookiesNavi;

    public CookiesSteps(SeleniumExecutor executor){
        this.executor = executor;

    }

    public void go_sleep(int milisec)throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        cookiesNavi = new CookiesNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    @When("^Cookies web page is opened$")
    public void Cookies_web_page_is_opened() throws Throwable {
        cookiesNavi.clickOnCookiesFooterReference();
    }

    @Then("^Cookies Page is available with proper content$")
    public void Cookies_Page_is_available_with_proper_content() throws Throwable {
        cookiesNavi.checkContentOfCookiesPage();
    }

}
