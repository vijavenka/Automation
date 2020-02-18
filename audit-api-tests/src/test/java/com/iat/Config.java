package com.iat;

import com.iat.utils.JdbcConnectionPool;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.isOneOf;

public class Config {

    public static String testEnvironment = "qa";


    public static void setTestEnvironment(String env) {
        if (env != null) testEnvironment = env;
        assertThat("Only one of the available environments can be used!", testEnvironment, isOneOf("prod", "qa", "stag"));
    }

    public static String apiUrl() {
        return testEnvironment.equals("prod") ? "https://api.bigdl.com" : String.format("https://%s-audit.epoints.com", testEnvironment);
    }


    public static String retailerTakenEmail = "duplicated.audit.retailer@iat.ltd"; //retailer with such email has to be added into system

    public static String iatCmsAdminCredentials = "admin,admin";
    public static String todaysPartnerIdQa = "2147295626";
    public static String premierPartnerIdQa = "2147295720";

    public static String todaysChainIdQaAuditCms = "1";
    public static String premierChainIdQaAuditCms = "2";

    public static String fakePartnerApiKey = "kb_ui_automated_tests";
    public static String fakePartnerShortName = "kb_ui_automated_tests_1";

    public static String apiStore1Name = "API_TESTS_STORE_1";
    public static String apiStore2Name = "API_TESTS_STORE_2";

    public static String apiStore1PremierName = "API_TESTS_STORE_1_Premier";
    public static String apiStore2PremierName = "API_TESTS_STORE_2_Premier";

    public static String apiWholesaler1Name = "AUDIT_CMS_WHOLESALER";

    public static String iatSupplierId = "1006";

    //TODO retailer emails associated with bulk upload files also should be put into variable?

    public static String getDoormanUrl() {
        return "http://test-proxy-qa-0.iatlimited.com:8950";
    }


    public static String pointsManagerDBUri = "test-proxy-" + testEnvironment + "-01.iatlimited.com";
    public static String pointsManagerDBUser = "admin";
    public static int pointsManagerDBPort = 3308;
    public static String pointsManagerSchema = "points_manager";
    public static String pointsManagerDBPasswordQa = "qGZ7zmhsu8Eb";
    public static String pointsManagerDBPasswordStag = "JLNp5SbtaGkHOVS";


    public static String auditDBUri = "test-proxy-" + testEnvironment + "-01.iatlimited.com";
    public static String auditDBUser = "auditcms";
    public static int auditDBPort = 3305;
    public static String auditSchema = "audit";
    public static String auditDBPasswordQa = "za9Kqpx9";
    public static String auditDBPasswordStag = "????";

    public static JdbcConnectionPool mysqlConnectionPool_pointsManager;
    public static JdbcConnectionPool mysqlConnectionPool_audit;

    static {
        mysqlConnectionPool_pointsManager = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql, "com.mysql.jdbc.Driver",
                Config.pointsManagerDBUri,
                Config.pointsManagerDBPort,
                Config.pointsManagerSchema,
                Config.pointsManagerDBUser,
                Config.testEnvironment.equals("qa") ? Config.pointsManagerDBPasswordQa : Config.pointsManagerDBPasswordStag);

        mysqlConnectionPool_audit = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql, "com.mysql.jdbc.Driver",
                Config.auditDBUri,
                Config.auditDBPort,
                Config.auditSchema,
                Config.auditDBUser,
                Config.testEnvironment.equals("qa") ? Config.auditDBPasswordQa : Config.auditDBPasswordStag);
    }

}