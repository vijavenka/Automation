package stepdefs

import geb.Browser
import geb.binding.BindingUpdater
import geb.driver.CachingDriverFactory
import mysqlConnection.jdbc

import static cucumber.api.groovy.Hooks.After
import static cucumber.api.groovy.Hooks.Before

Before() {
    bindingUpdater = new BindingUpdater(binding, new Browser())
    bindingUpdater.browser.clearCookies()
    bindingUpdater.initialize()
}

After() {
    println("Clearing cache and closing browser...")
    def cachedDriver = CachingDriverFactory.clearCacheAndQuitDriver()
    bindingUpdater.remove()
}