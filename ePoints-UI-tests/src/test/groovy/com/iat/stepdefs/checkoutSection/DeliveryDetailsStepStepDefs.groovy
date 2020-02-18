package com.iat.stepdefs.checkoutSection

import com.iat.Config
import com.iat.pages.checkout.EpointsBasketDeliveryDetailsPage
import com.iat.pages.checkout.EpointsBasketOrderSummaryPage
import com.iat.pages.checkout.EpointsBasketSelectedRewardsPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.rewards.EpointsRewardsPage
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.Before
import static org.hamcrest.MatcherAssert.assertThat

def epointsHomePage = new EpointsHomePage()
def epointsRewardsPage = new EpointsRewardsPage()
def epointsBasketSelectedRewardsPage = new EpointsBasketSelectedRewardsPage()
def epointsBasketDeliveryDetailsPage = new EpointsBasketDeliveryDetailsPage()
def epointsBasketOrderSummaryPage = new EpointsBasketOrderSummaryPage()

def func = new Functions()
def envVar = new envVariables()

String chosenRedemptionName
String chosenRedemptionCost

// 1.1 //  ------------------------------------------------------------------------ Checkout - delivery details page
// ---------------------------------------------------------------------------------------------------- page content
Given(~/^Delivery details checkout page is opened with (.+?) product in it by (.+?) user in '(.+?)' context$/) { String productNumber, String userLoginState, String partner ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    if (userLoginState.equals('logged')) {
        epointsHomePage.signInUserToEpoints(Config.unitedUser, Config.unitedUserPassword)
        sleep(1000)
        if (partner.toLowerCase() == "united") {
            epointsHomePage.accountSwitcher.unitedSwitcher.click()
            waitFor { epointsHomePage.accountSwitcher.currentAccount == "United" }
        } else if (partner.toLowerCase() == "epoints") {
            epointsHomePage.accountSwitcher.epointsSwitcher.click()
            waitFor { epointsHomePage.accountSwitcher.currentAccount == "epoints" }
        }
    }

    epointsHomePage.goToEpointsRewardsPage()
    at epointsRewardsPage
    epointsRewardsPage = page
    //clear basket if needed
    if (epointsHomePage.headerModule.headerBasketIcon.isDisplayed()) {
        epointsHomePage.headerModule.clickOnBasketIcon()
        epointsHomePage.basketModule.clickOnBasketPanelViewAllRedemptionsLink()
        at epointsBasketSelectedRewardsPage
        epointsBasketSelectedRewardsPage = page
        epointsBasketSelectedRewardsPage.removeAllRedemptionItemsFromBasket()
        epointsBasketSelectedRewardsPage.clickOnBasketBackToRewardsPageLink()
        at epointsRewardsPage
        epointsRewardsPage = page
    }
    //clear basket if needed
    //epointsRewardsPage.clickOnAvailableItemsLink()
    waitFor { epointsRewardsPage.redemptionList.size() > 0 }
    waitFor { epointsRewardsPage.redemptionList.chooseRandomRedemption().image.isDisplayed() }
    random = 0
    def redemptionCard = epointsRewardsPage.redemptionList.addToBasketAnyRedemption()
    chosenRedemptionName = redemptionCard.title
    chosenRedemptionCost = redemptionCard.cost
    if (productNumber.equals('two')) {
        random = 1
        redemptionCard = epointsRewardsPage.redemptionList.addToBasketAnyRedemption()
        chosenRedemptionName2 = redemptionCard.title
        chosenRedemptionCost2 = redemptionCard.cost
    }
    epointsHomePage.headerModule.clickOnBasketIcon()
    epointsHomePage.basketModule.clickOnBasketPanelViewAllRedemptionsLink()
    at EpointsBasketSelectedRewardsPage
    epointsBasketSelectedRewardsPage = page
    epointsBasketSelectedRewardsPage.clickOrderRewardButton()
    at EpointsBasketDeliveryDetailsPage
    epointsBasketDeliveryDetailsPage = page
}
When(~/^User look on delivery details page$/) { ->
    waitFor { epointsBasketDeliveryDetailsPage.deliveryDetailsHeader.isDisplayed() }
    assert epointsBasketDeliveryDetailsPage.deliveryDetailsHeader.text() == envVar.deliveryDetailsHeader
}
Then(~/^He can see that delivery detail page contains address cards section$/) { ->
    waitFor { epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardBasic[0].isDisplayed() }
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardBasic.size() >= 2
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardPlusButton.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardAddNewAddressButton.isDisplayed()
}
Then(~/^Delivery details page contains redemption selection section$/) { ->
    waitFor { epointsBasketDeliveryDetailsPage.ddItemsSelectionRedemptionNameBasic.isDisplayed() }
    assert epointsBasketDeliveryDetailsPage.ddItemsSelectionEditButton.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.ddItemsSelectionRedemptionNameBasic.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.ddItemsSelectionRedemptionPointsValueBasic.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.ddItemsSelectionTotalEpointsNeeded.isDisplayed()
}
Then(~/^Redemption selection section contains proper product and points values$/) { ->
    waitFor { epointsBasketDeliveryDetailsPage.ddItemsSelectionRedemptionNameBasic.isDisplayed() }
    assert epointsBasketDeliveryDetailsPage.ddItemsSelectionRedemptionNameBasic.text().contains(chosenRedemptionName)
    assert epointsBasketDeliveryDetailsPage.ddItemsSelectionRedemptionPointsValueBasic.text() == chosenRedemptionCost
    def calculatedRedmptionsCost = 0
    for (int i = 0; i < epointsBasketDeliveryDetailsPage.ddItemsSelectionRedemptionPointsValueBasic.size(); i++) {
        calculatedRedmptionsCost = calculatedRedmptionsCost + Integer.parseInt(epointsBasketDeliveryDetailsPage.ddItemsSelectionRedemptionPointsValueBasic[i].text().replace(',', ''))
    }
    assert calculatedRedmptionsCost == Integer.parseInt(epointsBasketDeliveryDetailsPage.ddItemsSelectionTotalEpointsNeeded.text().replace(',', ''))
}
Then(~/^Delivery details page contains delivery info section$/) { ->
    waitFor { epointsBasketDeliveryDetailsPage.ddDeliveryInformationBox.isDisplayed() }
    assert epointsBasketDeliveryDetailsPage.ddDeliveryInformationBox.isDisplayed()
}
Then(~/^Delivery details page contains navigation buttons$/) { ->
    waitFor { epointsBasketDeliveryDetailsPage.ddNextButton.isDisplayed() }
    waitFor { epointsBasketDeliveryDetailsPage.ddBackButton.isDisplayed() }
    assert epointsBasketDeliveryDetailsPage.ddNextButton.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.ddBackButton.isDisplayed()
}

