package com.iat.pages.rewards

import com.iat.pages.AbstractPage
import com.iat.pages.checkout.EpointsBasketSelectedRewardsPage
import com.iat.pages.rewards.modules.BreadcrumbModule
import com.iat.pages.rewards.modules.BuyEpointsModule
import com.iat.pages.rewards.modules.RedemptionCardsBlockModule

class EpointsRedemptionPage extends AbstractPage {

    static at = {
        waitFor {
            title.contains("Spend your epoints on")
            basketWidget.isDisplayed()
        }
    }

    static content = {
        breadcrumb { module BreadcrumbModule }
        buyEpoints { module BuyEpointsModule }

        header { $('h1') }
        descriptionSection { $('.productContent-description') }
        deliverySection { $('.productContent-delivery') }
        pictureSection { $('.productImagesWidget') }

        basketWidget { $(".productBasketWidget-wrapper") }
        basketWidgetTitle { basketWidget.find('.productBasketWidget-title') }
        basketWidgetPrice { basketWidget.find('.productBasketWidget-prices-wrapper-currentPrice') }
        quantityBox { basketWidget.$("input") }
        quantityBoxValue { basketWidget.$("input").@value.trim().toInteger() }
        increaseButton { basketWidget.$("button[ng-click*='increase'") }
        decreaseButton { basketWidget.$("button[ng-click*='decrease'") }
        selectRewardButton { basketWidget.$(".productBasketWidget-buy button") }

        imageSelectedProductText(required: false) {
            String msg = $(".productImagesWidget-wrapper-banner").text()
            msg == null ? "" : msg.trim()
        }

        viewSelectedRewards(required: false) { basketWidget.$(".productBasketWidget-rewardSelected") }
        viewSelectedRewardsLink(required: false, to: EpointsBasketSelectedRewardsPage) { viewSelectedRewards.$("a") }
        viewSelectedRewardsText {
            viewSelectedRewards.text() == null ? "" : viewSelectedRewards.text().trim().replaceAll("[\n\r]", " ")
        }

        //advance image widget
        primaryImageSource(required: false) { $(".productImagesWidget-image img").@src }
        primaryImageZoom(required: false) { $(".productImagesWidget-image-zoom") }
        zoomedImageCloseButton(required: false) { $(".productImagesModal-close") }
        thumbnails(required: false) { $(".productImagesWidget-thumbnails a") }
        imageControlLeft(required: false) { $(".productImagesWidget-image-arrow-left") }
        imageControlRight(required: false) { $(".productImagesWidget-image-arrow-right") }

        //buy epoints module
        buyEpointsModuleText(required: true) { $(".buyMorePoints-pointsLabel") }

        buyEpointsWidget(required: true) { $(".input-group.pointsInputGroup") }
        buyEpointsWidgetDDL(required: true) { $(".selectpicker") }
        buyEpointsWidgetDDLOption(required: true) { buyEpointsWidgetDDL.find('option') }
        buyEpointsWidgetBuyButton(required: true) { $(".buyMorePoints-pointsInputGroupButton") }

        //related rewards section
        relatedRewards(required: true) { $(".productPage-relatedRewards") }
        relatedRewardsTitle(required: true) { $(".RelatedRewards-title") }
        redemptionList { module RedemptionCardsBlockModule }
    }

    void addToBasket() {
        addToBasket(1)
    }

    void changeProductsQuantity(int howMany) {
        def button
        waitFor { quantityBox.isDisplayed() }
        if (howMany < quantityBoxValue) button = decreaseButton
        else if (howMany > quantityBoxValue) button = increaseButton

        int quantityValTmp
        while (howMany != quantityBoxValue) {
            quantityValTmp = quantityBoxValue
            button.click()
            waitFor { quantityBoxValue != quantityValTmp }
        }
    }

    void addToBasket(int howMany) {
        changeProductsQuantity(howMany)
        clickSelectReward()
    }

    void clickSelectReward() {
        int valueFromBasketRewardsCounter = headerModule.headerBasketCounter
        selectRewardButton.click()
        waitFor { headerModule.headerBasketCounter > valueFromBasketRewardsCounter }
    }

}