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
public class GetDailyDealsPageLocators {
    public Locator dealHeaderLocationDailyDealPage= new Locator(LocatorType.XPATH, "//strong[@class='location']");
// Daily deals cards
    public Locator dealContainerDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']");
    public Locator dealImageDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']//div[@class='image']");
    public Locator dealEpRewarnDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']//div[@class='epoints-earned']");
    public Locator dealDescriptionDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']//div[@class='title']");
    public Locator dealPriceDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']//div[@class='price']");
    public Locator getDealButtonDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']//a[@class='buy-button btn btn-yellow clickOutLink']");
    public Locator dealExpiryDateDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']//span[@class='expire-date'] ");
    public Locator dealPercentageSavingDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']//div[@class='deal-percent-saving']");
// Facets and popup window slider options
    public Locator facetsSeeAllButton = new Locator(LocatorType.XPATH, "//span[@class='show-all-button action-button']");

    public Locator epointsEarnedSliderFacet = new Locator(LocatorType.XPATH, "//ul[@class='nav nav-tabs']//a[contains(text(),'Epoints Earned')]");
    public Locator priceSliderFacet = new Locator(LocatorType.XPATH, "//ul[@class='nav nav-tabs']//a[contains(text(),'Price')]");
    public Locator savingSliderFacet = new Locator(LocatorType.XPATH, "//ul[@class='nav nav-tabs']//a[contains(text(),'% Saving')]");

    public Locator leftValueEpointsEarned = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@data-name='i_epointsEarned']//span[@class='value-from']");
    public Locator rightValueEpointsEarned = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@data-name='i_epointsEarned']//span[@class='value-to']");
    public Locator leftSliderEpointsEarned = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@data-name='i_epointsEarned']//div[@class='ui-range-slider horizontal']//a//div");
    public Locator rightSliderEpointsEarned = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@data-name='i_epointsEarned']//div[@class='ui-range-slider horizontal']//a[2]//div");
    public Locator leftValuePrice = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@data-name='f_price']//span[@class='value-from']");
    public Locator rightValuePrice = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@data-name='f_price']//span[@class='value-to']");
    public Locator leftSliderPrice = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@data-name='f_price']//div[@class='ui-range-slider horizontal']//a//div");
    public Locator rightSliderPrice = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@data-name='f_price']//div[@class='ui-range-slider horizontal']//a[2]//div");
    public Locator leftValueSaving = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@data-name='f_percentSaving']//span[@class='value-from']");
    public Locator rightValueSaving = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@data-name='f_percentSaving']//span[@class='value-to']");
    public Locator leftSliderSaving = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@data-name='f_percentSaving']//div[@class='ui-range-slider horizontal']//a//div");
    public Locator rightSliderSaving = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@data-name='f_percentSaving']//div[@class='ui-range-slider horizontal']//a[2]//div");

    public Locator facetsDoneButton = new Locator(LocatorType.XPATH, "//button[@class='btn btn-yellow button-done']");
// Location Locators
    public Locator changeLocationButton = new Locator(LocatorType.XPATH, "//span[contains(text(),'Change location')]");
    public Locator locationOptionModal = new Locator(LocatorType.XPATH, "//ul[@class='deal-locations']//li");
    public Locator locationModal = new Locator(LocatorType.XPATH, "//div[@class='title deal-locations-modal-title']");
    public Locator locationHeaderModal = new Locator(LocatorType.XPATH, "//div[contains(text(),'Select location')]");
    public Locator locationCloseModal = new Locator(LocatorType.XPATH, "//button[@class='close-modal-button']");
// Individual daily deals page
    public Locator backToPreviousPageDDIndivididualPage = new Locator(LocatorType.XPATH, "//a[@class='back-to-previous']");
    public Locator dealNameDDIndivididualPage = new Locator(LocatorType.XPATH, "//h1[@class='page-title']");
    public Locator dealPictureDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@class='product-image']");
    public Locator dealPriceDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@class='price-value']");
    public Locator dealSavingDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@class='price-saving']");
    public Locator dealRetailerDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@class='retailer']//a");
    public Locator dealTrustPilotDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@class='rating-modal']");
    public Locator getDealButtonDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@class='btn-container']//a");
    public Locator dealEpointsDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@class='btn-container']//div[@class='epoints']");
    public Locator expireCounterDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@id='countdownRegion']");
    public Locator dealDescriptionDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@class='grid-row seminarrow']//p");
    public Locator dealShareComponentDDIndivididualPage = new Locator(LocatorType.XPATH, "//p[@class='share-icons social']");

}
