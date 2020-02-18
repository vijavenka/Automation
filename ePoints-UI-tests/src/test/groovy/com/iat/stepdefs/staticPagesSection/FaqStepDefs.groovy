package com.iat.stepdefs.staticPagesSection

import com.iat.pages.home.EpointsHomePage
import com.iat.pages.home.footerStaticPages.FaqPage
import com.iat.stepdefs.envVariables
import geb.Browser

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def faqPage = new FaqPage()

def browser = new Browser()
def envVar = new envVariables()

// 1.1 //  ------------------------------------------------------------------------------------------------ FAQ page
// ---------------------------------------------------------------------------------------------------- page content
Given(~/^FAQ page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.footerModule.clickFAQLink()
    at FaqPage
    faqPage = page
}
When(~/^User look on faq page$/) { ->
    //leave empty
}
Then(~/^He will see that it contains section with frequently asked questions$/) { ->
    assert faqPage.header.text() == envVar.faqHeader
    sleep(2000)
    waitFor { faqPage.individualQuestionLink.size() == 21 }
    assert faqPage.individualQuestionLink.size() == 21
    assert faqPage.individualAnswerSection.size() == 21
}
Then(~/^Number of questions and answers is the same$/) { ->
    assert faqPage.individualQuestionLink.size() == faqPage.individualAnswerSection.size()
}