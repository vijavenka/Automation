package com.iat.stepdefs.checkoutSection

import com.iat.Config
import com.iat.pages.checkout.EpointsBasketDeliveryDetailsPage
import com.iat.pages.checkout.EpointsBasketSelectedRewardsPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.rewards.EpointsRewardsPage
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions
import com.iat.stepdefs.utils.JdbcDatabaseConnector
import geb.Browser

import static cucumber.api.groovy.EN.*
import static cucumber.api.groovy.Hooks.Before
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

def epointsHomePage = new EpointsHomePage()
def epointsRewardsPage = new EpointsRewardsPage()
def epointsBasketSelectedRewardsPage = new EpointsBasketSelectedRewardsPage()
def epointsBasketDeliveryDetailsPage = new EpointsBasketDeliveryDetailsPage()

def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager)

String chosenRedemptionName
String chosenRedemptionCost
String chosenRedemptionName2
String chosenRedemptionCost2

Before('@setHighEpointsValue') {
    //Two main user which awards points in ecards in tests
    mySQLConnector.execute("UPDATE points_manager.User SET confirmed = '10000000' WHERE userKey = '" + new UserRepositoryImpl().findByEmail(Config.epointsUser).getUuid() + "'")
    mySQLConnector.execute("UPDATE points_manager.User SET confirmed = '10000000' WHERE userKey = '" + new UserRepositoryImpl().findByEmail(Config.unitedUser).getUuid() + "'")
    mySQLConnector.execute("UPDATE points_manager.Account SET confirmed = '10000000' WHERE userKey = '" + new UserRepositoryImpl().findByEmail(Config.unitedUser).getUuid() + "'")
}

Before('@setLowEpointsValue') {
    mySQLConnector.execute("UPDATE points_manager.User SET confirmed = '1' WHERE userKey = '" + new UserRepositoryImpl().findByEmail(Config.epointsUser).getUuid() + "'")
    mySQLConnector.execute("UPDATE points_manager.User SET confirmed = '1' WHERE userKey = '" + new UserRepositoryImpl().findByEmail(Config.unitedUser).getUuid() + "'")
    mySQLConnector.execute("UPDATE points_manager.Account SET confirmed = '1' WHERE userKey = '" + new UserRepositoryImpl().findByEmail(Config.unitedUser).getUuid() + "'")
}

