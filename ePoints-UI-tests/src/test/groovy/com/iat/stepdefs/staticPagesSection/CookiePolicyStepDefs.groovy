package com.iat.stepdefs.staticPagesSection

import com.iat.pages.home.EpointsHomePage
import com.iat.pages.home.footerStaticPages.CookiePolicyPage
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def cookiePolicyPage = new CookiePolicyPage()

def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

// 1.1 //  -------------------------------------------------------------------------------------- Cookie policy page
// ---------------------------------------------------------------------------------------------------- page content
// Update ---------------------------------------------------- EPOINTS - complete Angular for static com.iat.pages(NBO-1437)
Given(~/^Cookie policy page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.footerModule.clickCookiePolicyLink()
    at CookiePolicyPage
    cookiePolicyPage = page
}
When(~/^User looks on cookie policy page$/) { ->
    //leave empty
}
Then(~/^He can see that cookie policy page contains proper information$/) { ->
    waitFor { cookiePolicyPage.pageContent.isDisplayed() }
}

// 1.2 //  -------------------------------------------------------------------------------------- Cookie policy page
// ------------------------------------------------------------------------------------------ youronlinechoices link
// Update ---------------------------------------------------- EPOINTS - complete Angular for static com.iat.pages(NBO-1437)
When(~/^User click youronlinechoices link$/) { ->
    browser.withNewWindow({ cookiePolicyPage.clickYourOnlineChoicesLink() }, close: true, wait: true) {
        waitFor { browser.currentUrl == envVar.yourOnlineChoicesURL }
        assert browser.currentUrl == envVar.yourOnlineChoicesURL
    }
}
Then(~/^He will be redirected to youronlinechoices page$/) { ->
    //done in previous step
}

// 1.3 //  -------------------------------------------------------------------------------------- Cookie policy page
// -------------------------------------------------------------------------------------------- allaboutcookies link
// Update ---------------------------------------------------- EPOINTS - complete Angular for static com.iat.pages(NBO-1437)
When(~/^User click allaboutcookies link$/) { ->
    browser.withNewWindow({ cookiePolicyPage.clickAllaboutcookiesLink() }, close: true, wait: true) {
        waitFor { browser.currentUrl == envVar.allaboutcookiesURL }
        assert browser.currentUrl == envVar.allaboutcookiesURL
    }
}
Then(~/^He will be redirected to allaboutcookies page$/) { ->
    //done in previous step
}