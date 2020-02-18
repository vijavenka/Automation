package com.iat.stepdefs.usersManagement

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.usersManagement.BrowseUsersPage
import com.iat.pages.usersManagement.CreateUserPage
import com.iat.stepdefs.utils.HelpFunctions
import geb.Browser

import static cucumber.api.groovy.EN.*

def dashboardPage = new DashboardPage()
def browseUsersPage = new BrowseUsersPage()
def createUserPage = new CreateUserPage()
def helpFunctions = new HelpFunctions()
def browser = new Browser()

def newUserEmployeeNumber
def newUserEmail
def newUserName
def newUserGender
def newUserDOB
def newUserDepartentName
def newUserRole
def newUserManagerRole

//Scenario: Create user - general view
When(~/^Add employee page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToAddUserPageUsingHomePageLinks()
    at CreateUserPage
    createUserPage = page
}

Then(~/^Admin is presented with create user interface$/) { ->
    waitFor { createUserPage.pageHeader.isDisplayed() }
    assert createUserPage.pageHeader.text() == 'Add new User'
    assert createUserPage.addUserManuallyTab.hasClass('active')
}

Then(~/^It contains two tabs, "Insert manually" and "Bulk upload"$/) { ->
    assert createUserPage.addUserManuallyTab.isDisplayed()
    assert createUserPage.bulkUploadTab.isDisplayed()
}

Then(~/^It contains employee number field$/) { ->
    assert createUserPage.employeenumberLabel.text() == 'Employee number'
    assert createUserPage.employeeNumberInputField.isDisplayed()
}

Then(~/^It contains name field$/) { ->
    assert createUserPage.nameLabel.text() == 'Name'
    assert createUserPage.nameInputField.isDisplayed()
}

Then(~/^It contains date of birth field$/) { ->
    assert createUserPage.dateOfBirthLabel.text() == 'Date of birth'
    assert createUserPage.dateOfBirthInputField.isDisplayed()
    assert createUserPage.dateOfBirthDatepicker.isDisplayed()
}

Then(~/^It contains email field$/) { ->
    assert createUserPage.emailLabel.text() == 'Email *'
    assert createUserPage.emailInputField.isDisplayed()
}

Then(~/^It contains gender checkboxes$/) { ->
    assert createUserPage.genderLabel.text() == 'Gender'
    assert createUserPage.genderMaleCheckbox.isDisplayed()
    assert createUserPage.genderFemaleCheckbox.isDisplayed()
}

Then(~/^It contains company start date checkboxes$/) { ->
    assert createUserPage.companyStartDateLabel.text() == 'Company Start Date'
    assert createUserPage.companyStartDateInputField.isDisplayed()
    assert createUserPage.companyStartDateDatepicker.isDisplayed()
}

Then(~/^It contains user role checkboxes$/) { ->
    assert createUserPage.userRoleLabel.text() == 'Role'
    assert createUserPage.userRoleUserCheckbox.isDisplayed()
    assert createUserPage.userRoleManagerCheckbox.isDisplayed()

    assert createUserPage.adminPrivilagesLabel.text() == 'Admin privileges'
    assert createUserPage.adminPrivilagesNoneOption.isDisplayed()
    assert createUserPage.adminPrivilagesAdminOption.isDisplayed()
    assert createUserPage.adminPrivilagesSuperAdminOption.isDisplayed()
}

Then(~/^It contains department tree$/) { ->
    assert createUserPage.departmentsLabel.text() == 'Department *'
    assert createUserPage.departmentsTree.isDisplayed()
}

Then(~/^It contains cancel and save button$/) { ->
    assert createUserPage.canceButton.isDisplayed()
    assert createUserPage.saveButton.isDisplayed()
    assert createUserPage.saveButton.attr('disabled') == 'true'
}

//Scenario: Create user - cancel button clicked
When(~/^He clicks on \"Cancel\" button on add employee page$/) { ->
    helpFunctions.waitSomeTime(Config.waitMedium)
    createUserPage.clickCancelButton()
}

Then(~/^He is re-directed to "Browse users" interface$/) { ->
    at BrowseUsersPage
}

//Scenario: Create user - add and create new

