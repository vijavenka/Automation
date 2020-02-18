package com.iat.contracts.points;

public class SearchMerchantContract {

    public String getMerchantsDepartmentsPrefixesList(String keyword, String page, String pageSize, String departments, String prefixes, String favourite) {

        String path = "/rest/search/merchant/";

        if (!keyword.equals("null"))
            path += "&keyword=" + keyword;

        if (!page.equals("null"))
            path += "&page=" + page;

        if (!pageSize.equals("null"))
            path += "&pageSize=" + pageSize;

        if (!departments.equals("null"))
            path += "&departments=" + departments;

        if (!prefixes.equals("null"))
            path += "&prefixes=" + prefixes;

        if (!favourite.equals("null"))
            path += "&favourite=" + favourite;

        return path.replace("/&", "?");
    }

    public String getDepartmentsUndecoratedPath() {
        return "/rest/departments/undecorated/with-categories";
    }

    public String getMerchantsAboutUsPagePath() {
        return "/rest/user/merchants";
    }
}