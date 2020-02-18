package com.iat.stepdefs.reporting

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.reporting.EcardUsageBreakdownPage
import com.iat.stepdefs.utils.HelpFunctions
import geb.Browser

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.is

def ecardUsageBreakdownPage = new EcardUsageBreakdownPage()
def browser = new Browser()
def helpFunctions = new HelpFunctions()

String chosenDepartmentName
String basicDepartmentFirstUserName

//Scenario Outline: Ecard usage breakdown - general view
When(~/^User open ecard usage breakdown page$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToEcardUsageBreakdownPageUsingHomePageLinks()
    at EcardUsageBreakdownPage
    ecardUsageBreakdownPage = page
    waitFor { (ecardUsageBreakdownPage.pageHeader.text() == 'Ecard usage breakdown') }
}

When(~/^User set time preset "(.+?)"$/) { String timePreset ->
    ecardUsageBreakdownPage.selectTimePreset(timePreset)
    helpFunctions.waitSomeTime(Config.waitShort)
    waitFor { !ecardUsageBreakdownPage.departmentsTableLinearLoader.isDisplayed() }
    waitFor { !ecardUsageBreakdownPage.usersTableLinearLoader.isDisplayed() }
}

Then(~/^First element of breadcrumb is displayed$/) { ->
    waitFor { ecardUsageBreakdownPage.departmentsBreadcrumb.isDisplayed() }
    assert ecardUsageBreakdownPage.departmentsBreadcrumb.isDisplayed()
    assertThat(ecardUsageBreakdownPage.departmentsBreadcrumbElement.size(), is(1))
}

Then(~/^Departments navigation table is displayed with proper columns$/) { ->
    waitFor { ecardUsageBreakdownPage.departmentsTable.isDisplayed() }
    assert ecardUsageBreakdownPage.departmentsTable.isDisplayed()
    assertThat(ecardUsageBreakdownPage.departmentsTableHeadersRowElement.first().text(), is("Department"))
    assertThat(ecardUsageBreakdownPage.departmentsTableHeadersRowElement[1].text(), is("Manager"))
    assertThat(ecardUsageBreakdownPage.departmentsTableHeadersRowElement[ecardUsageBreakdownPage.departmentsTableHeadersRowElement.size() - 2].text(), is("Total"))
}

And(~/^Manager details are properly populated$/) { ->
    //exact manager details will be hard to compare because we can have more than one manager in department
    //we also cannot go to browse users page to check details because we do not know if email or first/last name is displayed - even if we know, name/last name could be changed, in that case browse users search will not work
    //assumption here is done that each department in company has assigned manager
    for (int i = 0; i < ecardUsageBreakdownPage.departmentsTableRowBasic.size() - 1; i++) {
        def departmentName = ecardUsageBreakdownPage.returnDepartmentsTableLocator("Department", 0, i).text()
        assertThat("Department " + departmentName + " does not contains manager name", ecardUsageBreakdownPage.returnDepartmentsTableLocator("Manager", 0, i).isDisplayed())

    }
}

Then(~/^Ecards usage table is displayed with proper columns$/) { ->
    waitFor { ecardUsageBreakdownPage.usersTable.isDisplayed() }
    ecardUsageBreakdownPage.usersTable.isDisplayed()
    assertThat(ecardUsageBreakdownPage.usersTableHeadersRowElement.first().text(), is("Username"))
    assertThat(ecardUsageBreakdownPage.usersTableHeadersRowElement.last().text(), is("Total"))
}

Then(~/^Ecard usage breakdown filter component is displayed$/) { ->
    assert ecardUsageBreakdownPage.filtersBar.isDisplayed()
    assert ecardUsageBreakdownPage.predefinedRangeOptionsDDL.isDisplayed()
    assert ecardUsageBreakdownPage.filtersDateFromInput.isDisplayed()
    assert ecardUsageBreakdownPage.filtersDateFromDatepicker.isDisplayed()
    assert ecardUsageBreakdownPage.filtersDateToInput.isDisplayed()
    assert ecardUsageBreakdownPage.filtersDateToDatepicker.isDisplayed()
}

Then(~/^Export to file button is available on ecard usage breakdowng page$/) { ->
    assert ecardUsageBreakdownPage.exportToFileButton.isDisplayed()
    assertThat("Wrong export button name", ecardUsageBreakdownPage.exportToFileButton.text(), containsString("Export All"))
}

//Scenario Outline: Ecard usage breakdown - department navigation
When(~/^User click some of department from initial list$/) { ->
    chosenDepartmentName = ecardUsageBreakdownPage.returnDepartmentsTableLocator('Department', 0, 0).text()
    basicDepartmentFirstUserName = ecardUsageBreakdownPage.returnUsersTableLocator('Username', 0, 0).text()
    System.out.println(chosenDepartmentName)
    System.out.println(basicDepartmentFirstUserName)
    ecardUsageBreakdownPage.clickChosenDepartmentFormDepartmentsTable(0)
}

Then(~/^Department table will be refreshed and show only departments below selected departments$/) { ->
    waitFor {
        (ecardUsageBreakdownPage.returnDepartmentsTableLocator('Department', 0, 0).text() != chosenDepartmentName)
    }
}

Then(~/^Users table will be updated and show only ecards sent by user from chosen department$/) { ->
//    waitFor {
//        !ecardUsageBreakdownPage.returnUsersTableLocator('Username', 0, 0).text().equals(basicDepartmentFirstUserName)
//    }
}

Then(~/^Selected department will appear on breadcrumb as last element$/) { ->
    assertThat(ecardUsageBreakdownPage.departmentsBreadcrumbElement.last().text(), is(chosenDepartmentName))
    assertThat(ecardUsageBreakdownPage.departmentsBreadcrumbElement.size(), is(2))
}

Then(~/^User can use breadcrumb to navigate to higher levels of company structure$/) { ->
    ecardUsageBreakdownPage.clickChosenDepartmentBreadcrumbElement(0)
    waitFor {
        (ecardUsageBreakdownPage.returnDepartmentsTableLocator('Department', 0, 0).text() == chosenDepartmentName)
    }
    assertThat(ecardUsageBreakdownPage.returnUsersTableLocator("Username", 0, 0).text(), is(basicDepartmentFirstUserName))
}

When(~/^Export button will be clicked on ecard usage breakdown page$/) { ->
    ecardUsageBreakdownPage.clickExportButton()
    helpFunctions.waitSomeTime(Config.waitShort)
}

Then(~/^Notification about '(.+?)' is displayed over ecard usage breakdown page$/) { info ->
    waitFor { ecardUsageBreakdownPage.exportPopupModule.header.isDisplayed() }
    assertThat("Wrong export popup header", ecardUsageBreakdownPage.exportPopupModule.header.text(), is(info))
    assertThat("Wrong export popup info", ecardUsageBreakdownPage.exportPopupModule.info.text(), is("You will get separate notification when file is ready to download."))
}

Then(~/^Notification about 'Export is in progress.' can be closed on ecard usage breakdown page$/) { ->
    ecardUsageBreakdownPage.exportPopupModule.closePopup()
}