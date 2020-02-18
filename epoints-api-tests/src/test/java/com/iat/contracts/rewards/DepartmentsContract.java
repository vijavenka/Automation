package com.iat.contracts.rewards;

public class DepartmentsContract {

    public String getDepartmentsPath(String businessId) {
        String path = "/rest/departments";
        return !businessId.equals("null") ? path + "?businessId=" + businessId : path;
    }
}