package com.iat.pages.home.modules

import geb.Module

class UserDropDownMenuModule extends Module {

    static content = {
        //Links of logged User context Menu
        useDropDownMenuMain(required: true) { $("a").has(".SecondaryNav--username") }
        userDropDownMenuBasicElement(wait: true) { $('.sign-in__inner.clearfix').find('ul').find('li') }
        userDropDownMenuMyEpointsOption(required: false) { $('a[translate="header.account-sub.myEpoints"]') }
        userDropDownMenuProfileOption(wait: true) { $('a[translate="header.account-sub.profile"]') }
        userDropDowneMenuActivityOption(wait: true) { $('a[translate="header.account-sub.activity"]') }
        userDropDownMenuRewardsHistoryOption(wait: true) { $('a[translate="header.account-sub.rewards"]') }
        userDropDownMenuPurchaseEpointsOption(required: false) { $('a[translate="header.account-sub.buyEpoints"]') }
        userDropDownMenuEcardManagementOption(required: false) { $('a[translate="header.account-sub.ecards"]') }
        userDropDownMenuSignOutOption(wait: true, required: false) { $('a[translate="header.logout"]') }
    }

    //Atomic operations on User DropDown Menu
    def clickOnMyEpointsOption() {
        waitFor { userDropDownMenuMyEpointsOption.isDisplayed() }; userDropDownMenuMyEpointsOption.click()
    }

    def clickOnProfileOption() {
        waitFor { userDropDownMenuProfileOption.isDisplayed() }; userDropDownMenuProfileOption.click()
    }

    def clickOnActivityOption() {
        waitFor { userDropDowneMenuActivityOption.isDisplayed() }; userDropDowneMenuActivityOption.click()
    }

    def clickOnRewardsHistoryOption() {
        waitFor { userDropDownMenuRewardsHistoryOption.isDisplayed() }; userDropDownMenuRewardsHistoryOption.click()
    }

    def clickOnPurchaseHistoryOption() {
        waitFor { userDropDownMenuPurchaseEpointsOption.isDisplayed() }; userDropDownMenuPurchaseEpointsOption.click()
    }

    def clickOnEcardManagementOption() {
        waitFor { userDropDownMenuEcardManagementOption.isDisplayed() }; userDropDownMenuEcardManagementOption.click()
    }

    def clickOnSignOutOption() {
        waitFor { userDropDownMenuSignOutOption.isDisplayed() }; userDropDownMenuSignOutOption.click()
    }
}
