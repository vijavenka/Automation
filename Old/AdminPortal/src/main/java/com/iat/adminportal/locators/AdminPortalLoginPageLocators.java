package com.iat.adminportal.locators;

public class AdminPortalLoginPageLocators implements IPageLocators {

    public Locator login_box = new Locator(LocatorType.XPATH, "//section[contains(@id, 'content')]");

    public Locator user_name = new Locator(LocatorType.XPATH, "//input[contains(@id, 'username')]");

    public Locator password = new Locator(LocatorType.XPATH, "//input[contains(@id, 'password')]");

    public Locator remember_me = new Locator(LocatorType.XPATH, "//input[contains(@id, 'remember')]");

    public Locator login_button = new Locator(LocatorType.XPATH, "//div/button[contains(@class, 'fr submit')]");

    public Locator lost_password = new Locator(LocatorType.XPATH, "");

    public Locator welcome_sign = new Locator(LocatorType.XPATH, "//div[contains(@id, 'welcome')]/p");

    public Locator login_alert_div = new Locator(LocatorType.XPATH, "//div[contains(@class, 'alert')]");

}
