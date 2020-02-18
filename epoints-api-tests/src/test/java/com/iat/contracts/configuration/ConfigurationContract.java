package com.iat.contracts.configuration;

public class ConfigurationContract {

    public String getConfigurationPath(String filePath) {
        return "/rest/configuration/" + filePath;
    }

}