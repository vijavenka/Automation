package com.iat.ePoints.Navigations.Get;

import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.EnvironmentVariables;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Get.*;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 31.01.14
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
public class GetShopPageNavigation extends AbstractPage {

    private GetShopPageLocators locators_shop = new GetShopPageLocators();
    private GetPriceComparisonPageLocators locators_compare = new GetPriceComparisonPageLocators();
    private GetRetailerPageLocators locators_retailer = new GetRetailerPageLocators();
    private GetStoresAZPageLocators locators_AZpage = new GetStoresAZPageLocators();
    private GetLandingPageLocators locators_getLandingPage = new GetLandingPageLocators();

    private EnvironmentVariables envVariables = new EnvironmentVariables();

    public GetShopPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    //EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) - check department navigation visibility
    String chosenDepartmentName;
    public void chooseSomeDepartmenIcon() throws InterruptedException {

        int randomDepartment = 0;//executor.return_random_value(8);
            //find and click chosen department
            executor.click(locators_compare.returnCategoryLocator(randomDepartment));
            Thread.sleep(2000);
        executor.clickXpath(locators_compare.returnCategoryLocator(randomDepartment).get_body()+locators_compare.basicDepartmentCategoryLink.get_body());
    }

    public void checkIfDepartmentNavigationIsAvaliable(){
        assertTrue("Department navigation is no visible", executor.is_element_present(locators_shop.searchDepartmentComponent));
    }

    //EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) - check if department navigation has proper content
    public void checkContentOfLeftDDL() throws InterruptedException {
        Thread.sleep(4000);
        assertTrue("Left Department DDL is no visible", executor.is_element_present(locators_shop.searchDepartmentDDL));
        //move mouse to DDL to activate subsections
        executor.moveMouseToWebElement(locators_shop.searchDepartmentDDL);
        Thread.sleep(1000);
        for(int i=0; i<10; i++){
            assertEquals("Department name is incorrect", executor.getText(locators_shop.DepartmentsDDL[i]), locators_shop.departments[i+1]);
        }
    }

    public void checkContenOfSearch(){
        assertTrue("Search text field is no visible", executor.is_element_present(locators_shop.searchTextField));
        assertTrue("Search button is no visible", executor.is_element_present(locators_shop.searchButton));
    }

    public void checkContentOfRightDDL(){
        //only movement and checking departments name and subsection availability
        executor.click(locators_shop.searchDepartmentRightDDL);
        List<WebElement> departments = executor.getWebElementsList(locators_shop.searchDepartmentRightOption);
        int counter=0;
        for(WebElement department : departments){
            //first one is all departments
            assertEquals("Department name is incorrect", "in "+locators_shop.departments[counter], executor.getText(department));
            counter++;
        }
    }

    // EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) - check if search works correctly
    String defaultFirstProductName;
    String defaultFirstProductNameAfterSearch;
    public void searchNeededPhrase(String phrase) throws InterruptedException {
        Thread.sleep(6000);
        defaultFirstProductName = executor.getText(locators_shop.basicProductProductTitle);
        executor.send_keys(locators_shop.searchTextField, phrase);
        executor.click(locators_shop.searchButton);
        Thread.sleep(2000);
        defaultFirstProductNameAfterSearch = executor.getText(locators_shop.basicProductProductTitle);
    }

    public void checkIfProperProductWereFound(String phrase) throws InterruptedException {
        //List Locators of names of found product
        Thread.sleep(5000); //TODO - - -
        List<WebElement> productNames = executor.getWebElementsList(locators_shop.basicProductProductTitle);
        boolean foundFlag = false;
        for(WebElement productName : productNames){
            if(executor.getText(productName).contains(phrase)){
                foundFlag = true;
                break;
            }
        }
        assertTrue("Search does not work, needed phrase was not found "+phrase, foundFlag);
    }

    // EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) - check if department filter works
    public void changeSearchDepartment() throws InterruptedException {
        executor.click(locators_shop.searchDepartmentRightDDL);
        List<WebElement> departments = executor.getWebElementsList(locators_shop.searchDepartmentRightOption);
        executor.click(departments.get(executor.return_random_value(10)));
        executor.click(locators_shop.searchButton);
        Thread.sleep(1000);
    }

    public void checkIfDisplayedProductWereChanged(){
        //if another product was found check if is different
        if(executor.is_element_present(locators_shop.basicProductProductTitle)){
            assertNotEquals("Products after department change should be different", defaultFirstProductName, executor.getText(locators_shop.basicProductProductTitle));
        }
    }

