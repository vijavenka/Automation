package com.iat.stepdefs.joinLoginSection

import com.iat.Config
import com.iat.domain.NewUser
import com.iat.domain.User
import com.iat.pages.CompleteAccountPage
import com.iat.pages.JoinFinishedPage
import com.iat.pages.JoinPage
import com.iat.pages.home.EpointsHomePage
import com.iat.repository.impl.CreateUserRepositoryImpl
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.DataExchanger
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.After

def epointsHomePage = new EpointsHomePage()
def completeAccountPage = new CompleteAccountPage()
def joinPage = new JoinPage()
def joinFinishedPage = new JoinFinishedPage()
def dataExchanger = DataExchanger.getInstance()

def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()
User user

String createdAdminId
String firstName
String lastName
String gender

// 1.1 //  ------------------------------------ EPOINTS - add gender selection to epoints registration flow(RD-4228)
// ----------------------------------------------------------------------------------- complete account page content
String automatedTestEmail
Given(~/^Complete account page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    automatedTestEmail = 'automatedtestemail' + func.returnEpochOfCurrentDay() + '@gamil.com'
    dataExchanger.setEmail(automatedTestEmail)
    epointsHomePage.goToJoinPage()
    at JoinPage
    joinPage = page
    joinPage.enterEmailAddressOnJoin(automatedTestEmail)
    joinPage.clickJoinNowButton()
    sleep(2000)
    user = new UserRepositoryImpl().findByEmail(automatedTestEmail)
    String tokenValue = new UserRepositoryImpl().getTokenValueByUUID(user.getUuid())
    browser.go(envVar.epointsLink + "join/confirm-email/" + tokenValue)
    at CompleteAccountPage
    completeAccountPage = page
}
When(~/^User look at complete account page$/) { ->
    waitFor { browser.title == envVar.createPasswordTitle }
    waitFor { completeAccountPage.header.text() == envVar.completePageHeader }
    assert browser.title == envVar.createPasswordTitle
    assert completeAccountPage.header.text() == envVar.completePageHeader
}
Then(~/^Password field is available$/) { ->
    waitFor { completeAccountPage.passwordField.isDisplayed() }
    waitFor { completeAccountPage.basicLabel[0].text() == envVar.completePageLabels[0] }
    assert completeAccountPage.passwordField.isDisplayed()
    assert completeAccountPage.basicLabel[0].text() == envVar.completePageLabels[0]
}
Then(~/^Confirm password field is available$/) { ->
    waitFor { completeAccountPage.confirmPasswordField.isDisplayed() }
    waitFor { completeAccountPage.basicLabel[1].text() == envVar.completePageLabels[1] }
    assert completeAccountPage.confirmPasswordField.isDisplayed()
    assert completeAccountPage.basicLabel[1].text() == envVar.completePageLabels[1]
}
Then(~/^First name field is available$/) { ->
    waitFor { completeAccountPage.nameField.isDisplayed() }
    waitFor { completeAccountPage.basicLabel[2].text() == envVar.completePageLabels[2] }
    assert completeAccountPage.nameField.isDisplayed()
    assert completeAccountPage.basicLabel[2].text() == envVar.completePageLabels[2]
}
Then(~/^Last name field is available$/) { ->
    waitFor { completeAccountPage.lastNameField.isDisplayed() }
    waitFor { completeAccountPage.basicLabel[2].text() == envVar.completePageLabels[2] }
    assert completeAccountPage.lastNameField.isDisplayed()
    assert completeAccountPage.basicLabel[2].text() == envVar.completePageLabels[2]
}
Then(~/^Gender select options are available$/) { ->
    waitFor { completeAccountPage.genderMenWomenIcon[0].isDisplayed() }
    assert completeAccountPage.genderMenWomenIcon[0].isDisplayed()
    assert completeAccountPage.genderMenWomenIcon[1].isDisplayed()
    assert completeAccountPage.genderMenWomenIcon[0].hasClass('male')
    assert completeAccountPage.genderMenWomenIcon[1].hasClass('female')
    assert completeAccountPage.basicLabel[4].text() == envVar.completePageLabels[4]
}
Then(~/^Done option is available$/) { ->
    waitFor { completeAccountPage.doneButton.isDisplayed() }
    assert completeAccountPage.doneButton.isDisplayed()
}

