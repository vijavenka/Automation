package com.iat.ePoints.Navigations.Checkout;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Checkout.SelectedRewardsLocators;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Locators.Spend.BrowseRewardsLocators;
import com.iat.ePoints.Locators.Spend.ManageItemPageLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.*;

public class SelectedRewardsNavigation extends AbstractPage {

    protected SelectedRewardsLocators locators = new SelectedRewardsLocators();
    HeaderLocators locators_header = new HeaderLocators();
    BrowseRewardsLocators locators_browse = new BrowseRewardsLocators();
    ManageItemPageLocators locator_item = new ManageItemPageLocators();

    public SelectedRewardsNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public Boolean checkIfProperPage() {
        if ((executor.getText(locators.pageTitle).equals("Selected rewards")))
            return true;
        else
            return false;
    }

    // Scenario: User opens selected rewards page //////////////////////////////////////////////////////////////////////
    public void addProductToBasket() throws InterruptedException {
        executor.click(locators_header.spendPoints);
        executor.click(locators_browse.basicDepartmentName);
        Thread.sleep(6000);
        List<WebElement> addToBasketButtons = executor.getWebElementsList(locators_browse.itemsCardAddToBasketButton);
        executor.click(addToBasketButtons.get(executor.return_random_value(39) + 1));
    }

    public void openBasketView() throws InterruptedException {
        Thread.sleep(1000);
        executor.click(locators_header.openBasket);
    }

    public void goToSelectRewardView() {
        executor.click(locator_item.basketSmallWindowViewSelectedRewardsButton);
    }

    public void checkIfCheckouPageHasproperTitle() {
        assertEquals("Basket Page title is incorrect", executor.return_driver().getTitle(), "Checkout | epoints");
    }

    public void checkIfSelectRewardsModuleHasProperTitle() {
        assertEquals("Select reward title is incorrect", "Selected rewards", executor.getText(locators.pageTitle));
    }

    public void clickBackToRewardsButton() {
        executor.click(locators.backToRewards_btn);
    }

    public void checkIfUserWasRedirectedToOrderPage() {
        assertEquals("Order page title is incorrect", executor.return_driver().getTitle(), "Find your epoints reward | epoints");
    }

    // Scenario: Remove single item/remove all items ///////////////////////////////////////////////////////////////////
    public void removeOneItemFromBasket() {
        executor.click(locators.remove_btn);
        assertTrue("Question popup is no visible", executor.is_element_present(locators.popupQuestionWindow));
        executor.click(locators.delete_btn);
    }

    public void checkIfInBasketIsOneItem() {
        assertTrue("In basket is more than one item", executor.getWebElementsList(locators.remove_btn).size() == 1);
    }

    public void useRemoveAllItemsReference() {
        executor.click(locators.removeAll_btn);
        assertTrue("Question popup is no visible", executor.is_element_present(locators.popupQuestionWindow));
        executor.click(locators.delete_btn);
    }

    public void checkIfInBasketIsNoItems() {
        assertTrue("No Selected rewards comunicate is no visible", executor.is_element_present(locators.noSelectedRewardsCominicate));
    }

    // Scenario: Increase and decrease number of products/check total epoints needed ///////////////////////////////////
    public void increaseQuantityOfAllProductByOne() {
        List<WebElement> plusButtons = executor.getWebElementsList(locators.plus_btn);
        for (WebElement plus : plusButtons) {
            executor.click(plus);
        }
    }

    public void checkCorrectnessOfTotalEpointsNeededValue() {
        List<WebElement> itemspoints = executor.getWebElementsList(locators.pointsOneItem);
        int totalPointsCounted = 0;
        for (WebElement point : itemspoints) {
            totalPointsCounted = totalPointsCounted + Integer.parseInt(executor.getText(point).replace(",", ""));
        }
        assertTrue("Total epoints needed value is incorrect there is " + Integer.parseInt(executor.getText(locators.totalPoints).replace(",", "")) + " but should be " + totalPointsCounted, Integer.parseInt(executor.getText(locators.totalPoints).replace(",", "")) == totalPointsCounted * 2);
    }

    public void decreaseQuantityOfFirstProductByOne() {
        List<WebElement> plusButtons = executor.getWebElementsList(locators.minus_btn);
        for (WebElement plus : plusButtons) {
            executor.click(plus);
        }
    }

    public void checkDisplayedQuantityOfEachProduct() {
        List<WebElement> quantity = executor.getWebElementsList(locators.pointsOneItem);
        for (WebElement quant : quantity) {
            assertTrue("Product quantity is incorrect", Integer.parseInt(executor.getValue(locators.itemQuantity)) == 2);
        }
    }

    public void checkDisplayedQuantityOfEachProduct2() {
        List<WebElement> quantity = executor.getWebElementsList(locators.pointsOneItem);
        for (WebElement quant : quantity) {
            assertTrue("Product quantity is incorrect", Integer.parseInt(executor.getValue(locators.itemQuantity)) == 1);
        }
    }

    public void clikcOrderRewardButton() {
        executor.click(locators.order_btn);
    }
}