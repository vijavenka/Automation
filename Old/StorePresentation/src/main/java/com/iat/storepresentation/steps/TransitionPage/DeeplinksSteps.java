package com.iat.storepresentation.Steps.TransitionPage;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Executors.SeleniumExecutor;
import com.iat.storepresentation.Navigations.TransitionPage.DeeplinksNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.04.14
 * Time: 09:26
 * To change this template use File | Settings | File Templates.
 */
public class DeeplinksSteps {

    private IExecutor executor;
    private DeeplinksNavigation deeplinksNavi;

    public DeeplinksSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        deeplinksNavi = new DeeplinksNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // AFFILIATE MANAGER - create deeplinks outside of SOLR for WLS (RD-2774)
    @Given("^Transition page using deeplink is opened$")
    public void Transition_page_using_deeplink_is_opened() throws Throwable {
        deeplinksNavi.openDeeplinkTransitionPage();
    }

    @Then("^New clickout should be reported 'user_sign_out' 'user_sign_out' for deeplink$")
    public void New_clickout_should_be_reported_user_sign_out_user_sign_out_for_deeplink() throws Throwable {

    }
}
