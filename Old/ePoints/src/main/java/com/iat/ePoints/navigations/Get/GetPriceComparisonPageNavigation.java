package com.iat.ePoints.Navigations.Get;

import com.iat.ePoints.EnvironmentVariables;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Get.GetLandingPageLocators;
import com.iat.ePoints.Locators.Get.GetPriceComparisonPageLocators;
import com.iat.ePoints.Locators.Get.GetShopPageLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 29.01.14
 * Time: 09:46
 * To change this template use File | Settings | File Templates.
 */
public class GetPriceComparisonPageNavigation extends AbstractPage{

    private GetLandingPageLocators locators_getLanding = new GetLandingPageLocators();
    private EnvironmentVariables envVariables = new EnvironmentVariables();
    private GetShopPageLocators locators_shop = new GetShopPageLocators();
    private GetPriceComparisonPageLocators locatorsComparison = new GetPriceComparisonPageLocators();

    public GetPriceComparisonPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // EPOINTS - extend top level navigation to include price comparison link (RD-1366) ////////////////////////////////
    public void checkIfPriceComparisonLinkIsAvailable(){
        assertTrue("Price comparison link is no visible", executor.is_element_present(locators_getLanding.priceComparisonSubmenu));
    }

    public void clickPriceComparisonLink(){
        executor.click(locators_getLanding.priceComparisonSubmenu);
    }

    public void checkPriceComparisonPageTitle(){
        assertEquals("Price comparison title is incorrect", "Price comparison | epoints", executor.return_driver().getTitle());
    }

    public void checkCorrectnessOfURL(){
        assertTrue("Price comparison URL is incorrect", executor.return_driver().getCurrentUrl().contains("/price-comparison"));
    }

    //EPOINTS - add department level navigation and search box (including department filter) to all shop pages (RD-1144) check if /product does not contains navigation module
    public void checkIfDepartmentComponentIsInvisible(){
        assertFalse("Department component is visible but should not be", executor.is_element_present(locators_shop.searchDepartmentComponent));
    }

    // EPOINTS - create new landing page for price comparison area (products) (Rd-1143) - check content of comparison page
    public void checkVisibilityOfBrowser() throws InterruptedException {
        Thread.sleep(5000);
        assertTrue("Search text field is invisible",executor.is_element_present(locatorsComparison.searchTeaxtField));
        assertTrue("Search button is invisible",executor.is_element_present(locatorsComparison.searchButton));
        executor.click(locatorsComparison.searchDepartmentDDLDownArrow);
        Thread.sleep(2000);
        assertTrue("Search departments are invisible", executor.is_element_present(locatorsComparison.searchDepartmentDDL));
        List<WebElement> departments = executor.getWebElementsList(locatorsComparison.searchDepartmentDDLOptions);
        for(int i=0; i<departments.size(); i++){
            assertEquals("Department name is incorrect", executor.getText(departments.get(i)), "in " + locatorsComparison.departments[i]);
        }
    }

    public void checkVisibilityOfDepartmentsLinks(){
        for(int i=0; i<locatorsComparison.departments.length; i++){
            assertTrue("Department "+locatorsComparison.departments[i]+" is invisible", executor.is_element_present(locatorsComparison.returnCategoryLocator(i)));
        }
    }

    // EPOINTS - create new landing page for price comparison area (products) (Rd-1143) - check working of search
    public void typeGivenWordintoSearch(String word){
        executor.send_keys(locatorsComparison.searchTeaxtField,word);
    }

    public void clicSearchButton(){
        executor.click(locatorsComparison.searchButton);
    }

    public void checkIfUserWasRedirectedToShopPage(){
        assertEquals("User was no redirected to shop page", "Price comparison search | epoints", executor.return_driver().getTitle());
    }

    // EPOINTS - create new landing page for price comparison area (products) (Rd-1143) - check working of search
    public void forAllDepartmentsCheckVisibilityOfCategoryCardAndLinks() throws InterruptedException {
        List<WebElement> departments = executor.getWebElementsList(locatorsComparison.basicDepartmentNotActive);
        boolean categoryVisibility=false;
        for(WebElement department : departments){
            executor.click(department);
            Thread.sleep(1000);
            //check card visibility
            assertTrue("Category card is invisible for" + executor.getText(department), executor.is_element_present(locatorsComparison.basicDepartmentCardActive));
            //check link visibility
            if(executor.is_element_present(locatorsComparison.basicDepartmentCategoryLargeActive) || executor.is_element_present(locatorsComparison.basicDepartmentCategorySmallActive)){
                categoryVisibility = true;
            }
            assertTrue("Any category link is no visible", categoryVisibility);
            categoryVisibility=false;
            //close card
            executor.click(locatorsComparison.basicDepartmentCardClose);
            Thread.sleep(500);
        }
    }

}
