package com.iat.ePoints.Locators.Get;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 12.02.14
 * Time: 14:57
 * To change this template use File | Settings | File Templates.
 */
public class GetVouchersPageLocators {
    public Locator voucherContainerVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']");
    public Locator voucherImageVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']//img");
    public Locator voucherRetailerVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']//div[@class='retailer']");
    public Locator voucherNameVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']//h4[@itemprop='name']");
    public Locator voucherDescriptionVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']//p[@class='voucher-description']");
    public Locator getVoucherButtonVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']//span[@class='gotoretailer']");
    public Locator voucherExpireVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']//span[@class='voucher-expire']");
    public Locator voucherCodeVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']//div[@class='voucher-code']");
    public Locator voucherShareTwitterPage= new Locator(LocatorType.XPATH, "//span[@class='icon-twitter']");
// voucher facebook share window (important, facebook used account is US)
    public Locator voucherLink = new Locator(LocatorType.XPATH, "//textarea[@id='status']");
    public Locator shareButton = new Locator(LocatorType.XPATH, "//button[@name='share']");
// voucher individual page chosen voucher main view
    public Locator chosenVoucherContainerVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='section top-section']");
    public Locator chosenVoucherImageVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='section top-section']//div[@class='voucher-image']");
    public Locator chosenVoucherRetailerVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='section top-section']//div[@class='voucher-info']//h2");
    public Locator chosenVoucherNameVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='section top-section']//div[@class='voucher-info']//h1");
    public Locator chosenVoucherDescriptionVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='section top-section']//div[@class='voucher-info']//p[@class='description']");
    public Locator getChosenVoucherButtonVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='section top-section']//a[@class='get-voucher-codes btn btn-yellow']");
    public Locator chosenVoucherExpireVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='section top-section']//span[@class='voucher-expire']");
    public Locator chosenVoucherCodeVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='section top-section']//div[@class='voucher-code']");

    public Locator moreVouchersSections = new Locator(LocatorType.XPATH, "//span[@class='more-voucher-title']");
}
