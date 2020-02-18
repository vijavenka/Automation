package com.iat.controller;

import com.iat.Config;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class SolrController {

    public void checkHealthCheckResponse(int port) {
        get(Config.getSolrIndexUrl(System.getProperty("test.env"), port) + "/solr")
                .then()
                .statusCode(200);
    }

    public ValidatableResponse getAllProducts(int port) {
        return given()
                .urlEncodingEnabled(false)
                .get(Config.getSolrIndexUrl(System.getProperty("test.env"), port) + "/solr/offerings/select?q=*%3A*&start=0&rows=0&wt=json&indent=true")
                .then();
    }
}