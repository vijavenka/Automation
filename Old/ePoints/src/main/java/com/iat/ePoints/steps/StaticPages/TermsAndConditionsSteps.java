package com.iat.ePoints.Steps.StaticPages;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 20:55
 * To change this template use File | Settings | File Templates.
 */
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.StaticPages.FooterNavigation;
import com.iat.ePoints.Navigations.StaticPages.TermsAndConditionsNavigation;

public class TermsAndConditionsSteps {


    private IExecutor executor;
    private TermsAndConditionsNavigation tcNavi;
    private FooterNavigation footerNavi;

    public TermsAndConditionsSteps(SeleniumExecutor executor){
        this.executor = executor;

    }

    public void go_sleep(int milisec)throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        tcNavi = new TermsAndConditionsNavigation(executor);
        footerNavi = new FooterNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // scenario 1 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("^Check content of footer for T&C link$")
    public void Check_content_of_footer_for_T_C_link() throws Throwable {
        footerNavi.checkTCLinkAvailableInFooter();
    }

    @Then("^T&C hyperlink should be available in footer$")
    public void T_C_hyperlink_should_be_available_in_footer() throws Throwable {
        footerNavi.clickInTCLinkInFooter();
    }

    @Then("^Should link to T&C page$")
    public void Should_link_to_T_C_page() throws Throwable {
        tcNavi.checkIsTCPageOpened();
    }

    //scenario 2 ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @And("^Terms and Conditions page is opened$")
    public void Terms_And_Conditions_page_is_opened() throws Throwable{
        footerNavi.clickInTCLinkInFooter();
        tcNavi.checkIsTCPageOpened();
    }

    @When("^Check content of page terms and conditions$")
    public void Check_content_of_page() throws Throwable{

    }

    @Then("^Information about the Terms and Conditions should be displayed$")
    public void Information_about_the_Terms_and_Conditions_should_be_displayed(){
        tcNavi.checkVisibilityOfMainTitles();
    }
}
