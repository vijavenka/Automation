package com.iat.stepdefs.joinLoginSection

import com.iat.Config
import com.iat.pages.JoinConfirmationPage
import com.iat.pages.JoinFinishedPage
import com.iat.pages.JoinPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.home.footerStaticPages.AboutEpointsPage
import com.iat.pages.home.footerStaticPages.CookiePolicyPage
import com.iat.pages.home.footerStaticPages.PrivacyPolicyPage
import com.iat.pages.home.footerStaticPages.TandCPage
import com.iat.pages.points.PointsPage
import com.iat.pages.rewards.EpointsRewardsPage
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.DataExchanger
import com.iat.stepdefs.utils.Functions
import com.iat.stepdefs.utils.JdbcDatabaseConnector
import geb.Browser

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

def epointsHomePage = new EpointsHomePage()
def joinPage = new JoinPage()
def joinConfirmationPage = new JoinConfirmationPage()
def pointsPage = new PointsPage()
def aboutEpointsPage = new AboutEpointsPage()
JoinFinishedPage joinFinishedPage = new JoinFinishedPage()

def func = new Functions()
def envVar = new envVariables()
def browser = new Browser()
def dataExchanger = DataExchanger.getInstance()
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager)

String newAccountEmailAddress

// 1.1 //  ------------------------------------------------------------------------------------ Joining into epoints
// ---------------------------------------------------------------------------------------------------- page content
Given(~/^Not registered user open epoints page$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
}
When(~/^User click join now button in navbar$/) { ->
    epointsHomePage.headerModule.clickOnJoinButton()
}
Then(~/^Join page will be opened$/) { ->
    at JoinPage
    joinPage = page
}
Then(~/^Join page has proper content$/) { ->
    waitFor { joinPage.joinEpointsHeader.isDisplayed() }
    assertThat("Missing or wrong join page header", joinPage.joinEpointsHeader.text(), is(envVar.joinHeader))
    assertThat("Missing or wrong join page main info", joinPage.joinEpointsMainText.text(), is(envVar.joinMainText))
    assertThat("Wrong email input label", joinPage.emailInputFieldLabel.text(), is(envVar.joinEmailAddressLabel))
    assertThat("Missing email input field", joinPage.emailInputFieldOnJoin.isDisplayed())
    assertThat("Missing join now button", joinPage.joinNowButtonOnJoin.isDisplayed())
    assertThat("Missing join by facebook button", joinPage.joinViaFacebookButtonOnJoin.isDisplayed())
    assertThat("Missing tell me more link", joinPage.tellMeMoreLinkOnJoin.isDisplayed())
    assertThat("Missing or wrong opt in text", joinPage.optInInfo.text(), is("By clicking Create Account, you agree to our Terms and confirm that you have read our Privacy Policy, including our Cookie Policy, and receiving marketing communication emails from us. This setting can be updated in your account area after you have joined"))
    assertThat("Missing opt in cookie policy link", joinPage.optInCookiePolicyLink.isDisplayed())
    assertThat("Missing opt in privacy policy link", joinPage.optInPrivacyPolicyLink.isDisplayed())
    assertThat("Missing opt in terms and condition link", joinPage.optInTandCLink.isDisplayed())
}

// 1.2 //  ------------------------------------------------------------------------------------ Joining into epoints
// ----------------------------------------------------------------------------------------------- tell me more link
Given(~/^Join page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.goToJoinPage()
    at JoinPage
    joinPage = page
}
When(~/^User click tell me more link$/) { ->
    joinPage.clickTellMeMoreLink()
}
Then(~/^Modal window with proper information will be displayed$/) { ->
    waitFor { joinPage.tellMeMoreModalWindow.isDisplayed() }
    assert joinPage.tellMeMoreCloseButton.isDisplayed()
    assert joinPage.tellMeMoreXButton.isDisplayed()
    assert joinPage.tellMeMoreModalWindowText.text().replace("\n", "") == envVar.joinTellMeMoreModalText
}
When(~/^User click close button on tell me more modal$/) { ->
    joinPage.clickCloseButtonInTellMeMoreWindow()
}
Then(~/^Tell me more modal will be closed$/) { ->
    waitFor { !joinPage.tellMeMoreModalWindow.isDisplayed() }
    assert !joinPage.tellMeMoreModalWindow.isDisplayed()
}