    // EPOINTS - add points to redeem display to product interfaces (RD-1152) - check visibility and working of links #we search to find some product with redeeem display
    public void checkIfSomeRedeemProductIsVisible(){
        assertTrue("Product with redeem is no visible", executor.is_element_present(locators_shop.basicProductRedeemLink));
    }

    public void useRedeemLinkOnProductCard(){
        executor.click(locators_shop.basicProductRedeemLink);
    }

    public void checkCorrectnessOfOpenedProductPage(){
        assertTrue("Page title is incorrect", executor.return_driver().getTitle().contains(defaultFirstProductNameAfterSearch));
        assertTrue("Page url is incorrect", executor.return_driver().getCurrentUrl().contains("/spend"));
        executor.return_driver().navigate().back();
    }

    public void clickOnProductToShowSplitView() throws InterruptedException {
        executor.click(locators_shop.basicProductProductTitle);
        Thread.sleep(1000);
    }

    public void useRedeemLinkOnProductSplitView(){
        assertTrue("Redeem link is no available on split view", executor.is_element_present(locators_shop.basicProductRedeemLinkSplit));
        executor.click(locators_shop.basicProductRedeemLinkSplit);
    }

    public void goToIndividualShopProductPage()  {
        executor.click(locators_shop.basicMainProductTitleSplit);
    }

    public void checkVisibilityOfRedeemLinksOnShopIndyvidualProductPage(){
        assertTrue("First redeem link is no visible", executor.is_element_present(locators_shop.productRedeemLinkIndividualPage1));
        assertTrue("Second redeem link is no visible", executor.is_element_present(locators_shop.productRedeemLinkIndividualPage2));
    }

    public void checkWorkingOfTwoRedeemLinksOnShopProductPage(){
        executor.click(locators_shop.productRedeemLinkIndividualPage1);
        checkCorrectnessOfOpenedProductPage();
        executor.click(locators_shop.productRedeemLinkIndividualPage2);
        checkCorrectnessOfOpenedProductPage();
    }

    // EPOINTS - add DEPARTMENT interfaces to epoints.com (RD-1154) - Department page availability from comparison page
    String departmrntName;
    int categoriesListNumber;
    public void chooseSomeDepartmentAndRememberItsData() throws InterruptedException {
        int randomDepartment = executor.return_random_value(10);
        List<WebElement> departments = executor.getWebElementsList(locators_compare.basicDepartmentNotActive);
        executor.click(departments.get(randomDepartment));
        Thread.sleep(1000);
        departmrntName = executor.getText(locators_compare.basicDepartmentCrdActiveName);

        //copy all categories
        List<WebElement> categories = null;
        if(executor.is_element_present(locators_compare.basicDepartmentCategoryLargeActive)){
            categories = executor.getWebElementsList(locators_compare.basicDepartmentCategoryLargeActive);
        }else if(executor.is_element_present(locators_compare.basicDepartmentCategorySmallActive)){
            categories = executor.getWebElementsList(locators_compare.basicDepartmentCategorySmallActive);
        }
        categoriesListNumber = categories.size();
        executor.click(locators_compare.basicDepartmentCrdActiveName);
    }

    public void checkIfCorrectDepartmentPageWasOpened(boolean isFormComparison) throws InterruptedException {
        Thread.sleep(2000);
        assertEquals("Department Page is incorrect", departmrntName + " department | epoints", executor.return_driver().getTitle());
        assertEquals("Department header name is incorrect", "Department:"+departmrntName, "Department:"+executor.getText(locators_shop.departmentCategoryNameHeader));
        if(isFormComparison){
            List<WebElement> categories = executor.getWebElementsList(locators_shop.basicDepartmentCategoryLeftPaneOption);
            assertEquals("Categories number is incorrect", categoriesListNumber, categories.size() );
        }
    }

    public void checkVisibilityOSearchComponentOnDepartmentCategoryView(){
        assertTrue("Department navigation and search component is invisible on department and category page", executor.is_element_present(locators_shop.searchDepartmentComponentDepartmrntCategoryPage));
    }

