package com.iat.pages.reporting

import com.iat.pages.allocationsHistory.modules.ExportPopupModule
import com.iat.pages.modules.LeftSidePanelNavigationModule
import com.iat.pages.modules.TopNavigationModule
import geb.Page

class ReportingPage extends Page {

    static url = '/reporting/hr-peer-to-peer'
    static atCheckWaiting = true
    static at = {
        waitFor { title.contains('epoints admin') && browser.currentUrl.contains('reporting/hr-peer-to-peer') }
    }

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

        baseStatisticsContainer(wait: true) { $('.row').last() }
        numberOfLoginsGraph(wait: true) { baseStatisticsContainer.find('.StatTimelineCard.CardTile', 0) }
        numberOfUniqueLoginsGraph(wait: true) { baseStatisticsContainer.find('.StatTimelineCard.CardTile', 1) }
        totalUserToUserEcardsSentGraph(wait: true) { baseStatisticsContainer.find('.StatTimelineCard.CardTile', 2) }
        uniqueUsersSentEcardsGraph(wait: true) { baseStatisticsContainer.find('.StatTimelineCard.CardTile', 3) }
        numberOfEcardsOpenedGraph(wait: true) { baseStatisticsContainer.find('.StatTimelineCard.CardTile', 4) }
        reasonsUsageBreakdownGraph(wait: true) { baseStatisticsContainer.find('.CardTile.StatPie') }

        chartTitleBasic(wait: true) { baseStatisticsContainer.find('.CardTile-title') }
        chartsDates(wait: true) {
            $(".TimelineChart").find('.c3-axis.c3-axis-x').find('tspan')
        }
    }

    def returnChartsDatesOfSelectedTimeChart(chartNumber) {
        return $(".TimelineChart", chartNumber).find('.c3-axis.c3-axis-x').find('tspan')
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
                if (predefinedRangeDDLOption[i].text().toLowerCase() == timePreset.toLowerCase())
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

    def clickExportButton() {
        waitFor { exportToFileButton.isDisplayed() }; exportToFileButton.click()
    }
}