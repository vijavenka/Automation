package com.iat.controller.rewards;

import com.iat.contracts.rewards.DepartmentsContract;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class DepartmentsController {

    private DepartmentsContract departmentsContract = new DepartmentsContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getDepartmentsRequest(String businessId, int status) {
        String path = departmentsContract.getDepartmentsPath(businessId);
        log.info("Product Details Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getDepartmentsByBusinessId(String businessId, int status) {
        ValidatableResponse validatableResponse = getDepartmentsRequest(businessId, status);
        contractValidator.validateResponseWithContract("/rewards/GET-200-Departments.json", validatableResponse, status);
        return validatableResponse;
    }
}