package com.iat.adminportal.locators.network_manager;

import com.iat.adminportal.locators.IPageLocators;
import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

public class AdminPortalNetworkManagerHomeLocators implements IPageLocators {

    //Main locators for Network Manager
    public Locator page_header = new Locator(LocatorType.XPATH, "//section[@id='content']/div/h1");
    public Locator view_all_network_transactions_button = new Locator(LocatorType.XPATH, "//a[@id='network_transactions']");
    public Locator create_new_button = new Locator(LocatorType.XPATH, "//a[@id='network_add']");

    //Table headers locators for Network Manager
    public Locator table_id_column = new Locator(LocatorType.XPATH, "//table[@id='networksTable']/thead/tr/th[@class='id sorting_asc' or @class='id sorting_desc']");
    public Locator table_name_column = new Locator(LocatorType.XPATH, "//table[@id='networksTable']/thead/tr/th[@class='name sorting' or @class='name sorting_asc' or @class='name sorting_desc']");
    public Locator table_active_column = new Locator(LocatorType.XPATH, "//table[@id='networksTable']/thead/tr/th[@class='sorting' or @class='sorting_asc' or @class='sorting_desc']");
    //Table content locators
    public Locator tableContentID = new Locator(LocatorType.XPATH, "//table[@id='networksTable']//td[1]");
    public Locator tableContentName = new Locator(LocatorType.XPATH, "//table[@id='networksTable']//td[2]");
    public Locator tableContentActive = new Locator(LocatorType.XPATH, "//table[@id='networksTable']//td//span[@class='active'] ");
    public Locator tableContentActiveButton = new Locator(LocatorType.XPATH, "//table[@id='networksTable']//td//a[@class='btn small active']");
    public Locator tableContentStatsButton = new Locator(LocatorType.XPATH, "//table[@id='networksTable']//td//a[@class='btn small networkstats'] ");
    public Locator tableContentEditButton = new Locator(LocatorType.XPATH, "//table[@id='networksTable']//td//a[@class='btn small networkedit'] ");

    public Locator stor_man_button = new Locator(LocatorType.XPATH, "//a[@href='/storeCategories']");

    public Locator filter_man_button = new Locator(LocatorType.XPATH, "//a[@href='/filters']");

    public Locator cont_man_button = new Locator(LocatorType.XPATH, "//a[@href='/contents']");

    public Locator items_man_button = new Locator(LocatorType.XPATH, "//a[@href='/items']");

    public Locator offer_man_button = new Locator(LocatorType.XPATH, "//a[@href='/offers']");

}
