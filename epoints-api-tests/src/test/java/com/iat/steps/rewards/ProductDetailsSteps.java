package com.iat.steps.rewards;

import com.iat.actions.rewards.ProductDetailsActions;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ProductDetailsSteps {

    private ResponseContainer response;
    private ProductDetailsActions productDetailsActions = new ProductDetailsActions();

    @When("^Redemption product details are requested '(.+?)'$")
    public void redemptionItemDetailsRequest(String productSeoSlug) throws Throwable {
        response = productDetailsActions.getRedemptionItemDetails(productSeoSlug, 200);
    }

    @Then("^Product details response data is same as in contract$")
    public void product_details_response_data_is_same_as_in_contract() throws Throwable {
        //controllers are containing contract validators
        //contractValidator.validateResponseWithContract("/rewards/GET-200-RedemptionItemDetails.json", response);

    }

    @When("^Related redemption '(.+?)' products list is requested with params '(.+?)'$")
    public void related_redemption_products_list_is_requested(String productSeoSlug, String params) throws Throwable {
        response = productDetailsActions.getRedemptionItemRelatedProducts(productSeoSlug, params, 200);
    }

    @Then("^Related redemption products response data is same as in contract$")
    public void related_redemption_products_response_data_is_same_as_in_contract() throws Throwable {
        //controllers are containing contract validators
        //contractValidator.validateResponseWithContract("/rewards/GET-200-SimilarRedemptions.json", response);
    }


}
