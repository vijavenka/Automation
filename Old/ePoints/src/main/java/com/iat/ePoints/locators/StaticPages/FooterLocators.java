package com.iat.ePoints.Locators.StaticPages;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 19:22
 * To change this template use File | Settings | File Templates.
 */
public class FooterLocators implements IPageLocators {
    public Locator partnersFooter = new Locator(LocatorType.XPATH, "//div[@class='bottom-menu']//li//a");
    public Locator privacyPolicyFooter = new Locator(LocatorType.XPATH, "//div[@class='bottom-menu']//li[4]//a");
    public Locator TCFooter = new Locator(LocatorType.XPATH, "//div[@class='bottom-menu']//li[5]//a");
//Social icons
    public Locator FacebookIcon = new Locator(LocatorType.XPATH, "//div[@id='facebook']");
    public Locator TwitterIcon = new Locator(LocatorType.XPATH, "//div[@id='twitter']");
}