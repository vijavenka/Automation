package com.iat.pages.home.modules

import geb.Module

class HeaderModule extends Module {

    static content = {
        //Header Links of Epoints Home Page
        headerSignInButton(required: false) { $('.SecondaryNav-link.sign-in__btn') }
        headerFacebookSigInButton(required: false) { $(".sign-in__panel .btn-facebook") }
        headerJoinButton(required: false) { $('a[translate="header.join"]') }
        headerUserNameLabel(wait: true) { $('span.SecondaryNav-link.SecondaryNav--username') }
        headerHomeButton(wait: true) { $(".MainNav--home") }
        headerPointsButton(required: false) { $('.MainNav--get') }
        pointsNavbar(wait: true, required: true) { $('.SubHeader.SubHeader--get') }
        navigationOption(wait: true, required: true) { String navigationOption -> pointsNavbar.find("a", text: navigationOption) }
//        headerRewardsSectionButton(wait: true, required: true) { $(".MainNav--spend", 0) }
        headerRewardsSectionButton(wait: true, required: true) { $('.main-logo') }
//        headerPrizesButton(required: false) { $(".MainNav--prizes") }
        headerEcardsButton(required: false) { $(".MainNav--ecards") }
//        headerBasketIcon(required: false) { $('a[class="sign-in__btn sign-in--cart"]') }
        sign_wrapper(wait: true) { $('div.sign-wrapper')}
        headerBasketIcon(required: false) { sign_wrapper.find('div.bag-points') }
        headerBasketCounter(required: false) {
            headerBasketIcon.isDisplayed() ? headerBasketIcon.$(".RewardsCounter").text().trim().toInteger() : 0
        }
        headerPointsBalancePending(required: false) { $('.balance-widget__pending__value') }
        headerPointsBalanceConfirmed(required: false) { $('.balance-widget__confirmed__value') }

        zonePickerFlag { $('.ZonePicker__flag') }
        zonePickerPanel { $('.ZonePicker') }
        zonePickerCountryBasic { $('.ZonePicker__country') }
        zonePickerPopupHeader { zonePickerPanel.find('h3') }
        zonePickerPopupText { zonePickerPanel.find('p') }
        zonePickerPopupCloseButton { zonePickerPanel.find('.ZonePicker__closeInfo').find('a') }

        logo(required: true) { $(".MainHeader-logoContainer") }
        logoText(required: false) { logo.text().trim() }
        logoLink(required: true) { logo.$("a").@href }
    }

    //Atomic operation on Header of Epoints
    def clickOnSignInButton() { waitFor { headerSignInButton.isDisplayed() }; headerSignInButton.click() }

    def clickOnJoinButton() { waitFor { headerJoinButton.isDisplayed() }; headerJoinButton.click() }

    def clickOnUserNameLabel() {
        waitFor { headerUserNameLabel.isDisplayed() }; headerUserNameLabel.click(); sleep(1000)
    }

    def clickOnPointsButton() {
        waitFor { headerPointsButton.isDisplayed() }; headerPointsButton.click()
    }

    def clickPointsSubNavigationOption(navOption) {
        waitFor { navigationOption(navOption).parent().isDisplayed() }
        navigationOption(navOption).parent().click()
    }

    def clickOnRewardsButton() {
        waitFor { headerRewardsSectionButton.isDisplayed() }; headerRewardsSectionButton.click()
    }

//    def clickOnPrizesButton() {
//        waitFor { headerPrizesButton.isDisplayed() }; headerPrizesButton.click()
//    }

    def clickOnBasketIcon() {
        waitFor { headerBasketIcon.isDisplayed() }
        headerBasketIcon.click()
    }

    def epandZonePickerPanel() { waitFor { zonePickerFlag[0].isDisplayed() }; zonePickerFlag[0].click() }

    def clickCloseZonePickerButton() {
        waitFor { zonePickerPopupCloseButton.isDisplayed() }; zonePickerPopupCloseButton.click()
    }

    def choseUKzone() { waitFor { zonePickerFlag[1].isDisplayed() }; zonePickerFlag[1].click() }

    def choseIrelandZone() { waitFor { zonePickerFlag[2].isDisplayed() }; zonePickerFlag[2].click() }
}