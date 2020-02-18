package com.iat.restful_services.actions;

import com.iat.restful_services.json.JSONBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;

public class AddingConfirmedPointsWebserviceActions {

    JSONBuilder buildJson = new JSONBuilder();

	public AddingConfirmedPointsWebserviceActions() {
		RestAssured.baseURI = "http://10.10.31.144";
		RestAssured.port = 8913;
		RestAssured.basePath = "";
	}

    public void performEarningAction(int pnts, String apiaccesskey, String userid, String tagname, String pointsstatus, String externaltransactionid, String assignerreference, String activityinfo) {
        String json = buildJson.buildAddPointsJSON(pnts, apiaccesskey, userid, tagname, pointsstatus, externaltransactionid, assignerreference, activityinfo);
        String path = "/transactions/addPoints";
            System.out.println(json);
        Response response = given().contentType("application/json").body(json).expect().response().when().post(path);
            System.out.println(response.getBody().asString());
    }

	public void checkApplicationHealth() {
		String path = "/";
		expect().statusCode(404).when().post(path);
	}

	public void addPointsToTheUser(String userName) {
		String path = "/search/filters/all";
		expect().statusCode(404).when().get(path);
	}

	public String getAvailableResponse() {
		String path ="/search/filters/all";
		Response response = expect().statusCode(200).when().get(path);
		ResponseBody body = response.getBody();
		String filters = body.asString();

		return filters;
	}
}
