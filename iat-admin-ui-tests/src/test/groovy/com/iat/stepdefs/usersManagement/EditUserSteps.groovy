package com.iat.stepdefs.usersManagement

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.usersManagement.BrowseUsersPage
import com.iat.pages.usersManagement.EditUserPage
import com.iat.repository.impl.EpointsRepositoryImpl
import com.iat.repository.impl.UserTokenRepositoryImpl
import com.iat.stepdefs.utils.DataExchanger
import com.iat.stepdefs.utils.HelpFunctions
import geb.Browser

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

def dashboardPage = new DashboardPage()
def editUserPage = new EditUserPage()
def browseUsersPage = new BrowseUsersPage()
def helpFunctions = new HelpFunctions()
def browser = new Browser()
def userTokenRepository = new UserTokenRepositoryImpl()
def epointsRepository = new EpointsRepositoryImpl()
DataExchanger dataExchanger = DataExchanger.getInstance()

def userEmployeeNumber
def userEmail
def userName
def userLastName
def userGender
def userDOB
def userDepartentName
def userRole
def userManagerRole

def editedEmployeeNumber
def editedUserName
def editedUserLastName
def editedUserDOB
def editedUserEmail
def editedUserGender
def editedUserCompanyStartDate
def editedUserDepartentName
def editedUserRole
def editedUserManagerRole
boolean userEmailUpdated

//Scenario: Edit users - delete user popup
When(~/^Edit user page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToBrowseUsersPageUsingHomePageLinks()
    at BrowseUsersPage
    browseUsersPage = page
    //get all data of current first user
    waitFor { browseUsersPage.usersTableRowBasic[0].isDisplayed() }
    if (browseUsersPage.returnUsersTableLocator("Status", 0).text() != 'active') {
        helpFunctions.waitSomeTime(5000)
        browser.getDriver().navigate().refresh()
    }
    waitFor { (browseUsersPage.returnUsersTableLocator("Status", 0).text() == 'active') }
    userEmployeeNumber = browseUsersPage.returnUsersTableLocator("Employee number", 0).text()
    userEmail = browseUsersPage.returnUsersTableLocator("User details", 0).find('small').text()
    userName = browseUsersPage.returnUsersTableLocator("User details", 0).find('span').text().split(' ')[0]
    userLastName = browseUsersPage.returnUsersTableLocator("User details", 0).find('span').text().split(' ')[1]
    userDepartentName = browseUsersPage.returnUsersTableLocator("Department", 0).text()

    browseUsersPage.clickEditButtonOfChosenUserRow(0)
    at EditUserPage
    editUserPage = page

    //get all data of current first user
    waitFor { editUserPage.genderMaleCheckbox.isDisplayed() }
    if (editUserPage.genderMaleCheckbox.hasClass('md-checked'))
        userGender = 'male'
    else if (editUserPage.genderFemaleCheckbox.hasClass('md-checked'))
        userGender = 'female'
    userDOB = editUserPage.dateOfBirthInputField.value()
    if (editUserPage.adminPrivilagesNoneOption.hasClass('md-checked'))
        userManagerRole = 'none'
    else if (editUserPage.adminPrivilagesAdminOption.hasClass('md-checked'))
        userManagerRole = 'admin'
    else if (editUserPage.adminPrivilagesSuperAdminOption.hasClass('md-checked'))
        userManagerRole = 'superadmin'
}

When(~/^User clicks "Delete" button on edit user page$/) { ->
    editUserPage.clickDeleteEditButton()
}

Then(~/^Delete user confirmation modal is displayed with "Delete" and "Cancel" options$/) { ->
    waitFor { editUserPage.deleteEditUserPopup.isDisplayed() }
    helpFunctions.waitSomeTime(Config.waitMedium)
    assertThat(editUserPage.deleteEditUserPopupHeader.text(), containsString("Would you like to delete \"$userName $userLastName\"?"))
    assert editUserPage.deleteEditUserPopupCancelButton.isDisplayed()
    assert editUserPage.deleteEditUserPopupDeleteEditButton.isDisplayed()
}

