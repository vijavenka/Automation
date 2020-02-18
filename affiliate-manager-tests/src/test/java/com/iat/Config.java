package com.iat;

import com.iat.utils.JdbcConnectionPool;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Config {
    //##################################################################################################################
    public static String testEnvironment = "prod";

    public static void setTestEnvironment(String env) {
        if (env != null) testEnvironment = env;
        assertThat("Only one of the available environments can be used!", testEnvironment, isOneOf("prod", "qa", "stag"));
    }

    public static String affiliateManagerUrl() {
        return "http://test-proxy-" + testEnvironment + "-0.iatlimited.com:8905";
    }


    public static JdbcConnectionPool mysqlConnectionPoolAffiliateManagerLIVE = new JdbcConnectionPool(JdbcConnectionPool.DB.mysql,
            "com.mysql.cj.jdbc.Driver",
            "test-proxy-live-01.iatlimited.com",
            3306,
            "affiliate_manager",
            "aff_mgr_doorman",
            "asdDSA123#@!");
}