// 1.2 //  ------------------------------------------------------------------------ Checkout - delivery details page
// --------------------------------------------------------------------------- redemptions selection box edit button
When(~/^User click edit button in redemptions selection box$/) { ->
    epointsBasketDeliveryDetailsPage.clickEditButtonInProductsSelectionBox()
}
Then(~/^He will be redirected to selected rewards step$/) { ->
    at EpointsBasketSelectedRewardsPage
    epointsBasketSelectedRewardsPage = page
    waitFor { epointsBasketSelectedRewardsPage.selectedRewardsHeader.text() == envVar.selectedRewardsHeader }
    assert epointsBasketSelectedRewardsPage.selectedRewardsHeader.text() == envVar.selectedRewardsHeader
}

// 1.3 //  ------------------------------------------------------------------------ Checkout - delivery details page
// ----------------------------------------------------------------------------------------------------- back button
When(~/^User click back button on delivery details page$/) { ->
    epointsBasketDeliveryDetailsPage.clickBackButtonOnDeliveryDetailsPage()
}

// 1.4 //  ------------------------------------------------------------------------ Checkout - delivery details page
// ----------------------------------------------------------------------------------------------------- next button
When(~/^User click next button on delivery details page$/) { ->
    epointsBasketDeliveryDetailsPage.clickNextButtonOnDeliveryDetailsPage()
}
Then(~/^He will be redirected to order summary step$/) { ->
    at EpointsBasketOrderSummaryPage
    epointsBasketOrderSummaryPage = page
    waitFor { epointsBasketDeliveryDetailsPage.deliveryDetailsHeader.text() == envVar.orderSummaryHeader }
    assert epointsBasketDeliveryDetailsPage.deliveryDetailsHeader.text() == envVar.orderSummaryHeader
}