    public void checIfCategoriesOnDepartmentPageAreDisplayedCorrectly(){
        List<WebElement> categoriesLinks = executor.getWebElementsList(locators_shop.basicDepartmentCategoryLeftPaneOption);
        List<WebElement> categoriesCircless = executor.getWebElementsList(locators_shop.basicDepartmentCategoryPictureOption);
        assertTrue("There is no option on Left Panel", categoriesLinks.size()>0);
        assertTrue("There is no any circle on Department page", categoriesCircless.size()>0);
        for(int i=0; i<categoriesLinks.size(); i++){
            assertTrue("Categories are not the same", executor.getText(categoriesCircless.get(i)).contains(executor.getText(categoriesLinks.get(i))));
        }
    }

    // EPOINTS - add DEPARTMENT interfaces to epoints.com (RD-1154) - Department page availability from shop page
    public void chooseCategoryFromDepartmentsDDL() throws InterruptedException {
        Thread.sleep(6000);
        int randomDepartment = executor.return_random_value(10);
        executor.moveMouseToWebElement(locators_shop.searchDepartmentDDL);
        //departmrntName = locators_shop.departments[1];
        departmrntName = executor.getText(locators_shop.DepartmentsDDL[randomDepartment]);
        executor.click(locators_shop.DepartmentsDDL[randomDepartment]);
    }

    // EPOINTS - add DEPARTMENT interfaces to epoints.com (RD-1154) - second level of department
    String subcategoryName;
    public void choseOneOfSubDepartment() {
        List<WebElement> categoriesLinks = executor.getWebElementsList(locators_shop.basicDepartmentCategoryLeftPaneOption);
        int random = executor.return_random_value(categoriesLinks.size());
        subcategoryName = executor.getText(categoriesLinks.get(random));
        executor.click(categoriesLinks.get(random));
    }

    public void checkWhichPathWasSetAndCheckContentOfPageAndTheirName() throws InterruptedException {
        // if department second level page check contend and go to shop
        // if shop page check only shop page name
        if(executor.is_element_present(locators_shop.basicDepartmentCategoryLeftPaneOption)){
            Thread.sleep(5000);
            List<WebElement> categoriesLinksChildren = executor.getWebElementsList(locators_shop.basicDepartmentCategoryLeftPaneChildrenOnlyOption);
            List<WebElement> categoriesCircles = executor.getWebElementsList(locators_shop.basicDepartmentCategoryPictureOption);
            assertEquals("D1 category name is incorrect", subcategoryName, executor.getText(locators_shop.departmentCategoryNameHeader));
            assertEquals("Page tittle is incorrect", subcategoryName + " department | epoints", executor.return_driver().getTitle());
            for(int i=0; i<categoriesLinksChildren.size(); i++){
                assertTrue("Categories are not the same", executor.getText(categoriesCircles.get(i)).contains(executor.getText(categoriesLinksChildren.get(i))));
            }
            int random = executor.return_random_value(categoriesLinksChildren.size());
            subcategoryName = executor.getText(categoriesLinksChildren.get(random));
            executor.click(categoriesLinksChildren.get(random));
        }
        assertEquals("Shop page title is incorrect", "Find your epoints reward | epoints", executor.return_driver().getTitle());
        assertEquals("Shop page title is not the same as chosen category/department", subcategoryName, executor.getText(locators_shop.chosenCategoryInDepartmentPage));
    }

    // EPOINTS - add PRODUCT PAGE interface to epoints.com (RD-1146) - product page availability from comparison page
    String productPrise;
    // TODO epoints reward is no mandatory
    //String productEpointsReward;
    String productName;
    String retailerName;
    int randomProduct = executor.return_random_value(40);
    public void saveNamePriceEpointsOfChosenProduct(){
        productPrise = executor.getText(executor.getWebElementsList(locators_shop.basicProductNewPrice).get(randomProduct));
        //productEpointsReward = executor.getText(executor.getWebElementsList(locators_shop.basicProductEpointsEarned).get(randomProduct));
        productName = executor.getText(executor.getWebElementsList(locators_shop.basicProductProductTitle).get(randomProduct));
    }

    public void openProductPageOfChosenProduct(){
        executor.click(executor.getWebElementsList(locators_shop.basicProductProductTitle).get(randomProduct));
        retailerName = executor.getText(locators_shop.retailerNameInformationTabSplit);
        executor.click(locators_shop.basicMainProductTitleSplit);
        assertEquals("Proper product page was not opened", productName +" | epoints", executor.return_driver().getTitle());
    }

