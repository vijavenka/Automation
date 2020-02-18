package modules.Epoints_modules.getSection

import geb.Module

/**
 * Created by kbaranowski on 2015-04-01.
 */
class inviteFriendPageModule extends Module{
    static content = {
        mainBoldedPageHeader{ $('.inviteFriend-title') }
        //not logged
        joinNowText{ $('.inviteFriend-topPanel-content',0) }
        joinNowButton{ $('.inviteFriend-topPanel-content',0).find('.btn-success') }
        signInButton{ $('.inviteFriend-topPanel-content',0).find('.u-link-success') }
        howItWorksText{ $('.inviteFriend-topPanel-content',1) }
        //logged
        uniqueInvitationLinkText{ $('.inviteFriend-topPanel-content') }
        uniqueInvitationLinkField{ $('.inviteFriend-topPanel-content').find('.inviteFriend-linkInput') }
        orSimplyText{ $('.inviteFriend-topPanel-content',1) }
        postLinkOnFacebookButton{ $('.inviteFriend-topPanel-content',1).find('.btn-facebook') }
        howItWorksTextLogged{ $('.inviteFriend-section') }
    }
    def clikJoinButton(){ waitFor{ joinNowButton.isDisplayed() }; joinNowButton.click() }
    def clikSignInButton(){ waitFor{ signInButton.isDisplayed() }; signInButton.click() }
    def clickPostOnFacebookWallButton(){ waitFor{ postLinkOnFacebookButton.isDisplayed() }; postLinkOnFacebookButton.click() }
}