package modules.Epoints_modules.spendSection

import geb.Module

/**
 * Created by kbaranowski on 2014-11-06.
 */
class browseRewardsPageModule extends Module{
    static content = {
        epointsFilterModule { $('.SubHeader--spend').find('.EpointsRanges') }
        epointsFilterModuleOptionBasic { epointsFilterModule.find('li') }
        epointsFilterModuleOptionActive { epointsFilterModule.find('.is-active') }

        searchComponent{ $('.itemsSearch.itemsSearch--large') }
        searchInputField{ searchComponent.find('input') }
        rewardByEpointsDDL{ searchComponent.find('.customSelect-selection') }
        rewardByEpointsDDLOption{ searchComponent.find('.customSelect-dropdown').find('.customSelect-option') }
        searchButton{ searchComponent.find('button') }

        breadcrumbElementBasic{ $('.Breadcrumbs-item') }
        breadcrumbTotalElement{ $('.Breadcrumbs-totalItems') }

        itemAvailableInfoAfterLogin(required: false){ $('.AvailableItems') }
        itemCounterAfterLogin(required: false){ itemAvailableInfoAfterLogin.find('.AvailableItems-counter') }

        redemptionCardBasic(required: false){ $('.ProductCard') }
        redemptionCardNameBasic(required: false){ redemptionCardBasic.find('.ProductCard-title') }
        redemptionCardPointsValueBasic(required: false){ redemptionCardBasic.find('.ProductCard-priceValue') }
        redemptionCardAddToBasketBasic(required: false){ redemptionCardBasic.find('.ProductCard-addToBasket').find('a') }
        redemptionCardPictureBasic(required: false){ redemptionCardBasic.find('.ProductCard-image') }
        redemptionCardAddedToBasketRibbon(required: false){ redemptionCardBasic.find('.ProductCard-inBasketMessage') }

        backToAllRewardsButton{ $('.back-to-rewards.link') }

        //paginations
        //top
        topSearchBoxLeft { $('.topPagination--resultsInfo') }
        topSearchBoxRight { $('.topPagination--perPage') }
        showingElement { topSearchBoxLeft.find('.pagination-summary') }
        outOfElement{ topSearchBoxLeft.find('.pagination-totalItems') }
        leftArrowElement{ topSearchBoxLeft.find('.pagination-button.pagination--prevButton') }
        rightArrowElement { topSearchBoxLeft.find('.pagination-button.pagination--nextButton') }
        itemsPerPageLabelElement { topSearchBoxRight.find('span',0) }
        itemsPerPage20Element { topSearchBoxRight.find('button',0).find('span') }
        itemsPerPage40Element { topSearchBoxRight.find('button',1).find('span') }
        itemsPerPage100Element { topSearchBoxRight.find('button',2).find('span') }
        orderSelectElement { $('.topPagination--order') }
        orderSelectElementOption { orderSelectElement.find('option') } //values: name|asc, name|desc, multiplier:asc, multiplier:desc
        //bottom
        paginationModule { $('.botttomPagination') }
        nextButton { paginationModule.find('.pagination-button.pagination--nextButton') }
        previousButton { paginationModule.find('.pagination-button.pagination--prevButton').find('span') }
        pageNumberBasic { paginationModule.find('.pagination-button.pagination-numericButton') }
        pageNumberActiveBasic { paginationModule.find('.is-active') }
        backToTopButton{ $('.u-link.u-pointer') }


        //department, category, brand and number of epoints facets
        departmentFacet(required: false){ $('.Filter.Filter--single.FilterID--s_department') }
        departmentFacetHeader{ departmentFacet.find('.Filter-title') }
        departmentFacetDepartmentButtonBasic{ departmentFacet.find('.Filter-items').find('.Filter-name') }
        departmentFacetDepartmentButtonBasicCount{ departmentFacet.find('.Filter-items').find('.Filter-count') }

        categoryFacet(required: false){ $('.Filter.Filter--single.FilterID--s_categoryFromFeedExtracted_multiVal') }
        categoryFacetHeader(required: false){ categoryFacet.find('.Filter-title') }
        categoryFacetCategoryButtonBasic(required: false){ categoryFacet.find('.Filter-items').find('.Filter-name') }
        categoryFacetCategoryButtonCounterBasic(required: false){ categoryFacet.find('.Filter-items').find('.Filter-count') }

        brandFacet(required: false){ $('.Filter.Filter--multi',0) }
        brandFacetHeader(required: false){ brandFacet.find('.Filter-title') }
        brandFacetClearButton(required: false){ brandFacet.find('.Filter-clear') }
        brandFacetSearchInputField(required: false){ brandFacet.find('.Filter-search').find('input') }
        brandFacetBrandButtonBasic(required: false){ brandFacet.find('.Filter-items').find('.Filter-name') }
        brandFacetBrandButtonCounterBasic(required: false){ brandFacet.find('.Filter-items').find('.Filter-count') }

        typeFacet(required: false){ $('.Filter.Filter--multi',1) }
        typeFacetHeader(required: false){ typeFacet.find('.Filter-title') }
        typeFacetClearButton(required: false){ typeFacet.find('.Filter-clear') }
        typeFacetSearchInputField(required: false){ typeFacet.find('.Filter-search').find('input') }
        typeFacetTypeButtonBasic(required: false){ typeFacet.find('.Filter-items').find('.Filter-name') }
        typeFacetTypeButtonCounterBasic(required: false){ typeFacet.find('.Filter-items').find('.Filter-count') }

        epointsFacet(required: false){ $('.Filter.Filter--range') }
        epointsFacetHeader(required: false){ epointsFacet.find('.Filter-title') }
        epointsFacetClearButton(required: false){ epointsFacet.find('.Filter-clear') }
        epointsFacetFromValue(required: false){ epointsFacet.find('.Filter-rangeInput',0) }
        epointsFacetToValue(required: false){ epointsFacet.find('.Filter-rangeInput',1) }
        epointsFacetGoButton(required: false){ epointsFacet.find('.btn-yellow') }
        epointsFacetRangeInfo(required: false){ epointsFacet.find('.Filter-tip') }

        clearAllfiltersButton{ $('.FiltersColumn-clear') }
        backToTopButton{ $('.scrollto-top') }
    }

