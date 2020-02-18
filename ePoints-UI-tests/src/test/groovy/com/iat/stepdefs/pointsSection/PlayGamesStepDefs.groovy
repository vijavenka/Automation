package com.iat.stepdefs.pointsSection

import com.iat.Config
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.points.PlayGamesPage
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def playGamesPage = new PlayGamesPage()

def envVar = new envVariables()
def func = new Functions()

// 1.1 //  ------------------------------------------- GAME PAGE - create partner page for GAME for desktop(NBO-346)
// 1.5 //  -------------------------------------------------GAME PAGE - add adverts to game page on DESKTOP(NBO-817)
// ---------------------------------------------------------------------------------------------------- page content - user logged
Given(~/^Play games page is opened by (.+?) user$/) { String loginState ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    if (loginState.equals('logged'))
        epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
    epointsHomePage.headerModule.clickOnPointsButton()
    epointsHomePage.headerModule.clickPointsSubNavigationOption("Play games")
    at PlayGamesPage
    playGamesPage = page
}
When(~/^User look on play games page$/) { ->
    //leave empty
}
Then(~/^He will see clumsy bird game window$/) { ->
    waitFor { playGamesPage.clumsyBirdGameWindow.isDisplayed() }
    waitFor { playGamesPage.clumsyBirdPlayButton.isDisplayed() }
    assert playGamesPage.clumsyBirdGameWindow.isDisplayed()
    assert playGamesPage.clumsyBirdPlayButton.isDisplayed()
}
Then(~/^Clumsy bird image description will be displayed$/) { ->
    waitFor { playGamesPage.clumsyBirdInstructionImage.isDisplayed() }
    assert playGamesPage.clumsyBirdInstructionImage.isDisplayed()
}
Then(~/^Commercials will be displayed$/) { ->
    waitFor { playGamesPage.clumsyBirdCommercial.isDisplayed() }
    waitFor { playGamesPage.clumsyBirdCommercial2.isDisplayed() }
    assert playGamesPage.clumsyBirdCommercial.isDisplayed()
    assert playGamesPage.clumsyBirdCommercial2.isDisplayed()
}
Then(~/^Other elements like points counter will be displayed$/) { ->
    waitFor { playGamesPage.clumsyBirdGetText.isDisplayed() }
    waitFor { playGamesPage.clumsyBirdInstuctionText.isDisplayed() }
    waitFor { playGamesPage.clumsyBirdMultiplier.isDisplayed() }
    //waitFor{ playGamesPage.clumsyBirdInstructionImage.attr('ng-switch-when').length()>0 }
    assert playGamesPage.clumsyBirdGetText.text() == envVar.clumsyBirdGetText
    assert playGamesPage.clumsyBirdMultiplier.isDisplayed()
    //assert playGamesPage.clumsyBirdInstuctionText.text().replace("\n"," ") == envVar.returnClumsyBirdInstructionText(playGamesPage.clumsyBirdInstructionImage.attr('ng-switch-when'), playGamesPage.clumsyBirdMultiplier.text())
}

// 1.2 //  ------------------------------------------- GAME PAGE - create partner page for GAME for desktop(NBO-346)
// 1.5 //  -------------------------------------------------GAME PAGE - add adverts to game page on DESKTOP(NBO-817)
// ---------------------------------------------------------------------------------- page content - user not logged
Then(~/^Other elements like page header will be displayed$/) { ->
    waitFor { playGamesPage.clumsyBirdGetText.isDisplayed() }
    waitFor { playGamesPage.clumsyBirdInstuctionText.isDisplayed() }
    waitFor { playGamesPage.clumsyBirdMultiplier.isDisplayed() }
    //waitFor{ playGamesPage.clumsyBirdInstructionImage.attr('ng-switch-when').length()>0 }
    assert playGamesPage.clumsyBirdGetText.text() == envVar.clumsyBirdGetText
    assert playGamesPage.clumsyBirdMultiplier.isDisplayed()
    //assert playGamesPage.clumsyBirdInstuctionText.text().replace("\n"," ") == envVar.returnClumsyBirdInstructionText(playGamesPage.clumsyBirdInstructionImage.attr('ng-switch-when'), playGamesPage.clumsyBirdMultiplier.text())
}
Then(~/^Sign into epoints button will be displayed$/) { ->
    waitFor { playGamesPage.signInButton.isDisplayed() }
    waitFor { playGamesPage.signInInfo.isDisplayed() }
    assert playGamesPage.signInButton.isDisplayed()
    assert playGamesPage.signInInfo.text() == envVar.signInToearnEpointsInfo
}

// 1.3 // GAME PAGE - show message and UI for users who access game page and are not identified for DESKTOP(NBO-655)
// -------------------------------------------------------------------------------------------------- sign in button
When(~/^User click sign in button on game page$/) { ->
    // Write code here that turns the phrase above into concrete actions
    playGamesPage.clickSignInButtonOnGamePage()
}
Then(~/^Login modal with all needed elements will be displayed over play games page$/) { ->
    waitFor { playGamesPage.loginModalModule.loginModalHeader.isDisplayed() }
    assert playGamesPage.loginModalModule.loginModalHeader.isDisplayed()
    assert playGamesPage.loginModalModule.loginModalEmailInputFieldLabel.isDisplayed()
    assert playGamesPage.loginModalModule.loginModalEmailInputField.isDisplayed()
    assert playGamesPage.loginModalModule.loginModalPasswordInputFieldLabel.isDisplayed()
    assert playGamesPage.loginModalModule.loginModalPasswordInputField.isDisplayed()
    assert playGamesPage.loginModalModule.loginModalForgotPasswordLink.isDisplayed()
    assert playGamesPage.loginModalModule.loginModalSignInButton.isDisplayed()
    assert playGamesPage.loginModalModule.loginModalSignInWithFacebookButton.isDisplayed()
}

// 1.4 //  ---------------- GAME PAGE - integrate the Clumsy Bird game into the DESKTOP game page interface(NBO-665)
// --------------------------------------------------------------------------------------- game window, close button
When(~/^User click play clumsy bird button$/) { ->
    playGamesPage.clickPlayClumsyBirdButton()
}
Then(~/^Modal window with clumsy bird game will be opened$/) { ->
    waitFor { playGamesPage.clumsyBirdGameModalWindow.isDisplayed() }
    assert playGamesPage.clumsyBirdGameModalWindow.isDisplayed()
}
When(~/^User click close clumsy bird game button$/) { ->
    playGamesPage.clickClumsyBirdGameWindowCloseButton()
}
Then(~/^Modal window with clumsy bird game will be closed$/) { ->
    waitFor { !playGamesPage.clumsyBirdGameModalWindow.isDisplayed() }
    assert !playGamesPage.clumsyBirdGameModalWindow.isDisplayed()
}