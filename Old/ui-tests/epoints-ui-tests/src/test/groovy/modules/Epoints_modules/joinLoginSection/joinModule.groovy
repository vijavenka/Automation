package modules.Epoints_modules.joinLoginSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-02.
 */

class joinModule extends Module{
    static content = {
        //join form
        joinEpointsHeader{ $('.row').find('h1') }
        joinEpointsMainText{ $('.row').find('p') }
        emailInputFieldLabel{ $('#regForm').find('label') }
        emailInputFieldOnJoin{ $('#regForm').find('#email') }
        emailAlertOnJoin{ $('#regForm').find('.error') }
        emailErrorOnJoin{ $('#regForm').find('.errorMsg') }
        joinNowButtonOnJoin{ $('#regForm').find('#startRegBtn') }
        joinViaFacebookButtonOnJoin{ $('#regForm').find('.btn-social-content') }
        tellMeMoreLinkOnJoin{ $('#tellMeMore') }
        confirmationMessageOnJoin{ $('.checkInbox') }
        continueToRetailerButtonOnJoin(required: false){ $('.back-to-transition') }
        resendEmailLink{ $('.resendEmail') }
        resendEmailLinkSuccessAlert{ $('.alert-success') }
        joinPageGetEpointsLink{ $('.bottommenu').find('li',0).find('a') }
        joinPageSpendEpointsLink{ $('.bottommenu').find('li',1).find('a') }
        joinPageLearnMoreLink{ $('.bottommenu').find('li',2).find('a') }

        notVerifiedSignOutModalAngular{ $('.modal-body') }
        notVerifiedSignOutModalXButtonAngular{ notVerifiedSignOutModalAngular.find('.modal-close-btn') }
        notVerifiedSignOutModalResendEmailLinkAngular{ notVerifiedSignOutModalAngular.find('.u-link') }
        notVerifiedSignOutModalOkButtonAngular{ notVerifiedSignOutModalAngular.find('.btn.btn-yellow') }
        notVerifiedSignOutModalCancelButtonAngular{ notVerifiedSignOutModalAngular.find('.btn.btn-default') }
        notVerifiedSignOutModalSuccessAlert{ notVerifiedSignOutModalAngular.find('.alert-success') }

        tellMeMoreModalWindow(required: false){ $('.module-modal') }
        tellMeMoreModalWindowText(required: false){ tellMeMoreModalWindow.find('.content') }
        tellMeMoreCloseButton(required: false){ tellMeMoreModalWindow.find('.close-modal-link') }
        tellMeMoreXButton(required: false){ tellMeMoreModalWindow.find('.close-modal-button') }
    }
    //join form
    def enterEmailAddressOnJoin(email){ waitFor{ emailInputFieldOnJoin.isDisplayed() }; emailInputFieldOnJoin.value(email) }
    def clickJoinNowButton(){ waitFor{ joinNowButtonOnJoin.isDisplayed() }; joinNowButtonOnJoin.click() }
    def clickcontinueToRetailerButton(){ waitFor{ continueToRetailerButtonOnJoin.isDisplayed() }; continueToRetailerButtonOnJoin.click() }
    def clickTellMeMoreLink(){ waitFor{ tellMeMoreLinkOnJoin.isDisplayed() }; tellMeMoreLinkOnJoin.click() }
    def clickCloseButtonInTellMeMoreWindow(){ waitFor{ tellMeMoreCloseButton.isDisplayed() }; tellMeMoreCloseButton.click() }
    def clickXButtonInTellMeMoreWindow(){ waitFor{ tellMeMoreXButton.isDisplayed() }; tellMeMoreXButton.click() }
    def clickResendConfirmationEmail(){ waitFor{ resendEmailLink.isDisplayed() }; resendEmailLink.click() }
    def clickGetEpointsLinkOnJoinOKPage(){ waitFor{ joinPageGetEpointsLink.isDisplayed() }; joinPageGetEpointsLink.click() }
    def clickSpendEpointsLinkOnJoinOKPage(){ waitFor{ joinPageSpendEpointsLink.isDisplayed() }; joinPageSpendEpointsLink.click() }
    def clickLearnMoreLinkOnJoinOKPage(){ waitFor{ joinPageLearnMoreLink.isDisplayed() }; joinPageLearnMoreLink.click() }
    def clickJoinByFacebookButton(){ waitFor{ joinViaFacebookButtonOnJoin.isDisplayed() }; joinViaFacebookButtonOnJoin.click() }

    def clickOkSignOutButtonAngular(){ waitFor{ notVerifiedSignOutModalOkButtonAngular.isDisplayed() }; notVerifiedSignOutModalOkButtonAngular.click() }
    def clickResendConfirmationEmailInWarningModal(){ waitFor{ notVerifiedSignOutModalResendEmailLinkAngular.isDisplayed() }; notVerifiedSignOutModalResendEmailLinkAngular.click() }
}