package modules.Epoints_modules.joinLoginSection

import geb.Module

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 18.09.14
 * Time: 14:26
 * To change this template use File | Settings | File Templates.
 */

class signInModule extends Module{
    static content = {
    //sign in form old
        authenticationPanelNotAngular{ $('.authPanel') }
        emailInputFieldLabelNotAngular{ authenticationPanelNotAngular.find('label', for: 'email') }
        emailInputFieldNotAngular{ authenticationPanelNotAngular.find('#email') }
        errorBasicNotAngular{ authenticationPanelNotAngular.find('.error') }
        errorDangerNotAngular{ authenticationPanelNotAngular.find('.errorMsg') }
        passwordInputFieldLabelNotAngular{ authenticationPanelNotAngular.find('label', for: 'password') }
        passwordInputFieldNotAngular{ authenticationPanelNotAngular.find('#password') }
        forgotPasswordLinkNotAngular{ authenticationPanelNotAngular.find('a', text: 'Forgot password?') }
        signInButtonNotAngular{ authenticationPanelNotAngular.find('#signInBtn') }
        joinHereLinkNotAngular{ authenticationPanelNotAngular.find('a', text: 'Join here') }
        signInWithFacebookButtonNotAngular{ authenticationPanelNotAngular.find('.btn-social-content') }
        closeButtonNotAngular{ authenticationPanelNotAngular.find('a', text: 'Close') }
    //sign in form angular
        authenticationPanel{ $('.sign-in__inner') }
        emailInputFieldLabel{ authenticationPanel.find('label', for: 'email') }
        emailInputField{ authenticationPanel.find('#email') }
        errorBasic{ authenticationPanel.find('.valid-error') }
        errorDanger{ authenticationPanel.find('.alert.alert-danger') }
        passwordInputFieldLabel{ authenticationPanel.find('label', for: 'password') }
        passwordInputField{ authenticationPanel.find('#password') }
        forgotPasswordLink{ authenticationPanel.find('a', text: 'Forgot password?') }
        signInButton{ authenticationPanel.find('.btn-green') }
        joinHereLink{ authenticationPanel.find('a', text: 'Join here') }
        signInWithFacebookButton{ authenticationPanel.find('.btn-facebook') }
        closeButton{ authenticationPanel.find('a', text: 'Close') }
    //sign in form modal old
        loginModal{ $('#modal') }
        loginModalHeader{ loginModal.find('.title') }
        loginModalEmailInputFieldLabel{ loginModal.find('label', for: 'email') }
        loginModalEmailInputField{ loginModal.find('#email') }
        loginModalErrorBasic{ loginModal.find('.valid-error') }
        loginModalErrorDanger{ loginModal.find('.alert.alert-danger') }
        loginModalPasswordInputFieldLabel{ loginModal.find('label', for: 'password') }
        loginModalPasswordInputField{ loginModal.find('#password') }
        loginModalForgotPasswordLink{ loginModal.find('a', text: 'Forgot password?') }
        loginModalSignInButton{ loginModal.find('.btn-green') }
        loginModalJoinHereLink{ loginModal.find('a', text: 'Join here') }
        loginModalSignInWithFacebookButton{ loginModal.find('.btn-social-content') }
        loginModalCloseButton{ loginModal.find('a', text: 'Close') }
     //sign in form modal angular
        loginModalAngular{ $('.modal-body') }
        loginModalHeaderAngular{ loginModalAngular.find('.sign-in-form').find('h3') }
        loginModalEmailInputFieldLabelAngular{ loginModalAngular.find('label', for: 'email') }
        loginModalEmailInputFieldAngular{ loginModalAngular.find('#email') }
        loginModalErrorBasicAngular{ loginModalAngular.find('.valid-error') }
        loginModalErrorDangerAngular{ loginModalAngular.find('.alert.alert-danger') }
        loginModalPasswordInputFieldLabelAngular{ loginModalAngular.find('label', for: 'password') }
        loginModalPasswordInputFieldAngular{ loginModalAngular.find('#password') }
        loginModalForgotPasswordLinkAngular{ loginModalAngular.find('a', text: 'Forgot password?') }
        loginModalSignInButtonAngular{ loginModalAngular.find('.btn-green') }
        loginModalJoinHereLinkAngular{ loginModalAngular.find('a', text: 'Join here') }
        loginModalSignInWithFacebookButtonAngular{ loginModalAngular.find('.btn-facebook ') }
        loginModalCloseButtonAngular{ $('.modal-close-btn') }
    }
    //sign in form old
        def enterEmailAddressNotAngular(email){ waitFor{ emailInputFieldNotAngular.isDisplayed() }; emailInputFieldNotAngular.value(email) }
        def enterPasswordNotAngular(password){ waitFor{ passwordInputFieldNotAngular.isDisplayed() }; passwordInputFieldNotAngular.value(password) }
        def enterLoginDataNotAngular(email, password){ enterEmailAddressNotAngular(email); enterPasswordNotAngular(password) }
        def clickSignInButtonNotAngular(){ waitFor{ signInButtonNotAngular.isDisplayed() }; signInButtonNotAngular.click() }
        def clickJoinHereLinkNotAngular(){ waitFor{ joinHereLinkNotAngular.isDisplayed() }; joinHereLinkNotAngular.click() }
        def clickForgotPasswordLinkNotAngular(){ waitFor{ forgotPasswordLinkNotAngular.isDisplayed() }; forgotPasswordLinkNotAngular.click() }
    //sign in form angular
        def enterEmailAddress(email){ waitFor{ emailInputField.isDisplayed() }; emailInputField.value(email) }
        def enterPassword(password){ waitFor{ passwordInputField.isDisplayed() }; passwordInputField.value(password) }
        def enterLoginData(email, password){ enterEmailAddress(email); enterPassword(password) }
        def clickSignInButton(){ waitFor{ signInButton.isDisplayed() }; signInButton.click() }
        def clickJoinHereLink(){ waitFor{ joinHereLink.isDisplayed() }; joinHereLink.click() }
        def clickForgotPasswordLink(){ waitFor{ forgotPasswordLink.isDisplayed() }; forgotPasswordLink.click() }
    //login modal old
        def enterEmailAddresModal(email){ waitFor{ loginModalEmailInputField.isDisplayed() }; loginModalEmailInputField.value(email) }
        def enterPasswordModal(password){  waitFor{ loginModalPasswordInputField.isDisplayed() }; loginModalPasswordInputField.value(password) }
        def enterLoginDataModal(email, password){ enterEmailAddresModal(email); enterPasswordModal(password) }
        def clickSignInButtonModal(){ waitFor{ loginModalSignInButton.isDisplayed() }; loginModalSignInButton.click() }
        def clickForgotPasswordModalLink(){ waitFor{ loginModalForgotPasswordLink.isDisplayed() }; loginModalForgotPasswordLink.click() }
    //login modal angular
        def enterEmailAddresModalAngular(email){ waitFor{ loginModalEmailInputFieldAngular.isDisplayed() }; loginModalEmailInputFieldAngular.value(email) }
        def enterPasswordModalAngular(password){  waitFor{ loginModalPasswordInputFieldAngular.isDisplayed() }; loginModalPasswordInputFieldAngular.value(password) }
        def enterLoginDataModalAngular(email, password){ enterEmailAddresModalAngular(email); enterPasswordModalAngular(password) }
        def clickSignInButtonModalAngular(){ waitFor{ loginModalSignInButtonAngular.isDisplayed() }; loginModalSignInButtonAngular.click() }
        def clickForgotPasswordModalLinkAngular(){ waitFor{ loginModalForgotPasswordLinkAngular.isDisplayed() }; loginModalForgotPasswordLinkAngular.click() }
}