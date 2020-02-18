package com.iat.stepdefs.utils;

import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import gherkin.deps.com.google.gson.JsonParser;

/* Implementation of JsonParser was done in Singleton convention, please do not modify
*  To get instance of JsonParserUtils for usage, you have to directly add in method:
*  JsonParserUtils jsonParser = JsonParserUtils.getInstance();
*
*  Methods:
*  convertStringToJson - converts provided string to JsonObject
*  extractValueFromFlatJson - returns string value of provided jsonKey within JsonObject
*  extractValuesFromJsonArray - returns StringArray of all values connected with jsonKey.
*                              Method takes as input JsonObject, jsonArrayKey and jsonKey of
*                              seek value.
*
* */

public class JsonParserUtils {

    private static JsonParserUtils jsonParser = new JsonParserUtils();

    private JsonParserUtils() {
    }

    public static JsonParserUtils getInstance() {
        return jsonParser;
    }

    public JsonObject convertStringToJson(String string) {
        return (JsonObject) new JsonParser().parse(string);
    }

    public JsonArray convertStringToJsonArray(String string) {
        return (JsonArray) new JsonParser().parse(string);
    }

    public String extractValueFromFlatJson(JsonObject object, String jsonKey) {
        if ((object.get(jsonKey)) == null) {
            return "null";
        } else {
            return (object.get(jsonKey)).isJsonNull() ? "null" : object.get(jsonKey).getAsString();
        }
    }

    public String[] extractValuesFromFlatJson(JsonArray object, String jsonKey) {
        String[] extractedValue;
        JsonArray jsonArray = object;
        extractedValue = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size(); i++) {
            extractedValue[i] = extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), jsonKey);
        }
        return extractedValue;
    }

    public String extractSingleValueFromJsonArray(JsonObject jsonObject, String jsonArrayKey, String jsonKey) {
        return extractValueFromFlatJson(jsonObject.get(jsonArrayKey).getAsJsonArray().get(0).getAsJsonObject(), jsonKey);
    }

    public String[] extractValuesFromJsonArray(JsonObject jsonObject, String jsonArrayKey, String jsonKey) {
        String[] extractedValue;
        if (!jsonObject.get(jsonArrayKey).isJsonArray()) {
            extractedValue = new String[]{extractValueFromFlatJson(jsonObject.get(jsonArrayKey).getAsJsonObject(), jsonKey)};
        } else {
            JsonArray jsonArray = jsonObject.get(jsonArrayKey).getAsJsonArray();
            extractedValue = new String[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                extractedValue[i] = extractValueFromFlatJson(jsonArray.get(i).getAsJsonObject(), jsonKey);
            }
        }

        return extractedValue;
    }
}

