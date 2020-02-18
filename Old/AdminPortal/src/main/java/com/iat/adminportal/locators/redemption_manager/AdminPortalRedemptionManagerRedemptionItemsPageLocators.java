package com.iat.adminportal.locators.redemption_manager;

import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 19.03.14
 * Time: 13:24
 * To change this template use File | Settings | File Templates.
 */
public class AdminPortalRedemptionManagerRedemptionItemsPageLocators {
    // Redemption Items page
    //searchers
    public Locator titleSearchRedemptionItemsPage = new Locator(LocatorType.ID, "searchTitle");
    public Locator categorySearchRedemptionItemsPage = new Locator(LocatorType.ID, "searchCategory");
    public Locator descriptionSearchRedemptionItemsPage = new Locator(LocatorType.ID, "searchDesc");
    public Locator typeSearchRedemptionItemsPage = new Locator(LocatorType.ID, "searchType");
    public Locator merchantSearchRedemptionItemsPage = new Locator(LocatorType.ID, "searchMerchant");
    public Locator epointsSearchRedemptionItemsPage = new Locator(LocatorType.ID, "searchEpoints");
    public Locator clearButtonRedemptionItemsPage = new Locator(LocatorType.ID, "clearbtn");
    public Locator searchButtonRedemptionItemsPage = new Locator(LocatorType.ID, "searchbtn");

    public Locator titleColumnBasicRedemptionItemsPage = new Locator(LocatorType.XPATH, "//table[@id='redeemsTable']//td[@class='title sorting_1']//span");
    public Locator pointsColumnBasicRedemptionItemsPage = new Locator(LocatorType.XPATH, "//table[@id='redeemsTable']//td[2]");
    public Locator priceColumnBasicRedemptionItemsPage = new Locator(LocatorType.XPATH, "//table[@id='redeemsTable']//td[@class='price']");
    public Locator merchantColumnBasicRedemptionItemsPage = new Locator(LocatorType.XPATH, "//table[@id='redeemsTable']//td[@class='merchant']//a ");
    public Locator categoryColumnBasicRedemptionItemsPage = new Locator(LocatorType.XPATH, "//table[@id='redeemsTable']//td[5]  ");
    public Locator brandColumnBasicRedemptionItemsPage = new Locator(LocatorType.XPATH, "//table[@id='redeemsTable']//td[@class='brand']");
    public Locator typeColumnBasicRedemptionItemsPage = new Locator(LocatorType.XPATH, "//table[@id='redeemsTable']//td[7]");
    public Locator addButtonColumnBasicRedemptionItemsPage = new Locator(LocatorType.XPATH, "//a[@class='btn small redeemEdit']");
}
