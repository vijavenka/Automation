package com.iat.stepdefs.userAccountSection.EcardManagement

import com.iat.Config
import com.iat.domain.EcardAward
import com.iat.pages.SignInPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.userAccount.ecardsManagement.EcardIndividualPage
import com.iat.pages.userAccount.ecardsManagement.EcardsCreationPage
import com.iat.pages.userAccount.ecardsManagement.EcardsHistoryPage
import com.iat.repository.UserRepository
import com.iat.repository.impl.EcardsAwardRepositoryImpl
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static com.iat.Config.reason1Id
import static com.iat.Config.template1Id
import static cucumber.api.groovy.EN.*

def signInPage = new SignInPage()
def functions = new Functions()
def ecardsHistoryPage = new EcardsHistoryPage()
def ecardIndividualPage = new EcardIndividualPage()
def ecardsCreationPage = new EcardsCreationPage()
def homePage = new EpointsHomePage()
def browser = new Browser()
def envVariables = new envVariables()

def ecardDate
def ecardReason
def ecardFromTo
def ecardPointsValue
boolean areEpointsAvailableonEcard = false

String individualEcardLinkAuthorisedUser = envVariables.epointsLink + '/my-account/ecards/ecard/' + Config.availableEcardId
String individualEcardLinkNotAuthorisedUser = envVariables.epointsLink + '/my-account/ecards/ecard/' + Config.notAvailableEcardId

//HS-106
//Scenario: Personal ecards history - logged out user
Given(~/^Ecards history page is opened$/) { ->
    at EpointsHomePage
    homePage = page
    homePage.userDropDownMenuModule.clickOnEcardManagementOption()
    waitFor { at EcardsCreationPage }
    ecardsCreationPage = page
    ecardsCreationPage.ecardsNavigationModule.clickHistoryNavigationOption()
    ecardsCreationPage.ecardsNavigationModule.clickHistorySentLeftSideNavigationOption()
    waitFor { at EcardsHistoryPage }
    ecardsHistoryPage = page
    //sent ecards is already opened
}

Given(~/^Epoints user is signed out from epoints$/) { ->
    to EpointsHomePage
    homePage = page
    homePage.headerModule.clickOnUserNameLabel()
    homePage.userDropDownMenuModule.clickOnSignOutOption()
    browser.getDriver().navigate().refresh()
}

When(~/^He tries to access ecard history area$/) { ->
    browser.go('/my-account/ecards/history/sent')
}

Then(~/^He is asked to logged in$/) { ->
    waitFor { browser.title.equals('Sign in | epoints') }
}

Then(~/^He cannot open ecards history page$/) { ->
    assert !ecardsHistoryPage.pageBaseLocator.isDisplayed()
}
//Scenario: Personal ecards history - received ecards
When(~/^User clicks on "received" navigation option$/) { ->
    ecardsHistoryPage.ecardsNavigationModule.clickHistoryRceivedLeftSideNavigationOption()
}

Then(~/^He is presented with the interface showing received ecards$/) { ->
    waitFor { ecardsHistoryPage.personalEcardHistoryHeader.text().equals('History') }
    waitFor { ecardsHistoryPage.ecardsNavigationModule.historyRceivedLeftSideNavigationOption.hasClass('is-active') }
}

Then(~/^These ecards contain following elements: reason, points , thumbnail image, date, who to$/) { ->
    for (int i = 0; i < ecardsHistoryPage.singleEcardContainer.size(); i++) {
        assert ecardsHistoryPage.ecardImageBasic[i].isDisplayed()
        assert ecardsHistoryPage.ecardAddedDate[i].isDisplayed()
        assert ecardsHistoryPage.ecardReasonBasic[i].isDisplayed()
        assert ecardsHistoryPage.ecardFromToWhoBasic[i].isDisplayed()
        assert ecardsHistoryPage.ecardSeeEcardDetailsButtonBasic[i].isDisplayed()
    }
}

//Scenario: Personal ecards history - sent ecards
When(~/^User clicks on "sent" navigation option$/) { ->
    ecardsHistoryPage.ecardsNavigationModule.clickHistorySentLeftSideNavigationOption()
}

Then(~/^He is presented with the interface showing sent ecards$/) { ->
    waitFor { ecardsHistoryPage.personalEcardHistoryHeader.text().equals('History') }
    waitFor { ecardsHistoryPage.ecardsNavigationModule.historySentLeftSideNavigationOption.hasClass('is-active') }
}

//Scenario: Personal ecards history - individual card page link
Given(~/^He sent or received some ecards already$/) { ->
    //assumption wea are on set history page by clicking history navigation option and we have lot of ecards
}

When(~/^He clicks on ecard details link of chosen ecard$/) { ->
    ecardDate = ecardsHistoryPage.ecardAddedDate[0].text()
    ecardReason = ecardsHistoryPage.ecardReasonBasic[0].text()
    ecardFromTo = ecardsHistoryPage.ecardFromToWhoBasic[0].text()
    if (ecardsHistoryPage.ecardPointsValueBasic[0].isDisplayed()) {
        areEpointsAvailableonEcard = true
        ecardPointsValue = ecardsHistoryPage.ecardPointsValueBasic[0].text()
    }
}

Then(~/^He is re-directed to full page of chosen ecard$/) { ->
    ecardsHistoryPage.clickSeeEcardDetailsButtonOfChosenEcard(0)
    at EcardIndividualPage
}

//Scenario: Personal ecards history - show more
Given(~/^There are more than 20 ecards in the history$/) { ->
    //this is assumption and will not change, only need to be populated once
}

