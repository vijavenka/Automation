package com.iat.pages

class FourOFourPage extends AbstractPage {

    static url = '/404'
    static at = {
        waitFor {
            browser.currentUrl.contains("/404")
        }
    }

    static content = {
        errorTextArea(wait: true) { $('.row').find('div', 0) }
        linksArea(wait: true) { $('.row').find('div', 1) }
        rewardsLink(wait: true) { linksArea.find("a[ui-sref=\"app.rewards\"]") }
        rewardsDepartmentLink(wait: true) { rewardsLink.find('li') }
        shopOnlineLink(wait: true, required: false) { linksArea.find("a[ui-sref=\"app.get.az\"]") }
        playGamesLink(wait: true, required: false) { linksArea.find("a[ui-sref=\"app.get.play\"]") }
        goInstoreLink(wait: true, required: false) { linksArea.find("a[ui-sref=\"app.get.instore\"]") }
        inviteFrindsLink(wait: true, required: false) { linksArea.find("a[ui-sref=\"app.get.inviteFriend\"]") }
    }

    def clickRewarsLink() { waitFor { rewardsLink.isDisplayed() }; rewardsLink.click() }

    def clickRewarsDepartmentLink() { waitFor { rewardsDepartmentLink.isDisplayed() }; rewardsDepartmentLink.click() }

    def clickShopOnlineLink() { waitFor { shopOnlineLink.isDisplayed() }; shopOnlineLink.click() }

    def clickPlayGamesLink() { waitFor { playGamesLink.isDisplayed() }; playGamesLink.click() }

    def clickGoInstoreLink() { waitFor { goInstoreLink.isDisplayed() }; goInstoreLink.click() }

    def clickInviteFrindsLinkLink() { waitFor { inviteFrindsLink.isDisplayed() }; inviteFrindsLink.click() }
}