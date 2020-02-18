package com.iat.controller;

import com.iat.contracts.DepartmentsStructureContract;
import com.iat.domain.departmentsStructure.CRUDDepartment;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class DepartmentsStructureController {

    private DepartmentsStructureContract departmentsStructureContract = new DepartmentsStructureContract();
    private DataExchanger sessionId = DataExchanger.getInstance();
    private ContractValidator contractValidator = new ContractValidator();


    private ValidatableResponse createDepartment(CRUDDepartment jsonBody, int status) {
        String path = departmentsStructureContract.getDepartmentsCPath();

        log.info("Path: POST {}", path);
        log.info("BODY: {}", jsonBody.toString());

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse createNewDepartment(CRUDDepartment jsonBody, int status) {
        ValidatableResponse validatableResponse = createDepartment(jsonBody, status);
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("structure/POST-PUT-201-department.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse updateDepartmentNameRequest(CRUDDepartment jsonBody, String departmentId, int status) {
        String path = departmentsStructureContract.getDepartmentsUDPath(departmentId);

        log.info("Path: PUT {}", path);
        log.info("BODY: {}", jsonBody.toString());

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .body(jsonBody)
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse updateDepartmentName(CRUDDepartment jsonBody, String departmentId, int status) {
        ValidatableResponse validatableResponse = updateDepartmentNameRequest(jsonBody, departmentId, status);
        if (status == 400)
            contractValidator.validateResponseWithContract("ErrorResponse-schema-fieldsValidation.json", validatableResponse);
        else
            contractValidator.validateResponseWithContract("structure/POST-PUT-201-department.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse moveDepartmentRequest(CRUDDepartment jsonBody, String departmentId, int status) {
        String path = departmentsStructureContract.getMoveDepartmentsPath(departmentId);

        log.info("Path: PUT {}", path);
        log.info("BODY: {}", jsonBody.toString());

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .body(jsonBody)
                .when()
                .put(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse moveDepartment(CRUDDepartment jsonBody, String departmentId, int status) {
        return moveDepartmentRequest(jsonBody, departmentId, status);
    }

    private ValidatableResponse deleteDepartmentRequest(String departmentId, int status) {
        String path = departmentsStructureContract.getDepartmentsUDPath(departmentId);

        log.info("Path: DELETE {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .delete(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse deleteDepartment(String departmentId, int status) {
        return deleteDepartmentRequest(departmentId, status);
    }

    private ValidatableResponse getDepartmentsStructureRequest(String withRoot, int status) {
        String path = departmentsStructureContract.getDepartmentsStructurePath(withRoot);

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getDepartmentsStructure(String withRoot, int status) {
        ValidatableResponse validatableResponse = getDepartmentsStructureRequest(withRoot, status);
        contractValidator.validateResponseWithContract("structure/200-ecard-structure.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getDepartmentsValidStructureRequest(int status) {
        String path = departmentsStructureContract.getDepartmentsStructureValidPath();

        log.info("Path: GET {}", path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getDepartmentsValidStructure(int status) {
        ValidatableResponse validatableResponse = getDepartmentsValidStructureRequest(status);
        contractValidator.validateResponseWithContract("structure/200-ecard-structure.json", validatableResponse, status);
        return validatableResponse;
    }

}