package com.iat.pages.specsaversAdmin

import com.iat.pages.specsaversAdmin.modules.TabNaviagationModule
import com.iat.stepdefs.utils.Functions
import geb.Page

class SpecsaversAdminReportingPage extends Page {

    Functions functions = new Functions()

    static url = '/admin/specsavers/reports'
    static at = {
        waitFor {
            getTitle().contains('Specsavers - reports | epoints')
        }
    }

    static content = {
        tabNaviagationModule { module TabNaviagationModule }

        reportingTab { $('.nav-tabs').find('li', 2).find('a') }
        startDateLabel { $('.row').find('label', 0) }
        startDateInputField { $('.DatePicker', 0) }
        startDateDatePicker { $('.btn-noShadow', 0) }
        endDateLabel { $('.row').find('label', 1) }
        endDateInputField { $('.DatePicker', 1) }
        endDateDatePicker { $('.btn-noShadow', 1) }
        generateReportButton { $('.GenerateReport') }
        overviewSectionTab { $('.btn-group-justified').find('a', 0) }
        basicSectionHeader { $('h3') }
        epointsAwardedSectionTab { $('.btn-group-justified').find('a', 1) }
        epointsRedeemedSectionTab { $('.btn-group-justified').find('a', 2) }
        downloadReportTab { $('.u-link') }

        calendar { $('.dropdown-menu.ng-valid-date-disabled') }
        calendarMonthYear { calendar.find('strong') }
        calendarMonthNext { calendar.find('.glyphicon-chevron-right') }
        calendarMonthPrevious { calendar.find('.glyphicon-chevron-left') }
        calendarDayBasic { calendar.find('tbody').find('td').find('button') }
        calendarTodayButton { calendar.find('.btn-group.pull-left').find('button', 0) }
        calendarClearButton { calendar.find('.btn-group.pull-left').find('button', 1) }
        calendarCloseButton { calendar.find('.btn-success.pull-right', 0) }
        wrongDateAlert { $('.text-danger') }

        contentLoader(required: false) { $('.u-loader') }

        //overview section
        overwiewTable { $('.ReportOverview-table') }
        overwiewTableRowBasic { whichRow -> overwiewTable.find('tr', whichRow) }
        overwiewTableRowLabelBasic { whichRow -> overwiewTableRowBasic(whichRow).find('th') }
        overwiewTableRowDataBasic { whichRow -> overwiewTableRowBasic(whichRow).find('td') }
        //epoints awarded section
        //epoints redeemed section
        tableHeaderRow { $('.SpecsaversReports-reportsView').find('thead') }
        tableHeaderRowElementBasic { tableHeaderRow.find('th') }
        tableRowStatic { $('.SpecsaversReports-reportsView').find('tbody').find('tr') }
        tableRowBasic { whichRow -> $('.SpecsaversReports-reportsView').find('tbody').find('tr', whichRow) }
        tableRowElementBasic { whichRow, whichElement -> tableRowBasic(whichRow).find('td', whichElement) }
        epointsSummary { $('.ReportTotals').find('div', 0) }
        valueSummary { $('.ReportTotals').find('div', 1) }
        //pagination
        showingElement { $('.pagination-summary') }
        outOfElement { showingElement.find('.pagination-totalItems') }
        rowNumberSelector { $('.paginationPerPage') }
        rowNumberSelectorOption { rowNumberSelector.find('button') }
        topPaginationArrowLeft(required: false) { $('.pagination--prevButton', 0).find('i') }
        topPaginationArrowRight(required: false) { $('.pagination--nextButton', 0).find('i') }
        bottomPaginationArrowLeft { $('.pagination--prevButton', 1).find('i') }
        bottomPaginationArrowRight { $('.pagination--nextButton', 1).find('i') }
        bottomPaginationPageNumberBasic { $('.pagination-numericButton') }
        bottomPaginationPageNumberActiveBasic { $('.pagination-numericButton.is-active') }
    }

    //reporting tab
    def clickReportingTab() { waitFor { reportingTab.isDisplayed() }; reportingTab.click() }

    def openOverviewSection() {
        sleep(1000); waitFor { overviewSectionTab.isDisplayed() }; overviewSectionTab.click()
    }

    def openAwardedSection() {
        sleep(1000); waitFor { epointsAwardedSectionTab.isDisplayed() }; epointsAwardedSectionTab.click()
    }

    def openRedeemedSection() {
        sleep(1000); waitFor { epointsRedeemedSectionTab.isDisplayed() }; epointsRedeemedSectionTab.click()
    }

    def openStartDateCalendar() { waitFor { startDateDatePicker.isDisplayed() }; startDateDatePicker.click() }

    def clickCalendarTodayButton() { waitFor { calendarTodayButton.isDisplayed() }; calendarTodayButton.click() }

    def clickCalendarClearButton() { waitFor { calendarClearButton.isDisplayed() }; calendarClearButton.click() }

    def clickCalendarCloseButton() { waitFor { calendarCloseButton.isDisplayed() }; calendarCloseButton.click() }

    def clickCalendarChosenDayButton(number) {
        waitFor { calendarDayBasic[number].isDisplayed() }; calendarDayBasic[number].click()
    }

    def enterStartDate(startDate) {
        waitFor { startDateInputField.isDisplayed() }; startDateInputField.value(startDate)
    }

    def enterEndDate(endDate) { waitFor { endDateInputField.isDisplayed() }; endDateInputField.value(endDate) }

    def clickGenerateReportButton() { waitFor { generateReportButton.isDisplayed() }; generateReportButton.click() }

    def clickOpenLinkOfChosenRow(whichRow) {
        waitFor { tableRowElementBasic(whichRow, 6).isDisplayed() }; tableRowElementBasic(whichRow, 6).click()
    }
    //pagination
    def clickItemPerPage20() {
        waitFor { rowNumberSelectorOption[0].isDisplayed() }; rowNumberSelectorOption[0].click()
    }

    def clickItemPerPage50() {
        waitFor { rowNumberSelectorOption[1].isDisplayed() }; rowNumberSelectorOption[1].click()
    }

    def clickItemPerPage100() {
        waitFor { rowNumberSelectorOption[2].isDisplayed() }; rowNumberSelectorOption[2].click()
    }

    def clickItemPerPageAll() {
        waitFor { rowNumberSelectorOption[3].isDisplayed() }; rowNumberSelectorOption[3].click()
    }

    def clickNextPageButton() { waitFor { topPaginationArrowRight.isDisplayed() }; topPaginationArrowRight.click() }

    def clickPreviousPageButton() { waitFor { topPaginationArrowLeft.isDisplayed() }; topPaginationArrowLeft.click() }

    def clickChosenPageNumber(number) {
        functions.scrolPageUpDown("down")
        waitFor { bottomPaginationPageNumberBasic[number].isDisplayed() }
        bottomPaginationPageNumberBasic[number].click()
    }

    def clickNextBottomPageButton() {
        functions.scrolPageUpDown("down")
        waitFor { bottomPaginationArrowRight.isDisplayed() }; bottomPaginationArrowRight.click()
    }

    def clickPreviousBottomPageButton() {
        functions.scrolPageUpDown("down")
        waitFor { bottomPaginationArrowLeft.isDisplayed() }; bottomPaginationArrowLeft.click()
    }

}