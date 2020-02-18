package com.iat.pages.points.pointsPageModules

import com.iat.pages.AbstractPage

abstract class AbstractPointsPage extends AbstractPage {

    static content = {
        breadcrumbModule { module BreadcrumbModule }
        lettersFacetModule { module LettersFacetModule }
        retailerCardModule { RetailerCardModule }
        departmentsFacetModule { module DepartmentsFacetModule }
        paginationModule { module PaginationPointsModule }
    }
}