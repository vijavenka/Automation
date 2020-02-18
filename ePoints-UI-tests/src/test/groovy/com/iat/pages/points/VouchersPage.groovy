package com.iat.pages.points

import com.iat.pages.AbstractPage
import com.iat.pages.points.voucherPageModules.VoucherModule
import com.iat.stepdefs.envVariables

class VouchersPage extends AbstractPage {

    static url = '/points/vouchers'
    static at = {
        waitFor {
            browser.currentUrl.contains("/vouchers")
        }
    }

    def envVar = new envVariables()

    static content = {

        voucherCards{ $('.VoucherCard').moduleList(VoucherModule) }

        //breadcrumb
        breadcrumb { $('.Breadcrumbs') }
        breadCrumbValues { breadcrumb.find('.Breadcrumbs-item').find('a') }
        breadCrumbNumberOfElementsFound { breadcrumb.find('.Breadcrumbs-totalItems') }
        //department filter
        departmentFacet(required: false) { $('.Filter.Filter--single.FilterID--s_department') }
        departmentFacetHeader { departmentFacet.find('.Filter-title') }
        departmentFacetDepartmentButtonBasic { departmentFacet.find('.Filter-name') }
        departmentFacetDepartmentButtonBasicCount { departmentFacet.find('.Filter-count') }
        //merchant filter
        merchantFacet { $('.Filter.Filter--single.FilterID--popularRetailers') }
        merchantFacetClearButton { merchantFacet.find('.Filter-clear') }
        merchantFacetHeader { merchantFacet.find('.Filter-title') }
        merchantFacetMerchantButtonBasic { merchantFacet.find('.Filter-name') }
        //category filter
        categoryFacet(required: false) { $('.Filter.Filter--single.FilterID--s_categoryFromFeedExtracted_multiVal') }
        categoryFacetClearButton { categoryFacet.find('.Filter-clear') }
        categoryFacetHeader { categoryFacet.find('.Filter-title') }
        categoryFacetCategoryButtonBasic(required: false) { categoryFacet.find('.Filter-name') }
        categoryFacetCategoryButtonBasicCount(required: false) { categoryFacet.find('.Filter-count') }

        clearAllButton { $('.FiltersColumn-clear') }
        //top navigation - only filters
        topPaginationBar { $('.topPagination') }
        orderSelectElement { topPaginationBar.find('.paginationOrder-select') }
        orderSelectElementOption { orderSelectElement.find('option') }
        //values: name|asc, name|desc, multiplier:asc, multiplier:desc
        outOfElement { $('.pagination-totalItems') }
//        //main voucher card on individual page
//        indVoucherCard { $('.voucherPage-voucherContainer') }
//        indVoucherMerchantNameBasic { indVoucherCard.find('.voucherPage-merchant') }
//        indVoucherNameBasic { indVoucherCard.find('.voucherPage-title') }
//        indVoucherDescriptionBasic { indVoucherCard.find('.voucherPage-description') }
//        indGetVoucherCodeButtonBasic { indVoucherCard.find('.voucherPage-getCodeButton') }
//        indVoucherCode { indVoucherCard.find('.voucherPage-code') }
//        indVoucherExpiryDateBasic { indVoucherCard.find('.voucherPage-expires') }
//        indVoucherShareFacebookBasic { indVoucherCard.find('.facebook-share') }
//        indVoucherShareTwitterBasic { indVoucherCard.find('.twitter-share') }
//        indVoucherShareGoogleBasic { indVoucherCard.find('.googleplus-share') }
        //other voucher section
//        indVoucherMoreVouchersSectionHeader { $('.voucherPage-moreHeader') }
//
//        moreVouchersVoucherCard { $('.VoucherCard') }
//        moreVouchersVoucherMerchantNameBasic { moreVouchersVoucherCard.find('h3') }
//        moreVouchersVoucherNameBasic { moreVouchersVoucherCard.find('h4') }
//        moreVouchersVoucherDescriptionBasic { moreVouchersVoucherCard.find('p') }
//        moreVouchersGetVoucherCodeButtonBasic { moreVouchersVoucherCard.find('.VoucherCard-getCodeButton') }
//        moreVouchersGoToRetailerButtonBasic { moreVouchersVoucherCard.find('.VoucherCard-openSite') }
//        moreVouchersVoucherCode { moreVouchersVoucherCard.find('.VoucherCard-codeValue') }
//        moreVouchersVoucherExpiryDateBasic { moreVouchersVoucherCard.find('.VoucherCard-expiryDate') }
//        moreVouchersVoucherShareFacebookBasic { moreVouchersVoucherCard.find('.facebook-share') }
//        moreVouchersVoucherShareTwitterBasic { moreVouchersVoucherCard.find('.twitter-share') }
//        moreVouchersVoucherShareGoogleBasic { moreVouchersVoucherCard.find('.googleplus-share') }
//
//        indVoucherMoreVouchersSeeAllButton { $('.u-link') }
    }

