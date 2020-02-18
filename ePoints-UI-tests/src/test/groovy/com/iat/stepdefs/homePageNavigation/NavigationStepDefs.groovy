package com.iat.stepdefs.homePageNavigation

import com.iat.pages.home.EpointsHomePage
import com.iat.pages.points.*
import com.iat.stepdefs.envVariables

import static cucumber.api.groovy.EN.Then
import static cucumber.api.groovy.EN.When
import static org.hamcrest.MatcherAssert.assertThat

def epointsHomePage = new EpointsHomePage()
def pointsPage = new PointsPage()
def vouchersPage = new VouchersPage()
def playGamesPage = new PlayGamesPage()
def goInStorePage = new GoInstorePage()
def inviteFriendsPage = new InviteFriendsPage()
def envVar = new envVariables()

// 1.1 //  --------------------------------------------------- NAVIGATION - changes to get area for desktop(NBO-349)
// ------------------------------------------------------------------------------------------------------ navigation
Then(~/^Additional subnavbar option will appear$/) { ->
    at PointsPage
    pointsPage = page

    waitFor { pointsPage.headerModule.pointsNavbar.isDisplayed() }
    assertThat("Shop online option is not active", pointsPage.headerModule.navigationOption("Shop online").parent().hasClass('is-active'))
    assertThat("Vouchers option is active", !pointsPage.headerModule.navigationOption("Vouchers").parent().hasClass('is-active'))
    assertThat("Play games option is active", !pointsPage.headerModule.navigationOption("Play games").parent().hasClass('is-active'))
    assertThat("Go in-store option is active", !pointsPage.headerModule.navigationOption("Go in-store").parent().hasClass('is-active'))
    assertThat("Invite friends option is active", !pointsPage.headerModule.navigationOption("Invite friends").parent().hasClass('is-active'))
}
When(~/^User use vouchers link$/) { ->
    pointsPage.headerModule.clickPointsSubNavigationOption("Vouchers")
}
Then(~/^He will be redirected to vouchers page$/) { ->
    at VouchersPage
    vouchersPage = page
    waitFor { vouchersPage.headerModule.navigationOption("Vouchers").parent().hasClass('is-active') }
    assertThat("Vouchers option is not active", vouchersPage.headerModule.navigationOption("Vouchers").parent().hasClass('is-active'))
    to PointsPage
    pointsPage = page
}
When(~/^User use play games link$/) { ->
    pointsPage.headerModule.clickPointsSubNavigationOption("Play games")
}
Then(~/^He will be redirected to play games page$/) { ->
    at PlayGamesPage
    playGamesPage = page
    waitFor { playGamesPage.headerModule.navigationOption("Play games").isDisplayed() }
    assertThat("Play games option is not active", playGamesPage.headerModule.navigationOption("Play games").parent().hasClass('is-active'))
    to PointsPage
    pointsPage = page
}
When(~/^User use instore link$/) { ->
    pointsPage.headerModule.clickPointsSubNavigationOption("Go in-store")
}
Then(~/^He will be redirected to in-store page$/) { ->
    at GoInstorePage
    goInStorePage = page
    waitFor { playGamesPage.headerModule.navigationOption("Go in-store").isDisplayed() }
    assertThat("Go in-store option is not active", goInStorePage.headerModule.navigationOption("Go in-store").parent().hasClass('is-active'))
    to PointsPage
    pointsPage = page
}
When(~/^User use invite friend link$/) { ->
    pointsPage.headerModule.clickPointsSubNavigationOption("Invite friends")
}
Then(~/^He will be redirected to invite friend page$/) { ->
    at InviteFriendsPage
    inviteFriendsPage = page
    waitFor { playGamesPage.headerModule.navigationOption("Invite friends").isDisplayed() }
    assertThat("Invite friends option is not active", inviteFriendsPage.headerModule.navigationOption("Invite friends").parent().hasClass('is-active'))
    to PointsPage
    pointsPage = page
}

// 1.2 //  --------------------------------------------------- NAVIGATION - changes to get area for desktop(NBO-349)
// ----------------------------------------------------------- content of navbar on angular page for not logged user
Then(~/^He can see that navbar has proper content for not logged user$/) { ->
    at PointsPage
    pointsPage = page

    waitFor { pointsPage.headerModule.pointsNavbar.isDisplayed() }
    assertThat("Home button is not displayed", pointsPage.headerModule.headerHomeButton.isDisplayed())
    assertThat("Points button is not displayed", pointsPage.headerModule.headerPointsButton.isDisplayed())
    assertThat("Shop online nav option is not displayed", pointsPage.headerModule.navigationOption("Shop online").isDisplayed())
    assertThat("Vouchers nav option is not displayed", pointsPage.headerModule.navigationOption("Vouchers").isDisplayed())
    assertThat("Play games nav option is not displayed", pointsPage.headerModule.navigationOption("Play games").isDisplayed())
    assertThat("Go in-store nav option is not displayed", pointsPage.headerModule.navigationOption("Go in-store").isDisplayed())
    assertThat("Invite friends nav option is not displayed", pointsPage.headerModule.navigationOption("Invite friends").isDisplayed())
//    assertThat("Roulette nav option is not displayed", pointsPage.headerModule.headerPrizesButton.isDisplayed())
    assertThat("Rewards button is not displayed", pointsPage.headerModule.headerRewardsSectionButton.isDisplayed())
    assertThat("Join button is not displayed", pointsPage.headerModule.headerJoinButton.isDisplayed())
    assertThat("Zone picker is not displayed", pointsPage.headerModule.zonePickerFlag[0].isDisplayed())
}

