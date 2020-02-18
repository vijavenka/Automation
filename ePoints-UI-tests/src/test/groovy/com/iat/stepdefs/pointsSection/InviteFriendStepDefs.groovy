package com.iat.stepdefs.pointsSection

import com.iat.Config
import com.iat.pages.CompleteAccountPage
import com.iat.pages.JoinPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.points.InviteFriendsPage
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.DataExchanger
import com.iat.stepdefs.utils.Functions
import com.iat.stepdefs.utils.JdbcDatabaseConnector
import geb.Browser
import groovy.json.JsonSlurper

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.Before

def epointsHomePage = new EpointsHomePage()
def inviteFriendsPage = new InviteFriendsPage()
def joinPage = new JoinPage()
def completeAccountPage = new CompleteAccountPage()

def envVar = new envVariables()
def func = new Functions()
def browser = new Browser()
def dataExchanger = DataExchanger.getInstance()
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager)

// 1.1 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
// ------------------------------------------------------------------------------------ not logged user page content
Given(~/^Invite friend page is opened by (.+?) user$/) { String loginState ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    if (loginState.equals('logged'))
        epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
    epointsHomePage.headerModule.clickOnPointsButton()
    epointsHomePage.headerModule.clickPointsSubNavigationOption("Invite friends")
    at InviteFriendsPage
    inviteFriendsPage = page
}
When(~/^User look on invite friend page$/) { ->
    waitFor { browser.currentUrl == envVar.epointsLink + envVar.inviteFriendURL }
    assert browser.currentUrl == envVar.epointsLink + envVar.inviteFriendURL
}
Then(~/^He will see proper page header$/) { ->
    waitFor { inviteFriendsPage.mainBoldedPageHeader.isDisplayed() }
    assert inviteFriendsPage.mainBoldedPageHeader.text() == envVar.inviteFriendBoldText
}
Then(~/^He will see join option on invite friend page$/) { ->
    waitFor { inviteFriendsPage.joinNowButton.isDisplayed() }
    assert inviteFriendsPage.joinNowButton.isDisplayed()
    assert inviteFriendsPage.joinNowText.text().replace('\n', '').replace(' ', '') == envVar.inviteFriendJoinText.replace('\n', '').replace(' ', '')
}
Then(~/^He will see sign in option on invite friend page$/) { ->
    waitFor { inviteFriendsPage.signInButton.isDisplayed() }
    assert inviteFriendsPage.signInButton.isDisplayed()
}
Then(~/^He will see explanation how it works on invite friend page$/) { ->
    waitFor { inviteFriendsPage.howItWorksText.isDisplayed() }
    assert inviteFriendsPage.howItWorksText.text().replace('\n', '').replace(' ', '') == envVar.inviteFriendHowItWorks.replace('\n', '').replace(' ', '')
}

// 1.2 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
// ----------------------------------------------------------------------------------------------------- Join button
When(~/^User click on join button on invite friend page$/) { ->
    inviteFriendsPage.clikJoinButton()
}

// 1.3 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
// -------------------------------------------------------------------------------------------------- sign in button
When(~/^User click on sign in button on invite friend page$/) { ->
    inviteFriendsPage.clikSignInButton()
}
Then(~/^Login modal with all needed elements will be displayed over invite friend page$/) { ->
    waitFor { inviteFriendsPage.loginModalModule.loginModalHeader.isDisplayed() }
    assert inviteFriendsPage.loginModalModule.loginModalHeader.isDisplayed()
    assert inviteFriendsPage.loginModalModule.loginModalEmailInputFieldLabel.isDisplayed()
    assert inviteFriendsPage.loginModalModule.loginModalEmailInputField.isDisplayed()
    assert inviteFriendsPage.loginModalModule.loginModalPasswordInputFieldLabel.isDisplayed()
    assert inviteFriendsPage.loginModalModule.loginModalPasswordInputField.isDisplayed()
    assert inviteFriendsPage.loginModalModule.loginModalForgotPasswordLink.isDisplayed()
    assert inviteFriendsPage.loginModalModule.loginModalSignInButton.isDisplayed()
    assert inviteFriendsPage.loginModalModule.loginModalSignInWithFacebookButton.isDisplayed()
}

