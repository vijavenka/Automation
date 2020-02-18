package com.iat.stepdefs.staticPagesSection

import com.iat.pages.home.EpointsHomePage
import com.iat.pages.home.footerStaticPages.TandCPage
import com.iat.stepdefs.envVariables
import geb.Browser

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def tandcPage = new TandCPage()

def browser = new Browser()
def envVar = new envVariables()

// 1.1 //  ------------------------------------------------------------------------------------ Terms and conditions
// ---------------------------------------------------------------------------------------------------- page content
Given(~/^Terms and conditions page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.footerModule.clickTermsAndConditionsLink()
    at TandCPage
    tandcPage = page
}
When(~/^User looks on terms and conditions page$/) { ->
    //leave empty
}
Then(~/^He can see that terms and conditions page contains proper information$/) { ->
    waitFor { tandcPage.pageContent.isDisplayed() }
}

// 1.2 //  ------------------------------------------------------------------------------------ Terms and conditions
// ---------------------------------------------------------------------------------------------------- epoints link
When(~/^User use epoints link$/) { ->
    browser.withNewWindow({ tandcPage.clickEpointsLink() }, close: true, wait: true) {
        waitFor { browser.currentUrl == envVar.epointsLiveURL }
        assert browser.currentUrl == envVar.epointsLiveURL
    }
}
Then(~/^He will be redirected to epoints home page$/) { ->
    //done in previous step
}

// 1.3 //  ------------------------------------------------------------------------------------ Terms and conditions
// ---------------------------------------------------------------------------------------------------- support link
When(~/^User use support link$/) { ->
    //leave empty only link href will be checked
}
Then(~/^Email tool will be opened$/) { ->
    assert tandcPage.supportLink.attr('href') == envVar.termsAndConditionssupportLink
}