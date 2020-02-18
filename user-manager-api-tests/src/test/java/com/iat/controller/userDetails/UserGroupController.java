package com.iat.controller.userDetails;

import com.iat.contracts.userDetails.UserGroupContract;
import com.iat.domain.UserGroup;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UserGroupController {

    private ContractValidator contractValidator = new ContractValidator();
    private UserGroupContract userGroupContract = new UserGroupContract();

    private ValidatableResponse postGroupRequest(String userKey, String idType, String apiKey, UserGroup userGroup, int status) {
        String path = userGroupContract.groupPath(userKey, idType, apiKey);

        log.info("Path: POST {}{}\n{}", RestAssured.baseURI, path, userGroup.toString());

        return given()
                .contentType(ContentType.JSON)
                .body(userGroup)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse postGroup(String userKey, String idType, String apiKey, UserGroup userGroup, int status) {
        ValidatableResponse validatableResponse = postGroupRequest(userKey, idType, apiKey, userGroup, status);
        contractValidator.validateResponseWithContract("GET-200-postGroup.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse deleteGroupRequest(String userKey, String idType, String apiKey, UserGroup userGroup, int status) {
        String path = userGroupContract.groupPath(userKey, idType, apiKey);

        log.info("Path: DELETE {}{}\n{}", RestAssured.baseURI, path, userGroup.toString());

        return given()
                .contentType(ContentType.JSON)
                .body(userGroup)
                .when()
                .delete(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse deleteGroup(String userKey, String idType, String apiKey, UserGroup userGroup, int status) {
        ValidatableResponse validatableResponse = deleteGroupRequest(userKey, idType, apiKey, userGroup, status);
        contractValidator.validateResponseWithContract("GET-200-postGroup.json", validatableResponse, status);

        return validatableResponse;
    }

    private ValidatableResponse getGroupsRequest(String userKey, String idType, String apiKey, int status) {
        String path = userGroupContract.groupPath(userKey, idType, apiKey);

        log.info("PAth: GET {}{}", RestAssured.baseURI, path);

        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getGroups(String userKey, String idType, String apiKey, int status) {
        ValidatableResponse validatableResponse = getGroupsRequest(userKey, idType, apiKey, status);
        contractValidator.validateResponseWithContract("GET-200-postGroup.json", validatableResponse, status);

        return validatableResponse;
    }

}