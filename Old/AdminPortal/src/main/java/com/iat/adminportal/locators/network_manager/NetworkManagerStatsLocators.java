package com.iat.adminportal.locators.network_manager;

import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 10.04.14
 * Time: 12:08
 * To change this template use File | Settings | File Templates.
 */
public class NetworkManagerStatsLocators {
    public Locator userID = new Locator(LocatorType.ID, "searchUserId");
    public Locator searchButton = new Locator(LocatorType.ID, "searchbtn");
    public Locator toggleColumns = new Locator(LocatorType.XPATH, "//a[@class='btn dropdown-toggle']");
    public Locator toggleColumnP1 = new Locator(LocatorType.XPATH, "//ul[@class='dropdown-menu']//li[contains(text(),'P1')]");
    public Locator toggleColumnP2 = new Locator(LocatorType.XPATH, "//ul[@class='dropdown-menu']//li[contains(text(),'P2')]");
    public Locator toggleColumnP3 = new Locator(LocatorType.XPATH, "//ul[@class='dropdown-menu']//li[contains(text(),'P3')]");
    public Locator toggleColumnP4 = new Locator(LocatorType.XPATH, "//ul[@class='dropdown-menu']//li[contains(text(),'P4')]");
    public Locator p1Sorting = new Locator(LocatorType.XPATH, "//th[@class='p1Parameter sorting']");
    public Locator p2Sorting = new Locator(LocatorType.XPATH, "//th[@class='p2Parameter sorting']");
    public Locator p3Sorting = new Locator(LocatorType.XPATH, "//th[@class='p3Parameter sorting']");
    public Locator p4Sorting = new Locator(LocatorType.XPATH, "//th[@class='p4Parameter sorting']");
    public Locator p1TableContent = new Locator(LocatorType.XPATH, "//table[@id='reportingListTable']//td[@class='p1Parameter']");
    public Locator p2TableContent = new Locator(LocatorType.XPATH, "//table[@id='reportingListTable']//td[@class='p2Parameter']");
    public Locator p3TableContent = new Locator(LocatorType.XPATH, "//table[@id='reportingListTable']//td[@class='p3Parameter']");
    public Locator p4TableContent = new Locator(LocatorType.XPATH, "//table[@id='reportingListTable']//td[@class='p4Parameter']");

}
