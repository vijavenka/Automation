package com.iat.actions.search;

import com.iat.controller.search.SearchController;
import com.iat.domain.facets.Facet;
import com.iat.domain.facets.Facets;
import com.iat.utils.ResponseContainer;

import java.io.UnsupportedEncodingException;

import static com.iat.utils.ResponseContainer.initResponseContainer;

public class SearchActions {

    private SearchController searchController = new SearchController();


    public ResponseContainer getRedemptionItems(String businessId, String filterValue, String keyword, boolean print, int status) {
        return initResponseContainer(searchController.getRedemptionItems(businessId, filterValue, keyword, status), print ? "RESPONSE:" : "");
    }

    public ResponseContainer getRedemptionItems(String businessId, String filterValue, String keyword, int status) {
        return getRedemptionItems(businessId, filterValue, keyword, true, status);
    }

    public ResponseContainer getEpointsRangesAll(String businessId, int status) {
        return initResponseContainer(searchController.getEpointsRangesAll(businessId, status));
    }

    public ResponseContainer getRedemptionFacets(String businessId, String version, String filterValue, int status) {
        return initResponseContainer(searchController.getRedemptionFacets(businessId, version, filterValue, status));
    }

    public ResponseContainer getRedemptionFacetsForSelectedMultivalFilter(String businessId, String filterValue, String version, String multivalFilterName, String multivalFilterValue, int status) {
        return initResponseContainer(searchController.getRedemptionFacetsForSelectedMultivalFilter(businessId, filterValue, version, multivalFilterName, multivalFilterValue, status));
    }

    public String[] getFiltersSetToBuildRequest(String businessId, String filterName) {
        String[] filtersSet = new String[2];

        if (!filterName.equals("null")) {
            String temp;
            if (filterName.equals("s_departmentSeoSlug")) {
                temp = getFilterFromFacets(businessId, filterName, "seo");
                if (temp.contains("null")) {
                    filtersSet[0] = "null";
                    filtersSet[1] = "null";

                    return filtersSet;
                } else {
                    filtersSet[0] = "&fq=" + filterName + ":\"" + temp + "\"";
                    filtersSet[1] = filterName + ":" + temp;

                    return filtersSet;
                }
            } else if (filterName.equals("i_epointsToPurchase")) {
                temp = getFilterFromFacets(businessId, filterName, "seo");
                if (temp.contains("null")) {
                    filtersSet[0] = "null";
                    filtersSet[1] = "null";

                    return filtersSet;
                } else {
                    filtersSet[0] = "&fq=" + filterName + "UK:[" + temp.replace("-", " TO ") + "]";
                    filtersSet[1] = filterName + ":" + temp.replace("-", "%7C");

                    return filtersSet;
                }
            } else if (filterName.equals("s_type")) {
                temp = getFilterFromFacets(businessId, "s_departmentSeoSlug", "seo");
                if (temp.contains("null")) {
                    filtersSet[0] = "null";
                    filtersSet[1] = "null";

                    return filtersSet;
                } else {
                    filtersSet[0] = "&fq=s_departmentSeoSlug:\"" + temp + "\"";
                    filtersSet[1] = "s_departmentSeoSlug" + ":" + temp;

                    temp = getFilterFromFacets(businessId, "s_categoryFromFeedExtractedSeoSlugs_multiVal", "seo");
                    if (temp.contains("null"))
                        return filtersSet;
                    else {
                        filtersSet[0] = filtersSet[0] + "&fq=s_categoryFromFeedExtractedSeoSlugs_multiVal:\"" + temp + "\"";
                        filtersSet[1] = filtersSet[1] + ",s_categoryFromFeedExtractedSeoSlugs_multiVal" + ":" + temp;

                        temp = getFilterFromFacets(businessId, filterName, "seo");
                        if (temp.contains("null"))
                            return filtersSet;
                        else {
                            filtersSet[0] = filtersSet[0] + "&fq=" + filterName + ":\"" + temp + "\"";
                            filtersSet[1] = filtersSet[1] + "," + filterName + ":" + temp;

                            return filtersSet;
                        }
                    }
                }
            } else {
                temp = getFilterFromFacets(businessId, "s_departmentSeoSlug", "seo");
                if (temp.contains("null")) {
                    filtersSet[0] = "null";
                    filtersSet[1] = "null";

                    return filtersSet;
                } else {
                    filtersSet[0] = "&fq=s_departmentSeoSlug:\"" + temp + "\"";
                    filtersSet[1] = "s_departmentSeoSlug" + ":" + temp;

                    temp = getFilterFromFacets(businessId, filterName, "seo");

                    if (temp.contains("null"))
                        return filtersSet;
                    else {
                        filtersSet[0] = filtersSet[0] + "&fq=" + filterName + ":\"" + temp + "\"";
                        filtersSet[1] = filtersSet[1] + "," + filterName + ":" + temp;

                        return filtersSet;
                    }
                }
            }
        } else {
            filtersSet[0] = "null";
            filtersSet[1] = "null";

            return filtersSet;
        }
    }

