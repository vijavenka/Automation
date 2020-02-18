package com.iat.stepdefs.staticPagesSection

import com.iat.pages.home.EpointsHomePage
import com.iat.pages.home.footerStaticPages.PrivacyPolicyPage
import com.iat.stepdefs.envVariables
import geb.Browser

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def privacyPolicyPage = new PrivacyPolicyPage()

def browser = new Browser()
def envVar = new envVariables()

// 1.1 //  ------------------------------------------------------------------------------------- Privacy policy page
// ---------------------------------------------------------------------------------------------------- page content
// Update ---------------------------------------------------- EPOINTS - complete Angular for static com.iat.pages(NBO-1437)
Given(~/^Privacy policy page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.footerModule.clickPrivacyPolicyLink()
    at PrivacyPolicyPage
    privacyPolicyPage = page
}
When(~/^User looks on privacy policy page$/) { ->
    //leave empty
}
Then(~/^He can see that privacy policy page contains proper information$/) { ->
    waitFor { privacyPolicyPage.pageContent.isDisplayed() }
}

// 1.2 //  ------------------------------------------------------------------------------------- Privacy policy page
// ---------------------------------------------------------------------------------------------- google policy link
// Update ---------------------------------------------------- EPOINTS - complete Angular for static com.iat.pages(NBO-1437)
When(~/^User click google policy link$/) { ->
    browser.withNewWindow({ privacyPolicyPage.clickGooglePrivacyLink() }, close: true, wait: true) {
        waitFor { browser.currentUrl == envVar.googlePrivacyURL }
        assert browser.currentUrl == envVar.googlePrivacyURL
    }
}
Then(~/^He will be redirected to google privacy policy page$/) { ->
    //done in previous step
}