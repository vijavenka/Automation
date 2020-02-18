package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.passwordRemind;
import com.iat.repository.UserAccountManagementRepository;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;


public class UserAccountManagementRepositoryImpl implements UserAccountManagementRepository {

    private ValidatableResponse remindPassword(passwordRemind body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put(Config.getIatAdminUrl() + "/api/account/password/remind")
                .then();
    }

    public String remindUserPassword(passwordRemind body) {
        ValidatableResponse response = remindPassword(body);
        return response.statusCode(200).extract().body().asString();
    }
}