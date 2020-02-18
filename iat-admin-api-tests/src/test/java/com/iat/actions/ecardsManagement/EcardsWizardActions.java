package com.iat.actions.ecardsManagement;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.EcardsConfigActions;
import com.iat.actions.MilestonesActions;
import com.iat.controller.ecardsManagement.EcardsWizardController;
import com.iat.domain.EcardsConfig.Reason;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class EcardsWizardActions {

    private EcardsWizardController ecardsWizardController = new EcardsWizardController();
    private EcardsConfigActions ecardsConfigActions = new EcardsConfigActions();
    private EcardsReasonsActions ecardsReasonsActions = new EcardsReasonsActions();
    private JdbcDatabaseConnector mySQLConnector_iatAdmin = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin);
    private JdbcDatabaseConnector mySQLConnector_PointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager);
    private ObjectMapper mapper = new ObjectMapper();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ResponseContainer response;

    public void clearPartnerIatAdminSettings() {
        //clear setting and reasons -> to simulate firs log in

        String query = "DELETE FROM ECardsSettings WHERE partnerId =" + Config.getWizardTestPartnerId();
        mySQLConnector_iatAdmin.execute(query);
        System.out.println(query);
        query = "DELETE FROM ECardsReason WHERE partnerId =" + Config.getWizardTestPartnerId();
        mySQLConnector_iatAdmin.execute(query);
        System.out.println(query);
        query = "DELETE FROM Tag where partnerId =" + Config.getWizardTestPartnerId();
        mySQLConnector_PointsManager.execute(query);
        System.out.println(query);

        //For clear milestones table
        new MilestonesActions().clearPartnerIatAdminMilestones();
    }


    public ResponseContainer getEcardsWizardStatus(int status) {
        return initResponseContainer(ecardsWizardController.getEcardsWizardStatus(status));
    }

    public ResponseContainer setEcardsWizardStep(String step, int status) {
        return initResponseContainer(ecardsWizardController.setEcardsWizardStep(step, status));
    }

    public void wizardSetUpConfigToStepNo(int number) throws Throwable {

        if (number > 0) {
            String globalConfigJson = "{\"userSharePointsRange\": \"SAME_DEPARTMENT\", \"managerSharePointsRange\": \"SAME_COMPANY\", \"sharePointsWithManager\": true, \"approvalProcess\": \"NONE\"}";
            ecardsConfigActions.setEcardsSettingsForType("pointsSharing", globalConfigJson, 200);
            setEcardsWizardStep("1", 200);
        }

        if (number > 1) {
            String globalReasonLimitation = "{\"managerToUserMin\": 10, \"managerToUserMax\": 2000000, \"userToUserMin\": 2, \"userToUserMax\": 1000000}";
            ecardsConfigActions.setEcardsSettingsForType("reasons", globalReasonLimitation, 200);
            setEcardsWizardStep("2", 200);
        }

        if (number > 2) {
            String globalEcards = "{\"useDefaultTemplatesSet\": true}";
            ecardsConfigActions.setEcardsSettingsForType("templates", globalEcards, 200);
            setEcardsWizardStep("3", 200);
        }

        if (number > 3) {
            String reasonForWizard = "{\"name\":\"Reason for wizard 1st login \",\"managerToUserReasonRange\":\"GLOBAL\",\"userToUserReasonRange\":\"GLOBAL\"}";

            mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
            Reason reason = mapper.readValue(reasonForWizard, Reason.class);
            dataExchanger.setReasonObject(reason);
            response = ecardsReasonsActions.createNewEcardsReason(reason.toJsonRequest(), 201);
            dataExchanger.getReasonObject().setId(response.toString());

            assertThat("Create new ecards reason response is without Id!!", response.toString(), not(isEmptyOrNullString()));
            setEcardsWizardStep("4", 200);
        }
    }

    public ResponseContainer trySetUpWizardConfig(int stepNumber, int status) {
        switch (stepNumber) {
            case 1:
                String globalConfigJson = "{\"userSharePointsRange\": \"SAME_DEPARTMENT\", \"managerSharePointsRange\": \"SAME_COMPANY\", \"sharePointsWithManager\": true, \"approvalProcess\": \"NONE\"}";
                return ecardsConfigActions.setEcardsSettingsForType("pointsSharing", globalConfigJson, status);
            case 2:
                String globalReasonLimitation = "{\"managerToUserMin\": 10, \"managerToUserMax\": 2000000, \"userToUserMin\": 2, \"userToUserMax\": 1000000}";
                return ecardsConfigActions.setEcardsSettingsForType("reasons", globalReasonLimitation, status);
            case 3:
                String globalEcards = "{\"useDefaultTemplatesSet\": true}";
                return ecardsConfigActions.setEcardsSettingsForType("templates", globalEcards, status);
            default:
                assertThat("Wrong step number!", stepNumber, isOneOf(1, 2, 3));
                return response;
        }
    }
}
