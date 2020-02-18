package com.iat.pages.points.pointsPageModules

import geb.Module

class RetailerCardModule extends Module {

    static content = {
        retailerCardOverlay(wait: true) { $('.RetailerCard-overlay') }
        retailerCardOverlayButton(wait: true) { $('.RetailerCard-overlayButton') }

        epointsInfo(wait: true) { $('.RetailerCard-points') }
        epointsIcon(wait: true) { epointsInfo.find('.RetailerCard-detailsIcon') }
        favouriteIconDisbleUserLogout(wait: true, required: false) {
            $('.RetailerCard-favourites').find('.RetailerCard-detailsIcon')
        }
        favouriteIconDisbled(wait: true, required: false) {
            $('div', i: 'svg-icon-favourite-passive')
        }
        favouriteIconEnabled(wait: true, required: false) {
            $('div', i: 'svg-icon-favourite-active')
        }
        voucherIcon(required: false) {
            $('.RetailerCard-vouchers').find('.RetailerCard-detailsIcon')
        }

        leadsBadge(required: false) { $('.RetailerCard-badge') }
        maxCommissionName(required: false) { $('.RetailerCard-upTo') }
        maxCommissionValue(required: false) { $('.RetailerCard-epoints') }
    }

    def addRetailerToFavourite() {
        waitFor { favouriteIconDisbled.isDisplayed() }; favouriteIconDisbled.click()
    }

    def addRetailerToFavouriteWhenLogout() {
        waitFor { favouriteIconDisbleUserLogout.isDisplayed() }
        favouriteIconDisbleUserLogout.click()
    }

    def removeRetailerFromFavourite() {
        waitFor { favouriteIconEnabled.isDisplayed() }; favouriteIconEnabled.click()
    }

    def mouseoverOnRetailerCard() { jquery.mouseover() }

}
