package com.iat.pages.userAccount.purchaseEpoints

import com.iat.pages.AbstractPage

class PurchaseEpointsPage extends AbstractPage {

    static url = '/my-account/buy-epoints'
    static at = { waitFor { title.contains('Purchase epoints | epoints') } }

    static content = {

        //screen 1
        header { $('h1') }
        buyEpointsFormText { $('div', text: 'How many epoints do you want to purchase?') }
        buyEpointsForm { $('form', name: 'buyEpointsForm') }
        buyEpointsFormDDL { buyEpointsForm.find('.selectize-control') }
        buyEpointsFormDDLOption { $('.buyDropdown-option') }
        buyEpointsFormDDLOptionEpointsValue { buyEpointsFormDDLOption.find('.buyDropdown-epointsValue') }
        buyEpointsFormDDLOptionPoundsValue { buyEpointsFormDDLOption.find('.buyDropdown-moneyValue') }
        rewardsCounterAfterPurchaseSimulation { $('.AvailableItems') }
        buyEpointsReviewOrderButton(required: false) { buyEpointsForm.find('.btn-yellow') }

        loaderIcon(required: false) { $('div[ng-if="loaders.orderDetails"]') }

        //screen 2
        buyEpointsFormTextSecondScreen {
            $('div', text: 'Check the details of you order. Press confirm button to open online payment.')
        }
        buyEpointsFormSecondScreen { $('.buyEpointsPage-topPanel-content') }
        buyEpointsFormEpointsValueLabel { buyEpointsFormSecondScreen.find('label', 0) }
        buyEpointsFormEpointsValueField { buyEpointsFormSecondScreen.find('.form-group', 0).find('input') }
        buyEpointsFormValueLabel { buyEpointsFormSecondScreen.find('label', 1) }
        buyEpointsFormValueField { buyEpointsFormSecondScreen.find('.form-group', 1).find('input') }
        buyEpointsFormFeeLabel { buyEpointsFormSecondScreen.find('label', 2) }
        buyEpointsFormFeeField { buyEpointsFormSecondScreen.find('.form-group', 2).find('input') }
        buyEpointsFormVATLabel { buyEpointsFormSecondScreen.find('label', 3) }
        buyEpointsFormVATField { buyEpointsFormSecondScreen.find('.form-group', 3).find('input') }
        buyEpointsFormTotalLabel { buyEpointsFormSecondScreen.find('label', 4) }
        buyEpointsFormTotalField { buyEpointsFormSecondScreen.find('.form-group', 4).find('input') }
        buyEpointsCancelButton { buyEpointsFormSecondScreen.find('.btn-grey') }
        buyEpointsConfirmButton(required: false) { buyEpointsFormSecondScreen.find('.btn-success') }

        //on failure
        redirectInfoBasic { $('.buyEpointsPage-topPanel-content') }
        redirectInfoLinkBasic { redirectInfoBasic.find('a') }

        //on success
        paymentDetails { $('.buyEpointsPage-confirmation') }
        paymentDetailsHeader { $('.buyEpointsPage-confirmationHeader') }
        paymentDetailsLabelBasic { labelNumber -> paymentDetails.find('tr', labelNumber).find('td', 0) }
        paymentDetailsValueBasic { valueNumber -> paymentDetails.find('tr', valueNumber).find('td', 1) }

        printButton { $('.btn.hidden-print', 0) }
        goToProfileButton { $('.btn.hidden-print', 1) }
        spendEpointsButton { $('.btn.hidden-print', 2) }
    }

    def expandBuyEpointsDDL() { waitFor { buyEpointsFormDDL.isDisplayed() }; buyEpointsFormDDL.click() }

    def clickChosenBuyEpointsDDLOption(number) {
        waitFor { buyEpointsFormDDLOption[number].isDisplayed() }; buyEpointsFormDDLOption[number].click()
    }

    def clickReviewOrderButton() {
        waitFor { buyEpointsReviewOrderButton.isDisplayed() }
        buyEpointsReviewOrderButton.click()
    }

    def clickCancelButton() {
        waitFor { buyEpointsCancelButton.isDisplayed() }
        buyEpointsCancelButton.click()
    }

    def clickConfirmButton() { waitFor { buyEpointsConfirmButton.isDisplayed() }; buyEpointsConfirmButton.click() }

    def clickRedirectInfoLink() { waitFor { redirectInfoLinkBasic.isDisplayed() }; redirectInfoLinkBasic.click() }

    def clickSpendButtonOnSummaryPage() {
        waitFor {
            spendEpointsButton.isDisplayed()
        }
        spendEpointsButton.click()
    }

    def clickGoToProfileButtonOnSummaryPage() {
        waitFor { goToProfileButton.isDisplayed() }
        goToProfileButton.click()
    }
}
