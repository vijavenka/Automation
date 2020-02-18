package stepdefs.EpointsStepDefs.homePageNavigation

import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-01-21.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()
def epointsLink = envVar.epointsLink

    // 1.1 //  --------------------------------------------------- NAVIGATION - changes to get area for desktop(NBO-349)
    // ------------------------------------------------------------------------------------------------------ navigation
    Given(~/^Epoints site is opened$/) { ->
        epointsPage.url = epointsLink
        to epointsPage
        epoints = page
    }
    When(~/^User use get button from main navbar$/) { ->
        epoints.clickGetButton()
    }
    Then(~/^He will land on get online page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink+envVar.getPageURL }
        assert  browser.currentUrl == envVar.epointsLink+envVar.getPageURL
    }
    Then(~/^additional four subnavbar option will appear$/) { ->
        waitFor{ epoints.shopOnlineButtonAngular.isDisplayed() }
        waitFor{ epoints.playGamesButtonAngular.isDisplayed() }
        waitFor{ epoints.watchVideosButtonAngular.isDisplayed() }
        waitFor{ epoints.goInStoreButtonAngular.isDisplayed() }
        waitFor{ epoints.inviteFriendsButton.isDisplayed() }
        waitFor{ epoints.shopOnlineButtonAngular.hasClass('is-active') }
        assert  epoints.shopOnlineButtonAngular.hasClass('is-active')
        assert  !epoints.playGamesButtonAngular.hasClass('is-active')
        assert  !epoints.watchVideosButtonAngular.hasClass('is-active')
        assert  !epoints.goInStoreButtonAngular.hasClass('is-active')
        assert  !epoints.inviteFriendsButton.hasClass('is-active')
    }
    When(~/^User use play games link$/) { ->
        epoints.clickPlayGamesButtonAngular()
    }
    Then(~/^He will be redirected to get play games page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink+envVar.playGamesURL }
        waitFor{ epoints.playGamesButtonAngular.hasClass('is-active') }
        assert  browser.currentUrl == envVar.epointsLink+envVar.playGamesURL
        assert epoints.playGamesButtonAngular.hasClass('is-active')
    }
    When(~/^User use watch link$/) { ->
        epoints.clickWatchVideoButtonAngular()
    }
    Then(~/^He will be redirected to get watch page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink+envVar.watchVideosURL }
        waitFor{ epoints.watchVideosButtonAngular.hasClass('is-active') }
        assert  browser.currentUrl == envVar.epointsLink+envVar.watchVideosURL
        assert epoints.watchVideosButtonAngular.hasClass('is-active')
    }
    When(~/^User use instore link$/) { ->
        epoints.clickGoInStoreButtonAngular()
    }
    Then(~/^He will be redirected to get in-store page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink+envVar.goInStoreURL }
        waitFor{ epoints.goInStoreButtonAngular.hasClass('is-active') }
        assert  browser.currentUrl == envVar.epointsLink+envVar.goInStoreURL
        assert epoints.goInStoreButtonAngular.hasClass('is-active')
    }
    When(~/^User use invite friend link$/) { ->
        epoints.clickInviteFriendButton()
    }
    Then(~/^He will be redirected to get invite friend page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink+envVar.inviteFriendURL }
        waitFor{ epoints.inviteFriendsButton.hasClass('is-active') }
        assert  browser.currentUrl == envVar.epointsLink+envVar.inviteFriendURL
        assert epoints.inviteFriendsButton.hasClass('is-active')
    }

    // 1.2 //  --------------------------------------------------- NAVIGATION - changes to get area for desktop(NBO-349)
    // ----------------------------------------------------------- content of navbar on angular page for not logged user
    Then(~/^He can see that navbar has proper content on angular page for not logged user$/) { ->
        waitFor{ epoints.getButton.isDisplayed() }
        assert epoints.homeButton.isDisplayed()
        assert epoints.getButton.isDisplayed()
        assert epoints.shopOnlineButtonAngular.isDisplayed()
        assert epoints.playGamesButtonAngular.isDisplayed()
        assert epoints.watchVideosButtonAngular.isDisplayed()
        assert epoints.goInStoreButtonAngular.isDisplayed()
        assert epoints.spendButton.isDisplayed()
        assert epoints.topNavAboutUsButton.isDisplayed()
        assert epoints.topNavFAQButton.isDisplayed()
        assert epoints.topNavJoinNowButton.isDisplayed()
        assert epoints.topNavSignInButton.isDisplayed()
        assert epoints.topNavZonePickerFlag.isDisplayed()
    }

    // 1.3 //  --------------------------------------------------- NAVIGATION - changes to get area for desktop(NBO-349)
    // ---------------------------------------------- content of navbar on angular and not angular pages for logged user
    Given(~/^User is logged into epoints page$/) { ->
        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
        epoints.signModule.clickSignInButton()
        waitFor{ epoints.topNavYourAccountButton.isDisplayed() }
    }
    Then(~/^He can see that navbar has proper content on angular page for logged user$/) { ->
        waitFor{ epoints.getButton.isDisplayed() }
        assert epoints.homeButton.isDisplayed()
        assert epoints.getButton.isDisplayed()
        assert epoints.shopOnlineButtonAngular.isDisplayed()
        assert epoints.playGamesButtonAngular.isDisplayed()
        assert epoints.watchVideosButtonAngular.isDisplayed()
        assert epoints.goInStoreButtonAngular.isDisplayed()
        assert epoints.spendButton.isDisplayed()
        assert epoints.topNavAboutUsButton.isDisplayed()
        assert epoints.topNavFAQButton.isDisplayed()
        assert epoints.topNavHiUsername.isDisplayed()
        assert epoints.topNavYourAccountButton.isDisplayed()
        assert epoints.yourEpointsCounter.isDisplayed()
        assert epoints.pendingEpointsCounter.isDisplayed()
        assert epoints.topnavSignOutButton.isDisplayed()
        assert epoints.topNavZonePickerFlag.isDisplayed()
    }

    // 2.1 //  ----------------------------------------------- SPECSAVERS - add partner page link to navigation(NBO-748)
    // ----------------------------------------------------------------------------------------- partner page sub navbar
    Given(~/^Partner page is opened$/) { ->
        epoints.clickPartnerPageLink()
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.partnersURL }
    }
    When(~/^User click case studies sub navbar option$/) { ->
        epoints.clickCaseStudiesButtonAngular()
    }
    Then(~/^He will be redirected to specsavers partner page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversURL
    }

    // 3.1 //  ----------------- DESKTOP - EPOINTS - IP recognition or manual selection to set global site view(NBO-546)
    // -------------------------------------------------------------------------------- zone picker content/close button
    When(~/^Zone picker panel will be opened$/) { ->
        epoints.epandZonePickerPanel()
    }
    Then(~/^User will see that zone picker panel has proper content$/) { ->
        waitFor{ epoints.topNavZonePickerPanel.hasClass('ZonePicker--open') }
        Thread.sleep(1000)
        assert epoints.topNavZonePickerFlag[1].isDisplayed() //UK
        assert epoints.topNavZonePickerFlag[2].isDisplayed() //Ireland
        assert epoints.topNavZonePickerCountryBasic.size() == 2 //currently zone number
        assert epoints.topNavZonePickerCountryBasic[0].text() == envVar.zonePickerUK
        assert epoints.topNavZonePickerCountryBasic[1].text() == envVar.zonePickerIreland
        assert epoints.topNavZonePickerPopupHeader.text() == envVar.zonePickerPanelHeader
        assert epoints.topNavZonePickerPopupCloseButton.isDisplayed()
    }
    When(~/^User click close zone picker button$/) { ->
        epoints.clickCloseZonePickerButton()
    }
    Then(~/^Zone picker panel will be closed$/) { ->
        waitFor{ !epoints.topNavZonePickerPanel.hasClass('ZonePicker--open') }
        assert !epoints.topNavZonePickerPanel.hasClass('ZonePicker--open')
    }