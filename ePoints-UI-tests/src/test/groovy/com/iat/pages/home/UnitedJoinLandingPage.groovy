package com.iat.pages.home

import com.iat.pages.home.modules.CookiePanelModule
import com.iat.pages.home.modules.FooterModule
import com.iat.pages.home.unitedModules.UnitedLandingJoinSectionModule
import com.iat.pages.rewards.modules.RedemptionCardsBlockModule
import geb.Page

class UnitedJoinLandingPage extends Page {

    static url = '/united/join'
    static at = { waitFor { url.contains('/united/join') } }

    static content = {

        cookiePanelModule { module CookiePanelModule }
        footerModule { module FooterModule }

        epointsLogo(required: true, wait: true) { $('.MainHeader-logo') }
        signInButton(required: true, wait: true) { $('.MainNav-pill.MainNav--home') }
        joinButton(required: true, wait: true) { $('.MainNav-pill.MainNav--get') }
        joinSectionTop(required: true) { $('.BusinessJoinForm', 0).module(UnitedLandingJoinSectionModule) }

        descriptionSectionBlockWithImages(wait: true) { $('.HomeSection.HomeSection--whiteBanner') }
        descriptionSectionBlockImage(wait: true) { descriptionSectionBlockWithImages.find('img') }
        descriptionSectionBlockDescription(wait: true) {
            descriptionSectionBlockWithImages.find('.HomeSection-content-block-text')
        }
        descriptionSectionFaq(wait: true) { $('.HomeSection--earnPoints') }

        promotedBrandSectionDescription(required: true) { $('.BlockWithText', 0) }
        promotedBrandsSectionTitle(required: true, wait: true) { $('.PopularBrands h3') }
        promotedBrandImage(required: true, wait: true) { $('.PopularBrands-img') }

        redemptionsBlockDescription(required: true) { $('.BlockWithText', 1) }
        redemptionsBlock(required: true, wait: true) {
            $(".BlockWithSpecialOffers").module(RedemptionCardsBlockModule)
        }

        joinSectionBottom(required: true) { $('').module(UnitedLandingJoinSectionModule) }
    }

    def clickSignInButton() {
        waitFor { signInButton.isDisplayed(); signInButton.click() }
    }
}