Then(~/^He clicks "Cancel" button on delete user confirmation popup$/) { ->
    helpFunctions.waitSomeTime(Config.waitLong)
    editUserPage.clickCancelButtonOnDeleteEditUserConfirmationPopup()
    waitFor { !editUserPage.deleteEditUserPopup.isDisplayed() }
}

//Scenario Outline: Edit users - delete user popup, cancel button click
Then(~/^Delete edit user confirmation popup will be closed$/) { ->
    waitFor { !editUserPage.deleteEditUserPopup.isDisplayed() }
}

Then(~/^User will not be deleted$/) { ->
    editUserPage.clickCancelButton()
    waitFor { editUserPage.confirmationModal.isDisplayed() }
    editUserPage.clickCancelConfirmationModalYesButton()
    at BrowseUsersPage
    browseUsersPage = page
    assert browseUsersPage.returnUsersTableLocator("Actions", 0).isDisplayed()
    assertThat(browseUsersPage.returnUsersTableLocator("Status", 0).text(), is('active'))
}

//Scenario Outline: Edit users - delete user popup, delete button click
When(~/^He clicks "Delete" button on delete user confirmation popup$/) { ->
    editUserPage.clickDeleteEditButtonOnDeleteEditUserConfirmationPopup()
}

Then(~/^User will be soft deleted$/) { ->
    waitFor { editUserPage.topNavigation.alertInfo.isDisplayed() }
    assertThat(editUserPage.topNavigation.alertInfo.text(), is('User has been deleted'))
    at BrowseUsersPage
    browseUsersPage = page
    if (browseUsersPage.returnUsersTableLocator("Status", 0).text() != 'deleted') {
        helpFunctions.waitSomeTime(10000)
        browser.getDriver().navigate().refresh()
    }
    assertThat(browseUsersPage.returnUsersTableLocator("Status", 0).text(), is('deleted'))
}

//Scenario: Edit users - general view
Then(~/^Admin is presented with edit user interface$/) { ->
    waitFor { editUserPage.pageHeader.isDisplayed() }
    waitFor { editUserPage.pageHeader.text().contains('User details') }
}

Then(~/^Edit page contains delete user button$/) { ->
    assert editUserPage.deleteEditUserButton.isDisplayed()
    assert editUserPage.deleteEditUserButton.isDisplayed()
}

Then(~/^Edit page contains employee number field$/) { ->
    assertThat(editUserPage.employeenumberLabel.text(), is('Employee number'))
    assert editUserPage.employeeNumberInputField.isDisplayed()
}

Then(~/^Edit page contains name field$/) { ->
    assertThat(editUserPage.nameLabel.text(), is('Name'))
    assert editUserPage.nameInputField.isDisplayed()
}

Then(~/^Edit page contains date of birth field$/) { ->
    assertThat(editUserPage.dateOfBirthLabel.text(), is('Date of birth'))
    assert editUserPage.dateOfBirthInputField.isDisplayed()
    assert editUserPage.dateOfBirthDatepicker.isDisplayed()
}

Then(~/^Edit page contains email field$/) { ->
    assertThat(editUserPage.emailLabel.text(), is('Email *'))
    assert editUserPage.emailInputField.isDisplayed()
}

Then(~/^Edit page contains gender checkboxes$/) { ->
    assertThat(editUserPage.genderLabel.text(), is('Gender'))
    assert editUserPage.genderMaleCheckbox.isDisplayed()
    assert editUserPage.genderFemaleCheckbox.isDisplayed()
}

Then(~/^Edit page contains start date checkboxes$/) { ->
    assertThat(editUserPage.companyStartDateLabel.text(), is('Company Start Date'))
    assert editUserPage.companyStartDateInputField.isDisplayed()
    assert editUserPage.companyStartDateDatepicker.isDisplayed()
}

Then(~/^Edit page contains user role checkboxes$/) { ->
    assertThat(editUserPage.userRoleLabel.text(), is('Role'))
    assert editUserPage.userRoleUserCheckbox.isDisplayed()
    assert editUserPage.userRoleManagerCheckbox.isDisplayed()

    assertThat(editUserPage.adminPrivilagesLabel.text(), is('Admin privileges'))
    assert editUserPage.adminPrivilagesNoneOption.isDisplayed()
    assert editUserPage.adminPrivilagesAdminOption.isDisplayed()
    assert editUserPage.adminPrivilagesSuperAdminOption.isDisplayed()
}

