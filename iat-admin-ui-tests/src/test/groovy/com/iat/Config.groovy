package com.iat

import com.iat.stepdefs.utils.JdbcConnectionPool
import io.restassured.RestAssured

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.isOneOf

class Config {
    public static int waitShort = 500
    public static int waitMedium = 1000
    public static int waitLong = 2000

    //##################################################################################################################
    public static String environment = 'qa'
    public static boolean makeScreenshotAndVideo = false
    //##################################################################################################################

    //IAT admins data used in ui automated tests
    public static String uberAdminLogin = 'ui_automated_tests_uberadmin@wp.pl'
    public static String uberAdminPassword = 'password'

    public static String superAdminLogin = 'ui_automated_tests_superadmin@wp.pl'
    public static String superAdminPassword = 'password'

    public static String bupaSuperAdminLogin = 'bupa_ui_automated_tests_superadmin@wp.pl'
    public static String bupaSuperAdminPassword = 'password'

    public static String adminLogin = 'ui_automated_tests_admin@wp.pl'
    public static String adminPassword = 'password'

//    public static String managerLogin = 'ui_automated_tests_manager@wp.pl'
//    public static String managerPassword = 'password'
    public static String managerLogin = 'andy_test_super_admin_company2@iat.admin.pl'
    public static String managerPassword = 'Password1'

    public static String wizardSuperAdminLogin = 'ui_automated_tests_wizard_test@wp.pl'
    public static String wizardSuperAdminPassword = 'password'

    public static String profileTestsAdminLogin = 'profiletestsadmin@wp.pl'
    public static String profileTestsAdminPassword = 'password'

    //associated with partner which can be used for test
    public static String associatedUser1 = 'emailfortest@wp.pl'
    public static String associatedUser2 = 'emailfortest1@wp.pl'
    public static String associatedUser3 = 'emailfortest2@wp.pl'
    public static String associatedUser4 = 'emailfortest3@wp.pl'

    //id of reason on template used in automated tests
    public static String reason1Id = '22102016112'
    public static String template1Id = '22102016112'

    public static int availablePointsNumber = 20000
    public static int reasonManagerToUserMin = 10
    public static int reasonManagerToUserMax = 100
    public static int reasonUserToUserMin = 10
    public static int reasonUserToUserMax = 100

    static String getIatAdminUrl() {
        if (environment == 'qa')
            return "https://qa-control.epoints.com:443"
        else if (environment == 'stag')
            return "https://stag-control.epoints.com:443"
    }

    static String getDoormanUrl() {
        if (environment == 'qa')
            return "http://test-proxy-qa-0.iatlimited.com:8950"
        else if (environment == 'stag')
            return "http://test-proxy-stag-0.iatlimited.com:8950"
    }

    static String getEpointsUrl() {
        RestAssured.port = 443
        String epointsUrl = null
        switch (environment) {
            case "qa":
                epointsUrl = "https://qa.epoints.com"
                break
            case "qa2":
                epointsUrl = "https://qa2.epoints.com"
                break
            case "stag":
                epointsUrl = "https://stag.epoints.com:443"
                break
            case "prod":
                epointsUrl = "https://www.epoints.com:443"
                break
        }
        return epointsUrl
    }

    static void setTestEnvironment(String env) {
        if (env != null) environment = env
        assertThat("Only one of the available environments can be used!", environment, isOneOf("prod", "qa", "stag"))
    }

    static void setVideoRecordingAndScreenshotMaking(String scrAndVid) {
        if (scrAndVid != null) {
            assertThat("Screenshot making and video recording should be turned off or tuned on", scrAndVid, isOneOf("true", "false"))
            makeScreenshotAndVideo = Boolean.parseBoolean(scrAndVid)
        }
    }

    //Points Manager
    public static String pointsManagerDBUri = "test-proxy-$environment-01.iatlimited.com"
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
                environment == "qa" ? pointsManagerDBPasswordQa : pointsManagerDBPasswordStag)
    }

    //IAT Admin
    public static String iatAdminDBUri = "test-proxy-" + environment + "-01.iatlimited.com"
    public static String iatAdminDBUser = "admin"
    public static int iatAdminDBPort = 3309
    public static String iatAdminSchemaQa = "iat_admin_qa"
    public static String iatAdminSchemaStag = "iat_admin_stag"
    public static String iatAdminDBPassword = "V2fPqKvDED0AHwt"

    public static JdbcConnectionPool mysqlConnectionPoolIatAdmin

    static {
        mysqlConnectionPoolIatAdmin = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql,
                "com.mysql.cj.jdbc.Driver",
                iatAdminDBUri,
                iatAdminDBPort,
                environment == "qa" ? iatAdminSchemaQa : iatAdminSchemaStag,
                iatAdminDBUser,
                iatAdminDBPassword)
    }
}