// 1.1 //  ---------------------------------------------------------------------------- Checkout - selected rewards page
// -------------------------------------------------------------------------------------------------------- page content
Given(~/^Selected rewards checkout page is opened with (.+?) product in it by (.+?) user in '(.+?)' context$/) { String productNumber, String userLoginState, String partner ->
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
    def redemptionCard = epointsRewardsPage.redemptionList.addToBasketAnyRedemption()
    waitFor { epointsRewardsPage.headerModule.headerBasketCounter == 1 }
    chosenRedemptionName = redemptionCard.title
    chosenRedemptionCost = redemptionCard.cost
    if (productNumber.equals('two')) {
        redemptionCard = epointsRewardsPage.redemptionList.getRandomCard()
        while (redemptionCard.title == chosenRedemptionName) {
            redemptionCard = epointsRewardsPage.redemptionList.getRandomCard()
        }
        redemptionCard.clickAddToBasketButton()
        chosenRedemptionName2 = redemptionCard.title
        chosenRedemptionCost2 = redemptionCard.cost
    }
    epointsHomePage.headerModule.clickOnBasketIcon()
    epointsHomePage.basketModule.clickOnBasketPanelViewAllRedemptionsLink()
    at EpointsBasketSelectedRewardsPage
    epointsBasketSelectedRewardsPage = page

    assertThat(epointsBasketSelectedRewardsPage.basketStepIndicatorModule.selectedRewardsStep.text(), is(envVar.checkoutStep1))
    assertThat(epointsBasketSelectedRewardsPage.basketStepIndicatorModule.deliveryDetailsStep.text(), is(envVar.checkoutStep2))
    assertThat(epointsBasketSelectedRewardsPage.basketStepIndicatorModule.orderSummaryStep.text(), is(envVar.checkoutStep3))
    assertThat(epointsBasketSelectedRewardsPage.basketStepIndicatorModule.completeStep.text(), is(envVar.checkoutStep4))
    assertThat(epointsBasketSelectedRewardsPage.basketStepIndicatorModule.stepCircle.size(), is(4))
}
When(~/^User look on selected rewards page$/) { ->
    waitFor { epointsBasketSelectedRewardsPage.selectedRewardsHeader.isDisplayed() }
    assertThat(epointsBasketSelectedRewardsPage.selectedRewardsHeader.text(), is(envVar.selectedRewardsHeader))
}
Then(~/^He can see that it contains all item card elements$/) { ->
    waitFor { epointsBasketSelectedRewardsPage.redemptionImageBasic.isDisplayed() }
    assertThat(epointsBasketSelectedRewardsPage.redemptionNameBasic.text(), is(chosenRedemptionName))
    assert epointsBasketSelectedRewardsPage.redemptionImageBasic.isDisplayed()
    assert epointsBasketSelectedRewardsPage.redemptionRemoveButtonBasic.isDisplayed()
    assertThat(epointsBasketSelectedRewardsPage.redemptionNumberBasic.value() as Integer, is(1))
    assert epointsBasketSelectedRewardsPage.redemptionQuantityMinus.isDisplayed()
    assert epointsBasketSelectedRewardsPage.redemptionQuantityPlus.isDisplayed()
    assertThat(epointsBasketSelectedRewardsPage.redemptionPointsValue.text(), is(chosenRedemptionCost))
}
Then(~/^Summary row is properly displayed$/) { ->
    waitFor { epointsBasketSelectedRewardsPage.basketRemoveAllItemsLink.isDisplayed() }
    assert epointsBasketSelectedRewardsPage.basketRemoveAllItemsLink.isDisplayed()
    assertThat(epointsBasketSelectedRewardsPage.totalEpointsNeededText.text().replace("\n", ""), is(envVar.returnTotalEpointsNeededInfo(chosenRedemptionCost)))
}
Then(~/^Selected rewards page navigation buttons are properly displayed$/) { ->
    waitFor { epointsBasketSelectedRewardsPage.basketBackToRewardsPageLink.isDisplayed() }
    assert epointsBasketSelectedRewardsPage.basketBackToRewardsPageLink.isDisplayed()
    assert epointsBasketSelectedRewardsPage.orderRewardButton.isDisplayed()
}

// 1.2 //  ---------------------------------------------------------------------------- Checkout - selected rewards page
// ------------------------------------------------------------------------------------- working of order rewards button
When(~/^User click order rewards Button$/) { ->
    epointsBasketSelectedRewardsPage.clickOrderRewardButton()
}
Then(~/^He will be redirected to delivery details step$/) { ->
    at EpointsBasketDeliveryDetailsPage
    epointsBasketDeliveryDetailsPage = page
    waitFor { epointsBasketDeliveryDetailsPage.deliveryDetailsHeader.text() == envVar.deliveryDetailsHeader }
    assertThat(epointsBasketDeliveryDetailsPage.deliveryDetailsHeader.text(), is(envVar.deliveryDetailsHeader))
}

// 1.3 //  ---------------------------------------------------------------------------- Checkout - selected rewards page
// ----------------------------------------------------------------------------------- working of back to rewards button
When(~/^User click back to rewards button$/) { ->
    epointsBasketSelectedRewardsPage.clickOnBasketBackToRewardsPageLink()
}

Then(~/^He will be redirected to browse rewards page$/) { ->
    at epointsRewardsPage
    epointsRewardsPage = page
    waitFor { browser.title == envVar.rewardsPageTitle }
    assertThat(browser.title, is(envVar.rewardsPageTitle))
}

