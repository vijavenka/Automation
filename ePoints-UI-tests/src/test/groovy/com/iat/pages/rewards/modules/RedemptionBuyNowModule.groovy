package com.iat.pages.rewards.modules

import geb.Module

class RedemptionBuyNowModule extends Module {

    static content = {

        basketWidget(wait: true) { $('div.productBasketWidget') }
        toBasketButton(wait: true) { basketWidget.find('button.productBasketWidget-addToBasket.btn.btn-block') }
    }

    def clickAddToBasketButton() {
        waitFor(1000) { toBasketButton.isDisplayed() }
        toBasketButton.click()
    }
}