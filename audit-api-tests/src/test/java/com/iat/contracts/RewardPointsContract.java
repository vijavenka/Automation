package com.iat.contracts;

public class RewardPointsContract {

    public String getRewardPointsPath(String params) {
        String[] param = params.split(";");

        String path = "/api/reward-points/";

        if (param.length > 1) {
            String page = param[0];
            String size = param[1];
            String sort = param[2];

            if (!page.equals("null"))
                path += "&page=" + page;

            if (!size.equals("null"))
                path += "&size=" + size;

            if (!sort.equals("null"))
                path += "&sort=" + sort;

        }
        return path.replace("/&", "?");
    }

    public String getRewardPointsByIdPath(String id) {
        return "/api/reward-points" + id;
    }
}