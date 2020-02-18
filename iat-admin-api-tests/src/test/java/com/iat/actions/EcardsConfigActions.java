package com.iat.actions;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.controller.EcardsConfigController;
import com.iat.domain.EcardsConfig.Reason;
import com.iat.utils.DataExchanger;
import com.iat.utils.JdbcDatabaseConnector;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static org.hamcrest.MatcherAssert.assertThat;


public class EcardsConfigActions {

    private EcardsConfigController ecardsConfigController = new EcardsConfigController();
    private JdbcDatabaseConnector mysqlConnectionPoolIatAdmin = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin);
    private ObjectMapper mapper = new ObjectMapper();
    private DataExchanger dataExchanger = DataExchanger.getInstance();


    public ResponseContainer getEcardsSettingsForType(String settingsType, int status) {
        return initResponseContainer(ecardsConfigController.getEcardsSettingsForType(settingsType, status));
    }


    public ResponseContainer setEcardsSettingsForType(String settingsType, String jsonBody, int status) {
        return initResponseContainer(ecardsConfigController.setEcardsSettingsForType(settingsType, jsonBody, status));
    }

    public void clearGlobalPasswordSettings(String partnerId) {
        mysqlConnectionPoolIatAdmin.execute("Update ECardsSettings Set globalPassword = null, allowGlobalPassword = 1 WHERE partnerId =  " + partnerId);

        ResponseContainer response = getEcardsSettingsForType("pointsSharing", 200);
        assertThat("Global password settings not allowed", response.getBoolean("allowGlobalPassword"));
        assertThat("Global password is already set", !response.getBoolean("globalPasswordSet"));

    }

    public void setDefaultGlobalReasonLimits() throws Throwable {
        Reason reason = new Reason(2, 2000000, 2, 1000000);
        dataExchanger.setGlobalReasonLimits(reason);

        setEcardsSettingsForType("reasons", reason.toJsonRequest(), 200);

    }

    public void updateGlobalReasonLimits(String json) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        Reason reason = mapper.readValue(json, Reason.class);
        dataExchanger.setGlobalReasonLimits(reason);

        setEcardsSettingsForType("reasons", reason.toJsonRequest(), 200);

    }
}
