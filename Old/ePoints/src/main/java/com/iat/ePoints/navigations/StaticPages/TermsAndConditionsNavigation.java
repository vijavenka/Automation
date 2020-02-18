package com.iat.ePoints.Navigations.StaticPages;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 */
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.StaticPages.TermsAndConditionsLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.assertTrue;

public class TermsAndConditionsNavigation extends AbstractPage {

    private TermsAndConditionsLocators locators_TC = new TermsAndConditionsLocators();

    public TermsAndConditionsNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public void checkIsTCPageOpened(){
        assertTrue("T&C page not opened properly", executor.is_element_present(locators_TC.TCCheck));
    }

    public void checkVisibilityOfMainTitles(){
        assertTrue("Definition title not display properly",executor.is_element_present(locators_TC.definitions));
        assertTrue("EarningAndRedeemingPoints title not display properly",executor.is_element_present(locators_TC.earningAndRedeemingPoints));
        assertTrue("GoverningLaw title not display properly",executor.is_element_present(locators_TC.governingLaw));
        assertTrue("PrizeDraws title not display properly",executor.is_element_present(locators_TC.prizeDraws));
        assertTrue("RegistrationAndProgramme title not display properly",executor.is_element_present(locators_TC.registrationAndProgramme));
        assertTrue("TheProgramme title not display properly",executor.is_element_present(locators_TC.theProgramme));
        assertTrue("UseOfTheWebsite title not display properly",executor.is_element_present(locators_TC.useOfTheWebsite));
        assertTrue("WeOurPartners title not display properly",executor.is_element_present(locators_TC.weOurPartners));
        assertTrue("YourAgreement title not display properly",executor.is_element_present(locators_TC.yourAgreement));
    }
}
