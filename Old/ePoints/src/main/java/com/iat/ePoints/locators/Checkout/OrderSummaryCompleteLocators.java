package com.iat.ePoints.Locators.Checkout;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

public class OrderSummaryCompleteLocators implements IPageLocators {

// Order Summary
    public Locator editRewards_btn = new Locator(LocatorType.XPATH, "");
    public Locator editDelivery_btn = new Locator(LocatorType.XPATH, "");
    public Locator cancel_btn = new Locator(LocatorType.XPATH, "");
    public Locator placeOrder_btn = new Locator(LocatorType.XPATH, "");
    public Locator pointsItem = new Locator(LocatorType.XPATH, "");
    public Locator deliveryData = new Locator(LocatorType.XPATH, "");
// Complete Order
    public Locator orderHistory_btn = new Locator(LocatorType.XPATH, "");
    public Locator myAccount_btn = new Locator(LocatorType.XPATH, "");
    public Locator earnPoints_btn = new Locator(LocatorType.XPATH, "");
}