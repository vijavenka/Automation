package modules.Epoints_modules.spendSection

import geb.Module

/**
 * Created by kbaranowski on 2014-11-07.
 */
class individualRedemptionPageModule extends Module{
    static content = {
        backToRewardsLink{ $('.redemptionHeader-backLink') }
        header{ $('.redemptionHeader-title') }
        descriptionSection{ $('.redemptionContent-details') }
        deliverySection{ $('.redemptionContent-delivery') }
        pictureSection{ $('.redemptionContent-imageWrapper') }

        basketModule{ $('.productBasketWidget') }
        basketModuleTitle{ basketModule.find('.productBasketWidget-title') }
        basketModulePrice{ basketModule.find('.productBasketWidget-price') }
        basketModuleQuantity{ basketModule.find('.productBasketWidget-quantity-valueInput') }
        basketModuleBuyButton{ basketModule.find('.productBasketWidget-addToBasket') }
        basketModuleQuantityPlus{ basketModule.find('.productBasketWidget-quantity-incrase') }
        basketModuleQuantityMinus{ basketModule.find('.productBasketWidget-quantity-decrase') }
        basketModuleRewardsSelectedText{ $('.productBasketWidget-rewardSelected') }
        basketModuleRewardsSelectedBasketLink{ basketModuleRewardsSelectedText.find('a') }
    }
        def clickBacktToRewardsLink(){ waitFor{ backToRewardsLink.isDisplayed() }; backToRewardsLink.click() }
        def increaseProductsForSelection(){ waitFor{ basketModuleQuantityPlus.isDisplayed() }; basketModuleQuantityPlus.click() }
        def decreaseProductsForSelection(){ waitFor{ basketModuleQuantityMinus.isDisplayed() }; basketModuleQuantityMinus.click() }
        def clickSelectRewardButton(){ waitFor{ basketModuleBuyButton.isDisplayed() }; basketModuleBuyButton.click() }
        def clickModuleRewardsSelectedBasketLink(){ basketModuleRewardsSelectedBasketLink.isDisplayed(); basketModuleRewardsSelectedBasketLink.click() }
}