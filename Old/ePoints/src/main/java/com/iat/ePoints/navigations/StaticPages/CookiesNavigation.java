package com.iat.ePoints.Navigations.StaticPages;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.StaticPages.CookiesLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 28.11.13
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 */
public class CookiesNavigation extends AbstractPage {

    CookiesLocators locators_cookies = new CookiesLocators();

    public CookiesNavigation (IExecutor executor) {
        super(executor, "");
    }

    public void clickOnCookiesFooterReference() {
        executor.click(locators_cookies.cookiesMainPageReference);
    }

    public void checkContentOfCookiesPage() {
        assertTrue("Applies to section is invisible",executor.is_element_present(locators_cookies.appliesToSection));
        assertTrue("Cookies page main title is invisible",executor.is_element_present(locators_cookies.cookiesPageMainTitle));
        assertTrue("Managing cookies section is invisible",executor.is_element_present(locators_cookies.managingCookiesSection));
        assertTrue("Two types of cookie is invisible",executor.is_element_present(locators_cookies.twoTypesOfCookieSection));
        assertTrue("What is cookie is invisible",executor.is_element_present(locators_cookies.whatIsCookieSection));
        assertTrue("Your information is invisible",executor.is_element_present(locators_cookies.yourInformationSection));
    }
}
