package com.iat.ePoints.Navigations.SignIn;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Checkout.SelectedRewardsLocators;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Locators.SignIn.LoginPageLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.*;

public class LogInNavigation extends AbstractPage {

    private LoginPageLocators locators = new LoginPageLocators();
    private HeaderLocators locators_header = new HeaderLocators();
    private SelectedRewardsLocators locators_selected = new SelectedRewardsLocators();

    public LogInNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public boolean checkSignIn_option() {
        return executor.is_element_present(locators.signIn_opt);
    }

    public void clickSignIn_option() throws InterruptedException {
        executor.click(locators.signIn_opt);
        Thread.sleep(2000);//has to stay
    }

    public void checkAuthPanel_isDisplay() {
        assertTrue("Authentication panel is not displayed", executor.is_element_present(locators.authPanel_area));
    }

    public void checkEmail_lbl_isDisplay() {
        assertTrue("Email address field label is not available", executor.is_element_present(locators.email_lbl));
        assertEquals("Email address field label is incorrect", "Email address", executor.getText(locators.email_lbl));
    }

    public void checkEmail_fld_isDisplay() {
        assertTrue("Email field is not available", executor.is_element_present(locators.email_fld));
    }

    public void checkPassword_lbl_isDisplay() {
        assertTrue("Password field label is not available", executor.is_element_present(locators.pwd_lbl));
        assertEquals("Password field label is incorrect", "Password", executor.getText(locators.pwd_lbl));
    }

    public void checkPassword_fld_isDisplay() {
        assertTrue("Password field is not available", executor.is_element_present(locators.pwd_fld));
    }

    public void checkForgotPassword_opt_isDisplay() {
        assertTrue("Forgot password option is not available", executor.is_element_present(locators.forgot_password_opt));
        assertEquals("Forgot password option name is incorrect", "Forgot password?", executor.getText(locators.forgot_password_opt));
    }

    public void checkSignIn_btn_isDisplay() {
        assertTrue("Sign In button is not available", executor.is_element_present(locators.signIn_btn));
        assertEquals("Sign In button name is incorrect", "Sign in", executor.getText(locators.signIn_btn));
    }

    public void checkJoinHere_opt_isDisplayed() {
        assertTrue("Start here option is not available", executor.is_element_present(locators.join_here_opt));
        assertEquals("Start here option name is incorrect", "Join here", executor.getText(locators.join_here_opt));
    }

    public void checkResendConfirmationEmailVisibility() {
        assertTrue(executor.is_element_present(locators.resend_email_link));
    }

    public void checkLoginUsinFacebookButtonVisibility() {
        assertTrue(executor.is_element_present(locators.facebookLoginOption));
    }

    public void checkCloseLinkVisibility() {
        assertTrue(executor.is_element_present(locators.close_link));
    }

    public void clickSignIn_btn() throws InterruptedException {
        executor.click(locators.signIn_btn);
    }

    public void checkEmailWarning_isDisplay() {
        assertTrue("Email error message area is not available", executor.is_element_present(locators.emailError_msg));
    }

    public void checkPasswordWarning_isDisplay() {
        assertTrue("Password error message area is not available", executor.is_element_present(locators.passwordError_msg));
    }

    public void checkEmailWarning_msg() {
        assertEquals("Email error message is incorrect", "Email address is required", executor.getText(locators.emailError_msg));
    }

    public void checkPasswordWarning_msg() {
        assertEquals("Password error message is incorrect", "Password is required", executor.getText(locators.passwordError_msg));
    }

    public void fillEmail_fld(String emailValue) throws InterruptedException {
        executor.send_keys(locators.email_fld, emailValue);
    }

    public void fillPassword_fld(String passwordValue) throws InterruptedException {
        executor.send_keys(locators.pwd_fld, passwordValue);
    }

    public void checkAuthPanelSystemWarning_isDisplay() {
        assertTrue("Authorization panel system message area is not available", executor.is_element_present(locators.authPanel_msg));
    }

    public void checkAuthPanelSystemWarning_msg() {
        assertEquals("Authorization panel system message is incorrect", "Authorization failed.", executor.getText(locators.authPanel_msg));
    }


    public boolean checkIsUserLoggIn() {
        return executor.is_element_present(locators.signOut_opt);
    }

    public void clickSignOut_opt() {
        executor.click(locators.signOut_opt);
    }

    public void checkSignOutSuccessful() {
        assertTrue("Sign in option is no available", executor.is_element_present(locators.signIn_opt));
    }


    public boolean checkIsBaskedOpened() {
        return executor.is_element_present(locators_selected.pageTitle);
    }

    public void clickInOrderTheseRewardsStep2_btn() {
        executor.click(locators_selected.order_btn);
    }

    public boolean checkIsLoginModalShown() {
        if (!executor.is_element_present(locators.modalWindow)) return false;

        else if (!executor.is_element_present(locators.signIn_btn)) return false;

        else return true;
    }

    public void openMyAccountMainPage() {
        executor.open("/test/myAccountPage");
    }

    public void openMyAccountDashboard() {
        executor.open("/test/myAccountPage#tab=dashboard");
    }

    public void openMyAccountMyProfile() {
        executor.open("/test/myAccountPage#tab=myDetails");
    }

    public void openMyAccount_ePointsStatement() {
        executor.open("/test/myAccountPage#tab=ePointsStatement");
    }

    public void openMyAccountRewardsHistory() {
        executor.open("/test/myAccountPage#tab=rewardsHistory");
    }

    public void checkIfPointsWereAdedAfterAccountCreation(int arg1) {
        assertEquals("Points number after creating account is improper", Integer.toString(arg1), executor.getText(locators_header.poinsMainPageCounter));
    }
}
