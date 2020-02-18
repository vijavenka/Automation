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

    public static String getIatAdminUrl() {
        //    "https://stag-uberadmin.epoints.com"; //stag
        return String.format("https://%s-control.epoints.com", testEnvironment);
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

    public static String getUserManagerApiUrl() {
        return String.format("http://test-proxy-%s-0.iatlimited.com:8920", testEnvironment);
    }


    public static String getTestPartnerId() {
        return "7777";
    }

    public static String getWizardTestPartnerId() {
        return "666";
    }

    public static String epointsPartnerShortName = "ePoints.com";

    public static String getSuperAdminCredentials() {
        return "api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd";
    }

    public static String getEcardDefaultTemplateId() {
        List<String> templateIds;
        if (testEnvironment.equals("stag"))
            templateIds = asList("18", "19", "20", "21", "22", "23", "24");//stag
        else
            templateIds = asList("1004", "1006", "1007");//qa
        return templateIds.get(new Random().nextInt(templateIds.size()));
    }

    public static String getDepartmentIdForName(String name) {
        if (name == null) return null;
        switch (name) {
            case "Department level 1 [A]":
                return Config.testEnvironment.equals("stag") ? "1" : "383";
            case "Department level 1 [B]":
                return Config.testEnvironment.equals("stag") ? "2" : "384";
            case "Department level 1 [C]":
                return Config.testEnvironment.equals("stag") ? "3" : "221545";
            case "Department level 1 [D]":
                return Config.testEnvironment.equals("stag") ? "4" : "221548";
            case "Department level 2 [A.1]":
                return Config.testEnvironment.equals("stag") ? "5" : "385";
            case "Department level 2 [A.2]":
                return Config.testEnvironment.equals("stag") ? "6" : "221546";
            case "Department level 2 [A.3]":
                return Config.testEnvironment.equals("stag") ? "7" : "221549";
            case "Department level 2 [C.1]":
                return Config.testEnvironment.equals("stag") ? "2215419" : "221554";
            case "Department level 2 [C.2]":
                return Config.testEnvironment.equals("stag") ? "2215418" : "221553";
            case "Department level 3 [A.1.1]":
                return Config.testEnvironment.equals("stag") ? "8" : "386";
            case "Department level 3 [A.1.2]":
                return Config.testEnvironment.equals("stag") ? "9" : "387";
            case "Department level 3 [A.1.3]":
                return Config.testEnvironment.equals("stag") ? "10" : "221547";
            case "Department level 2 [B.1]":
                return Config.testEnvironment.equals("stag") ? "11" : "221550";
            case "Department level 2 [B.2]":
                return Config.testEnvironment.equals("stag") ? "12" : "221551";
            case "Department level 2 [D.1]":
                return Config.testEnvironment.equals("stag") ? "13" : "221552";
            case "Department from other company":
                return Config.testEnvironment.equals("stag") ? "221521" : "221521";
            case "Department not exists":
                return Config.testEnvironment.equals("stag") ? "1000500100900" : "1000500100900";
            default:
                return name;
        }
    }

    public static String getDepartmentManagerForName(String name) {
        String manager;

        switch (name) {
            case "Department level 1 [A]":
                manager = "api_test_manager_department_1@api.iat.admin.pl,P@ssw0rd";
                break;
            case "Department level 2 [A.1]":
                manager = "api_test_manager_subdepartment_1@api.iat.admin.pl,P@ssw0rd";
                break;
            case "Department level 3 [A.1.1]":
                manager = "api_test_manager_subdepartment_1.1@api.iat.admin.pl,P@ssw0rd";
                break;
            case "Department level 3 [A.1.2]":
                manager = "api_test_manager_subdepartment_1.2@api.iat.admin.pl,P@ssw0rd";
                break;
            default:
                manager = name;
                break;

        }
        return manager;
    }


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

    public static String iatAdminDBUri = "test-proxy-" + testEnvironment + "-01.iatlimited.com";
    public static String iatAdminDBUser = "admin";
    public static int iatAdminDBPort = 3309;
    public static String iatAdminSchemaQa = "iat_admin_qa";
    public static String iatAdminSchemaStag = "iat_admin_stag";
    public static String iatAdminDBPassword = "V2fPqKvDED0AHwt";

    public static JdbcConnectionPool mysqlConnectionPoolIatAdmin;

    static {
        mysqlConnectionPoolIatAdmin = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql,
                "com.mysql.jdbc.Driver",
                Config.iatAdminDBUri,
                Config.iatAdminDBPort,
                Config.testEnvironment.equals("qa") ? Config.iatAdminSchemaQa : Config.iatAdminSchemaStag,
                Config.iatAdminDBUser,
                Config.iatAdminDBPassword);
    }


    public static List<Integer> getMilestonesDefaultList(String name) {
        if (name.equals("workAnniversary"))
            return asList(1, 5, 10, 15, 20, 25);
        else
            return asList(18, 21, 30, 40, 50, 60);
    }

}