// 1.2 //  ------------------------------------ EPOINTS - add gender selection to epoints registration flow(RD-4228)
// ------------------------------------------------------------------------------------ complete account page alerts
When(~/^User Enter all needed complete data$/) { ->
    completeAccountPage.fillAllCompliteAccountData('password', 'password', 'name', 'lastname')
}
When(~/^User delete all entered previous complete data$/) { ->
    completeAccountPage.fillAllCompliteAccountData('', '', '', '')
}
Then(~/^All complete account section alerts will be shown$/) { ->
    waitFor { completeAccountPage.basicAlert[1].isDisplayed() }
    assert completeAccountPage.basicAlert[1].text() == envVar.completePageAlerts[0]
    assert completeAccountPage.basicAlert[2].text() == envVar.completePageAlerts[1]
    assert completeAccountPage.basicAlert[3].text() == envVar.completePageAlerts[3]
    assert completeAccountPage.basicAlert[4].text() == envVar.completePageAlerts[4]
}
When(~/^User Enter two different passwords name and last name$/) { ->
    completeAccountPage.fillAllCompliteAccountData('password', 'differentPassword', 'name', 'lastname')
}
When(~/^Press done button$/) { ->
    completeAccountPage.clickDoneButton()
}
Then(~/^Only password confirmation alert will stay visible$/) { ->
    waitFor { completeAccountPage.basicAlert[2].text() == envVar.completePageAlerts[2] }
    assert completeAccountPage.basicAlert[2].text() == envVar.completePageAlerts[2]
}

// 1.3 //  ------------------------------------ EPOINTS - add gender selection to epoints registration flow(RD-4228)
// ---------------------------------------------------------------- complete account page, proper account completion
When(~/^User correctly fill all needed data$/) { ->
    completeAccountPage.fillAllCompliteAccountData('password', 'password', 'name', 'lastname')
    dataExchanger.setPassword('password')
}
When(~/^User select gender$/) { ->
    completeAccountPage.selectGenderOption(0)
}
Then(~/^User will be redirected to join finished page$/) { ->
    at JoinFinishedPage
    joinFinishedPage = page
    assert joinFinishedPage.allDoneViewHeader.text() == envVar.allDoneViewHeader
    assert joinFinishedPage.getEpointsNowButton.isDisplayed()
    assert joinFinishedPage.tellMeABitMoreLink.isDisplayed()
}
Then(~/^Account data should be updated$/) { ->
    //TODO data moved to dynamo
}

// 1.4 //  ----------------------------------------------------------------------------------- Complete account page
// --------------------------------------------------------------------------------------------------- invalid token
Given(~/^Complete account page is opened with expired token$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    browser.go(envVar.epointsLink + "join/confirm-email/" + "dd76we8d6we7d68we76dwe76d8we6d")
    at CompleteAccountPage
    completeAccountPage = page
}
When(~/^User look at opened page$/) { ->
    //leave empty
}
Then(~/^He will see invalid token message on account confirmation page$/) { ->
    waitFor() { completeAccountPage.confirmationFailedMessage.text() == envVar.confirmationFailedMessage }
    assert completeAccountPage.confirmationFailedMessage.text() == envVar.confirmationFailedMessage
    assert completeAccountPage.invalidLinkInformation.text() == envVar.confirmationLinkInvalidMessage
}

After('@deleteCreatedUserAfterTest') {
    String response = new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(createdAdminId)
}

Given(~/^User has been added manually in admin interface with given informations '(.*)','(.*)','(.*)'$/) {
    String _firstName,
    String _lastName,
    String _gender ->
        firstName = _firstName
        lastName = _lastName
        gender = _gender
        automatedTestEmail = 'userAddedInAdmin' + func.returnRandomValue(1000000) + '@gmail.com'
        String name = firstName + " " + lastName
        def body = new NewUser(
                employeeNumber: func.returnRandomValue(9999999).toString(), email: automatedTestEmail, name: name,
                gender: gender, birthDate: "1986-12-27", department: "221516", role: "USER", adminRole: "NONE", status: "ACTIVE")
        createdAdminId = new CreateUserRepositoryImpl().createNewUser(body, Config.superAdminLogin, Config.superAdminPassword)
        user = new User(uuid: createdAdminId, email: automatedTestEmail)
}

When(~/^He looks at complete account page$/) { ->
//    user = new UserRepositoryImpl().findByEmail(automatedTestEmail)
    String tokenValue = new UserRepositoryImpl().getTokenValueByUUID(user.getUuid())
    browser.go(envVar.epointsLink + "join/confirm-email/" + tokenValue)
    at CompleteAccountPage
    completeAccountPage = page
}

Then(~/^Form is filled in with his personal informations$/) { ->
    assert completeAccountPage.nameField.value() == firstName
    assert completeAccountPage.lastNameField.value() == lastName

    if (gender == 'MALE') {
        assert completeAccountPage.genderMenWomenIcon[0].getAttribute('class').contains('male active')
        assert completeAccountPage.genderMenWomenIcon[1].getAttribute('class').contains('female active') == false
    } else if (gender == 'FEMALE') {
        assert completeAccountPage.genderMenWomenIcon[0].getAttribute('class').contains('male active') == false
        assert completeAccountPage.genderMenWomenIcon[1].getAttribute('class').contains('female active')
    } else {
        assert completeAccountPage.genderMenWomenIcon[0].getAttribute('class').contains('male active') == false
        assert completeAccountPage.genderMenWomenIcon[1].getAttribute('class').contains('female active') == false
    }
}