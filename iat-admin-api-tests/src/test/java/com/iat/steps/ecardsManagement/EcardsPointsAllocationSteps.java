package com.iat.steps.ecardsManagement;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.NotificationsActions;
import com.iat.actions.StatisticsActions;
import com.iat.actions.ecardsManagement.EcardsPointsAllocationActions;
import com.iat.domain.EcardsConfig.PointsAllocation;
import com.iat.domain.EcardsConfig.PointsAllocations;
import com.iat.steps.LoginSteps;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ecardsManagement.EcardsPointsAllocationValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class EcardsPointsAllocationSteps {

    private LoginSteps loginSteps = new LoginSteps();
    private EcardsPointsAllocationActions ecardsPointsAllocationActions = new EcardsPointsAllocationActions();
    private NotificationsActions notificationsActions = new NotificationsActions();
    private StatisticsActions statisticsActions = new StatisticsActions();

    private EcardsPointsAllocationValidator ecardsPointsAllocationValidator = new EcardsPointsAllocationValidator();

    private ResponseContainer response;
    private ObjectMapper mapper = new ObjectMapper();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private int statsAllocatedPoints, pointsAllocationLimit, pointsAllocationLimitForParent, pointsAllocationLimitForDepartment;


    //Get partners - contract validation
    @When("^Get partners call is made$")
    public void getPartners() throws Throwable {
        response = ecardsPointsAllocationActions.getEcardsPartners(200);
    }

    @Then("^Get partners call returns proper contract$")
    public void getPartnersMatchContract() throws Throwable {
        //done at controller level
    }


    //Get partners - Error message validation
    @When("^Get partners call is made for incorrect data '(.+?)'$")
    public void getPartnersErrorMessage(int status) throws Throwable {
        response = ecardsPointsAllocationActions.getEcardsPartners(status);
        dataExchanger.setResponse(response);
    }

    //Get virtual points allocation statistics - contract validation
    @When("^Get virtual points allocation statistics call is made for '(.+?)' with '(.+?)'$")
    public void getVirtualPointsAllocationStatistics(String level, String params) throws Throwable {
        response = ecardsPointsAllocationActions.getEcardsAllocationsStats(level, params, 200);
    }

    @Then("^For '(.+?)' level virtual points allocation statistics equals points allocation limit$")
    public void for_department_level_virtual_points_allocation_statistics_equals_points_allocation_limit(String level) throws Throwable {
        if (level.equals("department")) {
            String availablePoints = response.getString("availablePoints");
            String limit = ecardsPointsAllocationActions.getEcardsAllocationLimit(200).toString();
            assertThat("availablePoints not equals limit ", availablePoints, is(limit));
        } else
            assertThat("Not implemented", false);

    }

    @When("^Landing page API is called '(.+?)'$")
    public void Landing_page_API_is_called(String ToValidate) throws Throwable {
        String limit = ecardsPointsAllocationActions.getEcardsAllocationLanding(200, ToValidate).toString();
    }

    //Get virtual points allocation history
    @When("^Get virtual points allocation history call is made for '(.+?)' and filters '(.+?)'$")
    public void getVirtualPoiuntsAllocationHistory(String level, String params) throws Throwable {
        response = ecardsPointsAllocationActions.getEcardsAllocations(level, params, 200);
    }

    @Then("^Get virtual points allocation history call for '(.+?)' returns proper contract and data for filters '(.+?)'$")
    public void getVirtualPointsAllocationHistoryMatchContract(String level, String params) throws Throwable {
        ecardsPointsAllocationValidator.validateEcardsPointsAllocationHistory(params, response);
    }


    //Get virtual points allocation history Error message validation
    @When("^Get virtual points allocation history call for incorrect data '(.+?)', '(.+?)', '(.+?)'$")
    public void getVirtualPointsAllocationHistoryErrorMessage(String level, String jsonBody, int status) throws Throwable {
        response = ecardsPointsAllocationActions.getEcardsAllocations(level, jsonBody, status);
        dataExchanger.setResponse(response);
    }

    //Add virtual points to partner by IAT Platform admin
    @Given("^Current ecards points allocation limit for partner is known$")
    public void getEcardsPointsAllocationLimitForPartner() throws Throwable {
        pointsAllocationLimit = Integer.parseInt(ecardsPointsAllocationActions.getEcardsAllocationLimit(200).toString());
    }

    @When("^Iat Platform Admin '(.+?)' distribute virtual points to partner call is made for data '(.+?)'$")
    public void iatPlatformAdminDistributeVirtualPointsToPartner(String credentials, String jsonBody) throws Throwable {
        loginSteps.iatAdminUserLogIn(credentials);
        //allocation Stats
        statsAllocatedPoints = Integer.parseInt(ecardsPointsAllocationActions.getEcardsAllocationsStats("partner", 200).getString("allocatedPoints"));

        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsAllocation pointsAllocation = mapper.readValue(jsonBody, PointsAllocation.class);

        ecardsPointsAllocationActions.setEcardsAllocations("partner", new PointsAllocations(pointsAllocation), 201);
        dataExchanger.setPointsAllocationObject(pointsAllocation);
    }

    @Then("^Ecards points allocation limit for partner was properly updated$")
    public void getEcardsPointsAllocationLimitForPartnerAndCheckData() throws Throwable {
        int expectedPointsAllocationLimit = pointsAllocationLimit + Integer.parseInt(dataExchanger.getPointsAllocationObject().getAmount());
        String pointsAllocationLimitAfter = ecardsPointsAllocationActions.getEcardsAllocationLimit(200).toString();
        assertThat("Limit for partner is incorrect", pointsAllocationLimitAfter, is(Integer.toString(expectedPointsAllocationLimit)));
    }

    @Then("^Allocated points value was increased after allocate points to partner by IAT Platform Admin '(.+?)'$")
    public void increasePointsAllocationByPlatformAdmin(String credentials) throws Throwable {
        loginSteps.iatAdminUserLogIn(credentials);
        int allocatedPointsBefore = statsAllocatedPoints;
        statsAllocatedPoints = Integer.parseInt(ecardsPointsAllocationActions.getEcardsAllocationsStats("partner", 200).getString("allocatedPoints"));
        assertThat("statsAllocatedPoints value not increased properly", statsAllocatedPoints, is(allocatedPointsBefore + Integer.parseInt(dataExchanger.getPointsAllocationObject().getAmount())));
    }


    //Add virtual points to departments
    @Given("^Current ecards points allocation limit for parent '(.+?)' is known$")
    public void getPointsAllocationLimitForDepartmentBeforeAllocation(String credentials) throws Throwable {
        //Allocate additional points to partner
        ecardsPointsAllocationActions.allocateAdditionalPointsForPartner();

        //get limit for logged admin/manager department
        loginSteps.iatAdminUserLogIn(credentials);
        pointsAllocationLimitForParent = Integer.parseInt(ecardsPointsAllocationActions.getEcardsAllocationLimit(200).toString());

        //statsAllocatedPoints stats
        statsAllocatedPoints = Integer.parseInt(ecardsPointsAllocationActions.getEcardsAllocationsStats("department", 200).getString("allocatedPoints"));
    }

    @Given("^Current ecards points allocation limit for department '(.+?)' is known$")
    public void currentEcardsPointsAllocationLimitForDepartmentIsKnown(String departmentName) throws Throwable {
        loginSteps.iatAdminUserLogIn(Config.getDepartmentManagerForName(departmentName));
        pointsAllocationLimitForDepartment = Integer.parseInt(ecardsPointsAllocationActions.getEcardsAllocationLimit(200).toString());
    }

    @When("^Partner Admin or manager distribute virtual points to department call is made for data '(.+?)', '(.+?)', '(.+?)'$")
    public void partnerAdminAlocatePointsToDepartment(String departmentName, String jsonBody, String credentials) throws Throwable {
        //log in to admin / manager account which will allocate points to department
        loginSteps.iatAdminUserLogIn(credentials);

        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsAllocation pointsAllocation = mapper.readValue(jsonBody, PointsAllocation.class);
        pointsAllocation.setReceiverId(Config.getDepartmentIdForName(departmentName));

        ecardsPointsAllocationActions.setEcardsAllocations("department", new PointsAllocations(pointsAllocation), 201);
        dataExchanger.setPointsAllocationObject(pointsAllocation);

    }

    @Then("^Current ecards points allocation limit for parent '(.+?)' was properly decreased$")
    public void validateIfAllocatedPointsWasDecreasedForParenDepartment(String credentials) throws Throwable {
        int amount = Integer.parseInt(dataExchanger.getPointsAllocationObject().getAmount());

        loginSteps.iatAdminUserLogIn(credentials);
        int parentAllocationLimitBefore = pointsAllocationLimitForParent;
        int parentAllocationLimitAfter = Integer.parseInt(ecardsPointsAllocationActions.getEcardsAllocationLimit(200).toString());

        assertThat("Parent department user \"/allocations/limit\" value improperly decreased ", parentAllocationLimitAfter, is(parentAllocationLimitBefore - amount));

        //Stats allocationPoints validation
        int statsAllocatedPointsBefore = statsAllocatedPoints;
        response = ecardsPointsAllocationActions.getEcardsAllocationsStats("department", 200);
        int availablePoints = response.getInt("availablePoints");
        int statsAllocatedPointsAfter = response.getInt("allocatedPoints");

        assertThat("Parent department user \"/allocations/stats\" availablePoints value improperly increased", statsAllocatedPointsAfter, is(statsAllocatedPointsBefore + amount));

        assertThat("Parent department user \"/allocations/stats\" availablePoints value improperly decreased should be same as \"/allocations/limit\"", availablePoints, is(parentAllocationLimitAfter));


    }

    @Then("^Current ecards points allocation limit for department '(.+?)' was properly updated$")
    public void departmentPointsAllocationLimitWasUpdated(String departmentName) throws Throwable {
        //Check if limit on department level was increased
        loginSteps.iatAdminUserLogIn(Config.getDepartmentManagerForName(departmentName));

        int amount = Integer.parseInt(dataExchanger.getPointsAllocationObject().getAmount());
        int pointsAllocationLimitForDepartmentBefore = pointsAllocationLimitForDepartment;
        int pointsAllocationLimitForDepartmentAfter = Integer.parseInt(ecardsPointsAllocationActions.getEcardsAllocationLimit(200).toString());

        assertThat("Department \"/allocations/limit\" value was not increased ", pointsAllocationLimitForDepartmentAfter, is(pointsAllocationLimitForDepartmentBefore + amount));
    }


    //Add virtual points to partner/department Error message validation
    @When("^Distribute virtual points to '(.+?)' call for incorrect data '(.+?)', '(.+?)'$")
    public void distributeVirtualPointsIntoDepartmentsErrorMessage(String level, String jsonBody, int status) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        PointsAllocation pointsAllocation = mapper.readValue(jsonBody, PointsAllocation.class);

        response = ecardsPointsAllocationActions.setEcardsAllocations(level, new PointsAllocations(pointsAllocation), status);
        dataExchanger.setPointsAllocationObject(pointsAllocation);
        dataExchanger.setResponse(response);
    }


    @When("^POST call to export ecards ([^\"]*) is made ([^\"]*), (\\d+)$")
    public void postCallToExportEcardsAwardsIsMade(String whatExport, String params, int status) throws Throwable {

        switch (whatExport.toLowerCase()) {
            case "awards":
                response = ecardsPointsAllocationActions.postEcardsAwardExport(params, status);
                break;
            case "partners":
                response = ecardsPointsAllocationActions.postEcardsAllocPartnerExport(params, status);
                break;
            case "departments":
                response = ecardsPointsAllocationActions.postEcardsAllocDepartmentExport(params, status);
                break;
            case "ecardusagebreakdown":
                response = statisticsActions.postEcardsUsageBreakdownToBeExport(params, status);
                break;
            case "statistics":
                assertThat("Not implemented", false);
                break;
        }

        dataExchanger.setResponse(response);
    }

    @Then("^Name of the exported ecards ([^\"]*) file is retrieved$")
    public void nameOfTheExportedEcardsFileIsRetrieved(String whatExport) throws Throwable {
        String filename = response.getString("html.body");

        switch (whatExport.toLowerCase()) {
            case "awards":
                assertThat("Invalid filename!", filename, allOf(startsWith("users_allocation_history_"), endsWith(".xls")));
                break;
            case "partners":
                assertThat("Invalid filename!", filename, allOf(startsWith("partners_allocation_history_"), endsWith(".xls")));
                break;
            case "departments":
                assertThat("Invalid filename!", filename, allOf(startsWith("departments_allocation_history_"), endsWith(".xls")));
                break;
            case "ecardusagebreakdown":
                assertThat("Invalid filename!", filename, allOf(startsWith("ecard-usage-breakdown_"), endsWith(".xls")));
                break;
        }
    }

    @Then("^It is possible to use GET call in order to download exported ecards$")
    public void itIsPossibleToUseGETCallInOrderToDownloadExportedEcardsFile() throws Throwable {
        await("Waiting for file is ready").ignoreExceptions()
                .atMost(180, SECONDS).pollInterval(5, SECONDS)
                .until(() -> notificationsActions.getNotifications().getString("name[-1]"), is(response.getString("html.body")));

        String filename = notificationsActions.getNotifications().getString("name[-1]");
        response = ecardsPointsAllocationActions.getExportFile(filename);
        response.getValidatableResponse().contentType(containsString("application/download"));
    }

    @When("^GET call to download exported file is made with (.+?) filename, (\\d+)$")
    public void getCallToDownloadExportedEcardsIsMadeWithFilename(String filename, int status) throws Throwable {
        response = ecardsPointsAllocationActions.getExportFile(filename, status);
        dataExchanger.setResponse(response);
    }

}