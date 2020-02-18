package com.iat;

public class Config {
    //##################################################################################################################
    public static environment = 'qa'
    //##################################################################################################################

    public static String getDoormanUrl() {
        if (environment.equals('qa')) {
            return "http://test-proxy-qa-0.iatlimited.com:8950";
        } else if (environment.equals('stag')) {
            return "http://test-proxy-stag-0.iatlimited.com:8950";
        }
    }
}