package modules.Epoints_modules.getSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-03.
 */

class playGamesPageModule extends Module{
    static content = {
        clumsyBirdGetText { $('.GetPlay-header') }
        clumsyBirdGameWindow{ $('.GameSplashScreen-image') }
        clumsyBirdPlayButton{ $('.GameSplashScreen-button') }
            clumsyBirdGameModalWindow(required: false){ $('.modal-content') }
            clumsyBirdGameModalWindowCloseButton(required: false){ clumsyBirdGameModalWindow.find('.modal-close-btn') }
        clumsyBirdInstuctionText{ $('.GetPlay-description') }
        clumsyBirdInstructionImage{ $('.GetPlay-instructionsImage') }
        clumsyBirdMultiplier{ $('.GetPlay-instructionsPoints') }
        clumsyBirdCommercial{ $('.text-center',2) }
        clumsyBirdCommercial2{ $('.text-center',1) }
        signInButton(required: false){ $('.GameSplashScreen-message').find('.btn') }
        signInInfo(required: false){ $('.GameSplashScreen-message').find('span') }
    }

    def clickPlayClumsyBirdButton(){ waitFor{ clumsyBirdPlayButton.isDisplayed() }; clumsyBirdPlayButton.click() }
    def clickClumsyBirdGameWindowCloseButton(){ waitFor{ clumsyBirdGameModalWindowCloseButton.isDisplayed() }; clumsyBirdGameModalWindowCloseButton.click() }
    def clickSignInButtonOnGamePage(){ waitFor{ signInButton.isDisplayed() }; signInButton.click() }
}