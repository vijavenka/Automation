package com.iat.ePoints.Steps.Checkout;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.Checkout.DeliveryDetailsNavigation;
import com.iat.ePoints.Navigations.Checkout.SelectedRewardsNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 05.12.13
 * Time: 10:18
 * To change this template use File | Settings | File Templates.
 */
public class DeliveryDetailsSteps {

    private IExecutor executor;
    private DeliveryDetailsNavigation deliveryNavi;
    private SelectedRewardsNavigation rewardsNavi;

    public DeliveryDetailsSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    @Before
    public void set_up() {
        executor.start();
        deliveryNavi = new DeliveryDetailsNavigation(executor);
        rewardsNavi = new SelectedRewardsNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    //Scenario: User opens delivery details page////////////////////////////////////////////////////////////////////////

    @When("^SignIn window appear enter password$")
    public void Login_window_appear_enter_password() throws Throwable {
        deliveryNavi.authenticateUser();
    }

    @When("^User click order reward button$")
    public void User_click_order_reward_button() throws Throwable {
        rewardsNavi.clikcOrderRewardButton();
    }

    @Then("^He is re-directed to delivery details page$")
    public void He_is_re_directed_to_delivery_details_page() throws Throwable {
        deliveryNavi.checkContentOfDeliveryDetailsPage();
    }

    @When("^User click back or edit button$")
    public void User_click_back_button() throws Throwable {
        deliveryNavi.clickBackButton();
    }

    @Then("^He return to select reward page$")
    public void He_return_to_select_reward_page() throws Throwable {
        rewardsNavi.checkIfProperPage();
    }

    //Scenario: User is sure that address from user account is properly copied in delivery details//////////////////////

    @Then("^He can see that all contact data was copied properly '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)'$")
    public void He_can_see_that_all_contact_data_was_copied_properly(String Name, String LastName, String HouseNumber, String Street, String City, String County, String Country, String PostCode) throws Throwable {
        deliveryNavi.checkIfUserContactDataWasCopiedProperly(Name, LastName, HouseNumber, Street, City, County, Country, PostCode);
    }

    //Scenario: User can add new address and replace his account contact data in delivery details section///////////////

    @Then("^User is able to add new address$")
    public void User_is_able_to_add_new_address() throws Throwable {
        deliveryNavi.clickAddNewAddressButton();
    }

    @Then("^All data should be filled correctly '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)'$")
    public void All_data_should_be_filled_correctly(String Name, String LastName, String HouseNumber, String Street, String City, String County, String Country, String PostCode) throws Throwable {
        deliveryNavi.enterNeededData(Name, LastName, HouseNumber, Street, City, County, Country, PostCode);
    }

    @Then("^Confirm button should be used$")
    public void Confirm_button_should_be_used() throws Throwable {
        deliveryNavi.confirmChanges();
    }

    @Then("^Compare all details with data entered before '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)' '(.+)'$")
    public void Compare_all_details_with_data_entered_before_Krzysztof_Baranowski_Poboczna_Wroclaw_Dolnoslaskie_Poland_(String Name, String LastName, String HouseNumber, String Street, String City, String County, String Country, String PostCode) throws Throwable {
        deliveryNavi.checkCorrectnessOfNeededDetails(Name, LastName, HouseNumber, Street, City, County, Country, PostCode);
    }
}
