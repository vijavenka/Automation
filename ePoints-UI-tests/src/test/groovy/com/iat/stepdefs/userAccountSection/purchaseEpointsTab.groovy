package com.iat.stepdefs.userAccountSection

import com.iat.Config
import com.iat.pages.PaypalPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.home.footerStaticPages.ContactUsPage
import com.iat.pages.userAccount.purchaseEpoints.PurchaseEpointsPage
import com.iat.repository.impl.UserRepositoryImpl
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions
import com.iat.stepdefs.utils.JdbcDatabaseConnector
import geb.Browser

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def purchaseEpointsPage = new PurchaseEpointsPage()
def paypalPage = new PaypalPage()
def contactUsPage = new ContactUsPage()

def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()
def mySQLConnector = new JdbcDatabaseConnector(Config.mysqlConnectionPoolPointsManager)

// 1.1 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
// ----------------------------------------------------------------------------------------------------- tab content
// Update --------------------------- EPOINTS - update purchase points page offering epoints/pound buckets(NBO-1440)
Given(~/^Purchase epoints page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
    epointsHomePage.goToUserEpointsPurchasePage(true)
    at PurchaseEpointsPage
    purchaseEpointsPage = page
}
Then(~/^He will see that purchase epoints tab has all needed elements to buy epoints$/) { ->
    waitFor {
        purchaseEpointsPage.header.text().replace('\n', '').replace(' ', '') == envVar.purchaseEpointsHeader.replace('\n', '').replace(' ', '')
    }
    waitFor { purchaseEpointsPage.buyEpointsFormText.text() == envVar.purchaseEpointsMainFormText }
    assert purchaseEpointsPage.header.text().replace('\n', '').replace(' ', '') == envVar.purchaseEpointsHeader.replace('\n', '').replace(' ', '')
    assert purchaseEpointsPage.buyEpointsFormText.text() == envVar.purchaseEpointsMainFormText
    assert purchaseEpointsPage.buyEpointsFormDDL.isDisplayed()
    assert purchaseEpointsPage.buyEpointsReviewOrderButton.isDisplayed()
    assert purchaseEpointsPage.rewardsCounterAfterPurchaseSimulation.isDisplayed()
}

// 1.2 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
// ----------------------------------------------------------------------------------- epoints to pounds DDL content
// Update --------------------------- EPOINTS - update purchase points page offering epoints/pound buckets(NBO-1440)
When(~/^User expand buy epoints ddl$/) { ->
    purchaseEpointsPage.expandBuyEpointsDDL()
}
String[][] availablePurchaseOptions = [["6,000", "12,000", "18,000", "24,000", "30,000", "36,000", "42,000", "48,000", "100,000", "200,000"], ["£30", "£60", "£90", "£120", "£150", "£180", "£210", "£240", "£500", "£1,000"]]
Then(~/^He will see it contains proper number of fixed values with proper values$/) { ->
    waitFor { purchaseEpointsPage.buyEpointsFormDDLOption[0].isDisplayed() }
    for (int i = 1; i < purchaseEpointsPage.buyEpointsFormDDLOption.size(); i++) {
        assert purchaseEpointsPage.buyEpointsFormDDLOptionEpointsValue[i].text() == availablePurchaseOptions[0][i - 1]
        assert purchaseEpointsPage.buyEpointsFormDDLOptionPoundsValue[i].text() == availablePurchaseOptions[1][i - 1]
    }
}

