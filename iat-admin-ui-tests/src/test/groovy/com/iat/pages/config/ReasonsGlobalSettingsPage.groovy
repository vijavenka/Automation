package com.iat.pages.config

import com.iat.pages.modules.ReasonsGlobalSettingsModule
import com.iat.pages.modules.TopNavigationModule
import geb.Page

class ReasonsGlobalSettingsPage extends Page {

    static url = '/hr/config/reasons/edit-globals'
    static atCheckWaiting = true
    static at = { waitFor { browser.currentUrl.contains("edit-globals") } }

    static content = {
        topNavigation(wait: true) { module TopNavigationModule }
        mainModule { module ReasonsGlobalSettingsModule }
    }
}