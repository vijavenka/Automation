package com.iat.ePoints.Locators.StaticPages;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 19.11.13
 * Time: 16:27
 * To change this template use File | Settings | File Templates.
 */
public class SubmitARequestPageLocators {

    public Locator submitARequestTitle = new Locator(LocatorType.XPATH, "//h2[text()='Submit a request']");
    public Locator yourEmailAddressTextFied = new Locator(LocatorType.XPATH, "//input[@id='email_address']");
    public Locator subjectTextField = new Locator(LocatorType.XPATH, "//input[@id='ticket_subject']");
    public Locator descriptionTextField = new Locator(LocatorType.XPATH, "//textarea[@id='comment_value']");
    public Locator attachfileReference = new Locator(LocatorType.XPATH, "//span[@id='attach_link']");
    public Locator submitButton = new Locator(LocatorType.XPATH, "//input[@type='button']");
}