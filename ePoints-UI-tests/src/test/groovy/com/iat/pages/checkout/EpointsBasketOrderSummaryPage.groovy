package com.iat.pages.checkout

import com.iat.pages.AbstractPage
import com.iat.pages.checkout.modules.BasketStepIndicatorModule

class EpointsBasketOrderSummaryPage extends AbstractPage {

    static url = '/checkout/delivery'
    static at = {
        waitFor {
            title.contains('Checkout | epoints') && basketStepIndicatorModule.stepIndicator.hasClass('current-step-3')
        }
    }

    static content = {
        basketStepIndicatorModule { module BasketStepIndicatorModule }

        orderSummaryHeader(wait: true) { $('h2') }
        productsSection(wait: true) { $('.CheckoutDelivery__items') }
        productsSectionEditButton(wait: true) { $('.CheckoutDelivery__editLink', 0) }
        osRedemptionImageBasic(wait: true) { productsSection.find('.CheckoutDelivery__itemImageCircle') }
        osRedemptionNameBasic(wait: true) { productsSection.find('.CheckoutDelivery__itemDesc') }
        osRedemptionPointsValue(wait: true) { productsSection.find('.cost-value') }
        osTotalEpointsNeeded(wait: true) { $('.CheckoutDelivery__total').find('.cost-value') }
        deliveryDetailsSection(wait: true) { $('.order-address') }
        deliverySectionEditButon(wait: true) { $('.CheckoutDelivery__editLink', 1) }
        osDeliveryDetailsBasic(wait: true) { $('.CheckoutDelivery__address') }
        osBackButton(wait: true) { $('.CheckoutDelivery__buttons').find('.btn-grey') }
        osPlaceOrderButton(wait: true) { $('.CheckoutDelivery__buttons').find('.btn-yellow') }
    }

    def clickProductsEditButton() {
        waitFor { productsSectionEditButton.isDisplayed() }; productsSectionEditButton.click()
    }

    def clickDeliveryAddressEditButton() {
        waitFor { deliverySectionEditButon.isDisplayed() }; deliverySectionEditButon.click()
    }

    def clickBackButtonOnOrderSummaryPage() {
        waitFor { osBackButton.isDisplayed() }; sleep(2000); osBackButton.click()
    }

    def clickPlaceOrderButton() {
        waitFor { osPlaceOrderButton.isDisplayed() }; sleep(2000); osPlaceOrderButton.click()
    }
}