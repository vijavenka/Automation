package com.iat.pages

import com.iat.pages.home.modules.*
import geb.Page

abstract class AbstractPage extends Page {

    static content = {
        cookiePanelModule { module CookiePanelModule }
        headerModule { module HeaderModule }
        signInModule { module SignInModule }
        userDropDownMenuModule { module UserDropDownMenuModule }
        basketModule { module BasketModule }
        footerModule { module FooterModule }
        loginModalModule { module LoginModalModule }
        accountSwitcher(required: false) { module(SwitchAccountModule) }
    }
}