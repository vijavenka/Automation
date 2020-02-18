package com.iat.storepresentation.Navigations.AZPage;

import com.iat.storepresentation.Database.JDBC;
import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.AZPage.AZPageLocators;
import com.iat.storepresentation.Locators.RetailePage.RetailerPageLocators;
import com.iat.storepresentation.Locators.SearchComponent.SearchPageLocators;
import com.iat.storepresentation.Locators.ShopHome.HomePageLocators;
import com.iat.storepresentation.Navigations.AbstractPage;
import com.iat.storepresentation.Navigations.SearchComponent.SearchPageNavigation;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.03.14
 * Time: 13:27
 * To change this template use File | Settings | File Templates.
 */
public class AZPageNavigations extends AbstractPage{

    private HomePageLocators locators_home = new HomePageLocators();
    private AZPageLocators locators_AZ = new AZPageLocators();
    private SearchPageLocators locators_search = new SearchPageLocators();
    private RetailerPageLocators locators_retailer = new RetailerPageLocators();
    private SearchPageNavigation searchNavi = new SearchPageNavigation(executor);


    public AZPageNavigations(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (Rd-1164) - content of AZ page
    public void openAZPage(){
        executor.return_driver().navigate().refresh();
        executor.click(locators_home.azPageHome);
    }

    public void checkContentOfAZpage() throws InterruptedException {
        //TODO
        Thread.sleep(8000);
        //assertTrue("Popular stores carousel is no visible", executor.is_element_present(locators_AZ.popularStorriesContainer));
        if(executor.getWebElementsList(locators_AZ.favouriteStoresBasicRetailerlocator).size()>4){
            assertTrue("Left arrow is no visible", executor.is_element_present(locators_AZ.favouritesArrowLeft));
            assertTrue("Right arrow is no visible", executor.is_element_present(locators_AZ.favouritesArrowRight));
        }
        assertTrue("Stores A-Z title is no visible", executor.is_element_present(locators_AZ.storesAZHeader));
        assertTrue("Searcher text field is no visible", executor.is_element_present(locators_AZ.searcherTextfield));
        assertTrue("Search button is no visible", executor.is_element_present(locators_AZ.searcherButton));
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
        List<WebElement> elements = executor.getWebElementsList(locators_AZ.basicAlphabetLocator);
        for (int i = 0; i < elements.size(); i++) {
            assertEquals("Letter " + alphabet[i] + " is incorrect", alphabet[i], executor.getText(elements.get(i)));
        }
        //pagination elements
        assertTrue("Top pagination girder is not visible", executor.is_element_present(locators_search.mainPaginationGirder));
        assertTrue("Bottom Pagination Component is no visible", executor.is_element_present(locators_search.mainBottomPaginationComponent));
        assertTrue("Department component is no visible", executor.is_element_present(locators_AZ.depeartmentsComponent));
    }

    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (Rd-1164) - working of search
    public void checkSearcherVisibility() {
        assertTrue("Searcher text field is no visible", executor.is_element_present(locators_AZ.searcherTextfield));
        assertTrue("Search button is no visible", executor.is_element_present(locators_AZ.searcherButton));
    }

    public void enterRetailerNameIntoSearcher(String retailer) {
        executor.send_keys(locators_AZ.searcherTextfield, retailer);
    }

    public void clickSearchButton() throws InterruptedException {
        executor.click(locators_AZ.searcherButton);
        Thread.sleep(4000);
    }

    public void checkRetailerResult(String retailer) throws InterruptedException {
        executor.moveMouseToWebElement(locators_AZ.basicAlphabetRetailerLocator);
        executor.click(locators_AZ.retailerCardRetailerPageButtonAlphabeticOnly);
        assertTrue("Retailer was not found", executor.return_driver().getCurrentUrl().contains(retailer.replace(" ","-").toLowerCase()));
        //assertEquals("Retailers names are not the same after search", retailer, executor.get_element(locators_AZ.basicAlphabetRetailerNameLocator).getText());
    }
    
    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (Rd-1164) - alphabetic search
    public void checkAllRetailersForGivenLetters() throws InterruptedException {
        //list all letters
        List<WebElement> letters = executor.getWebElementsList(locators_AZ.basicAlphabetLocator);
        for (int i = 0; i < letters.size() - 1; i++) {
            //click chosen letter
            executor.click(letters.get(i));
            if(letters.get(i).getText().equals("X")){continue;} //TODO - there is no any retailer on X that why this line is introduced
            //list retailers
            Thread.sleep(500);
            List<WebElement> retailers = executor.getWebElementsList(locators_AZ.basicAlphabetRetailerLocator);
            if(retailers.size()==0){
                continue;
            }
            List<WebElement> retailersName;
            for (int j=0; j<retailers.size(); j++) {
                executor.moveMouseToWebElement(retailers.get(j));
                Thread.sleep(1000);
                retailersName = executor.getWebElementsList(locators_AZ.basicAlphabetRetailerNameLocator);
                if(retailersName.get(j).getText().length()>0){
                    assertEquals("First letter of retailer name is incorrect - retailer name - " + retailersName.get(j).getText() + ", letter - " + executor.getText(letters.get(i)), executor.getText(letters.get(i)).charAt(0), retailersName.get(j).getText().toUpperCase().charAt(0));
                }
                if(j>1){
                    break;
                }
            }
        }
    }
    
    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (Rd-1164) - merchant card
    public void pointChosenRetailer() throws InterruptedException {
        Thread.sleep(2000);
        List<WebElement> retailers = executor.getWebElementsList(locators_AZ.basicAlphabetRetailerLocator);
        List<WebElement>  linksToRetailersPages = executor.getWebElementsList(locators_AZ.retailerCardRetailerPageButtonAlphabeticOnly);
        List<WebElement> linksToTransitionPages = executor.getWebElementsList(locators_AZ.retailerCardTransitionPAgeButtonAlphabeticOnly);
        for(int i=0; i<retailers.size(); i++){
            executor.moveMouseToWebElement(retailers.get(i));
            assertTrue("Retailer card link button is invisible", executor.is_element_present(linksToRetailersPages.get(i)));
            assertTrue("Transition card link is invisible", executor.is_element_present(linksToTransitionPages.get(i)));
            if(i>0){
                break;
            }
        }
    }

    public void goToRetailerPage() throws InterruptedException {
        executor.moveMouseToWebElement(executor.get_element(locators_AZ.retailerCardRetailerCardContainerAlphabeticOnly));
        String retailerName = executor.getText(locators_AZ.basicAlphabetRetailerNameLocator);
        executor.click(locators_AZ.retailerCardRetailerPageButtonAlphabeticOnly);
        Thread.sleep(2000);
        assertTrue("Page URL is incorrect", executor.return_driver().getCurrentUrl().contains("retailer") && executor.return_driver().getCurrentUrl().contains(retailerName.replace(" ", "-").toLowerCase()));
        executor.return_driver().navigate().back();
    }

    public void goToTransitionPage(){
        executor.moveMouseToWebElement(executor.get_element(locators_AZ.retailerCardRetailerCardContainerAlphabeticOnly));

        executor.click(locators_AZ.retailerCardTransitionPAgeButtonAlphabeticOnly);
        executor.handleMultipleWindows("Sending to retailer");
        assertTrue("Transition page was not opened", executor.return_driver().getCurrentUrl().contains("transition"));
    }

    // ETAILER PAGE ON WLS - add navigation and A_Z page logic (Rd-1164) - favourite logic
    public void checkFavouriteStoreSectionVisibility(boolean expectation) throws InterruptedException {
        if(expectation){
            Thread.sleep(8000);
            assertTrue("Favourite section is no visible",executor.is_element_present(locators_AZ.favouriteStoresContainer));
        }else if(!expectation){
            assertFalse("Favourite section is visible, but should not", executor.is_element_present(locators_AZ.favouriteStoresContainer));
        }
    }

    String multiplier;
    String multiplierText;
    String retailerId;
    public void goToChosenRetailerPageAndStoreCardElementsValues() throws InterruptedException {
        int random = executor.return_random_value(19);
        List<WebElement> retailerelement;
        //Get ep multiplier text like x4
        retailerelement = executor.getWebElementsList(locators_AZ.basicAlphabetRetailerMultiplier);
        multiplier = retailerelement.get(random).getText();
        //Get ep multiplier text
        retailerelement = executor.getWebElementsList(locators_AZ.basicAlphabetRetailerMultiplierText);
        multiplierText = retailerelement.get(random).getText();
        //Position mouse on wanted retailer card
        retailerelement = executor.getWebElementsList(locators_AZ.basicAlphabetRetailerLocator);
        executor.moveMouseToWebElement(retailerelement.get(random));
        //Go to chosen retailer page
        retailerelement = executor.getWebElementsList(locators_AZ.retailerCardRetailerPageButtonAlphabeticOnly);
        retailerId = retailerelement.get(random).getAttribute("href");
        executor.click(retailerelement.get(random));
    }

    public void clickAddOrRemoveToFavouritesButton(boolean addOrRemove) throws InterruptedException {
        Thread.sleep(2000);
        if(addOrRemove)
        {
            executor.click(locators_retailer.addToFavouritesPlusButton);
        }else if(!addOrRemove){
            executor.click(locators_retailer.addToFavouritesOkButton);
        }
        executor.return_driver().navigate().back();
    }

    public void checkFavouriteBlockContent() throws InterruptedException {
        executor.click(locators_AZ.favouriteStoresContainer);
        Thread.sleep(500);
        List<WebElement> favouriteRetailer = executor.getWebElementsList(locators_AZ.favouriteStoresBasicRetailerlocator);
        assertEquals("Multiplier values are not the same", executor.getText(locators_AZ.favouriteStoresBasicRetailerMultipler), multiplier);
        assertEquals("Multiplier texts are not the same", executor.getText(locators_AZ.favouriteStoresBasicRetailerMultiplerText), multiplierText);
        assertTrue("Retailers number in favourites is no correct", favouriteRetailer.size()==1);
    }

    public void checkUserInterestsTableForChanges() throws SQLException {
        JDBC jdbc = new JDBC("points_manager");
        assertTrue("Favourite information was wrote in db incorrectly", retailerId.contains(jdbc.return_proper_db_value("SELECT interest FROM points_manager.UserInterests WHERE userId = '" + jdbc.return_proper_db_value(jdbc.return_proper_query("returnId", "emailwybitnietestowy@gmail.com"), 1) + "'", 1)));
        jdbc.close();
    }

    public void removeStoreFromFavourites() throws InterruptedException {
        //Position mouse on wanted retailer card
        executor.moveMouseToWebElement(locators_AZ.favouriteStoresBasicRetailerlocator);
        //Go to chosen retailer page
        executor.click(locators_AZ.retailerCardRetailerPageButton);
        //Remove store from favourites
        clickAddOrRemoveToFavouritesButton(false);
    }

    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - favourite logic several products
    public void addSeveralRetailersToFavourites() throws InterruptedException {
        List<WebElement> retailers;
        for(int i=0; i<8; i++){
            retailers = executor.getWebElementsList(locators_AZ.basicAlphabetRetailerLocator);
            int random = executor.return_random_value(retailers.size());
            executor.moveMouseToWebElement(retailers.get(random));
            retailers = executor.getWebElementsList(locators_AZ.retailerCardRetailerPageButtonAlphabeticOnly);
            executor.click(retailers.get(random));
            Thread.sleep(2000);
            if(!executor.is_element_present(locators_retailer.addToFavouritesPlusButton)){
                i--;
                executor.return_driver().navigate().back();
                continue;
            }
            clickAddOrRemoveToFavouritesButton(true);
        }
    }

    public void checkContentAndBehaviourOfCarousel(){
        executor.click(locators_AZ.favouriteStoresContainer);
        assertTrue("Right arrow is no visible", executor.is_element_present(locators_AZ.favouritesArrowRight));
        assertTrue("left arrow is no visible", executor.is_element_present(locators_AZ.favouritesArrowLeft));
    }

    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - recently visited stores for not logged user
    public void checkIfRecentlyVisitedComponentIsEnabled(boolean isEnabled) throws InterruptedException {
        if(isEnabled){
            executor.waitUntilElementAppears(locators_AZ.recentlyVisitedStoresContainer);
            assertTrue("Recently visited component is no visible", executor.is_element_present(locators_AZ.recentlyVisitedStoresContainer));
        }else if(!isEnabled){
            assertFalse("Recently visited component is visible but should not", executor.is_element_present(locators_AZ.recentlyVisitedStoresContainer));
        }
    }
    
    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - recently visited stores for logged user
    String retailer_name;
    public void goToRecentlyVisitedComponent(){
        executor.click(locators_AZ.recentlyVisitedStoresContainer);
    }

    public void checkIfInsideRecentlyVisitedComponentIsAnyRetailer(){
        assertTrue("There is no retailers in RVS component", executor.is_element_present(locators_AZ.favouriteStoresBasicRetailerlocator));
    }

    public void openRetailerPageUsingRecentlyVisitedComponentContent(){
        executor.moveMouseToWebElement(locators_AZ.favouriteStoresBasicRetailerlocator);
        retailer_name = executor.getText(locators_AZ.basicFavouriteRetailerNameLocator);
        executor.click(locators_AZ.retailerCardRetailerPageButton);
        assertTrue("Retailer Page title is incorrect", executor.return_driver().getCurrentUrl().contains(retailer_name.replace(" ","-").toLowerCase()));
    }
    
    // RETAILER PAGE ON WLS - add navigation and A_Z page logic (RD-1164) - department filter
    public void checkVisibilityOfAllDepartments(){
        assertTrue("Categories component is not visible", executor.is_element_present(locators_AZ.depeartmentsComponent));

        String[] categories = {"Books","Fashion","Home & Garden","Health & Beauty","Baby & Child","Sports & Outdoors","Computer & Office","Music, Film & Gaming","Toys & Games","Electronics"};
        List <WebElement> categoriesUI = executor.getWebElementsList(locators_AZ.basicCategory);
        for(int i=0; i<categoriesUI.size(); i++){
            assertEquals("Category is no correct", executor.getText(categoriesUI.get(i)), categories[i]);
        }
    }

    int numberOfRetailersOnBeginning;
    List<WebElement> retailers;
    public void selectOneDepartment(){
        retailers =executor.getWebElementsList(locators_AZ.basicAlphabetRetailerLocator);
        numberOfRetailersOnBeginning = retailers.size();
        List<WebElement> categories = executor.getWebElementsList(locators_AZ.basicCategory);
        for(WebElement category : categories){
            if(executor.getText(category).equals("Fashion")){
               executor.click(category);
            }
        }
    }

    public void checkIfNumberOfRetailersWasDecreased(){
        retailers = executor.getWebElementsList(locators_AZ.basicAlphabetRetailerLocator);
        assertTrue("Number of Retailers was not decreased", retailers.size()<numberOfRetailersOnBeginning);
    }

    public void checkIfThereAreHiddenLetters(){
        List<WebElement> disabledLetters = executor.getWebElementsList(locators_AZ.basicAlphabetLocatorDisabled);
        assertTrue("Any letter is disabled but should be", disabledLetters.size()>0);
    }

    public void clickSeeAllButton() throws InterruptedException {
        executor.click(locators_AZ.seeAll);
        Thread.sleep(1000);
    }

    public void compareNumberOfDisplayedRetailerWithNumberOnBeggining(){
        retailers = executor.getWebElementsList(locators_AZ.basicAlphabetRetailerLocator);
        assertTrue("Number of locator is different then on beginning", retailers.size()==numberOfRetailersOnBeginning);
    }


}
