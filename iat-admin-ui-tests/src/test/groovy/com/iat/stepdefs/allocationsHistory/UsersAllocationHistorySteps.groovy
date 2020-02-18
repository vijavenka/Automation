package com.iat.stepdefs.allocationsHistory

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.allocationsHistory.UsersAllocationHistoryPage
import com.iat.pages.userProfile.LoginPage
import com.iat.stepdefs.utils.HelpFunctions
import geb.Browser

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

def loginPage = new LoginPage()
def dashboardPage = new DashboardPage()
def usersAllocationHistoryPage = new UsersAllocationHistoryPage()
def helpFunctions = new HelpFunctions()
def browser = new Browser()

boolean ifMoreThanOnePage = false
boolean historyAvailable = false
def chosenFilterName
def enteredSearchPhrase

//Scenario Outline: History allocation - users allocation tables content
Given(~/^Admin with users awarding history permissions is logged into iat admin$/) { ->
    to LoginPage
    loginPage = page
    loginPage.signInUser(Config.superAdminLogin, Config.superAdminPassword)
}

When(~/^Users award history page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToUsersHistoryAllocationPageUsingHomePage()
    at UsersAllocationHistoryPage
    usersAllocationHistoryPage = page
}

Then(~/^He sees the table with breakdown points awarded to users$/) { ->
    waitFor { usersAllocationHistoryPage.allocationsTable.isDisplayed() }
    assert usersAllocationHistoryPage.allocationsTable.isDisplayed()
}

Then(~/^The users award data is sorted by date desc$/) { ->
    for (int i = 0; i < usersAllocationHistoryPage.allocationTableRowBasic.size() - 2; i++)
        assert (helpFunctions.parseDateFromStringToMiliseconds(usersAllocationHistoryPage.returnAllocationTableLocator("Date send", i + 1).text(), "yyyy-MM-dd") <= helpFunctions.parseDateFromStringToMiliseconds(usersAllocationHistoryPage.returnAllocationTableLocator("Date send", i).text(), "yyyy-MM-dd"))
}

Then(~/^Correct users award table columns are available '(.+?)'$/) { String columnNames ->
    def columns = columnNames.split(",")
    for (int i = 0; i < columns.length; i++)
        assert usersAllocationHistoryPage.allocationsTableHeadersRowElement[i].text() == columns[i]
}

Then(~/^Each of the users award table columns is sortable$/) { ->
    assert usersAllocationHistoryPage.allocationsTableHeadersRowSortUpElementBasic.size() == 7 //8 with approval process
    assert usersAllocationHistoryPage.allocationsTableHeadersRowSortDownElementBasic.size() == 7
}

Then(~/^Totals are displayed at the bottom of the users award table$/) { ->
    assert usersAllocationHistoryPage.allocationsPointsSummary.isDisplayed()
    assert usersAllocationHistoryPage.allocationsPoundsSummary.isDisplayed()
    int totalPoints = 0
    float totalPounds = 0
    for (int i = 0; i < usersAllocationHistoryPage.allocationTableRowBasic.size() - 1; i++) {//-1 for summary row
        totalPoints = totalPoints + Integer.parseInt(usersAllocationHistoryPage.returnAllocationTableLocator('Points awarded', i).text().replace(',', ''))
        totalPounds = totalPounds + Float.parseFloat(usersAllocationHistoryPage.returnAllocationTableLocator('Value of points', i).text().replace('£', ''))
    }
    assert totalPoints == Integer.parseInt(usersAllocationHistoryPage.allocationsPointsSummary.text().replace(',', ''))
    assert (totalPounds - Float.parseFloat(usersAllocationHistoryPage.allocationsPoundsSummary.text().replace('£', ''))).abs() < 0.001
}

