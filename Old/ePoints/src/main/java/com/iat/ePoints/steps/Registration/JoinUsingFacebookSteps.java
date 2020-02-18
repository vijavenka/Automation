package com.iat.ePoints.Steps.Registration;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Registration.JoinUsingFacebookNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 02.01.14
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */
public class JoinUsingFacebookSteps {

    private IExecutor executor;
    private JoinUsingFacebookNavigation regFaceNavi;

    public JoinUsingFacebookSteps(SeleniumExecutor executor) {
        this.executor = executor;

    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        regFaceNavi = new JoinUsingFacebookNavigation(executor);
    }

    @After
    public void tear_down() throws SQLException {
        regFaceNavi.closeDBConnection();
        executor.stop();
    }

    // 1 Check if Join interface has proper facebook option ////////////////////////////////////////////////////////////
    @And("^Join via facebook is available on main page$")
    public void Join_via_facebook_is_available_on_main_page() {
        regFaceNavi.checkIfFacebookOptionIsAvailableOnMainPage();
    }

    @Then("^Join interface should has sign in with facebook option$")
    public void Join_interface_should_has_sign_in_with_facebook_option() throws Throwable {
        go_sleep(2000);
        regFaceNavi.checkVisibilityOfSignInWithFacebookButton();
    }

    @Then("^Sign in with facebook button should works$")
    public void Sign_in_with_facebook_button_should_works() throws Throwable {
        regFaceNavi.clickSignInWithFacebookButton();
    }

    @Then("^Sign in with facebook window should has proper content$")
    public void Sign_in_with_facebook_window_should_has_proper_content() throws Throwable {
        regFaceNavi.checkContentOfFacebbokLoginWindow();
    }

    // 2 Check if user can join using facebook and all needed data are properly download to epoints ////////////////////
    @Then("^User enter facebook credentials '(.+)' '(.+)' and confirm login details$")
    public void User_enter_facebook_credentials_and_confirm_login_details(String email, String password) throws Throwable {
        regFaceNavi.enterFacebokCredentials(email, password);
    }

    @Then("^User can see that he received '(.+)' points$")
    public void User_can_see_that_he_received_fifty_points(String pointsNumber) throws Throwable {
        regFaceNavi.checkIfUserHas50Points(pointsNumber);
    }

    @And("^No confirmation email should be sent$")
    public void No_confirmation_email_should_be_sent() throws Throwable {
        regFaceNavi.checkIfConfirmationEmailMessageIsInvisible();
    }

    @And("^Congratulation message should be displayed$")
    public void Congratulation_message_should_be_displayed() throws Throwable {
        regFaceNavi.checkVisibilityOfCongratulationMessage();
    }

    @And("^User can see that his avatar is visible$")
    public void User_can_see_that_his_avatar_is_visible() throws Throwable {
        regFaceNavi.checkIfUserAvatarIsVisible();
    }

    @Then("^His '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' were properly downloaded$")
    public void His_details_were_properly_downloaded(String name, String lastName, String gender, String birthDate, String email) throws Throwable {
        regFaceNavi.goToUserProfile();
        regFaceNavi.checkCorrectnessOfFbDetails(name, lastName, gender, birthDate, email);
    }

    @Then("^All data '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' are correctly stored in db$")
    public void All_data_are_correctly_stored_in_db(String name, String lastName, String gender, String birthDate, String email) throws Throwable {
        regFaceNavi.checkIfUserDataAreProperlyStoredInDb(name, lastName, gender, birthDate, email);
    }

    @Then("^User is recognized as facebook user$")
    public void User_is_recognized_as_facebook_user() throws Throwable {
        regFaceNavi.checkIfUserWasRecognizedAsFacebookUser();
    }
}
