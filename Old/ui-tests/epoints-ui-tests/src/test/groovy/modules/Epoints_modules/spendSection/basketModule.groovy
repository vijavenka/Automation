package modules.Epoints_modules.spendSection

import geb.Module

/**
 * Created by kbaranowski on 2014-11-07.
 */
class basketModule extends Module{
    static content = {
        //basket preview
        basketSmallIcon{ $('.Basket-trigger').find('svg') }
        basketSmallPreview(required: false){ $('.Basket-trigger').find('span',0) }
        basketSmallPreviewOption(required: false){ $('.Basket-dropdown').find('.Basket-title') }
        basketSmallPreviewOptionQuantity(required: false){ $('.Basket-dropdown').find('.Basket-quantity') }
        basketSmallViewSelectedRewardsButton(required: false){ $('.Basket-dropdown').find('.btn-yellow') }

        //steps on all tabs
        stepIndicator{ $('#stepIndicator') }
        selectedRewardsStep{ stepIndicator.find('.step-label',0) }
        deliveryDetailsStep{ stepIndicator.find('.step-label',1) }
        orderSummaryStep{ stepIndicator.find('.step-label',2) }
        completeStep{ stepIndicator.find('.step-label',3) }
        stepCircle{ stepIndicator.find('.step-circle') }

        //basket selected rewards
        selectedRewardsHeader{ $('h2') }
        basicBasketRedemption(required: false){ $('.OrderItem__item') }
        redemptionImageBasic{ basicBasketRedemption.find('.OrderItem__imageCircle') }
        redemptionNameBasic{ basicBasketRedemption.find('.OrderItem__title') }
        redemptionRemoveButtonBasic{ basicBasketRedemption.find('.OrderItem__removeItem') }
            redemptionRemoveInfo{ $('.OrderItem__removeItem').find('.popup').find('div') }
            redemptionRemoveCancelButton{ $('.OrderItem__removeItem').find('.popup').find('.btn-danger') }
            redemptionRemoveDeleteButton{ $('.OrderItem__removeItem').find('.popup').find('.btn-green') }
        redemptionNumberBasic{ basicBasketRedemption.find('.OrderItem__quantityValueInput') }
        redemptionQuantityMinus{ basicBasketRedemption.find('.OrderItem__quantityDecrease') }
        redemptionQuantityPlus{ basicBasketRedemption.find('.OrderItem__quantityIncrease') }
        redemptionPointsValue{ basicBasketRedemption.find('.cost-value') }
        removeAllItemsButton{ $('.remove-all-items') }
            redemptionRemoveAllInfo{ $('#orderItemsSummary').find('.popup').find('div') }
            redemptionRemoveAllCancelButton{ $('#orderItemsSummary').find('.popup').find('.btn-danger') }
            redemptionRemoveAllDeleteButton{ $('#orderItemsSummary').find('.popup').find('.btn-green') }
        totalEpointsNeededText{ $('.pull-right') }
        totalEpointsNeededValue{ totalEpointsNeededText.find('.cost-value') }
        backToRewardsButton{ $('#orderNavigationButtons').find('.btn-grey') }
        orderRewardButton{ $('#orderNavigationButtons').find('.btn-green') }
        notEnoughPointsAlert{ $('.not-enough-points-alert') }
        noRewardsInBasketInformation{ $('.no-rewards') }

        //basket delivery details
        deliveryDetailsHeader{ $('h2') }
        ddNextbutton{ $('.CheckoutDelivery__buttons').find('.btn-yellow') }
        ddItemsSelectionBox{ $('.items-selection') }
        ddItemsSelectionEditButton{ ddItemsSelectionBox.find('.CheckoutDelivery__editLink') }
        ddItemsSelectionRedemptionNameBasic{ ddItemsSelectionBox.find('.CheckoutDelivery__itemDesc') }
        ddItemsSelectionRedemptionPointsValueBasic{ ddItemsSelectionBox.find('.cost-value') }
        ddItemsSelectionTotalEpointsNeeded{ ddItemsSelectionBox.find('.CheckoutDelivery__total').find('.cost-value') }
        ddDeliveryInformationBox{ $('.box').find('h3', text: 'Delivery information') }
        ddNextButton{ $('.CheckoutDelivery__buttons').find('.btn-grey') }
        ddBackButton{ $('.CheckoutDelivery__buttons').find('.btn-yellow') }
        ddDeliveryAddresCardBasic{ $('.CheckoutDelivery__addressCard') }
        ddDeliveryAddresCardDetailsBasic{ ddDeliveryAddresCardBasic.find('.CheckoutDelivery__address') }
        ddDeliveryAddresSecondCardDetailsBasic{ ddDeliveryAddresCardBasic[1].find('.CheckoutDelivery__address') }
        ddDeliveryAddresCardPlusButton{ ddDeliveryAddresCardBasic.find('.CheckoutDelivery__plus') }
        ddDeliveryAddresCardAddNewAddressButton{ ddDeliveryAddresCardBasic.find('.btn-grey') }

        ddInternalisationModalBasic(required: false){ $('.modal-body') }
        ddInternalisationModalButtonBasic(required: false){ ddInternalisationModalBasic.find('.btn-yellow') }
        //new address form
        deliveryForm{ $('.CheckoutDelivery__form') }
        cancelButton{ deliveryForm.find('.CheckoutDelivery__formCancel') }
        firstNameLabel{ deliveryForm.find('label',0) }
        firstNameInputField{ deliveryForm.find('input', name: 'firstName') }
        lastNameLabel{ deliveryForm.find('label',1) }
        lastNameInputField{ deliveryForm.find('input', name: 'lastName') }
        houseNumberLabel{ deliveryForm.find('label',2) }
        houseNumberInputField{ deliveryForm.find('input', name: 'house') }
        streetLabel{ deliveryForm.find('label',3) }
        streetInputField{ deliveryForm.find('input', name: 'street') }
        townLabel{ deliveryForm.find('label',4) }
        townInputField{ deliveryForm.find('input', name: 'city') }
        coutyLabel{ deliveryForm.find('label',5) }
        countyInputField{ deliveryForm.find('input', name: 'county') }
        coutryLabel{ deliveryForm.find('label',6) }
        coutryDDL{ deliveryForm.find('select', name: 'country') }
        coutryDDLOption{ coutryDDL.find('option') }
        postcodeLabel{ deliveryForm.find('label',7) }
        postcodeInputField{ deliveryForm.find('input', name: 'postCode') }
        findAddressButton{ deliveryForm.find('.btn-green') }
        postcodeDDL{ deliveryForm.find('.CheckoutDelivery__suggested') }
        postcodeDDLOption{ postcodeDDL.find('option') }
        useAsContactDetailsCheckbox{ deliveryForm.find('span', text: 'Use this as my contact details') }
        validErrorBasic{ $('.valid-error') }

        //basket order summary
        orderSummaryHeader{ $('h2') }
        productsSection{ $('.CheckoutDelivery__items') }
        productsSectionEditButton{ $('.CheckoutDelivery__editLink',0) }
        osRedemptionImageBasic{ productsSection.find('.CheckoutDelivery__itemImageCircle') }
        osRedemptionNameBasic{ productsSection.find('.CheckoutDelivery__itemDesc') }
        osRedemptionPointsValue{ productsSection.find('.cost-value') }
        osTotalEpointsNeeded{ $('.CheckoutDelivery__total').find('.cost-value') }
        deliveryDetailsSection{ $('.order-address') }
        deliverySectionEditButon{ $('.CheckoutDelivery__editLink',1) }
        osDeliveryDetailsBasic{ $('.CheckoutDelivery__address') }
        osBackButton{ $('.CheckoutDelivery__buttons').find('.btn-grey') }
        osPlaceOrderButton{ $('.CheckoutDelivery__buttons').find('.btn-yellow') }

        //basket complete
        cThankYouText{ $('.box') }
        cFAQLink{ cThankYouText.find('a') }
        cMyAccountButton{ $('.CheckoutDelivery__buttons').find('.btn-grey') }
        cGetEpointsButton{ $('.CheckoutDelivery__buttons').find('.btn-yellow') }
    }
    //basket preview
    def openBasketPreview(){ waitFor{ basketSmallIcon.isDisplayed() }; basketSmallIcon.click() }
    def clickViewSelectedRewardButton(){ waitFor{ basketSmallViewSelectedRewardsButton.isDisplayed() }; basketSmallViewSelectedRewardsButton.click() }

