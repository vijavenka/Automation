package com.iat.controller.tagsManagement;

import com.iat.contracts.tagsManagement.TagsManagementContract;
import com.iat.utils.HMACHeader;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TagsManagementController {

    private HMACHeader hmacHeader = new HMACHeader();
    private TagsManagementContract tagsManagementContract = new TagsManagementContract();

    public ValidatableResponse getTagByTagKey(String tagKey, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = tagsManagementContract.getTagDataByKey(tagKey);
        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getTagsForCurrentClient(int status) {
        String date = hmacHeader.generateHttpDate();
        String path = tagsManagementContract.getAllTagsDataFromCurrentClient();
        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse createNewTag(String tagKey, String cap, String frequency, String description, String autoConfirm, String imageUrl, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = tagsManagementContract.createNewTag();
        return given().
                contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("POST", null, null, path, date))
                .header("X-IAT-Date", date)
                .body(tagsManagementContract.returnCreateTagRequestBody(tagKey, cap, frequency, description, autoConfirm, imageUrl))
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

}