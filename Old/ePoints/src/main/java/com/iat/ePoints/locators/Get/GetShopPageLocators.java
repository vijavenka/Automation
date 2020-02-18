package com.iat.ePoints.Locators.Get;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 31.01.14
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
public class GetShopPageLocators {

    // Top search Locators
    public String[] departments = {"all departments","Books","Fashion","Home & Garden","Health & Beauty","Baby & Child","Sports & Outdoors","Computer & Office","Music, Film & Gaming","Toys & Games","Electronics"};
    //departments
    public Locator searchDepartmentComponent = new Locator(LocatorType.XPATH, "//div[@class='search-container shop-search-bar']");
    public Locator searchDepartmentComponentDepartmrntCategoryPage = new Locator(LocatorType.XPATH, "//div[@class='shop-search-bar']");
    public Locator searchDepartmentComponentProductPage = new Locator(LocatorType.XPATH, "//div[@class='search-container']");

    //div[@class='shop-search-bar']
    public Locator searchDepartmentDDL = new Locator(LocatorType.XPATH, "//div[@id='searchDepartmentsRegion']//div[@class='departments-navigation']//a");

        public Locator searchDepartmentOptionBooks = new Locator(LocatorType.XPATH, "//a[@href='/price-comparison/books']");
        public Locator searchDepartmentOptionFashion = new Locator(LocatorType.XPATH, "//a[@href='/price-comparison/fashion']");
        public Locator searchDepartmentOptionHomeAndGarden = new Locator(LocatorType.XPATH, "//a[@href='/price-comparison/home-and-garden']");
        public Locator searchDepartmentOptionHealthAndBeauty = new Locator(LocatorType.XPATH, "//a[@href='/price-comparison/health-and-beauty']");
        public Locator searchDepartmentOptionBabyAndChild = new Locator(LocatorType.XPATH, "//a[@href='/price-comparison/baby-and-child']");
        public Locator searchDepartmentOptionSportAndOutdors = new Locator(LocatorType.XPATH, "//a[@href='/price-comparison/sports-and-outdoors']");
        public Locator searchDepartmentOptionComputerAndOffice = new Locator(LocatorType.XPATH, "//a[@href='/price-comparison/computer-and-office']");
        public Locator searchDepartmentOptionBooksFilmAndGaming = new Locator(LocatorType.XPATH, "//a[@href='/price-comparison/music-film-and-gaming']");
        public Locator searchDepartmentOptionToysAndGames = new Locator(LocatorType.XPATH, "//a[@href='/price-comparison/toys-and-games']");
        public Locator searchDepartmentOptionElectronics = new Locator(LocatorType.XPATH, "//a[@href='/price-comparison/electronics']");
        public Locator[] DepartmentsDDL ={searchDepartmentOptionBooks,searchDepartmentOptionFashion,searchDepartmentOptionHomeAndGarden,searchDepartmentOptionHealthAndBeauty,searchDepartmentOptionBabyAndChild,searchDepartmentOptionSportAndOutdors,searchDepartmentOptionComputerAndOffice,searchDepartmentOptionBooksFilmAndGaming,searchDepartmentOptionToysAndGames,searchDepartmentOptionElectronics};