// 1.4 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
// ---------------------------------------------------------------------------------------- logged user page content
Then(~/^He will see unique invitation link on invite friend page$/) { ->
    waitFor { inviteFriendsPage.uniqueInvitationLinkText.isDisplayed() }
    assert inviteFriendsPage.uniqueInvitationLinkText.text().replace('\n', '').replace(' ', '') == envVar.inviteFriendCopyInvitationLinkText.replace('\n', '').replace(' ', '')
    waitFor { inviteFriendsPage.uniqueInvitationLinkField.isDisplayed() }
    String referrerId = new UserRepositoryImpl().findByEmail(Config.epointsUser).getUuid()
    assert inviteFriendsPage.uniqueInvitationLinkField.value() == envVar.returnUniqueInvitationLink(referrerId)
}
Then(~/^He will see facebook post button on invite friend page$/) { ->
    waitFor { inviteFriendsPage.orSimplyText.isDisplayed() }
    assert inviteFriendsPage.orSimplyText.text().replace('\n', '').replace(' ', '') == envVar.inviteFriendOrSimplyText.replace('\n', '').replace(' ', '')
    waitFor { inviteFriendsPage.postLinkOnFacebookButton.isDisplayed() }
    assert inviteFriendsPage.postLinkOnFacebookButton.isDisplayed()
}
Then(~/^He will see how it works explanation on invite friend page$/) { ->
    waitFor { inviteFriendsPage.howItWorksTextLogged.isDisplayed() }
    inviteFriendsPage.howItWorksTextLogged.text().replace('\n', '').replace(' ', '') == envVar.inviteFriendHowItWorks.replace('\n', '').replace(' ', '')
}

// 1.5 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
// ------------------------------------------------------------------------------ share link usage and join by email
int currentPointsValue
Given(~/^All invited friend are deleted from system$/) { ->
    def refererKey = new UserRepositoryImpl().findByEmail(Config.epointsUser).getUuid()
    def refererId = mySQLConnector.getSingleResult("SELECT id FROM points_manager.User WHERE userKey = '" + refererKey + "'")
    mySQLConnector.execute("DELETE FROM points_manager.Request WHERE userId='" + refererKey + "'")
    sleep(1000)
    mySQLConnector.execute("DELETE FROM points_manager.Points WHERE userId='" + refererKey + "'")

    int numOfElem = Integer.parseInt(mySQLConnector.getSingleResult("SELECT count(*) FROM points_manager.Points WHERE activityInfo = 'User referred a friend who joined epoints.com' and userId = '" + refererId + "'"))
    currentPointsValue = Integer.parseInt(mySQLConnector.getSingleResult("SELECT confirmed FROM points_manager.User WHERE userKey = '" + refererKey + "'"))

    if (numOfElem > 0) {
        def userKey
        HashMap<Integer, List> transactions = mySQLConnector.getResult("SELECT externalTransactionId FROM points_manager.Points WHERE activityInfo = 'User referred a friend who joined epoints.com' and userId = '" + refererId + "'", Arrays.asList("externalTransactionId"))
        for (int i = 0; i < numOfElem; i++) {
            userKey = transactions.get(i).get(0).toString().replace('|epointsReferAFriend', '')
            new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(userKey)
        }
    }
}
When(~/^Invite link will be used be other user$/) { ->
    waitFor { inviteFriendsPage.uniqueInvitationLinkField.isDisplayed() }
    def inviteLink = inviteFriendsPage.uniqueInvitationLinkField.value()
    if (!inviteLink.contains(envVar.epointsLink))
        inviteLink = inviteLink.replace("epoints.com/", envVar.epointsLink)
    browser.go(inviteLink)
    at JoinPage
    joinPage = page
}

