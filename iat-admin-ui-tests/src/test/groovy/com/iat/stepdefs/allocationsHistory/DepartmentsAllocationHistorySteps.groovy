package com.iat.stepdefs.allocationsHistory

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.allocationsHistory.DepartmentsAllocationHistoryPage
import com.iat.pages.userProfile.LoginPage
import com.iat.stepdefs.utils.DataExchanger
import com.iat.stepdefs.utils.HelpFunctions
import geb.Browser

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.is

def loginPage = new LoginPage()
def dashboardPage = new DashboardPage()
def departmentsAllocationHistoryPage = new DepartmentsAllocationHistoryPage()
DataExchanger dataExchanger = DataExchanger.getInstance();
def helpFunctions = new HelpFunctions()
def browser = new Browser()

boolean ifMoreThanOnePage = false

boolean historyAvailable = false
def chosenFilterName
def enteredSearchPhrase
def enteredDateRangeFrom
def enteredDateRangeTo
def initialTotalPointsValue
def initialTotalPoundsValue
def initialData
def maxWait = 60

//Scenario Outline: History allocation - department allocation tables content
Given(~/^User with department allocation history permissions is logged into iat admin$/) { ->
    to LoginPage
    loginPage = page
    loginPage.signInUser(Config.superAdminLogin, Config.superAdminPassword)
}

When(~/^Departments history page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToDepartmentsHistoryAllocationPageUsinhHomePageLinks()
    at DepartmentsAllocationHistoryPage
    departmentsAllocationHistoryPage = page
}

Then(~/^He sees the table with breakdown of virtual points awarded to departments$/) { ->
    waitFor { departmentsAllocationHistoryPage.allocationsTable.isDisplayed() }
    assert departmentsAllocationHistoryPage.allocationsTable.isDisplayed()
}

Then(~/^The departments allocation data is sorted by date desc$/) { ->
    for (int i = 0; i < departmentsAllocationHistoryPage.allocationTableRowBasic.size() - 2; i++)
        assert (helpFunctions.parseDateFromStringToMiliseconds(departmentsAllocationHistoryPage.returnAllocationTableLocator("Date send", i + 1).text(), "yyy-MM-dd") <= helpFunctions.parseDateFromStringToMiliseconds(departmentsAllocationHistoryPage.returnAllocationTableLocator("Date send", i).text(), "yyy-MM-dd"))
}

Then(~/^Correct departments allocation table columns are available '(.+?)'$/) { String columnNames ->
    def columns = columnNames.split(",")
    for (int i = 0; i < columns.length; i++)
        assert departmentsAllocationHistoryPage.allocationsTableHeadersRowElement[i].text() == columns[i]
}

Then(~/^Each of the departments allocation table columns is sortable$/) { ->
    assert departmentsAllocationHistoryPage.allocationsTableHeadersRowSortUpElementBasic.size() == 8
    assert departmentsAllocationHistoryPage.allocationsTableHeadersRowSortDownElementBasic.size() == 8
}

Then(~/^Totals are displayed at the bottom of the departments allocation table$/) { ->
    assert departmentsAllocationHistoryPage.allocationsPointsSummary.isDisplayed()
    assert departmentsAllocationHistoryPage.allocationsPoundsSummary.isDisplayed()
    int totalPoints = 0
    float totalPounds = 0
    for (int i = 0; i < departmentsAllocationHistoryPage.allocationTableRowBasic.size() - 1; i++) {//-1 for summary row
        totalPoints = totalPoints + Integer.parseInt(departmentsAllocationHistoryPage.returnAllocationTableLocator('Points awarded', i).text().replace(',', ''))
        totalPounds = totalPounds + Float.parseFloat(departmentsAllocationHistoryPage.returnAllocationTableLocator('Value of points', i).text().replace(',', '').replace('£', ''))
    }
    assert totalPoints == Integer.parseInt(departmentsAllocationHistoryPage.allocationsPointsSummary.text().replace(',', ''))
    assert (totalPounds - Float.parseFloat(departmentsAllocationHistoryPage.allocationsPoundsSummary.text().replace(',', '').replace('£', ''))).abs() < 0.001
}

Then(~/^Pagination component is available on departments history page$/) { ->
    assert departmentsAllocationHistoryPage.paginationFirstButton.isDisplayed()
    assert departmentsAllocationHistoryPage.paginationNextArrow.isDisplayed()
    assert departmentsAllocationHistoryPage.paginationPageNumberButtonBasic[0].isDisplayed()
    assert departmentsAllocationHistoryPage.paginationPageNumberButtonBasic[0].parent().hasClass('active')
    assert departmentsAllocationHistoryPage.paginationNextArrow.isDisplayed()
    assert departmentsAllocationHistoryPage.paginationLastButton.isDisplayed()
}

