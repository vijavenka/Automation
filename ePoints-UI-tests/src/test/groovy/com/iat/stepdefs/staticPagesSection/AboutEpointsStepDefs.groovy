package com.iat.stepdefs.staticPagesSection

import com.iat.Config
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.home.footerStaticPages.AboutEpointsPage
import com.iat.pages.points.TransitionPage
import com.iat.stepdefs.envVariables
import geb.Browser

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def aboutEpointsPage = new AboutEpointsPage()
def transitionPage = new TransitionPage()

def browser = new Browser()
def envVar = new envVariables()

// 1.1 //  -------------------------------------------------------------------------------------- About epoints page
// ---------------------------------------------------------------------------------------- What is epoints? section
// Update ---------------------------------------------------- EPOINTS - complete Angular for static com.iat.pages(NBO-1437)
Given(~/^About epoints page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.footerModule.clickAboutEpointsLink()
    at aboutEpointsPage
    aboutEpointsPage = page
}
When(~/^User click 'What is epoints\?' link$/) { ->
    aboutEpointsPage.clickWhatIsEpointsLink()
}
Then(~/^Page will be scrolled to 'What is epoints\?' section$/) { ->
    //leave empty
}
Then(~/^'What is epoints\?' section has proper content$/) { ->
    assert aboutEpointsPage.firstSectionHeader.text() == envVar.aboutEpointsFirstSectionHeader
    assert aboutEpointsPage.firstSectionPicture.isDisplayed()
    assert aboutEpointsPage.firstSectionText.text() == envVar.aboutEpointsFirstSectionText
    assert aboutEpointsPage.firstSectionBackToTopLink.isDisplayed()
}

// 1.2 //  -------------------------------------------------------------------------------------- About epoints page
// ---------------------------------------------------------------------------------- So much choice section content
// Update ---------------------------------------------------- EPOINTS - complete Angular for static com.iat.pages(NBO-1437)
When(~/^User click 'So much choice' link$/) { ->
    aboutEpointsPage.clickSoMuchChoiceLink()
}
Then(~/^Page will be scrolled to 'So much choice' section$/) { ->
    //leave empty
}
Then(~/^'So much choice' section has proper content$/) { ->
    waitFor { aboutEpointsPage.secondSectionRetailerCardBasic[0].isDisplayed() }
    assert aboutEpointsPage.secondSectionHeader.text() == envVar.aboutEpointsSecondSectionHeader
    assert aboutEpointsPage.secondSectionRetailerCardBasic.size() >= 5
    assert aboutEpointsPage.secondSectionText.text() == envVar.aboutEpointsSecondSectionText
    assert aboutEpointsPage.secondSectionPreviousArrow.isDisplayed()
    assert aboutEpointsPage.secondSectionNextArrow.isDisplayed()
    assert aboutEpointsPage.secondSectionBackToTopLink.isDisplayed()
}

// 1.3 //  -------------------------------------------------------------------------------------- About epoints page
// ---------------------------------------------------------------------------- So much choice section retailer link
// Update ---------------------------------------------------- EPOINTS - complete Angular for static com.iat.pages(NBO-1437)
Given(~/^About epoints page is opened by logged user$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
    epointsHomePage.footerModule.clickAboutEpointsLink()
    at aboutEpointsPage
    aboutEpointsPage = page
    waitFor { browser.title == envVar.aboutEpointsPageTitle }
    assert browser.title == envVar.aboutEpointsPageTitle
}
When(~/^User click one of retailer card$/) { ->
    int random = 0
    waitFor { aboutEpointsPage.secondSectionRetailerCardBasic[0].isDisplayed() }
    waitFor { !aboutEpointsPage.loader.isDisplayed() }
    while (!aboutEpointsPage.secondSectionRetailerCardBasic[random].isDisplayed())
        random = new Random().nextInt(aboutEpointsPage.secondSectionRetailerCardBasic.size())
    browser.withNewWindow({ aboutEpointsPage.clickChosenRetailerCard(random) }, close: true, wait: true) {
        at TransitionPage
        transitionPage = page
    }
}

// 1.4 //  -------------------------------------------------------------------------------------- About epoints page
// ------------------------------------------------------------------------------------- Hassle-free rewards content
// Update ---------------------------------------------------- EPOINTS - complete Angular for static com.iat.pages(NBO-1437)
When(~/^User click 'Hassle-free rewards' link$/) { ->
    aboutEpointsPage.clickHassleFreeRewardsLink()
}
Then(~/^Page will be scrolled to 'Hassle-free rewards' section$/) { ->
    //leave empty
}
Then(~/^'Hassle-free rewards' section has proper content$/) { ->
    assert aboutEpointsPage.thirdSectionHeader.text() == envVar.aboutEpointsThirdSectionHeader
    assert aboutEpointsPage.thirdSectionPicture.isDisplayed()
    assert aboutEpointsPage.thirdSectionText.text() == envVar.aboutEpointsThirdSectionText
    assert aboutEpointsPage.thirdSectionBackToTopLink.isDisplayed()
}

// 1.5 //  -------------------------------------------------------------------------------------- About epoints page
// ------------------------------------------------------------------------------------ Get involved section content
// Update ---------------------------------------------------- EPOINTS - complete Angular for static com.iat.pages(NBO-1437)
When(~/^User click 'Get involved' link$/) { ->
    aboutEpointsPage.clickGetInvolvedLink()
}
Then(~/^Page will be scrolled to 'Get involved' section$/) { ->
    //leave empty
}
Then(~/^'Get involved' section has proper content$/) { ->
    assert aboutEpointsPage.fourthSectionHeader.text() == envVar.aboutEpointsFourthSectionHeader
    assert aboutEpointsPage.fourthSectionText.text() == envVar.aboutEpointsFourthSectionText
    assert aboutEpointsPage.fourthSectionText2.text() == envVar.aboutEpointsFourthSectionText2
    assert aboutEpointsPage.fourthSectionBackToTopLink.isDisplayed()
    assert aboutEpointsPage.fourthSectionGetInTouchButton.isDisplayed()
    assert aboutEpointsPage.fourthSectionWatchLink.isDisplayed()
}

// 1.6 //  -------------------------------------------------------------------------------------- About epoints page
// ---------------------------------------------------------------------- Get involved section submit a request link
// Update ---------------------------------------------------- EPOINTS - complete Angular for static com.iat.pages(NBO-1437)
When(~/^User click get in touch button$/) { ->
    //browser.withNewWindow({ AboutEpointsPage.clickGetInTouchButton() }, close:true){ //TODO should be opened in new tab
    aboutEpointsPage.clickGetInTouchButton()
    waitFor { browser.currentUrl == envVar.epointsLink + envVar.contactUsURL }
    assert browser.currentUrl == envVar.epointsLink + envVar.contactUsURL
    //}
}
Then(~/^He will be redirected to submit a request page$/) { ->
    //done in previous step
}