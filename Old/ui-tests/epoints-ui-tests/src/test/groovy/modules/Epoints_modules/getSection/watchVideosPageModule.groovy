package modules.Epoints_modules.getSection

import geb.Module

/**
 * Created by kbaranowski on 2015-01-29.
 */
class watchVideosPageModule extends Module {
    static content = {
        mainHeader{ $('.getWatch-row').find('h1') }
        mainDescription{ $('.getWatch-row').find('p') }
        firstSectionPicture{ $('.row.getWatch-row',0).find('.section-image').find('img') }
        firstSectionHeader{ $('.row.getWatch-row',0).find('h2') }
        firstSectionText{ $('.row.getWatch-row',0).find('p') }

        secondSectionPicture{ $('.row.getWatch-row',1).find('.section-image').find('img') }
        secondSectionHeader{ $('.row.getWatch-row',1).find('h2') }
        secondSectionText{ $('.row.getWatch-row',1).find('p') }

        thirdSectionPicture{ $('.row.getWatch-row',2).find('.section-image').find('img') }
        thirdSectionHeader{ $('.row.getWatch-row',2).find('h2') }
        thirdSectionText{ $('.row.getWatch-row',2).find('p') }

        videojugLink{ $('.u-link') }
    }
    def clickVideojugLink(number){ waitFor{ videojugLink[number].isDisplayed() }; videojugLink[number].click() }
}