package com.iat.ePoints.Steps.StaticPages;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 20:13
 * To change this template use File | Settings | File Templates.
 */



import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.StaticPages.FooterNavigation;
import com.iat.ePoints.Navigations.StaticPages.PrivacyPolicyNavigation;



public class PrivacyPolicySteps {


    private IExecutor executor;
    private PrivacyPolicyNavigation ppNavi;
    private FooterNavigation footerNavi;

    public PrivacyPolicySteps(SeleniumExecutor executor){
        this.executor = executor;
    }

    public void go_sleep(int milisec)throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        ppNavi = new PrivacyPolicyNavigation(executor);
        footerNavi = new FooterNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    //scenario 1 ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("^Check content of footer for PrivacyPolicy link$")
    public void Check_content_of_footer_for_PrivacyPolicy_link() throws Throwable {
        footerNavi.checkPPLinkAvailableInFooter();
    }

    @Then("^Privacy Policy hyperlink should be available in footer$")
    public void Privacy_Policy_hyperlink_should_be_available_in_footer() throws Throwable {
        footerNavi.clickInPPLinkInFooter();
    }

    @Then("^Should link to Privacy Policy page$")
    public void Should_link_to_Privacy_Policy_page() throws Throwable {
        ppNavi.checkIsPPPageOpened();
    }

    //scenario 1 ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Information about the privacy policy should be displayed$")
    public void Information_about_the_privacy_policy_should_be_displayed() throws Throwable {
        ppNavi.checkVisibilityOfBasicElementsInPPPage();
    }
}
