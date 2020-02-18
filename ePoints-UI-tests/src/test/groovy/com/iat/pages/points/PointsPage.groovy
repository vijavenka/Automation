package com.iat.pages.points

import com.iat.pages.points.pointsPageModules.AbstractPointsPage
import com.iat.pages.points.pointsPageModules.RetailerCardModule

class PointsPage extends AbstractPointsPage {

    static url = '/points/online'
    static at = { waitFor { browser.currentUrl.contains("/online") } }

    static content = {

        roulettBanner(required: true, wait: true) { $(".ConfigBlock-blockWithBaner") }

        retailersList(wait: true) { $('.custom-collection-view li').moduleList(RetailerCardModule) }

        departmentSeoHeading(wait: true) { $('h1[ng-if="pageSeo.heading"]') }
        departmentSeoDescription(wait: true) { $('p[ng-if="pageSeo.copy"]') }
        favouriteButtonDisabled(required: false) { $('.show-favourites.disabled') }
        favouriteButtonEnabled(wait: true) { $('.show-favourites') }
        recentlyVisitedRetailersHeader(wait: true) { $('.RecentlyViewed-view') }
        recentlyVisitedRetailersRetailerElement(wait: true) {
            recentlyVisitedRetailersHeader.find('.RecentlyViewed-link')
        }

        loader(required: false) { $('.Loader-wrapper') }
        //search
        searchComponent(wait: true) { $('.azSearch-search') }
        searchField(wait: true) { searchComponent.find('#searchAz') }
        searchButton(wait: true) { searchComponent.find('.btn-yellow') }
        searchResultsDDL(required: false) { $('.ui-autocomplete') }
        searchResultsDDLOption(required: false) { searchResultsDDL.find('.ui-menu-item') }
        noResultsFoundMessage(wait: true) { $('.no-results') }
        //search

        //leads promoted merchants module//
        promotedLeadsRetailesModule(wait: true) { $('.PromotedMerchants') }
        promotedLeadsRetailesModuleTitle(wait: true) { promotedLeadsRetailesModule.$('.PromotedMerchants-title') }
        promotedLeadsRetailesModuleDescription(wait: true) {
            promotedLeadsRetailesModule.$('.PromotedMerchants-description')
        }
        promotedLeadsRetailer(wait: true) { promotedLeadsRetailesModule.$('.PromotedMerchants-merchantContainer') }
        promotedLeadsRetailerImage(wait: true) { promotedLeadsRetailer.$('img') }
        promotedLeadsRetailerMaxCommision(wait: true) {
            promotedLeadsRetailer.$('.PromotedMerchants-earnPoints--boldQuantity')
        }
        promotedLeadsRetailerCheckButton(wait: true) { promotedLeadsRetailer.$('a') }
        promotedLeadsRetailesModuleCheckMoreLeadsButton(wait: true) {
            promotedLeadsRetailesModule.$('.PromotedMerchants-moreOffersButton')
        }
        //leads promoted merchants module//

    }

    def clickShowFavouritesButton() {

        waitFor {
            sleep(3000)
            favouriteButtonEnabled.isDisplayed()
        }
        favouriteButtonEnabled.click()
    }

    def clickChosenRetailerFromRecentlyVisited(number) {
        waitFor { recentlyVisitedRetailersRetailerElement[number].isDisplayed() }
        recentlyVisitedRetailersRetailerElement[number].click()
    }
    //search
    def enterPhraseIntoSearch(phrase) { waitFor { searchField.isDisplayed() }; searchField.value(phrase) }

    def clickSearchButton() { waitFor { searchButton.isDisplayed() }; searchButton.click() }
}