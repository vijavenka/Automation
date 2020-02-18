package modules.Epoints_modules.getSection

import geb.Module

/**
 * Created by kbaranowski on 2015-01-29.
 */
class goInstorePageModule extends Module{
    static content = {
        mainHeader{ $('.getInstore-row').find('h1') }
        mainDescription{ $('.getInstore-row').find('p') }
        firstSectionPicture{ $('.row.getInstore-row',0).find('.section-image').find('img') }
        firstSectionHeader{ $('.row.getInstore-row',0).find('h2') }
        firstSectionText{ $('.row.getInstore-row',0).find('p') }

        secondSectionPicture{ $('.row.getInstore-row',1).find('.section-image').find('img') }
        secondSectionHeader{ $('.row.getInstore-row',1).find('h2') }
        secondSectionText{ $('.row.getInstore-row',1).find('p') }

        thirdSectionPicture{ $('.row.getInstore-row',2).find('.section-image').find('img') }
        thirdSectionHeader{ $('.row.getInstore-row',2).find('h2') }
        thirdSectionText{ $('.row.getInstore-row',2).find('p') }

        googlePlayLink{ $('.getInstore-appButtonsRow').find('.getInstore-appButton.getInstore--androidButton') }
        appStoreLink{ $('.getInstore-appButtonsRow').find('.getInstore-appButton.getInstore--iosButton') }
        bigdlLink{ $('.u-link',0) }
        googlePlayTextLink{ $('.u-link',1) }
        appStoreTextLink{ $('.u-link',2) }
    }

        def clickGooglePlayLink(){ waitFor{ googlePlayLink.isDisplayed() }; googlePlayLink.click() }
        def clickAppStoreLink(){ waitFor{ appStoreLink.isDisplayed() }; appStoreLink.click() }
        def clickGooglePlayTextLink(){ waitFor{ googlePlayTextLink.isDisplayed() }; googlePlayTextLink.click() }
        def clickAppStoreTextLink(){ waitFor{ appStoreTextLink.isDisplayed() }; appStoreTextLink.click() }
        def clickBigdlLink(){ waitFor{ bigdlLink.isDisplayed() }; bigdlLink.click() }
}