// 1.4 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
// ------------------------------------------------------------------ simulation of available rewards after purchase
// Update --------------------------- EPOINTS - update purchase points page offering epoints/pound buckets(NBO-1440)
String epointsToChange
String poundsCost
String userAvailablePointsNumber
When(~/^User select number of epoints he want to buy$/) { ->
    purchaseEpointsPage.expandBuyEpointsDDL()
    waitFor { purchaseEpointsPage.buyEpointsFormDDLOption[0].isDisplayed() }
    userAvailablePointsNumber = purchaseEpointsPage.headerModule.headerPointsBalanceConfirmed.text()
    random = func.returnRandomValue(purchaseEpointsPage.buyEpointsFormDDLOption.size())
    epointsToChange = purchaseEpointsPage.buyEpointsFormDDLOptionEpointsValue[random].text()
    poundsCost = purchaseEpointsPage.buyEpointsFormDDLOptionPoundsValue[random].text()
    purchaseEpointsPage.clickChosenBuyEpointsDDLOption(random)
}
Then(~/^Available items counter will be properly updated according to new points value$/) { ->
    waitFor {
        purchaseEpointsPage.rewardsCounterAfterPurchaseSimulation.text().contains((Integer.parseInt(userAvailablePointsNumber) + Integer.parseInt(epointsToChange.replace(',', ''))).toString())
    }
    assert purchaseEpointsPage.rewardsCounterAfterPurchaseSimulation.text().contains((Integer.parseInt(userAvailablePointsNumber) + Integer.parseInt(epointsToChange.replace(',', ''))).toString())
}

// 1.5 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
// ----------------------------------------------------------------------------------------- 2nd screen page content
// Update --------------------------- EPOINTS - update purchase points page offering epoints/pound buckets(NBO-1440)
When(~/^Second confirmation screen is opened with some pound value to change on epoints$/) { ->
    purchaseEpointsPage.expandBuyEpointsDDL()
    waitFor { purchaseEpointsPage.buyEpointsFormDDLOption.size() == 10 + 1 }
    random = func.returnRandomValue(purchaseEpointsPage.buyEpointsFormDDLOption.size() - 1) + 1
    epointsToChange = purchaseEpointsPage.buyEpointsFormDDLOptionEpointsValue[random].text()
    poundsCost = purchaseEpointsPage.buyEpointsFormDDLOptionPoundsValue[random].text()
    purchaseEpointsPage.clickChosenBuyEpointsDDLOption(random)
    purchaseEpointsPage.clickReviewOrderButton()
    waitFor { purchaseEpointsPage.loaderIcon.isDisplayed() }
    waitFor(20) { !purchaseEpointsPage.loaderIcon.isDisplayed() }
}
When(~/^User look on buy epoints summary page$/) { ->
    //we check here once population of payment table in points_manager
    waitFor(15) {
        mySQLConnector.getSingleResult("SELECT status FROM points_manager.Payment ORDER BY createdAt DESC") == 'created'
    }
    assert mySQLConnector.getSingleResult("SELECT userID FROM points_manager.Payment ORDER BY createdAt DESC") == mySQLConnector.getSingleResult("SELECT id FROM points_manager.User WHERE userKey = '" + new UserRepositoryImpl().findByEmail(Config.epointsUser).getUuid() + "'")
    assert mySQLConnector.getSingleResult("SELECT moneyValue FROM points_manager.Payment ORDER BY createdAt DESC") == purchaseEpointsPage.buyEpointsFormValueField.value().replace('£', '').replace(',', '')
    assert mySQLConnector.getSingleResult("SELECT epoints FROM points_manager.Payment ORDER BY createdAt DESC") == (epointsToChange.replace(',', '')).toString()
    assert mySQLConnector.getSingleResult("SELECT fee FROM points_manager.Payment ORDER BY createdAt DESC") == purchaseEpointsPage.buyEpointsFormFeeField.value().replace('£', '').replace(',', '')
    assert mySQLConnector.getSingleResult("SELECT tax FROM points_manager.Payment ORDER BY createdAt DESC") == purchaseEpointsPage.buyEpointsFormVATField.value().replace('£', '').replace(',', '')
    assert mySQLConnector.getSingleResult("SELECT total FROM points_manager.Payment ORDER BY createdAt DESC") == purchaseEpointsPage.buyEpointsFormTotalField.value().replace('£', '').replace(',', '')
}
Then(~/^He can see that page contains all needed element$/) { ->
    waitFor {
        purchaseEpointsPage.buyEpointsFormTextSecondScreen.text() == envVar.purchaseEpointsMainFormTextSecondScreen
    }
    assert purchaseEpointsPage.header.text().replace('\n', '').replace(' ', '') == envVar.purchaseEpointsHeader.replace('\n', '').replace(' ', '')
    assert purchaseEpointsPage.buyEpointsFormTextSecondScreen.text() == envVar.purchaseEpointsMainFormTextSecondScreen
    assert purchaseEpointsPage.buyEpointsFormValueField.isDisplayed()
    assert purchaseEpointsPage.buyEpointsFormVATField.isDisplayed()
    assert purchaseEpointsPage.buyEpointsFormFeeField.isDisplayed()
    assert purchaseEpointsPage.buyEpointsFormTotalField.isDisplayed()
    assert purchaseEpointsPage.buyEpointsCancelButton.isDisplayed()
    assert purchaseEpointsPage.buyEpointsConfirmButton.isDisplayed()
    assert purchaseEpointsPage.buyEpointsFormValueLabel.text() == envVar.purchaseEpointsValueFieldLabel
    assert purchaseEpointsPage.buyEpointsFormVATLabel.text() == envVar.purchaseEpointsVATFieldLabel
    assert purchaseEpointsPage.buyEpointsFormFeeLabel.text() == envVar.purchaseEpointsFeeFieldLabel
    assert purchaseEpointsPage.buyEpointsFormTotalLabel.text() == envVar.purchaseEpointsTotalFieldLabel
}
Then(~/^All values are properly calculated on buy epoints summary page$/) { ->
    def poundsCostValue = (poundsCost.replace('£', '').replace(',', '')).toFloat()
    assert purchaseEpointsPage.buyEpointsFormEpointsValueField.value() == epointsToChange.replace(',', '')
    assert Float.parseFloat(purchaseEpointsPage.buyEpointsFormValueField.value().replace('£', '').replace(',', '')).round() == ((float) poundsCostValue).round()
    assert Float.parseFloat(purchaseEpointsPage.buyEpointsFormFeeField.value().replace('£', '').replace(',', '')).round() == ((float) poundsCostValue * 0.05).round()
    assert Float.parseFloat(purchaseEpointsPage.buyEpointsFormVATField.value().replace('£', '').replace(',', '')).round() == (((float) poundsCostValue + (float) poundsCostValue * 0.05) * 0.2).round()
    assert Float.parseFloat(purchaseEpointsPage.buyEpointsFormTotalField.value().replace('£', '').replace(',', '')).round() == (((float) poundsCostValue + (float) poundsCostValue * 0.05) * 0.2 + (float) poundsCostValue * 0.05 + (float) poundsCostValue).round()
}

