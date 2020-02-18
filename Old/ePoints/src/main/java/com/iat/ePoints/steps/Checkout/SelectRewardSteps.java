package com.iat.ePoints.Steps.Checkout;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Checkout.AddToTheBasketNavigation;
import com.iat.ePoints.Navigations.Checkout.SelectedRewardsNavigation;
import com.iat.ePoints.Navigations.Spend.BrowseRewardsNavigation;
import com.iat.ePoints.Navigations.Spend.ManageItemPageNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SelectRewardSteps {

    private IExecutor executor;
    private BrowseRewardsNavigation browsePage;
    private ManageItemPageNavigation itemPageNavi;
    private SelectedRewardsNavigation rewardsNavi;
    private AddToTheBasketNavigation addBasketNavi;
    private String recordID;
    private String recordStatus;

    public SelectRewardSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    @Before
    public void set_up() {
        executor.start();
        browsePage = new BrowseRewardsNavigation(executor);
        itemPageNavi = new ManageItemPageNavigation(executor);
        rewardsNavi = new SelectedRewardsNavigation(executor);
        addBasketNavi = new AddToTheBasketNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();

    }
    // Sceeanrio: User opens selected rewards page /////////////////////////////////////////////////////////////////////

    @Given("^There is at last one item added to the basket$")
    public void there_is_at_last_one_item_added_to_the_basket() throws Throwable {
        rewardsNavi.addProductToBasket();
    }

    @Given("^User is on the basket view$")
    public void User_is_on_the_basket_view() throws Throwable {
        rewardsNavi.openBasketView();
    }


    @When("^User clicks on Order rewards button$")
    public void User_clicks_on_Order_rewards_button() throws Throwable {
        rewardsNavi.goToSelectRewardView();
    }

    @Then("^He is re-directed to the Selected rewards page$")
    public void He_is_re_directed_to_the_Selected_rewards_page() throws Throwable {
        rewardsNavi.checkIfCheckouPageHasproperTitle();
    }

    @When("^User click back to rewards page$")
    public void User_click_back_to_rewards_page() throws Throwable {
        rewardsNavi.clickBackToRewardsButton();
    }

    @Then("^He is re-directed to order rewards page$")
    public void He_is_re_directed_to_order_rewards_page() throws Throwable {
        rewardsNavi.checkIfUserWasRedirectedToOrderPage();
    }


    // Scenario: Select the reward /////////////////////////////////////////////////////////////////////////////////////

    @Given("^Add to basket option is available$")
    public void Add_to_basket_option_is_available() throws Throwable {
        itemPageNavi.checkSelectRewardButtonVisibility();
    }

    @Given("^There is '(\\d+)' of items chosen$")
    public void There_is_of_items_chosen(int Quantity) throws Throwable {
        itemPageNavi.checkIfStartingProductQuantityIsProper(Quantity);
    }

    @Given("^Basket is empty$")
    public void Basket_is_empty() throws Throwable {
        itemPageNavi.checkIfBasketElementsAreInvisible();
    }

    @When("^User selects item to basket$")
    public void User_selects_item_to_basket() throws Throwable {
        itemPageNavi.clickSelectRewardButton();
    }

    @Then("^The item is added to the basket$")
    public void The_item_is_added_to_the_basket() throws Throwable {
        itemPageNavi.checkIfItemWasProperlyAddedToBasket("1");
    }

    @Then("^User is informed that one product is added to the basket$")
    public void User_is_informed_that_one_product_is_added_to_the_basket() throws Throwable {
        //Leave empty
    }

    @When("^Second item is added to the basket$")
    public void Second_item_is_added_to_the_basket() throws Throwable {
        itemPageNavi.clickSelectRewardButton();
    }

    @Then("^The count of item in the basket is increased$")
    public void The_count_of_item_in_the_basket_is_increased() throws Throwable {
        itemPageNavi.checkIfItemWasProperlyAddedToBasket("2");
    }

    @Then("^User is able to see small view of basket$")
    public void User_is_able_to_see_small_view_of_basket() throws Throwable {
        itemPageNavi.checkVisibilityOfSmallBasketWindow();
    }

    @Then("^Content of small basket view is correct '(.+)'$")
    public void Content_of_small_basket_view_is_correct(String itemName) throws Throwable {
        itemPageNavi.checkCorrectnessOfSmallBasketWindowContent(itemName);
    }

    // Scenario : Remove single item/remove all items //////////////////////////////////////////////////////////////////

    @Then("^Opened basket with two product in it$")
    public void Opened_basket_with_two_product_in_it() throws Throwable {
        addBasketNavi.addToBasketTwoProductsAndGoToBasket();
    }

    @Then("^He is able to remove one of the product$")
    public void He_is_able_to_remove_one_of_the_product() throws Throwable {
        rewardsNavi.removeOneItemFromBasket();
        rewardsNavi.checkIfInBasketIsOneItem();
    }

    @Then("^He is able to remove all items making one action$")
    public void He_is_able_to_remove_all_items_making_one_action() throws Throwable {
        rewardsNavi.useRemoveAllItemsReference();
        rewardsNavi.checkIfInBasketIsNoItems();
    }

    // Scenario: Increase and decrease number of products/check total epoints needed ///////////////////////////////////

    @Then("^User can increase quantity of all products$")
    public void User_can_increase_quantity_of_all_products() throws Throwable {
        rewardsNavi.increaseQuantityOfAllProductByOne();
        rewardsNavi.checkDisplayedQuantityOfEachProduct();
    }

    @Then("^Total epoints needed should be calculated properly$")
    public void Total_epoints_needed_should_be_calculated_properly() throws Throwable {
        rewardsNavi.checkCorrectnessOfTotalEpointsNeededValue();
    }

    @Then("^User can decrease quantity of all products$")
    public void user_can_decrease_quantity_of_one_products() throws Throwable {
        rewardsNavi.decreaseQuantityOfFirstProductByOne();
        rewardsNavi.checkDisplayedQuantityOfEachProduct2();
    }

}