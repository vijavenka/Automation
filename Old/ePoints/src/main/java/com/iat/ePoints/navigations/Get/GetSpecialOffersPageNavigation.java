package com.iat.ePoints.Navigations.Get;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Get.GetLandingPageLocators;
import com.iat.ePoints.Locators.Get.GetShopPageLocators;
import com.iat.ePoints.Locators.Get.GetSpecialOffersPageLocators;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 12.02.14
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class GetSpecialOffersPageNavigation extends AbstractPage{

    private GetSpecialOffersPageLocators locators_specialOffers = new GetSpecialOffersPageLocators();
    private HeaderLocators locators_header = new HeaderLocators();
    private GetLandingPageLocators locators_landingPage = new GetLandingPageLocators();
    //for products card Locators
    private GetShopPageLocators locators_shop = new GetShopPageLocators();

    public GetSpecialOffersPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // EPOINTS - add SPECIAL OFFERS interfaces to epoints (RD-1142) - check content of special offers page
    public void openSpecialOffersPage() throws InterruptedException {
        executor.click(locators_header.getPoints);
        executor.click(locators_landingPage.specialOffersSubmenu);
        Thread.sleep(5000);
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
        assertTrue("Element missing", offersNames.size()==offersViewButtons.size());
    }

    // EPOINTS - add SPECIAL OFFERS interfaces to epoints (RD-1142) - check working of view special offer button
    public void clickOneChosenViewSpecialOfferButton(){
        List<WebElement> offersViewButtons = executor.getWebElementsList(locators_specialOffers.cardViewButtonSpecialOffersPage);
        executor.click(offersViewButtons.get(executor.return_random_value(offersViewButtons.size())));
    }

    public void checkIfOfferSearchPageWasOpened(){
        assertFalse("Special offer page was not replaced with search page", executor.return_driver().getTitle().contains("/offers"));
    }

    // EPOINTS - add SPECIAL OFFERS interfaces to epoints (RD-1142) - check working of percentage saving filter and department filter
    int percentageUpRange;
    int percentageLowRange;
    public void chosePercentageSavingRange(){
        int random = executor.return_random_value(3);
        System.out.println(random);
        if(random==0){
            percentageLowRange = 75;
            percentageUpRange = 100;
        }else if(random==1){
            percentageLowRange = 50;
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
        System.out.println(random);
        executor.selectOptionByValue(locators_specialOffers.departmentDDlSpecialOffersPage, locators_specialOffers.departmentsSpecialOffersPage[random].replace("&", "and").replace(" ", "-").toLowerCase());
        Thread.sleep(5000);
    // category selection
        List<WebElement> categories = executor.getWebElementsList(locators_specialOffers.categorySpecialOffersPage);
        random = executor.return_random_value(categories.size());
        System.out.println(random);
        executor.click(categories.get(random));
    }

    public void clickShowButtonAndCheckResults() throws InterruptedException {
       executor.click(locators_specialOffers.showMeButtonSpecialOffersPage);
       Thread.sleep(4000);
       if(executor.is_element_present(locators_shop.basicProductContainer)){
           List<WebElement> savings = executor.getWebElementsList(locators_shop.basicProductPercentageSaving);
           int saveLocal;
           for(WebElement save : savings){
               saveLocal = Integer.parseInt(executor.getText(save).replace("% OFF",""));
               assertTrue("Percentage saving is not in range " + percentageLowRange + " " + percentageUpRange + " found value " + saveLocal, saveLocal >= percentageLowRange && saveLocal <= percentageUpRange);
           }
       }
    }

        public void checkWorkingOfPriceFilter() throws InterruptedException {
            if(executor.is_element_present(locators_shop.basicProductContainer)){
                executor.selectOptionByValue(locators_specialOffers.priceRangeFilter, "price|asc");
                Thread.sleep(5000);
                List<WebElement> prices = executor.getWebElementsList(locators_shop.basicProductNewPrice);
                int previous=0;
                int priceLocal;
                for(WebElement price : prices){
                    System.out.println(executor.getText(price));
                    if(executor.getText(price).replace("£ ","").substring(0,1).equals("0")){
                        priceLocal = Integer.parseInt(executor.getText(price).replace("£ ","").replace(",","").replace("0.",""));
                    }else{
                        priceLocal = Integer.parseInt(executor.getText(price).replace("£ ","").replace(",","").replace(".",""));
                    }
                    assertTrue("Wrong order no ascending", priceLocal >= previous);
                    previous = priceLocal;
                }

                executor.selectOptionByValue(locators_specialOffers.priceRangeFilter, "price|desc");
                executor.pressEnterOnChosenLocator(locators_specialOffers.priceRangeFilter);
                Thread.sleep(5000);
                prices = executor.getWebElementsList(locators_shop.basicProductNewPrice);
                previous=1000000000;
                for(WebElement price : prices){
                    System.out.println(" - "+executor.getText(price));
                    if(executor.getText(price).replace("£ ","").substring(0,1).equals("0")){
                        priceLocal = Integer.parseInt(executor.getText(price).replace("£ ","").replace(",","").replace("0.","").replace(".",""));
                    }else{
                        priceLocal = Integer.parseInt(executor.getText(price).replace("£ ","").replace(",","").replace(".",""));
                    }
                    assertTrue("Wrong order no descending", priceLocal <= previous);
                    previous = priceLocal;
                }
            }
        }
}

