    public void checkVisibilityOfAllProductPageElements(){
        assertTrue("Back to previous page link is no visible " + productName, executor.is_element_present(locators_shop.backToPreviousPageLinkProductPage));
        assertTrue("Product name is no visible " + productName, executor.is_element_present(locators_shop.pageTitleProductNameProductPage));
        assertTrue("Product image is no visible " + productName, executor.is_element_present(locators_shop.productImageProductPage));
        assertTrue("Price is no visible " + productName, executor.is_element_present(locators_shop.priceValueProductPage));
        assertTrue("Retailer link is no visible " + productName, executor.is_element_present(locators_shop.retailerLinkProductPage));
        assertTrue("Product rating is no visible " + productName, executor.is_element_present(locators_shop.ratingProductPage));
        assertTrue("Buy button is no visible " + productName, executor.is_element_present(locators_shop.buyFromStoreButtonProductPage));
        // epoints values are not mandatory
        //assertTrue("Epoints value is no visible " + productName, executor.is_element_present(locators_shop.epointsValueProductPage));
        assertTrue("Description is no visible " + productName, executor.is_element_present(locators_shop.descripionProductPage));
        assertTrue("Socials icons are no visible " + productName, executor.is_element_present(locators_shop.socialIconsProductPage));
        assertTrue("Comparison table is no visible " + productName, executor.is_element_present(locators_shop.comparisonTableProductPage));
    }

    public void compareIndividualProductValuesFromProductCard(){
        assertEquals("Names are not the same" + productName, productName , executor.getText(locators_shop.pageTitleProductNameProductPage));
        assertEquals("Prises are not the same" + productName, productPrise , executor.getText(locators_shop.priceValueProductPage));
        //assertEquals("Epoints values are not the same" + productName, productEpointsReward , executor.getText(locators_shop.epointsValueProductPage).replace(",",""));
        assertEquals("Retailer names are not the same" + productName, "from " + retailerName , executor.getText(locators_shop.retailerLinkProductPage));
    }

    public void clickBackToPreviousPageButton(){
        executor.click(locators_shop.backToPreviousPageLinkProductPage);
    }

    public void checkIfUserWasCorrectlyRedirectedToComparisonPage(){
        assertEquals("User was redirected to wrong page", "Find your epoints reward | epoints", executor.return_driver().getTitle());
    }

    // EPOINTS - add PRODUCT PAGE interface to epoints.com (RD-1146) - product page availability from retailer page
    public void saveNamePriceEpointsOfChosenProductFromSpecialOfferBlock(){
        productPrise = executor.getText(locators_retailer.specialOffersNewPrice);
        //productEpointsReward = executor.getText(locators_retailer.specialOffersEpointsEarned);
        productName = executor.getText(locators_retailer.specialOffersProductTitle);
        retailerName = executor.getText(locators_retailer.retailerName);
    }

    public void openProductPageOfChosenProductFomSpecialOfferBlock(){
        executor.click(locators_retailer.specialOffersProductTitle);
    }

    public void checkIfUserWasCorrectlyRedirectedToRetailerPage(){
        assertEquals("User was redirected to incorrect page", retailerName + ": Offers, Vouchers and Reviews | epoints", executor.return_driver().getTitle());
    }

    // EPOINTS - add PRODUCT PAGE interface to epoints.com (RD-1146) - check working of retailer link and buy button
    public void clickRetailerLinkonProductPage(){
        executor.click(locators_shop.retailerLinkProductPage);
    }

    public void returnToProductPageAndClickBuyButton(){
        executor.return_driver().navigate().back();
        executor.click(locators_shop.buyFromStoreButtonProductPage);
    }

    // EPOINTS - add CATEGORY interfaces to epoints.com (RD-1145 ) - category reached form retailer page category block
    String categoryName;
    public void openCategoryPageWithProductInIt() throws InterruptedException {
        // chose category until it will contains some products
        if(executor.is_element_present(locators_retailer.productCategoriesSeeMoreButton)){
            executor.click(locators_retailer.productCategoriesSeeMoreButton);
        }
        Thread.sleep(2000);//hast to stay
        List<WebElement> categories = executor.getWebElementsList(locators_retailer.productCategoriesSubCategories);
        int random = executor.return_random_value(categories.size());
        categoryName = executor.getText(categories.get(random));
        executor.click(categories.get(random));
        while(!executor.is_element_present(locators_shop.basicProductContainer)){
            executor.return_driver().navigate().back();
            if(executor.is_element_present(locators_retailer.productCategoriesSeeMoreButton)){
                executor.click(locators_retailer.productCategoriesSeeMoreButton);
            }
            categories = executor.getWebElementsList(locators_retailer.productCategoriesSubCategories);
            random = executor.return_random_value(categories.size());
            categoryName = executor.getText(categories.get(random));
            executor.click(categories.get(executor.return_random_value(categories.size())));
        }
    }

