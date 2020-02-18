package com.iat.storepresentation.Steps.TransitionPage;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Executors.SeleniumExecutor;
import com.iat.storepresentation.Navigations.TransitionPage.TransitionPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 14.04.14
 * Time: 11:58
 * To change this template use File | Settings | File Templates.
 */
public class TransitionPageSteps {

    private IExecutor executor;
    private TransitionPageNavigation transitionNavi;

    public TransitionPageSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        transitionNavi = new TransitionPageNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // AFFILIATE MANAGER - cross channel sales tracking logic (RD-2837) - both normal transition
    @Then("^Additional P parameters can be added$")
    public void Additional_P_parameters_can_be_added() throws Throwable {
        transitionNavi.addPParametersToUrl();
    }

    @Then("^P parameters should correctly be stored in database$")
    public void P_parameters_should_correctly_be_stored_in_database() throws Throwable {
        transitionNavi.checkIfPParametersWereCorrectlyStoredInDB();
    }
}