Then(~/^Edit page contains department tree$/) { ->
    assertThat(editUserPage.departmentsLabel.text(), is('Department *'))
    assert editUserPage.departmentsTree.isDisplayed()
}

Then(~/^Edit page contains cancel and save button$/) { ->
    assert editUserPage.cancelButton.isDisplayed()
    assert editUserPage.saveButton.isDisplayed()
}

//Scenario: Edit users - cancel button click, confirmation modal yes
When(~/^User clicks "Cancel" button on edit user page$/) { ->
    editUserPage.clickCancelButton()
}

Then(~/^Confirmation popup will be displayed$/) { ->
    waitFor { editUserPage.confirmationModal.isDisplayed() }
    assertThat(editUserPage.confirmationModalText.text(), is('Unsaved data will be lost. Continue?'))
    assert editUserPage.confirmationModalNoButton.isDisplayed()
    assert editUserPage.confirmationModalYesButton.isDisplayed()
}

Then(~/^User can confirm cancel of user editing$/) { ->
    editUserPage.clickCancelConfirmationModalYesButton()
}

//Scenario: Edit users - cancel button click, confirmation modal no
Then(~/^User can cancel leaving the edit user page$/) { ->
    editUserPage.clickCancelConfirmationModalNoButton()
}

Then(~/^He stays on edit user page$/) { ->
    at EditUserPage
}

//Scenario: Edit users - edit all fields
When(~/^Admin edit all fields of chosen user (.+?)$/) { String emailToBeUpdated ->

    userEmailUpdated = !emailToBeUpdated.contains("without")

    editedEmployeeNumber = helpFunctions.returnCurrentEpochTime()
    editedUserName = "name" + helpFunctions.returnCurrentEpochTime()
    editedUserLastName = "lastname" + helpFunctions.returnCurrentEpochTime()
    editedUserDOB = "01-01-1989"
    editedUserEmail = "email" + helpFunctions.returnCurrentEpochTime() + "@wp.pl"

    if (userGender == 'male') {
        editedUserGender = 'female'
        editUserPage.selectGender('FEMALE')
    } else if (userGender == 'female') {
        editedUserGender = 'male'
        editUserPage.selectGender('MALE')
    }

    editedUserCompanyStartDate = "01-01-1999"

    if (userRole == 'user') {
        editedUserRole = 'manager'
        editUserPage.selectUserRole('MANAGER')
    } else if (userRole == 'manager') {
        editedUserRole = 'user'
        editUserPage.selectUserRole('USER')
    }

    editedUserDepartentName = editUserPage.departmentsTreeElementBasic[1].text()
    editUserPage.selectDepartment(1)

    if (userManagerRole == 'none') {
        editedUserManagerRole = 'admin'
        editUserPage.selectManagerRole('ADMIN')
    } else if (userManagerRole == 'admin' || userManagerRole == 'superadmin') {
        editedUserManagerRole = 'none'
        editUserPage.selectManagerRole('NONE')
    }

    editUserPage.enterEmployeeNumber(editedEmployeeNumber)
    editUserPage.enterName(editedUserName + " " + editedUserLastName)
    editUserPage.enterDOB(editedUserDOB)
    if (userEmailUpdated)
        editUserPage.enterEmail(editedUserEmail)
    editUserPage.enterCompanyStartDate(editedUserCompanyStartDate)
    helpFunctions.waitSomeTime(5000)
}

When(~/^Admin edit only email field of chosen user with existing in epoints email$/) { ->
    editUserPage.enterEmail(Config.associatedUser1)
}

When(~/^User clicks "Save" button on edit user page$/) { ->
    editUserPage.clickSaveButton()
}

