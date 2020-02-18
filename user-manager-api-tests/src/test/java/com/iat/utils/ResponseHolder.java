package com.iat.utils;

public class ResponseHolder {

    private static ResponseHolder responseHolder = new ResponseHolder();
    private ResponseContainer response;

    private ResponseHolder() {
    }

    public static ResponseHolder getInstance() {
        return responseHolder;
    }

    public ResponseContainer getResponse() {
        return response;
    }

    public void setResponse(ResponseContainer response) {
        this.response = response;
    }
}
