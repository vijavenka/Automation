package com.iat.stepdefs.otherSections

import com.iat.pages.DeeplinkCreatorPage
import com.iat.pages.points.TransitionPage
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.*

def deeplinkcreatorPage = new DeeplinkCreatorPage()
def transitionPage = new TransitionPage()

def func = new Functions()
browser = new Browser()
def envVar = new envVariables()


Given(~'^User is opening deeplink creator landing page$') { ->
    to DeeplinkCreatorPage
    deeplinkcreatorPage = page
    func.clearCookiesAndStorage()
}

// 1.1 //  ------------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
// -------------------------------------------------------------------------------------------------------- page content
Then(~/^All needed input elements and buttons ar visible$/) { ->
    waitFor { deeplinkcreatorPage.storeInputField.isDisplayed() }
    assert deeplinkcreatorPage.storeInputField.isDisplayed()
    assert deeplinkcreatorPage.nativeURLInputField.isDisplayed()
    assert deeplinkcreatorPage.p1InputField.isDisplayed()
    assert deeplinkcreatorPage.clearButton.isDisplayed()
    assert deeplinkcreatorPage.generateLinkButton.isDisplayed()
    assert deeplinkcreatorPage.generatedLinkField.isDisplayed()
    assert deeplinkcreatorPage.fieldLabelBasic[0].text() == 'Store'
    assert deeplinkcreatorPage.fieldLabelBasic[1].text() == 'Native Url'
    assert deeplinkcreatorPage.fieldLabelBasic[2].text() == 'p1'
    assert deeplinkcreatorPage.fieldLabelBasic[3].text() == 'Generated Link:'
}

// 1.2 //  ------------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
// -------------------------------------------------------------------------------------------------------- clear button
And(~/^He can see that input fields are empty$/) { ->
    waitFor { deeplinkcreatorPage.storeInputField.value() == '' }
    assert deeplinkcreatorPage.storeInputField.value() == ''
    assert deeplinkcreatorPage.nativeURLInputField.value() == ''
    assert deeplinkcreatorPage.p1InputField.value() == 'babyworld'
    assert deeplinkcreatorPage.generatedLinkField.value() == ''
}
When(~/^User fill generator input fields with some values$/) { ->
    deeplinkcreatorPage.fillAllGInputFields('fake store name', 'native url', 'p1')
    assert deeplinkcreatorPage.storeInputField.value() == 'fake store name'
    assert deeplinkcreatorPage.nativeURLInputField.value() == 'native url'
    assert deeplinkcreatorPage.p1InputField.value() == 'p1'
}
When(~/^Click generator clear button$/) { ->
    deeplinkcreatorPage.clickClearButton()
}
Then(~/^Input generator fields will be cleared$/) { ->
    waitFor { deeplinkcreatorPage.storeInputField.value() == '' }
    assert deeplinkcreatorPage.storeInputField.value() == ''
    assert deeplinkcreatorPage.nativeURLInputField.value() == ''
    assert deeplinkcreatorPage.p1InputField.value() == 'babyworld'
}

// 1.3 //  ------------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
// --------------------------------------------------------------------------------------------------- predictive search
When(~/^User start typing some text '(.+)' into store input field$/) { String storeName ->
    deeplinkcreatorPage.enterStoreName(storeName)
}
Then(~/^DDL with retailer will be displayed$/) { ->
    waitFor { deeplinkcreatorPage.storeDDLList.isDisplayed() }
    assert deeplinkcreatorPage.storeDDLList.isDisplayed()
}
Then(~/^On list user can find these with phrase '(.+)' typed into input field$/) { String storeName ->
    for (int i = 0; i < deeplinkcreatorPage.storeDDLOption.size(); i++)
        assert deeplinkcreatorPage.storeDDLOption[i].text().contains(storeName)
}

// 1.4 //  ------------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
// ---------------------------------------------------------------------------------- generation of link and its working
String deeplink
When(~/^User properly enter store field and native url field with '(.+)' '(.+)'$/) { String storeName, String nativeURL ->
    waitFor { !deeplinkcreatorPage.tryLink.isDisplayed() }
    assert !deeplinkcreatorPage.tryLink.isDisplayed()
    deeplinkcreatorPage.enterStoreName(storeName)
    deeplinkcreatorPage.enterNativeURL(nativeURL)
    sleep(1000)
}
When(~/^User click generate link button$/) { ->
    deeplinkcreatorPage.clickGenerateLinkButton(); sleep(3000)
}
Then(~/^Deeplink will be properly generated$/) { ->
    waitFor { !(deeplinkcreatorPage.generatedLinkField.value() == '') }
    assert !(deeplinkcreatorPage.generatedLinkField.value() == '')
    deeplink = deeplinkcreatorPage.generatedLinkField.value()
}
Then(~/^Try direct link will be displayed$/) { ->
    waitFor { deeplinkcreatorPage.tryLink.isDisplayed() }
    assert deeplinkcreatorPage.tryLink.isDisplayed()
}
When(~/^User use created link$/) { ->
    browser.go(deeplink)
}
Then(~/^He will be redirected to epoints transition page$/) { ->
    at TransitionPage
    transitionPage = page
}
Then(~/^He will be redirected to native retailer page '(.+)'$/) { String shortURL ->
    waitFor(20) { browser.getCurrentUrl().contains(shortURL) }
    assert browser.getCurrentUrl().contains(shortURL)
}

// 1.5 //  ------------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
// -------------------------------------------------------------------------------------------------- merchant not found
Then(~/^Merchant not found message including wrong merchant name '(.+)' will be displayed$/) { String wrongMerchantName ->
    waitFor { deeplinkcreatorPage.noMerchantAlert.text() == envVar.informationAboutNotFoundMerchant }
    assert deeplinkcreatorPage.noMerchantAlert.text() == envVar.informationAboutNotFoundMerchant
}
Then(~/^Deeplink will not be generated$/) { ->
    assert deeplinkcreatorPage.generatedLinkField.text() == ''
}