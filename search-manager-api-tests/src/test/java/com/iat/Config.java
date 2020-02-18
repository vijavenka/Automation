package com.iat;

public class Config {

    public static String getSolrIndexUrl(String env, int port) {
        System.out.println("Solr Path: " + "http://test-proxy-" + env + "-01.iatlimited.com:" + port);
        return "http://test-proxy-" + env + "-01.iatlimited.com:" + port;
    }
}