Then(~/^Export data to file button is available on departments history page$/) { ->
    assert departmentsAllocationHistoryPage.exportToFileButton.isDisplayed()
}

//Scenario Outline: History allocation - pagination
When(~/^User changes the pages on departments history page$/) { ->
    ifMoreThanOnePage = departmentsAllocationHistoryPage.paginationPageNumberButtonBasic.size() > 1
}

When(~/^Moves around with pagination on departments history page$/) { ->
    if (ifMoreThanOnePage) {
        departmentsAllocationHistoryPage.clickChosePageNumberButton(1)
        helpFunctions.waitSomeTime(Config.waitMedium)
        assert departmentsAllocationHistoryPage.paginationPageNumberButtonBasic[1].parent().hasClass('active')
        departmentsAllocationHistoryPage.clickPaginationPreviousArrow()
        helpFunctions.waitSomeTime(Config.waitMedium)
        assert departmentsAllocationHistoryPage.paginationPageNumberButtonBasic[0].parent().hasClass('active')
        departmentsAllocationHistoryPage.clickPaginationLastButton()
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { departmentsAllocationHistoryPage.paginationPageNumberButtonBasic.last().parent().hasClass('active') }
        assert departmentsAllocationHistoryPage.paginationPageNumberButtonBasic.last().parent().hasClass('active')
    }
}

Then(~/^Chosen page results are displayed on departments history page$/) { ->
    browser.currentUrl.contains("page=" + departmentsAllocationHistoryPage.paginationPageNumberButtonBasic.size().toString())
}

Then(~/^Sorting settings are applied to consecutive pages$/) { ->
    //TODO
}

//Scenario Outline: History allocation - points breakdown blocks
When(~/^Items per page is set to high value$/) { ->
    waitFor { browser.currentUrl.contains('allocation') }
    helpFunctions.waitSomeTime(Config.waitMedium)
    browser.go(browser.currentUrl + '?pageSize=5000')
}

Then(~/^He sees blocks with points breakdown on departments history page$/) { ->
    waitFor { departmentsAllocationHistoryPage.allocationsNumberCard.isDisplayed() }
    assert departmentsAllocationHistoryPage.allocationsNumberCard.text().contains("Allocations number")
    assert departmentsAllocationHistoryPage.alocatedPointsCard.isDisplayed()
    assert departmentsAllocationHistoryPage.alocatedPointsCard.text().contains("Allocated points")
    assert departmentsAllocationHistoryPage.alocatedMoneyCard.isDisplayed()
    assert departmentsAllocationHistoryPage.alocatedMoneyCard.text().contains("Allocated money")
}

Then(~/^They include number allocations done by department admins department$/) { ->
    assert departmentsAllocationHistoryPage.allocationsNumberValue.isDisplayed()
    waitFor(20) {
        Integer.parseInt(departmentsAllocationHistoryPage.allocationsNumberValue.text()) == (departmentsAllocationHistoryPage.allocationTableRowBasic.size() - 1)
    }
}

Then(~/^They include number of allocated by department admins department points$/) { ->
    assert departmentsAllocationHistoryPage.alocatedPointsValue.isDisplayed()
    assert departmentsAllocationHistoryPage.alocatedPointsValue.text() == departmentsAllocationHistoryPage.allocationsPointsSummary.text()
}

Then(~/^They include number of allocated by department admins department money$/) { ->
    def String allocatedPointsValue = departmentsAllocationHistoryPage.alocatedPointsValue.text().replace(',', '')
    assert (Integer.parseInt(allocatedPointsValue) / 200 - Float.parseFloat(departmentsAllocationHistoryPage.alocatedMoneyValue.text().replace(',', '').replace('£', ''))).abs() <= 0.006
    assert departmentsAllocationHistoryPage.alocatedMoneyValue.text().equals(departmentsAllocationHistoryPage.allocationsPoundsSummary.text())
}

