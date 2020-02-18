package com.iat.stepdefs.userProfile

import com.iat.Config
import com.iat.pages.DashboardPage
import com.iat.pages.userProfile.ChangePasswordPage
import com.iat.pages.userProfile.UserProfilePage
import com.iat.stepdefs.utils.HelpFunctions
import geb.Browser

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

def userProfilePage = new UserProfilePage()
def helpFunctions = new HelpFunctions()
def browser = new Browser()
def dashboardPage = new DashboardPage()

def userData = [email: Config.profileTestsAdminLogin]
def userNewData = [:]

When(~/^My profile page is clicked on menu$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToUserProfilePage()
}

And(~/^My profile page is opened$/) { ->
    at DashboardPage
    dashboardPage = page
    dashboardPage.navigateToUserProfilePage()
    at UserProfilePage
    userProfilePage = page
    //userData.email = userProfilePage.emailInputField.value()
    userData.name = userProfilePage.nameInputField.value()
    userData.gender = userProfilePage.genderMaleCheckbox.hasClass('md-checked') ? 'MALE' : "FEMALE"
    userData.DOB = userProfilePage.dateOfBirthInputField.value()
}

Then(~/^Admin is presented with my profile interface$/) { ->
    at UserProfilePage
    userProfilePage = page
}

And(~/^Page contains disabled employee number field$/) { ->
    waitFor { userProfilePage.employeeNumberInputField.isDisplayed() }
    assertThat(userProfilePage.employeeNumberInputField.attr('disabled'), is('true'))
}

And(~/^Page contains name field$/) { ->
    assert userProfilePage.nameInputField.isDisplayed()
}

And(~/^Page contains gender checkboxes$/) { ->
    assert userProfilePage.genderMaleCheckbox.isDisplayed()
    assert userProfilePage.genderFemaleCheckbox.isDisplayed()
}

And(~/^Page contains date of birth field$/) { ->
    assert userProfilePage.dateOfBirthInputField.isDisplayed()
    assert userProfilePage.dateOfBirthDatepicker.isDisplayed()
}

And(~/^Page contains email field$/) { ->
    assert userProfilePage.emailInputField.isDisplayed()
}

And(~/^Page contains disabled user role checkboxes$/) { ->
    assert userProfilePage.userRoleUserCheckbox.isDisplayed()
    assertThat(userProfilePage.userRoleUserCheckbox.attr('disabled'), is('true'))
    assert userProfilePage.userRoleManagerCheckbox.isDisplayed()
    assertThat(userProfilePage.userRoleManagerCheckbox.attr('disabled'), is('true'))

    assert userProfilePage.adminPrivilagesNoneOption.isDisplayed()
    assertThat(userProfilePage.adminPrivilagesNoneOption.attr('disabled'), is('true'))
    assert userProfilePage.adminPrivilagesAdminOption.isDisplayed()
    assertThat(userProfilePage.adminPrivilagesAdminOption.attr('disabled'), is('true'))
    assert userProfilePage.adminPrivilagesSuperAdminOption.isDisplayed()
    assertThat(userProfilePage.adminPrivilagesSuperAdminOption.attr('disabled'), is('true'))
}

And(~/^Page contains read only department tree$/) { ->
    assert userProfilePage.departmentsTree.isDisplayed()
    userProfilePage.selectDepartment(1)
    assert !userProfilePage.departmentsTreeElementBasic[1].hasClass('FormlyTree-node--selected')
}

And(~/^Page contains reset and save button$/) { ->
    assert userProfilePage.resetButton.isDisplayed()
    assert userProfilePage.saveButton.isDisplayed()
}

When(~/^User changes his details but doesn\'t save$/) { ->
    userProfilePage.enterEmail('someemail@gmail.com')
    userProfilePage.enterName('Test Test')
    userProfilePage.selectGender('FEMALE')
    userProfilePage.enterDOB('04-09-2005')
}

And(~/^He clicks on \"Reset\" button on my profile page$/) { ->
    userProfilePage.clickResetButton()
}

