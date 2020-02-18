package com.iat.stepdefs.allocationsHistory

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.allocationsHistory.PartnersAllocationHistoryPage
import com.iat.pages.userProfile.LoginPage
import com.iat.stepdefs.utils.HelpFunctions
import geb.Browser

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

def loginPage = new LoginPage()
def dashboardPage = new DashboardPage()
def partnersAllocationHistoryPage = new PartnersAllocationHistoryPage()
def helpFunctions = new HelpFunctions()
def browser = new Browser()

boolean ifMoreThanOnePage = false
boolean historyAvailable = false

//Scenario Outline: History allocation - partner allocation tables content
Given(~/^User with partner allocation history permissions is logged into iat admin$/) { ->
    to LoginPage
    loginPage = page
    loginPage.signInUser(Config.uberAdminLogin, Config.uberAdminPassword)
}

When(~/^Partners history page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToPartnersHistoryAllocationPageUsingHomePageLinks()
    at PartnersAllocationHistoryPage
    partnersAllocationHistoryPage = page
}

Then(~/^He sees the table with breakdown of virtual points awarded to partners$/) { ->
    waitFor { partnersAllocationHistoryPage.allocationsTable.isDisplayed() }
    assert partnersAllocationHistoryPage.allocationsTable.isDisplayed()
}

Then(~/^The partners allocation data is sorted by date desc$/) { ->
    for (int i = 0; i < partnersAllocationHistoryPage.allocationTableRowBasic.size() - 2; i++)
        assert (helpFunctions.parseDateFromStringToMiliseconds(partnersAllocationHistoryPage.returnAllocationTableLocator("Date send", i + 1).text(), "yyyy-MM-dd") <= helpFunctions.parseDateFromStringToMiliseconds(partnersAllocationHistoryPage.returnAllocationTableLocator("Date send", i).text(), "yyyy-MM-dd"))
}

Then(~/^Correct partners allocation table columns are available '(.+?)'$/) { String columnNames ->
    def columns = columnNames.split(",")
    for (int i = 0; i < columns.length; i++)
        assert partnersAllocationHistoryPage.allocationsTableHeadersRowElement[i].text() == columns[i]
}

Then(~/^Each of the partners allocation table columns is sortable$/) { ->
    assert partnersAllocationHistoryPage.allocationsTableHeadersRowSortUpElementBasic.size() == 6
    assert partnersAllocationHistoryPage.allocationsTableHeadersRowSortDownElementBasic.size() == 6
}

Then(~/^Totals are displayed at the bottom of the partners allocation table$/) { ->
    assert partnersAllocationHistoryPage.allocationsPointsSummary.isDisplayed()
    assert partnersAllocationHistoryPage.allocationsPoundsSummary.isDisplayed()
    int totalPoints = 0
    float totalPounds = 0
    for (int i = 0; i < partnersAllocationHistoryPage.allocationTableRowBasic.size() - 1; i++) {//-1 for summary row
        totalPoints = totalPoints + Integer.parseInt(partnersAllocationHistoryPage.returnAllocationTableLocator('Points awarded', i).text().replace(',', ''))
        totalPounds = totalPounds + Float.parseFloat(partnersAllocationHistoryPage.returnAllocationTableLocator('Value of points', i).text().replace('£', ''))
    }
    assert totalPoints == Integer.parseInt(partnersAllocationHistoryPage.allocationsPointsSummary.text().replace(',', ''))
    assert (totalPounds - Float.parseFloat(partnersAllocationHistoryPage.allocationsPoundsSummary.text().replace(',', '').replace('£', ''))).abs() < 0.001
}

Then(~/^Pagination component is available$/) { ->
    assert partnersAllocationHistoryPage.paginationFirstButton.isDisplayed()
    assert partnersAllocationHistoryPage.paginationNextArrow.isDisplayed()
    assert partnersAllocationHistoryPage.paginationPageNumberButtonBasic[0].isDisplayed()
    assert partnersAllocationHistoryPage.paginationPageNumberButtonBasic[0].parent().hasClass('active')
    assert partnersAllocationHistoryPage.paginationNextArrow.isDisplayed()
    assert partnersAllocationHistoryPage.paginationLastButton.isDisplayed()
}

Then(~/^Export data to file button is available on partners history page$/) { ->
    assert partnersAllocationHistoryPage.exportToFileButton.isDisplayed()
}

//Scenario Outline: History allocation - pagination
When(~/^User changes the pages on partners history page$/) { ->
    ifMoreThanOnePage = partnersAllocationHistoryPage.paginationPageNumberButtonBasic.size() > 1
}