Then(~/^Pagination component is available on users award history page$/) { ->
    assert usersAllocationHistoryPage.paginationFirstButton.isDisplayed()
    assert usersAllocationHistoryPage.paginationNextArrow.isDisplayed()
    assert usersAllocationHistoryPage.paginationPageNumberButtonBasic[0].isDisplayed()
    assert usersAllocationHistoryPage.paginationPageNumberButtonBasic[0].parent().hasClass('active')
    assert usersAllocationHistoryPage.paginationNextArrow.isDisplayed()
    assert usersAllocationHistoryPage.paginationLastButton.isDisplayed()
}

Then(~/^Export data to file button is available on users award history page$/) { ->
    assert usersAllocationHistoryPage.exportToFileButton.isDisplayed()
}

//Scenario Outline: History allocation - pagination
When(~/^User changes the pages on users award history page$/) { ->
    ifMoreThanOnePage = usersAllocationHistoryPage.paginationPageNumberButtonBasic.size() > 1
}

When(~/^Moves around with pagination on users award history page$/) { ->
    if (ifMoreThanOnePage) {
        usersAllocationHistoryPage.clickChosePageNumberButton(1)
        helpFunctions.waitSomeTime(Config.waitMedium)
        assert usersAllocationHistoryPage.paginationPageNumberButtonBasic[1].parent().hasClass('active')
        usersAllocationHistoryPage.clickPaginationPreviousArrow()
        helpFunctions.waitSomeTime(Config.waitMedium)
        assert usersAllocationHistoryPage.paginationPageNumberButtonBasic[0].parent().hasClass('active')
        usersAllocationHistoryPage.clickPaginationLastButton()
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { usersAllocationHistoryPage.paginationPageNumberButtonBasic.last().parent().hasClass('active') }
        assert usersAllocationHistoryPage.paginationPageNumberButtonBasic.last().parent().hasClass('active')
    }
}

Then(~/^Chosen page results are displayed on users award history page$/) { ->
    browser.currentUrl.contains("page=" + usersAllocationHistoryPage.paginationPageNumberButtonBasic.size().toString())
}

//Scenario Outline: History allocation - points breakdown blocks
Then(~/^He sees blocks with points breakdown on users award history page$/) { ->
    waitFor { usersAllocationHistoryPage.ecardsSentCard.text().contains("Sent ecards") }
    assert usersAllocationHistoryPage.ecardsSentCard.isDisplayed()
    assert usersAllocationHistoryPage.ecardsSentCard.text().contains("Sent ecards")
    assert usersAllocationHistoryPage.alocatedPointsCard.isDisplayed()
    assert usersAllocationHistoryPage.alocatedPointsCard.text().contains("Allocated points")
    assert usersAllocationHistoryPage.alocatedMoneyCard.isDisplayed()
    assert usersAllocationHistoryPage.alocatedMoneyCard.text().contains("Allocated money")
}

Then(~/^They include number of sent or received by admins department ecards$/) { ->
    assert usersAllocationHistoryPage.ecardsSentValue.isDisplayed()
}

Then(~/^They include number of allocated by admins department points$/) { ->
    assert usersAllocationHistoryPage.alocatedPointsValue.isDisplayed()
}

Then(~/^They include number of allocated by admins department money$/) { ->
    String allocatedPointsValue = usersAllocationHistoryPage.alocatedPointsValue.text().replace(',', '')
    assert (Integer.parseInt(allocatedPointsValue) / 200 - Float.parseFloat(usersAllocationHistoryPage.alocatedMoneyValue.text().replace(',', '').replace('£', ''))).abs() <= 0.006
}

//Scenario Outline: History allocation - approval status column
Then(~/^He can see an approval status column$/) { ->

}

