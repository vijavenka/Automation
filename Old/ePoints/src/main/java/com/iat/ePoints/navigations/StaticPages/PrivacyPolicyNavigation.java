package com.iat.ePoints.Navigations.StaticPages;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 19:38
 * To change this template use File | Settings | File Templates.
 */

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.StaticPages.PrivacyPolicyLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.assertTrue;

public class PrivacyPolicyNavigation extends AbstractPage {

    private PrivacyPolicyLocators locators_PP = new PrivacyPolicyLocators();


    public PrivacyPolicyNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public void checkIsPPPageOpened(){
        assertTrue("Privacy Policy page not opened properly", executor.is_element_present(locators_PP.privacyPolicyCheck));
    }

    public void checkVisibilityOfBasicElementsInPPPage(){
        assertTrue("collectionsOfPersonalDataHeader is no displayed",executor.is_element_present(locators_PP.collectionsOfPersonalDataHeader));
        assertTrue("daprotectionInformationHeader is no displayed",executor.is_element_present(locators_PP.dtaprotectionInformationHeader));
        assertTrue("marketingNewslettersHeader is no displayed",executor.is_element_present(locators_PP.marketingNewslettersHeader));
        assertTrue("personalDataHeader is no displayed",executor.is_element_present(locators_PP.personalDataHeader));
        assertTrue("privacyPolicyCheck is no displayed",executor.is_element_present(locators_PP.privacyPolicyCheck));
        assertTrue("whatAboutThirdParyAdvertisersHeaders is no displayed",executor.is_element_present(locators_PP.whatAboutThirdParyAdvertisersHeaders));
    }

}