// 1.5 //  ------------------------------------------------------------------------ Checkout - delivery details page
// ------------------------------------------------------------------------ new address form content / cancel button
When(~/^User click add new address button$/) { ->
    epointsBasketDeliveryDetailsPage.clickAddNewAddresButton()
}
Then(~/^New address form will be displayed$/) { ->
    waitFor { epointsBasketDeliveryDetailsPage.deliveryForm.isDisplayed() }
    assert epointsBasketDeliveryDetailsPage.cancelButton.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.firstNameLabel.text() == envVar.deliveryNameLabel
    assert epointsBasketDeliveryDetailsPage.firstNameInputField.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.lastNameLabel.text() == envVar.deliveryLastNameLabel
    assert epointsBasketDeliveryDetailsPage.lastNameInputField.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.houseNumberLabel.text() == envVar.deliveryHouseNumberLabel
    assert epointsBasketDeliveryDetailsPage.houseNumberInputField.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.streetLabel.text() == envVar.deliveryStreetLabel
    assert epointsBasketDeliveryDetailsPage.streetInputField.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.townLabel.text() == envVar.deliveryTownLabel
    assert epointsBasketDeliveryDetailsPage.townInputField.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.coutyLabel.text() == envVar.deliveryCountyLabel
    assert epointsBasketDeliveryDetailsPage.countyInputField.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.postcodeLabel.text() == envVar.deliveryPostcodeLabel
    assert epointsBasketDeliveryDetailsPage.postcodeInputField.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.findAddressButton.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.coutryLabel.text() == envVar.deliveryCoutryLabel
    assert epointsBasketDeliveryDetailsPage.coutryDDL.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.contactNumberLabel.text() == envVar.deliveryContactLabel
    assert epointsBasketDeliveryDetailsPage.contactNumberInputField.isDisplayed()
    assert epointsBasketDeliveryDetailsPage.useAsContactDetailsCheckbox.isDisplayed()
}
When(~/^User click cancel button on new address form$/) { ->
    epointsBasketDeliveryDetailsPage.clickCancelButtonOnNewAddressForm()
}
Then(~/^New addres form will be closed$/) { ->
    waitFor { !epointsBasketDeliveryDetailsPage.deliveryForm.isDisplayed() }
    assert !epointsBasketDeliveryDetailsPage.deliveryForm.isDisplayed()
}

// 1.6 //  ------------------------------------------------------------------------ Checkout - delivery details page
// -------------------------------------------------------------------------------------------------- address finder
Given(~/^New address form is opened$/) { ->
    epointsBasketDeliveryDetailsPage.clickAddNewAddresButton()
}
When(~/^User fill post code input field$/) { ->
    epointsBasketDeliveryDetailsPage.enterPostCode(envVar.UKPostcode)
}
When(~/^Click find address button$/) { ->
    epointsBasketDeliveryDetailsPage.clickFindAddressButton()
}
When(~/^User select some address from proposed addresses$/) { ->
    epointsBasketDeliveryDetailsPage.expandAddressDDL()
    sleep(1000)
    epointsBasketDeliveryDetailsPage.clickChosenAddressDDLOption(1)
}
Then(~/^House number, street, town inputs will be automatically filled with data$/) { ->
    waitFor { epointsBasketDeliveryDetailsPage.houseNumberInputField.value().size() > 0 }
    assert epointsBasketDeliveryDetailsPage.houseNumberInputField.value().size() > 0
    assert epointsBasketDeliveryDetailsPage.streetInputField.value().size() > 0
    assert epointsBasketDeliveryDetailsPage.townInputField.value().size() > 0
}

