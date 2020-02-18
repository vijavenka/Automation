package com.iat.pages.rewards.modules

import com.iat.stepdefs.utils.Functions
import geb.Module
import geb.navigator.Navigator

class RedemptionCardsBlockModule extends Module {

    def func = new Functions()

    RedemptionCardModule redemptionCard

    static content = {

        redemptionBuyNowModule {module RedemptionBuyNowModule}

        title(required: false, wait: 2) {
            def title = $("title")
            title.isDisplayed() ? title.text().trim() : ""
        }
        linkToSeeMore(required: false, wait: 2) { $(".seeMore a") }
        redemptions(required: false) {
            def smallCards = $(".BlockWithSpecialOffers-smallCards")
            smallCards = smallCards.isDisplayed() ? smallCards.$(".ProductCard") : $(".ProductCard")
            smallCards.moduleList(RedemptionCardModule)
        }
        largerCards(required: false) {
            $(".BlockWithSpecialOffers-largeCard .ProductCard").moduleList(RedemptionCardModule)
        }
    }

    def chooseRandomRedemption() {
        waitFor { size() > 0 }
        int index = func.returnRandomValue(size() - 1)
        redemptionCard = redemptions[index]
    }

    RedemptionCardModule getRandomCard() {
        chooseRandomRedemption()
        return redemptionCard
    }

    RedemptionCardModule addToBasketAnyRedemption() {
        chooseRandomRedemption()
        redemptionCard.click()
//        redemptionCard.clickAddToBasketButton()
        redemptionBuyNowModule.clickAddToBasketButton()
        return redemptionCard
    }

    @Override
    int size() {
        return redemptions.size() + largerCards.size()
    }

    List<String> getCategoryNames() {
        return redemptions.category*.text() + largerCards.category*.text()
    }

    List<String> getCategoryUrls() {
        return redemptions.category*.$("a")*.@href + largerCards.category*.$("a")*.@href
    }

    List<String> getTitles() {
        return redemptions.title + largerCards.title
    }

    List<String> getCosts() {
        return redemptions.cost + largerCards.cost
    }

    List<Integer> getCostsInt() {
        return redemptions.costInt + largerCards.costInt
    }

    List<Navigator> getImages() {
        return redemptions.image + largerCards.image
    }

    List<Navigator> getAddToBasketButtons() {
        return redemptions.toBasketButton + largerCards.toBasketButton
    }

    List<String> getUrls() {
        return redemptions.link + largerCards.link
    }
}
