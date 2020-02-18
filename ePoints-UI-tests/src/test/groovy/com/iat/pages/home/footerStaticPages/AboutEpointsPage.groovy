package com.iat.pages.home.footerStaticPages

import com.iat.pages.AbstractPage

class AboutEpointsPage extends AbstractPage {

    static url = '/about'
    static at = { waitFor { browser.currentUrl.contains("/about") } }

    static content = {

        header(wait: true) { $('.main-view').find('h1') }
        whatIsEpointsLink(wait: true) { $('.aboutPage-pageMenu').find('li', 0) }
        soMuchChoiceLink(wait: true) { $('.aboutPage-pageMenu').find('li', 1) }
        hassleFreeRewardsLink(wait: true) { $('.aboutPage-pageMenu').find('li', 2) }
        getInvolvedLink(wait: true) { $('.aboutPage-pageMenu').find('li', 3) }

        firstSectionHeader(wait: true) { $('#rewards').find('h3') }
        firstSectionPicture(wait: true) { $('#rewards').find('img') }
        firstSectionText(wait: true) { $('#rewards').find('p', 0) }
        firstSectionBackToTopLink(wait: true) { $('#rewards').find('.aboutPage-backToTop') }

        secondSectionHeader(wait: true) { $('#choices').find('h3') }
        secondSectionRetailerCardBasic(wait: true) { $('#choices').find('.RetailerCard ') }
        secondSectionText(wait: true) { $('#choices').find('p') }
        loader(wait: true, required: false) { $('.u-loader') }
        secondSectionPreviousArrow(wait: true) {
            $('#choices').find('.retailersCarousel--prev.retailersCarousel-button')
        }
        secondSectionNextArrow(wait: true) { $('#choices').find('.retailersCarousel--next.retailersCarousel-button') }
        secondSectionBackToTopLink(wait: true) { $('#choices').find('.aboutPage-backToTop') }

        thirdSectionHeader(wait: true) { $('#value').find('h3') }
        thirdSectionPicture(wait: true) { $('#value').find('img') }
        thirdSectionText(wait: true) { $('#value').find('p') }
        thirdSectionText2(wait: true) { $('#value').find('center') }
        thirdSectionBackToTopLink(wait: true) { $('#value').find('.aboutPage-backToTop') }

        fourthSectionHeader(wait: true) { $('#mission').find('h3') }
        fourthSectionText(wait: true) { $('#mission').find('p') }
        fourthSectionText2(wait: true) { $('#mission').find('center') }
        fourthSectionBackToTopLink(wait: true) { $('#mission').find('.aboutPage-backToTop') }
        fourthSectionWatchLink(wait: true) { $('#mission').find('b') }
        fourthSectionGetInTouchButton(wait: true) { $('#mission').find('.tell-us') }
    }

    def clickWhatIsEpointsLink() { waitFor { whatIsEpointsLink.isDisplayed() }; whatIsEpointsLink.click() }

    def clickSoMuchChoiceLink() { waitFor { soMuchChoiceLink.isDisplayed() }; soMuchChoiceLink.click() }

    def clickChosenRetailerCard(number) {
        waitFor { secondSectionRetailerCardBasic[number].isDisplayed() }; secondSectionRetailerCardBasic[number].click()
    }

    def clickHassleFreeRewardsLink() { waitFor { hassleFreeRewardsLink.isDisplayed() }; hassleFreeRewardsLink.click() }

    def clickGetInvolvedLink() { waitFor { getInvolvedLink.isDisplayed() }; getInvolvedLink.click() }

    def clickYoutubeEpointsVideoLink() {
        waitFor { fourthSectionWatchLink.isDisplayed() }
        fourthSectionWatchLink.click()
    }

    def clickGetInTouchButton() {
        waitFor {
            fourthSectionGetInTouchButton.isDisplayed()
        }
        fourthSectionGetInTouchButton.click()
    }

}
