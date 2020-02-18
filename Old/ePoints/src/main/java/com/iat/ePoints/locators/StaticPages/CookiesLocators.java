package com.iat.ePoints.Locators.StaticPages;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 28.11.13
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 */
public class CookiesLocators {

    public Locator cookiesMainPageReference = new Locator(LocatorType.XPATH, "//div[@class='bottom-menu']//li[2]//a");

    public Locator cookiesPageMainTitle = new Locator(LocatorType.XPATH, "//h1[contains(text(),'Cookie Policy')]");

    public Locator whatIsCookieSection = new Locator(LocatorType.XPATH, "//strong[contains(text(),'What is a cookie')]");
    public Locator twoTypesOfCookieSection = new Locator(LocatorType.XPATH, "//strong[contains(text(),'The 2 different types of cookies we use:')]");
    public Locator managingCookiesSection = new Locator(LocatorType.XPATH, "//strong[contains(text(),'Managing cookies')]");
    public Locator yourInformationSection = new Locator(LocatorType.XPATH, "//strong[contains(text(),'Your information')]");
    public Locator appliesToSection = new Locator(LocatorType.XPATH, "//strong[contains(text(),'Applies to')]");

}
