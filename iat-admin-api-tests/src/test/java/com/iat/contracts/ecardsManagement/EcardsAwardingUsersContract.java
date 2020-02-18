package com.iat.contracts.ecardsManagement;

/**
 * Created by miwanczyk on 2016-01-20.
 */
public class EcardsAwardingUsersContract {

    public String getEcardsAwardingUserPath(String sortField, String ascending, String page, String maxResults, String dateFrom, String dateTo, String from, String to, String points,
                                            String amount, String senderDepartment, String receiverDepartment, String approvalStatus) {

        String path = "/api/ecards/award/";

        if (!sortField.equals("null"))
            path += "&sortField=" + sortField;
        if (!ascending.equals("null"))
            path += "&ascending=" + ascending;
        if (!page.equals("null"))
            path += "&page=" + page;
        if (!maxResults.equals("null"))
            path += "&maxResults=" + maxResults;
        if (!from.equals("null"))
            path += "&from=" + from;
        if (!to.equals("null"))
            path += "&to=" + to;
        if (!dateFrom.equals("null"))
            path += "&dateFrom=" + dateFrom;
        if (!dateTo.equals("null"))
            path += "&dateTo=" + dateTo;
        if (!amount.equals("null"))
            path += "&amount=" + amount;
        if (!points.equals("null"))
            path += "&points=" + points;
        if (!senderDepartment.equals("null"))
            path += "&senderDepartment=" + senderDepartment;
        if (!receiverDepartment.equals("null"))
            path += "&receiverDepartment=" + receiverDepartment;
        if (!approvalStatus.equals("null"))
            path += "&approvalStatus=" + approvalStatus;

        return path.replace("/&", "?");
    }

    public String setEcardsAwardingUserPath() {
        return "/api/ecards/award";
    }

    public String getEcardsAwardingStatsPath() {
        return "/api/ecards/award/stats/";
    }
}
