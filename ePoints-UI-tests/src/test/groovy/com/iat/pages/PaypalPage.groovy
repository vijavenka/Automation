package com.iat.pages

import com.iat.stepdefs.envVariables
import geb.Page

class PaypalPage extends Page {

    def envVar = new envVariables()

    static url = 'https://www.sandbox.paypal.com'
    static at = {
        waitFor(20) {
            getTitle().contains('PayPal')
            !loader.isDisplayed()
        }
    }

    static content = {
        loader(required: false) { $('.loader') }
        loader2(required: false) { $('#progressMeter') }

        cancelTransactionButton(required: false) { $('#defaultCancelLink') }
        emailInputField(required: false) { $('.desktop.injectedUlLayout').$('#email') }
        passwordInputField(required: false) { $('.desktop.injectedUlLayout').$('#password') }
        loginButton(required: false) { $('#btnLogin') }
        continueButton(required: false) { $('#confirmButtonTop') }

        transactionDetailsExpandArrow(required: false) { $('.arrow', 0) }
        transactionDetailsBox(required: false) { $('.details') }
        spendRequestValue(required: false) { transactionDetailsBox.$('.itemPrice', 1) }
        vatRequestValue(required: false) { transactionDetailsBox.$('.itemPrice', 2) }
        feeRequestValue(required: false) { transactionDetailsBox.$('.itemPrice', 3) }
        totalRequestValue(required: false) { transactionDetailsBox.$('.itemPrice', 4) }

        emailInputField2(required: false) { $('#login_email') }
        passwordInputField2(required: false) { $('#login_password') }
        loginButton2(required: false) { $('#submitLogin') }
        continueButton2(required: false) { $('#continue') }
    }

    def clickCancelTransactionButton() {
        waitFor(15) { cancelTransactionButton.isDisplayed() }; cancelTransactionButton.click()
    }

    def enterEmail(email) {
        withFrame("injectedUl") {
            waitFor(15) { emailInputField.isDisplayed() }
            emailInputField.value(email)
        }
    }

    def enterPassword(password) {
        withFrame("injectedUl") {
            waitFor(15) { passwordInputField.isDisplayed() }
            passwordInputField.value(password)
        }
    }

    def clickLoginButton() {
        withFrame("injectedUl") { waitFor(15) { loginButton.isDisplayed() }; loginButton.click() }
    }

    def enterEmail2(email) {
        waitFor(15) { emailInputField2.isDisplayed() }
        emailInputField2.value(email)
    }

    def enterPassword2(password) {
        waitFor(15) { passwordInputField2.isDisplayed() }
        passwordInputField2.value(password)
    }

    def clickLoginButton2() {
        waitFor(15) { loginButton2.isDisplayed() }; loginButton2.click()
    }

    def clickContinueButton() { waitFor(15) { continueButton.isDisplayed() }; continueButton.click() }

    def expandTransactionsDetails() {
        waitFor(15) { transactionDetailsExpandArrow.isDisplayed() }
        transactionDetailsExpandArrow.click()
        waitFor(15) { transactionDetailsBox.isDisplayed() }
    }

//    def loginToPaypalAccount() {
//        if (!continueButton.isDisplayed() && !loginButton2.isDisplayed()) {
//            enterEmail(envVar.paypalUser)
//            enterPassword(envVar.paypalPassword)
//            clickLoginButton()
//            sleep(2000)
//            waitFor { !loader.isDisplayed() }
//            loginToPaypalAccount2()
//            clickContinueButton2()
//        }else{
//            loginToPaypalAccount2()
//            clickContinueButton2()
//        }
//        waitFor(15){ !loader.isDisplayed() }
//        waitFor(15){ !loader2.isDisplayed() }
//    }

    def loginToPaypalAccount() {
        if (!continueButton.isDisplayed()) {
            enterEmail(envVar.paypalUser)
            enterPassword(envVar.paypalPassword)
            clickLoginButton()
            sleep(2000)
            waitFor { !loader.isDisplayed() }
            clickContinueButton()
        }
        waitFor(15) { !loader.isDisplayed() }
        waitFor(15) { !loader2.isDisplayed() }
    }

    def loginToPaypalAccount2() {
        if (!continueButton2.isDisplayed()) {
            enterEmail2(envVar.paypalUser)
            enterPassword2(envVar.paypalPassword)
            clickLoginButton2()
            sleep(2000)
            waitFor { !loader2.isDisplayed() }
        }
    }

    def clickContinueButton2() { waitFor(15) { continueButton2.isDisplayed() }; continueButton2.click() }
}