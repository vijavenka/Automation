package com.iat.ePoints.Locators.Checkout;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 05.12.13
 * Time: 10:32
 * To change this template use File | Settings | File Templates.
 */
public class OrderSummaryLocators {

    public Locator orderSummaryTitle = new Locator(LocatorType.XPATH, "//h4[contains(text(),'Order summary')]");
    public Locator yourSelectionSection = new Locator(LocatorType.XPATH, "//div[@id='orderItemsPreviewRegion']");
    public Locator editYourSelectionSection = new Locator(LocatorType.XPATH, "//div[@id='orderItemsPreviewRegion']//span[text()='Edit']");
    public Locator basicProductTitle = new Locator(LocatorType.XPATH, "//span[@class='title']");
    public Locator deliveryDetailssection = new Locator(LocatorType.XPATH, "//div[@id='orderAddressRegion']");
    public Locator editDeliveryDetailsSection = new Locator(LocatorType.XPATH, "//div[@class='order-address']//span[contains(text(),'Edit')]");
    public Locator placeOrderButton = new Locator(LocatorType.XPATH, "//div[@class='btn btn-yellow order-go-to-step-4']");
    public Locator backButton = new Locator(LocatorType.XPATH, "//div[@class='btn btn-grey order-go-to-step-2']");
    public Locator summaryFirstNameLastName = new Locator(LocatorType.XPATH, "//div[@class='order-address']//div[@class='first-last-name']");
    public Locator summaryStreetHouse = new Locator(LocatorType.XPATH, "//div[@class='order-address']//div[@class='street house']");
    public Locator summaryTown = new Locator(LocatorType.XPATH, "//div[@class='order-address']//div[@class='city']");
    public Locator summaryCounty = new Locator(LocatorType.XPATH, "//div[@class='order-address']//div[@class='county']");
    public Locator summaryCountry = new Locator(LocatorType.XPATH, "//div[@class='order-address']//div[@class='country']");
    public Locator summaryPostcode = new Locator(LocatorType.XPATH, "//div[@class='order-address']//div[@class='postCode']");
}