package com.iat.pages.points

import com.iat.pages.AbstractPage
import com.iat.pages.home.modules.*
import com.iat.pages.points.pointsPageModules.BreadcrumbModule
import com.iat.pages.points.unitedPointsPageModules.LatestDealModule

class UnitedPointsPage extends AbstractPage {

    static url = '/united/points'
    static at = { waitFor { browser.currentUrl.contains("/united/points") } }

    static content = {
        //Included Modules
        cookiePanelModule { module CookiePanelModule }
        headerModule { module HeaderModule }
        userDropDownMenuModule { module UserDropDownMenuModule }
        basketModule { module BasketModule }
        footerModule { module FooterModule }
        accountSwitcher(required: false) { module(SwitchAccountModule) }
        breadcrumbModule { module BreadcrumbModule }

        mainBanner(wait: true, required: true) { $('.BlockWithBanner-image') }
        latestDeals(required: true) { $(".CriteriaCard").moduleList(LatestDealModule) }

        promotedBrandsSectionTitle(required: true, wait: true) { $('.PopularBrands h3') }
        promotedBrandImage(required: true, wait: true) { $('.PopularBrands-img') }

    }
}