When(~/^Moves around with pagination on partners history page$/) { ->
    if (ifMoreThanOnePage) {
        partnersAllocationHistoryPage.clickChosePageNumberButton(1)
        helpFunctions.waitSomeTime(Config.waitMedium)
        assert partnersAllocationHistoryPage.paginationPageNumberButtonBasic[1].parent().hasClass('active')
        partnersAllocationHistoryPage.clickPaginationPreviousArrow()
        helpFunctions.waitSomeTime(Config.waitMedium)
        assert partnersAllocationHistoryPage.paginationPageNumberButtonBasic[0].parent().hasClass('active')
        partnersAllocationHistoryPage.clickPaginationLastButton()
        helpFunctions.waitSomeTime(Config.waitMedium)
        assert partnersAllocationHistoryPage.paginationPageNumberButtonBasic.last().parent().hasClass('active')
    }
}

Then(~/^Chosen page results are displayed on partners history page$/) { ->
    browser.currentUrl.contains("page=" + partnersAllocationHistoryPage.paginationPageNumberButtonBasic.size().toString())
}

//Scenario Outline: History allocation - points breakdown blocks
Then(~/^He sees blocks with points breakdown on partners history page$/) { ->
    waitFor { partnersAllocationHistoryPage.allocationsNumberCard.isDisplayed() }
    assert partnersAllocationHistoryPage.allocationsNumberCard.isDisplayed()
    assert partnersAllocationHistoryPage.allocationsNumberCard.text().contains("Allocations number")
    assert partnersAllocationHistoryPage.alocatedPointsCard.isDisplayed()
    assert partnersAllocationHistoryPage.alocatedPointsCard.text().contains("Allocated points")
    assert partnersAllocationHistoryPage.alocatedMoneyCard.isDisplayed()
    assert partnersAllocationHistoryPage.alocatedMoneyCard.text().contains("Allocated money")
}

Then(~/^They include number allocations done by company$/) { ->
    assert partnersAllocationHistoryPage.allocationsNumberValue.isDisplayed()
    waitFor(10) {
        Integer.parseInt(partnersAllocationHistoryPage.allocationsNumberValue.text()) == (partnersAllocationHistoryPage.allocationTableRowBasic.size() - 1)
    }
}

Then(~/^They include number of allocated by company points$/) { ->
    assert partnersAllocationHistoryPage.alocatedPointsValue.isDisplayed()
    assert partnersAllocationHistoryPage.alocatedPointsValue.text() == partnersAllocationHistoryPage.allocationsPointsSummary.text()
}

Then(~/^They include number of allocated by company money$/) { ->
    String allocatedPointsValue = partnersAllocationHistoryPage.alocatedPointsValue.text().replace(',', '')
    assert partnersAllocationHistoryPage.alocatedMoneyValue.text().replace(',', '').contains((Integer.parseInt(allocatedPointsValue) / 200).toString())
    assert partnersAllocationHistoryPage.alocatedMoneyValue.text() == partnersAllocationHistoryPage.allocationsPoundsSummary.text()
}

//Scenario Outline: Filters - keyword search/input field
When(~/^There is some partner points allocation history data available in the table$/) { ->
    if (partnersAllocationHistoryPage.allocationTableRowBasic.size() > 0) {
        historyAvailable = true
        initialTotalPointsValue = partnersAllocationHistoryPage.allocationsPointsSummary.text()
        initialTotalPoundsValue = partnersAllocationHistoryPage.allocationsPoundsSummary.text()
        initialData = ''
        for (int i = 0; i < partnersAllocationHistoryPage.allocationTableRowBasic.size(); i++)
            initialData = initialData + partnersAllocationHistoryPage.allocationTableRowBasic[i].text()
    }
}

When(~/^User chooses one of the available fields, specific to partner points allocation history table '(.+?)'$/) { String filterName ->
    if (historyAvailable) {
        switch (filterName) {
            case "Comment text":
                chosenFilterName = 'Comment text'
                partnersAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Who allocated":
                chosenFilterName = 'Who allocated'
                partnersAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Partner":
                chosenFilterName = 'Partner'
                partnersAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Value":
                chosenFilterName = 'Value'
                partnersAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Points amount":
                chosenFilterName = 'Points amount'
                partnersAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
        }
    }
}

