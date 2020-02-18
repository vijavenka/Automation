package com.iat.pages

import geb.Page
import com.iat.pages.modules.LeftSideMenuModule
import com.iat.pages.modules.HeaderModule

class DashboardPage extends Page {

    static url = '/'
    static atCheckWaiting = true
    static at = { waitFor { browser.title.equals("Admin Portal") } }

    static content = {
        headerModule(wait: true) { module HeaderModule }
        leftSideMenuModule(wait: true) { module LeftSideMenuModule }
    }


}