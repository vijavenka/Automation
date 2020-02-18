package stepdefs.EpointsStepDefs.getSection

import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 18.09.14
 * Time: 13:15
 * To change this template use File | Settings | File Templates.
 */

def epoints = new epointsPage()
def envVar = new envVariables()
def func = new Functions()
def browser = new Browser()


def transitionPageLink = envVar.transitionLink
def epointsLink = envVar.epointsLink

Given(~'^User is opening transition page$') { ->
    epointsPage.url= transitionPageLink
    to epointsPage
    epoints = page
}

Given(~'^User is opening transition page clear$') { ->
    epointsPage.url= transitionPageLink
    to epointsPage
    epoints = page
    func.clearCookiesAndStorage()
}

    // 1.1 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    // ----------------------------------------------------------------- not logged information modal content, no button
    Then(~/^He will be presented with transition information modal for not logged users$/) { ->
        waitFor{ epoints.transitionPage.informationModal.isDisplayed() }
        assert browser.getTitle().contains(envVar.transitionPageTag)
        assert epoints.transitionPage.informationModalInformation.isDisplayed()
        assert epoints.transitionPage.informationModalYouWillGetInformation.isDisplayed()
        assert epoints.transitionPage.informationModalRetailerInformation.isDisplayed()
        assert epoints.transitionPage.informationModalInformation.text().contains(envVar.transitionInformationModalHeader)
        assert epoints.transitionPage.informationModalJoinButton.isDisplayed()
        assert epoints.transitionPage.informationModalNoButton.isDisplayed()
        assert epoints.transitionPage.informationModalDoNotShowCheckbox.isDisplayed()
        assert epoints.transitionPage.informationModalDoNotShowMessageCheckbox.isDisplayed()
        assert epoints.transitionPage.informationModalDoNotShowMessageCheckbox.text().contains(envVar.transitionPageModalDoNotShowInformation)
        assert epoints.transitionPage.informationModalCloseLink.isDisplayed()
        assert epoints.transitionPage.informationModalCloseCrossButton.isDisplayed()
    }
    When(~/^User click no button$/) { ->
        epoints.transitionPage.clickNoButtonOnInformationModal()
    }
    Then(~/^He will be directly taken to retailer page$/) { ->
        waitFor{ !browser.getTitle().contains(envVar.transitionPageTag) }
        assert !browser.getTitle().contains(envVar.transitionPageTag)
    }

    // 1.2 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    // --------------------------------------------------------------  not logged information modal content, join button
    When(~/^User click join button$/) { ->
        epoints.transitionPage.clickJoinButtonOnInformationModal()
    }
    Then(~/^Information modal will disappear$/) { ->
        waitFor{ !epoints.transitionPage.informationModal.isDisplayed() }
        assert !epoints.transitionPage.informationModal.isDisplayed()
    }
    Then(~/^Transition page with all login forms will be presented$/) { ->
        waitFor{ browser.getTitle().contains(envVar.transitionPageTag) }
        waitFor{ epoints.transitionPage.signInForm.isDisplayed() }
        waitFor{ epoints.transitionPage.joinNowLink.isDisplayed() }
        assert browser.getTitle().contains(envVar.transitionPageTag)
        assert epoints.transitionPage.signInForm.isDisplayed()
        assert epoints.transitionPage.joinNowLink.isDisplayed()
    }

    // 1.3 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    // ------------------------------------------------------ not logged information modal content, do not show checkbox
    When(~/^User refresh page$/) { ->
        func.refreshPage()
    }
    Then(~/^Information modal will be still displayed$/) { ->
        waitFor{ epoints.transitionPage.informationModal.isDisplayed() }
        assert epoints.transitionPage.informationModal.isDisplayed()
    }
    When(~/^User check do not show me this again checkbox$/) { ->
        epoints.transitionPage.checkDontShowCheckboxOnInformationModal()
    }
    Then(~/^Information modal will not be displayed$/) { ->
        waitFor{ !epoints.transitionPage.informationModal.isDisplayed() }
        assert !epoints.transitionPage.informationModal.isDisplayed()
    }

    // 1.4 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    //----------------------------------------------------------------- not logged information modal content, close link
    When(~/^User click close link$/) { ->
        epoints.transitionPage.clickCloseLinkOnInformationModal()
    }

    // 1.5 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    // ----------------------------------------------------------------- logged information modal content, got it button
    Given(~/^Epoints page is opened and user is logged$/) { ->
        epointsPage.url = epointsLink
        to epointsPage
        epoints = page
        epoints.clickSignInButton()
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
        epoints.signModule.clickSignInButton()
        waitFor{ epoints.topnavSignOutButton.isDisplayed() }
    }
    When(~/^User go to transition page$/) { ->
        browser.go(transitionPageLink)
    }
    Then(~/^He will be presented with transition information modal for logged users$/) { ->
        waitFor{ epoints.transitionPage.informationModal.isDisplayed() }
        assert browser.getTitle().contains(envVar.transitionPageTag)
        assert epoints.transitionPage.informationModalInformation.isDisplayed()
        assert epoints.transitionPage.informationModalYouWillGetInformation.isDisplayed()
        assert epoints.transitionPage.informationModalRetailerInformation.isDisplayed()
        assert epoints.transitionPage.informationModalInformation.text().contains(envVar.transitionInformationModalHeader)
        assert epoints.transitionPage.informationModalGotItButton.isDisplayed()
        assert epoints.transitionPage.informationModalDoNotShowCheckbox.isDisplayed()
        assert epoints.transitionPage.informationModalDoNotShowMessageCheckbox.isDisplayed()
        assert epoints.transitionPage.informationModalDoNotShowMessageCheckbox.text().contains(envVar.transitionPageModalDoNotShowInformation)
        assert epoints.transitionPage.informationModalCloseLink.isDisplayed()
        assert epoints.transitionPage.informationModalCloseCrossButton.isDisplayed()
    }
    When(~/^User click got it button$/) { ->
        epoints.transitionPage.clickGotItButtonOnInformationModal()
        waitFor{ epoints.transitionPage.transitionPageEpointsAnimation.isDisplayed() }
        waitFor{ epoints.transitionPage.transitionPageRetailerInformation.isDisplayed() }
        assert epoints.transitionPage.transitionPageEpointsAnimation.isDisplayed()
        assert epoints.transitionPage.transitionPageRetailerInformation.isDisplayed()
        Thread.sleep(4000) //page is redirected after 3 sec
    }

    // 1.6 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    // ---------------------------------------------------------- logged information modal content, do not show checkbox
    Given(~/^Epoints page is opened and user is logged clear$/) { ->
        epointsPage.url = epointsLink
        to epointsPage
        epoints = page
        if(!epoints.topnavSignOutButton.isDisplayed()) {
            func.clearCookiesAndStorage()
            epoints.clickSignInButton()
            epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
            epoints.signModule.clickSignInButton()
        }
        waitFor{ epoints.topnavSignOutButton.isDisplayed() }
    }

    // 1.7 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    // ------------------------------------------------------------ logged information modal content, close cross button
    When(~/^User click close cross button on information modal$/) { ->
        epoints.transitionPage.clickCloseCrossButtonOnInformationModal()
        Thread.sleep(4000) //page is redirected after 3 sec
    }
    // 1.8 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    //----------------------------------------------------------------------------- not logged , transition page content
    Given(~/^User is opening clear transition page without any modal clear$/) { ->
        to epointsPage
        func.clearCookiesAndStorage()
        epointsPage.url= transitionPageLink
        to epointsPage
        epoints = page
        epoints.transitionPage.clickJoinButtonOnInformationModal()

    }
    When(~/^He look at the transition page$/) { ->
        //leave empty
    }
    Then(~/^He can see that it has all needed elements$/) { ->
        waitFor{ epoints.transitionPage.transitionPagePointsInfo.isDisplayed() }
        assert  epoints.transitionPage.transitionPagePointsInfo.isDisplayed()
        assert epoints.transitionPage.transitionPageRetailerInformation.isDisplayed()
        assert epoints.transitionPage.signInWithFacebookButton.isDisplayed()
        assert epoints.transitionPage.emailAddressInputField.isDisplayed()
        assert epoints.transitionPage.passwordInputField.isDisplayed()
        assert epoints.transitionPage.forgotPasswordLink.isDisplayed()
        assert epoints.transitionPage.signInButton.isDisplayed()
        assert epoints.transitionPage.joinNowLink.isDisplayed()
        assert epoints.transitionPage.continueAnywayButton.isDisplayed()
        assert epoints.transitionPage.signInLabelBasic[0].text() == envVar.transitionPageEmailAddressLabel
        assert epoints.transitionPage.signInLabelBasic[1].text() == envVar.transitionPagePasswordLabel
        assert epoints.transitionPage.joinNowInformation.text() == envVar.transitionPageJoinInformation
        assert epoints.transitionPage.continueAnywayInformation.text() == envVar.transitionPageContinueAnywayInformation
    }

    // 1.9 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    // ------------------------------------------------------------------------------ not logged, continue anyway button
    Given(~/^User is opening clear transition page without any modal$/) { ->
        epointsPage.url= transitionPageLink
        to epointsPage
        epoints = page
        epoints.transitionPage.clickJoinButtonOnInformationModal()
    }
    When(~/^User use continue anyway button$/) { ->
        epoints.transitionPage.clickContinueAnywayButton()
    }

    // 1.10 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    // --------------------------------------------------------------------------------------------- login, login alerts
    When(~/^User click sign in button without filling any data$/) { ->
        epoints.transitionPage.clickLoginButton()
    }
    Then(~/^Signing input fields alerts will be displayed$/) { ->
        waitFor{ epoints.transitionPage.signInErrorBasic[0].text() == envVar.transitionPageLoginEmailAlert }
        waitFor{ epoints.transitionPage.signInErrorBasic[1].text() == envVar.transitionPageLoginPasswordAlert }
        assert epoints.transitionPage.signInErrorBasic[0].text() == envVar.transitionPageLoginEmailAlert
        assert epoints.transitionPage.signInErrorBasic[1].text() == envVar.transitionPageLoginPasswordAlert
    }
    When(~/^User enter incorrect email address in signing form$/) { ->
        epoints.transitionPage.fillLoginData('incorrectemail', 'incorrectpassword')
    }
    Then(~/^Email address is invalid alert will be displayed$/) { ->
        waitFor{ epoints.transitionPage.signInErrorBasic.isDisplayed() }
        assert epoints.transitionPage.signInErrorBasic.isDisplayed()
        epoints.transitionPage.signInErrorBasic[0].text() == envVar.transitionPageLoginIncorrectEmailAlert
    }
    When(~/^User enter credentials of user which is not in database$/) { ->
        epoints.transitionPage.fillLoginData('notexistinguseeremail@gmail.com', 'notexistinguseerpassword')
    }
    Then(~/^Authorization failed alert will be displayed$/) { ->
        waitFor{ epoints.transitionPage.authorizationFaildAlert.isDisplayed() }
        waitFor{ epoints.transitionPage.authorizationFaildAlert.text() == envVar.transitionPageLoginAuthorizationFailedAlert }
        assert epoints.transitionPage.authorizationFaildAlert.isDisplayed()
        assert epoints.transitionPage.authorizationFaildAlert.text() == envVar.transitionPageLoginAuthorizationFailedAlert
    }

    // 1.11 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    // ------------------------------------------------------------------------------- login, correct login into epoints
    When(~/^User enter correct login data$/) { ->
        epoints.transitionPage.fillLoginData(envVar.testUserEmail, envVar.testUserPassword)
    }
    When(~/^User click sign in button$/) { ->
        epoints.transitionPage.clickLoginButton()
    }
    When(~/^User return to epoints page$/) { ->
        browser.go(envVar.epointsLink)
    }
    Then(~/^He will be logged in$/) { ->
        waitFor{ epoints.topnavSignOutButton.isDisplayed() }
        assert epoints.topnavSignOutButton.isDisplayed()
    }

    // 1.12 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    // ------------------------------------------- join, join to epoints and continue to retailer on confirmation screen
    When(~/^User click join now button$/) { ->
        epoints.transitionPage.clickJoinNowLink()
    }
    Then(~/^He will e redirected to epoints join page$/) { ->
        waitFor{ browser.getCurrentUrl().contains(envVar.joinURL) }
        waitFor{ browser.getTitle().contains(envVar.joinPageTag) }
        waitFor{ !epoints.joinModule.continueToRetailerButtonOnJoin.isDisplayed() }
        assert browser.getCurrentUrl().contains(envVar.joinURL)
        assert browser.getTitle().contains(envVar.joinPageTag)
        assert !epoints.joinModule.continueToRetailerButtonOnJoin.isDisplayed()
    }
    When(~/^User correctly fill email input field$/) { ->
        epoints.joinModule.enterEmailAddressOnJoin('testemail'+func.returnRandomValue(1000000)+'@gmail.com')
    }
    When(~/^Click join now button on epoints join page$/) { ->
        epoints.joinModule.clickJoinNowButton()
    }
    Then(~/^Under confirmation screen will be displayed continue to retailer button$/) { ->
        waitFor{ epoints.joinModule.confirmationMessageOnJoin.text() == envVar.joinConfirmationMessage }
        waitFor{ epoints.joinModule.continueToRetailerButtonOnJoin.isDisplayed() }
        waitFor{ browser.getTitle().contains(envVar.joinPageConfirmationTag) }
        assert epoints.joinModule.confirmationMessageOnJoin.text() == envVar.joinConfirmationMessage
        assert epoints.joinModule.continueToRetailerButtonOnJoin.isDisplayed()
        assert browser.getTitle().contains(envVar.joinPageConfirmationTag)
    }
    When(~/^User click continue anyway button$/) { ->
        epoints.joinModule.clickcontinueToRetailerButton()
    }
    Then(~/^He will be redirected to retailer page$/) { ->
        waitFor{ !browser.getTitle().contains(envVar.joinPageConfirmationTag) }
        assert !browser.getTitle().contains(envVar.joinPageConfirmationTag)
    }

    // 1.13 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    // -------------------------------------------------------------------------------------------- forgot password link
    When(~/^User use forgot password link$/) { ->
        // done in next step
    }

    Then(~/^New tab with forgot password form will be opened$/) { ->
        browser.withNewWindow({ epoints.transitionPage.clickForgotPasswordLink() }, close:true){
            waitFor{ browser.getTitle().contains(envVar.forgotPasswordPageTag) }
            assert browser.getTitle().contains(envVar.forgotPasswordPageTag)
        }
    }

    // 1.14 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
    // ------------------------------------------------------------------------------------------- sign in with facebook
    When(~/^User will sign in with facebook$/) { ->
        browser.withNewWindow({ epoints.transitionPage.clickSignInWithFacebookButton() }, close:false){
            Thread.sleep(1000)
            waitFor{ $('input', id: 'email').isDisplayed() }
            $('input', id: 'email').value(envVar.testUserEmail)
            $('input', id: 'pass').value(envVar.testUserPassword)
            $('input', type: 'submit').click()
        }
        //Thread.sleep(5000)
    }

    // 2.1 //  ------------------------------------ DESKTOP - TRANSITION PAGE - update UI for Ireland clickouts(NBO-548)
    // --------------------------------------------------------------------------------------- displaying of UK currency
    Given(~/^UK zone is chosen$/) { ->
        waitFor{ epoints.topNavZonePickerFlag.isDisplayed() }
        epoints.epandZonePickerPanel()
        epoints.choseUKzone()

    }
    When(~/^User go to transition page from az page$/) { ->
        //done in next step (new window)
    }
    Then(~/^He can see that currency is properly set for UK$/) { ->
        epoints.clickGetButton()
        browser.withNewWindow({ epoints.azPage.clickOnChosenRetailerCard(0) }, close:true){
            waitFor{ epoints.transitionPage.transitionPagePointsInfo.text().contains('£') }
            waitFor{ epoints.transitionPage.informationModalYouWillGetInformation.text().contains('£') }
            assert epoints.transitionPage.transitionPagePointsInfo.text().contains('£')
            assert epoints.transitionPage.informationModalYouWillGetInformation.text().contains('£')
        }
    }

    // 2.2 //  ------------------------------------ DESKTOP - TRANSITION PAGE - update UI for Ireland clickouts(NBO-548)
    // ---------------------------------------------------------------------------------- displaying of Ireland currency
    Given(~/^Ireland zone is chosen$/) { ->
        waitFor{ epoints.topNavZonePickerFlag.isDisplayed() }
        epoints.epandZonePickerPanel()
        epoints.choseIrelandZone()
    }
    Then(~/^He can see that currency is properly set for Ireland$/) { ->
        epoints.clickGetButton()
        browser.withNewWindow({ epoints.azPage.clickOnChosenRetailerCard(0) }, close:true){
            waitFor{ epoints.transitionPage.transitionPagePointsInfo.text().contains('€') }
            waitFor{ epoints.transitionPage.informationModalYouWillGetInformation.text().contains('€') }
            assert epoints.transitionPage.transitionPagePointsInfo.text().contains('€')
            assert epoints.transitionPage.informationModalYouWillGetInformation.text().contains('€')
        }
    }