package com.iat.repository

import com.iat.pages.rewards.modules.FilterType

interface SolrRepository {

    String getUrlOfNotActiveProduct()

    String getUrlOfNotActiveProduct(String businessId)

    String getUrlOfMaxImagesProducts(String externalIdType)

    String getUrlOfAnyProduct(String externalIdType)

    //when externalIdType is not set - the default scope (epoints) is used
    String getDepartmentWithFilter(FilterType filter)

    String getDepartmentWithFilter(FilterType filter, String externalIdType)

    //when externalIdType is not set - the default scope (epoints) is used
    String getCategoryWithFilter(String department, FilterType filter)

    String getCategoryWithFilter(String department, FilterType filter, String externalIdType)

    //when externalIdType is not set - the default scope (epoints) is used
    int getNumberOfActiveProducts(String department, String category)

    int getNumberOfActiveProducts(String department, String category, String externalIdType)

    //when externalIdType is not set - the default scope (epoints) is used
    int getNumberOfFilteredActiveProducts(String department, String category, FilterType filterType, String filterValue)

    int getNumberOfFilteredActiveProducts(String department, String category, FilterType filterType, String filterValue, String externalIdType)
}