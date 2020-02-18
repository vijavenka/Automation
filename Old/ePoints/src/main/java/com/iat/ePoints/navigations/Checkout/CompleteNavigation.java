package com.iat.ePoints.Navigations.Checkout;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Checkout.CompleteLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 05.12.13
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */
public class CompleteNavigation extends AbstractPage {

    protected CompleteLocators locators_complete = new CompleteLocators();

    public CompleteNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    //Scenario: User opens complete page and checks working of all links and buttons////////////////////////////////////
    public void checkIfComplitePageWasOpenedProperly() {
        assertTrue("Page title is invisible", executor.is_element_present(locators_complete.compliteTitle));
    }

    public void clickFAQLink() {
        executor.click(locators_complete.faqLink);
    }

    public void checkFAQPageTitle() {
        assertEquals("Faq page title is incorrect", executor.return_driver().getTitle(), "epoints support");
    }

    public void backToComplite() {
        executor.return_driver().navigate().back();
    }

    public void clickMyAccountButton() {
        executor.click(locators_complete.myAccountButton);
    }

    public void checkMyAccountPageTitle() {
        assertEquals("My account page title is incorrect", executor.return_driver().getTitle(), "My epoints | epoints");
    }

    public void clickGetEpointsLink() {
        executor.click(locators_complete.getEpointsButton);
    }

    public void checkGetEpointsPageTitle() {
        assertEquals("Get epoints [age title is incorrect", executor.return_driver().getTitle(), "Get epoints from 1000s of online retailers and partners | epoints");
    }

}