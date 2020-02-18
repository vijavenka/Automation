package com.iat.pages.points

import com.iat.pages.AbstractPage

class PlayGamesPage extends AbstractPage {

    static url = '/points/play'
    static at = {
        waitFor {
            browser.currentUrl.contains("/play")
        }
    }

    static content = {

        clumsyBirdGetText(wait: true) { $('.GetPlay-header') }
        clumsyBirdGameWindow(wait: true) { $('.GameSplashScreen-image') }
        clumsyBirdPlayButton(wait: true) { $('.GameSplashScreen-button') }
        clumsyBirdGameModalWindow(required: false) { $('.modal-content') }
        clumsyBirdGameModalWindowCloseButton(wait: true, required: false) {
            clumsyBirdGameModalWindow.find('.modal-close-btn')
        }
        clumsyBirdInstuctionText(wait: true) { $('.GetPlay-description') }
        clumsyBirdInstructionImage(wait: true) { $('.GetPlay-instructionsImage') }
        clumsyBirdMultiplier(wait: true) { $('.GetPlay-instructionsPoints') }
        clumsyBirdCommercial(wait: true) { $('.GetPlay-container').$('.text-center', 0) }
        clumsyBirdCommercial2(wait: true) { $('.GetPlay-container').$('.text-center', 1) }
        signInButton(required: false) { $('.GameSplashScreen-message').find('.btn') }
        signInInfo(required: false) { $('.GameSplashScreen-message').find('span') }
    }

    def clickPlayClumsyBirdButton() { waitFor { clumsyBirdPlayButton.isDisplayed() }; clumsyBirdPlayButton.click() }

    def clickClumsyBirdGameWindowCloseButton() {
        waitFor { clumsyBirdGameModalWindowCloseButton.isDisplayed() }; clumsyBirdGameModalWindowCloseButton.click()
    }

    def clickSignInButtonOnGamePage() { waitFor { signInButton.isDisplayed() }; signInButton.click() }
}