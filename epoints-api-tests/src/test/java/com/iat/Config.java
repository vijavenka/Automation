package com.iat;

import com.iat.utils.JdbcConnectionPool;
import io.restassured.RestAssured;

import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isOneOf;

public class Config {

    public static String testEnvironment = "qa";

    public static void setTestEnvironment(String env) {
        if (env != null) testEnvironment = env;
        assertThat("Only one of the available environments can be used!", testEnvironment, isOneOf("prod", "qa", "stag"));
    }

    public static String getDoormanUrl() {
        return "http://test-proxy-" + testEnvironment + "-0.iatlimited.com:8950";
    }

    public static String getUserManagerApiUrl() {
        return String.format("http://test-proxy-%s-0.iatlimited.com:8920", testEnvironment);
    }

    public static String getEpointsUrl() {
        RestAssured.port = 443;
        String epointsUrl = null;
        switch (testEnvironment) {
            case "qa":
                epointsUrl = "https://qa.epoints.com";
                break;
            case "qa2":
                epointsUrl = "https://qa2.epoints.com";
                break;
            case "stag":
                epointsUrl = "https://stag.epoints.com:443";
                break;
            case "prod":
                epointsUrl = "https://www.epoints.com:443";
                break;
        }
        return epointsUrl;
    }

    public static String getIatAdminUrl() {
        RestAssured.port = 443;
        return "https://" + testEnvironment + "-control.epoints.com";
    }

    public static String getSuperAdminCredentialsForIAT_Admin() {
        return "api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd";
    }

    public static String getTestPartnerId() {
        return "7777";
    }


    public static String getEpointsUserDefault_1 = "Epointsdonotremove_1511168449640@epoints.com,password"; //094ef2fa-f45b-4ac8-95b0-94c7d591c592
    public static String getEpointsUserDefault_1_2 = "epointsdonotremove_1511168449640@epoints.com,password2"; //094ef2fa-f45b-4ac8-95b0-94c7d591c592
    public static String getEpointsUserDefault_2 = "epointsdonotremove_1511169696990@epoints.com,password"; //9ca9156c-ed91-46a7-b1c8-c71d13d5953b
    public static String getEpointsUserDefaultChanged_1 = "epointsdonotremove_1511169756853@epoints.com,password"; //8458b955-b324-46a3-ae00-a5865f8a94e2
    public static String getUnitedUserDefault_1 = "uniteddonotremove_1511169819647@epoints.com,password"; //9489a3df-51ff-4386-a72c-eb49cc00a0a6
    public static String getUnitedUserDefault_1_2 = "uniteddonotremove_1511169819647@epoints.com,password2"; //9489a3df-51ff-4386-a72c-eb49cc00a0a6
    public static String getUnitedUserDefaultChanged_1 = "uniteddonotremove_1511169877073@epoints.com,password"; //8316b710-ba33-4f82-97aa-ba3c0b5f8993
    public static String getNewsUserDefault_1 = "user.coupon.usage.front.end.1@test.bdl.pl,P@ssw0rd"; //2ad3347f-0ea6-4c88-8a27-381616852867


    public static String epointsPartnerShortName = "ePoints.com";
    public static String unitedPartnerShortName = "UnitedRetailers";

    public static String getEcardDefaultTemplateId() {
        List<String> templateIds;
        if (testEnvironment.equals("stag"))
            templateIds = asList("18", "19", "20", "21", "22", "23", "24");//stag
        else
            templateIds = asList("1004", "1006", "1007");//qa
        return templateIds.get(new Random().nextInt(templateIds.size()));
    }

    //Points Manager
    public static String pointsManagerDBUri = "test-proxy-" + testEnvironment + "-01.iatlimited.com";
    public static String pointsManagerDBUser = "admin";
    public static int pointsManagerDBPort = 3308;
    public static String pointsManagerSchema = "points_manager";
    public static String pointsManagerDBPasswordQa = "qGZ7zmhsu8Eb";
    public static String pointsManagerDBPasswordStag = "JLNp5SbtaGkHOVS";

    public static JdbcConnectionPool mysqlConnectionPoolPointsManager;

    static {
        mysqlConnectionPoolPointsManager = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql,
                "com.mysql.jdbc.Driver",
                Config.pointsManagerDBUri,
                Config.pointsManagerDBPort,
                Config.pointsManagerSchema,
                Config.pointsManagerDBUser,
                Config.testEnvironment.equals("qa") ? Config.pointsManagerDBPasswordQa : Config.pointsManagerDBPasswordStag);
    }

    //AdminPortal
    public static String adminPortalDBUri = "test-proxy-" + testEnvironment + "-01.iatlimited.com";
    public static String adminPortalDBUser = "admin";
    public static int adminPortalDBPort = 3305;
    public static String adminPortalSchema = "admin_ui";
    public static String adminPortalDBPasswordQa = "U70FXi1wdDgip3m";
    public static String adminPortalDBPasswordStag = "";

    public static JdbcConnectionPool mysqlConnectionPoolAdminPortal;

    static {
        mysqlConnectionPoolAdminPortal = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql,
                "com.mysql.jdbc.Driver",
                Config.adminPortalDBUri,
                Config.adminPortalDBPort,
                Config.adminPortalSchema,
                Config.adminPortalDBUser,
                Config.testEnvironment.equals("qa") ? Config.adminPortalDBPasswordQa : Config.adminPortalDBPasswordStag);
    }

    //Affiliate Network
    public static String affiliateNetworkDBUri = "test-proxy-" + testEnvironment + "-01.iatlimited.com";
    public static String affiliateNetworkDBUser = "admin";
    public static int affiliateNetworkDBPort = 3305;
    public static String affiliateNetworkSchema = "affiliate_manager";
    public static String affiliateNetworkDBPasswordQa = "U70FXi1wdDgip3m";
    public static String affiliateNetworkDBPasswordStag = "";

    public static JdbcConnectionPool mysqlConnectionPoolAffiliateManager;

    static {
        mysqlConnectionPoolAffiliateManager = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql,
                "com.mysql.jdbc.Driver",
                Config.affiliateNetworkDBUri,
                Config.affiliateNetworkDBPort,
                Config.affiliateNetworkSchema,
                Config.affiliateNetworkDBUser,
                Config.testEnvironment.equals("qa") ? Config.affiliateNetworkDBPasswordQa : Config.affiliateNetworkDBPasswordStag);
    }


    public static JdbcConnectionPool mysqlConnectionPoolAffiliateManagerLIVE = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql,
            "com.mysql.jdbc.Driver",
            "test-proxy-live-01.iatlimited.com",
            3306,
            "affiliate_manager",
            "aff_mgr_doorman",
            "asdDSA123#@!");


    public static String getSolrUrl() {
        String port = "";
        switch (testEnvironment) {
            case "qa":
                port = "8983";
                break;
            case "stag":
                port = "8989";
                break;
        }
        return "http://test-proxy-"+testEnvironment+"-01.iatlimited.com:" + port + "/solr/";
    }

    public static String getOauthProviderUrl() {
        return String.format("http://test-proxy-%s-0.iatlimited.com:8927", testEnvironment);
    }

    public static String getOauthProviderAuthorizationHeader() {

//        it can be obtained using for example https://www.blitter.se/utils/basic-authentication-header-generator/
//        with
//          - client_id: bdl
//          - client_secret: bdl_secret

        switch (testEnvironment) {
            case "qa":
                return "YmRsOmJkbF9zZWNyZXQ=";
            case "stag":
                return "";
            case "prod":
                return "";
        }
        return "";
    }

}