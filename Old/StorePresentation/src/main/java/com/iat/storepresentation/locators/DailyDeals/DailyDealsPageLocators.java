package com.iat.storepresentation.Locators.DailyDeals;

import com.iat.storepresentation.Locators.Locator;
import com.iat.storepresentation.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.03.14
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */
public class DailyDealsPageLocators {


// Daily deals cards
    public Locator dealContainerDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']");
    public Locator dealImageDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']//div[@class='image']");
    public Locator dealEpRewarnDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']//div[@class='epoints-earned']");
    public Locator dealDescriptionDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']//div[@class='title']");
    public Locator dealPriceDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']//div[@class='price']");
    public Locator getDealButtonDailyDealPage= new Locator(LocatorType.XPATH, "//div[@class='module-container product-card deal-card']//a[@class='buy-button btn']");
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

    public Locator locationFacetPopupWindow = new Locator(LocatorType.XPATH, "//li//a[contains(text(),'Location')]");
    public Locator locationFacetLocationPopupWindow = new Locator(LocatorType.XPATH, "//div[@class='tab-content']//li[@class='value']//label//span[@class='value-label']");

    public Locator facetsDoneButton = new Locator(LocatorType.XPATH, "//button[@class='btn btn-yellow button-done']");
// Individual daily deals page
    public Locator backToPreviousPageDDIndivididualPage = new Locator(LocatorType.XPATH, "//a[@class='back-page']");
    public Locator dealNameDDIndivididualPage = new Locator(LocatorType.XPATH, "//h3[@class='product-title']");
    public Locator dealPictureDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@class='photo-container']");
    public Locator dealPriceDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@class='bestprice-label']//div[@class='vertical-center']");
    public Locator dealSavingDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@class='saving branded']");
    public Locator dealRetailerDDIndivididualPage = new Locator(LocatorType.XPATH, "//span[@class='from']//a");
    //public Locator dealTrustPilotDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@class='rating-modal']");
    public Locator getDealButtonDDIndivididualPage = new Locator(LocatorType.XPATH, "//a[@class='bestprice-buy branded']");
    public Locator dealEpointsDDIndivididualPage = new Locator(LocatorType.XPATH, "//span[@class='epoints-earned']");
    public Locator expireCounterDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@id='countdownRegion']");
    public Locator dealDescriptionDDIndivididualPage = new Locator(LocatorType.XPATH, "//div[@id='descriptionRegion']//br");
    public Locator dealShareComponentDDIndivididualPage = new Locator(LocatorType.XPATH, "//span[@class='social']");
}
