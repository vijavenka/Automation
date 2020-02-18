package com.iat.ePoints.Locators.SignIn;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

public class LoginPageLocators implements IPageLocators {

    public Locator signIn_opt = new Locator(LocatorType.XPATH, "//a[@class='signIn status-0']");
    public Locator authPanel_area = new Locator(LocatorType.XPATH, "//div[@class='authPanel']");
    public Locator email_lbl = new Locator(LocatorType.XPATH, "//label[@for='email']");
    public Locator email_fld = new Locator(LocatorType.XPATH, "//input[@type='email']");
    public Locator pwd_lbl = new Locator(LocatorType.XPATH, "//label[@for='password']");
    public Locator pwd_fld = new Locator(LocatorType.XPATH, "//input[@type='password']");
    public Locator forgot_password_opt = new Locator(LocatorType.XPATH, "//a[@class='forgotPass link']");
    public Locator signIn_btn = new Locator(LocatorType.XPATH, "//button[@id='signInBtn']");
    public Locator join_here_opt = new Locator(LocatorType.XPATH, "//a[text()='Join here']");
    public Locator resend_email_link = new Locator(LocatorType.XPATH, "//a[text()='Resend confirmation email']");
    public Locator resend_email_button = new Locator(LocatorType.XPATH, "//button[@id='resendEmail']");
    public Locator resend_email_alert_error = new Locator(LocatorType.XPATH, "//div[contains(text(),'User has been already verified')]");
    public Locator resend_email_alert_success_ = new Locator(LocatorType.XPATH, "//div[contains(text(),'Email re-sent!')]");
    public Locator close_link = new Locator(LocatorType.XPATH, "//a[@class='signInClose link']");
    public Locator emailError_msg = new Locator(LocatorType.XPATH, "//span[contains(text(),'Email address is required')]");
    public Locator passwordError_msg = new Locator(LocatorType.XPATH, "//span[contains(text(),'Password is required')]");
    public Locator authPanel_msg = new Locator(LocatorType.XPATH, "//div[@class='errorMsg alert alert-warning']");
    public Locator signOut_opt = new Locator(LocatorType.XPATH, "//a[@class='signOut']");
    public Locator getRewards_menu = new Locator(LocatorType.XPATH, "//li[@data-menu='GetRewards']/a");
    public Locator redemtionItems_title_links = new Locator(LocatorType.XPATH, "//strong[@itemprop='name']/a");
    public Locator selectThisReward_RedemptionItemPage_btn = new Locator(LocatorType.XPATH, "//button[@class='btn btn-primary add-to-basket']");
    public Locator basket_btn = new Locator(LocatorType.XPATH, "//div[@class='order-items-counter basket-preview-trigger']");
    public Locator orderTheseRewards_opt = new Locator(LocatorType.XPATH, "//a[@class='btn btn-primary']");
    public Locator modalWindow = new Locator(LocatorType.XPATH, "//div[@class='module-modal']");
//Facebook Locators
    public Locator facebookLoginOption = new Locator(LocatorType.XPATH, "//span[contains(text(),'Sign in with facebook')]");
    public Locator facebookJoinOption = new Locator(LocatorType.XPATH, "//span[contains(text(),'Sign in with facebook')]");
    //new window name to switch function Facebook
    public Locator facebookLoginEmailTextfield = new Locator(LocatorType.XPATH, "//input[@id='email']");
    public Locator facebookLoginPasswordTextField = new Locator(LocatorType.XPATH, "//input[@id='pass']");
    public Locator facebookLoginDoNotLogoutMeCheckbox = new Locator(LocatorType.XPATH, "//input[@id='persist_box']");
    public Locator facebookLoginButton = new Locator(LocatorType.XPATH, "//label[@id='loginbutton']");
    public Locator facebookCancelButton = new Locator(LocatorType.XPATH, "//input[@id='u_0_2']");
    //avatar locator after after login via facebook
    public Locator facebookAvatar = new Locator(LocatorType.XPATH, "//span[@class='userFbAvatar']");
}