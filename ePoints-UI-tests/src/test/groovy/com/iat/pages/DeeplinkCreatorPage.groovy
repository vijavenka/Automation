package com.iat.pages

import geb.Page

class DeeplinkCreatorPage extends Page {

    static url = '/deeplink/babyworld'
    static at = {
        waitFor {
            getTitle().contains('Deeplink generator | epoints')
        }
    }

    static content = {
        deeplinkGenerator { $('.Deeplink-generator') }
        storeInputField { deeplinkGenerator.find('.Deeplink-stores').find('input') }
        storeDDLList { deeplinkGenerator.find('ul') }
        storeDDLOption { storeDDLList.find('li').find('span') }
        nativeURLInputField { deeplinkGenerator.find('#deeplinkUrl') }
        p1InputField { deeplinkGenerator.find('#p1') }
        clearButton { deeplinkGenerator.find('.clear') }
        generateLinkButton { deeplinkGenerator.find('.btn-green') }
        generatedLinkField { deeplinkGenerator.find('#generatedLink') }
        tryLink(required: false) { deeplinkGenerator.find('.try') }
        fieldLabelBasic { deeplinkGenerator.find('label') }
        noMerchantAlert { deeplinkGenerator.find('.alert-danger') }
    }

    def enterStoreName(storeName) { waitFor { storeInputField.isDisplayed() }; storeInputField.value(storeName) }

    def enterNativeURL(nativeURL) {
        waitFor { nativeURLInputField.isDisplayed() }; nativeURLInputField.value(nativeURL)
    }

    def enterp1(p1) { waitFor { p1InputField.isDisplayed() }; p1InputField.value(p1) }

    def fillAllGInputFields(storeName, nativeURL, p1) {
        enterStoreName(storeName); enterNativeURL(nativeURL); enterp1(p1)
    }

    def clickClearButton() { waitFor { clearButton.isDisplayed() }; clearButton.click() }

    def clickGenerateLinkButton() { waitFor { generateLinkButton.isDisplayed() }; generateLinkButton.click() }

}