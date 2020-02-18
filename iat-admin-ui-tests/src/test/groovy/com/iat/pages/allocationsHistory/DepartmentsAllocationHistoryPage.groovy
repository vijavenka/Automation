package com.iat.pages.allocationsHistory

import com.iat.Config
import com.iat.pages.allocationsHistory.modules.ExportPopupModule
import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import com.iat.stepdefs.utils.HelpFunctions
import geb.Page

class DepartmentsAllocationHistoryPage extends Page {

    def helpFunctions = new HelpFunctions()

    static url = '/hr/history/departments-allocation'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("departments-allocation") } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }
        exportPopupModule(required: false) { module ExportPopupModule }

        exportToFileButton(wait: true) { $('.zmdi-download').parent('.btn-primary') }

        allocationsNumberCard(wait: true, required: false) { $('.zmdi-star').closest('div') }
        allocationsNumberValue(wait: true, required: false) { allocationsNumberCard.find('.size-h2') }
        alocatedPointsCard(wait: true) { $('.zmdi-arrow-missed').closest('div') }
        alocatedPointsValue(wait: true) { alocatedPointsCard.find('.size-h2') }
        alocatedMoneyCard(wait: true) { $('.zmdi-balance-wallet').closest('div') }
        alocatedMoneyValue(wait: true) { alocatedMoneyCard.find('.size-h2') }

        filtersBar(wait: true) { $('.table-filters') }
        searchInputField(wait: true) { filtersBar.find('.md-input') }
        filtersInDDL(wait: true) { filtersBar.find('.md-select-value') }
        filtersInDDLOption(wait: true) { $('md-option') }
        filtersDateFromInput(wait: true) { filtersBar.find('.md-datepicker-input', 0) }
        filtersDateFromDatepicker(wait: true) { filtersBar.find('.md-datepicker-calendar-icon', 0) }
        filtersDateToInput(wait: true) { filtersBar.find('.md-datepicker-input', 1) }
        filtersDateToDatepicker(wait: true) { filtersBar.find('.md-datepicker-calendar-icon', 0) }
        filtersClearButton(wait: true) { filtersBar.find('.btn-w-md', 0) }
        filtersSearchButton(wait: true) { filtersBar.find('.btn-w-md', 1) }

        allocationsTable(wait: true, required: false) { $('.table') }
        allocationsTableHeadersRowElement(wait: true) { allocationsTable.find('thead').find('th') }
        allocationsTableHeadersRowSortUpElementBasic(wait: true) {
            allocationsTableHeadersRowElement.find('.fa-angle-up')
        }
        allocationsTableHeadersRowSortDownElementBasic(wait: true) {
            allocationsTableHeadersRowElement.find('.fa-angle-down')
        }
        allocationTableRowBasic(wait: true, required: false) { allocationsTable.find('tbody').find('tr') }
        allocationsPointsSummary(wait: true) { allocationsTable.find('.row-summary', 0) }
        allocationsPoundsSummary(wait: true) { allocationsTable.find('.row-summary', 1) }

        pagesPerPageElement(wait: true) { $('.page-num-info') }

        pagination(wait: true) { $('.pagination-container', 1) }//only bottom pagination
        paginationFirstButton(wait: true) { pagination.find('.pagination-first').find('a', 0) }
        paginationPreviousArrow(wait: true) { pagination.find('.pagination-prev').find('a', 0) }
        paginationPageNumberButtonBasic(wait: true) { pagination.find('.pagination-page').find('a') }
        paginationNextArrow(wait: true) { pagination.find('.pagination-next').find('a', 0) }
        paginationLastButton(wait: true) { pagination.find('.pagination-last').find('a', 0) }
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
        waitFor { searchInputField.isDisplayed() }; searchInputField.value(searchPhrase);
        helpFunctions.waitSomeTime(Config.waitMedium)
    }

    def clickFilterSearchButton() {
        waitFor { filtersSearchButton.isDisplayed() }; filtersSearchButton.click()
    }

    def clickFilterClearButton() {
        waitFor { filtersClearButton.isDisplayed() }; filtersClearButton.click()
    }

    def selectFilterOption(filterName) {
        waitFor { filtersInDDL.isDisplayed() }; filtersInDDL.click()
        switch (filterName) {
            case "Comment text":
                waitFor { filtersInDDLOption[5].isDisplayed() }; filtersInDDLOption[5].click()
                break
            case "Department":
                waitFor { filtersInDDLOption[4].isDisplayed() }; filtersInDDLOption[4].click()
                break
            case "From":
                waitFor { filtersInDDLOption[3].isDisplayed() }; filtersInDDLOption[3].click()
                break
            case "Who allocated":
                waitFor { filtersInDDLOption[2].isDisplayed() }; filtersInDDLOption[2].click()
                break
            case "Value":
                waitFor { filtersInDDLOption[1].isDisplayed() }; filtersInDDLOption[1].click()
                break
            case "Points amount":
                waitFor { filtersInDDLOption[0].isDisplayed() }; filtersInDDLOption[0].click()
                break
        }
    }

    def enterDateRangeFilterValueFrom(date) {
        waitFor { filtersDateFromInput.isDisplayed() }; filtersDateFromInput.value(date)
    }

    def enterDateRangeFilterValueTo(date) {
        waitFor { filtersDateToInput.isDisplayed() }; filtersDateToInput.value(date)
    }

    def returnAllocationTableLocator(String column, int position) {
        switch (column) {
            case "ID":
                waitFor { allocationTableRowBasic[position].find('td', 0).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 0)
                break
            case "Date send":
                waitFor { allocationTableRowBasic[position].find('td', 1).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 1)
                break
            case "From":
                waitFor { allocationTableRowBasic[position].find('td', 2).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 2)
                break
            case "Department name":
                waitFor { allocationTableRowBasic[position].find('td', 3).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 3)
                break
            case "Who":
                waitFor { allocationTableRowBasic[position].find('td', 4).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 4)
                break
            case "Comment":
                waitFor { allocationTableRowBasic[position].find('td', 5).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 5)
                break
            case "Points awarded":
                waitFor { allocationTableRowBasic[position].find('td', 6).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 6)
                break
            case "Value of points":
                waitFor { allocationTableRowBasic[position].find('td', 7).isDisplayed() }
                return allocationTableRowBasic[position].find('td', 7)
                break
        }
    }

    def clickSortOptionOfChosenColumn(String sortOption, String columnName) {
        def columnNumber
        switch (columnName) {
            case "ID":
                columnNumber = 0
                break
            case "Date send":
                columnNumber = 1
                break
            case "From":
                columnNumber = 2
                break
            case "Department name":
                columnNumber = 3
                break
            case "Who":
                columnNumber = 4
                break
            case "Comment":
                columnNumber = 5
                break
            case "Points awarded":
                columnNumber = 6
                break
            case "Value of points":
                columnNumber = 7
                break
        }

        if (sortOption == 'ascending') {
            waitFor { allocationsTableHeadersRowSortUpElementBasic.size() == 8 }
            waitFor { allocationsTableHeadersRowSortUpElementBasic[columnNumber].isDisplayed() }
            allocationsTableHeadersRowSortUpElementBasic[columnNumber].click()
        } else if (sortOption == 'descending') {
            waitFor { allocationsTableHeadersRowSortDownElementBasic.size() == 8 }
            waitFor { allocationsTableHeadersRowSortDownElementBasic[columnNumber].isDisplayed() }
            allocationsTableHeadersRowSortDownElementBasic[columnNumber].click()
        }
    }

    def chckIfcolumnWasProperlySorted(String columnName, String sortOption) {
        def helpFunctions = new HelpFunctions()
        for (int i = 1; i < allocationTableRowBasic.size() - 2; i++) {
            if (returnAllocationTableLocator(columnName, i).text() == '')
                continue

            switch (columnName) {
                case "ID":
                    if (sortOption == 'ascending')
                        assert Integer.parseInt(returnAllocationTableLocator(columnName, i).text()) <= Integer.parseInt(returnAllocationTableLocator(columnName, i + 1).text())
                    else if (sortOption == 'descending')
                        assert Integer.parseInt(returnAllocationTableLocator(columnName, i).text()) >= Integer.parseInt(returnAllocationTableLocator(columnName, i + 1).text())
                    break
                case "Date send":
                    if (sortOption == 'ascending')
                        assert helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i).text(), 'yyyy-MM-dd') <= helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i + 1).text(), 'yyyy-MM-dd')
                    else if (sortOption == 'descending')
                        assert helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i).text(), 'yyyy-MM-dd') >= helpFunctions.parseDateFromStringToMiliseconds(returnAllocationTableLocator(columnName, i + 1).text(), 'yyyy-MM-dd')
                    break
                case "From":
                    if (sortOption == 'ascending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) <= 0
                    else if (sortOption == 'descending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) >= 0
                    break
                case "Department name":
                    if (sortOption == 'ascending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) <= 0
                    else if (sortOption == 'descending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) >= 0
                    break
                case "Who":
                    if (sortOption == 'ascending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) <= 0
                    else if (sortOption == 'descending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) >= 0
                    break
                case "Comment":
                    if (sortOption == 'ascending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) <= 0
                    else if (sortOption == 'descending')
                        assert returnAllocationTableLocator(columnName, i).text().charAt(0).toLowerCase().compareTo(returnAllocationTableLocator(columnName, i + 1).text().charAt(0).toLowerCase()) >= 0
                    break
                case "Points awarded":
                    if (sortOption == 'ascending')
                        assert Integer.parseInt(returnAllocationTableLocator(columnName, i).text().replace(',', '')) <= Integer.parseInt(returnAllocationTableLocator(columnName, i + 1).text().replace(',', ''))
                    else if (sortOption == 'descending')
                        assert Integer.parseInt(returnAllocationTableLocator(columnName, i).text().replace(',', '')) >= Integer.parseInt(returnAllocationTableLocator(columnName, i + 1).text().replace(',', ''))
                    break
                case "Value of points":
                    if (sortOption == 'ascending')
                        assert Float.parseFloat(returnAllocationTableLocator(columnName, i).text().substring(1)) <= Float.parseFloat(returnAllocationTableLocator(columnName, i + 1).text().substring(1))
                    else if (sortOption == 'descending')
                        assert Float.parseFloat(returnAllocationTableLocator(columnName, i).text().substring(1)) >= Float.parseFloat(returnAllocationTableLocator(columnName, i + 1).text().substring(1))
                    break
            }
        }
    }

    def clickExportButton() {
        waitFor { exportToFileButton.isDisplayed() }; exportToFileButton.click()
    }

}