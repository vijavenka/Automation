package com.iat.contracts.usersManagement;

public class UsersListingContract {

    public String getUsersData() {
        return "/search/users";
    }

    public String returnRequestBody(String limit, String sortField, String sortOrder, String offset) {
        if (limit.equals("") || sortField.equals("") || sortOrder.equals("") || offset.equals(""))
            return "{}";
        else {
            if (sortOrder.equals("ascending"))
                return "{ \"limit\": " + limit + ", \"sortBy\": \"" + sortField + "\", \"offset\":" + offset + ", \"ascending\": true }";
            else if (sortOrder.equals("descending"))
                return "{ \"limit\": " + limit + ", \"sortBy\": \"" + sortField + "\", \"offset\":" + offset + ", \"ascending\": false }";
            else
                return "{ \"limit\": " + limit + ", \"sortBy\": \"" + sortField + "\", \"offset\":" + offset + ", \"ascending\": wrong }";
        }
    }

}