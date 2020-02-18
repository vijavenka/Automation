package com.iat.pages.specsaversAdmin.modules

import geb.Module

class TabNaviagationModule extends Module {

    static content = {
        awardPointsTab { $('.nav-tabs').find('li', 0).find('a') }
        importTab { $('.nav-tabs').find('li', 1).find('a') }
        reportingTab { $('.nav-tabs').find('li', 2).find('a') }
        storeTab { $('.nav-tabs').find('li', 3).find('a') }

        signOutButton { $('.Specsavers-logout') }
    }

    def clickAwardPointsTab() { waitFor { awardPointsTab.isDisplayed() }; awardPointsTab.click() }

    def clickImportTab() { waitFor { importTab.isDisplayed() }; importTab.click() }

    def clickReportingTab() { waitFor { reportingTab.isDisplayed() }; reportingTab.click() }

    def clickStoreTab() { waitFor { storeTab.isDisplayed() }; storeTab.click() }

    def clickSignOutButton() { waitFor { signOutButton.isDisplayed() }; signOutButton.click() }
}
