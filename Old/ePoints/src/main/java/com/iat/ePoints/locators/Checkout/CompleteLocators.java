package com.iat.ePoints.Locators.Checkout;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 05.12.13
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class CompleteLocators {

    public Locator compliteTitle = new Locator(LocatorType.XPATH, "//h4[contains(text(),'Order complete')]");
    public Locator faqLink = new Locator(LocatorType.XPATH, "//a[@class='link']");
    public Locator myAccountButton = new Locator(LocatorType.XPATH, "//a[contains(text(),'My account')]");
    public Locator getEpointsButton = new Locator(LocatorType.XPATH, "//a[@class='btn btn-yellow']");
}
