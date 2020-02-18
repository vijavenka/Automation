package com.iat.contracts.ecardsManagement;

public class EcardsUsersSearchContract {

    public String getEcardsUserPath(String departmentId, String search) {
        String path = "/api/ecards/department/" + departmentId + "/users";
        if (!search.equals("null"))
            path += "?search=" + search;

        return path;
    }


    public String getSearchCompanyUserPath(String search) {
        String path = "/api/ecards/users";
        if (!search.equals("null"))
            path += "?search=" + search;

        return path;
    }
}
