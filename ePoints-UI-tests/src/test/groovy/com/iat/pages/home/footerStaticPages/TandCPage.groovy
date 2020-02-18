package com.iat.pages.home.footerStaticPages

import com.iat.pages.AbstractPage

class TandCPage extends AbstractPage {

    static url = '/terms'
    static at = { waitFor { getTitle().contains('Terms & Conditions | epoints') } }

    static content = {

        pageContent(wait: true) { $('.main-view') }
        header(wait: true) { pageContent.find('h1') }
        epointsLink(wait: true) { pageContent.find('a', 0) }
        supportLink(wait: true) { pageContent.find('a', 1) }
    }

    def clickEpointsLink() { waitFor { epointsLink.isDisplayed() }; epointsLink.click() }

    def clickSupportLink() { waitFor { supportLink.isDisplayed() }; supportLink.click() }

}