//filters are generic so for now wil be implemented only here
//Scenario Outline: Filters - keyword search/input field
When(~/^There is some department points allocation history data available in the table$/) { ->
    if (departmentsAllocationHistoryPage.allocationTableRowBasic.size() > 0) {
        historyAvailable = true
        initialTotalPointsValue = departmentsAllocationHistoryPage.allocationsPointsSummary.text()
        initialTotalPoundsValue = departmentsAllocationHistoryPage.allocationsPoundsSummary.text()
        initialData = ''
        for (int i = 0; i < departmentsAllocationHistoryPage.allocationTableRowBasic.size(); i++)
            initialData = initialData + departmentsAllocationHistoryPage.allocationTableRowBasic[i].text()
    }
}

When(~/^User chooses one of the available fields, specific to department points allocation history table '(.+?)'$/) { filterName ->
    if (historyAvailable) {
        switch (filterName) {
            case "Comment text":
                chosenFilterName = 'Comment text'
                departmentsAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Department":
                chosenFilterName = 'Department'
                departmentsAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "From":
                chosenFilterName = 'From'
                departmentsAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Who allocated":
                chosenFilterName = 'Who allocated'
                departmentsAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Value":
                chosenFilterName = 'Value'
                departmentsAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Points amount":
                chosenFilterName = 'Points amount'
                departmentsAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
        }
    }
}

When(~/^User types a keyword in the search field on department history allocation page$/) { ->
    if (historyAvailable) {
        helpFunctions.waitSomeTime(Config.waitMedium)
        switch (chosenFilterName) {
            case "Comment text":
                enteredSearchPhrase = departmentsAllocationHistoryPage.returnAllocationTableLocator('Comment', 0).text().toLowerCase()
                break
            case "Department":
                enteredSearchPhrase = departmentsAllocationHistoryPage.returnAllocationTableLocator('Department name', 0).text().toLowerCase()
                break
            case "From":
                enteredSearchPhrase = departmentsAllocationHistoryPage.returnAllocationTableLocator('From', 0).text().toLowerCase()
                break
            case "Who allocated":
                enteredSearchPhrase = departmentsAllocationHistoryPage.returnAllocationTableLocator('Who', 0).text().toLowerCase()
                break
            case "Value":
                enteredSearchPhrase = departmentsAllocationHistoryPage.returnAllocationTableLocator('Value of points', 0).text().toLowerCase().replace('£', '')
                break
            case "Points amount":
                enteredSearchPhrase = departmentsAllocationHistoryPage.returnAllocationTableLocator('Points awarded', 0).text().toLowerCase().replace(',', '')
                break
        }
        departmentsAllocationHistoryPage.enterFilterSearchPhrase(enteredSearchPhrase)
    }
}

When(~/^User clicks search button on department points allocation history page$/) { ->
    helpFunctions.waitSomeTime(Config.waitMedium)
    departmentsAllocationHistoryPage.clickFilterSearchButton()
}

