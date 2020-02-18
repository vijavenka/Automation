package com.iat.ePoints.Steps.SignIn;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Registration.JoinUsingFacebookNavigation;
import com.iat.ePoints.Navigations.SignIn.SignInUsingFacebookNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 03.01.14
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
public class SignInUsingFacebookSteps {

    private IExecutor executor;
    private SignInUsingFacebookNavigation signFbNavi;
    private JoinUsingFacebookNavigation regFaceNavi;

    public SignInUsingFacebookSteps(SeleniumExecutor executor) {
        this.executor = executor;

    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        signFbNavi = new SignInUsingFacebookNavigation(executor);
        regFaceNavi = new JoinUsingFacebookNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // 1 USER SIGN IN - handle NON-verified accounts for first time Facebook sign ups(RD-660) //////////////////////////
    @And("^Enter email '(.+)' which will be later connected with facebook account$")
    public void Enter_email_emailwybitnietestowy_gmail_com_which_will_be_later_connected_with_facebook_account(String email) throws Throwable {
        signFbNavi.enterEmailConectedWitFacebookAccount(email);
    }

    // 2 USER SIGN IN - handle NON-successful first time sign in via Facebook //////////////////////////////////////////
    @Then("^His '(.+)' '(.+)' were properly downloaded and '(.+)' '(.+)' '(.+)' were not imported$")
    public void His_details_were_properly_downloaded(String name, String lastName, String gender, String birthDate, String email) throws Throwable {
        regFaceNavi.goToUserProfile();
        signFbNavi.checkCorrectnessOfFbDetails(name, lastName, gender, birthDate, email);
    }

    // 4 USER SIGN IN - handle successful first time sign in via Facebook (RD-658) /////////////////////////////////////
    @Then("^Facebook id should be send via cookies and it is the same as in database$")
    public void Facebook_id_should_be_send_via_cookies_and_it_is_the_same_as_in_database() throws Throwable {
        signFbNavi.getFacebookIdAndCompareItWithDatabase();
    }

}
