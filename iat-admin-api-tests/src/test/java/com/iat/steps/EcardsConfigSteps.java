package com.iat.steps;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iat.Config;
import com.iat.actions.EcardsConfigActions;
import com.iat.domain.EcardsConfig.PointsSharing;
import com.iat.domain.EcardsConfig.TemplatesConfig;
import com.iat.domain.EcardsConfig.milestones.MilestonesConfig;
import com.iat.utils.DataExchanger;
import com.iat.utils.ResponseContainer;
import com.iat.validators.ecardsManagement.EcardsConfigValidator;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

public class EcardsConfigSteps {

    private EcardsConfigActions ecardsConfigActions = new EcardsConfigActions();
    private EcardsConfigValidator ecardsConfigValidator = new EcardsConfigValidator();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ResponseContainer response;
    private ObjectMapper mapper = new ObjectMapper();

    //Get global settings for reasons
    @When("^Get Ecards settings call for '(.+?)' is made$")
    public void getEcardsSettingsByType(String settingsType) throws Throwable {
        response = ecardsConfigActions.getEcardsSettingsForType(settingsType, 200);
    }

    @Then("^Get Ecards settings call for '(.+?)' return proper contract$")
    public void getEcardsSettingsByTypeMatchContract(String settingsType) throws Throwable {
        //validated on controller level
    }

    //Get global settings for reasons Error message validation
    @When("^Get Ecards settings call for incorrect data '(.+?)', '(.+?)'$")
    public void getEcardsSettingsByTypeIncorrect(String settingsType, int status) throws Throwable {
        response = ecardsConfigActions.getEcardsSettingsForType(settingsType, status);
        dataExchanger.setResponse(response);
    }

    //Set global settings for reasons
    @When("^Set Ecards settings call for '(.+?)' is made with data '(.+?)'$")
    public void setEcardsSettingsByType(String settingsType, String jsonBody) throws Throwable {
        mapper.enable(JsonParser.Feature.IGNORE_UNDEFINED, JsonParser.Feature.ALLOW_MISSING_VALUES);
        if (settingsType.equals("templates")) {
            dataExchanger.setTemplatesConfigObject(mapper.readValue(jsonBody, TemplatesConfig.class));
            jsonBody = dataExchanger.getTemplatesConfigObject().toJsonRequest();
        }

        if (settingsType.equals("pointsSharing")) {
            dataExchanger.setPointsSharingObject(mapper.readValue(jsonBody, PointsSharing.class));
            jsonBody = dataExchanger.getPointsSharingObject().toJsonRequest();
        }

        if (settingsType.equals("reasons")) {
            dataExchanger.setReasonObject(mapper.readValue(jsonBody, com.iat.domain.EcardsConfig.Reason.class));
            jsonBody = dataExchanger.getReasonObject().toJsonRequest();
        }

        if (settingsType.equals("milestones")) {
            dataExchanger.setMilestonesConfigList(mapper.readValue(jsonBody, new TypeReference<List<MilestonesConfig>>() {
            }));
            jsonBody = dataExchanger.getMilestonesConfigList().toString();
        }

        ecardsConfigActions.setEcardsSettingsForType(settingsType, jsonBody, 200);
    }

    @Then("^Ecards settings should be properly updated for '(.+?)'$")
    public void getEcardsSettingsByTypeIsUpdated(String settingsType) throws Throwable {
        ecardsConfigValidator.validateSettingsByTypeIsUpdated(settingsType, response);
    }

    //Set global settings for reasons Error message validation
    @When("^Set Ecards settings call type '(.+?)' is made for incorrect data '(.+?)', '(.+?)'$")
    public void setEcardsSettingsByTypeIncorrect(String settingsType, String jsonBody, int status) throws Throwable {
        response = ecardsConfigActions.setEcardsSettingsForType(settingsType, jsonBody, status);
        dataExchanger.setResponse(response);
    }

    @Given("^Clear Global password settings$")
    public void clear_Global_password_settings() throws Throwable {
        ecardsConfigActions.clearGlobalPasswordSettings(Config.getTestPartnerId());
    }
}
