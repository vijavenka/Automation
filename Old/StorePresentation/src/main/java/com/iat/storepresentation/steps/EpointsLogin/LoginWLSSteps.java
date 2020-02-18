package com.iat.storepresentation.Steps.EpointsLogin;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Executors.SeleniumExecutor;
import com.iat.storepresentation.Navigations.EpointsLogin.LoginUsingFacebookNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.03.14
 * Time: 14:11
 * To change this template use File | Settings | File Templates.
 */
public class LoginWLSSteps {

    private IExecutor executor;
    private LoginUsingFacebookNavigation loginNavi;


    public LoginWLSSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        loginNavi = new LoginUsingFacebookNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    @Given("^Registered user open WLS page$")
    public void Registered_user_open_WLS_page() throws Throwable {
        loginNavi.open();
        loginNavi.openLoginPanel();
        loginNavi.enterLoginCrenentials("emailwybitnietestowy@gmail.com", "Delete777");
        loginNavi.clickSignInButtonInLoginPanel();
    }
}
