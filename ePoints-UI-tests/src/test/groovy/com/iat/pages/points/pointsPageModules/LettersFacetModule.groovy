package com.iat.pages.points.pointsPageModules

import geb.Module

class LettersFacetModule extends Module {

    static content = {
        azFacet(wait: true) { $('#azLetters') }
        azFacetHeader(wait: true) { azFacet.find('.header-text') }
        azFacetClearButton(wait: true) { azFacet.find('.clear-btn') }
        azFacetLetterButtonBasic(wait: true) { azFacet.find('li') }
        azFacetLetterButtonActiveBasic(wait: true, required: false) { azFacet.find('.active') }
    }

    def clickChosenLetter(number) {
        waitFor { azFacetLetterButtonBasic[number].isDisplayed() }; azFacetLetterButtonBasic[number].click()
    }

    def clickClearButtonOnAzFacet() { waitFor { azFacetClearButton.isDisplayed() }; azFacetClearButton.click() }

}