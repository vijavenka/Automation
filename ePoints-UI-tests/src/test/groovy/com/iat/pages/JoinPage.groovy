package com.iat.pages

class JoinPage extends AbstractPage {

    static url = '/join'
    static at = {
        waitFor {
            browser.currentUrl.contains("/join")
        }
    }

    static content = {

        //Join form
        joinEpointsHeader(wait: true) { $('.u-pageTitle') }
        joinEpointsMainText(wait: true) { $('.u-textHeadline') }
        joinForm(wait: true) { $('.joinForm') }
        emailInputFieldLabel(wait: true) { joinForm.find('label') }
        emailInputFieldOnJoin(wait: true) { joinForm.find('#joinEmail') }
        emailAlertOnJoin(wait: true) { joinForm.find('.valid-error div') }
        emailErrorOnJoin(wait: true) { joinForm.find('.alert') }
        joinNowButtonOnJoin(wait: true) { joinForm.find('.btn-green') }
        joinViaFacebookButtonOnJoin(wait: true) { $('.facebook-login').find('.btn-facebook') }
        optInInfo(wait: true) { $('.RegisterForm-agreement') }
        optInTandCLink(wait: true) { optInInfo.$('a', 0) }
        optInPrivacyPolicyLink(wait: true) { optInInfo.$('a', 1) }
        optInCookiePolicyLink(wait: true) { optInInfo.$('a', 2) }
        tellMeMoreLinkOnJoin(wait: true) { $('.link.u-tellMore') }

        notVerifiedSignOutModalAngular(wait: true) { $('.modal-body') }
        notVerifiedSignOutModalXButtonAngular(wait: true) { notVerifiedSignOutModalAngular.find('.modal-close-btn') }
        notVerifiedSignOutModalResendEmailLinkAngular(wait: true) { notVerifiedSignOutModalAngular.find('.u-link') }
        notVerifiedSignOutModalOkButtonAngular(wait: true) { notVerifiedSignOutModalAngular.find('.btn.btn-yellow') }
        notVerifiedSignOutModalCancelButtonAngular(wait: true) {
            notVerifiedSignOutModalAngular.find('.btn.btn-default')
        }
        notVerifiedSignOutModalSuccessAlert(wait: true) { notVerifiedSignOutModalAngular.find('.alert-success') }

        tellMeMoreModalWindow(required: false) { $('.modal-dialog') }
        tellMeMoreModalWindowText(wait: true, required: false) { tellMeMoreModalWindow.find('.modal-body') }
        tellMeMoreCloseButton(wait: true, required: false) { tellMeMoreModalWindow.find('.close-modal-link') }
        tellMeMoreXButton(wait: true, required: false) { tellMeMoreModalWindow.find('.close-modal-button') }

        continueToRetailerButtonOnJoin(wait: true, required: false) { $('.mddqwdwqdodal-dialog') }
    }

    //Join form
    def enterEmailAddressOnJoin(email) {
        waitFor { emailInputFieldOnJoin.isDisplayed() }; emailInputFieldOnJoin.value(email)
    }

    def clickJoinNowButton() { waitFor { joinNowButtonOnJoin.isDisplayed() }; joinNowButtonOnJoin.click() }

    def clickTellMeMoreLink() { waitFor { tellMeMoreLinkOnJoin.isDisplayed() }; tellMeMoreLinkOnJoin.click() }

    def clickCloseButtonInTellMeMoreWindow() {
        waitFor { tellMeMoreCloseButton.isDisplayed() }; tellMeMoreCloseButton.click()
    }

    def clickXButtonInTellMeMoreWindow() { waitFor { tellMeMoreXButton.isDisplayed() }; tellMeMoreXButton.click() }


    def clickJoinByFacebookButton() {
        waitFor { joinViaFacebookButtonOnJoin.isDisplayed() }; joinViaFacebookButtonOnJoin.click()
    }

    def clickOkSignOutButtonAngular() {
        waitFor { notVerifiedSignOutModalOkButtonAngular.isDisplayed() }; notVerifiedSignOutModalOkButtonAngular.click()
    }

    def clickResendConfirmationEmailInWarningModal() {
        waitFor { notVerifiedSignOutModalResendEmailLinkAngular.isDisplayed() }
        notVerifiedSignOutModalResendEmailLinkAngular.click()
    }

    def clickOptInCookiePolicyLink() {
        waitFor { optInCookiePolicyLink.isDisplayed() }; optInCookiePolicyLink.click()
    }

    def clickOptInPrivacyPolicyLink() {
        waitFor { optInPrivacyPolicyLink.isDisplayed() }; optInPrivacyPolicyLink.click()
    }

    def clickOptInTandCLink() {
        waitFor { optInTandCLink.isDisplayed() }; optInTandCLink.click()
    }
}