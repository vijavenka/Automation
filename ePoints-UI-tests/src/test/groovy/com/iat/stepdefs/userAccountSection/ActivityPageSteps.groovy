package com.iat.stepdefs.userAccountSection

import com.iat.Config
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.userAccount.activity.ActivityPage
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.utils.DateTimeUtil
import com.iat.stepdefs.utils.JdbcDatabaseConnector

import static com.iat.stepdefs.utils.DateTimeUtil.convertToDate
import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

def epointsHomePage = new EpointsHomePage()
def activityPage = new ActivityPage()
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager)

// 1.1 //  ----------------------------------------------------------------------------- Your account - activity section
// -------------------------------------------------------------------------------------------------------- page content
Given(~/^Activity page is opened in '(.+?)' context$/) { String partner ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.signInUserToEpoints(Config.unitedUser, Config.unitedUserPassword)
    sleep(1000)
    if (partner.toLowerCase() == "united") {
        epointsHomePage.accountSwitcher.unitedSwitcher.click()
        waitFor { epointsHomePage.accountSwitcher.currentAccount == "United" }
    } else if (partner.toLowerCase() == "epoints") {
        epointsHomePage.accountSwitcher.epointsSwitcher.click()
        waitFor { epointsHomePage.accountSwitcher.currentAccount == "epoints" }
    }
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    sleep(1000)
    epointsHomePage.userDropDownMenuModule.useDropDownMenuMain.click()
    epointsHomePage.userDropDownMenuModule.clickOnActivityOption()
    sleep(1000)
    at ActivityPage
    activityPage = page

    //to avoid failures when low number of rows
    activityPage.clikcItemPerPageOption20()
    sleep(500)
    waitFor { !activityPage.loader.isDisplayed() }
}
Given(~/^Table contains three tabs '(.+?)'$/) { String tabs ->
    waitFor { activityPage.transactionTable.isDisplayed() }
    assert activityPage.activityTableHeaderCurreentBalanceOption.isDisplayed()
    assertThat(activityPage.activityTableHeaderCurreentBalanceOption.text(), containsString(tabs.split(';')[0]))
    assert activityPage.activityTableHeaderPendingOption.isDisplayed()
    assertThat(activityPage.activityTableHeaderPendingOption.text(), containsString(tabs.split(';')[1]))
    assert activityPage.activityTableHeaderDeclinedOption.isDisplayed()
    assertThat(activityPage.activityTableHeaderDeclinedOption.text(), containsString(tabs.split(';')[2]))
}
Given(~/^Balance table contains columns '(.+?)'$/) { String columns ->
    waitFor { activityPage.activityTableHeaderColumnNameBasic.size() > 0 }
    List<String> balanceColumns = activityPage.activityTableHeaderColumnNameBasic*.text()

    assertThat(balanceColumns, contains(columns.split(";")))
}
Given(~/^Pagination option are available$/) { ->
    assert activityPage.paginationPrevButton.isDisplayed()
    assert activityPage.paginationNextButton.isDisplayed()
    assert activityPage.paginationNumberBasic[0].isDisplayed()
    assert activityPage.activityTableHeaderItemsPerPage20.isDisplayed()
    assert activityPage.activityTableHeaderItemsPerPage40.isDisplayed()
    assert activityPage.activityTableHeaderItemsPerPage100.isDisplayed()
}
Given(~/^Sort by filter is available$/) { ->
    assert activityPage.activityTableHeaderSortByDDL.isDisplayed()
}

// 1.2 //  ----------------------------------------------------------------------------- Your account - activity section
// ----------------------------------------------------------------------------------------------- items per page module
When(~/^User select number of activity rows which should be displayed in table '(\d+)'$/) { int rowsNumber ->
    if (rowsNumber == 20)
        activityPage.clikcItemPerPageOption20()
    else if (rowsNumber == 40)
        activityPage.clickItemPerPageOption40()
    else if (rowsNumber == 100)
        activityPage.clickItemPerPageOption100()
    sleep(500)
    waitFor { !activityPage.loader.isDisplayed() }

}
Then(~/^Wanted number of activity rows will be displayed '(\d+)'$/) { int rowsNumber ->
    assertThat(activityPage.activityTableContentDateBasic.size(), is(rowsNumber))
}

