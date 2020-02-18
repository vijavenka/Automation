package modules.Epoints_modules.staticPagesFooterSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-23.
 */
class faqPageModule extends Module{
    static content = {
        header{ $('.introductory_display_texts') }
        searchLabel{ $('#search_box') }
        searchInputField{ $('#suggestions_query') }
        searchButton{ $('#suggestion_submit') }
        overviewLink{ $('#forum_nav_overview') }
        recentLink{ $('#forum_nav_recent') }
        questionSection{ $('.category') }
    }
}