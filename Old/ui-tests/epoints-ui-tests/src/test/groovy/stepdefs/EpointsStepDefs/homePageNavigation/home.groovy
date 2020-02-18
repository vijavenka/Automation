package stepdefs.EpointsStepDefs.homePageNavigation

import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-01-26.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

    // 1.1 //  ------------------- HOME PAGE - change title and copy of get epoints section for desktop version(NBO-336)
    // --------------------------------------------------------------------------------------------- get epoints section
    Given(~/^Epoints home page is opened$/) { ->
        to epointsPage
        epoints = page
    }
    When(~/^User look on get epoints for shopping online section$/) { ->
        //leave empty
    }
    Then(~/^Get epoints for shopping online section has proper title$/) { ->
        waitFor{ epoints.getEpointsOnlineSectionTitle.isDisplayed() }
        assert epoints.getEpointsOnlineSectionTitle.text() == envVar.getEpointsSectionTitle
    }
    Then(~/^Get epoints for shopping online section text is correct$/) { ->
        waitFor{ epoints.getEpointsOnlineSectionText.isDisplayed() }
        assert epoints.getEpointsOnlineSectionText.text().replace("\n", "") == envVar.getEpointsSectionText
    }
    Then(~/^Online stores link on get epoints for shopping online section works fine$/) { ->
        epoints.clickOnlineStoresLinkOnGetEpointsSection()
        waitFor{ browser.getCurrentUrl() == envVar.epointsLink + envVar.shopOnlineURL }
        assert browser.getCurrentUrl() == envVar.epointsLink + envVar.shopOnlineURL
    }

    // 1.2 //  ------------------- HOME PAGE - change title and copy of get epoints section for desktop version(NBO-336)
    // ------------------------------------------------------------------------------------------- retailer card clicked
    String selectedRetailer
    When(~/^User click some retailer card on get epoints for shopping online section$/) { ->
        waitFor{ epoints.getEpointsOnlineSectionRetailerCardBasic }
        random = func.returnRandomValue( epoints.getEpointsOnlineSectionRetailerCardBasic.size() )
        while(!epoints.getEpointsOnlineSectionRetailerCardBasic[random].isDisplayed()){
            random = func.returnRandomValue( epoints.getEpointsOnlineSectionRetailerCardBasic.size() )
        }
        selectedRetailer = epoints.getEpointsOnlineSectionRetailerCardNameBasic[random].attr('data-retailername')
        System.out.println(selectedRetailer)
        epoints.clickChosenRetailerCard(random)
    }
    Then(~/^He will be redirected to transition page of proper retailer$/) { ->
        waitFor{ epoints.transitionPage.informationModalRetailerInformation.text() == selectedRetailer }
        assert epoints.transitionPage.informationModalRetailerInformation.text() == selectedRetailer
    }

    // 2.1 //  ------------------------------ HOME PAGE - add new sections in correct order in-store and videos(NBO-338)
    // ------------------------------------------------------------------------------------ get epoints in-store section
    When(~/^User look on get epoints for shopping in-store section$/) { ->
        //leave empty
    }
    Then(~/^Get epoints for shopping in-store section has proper title$/) { ->
        waitFor{ epoints.getEpointsInStoreSectionTitle.isDisplayed() }
        assert epoints.getEpointsInStoreSectionTitle.text() == envVar.getEpointsInStoreSectionTitle
    }
    Then(~/^Get epoints for shopping in-store section text is correct$/) { ->
        waitFor{ epoints.getEpointsInStoreSectionText.isDisplayed() }
        assert epoints.getEpointsInStoreSectionImages.isDisplayed()
        assert epoints.getEpointsInStoreSectionText.text().replace("\n", "") == envVar.getEpointsInStoreSectionText
    }
    Then(~/^Learn more link on get epoints for shopping online section works fine$/) { ->
        epoints.clickLearnMoreLinkOnGetEpointsInStoreSection()
        waitFor{ browser.getCurrentUrl() == envVar.epointsLink + envVar.goInStoreURL }
        assert browser.getCurrentUrl() == envVar.epointsLink + envVar.goInStoreURL
    }

    // 2.2 //  ------------------------------ HOME PAGE - add new sections in correct order in-store and videos(NBO-338)
    // -------------------------------------------------------------------------------------- get epoints videos section
    When(~/^User look on get epoints for watching videos section$/) { ->
        // leave empty
    }
    Then(~/^Get epoints for watching videos section has proper title$/) { ->
        waitFor{ epoints.getEpointsVideosSectionTitle.isDisplayed() }
        assert epoints.getEpointsVideosSectionTitle.text() == envVar.getEpointsVideosSectionTitle
    }
    Then(~/^Get epoints for watching videos section text is correct$/) { ->
        waitFor{ epoints.getEpointsVideosSectionText.isDisplayed() }
        assert epoints.getEpointsVideosSectionImages.isDisplayed()
        assert epoints.getEpointsVideosSectionText.text().replace("\n", "") == envVar.getEpointsVideosSectionText
    }
    Then(~/^Great videos link on get epoints for shopping online section works fine$/) { ->
        epoints.clickGreatvideosLinkOnGetEpointsVideosSection()
        waitFor{ browser.getCurrentUrl() == envVar.epointsLink + envVar.watchVideosURL }
        assert browser.getCurrentUrl() == envVar.epointsLink + envVar.watchVideosURL
    }

    // 3.1 //  ----------------------- HOME PAGE - update "spending epoints is fun" section for desktop version(NBO-342)
    // ------------------------------------------------------------------------------------ spend epoints easily section
    // Update ------------------------------- EPOINTS DESKTOP - spend message improvements from home page link(NBO-1039)
    When(~/^User look on spend all your epoints easily section$/) { ->
        //leave empty
    }
    Then(~/^Spend all your epoints easily section has proper title$/) { ->
        waitFor{ epoints.spendEpointsEasilySectionTitle.isDisplayed() }
        assert epoints.spendEpointsEasilySectionTitle.text() == envVar.spendEpointsEasilySectionTitle
    }
    Then(~/^Spend all your epoints easily section text is correct$/) { ->
        waitFor{ epoints.spendEpointsEasilySectionText.isDisplayed() }
        assert epoints.spendEpointsEasilySectionText.isDisplayed()
        assert epoints.spendEpointsEasilySectionText.text().replace("\n", "").replace(" ","") == envVar.spendEpointsEasilySectionText.replace(" ","")
    }
    Then(~/^Image link on spend all your epoints easily section works fine$/) { ->
        epoints.clickImageLinkOnSpendEpointsEasilySection()
        waitFor{ browser.getCurrentUrl() == envVar.epointsLink + envVar.spendURL }
        assert browser.getCurrentUrl() == envVar.epointsLink + envVar.spendURL
    }

    // 4.1 //  ------------------------ HOME PAGE - add game section to the new home page structure for desktop(NBO-337)
    // --------------------------------------------------------------------------- get epoints for playing games section
    When(~/^User look on get epoints for playing games section$/) { ->
        //leave empty
    }
    Then(~/^Get epoints for playing games section has proper title$/) { ->
        waitFor{ epoints.getEpointsForPlayingGamesSectionTitle.isDisplayed() }
        assert epoints.getEpointsForPlayingGamesSectionTitle.text() == envVar.getEpointsForPlayingGamesSectionTitle
    }
    Then(~/^Get epoints for playing games section text is correct$/) { ->
        waitFor{ epoints.getEpointsForPlayingGamesSectionText.isDisplayed() }
        assert epoints.getEpointsForPlayingGamesSectionText.isDisplayed()
        assert epoints.getEpointsForPlayingGamesSectionText.text().replace("\n", "") == envVar.getEpointsForPlayingGamesSectionText
    }
    Then(~/^Playing games link on get epoints for playing games section works fine$/) { ->
        //assert epoints.getEpointsForPlayingGamesSectionBird.isDisplayed()
        epoints.clickPlayingGamesLinkOnGetEpointsForPlayingGamesSection()
        waitFor{ browser.getCurrentUrl() == envVar.epointsLink + envVar.playGamesURL }
        assert browser.getCurrentUrl() == envVar.epointsLink + envVar.playGamesURL
    }

    // 5.1 //  ---------------------------- HOME PAGE - add video to non-logged in home page to desktop version(NBO-335)
    // -------------------------------------------------------------------------------------------------- video presence
    Given(~/^Video splash screen is visible$/) { ->
         waitFor{ epoints.videoSplashScreen.isDisplayed() }
        assert epoints.videoSplashScreen.isDisplayed()
    }
    Given(~/^Video play button is visible$/) { ->
        waitFor{ epoints.videoPlayButton.isDisplayed() }
        assert epoints.videoPlayButton.isDisplayed()
    }
    When(~/^User click play button$/) { ->
        epoints.clickVideoPlayerPlayButton()
    }
    Then(~/^Video will be started$/) { ->
        //leave empty
    }

    // 6.1 //  -------------------------------- IDENTIFIED HOME PAGE - remove the need for identified home page(NBO-343)
    // --------------------------------------------------------------------------------------- three states of home page
    Given(~/^For not logged user join forms are displayed$/) { ->
        waitFor{ epoints.joinInputField.isDisplayed() }
        assert epoints.joinInputField.attr('placeholder') == envVar.joinModuleEmailPlaceholder
        assert epoints.joinForFreeButton.text() == envVar.joinModuleButtonText
        assert epoints.signInWithFacebookButton.isDisplayed()
        assert epoints.get50EpointsInformation.isDisplayed()
    }
    When(~/^User login into epoints account$/) { ->
        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
        epoints.signModule.clickSignInButton()
        waitFor{ epoints.topnavSignOutButton.isDisplayed() }
    }
    Then(~/^Join forms will be removed from home page$/) { ->
        waitFor{ !epoints.joinInputField.isDisplayed() }
        assert !epoints.joinInputField.isDisplayed()
        assert !epoints.joinForFreeButton.isDisplayed()
        assert !epoints.signInWithFacebookButton.isDisplayed()
        assert !epoints.get50EpointsInformation.isDisplayed()
    }
    When(~/^Users session expires$/) { ->
        func.clearParticularCookieAndRefreshPage('JSESSIONID')
    }
    Then(~/^Sign in form appears instead of join form in logout state$/) { ->
        waitFor{ epoints.signInInputField.isDisplayed() }
        assert epoints.signInInputField.attr('placeholder') == envVar.signInModulePasswordPlaceholder
        assert epoints.signInButton.text() == envVar.signInModuleButtonText
        assert epoints.signInWithFacebookButton.isDisplayed()
        assert !epoints.get50EpointsInformation.isDisplayed()
    }
    Then(~/^User account will be not available before login$/) { ->
        epoints.clickYourAccountButton()
        waitFor{ epoints.signModule.loginModalHeaderAngular.text() == envVar.loginModalHeader }
        assert epoints.signModule.loginModalHeaderAngular.text() == envVar.loginModalHeader
    }

    // 7.1 //  --------------------------------------------- EPOINTS DESKTOP - update the in-store partner page(NBO-752)
    // ------------------------------------------------------------------------------------------------ video image link
    When(~/^User click on image in get epoints for watching videos section$/) { ->
        epoints.clickImageOnGetEpointsVidoeSection()
    }
    Then(~/^He will be redirected to watch page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.watchVideosURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.watchVideosURL
    }

    // 7.2 //  --------------------------------------------- EPOINTS DESKTOP - update the in-store partner page(NBO-752)
    // ---------------------------------------------------------------------------------------------- instore image link
    When(~/^User click on image in get epoints for shopping in-store section$/) { ->
        epoints.clickImageOnGetEpointsInStoreSection()
    }
    Then(~/^He will be redirected to instore page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.goInStoreURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.goInStoreURL
    }

    // 7.3 //  --------------------------------------------- EPOINTS DESKTOP - update the in-store partner page(NBO-752)
    // ------------------------------------------------------------------------------------------ clumsy bird image link
    When(~/^User click on image in get epoints for playing games clumsy bird section$/) { ->
        epoints.clickClumyBirdImageOnGetEpointsForPlayingGamesSection()
    }
    Then(~/^He will be redirected to play page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.playGamesURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.playGamesURL
    }

    // 8.1 //  -------------------------------------------------------------------------- EPOINTS DESKTOP - cookie panel
    // -------------------------------------------------------------------------------------------- cookie panel content
    Given(~/^Epoints home page is opened clear$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
    }
    When(~/^User looks on top of page$/) { ->
        //leave empty
    }
    Then(~/^He will see cookie panel with proper content$/) { ->
        waitFor{ epoints.cookiePanel.isDisplayed() }
        assert epoints.cookiePanelText.text().replace("\n","").replace(" ","") == envVar.cookiePanelText.replace(" ","")
        assert epoints.cookiePanelAcceptButton.isDisplayed()
        assert epoints.cookiePanelFindOutMoreButton.isDisplayed()
        assert epoints.cookiePanelCloseButton.isDisplayed()
    }

    // 8.2 //  -------------------------------------------------------------------------- EPOINTS DESKTOP - cookie panel
    // ------------------------------------------------------------------------------------------- accept button working
    When(~/^User click accept button on cookie panel$/) { ->
        epoints.clickAcceptButtonOnCookiePanel()
    }
    Then(~/^Cookie panel will be closed$/) { ->
        waitFor{ !epoints.cookiePanel.isDisplayed() }
        assert !epoints.cookiePanel.isDisplayed()
    }

    // 8.3 //  -------------------------------------------------------------------------- EPOINTS DESKTOP - cookie panel
    // ------------------------------------------------------------------------------------ find out more button working
    When(~/^User click find out more button$/) { ->
        epoints.clickFindOutMoreButtonOnCookiePanel()
    }
    Then(~/^He will be redirected to cookie policy page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.cookiePolicyURL }
        waitFor{ browser.title == envVar.cookiePolicyPageTitle }
        assert browser.currentUrl == envVar.epointsLink + envVar.cookiePolicyURL
        assert browser.title == envVar.cookiePolicyPageTitle
    }