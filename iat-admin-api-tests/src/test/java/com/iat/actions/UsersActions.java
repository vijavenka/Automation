package com.iat.actions;


import com.iat.controller.UsersController;
import com.iat.domain.ChangePassword;
import com.iat.domain.User;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static com.jayway.awaitility.Awaitility.await;
import static java.util.concurrent.TimeUnit.SECONDS;


public class UsersActions {

    private UsersController usersController = new UsersController();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private int sleep = 1000;
    private int limit = 120;

    public ResponseContainer createUser(User user, int status) {
        return initResponseContainer(usersController.createUser(user, status));
    }

    public ResponseContainer getUsers(String params, int status) {
        String[] params2 = params.split(",");
        String sortField = params2[0];
        String ascending = params2[1];
        String dateFrom = params2[2];
        String dateTo = params2[3];
        String departmentName = params2[4];
        String manager = params2[5];
        String user = params2[6];
        String statusParam = params2[7];
        String page = params2[8];
        String maxResults = params2[9];

        return initResponseContainer(usersController.getUsers(sortField, ascending, dateFrom, dateTo, departmentName,
                manager, user, statusParam, page, maxResults, status));
    }

    public ResponseContainer getUserById(String userId, int status) {
        return initResponseContainer(usersController.getUserById(userId, status));
    }

    public ResponseContainer updateUserById(String userId, User user, String emailUpdateType, int status) {
        return initResponseContainer(usersController.updateUserById(userId, user, emailUpdateType, status));
    }

    public ResponseContainer deleteUserById(String userId, int status) {
        return initResponseContainer(usersController.deleteUserById(userId, status));
    }

    public ResponseContainer deleteUserByIdIgnoreStatus(String userId) {
        return initResponseContainer(usersController.deleteUserByIdIgnoreStatus(userId));
    }

    public ResponseContainer enableUserById(String userId, int status) {
        return initResponseContainer(usersController.enableUserById(userId, status));
    }

    public ResponseContainer getUserProfile() {
        return getUserProfile(200);
    }

    public ResponseContainer getUserProfile(int status) {
        return initResponseContainer(usersController.getUserMe(status));
    }

    public ResponseContainer bulkUpload(String fileName, int status) {
        return initResponseContainer(usersController.bulkUpload(fileName, status));
    }

    public ResponseContainer changeUserPassword(ChangePassword changePassword, int status) {
        return initResponseContainer(usersController.changeUserPassword(changePassword, status));
    }

    public ResponseContainer remindUserPassword(String jsonBody, int status) {
        return initResponseContainer(usersController.remindUserPassword(jsonBody, status));
    }

    public ResponseContainer resetUserPassword(String jsonBody, int status) {
        return initResponseContainer(usersController.resetUserPassword(jsonBody, status));
    }

    public ResponseContainer verifyUserToken(String token, int status) {
        return initResponseContainer(usersController.verifyUserToken(token, status));
    }


    public void waitAfterCreated(String userId) throws Throwable {
        int counter = 0;
        System.out.println("Wait started!");
        while (usersController.getUserByIdWait(userId)) {
            if (counter > limit)
                break;

            Thread.sleep(sleep);
            System.out.println("Sleep loop: " + ++counter + "\n");
        }
        System.out.println("Wait Ends!");
    }

    public void waitUntilUserUpdate(String userId, String fieldName, String fieldValue) {
        await("Updating user processed").ignoreExceptions()
                .atMost(60, SECONDS).pollInterval(5, SECONDS)
                .until(() -> getUserById(userId, 200).getString(fieldName).equalsIgnoreCase(fieldValue));
    }


    public ResponseContainer emailChangePromptToBeShown(String userUuid, int status) {
        return initResponseContainer(usersController.emailChangePromptToBeShown(userUuid, status));
    }

    public void waitForEmailChangesHistoryIsUpToDate(int historyLength, String userId) throws InterruptedException {
        int counter = 0;
        System.out.println("Wait started!");

        User user = getUserById(userId, 200).getAsObject(User.class);

        while (user.getEmailChanges().size() != historyLength) {
            if (counter > limit)
                break;

            Thread.sleep(sleep);
            counter++;
            System.out.println("Sleep loop: " + counter + "\n");

            user = getUserById(userId, 200).getAsObject(User.class);
        }
        System.out.println("Wait Ends!");
        dataExchanger.setUserObject(user);
    }

}