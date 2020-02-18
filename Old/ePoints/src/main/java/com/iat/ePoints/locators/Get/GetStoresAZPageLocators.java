package com.iat.ePoints.Locators.Get;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

public class GetStoresAZPageLocators implements IPageLocators {

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
    public Locator searcherTextfieldOptions = new Locator(LocatorType.XPATH, "//li[@class='ui-menu-item']");

    public Locator searcherButton = new Locator(LocatorType.XPATH, "//button[@class='btn btn-yellow store-search']");
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
// Pagination Locators
    public Locator mainPaginationGirder = new Locator(LocatorType.XPATH, "//div[@class='search-settings-wrapper']");
    public Locator showingRange = new Locator(LocatorType.XPATH, "//span[@class='items-visible-range']");
    public Locator showingRangeLeft = new Locator(LocatorType.XPATH, "//div[@class='small-page-control']//i[@class='icon-chevron-left']");
    public Locator showingRangeRight = new Locator(LocatorType.XPATH, "//div[@class='small-page-control']//i[@class='icon-chevron-right']");
    public Locator totalRetailersNumber = new Locator(LocatorType.XPATH, "//span[@class='items-counter']");
    public Locator itemsPerPage2040100 = new Locator(LocatorType.XPATH, "//div[@class='page-size-settings']//span"); //20/40/100

    public Locator mainBottomPaginationComponent = new Locator(LocatorType.XPATH, "//div[@class='reg-page-control']");
    public Locator bottomPaginationPrev = new Locator(LocatorType.XPATH, "//ul[@class='pager']//a[contains(text(),'Prev')]");
    public Locator bottomPaginationNext = new Locator(LocatorType.XPATH, "//ul[@class='pager']//a[contains(text(),'Next')]");
    public Locator bottomPaginationPageNumber = new Locator(LocatorType.XPATH, "//div[@class='pagination-pages']//div");
    public Locator bottomPaginationPageNumberActive = new Locator(LocatorType.XPATH, "//div[@class='pagination-pages']//div[@class='page evt-goto-page active']");
    public Locator bottomPaginationPageNumberNotActive = new Locator(LocatorType.XPATH, "//div[@class='pagination-pages']//div[@class='page evt-goto-page']");
    public Locator backToTop = new Locator(LocatorType.XPATH, "//strong[contains(text(),'Back to top')]");
// Departments Locators
    public Locator depeartmentsComponent = new Locator(LocatorType.XPATH, "//div[@class='departments-filter']");
    public Locator seeAll = new Locator(LocatorType.XPATH, "//span[@class='pull-right showall evt-show-all']");
    public Locator basicCategory = new Locator(LocatorType.XPATH, "//ul[@class='departments-filter-list']//li"); //9 categories
}