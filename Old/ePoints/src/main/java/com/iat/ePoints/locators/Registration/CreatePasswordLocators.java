package com.iat.ePoints.Locators.Registration;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 29.01.14
 * Time: 12:19
 * To change this template use File | Settings | File Templates.
 */
public class CreatePasswordLocators {

// Passwords Creation text fields
    public Locator passwordField = new Locator(LocatorType.XPATH, "//input[@id='pass']");
    public Locator confirmPasswordField = new Locator(LocatorType.XPATH, "//input[@id='retypePass']");
    public Locator firstNameField = new Locator(LocatorType.XPATH, "//input[@name='firstName']");
    public Locator lastNameField = new Locator(LocatorType.XPATH, "//input[@name='lastName']");
// Passwords Creation alerts
    public Locator passwordFieldAlert = new Locator(LocatorType.XPATH, "//span[contains(text(),'Password is required')]");
    public Locator confirmPasswordFieldAlert = new Locator(LocatorType.XPATH, "//span[contains(text(),'Passwords do not match')]");
    public Locator firstNameFieldAlert = new Locator(LocatorType.XPATH, "//span[contains(text(),'First Name is required')]");
    public Locator lastNameFieldAlert = new Locator(LocatorType.XPATH, "//span[contains(text(),'Last Name is required')]");

    public Locator doneButton = new Locator(LocatorType.XPATH, "//button[@id='completeRegBtn']");
// After account activation Locators
    public Locator allDoneSentence= new Locator(LocatorType.XPATH, "//h1[contains(text(),'All done')]");
    public Locator getEpointsButton = new Locator(LocatorType.XPATH, "//a[@class='btn btn-large btn-green']");
    public Locator TellMeBitMoreLink = new Locator(LocatorType.XPATH, "//a[contains(text(),'Tell me a bit more')]");

// Forgot your password Locators
    public Locator forgotPasswordPageHeader = new Locator(LocatorType.XPATH, "//h1[contains(text(),'Forgot your password')]");
    public Locator forgotPasswordEmailTextField = new Locator(LocatorType.XPATH, "//input[@type='email']");
    public Locator forgotPasswordPageSendButton = new Locator(LocatorType.XPATH, "//a[@id='passReset']");
    public Locator forgotPasswordEmailIsRequiredAlert = new Locator(LocatorType.XPATH, "//span[contains(text(),'Email is required')]");
    public Locator forgotPasswordEmaildoesNotExistAlert = new Locator(LocatorType.XPATH, "//fieldset//div");
    public Locator forgotPasswordEmailSendingSuccesAlert = new Locator(LocatorType.XPATH, "//div[contains(text(),'Email sent! Follow the link in your email.')]");
// Change your password Locators
    public Locator changeYouPassworrdHeader = new Locator(LocatorType.XPATH, "//h1[contains(text(),'Change your password')]");
    public Locator changeYourPasswordChangeButton = new Locator(LocatorType.XPATH, "//a[@id='changePassBtn']");
    public Locator changeYourPasswordSuccessAlert = new Locator(LocatorType.XPATH, "//div[@class='alert alert-success']");
}
