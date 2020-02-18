package com.iat.contracts;


public class StatisticsContract {

    public String getStatisticsConfigPath() {
        return "/api/statistics/";
    }

    public String getStatisticsPath(String statId, String dateRange, String groupBy, String filters, String previousPeriod) {
        String path = "/api/statistics/";

        if (!statId.equals("null"))
            path += statId + "/";
        // example: 11-11-2015 00:00 +02:00,18-11-2015 23:59 +02:00
        if (!dateRange.equals("null"))
            path += "&dateRange=" + dateRange;
        // example: group:epoints
        if (!filters.equals("null"))
            path += "&filters=" + filters;
        //example: total
        if (!groupBy.equals("null"))
            path += "&groupBy=" + groupBy;
        //example: false
        if (!previousPeriod.equals("null"))
            path += "&previousPeriod=" + previousPeriod;

        return path.replace("/&", "?");
    }

    public String getEcardsUsageBreakdownExportPath(String dateRange, String key) {
        String path = getStatisticsConfigPath() + "ecards-users-breakdown/export/";

        if (!dateRange.equals("null"))
            path += "&dateRange=" + dateRange;
        if (!key.equals("null"))
            path += "&key=" + key;

        return path.replace("/&", "?");
    }

}