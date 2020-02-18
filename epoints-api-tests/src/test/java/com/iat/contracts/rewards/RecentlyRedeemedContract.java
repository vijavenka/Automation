package com.iat.contracts.rewards;

public class RecentlyRedeemedContract {

    public String getRecentlyRedeemedPath() {
        return "/rest/search/recently-redeemed?page=0&pageSize=4";
    }

    public String getRecentlyRedeemedByDepartmentSeoSlugPath(String departmentSeoSlug) {
        return "/rest/search/recently-redeemed?page=0&pageSize=4&s_departmentSeoSlug=" + departmentSeoSlug;
    }
}