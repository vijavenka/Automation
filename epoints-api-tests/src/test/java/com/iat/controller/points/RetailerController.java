package com.iat.controller.points;

import com.iat.contracts.points.RetailerContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class RetailerController {

    private RetailerContract retailerContract = new RetailerContract();
    private ContractValidator contractValidator = new ContractValidator();
    public ValidatableResponse validatableResponse;
    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();

    private ValidatableResponse getRecentlyVisitedRetailersRequest(int status) {
        String path = retailerContract.getRecentlyVisitedRetailersPath();
        log.info("Recently visited retailers Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getRecentlyVisitedRetailers(int status) {
        ValidatableResponse validatableResponse = getRecentlyVisitedRetailersRequest(status);
        contractValidator.validateResponseWithContract("/points/GET-200-RecentlyVisitedRetailers-schema.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse putDeleteFavouritesRetailersRequest(String merchantId, String userId, boolean favourite, int status) {
        String path = retailerContract.getFavouritesPath();
        log.info("Put delete favourite retailer Path: {}{}", RestAssured.baseURI, path);
        return given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("merchantId", merchantId)
                .formParam("userId", userId)
                .formParam("favourite", favourite)
                .sessionId("SESSION", sessionId.get())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }


    public ValidatableResponse putDeleteFavouritesRetailers(String merchantId, String userId, boolean favourite, int status) {
        ValidatableResponse validatableResponse = putDeleteFavouritesRetailersRequest(merchantId, userId, favourite, status);
        contractValidator.validateResponseWithContract("/points/POST-200-FavouriteRetailers-schema.json", validatableResponse, status);
        return validatableResponse;
    }

}