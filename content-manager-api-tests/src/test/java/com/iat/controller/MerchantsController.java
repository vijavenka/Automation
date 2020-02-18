package com.iat.controller;

import com.iat.utils.HMACHeader;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class MerchantsController {

    private HMACHeader hmacHeader = new HMACHeader();

    private ValidatableResponse getMerchants() {

        String date = hmacHeader.generateHttpDate();

        return given().
                contentType(ContentType.JSON)
                .header("Authorization", hmacHeader.getHmacHeader("GET", null, null, "/merchants", date))
                .header("X-IAT-Date", date)
                .when()
                .get("/merchants")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    public String getListOfMerchants() {
        String result = getMerchants().extract().body().asString();
        System.out.print(result);
        return result;
    }
}
