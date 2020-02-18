package com.iat.stepdefs.checkoutSection

import com.iat.Config
import com.iat.pages.checkout.EpointsBasketCompletePage
import com.iat.pages.checkout.EpointsBasketDeliveryDetailsPage
import com.iat.pages.checkout.EpointsBasketOrderSummaryPage
import com.iat.pages.checkout.EpointsBasketSelectedRewardsPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.home.footerStaticPages.FaqPage
import com.iat.pages.points.PointsPage
import com.iat.pages.rewards.EpointsRewardsPage
import com.iat.pages.rewards.modules.RedemptionCardModule
import com.iat.pages.userAccount.activity.ActivityPage
import com.iat.pages.userAccount.myEpoints.EpointsMyAccountPage
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.containsString
import static org.hamcrest.Matchers.is

def epointsHomePage = new EpointsHomePage()
def epointsRewardsPage = new EpointsRewardsPage()
def epointsBasketSelectedRewardsPage = new EpointsBasketSelectedRewardsPage()
def epointsBasketDeliveryDetailsPage = new EpointsBasketDeliveryDetailsPage()
def epointsBasketOrderSummaryPage = new EpointsBasketOrderSummaryPage()
def epointsBasketCompletePage = new EpointsBasketCompletePage()
def faqPage = new FaqPage()
def epointsMyAccountPage = new EpointsMyAccountPage()
def activityPage = new ActivityPage()
def pointsPage = new PointsPage()

def func = new Functions()
def envVar = new envVariables()
def browser = new Browser()

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

// 1.1 //  ------------------------------------------------------------------------------------ Checkout - complete page
// -------------------------------------------------------------------------------------------------------- page content

Given(~/^Complete checkout page is opened with (.+?) product in it by (.+?) user in '(.+?)' context$/) { String productNumber, String userLoginState, String partner ->
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
    redemptionCard = epointsRewardsPage.redemptionList.addToBasketAnyRedemption()
    chosenRedemptionName = redemptionCard.title
    chosenRedemptionCost = redemptionCard.cost
    if (productNumber == "two") {
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
    epointsBasketOrderSummaryPage.clickPlaceOrderButton()
    at EpointsBasketCompletePage
    epointsBasketCompletePage = page
}

When(~/^User look on complete page$/) { ->
    waitFor { epointsBasketCompletePage.compleateHeader.text() == envVar.completeOrderHeader }
    assertThat(epointsBasketCompletePage.compleateHeader.text(), is(envVar.completeOrderHeader))

}
Then(~/^He sees completed order page with confirmation message$/) { ->
    waitFor { epointsBasketCompletePage.cThankYouText.isDisplayed() }
    assertThat(epointsBasketCompletePage.cThankYouText.text().replace("\n", "").replace(" ", ""), is(envVar.completeThankYouText.replace(" ", "")))
}
Then(~/^Complete page redirect buttons will be displayed in '(.+?)' context$/) { String partner ->
    waitFor { epointsBasketCompletePage.cFAQLink.isDisplayed() }
    waitFor { epointsBasketCompletePage.cMyAccountButton.isDisplayed() }
    assert epointsBasketCompletePage.cFAQLink.isDisplayed()
    assert epointsBasketCompletePage.cMyAccountButton.isDisplayed()
    if (partner.equals("epoints")) {
        waitFor { epointsBasketCompletePage.cGetEpointsButton.isDisplayed() }
        assert epointsBasketCompletePage.cGetEpointsButton.isDisplayed()
    }
}

// 1.2 //  -------------------------------------------------------------------------------- Checkout - complete page
// -------------------------------------------------------------------------------------------------------- faq link
When(~/^User use faq link on complete page$/) { ->
    epointsBasketCompletePage.clickFaqLink()
}
Then(~/^He will be redirected to epoints faq page$/) { ->
    at FaqPage
    faqPage = page
}
// 1.3 //  -------------------------------------------------------------------------------- Checkout - complete page
// ----------------------------------------------------------------------------------------------- my account button
When(~/^User click my account button on complete page$/) { ->
    epointsBasketCompletePage.clickMyAccountButton()
}
Then(~/^He will be redirected to user account page$/) { ->
    at EpointsMyAccountPage
    epointsMyAccountPage = page
}
And(~/^New redemption activity will be visible on activity list$/) { ->
    waitFor { epointsMyAccountPage.activityInfoContentRewardNameBasic[0].text().contains(chosenRedemptionName) }
    assertThat("Redeemed product is not on recent activity list", epointsMyAccountPage.activityInfoContentRewardNameBasic[0].text(), containsString(chosenRedemptionName))
}

Then(~/^He will be redirected to user activity page$/) { ->
    at ActivityPage
    activityPage = page
}

Then(~/^New redemption activity will be visible on current balance list$/) { ->
    waitFor { activityPage.activityTableContentActivityBasic[0].text().contains(chosenRedemptionName) }
    assertThat("Redeemed product is not on current balance list", activityPage.activityTableContentActivityBasic[0].text(), containsString(chosenRedemptionName))
}

// 1.4 //  -------------------------------------------------------------------------------- Checkout - complete page
// ---------------------------------------------------------------------------------------------- get epoints button
When(~/^User click get epoints button on complete page$/) { ->
    epointsBasketCompletePage.clickGetEpointsButton()
}
Then(~/^He will be redirected to a-z page$/) { ->
    at PointsPage
    pointsPage = page
}