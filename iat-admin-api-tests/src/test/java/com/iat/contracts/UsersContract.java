package com.iat.contracts;

public class UsersContract {

    public String createUserPath() {
        return "/api/users/";
    }

    public String getUsersListPath(String sortField, String ascending, String dateFrom, String dateTo, String departmentName,
                                   String manager, String user, String status, String page, String maxResults) {

        String path = "/api/users/";

        if (!sortField.equals("null"))
            path += "&sortField=" + sortField;
        if (!ascending.equals("null"))
            path += "&ascending=" + ascending;
        if (!departmentName.equals("null"))
            path += "&departmentName=" + departmentName;
        if (!manager.equals("null"))
            path += "&manager=" + manager;
        if (!dateFrom.equals("null"))
            path += "&dateFrom=" + dateFrom;
        if (!dateTo.equals("null"))
            path += "&dateTo=" + dateTo;
        if (!user.equals("null"))
            path += "&user=" + user;
        if (!status.equals("null"))
            path += "&status=" + status;
        if (!maxResults.equals("null"))
            path += "&maxResults=" + maxResults;
        if (!page.equals("null"))
            path += "&page=" + page;

        return path.replace("/&", "?");
    }

    public String getUserByIdPath(String userId) {
        String path = "/api/users/";

        if (!userId.equals("null"))
            path += userId;

        return path;

    }

    public String updateUserByIdPath(String userId, String emailUpdateType) {
        String path = "/api/users/";

        if (!userId.equals("null"))
            path += userId + "/";

        if (!emailUpdateType.equals("null"))
            path += "&emailUpdateType=" + emailUpdateType;

        return path.replace("/&", "?");

    }

    public String getAdminUserMe() {
        return "/api/users/me";
    }

    public String setPasswordPath() {
        return "/api/account/password";
    }

    public String bulkUploadPath() {
        return "/api/users/bulk-upload";
    }

    public String changePasswordPath() {
        return "/api/users/me/password";
    }

    public String remindPasswordPath() {
        return "/api/account/password/remind";
    }

    public String verifyTokenPath(String token) {
        return "/api/account/token-verification?token=" + token;
    }

    public String resetPasswordPath() {
        return "/api/account/password/reset";
    }

    public String emailChangePromptToBeShownPath(String userUuid) {
        return "/api/epoints/users/" + userUuid + "/was-logged-in";
    }
}