    public Locator searchDepartmentOptionChosen = new Locator(LocatorType.XPATH, "//div[@class='departments-navigation']//ul[@class='departments-filter-list']//a[@class='branded']");
    public Locator searchDepartmentSectionHeader = new Locator(LocatorType.XPATH, "//div[@class='search-container']//ul[@class='departments-filter-list']//li//div[@class='submenu branded']//h4//a");
    public Locator searchCategory = new Locator(LocatorType.XPATH, "//div[@class='search-container']//ul[@class='departments-filter-list']//li//div[@class='submenu branded']//h5//a");
    public Locator searchSubCategory = new Locator(LocatorType.XPATH, "//div[@class='search-container']//ul[@class='departments-filter-list']//li//div[@class='submenu branded']//ul//li");
    //search
    public Locator searchTextField = new Locator(LocatorType.XPATH, "//input[@class='input']");
    public Locator searchDepartmentRightDDL = new Locator(LocatorType.XPATH, "//i[@class='dropdown-icon icon-chevron-down']");
    public Locator searchDepartmentRightOption= new Locator(LocatorType.XPATH, "//ul[@class='select-dropdown']//li");
    public Locator searchButton = new Locator(LocatorType.XPATH, "//button[contains(text(),'Search')]");
    // Facets filter component Locators
    public Locator basicFacetComponent = new Locator(LocatorType.XPATH, "//div[@id='searchFiltersRegion']");
    public Locator basicFacetSubCategory = new Locator(LocatorType.XPATH, "//span[@class='value-label']");
    public Locator basicFacetCategory = new Locator(LocatorType.XPATH, "//div[@class='facet']//div[@class='title']");
    public Locator basicFacetSeeAllButton = new Locator(LocatorType.XPATH, "//div[@class='facet']//span[@class='show-all-button action-button']");

    public Locator clearAllFiltresButton = new Locator(LocatorType.XPATH, "//div[@class='btn btn-grey facets-clear-all']");
    //modal window facet filter
    public Locator facetModalWindow = new Locator(LocatorType.XPATH, "//div[@class='module-container']");
    public Locator basicFacetCategoryFacetModal = new Locator(LocatorType.XPATH, "//div[@class='module-container']//li[@class='facet-tab-switcher']");
    public Locator basicFacetCategoryOptionFacetModal = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@class='tab-content']//li[@class='value']//label//span[@class='value-label']");
    public Locator doneButtonFacetModal = new Locator(LocatorType.XPATH, "//div[@class='module-container']//button[@class='btn btn-yellow button-done']");
    public Locator closeReferenceFacetModal = new Locator(LocatorType.XPATH, "//div[@class='module-container']//strong[contains(text(),'Close')]");
    public Locator closeXButtonFacetModal = new Locator(LocatorType.XPATH, "//div[@class='module-container']//button[@class='close-modal-button']");
// Pagination component Locators
    /**
     * The same Locators are iz GetStoresAZPageLocators class and those will be used
     */
    public Locator sortByDDL = new Locator(LocatorType.XPATH, "//div[@class='reg-sort-by inline-region']//div//select"); // ,price|asc,price|desc 
