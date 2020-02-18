package com.iat.pages.userAccount.modules

import geb.Module

class UserProfileMenuModule extends Module {

    static content = {
        myEpointsSubheaderOption { $('.SubHeader-list').find('.SubHeader-link', 0) }
        profileSubheaderOption { $('.SubHeader-list').find('.SubHeader-link', 1) }
        activitySubheaderOption { $('.SubHeader-list').find('.SubHeader-link', 2) }
        rewardsHistorySubheaderOption { $('.SubHeader-list').find('.SubHeader-link', 3) }
        buyEpointsSubheaderOption(required: false) { $('.SubHeader-list').find('.SubHeader-link', 4) }
        ecardManagementSubheaderOption(required: false) { $('.SubHeader-list').find('.SubHeader-link', 5) }
    }

}