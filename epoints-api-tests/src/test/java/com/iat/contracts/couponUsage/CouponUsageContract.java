package com.iat.contracts.couponUsage;

import com.iat.utils.DataExchanger;

public class CouponUsageContract {
    private DataExchanger dataExchanger = DataExchanger.getInstance();

    public String cashInPath() {
        return "/rest/users/" + dataExchanger.getUuid() + "/coupon-usage-cash-in";
    }

    public String couponListPath(String page, String size) {
        return "/rest/users/" + dataExchanger.getUuid() + "/coupon-usage?page=" + page + "&size=" + size;
    }

    public String couponUsageSummaryPath() {
        return "/rest/users/" + dataExchanger.getUuid() + "/coupon-usage-summary";
    }
}