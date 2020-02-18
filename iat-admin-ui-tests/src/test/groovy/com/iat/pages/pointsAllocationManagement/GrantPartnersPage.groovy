package com.iat.pages.pointsAllocationManagement

class GrantPartnersPage extends GrantDepartmentsPage {

    static url = '/hr/points-allocation/grant-partners'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("grant-partners") } }

    static content = {
        partnetrSearchInputField(wait: true) { allocationPanel.find('.form-control.pull-right') }
    }

    def enterSearchPhraseIntoSearchInputField(searchPhrase) {
        waitFor { partnetrSearchInputField.isDisplayed() }; partnetrSearchInputField.value(searchPhrase)
    }

}