package com.iat.repository.impl

import com.iat.Config
import com.iat.pages.rewards.modules.FilterType
import com.iat.repository.SolrRepository
import com.iat.stepdefs.utils.Functions
import groovy.json.JsonSlurper

import static io.restassured.RestAssured.given

class SolrRepositoryImpl implements SolrRepository {

    private String query = ""
    private String productScope

    SolrRepositoryImpl() {}

    SolrRepositoryImpl(String productScope) {
        this.productScope = productScope
    }

    private SolrRepositoryImpl putToQuery(String key, value) {
        if (value in String && value.contains(" ") && !(value.startsWith("[") && value.endsWith("]"))) value = "\"$value\""
        else value = "$value"
        if (!query.isEmpty()) query += "&"
        query += "fq="
        query += URLEncoder.encode("$key:$value", "UTF-8")
        return this
    }

    private SolrRepositoryImpl putToQuery(String keyVal) {
        return putToQuery(keyVal.split(":")[0], keyVal.split(":")[1])
    }

    private SolrRepositoryImpl resetQuery() {
        query = ""
        return this
    }

    private executeQuery() {
        //at the moment we are using - most of the time (always?) - the UK products. We will rearrange this a little if there will be need for other zones
        putToQuery("b_zoneUK", true)

        String solrUrl = Config.getSolrUrl()
        String path = "$solrUrl/select?$query&wt=json&indent=true"
        def solrResponse = given().urlEncodingEnabled(false).get(path).then().statusCode(200)
        solrResponse = solrResponse.extract().asString()
        return new JsonSlurper().parseText(solrResponse).response
    }

    private String getUrlOfSolrProduct(product, String externalIdType) {
        String department = product.s_departmentSeoSlug
        String category = product.s_categoryFromFeedExtractedSeoSlugs_multiVal[0]
        String seoSlug = product.s_seoSlug
        String id = product.id

        if (externalIdType.toUpperCase() == "UNITED") {
            return "/united/rewards/$department/$category/$seoSlug/$id"
        } else {
            return "/rewards/$department/$category/$seoSlug/$id"
        }
    }

    String getUrlOfNotActiveProduct(String externalIdType) {
        def response = resetQuery()
                .setProductScope(externalIdType)
                .putToQuery("b_active", false)
                .executeQuery()

        if (response.numFound < 1) return ""

        return getUrlOfSolrProduct(response.docs[0], externalIdType)

    }

    String getUrlOfNotActiveProduct() {
        return getUrlOfNotActiveProduct("")
    }

    String getUrlOfMaxImagesProducts(String externalIdType) {
        //todo it is not possible to search solr with s_additionalImageUrls_multiVal parameter (not indexed), so the FEED was created for that
        def response = resetQuery()
                .setProductScope(externalIdType)
                .putToQuery("s_feedDefinitionShortName", "FOUR_ADD_IMAGES")
                .executeQuery()

        if (response.numFound < 1) return ""

        return getUrlOfSolrProduct(response.docs[0], externalIdType)

    }

    String getUrlOfAnyProduct(String externalIdType) {
        def response = resetQuery()
                .setProductScope(externalIdType)
                .putToQuery("b_active", true)
                .executeQuery()

        if (response.numFound < 1) return ""

        return getUrlOfSolrProduct(response.docs[0], externalIdType)
    }

    String getDepartmentWithFilter(FilterType filter, String externalIdType) {
        def response = resetQuery()
                .setProductScope(externalIdType)
                .putToQuery(filter.toString(), "*")
                .putToQuery("b_active", true).executeQuery()

        if (response.numFound < 1) return ""

        int department = response.numFound
        if (department > 40) department = 40 //by default only 40 items are placed on one page when retrieving from solr
        department = new Functions().returnRandomValue(department)

        return response.docs[department].s_department
    }

    String getDepartmentWithFilter(FilterType filter) {
        return getDepartmentWithFilter(filter, "")
    }

    String getCategoryWithFilter(String department, FilterType filter, String externalIdType) {
        def response = resetQuery()
                .setProductScope(externalIdType)
                .putToQuery("s_department", department)
                .putToQuery(filter.toString(), "*")
                .putToQuery("b_active", true)
                .executeQuery()

        if (response.numFound < 1) return ""

        int category = response.numFound
        if (category > 40) category = 40 //by default only 40 items are placed on one page when retrieving from solr
        category = new Functions().returnRandomValue(category)

        return response.docs[category].s_categoryFromFeedExtracted_multiVal[0]
    }

    String getCategoryWithFilter(String department, FilterType filter) {
        return getCategoryWithFilter(department, filter, "")
    }

    int getNumberOfActiveProducts(String department, String category, String externalIdType) {
        if (department == null || department.isEmpty() || department == "null") department = "*"
        if (category == null || category.isEmpty() || category == "null") category = "*"
        def response = resetQuery()
                .setProductScope(externalIdType)
                .putToQuery("s_department", department)
                .putToQuery("s_categoryFromFeedExtracted_multiVal", category)
                .putToQuery("b_active", true)
                .executeQuery()

        return response.numFound
    }

    int getNumberOfActiveProducts(String department, String category) {
        return getNumberOfActiveProducts(department, category, "")
    }

    int getNumberOfFilteredActiveProducts(String department, String category, FilterType filterType, String filterValue, String externalIdType) {
        if (department == null || department.isEmpty() || department == "null") department = "*"
        if (category == null || category.isEmpty() || category == "null") category = "*"
        def response = resetQuery()
                .setProductScope(externalIdType)
                .putToQuery("s_department", department)
                .putToQuery("s_categoryFromFeedExtracted_multiVal", category)
                .putToQuery(filterType.toString(), filterValue)
                .putToQuery("b_active", true)
                .executeQuery()

        return response.numFound
    }

    int getNumberOfFilteredActiveProducts(String department, String category, FilterType filterType, String filterValue) {
        return getNumberOfFilteredActiveProducts(department, category, filterType, filterValue, "")
    }

    private SolrRepositoryImpl setProductScope(String externalIdType) {
        if (productScope != null) externalIdType = productScope
        if (externalIdType == null || externalIdType.isEmpty() || externalIdType == "null"
                || externalIdType.toLowerCase() == "epoints")
            return putToQuery("-s_productScope:*")

        return putToQuery("s_productScope", externalIdType)
    }
}