When(~/^User choose if also epoints email has to be updated '(.+?)' on popup$/) { epointsEmailToBeUpdated ->
    //check popup content
    waitFor { editUserPage.emailChangeConfirmationPopup.isDisplayed() }
    assertThat("Wrong email change confirmation popup header", editUserPage.emailChangeConfirmationPopupHeader.text(), is("Which email should be changed"))
    assertThat("Email change confirmation popup admin only button missing", editUserPage.emailChangeConfirmationPopupAdminOnlyButton.isDisplayed())
    assertThat("Email change confirmation popup both button missing", editUserPage.emailChangeConfirmationPopupBothButton.isDisplayed())
    assertThat("Email change confirmation popup cancel button missing", editUserPage.emailChangeConfirmationPopupCancelButton.isDisplayed())

    if (epointsEmailToBeUpdated == "true") {
        editUserPage.clickBothButtonOnEmailChangeConfirmationPopup()
    } else {
        editUserPage.clickAdminOnlyButtonOnEmailChangeConfirmationPopup()
    }
}

Then(~/^Edited data will be saved$/) { ->
    waitFor { editUserPage.topNavigation.alertInfo.isDisplayed() }
    assertThat(editUserPage.topNavigation.alertInfo.text(), is('User details has been updated'))
}

Then(~/^New data will be visible on browse user page$/) { ->
    at BrowseUsersPage
    browseUsersPage = page
    waitFor { browseUsersPage.usersTableRowBasic[0].isDisplayed() }
    if (editedEmployeeNumber != browseUsersPage.returnUsersTableLocator("Employee number", 0).text()) {
        helpFunctions.waitSomeTime(10000)
        browser.getDriver().navigate().refresh()
    }
    assertThat(editedEmployeeNumber as String, is(browseUsersPage.returnUsersTableLocator("Employee number", 0).text()))
    if (userEmailUpdated)
        assertThat(editedUserEmail as String, is(browseUsersPage.returnUsersTableLocator("User details", 0).find('small').text()))
    assertThat(editedUserName as String, is(browseUsersPage.returnUsersTableLocator("User details", 0).find('span').text().split(' ')[0]))
    assertThat(editedUserLastName as String, is(browseUsersPage.returnUsersTableLocator("User details", 0).find('span').text().split(' ')[1]))
    assertThat(editedUserDepartentName as String, containsString(browseUsersPage.returnUsersTableLocator("Department", 0).text()))
}

Then(~/^Edited data will be visible on user edit page$/) { ->
    browseUsersPage.clickEditButtonOfChosenUserRow(0)
    at EditUserPage
    editUserPage = page
    assertThat(editUserPage.employeeNumberInputField.value(), is(editedEmployeeNumber as String))
    assertThat(editUserPage.nameInputField.value(), is("$editedUserName $editedUserLastName" as String))
    assertThat(editUserPage.dateOfBirthInputField.value(), is(editedUserDOB))
    if (userEmailUpdated)
        assert editedUserEmail == editUserPage.emailInputField.value()
    if (editedUserGender == 'male')
        assert editUserPage.genderMaleCheckbox.hasClass('md-checked')
    else if (editedUserGender == 'female')
        assert editUserPage.genderFemaleCheckbox.hasClass('md-checked')

    assertThat(editedUserCompanyStartDate, is(editUserPage.companyStartDateInputField.value()))

    if (editedUserRole == 'user')
        assert editUserPage.userRoleUserCheckbox.hasClass('md-checked')
    else if (editedUserRole == 'manager')
        assert editUserPage.userRoleManagerCheckbox.hasClass('md-checked')

    if (editedUserManagerRole == 'none')
        assert editUserPage.adminPrivilagesNoneOption.hasClass('md-checked')
    else if (editedUserManagerRole == 'admin')
        assert editUserPage.adminPrivilagesAdminOption.hasClass('md-checked')

    assert editUserPage.departmentsTreeElementBasic[1].hasClass('FormlyTree-node--selected')
}

When(~/^User edit previously deleted user$/) { ->
    browseUsersPage.clickEditButtonOfChosenUserRow(0)
    at EditUserPage
    editUserPage = page
}

When(~/^User clicks "Enable user" button on edit user page$/) { ->
    editUserPage.clickDeleteEditButton()

    waitFor { editUserPage.deleteEditUserPopup.isDisplayed() }
    helpFunctions.waitSomeTime(Config.waitMedium)
    assertThat(editUserPage.deleteEditUserPopupHeader.text(), containsString("Would you like to enable \"$userName $userLastName\"?"))
    assert editUserPage.deleteEditUserPopupCancelButton.isDisplayed()
    assert editUserPage.deleteEditUserPopupDeleteEditButton.isDisplayed()
}

