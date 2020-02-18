package com.iat.stepdefs.pointsSection

import com.iat.Config
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.points.UnitedPointsPage
import com.iat.stepdefs.utils.Functions

import static cucumber.api.groovy.EN.Then
import static cucumber.api.groovy.EN.When
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

def epointsHomePage = new EpointsHomePage()
def unitedPointsPage = new UnitedPointsPage()

def func = new Functions()

When(~/^United points page is opened by logged user$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.signInUserToEpoints(Config.unitedUser, Config.unitedUserPassword)
    sleep(1000)
    epointsHomePage.accountSwitcher.unitedSwitcher.click()
    waitFor { epointsHomePage.accountSwitcher.currentAccount == "United" }
    epointsHomePage.headerModule.clickOnPointsButton()
    at UnitedPointsPage
    unitedPointsPage = page
    sleep(2000)
}

Then(~/^User will be presented with "Discover rewards" banner$/) { ->
    assertThat("Discover rewards banner is not displayed", unitedPointsPage.mainBanner.isDisplayed())
}

Then(~/^User will be presented with list of latest deals$/) { ->
    unitedPointsPage.latestDeals.each { epointsIcon ->
        assertThat("Not all of latest deals have epoints icon", epointsIcon.isDisplayed())
    }

    unitedPointsPage.latestDeals.each { price ->
        assertThat("Not all of latest deals have price", price.isDisplayed())
    }

    unitedPointsPage.latestDeals.each { dealName ->
        assertThat("Not all of latest deals have deal name", dealName.isDisplayed())
    }

    unitedPointsPage.latestDeals.each { dealImage ->
        assertThat("Not all of latest deals have deal image", dealImage.isDisplayed())
    }

}

Then(~/^User will be presented with popular brands$/) { ->
    assertThat("Discover rewards banner is not displayed", unitedPointsPage.promotedBrandsSectionTitle.text(), is("Popular Brands"))
    assertThat("Wrong number of popular brands images displayed", unitedPointsPage.promotedBrandImage.size(), is(6))
}