package com.iat.validators.search;

import com.iat.actions.SOLRActions;
import com.iat.domain.facets.Facet;
import com.iat.utils.ResponseContainer;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchValidator {

    private SOLRActions solrActions = new SOLRActions();

    public void checkCorrectnessOfProductBusinessScope(ResponseContainer response, String businessId) {
        List<String> productsIds = response.getList("results.id");
        List<String> businessIds = response.getList("results.businessId");

        int i = 0;
        for (String productIdFromResponse : productsIds) {
            String businessIdFromResponse = businessIds.get(i++);
            if (businessId.equals("united"))
                assertThat("Wrong product: " + productIdFromResponse + " businessId!", businessIdFromResponse, is("united"));
            else
                assertThat("Wrong product: " + productIdFromResponse + " businessId!", businessIdFromResponse, is(nullValue()));
        }
    }

    public void checkCorrectnessOfProductBusinessScopeAndProductsFiltersScope(ResponseContainer response, String businessId, String filterName, String solrFilter, int redemptionElements) {
        List<String> businessIds = response.getList("results.businessId");
        int allCount = response.getInt("allCount");
        int allCountFromSolr;

        if (businessId.equals("united"))
            assertThat("Wrong businessId!", businessIds, everyItem(is("united")));
        else
            assertThat("Wrong businessId!", businessIds, everyItem(isEmptyOrNullString()));

        if (businessId.equals("united"))
            allCountFromSolr = solrActions.getSOLRRedemptionsCount("fq=s_productScope:\"UNITED\"&fq=b_zoneUK:true&fq=b_active:true" + solrFilter);
        else
            allCountFromSolr = solrActions.getSOLRRedemptionsCount("fq=-s_productScope:*&fq=b_zoneUK:true&fq=b_active:true" + solrFilter);
        assertThat("Wrong number of redemptions solr!", allCount, is(allCountFromSolr));

        if (!filterName.equals("null") && redemptionElements != -1)
            assertThat("Wrong number of redemptions solr!", redemptionElements, is(allCountFromSolr));


        //It can be extended here to check each product data correctness, right now only count is validated
    }

    public void validateMultivalFacetResponseCorrectness(ResponseContainer response, String selectedMultivalFilterType, String selectedMultivalFilterValue, String solrFilter, String businessId) throws IOException {
        //check selected filter value
        int responseMultivalFilterRedemptionsNumber;
        String responseMultivalFilterValue;
        int allCountFromSolr;

        Facet facet = response.getAsObject(Facet.class);
        responseMultivalFilterRedemptionsNumber = Integer.parseInt(facet.getSelected().get(0).toString().split(",")[0].replace("[", "").trim());
        responseMultivalFilterValue = facet.getSelected().get(0).toString().split(",")[1].replace("]", "").trim();

        assertThat("Wrong selected filter name in response!", responseMultivalFilterValue, is(selectedMultivalFilterValue));

        if (businessId.equals("united"))
            allCountFromSolr = solrActions.getSOLRRedemptionsCount("fq=s_productScope:\"UNITED\"&fq=b_zoneUK:true" + solrFilter + "&fq=" + selectedMultivalFilterType + ":\"" + selectedMultivalFilterValue + "\"");
        else
            allCountFromSolr = solrActions.getSOLRRedemptionsCount("fq=-s_productScope:[\"\" TO *]&fq=b_zoneUK:true" + solrFilter + "&fq=" + selectedMultivalFilterType + ":\"" + selectedMultivalFilterValue + "\"");
        assertThat("Wrong items count for filter!", responseMultivalFilterRedemptionsNumber, is(allCountFromSolr));

        for (int i = 0; i < facet.getValues().size(); i++) {
            responseMultivalFilterRedemptionsNumber = Integer.parseInt(facet.getValues().get(i).toString().split(",")[0].replace("[", "").trim());
            responseMultivalFilterValue = facet.getValues().get(i).toString().split(",")[1].replace("]", "").trim();

            if (businessId.equals("united"))
                allCountFromSolr = solrActions.getSOLRRedemptionsCount("fq=s_productScope:\"UNITED\"&fq=b_zoneUK:true" + solrFilter + "&fq=" + selectedMultivalFilterType + ":\"" + responseMultivalFilterValue + "\"");
            else
                allCountFromSolr = solrActions.getSOLRRedemptionsCount("fq=-s_productScope:[\"\" TO *]&fq=b_zoneUK:true" + solrFilter + "&fq=" + selectedMultivalFilterType + ":\"" + responseMultivalFilterValue + "\"");
            assertThat("Wrong items count for filter!", responseMultivalFilterRedemptionsNumber, is(allCountFromSolr));

        }
    }

}