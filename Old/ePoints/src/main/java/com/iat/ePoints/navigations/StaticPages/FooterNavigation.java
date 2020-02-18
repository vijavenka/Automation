package com.iat.ePoints.Navigations.StaticPages;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 */

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.StaticPages.FooterLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.assertTrue;

public class FooterNavigation extends AbstractPage {

    private FooterLocators locators_footer = new FooterLocators();

    public FooterNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    //Social icons /////////////////////////////////////////////////////////////////////////////////////////////////////
    public void checkIsFacebookIconAvaliable(){
        assertTrue("Facebook icon not available in footer", executor.is_element_present(locators_footer.FacebookIcon));
    }


    public void checkIsTwitterLinkVisible() {
        assertTrue("Twitter icon is not visible", executor.is_element_present(locators_footer.TwitterIcon));

    }

    public void checkPPLinkAvailableInFooter() {
        assertTrue("Privacy Policy link not available in footer", executor.is_element_present(locators_footer.privacyPolicyFooter));
    }

    public void clickInPPLinkInFooter() throws InterruptedException {
        Thread.sleep(2000);
        executor.click(locators_footer.privacyPolicyFooter);
    }

    public void checkTCLinkAvailableInFooter() {
        assertTrue("T&C link not available in footer", executor.is_element_present(locators_footer.TCFooter));
    }

    public void clickInTCLinkInFooter() throws InterruptedException {
        Thread.sleep(2000);
        executor.click(locators_footer.TCFooter);
    }

}