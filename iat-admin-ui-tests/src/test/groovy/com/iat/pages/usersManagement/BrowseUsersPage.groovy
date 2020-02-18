package com.iat.pages.usersManagement

import com.iat.Config
import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import com.iat.stepdefs.utils.HelpFunctions
import geb.Page

class BrowseUsersPage extends Page {

    def helpFunctions = new HelpFunctions()

    static url = '/hr/users-management/users'
    static atCheckWaiting = true
    static at = {
        waitFor { browser.currentUrl.contains("users-management/users") } && waitFor { preloader.hasClass('hide') }
    }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }

        preloader(required: false) { $('.preloaderbar') }

        addNewUserButton(wait: true) { $('') }

        filtersBar(wait: true) { $('.table-filters') }
        searchInputField(wait: true) { filtersBar.find('.md-input') }
        filtersInDDL(wait: true) { filtersBar.find('md-select-value') }
        filtersInDDLOption(wait: true) { $('md-option') }
        filtersDateFromInput(wait: true) { filtersBar.find('.md-datepicker-input', 0) }
        filtersDateFromDatepicker(wait: true) { filtersBar.find('.md-datepicker-calendar-icon', 0) }
        filtersDateToInput(wait: true) { filtersBar.find('.md-datepicker-input', 1) }
        filtersDateToDatepicker(wait: true) { filtersBar.find('.md-datepicker-calendar-icon', 0) }
        filtersClearButton(wait: true) { filtersBar.find('.btn-w-md', 0) }
        filtersSearchButton(wait: true) { filtersBar.find('.btn-w-md', 1) }

        usersTable(wait: true) { $('.table') }
        usersTableHeadersRowElement(wait: true) { usersTable.find('thead').find('th') }
        usersTableHeadersRowSortUpElementBasic(wait: true) { usersTableHeadersRowElement.find('.fa-angle-up') }
        usersTableHeadersRowSortDownElementBasic(wait: true) { usersTableHeadersRowElement.find('.fa-angle-down') }
        usersTableRowBasic(wait: true, required: false) { usersTable.find('tbody').find('tr') }

        pagesPerPageElement(wait: true) { $('.page-num-info') }

        pagination(wait: true) { $('.pagination-container', 1) }//only bottom pagination
        paginationFirstButton(wait: true) { pagination.find('.pagination-first').find('a', 0) }
        paginationPreviousArrow(wait: true) { pagination.find('.pagination-prev').find('a', 0) }
        paginationPageNumberButtonBasic(wait: true) { pagination.find('.pagination-page').find('a') }
        paginationNextArrow(wait: true) { pagination.find('.pagination-next').find('a', 0) }
        paginationLastButton(wait: true) { pagination.find('.pagination-last').find('a', 0) }
    }

    def clickAddNewUserButton() {
        waitFor { addNewUserButton.isDisplayed() }; addNewUserButton.click()
    }

    def clickPaginationFirstButton() {
        waitFor { paginationFirstButton.isDisplayed() }; paginationFirstButton.click()
    }

    def clickPaginationLastButton() {
        waitFor { paginationLastButton.isDisplayed() }; paginationLastButton.click()
    }

    def clickPaginationNextArrow() {
        waitFor { paginationNextArrow.isDisplayed() }; paginationNextArrow.click()
    }

    def clickPaginationPreviousArrow() {
        waitFor { paginationPreviousArrow.isDisplayed() }; paginationPreviousArrow.click()
    }

    def clickChosePageNumberButton(pageNumber) {
        waitFor { paginationPageNumberButtonBasic[pageNumber].isDisplayed() };
        paginationPageNumberButtonBasic[pageNumber].click()
    }

    def enterFilterSearchPhrase(searchPhrase) {
        waitFor { searchInputField.isDisplayed() }; searchInputField.value(searchPhrase)
    }

    def enterDateRangeFilterValueFrom(date) {
        waitFor { filtersDateFromInput.isDisplayed() }; filtersDateFromInput.value(date)
    }

    def enterDateRangeFilterValueTo(date) {
        waitFor { filtersDateToInput.isDisplayed() }; filtersDateToInput.value(date)
    }

    def clickFilterSearchButton() {
        waitFor { filtersSearchButton.isDisplayed() }; filtersSearchButton.click()
        sleep(500)
        waitFor { !preloader.isDisplayed() }
    }

    def clickFilterClearButton() {
        waitFor { filtersClearButton.isDisplayed() }; filtersClearButton.click()
    }

    def selectFilterInOption(filterName) {
        waitFor { filtersInDDL.isDisplayed() }; filtersInDDL.click()
        helpFunctions.waitSomeTime(Config.waitMedium)
        switch (filterName) {
            case "User":
                waitFor { filtersInDDLOption[0].isDisplayed() }; filtersInDDLOption[0].click()
                break
            case "Manager":
                waitFor { filtersInDDLOption[1].isDisplayed() }; filtersInDDLOption[1].click()
                break
            case "Status":
                waitFor { filtersInDDLOption[2].isDisplayed() }; filtersInDDLOption[2].click()
                break
            case "Department":
                waitFor { filtersInDDLOption[3].isDisplayed() }; filtersInDDLOption[3].click()
                break
        }
        helpFunctions.waitSomeTime(Config.waitMedium)
    }

    def String returnSearchPhraseAccordingToChosenFilter(filterName) {
        //User will be at the same First name
        switch (filterName) {
            case "Department":
                returnUsersTableLocator("Department", 0).text().toLowerCase()
                break
            case "Manager":
                returnUsersTableLocator("Manager", 0).text().split(' ')[0].toLowerCase()
                break
            case "Email":
                returnUsersTableLocator("User details", 0).find('small').text()[1].toLowerCase()
                break
            case "Last name":
                returnUsersTableLocator("User details", 0).find('span').text().split(' ')[1].toLowerCase()
                break
            case "First name":
                returnUsersTableLocator("User details", 0).find('span').text().split(' ')[0].toLowerCase()
                break
            case "Status":
                returnUsersTableLocator("Status", 0).text().toLowerCase()
                break
        }
    }

    def returnUsersTableLocator(String column, int position) {
        switch (column) {
            case "Date added":
                waitFor { usersTableRowBasic[position].find('td', 0).isDisplayed() }
                return usersTableRowBasic[position].find('td', 0)
                break
            case "User details":
                waitFor { usersTableRowBasic[position].find('td', 1).isDisplayed() }
                return usersTableRowBasic[position].find('td', 1)
                break
            case "Department":
                waitFor { usersTableRowBasic[position].find('td', 2).isDisplayed() }
                return usersTableRowBasic[position].find('td', 2)
                break
            case "Manager":
                waitFor { usersTableRowBasic[position].find('td', 3).isDisplayed() }
                return usersTableRowBasic[position].find('td', 3)
                break
            case "Employee number":
                waitFor { usersTableRowBasic[position].find('td', 4).isDisplayed() }
                return usersTableRowBasic[position].find('td', 4)
                break
//            case "Role":
//                waitFor { usersTableRowBasic[position].find('td', 5).isDisplayed() }
//                return usersTableRowBasic[position].find('td', 5)
//                break
            case "Status":
                waitFor { usersTableRowBasic[position].find('td', 5).isDisplayed() }
                return usersTableRowBasic[position].find('td', 5)
                break
            case "Actions":
                //waitFor { usersTableRowBasic[position].find('td', 6).find('.Users-editButton').isDisplayed() }
                return usersTableRowBasic[position].find('td', 6).find('.Users-editButton')
                break
        }
    }

    def clickEditButtonOfChosenUserRow(rowNumber) {
        waitFor { returnUsersTableLocator("Actions", rowNumber).isDisplayed() };
        helpFunctions.waitSomeTime(Config.waitMedium)
        returnUsersTableLocator("Actions", rowNumber).click()
    }

    def clickChosenColumnSelectedSortOption(String sortOption, String columnName) {
        def columnNumber
        switch (columnName) {
            case "Date added":
                columnNumber = 0
                break
            case "User details":
                columnNumber = 1
                break
            case "Department":
                columnNumber = 2
                break
            case "Manager":
                columnNumber = 3
                break
            case "Employee number":
                columnNumber = 4
                break
            case "Status":
                columnNumber = 5
                break
        }

        if (sortOption == 'ascending') {
            waitFor { usersTableHeadersRowSortUpElementBasic.size() == 6 }
            waitFor { usersTableHeadersRowSortUpElementBasic[columnNumber].isDisplayed() }
            usersTableHeadersRowSortUpElementBasic[columnNumber].click()
        } else if (sortOption == 'descending') {
            waitFor { usersTableHeadersRowSortDownElementBasic.size() == 6 }
            waitFor { usersTableHeadersRowSortDownElementBasic[columnNumber].isDisplayed() }
            usersTableHeadersRowSortDownElementBasic[columnNumber].click()
        }
    }

    def chckIfcolumnWasProperlySorted(String columnName, String sortOption) {
        def helpFunctions = new HelpFunctions()
        for (int i = 1; i < usersTableRowBasic.size() - 2; i++) {
            if (returnUsersTableLocator(columnName, i).text().size() == 0) {
                continue
            }

            switch (columnName) {
                case "Date added":
                    if (sortOption == 'ascending') {
                        assert helpFunctions.parseDateFromStringToMiliseconds(returnUsersTableLocator(columnName, i).text(), 'yyyy-MM-dd') <= helpFunctions.parseDateFromStringToMiliseconds(returnUsersTableLocator(columnName, i + 1).text(), 'yyyy-MM-dd')
                    } else if (sortOption == 'descending') {
                        assert helpFunctions.parseDateFromStringToMiliseconds(returnUsersTableLocator(columnName, i).text(), 'yyyy-MM-dd') >= helpFunctions.parseDateFromStringToMiliseconds(returnUsersTableLocator(columnName, i + 1).text(), 'yyyy-MM-dd')
                    }
                    break
                case "User details":
                    if (sortOption == 'ascending') {
                        assert returnUsersTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnUsersTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) <= 0
                    } else if (sortOption == 'descending') {
                        assert returnUsersTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnUsersTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) >= 0
                    }
                    break
                case "Department":
                    if (sortOption == 'ascending') {
                        assert returnUsersTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnUsersTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) <= 0
                    } else if (sortOption == 'descending') {
                        assert returnUsersTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnUsersTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) >= 0
                    }
                    break
                case "Manager":
                    if (sortOption == 'ascending') {
                        assert returnUsersTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnUsersTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) <= 0
                    } else if (sortOption == 'descending') {
                        assert returnUsersTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnUsersTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) >= 0
                    }
                    break
                case "Employee number":
                    if (sortOption == 'ascending') {
                        assert returnUsersTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnUsersTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) <= 0
                    } else if (sortOption == 'descending') {
                        assert returnUsersTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnUsersTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) >= 0
                    }
                    break
                case "Status":
                    if (sortOption == 'ascending') {
                        assert returnUsersTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnUsersTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) <= 0
                    } else if (sortOption == 'descending') {
                        assert returnUsersTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnUsersTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) >= 0
                    }
                    break
            }
        }
    }

}
