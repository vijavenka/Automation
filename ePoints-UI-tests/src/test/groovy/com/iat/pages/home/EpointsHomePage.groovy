package com.iat.pages.home

import com.iat.pages.AbstractPage
import com.iat.pages.home.modules.CarouselBannerModule
import com.iat.pages.home.modules.RetailerModule
import com.iat.pages.rewards.modules.RedemptionCardsBlockModule

class EpointsHomePage extends AbstractPage {

    static url = '/'
    static at = { waitFor { title.contains('epoints - loyalty rewards | epoints') } }

    static content = {
        //carousel
        carouselBanner(required: true) { module CarouselBannerModule }

        //merchants block
        //leads merchants block
        ourRetailersSectionBanner { $('.BlockWithHeader')[0] } //this is not very precise and depends on config
        retailerBlockTitle(required: true) { $(".BlockRetailerCardSmall-title").text().trim() }
        retailerBlock(required: true) { $('.BlockRetailerCardSmall')[0].module(RetailerModule) }
        retailerBlockSpecial(required: true) { $('.BlockRetailerCardSmall')[1].module(RetailerModule) }

        //redemption block
        rewardsSectionBanner { $('.BlockWithHeader')[1] } //this is not very precise and depends on config
        redemptionsBlock(required: true, wait: true) {
            $('.BlockWithSpecialOffers').module(RedemptionCardsBlockModule)
        }

        //internationalization home page elements
        newRegionsSignInEmailInputField(wait: true) { $('.HomePageJoin-buttonsRow', 0).find('#email') }
        newRegionsSignInPasswordInputField(wait: true) { $('.HomePageJoin-buttonsRow', 0).find('#password') }
        newRegionsSignInButton(wait: true) { $('.HomePageJoin-buttonsRow', 0).find('.btn-green') }
        newRegionsJoinLink(wait: true) { $('.HomePageJoin-buttonsRow', 0).find('.u-textUnderline') }

        newRegionsCurrecnySection { $('.HomeSection.HomeSection--currency') }
        newRegionCatalogueSection { $('.HomeSection.HomeSection--catalogoue') }
        newRegionSpendSection { $('.HomeSection.HomeSection--spend') }

        howDoesItWorkSectionBanner { $('.BlockWithHeader').last() } //this is not very precise and depends on config
        howDoesItWorkSection { $('.HowItWorks') }
        hdiwQuestions { howDoesItWorkSection.$('.ArticleWithIcon-title') }
        hdiwSteps { howDoesItWorkSection.$('.HowItWorks-numericMedias') }
        hditJoinSection { howDoesItWorkSection.$('.HowItWorks-join') }
    }

    def signInUserToEpoints(email, password) {
        headerModule.clickOnSignInButton()
        waitFor { headerModule.headerSignInButton.isDisplayed() }
        signInModule.signInUserToEpointsCom(email, password)
        waitFor(15) { headerModule.headerUserNameLabel.isDisplayed() }
    }

    def goToUserMyAccountPage() {
        headerModule.clickOnUserNameLabel()
        userDropDownMenuModule.clickOnMyEpointsOption()
    }

    def goToUserProfilePage(boolean menuOpened) {
        if (!menuOpened) {
            headerModule.clickOnUserNameLabel()
        }
        userDropDownMenuModule.clickOnProfileOption()
    }

    def goToUserActivityPage() {
        headerModule.clickOnUserNameLabel()
        userDropDownMenuModule.clickOnActivityOption()
    }

    def goToUserRewardsHistoryPage() {
        headerModule.clickOnUserNameLabel()
        userDropDownMenuModule.clickOnRewardsHistoryOption()
    }

    def goToUserEpointsPurchasePage(boolean menuOpened) {
        if (!menuOpened) {
            headerModule.clickOnUserNameLabel()
        }
        userDropDownMenuModule.clickOnPurchaseHistoryOption()
    }

    def goToEpointsRewardsPage() {
        headerModule.clickOnRewardsButton()
    }

    def goToEpointsPointsPage() {
        headerModule.clickOnPointsButton()
    }

    def goToForgotPasswordPage() {
        headerModule.clickOnSignInButton()
        signInModule.clickForgotPasswordLink()
    }

    def goToJoinPage() {
        headerModule.clickOnJoinButton()
    }

    def clickRedemptionOffersSeeMoreLink() {
        waitFor {
            redemptionsBlock.linkToSeeMore.isDisplayed()
        }
        redemptionsBlock.linkToSeeMore.click()
    }

    def clickRandomRedemptionCard(cardNumber) {
        waitFor { redemptionsBlock.redemptions[cardNumber].isDisplayed() }
        redemptionsBlock.redemptions[cardNumber].click()
    }

}