package com.iat.pages.home.footerStaticPages

import com.iat.pages.AbstractPage

class PrivacyPolicyPage extends AbstractPage {

    static url = '/privacy'
    static at = { waitFor { getTitle().contains('Privacy policy | epoints') } }

    static content = {

        pageContent(wait: true) { $('.main-view') }
        header(wait: true) { pageContent.find('h1') }
        googlePrivacyLink(wait: true) { pageContent.find('a', 0) }
    }

    def clickGooglePrivacyLink() {
        waitFor {
            googlePrivacyLink.isDisplayed()
        }
        googlePrivacyLink.click()
    }

}