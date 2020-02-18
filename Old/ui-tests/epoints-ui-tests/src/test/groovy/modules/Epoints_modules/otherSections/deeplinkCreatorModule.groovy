package modules.Epoints_modules.otherSections
import geb.Module
/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 13.06.14
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */

class deeplinkCreatorModule extends Module{
    static content = {
        babyworldLogo{ $('.logo') }
        storeInputField{ $('#inputStore') }
        storeDDLList{ $('.ui-autocomplete') }
        storeDDLOption{ storeDDLList.find('.ui-menu-item').find('a') }
        nativeURLInputField{ $('#deeplinkUrl') }
        p1InputField{ $('#p1') }
        clearButton{ $('.clear') }
        generateLinkButton{ $('.generate-link') }
        generatedLinkField{ $('#generatedLink') }
        tryLink(required: false){ $('.try') }
        fieldLabelBasic{ $('label') }
        noMerchantAlert{ $('.alert-danger') }
    }

    def enterStoreName(storeName){ waitFor{ storeInputField.isDisplayed() }; storeInputField.value(storeName) }
    def enterNativeURL(nativeURL){ waitFor{ nativeURLInputField.isDisplayed() }; nativeURLInputField.value(nativeURL) }
    def enterp1(p1){ waitFor{ p1InputField.isDisplayed() }; p1InputField.value(p1) }
    def fillAllGInputFields(storeName, nativeURL, p1){ enterStoreName(storeName); enterNativeURL(nativeURL); enterp1(p1) }

    def clickClearButton(){ waitFor{ clearButton.isDisplayed() }; clearButton.click() }
    def clickGenerateLinkButton(){ waitFor{ generateLinkButton.isDisplayed() }; generateLinkButton.click() }
}