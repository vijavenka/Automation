package com.iat

import com.iat.stepdefs.utils.JdbcConnectionPool

import static java.lang.Boolean.parseBoolean
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.isOneOf

class Config {

    public static String testEnvironment = 'qa'
    public static boolean makeScreenshotAndVideo = false

    public static String unitedUser = "united_test_do_not_remove@iat.test"
    public static String unitedUserPassword = "testing"
    public static String epointsUser = "epoints_test_do_not_remove@iat.test"
    public static String epointsUserPassword = "testing"
    public static String epointsUser2 = "epoints_test_do_not_remove2@iat.test"
    public static String epointsUser2Password = "testing"
    public static String facebookUser = "emailwybitnietestowy3@gmail.com"
    public static String facebookUserPassword = "Delete777"
    public static String epointsCouponUsageUser = "user.coupon.usage.front.end.1@test.bdl.pl\t"
    public static String epointsCouponUsageUserPassword = "P@ssw0rd"

    // users used in ecards tests
    public static String associatedUser3 = 'emailfortest2@wp.pl'
    public static String associatedUser4 = 'emailfortest3@wp.pl'
    public static String associatedUser5 = 'emailfortest4@wp.pl'
    public static String associatedUser6 = 'emailfortest5@wp.pl'

    public static String superAdminLogin = 'ui_automated_tests_superadmin@wp.pl'
    public static String superAdminPassword = 'password'

    public static String reason1Id = '22102016112'
    public static String template1Id = '22102016112'
    public static String availableEcardId = '152147'
    public static String notAvailableEcardId = '1679'

    //
    static String getBaseUrl() {
        return "http://test-proxy-$testEnvironment-0.iatlimited.com"
    }

    static String getSolrUrl() {
        String port = ""
        switch (testEnvironment) {
            case "qa": port = "8989"; break
            case "stag": port = "8983"; break
        }
        return "http://test-proxy-stag-01.iatlimited.com:$port/solr/offerings_shard1_replica1"
    }

    static String getIatAdminUrl() {
        return "https://$testEnvironment-control.epoints.com:443"
    }

    static String getDoormanUrl() {
        return "http://test-proxy-$testEnvironment-0.iatlimited.com:8950"
    }

    static void setTestEnvironment(String env) {
        if (env != null) testEnvironment = env
        assertThat("Only one of the available environments can be used!", testEnvironment, isOneOf("prod", "qa", "stag"))
    }

    static void setVideoRecordingAndScreenshotMaking(String scrAndVid) {
        if (scrAndVid != null) {
            assertThat("Screenshot making and video recording should be turned off or on", scrAndVid, isOneOf("true", "false"))
            makeScreenshotAndVideo = parseBoolean(scrAndVid)
        }
    }

    //Points Manager
    public static String pointsManagerDBUri = "test-proxy-$testEnvironment-01.iatlimited.com"
    public static String pointsManagerDBUser = "admin"
    public static int pointsManagerDBPort = 3308
    public static String pointsManagerSchema = "points_manager"
    public static String pointsManagerDBPasswordQa = "qGZ7zmhsu8Eb"
    public static String pointsManagerDBPasswordStag = "JLNp5SbtaGkHOVS"

    public static JdbcConnectionPool mysqlConnectionPoolPointsManager

    static {
        mysqlConnectionPoolPointsManager = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql,
                "com.mysql.cj.jdbc.Driver",
                pointsManagerDBUri,
                pointsManagerDBPort,
                pointsManagerSchema,
                pointsManagerDBUser,
                testEnvironment == "qa" ? pointsManagerDBPasswordQa : pointsManagerDBPasswordStag)
    }

    //AdminPortal
    public static String adminPortalDBUri = "test-proxy-" + testEnvironment + "-01.iatlimited.com"
    public static String adminPortalDBUser = "admin"
    public static int adminPortalDBPort = 3305
    public static String adminPortalSchema = "admin_ui"
    public static String adminPortalDBPasswordQa = "U70FXi1wdDgip3m"
    public static String adminPortalDBPasswordStag = ""

    public static JdbcConnectionPool mysqlConnectionPoolAdminPortal

    static {
        mysqlConnectionPoolAdminPortal = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql,
                "com.mysql.jdbc.Driver",
                adminPortalDBUri,
                adminPortalDBPort,
                adminPortalSchema,
                adminPortalDBUser,
                testEnvironment == "qa" ? adminPortalDBPasswordQa : adminPortalDBPasswordStag)
    }
}