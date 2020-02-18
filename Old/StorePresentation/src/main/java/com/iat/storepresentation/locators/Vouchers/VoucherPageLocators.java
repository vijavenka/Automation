package com.iat.storepresentation.Locators.Vouchers;

import com.iat.storepresentation.Locators.Locator;
import com.iat.storepresentation.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.03.14
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class VoucherPageLocators {
    public Locator voucherContainerVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']");
    public Locator voucherImageVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='retailerLogo']//img");
    public Locator voucherRetailerVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']//div[@class='retailer']");
    public Locator voucherNameVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']//h4[@itemprop='name']");
    public Locator voucherDescriptionVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']//p[@class='voucherDescription']");
    public Locator getVoucherButtonVoucherPage= new Locator(LocatorType.XPATH, "//a[@class='get-voucher-codes']//span[@class='gotoretailer']");
    public Locator voucherExpireVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']//span[@class='voucherExpire']");
    public Locator voucherCodeVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherbox']//div[@class='voucherCode branded']");
    public Locator voucherShareTwitterPage= new Locator(LocatorType.XPATH, "//a[@class='action-share-twitter']");
    // voucher facebook share window (important, facebook used account is US)
    public Locator voucherLink = new Locator(LocatorType.XPATH, "//textarea[@id='status']");
    // voucher individual page chosen voucher main view
    public Locator chosenVoucherContainerVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='module-container']");
    public Locator chosenVoucherRetailerVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='module-container']//h5");
    public Locator chosenVoucherNameVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='module-container']//h3");
    public Locator chosenVoucherDescriptionVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@class='info']//p");
    public Locator getChosenVoucherButtonVoucherPage= new Locator(LocatorType.XPATH, "//a[@class='get-voucher-codes']");
    public Locator chosenVoucherExpireVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='section top-section']//span[@class='voucher-expire']");
    public Locator chosenVoucherCodeVoucherPage= new Locator(LocatorType.XPATH, "//div[@class='voucherCode']");
}
