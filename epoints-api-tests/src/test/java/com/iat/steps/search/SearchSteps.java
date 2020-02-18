package com.iat.steps.search;

import com.iat.actions.SOLRActions;
import com.iat.actions.rewards.ProductDetailsActions;
import com.iat.actions.search.SearchActions;
import com.iat.domain.EpointsRange;
import com.iat.domain.orderRedemption.RedemptionItem;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.search.SearchValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchSteps {

    private ResponseContainer response;
    private SearchActions searchActions = new SearchActions();
    private SOLRActions solrActions = new SOLRActions();
    private ProductDetailsActions productDetailsActions = new ProductDetailsActions();
    private SearchValidator searchValidator = new SearchValidator();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private List<EpointsRange> epointsRanges, epointsRanges2;
    private String solrFilter = "null";
    private String apiFilter = "null";
    private int redemptionElements;
    private boolean filterAvailable;
    private String selectedMultivalFilterValue;

    private int[] from = new int[]{100, 1000, 5000, 20000, 50000, 100000};
    private int[] to = new int[]{999, 4999, 19999, 49999, 99999, 2147483647};
    private String[] label = new String[]{"filters.ranges.100+", "filters.ranges.1000+", "filters.ranges.5000+", "filters.ranges.20000+", "filters.ranges.50000+", "filters.ranges.100000+"};

    @When("^Epoints ranges all will be requested for '(.+?)'$")
    public void getEpointsRangesAll(String businessId) throws Throwable {
        response = searchActions.getEpointsRangesAll(businessId, 200);
        epointsRanges = response.getList("", EpointsRange.class);
    }

    @Then("^Correct set of filters will be returned '(.+?)'$")
    public void validateRangesCorrectness(String businessId) throws Throwable {
        for (int i = 0; i < from.length; i++) {
            assertThat("Wrong from value for range: " + i, from[i], is(epointsRanges.get(i).getFrom()));
            assertThat("Wrong from value for range: " + i, to[i], is(epointsRanges.get(i).getTo()));
            assertThat("Wrong from value for range: " + i, label[i], is(epointsRanges.get(i).getLabel()));

            int redemptionsCount;
            if (businessId.equals("united"))
                redemptionsCount = solrActions.getSOLRRedemptionsCount("q=i_epointsToPurchaseUK:[" + epointsRanges.get(i).getFrom() + " TO " + epointsRanges.get(i).getTo() + "]&fq=s_productScope:\"UNITED\"&fq=b_zoneUK:true");
            else
                redemptionsCount = solrActions.getSOLRRedemptionsCount("q=i_epointsToPurchaseUK:[" + epointsRanges.get(i).getFrom() + " TO " + epointsRanges.get(i).getTo() + "]&fq=-s_productScope:[\"\" TO *]&fq=b_zoneUK:true");
            assertThat("Wrong items count for range: " + i, redemptionsCount, is(epointsRanges.get(i).getItemsCount()));
        }
    }

    @When("^Epoints ranges call call will be requested for united and epoints$")
    public void getEpointsRangesALlForEpointsAndUnited() throws Throwable {
        epointsRanges = searchActions.getEpointsRangesAll("null", 200).getList("", EpointsRange.class);
        epointsRanges2 = searchActions.getEpointsRangesAll("united", 200).getList("", EpointsRange.class);

    }

    @Then("^Items count should be different between them$")
    public void items_count_should_be_different_between_them() throws Throwable {
        // This will mainly works on prod when feeds will be independent on qa we are using same feeds with same products for tests
        for (int i = 0; i < from.length; i++)
            if (epointsRanges.get(i).getItemsCount() != 0 && epointsRanges2.get(i).getItemsCount() != 0)
                assertThat("Same department itemsCount for department: " + epointsRanges.get(i).getLabel(), epointsRanges.get(i).getItemsCount(), is(not(epointsRanges2.get(i).getItemsCount())));
    }

    @When("^Facets will be requested for '(.+?)', '(.+?)'$")
    public void getFacets(String businessId, String version) throws Throwable {
        response = searchActions.getRedemptionFacets(businessId, version, "null", 200);
    }

    @Then("^Correct set of facets will be returned$")
    public void checkFacetsCorrectness() throws Throwable {
        //TODO will not be implemented as face
    }

    @When("^Multival facets will be requested for '(.+?)', '(.+?)' for specified filter option '(.+?)'$")
    public void requestFactesForSelectedMultivalFilter(String businessId, String version, String selectedMultivalFilter) throws Throwable {
        filterAvailable = false;
        String[] filtersSet = searchActions.getFiltersSetToBuildRequestForMultivalFacetCall(businessId, selectedMultivalFilter);
        solrFilter = filtersSet[0];
        apiFilter = filtersSet[1];
        selectedMultivalFilterValue = filtersSet[2];

        if (!selectedMultivalFilterValue.contains("null")) {
            filterAvailable = true;
            response = searchActions.getRedemptionFacetsForSelectedMultivalFilter(businessId, apiFilter, version, selectedMultivalFilter, selectedMultivalFilterValue, 200);
        }
    }

    @When("^Facets request for empty results is made '(.+?)', '(.+?)' for specified filter option '(.+?)'$")
    public void requestFactesForEmptyResults(String businessId, String version, String queryParams) throws Throwable {
        response = searchActions.getRedemptionFacets(businessId, version, queryParams, 200);
    }

    @Then("^Facets response for empty results returns proper content$")
    public void facetsResponseForEmptyResultsReturnsProperContent() throws Throwable {
        List<String> facetsNames = asList("s_categoryFromFeedExtractedSeoSlugs_multiVal", "s_brandName", "s_actor_multiVal", "s_artist_multiVal", "s_author_multiVal", "i_epointsToPurchase");
        assertThat("All facets should be retrieved!", facetsNames, everyItem(isIn(response.getList("facets.name"))));
        assertThat("Filter values should not be retrieved for empty results!", response.getList("facets.values"), everyItem(is(empty())));
    }


    @Then("^Correct facet options '(.+?)' value are returned according to solr for business '(.+?)'$")
    public void chekcFacetsRedemptionNumberValuesInComparisionWithSolr(String selectedMultivalFilterType, String businessId) throws Throwable {
        if (filterAvailable)
            searchValidator.validateMultivalFacetResponseCorrectness(response, selectedMultivalFilterType, selectedMultivalFilterValue, solrFilter, businessId);
    }

    @When("^Epoints redemption items will be requested for '(.+?)' and filter '(.+?)'$")
    public void getRedemptionItems(String businessId, String filterName) throws Throwable {
        String[] filtersSet = searchActions.getFiltersSetToBuildRequest(businessId, filterName);
        solrFilter = filtersSet[0];
        apiFilter = filtersSet[1];

        redemptionElements = searchActions.getRedemptionCountForSpecifiedFilter(businessId, filterName);

        response = searchActions.getRedemptionItems(businessId, apiFilter, "null", false,200);
    }

    @Then("^Redemption items only from business '(.+?)' and selected filters '(.+?)' will be returned$")
    public void checkIfRedemptionsFromSelectedBusinessId(String businessId, String filterName) throws Throwable {
        searchValidator.checkCorrectnessOfProductBusinessScopeAndProductsFiltersScope(response, businessId, filterName, solrFilter, redemptionElements);
    }

    @When("^Epoints redemption items will be requested for '(.+?)' with keyword '(.+?)'$")
    public void getRedemptionItemsForKeywordSearch(String businessId, String keyword) throws Throwable {
        response = searchActions.getRedemptionItems(businessId, "null", keyword, 200);
    }

    @Then("^Redemption items only from business '(.+?)' and used keyword '(.+?)' will be returned$")
    public void checkKeywordSearchResultsAgainstBusinessAndKeyword(String businessId, String keyword) throws Throwable {
        searchValidator.checkCorrectnessOfProductBusinessScope(response, businessId);
    }

    @When("^Similar products will be requested for specific item from business '(.+?)'$")
    public void getSimilarProductsForRandomProduct(String businessId) throws Throwable {
        response = searchActions.getRedemptionItems(businessId, "null", "null",false, 200);
        String productId = response.getString("results[0].id");
        String productSeo = response.getString("results[0].seoSlug");

        response = productDetailsActions.getRedemptionItemRelatedProducts(productSeo + "/" + productId, "null;null", 200);
    }

    @Then("^Similar products will be returned according to specified '(.+?)'$")
    public void checkIfSimilarProductsFromCorrectBusiness(String businessId) throws Throwable {
        searchValidator.checkCorrectnessOfProductBusinessScope(response, businessId);
    }


    @Given("^Redemption item '(.+?)' is selected for '(.+?)' from list ordered '(.+?)'$")
    public void selectRedemptionItem(int itemNumber, String businessId, String order) throws Throwable {
        response = searchActions.getRedemptionItems(businessId, "sort=i_epointsToPurchase|" + order + "&userSearch=true",  "null", false, 200);
        List<RedemptionItem> redemptionsList = response.getList("results", RedemptionItem.class);

        /*for(int i=0; i<=itemNumber; i++){
            RedemptionItem redemptionItem = redemptionsList.get(i);
            dataExchanger.setRedemptionItem(redemptionItem);
            response = productDetailsActions.getRedemptionItemDetails(redemptionItem.getSeoSlug() + "/" + redemptionItem.getId(), 200);
        }*/

        RedemptionItem redemptionItem = redemptionsList.get(itemNumber);
        dataExchanger.setRedemptionItem(redemptionItem);
        response = productDetailsActions.getRedemptionItemDetails(redemptionItem.getSeoSlug() + "/" + redemptionItem.getId(), 200);
    }

    /*@Given("^Redemption item '(.+?)' is selected for '(.+?)' and '(.+?)' from list ordered '(.+?)'$")
    public void selectRedemptionItem(int itemNumber, String businessId, String departmentCategory, String order) throws Throwable {
        response = searchActions.getRedemptionItems(businessId, "sort=i_epointsToPurchase|" + order + "&userSearch=true", departmentCategory, "null", false, 200);
        List<RedemptionItem> redemptionsList = response.getList("results", RedemptionItem.class);

        for(int i=0; i<=itemNumber; i++){
            RedemptionItem redemptionItem = redemptionsList.get(i);
            dataExchanger.setRedemptionItem(redemptionItem);
            response = productDetailsActions.getRedemptionItemDetails(redemptionItem.getSeoSlug() + "/" + redemptionItem.getId(), 200);
        }

        *//*RedemptionItem redemptionItem = redemptionsList.get(itemNumber);
        dataExchanger.setRedemptionItem(redemptionItem);
        response = productDetailsActions.getRedemptionItemDetails(redemptionItem.getSeoSlug() + "/" + redemptionItem.getId(), 200);*//*
    }
*/
}