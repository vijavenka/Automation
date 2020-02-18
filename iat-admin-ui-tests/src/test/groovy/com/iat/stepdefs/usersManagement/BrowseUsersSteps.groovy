package com.iat.stepdefs.usersManagement

import com.iat.Config
import com.iat.domain.createNewUser
import com.iat.pages.DashboardPage
import com.iat.pages.userProfile.LoginPage
import com.iat.pages.usersManagement.BrowseUsersPage
import com.iat.pages.usersManagement.EditUserPage
import com.iat.repository.impl.CreateUserRepositoryImpl
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.utils.DataExchanger
import com.iat.stepdefs.utils.HelpFunctions
import com.iat.stepdefs.utils.JdbcDatabaseConnector
import com.iat.stepdefs.utils.JsonParserUtils

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.After
import static cucumber.api.groovy.Hooks.Before

def loginPage = new LoginPage()
def dashboardPage = new DashboardPage()
def browseUsersPage = new BrowseUsersPage()
def helpFunctions = new HelpFunctions()
JsonParserUtils jsonParserUtils = JsonParserUtils.getInstance();
DataExchanger dataExchanger = DataExchanger.getInstance();
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolIatAdmin)

String searchPhrase = ''
def createdUserId
def createdUserEmail
def response
def initialData

Before('@createNewUserBeforeTest') {
    createdUserEmail = "automatedtestemail" + helpFunctions.returnCurrentEpochTime() + "@gmail.com"
    body = new createNewUser(helpFunctions.returnCurrentEpochTime().toString(), "UI Automation", "1986-12-27", createdUserEmail, "MALE", "2015-12-27", "USER", "NONE", "ACTIVE", 221516L)
    createdUserId = jsonParserUtils.extractValueFromFlatJson(jsonParserUtils.convertStringToJson(new CreateUserRepositoryImpl().createNewUser(body, Config.superAdminLogin, Config.superAdminPassword)), 'id')

    dataExchanger.setUserEmail(createdUserEmail)
    dataExchanger.setUserUuid(createdUserId)
}

After('@DeleteCreatedUserAfterTest') {
    println "Removing user " + dataExchanger.getUserUuid()
    response = new CreateUserRepositoryImpl().deleteChosenUser(dataExchanger.getUserUuid(), Config.superAdminLogin, Config.superAdminPassword)
}

After('@deleteCreatedUserAfterTestPermanently') {
    println "Removing user " + dataExchanger.getUserUuid()
    response = new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(dataExchanger.getUserUuid())
    mySQLConnector.execute("DELETE FROM UserToken WHERE user_id in (SELECT id FROM User WHERE epointsUuid = '" + dataExchanger.getUserUuid() + "')");
    mySQLConnector.execute("DELETE FROM UserAuthority WHERE userId in (SELECT id FROM User WHERE epointsUuid = '" + dataExchanger.getUserUuid() + "')");
    mySQLConnector.execute("DELETE FROM User WHERE epointsUuid = '" + dataExchanger.getUserUuid() + "'");
}

Given(~/^User with a certain users management permissions is signed in to iat$/) { ->
    to LoginPage
    loginPage = page
    loginPage.signInUser(Config.superAdminLogin, Config.superAdminPassword)
}

// Scenario Outline: Reasons - check content of create reason form
Given(~/^User with a certain users management permissions is signed in to iat with (.+?), (.+?)$/) { String username, String password ->
    if (username.contains('default')) {
        username = Config.profileTestsAdminLogin
        password = Config.profileTestsAdminPassword
    }
    to LoginPage
    loginPage = page
    loginPage.signInUser(username, password)
}

Given(~/^Browse users page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToBrowseUsersPageUsingHomePageLinks()
    at BrowseUsersPage
    browseUsersPage = page
}

Then(~/^He can see a users table with proper columns '(.+?)'$/) { String columnNames ->
    waitFor { browseUsersPage.usersTable.isDisplayed() }
    def columns = columnNames.split(",")
    for (int i = 0; i < columns.length; i++) {
        assert browseUsersPage.usersTableHeadersRowElement[i].text() == columns[i]
    }
}

Then(~/^The table is empty$/) { ->
    //assert !BrowseUsersPage.usersTableRowBasic[0].isDisplayed()
}

