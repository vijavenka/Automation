package com.iat.pages.points

import com.iat.pages.AbstractPage

class InviteFriendsPage extends AbstractPage {

    static url = '/points/friends'
    static at = {
        waitFor {
            browser.currentUrl.contains("/friends")
        }
    }

    static content = {

        mainBoldedPageHeader(wait: true) { $('.inviteFriend-title') }
        //not logged
        joinNowText(wait: true) { $('.inviteFriend-topPanel-content', 0) }
        joinNowButton(wait: true) { $('.inviteFriend-topPanel-content', 0).find('.btn-success') }
        signInButton(wait: true) { $('.inviteFriend-topPanel-content', 0).find('.u-link-success') }
        howItWorksText(wait: true) { $('.inviteFriend-topPanel-content', 1) }
        //logged
        uniqueInvitationLinkText(wait: true) { $('.inviteFriend-topPanel-content', 0) }
        uniqueInvitationLinkField(wait: true) { $('.inviteFriend-topPanel-content', 0).find('.inviteFriend-linkInput') }
        orSimplyText(wait: true) { $('.inviteFriend-topPanel-content', 1) }
        postLinkOnFacebookButton(wait: true) { $('.inviteFriend-topPanel-content', 1).find('.btn-facebook') }
        howItWorksTextLogged(wait: true) { $('.inviteFriend-section') }
    }

    def clikJoinButton() { waitFor { joinNowButton.isDisplayed() }; joinNowButton.click() }

    def clikSignInButton() { waitFor { signInButton.isDisplayed() }; signInButton.click() }

    def clickPostOnFacebookWallButton() {
        waitFor { postLinkOnFacebookButton.isDisplayed() }; postLinkOnFacebookButton.click()
    }
}