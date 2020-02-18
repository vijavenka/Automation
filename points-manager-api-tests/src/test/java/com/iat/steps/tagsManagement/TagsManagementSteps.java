package com.iat.steps.tagsManagement;

import com.iat.actions.tagsManagement.TagsManagementActions;
import com.iat.utils.HelpFunctions;
import com.iat.utils.ResponseContainer;
import com.iat.validators.tagsManagement.TagsManagementValidator;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TagsManagementSteps {

    private TagsManagementActions tagsManagementActions = new TagsManagementActions();
    private TagsManagementValidator tagsManagementValidator = new TagsManagementValidator();
    private HelpFunctions helpFunctions = new HelpFunctions();
    private ResponseContainer response;

    //Scenario Outline: Return tag by id - check correctness of contract and if returned data is as expected
    @When("^Tag By tagKey request will be done with existing in the system '(.+?)'$")
    public void getTagDataByTagKey(String tagKey) throws Throwable {
        response = tagsManagementActions.getTagByTagKeyResponse(tagKey, 200);
        tagsManagementValidator.checkIfReturnedTagNameIsAsExpected(response, tagKey);
    }

    @Then("^Tag details data should be returned$")
    public void checkCorrectnessOfTagByTagResponse() throws Throwable {
        response.validateContract("tagByTag-response-schema.json", 200);
    }

    @And("^'(.+?)' will be as expected according to test data$")
    public void compareTagByTagResponsDataWithTestDataSet(String otherValues) throws Throwable {
        tagsManagementValidator.checkCorrectnessOfTagByTagKeyResponseFields(response, otherValues);
    }

    //Scenario Outline: Return tag by id - check correctness response code and message for tag which not exists in the system
    @When("^Tag By tagKey request will be done with not existing in the system '(.+?)'$")
    public void getTagDataByTagKeyForNotAvailableInTheSystemTagKey(String tagKey) throws Throwable {
        response = tagsManagementActions.getTagByTagKeyResponse(tagKey, 404);
        response.getValidatableResponse().assertThat().statusLine("HTTP/1.1 404 ");
    }

    @Then("^Tag details data should not be returned$")
    public void validateIfResponseDataIsEmpty() throws Throwable {
        tagsManagementValidator.checkIfResponsDataContainsNoResults(response);
    }

    @And("^Response message and code should be as expected$")
    public void checkIfResponseCodeAndMessageAreAsExpected() throws Throwable {
        //Checked on controller level in:
        //Tag By tagKey request will be done with not existing in the system '(.+?)'
        //step
    }

    //Scenario: Return tag list from current user - compare tag details of one tag from list with data returned by tag by tagKey request
    @Given("^List of current client tags is pulled$")
    public void getTagsData() throws Throwable {
        response = tagsManagementActions.getTagsResponse(200);
    }

    @And("^Tags request response data has proper structure$")
    public void checkCorrectnessOfTagsResponse() throws Throwable {
        response.validateContract("tags-response-schema.json", 200);
    }

    private String singleTagElement;
    private String cap, frequency, description, autoConfirm, tagKey, imageUrl;

    @When("^Tag By tagKey request will be done with one of previous returned tagKeys$")
    public void getTagDataByTagKeyForSelectedTag() throws Throwable {
        //TODO change that peace of code when Json parser will be available
        String responseString = response.toString();
        singleTagElement = responseString.substring(responseString.lastIndexOf("{"), responseString.lastIndexOf("}") + 1);
        cap = helpFunctions.returnNeededJsonFieldValue(singleTagElement, "cap");
        frequency = helpFunctions.returnNeededJsonFieldValue(singleTagElement, "frequency");
        description = helpFunctions.returnNeededJsonFieldValue(singleTagElement, "description");
        autoConfirm = helpFunctions.returnNeededJsonFieldValue(singleTagElement, "autoConfirm");
        tagKey = helpFunctions.returnNeededJsonFieldValue(singleTagElement, "tagName");

        response = tagsManagementActions.getTagByTagKeyResponse(tagKey, 200);
    }

    @Then("^Tag details returned by first request will be the same as in second request$")
    public void compareTagByTagResponsDataWithThoseRetunedByTagsRequest() throws Throwable {
        tagsManagementValidator.checkIfDataReturnedFromRequestsTagsAndTagByKeyAreEquals(response, cap, frequency, description, autoConfirm, tagKey);
    }

    //Scenario: Create new tag - check if created tag is available on list of returned tags and all data is as expected
    @And("^New tag is already created$")
    public void crateNewTagWithRandomData() throws Throwable {
        //TODO probably some picoContainer implementation will be helpful here
        tagKey = "tagKey_" + helpFunctions.returnRandomValue(10000);
        cap = "10000";
        frequency = "ONCE";
        description = "description_" + helpFunctions.returnRandomValue(10000);
        autoConfirm = "1";
        imageUrl = "https://ww.imageUrl." + helpFunctions.returnRandomValue(10000);
        response = tagsManagementActions.createNewTag(tagKey, cap, frequency, description, autoConfirm, imageUrl, 201);
        tagsManagementValidator.checkIfNewTagIdWasReturnedInResponse(response);
    }

    @Then("^New tag will be available on the list$")
    public void checkIfCreatedTagNameIsAvailableOnTheList() throws Throwable {
        response = tagsManagementActions.getTagsResponse(200);
        tagsManagementValidator.checkIfCreatedTagAvailableOnReturnedTagsList(response, tagKey);
    }

    @And("^New tag details on the list will be same as those posted during creation$")
    public void checkIfRestOfFieldsOfNewTagWasProperlyReturnedByTagsRequest() throws Throwable {
        tagsManagementValidator.checkIfDataReturnedFromRequestsTagsAndCreatedTagDetailsAreEquals(response, cap, frequency, description, autoConfirm, imageUrl);
    }

    //Scenario: Create new tag - check if system properly responds when tried to create tag which exists in the system
    @When("^New tag creation request will be done with tagKey which exists in system$")
    public void tryToCreateTagWithExistingInSystemTagKey() throws Throwable {
        response = tagsManagementActions.createNewTag("epointsRegistration", cap, frequency, description, autoConfirm, imageUrl, 400);
    }

    @Then("^New tag will not be created$")
    public void checkIfNewTagIsNotCreated() throws Throwable {
        tagsManagementValidator.checkIfResponsDataContainsNoResults(response);
    }

    //Scenario: Create new tag - check if created tag can be found and returned by tag by tagKey request
    @When("^Tag By tagKey request will be done for new created tagKey$")
    public void getTagDataByTagKey() throws Throwable {
        response = tagsManagementActions.getTagByTagKeyResponse(tagKey, 200);
        tagsManagementValidator.checkIfReturnedTagNameIsAsExpected(response, tagKey);
    }

    @And("^All returned tag details will be as expected according to created tag$")
    public void compareTagByTagResponsDataWithTestDataSet() throws Throwable {
        tagsManagementValidator.checkIfDataReturnedFromRequestsTagsAndCreatedTagDetailsAreEquals(response, cap, frequency, description, autoConfirm, imageUrl);
    }
}