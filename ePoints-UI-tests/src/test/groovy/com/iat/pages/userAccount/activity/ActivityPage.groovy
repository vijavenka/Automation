package com.iat.pages.userAccount.activity

import com.iat.pages.AbstractPage

class ActivityPage extends AbstractPage {

    static url = '/my-account/activity'
    static at = { waitFor { title.contains('Activity | epoints') } }

    static content = {
        activityHeader { $('.page-title') }

        searchSettingsWrapper(wait: true) { $('.search-settings-wrapper') }
        activityTableHeaderCurreentBalanceOption(wait: true) {
            searchSettingsWrapper.find('.results-info-wrapper').find('span', 0)
        }
        activityTableHeaderPendingOption(wait: true) {
            searchSettingsWrapper.find('.results-info-wrapper').find('span', 2)
        }
        activityTableHeaderDeclinedOption(wait: true) {
            searchSettingsWrapper.find('.results-info-wrapper').find('span', 4)
        }
        activityTableHeaderItemsPerPage20(wait: true) {
            searchSettingsWrapper.find('.search-settings').find('.page-size-settings').find('span', 0)
        }
        activityTableHeaderItemsPerPage40(wait: true) {
            searchSettingsWrapper.find('.search-settings').find('.page-size-settings').find('span', 1)
        }
        activityTableHeaderItemsPerPage100(wait: true) {
            searchSettingsWrapper.find('.search-settings').find('.page-size-settings').find('span', 2)
        }
        activityTableHeaderSortByDDL(wait: true) {
            searchSettingsWrapper.find('.search-settings').find('#sort-transactions')
        }
        activityTableHeaderSortByDDLOptionBasic(wait: true) {
            searchSettingsWrapper.find('.search-settings').find('#sort-transactions')
        }
        transactionTable(wait: true) { $('#transactions') }
        activityTableHeaderColumnNameBasic(wait: true) { transactionTable.find('thead').find('th') }

        activityTableContentDateBasic(wait: true) { transactionTable.$(".date-cell") }
        activityTableContentActivityBasic(wait: true) { transactionTable.find('tbody').find('tr').find('td', 1) }
        activityTableContentSiteBasic(wait: true) { transactionTable.find('tbody').find('tr').find('.link') }
        activityTableContentInBasic(wait: true) { transactionTable.find('tbody').find('tr').find('.text-center', 0) }
        activityTableContentOutBasic(wait: true) { transactionTable.find('tbody').find('tr').find('.text-center', 1) }
        activityTableContentBalanceBasic(wait: true) {
            transactionTable.find('tbody').find('tr').find('.text-center', 2)
        }

        paginationPrevButton(wait: true, required: false) { $('.pagination-icon', 0) }
        paginationNextButton(wait: true, required: false) { $('.pagination-icon', 1) }
        paginationNumberBasic(wait: true, required: false) { $('.pagination-numericButton') }

        loader(wait: true, required: false) { $('.Loader-icon') }
    }

    def clickOnCurrentBalanceOption() {
        waitFor { activityTableHeaderCurreentBalanceOption.isDisplayed() }
        activityTableHeaderCurreentBalanceOption.click()
    }

    def clickOnPendingOption() {
        waitFor { activityTableHeaderPendingOption.isDisplayed() }; activityTableHeaderPendingOption.click()
    }

    def clickOnDeclinedOption() {
        waitFor { activityTableHeaderDeclinedOption.isDisplayed() }; activityTableHeaderDeclinedOption.click()
    }

    def clikcItemPerPageOption20() {
        waitFor { activityTableHeaderItemsPerPage20.isDisplayed() }; activityTableHeaderItemsPerPage20.click()
    }

    def clickItemPerPageOption40() {
        waitFor { activityTableHeaderItemsPerPage40.isDisplayed() }; activityTableHeaderItemsPerPage40.click()
    }

    def clickItemPerPageOption100() {
        waitFor { activityTableHeaderItemsPerPage100.isDisplayed() }; activityTableHeaderItemsPerPage100.click()
    }

    def expandSortByDDL() {
        waitFor { activityTableHeaderSortByDDL.isDisplayed() }; activityTableHeaderSortByDDL.click()
    }

    def clickChosenSortByDDLOption(String sortOption) {
        waitFor { activityTableHeaderSortByDDLOptionBasic.isDisplayed() }
        activityTableHeaderSortByDDLOptionBasic = sortOption
    }

    def clickChosenSiteLink(number) {
        waitFor { activityTableContentSiteBasic[number].isDisplayed() }; activityTableContentSiteBasic[number].click()
    }

    def clickPaginationPrevButton() { waitFor { paginationPrevButton.isDisplayed() }; paginationPrevButton.click() }

    def clickPaginationNextButton() {
        waitFor { paginationNextButton.isDisplayed() }
        paginationNextButton.click()
    }

    def clickChosenPaginationNumberButton(number) {
        sleep(1000)
        waitFor { paginationNumberBasic[number].isDisplayed() }
        paginationNumberBasic[number].click()
    }
}
