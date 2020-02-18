package com.iat.adminportal.locators.voucher_manager;

import com.iat.adminportal.locators.IPageLocators;
import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

public class VoucherManagerLandingPageLocators implements IPageLocators {

    public Locator pageTitleH1 = new Locator(LocatorType.XPATH, "//h1");

    public Locator add_feed_configuration_btn = new Locator(LocatorType.XPATH, "//a[@id='voucherConfigCreate' and contains(@href, 'vouchers-manager/create')]");

    public Locator configTableProcessing = new Locator(LocatorType.XPATH, "//div[@id='voucherConfigTable_processing' and contains(@style,'visibility: hidden')]");

    public Locator rawVoucherStatisticsSectionContainer = new Locator(LocatorType.XPATH, "//div[@id='rawVoucherStats']");

    public Locator rawVoucherStatisticsSectionTitle = new Locator(LocatorType.XPATH, "//div[@id='rawVoucherStats']/h3[contains(text(),'Raw Voucher Statistics')]");

    public Locator rawVoucherStatisticsCreated = new Locator(LocatorType.XPATH, "//div[@id='rawVoucherStats']/div[@class='stats']/div[1]");

    public Locator rawVoucherStatisticsCreatedLink = new Locator(LocatorType.XPATH, "//div[@class='stats']/div/a[contains(@href,'created')]");

    public Locator rawVoucherStatisticsUpdated = new Locator(LocatorType.XPATH, "//div[@id='rawVoucherStats']/div[@class='stats']/div[2]");

    public Locator rawVoucherStatisticsUpdatedFields_li = new Locator(LocatorType.XPATH, "//ul[@id='updatedFields']/li");

    public Locator rawVoucherStatisticsDeleted = new Locator(LocatorType.XPATH, "//div[@id='rawVoucherStats']/div[@class='stats']/div[3]");

    public Locator editedVoucherStatisticsSectionContainer = new Locator(LocatorType.XPATH, "//div[@id='updatedVoucherStats']");

    public Locator editedVoucherStatisticsSectionTitle = new Locator(LocatorType.XPATH, "//div[@id='updatedVoucherStats']/h3[contains(text(),'Updated Voucher Statistics')]");

    public Locator editedVoucherStatisticsEdited = new Locator(LocatorType.XPATH, "//div[@id='updatedVoucherStats']/div[@class='stats']/div[1]");

    public Locator editedVoucherStatisticsPartiallyEdited = new Locator(LocatorType.XPATH, "//div[@id='updatedVoucherStats']/div[@class='stats']/div[2]");

    public Locator editedVoucherStatisticsEditedNetwork_li = new Locator(LocatorType.XPATH, "//ul[@id='networkStats']/li");

    public Locator VoucherConfigTable = new Locator(LocatorType.XPATH, "//table[@id='voucherConfigTable']");

    public Locator promoteButtonsCollection = new Locator(LocatorType.XPATH, "//tbody/tr[*]/td[8]/a");

    public Locator active_deactive_ButtonsCollection = new Locator(LocatorType.XPATH, "//span[@class='activeColumn']/a");

    //table locators
    public Locator createdBasicElement = new Locator(LocatorType.XPATH, "//td[3]//a");

}
