package com.iat.pages.home.footerStaticPages

import geb.Page

class RecognitionandRewardsPage extends Page {

    static url = 'http://eachperson.com/'
    static at = { waitFor { browser.getCurrentUrl() == "http://eachperson.com/" } }

    static content = {
        //external page
    }

}
