package com.iat.actions.configuration;

import com.iat.controller.configuration.ConfigurationController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class ConfigurationActions {

    private ConfigurationController configurationController = new ConfigurationController();

    public ResponseContainer getEpointsConfiguration(String filePath, int status) {
        return initResponseContainer(configurationController.getEpointsConfiguration(filePath, status));
    }

}