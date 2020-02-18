package com.iat.validators.ecardsManagement;

import com.iat.Config;
import com.iat.domain.EcardsConfig.Reason;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class EcardsReasonsValidator {

    private JdbcDatabaseConnector mySQLConnector_pointsManager = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager);
    private DataExchanger dataExchanger = DataExchanger.getInstance();


    public void validateEcardsReasonsListOrder(ResponseContainer response) {

        List<Long> ids = response.getList("id");
        for (int i = 0; i < ids.size() - 1; i++)
            assertThat("Reasons are not sorted id desc order ", ids.get(i), greaterThanOrEqualTo(ids.get(i + 1)));
    }

    public void validateGetReasonByIdData(ResponseContainer response) {
        Reason reason = dataExchanger.getReasonObject();

        String extractedName = response.getString("name");
        String extractedManagerToUserReasonRange = response.getString("managerToUserReasonRange");
        String extractedUserToUserReasonRange = response.getString("userToUserReasonRange");

        String providedName = reason.getName();
        String providedManagerToUserReasonRange = reason.getManagerToUserReasonRange();
        String providedUserToUserReasonRange = reason.getUserToUserReasonRange();

        assertThat("Respone name value is invalid!", extractedName, containsString(providedName));
        assertThat("Respone managerToUserReasonRange value is invalid!", extractedManagerToUserReasonRange, is(providedManagerToUserReasonRange));
        assertThat("Respone userToUserReasonRange value is invalid!", extractedUserToUserReasonRange, is(providedUserToUserReasonRange));

        if (!providedManagerToUserReasonRange.equals("GLOBAL")) {
            assertThat("Respone managerToUserMin value is invalid!", response.getInt("managerToUserMin"), is(reason.getManagerToUserMin()));
            assertThat("Respone managerToUserMax value is invalid!", response.getInt("managerToUserMax"), is(reason.getManagerToUserMax()));
            assertThat("Respone userToUserMin value is invalid!", response.getInt("userToUserMin"), is(reason.getUserToUserMin()));
            assertThat("Respone userToUserMax value is invalid!", response.getInt("userToUserMax"), is(reason.getUserToUserMax()));
        }
    }

    public String validateGetReasonByIdProperTagCreated() {
        String query = "Select * from Tag where description = \"" + dataExchanger.getReasonObject().getName() + "\"";
        return mySQLConnector_pointsManager.getSingleResult(query);
    }
}
