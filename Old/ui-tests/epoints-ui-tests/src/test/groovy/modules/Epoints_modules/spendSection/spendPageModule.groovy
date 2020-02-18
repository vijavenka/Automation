package modules.Epoints_modules.spendSection

import geb.Module

/**
 * Created by kbaranowski on 2014-11-06.
 */
class spendPageModule extends Module{
    static content = {
        //spendPageHeader{ $('') }
        //itemCounterLink{ $('') }

        topBannerRow{ $('.spendBanner-row') }
        topBannerColumnBasic{ columnNumber -> topBannerRow.find('.spendBanner-col', columnNumber) }
        topBannerColumnImageBasic{ columnNumber -> topBannerColumnBasic(columnNumber).find('.spendBanner-image') }
        topBannerColumnDescriptionBasic{ columnNumber -> topBannerColumnBasic(columnNumber).find('.spendBanner-description') }

        itemAvailableInfoAfterLogin(required: false){ $('.AvailableItems') }
        itemCounterAfterLogin(required: false){ itemAvailableInfoAfterLogin.find('.AvailableItems-counter') }

        searchInputField{ $('.itemsSearch-input') }
        rewardByEpointsDDL{ $('.customSelect-selection') }
        rewardByEpointsDDLOption{ $('.customSelect-dropdown').find('li') }
        searchButton{ $('.itemsSearch').find('button') }

        browseByDepartmensTab{ $('.SpendPageTabs-labels').find('span',0) }
            departmentCardBasic{ $('.DepartmentCard') }
            departmentCardDepartmentNameBasic{ departmentCardBasic.find('.DepartmentCard-title') }
            departmentCardItemsNumberBasic{ departmentCardBasic.find('.DepartmentCard-description') }
        rewardsByEpointsTotalTab{ $('.SpendPageTabs-labels').find('span',1) }
            rewardsCardBasic{ $('.EpointsRangeCard') }
            rewardsCardPointsRangeBasic{ rewardsCardBasic.find('.EpointsRangeCard-title') }
            rewardsCardItemsNumberBasic{ rewardsCardBasic.find('.EpointsRangeCard-description') }
    }

    def clickItemCounterLink(){ waitFor{ itemCounterLink.isDisplayed() }; itemCounterLink.click() }
    def enterTextIntoSearch(phrase){ waitFor(10){ searchInputField.isDisplayed() }; searchInputField.value(phrase) }
    def expandEpointsRangeDDL(){ waitFor{ rewardByEpointsDDL .isDisplayed()}; rewardByEpointsDDL.click() }
    def choseRangeOption(number){ waitFor{ rewardByEpointsDDLOption[number].isDisplayed() }; rewardByEpointsDDLOption[number].click() }
    def clickSearchButton(){ waitFor{ searchButton.isDisplayed() }; searchButton.click() }
    def goToBrowseByDepartmentTab(){ waitFor{ browseByDepartmensTab.isDisplayed() }; browseByDepartmensTab.click() }
        def clickChosenDepartmentCard(number){ waitFor{ departmentCardBasic[number].isDisplayed() }; departmentCardBasic[number].click() }
    def goToRewardsByEpointsTotalTab(){ waitFor{ rewardsByEpointsTotalTab.isDisplayed() }; rewardsByEpointsTotalTab.click() }
        def clickChosenRewardCard(number){ waitFor{ rewardsCardBasic[number].isDisplayed() }; rewardsCardBasic[number].click() }
    def clickItemCounterAfterLogin(){ waitFor{ itemCounterAfterLogin.isDisplayed() }; itemCounterAfterLogin.click() }
}