package com.iat.ePoints.Locators;

public class HeaderLocators implements IPageLocators {

    public Locator home = new Locator(LocatorType.XPATH, "//li[@data-menu='home']//a[@href='/']");
    public Locator getPoints = new Locator(LocatorType.XPATH, "//a[@href='/get-epoints']");
    public Locator spendPoints = new Locator(LocatorType.XPATH, "//li[@data-menu='getRewards']//a[@href='/spend']");
    public Locator myAccount = new Locator(LocatorType.XPATH, "//a[@href='/my-account']");
    public Locator SignIn = new Locator(LocatorType.XPATH, "//a[contains(text(),'Sign in')]");
    public Locator JoinNow = new Locator(LocatorType.XPATH, "//a[contains(text(),'Join Now')]");
    public Locator SignOut = new Locator(LocatorType.XPATH, "//a[@class='signOut']");
    public Locator pointsCounter = new Locator(LocatorType.XPATH, "//div[@class='confirmed']//div[@class='value']");
    public Locator aboutEpoints = new Locator(LocatorType.XPATH, "//a[contains(text(),'About epoints')]");
    public Locator FAQ = new Locator(LocatorType.XPATH, "//a[contains(text(),'FAQ')]");
    public Locator Blog = new Locator(LocatorType.XPATH, "//a[contains(text(),'Blog')]");
    public Locator openBasket = new Locator(LocatorType.XPATH, "//i[@class='icon-gift']");
    public Locator poinsMainPageCounter = new Locator(LocatorType.XPATH, "//div[@id='headerBalanceRegion']//div[@class='value']");
}