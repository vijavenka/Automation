package stepdefs.EpointsStepDefs.checkoutSection

import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-03-09.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

    // 1.1 //  -------------------------------------------------------------------------------- Checkout - complete page
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Complete checkout page is opened by logged user clear$/) { ->
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
        Thread.sleep(1000)
        epoints.basket.clickPlaceOrderButton()
        waitFor{ epoints.basket.stepIndicator.hasClass('current-step-4') }
        assert epoints.basket.selectedRewardsStep.text() == envVar.checkoutStep1
        assert epoints.basket.deliveryDetailsStep.text() == envVar.checkoutStep2
        assert epoints.basket.orderSummaryStep.text() == envVar.checkoutStep3
        assert epoints.basket.completeStep.text() == envVar.checkoutStep4
        assert epoints.basket.stepCircle.size() == 4
    }
    When(~/^User look on complete page$/) { ->
        waitFor{ epoints.basket.selectedRewardsHeader.text() == envVar.completeOrderHeader }
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.chekoutCompleteURL }
        assert epoints.basket.selectedRewardsHeader.text() == envVar.completeOrderHeader
        assert browser.currentUrl == envVar.epointsLink + envVar.chekoutCompleteURL
    }
    Then(~/^He can see order confirmation message$/) { ->
        waitFor{ epoints.basket.cThankYouText.isDisplayed() }
        assert epoints.basket.cThankYouText.text().replace("\n","").replace(' ','') == envVar.completeThankYouText.replace(' ','')
    }
    Then(~/^Complete page redirect buttons will be displayed$/) { ->
        waitFor{ epoints.basket.cFAQLink.isDisplayed() }
        waitFor{ epoints.basket.cMyAccountButton.isDisplayed() }
        waitFor{ epoints.basket.cGetEpointsButton.isDisplayed() }
        assert epoints.basket.cFAQLink.isDisplayed()
        assert epoints.basket.cMyAccountButton.isDisplayed()
        assert epoints.basket.cGetEpointsButton.isDisplayed()
    }

    // 1.2 //  -------------------------------------------------------------------------------- Checkout - complete page
    // -------------------------------------------------------------------------------------------------------- faq link
    When(~/^User use faq link on complete page$/) { ->
        epoints.basket.clickFaqLink()
    }
    Then(~/^He will be redirected to epoints support page$/) { ->
        waitFor{ browser.currentUrl == envVar.supportPageURL }
        assert browser.currentUrl == envVar.supportPageURL
    }
    // 1.3 //  -------------------------------------------------------------------------------- Checkout - complete page
    // ----------------------------------------------------------------------------------------------- my account button
    When(~/^User click my account button on complete page$/) { ->
        epoints.basket.clickMyAccountButton()
    }
    Then(~/^He will be redirected to user account page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.myAccountURL }
    }

    // 1.4 //  -------------------------------------------------------------------------------- Checkout - complete page
    // ---------------------------------------------------------------------------------------------- get epoints button
    When(~/^User click get epoints button on complete page$/) { ->
        epoints.basket.clickGetEpointsButton()
    }
    Then(~/^He will be redirected to a-z page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.getPageURL }
    }