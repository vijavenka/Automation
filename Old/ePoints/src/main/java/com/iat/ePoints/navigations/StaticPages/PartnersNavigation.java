package com.iat.ePoints.Navigations.StaticPages;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.StaticPages.PartnersLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 04.04.14
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class PartnersNavigation extends AbstractPage{

    private PartnersLocators locators_partners = new PartnersLocators();

    public PartnersNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // LEAD GEN SCREEN - add new submission form UI to epoints (RD-1645) - page content
    public void checkContentOfPartnersForm(){
        assertTrue("Name text field is not visible", executor.is_element_present(locators_partners.firstNamePartners));
        assertTrue("Surname text field is not visible", executor.is_element_present(locators_partners.surnamePartners));
        assertTrue("Company text field is not visible", executor.is_element_present(locators_partners.companyNamePartners));
        assertTrue("Email text field is not visible", executor.is_element_present(locators_partners.emailPartners));
        assertTrue("Website text field is not visible", executor.is_element_present(locators_partners.websitePartners));
        assertTrue("Submit button is not visible", executor.is_element_present(locators_partners.submitButtonPartners));
    }

    // LEAD GEN SCREEN - add new submission form UI to epoints (RD-1645) - alert behaviour
    public void clickSubmitButton(){
        executor.click(locators_partners.submitButtonPartners);
    }

    public void checkVisibilityOfAlerts(){
        assertTrue("Surname alert is not visible", executor.is_element_present(locators_partners.surnameAlertPartners));
        assertTrue("Company alert is not visible", executor.is_element_present(locators_partners.companyAlertPartners));
        assertTrue("Email alert is not visible", executor.is_element_present(locators_partners.emailAlertPartners));
    }
}
