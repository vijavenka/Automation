package com.iat.controller.ecardsManagement;

import com.iat.contracts.ecardsManagement.EcardsUsersSearchContract;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class EcardsUsersSearchController {

    private EcardsUsersSearchContract ecardsUsersSearchContract = new EcardsUsersSearchContract();
    private DataExchanger sessionId = DataExchanger.getInstance();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getEcardsUserRequest(String departmentId, String search, int status) {
        String path = ecardsUsersSearchContract.getEcardsUserPath(departmentId, search);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEcardsUser(String departmentId, String search, int status) {
        ValidatableResponse validatableResponse = getEcardsUserRequest(departmentId, search, status);
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("ecards/200-ecard-department-users.json", validatableResponse, status);

        return validatableResponse;

    }


    private ValidatableResponse searchCompanyUsersRequest(String search, int status) {
        String path = ecardsUsersSearchContract.getSearchCompanyUserPath(search);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse searchCompanyUsers(String search, int status) {
        ValidatableResponse validatableResponse = searchCompanyUsersRequest(search, status);
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("ecards/200-search-ecards-users-across-company.json", validatableResponse, status);

        return validatableResponse;

    }
}