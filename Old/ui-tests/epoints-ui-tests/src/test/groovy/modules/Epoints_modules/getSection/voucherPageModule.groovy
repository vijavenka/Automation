package modules.Epoints_modules.getSection

import geb.Module

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 23.10.14
 * Time: 08:45
 * To change this template use File | Settings | File Templates.
 */

class voucherPageModule extends Module{
    static content = {
    //breadcrumb
        breadcrumb{ $('.Breadcrumbs') }
        breadCrumbValues{ breadcrumb.find('.Breadcrumbs-item').find('a') }
        breadCrumbNumberOfElementsFound{ breadcrumb.find('.Breadcrumbs-totalItems') }
    //department filter
        departmentFacet(required: false){ $('.Filter.Filter--single.FilterID--s_department') }
        departmentFacetHeader{ departmentFacet.find('.Filter-title') }
        departmentFacetDepartmentButtonBasic{ departmentFacet.find('.Filter-name') }
        departmentFacetDepartmentButtonBasicCount{ departmentFacet.find('.Filter-count') }
    //merchant filter
        merchantFacet{ $('.Filter.Filter--multi.FilterID--s_merchantName') }
        merchantFacetClearButton{ merchantFacet.find('.Filter-clear') }
        merchantFacetSearch(required: false){ merchantFacet.find('.Filter-search') }
        merchantFacetSearchInputField(required: false){ merchantFacet.find('.Filter-search').find('input') }
        merchantFacetHeader{ merchantFacet.find('.Filter-title') }
        merchantFacetMerchantButtonBasic{ merchantFacet.find('.Filter-name') }
        merchantFacetMerchantButtonBasicCount{ merchantFacet.find('.Filter-count') }
    //category filter
        categoryFacet(required: false){ $('.Filter.Filter--multi.FilterID--s_categories_multiVal') }
        categoryFacetClearButton{ categoryFacet.find('.Filter-clear') }
        categoryFacetSearch(required: false){ categoryFacet.find('.Filter-search') }
        categoryFacetSearchInputField(required: false){ categoryFacet.find('.Filter-search').find('input') }
        categoryFacetHeader{ categoryFacet.find('.Filter-title') }
        categoryFacetCategoryButtonBasic(required: false){ categoryFacet.find('.Filter-name') }
        categoryFacetCategoryButtonBasicCount(required: false){ categoryFacet.find('.Filter-count') }

        clearAllButton{ $('.FiltersColumn-clear') }
    //top navigation - only filters
        topPaginationBar { $('.topPagination') }
        orderSelectElement { topPaginationBar.find('.paginationOrder-select') }
        orderSelectElementOption { orderSelectElement.find('option') } //values: name|asc, name|desc, multiplier:asc, multiplier:desc
        outOfElement{ $('.pagination-totalItems') }
    //voucher cards
        voucherCard{ $('.VoucherCard') }
        voucherMerchantNameBasic{ $('.VoucherCard-info').find('h3') }
        voucherNameBasic{ $('.VoucherCard-info').find('h4') }
        voucherDescriptionBasic{ $('.VoucherCard-info').find('p') }
        voucherImageBasic{ $('.VoucherCard-image').find('img') }
        getVoucherCodeButtonBasic{ $('.VoucherCard-getCodeButton') }
        getVoucherCodeButtonDescriptionBasic{ getVoucherCodeButtonBasic.find('span') }
        voucherExpiryDateBasic{ $('.VoucherCard-expiryDate') }
        voucherShareLabelBasic{ $('.VoucherCard-socialShare') }
        voucherShareFacebookBasic{ $('.facebook-share') }
        voucherShareTwitterBasic{ $('.twitter-share') }
        voucherShareGoogleBasic{ $('.googleplus-share') }
        voucherCode(required: false){ $('.VoucherCard-codeCell').find('.VoucherCard-codeValue') }
        voucherCodeHelpText{ $('.VoucherCard-codeCell').find('span') }
        openSiteButton(required: false){ $('.VoucherCard-openSite') }
     //main voucher card on individual page
        indVoucherCard{ $('.voucherPage-voucherContainer') }
        indVoucherMerchantNameBasic{ indVoucherCard.find('.voucherPage-merchant') }
        indVoucherNameBasic{ indVoucherCard.find('.voucherPage-title') }
        indVoucherDescriptionBasic{ indVoucherCard.find('.voucherPage-description') }
        indGetVoucherCodeButtonBasic{ indVoucherCard.find('.voucherPage-getCodeButton') }
        indVoucherCode{ indVoucherCard.find('.voucherPage-code') }
        indVoucherExpiryDateBasic{ indVoucherCard.find('.voucherPage-expires') }
        indVoucherShareFacebookBasic{ indVoucherCard.find('.facebook-share') }
        indVoucherShareTwitterBasic{ indVoucherCard.find('.twitter-share') }
        indVoucherShareGoogleBasic{ indVoucherCard.find('.googleplus-share') }
     //other voucher section
        indVoucherMoreVouchersSectionHeader{ $('.voucherPage-moreHeader') }

        moreVouchersVoucherCard{ $('.VoucherCard') }
        moreVouchersVoucherMerchantNameBasic{ moreVouchersVoucherCard.find('h3') }
        moreVouchersVoucherNameBasic{ moreVouchersVoucherCard.find('h4') }
        moreVouchersVoucherDescriptionBasic{ moreVouchersVoucherCard.find('p') }
        moreVouchersGetVoucherCodeButtonBasic{ moreVouchersVoucherCard.find('.VoucherCard-getCodeButton') }
        moreVouchersGoToRetailerButtonBasic{ moreVouchersVoucherCard.find('.VoucherCard-openSite') }
        moreVouchersVoucherCode{ moreVouchersVoucherCard.find('.VoucherCard-codeValue') }
        moreVouchersVoucherExpiryDateBasic{ moreVouchersVoucherCard.find('.VoucherCard-expiryDate') }
        moreVouchersVoucherShareFacebookBasic{ moreVouchersVoucherCard.find('.facebook-share') }
        moreVouchersVoucherShareTwitterBasic{ moreVouchersVoucherCard.find('.twitter-share') }
        moreVouchersVoucherShareGoogleBasic{ moreVouchersVoucherCard.find('.googleplus-share') }
        
        indVoucherMoreVouchersSeeAllButton{ $('.u-link') }
    }
    //breadcrumb
        def clickChosenBreadcrumbElement(number){ waitFor{ breadCrumbValues[number].isDisplayed() }; breadCrumbValues[number].click() }
    //department filter
        def selectChosenDepartment(number){ waitFor{ departmentFacetDepartmentButtonBasic[number].isDisplayed() }; departmentFacetDepartmentButtonBasic[number].click() }
    //merchant filter
        def selectChosenMerchant(number){ waitFor{ merchantFacetMerchantButtonBasic[number].isDisplayed() }; merchantFacetMerchantButtonBasic[number].click() }
        def clikClearButtonOnMerchantFacet(){ waitFor{ merchantFacetClearButton.isDisplayed() }; merchantFacetClearButton.click() }
        def useMerchantSearchWithPhrase(phrase){ waitFor{ merchantFacetSearchInputField.isDisplayed() }; merchantFacetSearchInputField.value(phrase) }
    //category filter
        def selectChosenCategory(number){ waitFor{ categoryFacetCategoryButtonBasic[number].isDisplayed() }; categoryFacetCategoryButtonBasic[number].click() }
        def clikClearButtonOnCategoryFacet(){ waitFor{ categoryFacetClearButton.isDisplayed() }; categoryFacetClearButton.click() }
        def useCategorySearchWithPhrase(phrase){ waitFor{ categoryFacetSearchInputField.isDisplayed() }; categoryFacetSearchInputField.value(phrase) }

