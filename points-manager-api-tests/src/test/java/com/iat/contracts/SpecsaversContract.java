package com.iat.contracts;

public class SpecsaversContract {

    public String getReasonsForGroupPath(String shortName, String apiKey, String clientId) {

        String path = "/reasons/" + shortName;

        if (!apiKey.equals("null")) {
            path += "?apiKey=" + apiKey;

            if (!clientId.equals("null"))
                path += "&clientId=" + clientId;
        }
        return path;
    }

    public String getReasonsForPartnerPath(String apiKey) {

        String path = "/reasons";

        if (!apiKey.equals("null"))
            path += "?apiKey=" + apiKey;
        return path;
    }

    public String getUsersInfoForPartnerPath(String shortName, String apiKey) {

        String path = "/partner/" + shortName + "/users";

        if (!apiKey.equals("null"))
            path += "?apiKey=" + apiKey;
        return path;
    }

    public String getAwardedPointsForPartnerPath(String shortName, String apiKey, String page, String pageSize, String startDate, String endDate) {

        String path = "/points/" + shortName + "/awarded";

        if (!apiKey.equals("null")) {
            path += "?apiKey=" + apiKey;

            if (!startDate.equals("null"))
                path += "&startDate=" + startDate;
            if (!endDate.equals("null"))
                path += "&endDate=" + endDate;
            if (!page.equals("null"))
                path += "&page=" + page;
            if (!pageSize.equals("null"))
                path += "&pageSize=" + pageSize;
        } else {
            if (!startDate.equals("null"))
                path += "?startDate=" + startDate;
            if (!endDate.equals("null"))
                path += "&endDate=" + endDate;
            if (!page.equals("null"))
                path += "&page=" + page;
            if (!pageSize.equals("null"))
                path += "&pageSize=" + pageSize;
        }

        return path;
    }

    public String getRedemeedPointsForPartnerPath(String shortName, String apiKey, String page, String pageSize, String startDate, String endDate) {

        String path = "/points/" + shortName + "/redeemed";

        if (!apiKey.equals("null")) {
            path += "?apiKey=" + apiKey;

            if (!startDate.equals("null"))
                path += "&startDate=" + startDate;
            if (!endDate.equals("null"))
                path += "&endDate=" + endDate;
            if (!page.equals("null"))
                path += "&page=" + page;
            if (!pageSize.equals("null"))
                path += "&pageSize=" + pageSize;
        } else {
            if (!startDate.equals("null"))
                path += "?startDate=" + startDate;
            if (!endDate.equals("null"))
                path += "&endDate=" + endDate;
            if (!page.equals("null"))
                path += "&page=" + page;
            if (!pageSize.equals("null"))
                path += "&pageSize=" + pageSize;
        }
        return path;
    }

    public String getReportOverviewForPartnerPath(String shortName, String apiKey, String startDate, String endDate) {

        String path = "/points/" + shortName + "/reportOverview";

        if (!apiKey.equals("null")) {
            path += "?apiKey=" + apiKey;

            if (!startDate.equals("null"))
                path += "&startDate=" + startDate;
            if (!endDate.equals("null"))
                path += "&endDate=" + endDate;
        } else {

            if (!startDate.equals("null"))
                path += "?startDate=" + startDate;
            if (!endDate.equals("null"))
                path += "&endDate=" + endDate;
        }

        return path;
    }

    public String getNetsValuesForPartnerPath(String shortName, String apiKey, String currency) {

        String path = "/points/" + shortName + "/nets";

        if (!apiKey.equals("null")) {
            path += "?apiKey=" + apiKey;

            if (!currency.equals("null"))
                path += "&currency=" + currency;
        } else {
            if (!currency.equals("null"))
                path += "?currency=" + currency;
        }

        return path;
    }
}