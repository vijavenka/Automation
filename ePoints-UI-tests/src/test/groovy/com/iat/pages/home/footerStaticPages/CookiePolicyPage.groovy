package com.iat.pages.home.footerStaticPages

import com.iat.pages.AbstractPage

class CookiePolicyPage extends AbstractPage {

    static url = '/cookie-policy'
    static at = { waitFor { getTitle().contains('Cookie policy | epoints') } }

    static content = {

        pageContent(wait: true) { $('.main-view') }
        header(wait: true) { pageContent.find('h1') }
        yourOnlineChoicesLink(wait: true) { pageContent.find('a', 0) }
        allaboutcookiesLink(wait: true) { pageContent.find('a', 1) }
    }

    def clickYourOnlineChoicesLink() { waitFor { yourOnlineChoicesLink.isDisplayed() }; yourOnlineChoicesLink.click() }

    def clickAllaboutcookiesLink() { waitFor { allaboutcookiesLink.isDisplayed() }; allaboutcookiesLink.click() }

}
