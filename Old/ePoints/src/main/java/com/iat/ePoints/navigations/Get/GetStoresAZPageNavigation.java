package com.iat.ePoints.Navigations.Get;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 18.06.13
 * Time: 14:20
 * To change this template use File | Settings | File Templates.
 */

import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Get.GetLandingPageLocators;
import com.iat.ePoints.Locators.Get.GetRetailerPageLocators;
import com.iat.ePoints.Locators.Get.GetStoresAZPageLocators;
import com.iat.ePoints.Locators.Get.GetTransitionPageLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class GetStoresAZPageNavigation extends AbstractPage {

    private GetLandingPageLocators locator_landingPage = new GetLandingPageLocators();
    private GetStoresAZPageLocators locator_shopsAZ = new GetStoresAZPageLocators();
    private GetRetailerPageLocators locator_retailer = new GetRetailerPageLocators();
    private GetTransitionPageLocators locator_transition = new GetTransitionPageLocators();

    String retailer_name;

    public GetStoresAZPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // Checking if lightbox window with Sign in panel is displayed correctly ///////////////////////////////////////////
    public void goToShopsAZpage() {
        executor.click(locator_landingPage.earnEpointsMenu);
        executor.click(locator_landingPage.shopsAZsubmenu);
        assertEquals("Page title is incorrect", executor.return_driver().getTitle(), "Get epoints from thousands of retailers | epoints");
    }


    //Checking content of Stores A-Z page //////////////////////////////////////////////////////////////////////////////
    public void checkContentOfAZpage() {
        assertTrue("Popular stores carousel is no visible", executor.is_element_present(locator_shopsAZ.popularStorriesContainer));
        assertTrue("Left arrow is no visible", executor.is_element_present(locator_shopsAZ.favouritesArrowLeft));
        assertTrue("Right arrow is no visible", executor.is_element_present(locator_shopsAZ.favouritesArrowRight));
        assertTrue("Stores A-Z title is no visible", executor.is_element_present(locator_shopsAZ.storesAZHeader));
        assertTrue("Searcher text field is no visible", executor.is_element_present(locator_shopsAZ.searcherTextfield));
        assertTrue("Search button is no visible", executor.is_element_present(locator_shopsAZ.searcherButton));
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        List<WebElement> elements = executor.getWebElementsList(locator_shopsAZ.basicAlphabetLocator);
        for (int i = 0; i < elements.size(); i++) {
            assertEquals("Letter " + alphabet[i] + " is incorrect", alphabet[i], executor.getText(elements.get(i)));
        }
        //pagination elements
        assertTrue("Top pagination girder is not visible", executor.is_element_present(locator_shopsAZ.mainPaginationGirder));
        assertTrue("Bottom Pagination Component is no visible", executor.is_element_present(locator_shopsAZ.mainBottomPaginationComponent));
        assertTrue("Department component is no visible", executor.is_element_present(locator_shopsAZ.depeartmentsComponent));
    }

    public void checkSearcherVisibility() {
        assertTrue("Searcher text field is no visible", executor.is_element_present(locator_shopsAZ.searcherTextfield));
        assertTrue("Search button is no visible", executor.is_element_present(locator_shopsAZ.searcherButton));
    }

    public void enterRetailerNameIntoSearcher(String retailer) {
        executor.send_keys(locator_shopsAZ.searcherTextfield, retailer);
    }

    public void clickSearchButton() throws InterruptedException {
        executor.click(locator_shopsAZ.searcherButton);
        Thread.sleep(2000);
    }

    public void checkRetailerResult(String retailer) {
        executor.moveMouseToWebElement(locator_shopsAZ.basicAlphabetRetailerLocator);
        assertEquals("Retailers names are not the same after search", retailer, executor.get_element(locator_shopsAZ.basicAlphabetRetailerNameLocator).getText());
    }

    public void checkAllRetailersForGivenLetters() throws InterruptedException {
        //list all letters
        List<WebElement> letters = executor.getWebElementsList(locator_shopsAZ.basicAlphabetLocator);
        for (int i = 0; i < letters.size() - 1; i++) {
            //click chosen letter
            executor.click(letters.get(i));
            if(letters.get(i).getText().equals("X")){continue;} //TODO - there is no any retailer on X that why this line is introduced
            //list retailers
            Thread.sleep(500);
            List<WebElement> retailers = executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerLocator);
            if(retailers.size()==0){
                continue;
            }
            List<WebElement> retailersName;
            for (int j=0; j<retailers.size(); j++) {
                executor.moveMouseToWebElement(retailers.get(j));
                Thread.sleep(100);
                retailersName = executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerNameLocator);
                if(retailersName.get(j).getText().length()>0){
                    assertEquals("First letter of retailer name is incorrect - retailer name - " + retailersName.get(j).getText() + ", letter - " + executor.getText(letters.get(i)), executor.getText(letters.get(i)).charAt(0), retailersName.get(j).getText().toUpperCase().charAt(0));
                }
                if(j>2){
                    break;
                }
            }
        }
    }

    // RETAILER PAGE ON EPOINTS - add pagination to A-Z interface(RD-1168) /////////////////////////////////////////////
    public void checkIfAllGirderElementsAreAvailable() {
        assertTrue("Showing range is no visible", executor.is_element_present(locator_shopsAZ.showingRange));
        assertTrue("Left arrow is no visible", executor.is_element_present(locator_shopsAZ.showingRangeLeft));
        assertTrue("Right arrow is no visible", executor.is_element_present(locator_shopsAZ.showingRangeRight));
        assertTrue("Total retailers number is no visible", executor.is_element_present(locator_shopsAZ.totalRetailersNumber));
        assertTrue("Items per page is no visible", executor.is_element_present(locator_shopsAZ.itemsPerPage2040100));
    }

    public void checkIfAllBottomPaginationElementsAreAvailable() {
        assertTrue("Prev button is no Visible", executor.is_element_present(locator_shopsAZ.bottomPaginationPrev));
        assertTrue("Next button is no Visible", executor.is_element_present(locator_shopsAZ.bottomPaginationNext));
        assertTrue("Active number is no Visible", executor.is_element_present(locator_shopsAZ.bottomPaginationPageNumberActive));
    }

    public void navigateToLetterWithLargeNumberOfRetailers(){
        List<WebElement> letters = executor.getWebElementsList(locator_shopsAZ.basicAlphabetLocator);
        for(WebElement letter : letters){
            executor.click(letter);
            if(Integer.parseInt(executor.getText(locator_shopsAZ.totalRetailersNumber).replace("(out of ","").replace(")","")) > 40){
                break;
            }
        }
    }

    public void clickRightArrow() {
        executor.click(locator_shopsAZ.showingRangeRight);
    }

    public void clickLeftArrow() {
        executor.click(locator_shopsAZ.showingRangeLeft);
    }

    public void checkShowingBehaviour(String range, String pageNumber) {
        assertEquals("Ranges are not equals", range, executor.getText(locator_shopsAZ.showingRange));
        assertEquals("Page number is incorrect", pageNumber, executor.getText(locator_shopsAZ.bottomPaginationPageNumberActive));
    }

    public void checkPaginationBehaviour(int retailersNumber) {
        //Check if there is proper pagination number according to total products
        int total = Integer.parseInt(executor.getText(locator_shopsAZ.totalRetailersNumber).replace("(out of ", "").replace(")", ""));
        List<WebElement> pages = executor.getWebElementsList(locator_shopsAZ.bottomPaginationPageNumber);
        assertEquals("Pagination number of pages is incorrect", executor.getText(pages.get(pages.size()-1)), Integer.toString(total / retailersNumber + 1));
    }

    public void changeItemPerPageNumber(String retailersOnPage) throws InterruptedException {
        //Click chosen range
        Thread.sleep(4000);
        List<WebElement> pageSizes = executor.getWebElementsList(locator_shopsAZ.itemsPerPage2040100);
        for (WebElement sizes : pageSizes) {
            if (sizes.getText().equals(retailersOnPage)) {
                executor.click(sizes);
                break;
            }
        }
        Thread.sleep(4000);// has to stay
        //Check number of retailers on page
        //List<WebElement> retailers = executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerLocator);
        //assertEquals("Retailer number on page is incorrect", Integer.parseInt(retailersOnPage), retailers.size());
    }

    public void clickNextButton() {
        executor.click(locator_shopsAZ.bottomPaginationNext);
    }

    public void clickPrevButton() {
        executor.click(locator_shopsAZ.bottomPaginationPrev);
    }

    public void clickDirectlyPageNumber() {
        executor.click(locator_shopsAZ.bottomPaginationPageNumberNotActive);
    }

    // RETAILER PAGE ON EPOINTS - update A-Z search results interface //////////////////////////////////////////////////
    public void pointChosenRetailer() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> retailers = executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerLocator);
        List<WebElement>  linksToRetailersPages = executor.getWebElementsList(locator_shopsAZ.retailerCardRetailerPageButtonAlphabeticOnly);
        List<WebElement> linksToTransitionPages = executor.getWebElementsList(locator_shopsAZ.retailerCardTransitionPAgeButtonAlphabeticOnly);
        for(int i=0; i<retailers.size(); i++){
            executor.moveMouseToWebElement(retailers.get(i));
            assertTrue("Retailer card link button is invisible", executor.is_element_present(linksToRetailersPages.get(i)));
            assertTrue("Transition card link is invisible", executor.is_element_present(linksToTransitionPages.get(i)));
            if(i>5){
                break;
            }
        }
    }

    public void goToRetailerPage() throws InterruptedException {
        executor.moveMouseToWebElement(executor.get_element(locator_shopsAZ.retailerCardRetailerCardContainerAlphabeticOnly));
        String retailerName = executor.getText(locator_shopsAZ.basicAlphabetRetailerNameLocator);
        executor.click(locator_shopsAZ.retailerCardRetailerPageButtonAlphabeticOnly);
        Thread.sleep(2000);
        assertEquals("page Title is incorrect", executor.return_driver().getTitle(), retailerName+": Offers, Vouchers and Reviews | epoints");
        executor.return_driver().navigate().back();
    }

    public void goToTransitionPage() throws InterruptedException {
        executor.moveMouseToWebElement(executor.get_element(locator_shopsAZ.retailerCardRetailerCardContainerAlphabeticOnly));

        executor.click(locator_shopsAZ.retailerCardTransitionPAgeButtonAlphabeticOnly);
        Thread.sleep(1000);
        assertTrue("Transition modal wass not opened",executor.is_element_present(locator_transition.transitionModalWindow));
    }

    // RETAILER PAGE ON EPOINTS - make retailer favourite logic and A-Z page change(RD-1159) ///////////////////////////
    public void checkFavouriteStoreSectionVisibility(boolean expectation) throws InterruptedException {
        Thread.sleep(4000); //TODO - - -
        if(expectation){
            assertTrue("Favourite section is no visible",executor.is_element_present(locator_shopsAZ.favouriteStoresContainer));
        }else if(!expectation){
            assertFalse("Favourite section is visible, but should not", executor.is_element_present(locator_shopsAZ.favouriteStoresContainer));
        }
    }

    String multiplier;
    String multiplierText;
    String retailerId;
    public void goToChosenRetailerPageAndStoreCardElementsValues() throws InterruptedException {
        int random = executor.return_random_value(19);
        List<WebElement> retailerelement;
        //Get ep multiplier text like x4
        retailerelement = executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerMultiplier);
        multiplier = retailerelement.get(random).getText();
        //Get ep multiplier text
        retailerelement = executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerMultiplierText);
        multiplierText = retailerelement.get(random).getText();
        //Position mouse on wanted retailer card
        retailerelement = executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerLocator);
        executor.moveMouseToWebElement(retailerelement.get(random));
        //Go to chosen retailer page
        retailerelement = executor.getWebElementsList(locator_shopsAZ.retailerCardRetailerPageButtonAlphabeticOnly);
        retailerId = retailerelement.get(random).getAttribute("href");
        executor.click(retailerelement.get(random));
    }

    public void clickAddOrRemoveToFavouritesButton(boolean addOrRemove) throws InterruptedException {
        Thread.sleep(2000);
        if(addOrRemove)
        {
            executor.click(locator_retailer.addToFavouritesPlusButton);
        }else if(!addOrRemove){
            executor.click(locator_retailer.addToFavouritesOkButton);
        }
        executor.return_driver().navigate().back();
    }

    public void checkFavouriteBlockContent() throws InterruptedException {
        executor.click(locator_shopsAZ.favouriteStoresContainer);
        Thread.sleep(500);
        List<WebElement> favouriteRetailer = executor.getWebElementsList(locator_shopsAZ.favouriteStoresBasicRetailerlocator);
        assertEquals("Multiplier values are not the same", executor.getText(locator_shopsAZ.favouriteStoresBasicRetailerMultipler), multiplier);
        assertEquals("Multiplier texts are not the same", executor.getText(locator_shopsAZ.favouriteStoresBasicRetailerMultiplerText), multiplierText);
        assertTrue("Retailers number in favourites is no correct", favouriteRetailer.size()==1);
    }

    public void checkUserInterestsTableForChanges() throws SQLException {
        JDBC jdbc = new JDBC("points_manager");
        assertTrue("Favourite information was wrote in db incorrectly", retailerId.contains(jdbc.return_proper_db_value("SELECT interest FROM points_manager.UserInterests WHERE userId = '" + jdbc.return_proper_db_value(jdbc.return_proper_query("returnId", "emailwybitnietestowy@gmail.com"), 1) + "'", 1)));
        jdbc.close();
    }

    public void removeStoreFromFavourites() throws InterruptedException {
       //Position mouse on wanted retailer card
       executor.moveMouseToWebElement(locator_shopsAZ.favouriteStoresBasicRetailerlocator);
       //Go to chosen retailer page
       executor.click(locator_shopsAZ.retailerCardRetailerPageButton);
       //Remove store from favourites
       clickAddOrRemoveToFavouritesButton(false);
    }

    // RETAILER PAGE ON EPOINTS - make retailer favourite logic and A-Z page change(RD-1159) - more product test ///////
    public void addSeveralRetailersToFavourites() throws InterruptedException {
        List<WebElement> retailers;
        List<WebElement> retailersPageButtons;
        for(int i=0; i<8; i++){
            changeItemPerPageNumber("40");
            retailers = executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerLocator);
            retailersPageButtons = executor.getWebElementsList(locator_shopsAZ.retailerCardRetailerPageButtonAlphabeticOnly);
            //int random = executor.return_random_value(retailers.size());
            executor.moveMouseToWebElement(retailers.get(i));
            executor.click(retailersPageButtons.get(i));
            Thread.sleep(2000);
            if(!executor.is_element_present(locator_retailer.addToFavouritesPlusButton)){
                //i--;
                executor.return_driver().navigate().back();
                continue;
            }
            clickAddOrRemoveToFavouritesButton(true);
        }
    }

    public void checkContentAndBehaviourOfCarousel(){
        executor.click(locator_shopsAZ.favouriteStoresContainer);
        assertTrue("Right arrow is no visible", executor.is_element_present(locator_shopsAZ.favouritesArrowRight));
        assertTrue("left arrow is no visible", executor.is_element_present(locator_shopsAZ.favouritesArrowLeft));
    }

    //
    public void checkVisibilityOfAllDepartments(){
        assertTrue("Categories component is not visible", executor.is_element_present(locator_shopsAZ.depeartmentsComponent));

        String[] categories = {"Books","Fashion","Home & Garden","Health & Beauty","Baby & Child","Sports & Outdoors","Computer & Office","Music, Film & Gaming","Toys & Games","Electronics"};
        List <WebElement> categoriesUI = executor.getWebElementsList(locator_shopsAZ.basicCategory);
        for(int i=0; i<categoriesUI.size(); i++){
            assertEquals("Category is no correct", executor.getText(categoriesUI.get(i)), categories[i]);
        }
    }

    int numberOfRetailersOnBeginning;
    List<WebElement> retailers;
    public void selectOneDepartment(){
        retailers =executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerLocator);
        numberOfRetailersOnBeginning = retailers.size();
        List<WebElement> categories = executor.getWebElementsList(locator_shopsAZ.basicCategory);
        executor.click(categories.get(executor.return_random_value(8)));
    }

    public void checkIfNumberOfRetailersWasDecreased(){
        retailers = executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerLocator);
        assertTrue("Number of Retailers was not decreased", retailers.size()<numberOfRetailersOnBeginning);
    }

    public void checkIfThereAreHiddenLetters(){
        List<WebElement> disabledLetters = executor.getWebElementsList(locator_shopsAZ.basicAlphabetLocatorDisabled);
        assertTrue("Any letter is disabled but should be", disabledLetters.size()>0);
    }

    public void clickSeeAllButton(){
        executor.click(locator_shopsAZ.seeAll);
    }

    public void compareNumberOfDisplayedRetailerWithNumberOnBeggining(){
        retailers = executor.getWebElementsList(locator_shopsAZ.basicAlphabetRetailerLocator);
        assertTrue("Number of locator is different then on beginning", retailers.size()==numberOfRetailersOnBeginning);
    }

    // Ensure that Recently visited stores is disabled for not logged user /////////////////////////////////////////////
    public void checkIfRecentlyVisitedComponentIsEnabled(boolean isEnabled) throws InterruptedException {
        Thread.sleep(8000);
        if(isEnabled){
            assertTrue("Recently visited component is no visible", executor.is_element_present(locator_shopsAZ.recentlyVisitedStoresContainer));
        }else if(!isEnabled){
            assertFalse("Recently visited component visible but should not", executor.is_element_present(locator_shopsAZ.recentlyVisitedStoresContainer));
        }
    }

    // Ensure that Recently visited stores is enabled for logged user and works fine ///////////////////////////////////
    public void goToRecentlyVisitedComponent(){
        executor.click(locator_shopsAZ.recentlyVisitedStoresContainer);
    }

    public void checkIfInsideRecentlyVisitedComponentIsAnyRetailer(){
        assertTrue("There is no retailers in RVS component", executor.is_element_present(locator_shopsAZ.favouriteStoresBasicRetailerlocator));
    }

    public void openRetailerPageUsingRecentlyVisitedComponentContent(){
        executor.moveMouseToWebElement(locator_shopsAZ.favouriteStoresBasicRetailerlocator);
        retailer_name = executor.getText(locator_shopsAZ.basicFavouriteRetailerNameLocator);
        executor.click(locator_shopsAZ.retailerCardRetailerPageButton);
        assertEquals("Retailer Page title is incorrect", retailer_name+": Offers, Vouchers and Reviews | epoints", executor.return_driver().getTitle());
    }




























    /*
    // AFFILAITE MANAGER - enable the use of deeplinks outside of solr (RD-1553) - check if transition was opened using deeplink and check clickout
    public void openTransitionPageUsingDeeplink() throws InterruptedException {
        executor.return_driver().get(envVariables.baseUrl+"/transition?name=My Favourite Magazines&multiplier=4&referrerUrl=https://qa.epoints.com/&deeplink=http%3A%2F%2Fwww.awin1.com%2Fpclick.php%3Fp%3D721275376%26a%3D95617%26m%3D1199");

    }

    public void checkClikoutForDeeplink() throws InterruptedException, SQLException {
        retailer_name ="My Favourite Magazines";
        checkIfClickoutWasReported("","");
    } */

}