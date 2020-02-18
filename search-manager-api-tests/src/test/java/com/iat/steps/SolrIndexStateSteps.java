package com.iat.steps;

import com.iat.actions.SolrCheckActions;
import com.iat.utils.ResponseHolder;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SolrIndexStateSteps {

    private SolrCheckActions solrCheckActions = new SolrCheckActions();
    private ResponseHolder responseHolder = ResponseHolder.getInstance();

    @Given("^Solr '(\\d+)' is responding to queries$")
    public void checkIfSolrRespondsToRequests(int indexPort) {
        responseHolder.setPort(indexPort);
        solrCheckActions.checkIfSolrIndexIsResponding(indexPort);
    }

    @When("^Query for products count is performed$")
    public void querySolrIndexForProductsCount() {
        responseHolder.setNumberOfProducts(solrCheckActions.getAllProducts(responseHolder.getPort()).getInt("response.numFound"));
    }

    @Then("^Number of current available products should be higher than agreed '(\\d+)'$")
    public void checkIfNumberOfProductsIsAboveThreshold(int minNumberOfProducts) {
        assertThat("Number of products is incorrect!", responseHolder.getNumberOfProducts(), is(greaterThanOrEqualTo(minNumberOfProducts)));
    }
}
