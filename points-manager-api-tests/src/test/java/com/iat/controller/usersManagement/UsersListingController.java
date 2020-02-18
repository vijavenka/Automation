package com.iat.controller.usersManagement;

import com.iat.contracts.usersManagement.UsersListingContract;
import com.iat.utils.HMACHeader;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class UsersListingController {

    private HMACHeader hmacHeader = new HMACHeader();
    private UsersListingContract usersListingContract = new UsersListingContract();

    public ValidatableResponse getUsersSearchData(String limit, String sortField, String sortOrder, String offset, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = usersListingContract.getUsersData();
        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("POST", null, null, path, date))
                .header("X-IAT-Date", date)
                .body(usersListingContract.returnRequestBody(limit, sortField, sortOrder, offset))
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }
}