package com.iat.validators;

import com.iat.utils.JsonParserUtils;

import static org.junit.Assert.assertTrue;

public class AuditCriteriaValidator {

    JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();

    public void validateGenerationAuditCriteriaErrorMessages(String response, String segmentation, String message, String description, String objectNameFE, String fieldFE, String messageFE) {
        System.out.println("\nRESPONSE: " + response);

        if (!objectNameFE.equals("general")) {
            String[] objectNames = jsonParserUtils.extractValuesFromJsonArray(jsonParserUtils.convertStringToJson(response), "fieldErrors", "objectName");
            String[] fieldNames = jsonParserUtils.extractValuesFromJsonArray(jsonParserUtils.convertStringToJson(response), "fieldErrors", "field");
            String[] messages = jsonParserUtils.extractValuesFromJsonArray(jsonParserUtils.convertStringToJson(response), "fieldErrors", "message");

            boolean fieldFound = false;
            assertTrue("Incorrect error validation message", jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "message").equals(message));

            if (!description.equals("null")) {
                String descriptionExtracted = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description");
                assertTrue("Wrong error description: " + descriptionExtracted + " should be: " + description, descriptionExtracted.equals(description));
            }
            for (int i = 0; i < fieldNames.length; i++) {
                if (fieldNames[i].equals(fieldFE)) {
                    fieldFound = true;
                    assertTrue("Incorrect message for field: " + fieldFE + "  was: " + messages[i] + " but should be: " + messageFE, messages[i].equals(messageFE));

                    assertTrue("Incorrect objectName for field: " + fieldFE + "  was: " + objectNames[i] + " but should be: " + objectNameFE, objectNames[i].equals(objectNameFE));
                }
            }
            assertTrue("Missing field: \"" + fieldFE + "\" in error response", fieldFound);
        } else {
            String descriptionExtracted = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "description");
            if (!message.equals("Request JSON cannot be parsed")) {
                assertTrue("Wrong error description: " + descriptionExtracted + " should be: " + description, descriptionExtracted.equals(description));
            } else {
                assertTrue("Wrong error description: " + descriptionExtracted + " should be: " + description, descriptionExtracted.contains(description));
            }
            String messageExtracted = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(response), "message");
            assertTrue("Wrong error message: " + messageExtracted + " should be: " + message, messageExtracted.equals(message));

        }

    }
}
