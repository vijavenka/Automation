package com.iat.pages.checkout

import com.iat.pages.AbstractPage
import com.iat.pages.checkout.modules.BasketStepIndicatorModule

class EpointsBasketCompletePage extends AbstractPage {

    static url = '/checkout/complete'
    static at = {
        waitFor {
            title.contains('Checkout | epoints') && basketStepIndicatorModule.stepIndicator.hasClass('current-step-4')
        }
    }

    static content = {
        basketStepIndicatorModule { module BasketStepIndicatorModule }

        compleateHeader(wait: true) { $('h2') }
        cThankYouText(wait: true) { $('.box') }
        cFAQLink(wait: true) { cThankYouText.find('a') }
        cMyAccountButton(wait: true) { $('.CheckoutDelivery__buttons').find('.btn-grey') }
        cGetEpointsButton(wait: true) { $('.CheckoutDelivery__buttons').find('.btn-yellow') }
    }

    def clickFaqLink() { waitFor { cFAQLink.isDisplayed() }; cFAQLink.click() }

    def clickMyAccountButton() { waitFor { cMyAccountButton.isDisplayed() }; cMyAccountButton.click() }

    def clickGetEpointsButton() { waitFor { cGetEpointsButton.isDisplayed() }; cGetEpointsButton.click() }

}