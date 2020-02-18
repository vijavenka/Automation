package stepdefs.EpointsStepDefs.spendSection
import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*
/**
 * Created by kbaranowski on 2014-11-07.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()
def epointsLink = envVar.epointsLink

    // 1.1 //  ------------------------------------------------------------------------------ Individual redemption page
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Browse rewards page is opened$/) { ->
        epointsPage.url = epointsLink
        to epointsPage
        epoints = page;  Thread.sleep(1000)
        epoints.clickSpendButton(); Thread.sleep(1000)
        waitFor{ epoints.spendPage.departmentCardBasic.isDisplayed() }
        random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
        while(epoints.spendPage.departmentCardBasic[random].hasClass('is-disabled')){
            random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
        }
        epoints.spendPage.clickChosenDepartmentCard(random)
        waitFor{ browser.title == envVar.browseRewardsPageTitle }
    }
    Given(~/^Browse rewards page is opened clear$/) { ->
        epointsPage.url = epointsLink
        to epointsPage
        epoints = page
        func.clearCookiesAndStorage()
        epoints.clickSpendButton(); Thread.sleep(1000)
        waitFor{ epoints.spendPage.departmentCardBasic.isDisplayed() }
        random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
        while(epoints.spendPage.departmentCardBasic[random].hasClass('is-disabled')){
            random = func.returnRandomValue(epoints.spendPage.departmentCardBasic.size())
        }
        epoints.spendPage.clickChosenDepartmentCard(random)
        waitFor{ browser.title == envVar.browseRewardsPageTitle }
    }
    String chosenRedemptionName
    String chosenRedemptionpointsValue
    When(~/^User choose some reward and click on it$/) { ->
        waitFor{ epoints.browseRewardsPage.redemptionCardBasic.isDisplayed() }
        random = func.returnRandomValue(epoints.browseRewardsPage.redemptionCardBasic.size())
        chosenRedemptionName = epoints.browseRewardsPage.redemptionCardNameBasic[random].text()
        chosenRedemptionpointsValue = epoints.browseRewardsPage.redemptionCardPointsValueBasic[random].text()
        epoints.browseRewardsPage.clickCardOfChosenRedemption(random)
    }
    Then(~/^Individual page of chosen redemption will be opened$/) { ->
        waitFor{ browser.title == envVar.returnIndividualRedemptionPageTitleText(chosenRedemptionName) }
        assert browser.title == envVar.returnIndividualRedemptionPageTitleText(chosenRedemptionName)
        waitFor{ epoints.indRedemptionPage.header.text() == chosenRedemptionName }
        assert epoints.indRedemptionPage.header.text() == chosenRedemptionName
    }
    Then(~/^It will contains picture, description and delivery information areas$/) { ->
        waitFor{ epoints.indRedemptionPage.descriptionSection.isDisplayed() }
        assert epoints.indRedemptionPage.descriptionSection.isDisplayed()
        assert epoints.indRedemptionPage.deliverySection.isDisplayed()
        assert epoints.indRedemptionPage.pictureSection.isDisplayed()
    }
    Then(~/^It will contains basket module with proper elements$/) { ->
        waitFor{ epoints.indRedemptionPage.basketModuleTitle.text().contains(envVar.basketModuleTitle) }
        assert epoints.indRedemptionPage.basketModuleTitle.text().contains(envVar.basketModuleTitle)
        assert epoints.indRedemptionPage.basketModulePrice.isDisplayed()
        assert epoints.indRedemptionPage.basketModuleQuantity.isDisplayed()
        assert epoints.indRedemptionPage.basketModuleBuyButton.isDisplayed()
        assert epoints.indRedemptionPage.basketModuleQuantityPlus.isDisplayed()
        assert epoints.indRedemptionPage.basketModuleQuantityMinus.isDisplayed()
        assert epoints.indRedemptionPage.basketModulePrice.text() == chosenRedemptionpointsValue
    }

    // 1.2 //  ------------------------------------------------------------------------------ Individual redemption page
    // ------------------------------------------------------------------------------------------ back to rewards button
    When(~/^User click 'back to rewards link'$/) { ->
        epoints.indRedemptionPage.clickBacktToRewardsLink()
    }

    // 1.3 //  ------------------------------------------------------------------------------ Individual redemption page
    // ------------------------------------------------------------------------ basket module, more less product buttons
    When(~/^User increase number of products in basket module$/) { ->
        epoints.indRedemptionPage.increaseProductsForSelection()
    }
    Then(~/^Product counter will be increased$/) { ->
        waitFor{ epoints.indRedemptionPage.basketModuleQuantity.attr('value') == '2' }
        assert epoints.indRedemptionPage.basketModuleQuantity.attr('value') == '2'
    }
    When(~/^User decrease number of products in basket module$/) { ->
        epoints.indRedemptionPage.decreaseProductsForSelection()
    }
    Then(~/^Product counter will be decreased$/) { ->
        waitFor{ epoints.indRedemptionPage.basketModuleQuantity.attr('value') == '1' }
        assert epoints.indRedemptionPage.basketModuleQuantity.attr('value') == '1'
    }

    // 1.4 //  ------------------------------------------------------------------------------ Individual redemption page
    // ------------------------------------------------------------------------------------------- add product to basket
    When(~/^User add product to the basket$/) { ->
        epoints.indRedemptionPage.clickSelectRewardButton()
    }
    Then(~/^Information about selected reward will be displayed$/) { ->
        waitFor{ epoints.basket.basketSmallPreview.text().contains(envVar.returnBasketPreviewQuantityInformation('1')) }
        assert epoints.basket.basketSmallPreview.text().contains(envVar.returnBasketPreviewQuantityInformation('1'))
    }
    Then(~/^Number of rewards on displayed information will increase$/) { ->
        waitFor{ epoints.basket.basketSmallPreview.text().contains(envVar.returnBasketPreviewQuantityInformation('2')) }
        assert epoints.basket.basketSmallPreview.text().contains(envVar.returnBasketPreviewQuantityInformation('2'))
    }
    When(~/^User Open basked preview$/) { ->
        epoints.basket.openBasketPreview()
    }
    Then(~/^Name and quantity will be correct according to added before products$/) { ->
        waitFor{ epoints.basket.basketSmallPreviewOption.text().contains(chosenRedemptionName) }
        waitFor{ epoints.basket.basketSmallPreviewOptionQuantity.text().contains(envVar.returnBasketPreviewContentQuantityInformation('2')) }
        assert epoints.basket.basketSmallPreviewOption.text().contains(chosenRedemptionName)
        assert epoints.basket.basketSmallPreviewOptionQuantity.text().contains(envVar.returnBasketPreviewContentQuantityInformation('2'))
    }

    // 1.5 //  ------------------------------------------------------------------------------ Individual redemption page
    // --------------------------------------------------------------- availability of direct basket link after addition
    Then(~/^Direct basket link will appears$/) { ->
        waitFor{ epoints.indRedemptionPage.basketModuleRewardsSelectedText.isDisplayed() }
        assert epoints.indRedemptionPage.basketModuleRewardsSelectedText.text().replace('\n','').replace(' ','') == envVar. indRedemptionSelectModuleRewardSelectedText.replace('\n','').replace(' ','')
    }
    Then(~/^It takes user to basket after click$/) { ->
        epoints.indRedemptionPage.clickModuleRewardsSelectedBasketLink()
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.chekoutRewardsURL }
    }