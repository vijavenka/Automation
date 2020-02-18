package com.iat.validators.ecardsManagement;


import com.iat.domain.EcardsConfig.PointsSharing;
import com.iat.domain.EcardsConfig.Reason;
import com.iat.domain.EcardsConfig.milestones.MilestonesConfig;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;


public class EcardsConfigValidator {

    private DataExchanger dataExchanger = DataExchanger.getInstance();


    public void validateSettingsByTypeIsUpdated(String settingsType, ResponseContainer response) {
        if (settingsType.equals("reasons")) {
            Reason reason = dataExchanger.getReasonObject();

            int managerToUserMinAfter = response.getInt("managerToUserMin");
            int managerToUserMaxAfter = response.getInt("managerToUserMax");
            int userToUserMinAfter = response.getInt("userToUserMin");
            int userToUserMaxAfter = response.getInt("userToUserMax");

            assertThat("managerToUserMin not updated", reason.getManagerToUserMin(), is(managerToUserMinAfter));
            assertThat("managerToUserMax not updated", reason.getManagerToUserMax(), is(managerToUserMaxAfter));
            assertThat("userToUserMin not updated", reason.getUserToUserMin(), is(userToUserMinAfter));
            assertThat("userToUserMax not updated", reason.getUserToUserMax(), is(userToUserMaxAfter));
        }

        if (settingsType.equals("pointsSharing")) {
            PointsSharing pointsSharing = dataExchanger.getPointsSharingObject();

            String userSharePointsRangeAfter = response.getString("userSharePointsRange");
            String managerSharePointsRangeAfter = response.getString("managerSharePointsRange");
            boolean sharePointsWithManagerAfter = response.getBoolean("sharePointsWithManager");

            assertThat("userSharePointsRange not updated", pointsSharing.getUserSharePointsRange(), is(userSharePointsRangeAfter));
            assertThat("managerSharePointsRange not updated", pointsSharing.getManagerSharePointsRange(), is(managerSharePointsRangeAfter));
            assertThat("sharePointsWithManager not updated", pointsSharing.getSharePointsWithManager(), is(sharePointsWithManagerAfter));


            if (pointsSharing.getGlobalPassword() != null) {
                String globalPassword = pointsSharing.getGlobalPassword();
                String globalPasswordAfter = response.getString("globalPassword");

                assertThat("globalPassword not set properly: " + globalPasswordAfter + " but should be: " + globalPassword, globalPasswordAfter.equals(globalPassword));
            }

            //Approval settings
            String approvalProcess = pointsSharing.getApprovalProcess();
            String approvalProcessAfter = response.getString("approvalProcess");

            assertThat("approvalProcess not updated", approvalProcess, is(approvalProcessAfter));

            if (approvalProcess.equals("THRESHOLD")) {
                int approvalThreshold = pointsSharing.getApprovalThreshold();
                int approvalThresholdAfter = response.getInt("approvalThreshold");

                assertThat("approvalThreshold not updated", approvalThreshold, is(approvalThresholdAfter));
            } else {
                String approvalThresholdAfter = response.getString("approvalThreshold");

                assertThat("approvalThreshold not empty but approvalProcess = " + approvalProcess, approvalThresholdAfter, isEmptyOrNullString());
            }
        }

        if (settingsType.equals("templates")) {

            boolean useDefaultTemplatesSet = dataExchanger.getTemplatesConfigObject().getUseDefaultTemplatesSet();
            boolean useDefaultTemplatesSetAfter = response.getBoolean("useDefaultTemplatesSet");

            assertThat("useDefaultTemplatesSet not updated", useDefaultTemplatesSet, is(useDefaultTemplatesSetAfter));
        }

        if (settingsType.equals("milestones")) {

            List<MilestonesConfig> milestonesConfigList = dataExchanger.getMilestonesConfigList();
            boolean birthdayActive = milestonesConfigList.get(0).getName().equals("birthdate") ? milestonesConfigList.get(0).getActive() : milestonesConfigList.get(1).getActive();
            boolean workAnniversaryActive = milestonesConfigList.get(0).getName().equals("workAnniversary") ? milestonesConfigList.get(0).getActive() : milestonesConfigList.get(1).getActive();

            List<MilestonesConfig> milestonesConfigListResponse = response.getList("", MilestonesConfig.class);
            boolean birthdayActiveAfter = milestonesConfigListResponse.get(0).getName().equals("birthdate") ? milestonesConfigListResponse.get(0).getActive() : milestonesConfigListResponse.get(1).getActive();
            boolean workAnniversaryActiveAfter = milestonesConfigListResponse.get(0).getName().equals("workAnniversary") ? milestonesConfigListResponse.get(0).getActive() : milestonesConfigListResponse.get(1).getActive();

            assertThat("birthdayActive not updated", birthdayActiveAfter, is(birthdayActive));
            assertThat("workAnniversaryActive not updated", workAnniversaryActiveAfter, is(workAnniversaryActive));
        }
    }

}
