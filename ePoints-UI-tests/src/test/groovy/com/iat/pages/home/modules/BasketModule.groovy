package com.iat.pages.home.modules

import geb.Module

class BasketModule extends Module {

    static content = {
        basketBorder(wait: true)  { $('.OrderItems') }
        basketTitle(wait: true)  { basketBorder.$('.OrderItem__title a') }
        increaseQuantity(wait: true) { basketBorder.$('button.btn.btn-sm.OrderItem__btn') }

        basketPanel(wait: true) { $('div.Basket-in-header') }
        redemptionImage(wait: true) { $('.Basket-imageContainer') }
        redemptionTitle(wait: true) { $('.Basket-title') }
        redemptionQuantity(wait: true) { $('.Basket-quantity') }

        basketPanelViewAllRedemptionsLink(wait: true) { basketPanel.find('a[translate="basket.viewRewards"]') }
    }

    //Atomic operations for Basket Module
    def clickOnBasketPanelViewAllRedemptionsLink() {
        waitFor { basketPanelViewAllRedemptionsLink.isDisplayed() }; basketPanelViewAllRedemptionsLink.click()
    }

    String BasketTitle() {
        sleep(1000)
        return basketTitle.text().trim()
    }

    def increaseQuantity() {
        waitFor { increaseQuantity[1].isDisplayed()
        }; increaseQuantity[1].click()
    }
}