And(~/^He click "Yes" button on reset confirmation popup$/) { ->
    userProfilePage.clickYesButtonOnResetConfirmationPopup()
}

Then(~/^Reset confirmation popup will be closed$/) { ->
    waitFor { !userProfilePage.resetConfirmationPopup.isDisplayed() }
}

And(~/^All changes are canceled and user sees his saved datails$/) { ->
    waitFor { (userProfilePage.nameInputField.value() == userData.name) }
    assertThat(userProfilePage.dateOfBirthInputField.value().toString(), is(userData.DOB))
    assertThat(userProfilePage.emailInputField.value() as String, is(userData.email))
    if (userData.gender == 'MALE')
        assert userProfilePage.genderMaleCheckbox.hasClass('md-checked')
    else
        assert userProfilePage.genderFemaleCheckbox.hasClass('md-checked')
}

When(~/^User clicks on Change password button$/) { ->
    userProfilePage.clickChangePasswordButton()
}

Then(~/^He is on page which allows him to change password$/) { ->
    at ChangePasswordPage
}

And(~/^He filled the form with proper data '(.*)'$/) { String _userNewDataStr ->
    _userNewData = _userNewDataStr.split(";")

    userNewData.email = _userNewData[0].contains('default') ? Config.profileTestsAdminLogin : _userNewData[0]
    userNewData.name = _userNewData[1] + helpFunctions.returnCurrentEpochTime()
    userNewData.gender = _userNewData[2]
    userNewData.DOB = _userNewData[3]

    userProfilePage.enterEmail(userNewData.email)
    userProfilePage.enterName(userNewData.name)
    userProfilePage.selectGender(userNewData.gender)
    userProfilePage.enterDOB(userNewData.DOB)
}

When(~/^He clicks on \"Save\" button under form$/) { ->
    userProfilePage.clickSaveButton()
}

When(~/^User decide if also epoints email has to be updated '(.+?)' on popup$/) { epointsEmailToBeUpdated ->
    //check popup content
    waitFor { userProfilePage.emailChangeConfirmationPopup.isDisplayed() }
    assertThat("Wrong email change confirmation popup header", userProfilePage.emailChangeConfirmationPopupHeader.text(), is("Which email should be changed"))
    assertThat("Email change confirmation popup admin only button missing", userProfilePage.emailChangeConfirmationPopupAdminOnlyButton.isDisplayed())
    assertThat("Email change confirmation popup both button missing", userProfilePage.emailChangeConfirmationPopupBothButton.isDisplayed())
    assertThat("Email change confirmation popup cancel button missing", userProfilePage.emailChangeConfirmationPopupCancelButton.isDisplayed())

    if (epointsEmailToBeUpdated == "true")
        userProfilePage.clickBothButtonOnEmailChangeConfirmationPopup()
    else
        userProfilePage.clickAdminOnlyButtonOnEmailChangeConfirmationPopup()

    waitFor { userProfilePage.topNavigation.alertInfo.isDisplayed() }
    assertThat(userProfilePage.topNavigation.alertInfo.text(), is('User details has been updated'))
}

Then(~/^Admin user stays on the my profile form$/) { ->
    at UserProfilePage
    waitFor { !userProfilePage.topNavigation.alertInfo.isDisplayed() }
    browser.getDriver().navigate().refresh()
}

And(~/^New data are saved and presenting$/) { ->
    if (userProfilePage.nameInputField.value() == userNewData.name) {
        helpFunctions.waitSomeTime(Config.waitMedium)
        browser.getDriver().navigate().refresh()
    }
    waitFor { (userProfilePage.nameInputField.value() == userNewData.name) }
    assertThat(userProfilePage.dateOfBirthInputField.value(), is(userNewData.DOB))
    assertThat(userProfilePage.emailInputField.value(), is(userNewData.email))
    if (userNewData.gender == 'MALE')
        assert userProfilePage.genderMaleCheckbox.hasClass('md-checked')
    else
        assert userProfilePage.genderFemaleCheckbox.hasClass('md-checked')
}