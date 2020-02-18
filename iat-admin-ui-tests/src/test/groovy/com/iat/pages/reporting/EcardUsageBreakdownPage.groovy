package com.iat.pages.reporting

import com.iat.pages.allocationsHistory.modules.ExportPopupModule
import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import geb.Page

class EcardUsageBreakdownPage extends Page {

    static url = '/reporting/hr-peer-to-peer-breakdown'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("hr-peer-to-peer-breakdown") } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        leftSidePanelNavigation(wait: true) { module LeftSidePanelNavigationModule }
        exportPopupModule(required: false) { module ExportPopupModule }

        pageHeader(wait: true) { $('h2') }
        filtersBar(wait: true) { $('.ReportingPage-filters') }
        filtersDateFromInput(wait: true) { filtersBar.find('input', 0) }
        filtersDateFromDatepicker(wait: true) { filtersBar.find('.input-group-addon', 0) }
        filtersDateToInput(wait: true) { filtersBar.find('input', 1) }
        filtersDateToDatepicker(wait: true) { filtersBar.find('.input-group-addon', 0) }
        exportToFileButton(wait: true) { $('.zmdi-download').parent('a') }
        predefinedRangeOptionsDDL(wait: true) { $('.Reporting-timePresets', 0) }
        predefinedRangeDDLOption(wait: true) { predefinedRangeOptionsDDL.find('.dropdown-menu').find('a') }

        departmentsBreadcrumb(wait: true) { $('.DepartmentsBreadcrumbs') }
        departmentsBreadcrumbElement(wait: true) { departmentsBreadcrumb.find('.DepartmentsBreadcrumbs-item') }

        departmentsTable(wait: true) { $('.StatTable', 0) }
        departmentsTableHeadersRowElement(wait: true) { departmentsTable.find('thead').find('th') }
        departmentsTableRowBasic(wait: true, required: false) { departmentsTable.find('tbody').find('tr') }
        departmentsTableLinearLoader(required: false) { $('.StatTable-queryLoader', 0) }

        usersTable(wait: true) { $('.StatTable', 1) }
        usersTableHeadersRowElement(wait: true) { usersTable.find('thead').find('th') }
        usersTableRowBasic(wait: true, required: false) { usersTable.find('tbody').find('tr') }
        usersTableLinearLoader(required: false) { $('.StatTable-queryLoader', 1) }

    }

    def returnDepartmentsTableLocator(String column, int reasonColumnNumber, int position) {
        switch (column) {
            case "Department":
                waitFor { departmentsTableRowBasic[position].find('td', 0).isDisplayed() }
                return departmentsTableRowBasic[position].find('td', 0)
                break
            case "Manager":
                waitFor { departmentsTableRowBasic[position].find('td', 1).isDisplayed() }
                return departmentsTableRowBasic[position].find('td', 1)
                break
            case "reason":
                waitFor { departmentsTableRowBasic[position].find('td', reasonColumnNumber).isDisplayed() }
                return departmentsTableRowBasic[position].find('td', reasonColumnNumber)
                break
            case "Total":
                waitFor { departmentsTableRowBasic[position].find('td').last().isDisplayed() }
                return departmentsTableRowBasic[position].find('td').last().previous()
                break
        }
    }

    def clickChosenDepartmentFormDepartmentsTable(int departmentNumber) {
        returnDepartmentsTableLocator('Department', 0, departmentNumber).click()
    }

    def returnUsersTableLocator(String column, int reasonColumnNumber, int position) {
        switch (column) {
            case "Username":
                waitFor { usersTableRowBasic[position].find('td', 0).isDisplayed() }
                return usersTableRowBasic[position].find('td', 0)
                break
            case "reason":
                waitFor { usersTableRowBasic[position].find('td', reasonColumnNumber).isDisplayed() }
                return usersTableRowBasic[position].find('td', reasonColumnNumber)
                break
            case "Total":
                waitFor { usersTableRowBasic[position].find('td').last().isDisplayed() }
                return usersTableRowBasic[position].find('td', 2).last()
                break
        }
    }

    def expandPredefinedDateRangeOptionsDDL() {
        waitFor { predefinedRangeOptionsDDL.isDisplayed() }; predefinedRangeOptionsDDL.click()
    }

    def clickChosenPredefinedDateRangeoption(option) {
        waitFor { predefinedRangeDDLOption[option].isDisplayed() }; predefinedRangeDDLOption[option].click()
    }

    def selectTimePreset(String timePreset) {
        if (predefinedRangeOptionsDDL.text().toLowerCase() != timePreset.toLowerCase()) {
            expandPredefinedDateRangeOptionsDDL()
            for (int i = 0; i < predefinedRangeDDLOption.size(); i++) {
                if (predefinedRangeDDLOption[i].text() == timePreset)
                    predefinedRangeDDLOption[i].click()
            }
            waitFor { (predefinedRangeOptionsDDL.text().toLowerCase() == timePreset.toLowerCase()) }
        }
    }

    def enterFromDate(fromDate) {
        waitFor { filtersDateFromInput.isDisplayed() }; filtersDateFromInput.value(fromDate)
    }

    def enterToDate(toDate) {
        waitFor { filtersDateToInput.isDisplayed() }; filtersDateToInput.value(toDate)
    }

    def clickChosenDepartmentBreadcrumbElement(int elementNumber) {
        waitFor { departmentsBreadcrumbElement[elementNumber].isDisplayed() }
        departmentsBreadcrumbElement[elementNumber].click()
    }

    def clickExportButton() {
        waitFor { exportToFileButton.isDisplayed() }; exportToFileButton.click()
    }
}