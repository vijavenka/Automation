package com.iat.pages.points

import com.iat.pages.AbstractPage
import com.iat.pages.points.pointsPageModules.BreadcrumbModule

class RetailerPage extends AbstractPage {

    static url = '/points/retailerName/retailerId'
    static at = { waitFor { title.contains('| Earn points from shopping at') } }

    static content = {
        breadcrumbModule { module BreadcrumbModule }

        retailerPointsMultiplierText(wait: true) { $('.RetailerArticle-points') }
        retailerNameArticleHeader(wait: true) { $('.RetailerArticle-title') }
        retailerDescription(wait: true) { $('.RetailerArticle-description').find('p', 1) }
        joinNowButton(wait: true) { $('.RetailerCTA').find('a[ui-sref="app.join"]') }
        watchVideoButton(wait: true) { $('.HomeSection').find('.HomeSection-videoPlay-icon') }
        videoPlayer(required: false) {
            $('iframe[src="//www.youtube.com/embed/VDvXZ12dEEE?rel=0&controls=0&showinfo=0&autoplay=1"]')
        }
        videoPlayerCloseButton(wait: true, required: false) { $('button[ng-click="vm.closeVideo()"]') }
        goToRetailerButton(wait: true, required: false) { $('.RetailerArticle-goToRetailer') }
    }

    def clickJoinNowButton() {
        waitFor { joinNowButton.isDisplayed() }
        joinNowButton.click()
    }

    def clickWatchVideoButton() {
        waitFor { watchVideoButton.isDisplayed() }
        watchVideoButton.click()
    }

    def clickVideoPlayerCloseButton() {
        waitFor { videoPlayerCloseButton.isDisplayed() }; videoPlayerCloseButton.click()
    }

    def clickGoToRetailerButton() {
        waitFor { goToRetailerButton.isDisplayed() }; goToRetailerButton.click()
    }

}