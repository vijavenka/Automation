package com.iat.adminportal.locators.voucher_manager;

import com.iat.adminportal.locators.IPageLocators;
import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

public class VoucherManagerSearchScreenLocators implements IPageLocators {

    public Locator pageTitleH1 = new Locator(LocatorType.XPATH, "//h1");

    public Locator searchTableProcessing = new Locator(LocatorType.XPATH, "//div[@id='vouchersListTable_processing' and contains(@style,'visibility: hidden')]");

    public Locator networkDropDown = new Locator(LocatorType.XPATH, "//select[@id='searchNetwork' and @name='network']");

    public Locator statusDropDown = new Locator(LocatorType.XPATH, "//select[@id='searchStatus' and @name='searchStatus']");

    public Locator searchTextBox = new Locator(LocatorType.XPATH, "//input[@id='searchkeyword' and @placeholder='Search...']");

    public Locator searchButton = new Locator(LocatorType.XPATH, "//button[@id='searchbtn' and text()='Search']");

    public Locator voucherTableWrapper = new Locator(LocatorType.XPATH, "//button[@id='searchbtn' and text()='Search']");

    //table elements
    public Locator editBasicElement = new Locator(LocatorType.XPATH, "//td[9]//a");
    public Locator merchantBasicElement = new Locator(LocatorType.XPATH, "//td[2]");

    //edit screen locators
    public Locator merchantNameDDLExpand = new Locator(LocatorType.XPATH, "//a[@class='select2-choice']");
    public Locator merchantNameDDLBasicOption = new Locator(LocatorType.XPATH, "//div[@class='select2-drop select2-with-searchbox select2-drop-active']//ul[@class='select2-results']//div");
    public Locator voucherEditModalCancelButton = new Locator(LocatorType.XPATH, "//button[@class='cancel']");
    public Locator voucherEditModalTitleInputField = new Locator(LocatorType.ID, "title");
    public Locator voucherEditModalOffInputField = new Locator(LocatorType.ID, "valueOff");
    public Locator voucherEditModalOffInputDDL = new Locator(LocatorType.ID, "typeOff");
    public Locator voucherEditModalAlertBasic = new Locator(LocatorType.XPATH, "//div[@class='tipsy-inner']");

    public Locator voucherEditModalSaveButton = new Locator(LocatorType.XPATH, "//button[@class='submit']");
}
