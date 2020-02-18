package com.iat.steps;

import com.iat.Config;
import com.iat.actions.MilestonesActions;
import com.iat.actions.UsersActions;
import com.iat.domain.EcardsConfig.milestones.MilestoneValue;
import com.iat.domain.EcardsConfig.milestones.MilestonesConfig;
import com.iat.domain.User;
import com.iat.domain.departmentsStructure.Department;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;
import com.iat.validators.MilestonesValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.iat.utils.DateTimeUtil.Format.yyyyMMdd;
import static com.iat.utils.DateTimeUtil.*;
import static java.util.Arrays.asList;

public class MilestonesSteps {

    private MilestonesActions milestonesActions = new MilestonesActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ResponseContainer response;
    private MilestonesValidator milestonesValidator = new MilestonesValidator();
    private LoginSteps loginSteps = new LoginSteps();
    private EcardsConfigSteps ecardsConfigSteps = new EcardsConfigSteps();
    private UsersActions usersActions = new UsersActions();
    private JdbcDatabaseConnector mySQLConnector_iatAdmin = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin);

    @When("^He add '(.+?)' milestone for specified milestoneType '(.+?)', '(\\d+)'$")
    public void addMilestoneForMilestoneType(String milestoneVal, String milestoneType, int status) throws Throwable {
        int value = milestoneVal.equals("random") ? new Random().nextInt(1000) : Integer.parseInt(milestoneVal);
        MilestoneValue milestoneValue = new MilestoneValue(value);
        dataExchanger.setMilestoneValue(milestoneValue);
        response = milestonesActions.postMilestonesForType(milestoneType, milestoneValue, status);
    }

    @Then("^Milestone will (.+?) available on '(.+?)' milestones list$")
    public void checkIfAddedMilestoneOnMilestonesList(String availability, String milestoneType) throws Throwable {
        boolean available = !availability.contains("not");

        response = milestonesActions.getMilestonesForType(milestoneType, 200);
        milestonesValidator.checkIfMilestoneOnMilestonesList(response, dataExchanger.getMilestoneValue(), available);
    }

    @When("^He remove '(.+?)' milestone for specified milestoneType '(.+?)', '(\\d+)'$")
    public void removeMilestoneForMilestoneType(String milestoneId, String milestoneType, int status) throws Throwable {
        if (milestoneId.equals("previous_call"))
            milestoneId = milestonesActions.getMilestoneIdByValue(milestoneType, dataExchanger.getMilestoneValue().getValue(), 200);

        milestonesActions.deleteMilestonesForType(milestoneType, milestoneId, status);
    }

    @When("^He get milestones list for specified milestoneType '(.+?)', '(\\d+)'$")
    public void getMilestoneForMilestoneType(String milestoneType, int status) throws Throwable {
        response = milestonesActions.getMilestonesForType(milestoneType, status);
    }

    @Given("^Birthday is (.+?) and workAnniversary is (.+?) for company$")
    public void setMilestonesConfigProperly(String birthdayEnabled, String workAnniversaryEnabled) throws Throwable {
        boolean birthday = birthdayEnabled.equals("enabled");
        boolean workAnniversary = workAnniversaryEnabled.equals("enabled");

        loginSteps.iatAdminUserLogIn(Config.getSuperAdminCredentials());
        List<MilestonesConfig> milestonesConfigList = asList(new MilestonesConfig("birthdate", birthday), new MilestonesConfig("workAnniversary", workAnniversary));
        ecardsConfigSteps.setEcardsSettingsByType("milestones", milestonesConfigList.toString());
        //switch to above line when config calls will be available
        mySQLConnector_iatAdmin.execute("UPDATE ECardsSettings SET birthdateVisibility = " + birthday + " WHERE partnerId = '" + Config.getTestPartnerId() + "'");
        mySQLConnector_iatAdmin.execute("UPDATE ECardsSettings SET workAnniversaryVisibility = " + workAnniversary + " WHERE partnerId = '" + Config.getTestPartnerId() + "'");

    }

    @When("^He get users list for specified milestoneType '(.+?)', '(.+?)', '(\\d+)'$")
    public void getUsersListForMilestoneType(String milestoneType, String allEvents, int status) throws Throwable {
        response = milestonesActions.getUsersListForMilestonesWithType(milestoneType, allEvents, status);
        dataExchanger.setResponse(response);
    }

    @Then("^Created user is on user for milestones list for milestoneValue '(\\d+)' and '(\\d+)', '(true|false)'$")
    public void checkIfUsersForMilestoneListContainsCorrectValues(int milestoneValue, int anniversaryDaysFromToday, boolean shouldBeReturned) throws Throwable {
        milestonesValidator.checkIfUserOnMilestonesList(response, milestoneValue, anniversaryDaysFromToday, shouldBeReturned);
    }

    @Given("^User with correct data is created according to '(.+?)', '(\\d+)', '(\\d+)', '(.+?)'$")
    public void createUserForAnniversary(String milestoneType, int milestoneValue, int anniversaryDaysFromToday, String userDepartmentName) throws Throwable {
        String companyStartDate = "1777-01-01";
        String birthday = "1777-01-01";
        Date date = new Date();

        if (milestoneType.equals("birthdate"))
            birthday = convertToString(adjustDateByYears(adjustDateByDays(date, anniversaryDaysFromToday), (milestoneValue * (-1))), yyyyMMdd);
        else if (milestoneType.equals("workAnniversary"))
            companyStartDate = convertToString(adjustDateByYears(adjustDateByDays(date, anniversaryDaysFromToday), milestoneValue * (-1)), yyyyMMdd);

        User user = new User("users for milestones test", "api.test.user", "FirstName LastName in[" + userDepartmentName + "]", "MALE", birthday, new Department(userDepartmentName), "USER", "NONE", "ACTIVE", companyStartDate, null);
        dataExchanger.setUserObject(user);

        response = usersActions.createUser(user, 201);
        dataExchanger.getUserObject().setId(response.getString("id"));
        //dynamic wait
        usersActions.waitAfterCreated(user.getId());

    }

    @Then("^Default milestones lists should be created for new partner$")
    public void defaultMilestonesCreated() throws Throwable {
        milestonesValidator.validateDefaultMilestoneListsCreatedForPartner();
    }


}