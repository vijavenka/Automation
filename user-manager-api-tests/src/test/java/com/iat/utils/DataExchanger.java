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

import com.iat.domain.OAuth;
import com.iat.domain.UserGroup;

public class DataExchanger {

    private static DataExchanger dataExchanger;
    private String email;
    private OAuth oAuth;
    private String uuid;
    private UserGroup userGroup;
    private boolean ewsRequest = false;

    private DataExchanger() {
    }

    public static DataExchanger getInstance() {

        if (dataExchanger == null)
            dataExchanger = new DataExchanger();
        return dataExchanger;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OAuth getoAuth() {
        return oAuth;
    }

    public void setoAuth(OAuth oAuth) {
        this.oAuth = oAuth;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public boolean isEwsRequest() {
        return ewsRequest;
    }

    public void setEwsRequest(boolean ewsRequest) {
        this.ewsRequest = ewsRequest;
    }
}