package modules.Epoints_modules.staticPagesFooterSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-23.
 */
class privacyPolicyPageModule extends Module{
    static content = {
        pageContent{ $('.main-view') }
        header{ pageContent.find('h1') }
        googlePrivacyLink{ pageContent.find('a',0) }
    }
    def clickGooglePrivacyLink(){ waitFor{ googlePrivacyLink.isDisplayed() }; googlePrivacyLink.click() }
}