// 1.6 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
// ---------------------------------------------------------------------------------------- 2nd screen cancel button
When(~/^User click cancel button on buy epoints summary page$/) { ->
    purchaseEpointsPage.clickCancelButton()
}
Then(~/^Summary page will be closed and user stay on purchase epoints screen$/) { ->
    waitFor { !(purchaseEpointsPage.buyEpointsConfirmButton.isDisplayed()) }
    assert purchaseEpointsPage.buyEpointsReviewOrderButton.isDisplayed()
}

// 1.7 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
// --------------------------------------------------------------------------------------- 2nd screen confirm button
When(~/^User click confirm button on buy epoints summary page$/) { ->
    browser.withNewWindow({ purchaseEpointsPage.clickConfirmButton() }, close: true, wait: true) {
        at PaypalPage
        paypalPage = page
    }
}
Then(~/^New external paypal page will be opened$/) { ->
    //done in previous step
}

// 1.8 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
// ------------------------------------------------------------------------------ 2nd screen manual redirection link
When(~/^User click confirm button on buy epoints summary$/) { ->
    purchaseEpointsPage.clickConfirmButton()
}
Then(~/^On basic epoints page proper redirection info will be displayed$/) { ->
    waitFor { purchaseEpointsPage.redirectInfoBasic.isDisplayed() }
    assert purchaseEpointsPage.redirectInfoBasic.text().replace('\n', '').replace(' ', '') == envVar.redirectPaymentInfo.replace('\n', '').replace(' ', '')
}
When(~/^User click manual redirection link$/) { ->
    browser.withNewWindow({ purchaseEpointsPage.clickRedirectInfoLink() }, close: true, wait: true) {
        at PaypalPage
        paypalPage = page
    }
}
Then(~/^Paypal page will be opened$/) { ->
    //done in previous step
}

