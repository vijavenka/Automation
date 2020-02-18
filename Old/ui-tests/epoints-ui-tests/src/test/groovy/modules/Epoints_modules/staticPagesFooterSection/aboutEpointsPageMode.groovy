package modules.Epoints_modules.staticPagesFooterSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-23.
 */
class aboutEpointsPageMode extends Module {
    static content ={
        header{ $('.main-view').find('h1') }
        whatIsEpointsLink{ $('.aboutPage-pageMenu').find('li',0) }
        soMuchChoiceLink{ $('.aboutPage-pageMenu').find('li',1) }
        hassleFreeRewardsLink{ $('.aboutPage-pageMenu').find('li',2) }
        getInvolvedLink{ $('.aboutPage-pageMenu').find('li',3) }

        firstSectionHeader{ $('#rewards').find('h3') }
        firstSectionPicture{ $('#rewards').find('img') }
        firstSectionText{ $('#rewards').find('p') }
        firstSectionBackToTopLink{ $('#rewards').find('.aboutPage-backToTop') }

        secondSectionHeader{ $('#choices').find('h3') }
        secondSectionRetailerCardBasic{ $('#choices').find('.RetailerCard ') }
        secondSectionText{ $('#choices').find('p') }
        secondSectionPreviousArrow{ $('#choices').find('.retailersCarousel--prev.retailersCarousel-button') }
        secondSectionNextArrow{ $('#choices').find('.retailersCarousel--next.retailersCarousel-button') }
        secondSectionBackToTopLink{ $('#choices').find('.aboutPage-backToTop') }

        thirdSectionHeader{ $('#value').find('h3') }
        thirdSectionPicture{ $('#value').find('img') }
        thirdSectionText{ $('#value').find('p') }
        thirdSectionText2{ $('#value').find('center') }
        thirdSectionBackToTopLink{ $('#value').find('.aboutPage-backToTop') }

        fourthSectionHeader{ $('#mission').find('h3') }
        fourthSectionText{ $('#mission').find('p') }
        fourthSectionText2{ $('#mission').find('center') }
        fourthSectionBackToTopLink{ $('#mission').find('.aboutPage-backToTop') }
        fourthSectionWatchLink{ $('#mission').find('b') }
        fourthSectionGetInTouchButton{ $('#mission').find('.tell-us') }
    }
    def clickWhatIsEpointsLink(){ waitFor{ whatIsEpointsLink.isDisplayed() }; whatIsEpointsLink.click() }
    def clickSoMuchChoiceLink(){ waitFor{ soMuchChoiceLink.isDisplayed() }; soMuchChoiceLink.click() }
    def clickChosenRetailerCard(number){ waitFor{ secondSectionRetailerCardBasic[number].isDisplayed() }; secondSectionRetailerCardBasic.click() }
    def clickHassleFreeRewardsLink(){ waitFor{ hassleFreeRewardsLink.isDisplayed() }; hassleFreeRewardsLink.click() }
    def clickGetInvolvedLink(){ waitFor{ getInvolvedLink.isDisplayed() }; getInvolvedLink.click() }
    def clickYoutubeEpointsVideoLink(){ waitFor{ fourthSectionWatchLink.isDisplayed() }; fourthSectionWatchLink.click() }
    def clickGetInTouchButton(){ waitFor{ fourthSectionGetInTouchButton.isDisplayed() }; fourthSectionGetInTouchButton.click() }
}