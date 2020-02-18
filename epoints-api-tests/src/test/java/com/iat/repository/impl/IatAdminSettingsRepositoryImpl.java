package com.iat.repository.impl;

import com.iat.Config;
import com.iat.domain.EcardsSettingsPointsSharing;
import com.iat.domain.EcardsSettingsTemplates;
import com.iat.repository.IatAdminSettingsRepository;
import com.iat.utils.ResponseContainer;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class IatAdminSettingsRepositoryImpl implements IatAdminSettingsRepository {

    private String authorizeUser(String email, String password) {
        Response response = given().post(Config.getIatAdminUrl() + "/api/login?user=" + email + "&password=" + password);
        response.then().statusCode(302);
        return response.then().extract().response().getCookie("SESSION");
    }


    public void setEcardsPointsSharing(EcardsSettingsPointsSharing settings, String credentials) {
        String sessionId = authorizeUser(credentials.split(",")[0], credentials.split(",")[1]);
        given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId)
                .body(settings)
                .when()
                .post(Config.getIatAdminUrl() + "/api/ecards/settings/pointsSharing")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .prettyPrint();
    }


    public void setEcardsTemplates(EcardsSettingsTemplates settings, String credentials) {
        String sessionId = authorizeUser(credentials.split(",")[0], credentials.split(",")[1]);
        given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId)
                .body(settings)
                .when()
                .post(Config.getIatAdminUrl() + "/api/ecards/settings/templates")
                .then()
                .statusCode(200)
                .extract()
                .response()
                .prettyPrint();
    }


    public ResponseContainer getDepartmentsStructure(String withRoot, String credentials) {
        String sessionId = authorizeUser(credentials.split(",")[0], credentials.split(",")[1]);
        String path = Config.getIatAdminUrl() + getDepartmentsStructurePath(withRoot);

        log.info("Path: GET {}", path);

        return new ResponseContainer(given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId)
                .when()
                .get(path)
                .then()
                .statusCode(200));
    }

    private String getDepartmentsStructurePath(String withRoot) {
        String path = "/api/departments";
        if (!withRoot.equals("null"))
            path += "?withRoot=" + withRoot;
        return path;
    }
}