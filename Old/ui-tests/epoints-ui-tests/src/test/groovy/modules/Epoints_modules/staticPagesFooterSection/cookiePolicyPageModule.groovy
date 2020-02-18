package modules.Epoints_modules.staticPagesFooterSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-23.
 */
class cookiePolicyPageModule extends Module {
    static content = {
        pageContent{ $('.main-view') }
        header{ pageContent.find('h1') }
        yourOnlineChoicesLink{ pageContent.find('a',0) }
        allaboutcookiesLink{ pageContent.find('a',1) }
    }
    def clickYourOnlineChoicesLink(){ waitFor{ yourOnlineChoicesLink.isDisplayed() }; yourOnlineChoicesLink.click() }
    def clickAllaboutcookiesLink(){ waitFor{ allaboutcookiesLink.isDisplayed() }; allaboutcookiesLink.click() }
}