When(~/^User types a keyword in the search field on partner history allocation page$/) { ->
    if (historyAvailable) {
        helpFunctions.waitSomeTime(Config.waitMedium)
        switch (chosenFilterName) {
            case "Comment text":
                enteredSearchPhrase = partnersAllocationHistoryPage.returnAllocationTableLocator('Comment', 0).text().toLowerCase()
                break
            case "Who allocated":
                enteredSearchPhrase = partnersAllocationHistoryPage.returnAllocationTableLocator('Who', 0).text().toLowerCase()
                break
            case "Partner":
                enteredSearchPhrase = partnersAllocationHistoryPage.returnAllocationTableLocator('Partner name', 0).text().toLowerCase()
                break
            case "Value":
                enteredSearchPhrase = partnersAllocationHistoryPage.returnAllocationTableLocator('Value of points', 0).text().toLowerCase().replace('£', '')
                break
            case "Points amount":
                enteredSearchPhrase = partnersAllocationHistoryPage.returnAllocationTableLocator('Points awarded', 0).text().toLowerCase().replace(',', '')
                break
        }
        partnersAllocationHistoryPage.enterFilterSearchPhrase(enteredSearchPhrase)
    }
}

When(~/^User clicks search button on partner points allocation history page$/) { ->
    helpFunctions.waitSomeTime(Config.waitMedium)
    partnersAllocationHistoryPage.clickFilterSearchButton()
}

