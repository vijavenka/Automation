package com.iat.pages.rewards.modules

import geb.Module

class BuyEpointsModule extends Module {

    static content = {
        pointsToBuyDdl(required: true, wait: true) { $("") }
        pointsToBuyDdlOption(required: true, wait: true) { $("") }

        curentPointsNumber(required: true, wait: true) { $("") }

        closeButton(required: true, wait: true) { $("") }

        personalTab(required: true, wait: true) { $("") }
        buisnessTab(required: true, wait: true) { $("") }

        checkOrderEpoints(required: true, wait: true) { $("") }
        checkOrderAmount(required: true, wait: true) { $("") }
        checkOrderVat(required: false, wait: true) { $("") }
        checkOrderTotal(required: true, wait: true) { $("") }

        cardNumberInput(required: true, wait: true) { $("") }
        expirationDateInput(required: true, wait: true) { $("") }
        cvvInput(required: true, wait: true) { $("") }
        firstNameInput(required: true, wait: true) { $("") }
        lastNameInput(required: true, wait: true) { $("") }

        cancelButton(required: true, wait: true) { $("") }
        buyEpointsButton(required: true, wait: true) { $("") }
    }

    void fillCardDetailsForm(String cardNr, String expirationDate, String cvv, String firstName, String lastName) {
        cardNumberInput.value(cardNr)
        expirationDateInput.value(expirationDate)
        cvvInput.value(cvv)
        firstNameInput.value(firstName)
        lastNameInput.value(lastName)
    }

    void cancelBuyingEpoints() {
        cancelButton.click()
    }

    void buyEpoints() {
        buyEpointsButton.click()
    }

    void switchToTab(String tabName) {
        if (tabName == "Personal") {
            personalTab.click()
        } else {
            buisnessTab.click()
        }
    }

}
