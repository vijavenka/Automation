package com.iat.ePoints.Navigations.Checkout;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Checkout.DeliveryDetailsLocators;
import com.iat.ePoints.Locators.Checkout.OrderSummaryLocators;
import com.iat.ePoints.Locators.Checkout.SelectedRewardsLocators;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Locators.Spend.ManageItemPageLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 05.12.13
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */
public class OrderSummaryNavigation extends AbstractPage {

    protected OrderSummaryLocators locators_summary = new OrderSummaryLocators();
    protected DeliveryDetailsLocators locators_delivery = new DeliveryDetailsLocators();
    protected SelectedRewardsLocators locators_rewards = new SelectedRewardsLocators();
    protected ManageItemPageLocators locators_itempage = new ManageItemPageLocators();
    protected HeaderLocators locator_header = new HeaderLocators();

    public OrderSummaryNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    //Scenario: User opens Order summary page///////////////////////////////////////////////////////////////////////////
    public void checkContentOfOrderSummaryPage() {
        assertTrue("Page title is invisible", executor.is_element_present(locators_summary.orderSummaryTitle));
        assertTrue("Your selection form is invisible", executor.is_element_present(locators_summary.yourSelectionSection));
        assertTrue("Delivery details form is invisible", executor.is_element_present(locators_summary.deliveryDetailssection));
        assertTrue("Place order button is invisible", executor.is_element_present(locators_summary.placeOrderButton));
    }

    public void clickBackButton() {
        executor.click(locators_summary.backButton);
    }

    // Scenario:  Check working of edit buttons in order summary////////////////////////////////////////////////////////
    public void clickEditYourSelectionButton() {
        executor.click(locators_summary.editYourSelectionSection);
    }

    public void returnToOrderSummaryFromSelectedRewards() throws InterruptedException {
        executor.click(locators_rewards.order_btn);
        Thread.sleep(2000);//has to stay
        executor.click(locators_delivery.next_btn);
    }

    public void clickEditDeliveryDetailsButton() {
        executor.click(locators_summary.editDeliveryDetailsSection);
    }

    // Scenario: Check details correctness in order summary section/////////////////////////////////////////////////////
    public void clearBasketIfNeeded() throws InterruptedException {
        //go to basket and delete all items. This function was written because in automated tests at this point in basket
        //were many products and there was no possibility to check correctness of added products and content of basket
        executor.click(locator_header.spendPoints);
        Thread.sleep(4000);//has to stay
        if (executor.is_element_present(locators_itempage.basketContentShortInformatinMessage)) {
            executor.click(locators_itempage.basketContentShortInformatinMessage);
            executor.click(locators_itempage.basketSmallWindowViewSelectedRewardsButton);
            executor.click(locators_rewards.removeAll_btn);
            executor.click(locators_rewards.delete_btn);
        } else {
        }
    }

    public void checkIfUserContactDataWasCopiedProperly(String Name, String LastName, String HouseNumber, String Street, String City, String County, String Country, String PostCode) {
        assertEquals("Name and last name is no correct", executor.getText(locators_summary.summaryFirstNameLastName), Name + " " + LastName);
        assertEquals("Street name and House name is no correct", executor.getText(locators_summary.summaryStreetHouse), HouseNumber + " " + Street);
        assertEquals("City name is no correct", executor.getText(locators_summary.summaryTown), City);
        assertEquals("County name is incorrect", executor.getText(locators_summary.summaryCounty), County);
        assertEquals("Country name is incorrect", executor.getText(locators_summary.summaryCountry), Country);
        assertEquals("Postcode is incorrect", executor.getText(locators_summary.summaryPostcode), PostCode);
    }

    String productName;
    public void getProductName() throws InterruptedException {
        Thread.sleep(2000);
        this.productName = executor.getText(locators_itempage.basketSmallWindowProductNameAndLink);
    }

    public void compareSummaryProductWithOrdered() {
        assertEquals("Ordered product and this in summary are not the same", productName, executor.getText(locators_summary.basicProductTitle));
    }

    //for complete scenarios
    public void clickPlaceOrderButton() {
        executor.click(locators_summary.placeOrderButton);
    }

}