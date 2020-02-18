package com.iat.steps.ecardsManagement;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.EcardsConfigActions;
import com.iat.actions.ecardsManagement.EcardsAwardingUsersActions;
import com.iat.actions.ecardsManagement.EcardsPointsAllocationActions;
import com.iat.actions.ecardsManagement.EcardsReasonsActions;
import com.iat.actions.ecardsManagement.EcardsUsersSearchActions;
import com.iat.domain.EcardsConfig.PointsSharing;
import com.iat.domain.ecardsAwarding.EcardsSent;
import com.iat.domain.ecardsAwarding.Product;
import com.iat.repository.UserRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.steps.GenericSteps;
import com.iat.steps.LoginSteps;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ecardsManagement.EcardsAwardingUsersValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;
import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class EcardsAwardingUsersSteps {

    private LoginSteps loginSteps = new LoginSteps();
    private EcardsAwardingUsersActions ecardsAwardingUsersActions = new EcardsAwardingUsersActions();
    private EcardsPointsAllocationActions ecardsPointsAllocationActions = new EcardsPointsAllocationActions();
    private EcardsConfigActions ecardsConfigActions = new EcardsConfigActions();
    private EcardsReasonsActions ecardsReasonsActions = new EcardsReasonsActions();

    private EcardsAwardingUsersValidator ecardsAwardingUsersValidator = new EcardsAwardingUsersValidator();

    private final UserRepository userRepository = new UserRepositoryImpl();
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    private ResponseContainer response;
    private String pointsAllocationLimitForDepartment;
    private int availablePoints, allocatedPoints;
    private long balance;


    //Get user awarding points history
    @When("^Get user awarding points history call is made with filters '(.+?)'$")
    public void getAwardingUsersHistory(String params) throws Throwable {
        response = ecardsAwardingUsersActions.getEcardsAwardingPoints(params, 200);
    }

    @Then("^Get user awarding points history call returns proper contract and data for filters '(.+?)'$")
    public void getAwardingUsersHistoryMatchContract(String params) throws Throwable {
        ecardsAwardingUsersValidator.validateGetEcardsAwardingPoints(params, response);

    }

    // Get user awarding points history - Error message validation
    @When("^Get user awarding points history call for incorrect data '(.+?)', '(.+?)'$")
    public void getAwardingUsersHistoryErrorMessage(String params, int status) throws Throwable {
        response = ecardsAwardingUsersActions.getEcardsAwardingPoints(params, status);
        dataExchanger.setResponse(response);
    }


    //Awarding ecards users
    @Given("^Partner pointsSharing scope for manager to user is set to '(.+?)', sharePointsWithManager is set to '(.+?)'$")
    public void changePartnerSettingsforPointsSharing(String managerSharePointsRange, boolean sharePointsWithManager) throws Throwable {
        loginSteps.iatAdminUserLogIn(Config.getSuperAdminCredentials());

        PointsSharing pointsSharing = ecardsConfigActions.getEcardsSettingsForType("pointsSharing", 200).getAsObject(PointsSharing.class);
        pointsSharing.setManagerSharePointsRange(managerSharePointsRange);
        pointsSharing.setSharePointsWithManager(sharePointsWithManager);

        ecardsConfigActions.setEcardsSettingsForType("pointsSharing", pointsSharing.toJsonRequest(), 200);
    }

    @Given("^Partner approvalProcess is set to '(.+?)'$")
    public void changePartnerSettingsforApprovalProcess(String approvalProcess) throws Throwable {
        loginSteps.iatAdminUserLogIn(Config.getSuperAdminCredentials());
    }


    @Given("^IAT user current limit and stats values are known before grant users$")
    public void iatAdminLimitBeforeGrantUsers() throws Throwable {
        if (!dataExchanger.getSessionId().equals("")) {

            //limit
            pointsAllocationLimitForDepartment = ecardsPointsAllocationActions.getEcardsAllocationLimit(200).toString();

            //stats
            ResponseContainer responseTemp = ecardsAwardingUsersActions.getEcardsAwardingStats(200);
            availablePoints = responseTemp.getInt("availablePoints");
            allocatedPoints = responseTemp.getInt("allocatedPoints");

            assertThat("Not equal allocations/limit allocatedPoints and award/stats availablePoints", Integer.parseInt(pointsAllocationLimitForDepartment), is(availablePoints));
        }
    }

    @When("^User award points call is made with data '(.+?)', '(.+?)'$")
    public void awardingUsers(String jsonBody, int status) throws Throwable {
        EcardsSent ecardsSent = new ObjectMapper().readValue(jsonBody, EcardsSent.class);

        //Select random users uuids dynamically
        if (ecardsSent.getUsersKey() != null)
            if (ecardsSent.getUsersKey().size() > 1)
                ecardsSent.setUsersKey(ecardsAwardingUsersActions.getDynamicUsersUuids(ecardsSent.getUsersKey()));

        response = ecardsAwardingUsersActions.setEcardsAwardingPoints(ecardsSent, status);
        dataExchanger.setEcardsSentObject(ecardsSent);
        dataExchanger.setResponse(response);
    }

    @Then("^User award points call properly reduced IAT user limits$")
    public void awardingUsersValidateLimit() throws Throwable {
        int usersNo = dataExchanger.getEcardsSentObject().getUsersKey().size();
        int points = 0;

        List<Product> productItems = dataExchanger.getEcardsSentObject().getProducts();
        if(productItems != null ){
            for(int i=0; i<productItems.size(); i++){
                points += productItems.get(i).getProductPoints();
            }
        }
        else{
            points = dataExchanger.getEcardsSentObject().getPointsValue() != null ? dataExchanger.getEcardsSentObject().getPointsValue() : 0;
        }

        int pointsLimitBefore = Integer.parseInt(pointsAllocationLimitForDepartment);
        int pointsLimitAfter = Integer.parseInt(ecardsPointsAllocationActions.getEcardsAllocationLimit(200).toString());

        assertThat("Points allocation limit value improperly reduced", pointsLimitAfter, is(pointsLimitBefore - (usersNo * points)));
    }


    @Then("^User award points call properly update award stats$")
    public void awardingUsersValidateStats() throws Throwable {

        int usersNo = dataExchanger.getEcardsSentObject().getUsersKey().size();
        int points = dataExchanger.getEcardsSentObject().getPointsValue() != null ? dataExchanger.getEcardsSentObject().getPointsValue() : 0;

        int availablePointsBefore = availablePoints;
        int allocatedPointsBefore = allocatedPoints;

        Thread.sleep(3000);
        response = ecardsAwardingUsersActions.getEcardsAwardingStats(200);
        int availablePointsAfter = response.getInt("availablePoints");
        int allocatedPointsAfter = response.getInt("allocatedPoints");


        assertThat("Stats availablePoints improperly decreased: ", availablePointsAfter, is(availablePointsBefore - (usersNo * points)));
        assertThat("Stats allocatedPoints improperly increased: ", allocatedPointsAfter, is(allocatedPointsBefore + (usersNo * points)));
    }


    @Then("^User award points call with incorrect data returns error message '(.+?)', '(.+?)', '(.+?)'$")
    public void awardingUsersErrorMessageValidation(int status, String error, String message) throws Throwable {
        String managerToUserMin = "";
        String managerToUserMax = "";

        if (!message.contains("authentication") && status != 403) {
            //get global min max reason limits
            if (dataExchanger.getEcardsSentObject().getReasonId() != null) {
                ResponseContainer reasonResponse = ecardsReasonsActions.getEcardsReasonById(dataExchanger.getEcardsSentObject().getReasonId(), 200);
                managerToUserMin = reasonResponse.getString("managerToUserMin");
                managerToUserMax = reasonResponse.getString("managerToUserMax");
            }
        }

        new GenericSteps().getGenericErrorMessageValidation("User award points call", status, error, String.format(message, managerToUserMin, managerToUserMax));
    }


    @Then("^User award points call for incorrect data not reduced IAT admin limit$")
    public void awardingUsersErrorNotReducedLimit() throws Throwable {
        if (!dataExchanger.getSessionId().equals("")) {
            String pointsAllocationLimitForDepartmentAfter = ecardsPointsAllocationActions.getEcardsAllocationLimit(200).toString();
            assertThat("Limit was changed!", pointsAllocationLimitForDepartmentAfter, is(pointsAllocationLimitForDepartment));
        }
    }


    @Given("^ePoints user balance before awarding by iat Admin is known$")
    public void epointsUserBalanceBefore() throws Throwable {
        balance = userRepository.findBalanceByUserId(dataExchanger.getEcardsSentObject().getUsersKey().get(0)).getConfirmed();
    }

    @Given("^ePoints user \\(from departmentName '(.+?)'\\) balance before awarding by iat Admin is known$")
    public void epointsUserFromDepartmentNameBalanceBefore(String departmentName) throws Throwable {
        balance = userRepository.findBalanceByUserId(new EcardsUsersSearchActions().getEcardsUser(Config.getDepartmentIdForName(departmentName), "User", 200).getList("uuid").get(0).toString()).getConfirmed();
    }


    @Then("^ePoints user balance after awarding by iat Admin was properly increased$")
    public void epointsUserBalanceAfter() throws Throwable {
        int points = dataExchanger.getEcardsSentObject().getPointsValue() != null ? dataExchanger.getEcardsSentObject().getPointsValue() : 0;

        long balanceBefore = balance;
        await("Balance was not increased").ignoreExceptions().atMost(20, SECONDS).pollInterval(1, SECONDS).until(() -> userRepository.findBalanceByUserId(dataExchanger.getEcardsSentObject().getUsersKey().get(0)).getConfirmed() > balanceBefore);
        balance = userRepository.findBalanceByUserId(dataExchanger.getEcardsSentObject().getUsersKey().get(0)).getConfirmed();
        assertThat("User balance was not increased: " + balanceBefore, (balanceBefore + (points)) == balance);
    }
}