Given(~/^He filled the form with proper data to create user '(.+?)'$/) { String newUserData ->
    userData = newUserData.split(";")

    newUserEmployeeNumber = userData[0] + helpFunctions.returnCurrentEpochTime()
    if (userData[1] != Config.associatedUser1)
        newUserEmail = userData[1] + helpFunctions.returnCurrentEpochTime() + "@gmail.com"
    else
        newUserEmail = userData[1]
    newUserName = userData[2] + helpFunctions.returnCurrentEpochTime()
    newUserGender = userData[3]
    newUserDOB = userData[4]
    newUserDepartentName = createUserPage.departmentsTreeElementBasicSelected.text().split(' ')[0]
    newUserRole = userData[5]
    newUserManagerRole = userData[6]
    newUserCompanyStartDate = userData[7]

    assert createUserPage.saveButton.attr('disabled') == 'true'
    createUserPage.enterEmail(newUserEmail)
    waitFor { !(createUserPage.saveButton.attr('disabled') == 'true') }
    createUserPage.enterEmployeeNumber(newUserEmployeeNumber)
    createUserPage.enterName(newUserName)
    createUserPage.enterDOB(newUserDOB)
    createUserPage.selectGender(newUserGender)
    createUserPage.enterCompanyStartDate(newUserCompanyStartDate)
//    disabled as fix for not clearing create user form, default user role is validated instead
//    createUserPage.selectUserRole(newUserRole)
    createUserPage.selectManagerRole(newUserManagerRole)
}

When(~/^He clicks on "Save" button$/) { ->
    createUserPage.clickSaveButton()
}

Then(~/^Admin user stays on the create user form$/) { ->
    at CreateUserPage
}

Then(~/^The form is cleared$/) { ->
    waitFor { (createUserPage.nameInputField.value() == '') }
    assert createUserPage.dateOfBirthInputField.value() == '01-01-1980'
    assert createUserPage.employeeNumberInputField.value() == ''
    assert createUserPage.emailInputField.value() == ''
    assert createUserPage.genderMaleCheckbox.hasClass('md-checked')
    assert !createUserPage.genderFemaleCheckbox.hasClass('md-checked')
    assert createUserPage.userRoleUserCheckbox.hasClass('md-checked')
    assert !createUserPage.userRoleManagerCheckbox.hasClass('md-checked')
    assert createUserPage.adminPrivilagesNoneOption.hasClass('md-checked')
    assert !createUserPage.adminPrivilagesAdminOption.hasClass('md-checked')
    assert !createUserPage.adminPrivilagesSuperAdminOption.hasClass('md-checked')
}

Then(~/^Admin user is informed about the success$/) { ->
    waitFor { createUserPage.topNavigation.alertInfo.isDisplayed() }
    assert createUserPage.topNavigation.alertInfo.text() == 'User has been added'
    waitFor { !createUserPage.topNavigation.alertInfo.isDisplayed() }
}

Then(~/^The new user is added and available on browse users page$/) { ->
    to browseUsersPage
    waitFor { browseUsersPage.usersTable.isDisplayed() }
    //BrowseUsersPage.selectFilterInOption("USER") //selected by default
    //BrowseUsersPage.enterFilterSearchPhrase(newUserEmail)
    //BrowseUsersPage.clickFilterSearchButton()
    //waitFor { BrowseUsersPage.usersTableRowBasic.size() == 1 }
    if (!browseUsersPage.returnUsersTableLocator("Employee number", 0).text().contains(newUserEmployeeNumber)) {
        helpFunctions.waitSomeTime(10000)
        browser.getDriver().navigate().refresh()
    }
    assert browseUsersPage.returnUsersTableLocator("Employee number", 0).text().contains(newUserEmployeeNumber)
    assert browseUsersPage.returnUsersTableLocator("User details", 0).text().contains(newUserName)
    assert browseUsersPage.returnUsersTableLocator("User details", 0).text().contains(newUserEmail)
    assert browseUsersPage.returnUsersTableLocator("Status", 0).text().contains('active')
}

//Scenario Outline: Create user - error info displaying
Then(~/^Information about user already exists will be displayed$/) { ->
    waitFor { createUserPage.topNavigation.alertInfo.isDisplayed() }
    assert createUserPage.topNavigation.alertInfo.text() == 'User with email ' + Config.associatedUser1 + ' already exists in company.'
}

//Scenario Outline: Create user - possible user roles to be created according to admin permissions
When(~/^He wants to add a user of chosen type$/) { ->

}

Then(~/^He can only create users within his permission scope '(.+?)'$/) { String username ->
    helpFunctions.waitSomeTime(Config.waitLong)
    if (username.contains('_manager')) {
        assert createUserPage.adminPrivilagesNoneOption.attr('disabled') == 'true'
        assert createUserPage.adminPrivilagesAdminOption.attr('disabled') == 'true'
        assert createUserPage.adminPrivilagesSuperAdminOption.attr('disabled') == 'true'
    } else if (username.contains('_admin')) {
        assert createUserPage.adminPrivilagesNoneOption.attr('disabled') == ''
        assert createUserPage.adminPrivilagesAdminOption.attr('disabled') == ''
        assert createUserPage.adminPrivilagesSuperAdminOption.attr('disabled') == 'true'
    } else if (username.contains('_superadmin')) {
        assert createUserPage.adminPrivilagesNoneOption.attr('disabled') == ''
        assert createUserPage.adminPrivilagesAdminOption.attr('disabled') == ''
        assert createUserPage.adminPrivilagesSuperAdminOption.attr('disabled') == ''
    }
}