When(~/^He clicks "Enable" button on delete user confirmation popup$/) { ->
    //we need to be sure that previous "delete" alert was closed
    waitFor { !editUserPage.topNavigation.alertInfo.isDisplayed() }
    helpFunctions.waitSomeTime(Config.waitLong)
    editUserPage.clickDeleteEditButtonOnDeleteEditUserConfirmationPopup()
    waitFor { !editUserPage.deleteEditUserPopup.isDisplayed() }
}

Then(~/^User will be reenabled$/) { ->
    waitFor { editUserPage.topNavigation.alertInfo.isDisplayed() }
    assertThat(editUserPage.topNavigation.alertInfo.text(), is("User has been enabled"))
    to BrowseUsersPage
    browseUsersPage = page
    if (browseUsersPage.returnUsersTableLocator("Status", 0).text() != 'active') {
        helpFunctions.waitSomeTime(10000)
        browser.getDriver().navigate().refresh()
    }
    assertThat(browseUsersPage.returnUsersTableLocator("Status", 0).text(), is("active"))
}

Then(~/^User will not be reenabled$/) { ->
    editUserPage.clickCancelButton()
    waitFor { editUserPage.confirmationModal.isDisplayed() }
    editUserPage.clickCancelConfirmationModalYesButton()
    at BrowseUsersPage
    browseUsersPage = page
    assert browseUsersPage.returnUsersTableLocator("Actions", 0).isDisplayed()
    assertThat(browseUsersPage.returnUsersTableLocator("Status", 0).text(), is("deleted"))
}

Given(~/^User which will be edited is (.+?) on epoints side$/) { String expectedVerificationState ->
    if (expectedVerificationState == "verified")
        epointsRepository.confirmEmail(userTokenRepository.getTokens(dataExchanger.getUserUuid()).get(0).getToken(), "password", "firstname", "lastName", "MALE", 302)
}

Then(~/^Information about user already exists will be displayed on edit user page$/) { ->
    waitFor { editUserPage.topNavigation.alertInfo.isDisplayed() }
    assertThat(editUserPage.topNavigation.alertInfo.text(), is('User with email ' + Config.associatedUser1 + ' already exists in company.'))
}

Given(~/^Email change history is (.+?)$/) { String emailChangeHistoryAvailability ->
    if (emailChangeHistoryAvailability.contains("not"))
        assertThat("Email change history - \"no changes\" info is not displayed", editUserPage.emailChangeHistoryArea.text(), containsString("no changes"))
    else {
        if (!editUserPage.emailChangeHistorySingleEmail[0].isDisplayed()) {
            helpFunctions.waitSomeTime(Config.waitLong)
            helpFunctions.refreshPage()
        }
        assertThat("Email change history is not available - emails are not visible", editUserPage.emailChangeHistorySingleEmail.size(), is(greaterThan(0)))
    }
}

When(~/^Admin edit chosen user email$/) { ->
    editedUserEmail = "email" + helpFunctions.returnCurrentEpochTime() + "@wp.pl"
    editUserPage.enterEmail(editedUserEmail)
}

When(~/^Admin back on edit page of user whose email was changed$/) { ->
    waitFor { browseUsersPage.usersTableRowBasic[0].isDisplayed() }
    browseUsersPage.clickEditButtonOfChosenUserRow(0)
    at EditUserPage
    editUserPage = page
}

Then(~/^Email change history shows (\d+) previous email$/) { int previousEmailsNumber ->
    assertThat("Wrong number of emails in history.", editUserPage.emailChangeHistorySingleEmail.size(), is(previousEmailsNumber))
    assertThat("Wrong number of last email in history.", editUserPage.emailChangeHistorySingleEmail.first().text(), is(dataExchanger.getUserEmail()))
}

Then(~/^Email change history displays user uuid$/) { ->
    assertThat("User uuid is not displayed.", editUserPage.emailChangeHistoryAreaUserUuid.isDisplayed())
    assertThat("User uuid is not correct.", editUserPage.emailChangeHistoryAreaUserUuid.text(), is("epoints UUID: " + dataExchanger.getUserUuid()))
}