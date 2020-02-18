package com.iat.controller.rewards;

import com.iat.contracts.rewards.RecentlyRedeemedContract;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class RecentlyRedeemedController {

    private RecentlyRedeemedContract recentlyRedeemedContract = new RecentlyRedeemedContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getRecentlyRedeemedRequest(int status) {
        String path = recentlyRedeemedContract.getRecentlyRedeemedPath();
        log.info("Recently redeemed Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse getRecentlyRedeemed(int status) {
        ValidatableResponse validatableResponse = getRecentlyRedeemedRequest(status);
        contractValidator.validateResponseWithContract("/rewards/GET-200-RecentlyRedeemed.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getRecentlyRedeemedFromDepartmentsRequest(String departmentSeoSlug, int status) {
        String path = recentlyRedeemedContract.getRecentlyRedeemedByDepartmentSeoSlugPath(departmentSeoSlug);
        log.info("Recently redeemed Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse getRecentlyRedeemedFromDepartments(String departmentSeoSlug, int status) {
        ValidatableResponse validatableResponse = getRecentlyRedeemedFromDepartmentsRequest(departmentSeoSlug, status);
        contractValidator.validateResponseWithContract("/rewards/GET-200-RecentlyRedeemed.json", validatableResponse, status);
        return validatableResponse;
    }
}