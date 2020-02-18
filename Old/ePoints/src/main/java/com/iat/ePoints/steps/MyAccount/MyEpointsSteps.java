package com.iat.ePoints.Steps.MyAccount;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */



import com.iat.ePoints.Navigations.MyAccount.MyEpointsStepsNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import cucumber.api.java.en.And;

public class MyEpointsSteps {

    private IExecutor executor;
    private MyEpointsStepsNavigation myEpointsNavi;


    public MyEpointsSteps(SeleniumExecutor executor){
        this.executor = executor;

    }

    public void go_sleep(int milisec)throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        myEpointsNavi = new MyEpointsStepsNavigation(executor);

    }

    @After
    public void tear_down() {
        executor.stop();

    }
    //scenario 1 ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @And("^Epoints sub page should be opened correctly$")
    public void Epoints_sub_page_should_be_opened_correctly() throws Throwable {
        myEpointsNavi.openMyEpointsTab();
    }

    @And("^Epoints sub page should have required elements$")
    public void Epoints_sub_page_should_have_required_elements() throws Throwable {
        myEpointsNavi.checkMyEpointsPageContent();

    }

    //scenario 2 ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @And("^All activity link works properly$")
    public void All_activity_link_works_properly() throws Throwable {
        myEpointsNavi.checkIfaAllActivityLinkWorksProperly();

    }

    @And("^I am able to return to my epoints tab$")
    public void I_am_able_to_return_to_my_epoints_tab() throws Throwable {
        myEpointsNavi.returnToMyepointsTab();

    }

    @And("^Find you favourite stores button works properly$")
    public void  Find_you_favourite_stores_button_works_properly() throws Throwable {
        myEpointsNavi.checkIfButtonWorksCorrectly();

    }
}