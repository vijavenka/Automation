package com.iat.pages

class JoinFinishedPage extends AbstractPage {

    static url = '/join/finished'
    static at = {
        waitFor(10) {
            getTitle().contains('You\'re officially an epoints member | epoints')
        }
    }

    static content = {

        allDoneViewHeader(wait: true) { $('h2') }
        getEpointsNowButton(wait: true) { $('.btn-green ') }
        tellMeABitMoreLink(wait: true) { $('.tell-me-more') }
    }

    def clickGetEpointsNowButton() { waitFor { getEpointsNowButton.isDisplayed() }; getEpointsNowButton.click() }

    def clickTellMeABitMoreLink() { waitFor { tellMeABitMoreLink.isDisplayed() }; tellMeABitMoreLink.click() }
}