// 1.4 //  ---------------------------------------------------------------------------- Checkout - selected rewards page
// -------------------------------------------------------------------------------------------- increase/decrease button
When(~/^User click increase quantity button$/) { ->
    epointsBasketSelectedRewardsPage.clickIncreaseButton()
}
Then(~/^Product number will be increased$/) { ->
    waitFor { epointsBasketSelectedRewardsPage.redemptionNumberBasic.value() == '2' }
    assertThat(epointsBasketSelectedRewardsPage.redemptionNumberBasic.value() as Integer, is(2))
}
Then(~/^Total cost will be recalculated$/) { ->
    assert Integer.parseInt(epointsBasketSelectedRewardsPage.redemptionNumberBasic.value()) * Integer.parseInt(epointsBasketSelectedRewardsPage.redemptionPointsValue.text().replace(',', '')) == Integer.parseInt(epointsBasketSelectedRewardsPage.totalEpointsNeededValue.text().replace(',', ''))
}
When(~/^User click decreased quantity button$/) { ->
    epointsBasketSelectedRewardsPage.clickDecreaseButton()
}
Then(~/^Product number will be decreased$/) { ->
    waitFor { epointsBasketSelectedRewardsPage.redemptionNumberBasic.value() == '1' }
    assertThat(epointsBasketSelectedRewardsPage.redemptionNumberBasic.value() as Integer, is(1))
}

// 1.5 //  ---------------------------------------------------------------------------- Checkout - selected rewards page
// ----------------------------------------------------------------------------------------------- delete single product
When(~/^User click remove button of chosen product$/) { ->
    waitFor { epointsBasketSelectedRewardsPage.basicBasketRedemption.size() == 2 }
    epointsBasketSelectedRewardsPage.clickRemoveButtonOfChosenProduct(0)
}
When(~/^Confirm deletion$/) { ->
    epointsBasketSelectedRewardsPage.clickRemoveItemYesButton(0)
}
Then(~/^Chosen product will be deleted from basket$/) { ->
    waitFor { epointsBasketSelectedRewardsPage.basicBasketRedemption.size() == 1 }
    assertThat(epointsBasketSelectedRewardsPage.basicBasketRedemption.size(), is(1))
}

// 1.6 //  ---------------------------------------------------------------------------- Checkout - selected rewards page
// -------------------------------------------------------------------------------------------------- delete all product
When(~/^User click remove all products$/) { ->
    epointsBasketSelectedRewardsPage.clickOnBasketRemoveAllItemsLink()
}
When(~/^Confirm deletion of all product from basket$/) { ->
    epointsBasketSelectedRewardsPage.clickOnBasketRemoveAllPopUpDeleteLink()
}
Then(~/^All products will be removed from basket$/) { ->
    waitFor { epointsBasketSelectedRewardsPage.basicBasketRedemption.size() == 0 }
    waitFor { epointsBasketSelectedRewardsPage.noRewardsInBasketInformation.isDisplayed() }
    assertThat(epointsBasketSelectedRewardsPage.basicBasketRedemption.size(), is(0))
    assertThat(epointsBasketSelectedRewardsPage.noRewardsInBasketInformation.text(), is(envVar.noProductInBasketInformation))
}

// 1.7 //  ---------------------------------------------------------------------------- Checkout - selected rewards page
// --------------------------------------------------------------------------------------------- cancel deleting product
Then(~/^Delete popup will be displayed$/) { ->
    waitFor { epointsBasketSelectedRewardsPage.basketRemoveAllPopUpInformation.isDisplayed() }
    assertThat(epointsBasketSelectedRewardsPage.basketRemoveAllPopUpInformation.text(), is(envVar.removeRemptonPopupQuestion))
    assert epointsBasketSelectedRewardsPage.basketRemoveAllPopUpCancelLink.isDisplayed()
    assert epointsBasketSelectedRewardsPage.basketRemoveAllPopUpDeleteLink.isDisplayed()
}
When(~/^User refuse of deleting product from basket$/) { ->
    epointsBasketSelectedRewardsPage.clickOnBasektRemoveAllPopUpCancelLink()
}
Then(~/^All product will stay in basket$/) { ->
    assertThat(epointsBasketSelectedRewardsPage.basicBasketRedemption.size(), is(2))
}

// 1.8 //  ---------------------------------------------------------------------------- Checkout - selected rewards page
// ---------------------------------------------------------------------------------------------- low points number info
Then(~/^He will see that his points number is to low to redeem selected item$/) { ->
    waitFor { epointsBasketSelectedRewardsPage.notEnoughPointsAlert.isDisplayed() }
    assertThat(epointsBasketSelectedRewardsPage.notEnoughPointsAlert.text().replace(",", "").replace("\n", ""), is(envVar.returnNotEnoughPointsAlert(Integer.parseInt(epointsBasketSelectedRewardsPage.redemptionPointsValue.text().replace(",", "")) - 1)))
}