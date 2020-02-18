package com.iat.stepdefs.reporting

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.pointsAllocationManagement.GrantUsersPage
import com.iat.pages.reporting.ReportingPage
import com.iat.pages.userProfile.LoginPage
import com.iat.stepdefs.utils.HelpFunctions
import geb.Browser

import java.text.SimpleDateFormat

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

def reportingPage = new ReportingPage()
def helpFunctions = new HelpFunctions()
def browser = new Browser()
def fromDate = '16-10-2016'
def toDate = '17-10-2016'

//Scenario Outline: reporting page - general view
Given(~/^User with Dashboard management permissions is logged into iat admin$/) { ->
    to LoginPage
    loginPage = page
    loginPage.signInUser(Config.superAdminLogin, Config.superAdminPassword)
}

When(~/^User open Dashboard page$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToReportingPageUsingHomePageLinks()
    at ReportingPage
    reportingPage = page
    waitFor { (reportingPage.pageHeader.text() == 'Reporting') }
}

Then(~/^Login number graph is displayed$/) { ->
    waitFor { reportingPage.numberOfLoginsGraph.isDisplayed() }
    assert reportingPage.numberOfLoginsGraph.isDisplayed()
    assert reportingPage.chartTitleBasic[0].text() == 'Number of epoints.com logins'
}

Then(~/^Reasons usage graph is displayed$/) { ->
    assert reportingPage.reasonsUsageBreakdownGraph.isDisplayed()
    assert reportingPage.chartTitleBasic[1].text() == 'Ecards sent per reason'
}

Then(~/^Unique login number graph is displayed$/) { ->
    assert reportingPage.numberOfUniqueLoginsGraph.isDisplayed()
    assert reportingPage.chartTitleBasic[2].text().contains('Number of unique epoints.com logins')
}

Then(~/^Total user to user ecards sent graph is displayed$/) { ->
    assert reportingPage.totalUserToUserEcardsSentGraph.isDisplayed()
    assert reportingPage.chartTitleBasic[3].text() == 'Number of ecards sent'
}

Then(~/^Unique users sending ecards graph is displayed$/) { ->
    assert reportingPage.uniqueUsersSentEcardsGraph.isDisplayed()
    assert reportingPage.chartTitleBasic[4].text().contains('Unique users sending ecards')
}

Then(~/^Number of ecards opened graph is displayed$/) { ->
    assert reportingPage.numberOfEcardsOpenedGraph.isDisplayed()
    assert reportingPage.chartTitleBasic[5].text() == 'Number of ecards opened'
}

Then(~/^Filter component is displayed$/) { ->
    assert reportingPage.filtersBar.isDisplayed()
}

Then(~/^Export to file button is available on hr peer to peer Dashboard page$/) { ->
    assert reportingPage.exportToFileButton.isDisplayed()
    assert reportingPage.predefinedRangeOptionsDDL.isDisplayed()
    assert reportingPage.filtersDateFromInput.isDisplayed()
    assert reportingPage.filtersDateFromDatepicker.isDisplayed()
    assert reportingPage.filtersDateToInput.isDisplayed()
    assert reportingPage.filtersDateToDatepicker.isDisplayed()
}

//Scenario Outline: reporting page - date selector
When(~/^he clicks on the range drop-down in filters section$/) { ->
    //if some option is already chosen we need to back to default state
    reportingPage.selectTimePreset('TIME PRESETS')

    reportingPage.expandPredefinedDateRangeOptionsDDL()
}

Then(~/^the drop-down is opened$/) { ->
    waitFor { reportingPage.predefinedRangeDDLOption[0].isDisplayed() }
}

Then(~/^It shows predefined date range options '(.+?)'$/) { String predefinedOption ->
    def options = predefinedOption.split(',')
    for (int i = 0; i < options.size(); i++) {
        assert reportingPage.predefinedRangeDDLOption[i].text() == options[i]
    }
}

//Scenario Outline: reporting page - date selector filter - choose option
When(~/^He selects one of the predefined options from the range drop-down '(.+?)'$/) { String option ->
    reportingPage.expandPredefinedDateRangeOptionsDDL()
    switch (option) {
        case "Today":
            reportingPage.clickChosenPredefinedDateRangeoption(0)
            break
    }
}

Then(~/^From\/to fields are filled with the chosen range '(.+?)'$/) { String option ->
    switch (option) {
        case "Today":
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy")
            def today = sdf.format(new Date())
            assert reportingPage.filtersDateFromInput.value() == today
            assert reportingPage.filtersDateToInput.value() == today
            break
    }
}

//Scenario Outline: reporting page - date range filter
When(~/^He fills from\/to fields with the proper date range$/) { ->
    reportingPage.enterFromDate(fromDate)
    helpFunctions.waitSomeTime(Config.waitMedium) //call has to be made
    reportingPage.enterToDate(toDate)
    helpFunctions.waitSomeTime(Config.waitMedium) //call has to be made
}

Then(~/^Charts are refreshed to show the data only from the chosen range$/) { ->
    for (int i = 0; i < 5; i++) {
        assert reportingPage.returnChartsDatesOfSelectedTimeChart(i)[0].text() == fromDate
        assert reportingPage.returnChartsDatesOfSelectedTimeChart(i).last().text() == toDate
    }
}

Then(~/^The selection is persisted throughout the session$/) { ->
    to GrantUsersPage
    at GrantUsersPage
    helpFunctions.waitSomeTime(Config.waitMedium)
    to ReportingPage
    at ReportingPage
    reportingPage = page
    waitFor { reportingPage.filtersDateFromInput.isDisplayed() && reportingPage.filtersDateToInput.isDisplayed() }
    assert reportingPage.filtersDateFromInput.value() == fromDate
    assert reportingPage.filtersDateToInput.value() == toDate
}

//Scenario Outline: reporting page - returning user
Given(~/^Filtering is already done$/) { ->
    reportingPage.enterFromDate(fromDate)
    helpFunctions.waitSomeTime(Config.waitMedium) //call has to be made
    reportingPage.enterToDate(toDate)
    helpFunctions.waitSomeTime(Config.waitMedium) //call has to be made
}

When(~/^User logs out$/) { ->
    reportingPage.topNavigation.expandUserImageDDL()
    reportingPage.topNavigation.clickLogoutOption()
    helpFunctions.waitSomeTime(Config.waitLong)
}

Then(~/^He can see default Dashboard page view back again$/) { ->
    waitFor { (reportingPage.filtersDateFromInput.value() != fromDate) }
    assert reportingPage.filtersDateToInput.value() != fromDate
}

When(~/^Export button will be clicked on Dashboard page$/) { ->
    reportingPage.clickExportButton()
    helpFunctions.waitSomeTime(Config.waitShort)
}

Then(~/^Notification about '(.+?)' is displayed over Dashboard page$/) { info ->
    waitFor { reportingPage.exportPopupModule.header.isDisplayed() }
    assertThat("Wrong export popup header", reportingPage.exportPopupModule.header.text(), is(info))
    assertThat("Wrong export popup info", reportingPage.exportPopupModule.info.text(), is("You will get separate notification when file is ready to download."))
}

Then(~/^Notification about 'Export is in progress.' can be closed on Dashboard page$/) { ->
    reportingPage.exportPopupModule.closePopup()
}