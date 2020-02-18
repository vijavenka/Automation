package com.iat.actions.couponUsage;

import com.iat.controller.couponUsage.CouponUsageController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class CouponUsageActions {

    private CouponUsageController couponUsageController = new CouponUsageController();

    public ResponseContainer cashIn(String jsonBody, int status) {
        return initResponseContainer(couponUsageController.cashIn(jsonBody, status));
    }

    public ResponseContainer getCouponUsageList(String page, String size, int status) {
        return initResponseContainer(couponUsageController.getCouponUsageList(page, size, status));
    }

    public ResponseContainer getCouponUsageList(String page, String size) {
        return getCouponUsageList(page, size, 200);
    }

    public ResponseContainer getCouponUsagePointsSummary(int status) {
        return initResponseContainer(couponUsageController.getCouponUsagePointsSummary(status));
    }

    public ResponseContainer getCouponUsagePointsSummary() {
        return getCouponUsagePointsSummary(200);
    }
}