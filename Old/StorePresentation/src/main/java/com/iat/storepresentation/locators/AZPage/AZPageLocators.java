package com.iat.storepresentation.Locators.AZPage;

import com.iat.storepresentation.Locators.Locator;
import com.iat.storepresentation.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.03.14
 * Time: 13:26
 * To change this template use File | Settings | File Templates.
 */
public class AZPageLocators {
    //AZ page
//Popular Stores
    public Locator popularStorriesContainer = new Locator(LocatorType.XPATH, "//div[@id='topCarousel']//h2[@data-retailers='popular']");
    //Stores Locators can be used from favourites Locators section
//Your Favourite stores
    public Locator favouriteStoresContainer = new Locator(LocatorType.XPATH, "//div[@id='topCarousel']//h2[contains(text(),'Your favourite stores')]");
    public Locator favouriteStoresHeader = new Locator(LocatorType.XPATH, "//div[@id='topCarousel']//h2[contains(text(),'Your favourite stores')]");
    public Locator favouriteStoresBasicRetailerlocator = new Locator(LocatorType.XPATH, "//div[@id='topCarousel']//div[@class='retailerCard']");
    public Locator basicFavouriteRetailerNameLocator = new Locator(LocatorType.XPATH, "//div[@id='topCarousel']//div[@class='retailerCard-hover']//h5");
    public Locator favouriteStoresBasicRetailerImage = new Locator(LocatorType.XPATH, "//div[@id='topCarousel']//div[@class='retailerCard']//div[@class='retailer-image']");
    public Locator favouriteStoresBasicRetailerMultipler = new Locator(LocatorType.XPATH, "//div[@id='topCarousel']//div[@class='retailerCard']//div[@class='multiplier']");
    public Locator favouriteStoresBasicRetailerMultiplerText = new Locator(LocatorType.XPATH, "//div[@id='topCarousel']//div[@class='retailerCard']//div[@class='multiplier-text']");
    public Locator favouritesArrowRight = new Locator(LocatorType.XPATH, "//a[@id='retailers_next']");
    public Locator favouritesArrowLeft = new Locator(LocatorType.XPATH, "//a[@id='retailers_prev']");
//Recently visited stores
    public Locator recentlyVisitedStoresContainer = new Locator(LocatorType.XPATH, "//div[@id='topCarousel']//h2[@data-retailers='visited']");
// Search Locators
    public Locator storesAZHeader = new Locator(LocatorType.XPATH, "//h1[contains(text(),'Get epoints across all these stores')]");
    public Locator searcherTextfield = new Locator(LocatorType.XPATH, "//input[@id='inputStore']");
    public Locator searcherButton = new Locator(LocatorType.XPATH, "//form[@id='searchStores']//button[@class='search-submit btn branded']");
    public Locator getEpointsWhen = new Locator(LocatorType.XPATH, "//p[contains(text(),'Get epoints when you buy at these online stores')]");
    public Locator basicAlphabetLocator = new Locator(LocatorType.XPATH, "//li[@class]//a[@class='letter']");
    public Locator basicAlphabetLocatorDisabled = new Locator(LocatorType.XPATH, "//li[@class='disabled']//a[@class='letter']");
// Alphabetic filter and retailers
    public Locator basicAlphabetRetailerLocator = new Locator(LocatorType.XPATH, "//div[@class='reg-items']//li");
    public Locator basicAlphabetRetailerNameLocator = new Locator(LocatorType.XPATH, "//div[@id='azMerchantsContent']//li//div[@class='retailerCard-hover']//h5"); //It contains retailer name ////div[@id='azMerchantsContent']//li//div//img old
    public Locator basicAlphabetRetailerHrefLocator = new Locator(LocatorType.XPATH, "//div[@id='azMerchantsContent']//div[@class='retailerCard-hover']//a[2]"); //It contains retailer href
    public Locator basicAlphabetRetailerImageSource = new Locator(LocatorType.XPATH, "//div[@id='azMerchantsContent']//div[@class='retailerCard']//div[@class='retailer-image']");
    public Locator basicAlphabetRetailerMultiplier = new Locator(LocatorType.XPATH, "//div[@id='azMerchantsContent']//div[@class='retailerCard']//div[@class='multiplier']");
    public Locator basicAlphabetRetailerMultiplierText = new Locator(LocatorType.XPATH, "//div[@id='azMerchantsContent']//div[@class='retailerCard']//div[@class='multiplier-text']");
//Links to retailer page and transition on retailers cards
    public Locator retailerCardRetailerCardContainer = new Locator(LocatorType.XPATH, "//div[@class='retailerCard']");
    public Locator retailerCardRetailerCardContainerAlphabeticOnly = new Locator(LocatorType.XPATH, "//div[@id='azMerchantsContent']//div[@class='retailerCard']");
    public Locator retailerCardRetailerPageButton = new Locator(LocatorType.XPATH, "//div[@class='retailerCard-hover']//a[contains(text(),'Info & Offers')]");
    public Locator retailerCardRetailerPageButtonAlphabeticOnly = new Locator(LocatorType.XPATH, "//div[@id='azMerchantsContent']//div[@class='retailerCard-hover']//a[contains(text(),'Info & Offers')]");
    public Locator retailerCardTransitionPAgeButton= new Locator(LocatorType.XPATH, "//div[@class='retailerCard-hover']//a[contains(text(),'Visit retailer ')]");
    public Locator retailerCardTransitionPAgeButtonAlphabeticOnly= new Locator(LocatorType.XPATH, "//div[@id='azMerchantsContent']//div[@class='retailerCard-hover']//a[contains(text(),'Visit retailer ')]");
// Departments Locators
    public Locator depeartmentsComponent = new Locator(LocatorType.XPATH, "//div[@class='departments-filter']");
    public Locator seeAll = new Locator(LocatorType.XPATH, "//span[@class='pull-right showall evt-show-all']");
    public Locator basicCategory = new Locator(LocatorType.XPATH, "//ul[@class='departments-filter-list']//li"); //9 categories

}
