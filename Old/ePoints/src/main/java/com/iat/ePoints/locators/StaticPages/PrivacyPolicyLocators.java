package com.iat.ePoints.Locators.StaticPages;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */
public class PrivacyPolicyLocators implements IPageLocators {

    public Locator privacyPolicyCheck = new Locator(LocatorType.XPATH, "//h1[text()='Privacy Policy']");
    public Locator dtaprotectionInformationHeader = new Locator(LocatorType.XPATH, "//strong[text()='Data protection information']");
    public Locator personalDataHeader = new Locator(LocatorType.XPATH, "//strong[text()='Personal Data']");
    public Locator collectionsOfPersonalDataHeader = new Locator(LocatorType.XPATH, "//strong[text()='Collection of personal data']");
    public Locator marketingNewslettersHeader = new Locator(LocatorType.XPATH, "//strong[text()='Marketing Newsletters']");
    public Locator whatAboutThirdParyAdvertisersHeaders = new Locator(LocatorType.XPATH, "//strong[text()='What About Third Party Advertisers?']");
}