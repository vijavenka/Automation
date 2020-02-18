package stepdefs.EpointsStepDefs.joinLoginSection

import geb.Browser
import mysqlConnection.jdbc
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-02-02.
 */

def epoints = new epointsPage()
def func = new Functions()
def envVar = new envVariables()
def browser = new Browser()

    // 1.1 //  ------------------------------------------------------------------------------------ Joining into epoints
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Not registered user open epoints page$/) { ->
        to epointsPage
        epoints = page
    }
    Given(~/^Not registered user open epoints page clear$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
    }
    When(~/^User click join now button in navbar$/) { ->
        epoints.clickJoinNowButton()
    }
    Then(~/^Join page will be opened$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.joinURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.joinURL
    }
    Then(~/^Join page has proper content$/) { ->
        waitFor{ epoints.joinModule.joinEpointsHeader.isDisplayed() }
        assert epoints.joinModule.joinEpointsHeader.text() == envVar.joinHeader
        assert epoints.joinModule.joinEpointsMainText.text() == envVar.joinMainText
        assert epoints.joinModule.emailInputFieldLabel.text() == envVar.joinEmailAddressLabel
        assert epoints.joinModule.emailInputFieldOnJoin.isDisplayed()
        assert epoints.joinModule.joinNowButtonOnJoin.isDisplayed()
        assert epoints.joinModule.joinViaFacebookButtonOnJoin.isDisplayed()
        assert epoints.joinModule.tellMeMoreLinkOnJoin.isDisplayed()
    }

    // 1.2 //  ------------------------------------------------------------------------------------ Joining into epoints
    // ----------------------------------------------------------------------------------------------- tell me more link
    Given(~/^Join page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.clickJoinNowButton()
    }
    When(~/^User click tell me more link$/) { ->
        epoints.joinModule.clickTellMeMoreLink()
    }
    Then(~/^Modal window with proper information will be displayed$/) { ->
        waitFor{ epoints.joinModule.tellMeMoreModalWindow.isDisplayed() }
        assert epoints.joinModule.tellMeMoreCloseButton.isDisplayed()
        assert epoints.joinModule.tellMeMoreXButton.isDisplayed()
        assert epoints.joinModule.tellMeMoreModalWindowText.text().replace("\n", "") == envVar.joinTellMeMoreModalText
    }
    When(~/^User click close button on tell me more modal$/) { ->
        epoints.joinModule.clickCloseButtonInTellMeMoreWindow()
    }
    Then(~/^Tell me more modal will be closed$/) { ->
        waitFor{ !epoints.joinModule.tellMeMoreModalWindow.isDisplayed() }
        assert !epoints.joinModule.tellMeMoreModalWindow.isDisplayed()
    }

    // 1.3 //  ------------------------------------------------------------------------------------ Joining into epoints
    // ---------------------------------------------------------------------------- checking mandatory fields validation
    When(~/^Email field will be not populated$/) { ->
        //leave empty
    }
    When(~/^User click join now button on join page$/) { ->
        epoints.joinModule.clickJoinNowButton()
    }
    Then(~/^Email address is required message will be displayed$/) { ->
        waitFor{ epoints.joinModule.emailAlertOnJoin }
        assert epoints.joinModule.emailAlertOnJoin.text() == envVar.joinEmailAddressIsRequiredAlert
    }

    // 1.4 //  ------------------------------------------------------------------------------------ Joining into epoints
    // ------------------------------------------------------------------------------- checking fields length validation
    String longEmailAddress = 'q1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e34rt45RT67UI89uq1w2e325@wp.pl'
    Given(~/^User can enter email consisted of (\d+) signs$/) { int arg1 ->
        epoints.joinModule.enterEmailAddressOnJoin(longEmailAddress)
        waitFor{ epoints.joinModule.emailInputFieldOnJoin.value() == longEmailAddress }
        assert epoints.joinModule.emailInputFieldOnJoin.value() == longEmailAddress
    }
    When(~/^User try to add more signs to email$/) { ->
        epoints.joinModule.enterEmailAddressOnJoin(longEmailAddress+'a')
    }
    Then(~/^It will be impossible$/) { ->
        waitFor{ epoints.joinModule.emailInputFieldOnJoin.value() == longEmailAddress }
        assert epoints.joinModule.emailInputFieldOnJoin.value() == longEmailAddress
    }

    // 1.5 //  ------------------------------------------------------------------------------------ Joining into epoints
    // ------------------------------------------------------------------------ checking validation for duplicated email
    When(~/^User enter email which is already used$/) { ->
        epoints.joinModule.enterEmailAddressOnJoin(envVar.testUserEmail)
    }
    Then(~/^Username already taken message will be displayed$/) { ->
        waitFor{ epoints.joinModule.emailErrorOnJoin.isDisplayed() }
        assert epoints.joinModule.emailErrorOnJoin.text() == envVar.joinUsernameAlreadyTakenError
    }

    // 1.6 //  ------------------------------------------------------------------------------------ Joining into epoints
    // ----------------------------------------------------------------- checking validation for incorrect email address
    When(~/^User enter invalid email address$/) { ->
        epoints.joinModule.enterEmailAddressOnJoin(envVar.testUserEmailInvalid)
    }
    Then(~/^Email address is invalid message will be shown$/) { ->
        waitFor{ epoints.joinModule.emailAlertOnJoin }
        assert epoints.joinModule.emailAlertOnJoin.text() == envVar.joinEmailAddressIsInvalidAlert
    }

    // 1.7 //  ------------------------------------------------------------------------------------ Joining into epoints
    // ---------------------------------------------------------------------------------- checking if account is created
    String newAccountEmailAddress = 'newAutomatedTestAccount'+func.returnRandomValue(100000000)+'@gmail.com'
    When(~/^User enter new email address$/) { ->
        epoints.joinModule.enterEmailAddressOnJoin(newAccountEmailAddress)
    }
    Then(~/^New account will be created$/) { ->
        waitFor{ epoints.topNavHiUsername.text().contains(newAccountEmailAddress) }
        assert epoints.topNavHiUsername.text().contains(newAccountEmailAddress)
        def mysql = new jdbc('points')
        waitFor{ mysql.get("SELECT count(*) FROM points_manager.User WHERE email = '"+newAccountEmailAddress+"'",1) == '1' }
            assert mysql.get("SELECT count(*) FROM points_manager.User WHERE email = '"+newAccountEmailAddress+"'",1) == '1'
        mysql.close()
    }
    Then(~/^New account should be unverified$/) { ->
        def mysql = new jdbc('points')
            assert mysql.get("SELECT emailVerified FROM points_manager.User WHERE email = '"+newAccountEmailAddress+"'",1) == '0'
        mysql.close()
    }
    Then(~/^Email submitted page should be opened$/) { ->
        waitFor{ browser.currentUrl == envVar.returnJoinOkURL(newAccountEmailAddress) }
        assert browser.currentUrl == envVar.returnJoinOkURL(newAccountEmailAddress)
    }
    Then(~/^On new account should be (.+?) points$/) { String pointsValue ->
        def mysql = new jdbc('points')
            assert mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+newAccountEmailAddress+"'",1) == pointsValue
        mysql.close()
        waitFor{ epoints.yourEpointsCounter.text() == pointsValue }
        assert epoints.yourEpointsCounter.text() == pointsValue
    }

    // 1.8 //  ------------------------------------------------------------------------------------ Joining into epoints
    // -------------------------------------------------------------- checking sign out of non verified account/sign out
    Given(~/^Join page is opened clear$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        epoints.clickJoinNowButton()
    }
    String newAccountEmailAddress2 = 'newAutomatedTestAccount'+func.returnRandomValue(100000000)+'@gmail.com'
    Given(~/^New account is created$/) { ->
        epoints.joinModule.enterEmailAddressOnJoin(newAccountEmailAddress2)
        epoints.joinModule.clickJoinNowButton()
        waitFor{ epoints.topNavHiUsername.isDisplayed() }
        waitFor{ epoints.topNavHiUsername.text().contains(newAccountEmailAddress2) }
    }
    When(~/^User click sign out button before account will be verified$/) { ->
        epoints.clikcSignOutButton()
    }
    Then(~/^Modal warning window will be opened$/) { ->
        waitFor{ epoints.joinModule.notVerifiedSignOutModalAngular.isDisplayed() }
    }
    Then(~/^Modal warning window has proper content$/) { ->
        assert epoints.joinModule.notVerifiedSignOutModalAngular.text().replace("\n", " ") == envVar.returnJoinSignOutModalTextAngular(newAccountEmailAddress2)
        assert epoints.joinModule.notVerifiedSignOutModalResendEmailLinkAngular.isDisplayed()
        assert epoints.joinModule.notVerifiedSignOutModalOkButtonAngular.isDisplayed()
        assert epoints.joinModule.notVerifiedSignOutModalCancelButtonAngular.isDisplayed()
    }
    When(~/^User click ok sign out button on modal warning window$/) { ->
        epoints.joinModule.clickOkSignOutButtonAngular()
    }
    Then(~/^He will be properly sign out from created account$/) { ->
        waitFor{ epoints.topNavSignInButton.isDisplayed() }
        epoints.topNavSignInButton.isDisplayed()
    }

    // 1.9 //  ------------------------------------------------------------------------------------ Joining into epoints
    // ----------------------------------------------------------- checking sign out of non verified account/resend link
    String newAccountEmailAddress4 = 'newAutomatedTestAccount'+func.returnRandomValue(100000000)+'@gmail.com'
    Given(~/^New account is  created$/) { ->
        epoints.joinModule.enterEmailAddressOnJoin(newAccountEmailAddress4)
        epoints.joinModule.clickJoinNowButton()
        waitFor{ epoints.topNavHiUsername.isDisplayed() }
        waitFor{ epoints.topNavHiUsername.text().contains(newAccountEmailAddress4) }
    }
    When(~/^User click resend confirmation link on warning modal$/) { ->
        epoints.joinModule.clickResendConfirmationEmailInWarningModal()
    }
    Then(~/^Confirmation link will be sent ane success message displayed$/) { ->
          waitFor{ epoints.joinModule.notVerifiedSignOutModalSuccessAlert.isDisplayed() }
          assert epoints.joinModule.notVerifiedSignOutModalSuccessAlert.isDisplayed()
    }

    // 1.10 //  ----------------------------------------------------------------------------------- Joining into epoints
    // ---------------------------------------------------------------------- join here button on sig in popup - angular
    Given(~/^Sign in popup is displayed$/) { ->
        epoints.clickSignInButton()
    }
    When(~/^User click join here link on sign in popup$/) { ->
        epoints.signModule.clickJoinHereLink()
    }
    Then(~/^He will be redirected to join page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.joinURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.joinURL
    }

    // 1.11 //  ----------------------------------------------------------------------------------- Joining into epoints
    // ---------------------------------------------------------------- resend email confirmation/epoints sections links
    String newAccountEmailAddress3 = 'newAutomatedTestAccount'+func.returnRandomValue(100000000)+'@gmail.com'
    Given(~/^New account is registered$/) { ->
        epoints.joinModule.enterEmailAddressOnJoin(newAccountEmailAddress3)
        epoints.joinModule.clickJoinNowButton()
        waitFor{ epoints.topNavHiUsername.text().contains(newAccountEmailAddress3) }
    }
    When(~/^User click resend confirmation link$/) { ->
        epoints.joinModule.clickResendConfirmationEmail()
    }
    Then(~/^Resend confirmation link success alert will be displayed$/) { ->
        waitFor{ epoints.joinModule.resendEmailLinkSuccessAlert.isDisplayed() }
        assert epoints.joinModule.resendEmailLinkSuccessAlert.text() == envVar.resendEmailConfirmationSuccessAlert
    }
    Then(~/^Bottom epoints links works properly$/) { ->
        epoints.joinModule.clickGetEpointsLinkOnJoinOKPage()
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.getPageURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.getPageURL
        browser.getDriver().navigate().back()
        Thread.sleep(2000)
        epoints.joinModule.clickSpendEpointsLinkOnJoinOKPage()
        waitFor{ browser.currentUrl.contains(envVar.epointsLink + envVar.browseRewardURL) }
        assert browser.currentUrl.contains(envVar.epointsLink + envVar.browseRewardURL)
        browser.getDriver().navigate().back()
        Thread.sleep(2000)
        epoints.joinModule.clickLearnMoreLinkOnJoinOKPage()
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.aboutUsURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.aboutUsURL
    }

    // 1.12 //  ----------------------------------------------------------------------------------- Joining into epoints
    // --------------------------------------------------------------------------------------------- joining by facebook
    Given(~/^Proper user is deleted from points manager '(.+?)'$/) { String email ->
        def mysql = new jdbc("points")
            mysql.update("DELETE FROM points_manager.Request WHERE userId='"+mysql.get("SELECT userKey FROM points_manager.User WHERE email='"+email+"'",1)+"'")
            mysql.update("DELETE FROM points_manager.Points WHERE userId='"+mysql.get("SELECT id FROM points_manager.User WHERE email='"+email+"'",1)+"'")
            mysql.update("DELETE FROM points_manager.UserToken WHERE user_id='"+mysql.get("SELECT id FROM points_manager.User WHERE email='"+email+"'",1)+"'")
            mysql.update("DELETE FROM points_manager.UserId WHERE user_id='"+mysql.get("SELECT id FROM points_manager.User WHERE email='"+email+"'",1)+"'")
            mysql.update("DELETE FROM points_manager.User WHERE id='"+mysql.get("SELECT id FROM points_manager.User WHERE email='"+email+"'",1)+"'")
        mysql.close()
    }
    When(~/^User use join by facebook option with correct data '(.+?)' '(.+?)'$/) { String email, String password ->
        browser.withNewWindow({ epoints.joinModule.clickJoinByFacebookButton() }, close:false){
            waitFor{ $('input', id: 'email').isDisplayed() }
            $('input', id: 'email').value(email)
            $('input', id: 'pass').value(password)
            $('input', type: 'submit').click()
        }
    }
    Then(~/^New epoints facebook account will be properly created '(.+?)' '(.+?)'$/) { String email, String name ->
        waitFor{ epoints.topNavHiUsername.text().contains(name) }
        assert epoints.topNavHiUsername.text().contains(name)
        def mysql = new jdbc('points')
            waitFor{ mysql.get("SELECT count(*) FROM points_manager.User WHERE email = '"+email+"'",1) == '1' }
            assert mysql.get("SELECT count(*) FROM points_manager.User WHERE email = '"+email+"'",1) == '1'
        mysql.close()
    }
    Then(~/^User account should be verified '(.+?)'$/) { String email ->
        def mysql = new jdbc('points')
            assert mysql.get("SELECT emailVerified FROM points_manager.User WHERE email = '"+email+"'",1) == '1'
        mysql.close()
    }
    Then(~/^On new account should be (.+?) points '(.+?)'$/) { String pointsValue, String email ->
        def mysql = new jdbc('points')
            assert mysql.get("SELECT confirmed FROM points_manager.User WHERE email = '"+email+"'",1) == pointsValue
        mysql.close()
        waitFor{ epoints.yourEpointsCounter.text() == pointsValue }
        assert epoints.yourEpointsCounter.text() == pointsValue
    }
    Then(~/^All data stored in user account section will be properly downloaded from facebook account '(.+?)' '(.+?)' '(.+?)' '(.+?)' '(.+?)' '(.+?)'$/) { String name, String lastName, String gender, String dateOfBirth, String email, String datOfBirthDBFormat ->

    }
    Then(~/^User will be recognized as facebook user '(.+?)'$/) { String email ->
        def mysql = new jdbc('points')
            String user_id =  mysql.get("SELECT user_id FROM points_manager.UserId WHERE userId='"+email+"'",1)
            assert mysql.get("SELECT COUNT(*) FROM points_manager.UserId WHERE user_id='"+user_id+"' AND userIdType ='FACEBOOK'",1) == '1'
        mysql.close()
    }