package com.iat.validator;

import io.restassured.response.ValidatableResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

public class ContractValidator {

    private String contractsDir = "src/test/java/com/iat/contract/json/";

    public void validateResponseWithContract(String contract, String response, int status) {
        /*if (status == 404 || status == 400)
            contract = "/bdlApi/ErrorMessageResponse.json";*/
        //todo in the future it may be useful to "inject" generic error response

        if (contract == null || contract.isEmpty()) {
            assertThat(response, isEmptyOrNullString());
            return;
        }

        String contractFileToCompare = contractsDir + contract;
        Scanner scannerForContract;

        try {
            scannerForContract = new Scanner(new File(contractFileToCompare));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("Contract file: '" + contractFileToCompare + "' was not found!");
            return;
        }

        String jsonObject = scannerForContract.useDelimiter("\\Z").next();
        assertThat(response, matchesJsonSchema(jsonObject));
    }

    public void validateResponseWithContract(String contract, ValidatableResponse validatableResponse, int status) {
        validateResponseWithContract(contract, validatableResponse.extract().response().asString(), status);
    }
}