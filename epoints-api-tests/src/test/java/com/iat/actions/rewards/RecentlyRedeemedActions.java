package com.iat.actions.rewards;

import com.iat.controller.rewards.RecentlyRedeemedController;
import com.iat.utils.ResponseContainer;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class RecentlyRedeemedActions {

    private RecentlyRedeemedController recentlyRedeemedController = new RecentlyRedeemedController();

    public ResponseContainer getRecentlyRedeemed(int status) {
        return initResponseContainer(recentlyRedeemedController.getRecentlyRedeemed(status));
    }

    public ResponseContainer getRecentlyRedeemedFromDepartments(String departmentSeoSlug, int status) {
        return initResponseContainer(recentlyRedeemedController.getRecentlyRedeemedFromDepartments(departmentSeoSlug, status));
    }

}