package modules.Epoints_modules.getSection

import geb.Module

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 02.10.14
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */

class azPageModule extends Module{
    static content = {
        voucherButton{ $('.vouchers-link') }
        favouriteButtonDisabled(required: false){ $('.show-favourites.disabled') }
        favouriteButtonEnabled{ $('.show-favourites') }
        recentlyVisitedRetailersHeader{ $('.visited-retialers-view') }
        recentlyVisitedRetailersRetailerElement{ recentlyVisitedRetailersHeader.find('.retailer-link-view') }
    //department facet
        departmentFacet { $('#azMerchantsDepartments') }
        departmentFacetHeader { departmentFacet.find('.header-text') }
        departmentFacetClearButton { departmentFacet.find('.evt-show-all') }
        departmentFacetDepartmentButtonBasic { departmentFacet.find('li') }
        departmentFacetDepartmentButtonActiveBasic { departmentFacet.find('.active') }
    //paginations
        //top
        topSearchBoxLeft { $('.results-info-wrapper') }
        topSearchBoxRight { $('.search-settings.show-with-results') }
        showingElement { topSearchBoxLeft.find('.items-visible-range') }
        outOfElement { topSearchBoxLeft.find('.items-counter') }
        leftArrowElement { topSearchBoxLeft.find('.icon-chevron-left') }
        rightArrowElement { topSearchBoxLeft.find('.icon-chevron-right') }
        itemsPerPageLabelElement { topSearchBoxRight.find('.page-size-label') }
        itemsPerPage20Element { topSearchBoxRight.find('.pagesize',0) }
        itemsPerPage40Element { topSearchBoxRight.find('.pagesize',1) }
        itemsPerPage100Element { topSearchBoxRight.find('.pagesize',2) }
        orderSelectElement { topSearchBoxRight.find('select') }
        orderSelectElementOption { orderSelectElement.find('option') } //values: name|asc, name|desc, multiplier:asc, multiplier:desc
        //bottom
        paginationModule { $('.pager-container') }
        nextButton { paginationModule.find('.next-page') }
        previousButton { paginationModule.find('.previous-page') }
        pageNumberBasic { paginationModule.find('.page') }
        pageNumberActiveBasic { paginationModule.find('.active') }
        backToTopButton { paginationModule.find('span') }
    //letters
        azFacet { $('#azLetters') }
        azFacetHeader { azFacet.find('.header-text') }
        azFacetClearButton { azFacet.find('.evt-show-all') }
        azFacetLetterButtonBasic { azFacet.find('li') }
        azFacetLetterButtonActiveBasic(required: false) { azFacet.find('.active') }
    //search
        searchField { $('#inputStore') }
        searchButton { $('.store-search') }
        noResultsFoundMessage { $('.no-retailers') }
    //cards
        searchResults { $('.reg-items') }
        retailerCardBasicSearchResults(required: false) { searchResults.find('.retailerCard') }
        basiCardTooltip { retailerCardBasicSearchResults.find('.tooltip') }
        epointsIconBasic { $('.multiplier') }
        favouriteIconDisbledBasicUserLogout(required: false) { retailerCardBasicSearchResults.find('.favourite-retailer-widget') }
        favouriteIconDisbledBasic(required: false) { retailerCardBasicSearchResults.find('.add-to-favourites-icon') }
        favouriteIconEnabledBasic(required: false) { retailerCardBasicSearchResults.find('.favourite-icon') }
        voucherIconBasic(required: false) { retailerCardBasicSearchResults.find('.vouchers-count') }
    }
        def clickVoucherButton(){ waitFor{ voucherButton.isDisplayed() }; voucherButton.click() }

        def clickShowFavouritesButton(){ waitFor{ Thread.sleep(3000); favouriteButtonEnabled.isDisplayed() }; favouriteButtonEnabled.click() }
        def clickChosenRetailerFromRecentlyVisited(number){ waitFor{ recentlyVisitedRetailersRetailerElement[number].isDisplayed() };recentlyVisitedRetailersRetailerElement[number].click() }
    //department facet
        def clickChosenDepartment(number){ waitFor{ departmentFacetDepartmentButtonBasic[number].isDisplayed() }; departmentFacetDepartmentButtonBasic[number].click() }
        def clickClearButtonOnDepartmentFacet(){ waitFor{ departmentFacetClearButton.isDisplayed() }; departmentFacetClearButton.click() }
    //paginations
    //top
        def clickRightArrowOnTopSearchBox(){ waitFor{ rightArrowElement.isDisplayed() }; rightArrowElement.click() }
        def clickLeftArrowOnTopSearchBox(){ waitFor{ leftArrowElement.isDisplayed() }; leftArrowElement.click() }
        def clickItemPerPage20(){ waitFor{itemsPerPage20Element.isDisplayed() }; itemsPerPage20Element.click() }
        def clickItemPerPage40(){ waitFor{itemsPerPage40Element.isDisplayed() }; itemsPerPage40Element.click() }
        def clickItemPerPage100(){ waitFor{itemsPerPage100Element.isDisplayed() }; itemsPerPage100Element.click() }
        def expandFilterDDL(){ waitFor{ orderSelectElement.isDisplayed() }; orderSelectElement.click() }
        def selectChosenFilterOption(number){ waitFor{ orderSelectElementOption[number].isDisplayed() }; orderSelectElementOption[number].click() }
    //bottom
        def clickNextPageButton(){ waitFor{ nextButton.isDisplayed() }; nextButton.click() }
        def clickPreviousPageButton(){ waitFor{ previousButton.isDisplayed() }; previousButton.click() }
        def clickChosenPageNumber(number){ waitFor{ pageNumberBasic.isDisplayed() }; pageNumberBasic[number].click() }
        def clickBackToTopButton(){ waitFor{ backToTopButton.isDisplayed() }; backToTopButton.click() }
    //letters
        def clickChosenLetter(number){ waitFor{ azFacetLetterButtonBasic[number].isDisplayed() }; azFacetLetterButtonBasic[number].click() }
        def clickClearButtonOnAzFacet(){ waitFor{ azFacetClearButton.isDisplayed() }; azFacetClearButton.click() }
    //search
        def enterPhraseIntoSearch(phrase){ waitFor{ searchField.isDisplayed() }; searchField.value(phrase) }
        def clickSearchButton(){ waitFor{ searchButton.isDisplayed() }; searchButton.click() }
    //cards
        def clickOnChosenRetailerCard(number){ waitFor{ retailerCardBasicSearchResults[number].isDisplayed() }; retailerCardBasicSearchResults[number].click() }
        def addToFavouriteChosenRetailer(number){ waitFor{ favouriteIconDisbledBasic[number].isDisplayed() }; favouriteIconDisbledBasic[number].click() }
        def clickAddToFavouriteChosenRetailerWhenUserLogout(number){ waitFor{ favouriteIconDisbledBasicUserLogout[number].isDisplayed() }; favouriteIconDisbledBasicUserLogout[number].click() }
        def removeFromFavouriteChosenRetailer(number){ waitFor{ favouriteIconEnabledBasic.isDisplayed() }; favouriteIconEnabledBasic[number].click() }
}