    //breadcrumb
    def clickChosenBreadcrumbElement(number) {
        waitFor { breadCrumbValues[number].isDisplayed() }; breadCrumbValues[number].click()
    }
    //department filter
    def selectChosenDepartment(number) {
        waitFor { departmentFacetDepartmentButtonBasic[number].isDisplayed() }
        departmentFacetDepartmentButtonBasic[number].click()
        sleep(1000) //animation
    }
    //merchant filter
    def selectChosenMerchant(number) {
        waitFor { merchantFacetMerchantButtonBasic[number].isDisplayed() }
        merchantFacetMerchantButtonBasic[number].click()
        sleep(1000) //animation
    }

    def clikClearButtonOnMerchantFacet() {
        waitFor { merchantFacetClearButton.isDisplayed() }; merchantFacetClearButton.click()
    }

    //category filter
    def selectChosenCategory(number) {
        waitFor { categoryFacetCategoryButtonBasic[number].isDisplayed() }
        categoryFacetCategoryButtonBasic[number].click()
        sleep(1000)
    }

    def clikClearButtonOnCategoryFacet() {
        waitFor { categoryFacetClearButton.isDisplayed() }; categoryFacetClearButton.click()
    }

    def clickClarAllButton() { waitFor {
        clearAllButton.isDisplayed() }
        clearAllButton.click()
        sleep(1000)
    }

    //top navigation - only filters
    def expandExpiryFilter() { waitFor { orderSelectElement.isDisplayed() }; orderSelectElement.click() }

    def clickChosenExpiryfilterOption(number) {
        waitFor { orderSelectElementOption[number].isDisplayed() }; orderSelectElementOption[number].click()
    }

    def selectSortFilterByName(filterName){
        expandExpiryFilter()
        if (filterName == 'relevance') {
            waitFor { clickChosenExpiryfilterOption(0).text() == envVar.voucherPageSortOptions[0] }
            assert clickChosenExpiryfilterOption(0).text() == envVar.voucherPageSortOptions[0]
            clickChosenExpiryfilterOption(0)
        } else if (filterName == 'expirysoonest') {
            waitFor { clickChosenExpiryfilterOption(1).text() == envVar.voucherPageSortOptions[1] }
            assert clickChosenExpiryfilterOption(1).text() == envVar.voucherPageSortOptions[1]
            clickChosenExpiryfilterOption(1)
        } else if (filterName == 'expirylatest') {
            waitFor { clickChosenExpiryfilterOption(2).text() == envVar.voucherPageSortOptions[2] }
            assert clickChosenExpiryfilterOption(2).text() == envVar.voucherPageSortOptions[2]
            clickChosenExpiryfilterOption(2)
        }
    }
//    //main voucher card on individual page
//    def clickGetVoucherCodeButtonOfMainVoucherOnIndividualVoucherPage() {
//        waitFor { indGetVoucherCodeButtonBasic.isDisplayed() }; indGetVoucherCodeButtonBasic.click()
//    }
//
//    def clickShareFacebookOptionOfMainVoucherOnIndividualVoucherPage() {
//        waitFor { indVoucherShareFacebookBasic.isDisplayed() }; indVoucherShareFacebookBasic.click()
//    }
//
//    def clickShareTwitterOptionOfMainVoucherOnIndividualVoucherPage() {
//        waitFor { indVoucherShareTwitterBasic.isDisplayed() }; indVoucherShareTwitterBasic.click()
//    }
//
//    def clickShareGoogleOptionOfMainVoucherOnIndividualVoucherPage() {
//        waitFor { indVoucherShareGoogleBasic.isDisplayed() }; indVoucherShareGoogleBasic.click()
//    }
//
//    def clickSeeAllButtonOnIndividualVoucherPage() {
//        waitFor { indVoucherMoreVouchersSeeAllButton.isDisplayed() }; indVoucherMoreVouchersSeeAllButton.click()
//    }
//    //other voucher section
//    def clickGetVoucherCodeButtonOfOtherVoucherOnIndividualVoucherPage(number) {
//        waitFor { moreVouchersGetVoucherCodeButtonBasic[number].isDisplayed() }
//        moreVouchersGetVoucherCodeButtonBasic[number].click()
//    }
//
//    def clickShareFacebookOptionOfOtherVoucherOnIndividualVoucherPage(number) {
//        waitFor { moreVouchersVoucherShareFacebookBasic[number].isDisplayed() }
//        moreVouchersVoucherShareFacebookBasic[number].click()
//    }
//
//    def clickShareTwitterOptionOfOtherVoucherOnIndividualVoucherPage(number) {
//        waitFor { moreVouchersVoucherShareTwitterBasic[number].isDisplayed() }
//        moreVouchersVoucherShareTwitterBasic[number].click()
//    }
//
//    def clickShareGoogleOptionOfOtherVoucherOnIndividualVoucherPage(number) {
//        waitFor { moreVouchersVoucherShareGoogleBasic[number].isDisplayed() }
//        moreVouchersVoucherShareGoogleBasic[number].click()
//    }
}