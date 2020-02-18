package stepdefs.EpointsStepDefs.checkoutSection

import cucumber.api.java.Before
import geb.Browser
import mysqlConnection.jdbc
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-03-02.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

    // 1.1 //  ------------------------------------------------------------------------ Checkout - selected rewards page
    // ---------------------------------------------------------------------------------------------------- page content
    @Before('@setHighEpointsValue')
    public void setHighEpointsValue(){
        def envVar = new envVariables()
        def mysql = new jdbc('points')
        mysql.update("UPDATE points_manager.User SET confirmed = '1000000' WHERE email = '"+envVar.testUserEmail+"'")
        mysql.close()
    }
    String chosenRedemptionName
    String chosenRedemptionCost
    Given(~/^Selected rewards checkout page is opened with one product in it clear$/) { ->
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
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
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.size() > 0 }
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
        waitFor{ epoints.basket.stepIndicator.hasClass('current-step-1') }
        assert epoints.basket.selectedRewardsStep.text() == envVar.checkoutStep1
        assert epoints.basket.deliveryDetailsStep.text() == envVar.checkoutStep2
        assert epoints.basket.orderSummaryStep.text() == envVar.checkoutStep3
        assert epoints.basket.completeStep.text() == envVar.checkoutStep4
        assert epoints.basket.stepCircle.size() == 4
    }
    When(~/^User look on selected rewards page$/) { ->
        waitFor{ epoints.basket.selectedRewardsHeader.isDisplayed() }
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.chekoutRewardsURL }
        assert epoints.basket.selectedRewardsHeader.text() == envVar.selectedRewardsHeader
        assert browser.currentUrl == envVar.epointsLink + envVar.chekoutRewardsURL
    }
    Then(~/^He can see that it contains all item card elements$/) { ->
        waitFor{ epoints.basket.redemptionImageBasic.isDisplayed() }
        assert epoints.basket.redemptionNameBasic.text() == chosenRedemptionName
        assert epoints.basket.redemptionImageBasic.isDisplayed()
        assert epoints.basket.redemptionRemoveButtonBasic.isDisplayed()
        assert epoints.basket.redemptionNumberBasic.value() == '1'
        assert epoints.basket.redemptionQuantityMinus.isDisplayed()
        assert epoints.basket.redemptionQuantityPlus.isDisplayed()
        assert epoints.basket.redemptionPointsValue.text() == chosenRedemptionCost
    }
    Then(~/^Summary row is properly displayed$/) { ->
        waitFor{ epoints.basket.removeAllItemsButton.isDisplayed() }
        assert epoints.basket.removeAllItemsButton.isDisplayed()
        assert epoints.basket.totalEpointsNeededText.text().replace("\n","") == envVar.returnTotalEpointsNeededInfo(chosenRedemptionCost)
    }
    Then(~/^Selected rewards page navigation buttons are properly displayed$/) { ->
        waitFor{ epoints.basket.backToRewardsButton.isDisplayed() }
        assert epoints.basket.backToRewardsButton.isDisplayed()
        assert epoints.basket.orderRewardButton.isDisplayed()
    }

    // 1.2 //  ------------------------------------------------------------------------ Checkout - selected rewards page
    // --------------------------------------------------------------------------------- working of order rewards button
    Given(~/^Selected rewards checkout page is opened with one product in it by logged user clear$/) { ->
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
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.size() > 0 }
        random = func.returnRandomValue(epoints.browseRewardsPage.redemptionCardBasic.size())
        chosenRedemptionName = epoints.browseRewardsPage.redemptionCardNameBasic[random].text()
        chosenRedemptionCost = epoints.browseRewardsPage.redemptionCardPointsValueBasic[random].text()
        epoints.browseRewardsPage.addToBasketChosenRedemption(random)
        Thread.sleep(6000)
        //TODO Uncomment this after fix
        if(!epoints.basket.basketSmallViewSelectedRewardsButton.isDisplayed()){
            epoints.basket.openBasketPreview()
        }
        epoints.basket.clickViewSelectedRewardButton()
        waitFor{ epoints.basket.stepIndicator.hasClass('current-step-1') }
        assert epoints.basket.selectedRewardsStep.text() == envVar.checkoutStep1
        assert epoints.basket.deliveryDetailsStep.text() == envVar.checkoutStep2
        assert epoints.basket.orderSummaryStep.text() == envVar.checkoutStep3
        assert epoints.basket.completeStep.text() == envVar.checkoutStep4
        assert epoints.basket.stepCircle.size() == 4
    }
    When(~/^User click order rewards Button$/) { ->
        epoints.basket.clickOrderRewardButton()
    }
    Then(~/^He will be redirected to delivery details step$/) { ->
        epoints = page
        waitFor{ epoints.basket.deliveryDetailsHeader.text() == envVar.deliveryDetailsHeader }
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.chekoutDeliveryURL }
        assert epoints.basket.deliveryDetailsHeader.text() == envVar.deliveryDetailsHeader
        assert browser.currentUrl == envVar.epointsLink + envVar.chekoutDeliveryURL
    }

    // 1.3 //  ------------------------------------------------------------------------ Checkout - selected rewards page
    // ------------------------------------------------------------------------------- working of back to rewards button
    When(~/^User click back to rewards button$/) { ->
        epoints.basket.clickBackToRewardsButton()
    }

    // 1.4 //  ------------------------------------------------------------------------ Checkout - selected rewards page
    // ---------------------------------------------------------------------------------------- increase/decrease button
    When(~/^User click increase quantity button$/) { ->
        epoints.basket.clickIncreaseButton()
    }
    Then(~/^Product number will be increased$/) { ->
        waitFor{ epoints.basket.redemptionNumberBasic.value() == '2' }
        assert epoints.basket.redemptionNumberBasic.value() == '2'
    }
    Then(~/^Total cost will be recalculated$/) { ->
        assert  Integer.parseInt(epoints.basket.redemptionNumberBasic.value()) * Integer.parseInt(epoints.basket.redemptionPointsValue.text().replace(',','')) == Integer.parseInt(epoints.basket.totalEpointsNeededValue.text().replace(',',''))
    }
    When(~/^User click decreased quantity button$/) { ->
        epoints.basket.clickDecreaseButton()
    }
    Then(~/^Product number will be decreased$/) { ->
        waitFor{ epoints.basket.redemptionNumberBasic.value() == '1' }
        assert epoints.basket.redemptionNumberBasic.value() == '1'
    }

    // 1.5 //  ------------------------------------------------------------------------ Checkout - selected rewards page
    // ------------------------------------------------------------------------------------------- delete single product
    String chosenRedemptionName2
    String chosenRedemptionCost2
    Given(~/^Selected rewards checkout page is opened with two product in it clear$/) { ->
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
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.size() > 0 }
        random = func.returnRandomValue(epoints.browseRewardsPage.redemptionCardBasic.size())
        chosenRedemptionName = epoints.browseRewardsPage.redemptionCardNameBasic[random].text()
        chosenRedemptionCost = epoints.browseRewardsPage.redemptionCardPointsValueBasic[random].text()
        epoints.browseRewardsPage.addToBasketChosenRedemption(random)
        random = func.returnRandomValue(epoints.browseRewardsPage.redemptionCardBasic.size())
        chosenRedemptionName2 = epoints.browseRewardsPage.redemptionCardNameBasic[random].text()
        chosenRedemptionCost2 = epoints.browseRewardsPage.redemptionCardPointsValueBasic[random].text()
        epoints.browseRewardsPage.addToBasketChosenRedemption(random)
        Thread.sleep(1000)
        //TODO Uncomment this after fix
        if(!epoints.basket.basketSmallViewSelectedRewardsButton.isDisplayed()){
            epoints.basket.openBasketPreview()
        }
        epoints.basket.clickViewSelectedRewardButton()
        waitFor{ epoints.basket.stepIndicator.hasClass('current-step-1') }
        assert epoints.basket.selectedRewardsStep.text() == envVar.checkoutStep1
        assert epoints.basket.deliveryDetailsStep.text() == envVar.checkoutStep2
        assert epoints.basket.orderSummaryStep.text() == envVar.checkoutStep3
        assert epoints.basket.completeStep.text() == envVar.checkoutStep4
        assert epoints.basket.stepCircle.size() == 4
    }
    When(~/^User click remove button of chosen product$/) { ->
        assert epoints.basket.basicBasketRedemption.size() == 2
        epoints.basket.clickRemoveButtonOfChosenProduct(0)
    }
    When(~/^Confirm deletion$/) { ->
        epoints.basket.clickRemoveItemYesButton()
    }
    Then(~/^Chosen product will be deleted from basket$/) { ->
        waitFor{ epoints.basket.basicBasketRedemption.size() == 1 }
        assert epoints.basket.basicBasketRedemption.size() == 1
    }

    // 1.6 //  ------------------------------------------------------------------------ Checkout - selected rewards page
    // ------------------------------------------------------------------------------------------------- delete all product
    When(~/^User click remove all products$/) { ->
        epoints.basket.clickRemoveAllItemsButton()
    }
    When(~/^Confirm deletion of all product from basket$/) { ->
        epoints.basket.clickRemoveAllItemsYesButton()
    }
    Then(~/^All products will be removed from basket$/) { ->
        waitFor{ epoints.basket.basicBasketRedemption.size() == 0 }
        waitFor{ epoints.basket.noRewardsInBasketInformation.isDisplayed() }
        assert epoints.basket.basicBasketRedemption.size() == 0
        assert epoints.basket.noRewardsInBasketInformation.text() == envVar.noProductInBasketInformation
    }

    // 1.7 //  ------------------------------------------------------------------------ Checkout - selected rewards page
    // ----------------------------------------------------------------------------------------- cancel deleting product
    Then(~/^Delete popup will be displayed$/) { ->
        waitFor{ epoints.basket.redemptionRemoveAllInfo.isDisplayed() }
        assert epoints.basket.redemptionRemoveAllInfo.text() == envVar.removeRemptonPopupQuestion
        assert epoints.basket.redemptionRemoveAllCancelButton.isDisplayed()
        assert epoints.basket.redemptionRemoveAllDeleteButton.isDisplayed()
    }
    When(~/^User refuse of deleting product from basket$/) { ->
        epoints.basket.clickRemoveAllItemsNoButton()
    }
    Then(~/^All product will stay in basket$/) { ->
        assert epoints.basket.basicBasketRedemption.size() == 2
    }

    // 1.8 //  ------------------------------------------------------------------------ Checkout - selected rewards page
    // ------------------------------------------------------------------------------------------ low points number info
    @Before('@setLowEpointsValue')
    public void setLowEpointsValue(){
        def envVar = new envVariables()
        def mysql = new jdbc('points')
            mysql.update("UPDATE points_manager.User SET confirmed = '1' WHERE email = '"+envVar.testUserEmail+"'")
        mysql.close()
    }
    Then(~/^He will see that his points number is to low to redeem selected item$/) { ->
        waitFor{ epoints.basket.notEnoughPointsAlert.isDisplayed() }
        assert epoints.basket.notEnoughPointsAlert.text().replace(',','').replace("\n","") == envVar.returnNotEnoughPointsAlert(Integer.parseInt(epoints.basket.redemptionPointsValue.text().replace(',',''))-1)
    }