    private String getFilterFromFacets(String businessId, String filterName, String valueElement) {
        Facets facets;
        ResponseContainer responseFacets;
        if (filterName.equals("s_departmentSeoSlug") || filterName.equals("i_epointsToPurchase"))
            responseFacets = getRedemptionFacets(businessId, "v2", "null", 200);
        else if (filterName.equals("s_type"))
            responseFacets = getRedemptionFacets(businessId, "v2", "s_categoryFromFeedExtractedSeoSlugs_multiVal:" + getFilterFromFacets(businessId, "s_categoryFromFeedExtractedSeoSlugs_multiVal", "seo"), 200);
        else
            responseFacets = getRedemptionFacets(businessId, "v2", "s_departmentSeoSlug:" + getFilterFromFacets(businessId, "s_departmentSeoSlug", "seo"), 200);
        facets = responseFacets.getAsObject(Facets.class);

        for (Facet facet : facets.getFacets()) {
            if (!facet.getName().equals(filterName)) continue;
            if (facet.getValues().size() < 1) return "null";

            switch (valueElement) {
                case "number":
                    return facet.getValues().get(0).toString().split(",")[0].replace("[", "").trim();
                case "seo":
                    return facet.getValues().get(0).toString().split(",")[1].replace("]", "").trim();
                case "name":
                    return facet.getValues().get(0).toString().split(",")[2].replace("]", "").trim();
            }
        }
        return "null";
    }

    public String[] getFiltersSetToBuildRequestForMultivalFacetCall(String businessId, String filterName) {
        String[] filtersSet = new String[3];
        filtersSet[2] = "null";

        if (!filterName.equals("null")) {
            String temp;
            if (filterName.equals("s_type")) {
                temp = getFilterFromFacets(businessId, "s_departmentSeoSlug", "seo");
                if (temp.contains("null")) {
                    filtersSet[0] = "null";
                    filtersSet[1] = "null";
                    filtersSet[2] = "null";

                    return filtersSet;
                } else {
                    filtersSet[0] = "&fq=s_departmentSeoSlug:\"" + temp + "\"";
                    filtersSet[1] = "s_departmentSeoSlug" + ":" + temp;

                    temp = getFilterFromFacets(businessId, "s_categoryFromFeedExtractedSeoSlugs_multiVal", "seo");
                    if (temp.contains("null"))
                        return filtersSet;
                    else {
                        filtersSet[0] = filtersSet[0] + "&fq=s_categoryFromFeedExtractedSeoSlugs_multiVal:\"" + temp + "\"";
                        filtersSet[1] = filtersSet[1] + ",s_categoryFromFeedExtractedSeoSlugs_multiVal" + ":" + temp;

                        temp = getFilterFromFacets(businessId, filterName, "seo");
                        if (temp.contains("null"))
                            return filtersSet;
                        else {
                            filtersSet[2] = getFilterFromFacets(businessId, filterName, "seo");
                            return filtersSet;
                        }
                    }
                }
            } else {
                temp = getFilterFromFacets(businessId, "s_departmentSeoSlug", "seo");
                if (temp.contains("null")) {
                    filtersSet[0] = "null";
                    filtersSet[1] = "null";
                    filtersSet[2] = "null";

                    return filtersSet;
                } else {
                    filtersSet[0] = "&fq=s_departmentSeoSlug:\"" + temp + "\"";
                    filtersSet[1] = "s_departmentSeoSlug" + ":" + temp;

                    temp = getFilterFromFacets(businessId, filterName, "seo");

                    if (temp.contains("null"))
                        return filtersSet;
                    else {
                        filtersSet[2] = getFilterFromFacets(businessId, filterName, "seo");
                        return filtersSet;
                    }
                }
            }
        } else {
            filtersSet[0] = "null";
            filtersSet[1] = "null";
            filtersSet[2] = "null";

            return filtersSet;
        }
    }

    public int getRedemptionCountForSpecifiedFilter(String businessId, String filterName) {
        if (!getFilterFromFacets(businessId, filterName, "number").equals("null"))
            return Integer.parseInt(getFilterFromFacets(businessId, filterName, "number"));
        return -1;
    }

    public ResponseContainer getFacetsForVouchers(String filterValue, int status) throws UnsupportedEncodingException {
        return initResponseContainer(searchController.getFacetsForVouchers(filterValue, status));
    }

    public ResponseContainer getVoucherItems(String filterValue, int page, int pageSize, String sort, int status) throws UnsupportedEncodingException {
        return initResponseContainer(searchController.getVoucherItems(filterValue, page, pageSize, sort, status));
    }
}