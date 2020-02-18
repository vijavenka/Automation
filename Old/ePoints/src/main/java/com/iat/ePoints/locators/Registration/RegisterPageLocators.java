package com.iat.ePoints.Locators.Registration;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

public class RegisterPageLocators implements IPageLocators {

    public Locator registerOption = new Locator(LocatorType.XPATH, "//a[@href='/join']");
    public Locator email = new Locator(LocatorType.ID, "email");
    public Locator tellMoreOption = new Locator(LocatorType.ID, "tellMeMore");
    public Locator whyRegisterPopUp = new Locator(LocatorType.XPATH, "//div[@class='module-container']");
    public Locator closePopUpOption = new Locator(LocatorType.XPATH, "//a[@class='close-modal-link link']");
    public Locator closePopUpButton = new Locator(LocatorType.XPATH, "//button[@class ='close-modal-button']");
    public Locator signUp_button = new Locator(LocatorType.ID, "startRegBtn");
    public Locator emailMessage = new Locator(LocatorType.XPATH, "//span[@class='error']");
    public Locator emailLenghtDuplicateMessage = new Locator(LocatorType.XPATH, "//div[@class='errorMsg']");
    public Locator emailInboxInformation = new Locator(LocatorType.XPATH, "//strong[@class='status-3']");
    public Locator hiUserNameWelcome = new Locator(LocatorType.ID, "authBox");
    public Locator yourEpoints = new Locator(LocatorType.XPATH, "//div[@class='value']");
    public Locator checkYourEmailMessage = new Locator(LocatorType.XPATH, "//p[@class='checkInbox']");
    public Locator allDoneMessage = new Locator(LocatorType.XPATH, "//h1[contains(text(),'All done')]");
    //facebook Locators
    //the same as in LoginPageLocators and Those should be used in tests
}