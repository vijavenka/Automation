package com.iat.pages.checkout

import com.iat.pages.AbstractPage
import com.iat.pages.checkout.modules.BasketStepIndicatorModule

class EpointsBasketDeliveryDetailsPage extends AbstractPage {

    static url = '/checkout/delivery'
    static at = {
        waitFor {
            title.contains('Checkout | epoints') && basketStepIndicatorModule.stepIndicator.hasClass('current-step-2')
        }
    }

    static content = {
        basketStepIndicatorModule { module BasketStepIndicatorModule }

        deliveryDetailsHeader(wait: true) { $('h2') }
        ddItemsSelectionBox(wait: true) { $('.items-selection') }
        productsSection(wait: true) { $('.CheckoutDelivery__items') }
        productsSectionEditButton(wait: true) { $('.CheckoutDelivery__editLink', 0) }
        ddItemsSelectionEditButton(wait: true) { ddItemsSelectionBox.find('.CheckoutDelivery__editLink') }
        ddItemsSelectionRedemptionNameBasic(wait: true) { ddItemsSelectionBox.find('.CheckoutDelivery__itemDesc') }
        ddItemsSelectionRedemptionPointsValueBasic(wait: true) {
            ddItemsSelectionBox.find('.CheckoutDelivery__itemCost')
        }
        ddItemsSelectionTotalEpointsNeeded(wait: true) {
            ddItemsSelectionBox.find('.CheckoutDelivery__total').find('.cost-value')
        }
        ddDeliveryInformationBox(wait: true) { $('.box').find('h3', text: 'Delivery information') }
        ddNextButton(wait: true) { $('.CheckoutDelivery__buttons').find('.btn-yellow') }
        ddBackButton(wait: true) { $('.CheckoutDelivery__buttons').find('.btn-grey') }
        ddDeliveryAddresCardBasic(wait: true) { $('.CheckoutDelivery__addressCard') }
        ddDeliveryAddresCardDetailsBasic(wait: true) { ddDeliveryAddresCardBasic.find('.CheckoutDelivery__address') }
        ddDeliveryAddresSecondCardDetailsBasic(wait: true) {
            ddDeliveryAddresCardBasic[1].find('.CheckoutDelivery__address')
        }
        ddDeliveryAddresCardPlusButton(wait: true) { ddDeliveryAddresCardBasic.find('.CheckoutDelivery__plus') }
        ddDeliveryAddresCardAddNewAddressButton(wait: true) {
            ddDeliveryAddresCardBasic.find('a[translate="checkoutPage.step2.addNewAddress"]')
        }

        ddInternalisationModalBasic(wait: true, required: false) { $('.modal-body') }
        ddInternalisationModalButtonBasic(wait: true, required: false) {
            ddInternalisationModalBasic.find('.btn-yellow')
        }
        //new address form
        deliveryForm(wait: true) { $('.CheckoutDelivery__form') }
        cancelButton(wait: true) { deliveryForm.find('.CheckoutDelivery__formCancel') }
        firstNameLabel(wait: true) { deliveryForm.find('label', 0) }
        firstNameInputField(wait: true) { deliveryForm.find('input', name: 'firstName') }
        lastNameLabel(wait: true) { deliveryForm.find('label', 1) }
        lastNameInputField(wait: true) { deliveryForm.find('input', name: 'lastName') }
        houseNumberLabel(wait: true) { deliveryForm.find('label', 2) }
        houseNumberInputField(wait: true) { deliveryForm.find('input', name: 'house') }
        streetLabel(wait: true) { deliveryForm.find('label', 3) }
        streetInputField(wait: true) { deliveryForm.find('input', name: 'street') }
        townLabel(wait: true) { deliveryForm.find('label', 4) }
        townInputField(wait: true) { deliveryForm.find('input', name: 'city') }
        coutyLabel(wait: true) { deliveryForm.find('label', 5) }
        countyInputField(wait: true) { deliveryForm.find('input', name: 'county') }
        postcodeLabel(wait: true) { deliveryForm.find('label', 6) }
        postcodeInputField(wait: true) { deliveryForm.find('input', name: 'postCode') }
        coutryLabel(wait: true) { deliveryForm.find('label', 7) }
        findAddressButton(wait: true) { deliveryForm.find('.btn-green') }
        postcodeDDL(wait: true) { deliveryForm.find('.CheckoutDelivery__suggested') }
        postcodeDDLOption(wait: true) { postcodeDDL.find('option') }
        coutryDDL(wait: true) { deliveryForm.find('.changeCountry') }
        coutryDDLOption(wait: true) { coutryDDL.find('option') }
        contactNumberLabel(wait: true) { deliveryForm.find('label', 8) }
        contactNumberInputField(wait: true) { deliveryForm.find('input', name: 'phoneNo') }
        useAsContactDetailsCheckbox(wait: true) { deliveryForm.find('span', text: 'Use this as my contact details') }
        validErrorBasic(wait: true) { $('.valid-error') }
    }