// 1.3 //  ------------------------------------------------------------------------------------ Joining into epoints
// ---------------------------------------------------------------------------- checking mandatory fields validation
When(~/^User delete previously entered email$/) { ->
    joinPage.enterEmailAddressOnJoin('')
}
Then(~/^Email address is required message will be displayed$/) { ->
    waitFor { joinPage.emailAlertOnJoin.isDisplayed() }
    assert joinPage.emailAlertOnJoin.text() == envVar.joinEmailAddressIsRequiredAlert
}

// 1.4 //  ------------------------------------------------------------------------------------ Joining into epoints
// ------------------------------------------------------------------------------- checking fields length validation
String longEmailAddress = 'q1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e325@wp.pl'
Given(~/^User can enter email consisted of (\d+) signs$/) { int signsNumber ->
    joinPage.enterEmailAddressOnJoin(longEmailAddress)
    waitFor { joinPage.emailInputFieldOnJoin.value() == longEmailAddress }
    assert joinPage.emailInputFieldOnJoin.value() == longEmailAddress
}
When(~/^User try to add more signs to email$/) { ->
    joinPage.enterEmailAddressOnJoin(longEmailAddress + 'a')
}
Then(~/^It will be impossible$/) { ->
    waitFor { joinPage.emailInputFieldOnJoin.value() == longEmailAddress }
    assert joinPage.emailInputFieldOnJoin.value() == longEmailAddress
}

// 1.5 //  ------------------------------------------------------------------------------------ Joining into epoints
// ------------------------------------------------------------------------ checking validation for duplicated email
When(~/^User enter email which is already used$/) { ->
    joinPage.enterEmailAddressOnJoin(Config.epointsUser)
}
When(~/^User click join now button on join page$/) { ->
    joinPage.clickJoinNowButton()
}
Then(~/^Username already taken message will be displayed$/) { ->
    waitFor { joinPage.emailErrorOnJoin.isDisplayed() }
    assert joinPage.emailErrorOnJoin.text() == envVar.joinUsernameAlreadyTakenError
}

// 1.6 //  ------------------------------------------------------------------------------------ Joining into epoints
// ----------------------------------------------------------------- checking validation for incorrect email address
When(~/^User enter invalid email address$/) { ->
    joinPage.enterEmailAddressOnJoin(envVar.testUserEmailInvalid)
}
Then(~/^Email address is invalid message will be shown$/) { ->
    waitFor { joinPage.emailAlertOnJoin }
    assert joinPage.emailAlertOnJoin.text() == envVar.joinEmailAddressIsInvalidAlert
}

// 1.7 //  ------------------------------------------------------------------------------------ Joining into epoints
// ---------------------------------------------------------------------------------- checking if account is created
When(~/^User enter new email address '(.+?)'$/) { String email ->
    newAccountEmailAddress = func.returnEpochOfCurrentDay()
    if (email == "random")
        newAccountEmailAddress = "newautomatedtestaccount$newAccountEmailAddress@gmail.com"
    else if (email == "associatedUser1")
        newAccountEmailAddress = Config.epointsUser
    else
        newAccountEmailAddress = email
    dataExchanger.setEmail(newAccountEmailAddress)
    joinPage.enterEmailAddressOnJoin(newAccountEmailAddress)
}
Then(~/^Email submitted page should be opened$/) { ->
    at JoinConfirmationPage
    joinConfirmationPage = page
}
Then(~/^New account will be created$/) { ->
    waitFor { joinConfirmationPage.headerModule.headerUserNameLabel.text().contains(newAccountEmailAddress) }
    assert joinConfirmationPage.headerModule.headerUserNameLabel.text().contains(newAccountEmailAddress)
}
Then(~/^On new account should be (.+?) points$/) { String pointsValue ->
    at JoinConfirmationPage
    joinConfirmationPage = page
    waitFor { joinConfirmationPage.headerModule.headerPointsBalanceConfirmed.text().contains(pointsValue) }
    assert joinConfirmationPage.headerModule.headerPointsBalanceConfirmed.text().contains(pointsValue)
}

