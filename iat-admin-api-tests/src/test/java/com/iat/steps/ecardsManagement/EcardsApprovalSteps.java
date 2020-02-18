package com.iat.steps.ecardsManagement;


import com.iat.actions.LoginActions;
import com.iat.actions.NotificationsActions;
import com.iat.actions.ecardsManagement.EcardsApprovalActions;
import com.iat.actions.ecardsManagement.EcardsUsersSearchActions;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.String.format;
import static org.exparity.hamcrest.date.DateMatchers.sameOrBefore;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class EcardsApprovalSteps {

    private EcardsApprovalActions ecardsApprovalActions = new EcardsApprovalActions();
    private NotificationsActions notificationsActions = new NotificationsActions();
    private EcardsUsersSearchActions ecardsUsersSearchActions = new EcardsUsersSearchActions();
    private LoginActions loginActions = new LoginActions();
    private int approvalListCounter, approvalLiveIconCounter, approvalNotificationsCount, managerNotificationsCount;
    private String approvalID;
    private ResponseContainer response;
    private String jsonBodyAwarding = "{\"reasonId\": REASONID_TO_REPLACE, \"templateId\": TEMPLATEID_TO_REPLACE, \"message\": \"MESSAGE_TO_REPLACE\"POINTS_TO_REPLACE, \"usersKey\": USER_KEY_TO_REPLACE, \"cc\":CC_TO_REPLACE}";


    //Check if approval process is properly turned ON and it's possible to reject
    @Given("^Partner '(.+?)' approvalProcess is turned ON '(.+?)'$")
    public void changePartnerSettingsforApprovalProcess(String credentials, String params) throws Throwable {
        loginActions.userLogIn(credentials);
    }


    @Given("^Approval manager '(.+?)' checks current approvals list count$")
    public void checkApprovalsListItemsCountBeforeEcardsSent(String credentials) throws Throwable {
        //log in as approval manager
        loginActions.userLogIn(credentials);
        response = ecardsApprovalActions.getEcardsApprovalList(200);
        System.out.println("\nStart approval List: " + response.toString() + "\n");

        //get count of pending approvals on list
        approvalListCounter = response.getList("").size();

        if (approvalListCounter > 0) {
            response.validateContract("ecards/approval/GET-ecard-approvals.json", 200);

            //check if sorted ascending
            List<Date> extractedCreatedAt = new ArrayList<>();
            response.getList("createdAt", Long.class).forEach(it -> extractedCreatedAt.add(new Date(it)));
            for (int i = 0; i < extractedCreatedAt.size() - 1; i++)
                assertThat("Results (date) are not sorted by asc!", extractedCreatedAt.get(i), is(sameOrBefore(extractedCreatedAt.get(i + 1))));
        }

        //get value for approval Live Icon Counter
        ResponseContainer temporaryResponse = ecardsApprovalActions.getEcardsApprovalCounter(200);
        approvalLiveIconCounter = temporaryResponse.getInt("counter");
    }

    @Then("^Approval manager '(.+?)' checks that he have new pending approves on approvals list$")
    public void checkApprovalsListItemsCountAfterEcardsSent(String credentials) throws Throwable {
        //log in as approval manager
        loginActions.userLogIn(credentials);

        response = ecardsApprovalActions.getEcardsApprovalList(200);
        System.out.println("\nNew approval on List " + response.toString() + "\n");

        //validation for new pending approvals on list
        int approvalListCounterBefore = approvalListCounter;
        approvalListCounter = response.getList("").size();
        assertThat("Pending approval requests count was not increased after sent ecards ", approvalListCounter, is(greaterThan(approvalListCounterBefore)));

        //validation for approval live icon counter
        int approvalLiveIconCounterBefore = approvalLiveIconCounter;
        ResponseContainer temporaryResponse = ecardsApprovalActions.getEcardsApprovalCounter(200);
        approvalLiveIconCounter = temporaryResponse.getInt("counter");
        assertThat("Approval live icon counter was not increased after sent ecards in approval flow", approvalLiveIconCounter, is(greaterThan(approvalLiveIconCounterBefore)));


        response.validateContract("ecards/approval/GET-ecard-approvals.json", 200);

        //check if sorted ascending
        List<Date> extractedCreatedAt = new ArrayList<>();
        response.getList("createdAt", Long.class).forEach(it -> extractedCreatedAt.add(new Date(it)));
        for (int i = 0; i < extractedCreatedAt.size() - 1; i++)
            assertThat("Results (date) are not sorted by asc!", extractedCreatedAt.get(i), is(sameOrBefore(extractedCreatedAt.get(i + 1))));
    }


    @Then("^Approval manager '(.+?)' checks current approvals list count was not changed$")
    public void approval_manager_api_admin_test__password_checks_current_approvals_list_count_was_not_changed(String credentials) throws Throwable {
        //log in as approval manager
        loginActions.userLogIn(credentials);
        ResponseContainer temporaryResponse = ecardsApprovalActions.getEcardsApprovalList(200);

        System.out.println("\nApproval List not changed (same as start): " + temporaryResponse.toString() + "\n");
        //check if pending approvals count was not changed
        int approvalListCounterBefore = approvalListCounter;
        approvalListCounter = temporaryResponse.getList("").size();
        assertThat("Pending approval requests count was changed after send ecard without points ", approvalListCounter, is(approvalListCounterBefore));

        //validation for approval live icon counter
        int approvalLiveIconCounterBefore = approvalLiveIconCounter;
        temporaryResponse = ecardsApprovalActions.getEcardsApprovalCounter(200);
        approvalLiveIconCounter = temporaryResponse.getInt("counter");
        assertThat("Approval live icon counter was changed after send ecard without points ", approvalLiveIconCounter, is(approvalLiveIconCounterBefore));

        response.validateContract("ecards/approval/GET-ecard-approvals.json", 200);

        //check if sorted ascending
        List<Date> extractedCreatedAt = new ArrayList<>();
        response.getList("createdAt", Long.class).forEach(it -> extractedCreatedAt.add(new Date(it)));
        for (int i = 0; i < extractedCreatedAt.size() - 1; i++)
            assertThat("Results (date) are not sorted by asc!", extractedCreatedAt.get(i), is(sameOrBefore(extractedCreatedAt.get(i + 1))));

    }


    @Then("^Approval manager '(.+?)' checks current approvals list count was decreased$")
    public void approval_manager_api_admin_test__password_checks_current_approvals_list_count_was_decreased(String credentials) throws Throwable {
        //log in as approval manager
        loginActions.userLogIn(credentials);
        ResponseContainer temporaryResponse = ecardsApprovalActions.getEcardsApprovalList(200);

        System.out.println("\nApprovals List after reject/approved: " + temporaryResponse.toString() + "\n");
        //check if pending approvals count
        int approvalListCounterBeforeReject = approvalListCounter;
        approvalListCounter = temporaryResponse.getList("").size();
        assertThat("Pending approval requests count was not decreased after reject approval pending request ", approvalListCounter, is(approvalListCounterBeforeReject - 1));

        //validation for approval live icon counter
        int approvalLiveIconCounterBeforeReject = approvalLiveIconCounter;
        temporaryResponse = ecardsApprovalActions.getEcardsApprovalCounter(200);
        approvalLiveIconCounter = temporaryResponse.getInt("counter");
        assertThat("Approval live icon counter was not decreased after reject approval pending request ", approvalLiveIconCounter, is(approvalLiveIconCounterBeforeReject - 1));

        response.validateContract("ecards/approval/GET-ecard-approvals.json", 200);

        //check if sorted ascending
        List<Date> extractedCreatedAt = new ArrayList<>();
        response.getList("createdAt", Long.class).forEach(it -> extractedCreatedAt.add(new Date(it)));
        for (int i = 0; i < extractedCreatedAt.size() - 1; i++)
            assertThat("Results (date) are not sorted by asc!", extractedCreatedAt.get(i), is(sameOrBefore(extractedCreatedAt.get(i + 1))));
    }


    @When("^Approval manager '(.+?)' approve pending approval from list$")
    public void approveLastPendingApprovalFromList(String credentials) throws Throwable {
        //log in as approval manager
        loginActions.userLogIn(credentials);

        List<String> approvalsBatchIds = response.getList("id", String.class);
        int lastElementIndex = approvalsBatchIds.size() - 1;
        //approve last added approval
        ecardsApprovalActions.setEcardsApprovalAsApproved(approvalsBatchIds.get(lastElementIndex), 200);
    }

    @Then("^Approval manager '(.+?)' reject pending approvals from list with message '(.+?)'$")
    public void rejectPendingApprovalsFromListWithMessage(String credentials, String message) throws Throwable {
        //log in as approval manager
        loginActions.userLogIn(credentials);

        List<String> approvalsBatchIds = response.getList("id", String.class);
        int lastElementIndex = approvalsBatchIds.size() - 1;
        //reject last added approval
        ecardsApprovalActions.setEcardsApprovalAsRejected(approvalsBatchIds.get(lastElementIndex), "{\"message\": \"" + message + "\"}", 200);
    }


    @Given("^New random Ecard is created by manager '(.+?)' with points value '(.+?)'$")
    public void new_random_pending_approval_is_created(String credentials, String points) throws Throwable {
        //log in as manager to send ecards
        loginActions.userLogIn(credentials);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime date = new DateTime();
        System.out.println();
        String departmentId = "385";
        String params = "9878192;1006;Reason WIZARD custom template 1,Manager to user in Department: " + departmentId + " in APPROVAL FLOW Date: " + formatter.print(date) + " Points value: " + points + ";" + points + ";dynamic;null";

        String[] params2 = params.split(";");
        String reasonId = params2[0].replace("null", "\"\"");
        String templateId = params2[1].replace("null", "\"\"");
        String message = params2[2].replace("null", "");
        String pointsValue = params2[3];
        String uuid = params2[4].replace("null", "");
        String cc = params2[5].replace("null", "");

        //Prepare points value field (because can be empty)
        pointsValue = pointsValue.equals("null") ? "" : ", \"pointsValue\": " + pointsValue;

        //Get users from departments dynamically
        if (uuid.equals("dynamic")) {
            uuid = "";
            ResponseContainer usersResponse = ecardsUsersSearchActions.getEcardsUser(departmentId, "null", 200);
            List<String> extractedUsersKeys = usersResponse.getList("uuid", String.class);
            for (String userKey : extractedUsersKeys)
                uuid += ",\"" + userKey + "\"";
        }
        uuid = ("[" + uuid + "]").replace("[,", "[");
        //build json
        jsonBodyAwarding = jsonBodyAwarding
                .replace("REASONID_TO_REPLACE", reasonId)
                .replace("TEMPLATEID_TO_REPLACE", templateId)
                .replace("MESSAGE_TO_REPLACE", message)
                .replace("POINTS_TO_REPLACE", pointsValue)
                .replace("USER_KEY_TO_REPLACE", uuid)
                .replace("CC_TO_REPLACE", "[" + cc + "]");
    }

    @Given("^IAT Admin user current department allocation limit is known before grant users in approval flow$")
    public void storeIatManagerLimitBeforeSendEcardsWithPointsInApprovalFlow() throws Throwable {
//        iatAdminLimit = Integer.parseInt(ecardsPointsAllocationActions.getEcardsAllocationLimit());
//        System.out.println("\nLimit before: " + iatAdminLimit);
    }

    @Then("^IAT Admin user current department allocation limit was not decreased after send ecard in approval flow$")
    public void checkIfIatManagerAllocationLimitWasNotDecreasedAfterEcardsSendInApprovalFlow() throws Throwable {
//        int iatAdminLimitAfter = Integer.parseInt(ecardsPointsAllocationActions.getEcardsAllocationLimit());
//        System.out.println("\nLimit after send: " + iatAdminLimitAfter);
//        assertThat("Department allocation limit was changed after send ecards in approval, flow was: " + iatAdminLimit + " and was changed to: " + iatAdminLimitAfter, iatAdminLimit, is(iatAdminLimitAfter));

    }

    @Then("^IAT Admin user current department allocation limit was decreased after approve ecard send for points value '(.+?)'$")
    public void iat_Admin_user_current_department_allocation_limit_was_decreased_after_approve_ecard_send(String points) throws Throwable {
        if (points.equals("null"))
            points = "0";
    }


    //Reject approval - Error message validation
    @When("^Approval manager try reject with incorrect data '(.+?)', '(.+?)', '(.+?)'$")
    public void rejectApprovalErrorMessage(String approvalId, String jsonBody, int status) throws Throwable {

        List<String> approvalsBatchIds = ecardsApprovalActions.getEcardsApprovalList(200).getList("id", String.class);
        int lastElementIndex = approvalsBatchIds.size() - 1;

        if (approvalId.equals("approved")) {
            approvalId = approvalsBatchIds.get(lastElementIndex);
            ecardsApprovalActions.setEcardsApprovalAsApproved(approvalId, 200);
        } else if (approvalId.equals("rejected")) {
            approvalId = approvalsBatchIds.get(lastElementIndex);
            ecardsApprovalActions.setEcardsApprovalAsRejected(approvalId, "{\"message\": \"Reject before try reject again\"}", 200);
        } else if (approvalId.equals("dynamic"))
            approvalId = approvalsBatchIds.get(lastElementIndex);

        jsonBody = jsonBody.replace("MORE_THAN_255", "q1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45o2551");
        approvalID = approvalId;
        response = ecardsApprovalActions.setEcardsApprovalAsRejected(approvalId, jsonBody, status);
    }

    @When("^Unauthorized try reject with incorrect data '(.+?)', '(.+?)'$")
    public void rejectApprovalErrorMessage(String jsonBody, int status) throws Throwable {
        response = ecardsApprovalActions.setEcardsApprovalAsRejected("11", jsonBody, status);
    }

    @Then("^Approval rejection made for incorrect data returns error message '(\\d+)', '(.+?)', '(.+?)'$")
    public void rejectApprovalWithIncorrectDataErrorMessageValidation(int status, String error, String message) throws Throwable {
        if (!error.equals("message")) {
            assertThat("Incorrect error!", response.getString("error"), is(error));
            assertThat("Incorrect message!", response.getString("message"), is(format(message, approvalID)));
            assertThat("Incorrect status!", response.getInt("status"), is(status));
            response.validateContract("ErrorResponse-schema.json", -1);
        } else {
            assertThat("Incorrect message for empty message field!", response.getString("errors[0].fieldName"), is(error));
            assertThat("Incorrect message for empty message field!", response.getString("errors[0].message"), is(message));
            response.validateContract("ErrorResponse-schema-fieldsValidation.json", -1);
        }


    }

    //Approve approval - Error message validation
    @When("^Approval manager try approve with incorrect data '(.+?)', '(.+?)'$")
    public void approveApprovalErrorMessage(String approvalId, int status) throws Throwable {
        List<String> approvalsBatchIds = response.getList("id", String.class);
        int lastElementIndex = approvalsBatchIds.size() - 1;

        if (approvalId.equals("approved")) {
            approvalId = approvalsBatchIds.get(lastElementIndex);
            ecardsApprovalActions.setEcardsApprovalAsApproved(approvalId, 200);
        } else if (approvalId.equals("rejected")) {
            approvalId = approvalsBatchIds.get(lastElementIndex);
            ecardsApprovalActions.setEcardsApprovalAsRejected(approvalId, "{\"message\": \"Reject before try approve it\"}", 200);
        } else if (approvalId.equals("dynamic"))
            approvalId = approvalsBatchIds.get(lastElementIndex);

        approvalID = approvalId;
        response = ecardsApprovalActions.setEcardsApprovalAsApproved(approvalId, status);
    }

    @When("^Unathorized try approve with incorrect data '(.+?)'$")
    public void approveApprovalErrorMessage(int status) throws Throwable {
        response = ecardsApprovalActions.setEcardsApprovalAsApproved("11", status);
    }

    @Then("^Approve call made for incorrect data returns error message '(\\d+)', '(.+?)', '(.+?)'$")
    public void approveApprovalWithIncorrectDataErrorMessageValidation(int status, String error, String message) throws Throwable {
        assertThat("Incorrect error!", response.getString("error"), is(error));
        assertThat("Incorrect message!", response.getString("message"), is(format(message, approvalID)));
        assertThat("Incorrect status!", response.getInt("status"), is(status));
        response.validateContract("ErrorResponse-schema.json", -1);

    }


    //Get approval by Id and validate contract
    @When("^Approval manager made request for get approval details by id$")
    public void getApprovalById() throws Throwable {
        List<String> approvalsBatchIds = response.getList("id", String.class);
        int lastElementIndex = approvalsBatchIds.size() - 1;
        response = ecardsApprovalActions.getEcardsApprovalById(approvalsBatchIds.get(lastElementIndex), 200);
    }

    @Then("^Requested approval details are returned with proper contract$")
    public void getApprovalByIdMatchContract() throws Throwable {
        System.out.println("Approval details: " + response.toString());
        response.validateContract("ecards/approval/GET-ecard-approval.json", 200);
    }

    //Get approval by Id - Error message validation
    @When("^Approval manager made request for get approval details by id with incorrect data '(.+?)', '(.+?)'$")
    public void getApprovalByIdErrorMessage(String approvalId, int status) throws Throwable {
        List<String> approvalsBatchIds = ecardsApprovalActions.getEcardsApprovalList(200).getList("id", String.class);
        int lastElementIndex = approvalsBatchIds.size() - 1;

        if (approvalId.equals("approved")) {
            approvalId = approvalsBatchIds.get(lastElementIndex);
            ecardsApprovalActions.setEcardsApprovalAsApproved(approvalId, 200);
        } else if (approvalId.equals("rejected")) {
            approvalId = approvalsBatchIds.get(lastElementIndex);
            ecardsApprovalActions.setEcardsApprovalAsRejected(approvalId, "{\"message\": \"Reject before try get\"}", 200);
        } else if (approvalId.equals("dynamic"))
            approvalId = approvalsBatchIds.get(lastElementIndex);

        approvalID = approvalId;
        response = ecardsApprovalActions.getEcardsApprovalById(approvalId, status);

    }


    @When("^Unathorized made request for get approval details by id with incorrect data '(.+?)'$")
    public void getApprovalByIdErrorMessage(int status) throws Throwable {
        response = ecardsApprovalActions.getEcardsApprovalById("11", status);
    }

    @Then("^Get approval details by id call made for incorrect data returns error message '(\\d+)', '(.+?)', '(.+?)'$")
    public void getApprovalByIdErrorMessageValidation(int status, String error, String message) throws Throwable {
        assertThat("Incorrect error!", response.getString("error"), is(error));
        assertThat("Incorrect message!", response.getString("message"), is(format(message, approvalID)));
        assertThat("Incorrect status!", response.getInt("status"), is(status));
        response.validateContract("ErrorResponse-schema.json", -1);
    }


    //Check if notifications are send to approval admin in approval flow
    @Given("^Approval manager '(.+?)' checks current notifications list count$")
    public void approvalManagerGetCurrentNotificationsList(String credentials) throws Throwable {
        loginActions.userLogIn(credentials);

        response = notificationsActions.getNotifications();
        approvalNotificationsCount = response.getList("id").size();

    }

    @Then("^Approval manager '(.+?)' checks current notifications list count was increased$")
    public void approvalManagerCheckNotificationsListCountIncreased(String credentials) throws Throwable {
        loginActions.userLogIn(credentials);

        response = notificationsActions.getNotifications();

        int approvalNotificationsCountBefore = approvalNotificationsCount;
        approvalNotificationsCount = response.getList("id").size();
        assertThat("Approval admin notifications not increased", approvalNotificationsCount, is(approvalNotificationsCountBefore + 1));

        //check if notifications type are APPROVAL_PENDING
        List<String> types = response.getList("type");
        assertThat("Every notification should match type!", types, everyItem(is("APPROVAL_PENDING")));
    }

    @Then("^Approval manager '(.+?)' mark all notifications as read$")
    public void approvalManagerMarkNotificationsListAsRead(String credentials) throws Throwable {
        loginActions.userLogIn(credentials);

        List<String> ids = response.getList("id", String.class);
        String jsonBody = "";
        for (String id : ids)
            jsonBody += id + ",";
        jsonBody = "[" + jsonBody.substring(0, jsonBody.lastIndexOf(",")) + "]";
        notificationsActions.setNotificationsRead(jsonBody, 200);

        //reject pending notification
        ResponseContainer temporaryResponse = ecardsApprovalActions.getEcardsApprovalList(200);
        List<String> approvalsBatchIds = temporaryResponse.getList("id", String.class);
        int lastElementIndex = approvalsBatchIds.size() - 1;
        //reject last added approval
        ecardsApprovalActions.setEcardsApprovalAsRejected(approvalsBatchIds.get(lastElementIndex), "{\"message\": \"Rejected after test notifications for Approval admin\"}", 200);

    }

    @Then("^Approval manager '(.+?)' checks current notifications list is empty$")
    public void approvalManagerCheckNotificationsListCountEmpty(String credentials) throws Throwable {
        loginActions.userLogIn(credentials);

        response = notificationsActions.getNotifications();
        approvalNotificationsCount = response.getList("id").size();

        assertThat("Approval admin notifications not deleted", approvalNotificationsCount, is(0));
    }

    //Check if notifications are send to Manager admin after aprove/reject pending approval
    @Given("^IAT admin user '(.+?)' checks current notifications list count$")
    public void iatAdminGetCurrentNotyficationsList(String credentials) throws Throwable {
        loginActions.userLogIn(credentials);

        response = notificationsActions.getNotifications();

        managerNotificationsCount = response.getList("id").size();
    }

    @Given("^IAT admin user '(.+?)' checks current notifications list count was not changed$")
    public void iatAdminCurrentNotyficationsListNotChanged(String credentials) throws Throwable {
        loginActions.userLogIn(credentials);

        response = notificationsActions.getNotifications();

        int managerNotificationsCountBefore = managerNotificationsCount;
        managerNotificationsCount = response.getList("id").size();
        assertThat("Admin Manager notifications count was changed after send ecards", managerNotificationsCount, is(managerNotificationsCountBefore));
    }

    @When("^Approval manager '(.+?)' process '(.+?)' pending approvals from list$")
    public void approvalManagerProcessPendingApprove(String credentials, String action) throws Throwable {
        loginActions.userLogIn(credentials);

        List<String> approvalsBatchIds = ecardsApprovalActions.getEcardsApprovalList(200).getList("id", String.class);
        int lastElementIndex = approvalsBatchIds.size() - 1;

        if (action.equals("approve"))
            ecardsApprovalActions.setEcardsApprovalAsApproved(approvalsBatchIds.get(lastElementIndex), 200);

        if (action.equals("reject"))
            ecardsApprovalActions.setEcardsApprovalAsRejected(approvalsBatchIds.get(lastElementIndex), "{\"message\": \"Reject for notifications\"}", 200);
    }

    @Then("^IAT admin user '(.+?)' checks current notifications list count was increased$")
    public void iatAdminCurrentNotyficationsListIncreased(String credentials) throws Throwable {
        loginActions.userLogIn(credentials);

        response = notificationsActions.getNotifications();

        int managerNotificationsCountBefore = managerNotificationsCount;
        managerNotificationsCount = response.getList("id").size();
        assertThat("Admin Manager notifications count was not increased after approve/reject", managerNotificationsCount, is(managerNotificationsCountBefore + 1));
    }

    @Then("^IAT admin user '(.+?)' mark all notifications as read$")
    public void iatAdminSetCurrentNotyficationsListAsRead(String credentials) throws Throwable {
        loginActions.userLogIn(credentials);

        List<String> ids = response.getList("id", String.class);
        String jsonBody = "";
        for (String id : ids)
            jsonBody += id + ",";
        jsonBody = "[" + jsonBody.substring(0, jsonBody.lastIndexOf(",")) + "]";
        notificationsActions.setNotificationsRead(jsonBody, 200);
    }

    @Then("^IAT admin user '(.+?)' checks current notifications list is empty$")
    public void iatAdminCurrentNotyficationsListIsEmpty(String credentials) throws Throwable {
        loginActions.userLogIn(credentials);

        response = notificationsActions.getNotifications();
        managerNotificationsCount = response.getList("id").size();

        assertThat("Admin Manager notifications not deleted", managerNotificationsCount, is(0));
    }
}



