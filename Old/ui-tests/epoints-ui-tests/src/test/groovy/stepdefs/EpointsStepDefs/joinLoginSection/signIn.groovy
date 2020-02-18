package stepdefs.EpointsStepDefs.joinLoginSection
import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*
/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 18.09.14
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */

def epoints = new epointsPage()
def func = new Functions()
def envVar = new envVariables()
def browser = new Browser()

    // 1.1 //  -------------------------------------------------------------------------------------- Login into epoints
    // ------------------------------------------------------------------------------------------- sign in panel content
    Given(~/^Not registered user open ePoints\.com$/) { ->
        to epointsPage
        epoints = page
    }
    When(~/^User click in sign in option$/) { ->
        epoints.clickSignInButton()
    }
    Then(~/^Authentication panel is shown$/) { ->
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        assert epoints.signModule.authenticationPanel.isDisplayed()
    }
    Then(~/^Email label is available$/) { ->
        waitFor{ epoints.signModule.emailInputFieldLabel.isDisplayed() }
        assert epoints.signModule.emailInputFieldLabel.text() == envVar.signInPanelEmailLabel
    }
    Then(~/^Email input field is available$/) { ->
        waitFor{ epoints.signModule.emailInputField.isDisplayed() }
        assert epoints.signModule.emailInputField.attr('placeholder') == envVar.signInPanelEmailPlaceholder
    }
    Then(~/^Password label is available$/) { ->
        waitFor{ epoints.signModule.passwordInputFieldLabel.isDisplayed() }
        assert epoints.signModule.passwordInputFieldLabel.text() == envVar.signInPanelPasswordLabel
    }
    Then(~/^Password input field is available$/) { ->
        waitFor { epoints.signModule.passwordInputField.isDisplayed() }
        assert epoints.signModule.passwordInputField.attr('placeholder') == envVar.signInPanelPasswordPlaceholder
    }
    Then(~/^Forgot password link is available$/) { ->
        waitFor{ epoints.signModule.forgotPasswordLink.isDisplayed() }
        assert epoints.signModule.forgotPasswordLink.isDisplayed()
    }
    Then(~/^Sign In button is available$/) { ->
        waitFor{ epoints.signModule.signInButton.isDisplayed() }
        assert epoints.signModule.signInButton.isDisplayed()
    }
    Then(~/^Join here Link is available$/) { ->
        waitFor{ epoints.signModule.joinHereLink.isDisplayed() }
        assert epoints.signModule.joinHereLink.isDisplayed()
    }
    Then(~/^Sign in using facebook button is available$/) { ->
        waitFor{ epoints.signModule.signInWithFacebookButton.isDisplayed() }
        assert epoints.signModule.signInWithFacebookButton.text() == envVar.signInPanelFacebookButonPlaceholder
    }
    Then(~/^Close Link is available$/) { ->
        waitFor{ epoints.signModule.closeButton.isDisplayed() }
        assert epoints.signModule.closeButton.isDisplayed()
    }

    // 1.2 //  -------------------------------------------------------------------------------------- Login into epoints
    // ------------------------------------------------------------------------------------------ required fields alerts
    Given(~/^Sign in panel is opened$/) { ->
        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        assert epoints.signModule.authenticationPanel.isDisplayed()
    }
    When(~/^User enter some phrases into input fields and delete them$/) { ->
        epoints.signModule.enterLoginData('email','password')
        epoints.signModule.enterLoginData('','')
    }
    Then(~/^Fields should be marked$/) { ->
        //leave empty
    }
    Then(~/^Fields required alerts will be displayed$/) { ->
        waitFor{ epoints.signModule.errorBasic.isDisplayed() }
        waitFor{ epoints.signModule.errorBasic.size() == 2 }
        assert epoints.signModule.errorBasic[0].text() == envVar.signInPanelEmailRequiredAlert
        assert epoints.signModule.errorBasic[1].text() == envVar.signInPanelPasswordRequiredAlert
    }

    // 1.3 //  -------------------------------------------------------------------------------------- Login into epoints
    // ------------------------------------------------------------------------------- sign in into not existing account
    When(~/^User fill email address using email which is not in the database$/) { ->
        epoints.signModule.enterEmailAddress(envVar.testUserEmailNotExisting)
    }
    When(~/^Fill Password field with random data$/) { ->
        epoints.signModule.enterPassword(func.returnRandomValue(1000000))
    }
    When(~/^Click On sign in button$/) { ->
        epoints.signModule.clickSignInButton()
    }
    Then(~/^Warning authentication system error should shown$/) { ->
        waitFor{  epoints.signModule.errorDanger.isDisplayed() }
        assert epoints.signModule.errorDanger.text() == envVar.signInPanelAuthorizationFailed
    }

    // 1.4 //  -------------------------------------------------------------------------------------- Login into epoints
    // ------------------------------------------------------------ sign in into existing account which is not activated
    When(~/^User fill email address using email which is not activated$/) { ->
        epoints.signModule.enterEmailAddress(envVar.testUserEmailNotActivated)
    }

    // 1.5 //  -------------------------------------------------------------------------------------- Login into epoints
    // -------------------------------------------------------------- sign in into existing account with random password
    When(~/^User enter correct email address$/) { ->
        epoints.signModule.enterEmailAddress(envVar.testUserEmail)
    }

    // 1.6 //  -------------------------------------------------------------------------------------- Login into epoints
    // ------------------------------------------------------------------------------------------------- correct sign in
    When(~/^User fill correct data into sign in form$/) { ->
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
    }
    Then(~/^He will be correctly logged in$/) { ->
        waitFor{ !epoints.topNavSignInButton.isDisplayed() }
        assert !epoints.topNavSignInButton.isDisplayed()
    }
    Then(~/^Points counter will be displayed$/) { ->
        waitFor{ epoints.yourEpointsCounter.isDisplayed() }
        waitFor{ epoints.pendingEpointsCounter.isDisplayed() }
        assert epoints.yourEpointsCounter.isDisplayed()
        assert epoints.pendingEpointsCounter.isDisplayed()
    }
    Then(~/^Navigation options for logged user will be displayed$/) { ->
        waitFor{ epoints.topNavHiUsername.isDisplayed() }
        waitFor{ epoints.topNavYourAccountButton.isDisplayed() }
        waitFor{ epoints.topnavSignOutButton.isDisplayed() }
        assert epoints.topNavHiUsername.text() == 'Hi ' + envVar.testUserName
        assert epoints.topNavYourAccountButton.isDisplayed()
        assert epoints.topnavSignOutButton.isDisplayed()

    }
    Then(~/^Join modules will be removed from home page$/) { ->
        waitFor{ !epoints.joinInputField.isDisplayed() }
        assert !epoints.joinInputField.isDisplayed()
        assert !epoints.joinForFreeButton.isDisplayed()
        assert !epoints.signInWithFacebookButton.isDisplayed()
        assert !epoints.get50EpointsInformation.isDisplayed()
    }

    // 1.7 //  -------------------------------------------------------------------------------------- Login into epoints
    // ------------------------------------------------------- checking if submit redemption order offer panel to log in
    Given(~/^Not registered user open ePoints\.com clear$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
    }
    Given(~/^Basket selected rewards section is opened with one product in it$/) { ->
        epoints.clickSpendButton()
        waitFor{ epoints.spendPage.departmentCardBasic.size() == 12 }
        random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
        while(epoints.spendPage.departmentCardBasic[random].hasClass('is-disabled')){
            random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
        }
        epoints.spendPage.clickChosenDepartmentCard(random)
        epoints.browseRewardsPage.addToBasketChosenRedemption(0)
        epoints.basket.openBasketPreview()
        epoints.basket.clickViewSelectedRewardButton()

    }
    When(~/^User click order reward button$/) { ->
        epoints.basket.clickOrderRewardButton()
    }
    Then(~/^Authentication modal panel should be shown$/) { ->
        epoints = page // for external test usage
        waitFor{ epoints.signModule.loginModalAngular.isDisplayed() }
        assert epoints.signModule.loginModalAngular.isDisplayed()
    }
    Then(~/^Authentication modal panel should have all needed elements$/) { ->
        waitFor{ epoints.signModule.loginModalHeaderAngular.isDisplayed() }
        assert epoints.signModule.loginModalHeaderAngular.isDisplayed()
        assert epoints.signModule.loginModalEmailInputFieldLabelAngular.isDisplayed()
        assert epoints.signModule.loginModalEmailInputFieldAngular.isDisplayed()
        assert epoints.signModule.loginModalPasswordInputFieldLabelAngular.isDisplayed()
        assert epoints.signModule.loginModalPasswordInputFieldAngular.isDisplayed()
        assert epoints.signModule.loginModalForgotPasswordLinkAngular.isDisplayed()
        assert epoints.signModule.loginModalSignInButtonAngular.isDisplayed()
        assert epoints.signModule.loginModalJoinHereLinkAngular.isDisplayed()
        assert epoints.signModule.loginModalSignInWithFacebookButtonAngular.isDisplayed()
        assert epoints.signModule.loginModalCloseButtonAngular.isDisplayed()
    }

    // 1.8 //  -------------------------------------------------------------------------------------- Login into epoints
    // ------------------------------------------------------------------------------------------------- sign out option
    Given(~/^Registered user open ePoints\.com clear$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
        epoints.signModule.clickSignInButton()
        waitFor{ epoints.topnavSignOutButton.isDisplayed() }
    }
    When(~/^User click on sign out button$/) { ->
        epoints.clikcSignOutButton()
    }
    Then(~/^He will be correctly logged out$/) { ->
        waitFor{ epoints.topNavSignInButton.isDisplayed() }
        assert epoints.topNavSignInButton.isDisplayed()
    }
    Then(~/^Points counter will not be displayed$/) { ->
        waitFor{ !epoints.yourEpointsCounter.isDisplayed() }
        waitFor{ !epoints.pendingEpointsCounter.isDisplayed() }
        assert !epoints.yourEpointsCounter.isDisplayed()
        assert !epoints.pendingEpointsCounter.isDisplayed()
    }
    Then(~/^Navigation options for not logged user will be displayed$/) { ->
        waitFor{ epoints.topNavJoinNowButton.isDisplayed() }
        waitFor{ epoints.topNavSignInButton.isDisplayed() }
        assert epoints.topNavJoinNowButton.isDisplayed()
        assert epoints.topNavSignInButton.isDisplayed()
    }
    Then(~/^Join modules will be displayed on home page$/) { ->
        waitFor{ epoints.joinInputField.isDisplayed() }
        assert epoints.joinInputField.isDisplayed()
        assert epoints.joinForFreeButton.isDisplayed()
        assert epoints.signInWithFacebookButton.isDisplayed()
        assert epoints.get50EpointsInformation.isDisplayed()
    }

    // 1.9 //  -------------------------------------------------------------------------------------- Login into epoints
    // ------------------------------------------------- sign in when user is provide by cookie and is not authenticated
    Given(~/^Identified user open ePoints\.com clear$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
        epoints.signModule.clickSignInButton()
        waitFor{ epoints.topNavYourAccountButton.isDisplayed() }
        func.clearParticularCookieAndRefreshPage('JSESSIONID')
    }
    When(~/^User click your account button$/) { ->
        epoints.clickYourAccountButton()
    }
    Then(~/^Login modal with all needed elements will be displayed$/) { ->
        epoints = page
        waitFor{ epoints.signModule.loginModalHeaderAngular.isDisplayed() }
        assert epoints.signModule.loginModalHeaderAngular.isDisplayed()
        assert epoints.signModule.loginModalEmailInputFieldLabelAngular.isDisplayed()
        assert epoints.signModule.loginModalEmailInputFieldAngular.isDisplayed()
        assert epoints.signModule.loginModalPasswordInputFieldLabelAngular.isDisplayed()
        assert epoints.signModule.loginModalPasswordInputFieldAngular.isDisplayed()
        assert epoints.signModule.loginModalForgotPasswordLinkAngular.isDisplayed()
        assert epoints.signModule.loginModalSignInButtonAngular.isDisplayed()
        assert epoints.signModule.loginModalJoinHereLinkAngular.isDisplayed()
        assert epoints.signModule.loginModalSignInWithFacebookButtonAngular.isDisplayed()
        assert epoints.signModule.loginModalCloseButtonAngular.isDisplayed()
    }
    When(~/^User fill correct data into sign in modal$/) { ->
        epoints.signModule.enterLoginDataModalAngular(envVar.testUserEmail, envVar.testUserPassword)
    }
    When(~/^Click on sign in button in modal window$/) { ->
        epoints.signModule.clickSignInButtonModalAngular()
        Thread.sleep(2000)
    }
    Then(~/^User will be redirected to your account area$/) { ->
        epoints.clickYourAccountButton()
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.myAccountURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.myAccountURL
    }