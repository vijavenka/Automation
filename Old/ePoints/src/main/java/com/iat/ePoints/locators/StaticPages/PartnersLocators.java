package com.iat.ePoints.Locators.StaticPages;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 04.04.14
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class PartnersLocators {

    public Locator firstNamePartners = new Locator(LocatorType.ID, "form_name");
    public Locator surnamePartners = new Locator(LocatorType.ID, "form_surname");
    public Locator companyNamePartners = new Locator(LocatorType.ID, "form_company_name");
    public Locator emailPartners = new Locator(LocatorType.ID, "form_email");
    public Locator websitePartners = new Locator(LocatorType.ID, "form_website");
    public Locator surnameAlertPartners = new Locator(LocatorType.XPATH, "//span[contains(text(),'Surname is required')]");
    public Locator companyAlertPartners = new Locator(LocatorType.XPATH, "//span[contains(text(),'Company Name is required')] ");
    public Locator emailAlertPartners = new Locator(LocatorType.XPATH, "//span[contains(text(),'Email is required')] ");
    public Locator submitButtonPartners = new Locator(LocatorType.XPATH, "//button[@class='btn btn-inverse']");
}