Then(~/^Each of the users table columns is sortable$/) { ->
    assert browseUsersPage.usersTableHeadersRowSortUpElementBasic.size() == 6
    assert browseUsersPage.usersTableHeadersRowSortDownElementBasic.size() == 6
}

Then(~/^Pagination component is available on find employee page$/) { ->
    assert browseUsersPage.paginationFirstButton.isDisplayed()
    assert browseUsersPage.paginationNextArrow.isDisplayed()
    assert browseUsersPage.paginationPageNumberButtonBasic[0].isDisplayed()
    assert browseUsersPage.paginationPageNumberButtonBasic[0].parent().hasClass('active')
    assert browseUsersPage.paginationNextArrow.isDisplayed()
    assert browseUsersPage.paginationLastButton.isDisplayed()
}

Then(~/^Filter component is available on find employee page$/) { ->
    waitFor { browseUsersPage.filtersBar.isDisplayed() }
    assert browseUsersPage.searchInputField.isDisplayed()
    assert browseUsersPage.filtersInDDL.isDisplayed()
    assert browseUsersPage.filtersClearButton.isDisplayed()
    assert browseUsersPage.filtersSearchButton.isDisplayed()
    assert browseUsersPage.filtersDateFromInput.isDisplayed()
    assert browseUsersPage.filtersDateFromDatepicker.isDisplayed()
    assert browseUsersPage.filtersDateToInput.isDisplayed()
    assert browseUsersPage.filtersDateToDatepicker.isDisplayed()
}

//Scenario Outline: Browse users - filtering
When(~/^He provides some filter data on find employee page '(.+?)'$/) { String inFilterOption ->
    waitFor { browseUsersPage.filtersBar.isDisplayed() }
    browseUsersPage.selectFilterInOption(inFilterOption)
    if (inFilterOption == 'User')
        searchPhrase = browseUsersPage.returnSearchPhraseAccordingToChosenFilter("First name")
    else
        searchPhrase = browseUsersPage.returnSearchPhraseAccordingToChosenFilter(inFilterOption)
    browseUsersPage.enterFilterSearchPhrase(searchPhrase)
    browseUsersPage.clickFilterSearchButton()
    waitFor { browseUsersPage.usersTableRowBasic[0].isDisplayed() }
}

Then(~/^Users table shows proper elements according to the chosen filter '(.+?)'$/) { String inFilterOption ->
    helpFunctions.waitSomeTime(Config.waitMedium)
    waitFor { browseUsersPage.topNavigation.preloader.hasClass('hide') }
    if (inFilterOption == 'User')
        inFilterOption = 'User details'
    waitFor { browseUsersPage.returnUsersTableLocator(inFilterOption, 0).text().toLowerCase().contains(searchPhrase) }
    for (int i = 0; i < browseUsersPage.usersTableRowBasic.size(); i++)
        assert browseUsersPage.returnUsersTableLocator(inFilterOption, i).text().toLowerCase().contains(searchPhrase)
}

Then(~/^They are ordered by the added date$/) { ->
    for (int i = 0; i < browseUsersPage.usersTableRowBasic.size() - 2; i++)
        assert (helpFunctions.parseDateFromStringToMiliseconds(browseUsersPage.returnUsersTableLocator("Date added", i + 1).text(), "yyyy-MM-dd") <= helpFunctions.parseDateFromStringToMiliseconds(browseUsersPage.returnUsersTableLocator("Date added", i).text(), "yyyy-MM-dd"))
}

//Scenario Outline: Browse users - pagination
When(~/^User changes the pages on find employee page$/) { ->
    ifMoreThanOnePage = browseUsersPage.paginationPageNumberButtonBasic.size() > 1
}

When(~/^Moves around with pagination on find employee page$/) { ->
    if (ifMoreThanOnePage) {
        browseUsersPage.clickChosePageNumberButton(1)
        helpFunctions.waitSomeTime(Config.waitMedium)
        assert browseUsersPage.paginationPageNumberButtonBasic[1].parent().hasClass('active')
        browseUsersPage.clickPaginationPreviousArrow()
        helpFunctions.waitSomeTime(Config.waitMedium)
        assert browseUsersPage.paginationPageNumberButtonBasic[0].parent().hasClass('active')
        browseUsersPage.clickPaginationLastButton()
        helpFunctions.waitSomeTime(Config.waitMedium)
        waitFor { browseUsersPage.paginationPageNumberButtonBasic.last().parent().hasClass('active') }
        assert browseUsersPage.paginationPageNumberButtonBasic.last().parent().hasClass('active')
    }
}

