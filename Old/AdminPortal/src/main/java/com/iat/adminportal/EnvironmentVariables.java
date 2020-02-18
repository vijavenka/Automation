package com.iat.adminportal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isOneOf;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 25.01.14
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class EnvironmentVariables {
    public static String environment = "qa";

    //Which browser you want to use
    //available options chrome, firefox, it
    public static String browser = "chrome";

    public static String getBaseUrl() {

        String baseUrl = null;
        switch (environment) {
            case "qa":
                baseUrl = "https://qa-admin.epoints.com";
                break;
            case "stag":
                baseUrl = "https://stag-admin.epoints.com";
                break;
            case "prod":
                baseUrl = "https://admin.epoints.com";
                break;
        }

        return baseUrl;
    }

    public static String[] getEpointsDbCredentials() {
        String[] credentials = new String[3];
        switch (environment) {
            case "qa":
                credentials[0] = "jdbc:mysql://test-proxy-qa-01.iatlimited.com:3308";
                credentials[1] = "admin";
                credentials[2] = "qGZ7zmhsu8Eb";
                break;
            case "stag":
                credentials[0] = "jdbc:mysql://test-proxy-stag-01.iatlimited.com:3308";
                credentials[1] = "admin";
                credentials[2] = "JLNp5SbtaGkHOVS";
                break;
        }

        return credentials;
    }

    public static String[] getAdminPortalDbCredentials() {
        String[] credentials = new String[3];
        switch (environment) {
            case "qa":
                credentials[0] = "jdbc:mysql://test-proxy-qa-01.iatlimited.com:3305";
                credentials[1] = "admin";
                credentials[2] = "U70FXi1wdDgip3m";
                break;
            case "stag":
                credentials[0] = "jdbc:mysql://test-proxy-stag-01.iatlimited.com:3307";
                credentials[1] = "admin";
                credentials[2] = "V2fPqKvDED0AHwt";
                break;
        }

        return credentials;
    }

    public static String getDoormanUrl() {
        String url = null;
        if (environment.equals("qa")) {
            url = "http://test-proxy-qa-0.iatlimited.com:8950";
        } else if (environment.equals("stag")) {
            url = "http://test-proxy-stag-0.iatlimited.com:8950";
        }
        return url;
    }

    public static int getEpointsPartnerId() {
        int partnerId = 0;
        if (environment.equals("qa")) {
            partnerId = 2;
        } else if (environment.equals("stag")) {
            partnerId = 2;
        }
        return partnerId;
    }

    static void setTestEnvironment(String env) {
        if (env != null) environment = env;
        assertThat("Only one of the available environments can be used!", environment, isOneOf("prod", "qa", "stag"));
    }
}