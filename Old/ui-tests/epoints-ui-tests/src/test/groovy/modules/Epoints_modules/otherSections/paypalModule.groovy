package modules.Epoints_modules.otherSections

import geb.Module

/**
 * Created by kbaranowski on 2015-04-17.
 */
class paypalModule extends Module{
    static content = {
        cancelTransactionButton{ $('.small.secondary.return').find('.action') }
        payWithMyPaypalAccount(required: false){ $('#loadLogin') }
        emailInputField{ $('#login_email') }
        passwordInputField{ $('#login_password') }
        loginButton{ $('#submitLogin') }
        continueButton{ $('#continue') }
        spendRequestValue{ $('.wrap.items.totals.item1').find('.amount') }
        vatRequestValue{ $('.wrap.items.totals.item1').find('#minicartTaxAmount') }
        feeRequestValue{ $('.wrap.items.totals.item1').find('#displayShippingAmount') }
        totalRequestValue{ $('.grandTotal.amount') }
    }

    def clickCancelTransactionButton(){ waitFor(15){ cancelTransactionButton.isDisplayed() }; cancelTransactionButton.click() }
    def clickPayWithMyPaypalAccountButton(){ waitFor(15){ payWithMyPaypalAccount.isDisplayed() }; payWithMyPaypalAccount.click() }
    def enterEmail(email){ waitFor(15){ emailInputField.isDisplayed() }; emailInputField.value(email) }
    def enterPassword(password){ waitFor(15){ passwordInputField.isDisplayed() }; passwordInputField.value(password) }
    def clickLoginButton(){ waitFor(15){ loginButton.isDisplayed() }; loginButton.click() }
    def clickContinueButton(){ waitFor(15){ continueButton.isDisplayed() }; continueButton.click() }
}