package com.iat.ePoints.Locators.Spend;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

public class ManageItemPageLocators implements IPageLocators {

    public Locator desc_tab = new Locator(LocatorType.XPATH, "//h1[@class='product-title']");
    public Locator desc_content = new Locator(LocatorType.XPATH, "//div[@class='section section-description']//div[@class='section-content description']");
    public Locator details_tab = new Locator(LocatorType.XPATH, "//div[@id='productDetailsRegion']");
    public Locator delivery_tab = new Locator(LocatorType.XPATH, "//div[@class='section section-delivery']//div[@class='section-header']");
    public Locator delivery_content = new Locator(LocatorType.XPATH, "//div[@class='section section-delivery']//div[@class='section-content description']");
    public Locator minus_btn = new Locator(LocatorType.XPATH, "//button[@class='quantity-decrase']");
    public Locator plus_btn = new Locator(LocatorType.XPATH, "//button[@class='quantity-incrase']");
    public Locator SelectReward_btn = new Locator(LocatorType.XPATH, "//button[contains(text(),'Select reward')]");
    public Locator goBack = new Locator(LocatorType.XPATH, "//a[contains(text(),'Back to previous page')]");
    public Locator itemsQuant = new Locator(LocatorType.XPATH, "//input[@class='quantity-value-input']");
    public Locator backToRewards = new Locator(LocatorType.XPATH, "//a[@class='back-to-rewards-button']");
    public Locator productPicture = new Locator(LocatorType.XPATH, "//div[@class='photo-container']");
    public Locator basketContentShortInformatinMessage = new Locator(LocatorType.XPATH, "//div[@id='orderItemCounter']//span[@class='message']");
    public Locator basketContentShortInformationValue = new Locator(LocatorType.XPATH, "//div[@id='orderItemCounter']//span[@class='value']");
    public Locator basketIcon = new Locator(LocatorType.XPATH, "//i[@class='icon-gift']");
    public Locator basketsWidgetRegion = new Locator(LocatorType.XPATH, "//div[@id='basketWidgetRegion']");
    public Locator basketSmallWindowPicture = new Locator(LocatorType.XPATH, "//div[@class='circle-image']");
    public Locator basketSmallWindowProductNameAndLink = new Locator(LocatorType.XPATH, "//div[@class='description']//div[@class='title']");
    public Locator basketSmallWindowQuantity = new Locator(LocatorType.XPATH, "//div[@class='quantity']");
    public Locator basketSmallWindowViewSelectedRewardsButton = new Locator(LocatorType.XPATH, "//div[@class='text-center']//a[@class='view-rewards-button btn btn-small btn-yellow']");
}