Then(~/^Department Allocation data is properly filtered$/) { ->
    if (historyAvailable) {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { departmentsAllocationHistoryPage.topNavigation.preloader.hasClass('hide') }
        for (int i = 0; i < departmentsAllocationHistoryPage.allocationTableRowBasic.size() - 1; i++) {
            switch (chosenFilterName) {
                case "Comment text":
                    waitFor {
                        departmentsAllocationHistoryPage.returnAllocationTableLocator("Comment", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert departmentsAllocationHistoryPage.returnAllocationTableLocator("Comment", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
                case "Department":
                    waitFor {
                        departmentsAllocationHistoryPage.returnAllocationTableLocator("Department name", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert departmentsAllocationHistoryPage.returnAllocationTableLocator("Department name", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
                case "From":
                    waitFor {
                        departmentsAllocationHistoryPage.returnAllocationTableLocator("From", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert departmentsAllocationHistoryPage.returnAllocationTableLocator("From", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
                case "Who allocated":
                    waitFor {
                        departmentsAllocationHistoryPage.returnAllocationTableLocator("Who", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert departmentsAllocationHistoryPage.returnAllocationTableLocator("Who", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
                case "Value":
                    waitFor {
                        departmentsAllocationHistoryPage.returnAllocationTableLocator("Value of points", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert departmentsAllocationHistoryPage.returnAllocationTableLocator("Value of points", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
                case "Points amount":
                    waitFor {
                        departmentsAllocationHistoryPage.returnAllocationTableLocator("Points awarded", 0).text().toLowerCase().replace(',', '').contains(enteredSearchPhrase)
                    }
                    assert departmentsAllocationHistoryPage.returnAllocationTableLocator("Points awarded", i).text().toLowerCase().replace(',', '').contains(enteredSearchPhrase)
                    break
            }
        }
    }
}

Then(~/^The department allocations totals values are changed to sum only the filtered data$/) { ->
    if (historyAvailable) {
        def pointsValue = 0
        def poundsValue = 0
        for (int i = 0; i < departmentsAllocationHistoryPage.allocationTableRowBasic.size() - 1; i++) {
            pointsValue = pointsValue + Integer.parseInt(departmentsAllocationHistoryPage.returnAllocationTableLocator('Points awarded', i).text().replace(',', ''))
            poundsValue = poundsValue + Float.parseFloat(departmentsAllocationHistoryPage.returnAllocationTableLocator('Value of points', i).text().replace('£', ''))
        }
        assert Integer.parseInt(departmentsAllocationHistoryPage.allocationsPointsSummary.text().replace(',', '')) == pointsValue
        assert (Float.parseFloat(departmentsAllocationHistoryPage.allocationsPoundsSummary.text().replace('£', '')) - poundsValue).abs() < 0.001
    }
}

Then(~/^The url reflects chosen filters on department points allocation history page$/) { ->
    if (historyAvailable) {
        helpFunctions.waitSomeTime(Config.waitMedium)
        switch (chosenFilterName) {
            case "Comment text":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase.split(' ')[0])
                assert browser.getDriver().currentUrl.contains('type=description')
                break
            case "department":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase.split(' ')[0])
                assert browser.getDriver().currentUrl.contains('type=to')
                break
            case "From":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase.split(' ')[0])
                assert browser.getDriver().currentUrl.contains('type=from')
                break
            case "Who allocated":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase.split(' ')[0])
                assert browser.getDriver().currentUrl.contains('type=who')
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
When(~/^User selects the date range on department points allocation history page$/) { ->
    if (historyAvailable) {
        enteredDateRangeFrom = enteredDateRangeTo = departmentsAllocationHistoryPage.returnAllocationTableLocator('Date send', 0).text()
        departmentsAllocationHistoryPage.enterDateRangeFilterValueFrom(helpFunctions.changeDateFormat(enteredDateRangeFrom, "yyyy-MM-dd", "dd-MM-yyyy"))
        departmentsAllocationHistoryPage.enterDateRangeFilterValueTo(helpFunctions.changeDateFormat(enteredDateRangeTo, "yyyy-MM-dd", "dd-MM-yyyy"))
    }
}

Then(~/^Data is properly filtered by the date on department points allocation history page$/) { ->
    if (historyAvailable) {
        waitFor {
            departmentsAllocationHistoryPage.returnAllocationTableLocator('Date send', 0).text() == enteredDateRangeFrom
        }
        waitFor { departmentsAllocationHistoryPage.topNavigation.preloader.hasClass('hide') }
        for (int i = 0; i < departmentsAllocationHistoryPage.allocationTableRowBasic.size() - 1; i++)
            assert departmentsAllocationHistoryPage.returnAllocationTableLocator('Date send', i).text() == enteredDateRangeFrom
    }
}

Then(~/^The url reflects chosen date range on department points allocation history page$/) { ->
    assert browser.getDriver().currentUrl.contains('dateFrom=')
    assert browser.getDriver().currentUrl.contains('dateTo=')
}

//Scenario Outline: Filter  - clear button
When(~/^User click clear filters button on department points allocation history page$/) { ->
    departmentsAllocationHistoryPage.clickFilterClearButton()
}

Then(~/^The initial department allocation data set is displayed$/) { ->
    def initialDataAfterClear = ''
    waitFor { departmentsAllocationHistoryPage.topNavigation.preloader.hasClass('hide') }
    waitFor { departmentsAllocationHistoryPage.allocationTableRowBasic[0].isDisplayed() }
    helpFunctions.waitSomeTime(Config.waitMedium)
    for (int i = 0; i < departmentsAllocationHistoryPage.allocationTableRowBasic.size(); i++)
        initialDataAfterClear = initialDataAfterClear + departmentsAllocationHistoryPage.allocationTableRowBasic[i].text()
    assert initialData.replace(' ', '').equals(initialDataAfterClear.replace(' ', ''))
}

Then(~/^Basic department allocation history url is displayed again$/) { ->
    assert browser.getDriver().currentUrl.endsWith('/hr/history/departments-allocation')
}

Then(~/^The totals show the sum of all departments points allocation records$/) { ->
    assert initialTotalPointsValue == departmentsAllocationHistoryPage.allocationsPointsSummary.text()
    assert initialTotalPoundsValue == departmentsAllocationHistoryPage.allocationsPoundsSummary.text()
}

//Scenario Outline: Filter  - columns sorting
When(~/^User use '(.+?)' sorting option for department allocations history table '(.+?)'$/) { String sortOption, String columnName ->
    departmentsAllocationHistoryPage.clickSortOptionOfChosenColumn(sortOption, columnName)
    helpFunctions.waitSomeTime(Config.waitMedium)
    waitFor { departmentsAllocationHistoryPage.topNavigation.preloader.hasClass('hide') }
}

Then(~/^Department allocations history results will be correctly sorted according to selected column sort option '(.+?)', '(.+?)'$/) { String columnName, String sortOption ->
    departmentsAllocationHistoryPage.chckIfcolumnWasProperlySorted(columnName, sortOption)
}

Given(~/^User has no recent download notifications$/) { ->
    at DashboardPage
    dashboardPage = page
    waitFor { dashboardPage.topNavigation.messageIcon.isDisplayed() }
    if (dashboardPage.topNavigation.messageIconCounter.isDisplayed()) {
        dataExchanger.setNotificationsNumber(Integer.parseInt(dashboardPage.topNavigation.messageIconCounter.text()))
        dashboardPage.topNavigation.expandMessagesDDL()
        waitFor { dashboardPage.topNavigation.messagesDDL.isDisplayed() }
        int downloadNotificationsNumber = dashboardPage.topNavigation.messagesDDLDownloadIcon.size()
        helpFunctions.waitSomeTime(Config.waitShort)
        for (int i = 0; i < downloadNotificationsNumber; i++) {
            dashboardPage.topNavigation.clickDownloadNotification(0)
            helpFunctions.waitSomeTime(Config.waitShort)
            dataExchanger.setNotificationsNumber(dataExchanger.getNotificationsNumber() - 1)
            if ((downloadNotificationsNumber - i) != 1) {
                dashboardPage.topNavigation.expandMessagesDDL()
                helpFunctions.waitSomeTime(Config.waitShort)
            }
        }
    }
    helpFunctions.refreshPage()
}

When(~/^Export button will be clicked on departments history page$/) { ->
    departmentsAllocationHistoryPage.clickExportButton()
    helpFunctions.waitSomeTime(Config.waitShort)
}

Then(~/^Notification about '(.+?)' is displayed over departments history page$/) { info ->
    waitFor { departmentsAllocationHistoryPage.exportPopupModule.header.isDisplayed() }
    assertThat("Wrong export popup header", departmentsAllocationHistoryPage.exportPopupModule.header.text(), is(info))
    assertThat("Wrong export popup info", departmentsAllocationHistoryPage.exportPopupModule.info.text(), is("You will get separate notification when file is ready to download."))
}

Then(~/^Notification about 'Export is in progress.' can be closed on departments history page$/) { ->
    departmentsAllocationHistoryPage.exportPopupModule.closePopup()
}

Then(~/^After some time file ready to be downloaded will appear in notification area$/) { ->
    to DashboardPage
    dashboardPage = page
    int iterator = 0
    if (dashboardPage.topNavigation.messageIconCounter.isDisplayed()) {
        while ((Integer.parseInt(dashboardPage.topNavigation.messageIconCounter.text()) != (dataExchanger.getNotificationsNumber() + 1)) && iterator <= maxWait) {
            println "Waiting for notification - " + iterator
            iterator++
            helpFunctions.waitSomeTime(Config.waitMedium)
        }
    } else {
        while (!dashboardPage.topNavigation.messageIconCounter.isDisplayed() && iterator <= maxWait) {
            println "Waiting for notification - " + iterator
            iterator++
            helpFunctions.waitSomeTime(Config.waitMedium)
        }
    }


    assertThat("Download notification does not appear", Integer.parseInt(dashboardPage.topNavigation.messageIconCounter.text()), is(dataExchanger.getNotificationsNumber() + 1))
}

Then(~/^File name will be correct - '(.+?)'$/) { String partOfFileName ->
    dashboardPage.topNavigation.expandMessagesDDL()
    waitFor { dashboardPage.topNavigation.messagesDDL.isDisplayed() }
    assertThat("Wrong exported file name", dashboardPage.topNavigation.messagesDDLDownloadLabel[0].text(), containsString(partOfFileName))
    assertThat("Missing correct file extension", dashboardPage.topNavigation.messagesDDLDownloadLabel[0].text(), containsString(".xls"))
}