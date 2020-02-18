package com.iat.pages.points

import com.iat.pages.AbstractPage

class GoInstorePage extends AbstractPage {

    static url = '/points/instore'
    static at = {
        waitFor {
            browser.currentUrl.contains("/instore")
        }
    }

    static content = {

        mainHeader(wait: true) { $('.GetInstore-row').find('h1') }
        mainDescription(wait: true) { $('.GetInstore-row', 0).find('p') }
        firstSectionPicture(wait: true) { $('.row.GetInstore-row', 0).find('.section-image').find('img') }
        firstSectionHeader(wait: true) { $('.row.GetInstore-row', 0).find('h2') }
        firstSectionText(wait: true) { $('.row.GetInstore-row', 0).find('p') }

        secondSectionPicture(wait: true) { $('.row.GetInstore-row', 1).find('.section-image').find('img') }
        secondSectionHeader(wait: true) { $('.row.GetInstore-row', 1).find('h2') }
        secondSectionText(wait: true) { $('.row.GetInstore-row', 1).find('p') }

        thirdSectionPicture(wait: true) { $('.row.GetInstore-row', 2).find('.section-image').find('img') }
        thirdSectionHeader(wait: true) { $('.row.GetInstore-row', 2).find('h2') }
        thirdSectionText(wait: true) { $('.row.GetInstore-row', 2).find('p') }

        googlePlayLink(wait: true) {
            $('.GetInstore-appButtonsRow').find('.GetInstore-appButton.GetInstore--androidButton')
        }
        appStoreLink(wait: true) { $('.GetInstore-appButtonsRow').find('.GetInstore-appButton.GetInstore--iosButton') }
        bigdlLink(wait: true) { $('.u-link', 0) }
        googlePlayTextLink(wait: true) { $('.u-link', 1) }
        appStoreTextLink(wait: true) { $('.u-link', 2) }
    }

    def clickGooglePlayLink() { waitFor { googlePlayLink.isDisplayed() }; googlePlayLink.click() }

    def clickAppStoreLink() { waitFor { appStoreLink.isDisplayed() }; appStoreLink.click() }

    def clickGooglePlayTextLink() { waitFor { googlePlayTextLink.isDisplayed() }; googlePlayTextLink.click() }

    def clickAppStoreTextLink() { waitFor { appStoreTextLink.isDisplayed() }; appStoreTextLink.click() }

    def clickBigdlLink() { waitFor { bigdlLink.isDisplayed() }; bigdlLink.click() }
}