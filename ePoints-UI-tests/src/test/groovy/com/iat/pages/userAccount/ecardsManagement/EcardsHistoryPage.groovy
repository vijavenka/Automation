package com.iat.pages.userAccount.ecardsManagement

import com.iat.pages.AbstractPage
import com.iat.pages.userAccount.ecardsManagement.modules.EcardsNavigationModule

class EcardsHistoryPage extends AbstractPage {

    static url = '/my-account/ecards/history/sent' //or received
    static atCheckWaiting = true
    static at = {
        waitFor { title.equals('Ecard history - sent | epoints') || title.equals('Ecard history - received | epoints') }
    }

    static content = {

        pageBaseLocator(required: false) { $('.EcardsActivity-items') }
        ecardsNavigationModule { module EcardsNavigationModule }
        personalEcardHistoryHeader(wait: true) { $('.EcardsActivity-title') }
        singleEcardContainer(required: false) { pageBaseLocator.find('.EcardsActivity-item') }
        ecardImageBasic(wait: true) { singleEcardContainer.find('.EcardsActivity-image') }
        ecardAddedDate(wait: true) { singleEcardContainer.find('.EcardsActivity-time') }
        ecardReasonBasic(wait: true) { singleEcardContainer.find('.EcardsActivity-reason') }
        ecardFromToWhoBasic(wait: true) { singleEcardContainer.find('.EcardsActivity-users') }
        ecardPointsValueBasic(wait: true, required: false) { singleEcardContainer.find('.EcardsActivity-points') }
        ecardXmoreButton(required: false) { singleEcardContainer.find('.EcardsActivity-others') }
        moreUsersModal(required: false) { $('.modal-body') }
        moreUsersModalContentSingleUser(required: false) { moreUsersModal.find('.EcardOtherUsers-user') }
        moreUsersModalCloseButton(required: false) { moreUsersModal.find('.btn-yellow') }
        moreUsersModalXButton(required: false) { $('.modal-close-btn') }
        ecardSeeEcardDetailsButtonBasic(wait: true) { singleEcardContainer.find('.EcardsActivity-seeEcard') }
        loadMoreEcardsButton(required: false) { $('.EcardsActivity-showMoreButton') }
        backToTopButton(wait: true) { $('button', 0) }
    }

    def clickSeeEcardDetailsButtonOfChosenEcard(number) {
        waitFor { ecardSeeEcardDetailsButtonBasic[number].isDisplayed() }
        ecardSeeEcardDetailsButtonBasic[number].click()
    }

    def clickLoadMoreEcardsButton() {
        waitFor { loadMoreEcardsButton.isDisplayed() }
        loadMoreEcardsButton.click()
    }

    def clickBackToTopButton() {
        waitFor { backToTopButton.isDisplayed() }
        backToTopButton.click()
    }

    def openMoreRecipientsModalOfChosenEcard(ecardNumber) {
        waitFor { ecardXmoreButton[ecardNumber].isDisplayed() }; ecardXmoreButton[ecardNumber].click()
    }

    def clickMoreRecipientsModalCloseButton() {
        sleep(1000)
        waitFor { moreUsersModalCloseButton.isDisplayed() }; moreUsersModalCloseButton.click()
    }
}