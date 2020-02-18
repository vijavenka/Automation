package com.iat.contracts.search;

import com.iat.Config;

public class SearchContract {


    //parameters list can be extended in future, full list will be available in search tests here department is needed
    public String getRedemptionItems(String businessId, String filterValue, String keyword) {
        String path = "/rest/search/redemptionitems/";
        if (!businessId.equals("null"))
            path += "&businessId=" + businessId;

//        path += "&filters=";

        /*String[] str1 = {"s_departmentSeoSlug:", ",s_categoryFromFeedExtractedSeoSlugs_multiVal:"};
        String[] str2;
        if (!departmentCategory.equals("null") ) {
            str2 = departmentCategory.split(",",2);
            for(int i=0; i<str2.length; i++){
                path += str1[i] + str2[i];
            }
            path += "&";
        }*/

        if (!filterValue.equals("null"))
            path += "&filters=" + filterValue;

        if (!keyword.equals("null"))
            path += "&keyword=" + keyword;


        return path.replace("/&", "?");
    }

    public String getEpointsRangesAll(String businessId) {
        String path = "/rest/search/epointsranges/all/";
        if (!businessId.equals("null"))
            path += "&businessId=" + businessId;

        return path.replace("/&", "?");
    }

    public String getEpointsFacets(String businessId, String filterValue, String version) {
        String path = "/rest/search/redemptionitems/facets/";
        if (version.equals("v2"))
            path += version + "/";

        if (!businessId.equals("null"))
            path += "&businessId=" + businessId;

        if (!filterValue.equals("null"))
            path += "&filters=" + filterValue;

        return path.replace("/&", "?");
    }

    public String getEpointsFacetsForMultivalFilterOption(String businessId, String filterValue, String version, String multivalFilterName, String multivalFilterValue) {
        String path = "/rest/search/redemptionitems/facets/" + version + "/" + multivalFilterName + "/";

        if (!businessId.equals("null"))
            path += "&businessId=" + businessId;

        if (!filterValue.equals("null"))
            path += "&filters=" + filterValue;

        if (!multivalFilterValue.equals("null"))
            path += "&selected=" + multivalFilterValue.replace(" ", "%20");

        return path.replace("/&", "?");
    }

    public String getVoucherFacets(String filterValue) {
        String path = "/rest/search/vouchers/facets/v2/";

        if (!filterValue.equals("null"))
            path += "&filters=" + filterValue;

        return path.replace("/&", "?");
    }

    public String getVoucherItems(String filterValue, int page, int pageSize, String sort) {
        String path = "/rest/search/vouchers/";

        if (!filterValue.equals("null"))
            path += "&filters=" + filterValue;

        if (page != -1)
            path += "&page=" + page;

        if (pageSize != -1)
            path += "&pageSize=" + pageSize;

        if (!sort.equals("null"))
            path += "&sort=" + sort;


        return path.replace("/&", "?");
    }
}