package com.iat.ePoints.Navigations.Spend;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Spend.ManageItemPageLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.*;

public class ManageItemPageNavigation extends AbstractPage {

    protected ManageItemPageLocators locators = new ManageItemPageLocators();

    public ManageItemPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public void goBack() {
        executor.click(locators.goBack);
    }


    public void moreItems() {
        executor.click(locators.plus_btn);
    }

    public void lessItems() {
        executor.click(locators.minus_btn);
    }

    public int getQuant() {
        return Integer.parseInt(executor.getValue(locators.itemsQuant));
    }

    public boolean checkDetails() {
        return (executor.is_element_present(locators.details_tab));
    }

    public boolean checkDescriptionTitle() {
        return (executor.is_element_present(locators.desc_tab));
    }

    public boolean checkDeliveryTitle() {
        return (executor.is_element_present(locators.delivery_tab));
    }

    public boolean checkPictureVisibility() {
        return (executor.is_element_present(locators.productPicture));
    }

    public boolean checkDescriptionContent() {
        return (executor.is_element_present(locators.desc_content));
    }

    public boolean checkDeliveryContent() {
        return (executor.is_element_present(locators.delivery_content));
    }

    public boolean checkSelectButton() {
        return (executor.is_element_present(locators.SelectReward_btn));
    }

    // scenario 2 from Manage the item page/////////////////////////////////////////////////////////////////////////////
    public void clickBackToRewardsReference(){
        executor.click(locators.backToRewards);
    }

    public void checkIfUserIsOnBrowseRewardsPage() {
        assertEquals("User was not redirected to browse rewards page", executor.return_driver().getTitle(), "Find your epoints reward | epoints");
    }

    // scenario: Select the reward /////////////////////////////////////////////////////////////////////////////////////
    public void checkSelectRewardButtonVisibility() {
        assertTrue("Select reward button is invisible", executor.is_element_present(locators.SelectReward_btn));
    }

    public void checkIfStartingProductQuantityIsProper(int Quantity) {
        assertTrue("Starting product quantity is incorrect (different than one)", Integer.parseInt(executor.getValue(locators.itemsQuant)) == Quantity);
    }

    public void checkIfBasketElementsAreInvisible() {
        assertFalse("Basket content product value is visible but should not", executor.is_element_present(locators.basketContentShortInformationValue));
        assertFalse("Basket content message is visible but should not", executor.is_element_present(locators.basketContentShortInformatinMessage));
        executor.click(locators.basketIcon);
        assertFalse("Small basket window is visible but should not", executor.is_element_present(locators.basketsWidgetRegion));
    }

    public void clickSelectRewardButton() {
        executor.click(locators.SelectReward_btn);
    }

    public void checkIfItemWasProperlyAddedToBasket(String whichIsAdded) {
        assertTrue("Basket content message is invisible", executor.is_element_present(locators.basketContentShortInformatinMessage));
        assertTrue("Basket content value is incorrect", executor.getText(locators.basketContentShortInformationValue).equals(whichIsAdded));
    }

    public void checkVisibilityOfSmallBasketWindow(){
        executor.click(locators.basketIcon);
        assertTrue(executor.is_element_present(locators.basketsWidgetRegion));
    }

    public void checkCorrectnessOfSmallBasketWindowContent(String itemName) throws InterruptedException {
        Thread.sleep(2000);//has to stay
        assertTrue("Product small image is invisible", executor.is_element_present(locators.basketSmallWindowPicture));
        assertTrue("View selected rewards button is invisible", executor.is_element_present(locators.basketSmallWindowViewSelectedRewardsButton));
        assertEquals("Product name is incorrect", executor.getText(locators.basketSmallWindowProductNameAndLink), itemName);
        assertEquals("Quantity information is incorrect", executor.getText(locators.basketSmallWindowQuantity), "Quantity 2");
    }

}