package stepdefs.EpointsStepDefs.otherSections
import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*
/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 12.06.14
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */


def epoints = new epointsPage()
def func = new Functions()
browser = new Browser()
def envVar = new envVariables()


Given(~'^User is opening deeplink creator landing page$') { ->
    epointsPage.url = envVar.deeplinkBabyworldURL
    to epointsPage
    epoints = page
}

Given(~'^User is opening deeplink creator landing page clear$') { ->
    epointsPage.url =  envVar.deeplinkBabyworldURL
    to epointsPage
    epoints = page
    func.clearCookiesAndStorage()
}

    // 1.1 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
    // ---------------------------------------------------------------------------------------------------- page content
    When(~/^He look at deeplink generator page$/) { ->
        waitFor{ browser.title == 'Deeplink generator | epoints' }
        assert browser.title == 'Deeplink generator | epoints'
    }
    Then(~/^He can see babyworld logo$/) { ->
        waitFor{ epoints.deeplinkCreator.babyworldLogo.isDisplayed() }
        assert epoints.deeplinkCreator.babyworldLogo.isDisplayed()
    }
    Then(~/^All needed input elements and buttons$/) { ->
        waitFor{ epoints.deeplinkCreator.storeInputField.isDisplayed() }
        assert epoints.deeplinkCreator.storeInputField.isDisplayed()
        assert epoints.deeplinkCreator.nativeURLInputField.isDisplayed()
        assert epoints.deeplinkCreator.p1InputField.isDisplayed()
        assert epoints.deeplinkCreator.clearButton.isDisplayed()
        assert epoints.deeplinkCreator.generateLinkButton.isDisplayed()
        assert epoints.deeplinkCreator.generatedLinkField.isDisplayed()
        assert epoints.deeplinkCreator.fieldLabelBasic[0].text() == 'Store'
        assert epoints.deeplinkCreator.fieldLabelBasic[1].text() == 'Native Url'
        assert epoints.deeplinkCreator.fieldLabelBasic[2].text() == 'p1'
        assert epoints.deeplinkCreator.fieldLabelBasic[3].text() == 'Generated Link:'
    }

    // 1.2 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
    // ---------------------------------------------------------------------------------------------------- clear button
    Then(~/^He can see that input fields are empty$/) { ->
        waitFor{ epoints.deeplinkCreator.storeInputField.value() == '' }
        assert epoints.deeplinkCreator.storeInputField.value() == ''
        assert epoints.deeplinkCreator.nativeURLInputField.value() == ''
        assert epoints.deeplinkCreator.p1InputField.value() == 'babyworld'
        assert epoints.deeplinkCreator.generatedLinkField.value() == ''
    }
    When(~/^User fill generator input fields with some values$/) { ->
        epoints.deeplinkCreator.fillAllGInputFields('store name','native url','p1')
        assert epoints.deeplinkCreator.storeInputField.value() == 'store name'
        assert epoints.deeplinkCreator.nativeURLInputField.value() == 'native url'
        assert epoints.deeplinkCreator.p1InputField.value() == 'p1'
    }
    When(~/^Click generator clear button$/) { ->
        epoints.deeplinkCreator.clickClearButton()
    }
    Then(~/^Input generator fields will be cleared$/) { ->
        waitFor{ epoints.deeplinkCreator.storeInputField.value() == '' }
        assert epoints.deeplinkCreator.storeInputField.value() == ''
        assert epoints.deeplinkCreator.nativeURLInputField.value() == ''
        assert epoints.deeplinkCreator.p1InputField.value() == 'babyworld'
    }

    // 1.3 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
    // ----------------------------------------------------------------------------------------------- predictive search
    When(~/^User start typing some text '(.+)' into store input field$/) {String storeName ->
        epoints.deeplinkCreator.enterStoreName(storeName)
    }
    Then(~/^DDL with retailer will be displayed$/) { ->
        Thread.sleep(2000)
        waitFor{ epoints.deeplinkCreator.storeDDLList.isDisplayed() }
        assert epoints.deeplinkCreator.storeDDLList.isDisplayed()
    }
    Then(~/^On list user can find these with phrase '(.+)' typed into input field$/) {String storeName ->
        for(int i=0; i<epoints.deeplinkCreator.storeDDLOption.size(); i++ ){
            assert epoints.deeplinkCreator.storeDDLOption[i].text().contains(storeName)
        }
    }

    // 1.4 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
    // ------------------------------------------------------------------------------ generation of link and its working
    String deeplink
    When(~/^User properly enter store field and native url field with '(.+)' '(.+)'$/) {String storeName, String nativeURL ->
        waitFor{ !epoints.deeplinkCreator.tryLink.isDisplayed() }
        assert !epoints.deeplinkCreator.tryLink.isDisplayed()
        epoints.deeplinkCreator.enterStoreName(storeName)
        epoints.deeplinkCreator.enterNativeURL(nativeURL)
    }
    When(~/^User click generate link button$/) { ->
        epoints.deeplinkCreator.clickGenerateLinkButton(); Thread.sleep(3000)
    }
    Then(~/^Deeplink will be properly generated$/) { ->
        waitFor{ !(epoints.deeplinkCreator.generatedLinkField.value() == '') }
        assert !(epoints.deeplinkCreator.generatedLinkField.value() == '')
        deeplink = epoints.deeplinkCreator.generatedLinkField.value()
    }
    Then(~/^Try direct link will be displayed$/) { ->
        waitFor{ epoints.deeplinkCreator.tryLink.isDisplayed() }
        assert epoints.deeplinkCreator.tryLink.isDisplayed()
    }
    When(~/^User use created link$/) { ->
        browser.go(deeplink)
    }
    Then(~/^He will be redirected to epoints transition page$/) { ->
        waitFor{ browser.title == 'Sending to retailer | epoints' }
        assert browser.title == 'Sending to retailer | epoints'
    }
    When(~/^User disagree to collecting epoints in at transition modal$/) { ->
        epoints.transitionPage.clickNoButtonOnInformationModal()
    }
    Then(~/^He will be redirected to native retailer page '(.+)'$/) {String shortURL  ->
        waitFor(20){ browser.getCurrentUrl().contains(shortURL) }
        assert browser.getCurrentUrl().contains(shortURL)
    }

    // 1.5 //  --------------------------------- WLS REMOVAL - create mechanism to generate deeplink on request(RD-3993)
    // ---------------------------------------------------------------------------------------------- merchant not found
    Then(~/^Merchant not found message including wrong merchant name '(.+)' will be displayed$/) { String wrongMerchantName ->
        waitFor{ epoints.deeplinkCreator.noMerchantAlert.text() == envVar.returnInformationAboutNotFoundMerchant(wrongMerchantName) }
        assert epoints.deeplinkCreator.noMerchantAlert.text() == envVar.returnInformationAboutNotFoundMerchant(wrongMerchantName)
    }
    Then(~/^Deeplink will not be generated$/) { ->
        assert epoints.deeplinkCreator.generatedLinkField.text() == ''
    }