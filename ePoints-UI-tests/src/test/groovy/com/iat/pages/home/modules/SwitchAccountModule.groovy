package com.iat.pages.home.modules

import geb.Module

class SwitchAccountModule extends Module {

    static content = {
        root(wait: true, required: true) { $(".businessHeader-view") }
        currentAccount(required: true) { epointsSwitcher.hasClass("is-active") ? "epoints" : "United" }
        unitedSwitcher(wait: true, required: true) { root.$(".BusinessHeaderNav-list a").has(text: "United") }
        epointsSwitcher(wait: true, required: true) { root.$(".BusinessHeaderNav-list a").has(text: "epoints") }
    }
}