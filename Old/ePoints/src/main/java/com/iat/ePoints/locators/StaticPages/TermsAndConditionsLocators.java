package com.iat.ePoints.Locators.StaticPages;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 19:27
 * To change this template use File | Settings | File Templates.
 */
public class TermsAndConditionsLocators implements IPageLocators {

    public Locator TCCheck = new Locator(LocatorType.XPATH, "//h1[text()='Terms & Conditions']");
    public Locator yourAgreement = new Locator(LocatorType.XPATH, "//strong[text()='Your agreement']");
    public Locator definitions = new Locator(LocatorType.XPATH, "//b[text()='Definitions']");
    public Locator registrationAndProgramme = new Locator(LocatorType.XPATH, "//b[text()='Registration to the Programme']");
    public Locator earningAndRedeemingPoints= new Locator(LocatorType.XPATH, "//b[text()='Earning and Redeeming Points']");
    public Locator weOurPartners = new Locator(LocatorType.XPATH, "//b[text()='We, our Partners and Retailers all reserve the right to amend the number of epoints rewarded for interactions included in the Programme.']");
    public Locator theProgramme = new Locator(LocatorType.XPATH, "//b[text()='The Programme']");
    public Locator prizeDraws = new Locator(LocatorType.XPATH, "//b[text()='Prize draws']");
    public Locator useOfTheWebsite = new Locator(LocatorType.XPATH, "//b[text()='Use of the Website']");
    public Locator governingLaw = new Locator(LocatorType.XPATH, "//b[text()='Governing law']");
}