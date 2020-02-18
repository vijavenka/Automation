package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.EpointsUserActions;
import com.iat.actions.UsersActions;
import com.iat.domain.ChangePassword;
import com.iat.domain.User;
import com.iat.domain.UserDetailsDoorman;
import com.iat.domain.UserGroupDoorman;
import com.iat.domain.departmentsStructure.Department;
import com.iat.repository.UserRepository;
import com.iat.repository.UserTokenRepository;
import com.iat.repository.impl.UserRepositoryImpl;
import com.iat.repository.impl.UserTokenRepositoryImpl;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;
import com.iat.validators.UsersValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UsersSteps {

    private HooksSteps hooksSteps = new HooksSteps();
    private UsersActions usersActions = new UsersActions();
    private EpointsUserActions epointsUserActions = new EpointsUserActions();
    private UsersValidator usersValidator = new UsersValidator();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private UserRepository userRepository = new UserRepositoryImpl();
    private UserTokenRepository userTokenRepository = new UserTokenRepositoryImpl();
    private ObjectMapper mapper = new ObjectMapper();
    private JdbcDatabaseConnector mySQLConnector_iatAdmin = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin);
    private ResponseContainer response;
    private String token;
    private List<String> emails;


    @When("^Get users list call is made with filters '(.+?)', '(.+?)'$")
    public void getUsersList(String params, int status) throws Throwable {
        response = usersActions.getUsers(params, status);
        dataExchanger.setResponse(response);
    }

    @Then("^Get users list call returns proper contract and data for filters '(.+?)', '(.+?)'$")
    public void getUsersListResponseValidation(String params, String credentials) throws Throwable {
        usersValidator.validateGetUsersList(response, params, credentials);
    }

    //Create, Get, Delete
    @When("^Create new user with following json '(.+?)' call is made '(.+?)'$")
    public void createNewUserJson(String jsonBody, int status) throws Throwable {
        User user;
        if (jsonBody.equalsIgnoreCase("default"))
            user = new User("DEFAULT", "api.test.user", null, new Department("Department level 1 [A]"), "USER", "NONE", "ACTIVE");
        else
            user = mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES).readValue(jsonBody, User.class);
        dataExchanger.setUserObject(user);
        response = usersActions.createUser(user, status);

        if (status == 201) {
            dataExchanger.getUserObject().setId(response.getString("id"));
            //dynamic wait
            usersActions.waitAfterCreated(user.getId());
        } else
            dataExchanger.setResponse(response);
    }

    //Create and edit user - contract and data validation
    @When("^Update user by id '(.+?)' with following json '(.+?)' and '(.+?)' call is made '(.+?)'$")
    public void updateUserById(String id, String jsonBody, String emailUpdateType, int status) throws Throwable {
        if (id.equals("previous_call"))
            id = dataExchanger.getUserObject().getId();

        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        User user = mapper.readValue(jsonBody, User.class);
        dataExchanger.getUserObject().update(user);
        response = usersActions.updateUserById(id, dataExchanger.getUserObject(), emailUpdateType, status);

        if (status == 200) {
            //dynamic wait
            if (!dataExchanger.getUserObject().getEmail().equals("api_test_admin_own_profile@api.iat.admin.pl"))
                usersActions.waitUntilUserUpdate(id, "employeeNumber", dataExchanger.getUserObject().getEmployeeNumber());
            else
                usersActions.waitUntilUserUpdate(id, "name", dataExchanger.getUserObject().getName());
        } else
            dataExchanger.setResponse(response);
    }

    @Then("^Get user by id '(.+?)' call is made '(.+?)'$")
    public void getUserById(String id, int status) throws Throwable {
        if (id.equals("previous_call"))
            id = dataExchanger.getUserObject().getId();

        response = usersActions.getUserById(id, status);
        dataExchanger.setResponse(response);
    }

    @Then("^(.+?) for incorrect data returns error message '(.+?)', '(.+?)', '(.+?)' - enriched message$")
    public void getDeleteEnableErrorMessageValidation(String method, int status, String error, String message) throws Throwable {
        System.out.println("\nError message validation for: '" + method + "'");

        message = String.format(message, dataExchanger.getUserObject().getId());
        new GenericSteps().getGenericErrorMessageValidation(method, status, error, message);
    }


    @When("^Delete user by id '(.+?)' call is made '(.+?)'$")
    public void deleteUserById(String id, int status) throws Throwable {
        if (id.equals("previous_call"))
            id = dataExchanger.getUserObject().getId();

        response = usersActions.deleteUserById(id, status);
        dataExchanger.getUserObject().setStatus("deleted");
        dataExchanger.setResponse(response);
    }

    @When("^Delete user by id '(.+?)' call is made '(.+?)' for DEBUG$")
    public void deleteUserByIdDEBUG(String id, int status) throws Throwable {
        getUserById(id, 200);
        dataExchanger.setUserObject(response.getAsObject(User.class));
        response = usersActions.deleteUserById(id, status);
        dataExchanger.getUserObject().setStatus("deleted");
        new HooksSteps().deleteUserById();
    }

    @When("^Delete user by id from List call is made to clear environment after tests$")
    public void deleteUserByIdFromListDEBUG() throws Throwable {
        List<User> usersList = response.getList("results", User.class);
        int i = 0;
        for (User user : usersList) {
            if (user.getEmployeeNumber() != null) {
                if (
                        user.getEmployeeNumber().contains("users for milestones ") ||
                                user.getEmployeeNumber().contains("DEFAULT") ||
                                user.getEmployeeNumber().contains("Update ") ||
                                user.getEmployeeNumber().contains("create ") ||
                                user.getEmployeeNumber().contains("Create ")) {
                    i++;
                    System.out.println(i);
                    dataExchanger.setUserObject(user);
                    new HooksSteps().deleteUserById();
                }
            }
        }
    }

    @When("^Enable user by id '(.+?)' call is made '(.+?)'$")
    public void enableUserById(String id, int status) throws Throwable {
        if (id.equals("previous_call"))
            id = dataExchanger.getUserObject().getId();

        response = usersActions.enableUserById(id, status);
        dataExchanger.getUserObject().setStatus("ACTIVE");
        dataExchanger.setResponse(response);
    }

    @Given("^IAT admin request for his profile details and store id$")
    public void iatAdminUserGetProfile() throws Throwable {
        response = usersActions.getUserProfile();
        String id = response.getString("id");

        response = usersActions.getUserById(id, 200);
        User user = response.getAsObject(User.class);
        dataExchanger.setUserObject(user);
        dataExchanger.getUserObject().setId(id);
    }


    @When("^Bulk upload users call is made with data '(.+?)', '(.+?)'$")
    public void bulUploadUsers(String fileName, int status) throws Throwable {
        String uuid = new UserRepositoryImpl().findByEmail("bulk.upload.user.1@iat.admin").getUuid();

        if (uuid != null) {
            dataExchanger.setUserObject(usersActions.getUserById(uuid, 200).getAsObject(User.class));
            hooksSteps.deleteUserById();
        }
        response = usersActions.bulkUpload(fileName, status);
    }

    //Scenario Outline: Change user password - correct password change
    @When("^Admin change his password using following data '(.+?)', '(.+?)'$")
    public void changeAdminPassword(String jsonBody, int status) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        ChangePassword changePassword = mapper.readValue(jsonBody, ChangePassword.class);
        dataExchanger.setChangePasswordObject(changePassword);

        response = usersActions.changeUserPassword(changePassword, status);
        dataExchanger.setResponse(response);
    }

    @Then("^Admin is able to login into IAT admin with new password '(.+?)'$")
    public void loginIntoAdminWithNewPassword(String oldCredentials) throws Throwable {
        new LoginSteps().iatAdminUserLogIn(oldCredentials.split(",")[0] + "," + dataExchanger.getChangePasswordObject().getNewPassword());
        assertThat("SessionId is null", dataExchanger.getSessionId(), not(isEmptyOrNullString()));
    }


    //Scenario Outline: Reset user password - correct user password reset and check if can login to admin
    //Remind user password - remind password, token verification
    @When("^Admin will send remind his password request '(.+?)', '(.+?)'$")
    public void remindUserPassword(String email, int status) throws Throwable {
        String jsonBody = "{\"email\": \"" + email + "\"}";
        response = usersActions.remindUserPassword(jsonBody, status);
        dataExchanger.setResponse(response);
    }


    @When("^Admin set new password '(.+?)', '(.+?)'$")
    public void resetAdminPassword(String passwords, String tncAccepted) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        ChangePassword changePassword = mapper.readValue(passwords, ChangePassword.class);
        dataExchanger.setChangePasswordObject(changePassword);
