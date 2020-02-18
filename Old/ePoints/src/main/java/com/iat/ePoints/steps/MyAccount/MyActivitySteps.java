package com.iat.ePoints.Steps.MyAccount;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 22:31
 * To change this template use File | Settings | File Templates.
 */



import com.iat.ePoints.Navigations.MyAccount.MyActivityNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import cucumber.api.java.en.Then;

public class MyActivitySteps {
    private IExecutor executor;
    private MyActivityNavigation myActivityNavi;



    public MyActivitySteps(SeleniumExecutor executor){
        this.executor = executor;

    }

    public void go_sleep(int milisec)throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        myActivityNavi = new MyActivityNavigation(executor);

    }

    @After
    public void tear_down() {
        executor.stop();

    }

    // scenario 1 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Go to my activity page$")
    public void Go_to_my_activity_page() throws Throwable {
        myActivityNavi.clickActivityReference();
    }

    @Then("^Check if activity page has proper content$")
    public void Check_if_activity_page_has_proper_content() throws Throwable {
        myActivityNavi.checkIfActivityPageHasProperContent();
    }

    // Check current balance page content
    @Then("^Check if current balance number is the same as in DB for '(.+)'$")
    public void Check_if_current_balance_number_is_the_same_as_in_DB(String userEmail) throws Throwable {
        myActivityNavi.checkNumberOfCurrentBalance(userEmail);
    }

    @Then("^Check if current balance content is properly comparing with DB for '(.+)'$")
    public void Check_if_current_balance_content_is_properly_comparing_with_DB(String userEmail) throws Throwable {
        myActivityNavi.checkContenOfCurrentBalaceForCurrentPage(userEmail);
    }

    // Check pending page content
    @Then("^Open pending tab$")
    public void Open_pending_tab() throws Throwable {
        myActivityNavi.openPendingTab();
    }

    @Then("^User can check if pending number is the same as in DB for '(.+)'$")
    public void User_can_check_if_pending_number_is_the_same_as_in_DB(String userEmail) throws Throwable {
        myActivityNavi.checkNumberOfPending(userEmail);
    }

    @Then("^He can check if pending content is properly comparing with DB for '(.+)'$")
    public void He_can_check_if_pending_content_is_properly_comparing_with_DB(String userEmail) throws Throwable {
        myActivityNavi.checkContenOfPendingForCurrentPage(userEmail);
    }

    //  Check declined page content
    @Then("^Open declined tab$")
    public void Open_declined_tab() throws Throwable {
        myActivityNavi.openDeclinedTab();
    }

    @Then("^User can check if declined number is the same as in DB for '(.+)'$")
    public void User_can_check_if_declined_number_is_the_same_as_in_DB(String userEmail) throws Throwable {
        myActivityNavi.checkNumberOfDeclined(userEmail);
    }

    @Then("^He can check if declined content is properly comparing with DB for '(.+)'$")
    public void He_can_check_if_declined_content_is_properly_comparing_with_DB_for_emailwybitnietestowy_gmail_com(String userEmail) throws Throwable {
        myActivityNavi.checkContenOfDeclinedForCurrentPage(userEmail);
    }
}
