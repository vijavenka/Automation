package com.iat.utils;

/* Implementation of SessionIdKeeper was done in Singleton convention, please do not modify
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

public class SessionIdKeeper {

    private static SessionIdKeeper sessionId;

    private SessionIdKeeper() {
    }

    private String ID;

    public static SessionIdKeeper getInstance() {

        if (sessionId == null)
            sessionId = new SessionIdKeeper();
        return sessionId;
    }

    public void set(String id) {
        ID = id;
    }

    public String get() {
        return ID;
    }
}

