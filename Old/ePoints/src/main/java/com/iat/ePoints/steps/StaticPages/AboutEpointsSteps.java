package com.iat.ePoints.Steps.StaticPages;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */
import com.iat.ePoints.Navigations.StaticPages.FooterNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.StaticPages.HeaderNavigation;
import com.iat.ePoints.Navigations.StaticPages.AboutUsNavigation;

public class AboutEpointsSteps {


    private IExecutor executor;
    private AboutUsNavigation aboutNavi;
    private HeaderNavigation headerNavi;
    private FooterNavigation footerNavi;

    public AboutEpointsSteps(SeleniumExecutor executor){
        this.executor = executor;

    }

    public void go_sleep(int milisec)throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        aboutNavi = new AboutUsNavigation(executor);
        headerNavi = new HeaderNavigation(executor);
        footerNavi = new FooterNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }
    // scenario 1 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("^Check header content of component$")
    public void Check_header_content_of_component() throws Throwable {
        headerNavi.checkAboutLinkInPageHeader();
    }

    @Then("^About hyperlink should be available$")
    public void About_hyperlink_should_be_available() throws Throwable {
        headerNavi.clickInAboutEpointsHeaderLink();
        aboutNavi.checkIsAboutUsPageOpened();
    }

    // scenario 2 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("^About Epoints page is opened$")
    public void About_Epoints_page_is_opened() throws Throwable {
        aboutNavi.openAboutEpointsPage();
    }

    @Then("^Check content of page about epoints$")
    public void Check_content_of_page_about_epoints()throws Throwable{
        aboutNavi.checkFourMainTitles();
        aboutNavi.checkButtonAndTopBackReferenceVisibility();
    }

    // scenario 2 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Go to submit a request page using get in touch button$")
    public void Go_to_submit_a_request_page_using_get_in_touch_button(){
        aboutNavi.clickGetInTouchButton();
    }

    @Then("^Check content of 'submit a request page'$")
    public void Check_content_of_submit_a_request_page(){
        aboutNavi.checkSubmitRequestPageContent();
    }
}

