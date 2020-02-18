package com.iat.adminportal.page_navigations.redemption_manager;

import com.iat.adminportal.EnvironmentVariables;
import com.iat.adminportal.locators.redemption_manager.AdminPortalRedemptionManagerRedemptionItemsPageLocators;
import com.iat.adminportal.page_navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 19.03.14
 * Time: 13:25
 * To change this template use File | Settings | File Templates.
 */
public class AdminPortalRedemptionItemsPageNavigation extends AbstractPage {

    private AdminPortalRedemptionManagerRedemptionItemsPageLocators locators_redemptionItems = new AdminPortalRedemptionManagerRedemptionItemsPageLocators();
    private EnvironmentVariables envVariables = new EnvironmentVariables();

    public AdminPortalRedemptionItemsPageNavigation() {
        super("");
    }

    public void open() {
        super.open();
    }

    // REDEMPTION MANAGER - add additional search input boxes to allow more granular searches (RD-2567).
    public void openRedemptionItemsPage() {
        executor.return_driver().get(envVariables.getBaseUrl() + "/redeems?bucket");
    }

    public void checkVisibilityOfSearchElements() {
        assertTrue("Title search is no visible", executor.is_element_present(locators_redemptionItems.titleSearchRedemptionItemsPage));
        assertTrue("Category search is no visible", executor.is_element_present(locators_redemptionItems.categorySearchRedemptionItemsPage));
        assertTrue("Description search is no visible", executor.is_element_present(locators_redemptionItems.descriptionSearchRedemptionItemsPage));
        assertTrue("Type search is no visible", executor.is_element_present(locators_redemptionItems.typeSearchRedemptionItemsPage));
        assertTrue("Merchant search is no visible", executor.is_element_present(locators_redemptionItems.merchantSearchRedemptionItemsPage));
        assertTrue("Points search is no visible", executor.is_element_present(locators_redemptionItems.epointsSearchRedemptionItemsPage));
        assertTrue("Clear button is no visible", executor.is_element_present(locators_redemptionItems.clearButtonRedemptionItemsPage));
        assertTrue("Search button is no visible", executor.is_element_present(locators_redemptionItems.searchButtonRedemptionItemsPage));
    }

    public void checkContentOfredemptionItemsTable() {
        assertTrue("Title (table) is no visible", executor.is_element_present(locators_redemptionItems.titleColumnBasicRedemptionItemsPage));
        assertTrue("Points (table) is no visible", executor.is_element_present(locators_redemptionItems.pointsColumnBasicRedemptionItemsPage));
        assertTrue("Price (table) is no visible", executor.is_element_present(locators_redemptionItems.priceColumnBasicRedemptionItemsPage));
        assertTrue("Merchant (table) is no visible", executor.is_element_present(locators_redemptionItems.merchantColumnBasicRedemptionItemsPage));
        assertTrue("Category (table) is no visible", executor.is_element_present(locators_redemptionItems.categoryColumnBasicRedemptionItemsPage));
        assertTrue("Brand (table) is no visible", executor.is_element_present(locators_redemptionItems.brandColumnBasicRedemptionItemsPage));
        assertTrue("Type (table) is no visible", executor.is_element_present(locators_redemptionItems.typeColumnBasicRedemptionItemsPage));
        assertTrue("Add button (table) is no visible", executor.is_element_present(locators_redemptionItems.addButtonColumnBasicRedemptionItemsPage));
    }

    // REDEMPTION MANAGER - add additional search input boxes to allow more granular searches (RD-2567) - searches.
    public void clearSearchResults() throws InterruptedException {
        executor.click(locators_redemptionItems.clearButtonRedemptionItemsPage);
        Thread.sleep(2000);
    }

    public void useTitleSearch(String title) {
        executor.send_keys(locators_redemptionItems.titleSearchRedemptionItemsPage, title);
    }

    public void clickSearchButton() throws InterruptedException {
        executor.click(locators_redemptionItems.searchButtonRedemptionItemsPage);
        Thread.sleep(1000);
    }

    public void checkTitleSearchResults(String title) {
        List<WebElement> titles = executor.get_elements(locators_redemptionItems.titleColumnBasicRedemptionItemsPage);
        for (WebElement temp : titles) {
            assertTrue("Title " + executor.getText(temp) + " does not contains proper phrase " + title, executor.getText(temp).contains(title));
        }
    }

    public void useCategorySearch(String category) {
        executor.send_keys(locators_redemptionItems.categorySearchRedemptionItemsPage, category);
    }

    public void checkCategorySearchResults(String category) {
        List<WebElement> categories = executor.get_elements(locators_redemptionItems.categoryColumnBasicRedemptionItemsPage);
        for (WebElement temp : categories) {
            assertTrue("Category " + executor.getText(temp) + " does not contains proper phrase" + category, executor.getText(temp).contains(category));
        }
    }

    public void useDescriptionSearch(String description) {
        executor.send_keys(locators_redemptionItems.descriptionSearchRedemptionItemsPage, description);
    }

    public void checkDescriptionSearchResults(String description) {

    }

    public void useTypeSearch(String type) {
        executor.send_keys(locators_redemptionItems.typeSearchRedemptionItemsPage, type);
    }

    public void checkTypeSearchResults(String type) {
        List<WebElement> types = executor.get_elements(locators_redemptionItems.typeColumnBasicRedemptionItemsPage);
        for (WebElement temp : types) {
            assertTrue("Type " + executor.getText(temp) + " does not contains proper phrase" + type, executor.getText(temp).contains(type));
        }
    }

    public void useMerchantSearch(String brand) {
        executor.send_keys(locators_redemptionItems.merchantSearchRedemptionItemsPage, brand);
    }

    public void checkMerchantSearchResults(String brand) {
        List<WebElement> merchants = executor.get_elements(locators_redemptionItems.merchantColumnBasicRedemptionItemsPage);
        for (WebElement temp : merchants) {
            assertTrue("Merchant " + executor.getText(temp) + " does not contains proper phrase" + brand, executor.getText(temp).contains(brand));
        }
    }

    public void usePointsSearch(String pointsLow, String pointHigh) {
        executor.send_keys(locators_redemptionItems.epointsSearchRedemptionItemsPage, pointsLow + ":" + pointHigh);
    }

    public void checkPointsSearchResults(String pointsLow, String pointsHigh) {
        List<WebElement> points = executor.get_elements(locators_redemptionItems.pointsColumnBasicRedemptionItemsPage);
        for (WebElement temp : points) {
            assertTrue("Points  " + executor.getText(temp) + " are not in range " + pointsLow + " : " + pointsHigh, Integer.parseInt(executor.getText(temp)) >= Integer.parseInt(pointsLow) && Integer.parseInt(executor.getText(temp)) <= Integer.parseInt(pointsHigh));
        }
    }

    // REDEMPTION MANAGER - add additional search input boxes to allow more granular searches (RD-2567) - searches all.

}






