        def clickClarAllButton(){waitFor{ clearAllButton.isDisplayed() }; clearAllButton.click() }
    //top navigation - only filters
        def expandExpiryFilter(){ waitFor{ orderSelectElement.isDisplayed() }; orderSelectElement.click() }
        def clickChosenExpiryfilterOption(number){ waitFor{ orderSelectElementOption[number].isDisplayed() }; orderSelectElementOption[number].click() }
    //voucher cards
        def clickShareFacebookOptionOfChosenVoucher(number){ waitFor{ voucherShareFacebookBasic[number].isDisplayed() }; voucherShareFacebookBasic[number].click() }
        def clickShareTwitterOptionOfChosenVoucher(number){ waitFor{ voucherShareTwitterBasic[number].isDisplayed() }; voucherShareTwitterBasic[number].click() }
        def clickShareGoogleOptionOfChosenVoucher(number){ waitFor{ voucherShareGoogleBasic[number].isDisplayed() }; voucherShareGoogleBasic[number].click() }
        def clickGetVoucherCodeButtonOfChosenVoucher(number){ waitFor{ getVoucherCodeButtonBasic[number].isDisplayed() }; getVoucherCodeButtonBasic[number].click() }
    //main voucher card on individual page
        def clickGetVoucherCodeButtonOfMainVoucherOnIndividualVoucherPage(){ waitFor{ indGetVoucherCodeButtonBasic.isDisplayed() }; indGetVoucherCodeButtonBasic.click() }
        def clickShareFacebookOptionOfMainVoucherOnIndividualVoucherPage(){ waitFor{ indVoucherShareFacebookBasic.isDisplayed() }; indVoucherShareFacebookBasic.click() }
        def clickShareTwitterOptionOfMainVoucherOnIndividualVoucherPage(){ waitFor{ indVoucherShareTwitterBasic.isDisplayed() }; indVoucherShareTwitterBasic.click() }
        def clickShareGoogleOptionOfMainVoucherOnIndividualVoucherPage(){ waitFor{ indVoucherShareGoogleBasic.isDisplayed() }; indVoucherShareGoogleBasic.click() }
        def clickSeeAllButtonOnIndividualVoucherPage(){ waitFor{ indVoucherMoreVouchersSeeAllButton.isDisplayed() }; indVoucherMoreVouchersSeeAllButton.click() }
    //other voucher section
        def clickGetVoucherCodeButtonOfOtherVoucherOnIndividualVoucherPage(number){ waitFor{ moreVouchersGetVoucherCodeButtonBasic[number].isDisplayed() }; moreVouchersGetVoucherCodeButtonBasic[number].click() }
        def clickShareFacebookOptionOfOtherVoucherOnIndividualVoucherPage(number){ waitFor{ moreVouchersVoucherShareFacebookBasic[number].isDisplayed() }; moreVouchersVoucherShareFacebookBasic[number].click() }
        def clickShareTwitterOptionOfOtherVoucherOnIndividualVoucherPage(number){ waitFor{ moreVouchersVoucherShareTwitterBasic[number].isDisplayed() }; moreVouchersVoucherShareTwitterBasic[number].click() }
        def clickShareGoogleOptionOfOtherVoucherOnIndividualVoucherPage(number){ waitFor{ moreVouchersVoucherShareGoogleBasic[number].isDisplayed() }; moreVouchersVoucherShareGoogleBasic[number].click() }
}