    public void checkIfCorrectCategoryPageWasOpened(){
        assertEquals("Category page was not opened", "Find your epoints reward | epoints", executor.return_driver().getTitle());
        assertTrue("Category page header is incorrect according to chosen one", categoryName.contains(executor.getText(locators_shop.categoryPageNameHeader)));
    }

    public void checkContentOfCategoryPage(){
        if(executor.is_element_present(locators_shop.basicProductProductTitle))
        {
            executor.click(locators_shop.basicProductProductTitle);
            assertTrue("Department level navigation is no visible", executor.is_element_present(locators_shop.searchDepartmentComponent));
            assertTrue("Top pagination  is no visible", executor.is_element_present(locators_AZpage.mainPaginationGirder));
            assertTrue("Bottom pagination is no visible", executor.is_element_present(locators_AZpage.mainBottomPaginationComponent));
            assertTrue("Facets are no visible", executor.is_element_present(locators_shop.basicFacetComponent));
            assertTrue("Products card is no visible", executor.is_element_present(locators_shop.basicProductContainer));
            assertTrue("Product Split view is no visible", executor.is_element_present(locators_shop.productInfoCardContainerSplit));
        }
    }

    // EPOINTS - add SEARCH RESULTS interfaces to epoints.com (RD-1153) - content of product cards
    public void viewAllProductOnCurrentPageAndCheckTheirContent(){
        List<WebElement> productCard = executor.getWebElementsList(locators_shop.basicProductContainer);
        List<WebElement> productPicture = executor.getWebElementsList(locators_shop.basicProductImage);
        // TODO ep rewards are not mandatory
        //List<WebElement> productEpReward = executor.getWebElementsList(locators_shop.basicProductEpointsEarned);
        List<WebElement> productTitle = executor.getWebElementsList(locators_shop.basicProductProductTitle);
        List<WebElement> productPrice = executor.getWebElementsList(locators_shop.basicProductNewPrice);
        List<WebElement> productRetailer = executor.getWebElementsList(locators_shop.basicProductFromRetailer);
        List<WebElement> productBuyButton = executor.getWebElementsList(locators_shop.basicProductBuyButton);
        assertTrue("Product element number should be the same", (productCard.size()/*+productPicture.size()/*+productEpReward.size()*/+productTitle.size()+productPrice.size()+productRetailer.size()+productBuyButton.size())/productCard.size() == 5);
        for(int i=0; i<productCard.size()-2; i++){
            assertTrue("Picture is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(productPicture.get(i)));
            // assertTrue("Ep reward is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(productEpReward.get(i)));
            assertTrue("Title is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(productTitle.get(i)));
            assertTrue("Price is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(productPrice.get(i)));
            assertTrue("Retailer name is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(productRetailer.get(i)));
            assertTrue("Buy button is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(productBuyButton.get(i)));
        }
    }

    // EPOINTS - add SEARCH RESULTS interfaces to epoints.com (RD-1153) - split view
    public void checkContentOfEachProductSplitView() throws InterruptedException {
        Thread.sleep(5000);
        executor.click(locators_shop.searchButton);//TODO selenium bug DDL is opened after creating list, that why search is additionally clicked
        List<WebElement> productCard = executor.getWebElementsList(locators_shop.basicProductContainer);
        //List<WebElement> productEpReward = executor.getWebElementsList(locators_shop.basicProductEpointsEarned);
        List<WebElement> productTitle = executor.getWebElementsList(locators_shop.basicProductProductTitle);
        List<WebElement> productPrice = executor.getWebElementsList(locators_shop.basicProductNewPrice);
        List<WebElement> productRetailer = executor.getWebElementsList(locators_shop.basicProductFromRetailer);
        executor.click(locators_shop.basicProductProductTitle);
        for(int i=0; i<5; i++){
            assertTrue("Split view is no available for "+ executor.getText(productTitle.get(i)), executor.is_element_present(locators_shop.productInfoCardContainerSplit));
            executor.click(locators_shop.informationTabSplit);
            // compare values from product card
            assertEquals("Product names are not equals for " + executor.getText(productTitle.get(i)), executor.getText(productTitle.get(i)), executor.getText(locators_shop.basicMainProductTitleSplit));

            List<WebElement> epointsValues = executor.getWebElementsList(locators_shop.epointsEarnedInformationTabSplit);
            int highestValue=0;
            for(WebElement ep : epointsValues){
                if(Integer.parseInt(executor.getText(ep).replace("+",""))>highestValue){
                    highestValue = Integer.parseInt(executor.getText(ep).replace("+", ""));
                }
            }
            // TODO ep rewards are not mandatory
            //assertEquals("Ep rewards are not equals for "+ executor.getText(productTitle.get(i)), executor.getText(productEpReward.get(i)), "+" + Integer.toString(highestValue));
            assertEquals("Product prices are not equals for "+ executor.getText(productPrice.get(i)), executor.getText(productPrice.get(i)), executor.getText(locators_shop.priceInformationTabSplit));
            assertEquals("Product retailers are not equals for "+ executor.getText(productRetailer.get(i)), executor.getText(productRetailer.get(i)), "from " + executor.getText(locators_shop.retailerNameInformationTabSplit));

            //static elements
            assertTrue("Close button is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_shop.closeButtonSplit));
            assertTrue("Product picture is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_shop.productImageSplit));
            assertTrue("Description is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_shop.productDescriptionInformationTabSplit));
            assertTrue("Rating is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_shop.ratingInformationTabSplit));
            assertTrue("Delivery information is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_shop.priceWithDeliveryInformationTabSplit));
            assertTrue("Buy button is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_shop.buyFromStoreButtonSplit));
            assertTrue("Facebook icon is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_shop.facebookIconSplit));
            assertTrue("Twitter icon is no visible for " + executor.getText(productTitle.get(i)),executor.is_element_present(locators_shop.twitterIconSplit));
            assertTrue("G+ icon is no visible for " + executor.getText(productTitle.get(i)),executor.is_element_present(locators_shop.googlePlusIconSplit));
            executor.click(locators_shop.naviNextSplit);
        }
    }

    // EPOINTS - add SEARCH RESULTS interfaces to epoints.com (RD-1153) - facet component
    public void checkVisibilityOfFacetComponent(){
        assertTrue("Facet component is no visibility", executor.is_element_present(locators_shop.basicFacetComponent));
    }

    String firstProductName;
    public void useFacetComponent() throws InterruptedException {
        executor.click(locators_shop.searchButton);//TODO selenium bug DDL is opened after creating list, that why search is additionally clicked
        Thread.sleep(2000);
        firstProductName = executor.getText(locators_shop.basicProductProductTitle);
        //List<WebElement> facetCategories = executor.getWebElementsList(locators_shop.basicFacetCategory);
        //executor.click(facetCategories.get(0));
        executor.click(locators_shop.basicFacetSubCategory);
        Thread.sleep(1000);
        assertNotEquals("Product should be filtered", firstProductName, executor.getText(locators_shop.basicProductProductTitle));
    }

    public void clickClearAllFilterButton() throws InterruptedException {
        executor.click(locators_shop.clearAllFiltresButton);
        Thread.sleep(2000);
    }

    public void checkIfFiltersWereDisabled(){
        assertEquals("Filters were not disabled", firstProductName, executor.getText(locators_shop.basicProductProductTitle));
    }

    // EPOINTS - add SEARCH RESULTS interfaces to epoints.com (RD-1153) - facet popup
    //String facetsFirst = null;
    public void clickSeeeAllFacetsButton() throws InterruptedException {
        executor.click(locators_shop.searchButton);//TODO selenium bug DDL is opened after creating list, that why search is additionally clicked
        //remember facets names to compare with popup
        firstProductName = executor.getText(locators_shop.basicProductProductTitle);
        //List<WebElement> facetsOptions = executor.getWebElementsList(locators_shop.basicFacetCategory);
        //facetsFirst = executor.getText(locators_shop.basicFacetCategory);
        executor.click(locators_shop.basicFacetSeeAllButton);
        Thread.sleep(2000);
    }

    public void checkVisibilityOfFacetsPopup(){
        assertTrue("Popup with facet is no visible", executor.is_element_present(locators_shop.facetModalWindow));
    }

    public void checkCorrectnessOfFacetCategories(){

    }

    public void choseFilterOption() throws InterruptedException {
        executor.click(locators_shop.basicFacetCategoryOptionFacetModal);
        executor.click(locators_shop.doneButtonFacetModal);
        Thread.sleep(2000);
    }

    public void checkIfFilterIsEnabled(){
        // if there is no visible product it means that no product were found and filter works
        if(executor.is_element_present(locators_shop.basicProductProductTitle)){
        assertNotEquals("Filter is not enabled - modal option", firstProductName, executor.getText(locators_shop.basicProductProductTitle));
        }
    }

    // EPOINTS - add SEARCH RESULTS interfaces to epoints.com (RD-1153) - clickout from product card
    public void clickBuyButtonOfChosenMerchant() throws InterruptedException {
        List<WebElement> buyButtons = executor.getWebElementsList(locators_shop.basicProductBuyButton);
        System.out.println(buyButtons.size());
        List<WebElement> retailerNames = executor.getWebElementsList(locators_shop.basicProductFromRetailer);
        int random = executor.return_random_value(buyButtons.size());
        retailerName = executor.getText(retailerNames.get(random)).replace("from ","");
        executor.click(buyButtons.get(random));
        executor.handleMultipleWindows("Sending to retailer | epoints");
    }

    public void checkIfClickoutWasReported(String ifSigned, String Email) throws InterruptedException, SQLException {
        // this function body is a copy of function from transition page navigation class, it is only a little bit changed
        String CurrentDate = executor.returnCurrentDate();
        Thread.sleep(10000);
        JDBC jdbc = new JDBC("affiliate_manager");
        assertTrue("In db there is no clickout with current date and hour, but should be", jdbc.return_proper_db_value("SELECT createdDate FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1).contains(CurrentDate));
        assertEquals("Clickout has improper merchant name", jdbc.return_proper_db_value("SELECT merchant FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), retailerName);
        assertEquals("Clickout has improper merchantId", jdbc.return_proper_db_value("SELECT merchantId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1));
        assertEquals("Clickout has improper publisher", jdbc.return_proper_db_value("SELECT publisher FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), "epoints");
        assertEquals("Clickout has improper publisherId", jdbc.return_proper_db_value("SELECT publisherId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), "ePoints.com");
        assertEquals("Clickout has improper affiliate networkId", jdbc.return_proper_db_value("SELECT affiliateNetworkId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1));
        assertEquals("Clickout has improper affiliate network name", jdbc.return_proper_db_value("SELECT affiliateNetwork FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT name FROM admin_ui.AffiliateNetwork WHERE id='" + jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1) + "'", 1));
        if (ifSigned.equals("user_sign_in")) {
            String temp = jdbc.return_proper_db_value("SELECT userId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1);
            jdbc = new JDBC("points_manager");
            assertEquals("Clickout has improper UserId", temp, jdbc.return_proper_db_value("SELECT userKey FROM points_manager.User WHERE email = '"+Email+"'",1));
        }
        jdbc.close();
    }

    // EPOINTS - extend top level navigation to include shop links (RD-1139)
    public void checkVisibilityOfAdditionalShopLinks(){
        assertTrue("Vouchers link is no visible", executor.is_element_present(locators_getLandingPage.vouchersSubmenu));
        assertTrue("Daily deals link is no visible", executor.is_element_present(locators_getLandingPage.dailyDealsSubmenu));
        assertTrue("Special offers link is no visible", executor.is_element_present(locators_getLandingPage.specialOffersSubmenu));
    }

    public void useAllAdditionalLinkAndChekkIfTheyWorks() throws InterruptedException {
        executor.click(locators_getLandingPage.vouchersSubmenu);
        Thread.sleep(2000);
        // voucher site + expiry date sorting.
        assertTrue("Vouchers page title is incorrect", executor.return_driver().getCurrentUrl().contains("/vouchers"));
        executor.return_driver().navigate().back();
        executor.click(locators_getLandingPage.specialOffersSubmenu);
        Thread.sleep(2000);
        assertTrue("Special offers page title is incorrect",executor.return_driver().getCurrentUrl().contains("/offers"));
        executor.return_driver().navigate().back();
        executor.click(locators_getLandingPage.dailyDealsSubmenu);
        Thread.sleep(2000);
        assertTrue("Daily deals page title is incorrect", executor.return_driver().getCurrentUrl().contains("/deals"));

    }

}
