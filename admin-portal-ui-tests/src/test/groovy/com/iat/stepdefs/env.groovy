package com.iat.stepdefs

import geb.Browser
import geb.binding.BindingUpdater
import static cucumber.api.groovy.Hooks.Before
import static cucumber.api.groovy.Hooks.After

Before() {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.browser.clearCookies()
    bindingUpdater.initialize()
}

After() {
    bindingUpdater.remove()
}