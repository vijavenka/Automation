package com.iat.ePoints.Navigations.Checkout;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Checkout.OrderSummaryCompleteLocators;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Locators.Spend.BrowseRewardsLocators;
import com.iat.ePoints.Locators.Spend.ManageItemPageLocators;
import com.iat.ePoints.Locators.Checkout.SelectedRewardsLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 03.12.13
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class AddToTheBasketNavigation extends AbstractPage {

    OrderSummaryCompleteLocators locators_order = new OrderSummaryCompleteLocators();
    HeaderLocators locators_header = new HeaderLocators();
    BrowseRewardsLocators locators_browse = new BrowseRewardsLocators();
    ManageItemPageLocators locator_item = new ManageItemPageLocators();
    SelectedRewardsLocators locators_selected = new SelectedRewardsLocators();

    public AddToTheBasketNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // Scenario: Add item to the Cart //////////////////////////////////////////////////////////////////////////////////
    String product_one;
    String product_two;
    boolean which = false;

    public void openSpendTab() {
        executor.click(locators_header.spendPoints);
    }

    public void openSelectedTopRewardCategory(){
        executor.click(locators_browse.bucketsTab);
        String Category = executor.getText(locators_browse.basicBucketBoxTitleLocator);
        executor.click(locators_browse.basicBucketBoxTitleLocator);
        assertEquals("Top Categories page title is incorrect", executor.return_driver().getTitle(), Category);
    }

    public void addToBasketSelectedProduct() {
        if (which) {
            executor.click(locators_browse.itemsCardAddToBasketButton);
            product_one = executor.getText(locators_browse.itemsCardTitles);
        } else if (!which) {
            executor.click(locators_browse.itemsCardAddToBasketButton2);
            product_two = executor.getText(locators_browse.itemsCardTitles);
        }
        which = true;
    }

    public void clickBackToAllRewardsLink() {
        executor.click(locators_browse.backToAllRewardsReference);
    }

    public void openSelectedRewardsByEpointsTotalCategory() {
        executor.click(locators_browse.rewardsByEpointsTab);
        executor.click(locators_browse.basicSearchRangeBoxLocator);
    }

    public void goToBasket() {
        executor.click(locators_header.openBasket);
        executor.click(locator_item.basketSmallWindowViewSelectedRewardsButton);
        assertEquals("Basket Page title is incorrect", executor.return_driver().getTitle(), "Checkout | epoints");
    }

    public void checkIfInBasketAreAddedBeforeProducts() {
        List<WebElement> productTitles = executor.getWebElementsList(locators_selected.itemsTitle_link);
        for (int i = 0; i < 2; i++) {
            boolean flag = false;
            for (WebElement title : productTitles) {
                if (i == 0) {
                    if (product_one.contains(executor.getText(title))) {
                        flag = true;
                    }
                } else if (i == 1) {
                    if (product_two.contains(executor.getText(title))) {
                        flag = true;
                    }
                }
            }
            assertTrue("Added before product " + product_one + " was not found in the basket " + i, flag);
        }
    }

    // Scenarious: Remove single item/remove all items and Increase and decrease number of products/check total epoints needed
    public void addToBasketTwoProductsAndGoToBasket() throws InterruptedException {
        executor.click(locators_header.spendPoints);
        executor.click(locators_browse.basicDepartmentName);
        Thread.sleep(6000);
        List<WebElement> products = executor.getWebElementsList(locators_browse.itemsCardAddToBasketButton);
        executor.click(products.get(1));
        Thread.sleep(2000);
        executor.click(products.get(2));
        executor.click(locators_header.openBasket);
        executor.click(locator_item.basketSmallWindowViewSelectedRewardsButton);
        assertEquals("Basket Page title is incorrect", executor.return_driver().getTitle(), "Checkout | epoints");
    }

}