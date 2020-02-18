package com.iat.controller;

import com.iat.contracts.SocialActivityContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class SocialActivityController {

    private SocialActivityContract socialActivityContract = new SocialActivityContract();
    private ContractValidator contractValidator = new ContractValidator();

    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();

    private ValidatableResponse getSocialStatusRequest(int status) {
        String path = socialActivityContract.getScoialPath();

        log.info("GET social status Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getSocialStatus(int status) {
        ValidatableResponse validatableResponse = getSocialStatusRequest(status);
        contractValidator.validateResponseWithContract("/users/GET-200-socialStatus.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse likeEpointsOnFacebookRequest(int status) {
        String path = socialActivityContract.getFacebookLikePath();

        log.info("GET Like epoints on Facebook Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse likeEpointsOnFacebook(int status) {
        return likeEpointsOnFacebookRequest(status);
    }

    private ValidatableResponse followEpointsOnTwitterRequest(int status) {
        String path = socialActivityContract.getTwitterFollowPath();

        log.info("GET Like epoints on Facebook Path: {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse followEpointsOnTwitter(int status) {
        return followEpointsOnTwitterRequest(status);
    }
}