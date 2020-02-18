package com.iat.pages.modules

import com.iat.Config
import com.iat.stepdefs.utils.HelpFunctions
import geb.Module

class TopNavigationModule extends Module {

    def helpFunctions = new HelpFunctions()

    static content = {

        topNavigation(wait: true) { module TopNavigationModule }

        preloader(required: false) { $('.preloaderbar') } //has classes hide and active depends on state
        topNavigationLogo(wait: true) { $('.logo') }
        messageIcon(wait: true, required: true) { $('.zmdi-email') }
        messageIconCounter(required: false) { $('.NotificationsWidget-counter') }
        messagesDDLCounter(required: false) { $('.NotificationsWidget-listHeader', 1) }
        messagesDDL(required: false) { $('.NotificationsWidget-list.list-group') }
        messagesDDLApprovedIcon(required: false) { messagesDDL.find('.zmdi-email') }
        messagesDDLApprovedLabel(required: false) {
            messagesDDL.find('span[translate="notifications.APPROVAL_APPROVED"]')
        }
        messagesDDLApprovedTime(required: false) { messagesDDL.find('.NotificationsWidget-itemDate') }
        messagesDDLPendingIcon(required: false) { messagesDDL.find('.zmdi-email') }
        messagesDDLPendingLabel(required: false) {
            messagesDDL.find('span[translate="notifications.APPROVAL_PENDING"]')
        }
        messagesDDLPendingTime(required: false) { messagesDDL.find('.NotificationsWidget-itemDate') }
        messagesDDLRejectedIcon(required: false) { messagesDDL.find('.zmdi-email') }
        messagesDDLRejectedLabel(required: false) {
            messagesDDL.find('span[translate="notifications.APPROVAL_REJECTED"]')
        }
        messagesDDLRejectedTime(required: false) { messagesDDL.find('.NotificationsWidget-itemDate') }
        messagesDDLDownloadIcon(required: false) { messagesDDL.find('.zmdi-download') }
        messagesDDLDownloadLabel(required: false) {
            messagesDDL.find('span[translate="notifications.FILE_READY"]')
        }
        messagesDDLDownloadTime(required: false) { messagesDDL.find('.NotificationsWidget-itemDate') }

        helpIcon(wait: true) { $('.zmdi-help') }

        userImageDDL(wait: true) { $('a[aria-label="profile"]') }
        myProfileOption(wait: true) { $('.zmdi-account', 0) }
        changePasswordOption(wait: true) { $('.zmdi-account', 1) }
        logoutOption(wait: true) { $('.zmdi-forward') }

        userName(wait: true) { $(".UserDropdown span") }
        userRoleType(wait: true) { $(".UserDropdown small") }
        warningInfo(required: false) { $('.md-toast-warn') }
        alertInfo(required: false) { $('.md-toast-content') }

    }

    def expandUserImageDDL() {
        waitFor { userImageDDL.isDisplayed() }; userImageDDL.click()
        helpFunctions.waitSomeTime(Config.waitShort)
    }

    def clickMyProfileOption() {
        waitFor { myProfileOption.isDisplayed() }; myProfileOption.click()
    }

    def clickChangePasswordOption() {
        waitFor { changePasswordOption.isDisplayed() }; changePasswordOption.click()
    }

    def clickLogoutOption() {
        waitFor { logoutOption.isDisplayed() }; logoutOption.click()
    }

    def expandMessagesDDL() {
        waitFor { messageIcon.isDisplayed() }; messageIcon.click()
    }

    def clickPendingNotification() {
        waitFor { messagesDDLPendingLabel.isDisplayed() }; messagesDDLPendingLabel.click()
    }

    def clickApprovedNotification() {
        waitFor { messagesDDLApprovedLabel.isDisplayed() }; messagesDDLApprovedLabel.click()
    }

    def clickRejectedNotification() {
        waitFor { messagesDDLRejectedLabel.isDisplayed() }; messagesDDLRejectedLabel.click()
    }

    def clickDownloadNotification(int labelNumber) {
        waitFor { messagesDDLDownloadLabel[labelNumber].isDisplayed() }; messagesDDLDownloadLabel[labelNumber].click()
    }

    def clickHelpButton() {
        waitFor { helpIcon.isDisplayed() }; helpIcon.click()
    }
}