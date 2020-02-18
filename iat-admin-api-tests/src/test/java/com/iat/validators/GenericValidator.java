package com.iat.validators;

import com.iat.utils.ResponseContainer;
import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonElement;
import gherkin.deps.com.google.gson.JsonParser;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

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
        List<String> fieldNames = response.getList("errors.fieldName");
        List<String> messages = response.getList("errors.message");
        assertThat("There should be the same amout of field names and messages!", fieldNames, hasSize(messages.size()));

        JsonArray validateFieldErrorsJsonArray = new JsonParser().parse(errorJson).getAsJsonObject().getAsJsonArray("errors");

        for (JsonElement validationElement : validateFieldErrorsJsonArray) {
            boolean foundField = false;
            String errorsFieldNameForAssertion;

            if (!validationElement.getAsJsonObject().get("fieldName").isJsonNull())
                errorsFieldNameForAssertion = validationElement.getAsJsonObject().get("fieldName").getAsString();
            else
                errorsFieldNameForAssertion = null;
            String errorsMessageForAssertion = validationElement.getAsJsonObject().get("message").getAsString();

            for (int i = 0; i < fieldNames.size() && !foundField; i++) {
                foundField = fieldNames.get(i) == null && errorsFieldNameForAssertion == null;
                foundField = foundField || (fieldNames.get(i).equals(errorsFieldNameForAssertion) && messages.get(i).equals(errorsMessageForAssertion));
            }
            assertThat("Not found Field:" + errorsFieldNameForAssertion + " with message: " + errorsMessageForAssertion, foundField);
        }
    }

    public void validateStringsOrder(List<String> list, String ascending, String sortedFieldName) {
        String previous;
        String current;

        System.out.println("List for " + sortedFieldName + " validation: " + list);
        for (int i = 1; i < list.size(); i++) {
            previous = list.get(i - 1) == null ? "null" : list.get(i - 1);
            current = list.get(i) == null ? "null" : list.get(i);
            if (ascending.equals("false"))
                assertThat("Sort by: " + sortedFieldName + "\nElement [" + (i - 1) + "]: " + previous + " and [" + i + "]: " + current + " are sorted ascending but should be descending",
                        current.toLowerCase().compareTo(previous.toLowerCase()), is(lessThanOrEqualTo(0)));
            else
                assertThat("Sort by: " + sortedFieldName + "\nElement [" + (i - 1) + "]: " + previous + " and [" + i + "]: " + current + " are sorted descending but should be ascending",
                        current.toLowerCase().compareTo(previous.toLowerCase()), is(greaterThanOrEqualTo(0)));
        }
    }
}