// 1.9 //  -------------------------- EPOINTS - take payment for buying epoints - screen 2 payment gateway(NBO-1217)
// 1.9 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 3 receipt(NBO-1218)
// ------------------------------------------------------------------------------------------------- payment failure
When(~/^User paymant fails from some reason$/) { ->
    browser.withNewWindow({ purchaseEpointsPage.clickRedirectInfoLink() }, close: true, wait: true) {
        at PaypalPage
        paypalPage = page
        paypalPage.clickCancelTransactionButton()
        at PurchaseEpointsPage
        purchaseEpointsPage = page
        waitFor { purchaseEpointsPage.redirectInfoBasic.isDisplayed() }
        assert purchaseEpointsPage.redirectInfoBasic.text().replace('\n', '').replace(' ', '') == envVar.failurePaymentInfo.replace('\n', '').replace(' ', '')
        purchaseEpointsPage.clickRedirectInfoLink()
        at ContactUsPage
        contactUsPage = page
    }
}
Then(~/^On epoints page proper info will be displayed about payment fails$/) { ->
    //done in first step in this test
}
When(~/^User click contact us link on failure message$/) { ->
    //done in first step in this test
}
Then(~/^He will be redirected to help page$/) { ->
    //done in first step in this test
}

// 1.10 //  ------------------------- EPOINTS - take payment for buying epoints - screen 2 payment gateway(NBO-1217)
// 1.10 //  --------------------------------- EPOINTS - take payment for buying epoints - screen 3 receipt(NBO-1218)
// ------------------------------------------------------------------------------------------------- payment success

