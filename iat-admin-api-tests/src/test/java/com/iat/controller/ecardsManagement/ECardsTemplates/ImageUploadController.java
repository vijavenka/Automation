package com.iat.controller.ecardsManagement.ECardsTemplates;

import com.iat.contracts.ecardsManagement.ECardsTemplates.ImageUploadContract;
import com.iat.utils.DataExchanger;
import com.iat.validators.ContractValidator;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.SocketException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

@Slf4j
public class ImageUploadController {

    private ImageUploadContract imageUploadContract = new ImageUploadContract();
    private ContractValidator contractValidator = new ContractValidator();
    private DataExchanger sessionId = DataExchanger.getInstance();

    private ValidatableResponse uploadImageRequest(String imageName, String rescale, int status) throws SocketException {
        String path = imageUploadContract.uploadImagePath(rescale);
        log.info("Path: POST {}", path);
        return given()
                .multiPart("image", new File("src//images//" + imageName))
                .sessionId("SESSION", sessionId.getSessionId())
                .when()
                .post(path)
                .then()
                .statusCode(status);
    }

    public ValidatableResponse uploadImage(String imageName, String rescale, int status) {
        ValidatableResponse validatableResponse = null;
        try {
            validatableResponse = uploadImageRequest(imageName, rescale, status);
            if (status != 200)
                contractValidator.validateResponseWithContract("imageProcessing/POST-imageUploadErrors-response-schema.json", validatableResponse);
            else
                contractValidator.validateResponseWithContract("imageProcessing/POST-imageUpload-response-schema.json", validatableResponse, status);
        } catch (SocketException e) {
//            System.out.println(e.toString());
            //Different response is returned when run by Jenkins
            assertThat("Connection reset by peer", e.toString(), anyOf(containsString("java.net.SocketException: Connection reset by peer: socket write error"), containsString("java.net.SocketException: Broken pipe")));
        }
        return validatableResponse;
    }
}