//        String jsonBody = "{\"token\": \"" + token + "\", \"password\": \"" + dataExchanger.getChangePasswordObject().getNewPassword() + "\"}";

        String jsonBody;
        if(tncAccepted.equals("NULL")){
            jsonBody = "{\"token\": \"" + token + "\", " +
                    "\"password\": \"" + dataExchanger.getChangePasswordObject().getNewPassword() + "\"" +
                    "}";
        }
        else{
            jsonBody = "{\"token\": \"" + token + "\", " +
                    "\"password\": \"" + dataExchanger.getChangePasswordObject().getNewPassword() + "\"," +
                    "\"tnCAccepted\":" + dataExchanger.getChangePasswordObject().isTnCAccepted()  + "}";
        }

        System.out.println(jsonBody);
        response = usersActions.resetUserPassword(jsonBody, 200);
        dataExchanger.setResponse(response);
    }

    //Scenario Outline: Reset user password - incorrect password reset
    @When("^Admin set new password with incorrect data '(.+?)', '(.+?)', '(.+?)'$")
    public void resetAdminPassword(String newPassword, String tokenCase, int status) throws Throwable {
        String jsonBody = "";
        if (tokenCase.equals("correct"))
            jsonBody = "{\"token\": \"" + token + "\", \"password\": " + (newPassword.equals("null") ? "null" : "\"" + newPassword + "\"") + "}";
        else if (tokenCase.equals("incorrect"))
            jsonBody = "{\"token\": \"incorrectToken\", \"password\": \"" + newPassword + "\"}";
        else if (tokenCase.equals("null"))
            jsonBody = "{\"token\": null, \"password\": \"" + newPassword + "\"}";
        response = usersActions.resetUserPassword(jsonBody, status);
        dataExchanger.setResponse(response);
    }

    //Scenario Outline: Set password - account activation
    @When("^New admin activate account and set password for the first time '(.+?)', '(.+?)'$")
    public void setAdminPasswordForTheFirstTime(String passwords, String tncAccepted) throws Throwable {
        verifyToken("REGISTER", dataExchanger.getUserObject().getEmail());
        resetAdminPassword(passwords, tncAccepted);
    }


    @Then("^Get user by id response details are the same as for (.+?) user$")
    public void getUserByIdDataValidation(String createdOrUpdated) throws Throwable {
        getUserById("previous_call", 200);
        usersValidator.validateUserDetails(response);

    }

    @Then("^Epoints user email is correct after changing email in iat admin '(.+?)'$")
    public void validateEpointsEmailChangedIfNeeded(String emailUpdateType) throws Throwable {
        usersValidator.validateEpointsEmailChangedIfNeededAfterEmailUpdateInIatAdmin(emailUpdateType);
    }


    @Then("^User account properly (.+?) for dynamo and mySql$")
    public void user_account_properly_createdUpdatedInDynamo(String statusToValidate) throws Throwable {
        statusToValidate = statusToValidate.equals("created") || statusToValidate.equals("updated") ? "active" : statusToValidate;

        //validate dynamo - proper details and groups add
        UserDetailsDoorman userDetailsDoorman = userRepository.findById(dataExchanger.getUserObject().getId());

        boolean groupFound = false;
        for (UserGroupDoorman userGroupDoorman : userDetailsDoorman.getUserGroupDoormen()) {
            if (String.valueOf(userGroupDoorman.getPartnerId()).equals(Config.getTestPartnerId())) {
                groupFound = true;
                System.out.println("Found group: " + userGroupDoorman);
                usersValidator.validateUserDetailsInDynamoAndMySql(userGroupDoorman, statusToValidate);
                break;
            }
        }
        assertThat("Not found group for Partner: " + Config.getTestPartnerId(), groupFound, is(true));
    }


    @Given("^Created account is verified on epoints side '(.+?)', '(.+?)'$")
    public void verifyEpointsAccountIfNeeded(String shouldBeVerified, String globalPasswordSet) throws Throwable {
        if (shouldBeVerified.equals("true") && globalPasswordSet.equals("false"))
            epointsUserActions.confirmEmail(userTokenRepository.getTokens(dataExchanger.getUserObject().getId()).get(0).getToken(), "password", "firstname", "lastName", "MALE", 302);
    }

    @When("^Check if the prompt needs to be shown call is done for user '(.+?)'$")
    public void callPromptToBeDisplayedRequest(String userUuid) throws Throwable {
        if (userUuid.equals("previous_call"))
            userUuid = dataExchanger.getUserObject().getId();
        response = usersActions.emailChangePromptToBeShown(userUuid, 200);
    }

    @Then("^In response we receive information if prompt needs to be shown according to '(.+?)', '(.+?)'$")
    public void checkCorrectnessOfPromptToBeDisplayedResponse(String verifiedOnEpointsSide, String globalPasswordSet) throws Throwable {
        usersValidator.checkCorrectnessOfPromptToBeDisplayedResponse(response, verifiedOnEpointsSide, globalPasswordSet);
    }


    @Then("^Bulk upload users call made for data returns proper response$")
    public void bulUploadUsersResponseValidation() throws Throwable {
        String extractedStatus = response.getString("status");
        String extractedKey = response.getString("key");

        assertThat("Improper status", extractedStatus, isIn(asList("ok", "in progress")));
        assertThat("Incorrect Key after uploading users file", extractedKey, not(isEmptyOrNullString()));
    }

    @Then("^Bulk uploaded (.+?) user was properly created$")
    public void bulkUploadUserCreatedValidation(String userType) throws Throwable {
        if (userType.equalsIgnoreCase("DEFAULT")) {
            String uuid = null;
            int counter = 0;
            System.out.println("Wait started!");
            while (uuid == null) {
                uuid = new UserRepositoryImpl().findByEmail("bulk.upload.user.1@iat.admin").getUuid();
                if (counter > 60)
                    break;

                Thread.sleep(1000);
                counter++;
                System.out.println("\nSleep loop: " + counter + "\n");
            }

            dataExchanger.setUserObject(usersActions.getUserById(uuid, 200).getAsObject(User.class));

            assertThat("employeeNumber field value incorrect!", dataExchanger.getUserObject().getEmployeeNumber(), is("Bulk upload"));
            assertThat("name field value incorrect!", dataExchanger.getUserObject().getName(), is("Bulk User"));
            assertThat("gender field value incorrect!", dataExchanger.getUserObject().getGender(), is("MALE"));
            assertThat("birthDate field value incorrect!", dataExchanger.getUserObject().getBirthDate(), is("1986-01-31"));
            assertThat("companyStartDate field value incorrect!", dataExchanger.getUserObject().getCompanyStartDate(), is("2010-01-01"));
            assertThat("department.name field value incorrect!", dataExchanger.getUserObject().getDepartment().getName(), is("Department level 2 [D.1]"));
            assertThat("role field value incorrect!", dataExchanger.getUserObject().getRole(), is("USER"));
            assertThat("adminRole field value incorrect!", dataExchanger.getUserObject().getAdminRole(), is("NONE"));
        } else
            assertThat("CASE not defined!!", is(false));

    }

    @Then("^New Admin is able to login into IAT admin with new password$")
    public void newAdminIsAbleToLogInWithNewPassword() throws Throwable {
        loginIntoAdminWithNewPassword(dataExchanger.getUserObject().getEmail());
    }

    @Then("^Proper verification token '(.+?)' is generated for email '(.+?)'$")
    public void verifyToken(String tokenType, String email) throws Throwable {
        Thread.sleep(2000);
        System.out.println("SELECT tokenValue FROM UserToken WHERE tokenType = '" + tokenType + "' AND user_id = (SELECT id FROM User WHERE username = '" + email + "') ORDER BY id DESC");
        token = mySQLConnector_iatAdmin.getSingleResult("SELECT tokenValue FROM UserToken WHERE tokenType = '" + tokenType + "' AND user_id = (SELECT id FROM User WHERE username = '" + email + "') ORDER BY id DESC");
        assertThat("Token was not created in UserToken: " + token, token, is(not("")));
        response = usersActions.verifyUserToken(token, 200);
    }

    @Then("^Verification token will not be generated '(.+?)', '(.+?)', '(.+?)', '(.+?)'$")
    public void checkIfTokenWasNotGenerated(String email, int status, String error, String message) throws Throwable {
        new GenericSteps().getGenericErrorMessageValidation("Verification Token", status, error, String.format(message, email));
    }


    @When("^Created user email will be updated (\\d+) times$")
    public void updateUserEmailSeveralTimes(int howManyEmails) throws Throwable {
        String userId = dataExchanger.getUserObject().getId();
        User user;

        emails = new ArrayList<>(howManyEmails);
        emails.add(dataExchanger.getUserObject().getEmail());

        for (int i = 1; i < howManyEmails; i++) {
            String email = "email" + new Date().getTime() + "@pl.pl";
            emails.add(email);

            user = new User("Update Before 1", email, "FirstName LastName", new Department("Department level 1 [A]"), "USER", "NONE", "ACTIVE");
            response = usersActions.updateUserById(userId, user, "ADMIN_ONLY", 200);
            Thread.sleep(1000);
        }

        usersActions.waitForEmailChangesHistoryIsUpToDate(emails.size() - 1, userId);
    }

    @Then("^History of email changes is properly returned by user details request$")
    public void checkUserEmailChangesCorrectness() throws Throwable {
        usersValidator.checkUserEmailChangesHistoryCorrectness(dataExchanger.getUserObject(), emails);
    }


    @Then("^Confirm new epoints account created via iatAdmin$")
    public void confirm_new_epoints_account_created_via_iatAdmin() throws Throwable {
        if (!dataExchanger.getUserObject().getName().contains("Not confirmed")) {
            System.out.println("USER Id: " + dataExchanger.getUserObject().getId());
            String uuid = userRepository.findByEmail(dataExchanger.getUserObject().getEmail()).getUuid();
            System.out.println("UUID: " + uuid);
            String token = userRepository.findByUUID(uuid).getToken();
            System.out.println("Token: " + token);

            epointsUserActions.confirmEmail(userTokenRepository.getTokens(dataExchanger.getUserObject().getId()).get(0).getToken(), "P@ssw0rd", dataExchanger.getUserObject().getName().split(" ")[0], dataExchanger.getUserObject().getName().split(" ")[1], dataExchanger.getUserObject().getGender(), 302);
        }
    }
}



