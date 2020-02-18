package com.iat.controller.users;

import com.iat.contracts.users.UsersContract;
import com.iat.utils.SessionIdKeeper;
import com.iat.validators.ContractValidator;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class UsersController {

    private SessionIdKeeper sessionId = SessionIdKeeper.getInstance();
    private UsersContract usersContract = new UsersContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getUserBalanceRequest(String businessId, int status) {
        String path = usersContract.getBalancePath(businessId);

        log.info("GET Balance Path {} ", RestAssured.baseURI + path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    private ValidatableResponse getUserBalanceRequestOAuth(String businessId, String access_token, int status) {
        String path = usersContract.getBalancePath(businessId);

        log.info("GET Balance Path {} ", RestAssured.baseURI + path);

        Header header = null;
        if (!access_token.equals("null"))
            header = new Header("Authorization", "Bearer " + access_token);

        return given()
                .contentType(ContentType.JSON)
                .header(header)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUserBalance(String businessId, String access_token, int status) {
        ValidatableResponse validatableResponse;
        if (access_token.equals("null"))
            validatableResponse = getUserBalanceRequest(businessId, status);
        else
            validatableResponse = getUserBalanceRequestOAuth(businessId, access_token, status);
        contractValidator.validateResponseWithContract("/users/GET-balance.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getUserTransactionsRequest(String uuid, int page, int size, String sort, String type, String businessId, int status) {
        String path = usersContract.getTransactionsPath(uuid, page, size, sort, type, businessId);

        log.info("GET Transactions Path {} ", RestAssured.baseURI + path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUserTransactions(String uuid, int page, int size, String sort, String type, String businessId, int status) {
        ValidatableResponse validatableResponse = getUserTransactionsRequest(uuid, page, size, sort, type, businessId, status);
        contractValidator.validateResponseWithContract("/users/GET-200-transactions.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse getUserRewardsHistoryRequest(String email, int size, String businessId, int status) {
        String path = usersContract.getRewardsHistory(email, size, businessId);

        log.info("GET Rewards History Path {} ", RestAssured.baseURI + path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getUserRewardsHistory(String email, int size, String businessId, int status) {
        ValidatableResponse validatableResponse = getUserRewardsHistoryRequest(email, size, businessId, status);
        contractValidator.validateResponseWithContract("/users/GET-200-rewardsHistory.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse redemptionOrderRequest(String businessId, String email, String jsonBody, int status) {
        String path = usersContract.redemptionOrderPath(businessId, email);

        log.info("POST Path {} ", RestAssured.baseURI + path);
        log.info("BODY: {} ", jsonBody);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    private ValidatableResponse redemptionOrderRequestOauth(String businessId, String email, String jsonBody, String access_token, int status) {
        String path = usersContract.redemptionOrderPath(businessId, email);

        log.info("POST Redemption order Path {} ", RestAssured.baseURI + path);
        log.info("POST Body {} ", RestAssured.baseURI + path);

        Header header = null;
        if (!access_token.equals("null"))
            header = new Header("Authorization", "Bearer " + access_token);

        return given()
                .contentType(ContentType.JSON)
                .header(header)
                .body(jsonBody)
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse redemptionOrder(String businessId, String email, String jsonBody, String access_token, int status) {
        ValidatableResponse validatableResponse;
        if (access_token.equals("null"))
            validatableResponse = redemptionOrderRequest(businessId, email, jsonBody, status);
        else
            validatableResponse = redemptionOrderRequestOauth(businessId, email, jsonBody, access_token, status);
        return validatableResponse;
    }

    private ValidatableResponse getAccountDashboardRequest(String userId, int page, int size, String sort, String type, int status) {
        String path = usersContract.dashboardPath(userId, page, size, sort, type);

        log.info("GET Dashboard Path {} ", RestAssured.baseURI + path);

        return given()
                .contentType(ContentType.JSON)
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getAccountDashboard(String userId, int page, int size, String sort, String type, int status) {
        ValidatableResponse validatableResponse = getAccountDashboardRequest(userId, page, size, sort, type, status);
        contractValidator.validateResponseWithContract("/users/GET-200-dashboard.json", validatableResponse, status);
        return validatableResponse;
    }

    private ValidatableResponse membershipType(int status){
        String path =  usersContract.loginSuccessPath();

        log.info("GET LoginSuccess Path {} {}", RestAssured.baseURI + path, sessionId.get());

        return given()
                .sessionId("SESSION", sessionId.get())
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getMembershipType(int status) {
        ValidatableResponse validatableResponse = membershipType(status);
        contractValidator.validateResponseWithContract("/users/GET-200-loginSuccess.json", validatableResponse, status);
        return validatableResponse;
    }
}