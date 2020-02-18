package com.iat.pages.points

import com.iat.pages.AbstractPage
import geb.Module

class LeadRetailerPage extends AbstractPage {

    static url = '/points/retailerName/retailerId'
    static at = { waitFor { title.contains('| Earn points from shopping at') } }

    static content = {


        name(wait: true, required: true) { $(".LeadArticle-content-desktop-info-title").text().trim() }

        description(wait: true) { $('.LeadArticle-content-desktop-info-description') }
        readMoreHideDescriptionButton(required: false) {
            $('.LeadArticle-content').find('.CollapseText-collapseButton')
        }

        maxCommission(wait: true, required: true) {
            $('.LeadArticle-content-desktop-points-title-amount', 1)
        }

        daysToConfirmPoints(wait: true, required: true) {
            $('.LeadArticle-content-desktop-points-info', 1)
        }
        earnPointsButton(wait: true, required: true) { $('.LeadArticle-content-desktop-points-button', 1) }
        checkOutOffersButton(required: false) { $('.LeadArticle-content-desktop-points-check', 1) }

        epointsDescriptionBlock(required: false) { $('.EarnPoints') }
        epointsDescriptionSignUpButton(required: false) { $('.EarnPoints-footer-join a') }

        offers(required: false) { $('.merchantOffer').moduleList(OfferModule) }

        termsAndConditionsTitle(required: false) { $('.TermsAndConditions-title') }
        termsAndConditionsContent(required: false) { $('.TermsAndConditions').find('.CollapseText') }
        readMoreHideTermsButton(required: false) { $('.TermsAndConditions').find('.CollapseText-collapseButton') }

        epointsRecomendationBlock(required: false) { $('.RetailerCTA') }
        epointsRecomendationSignUpButton(required: false) { $('.RetailerCTA .btn') }
    }
}


class OfferModule extends Module {

    static content = {
        commissionValue(required: true) { $('.merchantOffer-content-points') }
        maxCommissionBadge(required: false) { $('.merchantOffer-content-commission--rangeCommission') }
        forNewUsersOnly(required: false) { $('.merchantOffer-content-newUser') }
        title(required: true) { $('h3') }
        description(required: true) { $('p') }
        earnPointsButton(required: true) { $('.btn-earnPoints') }
    }

    int commissionValue() {
        return Integer.parseInt(commissionValue)
    }

}