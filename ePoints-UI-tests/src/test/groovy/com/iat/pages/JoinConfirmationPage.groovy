package com.iat.pages

class JoinConfirmationPage extends AbstractPage {

    static url = '/join/ok?email=userEmail'
    static at = {
        waitFor {
            getTitle().contains("Confirm your email address | epoints")
            confirmationMessageOnJoin.isDisplayed()
            (resendEmailLink.isDisplayed() || startEarningEpointsButton.text().trim() == "Start earning epoints")
        }
    }

    static content = {
        confirmationMessageOnJoin(wait: true) { $('.register-form') }
        continueToRetailerButtonOnJoin(wait: true, required: false) { confirmationMessageOnJoin.find('.btn-yellow') }
        resendEmailLink(required: false) { confirmationMessageOnJoin.find('.resend-email').find('a') }
        resendEmailLinkSuccessAlert { $('.alert-success') }
        joinPageGetEpointsLink(wait: true) { confirmationMessageOnJoin.find('.list-inline').find('li', 0).find('a') }
        joinPageSpendEpointsLink(wait: true) { confirmationMessageOnJoin.find('.list-inline').find('li', 1).find('a') }
        joinPageLearnMoreLink(wait: true) { confirmationMessageOnJoin.find('.list-inline').find('li', 2).find('a') }
        startEarningEpointsButton(required: false) { confirmationMessageOnJoin.$(".btn-success") }
    }

    def clickResendConfirmationEmail() { waitFor { resendEmailLink.isDisplayed() }; resendEmailLink.click() }

    def clickGetEpointsLinkOnJoinOKPage() {
        waitFor { joinPageGetEpointsLink.isDisplayed() }; joinPageGetEpointsLink.click()
    }

    def clickSpendEpointsLinkOnJoinOKPage() {
        waitFor { joinPageSpendEpointsLink.isDisplayed() }; joinPageSpendEpointsLink.click()
    }

    def clickLearnMoreLinkOnJoinOKPage() {
        waitFor { joinPageLearnMoreLink.isDisplayed() }; joinPageLearnMoreLink.click()
    }

    def clickContinueToRetailerButton() {
        waitFor { continueToRetailerButtonOnJoin.isDisplayed() }; continueToRetailerButtonOnJoin.click()
    }

}