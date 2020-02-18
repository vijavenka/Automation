package com.iat.ePoints.Locators.Checkout;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

public class DeliveryDetailsLocators implements IPageLocators {

//Basic page
    public Locator selectedRewardsTitle = new Locator(LocatorType.XPATH, "//h4[contains(text(),'Delivery details')]");
    public Locator pointsForm = new Locator(LocatorType.XPATH, "//div[@id='orderItemsPreviewRegion']");
    public Locator changeSelectionEdit = new Locator(LocatorType.XPATH, "//span[@class='edit-items btn-link to-right']");
    public Locator deliveryInfo = new Locator(LocatorType.XPATH, "//div[@class='delivery-information well' ]");
    public Locator next_btn = new Locator(LocatorType.XPATH, "//div[contains(text(),'Next')]");
    public Locator back_btn = new Locator(LocatorType.XPATH, "//div[contains(text(),'Back')]");
    public Locator adANewAddress = new Locator(LocatorType.XPATH, "//div[@class='add-delivery-address btn btn-small btn-grey']");
//Delivery details main address
    public Locator addressesForm = new Locator(LocatorType.XPATH, "//div[@class='clearfix well ']");
    public Locator firstNameLastName = new Locator(LocatorType.XPATH, "//div[@class='order-address-item selected']//div[@class='first-last-name']");
    public Locator streetHouse = new Locator(LocatorType.XPATH, "//div[@class='order-address-item selected']//div[@class='street house']");
    public Locator town = new Locator(LocatorType.XPATH, "//div[@class='order-address-item selected']//div[@class='city']");
    public Locator county = new Locator(LocatorType.XPATH, "//div[@class='order-address-item selected']//div[@class='county']");
    public Locator country = new Locator(LocatorType.XPATH, "//div[@class='order-address-item selected']//div[@class='country']");
    public Locator postcode = new Locator(LocatorType.XPATH, "//div[@class='order-address-item selected']//div[@class='postCode']");
//Creation of new address
    public Locator firstNameAddNew = new Locator(LocatorType.XPATH, "//div[@id='newAddressForm']//input[@name='firstName']");
    public Locator lastNameAddNew = new Locator(LocatorType.XPATH, "//div[@id='newAddressForm']//input[@name='lastName']");
    public Locator streetNameAddNew = new Locator(LocatorType.XPATH, "//div[@id='newAddressForm']//input[@name='street']");
    public Locator houseNoAddNew = new Locator(LocatorType.XPATH, "//div[@id='newAddressForm']//input[@name='house']");
    public Locator townAddNew = new Locator(LocatorType.XPATH, "//div[@id='newAddressForm']//input[@name='city']");
    public Locator countyAddNew = new Locator(LocatorType.XPATH, "//div[@id='newAddressForm']//input[@name='county']");
    public Locator countryAddNew = new Locator(LocatorType.XPATH, "//div[@id='newAddressForm']//input[@name='country']");
    public Locator postcodeAddNew = new Locator(LocatorType.XPATH, "//div[@id='newAddressForm']//input[@name='postCode']");
    public Locator enterAddressManualy = new Locator(LocatorType.XPATH, "//a[@class='enter-manually']");
    public Locator useAsContact = new Locator(LocatorType.XPATH, "//label[@for='useAsContact']");
//Login window
    public Locator emailField = new Locator(LocatorType.XPATH, "//input[@type='email']");
    public Locator passwordField = new Locator(LocatorType.XPATH, "//input[@type='password']");
    public Locator signInButton = new Locator(LocatorType.XPATH, "//button[@id='signInBtn']");
}