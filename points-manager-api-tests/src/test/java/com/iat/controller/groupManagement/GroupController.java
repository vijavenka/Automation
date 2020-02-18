package com.iat.controller.groupManagement;

import com.iat.contracts.groupManagement.GroupContract;
import com.iat.utils.HMACHeader;
import com.iat.validators.ContractValidator;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class GroupController {

    private HMACHeader hmacHeader = new HMACHeader();
    private GroupContract groupContract = new GroupContract();
    private ContractValidator contractValidator = new ContractValidator();

    private ValidatableResponse getGroupPartnersRequest(String shortName, int status) {
        String date = hmacHeader.generateHttpDate();
        String path = groupContract.getGroupPath(shortName);

        log.info("Path: {}", path);

        return given()
                .contentType(ContentType.JSON)
                .header("Authentication", hmacHeader.getHmacHeader("GET", null, null, path, date))
                .header("X-IAT-Date", date)
                .when()
                .get(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse getGroupPartners(String shortName, int status) {
        ValidatableResponse validatableResponse = getGroupPartnersRequest(shortName, status);

        if (status == 200)
            contractValidator.validateResponseWithContract("groupPartners-response-schema.json", validatableResponse);
        return validatableResponse;
    }

}