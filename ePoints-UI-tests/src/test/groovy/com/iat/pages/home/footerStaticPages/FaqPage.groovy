package com.iat.pages.home.footerStaticPages

import com.iat.pages.AbstractPage

class FaqPage extends AbstractPage {

    static url = '/faq'
    static at = { waitFor { getTitle().contains('FAQ | epoints') } }

    static content = {

        header(wait: true) { $('h1') }
        individualQuestionLink(wait: true) { $('.container', 0).find('ul', 0).find('li') }
        individualAnswerSection(wait: true) { $('.container', 0).find('div') }
    }

}