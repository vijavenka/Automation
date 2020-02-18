package com.iat.pages.points.pointsPageModules

import geb.Module

class PaginationPointsModule extends Module {

    static content = {
        //top
        topPaginationResultInfo(wait: true) { $('.topPagination--resultsInfo') }
        topPaginationPerPage(wait: true) { $('.topPagination--order') }
        topPaginationOrder(wait: true) { $('.topPagination--perPage') }
        showingElement(wait: true) { topPaginationResultInfo.find('.pagination-summary') }
        outOfElement(wait: true) { topPaginationResultInfo.find('.pagination-totalItems') }
        leftArrowElement(wait: true) { topPaginationResultInfo.find('.pagination--prevButton') }
        rightArrowElement(wait: true) { topPaginationResultInfo.find('.pagination--nextButton') }
        itemsPerPageLabelElement(wait: true) { topPaginationOrder.find('.topPagination--perPage').find('spoan') }
        itemsPerPage20Element(wait: true) { topPaginationOrder.find('.paginationPerPage-button', 0) }
        itemsPerPage40Element(wait: true) { topPaginationOrder.find('.paginationPerPage-button', 1) }
        itemsPerPage100Element(wait: true) { topPaginationOrder.find('.paginationPerPage-button', 2) }
        orderSelectElement(wait: true) { topPaginationPerPage.find('select') }
        orderSelectElementOption(wait: true) { orderSelectElement.find('option') }
        //values: name|asc, name|desc, multiplier:asc, multiplier:desc
        //bottom
        paginationModule(wait: true) { $('.botttomPagination') }
        nextButton(wait: true) { paginationModule.find('.pagination-button.pagination--nextButton') }
        previousButton(wait: true) { paginationModule.find('.pagination-button.pagination--prevButton') }
        pageNumberBasic(wait: true) { paginationModule.find('.pagination-button.pagination-numericButton') }
        pageNumberActiveBasic(wait: true) {
            paginationModule.find('.pagination-button.pagination-numericButton.is-active')
        }
        backToTopButton(wait: true) { $('.u-link.u-pointer') }
    }
    //top
    def clickRightArrowOnTopSearchBox() { waitFor { rightArrowElement.isDisplayed() }; rightArrowElement.click() }

    def clickLeftArrowOnTopSearchBox() { waitFor { leftArrowElement.isDisplayed() }; leftArrowElement.click() }

    def clickItemPerPage20() { waitFor { itemsPerPage20Element.isDisplayed() }; itemsPerPage20Element.click() }

    def clickItemPerPage40() { waitFor { itemsPerPage40Element.isDisplayed() }; itemsPerPage40Element.click() }

    def clickItemPerPage100() { waitFor { itemsPerPage100Element.isDisplayed() }; itemsPerPage100Element.click() }

    def expandFilterDDL() { waitFor { orderSelectElement.isDisplayed() }; orderSelectElement.click() }

    def selectChosenFilterOption(number) {
        waitFor { orderSelectElementOption[number].isDisplayed() }; orderSelectElementOption[number].click()
    }
    //bottom
    def clickNextPageButton() {
        waitFor { nextButton.isDisplayed() }
        nextButton.click()
    }

    def clickPreviousPageButton() {
        waitFor { previousButton.isDisplayed() }
        previousButton.click()
    }

    def clickChosenPageNumber(number) {
        waitFor { pageNumberBasic[number].isDisplayed() }
        pageNumberBasic[number].click()
    }

    def clickBackToTopButton() {
        waitFor { backToTopButton.isDisplayed() }
        backToTopButton.click()
    }
}