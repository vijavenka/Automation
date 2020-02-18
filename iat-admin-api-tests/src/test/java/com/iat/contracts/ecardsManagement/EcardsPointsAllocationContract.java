package com.iat.contracts.ecardsManagement;


public class EcardsPointsAllocationContract {

    public String getEcardsAllocationPath(String level, String sortField, String ascending, String page, String maxResults,
                                          String who, String from, String to, String dateFrom, String dateTo,
                                          String description, String amount, String points) {

        String path = "/api/ecards/allocations/" + level + "/";

        if (!sortField.equals("null"))
            path += "&sortField=" + sortField;
        if (!ascending.equals("null"))
            path += "&ascending=" + ascending;
        if (!page.equals("null"))
            path += "&page=" + page;
        if (!maxResults.equals("null"))
            path += "&maxResults=" + maxResults;
        if (!who.equals("null"))
            path += "&who=" + who;
        if (!from.equals("null"))
            path += "&from=" + from;
        if (!to.equals("null"))
            path += "&to=" + to;
        if (!dateFrom.equals("null"))
            path += "&dateFrom=" + dateFrom;
        if (!dateTo.equals("null"))
            path += "&dateTo=" + dateTo;
        if (!description.equals("null"))
            path += "&description=" + description;
        if (!amount.equals("null"))
            path += "&amount=" + amount;
        if (!points.equals("null"))
            path += "&points=" + points;

        return path.replace("/&", "?");
    }

    public String setEcardsAllocationPath(String level) {

        return "/api/ecards/allocations/" + level;
    }

    public String getEcardsAllocationLimitPath() {
        return "/api/ecards/allocations/limit";
    }

    public String getEcardsAllocationLandingPath(String ToValidate) {

        switch(ToValidate) {
            case "DEPARTMENT":
                return "/api/ecards/allocations/limit/department";

            case "USERS_COUNT":
                return "/api/ecards/department/users/count";

            case "TODAY_FALSE":
                return "/api/ecards/milestones/birthdate/today/count?allEvents=false";

            case "WEEK_FALSE":
                return "/api/ecards/milestones/birthdate/week/count?allEvents=false";

            case "TODAY":
                return "/api/ecards/milestones/birthdate/today/count";

            case "WEEK":
                return "/api/ecards/milestones/birthdate/week/count";

            case "wA_TODAY_FALSE":
                return "/api/ecards/milestones/workAnniversary/today/count?allEvents=false";

            case "wA_WEEK_FALSE":
                return "/api/ecards/milestones/workAnniversary/week/count?allEvents=false";

            case "wA_TODAY":
                return "/api/ecards/milestones/workAnniversary/today/count";

            case "wA_WEEK":
                return "/api/ecards/milestones/workAnniversary/week/count";

            case "ECARDS_COUNT":
                return "/api/ecards/sender/count";

            case "RECENT_DATE":
                return "/api/ecards/sender/recentdate";

            default:
                return null;
        }

    }

    public String getEcardsPartnersPath() {
        return "/api/ecards/partners";
    }

    public String getEcardsAllocationStatsPath(String level, String sortField, String ascending, String page, String maxResults,
                                               String who, String from, String to, String dateFrom, String dateTo,
                                               String description, String amount, String points) {

        String path = "/api/ecards/allocations/stats/" + level + "/";

        if (!sortField.equals("null"))
            path += "&sortField=" + sortField;
        if (!ascending.equals("null"))
            path += "&ascending=" + ascending;
        if (!page.equals("null"))
            path += "&page=" + page;
        if (!maxResults.equals("null"))
            path += "&maxResults=" + maxResults;
        if (!who.equals("null"))
            path += "&who=" + who;
        if (!from.equals("null"))
            path += "&from=" + from;
        if (!to.equals("null"))
            path += "&to=" + to;
        if (!dateFrom.equals("null"))
            path += "&dateFrom=" + dateFrom;
        if (!dateTo.equals("null"))
            path += "&dateTo=" + dateTo;
        if (!description.equals("null"))
            path += "&description=" + description;
        if (!amount.equals("null"))
            path += "&amount=" + amount;
        if (!points.equals("null"))
            path += "&points=" + points;

        return path.replace("/&", "?");
    }


    private String getEcardsPath() {
        return "/api/ecards";
    }

    public String getEcardsAwardExportPath(String dateFrom, String dateTo, String key) {
        String path = getEcardsPath() + "/award/export/";

        if (!dateFrom.equals("null"))
            path += "&dateFrom=" + dateFrom;
        if (!dateTo.equals("null"))
            path += "&dateTo=" + dateTo;
        if (!key.equals("null"))
            path += "&key=" + key;

        return path.replace("/&", "?");
    }

    public String getEcardsAllocPartnerExport(String dateFrom, String dateTo, String key) {
        String path = getEcardsPath() + "/allocations/partner/export/";

        if (!dateFrom.equals("null"))
            path += "&dateFrom=" + dateFrom;
        if (!dateTo.equals("null"))
            path += "&dateTo=" + dateTo;
        if (!key.equals("null"))
            path += "&key=" + key;

        return path.replace("/&", "?");
    }

    public String getEcardsAllocDepartmentExport(String dateFrom, String dateTo, String key) {
        String path = getEcardsPath() + "/allocations/department/export/";

        if (!dateFrom.equals("null"))
            path += "&dateFrom=" + dateFrom;
        if (!dateTo.equals("null"))
            path += "&dateTo=" + dateTo;
        if (!key.equals("null"))
            path += "&key=" + key;

        return path.replace("/&", "?");
    }

    public String getExportFile(String key) {
        String path = getEcardsPath() + "/export/";

        if (!key.equals("null"))
            path += "&key=" + key;

        return path.replace("/&", "?");
    }

}
