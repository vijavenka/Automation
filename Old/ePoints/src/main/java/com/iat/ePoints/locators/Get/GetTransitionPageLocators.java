package com.iat.ePoints.Locators.Get;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 31.03.14
 * Time: 09:05
 * To change this template use File | Settings | File Templates.
 */
public class GetTransitionPageLocators {

//Transition

    public Locator transitionModalWindow = new Locator(LocatorType.ID, "modal-content");
    //Sign in button
    public Locator SignInBtn = new Locator(LocatorType.XPATH, "//button[@id='signInBtn']");
        public Locator EmailField = new Locator(LocatorType.XPATH, "//form[@id='signInForm']//input[@type='email']");
            public Locator noEmailRequired = new Locator(LocatorType.XPATH, "//span[contains(text(),'Email address is required')]");
            public Locator noEmailInvalid = new Locator(LocatorType.XPATH, "//span[contains(text(),'Email address is invalid')]");
        public Locator PasswordField = new Locator(LocatorType.XPATH, "//form[@id='signInForm']//input[@type='password']");
            public Locator noPasswordAlert = new Locator(LocatorType.XPATH, "//span[contains(text(),'Password is required')]");
        public Locator ForgotPswdLink = new Locator(LocatorType.XPATH, "//a[text()='Forgot password?']");
            public Locator EmailFieldForgotPassweord = new Locator(LocatorType.XPATH, "//form[@id='forgotPassForm']//input[@type='email']");
            public Locator SendButton = new Locator(LocatorType.ID, "sendBtn");
            // email does not exist warning alert the same
            // success alert the same
            public Locator WrongEmailOrNoEmailAlert = new Locator(LocatorType.XPATH, "//span[@class='error']");
        public Locator authorizationFailedAlert = new Locator(LocatorType.XPATH, "//div[@class='errorMsg alert alert-warning']");

    //Join in button
    public Locator joinHereLink = new Locator(LocatorType.ID, "joinBtn");
        public Locator EmailFieldJoin = new Locator(LocatorType.XPATH, "//form[@id='joinForm']//input[@type='email']");
            public Locator alertSuccess = new Locator(LocatorType.XPATH, "//div[@class='alert alert-success']");
            public Locator alertWarning = new Locator(LocatorType.XPATH, "//div[@class='errorMsg alert alert-warning']");
        public Locator continueToRetailerButton = new Locator(LocatorType.XPATH, "//button[@class='btn continue-to-retailer']");

    //Sign in with facebook
    public Locator SignInUsingFacebookBtn = new Locator(LocatorType.XPATH, "//a[@class='btn btn-social facebook facebook-login']");
        // modal facebook window locators collected in another class

    public Locator retailerNameOnTransitionPage = new Locator(LocatorType.XPATH, "//span[@class='retailer']");
    public Locator learnMoreButton = new Locator(LocatorType.XPATH, "//a[@class='learn-more']");
    public Locator closeLearnMoreButton = new Locator(LocatorType.XPATH, "//a[@class='close-learn-more']");
    public Locator learnMoreInformation = new Locator(LocatorType.XPATH, "//div[@class='epoints-desc open']");

    //Rest of Locators
    public Locator CloseLink = new Locator(LocatorType.XPATH, "//a[@class='close-modal-link link']"); //for old modal transition window
    public Locator CloseBtn = new Locator(LocatorType.XPATH, "//button[@class='close-modal-button']"); //for old modal transition window
}
