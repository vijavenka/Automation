package com.iat.storepresentation.Navigations.SearchComponent;

import com.iat.storepresentation.EnvironmentVariables;
import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.SearchComponent.SearchPageLocators;
import com.iat.storepresentation.Locators.ShopHome.HomePageLocators;
import com.iat.storepresentation.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.*;
/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.03.14
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
public class SearchPageNavigation extends AbstractPage{

    private HomePageLocators locators_home = new HomePageLocators();
    private SearchPageLocators locator_search = new SearchPageLocators();
    private EnvironmentVariables envVariables = new EnvironmentVariables();


    public SearchPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - working of home page search
    public void typePhraseToSearcher(String phrase){
        executor.send_keys(locators_home.searchInputField, phrase);
    }

    public void clickSearchButton(){
        executor.click(locators_home.searchButton);
    }

    public void checkIfSearchPageWasOpened(){
        assertTrue("Search page was not opened", executor.return_driver().getCurrentUrl().contains(envVariables.baseUrl+"search"));
    }

    public void checkIfProperProductWasFound(String phrase){
        List<WebElement> productNames = executor.getWebElementsList(locators_home.basicProductProductTitle);
        boolean ifPhraseFound = false;
        for(WebElement productName : productNames){
            if(executor.getText(productName).contains(phrase)){
                ifPhraseFound = true;
                break;
            }
        assertTrue("Product with chosen phrase was not found", ifPhraseFound);
        }
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - working of pagination component
    public void checkIfAllGirderElementsAreAvailable() {
        assertTrue("Showing range is no visible", executor.is_element_present(locator_search.showingRange));
        assertTrue("Left arrow is no visible", executor.is_element_present(locator_search.showingRangeLeft));
        assertTrue("Right arrow is no visible", executor.is_element_present(locator_search.showingRangeRight));
        assertTrue("Total retailers number is no visible", executor.is_element_present(locator_search.totalRetailersNumber));
        assertTrue("Items per page is no visible", executor.is_element_present(locator_search.itemsPerPage2040100));
    }

    public void checkIfAllBottomPaginationElementsAreAvailable() {
        assertTrue("Prev button is no Visible", executor.is_element_present(locator_search.bottomPaginationPrev));
        assertTrue("Next button is no Visible", executor.is_element_present(locator_search.bottomPaginationNext));
        assertTrue("Active number is no Visible", executor.is_element_present(locator_search.bottomPaginationPageNumberActive));
        assertTrue("Non active number is no Visible", executor.is_element_present(locator_search.bottomPaginationPageNumberNotActive));
    }

    public void clickRightArrow() throws InterruptedException {
        executor.click(locator_search.showingRangeRight);
        Thread.sleep(1000);
    }

    public void clickLeftArrow() throws InterruptedException {
        executor.click(locator_search.showingRangeLeft);
        Thread.sleep(1000);
    }

    public void checkShowingBehaviour(String range, String pageNumber) {
        assertEquals("Ranges are not equals", range, executor.getText(locator_search.showingRange));
        assertEquals("Page number is incorrect", pageNumber, executor.getText(locator_search.bottomPaginationPageNumberActive));
    }

    public void checkPaginationBehaviour(int retailersNumber) {
        //Check if there is proper pagination number according to total products
        int total = Integer.parseInt(executor.getText(locator_search.totalRetailersNumber).replace("(out of ", "").replace(")", ""));
        List<WebElement> pages = executor.getWebElementsList(locator_search.bottomPaginationPageNumberNotActive);
        if(total % retailersNumber == 0){
            assertEquals("Pagination number of pages is incorrect", Integer.parseInt(executor.getText(pages.get(pages.size()-1))), total / retailersNumber);
        }else{
            assertEquals("Pagination number of pages is incorrect", Integer.parseInt(executor.getText(pages.get(pages.size()-1))), total / retailersNumber + 1);
        }
    }

    public void changeItemPerPageNumber(String retailersOnPage) throws InterruptedException {
        //Click chosen range
        List<WebElement> pageSizes = executor.getWebElementsList(locator_search.itemsPerPage2040100);
        for (WebElement sizes : pageSizes) {
            if (sizes.getText().equals(retailersOnPage)) {
                executor.click(sizes);
                Thread.sleep(1000);
                break;
            }
        }
        //Check number of retailers on page
        List<WebElement> products = executor.getWebElementsList(locators_home.basicProductContainer);
        assertEquals("Retailer number on page is incorrect", Integer.parseInt(retailersOnPage), products.size());
    }

    public void clickNextButton() throws InterruptedException {
        executor.click(locator_search.bottomPaginationNext);
        Thread.sleep(1000);
    }

    public void clickPrevButton() throws InterruptedException {
        executor.click(locator_search.bottomPaginationPrev);
        Thread.sleep(1000);
    }

    public void clickDirectlyPageNumber() throws InterruptedException {
        executor.click(locator_search.bottomPaginationPageNumberNotActive);
        Thread.sleep(1000);
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - working of price filter
    public void checkWorkingOfPriceFilter() throws InterruptedException {
        if(executor.is_element_present(locators_home.basicProductContainer)){
            executor.selectOptionByValue(locator_search.priceRangeFilter, "price|asc");
            Thread.sleep(8000);
            List<WebElement> prices = executor.getWebElementsList(locators_home.basicProductNewPrice);
            int previous=0;
            int priceLocal;
            for(WebElement price : prices){
                if(executor.getText(price).replace("£ ","").substring(0,1).equals("0")){
                    priceLocal = Integer.parseInt(executor.getText(price).replace("£ ","").replace(",","").replace("0.",""));
                }else{
                    priceLocal = Integer.parseInt(executor.getText(price).replace("£ ","").replace(",","").replace(".",""));
                }
                assertTrue("Wrong order no ascending", priceLocal >= previous);
                previous = priceLocal;
            }

            executor.selectOptionByValue(locator_search.priceRangeFilter, "price|desc");
            Thread.sleep(8000);
            prices = executor.getWebElementsList(locators_home.basicProductNewPrice);
            previous=1000000000;
            for(WebElement price : prices){
                System.out.println(executor.getText(price));
                if(executor.getText(price).replace("£ ","").substring(0,1).equals("0")){
                    priceLocal = Integer.parseInt(executor.getText(price).replace("£ ","").replace(",","").replace("0.",""));
                }else{
                    priceLocal = Integer.parseInt(executor.getText(price).replace("£ ","").replace(",","").replace(".",""));
                }
                assertTrue("Wrong order no descending", priceLocal <= previous);
                previous = priceLocal;
            }
        }
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - facet component
    public void checkVisibilityOfFacetComponent(){
        assertTrue("Facet component is no visibility", executor.is_element_present(locator_search.basicFacetComponent));
    }

    String firstProductPrice;
    public void useFacetComponent() throws InterruptedException {
        firstProductPrice = executor.getText(locators_home.basicProductNewPrice);
        List<WebElement> facetCategories = executor.getWebElementsList(locator_search.basicFacetCategory);
        executor.click(facetCategories.get(0));
        List<WebElement> facetSubCategories = executor.getWebElementsList(locator_search.basicFacetSubCategory);
        executor.click(facetSubCategories.get(2));
        Thread.sleep(4000);
        assertNotEquals("Product should be filtered", firstProductPrice, executor.getText(locators_home.basicProductNewPrice));
    }

    public void clickClearAllFilterButton() throws InterruptedException {
        executor.click(locator_search.clearAllFiltresButton);
        Thread.sleep(2000);
    }

    public void checkIfFiltersWereDisabled(){
        assertEquals("Filters were not disabled", firstProductPrice, executor.getText(locators_home.basicProductNewPrice));
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Search results pages (RD-2084) - facet popup window
    public void clickSeeeAllFacetsButton() throws InterruptedException {
        //remember facets names to compare with popup
        firstProductPrice = executor.getText(locators_home.basicProductNewPrice);
        List<WebElement> facetsOptions = executor.getWebElementsList(locator_search.basicFacetCategory);
        System.out.println(facetsOptions.size());
        //facetsFirst = executor.getText(locator_search.basicFacetCategory);
        executor.click(locator_search.basicFacetSeeAllButton);
        Thread.sleep(2000);
    }

    public void checkVisibilityOfFacetsPopup(){
        assertTrue("Popup with facet is no visible", executor.is_element_present(locator_search.facetModalWindow));
    }

    public void checkCorrectnessOfFacetCategories(){

    }

    public void choseFilterOption() throws InterruptedException {
        List<WebElement> facetElements = executor.getWebElementsList(locator_search.basicFacetCategoryOptionFacetModal);
        executor.click(facetElements.get(executor.return_random_value(facetElements.size())));
        executor.click(locator_search.doneButtonFacetModal);
        Thread.sleep(2000);
    }

    public void checkIfFilterIsEnabled(){
        // if there is no visible product it means that no product were found and filter works
        if(executor.is_element_present(locators_home.basicProductProductTitle)){
            assertNotEquals("Filter is not enabled - modal option", firstProductPrice, executor.getText(locators_home.basicProductNewPrice));
        }
    }
}
