package modules.Epoints_modules.staticPagesFooterSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-23.
 */
class termsAndConditionsPageModule extends Module{
    static content = {
        pageContent{ $('.main-view') }
        header{ pageContent.find('h1') }
        epointsLink{ pageContent.find('a',0) }
        supportLink{ pageContent.find('a',1) }
    }
    def clickEpointsLink(){ waitFor{ epointsLink.isDisplayed() }; epointsLink.click() }
    def clickSupportLink(){ waitFor{ supportLink.isDisplayed() }; supportLink.click() }
}