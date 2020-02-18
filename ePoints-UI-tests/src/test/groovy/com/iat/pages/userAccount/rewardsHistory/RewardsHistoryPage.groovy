package com.iat.pages.userAccount.rewardsHistory

import com.iat.pages.AbstractPage
import com.iat.stepdefs.utils.DateTimeUtil
import geb.Module

import static com.iat.stepdefs.utils.DateTimeUtil.convertToDate

class RewardsHistoryPage extends AbstractPage {

    static url = "/my-account/rewards"
    static at = { waitFor { title.contains("Rewards history | epoints") } }

    static content = {
        rewards(required: false) { $("#rewardsHistoryContent tbody tr").moduleList(RewardHistoryModule) }
        rewardsHistoryHeader { $(".page-title") }
        rewardsTable(required: false) { $("#rewardsHistoryContent") }
        rewardsTableHeaderColumnNameDate { rewardsTable.find(".in-header").find("th", 0) }
        rewardsTableHeaderColumnNameReward { rewardsTable.find(".in-header").find("th", 1) }
        rewardsTableHeaderColumnNameDeliveryDetails { rewardsTable.find(".in-header").find("th", 3) }
        backToTopButton { $(".u-link") }
        noRewardsHistoryYetInfo { $(".no-yet") }
    }

    def clickChosenContactUsLink(int number) {
        waitFor { rewards.size() > number }
        rewards.get(number).contactUsLink.click()
    }
}

public class RewardHistoryModule extends Module {

    static content = {
        date(required: true) {
            def date = $(".date")
            date.isDisplayed() ? convertToDate(date.text(), DateTimeUtil.Format.ddMMMyyyy_2) : null
        }
        image(required: true) { $(".rewardThumb") }
        name(required: true) {
            def name = $(".rewardInfoCell h2")
            name.isDisplayed() ? name.text().trim() : ""
        }
        epointsUsed(required: true) { $(".rewardInfoCell strong") }
        epointsSum(required: true) {
            def sum = $(".rewardInfoCell .sumPoints")
            sum.isDisplayed() ? sum.text().trim().replace(",", "").toInteger() : 0
        }
        address(required: true) {
            def address = $("td.rewardInfoCell").next()
            address.isDisplayed() ? address.text().trim() : ""
        }
        contactUsLink(required: true) { $(".link") }
    }
}