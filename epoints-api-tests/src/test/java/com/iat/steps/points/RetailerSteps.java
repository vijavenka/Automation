package com.iat.steps.points;

import com.iat.actions.points.RetailersActions;
import com.iat.actions.points.SearchMerchantActions;
import com.iat.domain.retailer.Retailer;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.points.RetailerValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

public class RetailerSteps {

    private ResponseContainer response;
    private RetailersActions retailersActions = new RetailersActions();
    private RetailerValidator retailerValidator = new RetailerValidator();
    private SearchMerchantActions searchMerchantActions = new SearchMerchantActions();
    private List<Retailer> retailers;
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private List<String> retailerIds;


    @When("^Recently visited retailer request is done$")
    public void getRecentlyVisitedRetailers() throws Throwable {
        response = retailersActions.getRecentlyVisitedRetailers(200);
        retailers = response.getList("", Retailer.class);
    }

    @When("^Merchant data returned by recently visited retailers call is correct$")
    public void validateRecentlyVisitedMerchantDataCorrectness() throws Throwable {
        retailerValidator.validateRecentlyVisitedMerchantDataCorrectness(retailers);
    }

    @When("^User add '(\\d+)' retailers to favourites$")
    public void addRetailersToFavourites(int retailerToBeAdded) throws Throwable {
        response = searchMerchantActions.getSearchMerchant("null;null;null;null;null;null");
        retailerIds = response.getList("merchants.id", String.class).subList(0, retailerToBeAdded);
        for (String retailerId : retailerIds)
            retailersActions.putDeleteFavouritesRetailers(retailerId, dataExchanger.getUuid(), true, 200);
    }

    @Then("^Retailers will be properly added to favourites$")
    public void checkIfRetailersAddedToFavourites() throws Throwable {
        retailerValidator.validateRetailersAddedToFavourites(retailerIds);
    }

    @Then("^Retailers can be also removed from favourites$")
    public void removeRetailerFromFavouritesAndValidateRamoval() throws Throwable {
        retailersActions.putDeleteFavouritesRetailers(retailerIds.get(0), dataExchanger.getUuid(), false, 200);
        retailerValidator.validateRetailerRemovedFromFavourites(retailerIds.get(0));
    }
}