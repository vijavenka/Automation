package com.iat.pages.home.modules

import geb.Module

class CookiePanelModule extends Module {

    static content = {
        cookiePanel(required: false) { $('.CookiesInfoMessage') }
        cookiePanelText(required: false) { cookiePanel.find('.CookiesInfoMessage-text') }
        cookiePanelAcceptButton(required: false) { cookiePanel.find('.btn-yellow') }
        cookiePanelFindOutMoreButton(required: false) { cookiePanel.find('.btn-grey') }
        cookiePanelCloseButton(required: false) { cookiePanel.find('.CookiesInfoMessage-close') }
    }

    def clickAcceptButtonOnCookiePanel() {
        waitFor { cookiePanelAcceptButton.isDisplayed() }; cookiePanelAcceptButton.click()
    }

    def clickFindOutMoreButtonOnCookiePanel() {
        waitFor { cookiePanelFindOutMoreButton.isDisplayed() }; cookiePanelFindOutMoreButton.click()
    }
}