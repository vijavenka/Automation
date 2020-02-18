package com.iat.actions.ecardsManagement;


import com.iat.Config;
import com.iat.actions.EcardsConfigActions;
import com.iat.actions.UsersActions;
import com.iat.controller.ecardsManagement.EcardsAwardingUsersController;
import com.iat.domain.EcardsConfig.PointsSharing;
import com.iat.domain.ecardsAwarding.EcardsSent;
import com.iat.steps.LoginSteps;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;

import java.util.ArrayList;
import java.util.List;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static java.util.Collections.singletonList;


public class EcardsAwardingUsersActions {

    private LoginSteps loginSteps = new LoginSteps();
    private EcardsAwardingUsersController ecardsAwardingUsersController = new EcardsAwardingUsersController();
    private EcardsConfigActions ecardsConfigActions = new EcardsConfigActions();
    private DataExchanger dataExchanger = DataExchanger.getInstance();


    public ResponseContainer getEcardsAwardingPoints(String params, int status) {
        String[] params2 = params.split(",");
        String sortField = params2[0];
        String ascending = params2[1];
        String page = params2[2];
        String maxResults = params2[3];
        String dateFrom = params2[4];
        String dateTo = params2[5];
        String from = params2[6];
        String to = params2[7];
        String points = params2[8];
        String amount = params2[9];
        String senderDepartment = params2[10];
        String receiverDepartment = params2[11];
        String approvalStatus = params2[12];

        return initResponseContainer(ecardsAwardingUsersController.getEcardsAwardingPoints(sortField, ascending, page, maxResults,
                dateFrom, dateTo, from, to, points, amount, senderDepartment, receiverDepartment, approvalStatus, status));
    }


    public ResponseContainer setEcardsAwardingPoints(EcardsSent jsonBodyObject, int status) {
        return initResponseContainer(ecardsAwardingUsersController.setEcardsAwardingPoints(jsonBodyObject, status));
    }


    public ResponseContainer getEcardsAwardingStats(int status) {
        return initResponseContainer(ecardsAwardingUsersController.getEcardsAwardingStats(status));
    }


    public List<String> getDynamicUsersUuids(List<String> usersKey) throws Throwable {
        List<String> randomUsers = new ArrayList<>();

        if (usersKey.get(0).equalsIgnoreCase("DYNAMIC")) {
            for (int i = 1; i < usersKey.size() - 1; i++) {
                if (i % 2 == 1) {
                    String departmentName = usersKey.get(i);
                    int howMany = Integer.parseInt(usersKey.get(i + 1));

                    List<String> uuids = new EcardsUsersSearchActions().getEcardsUser(Config.getDepartmentIdForName(departmentName), "User", 200).getList("uuid");

                    if (uuids.size() > howMany) {
                        for (int u = 0; u < howMany; u++)
                            randomUsers.add(uuids.get(u));
                    } else
                        randomUsers = uuids;
                }
            }
        }

        if (usersKey.get(0).equalsIgnoreCase("DYNAMIC-MANAGER")) {
            String departmentName = usersKey.get(1);
            String search = usersKey.get(2);

            String copyCurrentUserSessionId = DataExchanger.getInstance().getSessionId();
            //login as user which can edit company settings

            loginSteps.iatAdminUserLogIn(Config.getSuperAdminCredentials());
            //edit sharing config: turn on shareWithManager to be able search manager
            PointsSharing pointsSharing = ecardsConfigActions.getEcardsSettingsForType("pointsSharing", 200).getAsObject(PointsSharing.class);
            boolean sharePointsWithManager = pointsSharing.getSharePointsWithManager();
            pointsSharing.setSharePointsWithManager(true);

            ecardsConfigActions.setEcardsSettingsForType("pointsSharing", pointsSharing.toJsonRequest(), 200);
            randomUsers = new EcardsUsersSearchActions().getEcardsUser(Config.getDepartmentIdForName(departmentName), search, 200).getList("uuid");

            //restore sharing setting
            pointsSharing.setSharePointsWithManager(sharePointsWithManager);
            ecardsConfigActions.setEcardsSettingsForType("pointsSharing", pointsSharing.toJsonRequest(), 200);

            //restore credentials
            dataExchanger.setSessionId(copyCurrentUserSessionId);
        }


        if (usersKey.get(0).equalsIgnoreCase("PARENT_DEPARTMENT")) {
            int howMany = Integer.parseInt(usersKey.get(1));

            List<String> uuids = DataExchanger.getInstance().getResponse().getList("uuid");

            if (uuids.size() >= howMany)
                for (int u = 0; u < howMany; u++)
                    randomUsers.add(uuids.get(u));
        }


        if (usersKey.get(0).equalsIgnoreCase("OWN_ACCOUNT"))
            randomUsers = singletonList(new UsersActions().getUserProfile().getString("id"));

        return randomUsers;
    }


}
