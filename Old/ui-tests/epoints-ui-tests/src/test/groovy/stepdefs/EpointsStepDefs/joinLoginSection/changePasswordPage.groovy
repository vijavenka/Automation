package stepdefs.EpointsStepDefs.joinLoginSection

import geb.Browser
import mysqlConnection.jdbc
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-02-06.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

    // 1.1 //  ------------------------------------------------------------------------------------ Change password page
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Change password page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.clickSignInButton()
        epoints.signModule.clickForgotPasswordLink()
        epoints.forgotPasswordPage.enterEmail(envVar.testUserEmail)
        epoints.forgotPasswordPage.clickSendButton()
        Thread.sleep(2000)
        def mysql = new jdbc('points')
            resetPasswordLink = envVar.epointsLink+"reset/"+mysql.get("SELECT tokenValue FROM points_manager.UserToken WHERE tokenType ='FORGOTTEN_PASSWORD' AND user_id='"+mysql.get(mysql.get("SELECT id from points_manager.User WHERE email='"+envVar.testUserEmail+"'",1),1)+"' ORDER BY createdAt DESC",1);
            browser.go(resetPasswordLink)
        mysql.close()
        waitFor{ browser.currentUrl.contains(envVar.epointsLink + envVar.changePasswordURL) }
        assert browser.currentUrl.contains(envVar.epointsLink + envVar.changePasswordURL)
        assert browser.title == envVar.changePasswordTag
    }
    When(~/^User look on change password page$/) { ->
        //leave empty
    }
    Then(~/^He can see that it contains all needed elements$/) { ->
        waitFor{ epoints.changePasswordPage.pageHeader.text() == envVar.changePasswordHeader }
        assert epoints.changePasswordPage.pageHeader.text() == envVar.changePasswordHeader
        assert epoints.changePasswordPage.labelPassword.text() == envVar.changePasswordPasswordLabel
        assert epoints.changePasswordPage.labelConfirmPassword.text() == envVar.changePasswordConfirmPasswordLabel
        assert epoints.changePasswordPage.passwordInputField.isDisplayed()
        assert epoints.changePasswordPage.confirmPasswordInputField.isDisplayed()
        assert epoints.changePasswordPage.changeButton.isDisplayed()
        assert !epoints.changePasswordPage.passwordStrenghtElement.isDisplayed()
    }

    // 1.2 //  ------------------------------------------------------------------------------------ Change password page
    // -------------------------------------------------------------------------------------------- no input data alerts
    When(~/^User click change button without filling input fields$/) { ->
        epoints.changePasswordPage.clickChangePasswordbutton()
    }
    Then(~/^Password is required and passwords do not match alerts will be displayed$/) { ->
        waitFor{ epoints.changePasswordPage.passwordAlert.isDisplayed() }
        waitFor{ epoints.changePasswordPage.passwordConfirmationAlert.isDisplayed() }
        assert epoints.changePasswordPage.passwordAlert.text() == envVar.changePasswordisRequiredAlert
        assert epoints.changePasswordPage.passwordConfirmationAlert.text() == envVar.changePasswordDoNotMatchAlert
    }

    // 1.3 //  ------------------------------------------------------------------------------------ Change password page
    // ----------------------------------------------------------------------------------- password do notch match alert
    When(~/^Enter two different passwords into input fields$/) { ->
        epoints.changePasswordPage.fillChangePasswordForm('password1','password2')
    }
    When(~/^Click change password button$/) { ->
        epoints.changePasswordPage.clickChangePasswordbutton()
    }
    Then(~/^Then passwords do not match alerts will be displayed$/) { ->
        waitFor{ epoints.changePasswordPage.passwordAlert.isDisplayed() }
        assert epoints.changePasswordPage.passwordAlert.text() == envVar.changePasswordDoNotMatchAlert
    }
    Then(~/^Password strenght element will be displayed$/) { ->
        waitFor{ epoints.changePasswordPage.passwordStrenghtElement.isDisplayed() }
        assert epoints.changePasswordPage.passwordStrenghtElement.isDisplayed()
    }

    // 1.4 //  ------------------------------------------------------------------------------------ Change password page
    // --------------------------------------------------------------------------------------------------- invalid token
    Given(~/^Change password page is opened with expired token$/) { ->
        to epointsPage
        epoints = page
            resetPasswordLink = envVar.epointsLink+"reset/"+"231vh3h24vhv34h213v42hj4vb23hj4v12"
        browser.go(resetPasswordLink)
    }
    Then(~/^He will see confirmation failed message$/) { ->
        epoints = page
        waitFor{ epoints.changePasswordPage.pageSectionText.isDisplayed() }
        assert epoints.changePasswordPage.pageSectionText.text().replace("\n","") == envVar.changePasswordInvalidToken
    }