// Product card Locators, very similar Locators were presented in GetReetailerPage class (special offers section)
    public Locator basicProductContainer = new Locator(LocatorType.XPATH, "//div[@class='module-container product-card']");
    public Locator basicProductImage = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='image']");
    public Locator basicProductPercentageSaving = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='percent-saving']");
    public Locator basicProductEpointsEarned = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='epoints-earned']");
    public Locator basicProductProductTitle = new Locator(LocatorType.XPATH, "//div[@class='module-container product-card']//div[@class='title']");
    public Locator basicProductOldPrice = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='price-old']");
    public Locator basicProductNewPrice = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='price']");
    public Locator basicProductFromRetailer = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='price-from-retailer']");
    public Locator basicProductBuyButton = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//a[@class='buy-button btn btn-yellow clickOutLink']");
    public Locator basicProductBuyButtonHref = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//a");
    public Locator basicProductRedeemLink = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='redeem-button']");
    // Product split view
    public Locator basicMainProductTitleSplit = new Locator(LocatorType.XPATH, "//div[@class='productinfo-title']//a[@class='product-page-trigger']");
    public Locator basicProductRedeemLinkSplit = new Locator(LocatorType.XPATH, "//div[@class='module-productInfo rowcard']//div[@class='redeem-button']");
    public Locator productInfoCardContainerSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']");
    public Locator closeButtonSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='close-button']");
    public Locator naviPrevSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='navi-prev']");
    public Locator naviNextSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='navi-next']");
    public Locator productImageSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='image-container']");
    public Locator informationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//li[contains(text(),'Information')]");
    public Locator priceComparisonTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//li[contains(text(),'Price Comparison')]");
    public Locator productDescriptionInformationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//p[@class='description more'] ");
    public Locator retailerNameInformationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//span[@class='retailer-name']");
    public Locator ratingInformationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='rating-modal']");
    public Locator priceInformationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='price-container']//span[@class='price']");
    public Locator priceWithDeliveryInformationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='price-with-delivery-container']//span[@class='price']");
    public Locator epointsEarnedInformationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//span[@class='epoints-earned']");
    public Locator buyFromStoreButtonSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//a[@class='buy-button btn btn-yellow clickOutLink']");
    public Locator facebookIconSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//span[@class='icon-facebook-squared']");
    public Locator twitterIconSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//span[@class='icon-twitter']");
    public Locator googlePlusIconSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//span[@class='icon-googleplus']");
    public Locator comparisonTablePriceComparisonTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='productInfo-comparison-table']");
    // Product individual page
    public Locator productRedeemLinkIndividualPage1 = new Locator(LocatorType.XPATH, "//div[@class='product-info']//div[@class='redeem']//a[@class='redeem-button']");
    public Locator productRedeemLinkIndividualPage2 = new Locator(LocatorType.XPATH, "//div[@class='grid-row table-row']//div[@class='redeem']//a[@class='redeem-button']");
    public Locator backToPreviousPageLinkProductPage = new Locator(LocatorType.XPATH, "//a[@class='back-to-rewards-button']");
    public Locator pageTitleProductNameProductPage = new Locator(LocatorType.XPATH, "//h1[@class='page-title']");
    public Locator productImageProductPage = new Locator(LocatorType.XPATH, "//div[@class='product-image']");
    public Locator priceValueProductPage = new Locator(LocatorType.XPATH, "//div[@class='product-info']//div[@class='price-value']");
    public Locator retailerLinkProductPage = new Locator(LocatorType.XPATH, "//div[@class='product-info']//div[@class='retailer']//a");
    public Locator ratingProductPage = new Locator(LocatorType.XPATH, "//div[@class='product-info']//div[@class='rating-modal']");
    public Locator buyFromStoreButtonProductPage = new Locator(LocatorType.XPATH, "//div[@class='product-info']//a[@class='buy-button btn btn-yellow btn-large clickOutLink']");
    public Locator epointsValueProductPage = new Locator(LocatorType.XPATH, "//div[@class='product-info']//div[@class='epoints']");
    public Locator descripionProductPage = new Locator(LocatorType.XPATH, "//div[@class='grid-row narrow']//h2");
    public Locator socialIconsProductPage = new Locator(LocatorType.XPATH, "//div[@class='grid-row narrow']//p[@class='share-icons social']");
    public Locator comparisonTableProductPage = new Locator(LocatorType.XPATH, "//div[@class='productcard-comparison-table']");
// Category Page
// Department page
    public Locator departmentCategoryHeader = new Locator(LocatorType.XPATH, "//div[@class='page-title-container']");
    public Locator departmentCategoryNameHeader = new Locator(LocatorType.XPATH, "//div[@class='page-title-container']//span");
    public Locator categoryPageNameHeader = new Locator(LocatorType.XPATH, "//div[@class='page-title-container']//h1");
    public Locator basicDepartmentCategoryLeftPaneOption = new Locator(LocatorType.XPATH, "//div[@class='category-breadcrumb-nav']//li//a");
    public Locator basicDepartmentCategoryLeftPaneChildrenOnlyOption = new Locator(LocatorType.XPATH, "//div[@class='category-breadcrumb-nav']//ul[@class='children']//li//a");
    public Locator basicDepartmentCategoryPictureOption = new Locator(LocatorType.XPATH, "//div[@class='small-padding']//article[@class='category-card']");
    public Locator basicDepartmentCategoryPicture = new Locator(LocatorType.XPATH, "//div[@class='small-padding']//article[@class='category-card']//div[@class='image']");
    public Locator chosenCategoryInDepartmentPage = new Locator(LocatorType.XPATH, "//div[@class='custom-item-view']//h1[@class='page-title']");


}