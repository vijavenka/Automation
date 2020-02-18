package com.iat.stepdefs.pointsSection

import com.iat.Config
import com.iat.pages.points.PointsPage
import com.iat.pages.points.RetailerPage
import com.iat.pages.points.TransitionPage
import geb.Browser

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat

def pointsPage = new PointsPage()
def retailerPage = new RetailerPage()
def browser = new Browser()
def transitionPage = new TransitionPage()


def retailerMultiplier
def retailerName

// 1.1 //  ----------------------------------------------------------------------------------------------- Retailer page
// --------------------------------------------------------------------------------- check availability of retailer page
Given(~/^Selected retailer page is opened$/) { ->
    at PointsPage
    pointsPage = page
    waitFor { pointsPage.retailersList[0].isDisplayed() }

    pointsPage.enterPhraseIntoSearch('UI AUTOMATION MERCHANT III')
    sleep(500)
    pointsPage.clickSearchButton()

    sleep(500)
    browser.interact { moveToElement(pointsPage.retailersList[0]) }
    sleep(500)
    waitFor { pointsPage.retailersList[0].retailerCardOverlayButton.isDisplayed() }
    retailerName = pointsPage.retailersList[0].retailerCardOverlayButton.text()
    retailerMultiplier = pointsPage.retailersList[0].epointsInfo.text()
    pointsPage.retailersList[0].click()
    at RetailerPage
    retailerPage = page
    waitFor { browser.title.contains(retailerName) }
}
Then(~/^Retailer Name is displayed on retailer page$/) { ->
    waitFor { retailerPage.retailerNameArticleHeader.text().contains(retailerName) }
}
Then(~/^Retailer description is displayed on retailer page$/) { ->
    assert retailerPage.retailerDescription.isDisplayed()
}
Then(~/^Epoints multiplier is displayed on retailer page$/) { ->
    assert retailerPage.retailerPointsMultiplierText.isDisplayed()
    assert retailerPage.retailerPointsMultiplierText.text().contains(retailerMultiplier)
}
Then(~/^Join epoints link is displayed$/) { ->
    assert retailerPage.joinNowButton
}
Then(~/^Watch video button is displayed$/) { ->
    assert retailerPage.watchVideoButton.isDisplayed()
}
Then(~/^Go to retailer button is displayed$/) { ->
    assertThat("Go to retailer button is not displayed", retailerPage.goToRetailerButton.isDisplayed())
}
Then(~/^Breadcrumb last level show retailer name$/) { ->
    waitFor { retailerPage.breadcrumbModule.breadcrumb.isDisplayed() }
    assert retailerPage.breadcrumbModule.breadcrumbSingleElementBasic[2].text().contains(retailerName)
}

// 1.2 //  ----------------------------------------------------------------------------------------------- Retailer page
// ----------------------------------------------------------------------------------------------------------- join link
When(~/^User click join button on retailer page$/) { ->
    retailerPage.clickJoinNowButton()
}

// 1.3 //  ----------------------------------------------------------------------------------------------- Retailer page
// -------------------------------------------------------------------------------------------------------- video player
When(~/^User click watch video button on retailer page$/) { ->
    retailerPage.clickWatchVideoButton()
}
Then(~/^Video player will be displayed$/) { ->
    waitFor { retailerPage.videoPlayer.isDisplayed() }
}
Then(~/^Video player can be closed by using close button$/) { ->
    retailerPage.clickVideoPlayerCloseButton()
    waitFor { !retailerPage.videoPlayer.isDisplayed() }
}


Given(~/^User login to his account as epoints only user$/) { ->
    retailerPage.headerModule.clickOnSignInButton()
    waitFor { retailerPage.headerModule.headerSignInButton.isDisplayed() }
    retailerPage.signInModule.signInUserToEpointsCom(Config.epointsUser, Config.epointsUserPassword)
    waitFor(15) { retailerPage.headerModule.headerUserNameLabel.isDisplayed() }
}

When(~/^User click 'go to retailer button'$/) { ->
    retailerPage.clickGoToRetailerButton()
}

Then(~/^He will be redirected to retailer page or transition page according to login state (.+?)$/) { String loginState ->
    at TransitionPage
    transitionPage = page

    if (!loginState.equals("logged"))
        assert !transitionPage.transitionAnimation.isDisplayed()
    else {
        waitFor { !transitionPage.transitionAnimation.isDisplayed() }
        assert !browser.currentUrl.contains("/transition")
    }
}

Then(~/^He will be redirected to individual retailer page or transition page according to login state '(.+?)'$/) { String loginState ->

    if (!loginState.equals("logged")) {
        at RetailerPage
        retailerPage = page
    } else {
//        new window is opened //TODO
    }
}