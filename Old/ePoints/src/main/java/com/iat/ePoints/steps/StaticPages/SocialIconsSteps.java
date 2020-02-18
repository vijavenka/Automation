package com.iat.ePoints.Steps.StaticPages;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 21:33
 * To change this template use File | Settings | File Templates.
 */

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.StaticPages.FooterNavigation;


public class SocialIconsSteps {


    private IExecutor executor;
    private FooterNavigation footerNavi;

    public SocialIconsSteps(SeleniumExecutor executor){
        this.executor = executor;

    }

    public void go_sleep(int milisec)throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        footerNavi = new FooterNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    @When("^Check content of footer for Social icons$")
    public void Check_content_of_footer_for_Social_icons() throws Throwable {
       //Leave Empty
    }

    @Then("^Facebook icon should be available$")
    public void Facebook_icon_link_should_be_available() throws Throwable {
        footerNavi.checkIsFacebookIconAvaliable();
    }

    @Then("^Twitter icon should be available$")
    public void Twitter_icon_should_be_available() throws Throwable {
        footerNavi.checkIsTwitterLinkVisible();
    }
}