// 1.8 //  ------------------------------------------------------------------------------------ Joining into epoints
// -------------------------------------------------------------- checking sign out of non verified account/sign out
Given(~/^New account is created$/) { ->
    newAccountEmailAddress = 'newautomatedtestaccount' + func.returnEpochOfCurrentDay() + '@gmail.com'
    joinPage.enterEmailAddressOnJoin(newAccountEmailAddress)
    joinPage.clickJoinNowButton()
    at JoinConfirmationPage
    joinConfirmationPage = page
    waitFor { joinConfirmationPage.headerModule.headerUserNameLabel.text().contains(newAccountEmailAddress) }
}
When(~/^User click sign out button before account will be verified$/) { ->
    epointsHomePage.headerModule.clickOnUserNameLabel()
    epointsHomePage.userDropDownMenuModule.clickOnSignOutOption()
}
Then(~/^Modal warning window will be opened$/) { ->
    waitFor { joinPage.notVerifiedSignOutModalAngular.isDisplayed() }
}
Then(~/^Modal warning window has proper content$/) { ->
    assert joinPage.notVerifiedSignOutModalAngular.text().replace("\n", " ") == envVar.returnJoinSignOutModalTextAngular(newAccountEmailAddress)
    assert joinPage.notVerifiedSignOutModalResendEmailLinkAngular.isDisplayed()
    assert joinPage.notVerifiedSignOutModalOkButtonAngular.isDisplayed()
    assert joinPage.notVerifiedSignOutModalCancelButtonAngular.isDisplayed()
}
When(~/^User click ok sign out button on modal warning window$/) { ->
    joinPage.clickOkSignOutButtonAngular()
}
Then(~/^He will be properly sign out from created account$/) { ->
    waitFor { epointsHomePage.headerModule.headerSignInButton.isDisplayed() }
    epointsHomePage.headerModule.headerSignInButton.isDisplayed()
}

// 1.9 //  ------------------------------------------------------------------------------------ Joining into epoints
// ----------------------------------------------------------- checking sign out of non verified account/resend link
When(~/^User click resend confirmation link on warning modal$/) { ->
    joinPage.clickResendConfirmationEmailInWarningModal()
}
Then(~/^Confirmation link will be sent ane success message displayed$/) { ->
    waitFor { joinPage.notVerifiedSignOutModalSuccessAlert.isDisplayed() }
    assert joinPage.notVerifiedSignOutModalSuccessAlert.isDisplayed()
}

// 1.10 //  ----------------------------------------------------------------------------------- Joining into epoints
// ---------------------------------------------------------------------- Join here button on sig in popup - angular
Given(~/^Sign in popup is displayed$/) { ->
    epointsHomePage.headerModule.clickOnSignInButton()
}
When(~/^User click join here link on sign in popup$/) { ->
    epointsHomePage.signInModule.clickJoinHereLink()
}
Then(~/^He will be redirected to join page$/) { ->
    at JoinPage
    joinPage = page
}

// 1.11 //  ----------------------------------------------------------------------------------- Joining into epoints
// ---------------------------------------------------------------- resend email confirmation/epoints sections links
When(~/^User click resend confirmation link$/) { ->
    joinConfirmationPage.clickResendConfirmationEmail()
}
Then(~/^Resend confirmation link success alert will be displayed$/) { ->
    waitFor { joinConfirmationPage.resendEmailLinkSuccessAlert.isDisplayed() }
    assert joinConfirmationPage.resendEmailLinkSuccessAlert.text() == envVar.resendEmailConfirmationSuccessAlert
}
Then(~/^Bottom epoints links works properly$/) { ->
    joinConfirmationPage.clickGetEpointsLinkOnJoinOKPage()
    at PointsPage
    pointsPage = page
    browser.getDriver().navigate().back()
    at JoinConfirmationPage
    joinConfirmationPage = page
    joinConfirmationPage.clickSpendEpointsLinkOnJoinOKPage()
    at EpointsRewardsPage
    epointsHomePage = page
    browser.getDriver().navigate().back()
    at JoinConfirmationPage
    joinConfirmationPage = page
    joinConfirmationPage.clickLearnMoreLinkOnJoinOKPage()
    at AboutEpointsPage
    aboutEpointsPage = page
}

