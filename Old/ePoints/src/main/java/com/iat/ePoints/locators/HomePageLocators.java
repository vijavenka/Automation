package com.iat.ePoints.Locators;

public class HomePageLocators implements IPageLocators {
//First part
    public Locator emailAddressTextBox = new Locator(LocatorType.XPATH, "//a[@class='btn btn-social facebook facebook-login']");
    public Locator joinForFreeButton = new Locator(LocatorType.XPATH, "//button[@class='btn btn-green join-button']");
    public Locator register_via_facebook_button = new Locator(LocatorType.XPATH, "//a[@class='btn btn-social facebook facebook-login']");
//Second part
    public Locator sectionTwoHeader = new Locator(LocatorType.XPATH, "//div[contains(text(),'getting epoints is simple')]");
    public Locator retailerMainPageBasic = new Locator(LocatorType.XPATH, " //a[@class='merchant-logo-wrapper retailerLink']");  //exmple data-retailername="John Lewis"
//Third part
    public Locator sectionThreeHeader = new Locator(LocatorType.XPATH, "//div[contains(text(),'spending epoints is fun')]");
    public Locator onlineStores = new Locator(LocatorType.XPATH, "//u[contains(text(),'online stores')]");
//Fourth part
    public Locator sectionFourHeader = new Locator(LocatorType.XPATH, "//div[contains(text(),'talking epoints is for you')]");
    public Locator twoMillionRewards = new Locator(LocatorType.XPATH, "//u[contains(text(),'two million rewards')]");
//First part
    public Locator sectionFiveHeader = new Locator(LocatorType.XPATH, "//div[contains(text(),'now get started ...')]");

}