package com.iat.stepdefs.checkoutSection

import com.iat.Config
import com.iat.pages.checkout.EpointsBasketCompletePage
import com.iat.pages.checkout.EpointsBasketDeliveryDetailsPage
import com.iat.pages.checkout.EpointsBasketOrderSummaryPage
import com.iat.pages.checkout.EpointsBasketSelectedRewardsPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.rewards.EpointsRewardsPage
import com.iat.pages.rewards.modules.RedemptionCardModule
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def epointsRewardsPage = new EpointsRewardsPage()
def epointsBasketSelectedRewardsPage = new EpointsBasketSelectedRewardsPage()
def epointsBasketDeliveryDetailsPage = new EpointsBasketDeliveryDetailsPage()
def epointsBasketOrderSummaryPage = new EpointsBasketOrderSummaryPage()
def epointsBasketCompletePage = new EpointsBasketCompletePage()

def func = new Functions()
def envVar = new envVariables()

String chosenRedemptionName
String chosenRedemptionCost
RedemptionCardModule redemptionCard

String nameLastName
String streetHouse
String town
String county
String country
String postcode
String contactNumber

// 1.1 //  --------------------------------------------------------------------------- Checkout - order summary page
// ---------------------------------------------------------------------------------------------------- page content
Given(~/^Order summary checkout page is opened with (.+?) product in it by (.+?) user in '(.+?)' context$/) { String productNumber, String userLoginState, String partner ->
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
    waitFor { epointsRewardsPage.redemptionList.size() > 0 }
    waitFor { epointsRewardsPage.redemptionList.chooseRandomRedemption().image.isDisplayed() }
    random = 0
    redemptionCard = epointsRewardsPage.redemptionList.addToBasketAnyRedemption()
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
    waitFor { epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[0].isDisplayed() }
    nameLastName = epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[0].text()
    streetHouse = epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[1].text()
    town = epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[3].text()
    county = epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[4].text()
    country = epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[5].text()
    postcode = epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[6].text()
    contactNumber = epointsBasketDeliveryDetailsPage.ddDeliveryAddresCardDetailsBasic[7].text()
    epointsBasketDeliveryDetailsPage.clickNextButtonOnDeliveryDetailsPage()
    at EpointsBasketOrderSummaryPage
    epointsBasketOrderSummaryPage = page
}
When(~/^User look on order summary page$/) { ->
    waitFor { epointsBasketOrderSummaryPage.orderSummaryHeader.text() == envVar.orderSummaryHeader }
    assert epointsBasketOrderSummaryPage.orderSummaryHeader.text() == envVar.orderSummaryHeader
}
Then(~/^He will see that order summary page contains correct product$/) { ->
    waitFor { epointsBasketOrderSummaryPage.osRedemptionNameBasic.isDisplayed() }
    waitFor { epointsBasketOrderSummaryPage.osRedemptionPointsValue.isDisplayed() }
    assert epointsBasketOrderSummaryPage.osRedemptionNameBasic.text().contains(chosenRedemptionName)
    assert epointsBasketOrderSummaryPage.osRedemptionPointsValue.text() == chosenRedemptionCost
    assert epointsBasketOrderSummaryPage.productsSectionEditButton.isDisplayed()
    assert epointsBasketOrderSummaryPage.osRedemptionImageBasic.isDisplayed()

}
Then(~/^Total epoints cost is properly calculated$/) { ->
    waitFor { epointsBasketOrderSummaryPage.osTotalEpointsNeeded.isDisplayed() }
    def calculatedRedmptionsCost = 0
    for (int i = 0; i < epointsBasketOrderSummaryPage.osRedemptionPointsValue.size(); i++) {
        calculatedRedmptionsCost = calculatedRedmptionsCost + Integer.parseInt(epointsBasketOrderSummaryPage.osRedemptionPointsValue[i].text().replace(',', ''))
    }
    assert calculatedRedmptionsCost == Integer.parseInt(epointsBasketOrderSummaryPage.osTotalEpointsNeeded.text().replace(',', ''))
}
Then(~/^User will see that order summary page contains correct address$/) { ->
    waitFor { epointsBasketOrderSummaryPage.deliverySectionEditButon.isDisplayed() }
    waitFor { epointsBasketOrderSummaryPage.osDeliveryDetailsBasic[0].isDisplayed() }
    assert epointsBasketOrderSummaryPage.osDeliveryDetailsBasic[0].text().equals(nameLastName)
    assert epointsBasketOrderSummaryPage.osDeliveryDetailsBasic[1].text().equals(streetHouse)
    assert epointsBasketOrderSummaryPage.osDeliveryDetailsBasic[3].text().equals(town)
    assert epointsBasketOrderSummaryPage.osDeliveryDetailsBasic[4].text().equals(county)
    assert epointsBasketOrderSummaryPage.osDeliveryDetailsBasic[5].text().equals(country)
    assert epointsBasketOrderSummaryPage.osDeliveryDetailsBasic[6].text().equals(postcode)
    assert epointsBasketOrderSummaryPage.osDeliveryDetailsBasic[7].text().equals(contactNumber)
}
Then(~/^Order summary page contains navigation buttons$/) { ->
    waitFor { epointsBasketOrderSummaryPage.osBackButton.isDisplayed() }
    waitFor { epointsBasketOrderSummaryPage.osPlaceOrderButton.isDisplayed() }
    assert epointsBasketOrderSummaryPage.osBackButton.isDisplayed()
    assert epointsBasketOrderSummaryPage.osPlaceOrderButton.isDisplayed()
}

// 1.2 //  --------------------------------------------------------------------------- Checkout - order summary page
// --------------------------------------------------------------------------------------------- product edit button
When(~/^User click edit products button$/) { ->
    epointsBasketOrderSummaryPage.clickProductsEditButton()
}

// 1.3 //  --------------------------------------------------------------------------- Checkout - order summary page
// --------------------------------------------------------------------------------------------- address edit button
When(~/^User click edit delivery address button$/) { ->
    epointsBasketOrderSummaryPage.clickDeliveryAddressEditButton()
}

// 1.4 //  --------------------------------------------------------------------------- Checkout - order summary page
// ----------------------------------------------------------------------------------------------------- back button
When(~/^User click back button on order summary page$/) { ->
    at epointsBasketOrderSummaryPage
    epointsBasketOrderSummaryPage = page
    sleep(2000)
    epointsBasketOrderSummaryPage.clickBackButtonOnOrderSummaryPage()
}

// 1.5 //  --------------------------------------------------------------------------- Checkout - order summary page
// ---------------------------------------------------------------------------------------------- place order button
When(~/^User click place order button on order summary page$/) { ->
    epointsBasketOrderSummaryPage.clickPlaceOrderButton()
}
Then(~/^He will be redirected to confirmation step$/) { ->
    at EpointsBasketCompletePage
    epointsBasketCompletePage = page
    waitFor { epointsBasketCompletePage.compleateHeader.text() == envVar.completeOrderHeader }
    assert epointsBasketCompletePage.compleateHeader.text() == envVar.completeOrderHeader
}
Then(~/^Order will be correctly placed$/) { ->

}

Then(~/^Redeemed product will be displayed in recently redeemed section on rewards page$/) { ->
    to EpointsRewardsPage
    epointsRewardsPage = page
    waitFor { epointsRewardsPage.recentlyRedeemedBlock.isDisplayed() }
    assert epointsRewardsPage.recentlyRedeemedBlock.redemptions[0].title == chosenRedemptionName

}