// 1.7 //  ------------ DESKTOP - SPEND PAGES - update manual address entry screen to allow Irish addresses(NBO-565)
// ------------------------------------------------------------------------------------------- alerts for UK country
// 1.8 //  ------------ DESKTOP - SPEND PAGES - update manual address entry screen to allow Irish addresses(NBO-565)
// ------------------------------------------------------------------------------------------- alerts for IE country
When(~/^User fill all new address form fields for country (.+?)$/) { String country ->
    if (country == 'UK') {
        epointsBasketDeliveryDetailsPage.fillAllNewAddressFormFields('firstName', 'lastName', 'houseNr', 'streetName', 'townName', 'countyName', 'postcode', 'UK', 'conNum')
    } else if (country == 'IE') {
        epointsBasketDeliveryDetailsPage.fillAllNewAddressFormFields('firstName', 'lastName', 'houseNr', 'streetName', 'townName', 'countyName', 'postcode', 'IE', 'conNum')
    }
}
When(~/^Delete all data from new address form$/) { ->
    epointsBasketDeliveryDetailsPage.fillAllNewAddressFormFields(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')
}
Then(~/^Fields required alerts will be displayed (.+?) post code field$/) { String poscodeRequired ->
    waitFor { epointsBasketDeliveryDetailsPage.validErrorBasic[0].isDisplayed() }
    assert epointsBasketDeliveryDetailsPage.validErrorBasic[0].text() == envVar.deliveryNameRequiredAlert
    assert epointsBasketDeliveryDetailsPage.validErrorBasic[1].text() == envVar.deliveryLastNameRequiredAlert
    assert epointsBasketDeliveryDetailsPage.validErrorBasic[2].text() == envVar.deliveryHouseNumberRequiredAlert
    assert epointsBasketDeliveryDetailsPage.validErrorBasic[3].text() == envVar.deliveryStreetRequiredAlert
    assert epointsBasketDeliveryDetailsPage.validErrorBasic[4].text() == envVar.deliveryTownRequiredAlert
    assert epointsBasketDeliveryDetailsPage.validErrorBasic[5].text() == envVar.deliveryCountyRequiredAlert
    //assert epointsBasketDeliveryDetailsPage.validErrorBasic[6].text() == envVar
    if (poscodeRequired == 'including') {
        assert epointsBasketDeliveryDetailsPage.validErrorBasic[6].text() == envVar.deliveryPostcodeRequiredAlert
    } else if (poscodeRequired == 'excluding') {
        assert !epointsBasketDeliveryDetailsPage.validErrorBasic[6].isDisplayed()
    }
}

// 1.9 //  ------------------------------------------------------------------------ Checkout - delivery details page
// ------------------------------------------------------------------------------- next button / remembering address
String firstName = 'firstName' + func.returnRandomValue(9)
String lastName = 'lastName' + func.returnRandomValue(9)
String houseNr = 'houseNr' + func.returnRandomValue(9)
String street = 'street' + func.returnRandomValue(9)
String town = 'town' + func.returnRandomValue(9)
String county = 'county' + func.returnRandomValue(9)
String contactNumber = 'conNum' + func.returnRandomValue(9)

When(~/^User fill all new address form fields in '(.+?)' context$/) { String partner ->
    if (partner.equals("epoints")) {
        epointsBasketDeliveryDetailsPage.fillAllNewAddressFormFields(firstName, lastName, houseNr, street, town, county, envVar.UKPostcode, 'UK', contactNumber)
    } else if (partner.equals("united")) {
        waitFor { (epointsBasketDeliveryDetailsPage.coutryDDL.attr("disabled") == "true") }
        assertThat("Country ddl is not disabled in united context", epointsBasketDeliveryDetailsPage.coutryDDL.attr('disabled') == "true")
        epointsBasketDeliveryDetailsPage.fillAllNewAddressFormFields(firstName, lastName, houseNr, street, town, county, envVar.UKPostcode, 'null', contactNumber)
    }
}
Then(~/^He will see that entered address data were remembered on address card$/) { ->
    at EpointsBasketDeliveryDetailsPage
    epointsBasketDeliveryDetailsPage = page
    waitFor { epointsBasketDeliveryDetailsPage.ddDeliveryAddresSecondCardDetailsBasic[0].text().contains(firstName) }
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresSecondCardDetailsBasic[0].text().contains(firstName)
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresSecondCardDetailsBasic[0].text().contains(lastName)
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresSecondCardDetailsBasic[1].text().contains(houseNr)
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresSecondCardDetailsBasic[1].text().contains(street)
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresSecondCardDetailsBasic[3].text().contains(town)
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresSecondCardDetailsBasic[4].text().contains(county)
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresSecondCardDetailsBasic[5].text().contains('UK')
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresSecondCardDetailsBasic[6].text().contains(envVar.UKPostcode)
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresSecondCardDetailsBasic[7].text().contains(contactNumber)
}

// 1.10 //  ----------------------------------------------------------------------- Checkout - delivery details page
// ------------------------------------------------------------------------------ use as my contact details checkbox
When(~/^Select 'use as my contact details' checkbox$/) { ->
    epointsBasketDeliveryDetailsPage.selectUseAsMyContactDetailsCheckbox()
}
Then(~/^He can see that user contact data will be updated on first address card$/) { ->
    //First and last nae cannot be change
    waitFor { epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[0].isDisplayed() }
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[0].isDisplayed()
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[1].text().contains(houseNr)
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[1].text().contains(street)
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[3].text().contains(town)
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[4].text().contains(county)
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[5].text().contains('UK')
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[6].text().contains(envVar.UKPostcode)
    assert epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[7].text().contains(contactNumber)
}

Before('@setDefaultPersonalAndContactDataBefore') {
    //TODO to be change to use rest api
}