Then(~/^It's filled with approved\/declined status for ecards with epoints and it's empty for ecard with no epoints$/) {
    ->
    for (int i = 0; i < usersAllocationHistoryPage.allocationTableRowBasic.size(); i++) {
        if (usersAllocationHistoryPage.returnAllocationTableLocator('Points awarded', i).text() == '0')
            assert usersAllocationHistoryPage.returnAllocationTableLocator('Approval status', i).text() == ''
        else {
            assert usersAllocationHistoryPage.returnAllocationTableLocator('Approval status', i).text() == '' ||
                    usersAllocationHistoryPage.returnAllocationTableLocator('Approval status', i).text() == 'rejected' ||
                    usersAllocationHistoryPage.returnAllocationTableLocator('Approval status', i).text() == 'approved' ||
                    usersAllocationHistoryPage.returnAllocationTableLocator('Approval status', i).text() == 'pending'
        }
    }
}

//Scenario Outline: Approval column filtering
When(~/^There is some users points allocation history data available in the table$/) { ->
    if (usersAllocationHistoryPage.allocationTableRowBasic[0].isDisplayed()) {
        historyAvailable = true
    }
}

When(~/^User chooses one of the available fields, specific to users points allocation history table '(.+?)'$/) { String filterName ->
    if (historyAvailable) {
        switch (filterName) {
            case "Points amount":
                chosenFilterName = 'Points amount'
                usersAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Value":
                chosenFilterName = 'Value'
                usersAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Receiver":
                chosenFilterName = 'Receiver'
                usersAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Sender":
                chosenFilterName = 'Sender'
                usersAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Sender department":
                chosenFilterName = 'Sender department'
                usersAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Receiver department":
                chosenFilterName = 'Receiver department'
                usersAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
            case "Approval status":
                chosenFilterName = 'Approval status'
                usersAllocationHistoryPage.selectFilterOption(chosenFilterName)
                break
        }
    }
}

When(~/^User types a keyword in the search field on users history allocation page$/) { ->
    if (historyAvailable) {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { usersAllocationHistoryPage.allocationTableRowBasic.size() > 1 }
        switch (chosenFilterName) {
            case "Points amount":
                enteredSearchPhrase = usersAllocationHistoryPage.returnAllocationTableLocator('Points awarded', 0).text().toLowerCase().replace(',', '')
                break
            case "Value":
                enteredSearchPhrase = usersAllocationHistoryPage.returnAllocationTableLocator('Value of points', 0).text().toLowerCase().replace('£', '')
                break
            case "Receiver":
                enteredSearchPhrase = usersAllocationHistoryPage.returnAllocationTableLocator('Send to', 0).text().toLowerCase().split(' ')[0]
                break
            case "Sender":
                enteredSearchPhrase = usersAllocationHistoryPage.returnAllocationTableLocator('Send from', 0).text().toLowerCase().split(' ')[0]
                break
            case "Sender department":
                sender = usersAllocationHistoryPage.returnAllocationTableLocator('Send from', 0).text()
                enteredSearchPhrase = sender.toLowerCase().substring(sender.lastIndexOf("(") + 1, sender.lastIndexOf(")"))
                break
            case "Receiver department":
                receiver = usersAllocationHistoryPage.returnAllocationTableLocator('Send to', 0).text()
                enteredSearchPhrase = receiver.toLowerCase().substring(receiver.lastIndexOf("(") + 1, receiver.lastIndexOf(")"))
                break
            case "Approval status":
                break
        }
        usersAllocationHistoryPage.enterFilterSearchPhrase(enteredSearchPhrase)
    }
}

When(~/^User clicks search button on users points allocation history page$/) { ->
    helpFunctions.waitSomeTime(Config.waitMedium)
    usersAllocationHistoryPage.clickFilterSearchButton()
}

