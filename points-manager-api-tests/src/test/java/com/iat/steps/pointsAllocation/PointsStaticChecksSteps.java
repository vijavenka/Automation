package com.iat.steps.pointsAllocation;

import com.iat.domain.ExternalTransactionId;
import com.iat.repository.impl.PointsRepositoryImpl;
import com.iat.utils.HelpFunctions;
import com.iat.validators.pointsAllocation.PointsStaticChecksValidator;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.List;

public class PointsStaticChecksSteps {
    //TODO we need to change externaltransactionId in points addingtest;

    private List<ExternalTransactionId> externalTransactionIds = new ArrayList<>();
    private PointsStaticChecksValidator pointsStaticChecksValidator = new PointsStaticChecksValidator();
    private HelpFunctions helpFunctions = new HelpFunctions();

    @When("^Transaction ids will be returned for selected date range$")
    public void getExternalTransactionIdsFromSelectedDateRange() throws Throwable {
        String fromDate = helpFunctions.changeDateFormat("yesterday", "", "yyyy-MM-dd");
        String toDate = helpFunctions.changeDateFormat("current", "", "yyyy-MM-dd");

        externalTransactionIds = new PointsRepositoryImpl().getExternalTransactionIdsFromDateRange(fromDate, toDate);
    }

    @Then("^There should be not duplicated entries of external transaction ids in returned data$")
    public void lookForDuplicateEntriesOfExternalTransactionIds() throws Throwable {
        pointsStaticChecksValidator.checkIfNoDuplicatedExternalTransactionIdsInResponse(externalTransactionIds);
    }

}