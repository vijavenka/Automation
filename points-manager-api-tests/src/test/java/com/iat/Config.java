package com.iat;

import com.iat.utils.JdbcConnectionPool;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isOneOf;

public class Config {

    //possible testEnvironments qa,stag,prod{@Production tag should be used in RunTest class for prod testEnvironment}
    static String testEnvironment = "qa";

    public static String getDoormanUrl() {
        return format("http://test-proxy-%s-0.iatlimited.com:8950", testEnvironment);
    }

    public static String getBaseUrl() {
        return format("http://test-proxy-%s-0.iatlimited.com:8913", testEnvironment);
    }

    public static String getUserManagerUrl() {
        return format("http://test-proxy-%s-0.iatlimited.com:8920", testEnvironment);
    }

    public static String getEpointsUrl() {
        switch (testEnvironment) {
            case "qa":
                return "https://qa.epoints.com:443";
            case "stag":
                return "https://stag.epoints.com:443";
            case "prod":
                return "https://www.epoints.com:443";
        }
        return null;
    }

    public static String getPartnerAccessKey() {
        switch (testEnvironment) {
            case "qa":
                return "accessKey";
            case "stag":
                return "accessKey";
            case "prod":
                return "xHNZaBGQtDmxTkrnI7NOfoXkz";
        }
        return null;
    }

    public static String getBdlPartnerAccessKey() {
        switch (testEnvironment) {
            case "qa":
                return "bdl";
            case "stag":
                return "bdl";
            case "prod":
                return "cTuVVEoIAGk5Lir0quvyMPuJu";
        }
        return null;
    }

    public static void setTestEnvironment(String env) {
        if (env != null) testEnvironment = env;
        assertThat("Only one of the available environments can be used!", testEnvironment, isOneOf("prod", "qa", "stag"));
    }

    //data used for united project bulk upload partners/criteria/transactions

    //Partners data used for partners bulk upload tests, same should be applied in source file 15N1t4m6ojs6wqE7cfuCjiRMnY0d5hl6r5zK5EPi4reM
    public static List<String> getBulkUploadPartnersData() {
        return asList("API AUTO Bulk Partner One;APIAUTOBulkPartnerOne;1234567",
                "API AUTO Bulk Partner Two;APIAUTOBulkPartnerTwo;ID7654321");
    }

    //Partners data used for rewardsCriteria bulk upload tests, same should be applied in source file 1ULrpMzCxRq3q-DEKX3xXHavI8mdvVcc9bTzkDEKxGs4
    public static List<String> getBulkUploadRewardsCriteriaPartnersData() {
        return asList(
                "API AUTO RewardsCriteriaPartner One;APIAUTORewardsCriteriaPartnerOne;RC1234567890",
                "API AUTO RewardsCriteriaPartner Two;APIAUTORewardsCriteriaPartnerTwo;RC0987654321",
                "API AUTO RewardsCriteriaPartner Three;APIAUTORewardsCriteriaPartnerThree;RC0987654322",
                "API AUTO RewardsCriteriaPartner Four;APIAUTORewardsCriteriaPartnerFour;RC0987654323",
                "API AUTO RewardsCriteriaSupplier;APIAUTORewardsCriteriaSupplier;SupplierExternalId_sup_1");
    }

    //External id should be in sync with this in  file 15N1t4m6ojs6wqE7cfuCjiRMnY0d5hl6r5zK5EPi4reM and getBulkUploadPartnersData()
    public static List<String> getBulkUploadRewardsCriteriaData() {
        return asList("RC1234567890;id333;product description 1;70;CASH;1;2017-05-15;2017-09-15",
                "RC0987654321;334;product description 2;17;DELIVERY;0;2017-05-15;2017-09-15",
                "RC0987654322;234;product description 3;18;DELIVERY;;2017-05-16;2017-09-16",
                "RC0987654323;345;product description 4;19;;0;2017-05-17;2017-09-17");
    }
    //end of data used for united project bulk upload partners/criteria/transactions

    public static final long UNITED_TEST_AUTO_POINTS_USER_ID = 2948217227L;
    public static final String UNITED_TEST_AUTO_EXTERNAL_ID = "99999";
    public static final String refundTransactionTagKey = "refundUnited";

    public static String pointsManagerDBUri = "test-proxy-" + testEnvironment + "-01.iatlimited.com";
    public static String pointsManagerDBUser = "admin";
    public static int pointsManagerDBPort = 3308;
    public static String pointsManagerSchema = "points_manager";
    public static String pointsManagerDBPasswordQa = "qGZ7zmhsu8Eb";
    public static String pointsManagerDBPasswordStag = "JLNp5SbtaGkHOVS";

    public static JdbcConnectionPool mysqlConnectionPool;

    static {
        mysqlConnectionPool = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql,
                "com.mysql.jdbc.Driver",
                Config.pointsManagerDBUri,
                Config.pointsManagerDBPort,
                Config.pointsManagerSchema,
                Config.pointsManagerDBUser,
                Config.testEnvironment.equals("qa") ? Config.pointsManagerDBPasswordQa : Config.pointsManagerDBPasswordStag);
    }

    public static String getEwsUrl() {
        switch (testEnvironment) {
            case "qa":
                return "https://qa-api.epoints.com";
            case "prod":
                return "https://api.epoints.com";
        }
        return "";
    }

    public static String getEwsApiKey() {
        return "bdl";
    }

    public static String getEwsSecret() {
        switch (testEnvironment) {
            case "qa":
                return "a1fb8795fba9d8fc042159ba6434ef59";
            case "stag":
                return "a1fb8795fba9d8fc042159ba6434ef59";
            case "prod":
                //not sure about that one
                return "9lypV8AADfvHPxR";
        }
        return "";
    }
}