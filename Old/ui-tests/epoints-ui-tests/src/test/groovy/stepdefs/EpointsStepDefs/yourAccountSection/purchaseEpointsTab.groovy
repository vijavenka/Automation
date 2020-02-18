package stepdefs.EpointsStepDefs.yourAccountSection

import geb.Browser
import mysqlConnection.jdbc
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-04-16.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

    // 1.1 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
    // ----------------------------------------------------------------------------------------------------- tab content
    // Update --------------------------- EPOINTS - update purchase points page offering epoints/pound buckets(NBO-1440)
    Given(~/^Purchase epoints page is opened$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        func.closeAllAdditionalTabs()
        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
        epoints.signModule.clickSignInButton()
        epoints.clickYourAccountButton()
        Thread.sleep(2000)
        epoints.clickPurchaseEpointsButton()
    }
    When(~/^User look on purchase epoints tab$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.purchaseEpointsURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.purchaseEpointsURL
    }
    Then(~/^He will see that purchase epoints tab has all needed elements to buy epoints$/) { ->
        waitFor{ epoints.purchaseEpointsTab.header.text().replace('\n','').replace(' ','') == envVar.purchaseEpointsHeader.replace('\n','').replace(' ','') }
        waitFor{ epoints.purchaseEpointsTab.buyEpointsFormText.text() == envVar.purchaseEpointsMainFormText }
        assert epoints.purchaseEpointsTab.header.text().replace('\n','').replace(' ','') == envVar.purchaseEpointsHeader.replace('\n','').replace(' ','')
        assert epoints.purchaseEpointsTab.buyEpointsFormText.text() == envVar.purchaseEpointsMainFormText
        assert epoints.purchaseEpointsTab.buyEpointsFormDDL.isDisplayed()
        assert epoints.purchaseEpointsTab.buyEpointsReviewOrderButton.isDisplayed()
        assert epoints.purchaseEpointsTab.rewardsCounterAfterPurchaseSimulation.isDisplayed()
    }

    // 1.2 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
    // ----------------------------------------------------------------------------------- epoints to pounds DDL content
    // Update --------------------------- EPOINTS - update purchase points page offering epoints/pound buckets(NBO-1440)
    When(~/^User expand buy epoints ddl$/) { ->
        epoints.purchaseEpointsTab.expandBuyEpointsDDL()
    }
    def String[][] availablePurchaseOptions = [["6,000","12,000","18,000","24,000","30,000","36,000","42,000","48,000","100,000","200,000"],["£30","£60","£90","£120","£150","£180","£210","£240","£500","£1,000"]]
    Then(~/^He will see it contains proper number of fixed values with proper values$/) { ->
        waitFor{ epoints.purchaseEpointsTab.buyEpointsFormDDLOption.isDisplayed() }
        for(int i=1; i<epoints.purchaseEpointsTab.buyEpointsFormDDLOption.size(); i++){
           assert epoints.purchaseEpointsTab.buyEpointsFormDDLOptionEpointsValue[i].text() == availablePurchaseOptions[0][i-1]
           assert epoints.purchaseEpointsTab.buyEpointsFormDDLOptionPoundsValue[i].text() == availablePurchaseOptions[1][i-1]
        }
    }

    // 1.4 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
    // ------------------------------------------------------------------ simulation of available rewards after purchase
    // Update --------------------------- EPOINTS - update purchase points page offering epoints/pound buckets(NBO-1440)
    String epointsToChange
    String poundsCost
    String userAvailablePointsNumber
    When(~/^User select number of epoints he want to buy$/) { ->
        epoints.purchaseEpointsTab.expandBuyEpointsDDL()
        waitFor{ epoints.purchaseEpointsTab.buyEpointsFormDDLOption.isDisplayed() }
        userAvailablePointsNumber = epoints.yourEpointsCounter.text()
        random = func.returnRandomValue(epoints.purchaseEpointsTab.buyEpointsFormDDLOption.size())
        epointsToChange = epoints.purchaseEpointsTab.buyEpointsFormDDLOptionEpointsValue[random].text()
        poundsCost = epoints.purchaseEpointsTab.buyEpointsFormDDLOptionPoundsValue[random].text()
        epoints.purchaseEpointsTab.clickChosenBuyEpointsDDLOption(random)
    }
    Then(~/^Available items counter will be properly updated according to new points value$/) { ->
        waitFor{ epoints.purchaseEpointsTab.rewardsCounterAfterPurchaseSimulation.text().contains((Integer.parseInt(userAvailablePointsNumber)+Integer.parseInt(epointsToChange.replace(',',''))).toString()) }
        assert epoints.purchaseEpointsTab.rewardsCounterAfterPurchaseSimulation.text().contains((Integer.parseInt(userAvailablePointsNumber)+Integer.parseInt(epointsToChange.replace(',',''))).toString())
    }

    // 1.5 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
    // ----------------------------------------------------------------------------------------- 2nd screen page content
    // Update --------------------------- EPOINTS - update purchase points page offering epoints/pound buckets(NBO-1440)
    When(~/^Second confirmation screen is opened with some pound value to change on epoints$/) { ->
        epoints.purchaseEpointsTab.expandBuyEpointsDDL()
        waitFor{ epoints.purchaseEpointsTab.buyEpointsFormDDLOption.size() == 10+1 }
        random = func.returnRandomValue(epoints.purchaseEpointsTab.buyEpointsFormDDLOption.size()-1)+1
        epointsToChange = epoints.purchaseEpointsTab.buyEpointsFormDDLOptionEpointsValue[random].text()
        poundsCost = epoints.purchaseEpointsTab.buyEpointsFormDDLOptionPoundsValue[random].text()
        epoints.purchaseEpointsTab.clickChosenBuyEpointsDDLOption(random)
        epoints.purchaseEpointsTab.clickReviewOrderButton()
        waitFor{ epoints.purchaseEpointsTab.loaderIcon.isDisplayed() }
        waitFor(10){ !epoints.purchaseEpointsTab.loaderIcon.isDisplayed() }
    }
    When(~/^User look on buy epoints summary page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.purchaseEpointsURL }
        assert browser.currentUrl == envVar.epointsLink + envVar.purchaseEpointsURL
        //we check here once population of payment table in points_manager
        def mysql = new jdbc("points")
        waitFor(15){ mysql.get("SELECT status FROM points_manager.Payment ORDER BY createdAt DESC",1) == 'created' }
            assert mysql.get("SELECT userID FROM points_manager.Payment ORDER BY createdAt DESC",1) == mysql.get("SELECT id FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert mysql.get("SELECT moneyValue FROM points_manager.Payment ORDER BY createdAt DESC",1) == epoints.purchaseEpointsTab.buyEpointsFormValueField.value().replace('£','').replace(',','')
            assert mysql.get("SELECT epoints FROM points_manager.Payment ORDER BY createdAt DESC",1) == (epointsToChange.replace(',','')).toString()
            assert mysql.get("SELECT fee FROM points_manager.Payment ORDER BY createdAt DESC",1) == epoints.purchaseEpointsTab.buyEpointsFormFeeField.value().replace('£','').replace(',','')
            assert mysql.get("SELECT tax FROM points_manager.Payment ORDER BY createdAt DESC",1) == epoints.purchaseEpointsTab.buyEpointsFormVATField.value().replace('£','').replace(',','')
            assert mysql.get("SELECT total FROM points_manager.Payment ORDER BY createdAt DESC",1) == epoints.purchaseEpointsTab.buyEpointsFormTotalField.value().replace('£','').replace(',','')
        mysql.close()
    }
    Then(~/^He can see that page contains all needed element$/) { ->
        waitFor{ epoints.purchaseEpointsTab.buyEpointsFormTextSecondScreen.text() == envVar.purchaseEpointsMainFormTextSecondScreen }
        assert epoints.purchaseEpointsTab.header.text().replace('\n','').replace(' ','') == envVar.purchaseEpointsHeader.replace('\n','').replace(' ','')
        assert epoints.purchaseEpointsTab.buyEpointsFormTextSecondScreen.text() == envVar.purchaseEpointsMainFormTextSecondScreen
        assert epoints.purchaseEpointsTab.buyEpointsFormValueField.isDisplayed()
        assert epoints.purchaseEpointsTab.buyEpointsFormVATField.isDisplayed()
        assert epoints.purchaseEpointsTab.buyEpointsFormFeeField.isDisplayed()
        assert epoints.purchaseEpointsTab.buyEpointsFormTotalField.isDisplayed()
        assert epoints.purchaseEpointsTab.buyEpointsCancelButton.isDisplayed()
        assert epoints.purchaseEpointsTab.buyEpointsConfirmButton.isDisplayed()
        assert epoints.purchaseEpointsTab.buyEpointsFormValueLabel.text() == envVar.purchaseEpointsValueFieldLabel
        assert epoints.purchaseEpointsTab.buyEpointsFormVATLabel.text() == envVar.purchaseEpointsVATFieldLabel
        assert epoints.purchaseEpointsTab.buyEpointsFormFeeLabel.text() == envVar.purchaseEpointsFeeFieldLabel
        assert epoints.purchaseEpointsTab.buyEpointsFormTotalLabel.text() == envVar.purchaseEpointsTotalFieldLabel
    }
    Then(~/^All values are properly calculated on buy epoints summary page$/) { ->
        def poundsCostValue = (poundsCost.replace('£','').replace(',','')).toFloat()
        assert epoints.purchaseEpointsTab.buyEpointsFormEpointsValueField.value() == epointsToChange.replace(',','')
        assert Float.parseFloat(epoints.purchaseEpointsTab.buyEpointsFormValueField.value().replace('£','').replace(',','')).round() == ((float)poundsCostValue).round()
        assert Float.parseFloat(epoints.purchaseEpointsTab.buyEpointsFormFeeField.value().replace('£','').replace(',','')).round() == ((float)poundsCostValue*0.05).round()
        assert Float.parseFloat(epoints.purchaseEpointsTab.buyEpointsFormVATField.value().replace('£','').replace(',','')).round() == (((float)poundsCostValue + (float)poundsCostValue*0.05)*0.2).round()
        assert Float.parseFloat(epoints.purchaseEpointsTab.buyEpointsFormTotalField.value().replace('£','').replace(',','')).round() == (((float)poundsCostValue + (float)poundsCostValue*0.05)*0.2 + (float)poundsCostValue*0.05 +(float)poundsCostValue).round()
    }

    // 1.6 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
    // ---------------------------------------------------------------------------------------- 2nd screen cancel button
    When(~/^User click cancel button on buy epoints summary page$/) { ->
        epoints.purchaseEpointsTab.clickCancelButton()
    }
    Then(~/^Summary page will be closed and user stay on purchase epoints screen$/) { ->
        waitFor{ !(epoints.purchaseEpointsTab.buyEpointsConfirmButton.isDisplayed()) }
        assert epoints.purchaseEpointsTab.buyEpointsReviewOrderButton.isDisplayed()
    }

    // 1.7 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
    // --------------------------------------------------------------------------------------- 2nd screen confirm button
    When(~/^User click confirm button on buy epoints summary page$/) { ->
        browser.withNewWindow( {epoints.purchaseEpointsTab.clickConfirmButton() }, close:true){
            waitFor{ browser.currentUrl.contains('paypal')  }
            assert browser.currentUrl.contains('paypal')
        }
    }
    Then(~/^New external paypal page will be opened$/) { ->
        //done in previous step
    }

    // 1.8 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 1 confirm(NBO-1142)
    // ------------------------------------------------------------------------------ 2nd screen manual redirection link
    When(~/^User click confirm button on buy epoints summary$/) { ->
        epoints.purchaseEpointsTab.clickConfirmButton()
    }
    Then(~/^On basic epoints page proper redirection info will be displayed$/) { ->
        waitFor{ epoints.purchaseEpointsTab.redirectInfoBasic.isDisplayed() }
        assert epoints.purchaseEpointsTab.redirectInfoBasic.text().replace('\n','').replace(' ','') == envVar.redirectPaymentInfo.replace('\n','').replace(' ','')
    }
    When(~/^User click manual redirection link$/) { ->
        browser.withNewWindow( {epoints.purchaseEpointsTab.clickRedirectInfoLink() }, close:true){
            waitFor{ browser.currentUrl.contains('paypal')  }
            assert browser.currentUrl.contains('paypal')
        }
    }
    Then(~/^Paypal page will be opened$/) { ->
        //done in previous step
    }

    // 1.9 //  -------------------------- EPOINTS - take payment for buying epoints - screen 2 payment gateway(NBO-1217)
    // 1.9 //  ---------------------------------- EPOINTS - take payment for buying epoints - screen 3 receipt(NBO-1218)
    // ------------------------------------------------------------------------------------------------- payment failure
    When(~/^User paymant fails from some reason$/) { ->
        browser.withNewWindow( {epoints.purchaseEpointsTab.clickRedirectInfoLink() }, close:true){
            waitFor{ browser.currentUrl.contains('paypal')  }
            assert browser.currentUrl.contains('paypal')

            epoints.paypal.clickCancelTransactionButton()

            waitFor(10){ epoints.purchaseEpointsTab.redirectInfoBasic.isDisplayed() }
            assert epoints.purchaseEpointsTab.redirectInfoBasic.text().replace('\n','').replace(' ','') == envVar.failurePaymentInfo.replace('\n','').replace(' ','')
            epoints.purchaseEpointsTab.clickRedirectInfoLink()
            waitFor{ browser.currentUrl == envVar.contactUsURL }
            assert browser.currentUrl == envVar.contactUsURL
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
        waitFor{ epoints.purchaseEpointsTab.buyEpointsFormValueField.isDisplayed()  }
        amount = epoints.purchaseEpointsTab.buyEpointsFormValueField.value().replace('£','')
        vat = epoints.purchaseEpointsTab.buyEpointsFormVATField.value().replace('£','')
        fee = epoints.purchaseEpointsTab.buyEpointsFormFeeField.value().replace('£','')
        total = epoints.purchaseEpointsTab.buyEpointsFormTotalField.value().replace('£','')

        browser.withNewWindow( {epoints.purchaseEpointsTab.clickRedirectInfoLink() }, close:true){
            waitFor{ browser.currentUrl.contains('paypal')  }
            assert browser.currentUrl.contains('paypal')

            if(epoints.paypal.payWithMyPaypalAccount.isDisplayed()){
                epoints.paypal.clickPayWithMyPaypalAccountButton()
            }
            epoints.paypal.enterEmail(envVar.paypalUser)
            epoints.paypal.enterPassword(envVar.paypalPassword)
            epoints.paypal.clickLoginButton()
            epoints.paypal.clickContinueButton()

            waitFor(10){ epoints.purchaseEpointsTab.header.text().replace('\n','').replace(' ','').contains(envVar.returnSuccessfullPaymentInfo(epointsToChange.replace(',','')).replace('\n','').replace(' ','')) }
            assert epoints.purchaseEpointsTab.header.text().replace('\n','').replace(' ','').contains(envVar.returnSuccessfullPaymentInfo(epointsToChange.replace(',','')).replace('\n','').replace(' ',''))
            waitFor{ epoints.purchaseEpointsTab.printButton.isDisplayed() }
            assert epoints.purchaseEpointsTab.printButton.isDisplayed()
            epoints.purchaseEpointsTab.clickSpendButtonOnSummaryPage()
            waitFor{ browser.currentUrl.contains(envVar.epointsLink + envVar.spendURL) }
            assert browser.currentUrl.contains(envVar.epointsLink + envVar.spendURL)
            browser.getDriver().navigate().back()
            waitFor{ browser.currentUrl.contains(envVar.epointsLink + envVar.purchaseEpointsURL) }
            epoints.purchaseEpointsTab.clickGoToProfileButtonOnSummaryPage()
            waitFor{ browser.currentUrl.contains(envVar.epointsLink + envVar.myAccountURL) }
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
        def mysql = new jdbc("points")
            //payment table
            waitFor(15){ mysql.get("SELECT status FROM points_manager.Payment ORDER BY createdAt DESC",1) == 'approved' }
            assert mysql.get("SELECT userID FROM points_manager.Payment ORDER BY createdAt DESC",1) == mysql.get("SELECT id FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert mysql.get("SELECT moneyValue FROM points_manager.Payment ORDER BY createdAt DESC",1) == amount.replace(',','')
            assert mysql.get("SELECT epoints FROM points_manager.Payment ORDER BY createdAt DESC",1) == epointsToChange.replace(',','')
            assert mysql.get("SELECT tax FROM points_manager.Payment ORDER BY createdAt DESC",1) == vat.replace(',','')
            assert mysql.get("SELECT fee FROM points_manager.Payment ORDER BY createdAt DESC",1) == fee.replace(',','')
            assert mysql.get("SELECT total FROM points_manager.Payment ORDER BY createdAt DESC",1) == total.replace(',','')
            assert envVar.paypalUserName.contains(mysql.get("SELECT payerFirstname FROM points_manager.Payment ORDER BY createdAt DESC",1))
            assert envVar.paypalUserName.contains(mysql.get("SELECT payerLastname FROM points_manager.Payment ORDER BY createdAt DESC",1))
            //points table
            assert mysql.get("SELECT activityInfo FROM points_manager.Points ORDER BY createdAt DESC",1) == 'epoints purchased from epoints.com'
            assert mysql.get("SELECT externalTransactionId FROM points_manager.Points ORDER BY createdAt DESC",1) == mysql.get("SELECT paymentId FROM points_manager.Payment ORDER BY createdAt DESC",1)
            assert mysql.get("SELECT status FROM points_manager.Points ORDER BY createdAt DESC",1) == 'CONFIRMED'
            assert mysql.get("SELECT userId FROM points_manager.Points ORDER BY createdAt DESC",1) == mysql.get("SELECT id FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1)
            assert mysql.get("SELECT delta FROM points_manager.Points ORDER BY createdAt DESC",1) == epointsToChange.replace(',','')
            assert mysql.get("SELECT onBehalfOfId FROM points_manager.Points ORDER BY createdAt DESC",1) == 'ePoints.com'
            assert mysql.get("SELECT tagId FROM points_manager.Points ORDER BY createdAt DESC",1) == '26883'
        mysql.close()
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
        waitFor{ epoints.purchaseEpointsTab.buyEpointsFormValueField.isDisplayed()  }
        amount = epoints.purchaseEpointsTab.buyEpointsFormValueField.value().replace('£','')
        vat = epoints.purchaseEpointsTab.buyEpointsFormVATField.value().replace('£','')
        fee = epoints.purchaseEpointsTab.buyEpointsFormFeeField.value().replace('£','')
        total = epoints.purchaseEpointsTab.buyEpointsFormTotalField.value().replace('£','')

        browser.withNewWindow( {epoints.purchaseEpointsTab.clickRedirectInfoLink() }, close:true){
            waitFor{ browser.currentUrl.contains('paypal')  }
            assert browser.currentUrl.contains('paypal')
            waitFor(15){ epoints.paypal.spendRequestValue.isDisplayed() }
            assert (epoints.paypal.spendRequestValue.text().contains(amount.replace('.',',')) ||  epoints.paypal.spendRequestValue.text().contains(amount))
            assert (epoints.paypal.vatRequestValue.text().contains(vat.replace('.',',')) || epoints.paypal.vatRequestValue.text().contains(vat))
            assert (epoints.paypal.feeRequestValue.text().contains(fee.replace('.',',')) || epoints.paypal.feeRequestValue.text().contains(fee))
            assert (epoints.paypal.totalRequestValue.text().contains(total.replace('.',',')) || epoints.paypal.totalRequestValue.text().contains(total))
        }
    }
    Then(~/^On paypal page he will see that all values are correct according to his request$/) { ->
        //done in first step in this test
    }

    // 1.12 //  --------------------------------- EPOINTS - take payment for buying epoints - screen 3 receipt(NBO-1218)
    // ------------------------------------------------------------------------------------------------- payment details
    Given(~/^User bought some epoints value$/) { ->
        waitFor{ epoints.purchaseEpointsTab.buyEpointsFormValueField.isDisplayed()  }
        amount = epoints.purchaseEpointsTab.buyEpointsFormValueField.value().replace('£','')
        vat = epoints.purchaseEpointsTab.buyEpointsFormVATField.value().replace('£','')
        fee = epoints.purchaseEpointsTab.buyEpointsFormFeeField.value().replace('£','')
        total = epoints.purchaseEpointsTab.buyEpointsFormTotalField.value().replace('£','')

        browser.withNewWindow( {epoints.purchaseEpointsTab.clickRedirectInfoLink() }, close:true) {
            waitFor { browser.currentUrl.contains('paypal') }
            assert browser.currentUrl.contains('paypal')

            if(epoints.paypal.payWithMyPaypalAccount.isDisplayed()){
                epoints.paypal.clickPayWithMyPaypalAccountButton()
            }
            epoints.paypal.enterEmail(envVar.paypalUser)
            epoints.paypal.enterPassword(envVar.paypalPassword)
            epoints.paypal.clickLoginButton()
            epoints.paypal.clickContinueButton()

            waitFor(10) { epoints.purchaseEpointsTab.redirectInfoBasic.isDisplayed() }
            assert epoints.purchaseEpointsTab.header.text().replace('\n','').replace(' ','').contains(envVar.returnSuccessfullPaymentInfo(epointsToChange.replace(',','')).replace('\n','').replace(' ',''))
            assert epoints.purchaseEpointsTab.paymentDetailsHeader.text() == envVar.paymentDetailsHeader
            assert epoints.purchaseEpointsTab.paymentDetailsLabelBasic(0).text() == envVar.paymentDetailsDateLabel
            assert epoints.purchaseEpointsTab.paymentDetailsLabelBasic(1).text() == envVar.paymentDetailsPaymentStatusLabel
            assert epoints.purchaseEpointsTab.paymentDetailsLabelBasic(2).text() == envVar.paymentDetailsPayerNameLabel
            assert epoints.purchaseEpointsTab.paymentDetailsLabelBasic(3).text() == envVar.paymentDetailsPayerEmailLabel
            assert epoints.purchaseEpointsTab.paymentDetailsLabelBasic(4).text() == envVar.paymentDetailsCurrencyLabel
            assert epoints.purchaseEpointsTab.paymentDetailsLabelBasic(5).text() == envVar.paymentDetailsValueLabel
            assert epoints.purchaseEpointsTab.paymentDetailsLabelBasic(6).text() == envVar.paymentDetailsFeeLabel
            assert epoints.purchaseEpointsTab.paymentDetailsLabelBasic(7).text() == envVar.paymentDetailsTaxLabel
            assert epoints.purchaseEpointsTab.paymentDetailsLabelBasic(8).text() == envVar.paymentDetailsTotalLabel

            assert epoints.purchaseEpointsTab.paymentDetailsValueBasic(1).text() == 'approved'
            assert epoints.purchaseEpointsTab.paymentDetailsValueBasic(2).text() == envVar.paypalUserName
            assert epoints.purchaseEpointsTab.paymentDetailsValueBasic(3).text() == envVar.paypalUser
            assert epoints.purchaseEpointsTab.paymentDetailsValueBasic(4).text() == 'GBP'
            assert epoints.purchaseEpointsTab.paymentDetailsValueBasic(5).text() == amount.replace(',','')
            assert epoints.purchaseEpointsTab.paymentDetailsValueBasic(6).text() == fee
            assert epoints.purchaseEpointsTab.paymentDetailsValueBasic(7).text() == vat
            assert epoints.purchaseEpointsTab.paymentDetailsValueBasic(8).text() == total.replace('','')
            // - 2-1 hours depends of time
            assert ((func.convertDateToEpochFormat(epoints.purchaseEpointsTab.paymentDetailsValueBasic(0).text(), 'YYYY-MM-dd') <= func.returnEpochOfCurrentDay() - 3400000000) || (func.convertDateToEpochFormat(epoints.purchaseEpointsTab.paymentDetailsValueBasic(0).text(), 'YYYY-MM-dd') >= func.returnEpochOfCurrentDay() - 7400000000) )
        }
    }
    When(~/^User look on page after redirect to epoints$/) { ->
        //done in first step in this test
    }
    Then(~/^He can see proper summary of his payment$/) { ->
        //done in first step in this test
    }