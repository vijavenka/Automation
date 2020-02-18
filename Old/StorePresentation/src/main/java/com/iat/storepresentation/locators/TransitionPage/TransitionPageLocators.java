package com.iat.storepresentation.Locators.TransitionPage;

import com.iat.storepresentation.Locators.Locator;
import com.iat.storepresentation.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.01.14
 * Time: 13:14
 * To change this template use File | Settings | File Templates.
 */
public class TransitionPageLocators {

    public Locator signInWithFacebook = new Locator(LocatorType.XPATH, "//span[contains(text(),'Sign in with facebook' )]");
    public Locator emailAdress = new Locator(LocatorType.XPATH, "//input[@type='email']");
    public Locator password = new Locator(LocatorType.XPATH, "//input[@type='password']");
    public Locator forgotPasswordLink = new Locator(LocatorType.XPATH, "//a[@class='forgotPass link']");
    public Locator signInButton = new Locator(LocatorType.XPATH, "//button[@id='signInToEpoints']");
    public Locator startHereLink = new Locator(LocatorType.XPATH, "//a[@class='link']");
    public Locator continueAnywayButton = new Locator(LocatorType.XPATH, "//a[contains(text(),'continue anyway')]");

}
