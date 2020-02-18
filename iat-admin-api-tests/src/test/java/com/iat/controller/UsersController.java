package com.iat.controller;

import com.iat.contracts.UsersContract;
import com.iat.domain.ChangePassword;
import com.iat.domain.User;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static io.restassured.RestAssured.given;

@Slf4j
public class UsersController {

    private UsersContract usersContract = new UsersContract();
    private DataExchanger dataExchanger = DataExchanger.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse createUserRequest(User user, int status) {
        String path = usersContract.createUserPath();

        log.info("Path: POST {}", path);
        log.info("BODY: {}", user.toString());

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .body(user)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse createUser(User user, int status) {
        ValidatableResponse validatableResponse = createUserRequest(user, status);
        if (status == 401 || status == 403)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);
        if (status == 400 || status == 404)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        return validatableResponse;
    }


    private ValidatableResponse getUsersRequest(String sortField, String ascending, String dateFrom, String dateTo,
                                                String departmentName, String manager, String user, String statusParam,
                                                String page, String maxResults, int status) {

        String path = usersContract.getUsersListPath(sortField, ascending, dateFrom, dateTo, departmentName, manager, user, statusParam, page, maxResults);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUsers(String sortField, String ascending, String dateFrom, String dateTo,
                                        String departmentName, String manager, String user, String statusParam,
                                        String page, String maxResults, int status) {

        ValidatableResponse validatableResponse = getUsersRequest(sortField, ascending, dateFrom, dateTo, departmentName,
                manager, user, statusParam, page, maxResults, status);
        if (status == 400 || status == 404)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("users/GET-200-users.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getUserByIdForWaitRequest(String userId) {
        String path = usersContract.getUserByIdPath(userId);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .when()
                .get(path)
                .then();
    }

    public boolean getUserByIdWait(String userId) {
        return getUserByIdForWaitRequest(userId).extract().response().getStatusCode() == 404;
    }

    private ValidatableResponse getUserByIdRequest(String userId, int status) {
        String path = usersContract.getUserByIdPath(userId);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUserById(String userId, int status) {
        ValidatableResponse validatableResponse = getUserByIdRequest(userId, status);
        if (status == 400 || status == 404)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("users/GET-200-user.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse updateUserByIdRequest(String userId, User user, String emailUpdateType, int status) {
        //temporary workaround
        if (status == 4000)
            status = 400;

        String path = usersContract.updateUserByIdPath(userId, emailUpdateType);

        log.info("Path: PUT {}", path);
        log.info("BODY: {}", user.toString());

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .body(user)
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse updateUserById(String userId, User user, String emailUpdateType, int status) {
        ValidatableResponse validatableResponse = updateUserByIdRequest(userId, user, emailUpdateType, status);

        if (status == 401 || status == 403 || status == 4000)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);
        if (status == 400 || status == 404)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        return validatableResponse;
    }


    private ValidatableResponse deleteUserByIdIgnoreStatusRequest(String userId) {
        String path = usersContract.getUserByIdPath(userId);

        log.info("Path: DELETE {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .when()
                .delete(path)
                .then();
    }

    public ValidatableResponse deleteUserByIdIgnoreStatus(String userId) {
        return deleteUserByIdIgnoreStatusRequest(userId);
    }


    private ValidatableResponse deleteUserByIdRequest(String userId, int status) {
        String path = usersContract.getUserByIdPath(userId);

        log.info("Path: DELETE {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .when()
                .delete(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse deleteUserById(String userId, int status) {
        ValidatableResponse validatableResponse = deleteUserByIdRequest(userId, status);
        if (status == 401 || status == 403)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);
        if (status == 400 || status == 404)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        return validatableResponse;
    }


    private ValidatableResponse enableUserByIdRequest(String userId, int status) {
        String path = usersContract.getUserByIdPath(userId) + "/enable";

        log.info("Path: PUT {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse enableUserById(String userId, int status) {
        ValidatableResponse validatableResponse = enableUserByIdRequest(userId, status);
        if (status == 401 || status == 403)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);
        if (status == 400 || status == 404)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        return validatableResponse;
    }

    private ValidatableResponse getUserMeRequest(int status) {
        String path = usersContract.getAdminUserMe();

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse getUserMe(int status) {
        ValidatableResponse validatableResponse = getUserMeRequest(status);
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else if (status != 200 && status != 201)
            contractValidator.validateResponseWithContract("ErrorResponse-schema.json", validatableResponse);
        return validatableResponse;
    }

    private ValidatableResponse bulkUploadRequest(String fileName, int status) {
        String path = usersContract.bulkUploadPath();

        log.info("Path: POST {}", path);

        return given()
                .multiPart("file", new File("src//bulkUploadFiles//" + fileName))
                .sessionId("SESSION", dataExchanger.getSessionId())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse bulkUpload(String fileName, int status) {
        return bulkUploadRequest(fileName, status);
    }

    private ValidatableResponse changeUserPasswordRequest(ChangePassword changePassword, int status) {
        String path = usersContract.changePasswordPath();

        log.info("Path: PUT {}", path);
        log.info("BODY: {}", changePassword.toString());

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .body(changePassword)
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse changeUserPassword(ChangePassword changePassword, int status) {
        return changeUserPasswordRequest(changePassword, status);
    }


    private ValidatableResponse remindUserPasswordRequest(String jsonBody, int status) {
        String path = usersContract.remindPasswordPath();

        log.info("Path: PUT {}", path);
        log.info("BODY: {}", jsonBody);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .body(jsonBody)
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse remindUserPassword(String jsonBody, int status) {
        return remindUserPasswordRequest(jsonBody, status);
    }


    private ValidatableResponse resetUserPasswordRequest(String jsonBody, int status) {
        String path = usersContract.resetPasswordPath();

        log.info("Path: PUT {}", path);
        log.info("BODY: {}", jsonBody);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .body(jsonBody)
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse resetUserPassword(String jsonBody, int status) {
        return resetUserPasswordRequest(jsonBody, status);
    }


    private ValidatableResponse verifyUserTokenRequest(String token, int status) {
        String path = usersContract.verifyTokenPath(token);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse verifyUserToken(String token, int status) {
        return verifyUserTokenRequest(token, status);
    }

    private ValidatableResponse emailChangePromptRequest(String userUuid, int status) {
        String path = usersContract.emailChangePromptToBeShownPath(userUuid);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", dataExchanger.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse emailChangePromptToBeShown(String userUuid, int status) {
        ValidatableResponse validatableResponse = emailChangePromptRequest(userUuid, status);
        contractValidator.validateResponseWithContract("users/GET-200-users-changeEmailPromptToBeShown.json", validatableResponse, status);
        return validatableResponse;
    }

}