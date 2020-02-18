package com.iat.ePoints.Navigations.Registration;

import com.iat.ePoints.EnvironmentVariables;
import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Registration.CreatePasswordLocators;
import com.iat.ePoints.Locators.Registration.RegisterPageLocators;
import com.iat.ePoints.Locators.SignIn.LoginPageLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 29.01.14
 * Time: 12:18
 * To change this template use File | Settings | File Templates.
 */

public class CreatePasswordNavigation extends AbstractPage {

    private CreatePasswordLocators locatros_passwordCreate = new CreatePasswordLocators();
    private RegisterPageLocators locators_register = new RegisterPageLocators();
    private EnvironmentVariables envVariables= new EnvironmentVariables();
    private LoginPageLocators locators_login = new LoginPageLocators();

    public CreatePasswordNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // Checking if create password page view is correct ////////////////////////////////////////////////////////////////
    public void joinEpoints() {
        open();
        executor.click(locators_register.registerOption);
        executor.send_keys(locators_register.email, "emailwybitnietestowy3@gmail.com");
        executor.click(locators_register.signUp_button);
    }

    public void followConfirmationLink() throws SQLException, InterruptedException {
        Thread.sleep(2000);//has to stay
        JDBC jdbc = new JDBC("points_manager");
        executor.return_driver().get(envVariables.baseUrl+"/join/confirm-email/"+jdbc.return_proper_db_value("SELECT tokenValue FROM points_manager.UserToken WHERE tokenType = 'REGISTER' AND user_id='"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId", "emailwybitnietestowy3@gmail.com"),1)+"'",1));
        executor.handleMultipleWindows("Complete your epoints profile | epoints");
        jdbc.close();
    }

    public void checkIfPasswordFieldIsAvaliable(){
        assertTrue("Password field is no visible", executor.is_element_present(locatros_passwordCreate.passwordField));
    }

    public void checkIfPasswordConfirmationFieldIsAvaliable(){
        assertTrue("Confirm password field is no visible", executor.is_element_present(locatros_passwordCreate.confirmPasswordField));
    }

    public void checkIfFirstNameFieldIsAvaliable(){
        assertTrue("Name field is no visible", executor.is_element_present(locatros_passwordCreate.firstNameField));
    }

    public void checkIfFirstLastFieldIsAvaliable(){
        assertTrue("Last name field is no visible", executor.is_element_present(locatros_passwordCreate.lastNameField));
    }

    public void checkIfDoneButtonIsAvaliable(){
        assertTrue("Done button is no visible", executor.is_element_present(locatros_passwordCreate.doneButton));
    }

    // Checking if set password link from confirmation email is working correctly //////////////////////////////////////
    public void pressDoneButton(){
       executor.click(locatros_passwordCreate.doneButton);
    }

    public void checkPasswordAlertVisibility(boolean isTrue){
        if(isTrue){
            assertTrue("Password alert is invisible",executor.is_element_present(locatros_passwordCreate.passwordFieldAlert));
        }else if(!isTrue){
            assertFalse("Password alert is visible but should not", executor.is_element_present(locatros_passwordCreate.passwordFieldAlert));
        }
    }

    public void checkConfirmPasswordAlertVisibility(boolean isTrue){
        if(isTrue){
            assertTrue("Password confirmation alert is invisible",executor.is_element_present(locatros_passwordCreate.confirmPasswordFieldAlert));
        }else if(!isTrue){
            assertFalse("Password confirmation alert is visible but should not",executor.is_element_present(locatros_passwordCreate.confirmPasswordField));
        }
    }

    public void checkNameAlertVisibility(boolean  isTrue){
        if(isTrue){
            assertTrue("Name alert is invisible",executor.is_element_present(locatros_passwordCreate.firstNameFieldAlert));
        }else if(!isTrue){
            assertFalse("Name alert is visible but should not",executor.is_element_present(locatros_passwordCreate.firstNameFieldAlert));
        }
    }

    public void checkLastNameAlertVisibility(boolean isTrue){
        if(isTrue){
            assertTrue("Last name alert is invisible",executor.is_element_present(locatros_passwordCreate.lastNameFieldAlert));
        }else if(!isTrue){
            assertFalse("Last name alert is visible but should not",executor.is_element_present(locatros_passwordCreate.lastNameFieldAlert));
        }
    }

    public void enterNewPassword(String password){
        executor.send_keys(locatros_passwordCreate.passwordField, password);
    }

    public void enterConfirmNewPassword(String confirmPassword){
        executor.send_keys(locatros_passwordCreate.confirmPasswordField, confirmPassword);
    }

    public void enterName(String name){
        executor.send_keys(locatros_passwordCreate.firstNameField, name);
    }

    public void enterLastName(String lastName){
        executor.send_keys(locatros_passwordCreate.lastNameField, lastName);
    }

    // Checking if account activation working correctly ////////////////////////////////////////////////////////////////
    public void checkIfAccountWasActivated() throws SQLException, InterruptedException {
        JDBC jdbc = new JDBC("points_manager");
        Thread.sleep(2000);//has to stay
        assertTrue("Account was not activated", jdbc.return_proper_db_value("SELECT verified FROM points_manager.User WHERE email='emailwybitnietestowy3@gmail.com'", 1).equals("1"));
        jdbc.close();
    }

    public void checkIfAllDoneScreenIsVisible(){
        assertEquals("Page title is incorrect", "You're officially an epoints member | epoints", executor.return_driver().getTitle());
        //assertTrue("All done screan is no visible", executor.is_element_present(locatros_passwordCreate.allDoneSentence));
    }

    public void checkVisibilityOfTellMeMorelink(){
        assertTrue("Tell me more link is no visible", executor.is_element_present(locatros_passwordCreate.TellMeBitMoreLink));
    }

    public void checkIfTellMeMoreLinkWorksCorrectly(){
        executor.click(locatros_passwordCreate.TellMeBitMoreLink);
        assertTrue("Page URL is incorrect", executor.return_driver().getCurrentUrl().contains("/about"));
        executor.return_driver().navigate().back();
    }

    public void checkVisibilityOfGetEpointsButton(){
        assertTrue("Get epoints button is no visible", executor.is_element_present(locatros_passwordCreate.getEpointsButton));
    }

    public void checkIfGetEpointsButtonWorksCorrectly(){
        executor.click(locatros_passwordCreate.getEpointsButton);
        assertEquals("Page URL is incorrect", envVariables.baseUrl+"/get-epoints", executor.return_driver().getCurrentUrl());
        executor.return_driver().navigate().back();
    }

    // Check If forgot your password page is available and has proper content //////////////////////////////////////////
    public void openForgotPasswordPage(){
        executor.click(locators_login.signIn_opt);
        executor.click(locators_login.forgot_password_opt);
    }

    public void checkForgotPasswordPageTitle(){
        assertEquals("Forgot password page title is no correect", "Forgotten your password | epoints", executor.return_driver().getTitle());
        assertTrue("Forgot password page URL is no correct", executor.return_driver().getCurrentUrl().contains("/forgot"));
    }

    public void checkAvaliablityOfAllElements(){
        assertTrue("Email text field is no visible",executor.is_element_present(locatros_passwordCreate.forgotPasswordEmailTextField));
        assertTrue("Send button is no visible",executor.is_element_present(locatros_passwordCreate.forgotPasswordPageSendButton));
        assertTrue("Page header is no visible",executor.is_element_present(locatros_passwordCreate.forgotPasswordPageHeader));
    }

    // Check If forgot your password page returns proper alerts ////////////////////////////////////////////////////////
    public void clickSendButton(){
        executor.click(locatros_passwordCreate.forgotPasswordPageSendButton);
    }

    public void checkIfEmailRequiredAlertAppear(){
        assertTrue("Email required alert is no visible", executor.is_element_present(locatros_passwordCreate.forgotPasswordEmailIsRequiredAlert));
    }

    public void enterUserEmail(String email){
        executor.send_keys(locatros_passwordCreate.forgotPasswordEmailTextField, email);
    }

    public void checkIfEmailDoesNotExistAlertAppear(){
        assertTrue("Email does not exist alert is no visible", executor.is_element_present(locatros_passwordCreate.forgotPasswordEmaildoesNotExistAlert));
    }

    // Check If password restoration link is created and is working ////////////////////////////////////////////////////
    public void checkVisibilityOfSuccessAlert(){
        assertTrue("Success Alert is no visible", executor.is_element_present(locatros_passwordCreate.forgotPasswordEmailSendingSuccesAlert));
    }

    public void followRestorePasswordConfirmationLink(String userEmail) throws SQLException, InterruptedException {
        JDBC jdbc = new JDBC("points_manager");
        Thread.sleep(2000);//has to stay
        String resetPasswordLink = envVariables.baseUrl+"/reset/"+jdbc.return_proper_db_value("SELECT tokenValue FROM points_manager.UserToken WHERE tokenType ='FORGOTTEN_PASSWORD' AND user_id='"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",userEmail),1)+"' ORDER BY createdAt DESC",1);
        executor.return_driver().get(resetPasswordLink);
        jdbc.close();
    }

    public void checkIfProperPasswordRestorationPageWasOpened(){
        executor.handleMultipleWindows("Reset your password | epoints");
        assertTrue("Page header is no correct", executor.is_element_present(locatros_passwordCreate.changeYouPassworrdHeader));
    }

    public void enterDataAndConfirmNewPassword(String newPassword){
        executor.send_keys(locatros_passwordCreate.passwordField, newPassword);
        executor.send_keys(locatros_passwordCreate.confirmPasswordField, newPassword);
        executor.click(locatros_passwordCreate.changeYourPasswordChangeButton);
        assertTrue("Success alert is no visible", executor.is_element_present(locatros_passwordCreate.changeYourPasswordSuccessAlert));
    }
}
