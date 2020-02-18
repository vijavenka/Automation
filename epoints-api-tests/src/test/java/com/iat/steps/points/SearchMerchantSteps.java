package com.iat.steps.points;

import com.iat.actions.points.SearchMerchantActions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.points.SearchMerchantValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchMerchantSteps {

    private ResponseContainer response;
    private SearchMerchantActions searchMerchantActions = new SearchMerchantActions();
    private SearchMerchantValidator searchMerchantValidator = new SearchMerchantValidator();

    @When("^Merchants details with corresponding filters \\(departments; prefixes\\) are requested '(.+?)'$")
    public void getMerchantsDepartmentsPrefixesList(String params) throws Throwable {
        response = searchMerchantActions.getSearchMerchant(params);
    }

    @Then("^Merchants,Departments,Prefixes details response data is same as in contract$")
    public void checkResponseCorrectnessOfSearchMerchant() throws Throwable {
        //controller is doing that already
        //contractValidator.validateResponseWithContract("/points/GET-200-SearchMerchant-schema.json", response.toString());
    }

    @Then("^Returned data is correct according to keyword search, page size and page number, department and prefix '(.+?)'$")
    public void validateDataCorrectnessOfSearchMerchantResponse(String params) throws Throwable {
        searchMerchantValidator.validateDataCorrectnessOfSearchMerchantResponse(params, response);
    }

    @Then("^Departments undecorated with categories call response match schema$")
    public void requestDepartmentsUndecoratedWithCategories() throws Throwable {
        response = searchMerchantActions.getDepartmentsUndecorated(200);
    }

    @Then("^Merchants about us page call response match schema$")
    public void requestMerchantOnAboutUsPage() throws Throwable {
        response = searchMerchantActions.getMerchantsAboutUsPage(200);
    }

}