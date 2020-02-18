package com.iat.steps.ecardsManagement.ECardsTemplates;

import com.iat.actions.ecardsManagement.ECardsTemplates.ImageUploadActions;
import com.iat.utils.ResponseContainer;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ImageUploadSteps {

    private ImageUploadActions imageUploadActions = new ImageUploadActions();
    private ResponseContainer response;

    //Scenario Outline: Image Processing - upload image into system
    @When("^Admin upload image file into the system '(.+?)', '(.+?)'$")
    public void uploadImageIntoSystem(String imageName, String rescale) throws Throwable {
        response = imageUploadActions.uploadImage(imageName, rescale, 200);
    }

    @Then("^Image file should be uploaded '(.+?)', '(.+?)'$")
    public void checkIfImageWasUploadedAndResized(String rescale, String warningMessage) throws Throwable {
        assertThat("Image url was not returned", response.get("imageUrl"), containsString("http"));
        if (rescale.equals("true")) {
            assertThat("Image url was not returned", response.get("thumbnailUrl"), containsString("http"));
        } else {
            assertThat("Thumbnail url was returned", response.get("thumbnailUrl"), is(nullValue()));
        }

        if (!warningMessage.equals("null")) {
            assertThat("Warning message is incorrect", response.getString("warningMessage"), is(warningMessage));
        } else {
            assertThat("Warning message was returned", response.getString("warningMessage"), is(nullValue()));
        }

    }

    //Scenario Outline: Image Processing - upload image with invalid format or size
    @When("^Admin upload image file with invalid format or size '(.+?)', '(.+?)', '(\\d+)'$")
    public void uploadImageIntoSystemWithInvalidTypeOrSize(String imageName, String rescale, int status) throws Throwable {
        response = imageUploadActions.uploadImage(imageName, rescale, status);
    }

}