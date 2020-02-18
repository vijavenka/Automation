package com.iat.controller.points;

import com.iat.contracts.points.SearchMerchantContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class SearchMerchantController {

    private SearchMerchantContract searchMerchantContract = new SearchMerchantContract();
    private ContractValidator contractValidator = new ContractValidator();
    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();

    private ValidatableResponse searchMerchantRequest(String keyword, String page, String pageSize, String departments, String prefixes, String favourite, int status) {
        String path = searchMerchantContract.getMerchantsDepartmentsPrefixesList(keyword, page, pageSize, departments, prefixes, favourite);
        log.info("Search Merchant Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getSearchMerchant(String keyword, String page, String pageSize, String departments, String prefixes, String favourite, int status) {
        ValidatableResponse validatableResponse = searchMerchantRequest(keyword, page, pageSize, departments, prefixes, favourite, status);
        contractValidator.validateResponseWithContract("/points/GET-200-SearchMerchant-schema.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse searchMerchantRequestForLoggedUser(String keyword, String page, String pageSize, String departments, String prefixes, String favourite, int status) {
        String path = searchMerchantContract.getMerchantsDepartmentsPrefixesList(keyword, page, pageSize, departments, prefixes, favourite);
        log.info("Search Merchant Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getSearchMerchantForLoggedUser(String keyword, String page, String pageSize, String departments, String prefixes, String favourite, int status) {
        ValidatableResponse validatableResponse = searchMerchantRequestForLoggedUser(keyword, page, pageSize, departments, prefixes, favourite, status);
        contractValidator.validateResponseWithContract("/points/GET-200-SearchMerchant-schema.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getDepartmentsUndecoratedRequest(int status) {
        String path = searchMerchantContract.getDepartmentsUndecoratedPath();
        log.info("Departments undecorated Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getDepartmentsUndecorated(int status) {
        ValidatableResponse validatableResponse = getDepartmentsUndecoratedRequest(status);
        contractValidator.validateResponseWithContract("/points/GET-200-DepartmentsUndecorated-schema.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getMerchantsRequest(int status) {
        String path = searchMerchantContract.getMerchantsAboutUsPagePath();
        log.info("Search about us Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getMerchantsAboutUsPage(int status) {
        ValidatableResponse validatableResponse = getMerchantsRequest(status);
        contractValidator.validateResponseWithContract("/points/GET-200-merchantsAboutUsPage-schema.json", validatableResponse, status);
        return validatableResponse;
    }

}