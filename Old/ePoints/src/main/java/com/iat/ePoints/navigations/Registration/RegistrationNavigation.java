package com.iat.ePoints.Navigations.Registration;


import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Registration.RegisterPageLocators;
import com.iat.ePoints.Locators.SignIn.LoginPageLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.Keys;

import static org.junit.Assert.assertTrue;

public class RegistrationNavigation extends AbstractPage {

    private  RegisterPageLocators locators = new RegisterPageLocators();
    private LoginPageLocators locators_login = new LoginPageLocators();

    public RegistrationNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public void clickInRegister(){
        executor.click(locators.registerOption);
    }

    public boolean checkTellMeMore(){
        return executor.is_element_present(locators.tellMoreOption);
    }

    public  boolean checkEmailAddress(){
        return executor.is_element_present(locators.email);
    }

    public  boolean checkSignUpBtn(){
        return executor.is_element_present(locators.signUp_button);
    }

    public void clickTelMeMore(){
         executor.click(locators.tellMoreOption);

    }

    public boolean checkWhyRegisterWindow(){
        return executor.is_element_present(locators.whyRegisterPopUp);
    }

    public boolean checkWhyRegisterCloseOption(){
        return executor.is_element_present(locators.closePopUpOption);
    }

    public boolean checkWhyRegisterCloseButton(){
        return executor.is_element_present(locators.closePopUpButton);
    }

    public void clickWhyRegisterCloseButton(){
        executor.click(locators.closePopUpButton);

    }

    public void clickWhyRegisterCloseOption(){
        executor.click(locators.closePopUpOption);
    }

    public void clickWhyRegisterESC_key(){
        executor.send_keys(locators.whyRegisterPopUp, Keys.ESCAPE);
    }

    public void clickSignUp(){
        executor.click(locators.signUp_button);
    }


    public boolean checkEmailSystemMessage(){
        return executor.is_element_present(locators.emailMessage);
    }

    public boolean checkEmailLenghtSystemMessage(){
        return executor.is_element_present(locators.emailLenghtDuplicateMessage);
    }

    public String returnEmailSystemMessage(){
        return executor.getText(locators.emailMessage);
    }

    public String returnEmailLenghtDuplicateSystemMessage(){
        return executor.getText(locators.emailLenghtDuplicateMessage);
    }

    public void enterStringToEmailField(String send_keys){
        executor.send_keys(locators.email, send_keys);

    }

    public void checkVisibilityOfCheckYourInboxInformation(){
        executor.is_element_present(locators.emailInboxInformation);

    }

    public void checkVisibilityOfHiUserNameWelcome(){
        executor.is_element_present(locators.hiUserNameWelcome);

    }

    public boolean checkcorrectnessOfPointsAmount(int points_number)
    {
        if(points_number == Integer.parseInt(executor.getText(locators.yourEpoints)))
        {
            return true;
        }else{
            return false;
        }
    }
    public int getEmailFieldLength(){
        return executor.get_element(locators.email).getText().length();
    }

    /**
     * ******************************************************************************************************************
     * Navigations for scenarios given in file RegistrationEmail.feature
     * ******************************************************************************************************************
     */
    //scenario 1 ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public void checkVisibilityOfcheckYourEmailMessage(){
        executor.is_element_present(locators.checkYourEmailMessage);
    }

    // Resend confirmation email for verified User, Resend confirmation email for not verified email ///////////////////
    public void clickResentConfirmationEmailLink(){
        executor.click(locators_login.resend_email_link);
    }

    public void clickResendEmailButton(){
        executor.click(locators_login.resend_email_button);
    }

    public void checkIfResendErrorAlertIsDisplayed(String isVerified) throws InterruptedException {
        if(isVerified.equals("yes")){
            assertTrue("Error alert is no visible", executor.is_element_present(locators_login.resend_email_alert_error));
        }else if(isVerified.equals("no")){
            assertTrue("Success alert is no visible", executor.is_element_present(locators_login.resend_email_alert_success_));
        }
    }

}