Then(~/^Partner Allocation data is properly filtered$/) { ->
    if (historyAvailable) {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { partnersAllocationHistoryPage.topNavigation.preloader.hasClass('hide') }
        for (int i = 0; i < partnersAllocationHistoryPage.allocationTableRowBasic.size() - 1; i++) {
            switch (chosenFilterName) {
                case "Comment text":
                    waitFor {
                        partnersAllocationHistoryPage.returnAllocationTableLocator("Comment", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert partnersAllocationHistoryPage.returnAllocationTableLocator("Comment", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
                case "Who allocated":
                    waitFor {
                        partnersAllocationHistoryPage.returnAllocationTableLocator("Who", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert partnersAllocationHistoryPage.returnAllocationTableLocator("Who", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
                case "Partner":
                    waitFor {
                        partnersAllocationHistoryPage.returnAllocationTableLocator("Partner name", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert partnersAllocationHistoryPage.returnAllocationTableLocator("Partner name", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
                case "Value":
                    waitFor {
                        partnersAllocationHistoryPage.returnAllocationTableLocator("Value of points", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert partnersAllocationHistoryPage.returnAllocationTableLocator("Value of points", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
                case "Points amount":
                    waitFor {
                        partnersAllocationHistoryPage.returnAllocationTableLocator("Points awarded", 0).text().toLowerCase().replace(',', '').contains(enteredSearchPhrase)
                    }
                    assert partnersAllocationHistoryPage.returnAllocationTableLocator("Points awarded", i).text().toLowerCase().replace(',', '').contains(enteredSearchPhrase)
                    break
            }
        }
    }
}

Then(~/^The partner allocations totals values are changed to sum only the filtered data$/) { ->
    if (historyAvailable) {
        def pointsValue = 0
        def poundsValue = 0
        for (int i = 0; i < partnersAllocationHistoryPage.allocationTableRowBasic.size() - 1; i++) {
            pointsValue = pointsValue + Integer.parseInt(partnersAllocationHistoryPage.returnAllocationTableLocator('Points awarded', i).text().replace(',', ''))
            poundsValue = poundsValue + Float.parseFloat(partnersAllocationHistoryPage.returnAllocationTableLocator('Value of points', i).text().replace('£', ''))
        }
        assert Integer.parseInt(partnersAllocationHistoryPage.allocationsPointsSummary.text().replace(',', '')) == pointsValue
        assert (Float.parseFloat(partnersAllocationHistoryPage.allocationsPoundsSummary.text().replace('£', '')) - poundsValue).abs() < 0.001
    }
}

Then(~/^The url reflects chosen filters on partner points allocation history page$/) { ->
    if (historyAvailable) {
        switch (chosenFilterName) {
            case "Comment text":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase.split(' ')[0])
                assert browser.getDriver().currentUrl.contains('type=description')
                break
            case "Who allocated":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase.split(' ')[0])
                assert browser.getDriver().currentUrl.contains('type=who')
                break
            case "Partner":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase.split(' ')[0])
                assert browser.getDriver().currentUrl.contains('type=to')
                break
            case "Value":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase.split(' ')[0])
                assert browser.getDriver().currentUrl.contains('type=amount')
                break
            case "Points amount":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase.split(' ')[0])
                assert browser.getDriver().currentUrl.contains('type=points')
                break
        }
    }
}

//Scenario Outline: Filter  - date range filter
When(~/^User selects the date range on partner points allocation history page$/) { ->
    if (historyAvailable) {
        enteredDateRangeFrom = enteredDateRangeTo = partnersAllocationHistoryPage.returnAllocationTableLocator('Date send', 0).text()
        partnersAllocationHistoryPage.enterDateRangeFilterValueFrom(helpFunctions.changeDateFormat(enteredDateRangeFrom, "yyyy-MM-dd", "dd-MM-yyyy"))
        partnersAllocationHistoryPage.enterDateRangeFilterValueTo(helpFunctions.changeDateFormat(enteredDateRangeTo, "yyyy-MM-dd", "dd-MM-yyyy"))
    }
}

Then(~/^Data is properly filtered by the date on partner points allocation history page$/) { ->
    if (historyAvailable) {
        waitFor {
            partnersAllocationHistoryPage.returnAllocationTableLocator('Date send', 0).text() == enteredDateRangeFrom
        }
        waitFor { partnersAllocationHistoryPage.topNavigation.preloader.hasClass('hide') }
        for (int i = 0; i < partnersAllocationHistoryPage.allocationTableRowBasic.size() - 1; i++)
            assert partnersAllocationHistoryPage.returnAllocationTableLocator('Date send', i).text() == enteredDateRangeFrom
    }
}

Then(~/^The url reflects chosen date range on partner points allocation history page$/) { ->
    assert browser.getDriver().currentUrl.contains('dateFrom=')
    assert browser.getDriver().currentUrl.contains('dateTo=')
}

//Scenario Outline: Filter  - clear button
When(~/^User click clear filters button on partner points allocation history page$/) { ->
    partnersAllocationHistoryPage.clickFilterClearButton()
}

Then(~/^The initial partner allocation data set is displayed$/) { ->
    def initialDataAfterClear = ''
    waitFor { partnersAllocationHistoryPage.allocationTableRowBasic[0].isDisplayed() }
    helpFunctions.waitSomeTime(Config.waitMedium)
    for (int i = 0; i < partnersAllocationHistoryPage.allocationTableRowBasic.size(); i++)
        initialDataAfterClear = initialDataAfterClear + partnersAllocationHistoryPage.allocationTableRowBasic[i].text()
    assert initialData.replace(' ', '').equals(initialDataAfterClear.replace(' ', ''))
}

Then(~/^Basic partner allocation history url is displayed again$/) { ->
    assert browser.getDriver().currentUrl.endsWith('/hr/history/partners-allocation')
}

Then(~/^The totals show the sum of all partners points allocation records$/) { ->
    assert initialTotalPointsValue == partnersAllocationHistoryPage.allocationsPointsSummary.text()
    assert initialTotalPoundsValue == partnersAllocationHistoryPage.allocationsPoundsSummary.text()
}

//Scenario Outline: Filter  - columns sorting
When(~/^User use '(.+?)' sorting option for partner allocations history table '(.+?)'$/) { String sortOption, String columnName ->
    partnersAllocationHistoryPage.clickSortOptionOfChosenColumn(sortOption, columnName)
    helpFunctions.waitSomeTime(Config.waitMedium)
    waitFor { partnersAllocationHistoryPage.topNavigation.preloader.hasClass('hide') }
}

Then(~/^Partner allocations history results will be correctly sorted according to selected column sort option '(.+?)', '(.+?)'$/) { String columnName, String sortOption ->
    partnersAllocationHistoryPage.chckIfcolumnWasProperlySorted(columnName, sortOption)
}

When(~/^Export button will be clicked on partners history page$/) { ->
    partnersAllocationHistoryPage.clickExportButton()
    helpFunctions.waitSomeTime(Config.waitShort)
}

Then(~/^Notification about '(.+?)' is displayed over partners history page$/) { info ->
    waitFor { partnersAllocationHistoryPage.exportPopupModule.header.isDisplayed() }
    assertThat("Wrong export popup header", partnersAllocationHistoryPage.exportPopupModule.header.text(), is(info))
    assertThat("Wrong export popup info", partnersAllocationHistoryPage.exportPopupModule.info.text(), is("You will get separate notification when file is ready to download."))
}

Then(~/^Notification about 'Export is in progress.' can be closed on partners history page$/) { ->
    partnersAllocationHistoryPage.exportPopupModule.closePopup()
}