package com.iat.pages.rewards.modules

import com.iat.pages.rewards.EpointsRewardsPage
import geb.Module

class RedemptionCardModule extends Module {

    static content = {
        //redemptionExclusiveBadge(wait: true, required: false) { $("") }
        category(wait: true, to: EpointsRewardsPage) { $(".ProductCard-category") }
        image(wait: true) { $(".ProductCard-image") }
        title(wait: true) {
            def title = $(".ProductCard-title")
            title.isDisplayed() ? title.text().trim() : ""
        }
        cost(wait: true) {
            def cost = $(".ProductCard-priceValue")
            cost.isDisplayed() ? cost.text().trim() : ""
        }
        costInt() {
            !cost.isEmpty() ? cost.replaceAll(",", "").toInteger() : 0
        }
        originalCost(required: false, wait: true) { $(".ProductCard-oryginalValue").text().trim() }
        toBasketText(required: false, wait: true) {
            def text = $(".ProductCard-inBasketMessage")
            text.isDisplayed() ? text.text().trim() : ""
        }
        toBasketButton(wait: true) { $(".icon-svg.x2") }
        toBasketCounter(required: false, wait: true) {
            def counter = $(".ProductCard-basketQuantity")
            counter.isDisplayed() ? counter.text().trim().toInteger() : 0
        }
        badge(required: false, wait: 3) {
            def badge = $(".ProductCard-badge")
            badge.isDisplayed() ? badge.text().trim() : ""
        }
        link(required: true) { $(".ProductCard-more").@href }
    }

    def clickAddToBasketButton() {
        waitFor { toBasketButton.isDisplayed() }
        toBasketButton.click()
    }
}
