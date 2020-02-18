package com.iat.stepdefs.utils;

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

public class DataExchanger {

    private static DataExchanger dataExchanger;

    private DataExchanger() {
    }

    public static DataExchanger getInstance() {

        if (dataExchanger == null) {
            dataExchanger = new DataExchanger();
        }
        return dataExchanger;
    }

    private String userEmail;
    private String userUuid;

    private int notificationsNumber;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public int getNotificationsNumber() {
        return notificationsNumber;
    }

    public void setNotificationsNumber(int notificationsNumber) {
        this.notificationsNumber = notificationsNumber;
    }

}