package com.iat.pages.home.modules

import geb.Module

class RetailerModule extends Module {

    static content = {
        title(required: true) { $(".BlockRetailerCardSmall-title").text().trim() }
        description(required: true) { $(".BlockRetailerCardSmall-description").text().trim() }
        seeMoreLink(required: true) { $(".seeMore a") }
        retailerCards(required: true) { $(".RetailerCardSmall").moduleList(RetailerCardModule) }
    }

    def retailerCard(int index) {
        return retailerCards[index]
    }

    def retailerCard(String name) {
        int index = retailerCards.findIndexOf { it.altTextFromLogo == "$name logo" }
        return retailerCard(index)
    }

    def clickSeeMoreLink() {
        seeMoreLink.click()
    }
}

class RetailerCardModule extends Module {
    static content = {
        url(required: true) {
            String url = $("a").@href
            url
        }
        altTextFromLogo(required: true) { $("img").@alt }

        specialText(required: false) { $(".RetailerCardSmall-content p")[0].text().trim() }
        specialPoints(required: false) {
            $(".RetailerCardSmall-content p")[1].text().trim().replaceAll(",", "").toInteger()
        }
    }
}