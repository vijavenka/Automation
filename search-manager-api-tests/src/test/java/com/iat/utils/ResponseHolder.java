package com.iat.utils;

public class ResponseHolder {

    private String response;
    private int numberOfProducts;
    private int port;
    private static ResponseHolder responseHolder = new ResponseHolder();

    private ResponseHolder() {}

    public static ResponseHolder getInstance() {
        return responseHolder;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public void setNumberOfProducts(int numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }
}
