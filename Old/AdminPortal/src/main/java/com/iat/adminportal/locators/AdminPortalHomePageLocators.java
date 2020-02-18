package com.iat.adminportal.locators;

public class AdminPortalHomePageLocators implements IPageLocators {

    public Locator page_header = new Locator(LocatorType.XPATH, "//section[@id='content']//h1");

    public Locator logout_button = new Locator(LocatorType.XPATH, "//a[text()='Logout']");

    public Locator feed_man_button = new Locator(LocatorType.XPATH, "//a[@href='/feeds']");

    public Locator mer_man_button = new Locator(LocatorType.XPATH, "//a[@href='/merchants']");

    public Locator net_man_button = new Locator(LocatorType.XPATH, "//a[@href='/networks']");

    public Locator brand_man_button = new Locator(LocatorType.XPATH, "//a[@href='/brands']");

    public Locator stor_man_button = new Locator(LocatorType.XPATH, "//a[@href='/storeCategories']");

    public Locator filter_man_button = new Locator(LocatorType.XPATH, "//a[@href='/filters']");

    public Locator voucher_man_button = new Locator(LocatorType.XPATH, "//li[@class='i_price_tag']//span[contains(.,'Voucher Manager')]");

    public Locator redemption_man_button = new Locator(LocatorType.XPATH, "//li[@class='i_money']//span[contains(.,'Redemption Manager')]");

    public Locator email_man_button = new Locator(LocatorType.XPATH, "//li[@class='i_mail']//span[contains(text(),'Email Manager')]");

    public Locator member_man_button = new Locator(LocatorType.XPATH, "//li[@class='i_running_man']//span[contains(text(),'Member Manager')]");

    public Locator homePageWelcomeMsg = new Locator(LocatorType.XPATH, "//div[@class='menu']/p[1]");

    public Locator left_navi_panel = new Locator(LocatorType.ID, "nav");

}