String amount
String vat
String fee
String total
When(~/^User payment was properly done$/) { ->
    waitFor { purchaseEpointsPage.buyEpointsFormValueField.isDisplayed() }
    amount = purchaseEpointsPage.buyEpointsFormValueField.value().replace('£', '')
    vat = purchaseEpointsPage.buyEpointsFormVATField.value().replace('£', '')
    fee = purchaseEpointsPage.buyEpointsFormFeeField.value().replace('£', '')
    total = purchaseEpointsPage.buyEpointsFormTotalField.value().replace('£', '')

    browser.withNewWindow({ purchaseEpointsPage.clickRedirectInfoLink() }, close: true, wait: true) {
        at PaypalPage
        paypalPage = page
        paypalPage.loginToPaypalAccount()
        at PurchaseEpointsPage
        purchaseEpointsPage = page
        waitFor {
            purchaseEpointsPage.header.text().replace('\n', '').replace(' ', '').contains(envVar.returnSuccessfullPaymentInfo(epointsToChange.replace(',', '')).replace('\n', '').replace(' ', ''))
        }
        assert purchaseEpointsPage.header.text().replace('\n', '').replace(' ', '').contains(envVar.returnSuccessfullPaymentInfo(epointsToChange.replace(',', '')).replace('\n', '').replace(' ', ''))
        waitFor { purchaseEpointsPage.printButton.isDisplayed() }
        assert purchaseEpointsPage.printButton.isDisplayed()
        purchaseEpointsPage.clickSpendButtonOnSummaryPage()
        waitFor { browser.currentUrl.contains(envVar.epointsLink + envVar.spendURL) }
        assert browser.currentUrl.contains(envVar.epointsLink + envVar.spendURL)
        browser.getDriver().navigate().back()
        waitFor { browser.currentUrl.contains(envVar.epointsLink + envVar.purchaseEpointsURL) }
        purchaseEpointsPage.clickGoToProfileButtonOnSummaryPage()
        waitFor { browser.currentUrl.contains(envVar.epointsLink + envVar.myAccountURL) }
        assert browser.currentUrl.contains(envVar.epointsLink + envVar.myAccountURL)
    }
}
Then(~/^On epoints page proper info will be displayed about payment success$/) { ->
    //done in first step in this test
}
Then(~/^Print button will be available$/) { ->
    //done in first step in this test
}
Then(~/^Points will be properly awarded to user epoints account$/) { ->
    //payment table

    def userEmail = Config.epointsUser
    def userUuid = new UserRepositoryImpl().findByEmail(userEmail).getUuid()
    def userId = mySQLConnector.getSingleResult("SELECT id FROM points_manager.User WHERE userKey = '" + userUuid + "'")

    waitFor(15) {
        mySQLConnector.getSingleResult("SELECT status FROM points_manager.Payment ORDER BY createdAt DESC") == 'approved'
    }
    assert mySQLConnector.getSingleResult("SELECT moneyValue FROM points_manager.Payment WHERE userId = '" + userId + "'  ORDER BY createdAt DESC") == amount.replace(',', '')
    assert mySQLConnector.getSingleResult("SELECT epoints FROM points_manager.Payment WHERE userId = '" + userId + "' ORDER BY createdAt DESC") == epointsToChange.replace(',', '')
    assert mySQLConnector.getSingleResult("SELECT tax FROM points_manager.Payment WHERE userId = '" + userId + "' ORDER BY createdAt DESC") == vat.replace(',', '')
    assert mySQLConnector.getSingleResult("SELECT fee FROM points_manager.Payment WHERE userId = '" + userId + "' ORDER BY createdAt DESC") == fee.replace(',', '')
    assert mySQLConnector.getSingleResult("SELECT total FROM points_manager.Payment WHERE userId = '" + userId + "' ORDER BY createdAt DESC") == total.replace(',', '')
    assert envVar.paypalUserName.contains(mySQLConnector.getSingleResult("SELECT payerFirstname FROM points_manager.Payment WHERE userId = '" + userId + "' ORDER BY createdAt DESC"))
    assert envVar.paypalUserName.contains(mySQLConnector.getSingleResult("SELECT payerLastname FROM points_manager.Payment WHERE userId = '" + userId + "' ORDER BY createdAt DESC"))
    //points table
    assert mySQLConnector.getSingleResult("SELECT activityInfo FROM points_manager.Points WHERE userId = '" + userId + "' ORDER BY createdAt DESC") == 'epoints purchased from epoints.com'
    assert mySQLConnector.getSingleResult("SELECT externalTransactionId FROM points_manager.Points WHERE userId = '" + userId + "' ORDER BY createdAt DESC") == mySQLConnector.getSingleResult("SELECT paymentId FROM points_manager.Payment ORDER BY createdAt DESC")
    assert mySQLConnector.getSingleResult("SELECT status FROM points_manager.Points WHERE userId = '" + userId + "' ORDER BY createdAt DESC") == 'CONFIRMED'
    assert mySQLConnector.getSingleResult("SELECT delta FROM points_manager.Points WHERE userId = '" + userId + "' ORDER BY createdAt DESC") == epointsToChange.replace(',', '')
    assert mySQLConnector.getSingleResult("SELECT onBehalfOfId FROM points_manager.Points WHERE userId = '" + userId + "' ORDER BY createdAt DESC") == 'ePoints.com'
    assert mySQLConnector.getSingleResult("SELECT tagId FROM points_manager.Points WHERE userId = '" + userId + "' ORDER BY createdAt DESC") == '26883'
}
When(~/^User click spend epoints link on success message$/) { ->
    //done in first step in this test
}
Then(~/^He will be redirected to spend page$/) { ->
    //done in first step in this test
}
When(~/^He back and click go to profile button$/) { ->
    //done in first step in this test
}
Then(~/^He will be redirected to user profile page$/) { ->
    //done in first step in this test
}

// 1.11 //  ------------------------- EPOINTS - take payment for buying epoints - screen 2 payment gateway(NBO-1217)
// ------------------------------------------------------------------------------ correctness of data on paypal page
When(~/^User decide to buy some epoints and click confirm button$/) { ->
    waitFor { purchaseEpointsPage.buyEpointsFormValueField.isDisplayed() }
    amount = purchaseEpointsPage.buyEpointsFormValueField.value()
    vat = purchaseEpointsPage.buyEpointsFormVATField.value()
    fee = purchaseEpointsPage.buyEpointsFormFeeField.value()
    total = purchaseEpointsPage.buyEpointsFormTotalField.value()

    browser.withNewWindow({ purchaseEpointsPage.clickRedirectInfoLink() }, close: true, wait: true) {
        at PaypalPage
        paypalPage = page
        paypalPage.expandTransactionsDetails()
        assert (paypalPage.spendRequestValue.text().contains(amount))
        assert (paypalPage.vatRequestValue.text().contains(vat))
        assert (paypalPage.feeRequestValue.text().contains(fee))
        assert (paypalPage.totalRequestValue.text().contains(total))
    }
}
Then(~/^On paypal page he will see that all values are correct according to his request$/) { ->
    //done in first step in this test
}

