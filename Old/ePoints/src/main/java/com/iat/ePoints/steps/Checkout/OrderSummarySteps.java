package com.iat.ePoints.Steps.Checkout;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Checkout.DeliveryDetailsNavigation;
import com.iat.ePoints.Navigations.Checkout.OrderSummaryNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 05.12.13
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */
public class OrderSummarySteps {

    private IExecutor executor;
    private DeliveryDetailsNavigation deliveryNavi;
    private OrderSummaryNavigation summaryNavi;

    public OrderSummarySteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    @Before
    public void set_up() {
        executor.start();
        deliveryNavi = new DeliveryDetailsNavigation(executor);
        summaryNavi = new OrderSummaryNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    //Scenario: User opens Order summary page///////////////////////////////////////////////////////////////////////////

    @Then("^User click next page in delivery page$")
    public void User_click_next_page_in_delivery_page() throws Throwable {
        deliveryNavi.clickNextButton();
    }

    @Then("^He is re-directed to Order summary page$")
    public void He_is_re_directed_to_Order_summary_page() throws Throwable {
        summaryNavi.checkContentOfOrderSummaryPage();
    }

    @When("^User click back to delivery details$")
    public void User_click_back_to_delivery_details() throws Throwable {
        summaryNavi.clickBackButton();
    }

    @Then("^He return to delivery details page$")
    public void He_return_to_delivery_details_page() throws Throwable {
        deliveryNavi.checkIfProperPage();
    }

    // Scenario:  Check working of edit buttons in order summary////////////////////////////////////////////////////////

    @When("^User click edit your selection button$")
    public void User_click_edit_your_selection_button(){
        summaryNavi.clickEditYourSelectionButton();
    }

    @When("^User click edit your delivery details$")
    public void User_click_edit_your_delivery_details() throws Throwable {
        summaryNavi.returnToOrderSummaryFromSelectedRewards();
        summaryNavi.clickEditDeliveryDetailsButton();
    }

    // Scenario: Check details correctness in order summary section/////////////////////////////////////////////////////
    @Given("^Basket has empty content$")
    public void Basket_is_empty() throws Throwable {
        summaryNavi.clearBasketIfNeeded();
    }

    @Then("^Product name will be stored to later compare$")
    public void Product_name_will_be_stored_to_later_compare() throws Throwable {
        summaryNavi.getProductName();
    }

    @Then("^Can see proper delivery details '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)'$")
    public void Can_see_proper_delivery_details(String Name, String LastName, String HouseNumber, String Street, String City, String County, String Country, String PostCode) throws Throwable {
        summaryNavi.checkIfUserContactDataWasCopiedProperly(Name, LastName, HouseNumber, Street, City, County, Country, PostCode);
    }

    @Then("^Can see correct product in order summary section$")
    public void Can_see_correct_product_in_order_summary_section() throws Throwable {
        summaryNavi.compareSummaryProductWithOrdered();
    }


}
