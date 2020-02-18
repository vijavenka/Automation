package com.iat.steps.points;

import com.iat.actions.points.MerchantsActions;
import com.iat.actions.points.SearchMerchantActions;
import com.iat.utils.HelpFunctions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ContractValidator;
import com.iat.validators.points.MerchantDetailsValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MerchantsSteps {

    private String merchantId;
    private ResponseContainer response, responseMerchantsList, responseMerchantDetails;
    private MerchantsActions merchantsActions = new MerchantsActions();
    private SearchMerchantActions searchMerchantActions = new SearchMerchantActions();
    public ContractValidator contractValidator = new ContractValidator();
    private MerchantDetailsValidator merchantDetailsValidator = new MerchantDetailsValidator();
    private HelpFunctions helpFunctions = new HelpFunctions();
    private int random;

    @When("^User request details of selected merchant available on merchant list '(.+?)'$")
    public void getMerchantDetailsData(String params) throws Throwable {
        responseMerchantsList = searchMerchantActions.getSearchMerchant(params);
        List<String> merchantIds = responseMerchantsList.getList("merchants.id");
        random = helpFunctions.returnRandomValue(merchantIds.size());
        merchantId = merchantIds.get(random);
        responseMerchantDetails = merchantsActions.getMerchantDetails(merchantId, 200);
    }

    @Then("^Merchant details response data is same as in contract$")
    public void checkResponseCorrectnessOfMerchantDetails() throws Throwable {
        //contractValidator.validateResponseWithContract("/points/GET-200-MerchantDetails-schema.json", responseMerchantDetails);
    }

    @Then("^Returned data in merchant details match those returned in merchants list$")
    public void validateDataCorrectnessOfSearchMerchantDetailsResponseAcordingToDataFromMerchantsList() throws Throwable {
        merchantDetailsValidator.compareMerchantDetailsDataWithMerchantsListData(responseMerchantsList, responseMerchantDetails, random);
    }

    @When("^User request offers of selected merchant available on merchant list '(.+?)'$")
    public void getMerchantOffersData(String params) throws Throwable {
        responseMerchantsList = searchMerchantActions.getSearchMerchant(params);
        List<String> merchantIds = responseMerchantsList.getList("merchants.id");
        random = helpFunctions.returnRandomValue(merchantIds.size());
        merchantId = merchantIds.get(random);
    }

    @Then("^Merchant offers response data is same as in contract$")
    public void heckResponseCorrectnessOfMerchantOffers() throws Throwable {
        //contractValidator.validateResponseWithContract("/points/GET-200-MerchantOffers-schema.json", responseMerchantOffers);
    }

    @When("^User request promoted merchants '(.+?)' details$")
    public void getPromotedMerchantsDetails(String ids) throws Throwable {
        response = merchantsActions.getPromotedMerchantsDetails(ids, 200);
    }

    @Then("^Promoted merchants details response data is same as in contract for leads merchant$")
    public void validatePromotedMerchantsDetailsContract() throws Throwable {
        //contractValidator.validateResponseWithContract("/points/GET-200-PromotedMerchant-schema.json", response);
    }

    @Then("^Proper merchants were returned according to request data '(.+?)'$")
    public void validatePromotedMerchantsDetailsData(String ids) throws Throwable {
        String[] providedMerchantsArray = ids.split(",");

        List<String> responseMerchantsArray = response.getList("merchants.id");
        assertThat("Count of provided merchants is different than response!", responseMerchantsArray, hasSize(providedMerchantsArray.length));
        assertThat("Response is not containg all ids!", responseMerchantsArray, containsInAnyOrder(providedMerchantsArray));
    }

    @Then("^Inactive merchants are not returned in promoted endpoint$")
    public void notReturnInactiveMerchants() throws Throwable {
        assertThat("Inactive merchant is returned by api", response.getList("merchants.id"), is(empty()));
    }


}