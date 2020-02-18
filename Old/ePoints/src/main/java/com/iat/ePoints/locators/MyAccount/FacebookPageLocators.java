package com.iat.ePoints.Locators.MyAccount;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 10.01.14
 * Time: 11:11
 * To change this template use File | Settings | File Templates.
 */
public class FacebookPageLocators {

    public Locator EmailTextField = new Locator(LocatorType.XPATH, "//input[@id='email']");
    public Locator PasswordTextField = new Locator(LocatorType.XPATH, "//input[@id='pass']");
    public Locator LogInButton = new Locator(LocatorType.XPATH, "//input[@value='Log In']");

    public Locator HomeButton = new Locator(LocatorType.XPATH, "//li[@id='navHome']//a[@class='navLink bigPadding']");
    public Locator HomeButtonForPLFB = new Locator(LocatorType.XPATH, "//span[@class='headerTinymanName']");
    public Locator SettingsButton = new Locator(LocatorType.XPATH, "//div[@id='userNavigationLabel']");
    public Locator SignOutButton = new Locator(LocatorType.XPATH, "//input[@value='Log Out']");

    public Locator MessageContent = new Locator(LocatorType.XPATH, "//div[@id='js_5']//p");
    public Locator MessageContentForPLFB = new Locator(LocatorType.XPATH, "//span[@class='userContent']");
    public Locator MessageLink = new Locator(LocatorType.XPATH, "//div[@class='_6m6']//a");
}
