package com.iat.ePoints.Navigations.StaticPages;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Locators.HomePageLocators;
import com.iat.ePoints.Locators.StaticPages.FooterLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 20.01.14
 * Time: 12:57
 * To change this template use File | Settings | File Templates.
 */
public class HomeNavigation extends AbstractPage {

    private HomePageLocators locators_home = new HomePageLocators();
    private HeaderLocators locators_header = new HeaderLocators();
    private FooterLocators locators_footer = new FooterLocators();

    public HomeNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // Checking if home page has proper content ////////////////////////////////////////////////////////////////////////
    public void checkHomePageContent() {
        assertTrue("Join with facebook button is not visible", executor.is_element_present(locators_home.register_via_facebook_button));
        assertTrue("Email input box is not visible", executor.is_element_present(locators_home.emailAddressTextBox));
        assertTrue("Join for free button is not visible", executor.is_element_present(locators_home.joinForFreeButton));
        assertTrue("Online stores link is not visible", executor.is_element_present(locators_home.onlineStores));
        assertTrue("Section five header is not visible", executor.is_element_present(locators_home.sectionFiveHeader));
        assertTrue("Section four header is not visible", executor.is_element_present(locators_home.sectionFourHeader));
        assertTrue("Section three header is not visible", executor.is_element_present(locators_home.sectionThreeHeader));
        assertTrue("Section two is not visible", executor.is_element_present(locators_home.sectionTwoHeader));
        assertTrue("Two million rewards link is not visible", executor.is_element_present(locators_home.twoMillionRewards));
    }

    // Checking if home page links works correctly /////////////////////////////////////////////////////////////////////
    public void checkWorkingOfHomePageLinks() {
        executor.click(locators_home.twoMillionRewards);
        System.out.println(executor.return_driver().getCurrentUrl());
        assertTrue("Spend page url is incorrect", executor.return_driver().getCurrentUrl().contains("/spend"));
        executor.click(locators_header.home);
        executor.click(locators_home.onlineStores);
        System.out.println(executor.return_driver().getCurrentUrl());
        assertTrue("Get page title is incorrect", executor.return_driver().getCurrentUrl().contains("/get-epoints"));
        executor.click(locators_header.home);
    }

    // EPOINTS & WLS - make footer changes (RD-1920)
    public void checkVisibilityOfPartnersButton(){
        assertTrue("Partners button is invisible", executor.is_element_present(locators_footer.partnersFooter));
    }

    public void clickPartnerButton(){
        executor.click(locators_footer.partnersFooter);
    }

    public void checkIfPartnersPageWasopened(){
        System.out.println(executor.return_driver().getCurrentUrl());
        assertTrue("Partners page was not opened", executor.return_driver().getCurrentUrl().contains("/partners"));
    }
}
