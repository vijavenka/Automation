package com.iat.storepresentation.Locators.SearchComponent;

import com.iat.storepresentation.Locators.Locator;
import com.iat.storepresentation.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.03.14
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
public class SearchPageLocators {

// Pagination Locators
    public Locator mainPaginationGirder = new Locator(LocatorType.XPATH, "//div[@class='search-settings-wrapper']");
    public Locator showingRange = new Locator(LocatorType.XPATH, "//span[@class='items-visible-range']");
    public Locator showingRangeLeft = new Locator(LocatorType.XPATH, "//div[@class='small-page-control']//i[@class='icon-chevron-left']");
    public Locator showingRangeRight = new Locator(LocatorType.XPATH, "//div[@class='small-page-control']//i[@class='icon-chevron-right']");
    public Locator totalRetailersNumber = new Locator(LocatorType.XPATH, "//span[@class='items-counter']");
    public Locator itemsPerPage2040100 = new Locator(LocatorType.XPATH, "//div[@class='page-size-settings']//span"); //20/40/100

    public Locator mainBottomPaginationComponent = new Locator(LocatorType.XPATH, "//div[@class='reg-page-control']");
    public Locator bottomPaginationPrev = new Locator(LocatorType.XPATH, "//div[@class='page-control-container']//span[contains(text(),'previous page')]");
    public Locator bottomPaginationNext = new Locator(LocatorType.XPATH, "//div[@class='page-control-container']//span[contains(text(),'next page')]");
    public Locator bottomPaginationPageNumber = new Locator(LocatorType.XPATH, "//div[@class='page-control-row']");
    public Locator bottomPaginationPageNumberActive = new Locator(LocatorType.XPATH, "//div[@class='page-control-container']//span[@class='page evt-goto-page active']");
    public Locator bottomPaginationPageNumberNotActive = new Locator(LocatorType.XPATH, "//div[@class='page-control-container']//span[@class='page evt-goto-page']");
    public Locator backToTop = new Locator(LocatorType.XPATH, "//span[@class='scrollto-top']");
// pagination price filter
    public Locator priceRangeFilter = new Locator(LocatorType.XPATH, "//div[@class='reg-sort-by inline-region']//select");
    // Facets filter component Locators
    public Locator basicFacetComponent = new Locator(LocatorType.XPATH, "//div[@id='searchFiltersRegion']");
    public Locator basicFacetSubCategory = new Locator(LocatorType.XPATH, "//span[@class='value-label']");
    public Locator basicFacetCategory = new Locator(LocatorType.XPATH, "//div[@class='facet']//div[@class='title']");
    public Locator basicFacetSeeAllButton = new Locator(LocatorType.XPATH, "//div[@class='facet']//span[@class='show-all-button action-button']");

    public Locator clearAllFiltresButton = new Locator(LocatorType.XPATH, "//div[@class='btn btn-grey facets-clear-all']");
    //modal window facet filter
    public Locator facetModalWindow = new Locator(LocatorType.XPATH, "//div[@id='modal']//div[@class='module-container']");
    public Locator basicFacetCategoryFacetModal = new Locator(LocatorType.XPATH, "//div[@class='module-productfacets-modal']//li[@class=' facet-tab-switcher']");
    public Locator basicFacetCategoryOptionFacetModal = new Locator(LocatorType.XPATH, "//div[@class='module-container']//div[@class='tab-content']//li[@class='value']//label//span[@class='value-label']");
    public Locator doneButtonFacetModal = new Locator(LocatorType.XPATH, "//div[@class='module-container']//button[@class='btn btn-yellow button-done']");
    public Locator closeReferenceFacetModal = new Locator(LocatorType.XPATH, "//div[@class='module-container']//strong[contains(text(),'Close')]");
    public Locator closeXButtonFacetModal = new Locator(LocatorType.XPATH, "//div[@class='module-container']//button[@class='close-modal-button']");
}
