package com.iat.controller.search;

import com.iat.contracts.search.SearchContract;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static io.restassured.RestAssured.given;

@Slf4j
public class SearchController {

    private SearchContract searchContract = new SearchContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getRedemptions(String businessId, String filterValue, String keyword, int status) {
        String path = searchContract.getRedemptionItems(businessId, filterValue, keyword);
        log.info("Redemptions Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getRedemptionItems(String businessId, String filterValue, String keyword, int status) {
        ValidatableResponse validatableResponse = getRedemptions(businessId, filterValue, keyword, status);
        contractValidator.validateResponseWithContract("/search/GET-200-SearchRedemptions.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getEpointsRangess(String businessId, int status) {
        String path = searchContract.getEpointsRangesAll(businessId);
        log.info("Epoints ranges Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getEpointsRangesAll(String businessId, int status) {
        ValidatableResponse validatableResponse = getEpointsRangess(businessId, status);
        contractValidator.validateResponseWithContract("/search/GET-200-EpointsRanges.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getRedemptionFacetsRequest(String businessId, String version, String filterValue, int status) {
        String path = searchContract.getEpointsFacets(businessId, filterValue, version);
        log.info("Epoints facets Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getRedemptionFacets(String businessId, String version, String filterValue, int status) {
        ValidatableResponse validatableResponse = getRedemptionFacetsRequest(businessId, version, filterValue, status);
        if (version.equals("v2"))
            contractValidator.validateResponseWithContract("/search/GET-200-RedemptionitemsFacetsV2.json", validatableResponse, status);
        else
            contractValidator.validateResponseWithContract("/search/GET-200-RedemptionitemsFacetsV1.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getFacetsForMultivalFilterOptionRequest(String businessId, String filterValue, String version, String multivalFilterName, String multivalFilterValue, int status) {
        String path = searchContract.getEpointsFacetsForMultivalFilterOption(businessId, filterValue, version, multivalFilterName, multivalFilterValue);
        log.info("Epoints facets multival Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .urlEncodingEnabled(false)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getRedemptionFacetsForSelectedMultivalFilter(String businessId, String filterValue, String version, String multivalFilterName, String multivalFilterValue, int status) {
        ValidatableResponse validatableResponse = getFacetsForMultivalFilterOptionRequest(businessId, filterValue, version, multivalFilterName, multivalFilterValue, status);
        contractValidator.validateResponseWithContract("/search/GET-200-RedemptionitemsFacetsV2.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getFacetsForVouchersRequest(String filterValue, int status) throws UnsupportedEncodingException {
        filterValue = URLEncoder.encode(filterValue.trim(), "UTF-8");
        String path = searchContract.getVoucherFacets(filterValue);
        log.info("Voucher facets Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .urlEncodingEnabled(false)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getFacetsForVouchers(String filterValue, int status) throws UnsupportedEncodingException {
        ValidatableResponse validatableResponse = getFacetsForVouchersRequest(filterValue, status);
        contractValidator.validateResponseWithContract("/search/GET-200-RedemptionitemsFacetsV2.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getVoucherItemsRequest(String filterValue, int page, int pageSize, String sort, int status) throws UnsupportedEncodingException {
        boolean urlEncodingShouldBeEnabled = filterValue.contains("merchant") || filterValue.contains("null");

        if (!urlEncodingShouldBeEnabled)
            filterValue = URLEncoder.encode(filterValue.trim(), "UTF-8").replace("%2C", ",").replace("%3A", ":");

        String path = searchContract.getVoucherItems(filterValue, page, pageSize, sort);
        log.info("Voucher Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .urlEncodingEnabled(urlEncodingShouldBeEnabled)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getVoucherItems(String filterValue, int page, int pageSize, String sort, int status) throws UnsupportedEncodingException {
        ValidatableResponse validatableResponse = getVoucherItemsRequest(filterValue, page, pageSize, sort, status);
        contractValidator.validateResponseWithContract("/points/GET-200-SearchVouchers-schema.json", validatableResponse, status);
        return validatableResponse;
    }
}