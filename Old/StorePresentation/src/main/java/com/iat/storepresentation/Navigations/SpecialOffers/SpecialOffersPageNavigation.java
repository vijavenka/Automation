package com.iat.storepresentation.Navigations.SpecialOffers;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.SearchComponent.SearchPageLocators;
import com.iat.storepresentation.Locators.ShopHome.HomePageLocators;
import com.iat.storepresentation.Locators.SpecialOffers.SpecialOffersPageLocators;
import com.iat.storepresentation.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.03.14
 * Time: 09:42
 * To change this template use File | Settings | File Templates.
 */
public class SpecialOffersPageNavigation extends AbstractPage{

    private HomePageLocators locators_home = new HomePageLocators();
    private SpecialOffersPageLocators locators_specialOffers = new SpecialOffersPageLocators();
    private SearchPageLocators locators_search = new SearchPageLocators();
    //private TransitionPageLocators locators_transition = new TransitionPageLocators();
    //private EnvironmentVariables envVariables = new EnvironmentVariables();


    public SpecialOffersPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Special Offers pages (RD-2105) - check content of special offers page
    public void openSpecialOffersPage() throws InterruptedException {
        executor.click(locators_home.specialOffers);
        Thread.sleep(4000);
    }

    public void checkVisibilityOfPageHeader(){
        assertTrue("Special offers page header is invisible", executor.is_element_present(locators_specialOffers.headerSpecialOffersPage));
    }

    public void checkVisibilityOfSpecialOffersFilterModule(){
        assertTrue("Filter module is no visible", executor.is_element_present(locators_specialOffers.filterContainerSpecialOffersPage));
        assertTrue("Percentage saving DDL is no visible", executor.is_element_present(locators_specialOffers.percentageSavingDDLSpecialOffersPage));
        assertTrue("Department DDL is no visible", executor.is_element_present(locators_specialOffers.departmentDDlSpecialOffersPage));
        assertTrue("Show me button is no visible", executor.is_element_present(locators_specialOffers.showMeButtonSpecialOffersPage));
    }

    public void checkVisibilityOfSpecialOffersCardAndContent(){
        // only special offers names are mandatory
        List<WebElement> offersNames = executor.getWebElementsList(locators_specialOffers.cardTitleSpecialOffersPage);
        List<WebElement> offersViewButtons = executor.getWebElementsList(locators_specialOffers.cardViewButtonSpecialOffersPage);
        System.out.println(offersNames.size());
        System.out.println(offersViewButtons.size());
        assertTrue("Element missing", offersNames.size()==offersViewButtons.size());
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Special Offers pages (RD-2105) - check working of view special offer button
    public void clickOneChosenViewSpecialOfferButton() throws InterruptedException {
        List<WebElement> offersViewButtons = executor.getWebElementsList(locators_specialOffers.cardViewButtonSpecialOffersPage);
        executor.click(offersViewButtons.get(executor.return_random_value(offersViewButtons.size())));
        Thread.sleep(4000);
    }

    public void checkIfOfferSearchPageWasOpened(){
        assertTrue("Special offer page was not replaced with search page", executor.return_driver().getCurrentUrl().contains("search"));
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Special Offers pages (RD-2105) - check working of percentage saving filter, department filter and price range
    int percentageUpRange;
    int percentageLowRange;
    public void chosePercentageSavingRange(){
        int random = executor.return_random_value(3);
        System.out.println(random);
        if(random==0){
            percentageLowRange = 76;
            percentageUpRange = 100;
        }else if(random==1){
            percentageLowRange = 51;
            percentageUpRange = 75;
        }else if(random==2){
            percentageLowRange = 25;
            percentageUpRange = 50;
        }
        executor.selectOptionByValue(locators_specialOffers.percentageSavingDDLSpecialOffersPage, locators_specialOffers.savigOptions[random]);
    }

    public void choseDepartmentAndCategory() throws InterruptedException {
        // department selection
        int random = executor.return_random_value(locators_specialOffers.departmentsSpecialOffersPage.length);
        executor.selectOptionByValue(locators_specialOffers.departmentDDlSpecialOffersPage, locators_specialOffers.departmentsSpecialOffersPage[random].replace("&", "and").replace(" ", "-").toLowerCase());
        Thread.sleep(4000);
        // category selection
        List<WebElement> categories = executor.getWebElementsList(locators_specialOffers.categorySpecialOffersPage);
        System.out.println("Chosen element " + random+ " available elements " + categories.size());
        random = executor.return_random_value(categories.size());
        System.out.println(random);
        executor.click(categories.get(random));
    }

    public void clickShowButtonAndCheckResults() throws InterruptedException {
        executor.click(locators_specialOffers.showMeButtonSpecialOffersPage);
        Thread.sleep(1000);
        if(executor.is_element_present(locators_home.basicProductContainer)){
            List<WebElement> savings = executor.getWebElementsList(locators_home.basicProductPercentageSaving);
            int saveLocal;
            for(WebElement save : savings){
                saveLocal = Integer.parseInt(executor.getText(save).replace("% OFF",""));
                assertTrue("Percentage saving is not in range " + percentageLowRange + " " + percentageUpRange + " found value " + saveLocal, saveLocal >= percentageLowRange && saveLocal <= percentageUpRange);
            }
        }
    }

}