// 1.3 //  --------------------------------------------------- NAVIGATION - changes to get area for desktop(NBO-349)
// ---------------------------------------------- content of navbar on angular and not angular com.iat.pages for logged user
Then(~/^He can see that navbar has proper content for logged user$/) { ->
    at PointsPage
    pointsPage = page

    waitFor { pointsPage.headerModule.pointsNavbar.isDisplayed() }
    assertThat("Home button is not displayed", pointsPage.headerModule.headerHomeButton.isDisplayed())
    assertThat("Points button is not displayed", pointsPage.headerModule.headerPointsButton.isDisplayed())
    assertThat("Shop online nav option is not displayed", pointsPage.headerModule.navigationOption("Shop online").isDisplayed())
    assertThat("Vouchers nav option is not displayed", pointsPage.headerModule.navigationOption("Vouchers").isDisplayed())
    assertThat("Play games nav option is not displayed", pointsPage.headerModule.navigationOption("Play games").isDisplayed())
    assertThat("Go in-store nav option is not displayed", pointsPage.headerModule.navigationOption("Go in-store").isDisplayed())
    assertThat("Invite friends nav option is not displayed", pointsPage.headerModule.navigationOption("Invite friends").isDisplayed())
//    assertThat("Roulette nav option is not displayed", pointsPage.headerModule.headerPrizesButton.isDisplayed())
    assertThat("Rewards button is not displayed", pointsPage.headerModule.headerRewardsSectionButton.isDisplayed())
    assertThat("User name is not displayed", pointsPage.headerModule.headerUserNameLabel.isDisplayed())
    assertThat("Confirmed points are not displayed", pointsPage.headerModule.headerPointsBalanceConfirmed.isDisplayed())
    assertThat("Pending points are not displayed", pointsPage.headerModule.headerPointsBalancePending.isDisplayed())
    assertThat("Zone picker is not displayed", pointsPage.headerModule.zonePickerFlag[0].isDisplayed())
}

// 2.1 //  ----------------- DESKTOP - EPOINTS - IP recognition or manual selection to set global site view(NBO-546)
// -------------------------------------------------------------------------------- zone picker content/close button
When(~/^Zone picker panel will be opened$/) { ->
    at EpointsHomePage
    epointsHomePage = page
    epointsHomePage.headerModule.epandZonePickerPanel()
}
Then(~/^User will see that zone picker panel has proper content$/) { ->
    waitFor { epointsHomePage.headerModule.zonePickerPanel.hasClass('ZonePicker--open') }
    sleep(1000)
    assertThat("UK flag is not displayed", epointsHomePage.headerModule.zonePickerFlag[1].isDisplayed()) //UK
    assertThat("Ireland flag is not displayed", epointsHomePage.headerModule.zonePickerFlag[2].isDisplayed()) //Ireland
    assertThat("Number of zones is not correct", epointsHomePage.headerModule.zonePickerCountryBasic.size() == 2)
    //currently zone number
    assertThat("Wrong label of UK zone", epointsHomePage.headerModule.zonePickerCountryBasic[0].text() == envVar.zonePickerUK)
    assertThat("Wrong label of Ireland zone", epointsHomePage.headerModule.zonePickerCountryBasic[1].text() == envVar.zonePickerIreland)
    assertThat("Wrong zone picker heading text", epointsHomePage.headerModule.zonePickerPopupHeader.text() == envVar.zonePickerPanelHeader)
    assertThat("Close button not displayed", epointsHomePage.headerModule.zonePickerPopupCloseButton.isDisplayed())
}
When(~/^User click close zone picker button$/) { ->
    epointsHomePage.headerModule.clickCloseZonePickerButton()
}
Then(~/^Zone picker panel will be closed$/) { ->
    waitFor { !epointsHomePage.headerModule.zonePickerPanel.hasClass('ZonePicker--open') }
    assertThat("Zone picker is still opened", !epointsHomePage.headerModule.zonePickerPanel.hasClass('ZonePicker--open'))
}

//When(~/^User use prizes main navigation button$/) { ->
//    at EpointsHomePage
//    epointsHomePage = page
//    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
//    epointsHomePage.headerModule.clickOnPrizesButton()
//}

//Then(~/^Prize header menu option will not be displayed$/) { ->
//    at EpointsHomePage
//    epointsHomePage = page
//    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
//    waitFor { !epointsHomePage.headerModule.headerPrizesButton.isDisplayed() }
//    assertThat("Prize option is available", !epointsHomePage.headerModule.headerPrizesButton.isDisplayed())
//}