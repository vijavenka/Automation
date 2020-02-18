package stepdefs.EpointsStepDefs.getSection

import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-02-03.
 */

def epoints = new epointsPage()
def envVar = new envVariables()
def func = new Functions()
browser = new Browser()

    // 1.1 //  ------------------------------------------- GAME PAGE - create partner page for GAME for desktop(NBO-346)
    // 1.5 //  -------------------------------------------------GAME PAGE - add adverts to game page on DESKTOP(NBO-817)
    // ---------------------------------------------------------------------------------------------------- page content - user logged
    Given(~/^Play games page is opened by logged user$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
        epoints.signModule.clickSignInButton()
        epoints.clickGetButton()
        epoints.clickPlayGamesButtonAngular()
    }
    When(~/^User look on play games page$/) { ->
        //leave empty
    }
    Then(~/^He will see clumsy bird game window$/) { ->
        waitFor{ epoints.playGamesPage.clumsyBirdGameWindow.isDisplayed() }
        waitFor{ epoints.playGamesPage.clumsyBirdPlayButton.isDisplayed() }
        assert epoints.playGamesPage.clumsyBirdGameWindow.isDisplayed()
        assert epoints.playGamesPage.clumsyBirdPlayButton.isDisplayed()
    }
    Then(~/^Clumsy bird image description will be displayed$/) { ->
        waitFor{  epoints.playGamesPage.clumsyBirdInstructionImage.isDisplayed() }
        assert epoints.playGamesPage.clumsyBirdInstructionImage.isDisplayed()
    }
    Then(~/^Commercials will be displayed$/) { ->
        waitFor{ epoints.playGamesPage.clumsyBirdCommercial.isDisplayed() }
        waitFor{ epoints.playGamesPage.clumsyBirdCommercial2.isDisplayed() }
        assert epoints.playGamesPage.clumsyBirdCommercial.isDisplayed()
        assert epoints.playGamesPage.clumsyBirdCommercial2.isDisplayed()
    }
    Then(~/^Other elements like points counter will be displayed$/) { ->
        waitFor{ epoints.playGamesPage.clumsyBirdGetText.isDisplayed() }
        waitFor{ epoints.playGamesPage.clumsyBirdInstuctionText.isDisplayed() }
        waitFor{ epoints.playGamesPage.clumsyBirdMultiplier.isDisplayed() }
        //waitFor{ epoints.playGamesPage.clumsyBirdInstructionImage.attr('ng-switch-when').length()>0 }
        assert epoints.playGamesPage.clumsyBirdGetText.text() == envVar.clumsyBirdGetText
        assert epoints.playGamesPage.clumsyBirdMultiplier.isDisplayed()
        //assert epoints.playGamesPage.clumsyBirdInstuctionText.text().replace("\n"," ") == envVar.returnClumsyBirdInstructionText(epoints.playGamesPage.clumsyBirdInstructionImage.attr('ng-switch-when'), epoints.playGamesPage.clumsyBirdMultiplier.text())
    }

    // 1.2 //  ------------------------------------------- GAME PAGE - create partner page for GAME for desktop(NBO-346)
    // 1.5 //  -------------------------------------------------GAME PAGE - add adverts to game page on DESKTOP(NBO-817)
    // ---------------------------------------------------------------------------------- page content - user not logged
    Given(~/^Play games page is opened clear$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        epoints.clickGetButton()
        epoints.clickPlayGamesButtonAngular()
    }
    Then(~/^Other elements like page header will be displayed$/) { ->
        waitFor{ epoints.playGamesPage.clumsyBirdGetText.isDisplayed() }
        waitFor{ epoints.playGamesPage.clumsyBirdInstuctionText.isDisplayed() }
        waitFor{ epoints.playGamesPage.clumsyBirdMultiplier.isDisplayed() }
        //waitFor{ epoints.playGamesPage.clumsyBirdInstructionImage.attr('ng-switch-when').length()>0 }
        assert epoints.playGamesPage.clumsyBirdGetText.text() == envVar.clumsyBirdGetText
        assert epoints.playGamesPage.clumsyBirdMultiplier.isDisplayed()
        //assert epoints.playGamesPage.clumsyBirdInstuctionText.text().replace("\n"," ") == envVar.returnClumsyBirdInstructionText(epoints.playGamesPage.clumsyBirdInstructionImage.attr('ng-switch-when'), epoints.playGamesPage.clumsyBirdMultiplier.text())
    }
    Then(~/^Sign into epoints button will be displayed$/) { ->
        waitFor{ epoints.playGamesPage.signInButton.isDisplayed() }
        waitFor{ epoints.playGamesPage.signInInfo.isDisplayed() }
        assert epoints.playGamesPage.signInButton.isDisplayed()
        assert epoints.playGamesPage.signInInfo.text() == envVar.signInToearnEpointsInfo
    }

    // 1.3 // GAME PAGE - show message and UI for users who access game page and are not identified for DESKTOP(NBO-655)
    // -------------------------------------------------------------------------------------------------- sign in button
    Given(~/^Play games page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.clickGetButton()
        epoints.clickPlayGamesButtonAngular()
    }
    When(~/^User click sign in button on game page$/) { ->
        // Write code here that turns the phrase above into concrete actions
        epoints.playGamesPage.clickSignInButtonOnGamePage()
    }

    // 1.4 //  ---------------- GAME PAGE - integrate the Clumsy Bird game into the DESKTOP game page interface(NBO-665)
    // --------------------------------------------------------------------------------------- game window, close button
    When(~/^User click play clumsy bird button$/) { ->
        epoints.playGamesPage.clickPlayClumsyBirdButton()
    }
    Then(~/^Modal window with clumsy bird game will be opened$/) { ->
        waitFor{ epoints.playGamesPage.clumsyBirdGameModalWindow.isDisplayed() }
        assert epoints.playGamesPage.clumsyBirdGameModalWindow.isDisplayed()
    }
    When(~/^User click close clumsy bird game button$/) { ->
        epoints.playGamesPage.clickClumsyBirdGameWindowCloseButton()
    }
    Then(~/^Modal window with clumsy bird game will be closed$/) { ->
        waitFor{ !epoints.playGamesPage.clumsyBirdGameModalWindow.isDisplayed() }
        assert !epoints.playGamesPage.clumsyBirdGameModalWindow.isDisplayed()
    }