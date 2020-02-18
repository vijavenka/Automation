package com.iat.adminportal.locators;

public class AdminPortalCustomerPageLocators implements IPageLocators {

    public Locator customer_button_menu = new Locator(LocatorType.XPATH, "");

    public Locator customer_header = new Locator(LocatorType.XPATH, "");

    public Locator customer_create = new Locator(LocatorType.XPATH, "");
    //TODO: Create as separate Generic Functionality - Search
    public Locator customer_search_list = new Locator(LocatorType.XPATH, "");

    public Locator customer_search_box = new Locator(LocatorType.XPATH, "");

    public Locator customer_search_button = new Locator(LocatorType.XPATH, "");
    //TODO: Add all locators for customer manager
}