Then(~/^Users award data is properly filtered$/) { ->
    if (historyAvailable) {
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { usersAllocationHistoryPage.topNavigation.preloader.hasClass('hide') }
        for (int i = 0; i < usersAllocationHistoryPage.allocationTableRowBasic.size() - 1; i++) {
            switch (chosenFilterName) {
                case "Points amount":
                    waitFor {
                        usersAllocationHistoryPage.returnAllocationTableLocator("Points awarded", 0).text().replace(',', '').toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert usersAllocationHistoryPage.returnAllocationTableLocator("Points awarded", i).text().replace(',', '').toLowerCase().contains(enteredSearchPhrase)
                    break
                case "Value":
                    waitFor {
                        usersAllocationHistoryPage.returnAllocationTableLocator("Value of points", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert usersAllocationHistoryPage.returnAllocationTableLocator("Value of points", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
                case "Receiver":
                    waitFor {
                        usersAllocationHistoryPage.returnAllocationTableLocator("Send to", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert usersAllocationHistoryPage.returnAllocationTableLocator("Send to", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
                case "Sender":
                    waitFor {
                        usersAllocationHistoryPage.returnAllocationTableLocator("Send from", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert usersAllocationHistoryPage.returnAllocationTableLocator("Send from", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
                case "Approval status":
                    waitFor {
                        usersAllocationHistoryPage.returnAllocationTableLocator("Approval status", 0).text().toLowerCase().contains(enteredSearchPhrase)
                    }
                    assert usersAllocationHistoryPage.returnAllocationTableLocator("Approval status", i).text().toLowerCase().contains(enteredSearchPhrase)
                    break
            }
        }
    }
}

Then(~/^The users awards totals values are changed to sum only the filtered data$/) { ->
    if (historyAvailable) {
        def pointsValue = 0
        def poundsValue = 0
        for (int i = 0; i < usersAllocationHistoryPage.allocationTableRowBasic.size() - 1; i++) {
            pointsValue = pointsValue + Integer.parseInt(usersAllocationHistoryPage.returnAllocationTableLocator('Points awarded', i).text().replace(',', ''))
            poundsValue = poundsValue + Float.parseFloat(usersAllocationHistoryPage.returnAllocationTableLocator('Value of points', i).text().replace('£', ''))
        }
        assert Integer.parseInt(usersAllocationHistoryPage.allocationsPointsSummary.text().replace(',', '')) == pointsValue
        assert (Float.parseFloat(usersAllocationHistoryPage.allocationsPoundsSummary.text().replace('£', '')) - poundsValue).abs() < 0.001
    }
}

Then(~/^The url reflects chosen filters on users points allocation history page$/) { ->
    if (historyAvailable) {
        switch (chosenFilterName) {
            case "Points amount":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase)
                assert browser.getDriver().currentUrl.contains('type=points')
                break
            case "Value":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase)
                assert browser.getDriver().currentUrl.contains('type=amount')
                break
            case "Receiver":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase)
                assert browser.getDriver().currentUrl.contains('type=to')
                break
            case "Sender":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase)
                assert browser.getDriver().currentUrl.contains('type=from')
                break
            case "Approval status":
                assert browser.getDriver().currentUrl.contains('keyword=' + enteredSearchPhrase)
                assert browser.getDriver().currentUrl.contains('type=approvalStatus')
                break
        }
    }
}

//Scenario Outline: Filters - keyword search/input field
When(~/^There is some user points allocation history data available in the table$/) { ->
    historyAvailable = usersAllocationHistoryPage.allocationTableRowBasic.size() > 0
    if (!historyAvailable)
        return
    initialTotalPointsValue = usersAllocationHistoryPage.allocationsPointsSummary.text()
    initialTotalPoundsValue = usersAllocationHistoryPage.allocationsPoundsSummary.text()
    initialData = ''
    for (int i = 0; i < usersAllocationHistoryPage.allocationTableRowBasic.size(); i++)
        initialData = initialData + usersAllocationHistoryPage.allocationTableRowBasic[i].text()
}

//Scenario Outline: Filter  - date range filter
When(~/^User selects the date range on users points award history page$/) { ->
    if (historyAvailable) {
        enteredDateRangeFrom = enteredDateRangeTo = usersAllocationHistoryPage.returnAllocationTableLocator('Date send', 0).text()
        usersAllocationHistoryPage.enterDateRangeFilterValueFrom(helpFunctions.changeDateFormat(enteredDateRangeFrom, "yyyy-MM-dd", "dd-MM-yyyy"))
        usersAllocationHistoryPage.enterDateRangeFilterValueTo(helpFunctions.changeDateFormat(enteredDateRangeTo, "yyyy-MM-dd", "dd-MM-yyyy"))
    }
}

Then(~/^Data is properly filtered by the date on users points award history page$/) { ->
    if (historyAvailable) {
        waitFor {
            usersAllocationHistoryPage.returnAllocationTableLocator('Date send', 0).text() == enteredDateRangeFrom
        }
        waitFor { usersAllocationHistoryPage.topNavigation.preloader.hasClass('hide') }
        for (int i = 0; i < usersAllocationHistoryPage.allocationTableRowBasic.size() - 1; i++)
            assert usersAllocationHistoryPage.returnAllocationTableLocator('Date send', i).text() == enteredDateRangeFrom
    }
}

Then(~/^The url reflects chosen date range on users points award history page$/) { ->
    assert browser.getDriver().currentUrl.contains('dateFrom=')
    assert browser.getDriver().currentUrl.contains('dateTo=')
}

//Scenario Outline: Filter  - clear button
When(~/^User click clear filters button on users points award history page$/) { ->
    usersAllocationHistoryPage.clickFilterClearButton()
}

Then(~/^The initial users awards data set is displayed$/) { ->
    def initialDataAfterClear = ''
    waitFor { usersAllocationHistoryPage.allocationTableRowBasic[0].isDisplayed() }
    helpFunctions.waitSomeTime(Config.waitMedium)
    for (int i = 0; i < usersAllocationHistoryPage.allocationTableRowBasic.size(); i++)
        initialDataAfterClear = initialDataAfterClear + usersAllocationHistoryPage.allocationTableRowBasic[i].text()
    assert initialData.replace(' ', '').equals(initialDataAfterClear.replace(' ', ''))
}

Then(~/^Basic users award history url is displayed again$/) { ->
    assert browser.getDriver().currentUrl.endsWith('/hr/history/users-allocation')
}

Then(~/^The totals show the sum of all users points award records$/) { ->
    assert initialTotalPointsValue == usersAllocationHistoryPage.allocationsPointsSummary.text()
    assert initialTotalPoundsValue == usersAllocationHistoryPage.allocationsPoundsSummary.text()
}

//Scenario Outline: Filter  - columns sorting
When(~/^User use '(.+?)' sorting option for user awards history table '(.+?)'$/) { String sortOption, String columnName ->
    usersAllocationHistoryPage.clickSortOptionOfChosenColumn(sortOption, columnName)
    helpFunctions.waitSomeTime(Config.waitLong)
    waitFor { usersAllocationHistoryPage.topNavigation.preloader.hasClass('hide') }
}

Then(~/^User awards history results will be correctly sorted according to selected column sort option '(.+?)', '(.+?)'$/) { String columnName, String sortOption ->
    usersAllocationHistoryPage.chckIfcolumnWasProperlySorted(columnName, sortOption)
}

When(~/^Export button will be clicked on users award history page$/) { ->
    usersAllocationHistoryPage.clickExportButton()
    helpFunctions.waitSomeTime(Config.waitShort)
}

Then(~/^Notification about '(.+?)' is displayed over users award history page$/) { info ->
    waitFor { usersAllocationHistoryPage.exportPopupModule.header.isDisplayed() }
    assertThat("Wrong export popup header", usersAllocationHistoryPage.exportPopupModule.header.text(), is(info))
    assertThat("Wrong export popup info", usersAllocationHistoryPage.exportPopupModule.info.text(), is("You will get separate notification when file is ready to download."))
}

Then(~/^Notification about 'Export is in progress.' can be closed on users award history page$/) { ->
    usersAllocationHistoryPage.exportPopupModule.closePopup()
}