package com.iat.steps.points;

import com.iat.actions.search.SearchActions;
import com.iat.domain.facets.Facets;
import com.iat.domain.vouchers.VouchersList;
import com.iat.utils.ResponseContainer;
import com.iat.validators.points.SearchVouchersValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SearchVouchersSteps {

    private ResponseContainer response;
    private SearchActions searchActions = new SearchActions();
    private SearchVouchersValidator searchVouchersValidator = new SearchVouchersValidator();
    private VouchersList vouchersList;
    private String filterValue;

    @When("^Voucher facets call will be requested$")
    public void requestVoucherFacets() throws Throwable {
        response = searchActions.getFacetsForVouchers("null", 200);
    }

    @Then("^Its response match voucher facets response schema$")
    public void validateVoucherFacetsResponseSchema() throws Throwable {
        //leave empty
    }

    @Then("^As well as voucher search request$")
    public void requestAndValidateVoucherSearchResponseSchema() throws Throwable {
        response = searchActions.getVoucherItems("null", 0, 10, "null", 200);
    }

    @When("^Voucher search call will be requested with parameters '(\\d+)', '(\\d+)', '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void searchVouchersWithParameters(int page, int pageSize, String sort, String filterMerchant, String filterDepartment, String filterCategory) throws Throwable {
        filterValue = "null";

        //If .feature file will provide some specific filter values it has to be upgraded
        String requestFilterValue = "null";
        if (filterMerchant.equals("random") || filterDepartment.equals("random") || filterCategory.equals("random")) {
            response = searchActions.getFacetsForVouchers("null", 200);
            Facets facets = response.getAsObject(Facets.class);

            for (int i = 0; i < facets.getFacets().size(); i++) {
                if (filterMerchant.equals("random")) {
                    if (facets.getFacets().get(i).getName().equals("s_merchantName")) {
                        requestFilterValue = "s_merchantName:" + facets.getFacets().get(i).getValues().get(0).toString().split(",")[1].replace("]", "").trim();
                        filterValue = facets.getFacets().get(i).getValues().get(0).toString().split(",")[1].replace("]", "").trim();
                        break;
                    }
                }
                if (filterDepartment.equals("random") || filterCategory.equals("random")) {
                    if (facets.getFacets().get(i).getName().equals("s_department")) {
                        requestFilterValue = "s_department:" + facets.getFacets().get(i).getValues().get(0).toString().split(",")[1].trim();
                        filterValue = facets.getFacets().get(i).getValues().get(0).toString().split(",")[1].trim();
                        break;
                    }
                }
            }

            if (filterCategory.equals("random")) {
                response = searchActions.getFacetsForVouchers(requestFilterValue, 200);
                facets = response.getAsObject(Facets.class);

                for (int i = 0; i < facets.getFacets().size(); i++) {
                    if (facets.getFacets().get(i).getName().equals("s_categoryFromFeedExtracted_multiVal")) {
                        requestFilterValue = requestFilterValue + ",s_categoryFromFeedExtracted_multiVal:" + facets.getFacets().get(i).getValues().get(0).toString().split(",")[1].replace("]", "").trim();
                        filterValue = facets.getFacets().get(i).getValues().get(0).toString().split(",")[1].replace("]", "").trim();
                        break;
                    }
                }
            }
        }

        response = searchActions.getVoucherItems(requestFilterValue, page, pageSize, sort, 200);
        vouchersList = response.getAsObject(VouchersList.class);
    }

    @Then("^Response content is correct according to request parameters '(\\d+)', '(\\d+)', '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void validateSearchVoucherResponseData(int page, int pageSize, String sort, String filterMerchant, String filterDepartment, String filterCategory) throws Throwable {
        searchVouchersValidator.validateVoucherSearchCorrectness(page, pageSize, sort, vouchersList, filterMerchant, filterDepartment, filterCategory, filterValue);
    }
}