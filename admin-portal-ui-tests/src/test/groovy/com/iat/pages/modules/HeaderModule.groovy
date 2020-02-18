package com.iat.pages.modules

import geb.Module

class HeaderModule extends Module {

    static content = {
        autoLogoutCounter(wait: true) { $('#autoLogout') }
    }

}