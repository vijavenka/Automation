package com.iat.validators;

import com.iat.utils.ResponseContainer;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonParser;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GenericValidator {


    public void validateGenericErrorMessage(ResponseContainer response, String error, String message, int status) {

        int extractedStatus = response.getInt("status");
        String extractedError = response.getString("error");
        String extractedMessage = response.getString("message");

        assertThat("Incorrect status", extractedStatus, is(status));
        assertThat("Incorrect error", extractedError, is(error));
        assertThat("Incorrect message", extractedMessage, is(message));

    }

    public void multipleFieldsErrorMessageValidation(String errorJson, ResponseContainer response) {
        List<String> fieldNames = response.getList("errors.field");
        List<String> messages = response.getList("errors.message");

        JsonArray validateFieldErrorsJsonArray = new JsonParser().parse(errorJson).getAsJsonObject().getAsJsonArray("errors");

        for (JsonElement validationElement : validateFieldErrorsJsonArray) {
            boolean foundField = false;
            String errorsFieldNameForAssertion;

            if (!validationElement.getAsJsonObject().get("field").isJsonNull())
                errorsFieldNameForAssertion = validationElement.getAsJsonObject().get("field").getAsString();
            else
                errorsFieldNameForAssertion = null;
            String errorsMessageForAssertion = validationElement.getAsJsonObject().get("message").getAsString();

            for (int i = 0; i < fieldNames.size() && !foundField; i++)
                if (fieldNames.get(i) != null)
                    foundField = fieldNames.get(i).equals(errorsFieldNameForAssertion) && messages.get(i).equals(errorsMessageForAssertion);
            assertThat("Not found Field:" + errorsFieldNameForAssertion + " with message: " + errorsMessageForAssertion, foundField);
        }
    }
}