package com.iat.contracts.users;

public class UsersContract {

    public String getBalancePath(String businessId) {

        String path = "/rest/user/balance/";

        if (!businessId.equals("null"))
            path += "&businessId=" + businessId;

        return path.replace("/&", "?");
    }

    public String getTransactionsPath(String uuid, int page, int size, String sort, String type, String businessId) {

        String path = "/rest/users/" + uuid + "/transactions/";

        if (page != -1)
            path += "&page=" + page;

        if (size != -1)
            path += "&size=" + size;

        if (!sort.equals("null"))
            path += "&sort=" + sort;

        if (!type.equals("null"))
            path += "&type=" + type;

        if (!businessId.equals("null"))
            path += "&businessId=" + businessId;

        return path.replace("/&", "?");
    }

    public String getRewardsHistory(String email, int size, String businessId) {

        String path = "/rest/users/" + email + "/rewards/";

        if (size != -1)
            path += "&size=" + size;

        if (!businessId.equals("null"))
            path += "&businessId=" + businessId;

        return path.replace("/&", "?");
    }

    public String redemptionOrderPath(String businessId, String email) {
        String path = "/rest/users/" + email + "/order";

        if (!businessId.equals("null"))
            path += "?businessId=" + businessId;

        return path;
    }

    public String dashboardPath(String userId, int page, int size, String sort, String type) {
        String path = "/rest/users/" + userId + "/dashboard/";

        if (!(page == -1))
            path += "?page=" + page;

        if (!(size == -1))
            path += "?size=" + size;

        if (!sort.equals("null"))
            path += "?sort=" + sort;

        if (!type.equals("null"))
            path += "?type=" + type;

        return path.replace("/&", "?");
    }

    public String loginSuccessPath() {
        return "/rest/login/loginSuccess";
    }
}