    def clickEditButtonInProductsSelectionBox() {
        waitFor { productsSectionEditButton.isDisplayed() }; productsSectionEditButton.click()
    }

    def clickBackButtonOnDeliveryDetailsPage() {
        waitFor { ddBackButton.isDisplayed() }
        sleep(2000)
        ddBackButton.click()
    }

    def clickNextButtonOnDeliveryDetailsPage() {
        waitFor { ddNextButton.isDisplayed() }
        sleep(2000)
        ddNextButton.click()
    }

    def clickAddNewAddresButton() {
        waitFor { ddDeliveryAddresCardPlusButton.isDisplayed() }; ddDeliveryAddresCardPlusButton.click()
    }
    //new address form
    def clickCancelButtonOnNewAddressForm() { waitFor { cancelButton.isDisplayed() }; cancelButton.click() }

    def enterFirstName(firstName) {
        waitFor { firstNameInputField.isDisplayed() }; firstNameInputField.value(firstName)
    }

    def enterLastName(lastName) { waitFor { lastNameInputField.isDisplayed() }; lastNameInputField.value(lastName) }

    def enterHouseNumber(houseNumber) {
        waitFor { houseNumberInputField.isDisplayed() }; houseNumberInputField.value(houseNumber)
    }

    def enterStreetName(streetName) { waitFor { streetInputField.isDisplayed() }; streetInputField.value(streetName) }

    def enterTownName(townName) { waitFor { townInputField.isDisplayed() }; townInputField.value(townName) }

    def enterCountyName(countyName) { waitFor { countyInputField.isDisplayed() }; countyInputField.value(countyName) }

    def enterPostCode(postcode) {
        waitFor { postcodeInputField.isDisplayed() }; postcodeInputField.value(postcode); sleep(1000)
    }

    def clickFindAddressButton() { waitFor { findAddressButton.isDisplayed() }; findAddressButton.click() }

    def expandAddressDDL() { waitFor { postcodeDDL.isDisplayed() }; postcodeDDL.click() }

    def clickChosenAddressDDLOption(number) {
        waitFor { postcodeDDLOption[number].isDisplayed() }; postcodeDDLOption[number].click()
    }

    def expandCountryDDL() { waitFor { coutryDDL.isDisplayed() }; coutryDDL.click() }

    def clickChosenCountryDDLOption(number) {
        waitFor { coutryDDLOption[number].isDisplayed() }; coutryDDLOption[number].click()
    }

    def enterContactNumber(number) {
        waitFor { contactNumberInputField.isDisplayed() }; contactNumberInputField.value(number)
    }

    def fillAllNewAddressFormFields(firstName, lastName, houseNr, streetName, townName, countyName, postcode, country, number) {
        enterFirstName(firstName)
        enterLastName(lastName)
        enterHouseNumber(houseNr)
        enterStreetName(streetName)
        enterTownName(townName)
        enterCountyName(countyName)
        if (ddInternalisationModalBasic.isDisplayed()) {
            ddInternalisationModalButtonBasic.click()
            sleep(1000)
        }
        enterPostCode(postcode)
        expandCountryDDL()
        if (country == 'UK')
            clickChosenCountryDDLOption(0)
        else if (country == 'IE')
            clickChosenCountryDDLOption(1)
        enterContactNumber(number)
    }

    def selectUseAsMyContactDetailsCheckbox() {
        waitFor { useAsContactDetailsCheckbox.isDisplayed() }; useAsContactDetailsCheckbox.click()
    }

}