    //basket selected rewards
    def clickOrderRewardButton(){ waitFor{ orderRewardButton.isDisplayed() }; orderRewardButton.click() }
    def clickRemoveAllItemsButton(){ waitFor{ removeAllItemsButton.isDisplayed() }; removeAllItemsButton.click() }
    def clickRemoveAllItemsYesButton(){ waitFor{ redemptionRemoveAllDeleteButton.isDisplayed() }; redemptionRemoveAllDeleteButton.click() }
    def clickRemoveAllItemsNoButton(){ waitFor{ redemptionRemoveAllCancelButton.isDisplayed() }; redemptionRemoveAllCancelButton.click() }
    def clickBackToRewardsButton(){ waitFor{ backToRewardsButton.isDisplayed() }; backToRewardsButton.click() }
    def clickIncreaseButton(){ waitFor{ redemptionQuantityPlus.isDisplayed() }; redemptionQuantityPlus.click() }
    def clickDecreaseButton(){ waitFor{ redemptionQuantityMinus.isDisplayed() }; redemptionQuantityMinus.click() }
    def clickRemoveButtonOfChosenProduct(number){ waitFor{ redemptionRemoveButtonBasic[number].isDisplayed() }; redemptionRemoveButtonBasic[number].click() }
    def clickRemoveItemYesButton(){ waitFor{ redemptionRemoveDeleteButton.isDisplayed() }; redemptionRemoveDeleteButton.click() }