// 1.12 //  --------------------------------- EPOINTS - take payment for buying epoints - screen 3 receipt(NBO-1218)
// ------------------------------------------------------------------------------------------------- payment details
Given(~/^User bought some epoints value$/) { ->
    waitFor { purchaseEpointsPage.buyEpointsFormValueField.isDisplayed() }
    amount = purchaseEpointsPage.buyEpointsFormValueField.value().replace('£', '')
    vat = purchaseEpointsPage.buyEpointsFormVATField.value().replace('£', '')
    fee = purchaseEpointsPage.buyEpointsFormFeeField.value().replace('£', '')
    total = purchaseEpointsPage.buyEpointsFormTotalField.value().replace('£', '')

    browser.withNewWindow({ purchaseEpointsPage.clickRedirectInfoLink() }, close: true, wait: true) {
        at PaypalPage
        paypalPage = page
        paypalPage.loginToPaypalAccount()
        at PurchaseEpointsPage
        purchaseEpointsPage = page

        waitFor { purchaseEpointsPage.redirectInfoBasic.isDisplayed() }
        assert purchaseEpointsPage.header.text().replace('\n', '').replace(' ', '').contains(envVar.returnSuccessfullPaymentInfo(epointsToChange.replace(',', '')).replace('\n', '').replace(' ', ''))
        assert purchaseEpointsPage.paymentDetailsHeader.text() == envVar.paymentDetailsHeader
        assert purchaseEpointsPage.paymentDetailsLabelBasic(0).text() == envVar.paymentDetailsDateLabel
        assert purchaseEpointsPage.paymentDetailsLabelBasic(1).text() == envVar.paymentDetailsPaymentStatusLabel
        assert purchaseEpointsPage.paymentDetailsLabelBasic(2).text() == envVar.paymentDetailsPayerNameLabel
        assert purchaseEpointsPage.paymentDetailsLabelBasic(3).text() == envVar.paymentDetailsPayerEmailLabel
        assert purchaseEpointsPage.paymentDetailsLabelBasic(4).text() == envVar.paymentDetailsCurrencyLabel
        assert purchaseEpointsPage.paymentDetailsLabelBasic(5).text() == envVar.paymentDetailsValueLabel
        assert purchaseEpointsPage.paymentDetailsLabelBasic(6).text() == envVar.paymentDetailsFeeLabel
        assert purchaseEpointsPage.paymentDetailsLabelBasic(7).text() == envVar.paymentDetailsTaxLabel
        assert purchaseEpointsPage.paymentDetailsLabelBasic(8).text() == envVar.paymentDetailsTotalLabel

        assert purchaseEpointsPage.paymentDetailsValueBasic(1).text() == 'approved'
        assert purchaseEpointsPage.paymentDetailsValueBasic(2).text() == envVar.paypalUserName
        assert purchaseEpointsPage.paymentDetailsValueBasic(3).text() == envVar.paypalUser
        assert purchaseEpointsPage.paymentDetailsValueBasic(4).text() == 'GBP'
        assert purchaseEpointsPage.paymentDetailsValueBasic(5).text() == amount.replace(',', '')
        assert purchaseEpointsPage.paymentDetailsValueBasic(6).text() == fee
        assert purchaseEpointsPage.paymentDetailsValueBasic(7).text() == vat
        assert purchaseEpointsPage.paymentDetailsValueBasic(8).text() == total.replace(',', '')
        // - 2-1 hours depends of time
        assert ((func.convertDateToEpochFormat(purchaseEpointsPage.paymentDetailsValueBasic(0).text(), 'YYYY-MM-dd') <= func.returnEpochOfCurrentDay() - 3400000000) || (func.convertDateToEpochFormat(purchaseEpointsPage.paymentDetailsValueBasic(0).text(), 'YYYY-MM-dd') >= func.returnEpochOfCurrentDay() - 7400000000))
    }
}
When(~/^User look on page after redirect to epoints$/) { ->
    //done in first step in this test
}
Then(~/^He can see proper summary of his payment$/) { ->
    //done in first step in this test
}