// 1.12 //  ----------------------------------------------------------------------------------- Joining into epoints
// --------------------------------------------------------------------------------------------- joining by facebook
Given(~/^Proper user is deleted from points manager (.+?)$/) { String email ->
    if (!(new UserRepositoryImpl().findByEmail(email).getUuid() == null)) {
        new UserRepositoryImpl().deletUserFromDynamoAndPointsManager(new UserRepositoryImpl().findByEmail(email).getUuid())
    }
}
When(~/^User use join by facebook option with correct data$/) { ->
    joinPage = page

    def email = Config.facebookUser
    def password = Config.facebookUserPassword

    dataExchanger.setEmail(email)
    browser.withNewWindow({ joinPage.clickJoinByFacebookButton() }, close: false, wait: true) {
        waitFor(20) { $('input', id: 'email').isDisplayed() }
        $('input', id: 'email').value(email)
        $('input', id: 'pass').value(password)
        $('input', type: 'submit').click()
    }
    at JoinFinishedPage
    joinFinishedPage = page
}
Then(~/^New epoints facebook account will be properly created '(.+?)' '(.+?)'$/) { String email, String name ->
    waitFor { joinFinishedPage.headerModule.headerUserNameLabel.text().contains(name) }
    assert joinFinishedPage.headerModule.headerUserNameLabel.text().contains(name)
    waitFor {
        mySQLConnector.getSingleResult("SELECT count(*) FROM points_manager.User WHERE userKey = '" + new UserRepositoryImpl().findByEmail(email).getUuid() + "'") == '1'
    }
    assert mySQLConnector.getSingleResult("SELECT count(*) FROM points_manager.User WHERE userKey = '" + new UserRepositoryImpl().findByEmail(email).getUuid() + "'") == '1'
}
Then(~/^On new account should be (.+?) points '(.+?)'$/) { String pointsValue, String email ->
    waitFor(20){ mySQLConnector.getSingleResult("SELECT confirmed FROM points_manager.User WHERE userKey = '" + new UserRepositoryImpl().findByEmail(email).getUuid() + "'") == pointsValue }
    assert mySQLConnector.getSingleResult("SELECT confirmed FROM points_manager.User WHERE userKey = '" + new UserRepositoryImpl().findByEmail(email).getUuid() + "'") == pointsValue
    browser.getDriver().navigate().refresh() // hook for possibility of processing points award for login to late
    waitFor { joinFinishedPage.headerModule.headerPointsBalanceConfirmed.text() == pointsValue }
    assert joinFinishedPage.headerModule.headerPointsBalanceConfirmed.text() == pointsValue
}
Then(~/^All data stored in user account section will be properly downloaded from facebook account '(.+?)' '(.+?)' '(.+?)' '(.+?)' '(.+?)' '(.+?)'$/) { String name, String lastName, String gender, String dateOfBirth, String email, String datOfBirthDBFormat ->
    //TODO finish it when account area will be ready
}

Then(~/^Opt in cookie policy link redirect user to cookie policy page$/) { ->
    browser.withNewWindow({ joinPage.clickOptInCookiePolicyLink() }, close: true, wait: true) {
        at CookiePolicyPage
    }
}

And(~/^Opt in privacy policy link redirect user to privacy policy page$/) { ->
    browser.withNewWindow({ joinPage.clickOptInPrivacyPolicyLink() }, close: true, wait: true) {
        at PrivacyPolicyPage
    }
}

And(~/^Opt in term and condition policy link redirect user to terms and conditions page$/) { ->
    browser.withNewWindow({ joinPage.clickOptInTandCLink() }, close: true, wait: true) {
        at TandCPage
    }
}