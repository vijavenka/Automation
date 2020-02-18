package com.iat.stepdefs

import com.iat.pages.FourOFourPage
import com.iat.pages.points.GoInstorePage
import com.iat.pages.points.InviteFriendsPage
import com.iat.pages.points.PlayGamesPage
import com.iat.pages.points.PointsPage
import com.iat.pages.rewards.EpointsRewardsPage
import geb.Browser

import static cucumber.api.groovy.EN.Given
import static cucumber.api.groovy.EN.Then
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

FourOFourPage fourOFourPage = new FourOFourPage()
EpointsRewardsPage epointsRewardsPage = new EpointsRewardsPage()
PointsPage pointsPage = new PointsPage()
PlayGamesPage playGamesPage = new PlayGamesPage()
GoInstorePage goInstorePage = new GoInstorePage()
InviteFriendsPage inviteFriendsPage = new InviteFriendsPage()

def envVar = new envVariables()
def browser = new Browser()

Given(~/^User land on 404 page$/) { ->
    to FourOFourPage
    fourOFourPage = page
}

Then(~/^He is presented with error page information$/) { ->
    waitFor() { fourOFourPage.errorTextArea.isDisplayed() }
    assertThat(fourOFourPage.errorTextArea.text().replace('\n', ''), is(envVar.errorPageText))
}

Then(~/^He can use available rewards\/points links$/) { ->
    fourOFourPage.clickRewarsLink()
    at EpointsRewardsPage
    epointsRewardsPage = page
    browser.getDriver().navigate().back()
    fourOFourPage.clickShopOnlineLink()
    at PointsPage
    pointsPage = page
    browser.getDriver().navigate().back()
    fourOFourPage.clickPlayGamesLink()
    at PlayGamesPage
    playGamesPage = page
    browser.getDriver().navigate().back()
    fourOFourPage.clickGoInstoreLink()
    at GoInstorePage
    goInstorePage = page
    browser.getDriver().navigate().back()
    fourOFourPage.clickInviteFrindsLinkLink()
    at InviteFriendsPage
    inviteFriendsPage = page
}

Given(~/^User land on 404 united page$/) { ->
    go("/united/404")
}

Then(~/^He is redirected to (\d+) page in epoints context$/) { int arg1 ->
    at FourOFourPage
}