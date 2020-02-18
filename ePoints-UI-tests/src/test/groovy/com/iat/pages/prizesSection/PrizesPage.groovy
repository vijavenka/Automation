//package com.iat.pages.prizesSection
//
//import com.iat.pages.AbstractPage
//
//class PrizesPage extends AbstractPage {
//
//    static url = '/prizes '
//    static at = {
//        waitFor {
//            browser.currentUrl.contains("/prizes")
//        }
//    }
//
//    static content = {
//        //no spins view
//        //first section
//        descriptionSectionTitle(required: false) { $('.HomeSection--banner').$('.HomeSection-title-strong') }
//        shopToClaimSpinButton(required: false) { $('.HomeSection--banner').$('.HomeSection-greenButton') }
//        descriptionSectionMainInfo(required: false) { $('.HomeSection--whiteBanner').$('.PrizesSection-contetnt') }
//        descriptionSectionAdditionalInfo(required: false) { $('.HomeSection--earnPoints') }
//        signInEpointsInfo(required: false) { $('.PrizesPage-signIn').$('span', 0) }
//        signInEpointsLink(required: false) { $('.PrizesPage-signIn--click').$('span') }
//
//        numberOfSpinsLeft(required: false, wait: true) { $("spin-counter") }
//        roulette(required: false) { $('.GameRoulette-iframe') }
//
//        //second section
//        epointsRouleteWheelSecondSection(required: false) { $('.HomeSection--business') }
//        rouletteWheelHeader(required: false) { epointsRouleteWheelSecondSection.$('.HomeSection-logo') }
//        rouletteWheelTitle(required: false) { epointsRouleteWheelSecondSection.$('.HomeSection-title') }
//        rouletteWheelDescription(required: false) { epointsRouleteWheelSecondSection.$('.HomeSection-description') }
//        rouletteWheelImage(required: false) { epointsRouleteWheelSecondSection.$('.HomeSection-image') }
//        shopToClaimButton(required: false) { epointsRouleteWheelSecondSection.$('.HomeSection-yellowButton') }
//    }
//}