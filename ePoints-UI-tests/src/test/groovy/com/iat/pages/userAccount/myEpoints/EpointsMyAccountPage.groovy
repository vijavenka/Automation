package com.iat.pages.userAccount.myEpoints

import com.iat.pages.AbstractPage
import com.iat.pages.userAccount.modules.UserProfileMenuModule

class EpointsMyAccountPage extends AbstractPage {

    static url = '/my-account'
    static at = { waitFor { browser.currentUrl.contains("/my-account") } }

    static content = {

        userProfileMenuModule { module UserProfileMenuModule }

        myEpointsHeader { $('.page-title') }

        userPanel { $('#userInfoPanel') }
        userPanelUserName { userPanel.find('.name') }
        userPanelTotalEpointsText { userPanel.find('tbody', 0).find('tr', 0).find('td', 0) }
        userPanelTotalEpointsValue { userPanel.find('tbody').find('tr', 0).find('.points') }
        userPanelRewardsClaimedText { userPanel.find('tbody', 0).find('tr', 1).find('td', 0) }
        userPanelRewardsClaimedValue { userPanel.find('tbody').find('tr', 1).find('.points') }
        userPanelYourTopSitesText(required: false) { userPanel.find('h4') }
        userPanelYourTopSitesElemntTextBasic(required: false) { userPanel.find('tr', 2).find('td', 0) }
        userPanelYourTopSitesElementValueBasic(required: false) { userPanel.find('tr', 2).find('td', 1) }

        completeYourProfilePanel { $('.btn-yellow.completeProfile.inside-link') }

        activityTable { $('#recent-activity') }
        activityTableHeaderRecentActivityColumn { activityTable.find('.header.left.absolute').find('h3') }
        activityTableHeaderAllActivityLink { activityTable.find('.header.left.absolute').find('.inside-link') }
        activityTableHeaderEpEarned { activityTable.find('.in-header').find('.text-center', 0) }
        activityTableHeaderEpSpent { activityTable.find('.in-header').find('.text-center', 1) }
        activityTableHeaderBalance { activityTable.find('.in-header').find('.text-center', 2) }
        activityInfoContentDateBasic {
            activityTable.find('#recentTransactions').find('tbody').find('tr').find('.date-cell')
        }
        activityInfoContentRewardNameBasic {
            activityTable.find('#recentTransactions').find('tbody').find('tr').find('.action')
        }
        activityInfoContentEpEarnedBasic {
            activityTable.find('#recentTransactions').find('tbody').find('tr').find('.text-center.points', 0)
        }
        activityInfoContentEpSpentBasic {
            activityTable.find('#recentTransactions').find('tbody').find('tr').find('.text-center.points', 1)
        }
        activityInfoContentBalanceBasic {
            activityTable.find('#recentTransactions').find('tbody').find('tr').find('.text-center.points', 2)
        }

        recentRewardsTable { $('#recentRewards') }
        recentRewardsTableHeaderRrecentRewardsColumn { recentRewardsTable.find('.header.left.absolute').find('h3') }
        recentRewardsTableHeaderViewHistoryLink {
            recentRewardsTable.find('.header.left.absolute').find('.inside-link')
        }
        recentRewardsTableHeaderEpUsed { recentRewardsTable.find('.in-header').find('.text-center', 0) }
        recentRewardsTableHeaderTotal { recentRewardsTable.find('.in-header').find('.text-center', 1) }
        recentRewardsContentDateBasic {
            recentRewardsTable.find('#rewardsHistory').find('tbody').find('tr').find('.date')
        }
        recentRewardsContentRewardImageBasic {
            recentRewardsTable.find('#rewardsHistory').find('tbody').find('tr').find('.rewardThumb')
        }
        recentRewardsInfoContentRewardNameBasic {
            recentRewardsTable.find('#rewardsHistory').find('tbody').find('tr').find('.title').find('a')
        }
        recentRewardsInfoContentEpUsedBasic {
            recentRewardsTable.find('#rewardsHistory').find('tbody').find('tr').find('.points-used')
        }
        recentRewardsInfoContentTotalBasic {
            recentRewardsTable.find('#rewardsHistory').find('tbody').find('tr').find('.sumPoints')
        }

        whereGotEpointsTable(required: false) { $('#staticInfo') }
        whereGotEpointsTableHeaderText(required: false) { whereGotEpointsTable.find('.header.left') }
        whereGotEpointsTableContentText(required: false) { whereGotEpointsTable.find('p') }
        whereGotEpointsTableFindStoresButton(required: false) { whereGotEpointsTable.find('.btn-yellow') }
    }

    //Page operations
    def clickAllActivityLinkOnRecentActivityInfoTable() {
        waitFor { activityTableHeaderAllActivityLink.isDisplayed() }; activityTableHeaderAllActivityLink.click()
    }

    def clickCompleteYourProfileButton() {
        waitFor { completeYourProfilePanel.isDisplayed() }; completeYourProfilePanel.click()
    }

    def clickViewHistoryLinkOnRecentRewardsTable() {
        waitFor { recentRewardsTableHeaderViewHistoryLink.isDisplayed() }
        recentRewardsTableHeaderViewHistoryLink.click()
    }

    def clickChosenProductLinkOnRecentRewardsTable(number) {
        waitFor { recentRewardsInfoContentRewardNameBasic[number].isDisplayed() }
        recentRewardsInfoContentRewardNameBasic[number].click()
    }

    def clickFindYourFavouriteStoresButton() {
        waitFor { whereGotEpointsTableFindStoresButton.isDisplayed() }; whereGotEpointsTableFindStoresButton.click()
    }

}