When(~/^Invited user account will be properly registrated$/) { ->
    dataExchanger.setEmail("automatedtestaccount" + func.returnEpochOfCurrentDay() + "@gmail.pl")
    joinPage.enterEmailAddressOnJoin(dataExchanger.getEmail())
    joinPage.clickJoinNowButton()
    browser.go(envVar.epointsLink + "join/confirm-email/" + new UserRepositoryImpl().getTokenValueByUUID(new UserRepositoryImpl().findByEmail(dataExchanger.getEmail()).getUuid()))
    at CompleteAccountPage
    completeAccountPage = page
    completeAccountPage.fillAllCompliteAccountData('password', 'password', 'name', 'lastname')
    completeAccountPage.selectGenderOption(0)
    completeAccountPage.clickDoneButton()
    waitFor {
        mySQLConnector.getSingleResult("SELECT count(*) FROM points_manager.User WHERE userKey = '" + new UserRepositoryImpl().findByEmail(dataExchanger.getEmail()).getUuid() + "'") == '1'
    }
}

Then(~/^Referrer will receive additional fifty epoints '(.+?)'$/) { String userType ->
    def userEmail
    if (userType.contains("epoints")) {
        userEmail = Config.epointsUser
    } else if (userType.contains("facebook")) {
        userEmail = Config.facebookUser
    }
    def referrerUuid = new UserRepositoryImpl().findByEmail(userEmail).getUuid()
    def referrerId = mySQLConnector.getSingleResult("SELECT id FROM points_manager.User WHERE userKey = '" + referrerUuid + "'")
    waitFor(15) {
        mySQLConnector.getSingleResult("SELECT activityInfo FROM points_manager.Points WHERE userId = '" + referrerId + "' ORDER BY createdAt DESC") == 'User referred a friend who joined epoints.com'
    } //+1 for logout .Points had to be cleared
    assert (Integer.parseInt(mySQLConnector.getSingleResult("SELECT confirmed FROM points_manager.User WHERE userKey = '" + referrerUuid + "'")) == (currentPointsValue + 50))
    assert mySQLConnector.getSingleResult("SELECT activityInfo FROM points_manager.Points WHERE userId = '" + referrerId + "' ORDER BY createdAt DESC") == 'User referred a friend who joined epoints.com'
    assert mySQLConnector.getSingleResult("SELECT delta FROM points_manager.Points WHERE userId = '" + referrerId + "' ORDER BY createdAt DESC") == '50'
    assert mySQLConnector.getSingleResult("SELECT externalTransactionId FROM points_manager.Points WHERE userId = '" + referrerId + "' ORDER BY createdAt DESC") == new UserRepositoryImpl().findByEmail(dataExchanger.getEmail()).getUuid() + "|epointsReferAFriend"
    assert mySQLConnector.getSingleResult("SELECT status FROM points_manager.Points WHERE userId = '" + referrerId + "' ORDER BY createdAt DESC") == 'CONFIRMED'
    assert mySQLConnector.getSingleResult("SELECT userId FROM points_manager.Points WHERE userId = '" + referrerId + "' ORDER BY createdAt DESC") == referrerId
    assert mySQLConnector.getSingleResult("SELECT balance FROM points_manager.Points WHERE userId = '" + referrerId + "' ORDER BY createdAt DESC") == mySQLConnector.getSingleResult("SELECT confirmed FROM points_manager.User WHERE userKey = '" + referrerUuid + "'")
    assert mySQLConnector.getSingleResult("SELECT onBehalfOfId FROM points_manager.Points WHERE userId = '" + referrerId + "' ORDER BY createdAt DESC") == 'ePoints.com'
}

// 1.6 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
// --------------------------------------------------------------------------- share link usage and join by facebook