Then(~/^Chosen page results are displayed on find employee page$/) { ->
    browser.currentUrl.contains("page=" + browseUsersPage.paginationPageNumberButtonBasic.size().toString())
}

//Scenario Outline: Browse users - sorting
When(~/^User use '(.+?)' sorting option for find employee table '(.+?)'$/) { String sortOption, String columnName ->
    browseUsersPage.clickChosenColumnSelectedSortOption(sortOption, columnName)
    helpFunctions.waitSomeTime(Config.waitMedium)
    waitFor { browseUsersPage.topNavigation.preloader.hasClass('hide') }
}

Then(~/^Users table results will be correctly sorted according to selected column sort option '(.+?)', '(.+?)'$/) { String columnName, String sortOption ->
    browseUsersPage.chckIfcolumnWasProperlySorted(columnName, sortOption)
}

//Scenario: Browse users - deleted users rows
When(~/^Deleted users will be filtered$/) { ->
    waitFor { browseUsersPage.filtersBar.isDisplayed() }
    browseUsersPage.selectFilterInOption("Status")
    browseUsersPage.enterFilterSearchPhrase("Deleted")
    browseUsersPage.clickFilterSearchButton()
    waitFor { browseUsersPage.usersTableRowBasic[0].isDisplayed() }
}

Then(~/^There are active "edit" buttons displayed for this records$/) { ->
    waitFor { browseUsersPage.returnUsersTableLocator("Actions", 0).isDisplayed() }
    for (int i = 0; i < browseUsersPage.usersTableRowBasic.size(); i++)
        assert browseUsersPage.returnUsersTableLocator("Actions", i).isDisplayed()
}

//Scenario: Browse users - edit button click
When(~/^User click edit button of chosen user$/) { ->
    browseUsersPage.clickEditButtonOfChosenUserRow(0)
}

Then(~/^He will be redirected to edit page of chosen user$/) { ->
    at EditUserPage
}

//Scenario: Filter  - date range filter
Given(~/^There is some find employee data available in the table$/) { ->
    initialData = ''
    for (int i = 0; i < browseUsersPage.usersTableRowBasic.size(); i++)
        initialData = initialData + browseUsersPage.usersTableRowBasic[i].text()
}

When(~/^User selects the date range on find employee page$/) { ->
    enteredDateRangeFrom = enteredDateRangeTo = browseUsersPage.returnUsersTableLocator('Date added', 0).text()
    browseUsersPage.enterDateRangeFilterValueFrom(helpFunctions.changeDateFormat(enteredDateRangeFrom, "yyyy-MM-dd", "dd-MM-yyyy"))
    browseUsersPage.enterDateRangeFilterValueTo(helpFunctions.changeDateFormat(enteredDateRangeTo, "yyyy-MM-dd", "dd-MM-yyyy"))
}

When(~/^User clicks search button on find employee page$/) { ->
    helpFunctions.waitSomeTime(Config.waitMedium)
    browseUsersPage.clickFilterSearchButton()
}

Then(~/^Data is properly filtered by the date on find employee page$/) { ->
    waitFor {
        browseUsersPage.returnUsersTableLocator('Date added', 0).text() == enteredDateRangeFrom
    }
    waitFor { browseUsersPage.topNavigation.preloader.hasClass('hide') }
    for (int i = 0; i < browseUsersPage.usersTableRowBasic.size() - 1; i++)
        assert browseUsersPage.returnUsersTableLocator('Date added', i).text() == enteredDateRangeFrom
}

//Scenario: Filter  - clear button
When(~/^User click clear filters button on find employee page$/) { ->
    browseUsersPage.clickFilterClearButton()
}

Then(~/^The initial find employee data set is displayed$/) { ->
    def initialDataAfterClear = ''
    waitFor { browseUsersPage.usersTableRowBasic[0].isDisplayed() }
    helpFunctions.waitSomeTime(Config.waitLong)
    for (int i = 0; i < browseUsersPage.usersTableRowBasic.size(); i++)
        initialDataAfterClear = initialDataAfterClear + browseUsersPage.usersTableRowBasic[i].text()
    assert initialData.replace(' ', '').equals(initialDataAfterClear.replace(' ', ''))
}