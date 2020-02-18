package com.iat.pages.userAccount.ecardsManagement

import com.iat.pages.userAccount.ecardsManagement.modules.EcardsNavigationModule

class EcardIndividualPage extends EcardsHistoryPage {

    static url = '/my-account/ecards/ecard/ecardId'
    static atCheckWaiting = true
    static at = { waitFor { title.contains("Ecard | epoints") } }

    static content = {

        ecardsNavigationModule { module EcardsNavigationModule }

        ecardContainer(wait: true) { $('.Ecard') }
        ecardImageBasic(wait: true) { ecardContainer.find('.Ecard-image') }
        ecardAddedDate(wait: true) { ecardContainer.find('.Ecard-time') }
        ecardReason(wait: true) { ecardContainer.find('.Ecard-reason') }
        ecardFromTo(wait: true) { ecardContainer.find('.Ecard-users') }
        ecardPointsValue(wait: true, required: false) { ecardContainer.find('.Ecard-points') }
        ecardMessage(wait: true) { ecardContainer.find('.Ecard-message') }
        errorMessage(wait: true) { $('.Ecard-errorMessage') }
        printButton(wait: true, required: false) { ecardContainer.find('.btn-primary') }
    }

    def clickPrintButton() {
        waitFor { printButton.isDisplayed() }; printButton.click()
    }
}
