package com.iat.adminportal.locators.network_manager;

import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

public class AdminPortalNetworkManagerEditFormLocators {

    public Locator page_header = new Locator(LocatorType.XPATH, "//section[@id='content']/div/h1");

    public Locator view_all_network_transactions_button = new Locator(LocatorType.XPATH, "//a[@id='network_transactions']");

    public Locator create_new_button = new Locator(LocatorType.XPATH, "//a[@id='network_add']");

    public Locator mer_man_button = new Locator(LocatorType.XPATH, "//a[@href='/merchants']");

    public Locator net_man_button = new Locator(LocatorType.XPATH, "//a[@href='/networks']");

    public Locator brand_man_button = new Locator(LocatorType.XPATH, "//a[@href='/brands']");

    public Locator stor_man_button = new Locator(LocatorType.XPATH, "//a[@href='/storeCategories']");

    public Locator filter_man_button = new Locator(LocatorType.XPATH, "//a[@href='/filters']");

    public Locator cont_man_button = new Locator(LocatorType.XPATH, "//a[@href='/contents']");

    public Locator items_man_button = new Locator(LocatorType.XPATH, "//a[@href='/items']");

    public Locator offer_man_button = new Locator(LocatorType.XPATH, "//a[@href='/offers']");

}