Given(~/^"Show more" button is available at the bottom$/) { ->
    waitFor { ecardsHistoryPage.loadMoreEcardsButton.isDisplayed() }
    assert ecardsHistoryPage.loadMoreEcardsButton.isDisplayed()
}

When(~/^user clicks on "See more" button$/) { ->
    ecardsHistoryPage.clickLoadMoreEcardsButton()
}

Then(~/^20 more cards are displayed$/) { ->
    waitFor { ecardsHistoryPage.singleEcardContainer.size() > 20 }
}

Then(~/^"See more" button is displayed if more cards available$/) { ->
    assert !ecardsHistoryPage.loadMoreEcardsButton.isDisplayed()
}

//Scenario: Personal ecards history - back to top
When(~/^He scrolls to the bottom of ecards display$/) { ->
    functions.scrolPageUpDown('down')
}

When(~/^Clicks on "Back to the top" button$/) { ->
    ecardsHistoryPage.clickBackToTopButton()
}

Then(~/^The view is scrolled to the top of the page$/) { ->
    //TODO how to check that
}

//HS-104
//Scenario: Personal ecards history - logged out user
When(~/^He clicks on the individual ecard link$/) { ->
    browser.go(individualEcardLinkAuthorisedUser)
}

Then(~/^He is re-directed to log in page$/) { ->
    at SignInPage
    signInPage = page
    waitFor { signInPage.signInForm.isDisplayed() }
    assert signInPage.signInForm.isDisplayed()
}

//Scenario: Personal ecards history - logged in user
Then(~/^He is re-directed to ecard page$/) { ->
    at EcardIndividualPage
    ecardIndividualPage = page
}

Then(~/^It shows epoints if added, message, image & reason$/) { ->
    assert ecardIndividualPage.ecardImageBasic.isDisplayed()
    assert ecardIndividualPage.ecardAddedDate.isDisplayed()
    assert ecardIndividualPage.ecardReason.isDisplayed()
    assert ecardIndividualPage.ecardFromTo.isDisplayed()
    assert ecardIndividualPage.ecardMessage.isDisplayed()
    assert ecardIndividualPage.printButton.isDisplayed()
}

//Scenario: Personal ecards history - ecard details comparison
Then(~/^Individual ecard page details match those available on history list$/) { ->
    at EcardIndividualPage
    ecardIndividualPage = page
    waitFor { ecardIndividualPage.ecardAddedDate.text().equals(ecardDate) }
    assert ecardIndividualPage.ecardAddedDate.text().equals(ecardDate)
    assert ecardIndividualPage.ecardReason.text().equals(ecardReason)
    assert ecardIndividualPage.ecardFromTo.isDisplayed() //TODO has to be specified
    if (areEpointsAvailableonEcard) {
        ecardIndividualPage.ecardPointsValue.text().equals(ecardPointsValue)
    }
}

//Scenario: Personal ecards history - unauthorised user
Given(~/^User has an ecard link$/) { ->
    // link is prepared as individualEcardLinkNotAuthorisedUser variable
}

Given(~/^He is not a sender or a recipient of an ecard$/) { ->
    // individualEcardLinkNotAuthorisedUser variable assumption
}

When(~/^He clicks on the link$/) { ->
    browser.go(individualEcardLinkNotAuthorisedUser)
}

Then(~/^The information about the ecard not being available for him is displayed$/) { ->
    at EcardIndividualPage
    ecardIndividualPage = page
    waitFor { ecardIndividualPage.errorMessage.isDisplayed() }
    assert ecardIndividualPage.errorMessage.text().equals('This ecard is not available')
}

// Scenario: Personal ecards history - recipients list popup
Given(~/^Last ecard was sent by user (.+?), (.+?) to more than 3 recipients$/) { String username, String password ->
    if (username.contains('default')) {
        username = Config.epointsUser
        password = Config.epointsUserPassword
    }
    UserRepository userRepository = new UserRepositoryImpl()
    def body = new EcardAward(reasonId: reason1Id, templateId: template1Id, message: "UI_automated_tests_message2",
            pointsValue: 75, cc: ["emailwybitnietestowy@gmail.com"],
            usersKey: [userRepository.findByEmail(Config.associatedUser3).getUuid(), userRepository.findByEmail(Config.associatedUser4).getUuid(), userRepository.findByEmail(Config.associatedUser5).getUuid(), userRepository.findByEmail(Config.associatedUser6).getUuid()])
    pointsAwardId = new EcardsAwardRepositoryImpl().setEcardsAward(body, username, password)
    browser.getDriver().navigate().refresh()
}

When(~/^He clicks on "X others" button on chosen ecard on sent history page$/) { ->
    ecardsHistoryPage.openMoreRecipientsModalOfChosenEcard(0)
}

Then(~/^The scrollable pop-up with recipients list is displayed over sent history page$/) { ->
    waitFor { ecardsHistoryPage.moreUsersModal.isDisplayed() }
    assert ecardsHistoryPage.moreUsersModalContentSingleUser.size() == 4
    assert ecardsHistoryPage.moreUsersModalCloseButton.isDisplayed()
    assert ecardsHistoryPage.moreUsersModalXButton.isDisplayed()
}

Then(~/^User can close recipients list popup of chosen history page ecard$/) { ->
    ecardsHistoryPage.clickMoreRecipientsModalCloseButton()
    waitFor { !ecardsHistoryPage.moreUsersModal.isDisplayed() }
}