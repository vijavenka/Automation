package com.iat.actions;

import com.iat.Config;
import com.iat.controller.MilestonesController;
import com.iat.domain.EcardsConfig.milestones.MilestoneValue;
import com.iat.domain.EcardsConfig.milestones.MilestonesList;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;

import java.util.List;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MilestonesActions {

    private MilestonesController milestonesController = new MilestonesController();
    private JdbcDatabaseConnector mySQLConnector_iatAdmin = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin);

    public ResponseContainer getMilestonesForType(String milestoneType, int status) {
        ResponseContainer response = initResponseContainer(milestonesController.getMilestonesForType(milestoneType, status));
        if (milestoneType.equals("birthday") || milestoneType.equals("workAnniversary"))
            assertThat("Wrong type of milestones data", response.getAsObject(MilestonesList.class).getValueType(), is("integer"));
        return response;
    }

    public ResponseContainer postMilestonesForType(String milestoneType, MilestoneValue milestoneValue, int status) {
        return initResponseContainer(milestonesController.postMilestonesForType(milestoneType, milestoneValue, status));
    }

    public ResponseContainer deleteMilestonesForType(String milestoneType, String milestoneId, int status) {
        return initResponseContainer(milestonesController.deleteMilestonesForType(milestoneType, milestoneId, status));
    }

    public String getMilestoneIdByValue(String milestoneType, int milestoneValue, int status) {
        ResponseContainer response = getMilestonesForType(milestoneType, status);
        List<String> ids = response.getList("values.findAll{it.value == '" + milestoneValue + "'}.id", String.class);
        if (ids == null || ids.isEmpty())
            return null;
        return ids.get(0);
    }

    public ResponseContainer getUsersListForMilestonesWithType(String milestoneType, String allEvents, int status) {
        return initResponseContainer(milestonesController.getUsersListForMilestonesWithType(milestoneType, allEvents, status));
    }

    public void clearPartnerIatAdminMilestones() {
        //clear setting and reasons -> to simulate firs log in

        String query = "DELETE FROM Milestone WHERE partnerId =" + Config.getWizardTestPartnerId();
        mySQLConnector_iatAdmin.execute(query);
        System.out.println(query);

        query = "SELECT count(*) FROM iat_admin_qa.Milestone where partnerId = " + Config.getWizardTestPartnerId();

        assertThat("Milestones for partnerId " + Config.getWizardTestPartnerId() + " not cleared", mySQLConnector_iatAdmin.getSingleResult(query), is("0"));
    }


}