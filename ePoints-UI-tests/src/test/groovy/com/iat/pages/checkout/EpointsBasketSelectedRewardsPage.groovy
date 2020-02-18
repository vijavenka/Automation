package com.iat.pages.checkout

import com.iat.pages.AbstractPage
import com.iat.pages.checkout.modules.BasketStepIndicatorModule

class EpointsBasketSelectedRewardsPage extends AbstractPage {

    static url = '/checkout/rewards'
    static at = {
        waitFor {
            title.contains('Checkout | epoints') && basketStepIndicatorModule.stepIndicator.hasClass('current-step-1') || title.contains('Geselecteerde geschenken') || title.contains('https://nl-qa.epoints.com/checkout/rewards')
        }
    }

    static content = {
        basketStepIndicatorModule { module BasketStepIndicatorModule }

        //Step 1 - Basket Page
        selectedRewardsHeader(wait: true) { $('h2') }
        basicBasketRedemption(wait: true, required: false) { $('.OrderItem__item') }
        redemptionImageBasic(wait: true) { basicBasketRedemption.find('.OrderItem__imageCircle') }
        redemptionNameBasic(wait: true) { basicBasketRedemption.find('.OrderItem__title') }
        redemptionRemoveButtonBasic(wait: true) {
            basicBasketRedemption.find('.OrderItem__removeItem')
        }
        redemptionRemoveInfo(wait: true) { $('.OrderItem__removeItem').find('.popup').find('div') }
        redemptionRemoveCancelButton(wait: true) {
            $('.OrderItem__removeItem').find('.popup').find('.btn-danger')
        }
        redemptionRemoveDeleteButton(wait: true) {
            $('.OrderItem__removeItem').find('.popup').find('.btn-green')
        }
        redemptionNumberBasic(wait: true) {
            basicBasketRedemption.find('.OrderItem__quantityValueInput')
        }
        redemptionQuantityMinus(wait: true) {
            basicBasketRedemption.find('.OrderItem__quantityDecrease')
        }
        redemptionQuantityPlus(wait: true) {
            basicBasketRedemption.find('.OrderItem__quantityIncrease')
        }
        redemptionPointsValue(wait: true) { basicBasketRedemption.find('.cost-value') }
        basketRemoveAllItemsLink(wait: true) { $('div.remove-all-items') }
        basketRemoveAllPopUp(wait: true) { $('div[ng-show="popup.removeAll"]') }
        basketRemoveAllPopUpInformation(wait: true) {
            $('div[translate="checkoutPage.step1.removeAllItemsConfirmation"]')
        }
        basketRemoveAllPopUpCancelLink(wait: true) {
            basketRemoveAllPopUp.find('a[translate="commons.cancel"]', 0)
        }
        basketRemoveAllPopUpDeleteLink(wait: true) {
            basketRemoveAllPopUp.find('a[translate="commons.delete"]', 0)
        }
        basketBackToRewardsPageLink(wait: true) {
            $('a[translate="checkoutPage.step1.backToRewards"]')
        }
        totalEpointsNeededText(wait: true) { $('.pull-right') }
        totalEpointsNeededValue(wait: true) { totalEpointsNeededText.find('.cost-value') }
        orderRewardButton(wait: true) { $('#orderNavigationButtons').find('.btn-green') }
        notEnoughPointsAlert(wait: true) { $('.not-enough-points-alert') }
        noRewardsInBasketInformation(wait: true) { $('.no-rewards') }
    }

    //Atomic Actions on Basket Page
    def clickOrderRewardButton() { waitFor { orderRewardButton.isDisplayed() }; orderRewardButton.click() }

    def clickOnBasketRemoveAllItemsLink() {
        waitFor { basketRemoveAllItemsLink.isDisplayed() }; basketRemoveAllItemsLink.click()
    }

    def clickOnBasketRemoveAllPopUpDeleteLink() {
        waitFor { basketRemoveAllPopUpDeleteLink.isDisplayed() }; basketRemoveAllPopUpDeleteLink.click()
    }

    def clickOnBasektRemoveAllPopUpCancelLink() {
        waitFor { basketRemoveAllPopUpCancelLink.isDisplayed() }; basketRemoveAllPopUpCancelLink.click()
    }

    def clickOnBasketBackToRewardsPageLink() {
        waitFor { basketBackToRewardsPageLink.isDisplayed() }; basketBackToRewardsPageLink.click()
    }

    def clickIncreaseButton() { waitFor { redemptionQuantityPlus.isDisplayed() }; redemptionQuantityPlus.click() }

    def clickDecreaseButton() { waitFor { redemptionQuantityMinus.isDisplayed() }; redemptionQuantityMinus.click() }

    def clickRemoveButtonOfChosenProduct(chosenProductNumber) {
        waitFor { redemptionRemoveButtonBasic[chosenProductNumber].isDisplayed() }
        redemptionRemoveButtonBasic[chosenProductNumber].click()
    }

    def clickRemoveItemYesButton(chosenProductNumber) {
        waitFor { redemptionRemoveDeleteButton[chosenProductNumber].isDisplayed() }
        redemptionRemoveDeleteButton[chosenProductNumber].click()
    }

    //More complex logic for Basket Page
    def removeAllRedemptionItemsFromBasket() {
        clickOnBasketRemoveAllItemsLink()
        clickOnBasketRemoveAllPopUpDeleteLink()
        waitFor { headerModule.headerBasketCounter == 0 }
    }
}