    //basket delivery details
    def clickEditButtonInProductsSelectionBox(){ waitFor{ productsSectionEditButton.isDisplayed() }; productsSectionEditButton.click() }
    def clickBackButtonOnDeliveryDetailsPage(){ waitFor{ ddBackButton.isDisplayed() }; ddBackButton.click() }
    def clickNextButtonOnDeliveryDetailsPage(){ waitFor{ ddNextbutton.isDisplayed() }; ddNextbutton.click() }
    def clickAddNewAddresButton(){ waitFor{ ddDeliveryAddresCardPlusButton.isDisplayed() }; ddDeliveryAddresCardPlusButton.click() }
    //new address form
    def clickCancelButtonOnNewAddressForm(){ waitFor{ cancelButton.isDisplayed() }; cancelButton.click() }
    def enterPostCode(postcode){ waitFor{ postcodeInputField.isDisplayed() }; postcodeInputField.value(postcode) }
    def clickFindAddressButton(){ waitFor{ findAddressButton.isDisplayed() }; findAddressButton.click() }
    def expandAddressDDL(){ waitFor{ postcodeDDL.isDisplayed() }; postcodeDDL.click() }
    def clickChosenAddressDDLOption(number){ waitFor{ postcodeDDLOption[number].isDisplayed() }; postcodeDDLOption[number].click() }
    def enterFirstName(firstName){ waitFor{ firstNameInputField.isDisplayed() }; firstNameInputField.value(firstName) }
    def enterLastName(lastName){ waitFor{ lastNameInputField.isDisplayed() }; lastNameInputField.value(lastName) }
    def enterHouseNumber(houseNumber){ waitFor{  houseNumberInputField.isDisplayed() };  houseNumberInputField.value(houseNumber) }
    def enterStreetName(streetName){ waitFor{ streetInputField.isDisplayed() }; streetInputField.value(streetName) }
    def enterTownName(townName){ waitFor{ townInputField.isDisplayed() }; townInputField.value(townName) }
    def enterCountyName(countyName){ waitFor{ countyInputField.isDisplayed() }; countyInputField.value(countyName) }
    def expandCountryDDL(){ waitFor{ coutryDDL.isDisplayed() }; coutryDDL.click() }
    def clickChosenCountryDDLOption(number){ waitFor{ coutryDDLOption[number].isDisplayed() }; coutryDDLOption[number].click() }
    def fillAllNewAddressFormFields(firstName,lastName,houseNr,streetName,townName,countyName,country,postcode){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterHouseNumber(houseNr);
        enterStreetName(streetName);
        enterTownName(townName);
        enterCountyName(countyName);
        if(ddInternalisationModalBasic.isDisplayed()){
            ddInternalisationModalButtonBasic.click()
            Thread.sleep(1000)
        }
        expandCountryDDL()
        if(country == 'UK'){
            clickChosenCountryDDLOption(0)
        }else if(country == 'IE'){
            clickChosenCountryDDLOption(1)
        }
        enterPostCode(postcode)}
    def selectUseAsMyContactDetailsCheckbox(){ waitFor{ useAsContactDetailsCheckbox.isDisplayed() }; useAsContactDetailsCheckbox.click() }

    //basket order summary
    def clickProductsEditButton(){ waitFor{ productsSectionEditButton.isDisplayed() }; productsSectionEditButton.click() }
    def clickDeliveryAddressEditButton(){ waitFor{ deliverySectionEditButon.isDisplayed() }; deliverySectionEditButon.click() }
    def clickBackButtonOnOrderSummaryPage(){ waitFor{ osBackButton.isDisplayed() }; osBackButton.click() }
    def clickPlaceOrderButton(){ waitFor{ osPlaceOrderButton.isDisplayed() }; osPlaceOrderButton.click() }

    //basket complete
    def clickFaqLink(){ waitFor{ cFAQLink.isDisplayed() }; cFAQLink.click() }
    def clickMyAccountButton(){ waitFor{ cMyAccountButton.isDisplayed() }; cMyAccountButton.click() }
    def clickGetEpointsButton(){ waitFor{ cGetEpointsButton.isDisplayed() }; cGetEpointsButton.click() }
}