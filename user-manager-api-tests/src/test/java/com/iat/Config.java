package com.iat;

import com.iat.utils.JdbcConnectionPool;
import com.iat.utils.ResponseContainer;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;

import static com.iat.utils.ResponseContainer.initResponseContainer;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isOneOf;

@Slf4j
public class Config {


    public static String testEnvironment = "qa";

    public static String pointsManagerDBUri = "test-proxy-" + testEnvironment + "-01.iatlimited.com";
    public static String pointsManagerDBUser = "admin";
    public static int pointsManagerDBPort = 3308;
    public static String pointsManagerSchema = "points_manager";
    public static String pointsManagerDBPasswordQa = "qGZ7zmhsu8Eb";
    public static String pointsManagerDBPasswordStag = "JLNp5SbtaGkHOVS";
    public static JdbcConnectionPool mysqlConnectionPool;

    //Test users data --------------------------------------------------------------------------------------------------
    public static String mainTestUserEmail = "epoints.api.automation.1@test.pl";
    public static String mainTestUserPassword = "P@ssw0rd";
    //------------------------------------------------------------------------------------------------------------------

    static {
        mysqlConnectionPool = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql,
                "com.mysql.jdbc.Driver",
                Config.pointsManagerDBUri,
                Config.pointsManagerDBPort,
                Config.pointsManagerSchema,
                Config.pointsManagerDBUser,
                Config.testEnvironment.equals("qa") ? Config.pointsManagerDBPasswordQa : Config.pointsManagerDBPasswordStag);
    }

    public static String getIatDoormanUrl() {
        return String.format("http://test-proxy-%s-0.iatlimited.com:8950", testEnvironment);
    }

    public static String getApiUrl() {
        return String.format("http://test-proxy-%s-0.iatlimited.com:8920", testEnvironment);
    }

    public static void setTestEnvironment(String env) {
        if (env != null) testEnvironment = env;
        assertThat("Only one of the available environments can be used!", testEnvironment, isOneOf("prod", "qa", "stag"));
    }

    public static String getExternalId(String externalId) {
        switch (externalId) {
            case "TEST_EXTERNAL_UNITED_ID_ACTIVE_VERIFIED":
                return "4";
            case "TEST_EXTERNAL_UNITED_ID_ACTIVE_NOT_VERIFIED":
                return "3";
            case "TEST_EXTERNAL_UNITED_ID_INACTIVE":
                return "5";
            case "TEST_EXTERNAL_UNITED_ID_ACTIVE_DUPLICATE_1":
                return "6";
            case "TEST_EXTERNAL_UNITED_ID_NOT_EXISTING":
                return externalId;
            case "GRIBEK-UNITED":
                return "xxxx";
            default:
                assertThat("Wrong externalId used!", externalId, isOneOf("TEST_EXTERNAL_UNITED_ID_ACTIVE_VERIFIED",
                        "TEST_EXTERNAL_UNITED_ID_ACTIVE_NOT_VERIFIED", "TEST_EXTERNAL_UNITED_ID_INACTIVE",
                        "TEST_EXTERNAL_UNITED_ID_ACTIVE_D", "TEST_EXTERNAL_UNITED_ID_NOT_EXISTING", "GRIBEK-UNITED"));
        }
        return null;
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

    public static String getBdlDoormanUrl() {
        return String.format("http://test-proxy-%s-0.bigdls.com:8950", testEnvironment);
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

    public enum User {
        TESTY_TESTATORI("105902003227838", "qbgxomgzja_1479301398@tfbnw.net", "1973537837");

        private final String facebookId;
        private final String email;
        private final String facebookPassword;

        User(String facebookId, String email, String facebookPassword) {
            this.facebookId = facebookId;
            this.email = email;
            this.facebookPassword = facebookPassword;
        }

        public String getFacebookId() {
            return facebookId;
        }

        public String getEmail() {
            return email;
        }

        public String getFacebookPassword() {
            return facebookPassword;
        }

        public String getFacebookAccessToken() {
            String path = Config.getBdlDoormanUrl() + "/facebook-test-accounts";
            log.info("GET: " + path);
            ResponseContainer response = initResponseContainer(given().get(path).then().statusCode(200), "");
//            response = RestAssured.get(Config.getBdlDoormanUrl() + "/facebook-test-accounts").thenReturn().jsonPath().getString("[0].access_token");
            return response.getString("[0].access_token");
        }
    }

    public static String getEpointsUrl() {
        RestAssured.port = 443;
        String epointsUrl = null;
        switch (testEnvironment) {
            case "qa":
                epointsUrl = "https://qa.epoints.com";
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
}
