package stepdefs.EpointsStepDefs.checkoutSection

import geb.Browser
import mysqlConnection.jdbc
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-03-03.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

    // 1.1 //  --------------------------------------------------------------------------- Checkout - order summary page
    // ---------------------------------------------------------------------------------------------------- page content
    String chosenRedemptionName
    String chosenRedemptionCost
    Given(~/^Order summary checkout page is opened with one product in it by logged user clear$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        epoints.clickSignInButton()
        waitFor{ epoints.signModule.authenticationPanel.isDisplayed() }
        epoints.signModule.enterLoginData(envVar.testUserEmail, envVar.testUserPassword)
        epoints.signModule.clickSignInButton()
        epoints.clickSpendButton()

        waitFor{ epoints.spendPage.departmentCardBasic.isDisplayed() }
        random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
        while(epoints.spendPage.departmentCardBasic[random].hasClass('is-disabled')){
            random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
        }
        epoints.spendPage.clickChosenDepartmentCard(random)
        //TODO Remove this  after fix
        epoints.browseRewardsPage.addToBasketChosenRedemption(random)
        //clear basket if needed
        if(!epoints.basket.basketSmallViewSelectedRewardsButton.isDisplayed()){
            epoints.basket.openBasketPreview()
        }
        Thread.sleep(1000)
        if(epoints.basket.basketSmallPreviewOption.size()>0){
            epoints.basket.clickViewSelectedRewardButton()
            epoints.basket.clickRemoveAllItemsButton()
            epoints.basket.clickRemoveAllItemsYesButton()
            epoints.basket.clickBackToRewardsButton()
        }
        //clear basket if needed
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.size() == 40 }
        random = func.returnRandomValue(epoints.browseRewardsPage.redemptionCardBasic.size())
        chosenRedemptionName = epoints.browseRewardsPage.redemptionCardNameBasic[random].text()
        chosenRedemptionCost = epoints.browseRewardsPage.redemptionCardPointsValueBasic[random].text()
        epoints.browseRewardsPage.addToBasketChosenRedemption(random)
        Thread.sleep(1000)
        //TODO Uncomment this after fix
        if(!epoints.basket.basketSmallViewSelectedRewardsButton.isDisplayed()){
            epoints.basket.openBasketPreview()
        }
        epoints.basket.clickViewSelectedRewardButton()
        Thread.sleep(1000)
        epoints.basket.clickOrderRewardButton()
        Thread.sleep(1000)
        epoints.basket.clickNextButtonOnDeliveryDetailsPage()
        waitFor{ epoints.basket.stepIndicator.hasClass('current-step-3') }
        assert epoints.basket.selectedRewardsStep.text() == envVar.checkoutStep1
        assert epoints.basket.deliveryDetailsStep.text() == envVar.checkoutStep2
        assert epoints.basket.orderSummaryStep.text() == envVar.checkoutStep3
        assert epoints.basket.completeStep.text() == envVar.checkoutStep4
        assert epoints.basket.stepCircle.size() == 4
    }
    When(~/^User look on order summary page$/) { ->
        waitFor{ epoints.basket.orderSummaryHeader.text() == envVar.orderSummaryHeader }
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.chekoutSummaryURL }
        assert epoints.basket.orderSummaryHeader.text() == envVar.orderSummaryHeader
        assert browser.currentUrl == envVar.epointsLink + envVar.chekoutSummaryURL
    }
    Then(~/^He will see that order summary page contains correct product$/) { ->
        waitFor{ epoints.basket.osRedemptionNameBasic.isDisplayed() }
        waitFor{ epoints.basket.osRedemptionPointsValue.isDisplayed() }
        assert epoints.basket.osRedemptionNameBasic.text().contains(chosenRedemptionName)
        assert epoints.basket.osRedemptionPointsValue.text() == chosenRedemptionCost
        assert epoints.basket.productsSectionEditButton.isDisplayed()
        assert epoints.basket.osRedemptionImageBasic.isDisplayed()

    }
    Then(~/^Total epoints cost is properly calculated$/) { ->
        waitFor{ epoints.basket.osTotalEpointsNeeded.isDisplayed() }
        def calculatedRedmptionsCost = 0
        for(int i=0; i<epoints.basket.osRedemptionPointsValue.size(); i++){
            calculatedRedmptionsCost = calculatedRedmptionsCost + Integer.parseInt(epoints.basket.osRedemptionPointsValue[i].text().replace(',',''))
        }
        assert calculatedRedmptionsCost == Integer.parseInt(epoints.basket.osTotalEpointsNeeded.text().replace(',',''))
    }
    Then(~/^User will see that order summary page contains correct address$/) { ->
        waitFor{ epoints.basket.deliverySectionEditButon.isDisplayed() }
        waitFor{ epoints.basket.osDeliveryDetailsBasic.isDisplayed() }
        assert epoints.basket.deliverySectionEditButon.isDisplayed()
        def mysql = new jdbc('points')
            assert epoints.basket.osDeliveryDetailsBasic[0].text().contains(mysql.get("SELECT firstName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.osDeliveryDetailsBasic[0].text().contains(mysql.get("SELECT lastName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.osDeliveryDetailsBasic[1].text().contains(mysql.get("SELECT houseNumberOrName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.osDeliveryDetailsBasic[1].text().contains(mysql.get("SELECT street FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.osDeliveryDetailsBasic[2].text().contains(mysql.get("SELECT townOrCity FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.osDeliveryDetailsBasic[3].text().contains(mysql.get("SELECT county FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.osDeliveryDetailsBasic[4].text().contains(mysql.get("SELECT country FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert epoints.basket.osDeliveryDetailsBasic[5].text().contains(mysql.get("SELECT postcode FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
        mysql.close()

    }
    Then(~/^Order summary page contains navigation buttons$/) { ->
        waitFor{ epoints.basket.osBackButton.isDisplayed() }
        waitFor{ epoints.basket.osPlaceOrderButton.isDisplayed() }
        assert epoints.basket.osBackButton.isDisplayed()
        assert epoints.basket.osPlaceOrderButton.isDisplayed()
    }

    // 1.2 //  --------------------------------------------------------------------------- Checkout - order summary page
    // --------------------------------------------------------------------------------------------- product edit button
    When(~/^User click edit products button$/) { ->
        epoints.basket.clickProductsEditButton()
    }
    Then(~/^He will be redirected to selected rewards step$/) { ->
        epoints = page
        waitFor{ epoints.basket.selectedRewardsHeader.text() == envVar.selectedRewardsHeader }
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.chekoutRewardsURL }
        assert epoints.basket.selectedRewardsHeader.text() == envVar.selectedRewardsHeader
        assert browser.currentUrl == envVar.epointsLink + envVar.chekoutRewardsURL
    }

    // 1.3 //  --------------------------------------------------------------------------- Checkout - order summary page
    // --------------------------------------------------------------------------------------------- address edit button
    When(~/^User click edit delivery address button$/) { ->
        epoints.basket.clickDeliveryAddressEditButton()
    }

    // 1.4 //  --------------------------------------------------------------------------- Checkout - order summary page
    // ----------------------------------------------------------------------------------------------------- back button
    When(~/^User click back button on order summary page$/) { ->
        epoints = page
        epoints.basket.clickBackButtonOnOrderSummaryPage()
    }

    // 1.5 //  --------------------------------------------------------------------------- Checkout - order summary page
    // ---------------------------------------------------------------------------------------------- place order button
    When(~/^User click place order button on order summary page$/) { ->
        epoints.basket.clickPlaceOrderButton()
    }
    Then(~/^He will be redirected to confirmation step$/) { ->
        waitFor{ epoints.basket.selectedRewardsHeader.text() == envVar.completeOrderHeader }
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.chekoutCompleteURL }
        assert epoints.basket.selectedRewardsHeader.text() == envVar.completeOrderHeader
        assert browser.currentUrl == envVar.epointsLink + envVar.chekoutCompleteURL
    }
    Then(~/^Order will be correctly placed$/) { ->
        def mysql = new jdbc('points')
            // RedemptionOrder elements
            assert mysql.get("SELECT name FROM points_manager.RedemptionOrder ORDER BY createdAt DESC",1).contains(mysql.get("SELECT firstName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert mysql.get("SELECT name FROM points_manager.RedemptionOrder ORDER BY createdAt DESC",1).contains(mysql.get("SELECT lastName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert mysql.get("SELECT address1 FROM points_manager.RedemptionOrder ORDER BY createdAt DESC",1).contains(mysql.get("SELECT houseNumberOrName FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert mysql.get("SELECT address2 FROM points_manager.RedemptionOrder ORDER BY createdAt DESC",1).contains(mysql.get("SELECT street FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert mysql.get("SELECT city FROM points_manager.RedemptionOrder ORDER BY createdAt DESC",1).contains(mysql.get("SELECT townOrCity FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert mysql.get("SELECT county FROM points_manager.RedemptionOrder ORDER BY createdAt DESC",1).contains(mysql.get("SELECT county FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert mysql.get("SELECT country FROM points_manager.RedemptionOrder ORDER BY createdAt DESC",1).contains(mysql.get("SELECT country FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert mysql.get("SELECT postcode FROM points_manager.RedemptionOrder ORDER BY createdAt DESC",1).contains(mysql.get("SELECT postcode FROM points_manager.User WHERE email = '"+envVar.testUserEmail+"'",1))
            assert mysql.get("SELECT zone FROM points_manager.RedemptionOrder ORDER BY createdAt DESC",1) == 'UK' //TODO for now always will be UK
            // Points table
            assert mysql.get("SELECT activityInfo FROM points_manager.Points ORDER BY createdAt DESC",1).contains(chosenRedemptionName)
            assert mysql.get("SELECT delta FROM points_manager.Points ORDER BY createdAt DESC",1) == chosenRedemptionCost.replace(',','')
            assert mysql.get("SELECT status FROM points_manager.Points ORDER BY createdAt DESC",1) == 'REDEEMED'
            assert mysql.get("SELECT userId FROM points_manager.Points ORDER BY createdAt DESC",1) ==  mysql.get("SELECT id FROM points_manager.User ORDER BY WHERE email = '"+envVar.testUserEmail+"'",1)
            assert mysql.get("SELECT status FROM points_manager.Points ORDER BY createdAt DESC",1) ==  mysql.get("SELECT confirmed FROM points_manager.User ORDER BY WHERE email = '"+envVar.testUserEmail+"'",1)
        mysql.close()
    }