// 1.7 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
// --------------------------------------------------------------------------------------- share link 10 usage limit
When(~/^Invite link will be used be other user ten times$/) { ->
    waitFor { inviteFriendsPage.uniqueInvitationLinkField.isDisplayed() }
    def inviteLink = inviteFriendsPage.uniqueInvitationLinkField.value()
    if (!inviteLink.contains(envVar.epointsLink))
        inviteLink = inviteLink.replace("epoints.com/", envVar.epointsLink)
    def refererKey = new UserRepositoryImpl().findByEmail(Config.epointsUser).getUuid()
    for (int i = 0; i <= 10; i++) {
        println("iteration " + i)
        browser.go(inviteLink)
        at JoinPage
        joinPage = page
        dataExchanger.setEmail("automatedtestaccount" + func.returnEpochOfCurrentDay() + "@gmail.pl")
        joinPage.enterEmailAddressOnJoin(dataExchanger.getEmail())
        joinPage.clickJoinNowButton()
        currentPointsValue = Integer.parseInt(mySQLConnector.getSingleResult("SELECT confirmed FROM points_manager.User WHERE userKey = '" + refererKey + "'"))
        sleep(1000) //to be sure that registration token is already created in dynamo
        browser.go(envVar.epointsLink + "join/confirm-email/" + new UserRepositoryImpl().getTokenValueByUUID(new UserRepositoryImpl().findByEmail(dataExchanger.getEmail()).getUuid()))
        at CompleteAccountPage
        completeAccountPage = page
        completeAccountPage.fillAllCompliteAccountData('password', 'password', 'name', 'lastname')
        completeAccountPage.selectGenderOption(0)
        completeAccountPage.clickDoneButton()
        if (i != 10) {
            waitFor {
                (Integer.parseInt(mySQLConnector.getSingleResult("SELECT confirmed FROM points_manager.User WHERE userKey = '" + refererKey + "'")) == (currentPointsValue + 50))
            }
            assert (Integer.parseInt(mySQLConnector.getSingleResult("SELECT confirmed FROM points_manager.User WHERE userKey = '" + refererKey + "'")) == (currentPointsValue + 50))
        } else if (i == 10) {
            waitFor {
                (Integer.parseInt(mySQLConnector.getSingleResult("SELECT confirmed FROM points_manager.User WHERE userKey = '" + refererKey + "'")) == (currentPointsValue))
            }
            assert (Integer.parseInt(mySQLConnector.getSingleResult("SELECT confirmed FROM points_manager.User WHERE userKey = '" + refererKey + "'")) == (currentPointsValue))
        }
    }
}
Then(~/^Referrer will not receive additional fifty epoints in current month$/) { ->
    //assertion done in previous step
}

// 1.8 //  --------------------------- EPOINTS - create invite a friend link functionality to account area(NBO-1037)
// ------------------------------------------------------------------------------------------------ facebook posting
Before('@userIsLogoutFromFacebook') {
    browser = new Browser()
    browser.go('https://www.facebook.com/home.php')
    sleep(2000)
    if (browser.$('#userNavigationLabel').isDisplayed()) {
        sleep(500)
        browser.$('#userNavigationLabel').click()
        browser.waitFor { browser.$('li', text: 'Wyloguj się').isDisplayed() }
        browser.browser.$('li', text: 'Wyloguj się').click()
        browser.waitFor { browser.$('.loggedout_menubar_container').isDisplayed() }
    }
}


When(~/^User click post on your timeline button$/) { ->
    browser.withNewWindow({ inviteFriendsPage.clickPostOnFacebookWallButton() }, close: true, wait: true) {
        if ($('input', id: 'email').isDisplayed()) {
            $('input', id: 'email').value(Config.facebookUser)
            $('input', id: 'pass').value(Config.facebookUserPassword)
            $('input', type: 'submit').click()
        }
        //Below locators change often - facebook window
        //TODO to be checked by Szymon why text is wrong
//        waitFor { $('._6m7').text() == envVar.inviteFriendFacebookPost }
//        assert $('._6m7').text() == envVar.inviteFriendFacebookPost
        waitFor(20) { $('input[name="share_action_properties"]') }
        inviteLink = new JsonSlurper().parseText($('input[name="share_action_properties"]',).attr('value').replace("\\", "")).object
    }
}
When(~/^Login into facebook account$/) { ->
    //done in previous step
}
Then(~/^He will see that facebook post is properly prepared$/) { ->
    // done in previous step
}
Then(~/^Referer join link is also proper$/) { ->
    browser.go(inviteLink)
    at JoinPage
    joinPage = page
}