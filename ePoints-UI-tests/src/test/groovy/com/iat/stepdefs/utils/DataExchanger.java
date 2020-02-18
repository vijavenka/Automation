package com.iat.stepdefs.utils;

public class DataExchanger {

    private static DataExchanger dataExchanger;

    private DataExchanger() {
    }

    private String email;
    private String password;
    private String productUrl;

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

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}