package modules.Epoints_modules.getSection
import geb.Module

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 17.09.14
 * Time: 12:52
 * To change this template use File | Settings | File Templates.
 */
class transitionModule extends Module{
    static content = {
    //transition page
        transitionPagePointsInfo{ $('.points-info') }
        transitionPageRetailerInformation{ $('h2') }
        transitionPageEpointsAnimation{ $('#swiffycontainer') }

        signInWithFacebookButton{ $('.facebook').find('span') }
        signInForm{ $('#signInForm') }
            emailAddressInputField{ signInForm.find('#email') }
            passwordInputField{ signInForm.find('#password') }
            signInLabelBasic{ signInForm.find('label') }
            signInErrorBasic{ signInForm.find('.error') }
            signInButton{ signInForm.find('#signInBtn') }
            forgotPasswordLink{ signInForm.find('.link') }
            authorizationFaildAlert{ signInForm.find('.alert-warning') }
        joinNowLink{ $('#joinBtn') }
        joinNowInformation{ $('.join-text') }
        continueAnywayButton{ $('#ContinueAnywayBtn') }
        continueAnywayInformation{ $('.epoints-info') }

    //information window not logged/logged
        informationModal(required: false){ $('.module-modal.clickout-info-modal') }
        informationModalInformation{ informationModal.find('h2') }
        informationModalYouWillGetInformation{ informationModal.find('.points-info') }
        informationModalRetailerInformation{ informationModal.find('h3') }
        informationModalGotItButton(required: false){ informationModal.find('.btn-green.close-modal') }
        informationModalJoinButton(required: false){ informationModal.find('.close-modal') }
        informationModalNoButton(required: false){ informationModal.find('.btn-grey.continue-to-retailer') }
        informationModalDoNotShowCheckbox{ informationModal.find('.value-label') }
        informationModalDoNotShowMessageCheckbox{ informationModal.find('.value-label') }
        informationModalCloseLink{ $('.close-modal-link') }
        informationModalCloseCrossButton{ $('.close-modal-button') }

    }
        def clickSignInWithFacebookButton(){ waitFor{ signInWithFacebookButton }; signInWithFacebookButton.click() }

    //transition page
        def enterEmailAddress(email){waitFor{ emailAddressInputField.isDisplayed() }; emailAddressInputField.value(email) }
        def enterPassword(password){ waitFor{ passwordInputField.isDisplayed() }; passwordInputField.value(password) }
        def fillLoginData(email, password){ enterEmailAddress(email); enterPassword(password) }
        def clickLoginButton(){ waitFor{ signInButton.isDisplayed() }; signInButton.click() }
        def clickJoinNowLink(){ waitFor{ joinNowLink.isDisplayed() }; joinNowLink.click() }
        def clickForgotPasswordLink(){ waitFor{ forgotPasswordLink.isDisplayed() }; forgotPasswordLink.click() }

    //information window not logged/logged
        def clickGotItButtonOnInformationModal(){ waitFor{ informationModalGotItButton.isDisplayed() }; informationModalGotItButton.click() }
        def clickNoButtonOnInformationModal(){ waitFor{ informationModalNoButton.isDisplayed() }; informationModalNoButton.click() }
        def clickJoinButtonOnInformationModal(){ waitFor{ informationModalJoinButton.isDisplayed() }; informationModalJoinButton.click() }
        def checkDontShowCheckboxOnInformationModal(){ waitFor{ informationModalDoNotShowCheckbox.isDisplayed() }; informationModalDoNotShowCheckbox.click() }
        def clickCloseLinkOnInformationModal(){ waitFor{ informationModalCloseLink.isDisplayed() }; informationModalCloseLink.click() }
        def clickCloseCrossButtonOnInformationModal(){ waitFor{ informationModalCloseCrossButton.isDisplayed() }; informationModalCloseCrossButton.click() }

        def clickContinueAnywayButton(){ waitFor{ continueAnywayButton.isDisplayed() }; continueAnywayButton.click() }
}