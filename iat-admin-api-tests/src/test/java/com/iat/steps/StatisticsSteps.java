package com.iat.steps;

import com.iat.actions.StatisticsActions;
import com.iat.domain.statistics.Statistics;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.StatisticsValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StatisticsSteps {

    private StatisticsActions statisticsActions = new StatisticsActions();
    private StatisticsValidator statisticsValidator = new StatisticsValidator();
    private ResponseContainer response;
    private DataExchanger dataExchanger = DataExchanger.getInstance();


    //Get statistics config details and validate contract
    @When("^Get statistics config request is made$")
    public void getStatisticsConfig() throws Throwable {
        response = statisticsActions.getStatisticsConfig(200);
    }

    @Then("^Get statistics config request returns proper response$")
    public void getStatisticsConfigMatchContract() throws Throwable {
        //validated at controller level
    }

    @Then("^Get statistics config include agreed set of statistics '(.+?)'$")
    public void getStatisticsConfigIncludeProperStatistics(String params) throws Throwable {
        statisticsActions.validateStatisticsConfigSet(response, params);
    }


    //Get specific statistic by id and validate contract
    @When("^Get statistic by id '(.+?)' request is made with params '(.+?)'$")
    public void getStatisticById(String statId, String params) throws Throwable {
        response = statisticsActions.getStatisticById(statId, params, 200);
    }

    @Then("^Get statistic by id '(.+?)' request with params '(.+?)' returns proper response$")
    public void getStatisticByIdMatchContract(String statId, String params) throws Throwable {
        statisticsActions.validateStatisticByIdData(response, statId, params);
    }

    //Get specific statistic by id - Error message validation
    @When("^Get statistic by id request is made with improper data '(.+?)', '(.+?)', '(.+?)'$")
    public void getStatisticByIdForInvalidData(String statId, String params, int status) throws Throwable {
        response = statisticsActions.getStatisticById(statId, params, status);
        dataExchanger.setResponse(response);
        System.out.println("\nRESPONSE: " + response + "\n");
    }


    @Then("^Each department node should have manager details if manager assigned to it$")
    public void checkIfCorrectManagerDetailsAssignedToDepartmentNode() throws Throwable {
        statisticsValidator.checkIfCorrectManagerDetailsAssignedToDepartmentNodes(response.getAsObject(Statistics.class));
    }
}



