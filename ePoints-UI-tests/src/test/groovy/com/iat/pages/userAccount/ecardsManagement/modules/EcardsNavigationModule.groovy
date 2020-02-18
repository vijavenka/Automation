package com.iat.pages.userAccount.ecardsManagement.modules

import geb.Module

class EcardsNavigationModule extends Module {

    static content = {
        ecardsLeftSideNavigation(wait: true) { $('aside.EcardMgmt-sidebar') }
        createEcardLeftSideNavigationOption(wait: true) {
            ecardsLeftSideNavigation.find('a[ui-sref="app.account.ecards.create"]')
        }
        companyActivityLeftSideNavigationOption(wait: true) {
            ecardsLeftSideNavigation.find('a[ui-sref="app.account.ecards.activity"]')
        }
        historyLeftSideNavigationOption(wait: true) {
            ecardsLeftSideNavigation.find('a[ui-sref="app.account.ecards.history"]')
        }
        historySentLeftSideNavigationOption(wait: true) {
            ecardsLeftSideNavigation.find('a[ui-sref="app.account.ecards.history.sent"]', 0)
        }
        historyRceivedLeftSideNavigationOption(wait: true) {
            ecardsLeftSideNavigation.find('a[ui-sref="app.account.ecards.history.received"]', 0)
        }
    }

    //Atomic operations for Ecards Management Navigation Menu
    def clickCreateEcardNavigationOption() {
        waitFor { createEcardLeftSideNavigationOption.isDisplayed() }; createEcardLeftSideNavigationOption.click()
    }

    def clickCompanyActivityNavigationOption() {
        waitFor { companyActivityLeftSideNavigationOption.isDisplayed() };
        companyActivityLeftSideNavigationOption.click()
    }

    def clickHistoryNavigationOption() {
        waitFor { historyLeftSideNavigationOption.isDisplayed() }; historyLeftSideNavigationOption.click()
    }

    def clickHistorySentLeftSideNavigationOption() {
        waitFor { historySentLeftSideNavigationOption.isDisplayed() }; historySentLeftSideNavigationOption.click()
    }

    def clickHistoryRceivedLeftSideNavigationOption() {
        waitFor { historyRceivedLeftSideNavigationOption.isDisplayed() }; historyRceivedLeftSideNavigationOption.click()
    }
}
