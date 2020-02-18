package com.iat.ePoints.Steps.Get;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 18.06.13
 * Time: 14:17
 * To change this template use File | Settings | File Templates.
 */

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Get.GetLandingPageNavigation;
import com.iat.ePoints.Navigations.Get.GetStoresAZPageNavigation;
import com.iat.ePoints.Navigations.Get.GetTransitionPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GetTransitionPageSteps {

    private IExecutor executor;
    private GetLandingPageNavigation earnLandingNavi;
    private GetStoresAZPageNavigation earnAZNavi;
    private GetTransitionPageNavigation transitionNavi;

    public GetTransitionPageSteps(SeleniumExecutor executor) {
        this.executor = executor;

    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        earnLandingNavi = new GetLandingPageNavigation(executor);
        earnAZNavi = new GetStoresAZPageNavigation(executor);
        transitionNavi = new GetTransitionPageNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // AFFILAITE MANAGER - enable the use of deeplinks outside of solr (RD-1553) - check if transition was opened using deeplink and check clickout
    @Given("^Tranition page is opened using deeplink$")
    public void Tranition_page_is_opened_using_deeplink() throws Throwable {
       // transitionNavi.openTransitionPageUsingDeeplink();
    }

    @When("^New clickout for deeplink should be reported$")
    public void New_clickout_for_deeplink_should_be_repoted() throws Throwable {
        //transitionNavi.checkClikoutForDeeplink();
    }

    // TRANSITION PAGE DESKTOP- new sign in option using email/password on module page - modal content
    @Given("^Shops A-Z page is opened$")
    public void Shops_A_Z_page_is_opened() throws Throwable {
        earnLandingNavi.open();
        earnAZNavi.goToShopsAZpage();
        go_sleep(5000);
    }

    @When("^Select Random retailer$")
    public void Select_Random_retailer() throws Throwable {
        //Leave empty
    }

    @When("^Click in link to transfer into retailer modal$")
    public void Click_in_link_to_transfer_into_retailer_page_shopadoo() throws Throwable {
        transitionNavi.clickInFirstRetailerFromList();
    }

    @Then("^Transition modal window should be displayed$")
    public void Transition_modal_window_should_be_displayed() throws Throwable {
        transitionNavi.checkIfTransitionModalIsVisible();
    }

    @When("^Checking view of panel$")
    public void Checking_view_of_panel() throws Throwable {
        //Leave empty
    }


    @Then("^Join here link is available$")
    public void join_here_link_is_available() throws Throwable {
        transitionNavi.checkVisibilityOfJoinHeareLink();
    }


    @Then("^Sign in button is available$")
    public void Sign_in_button_is_available() throws Throwable {
        transitionNavi.checkVisibilityOfSignInButton();
    }

    @Then("^Sign in using facebook is available$")
    public void Sign_in_using_facebook_is_available() throws Throwable {
        transitionNavi.checkVisibilityOfSignInUsingFacebookButton();
    }

    @Then("^Learn more button is available$")
    public void Learn_more_button_is_available() throws Throwable {
        transitionNavi.checkIfLearnMoreButtonIsVisible();
    }

    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - page content
    @Given("^Transition page is opened$")
    public void Transition_page_is_opened() throws Throwable {
        transitionNavi.openFullWindowTransitionPage();
        transitionNavi.checkIsAuthorizationPanelShown();
    }

    //TRANSITION PAGE DESKTOP- new sign in option using email/password on module page (RD - 2671).
    @When("^Click in transition Sign In button$")
    public void Click_in_transition_Sign_In_button() throws Throwable {
        transitionNavi.clickSignInButton();
    }

    @When("^Fill out Sign In form with using correct data '(.+)' '(.+)'$")
    public void Fill_out_Sign_In_form_with_using_correct_data(String Email, String Password) throws Throwable {
        transitionNavi.enterEmailInTransitionPage(Email);
        transitionNavi.enterPasswordInTransiotionPage(Password);
    }

    @Then("^Transition page should be replaced with retailer page$")
    public void Transition_page_should_be_replaced_with_retailer_page() throws Throwable {
        transitionNavi.setCurrentDate();
        //transitionNavi.checkIfTransitionPageIsInvisible();
    }

    @Then("^New clickout should be reported '(.+)' '(.+)'$")
    public void New_clickout_should_be_reported(String ifSigned, String Email) throws Throwable {
        transitionNavi.checkIfClickoutWasReported(ifSigned, Email);
    }

    //TRANSITION PAGE DESKTOP- new sign in option using email/password on module page (RD - 2671) wrong ~password
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - wrong password
    @When("^User try to login in transition page using improper data '(.+)' '(.+)'$")
    public void User_try_to_login_in_in_transition_page_using_improper_data(String Email, String Password) throws Throwable {
        go_sleep(4000);
        transitionNavi.enterEmailInTransitionPage(Email);
        transitionNavi.enterPasswordInTransiotionPage(Password);
    }

    @Then("^Authorization failed alert should be displayed$")
    public void Authorization_failed_alert_should_be_displayed() throws Throwable {
        transitionNavi.checkIfAuthorizationFailedAlertIsDisplayed();
    }

    //TRANSITION PAGE DESKTOP- new sign in option using email/password on module page (RD - 2671) - no login data
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - no login data
    @Then("^Alerts of needed email and password should be displayed$")
    public void Alerts_of_needed_email_and_password_should_be_displayed() throws Throwable {
        transitionNavi.checkIfEmailAndPasswordAlertsAreVisible();
    }

    @When("^Click in transition Sign In button twice$")
    public void Click_in_transition_Sign_In_button_twice() throws Throwable {
        transitionNavi.clickSignInButton();
        transitionNavi.clickSignInButton();
    }

    // TRANSITION PAGE DESKTOP - new sign in option using FACEBOOK on module page (RD-2701).
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - facebook login
    @When("^Sign in using facebook button will be clicked$")
    public void Sign_in_using_facebook_button_will_be_clicked() throws Throwable {
        transitionNavi.clickSignInUsingFacebookButton();
        //switch to properbrowse window
        executor.handleMultipleWindows("Facebook");
    }

    // TRANSITION PAGE DESKTOP - new JOIN option on module page (RD-2711) - join correct
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - join correct
    @When("^Join button will be used$")
    public void Join_button_will_be_used() throws Throwable {
        transitionNavi.clickJoinButton();
    }

    @When("^New email will be typed$")
    public void New_email_will_be_typed() throws Throwable {
        transitionNavi.enterNewUniqueEmail();
    }

    @Then("^Success alert will be displayed$")
    public void Success_alert_will_be_displayed() throws Throwable {
        transitionNavi.checkIfSuccessAlertIsVisible();
    }

    @Then("^User will be stored in database as not verified$")
    public void User_will_be_stored_in_database_as_not_verified() throws Throwable {
        transitionNavi.checkIfUserEmailWasStoredAsNotVerified();
    }

    @Then("^Go to retailer button will be available$")
    public void Go_to_retailer_button_will_be_available() throws Throwable {
        transitionNavi.checkIfGoToRetailerButtonIsAvailable();
    }

    @When("^User use go to retailer button$")
    public void User_use_go_to_retailer_button() throws Throwable {
        transitionNavi.clickGoToRetailerButton();
    }

    // TRANSITION PAGE DESKTOP - new JOIN option on module page (RD-2711) - email taken
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - email taken
    @When("^Taken email will be typed$")
    public void Taken_email_will_be_typed() throws Throwable {
        transitionNavi.enterTakenEmail();
    }

    @Then("^Warning alert will be displayed$")
    public void Warning_alert_will_be_displayed() throws Throwable {
        transitionNavi.checkIfWarningAlertIsVisible();
    }

    // TRANSITION PAGE DESKTOP - new FORGOTTEN PASSWORD option on module page (RD-2741) - correct email
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - correct email
    @When("^Forgot password Link will be clicked$")
    public void Forgot_password_Link_will_be_clicked() throws Throwable {
        transitionNavi.clickForgotPasswordButton();
    }

    @Then("^User can type email to retrieve password '(.+)'$")
    public void User_can_type_email_to_retrieve_password(String validEmail) throws Throwable {
        transitionNavi.enterEmailForWhichPasswordHasToBeRetrieved(validEmail);
    }

    @When("^User press send button$")
    public void User_press_send_button() throws Throwable {
        transitionNavi.clickSendButton();
    }

    // TRANSITION PAGE DESKTOP - new FORGOTTEN PASSWORD option on module page (RD-2741) - incorre ctemail, no email
    // TRANSITION PAGE - create module approach view for full page view (RD-2661) - incorrect email, no email
    @Then("^Email invalid alert will be displayed$")
    public void Email_invalid_alert_will_be_displayed() throws Throwable {
        transitionNavi.checkIfEmailInvalidIsVisible();
    }

    @When("^User type no password$")
    public void User_type_no_password() throws Throwable {
        transitionNavi.clearEmailFieldForForgotPasswordModule();
    }

    @Then("^Email required alert will be displayed$")
    public void Email_required_alert_will_be_displayed() throws Throwable {
        transitionNavi.checkIfEmailRequiredIsVisible();
    }

    // AFFILIATE MANAGER - cross channel sales tracking logic (RD-2837).
    @Given("^Transition page is opened with additional p parameters$")
    public void Transition_page_is_opened_with_additional_p_parameters() throws Throwable {
        transitionNavi.openTransitionPageWithAdditionalPParameterts();
    }

    @Then("^P parameters should correctly be stored in database$")
    public void P_parameters_should_correctly_be_stored_in_database() throws Throwable {
        transitionNavi.checkIfPParametersWereCorrectlyStoredInDB();
    }
}


