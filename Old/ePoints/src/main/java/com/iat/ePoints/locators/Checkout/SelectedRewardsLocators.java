package com.iat.ePoints.Locators.Checkout;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

public class SelectedRewardsLocators implements IPageLocators {

    public Locator pageTitle = new Locator(LocatorType.XPATH, "//h4[contains(text(),'Selected rewards')]");
    public Locator itemsTitle_link = new Locator(LocatorType.XPATH, "//strong[@class='title']");
    public Locator remove_btn = new Locator(LocatorType.XPATH, "//div[@class='remove-item']");
    public Locator popupQuestionWindow = new Locator(LocatorType.XPATH, "//div[@class='popover-content']");
    public Locator delete_btn = new Locator(LocatorType.XPATH, "//span[@class='btn btn-success confirm-yes']");
    public Locator noSelectedRewardsCominicate = new Locator(LocatorType.XPATH, "//div[@class='no-rewards']");
    public Locator minus_btn = new Locator(LocatorType.XPATH, "//button[@class='quantity-decrase']");
    public Locator plus_btn = new Locator(LocatorType.XPATH, "//div[@class='item-container']//button[@class='quantity-incrase enabled']");
    public Locator itemQuantity = new Locator(LocatorType.XPATH, "//div[@class='item']//input[@class='quantity-value-input']");
    public Locator pointsOneItem = new Locator(LocatorType.XPATH, "//div[@class='item']//span[@class='cost-value']");
    public Locator missingPoints = new Locator(LocatorType.XPATH, ".//*[@class='missing-points']");
    public Locator totalPoints = new Locator(LocatorType.XPATH, "//div[@id='orderItemsSummary']//span[@class='total-epoints-needed']");
    public Locator removeAll_btn = new Locator(LocatorType.XPATH, "//div[contains(text(),'Remove all items')]");
    public Locator back_btn = new Locator(LocatorType.XPATH, "//div[contains(text(),'Back to previous page')]");
    public Locator order_btn = new Locator(LocatorType.XPATH, "//div[contains(text(),'Order reward')]");
    public Locator backToRewards_btn = new Locator(LocatorType.XPATH, "//a[contains(text(), 'Back to rewards')]");
}