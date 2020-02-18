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
    //remove all automated test accounts
    /*def mysql = new jdbc('points')
    mysql.get_all_query_content("Select email FROM points_manager.User WHERE email LIKE '%automated%'")
    for(int i=1; i<=Integer.parseInt(mysql.get("Select count(*) FROM points_manager.User WHERE email LIKE '%automated%'",1)); i++) {
        mysql.get_value_of_proper_row(true, 1)
            mysql.update("DELETE FROM points_manager.Request WHERE userId='" + mysql.get("SELECT userKey FROM points_manager.User WHERE email='" + mysql.get_value_of_proper_row(false, 1) + "'", 1) + "'")
            mysql.update("DELETE FROM points_manager.Points WHERE userId='" + mysql.get("SELECT id FROM points_manager.User WHERE email='" + mysql.get_value_of_proper_row(false, 1) + "'", 1) + "'")
            mysql.update("DELETE FROM points_manager.UserToken WHERE user_id='" + mysql.get("SELECT id FROM points_manager.User WHERE email='" + mysql.get_value_of_proper_row(false, 1) + "'", 1) + "'")
            mysql.update("DELETE FROM points_manager.UserId WHERE user_id='" + mysql.get("SELECT id FROM points_manager.User WHERE email='" + mysql.get_value_of_proper_row(false, 1) + "'", 1) + "'")
            mysql.update("DELETE FROM points_manager.User WHERE id='" + mysql.get("SELECT id FROM points_manager.User WHERE email='" + mysql.get_value_of_proper_row(false, 1) + "'", 1) + "'")
        }
    mysql.close()*/
    //
    println("Clearing cache and closing browser...")
    def cachedDriver = CachingDriverFactory.clearCacheAndQuitDriver()
    bindingUpdater.remove()
}