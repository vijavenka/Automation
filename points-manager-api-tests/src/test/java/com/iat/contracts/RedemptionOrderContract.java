package com.iat.contracts;

public class RedemptionOrderContract {


    public String getCreateRedemptionOrderPath(String userId, String idType, String apiKey) {
        String path = "/redemptionOrder/user/" + userId + "/";

        if (!idType.equals("null"))
            path += "&idType=" + idType;
        if (!apiKey.equals("null"))
            path += "&apiKey=" + apiKey;

        return path.replace("/&", "/?");
    }

    public String getRedemptionOrderPath(String apiKey, String orderId) {
        String path = "/redemptionOrder/" + orderId + "/";

        if (!apiKey.equals("null"))
            path += "&apiKey=" + apiKey;

        return path.replace("/&", "/?");
    }

    public String getRecentlyRedeemedPath(String apiKey, String region, String zone, String offset, String limit) {
        String path = "/recently-redeemed" + "/";

        if (!apiKey.equals("null"))
            path += "&apiKey=" + apiKey;
        if (!region.equals("null"))
            path += "&region=" + region;
        if (!zone.equals("null"))
            path += "&zone=" + zone;
        if (!offset.equals("null"))
            path += "&offset=" + offset;
        if (!limit.equals("null"))
            path += "&limit=" + limit;

        return path.replace("/&", "/?");
    }

    public String getRedemptionOrderHistoryPath(String userId, String idType, String apiKey, String ascending, String offset, String limit, String startDate, String endDate) {
        String path = "/redemptionOrder/user/" + userId + "/";

        if (!idType.equals("null"))
            path += "&idType=" + idType;
        if (!apiKey.equals("null"))
            path += "&apiKey=" + apiKey;
        if (!ascending.equals("null"))
            path += "&ascending=" + ascending;
        if (!offset.equals("null"))
            path += "&offset=" + offset;
        if (!limit.equals("null"))
            path += "&limit=" + limit;
        if (!startDate.equals("null"))
            path += "&startDate=" + startDate;
        if (!endDate.equals("null"))
            path += "&endDate=" + endDate;

        return path.replace("/&", "/?");
    }
}
