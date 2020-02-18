package com.iat.validators.tagsManagement;

import com.iat.utils.ResponseContainer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TagsManagementValidator {

    public void checkCorrectnessOfTagByTagKeyResponseFields(ResponseContainer response, String otherTagFields) {
        String[] tagFields = otherTagFields.split(",");

        assertThat("Cap is not correct", response.getString("cap"), is(tagFields[0]));
        assertThat("Frequency is not correct", response.getString("frequency"), is(tagFields[1]));
        assertThat("Description is not correct", response.getString("description"), is(tagFields[2]));
        assertThat("AutoConfirm is not correct", response.getString("autoConfirm"), is(tagFields[3]));
    }

    public void checkIfReturnedTagNameIsAsExpected(ResponseContainer response, String tagName) {
        assertThat("Tag name is not as expected", response.getString("tagName"), is(tagName));
    }

    public void checkIfResponsDataContainsNoResults(ResponseContainer response) {
        assertThat("Respons has some values", response.toString(), isEmptyOrNullString());
    }

    public void checkIfDataReturnedFromRequestsTagsAndTagByKeyAreEquals(ResponseContainer response, String cap, String frequency, String description, String autoConfirm, String tagKey) {
        assertThat("Caps are not equal", response.getString("cap"), is(cap));
        assertThat("Frequencies are not equal", response.getString("frequency"), is(frequency));
        assertThat("Descriptions are not equal", response.getString("description"), is(description));
        assertThat("autoConfirms are not equal", response.getString("autoConfirm", true), is(autoConfirm));
        assertThat("tagKeys are not equal", response.getString("tagName"), is(tagKey));
    }

    public void checkIfNewTagIdWasReturnedInResponse(ResponseContainer response) {
        assertThat("Id has not proper length", response.toString(), not(isEmptyOrNullString()));
    }

    public void checkIfCreatedTagAvailableOnReturnedTagsList(ResponseContainer response, String tagKey) {
        //TODO change that peace of code when Json parser will be available
        //TODO what if we have array from begin, cannot be converted
        assertThat("CreatedTag not available on list", response.toString(), containsString(tagKey));
    }

    public void checkIfDataReturnedFromRequestsTagsAndCreatedTagDetailsAreEquals(ResponseContainer response, String cap, String frequency, String description, String autoConfirm, String imageUrl) {
        //TODO change that peace of code when Json parser will be available
        //TODO what if we have array from begin, cannot be converted
        String responseString = response.toString();
        assertThat("Caps are not equal", responseString, containsString(cap));
        assertThat("Frequencies are not equal", responseString, containsString(frequency));
        assertThat("Descriptions are not equal", responseString, containsString(description));
        assertThat("autoConfirms are not equal", responseString, containsString(autoConfirm));
        assertThat("imageUrls are not equal", responseString, containsString(imageUrl));
    }

}