    def clickBackToAllRewardsButton(){ waitFor{ backToAllRewardsButton.isDisplayed() }; backToAllRewardsButton.click() }

    def clickCardOfChosenRedemption(number){ waitFor{ redemptionCardBasic[number].isDisplayed() }; redemptionCardBasic[number].click() }
    def addToBasketChosenRedemption(number){ waitFor{ redemptionCardAddToBasketBasic[number].isDisplayed() }; redemptionCardAddToBasketBasic[number].click() }

    def clickBreadcrumbChosenElement(number){ waitFor{ breadcrumbElementBasic[number].isDisplayed() }; breadcrumbElementBasic[number].click() }

    def clickChosenTopEpointsRangeFilterOption(number){ waitFor{ epointsFilterModuleOptionBasic[number].isDisplayed() }; epointsFilterModuleOptionBasic[number].click() }

    //search area
    def enterPhraseIntoSearchInputField(phrase){ waitFor{ searchInputField.isDisplayed() }; searchInputField.value(phrase) }
    def expandEpointsRangeDDL(){ waitFor{ rewardByEpointsDDL.isDisplayed() }; rewardByEpointsDDL.click() }
    def clickChosenDDLEpointsRangeFilterOption(number){ waitFor{ rewardByEpointsDDLOption[number].isDisplayed() }; rewardByEpointsDDLOption[number].click() }
    def clickSearchButton(){ waitFor{ searchButton.isDisplayed() }; searchButton.click() }
    //pagination
    //top
    def expandExpiryFilter(){ waitFor{ orderSelectElement.isDisplayed() }; orderSelectElement.click() }
    def clickChosenExpiryfilterOption(number){ waitFor{ orderSelectElementOption[number].isDisplayed() }; orderSelectElementOption[number].click() }
    def clickItemPerPage20(){ waitFor{ itemsPerPage20Element.isDisplayed() }; itemsPerPage20Element.click() }
    def clickItemPerPage40(){ waitFor{ itemsPerPage40Element.isDisplayed() }; itemsPerPage40Element.click() }
    def clickItemPerPage100(){ waitFor{ itemsPerPage100Element.isDisplayed() }; itemsPerPage100Element.click() }
    def clickLeftArrowOnTopSearchBox(){ waitFor{ leftArrowElement.isDisplayed() }; leftArrowElement.click() }
    def clickRightArrowOnTopSearchBox(){ waitFor{ rightArrowElement.isDisplayed() }; rightArrowElement.click() }
    //bottom
    def clickNextPageButton(){ nextButton.isDisplayed(); nextButton.click() }
    def clickPreviousPageButton(){ previousButton.isDisplayed(); previousButton.click() }
    def clickChosenPageNumber(number){ waitFor{ pageNumberBasic[number].isDisplayed() }; pageNumberBasic[number].click() }
    //filters
    def selectChosenDepartment(number){ waitFor{ departmentFacetDepartmentButtonBasic[number].isDisplayed() }; departmentFacetDepartmentButtonBasic[number].click() }
    def selectChosenCategory(number){ waitFor{ categoryFacetCategoryButtonBasic[number].isDisplayed() }; categoryFacetCategoryButtonBasic[number].click() }
    def selectChosenBrand(number){ waitFor{ brandFacetBrandButtonBasic[number].isDisplayed() }; brandFacetBrandButtonBasic[number].click() }
    def clickClearButtonOnBrandFacet(){ waitFor{ brandFacetClearButton.isDisplayed() }; brandFacetClearButton.click() }
    def enterPhraseIntoBrandSearch(phrase){ waitFor{ brandFacetSearchInputField.isDisplayed() }; brandFacetSearchInputField.value(phrase) }
    def selectChosenType(number){ waitFor{ typeFacetTypeButtonBasic[number].isDisplayed() }; typeFacetTypeButtonBasic[number].click() }
    def clickClearButtonOnTypeFacet(){ waitFor{ typeFacetClearButton.isDisplayed() }; typeFacetClearButton.click() }
    def defenterPhraseIntoTypeSearch(phrase){ waitFor{ typeFacetSearchInputField.isDisplayed() }; typeFacetSearchInputField.value(phrase) }
    def enterFromValue(from){ waitFor{ epointsFacetFromValue.isDisplayed() }; epointsFacetFromValue.value(from) }
    def enterToValue(to){ waitFor{ epointsFacetToValue.isDisplayed() }; epointsFacetToValue.value(to) }
    def clickGoButton(){ waitFor{ epointsFacetGoButton.isDisplayed() }; epointsFacetGoButton.click() }
    def clickClearButtonOnEpointsFacet(){ waitFor{ epointsFacetClearButton.isDisplayed() }; epointsFacetClearButton.click() }

    def clickClearAllButton(){ waitFor{ clearAllfiltersButton.isDisplayed() }; clearAllfiltersButton.click() }
}