// 1.3 //  ----------------------------------------------------------------------------- Your account - activity section
// ---------------------------------------------------------------------------------------------------------- pagination
When(~/^User click next button on activity page$/) { ->
    activityPage.clickPaginationNextButton()
}
Then(~/^Second activity rows page will be displayed$/) { ->
    waitFor { activityPage.paginationNumberBasic[1].hasClass('is-active') }
}
Then(~/^User can go to any page using page number buttons$/) { ->
    activityPage.clickChosenPaginationNumberButton(0)
    waitFor { activityPage.paginationNumberBasic[0].hasClass('is-active') }
}

// 1.4 //  ----------------------------------------------------------------------------- Your account - activity section
// ---------------------------------------------------------------------------------------------------------------- sort
When(~/^User select wanted activity sort option '(.+?)'$/) { String sortOption ->
    assertThat("Invalid option! Probably outdated feature!", sortOption, isOneOf("Recent first", "Recent last"))
    activityPage.clickChosenSortByDDLOption(sortOption)
}
Then(~/^Activity rows will be sorted according to chosen sort option '(.+?)'$/) { String sortOption ->
    sleep(500)
    waitFor { !activityPage.loader.isDisplayed() }
    List<String> stringDates = activityPage.activityTableContentDateBasic*.text()
    List<Date> dates = new ArrayList<>()

    for (String date : stringDates)
        dates.add(convertToDate(date, DateTimeUtil.Format.ddMMMyyyy))

    if (sortOption == "Recent first")
        assertThat(dates, is(dates.sort(false).reverse()))
    else
        assertThat(dates, is(dates.sort(false)))
}

Then(~/^Each tab: 'Current balance;Pending;Decline' contains correct count value for '(.+?)' context$/) { String partner ->
    waitFor { activityPage.activityTableHeaderCurreentBalanceOption.isDisplayed() }
    waitFor { activityPage.activityTableHeaderPendingOption.isDisplayed() }
    waitFor { activityPage.activityTableHeaderDeclinedOption.isDisplayed() }

    String userId = mySQLConnector.getSingleResult("SELECT id FROM points_manager.User WHERE userKey='" + new UserRepositoryImpl().findByEmail(Config.unitedUser).getUuid() + "'")

    if (partner == "epoints") {
        assertThat("Wrong current balance counter", activityPage.activityTableHeaderCurreentBalanceOption.text(), containsString(mySQLConnector.getSingleResult("SELECT count(*) FROM points_manager.Points WHERE userId = $userId AND status IN ('CONFIRMED','SPENT','REDEEMED', 'TRANSFERED') AND accountId is null")))
        assertThat("Wrong pending counter", activityPage.activityTableHeaderPendingOption.text(), containsString(mySQLConnector.getSingleResult("SELECT count(*) FROM points_manager.Points WHERE userId = $userId AND status IN ('PENDING') AND accountId is null")))
        assertThat("Wrong declined counter", activityPage.activityTableHeaderDeclinedOption.text(), containsString(mySQLConnector.getSingleResult("SELECT count(*) FROM points_manager.Points WHERE userId = $userId AND status IN ('DECLINED') AND accountId is null")))
    } else if (partner == "united") {
        assertThat("Wrong current balance counter", activityPage.activityTableHeaderCurreentBalanceOption.text(), containsString(mySQLConnector.getSingleResult("SELECT count(*) FROM points_manager.Points WHERE userId = $userId AND status IN ('CONFIRMED','SPENT','REDEEMED', 'TRANSFERED') AND accountId is not null")))
        assertThat("Wrong pending counter", activityPage.activityTableHeaderPendingOption.text(), containsString(mySQLConnector.getSingleResult("SELECT count(*) FROM points_manager.Points WHERE userId = $userId AND status IN ('PENDING') AND accountId is not null")))
        assertThat("Wrong declined counter", activityPage.activityTableHeaderDeclinedOption.text(), containsString(mySQLConnector.getSingleResult("SELECT count(*) FROM points_manager.Points WHERE userId = $userId AND status IN ('DECLINED') AND accountId is not null")))
    }
}