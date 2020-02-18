package stepdefs.EpointsStepDefs.joinLoginSection

import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables
import mysqlConnection.jdbc

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-02-05.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

    // 1.1 //  ----------------------------------------------------------------------------------------- Forgot password
    // --------------------------------------------------------------------------------- page availability popup angular
    Given(~/^Epoints page is opened$/) { ->
        to epointsPage
        epoints = page
    }
    When(~/^User click sign in button in main navbar$/) { ->
        epoints.clickSignInButton()
    }
    When(~/^User click forgot password link$/) { ->
           epoints.signModule.clickForgotPasswordLink()
    }
    Then(~/^Forgot password page will be opened$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.forgotPasswordURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.forgotPasswordURL
    }
    Then(~/^Forgot password page has proper content$/) { ->
        waitFor{ epoints.forgotPasswordPage.pageHeader.isDisplayed() }
        assert epoints.forgotPasswordPage.pageHeader.text() == envVar.forgotPasswordHeader
        assert epoints.forgotPasswordPage.pageMainInformation.text() == envVar.forgotPasswordMainText
        assert epoints.forgotPasswordPage.emailInputFieldLabel.text() == envVar.forgotPasswordEmailLabel
        assert epoints.forgotPasswordPage.emailInputField.isDisplayed()
        assert epoints.forgotPasswordPage.sendButton.isDisplayed()
    }

    // 1.2 //  ----------------------------------------------------------------------------------------- Forgot password
    // --------------------------------------------------------------------------------- page availability modal angular
    Given(~/^Identified user open ePoints page clear$/) { ->
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
    Given(~/^Sign in modal is opened$/) { ->
        epoints.clickYourAccountButton()
    }
    Given(~/^User click forgot password link in modal window$/) { ->
        epoints.signModule.clickForgotPasswordModalLinkAngular()
    }

    // 1.3 //  ----------------------------------------------------------------------------------------- Forgot password
    // -------------------------------------------------------- check if forgot your password page returns proper alerts
    Given(~/^Epoints page is opened clear$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
    }
    Given(~/^Forgot password page is opened$/) { ->
        epoints.clickSignInButton()
        epoints.signModule.clickForgotPasswordLink()
    }
    When(~/^User try to restore password without entering email$/) { ->
        epoints.forgotPasswordPage.clickSendButton()
    }
    Then(~/^Email is required alert should appear$/) { ->
        waitFor{ epoints.forgotPasswordPage.emailIsRequiredAlert.isDisplayed() }
        assert epoints.forgotPasswordPage.emailIsRequiredAlert.text() == envVar.forgotPasswordEmailIsRequiredAlert
    }
    When(~/^User enter incorrect email$/) { ->
        epoints.forgotPasswordPage.enterEmail(envVar.testUserEmailNotExisting)
        epoints.forgotPasswordPage.clickSendButton()
    }
    Then(~/^Email does not exist alert should appear$/) { ->
        waitFor{ epoints.forgotPasswordPage.emaildoesNotExistAlert.isDisplayed() }
        assert epoints.forgotPasswordPage.emaildoesNotExistAlert.text() == envVar.forgotPasswordUserDoesNotExistsAlert
    }

    // 1.4 //  ----------------------------------------------------------------------------------------- Forgot password
    // ---------------------------------------------------- check if password restoration link is created and is working
    When(~/^User enter correct email$/) { ->
        epoints.forgotPasswordPage.enterEmail(envVar.testUserEmail)
    }
    When(~/^Click send restoration link button$/) { ->
        epoints.forgotPasswordPage.clickSendButton()
    }
    Then(~/^Success alert should be shown$/) { ->
        waitFor{ epoints.forgotPasswordPage.emailSendingSuccesAlert.isDisplayed() }
        assert epoints.forgotPasswordPage.emailSendingSuccesAlert.text() == envVar.forgotPasswordEmailSentAlert
    }
    Then(~/^Link sent on user email should works$/) { ->
        def mysql = new jdbc('points')
        Thread.sleep(2000);//has to stay
        resetPasswordLink = envVar.epointsLink+"/reset/"+mysql.get("SELECT tokenValue FROM points_manager.UserToken WHERE tokenType ='FORGOTTEN_PASSWORD' AND user_id='"+mysql.get(mysql.get("SELECT id from points_manager.User WHERE email='"+envVar.testUserEmail+"'",1),1)+"' ORDER BY createdAt DESC",1);
        System.out.println(resetPasswordLink)
        browser.go(resetPasswordLink)
        mysql.close()
    }
    Then(~/^User can create new password '(.+?)'$/) { String newPassword ->
        epoints.changePasswordPage.fillChangePasswordForm(newPassword, newPassword)
        epoints.changePasswordPage.clickChangePasswordbutton()
    }
    Then(~/^User can check that new password works '(.+?)'$/) { String newPassword->
        waitFor{ epoints.changePasswordPage.alertSuccess.isDisplayed() }
        assert epoints.changePasswordPage.alertSuccess.text() == envVar.changePasswordSuccessAlert

        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        epoints.signModule.enterLoginData(envVar.testUserEmail, newPassword)
        epoints.signModule.clickSignInButton()
        waitFor{ epoints.topNavYourAccountButton.isDisplayed() }
    }