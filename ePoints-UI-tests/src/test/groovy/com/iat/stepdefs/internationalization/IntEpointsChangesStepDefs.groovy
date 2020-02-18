package com.iat.stepdefs.internationalization

import com.iat.Config
import com.iat.pages.FourOFourPage
import com.iat.pages.SignInPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.home.footerStaticPages.AboutEpointsPage
import com.iat.pages.userAccount.myEpoints.EpointsMyAccountPage
import com.iat.pages.userAccount.profile.ProfilePage
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.*

EpointsHomePage epointsHomePage = new EpointsHomePage()
SignInPage signInPage = new SignInPage()
EpointsMyAccountPage epointsMyAccountPage = new EpointsMyAccountPage()
FourOFourPage fourOFourPage = new FourOFourPage()
AboutEpointsPage aboutEpointsPage = new AboutEpointsPage()
ProfilePage profilePage = new ProfilePage()

def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

String[] availableRegionsURLQA = ["https://se-" + Config.testEnvironment + ".epoints.com/", "https://fi-" + Config.testEnvironment + ".epoints.com/", "https://dk-" + Config.testEnvironment + ".epoints.com/", "https://nl-" + Config.testEnvironment + ".epoints.com/", "https://no-" + Config.testEnvironment + ".epoints.com/"]
def random

// 1.1 //  ----------- EPOINTS INT - remove sign in by Facebook from the sign in UI for non-uk sub-domains(NBO-3247)
// -------------------------------------------------------------- EPOINTS INT - remove Join option from UI(NBO-3248)
// -------------------------------------------------------- check if buttons was properly removed from sign in popup
Given(~/^ePoints page is opened in one of new regions$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    //only one region is supported right now
    random = 3//func.returnRandomValue(availableRegionsURLQA.size())
    browser.go(availableRegionsURLQA[random])
}
Then(~/^Facebook sign in option is not available in new regions on sign in popup$/) { ->
    assert !epointsHomePage.signInModule.signInWithFacebookButton.isDisplayed()
}
Then(~/^Join option is not available in new regions on sign in popup$/) { ->
    assert !epointsHomePage.signInModule.joinHereLink.isDisplayed()
}

// 1.2 //  ----------- EPOINTS INT - remove sign in by Facebook from the sign in UI for non-uk sub-domains(NBO-3247)
// ---------------------------------------------------------- check if button was properly removed from sign in page
Given(~/^ePoints page is opened in one of new regions by identified user$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    //only one region is supported right now
    random = 3//func.returnRandomValue(availableRegionsURLQA.size())
    browser.go(availableRegionsURLQA[random])
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
    func.clearParticularCookieAndRefreshPage('SESSION')
}
When(~/^User look on opened page$/) { ->
    sleep(2000)
}
Then(~/^He will see that facebook sign in option is not available in new regions on sign in page$/) { ->
    signInPage = page
    assert !signInPage.loginPageSignInWithFacebookButton.isDisplayed()
}

// 2.1 //  ------------------------------------------------------ EPOINTS INT - remove Join option from UI(NBO-3248)
// -------------------------------------------------------------- check if jon was removed from header and home page
Then(~/^He cannot see join option in header because it is not available in regions different than UK$/) { ->
    assert !epointsHomePage.headerModule.headerJoinButton.isDisplayed()
}
Then(~/^He cannot see join option on home page because it is not available in regions different than UK, only sign in form$/) {
    ->
    assert epointsHomePage.newRegionsSignInButton.isDisplayed()
    assert epointsHomePage.newRegionsSignInEmailInputField.isDisplayed()
    assert epointsHomePage.newRegionsSignInPasswordInputField.isDisplayed()
    assert epointsHomePage.newRegionsJoinLink.isDisplayed()
}

// 2.2 //  ------------------------------------------------------ EPOINTS INT - remove Join option from UI(NBO-3248)
// ---------------------------------------------------------------- check if jon page is not available by direct url
When(~/^User try to reach jon page by using \/join url$/) { ->
    browser.go(availableRegionsURLQA[random] + envVar.joinURL)
}
Then(~/^He will be redirected to 404 page$/) { ->
    at FourOFourPage
    fourOFourPage = page
}

// 3.1 //  ------ EPOINTS INT - remove Facebook and Twitter options from the footer for non-uk sub-domains(NBO-3250)
// ----------------------------------------------------------------------- check if widgets were removed from footer
Then(~/^He cannot see facebook and twitter widgets because they are not available in regions different than UK$/) { ->
    assert !epointsHomePage.footerModule.facebookFooterWidget.isDisplayed()
    assert !epointsHomePage.footerModule.twitterFooterWidget.isDisplayed()
}

// 4.1 //  ------ EPOINTS INT - remove get section from non-UK sub-domain sites on both desktop and mobile(NBO-3251)
// ------------------------------------------------------------------ check if Points option was removed from header
Then(~/^He cannot see Points link because it is not available in regions different than UK$/) { ->
    assert !epointsHomePage.headerModule.headerPointsButton.isDisplayed()
}

// 4.2 //  ------ EPOINTS INT - remove get section from non-UK sub-domain sites on both desktop and mobile(NBO-3251)
// ----------------------------------------------- check if /get page and any subpage cannot be opened by direct url
When(~/^User try to open points page or any other of not available subpages$/) { ->
    String[] getPageLinks = [availableRegionsURLQA[random] + envVar.shopOnlineURL, availableRegionsURLQA[random] + envVar.playGamesURL, availableRegionsURLQA[random] + envVar.watchVideosURL, availableRegionsURLQA[random] + envVar.goInStoreURL, availableRegionsURLQA[random] + envVar.inviteFriendURL]
    for (int i = 0; i < getPageLinks.size(); i++) {
        browser.go(getPageLinks[i])
        sleep(1000)
        at FourOFourPage
    }
}
Then(~/^Each time he will be redirected to 404 page$/) { ->
    //all assertions done on previous page
}

// 5.1 //  ----------------- EPOINTS INT - remove partner page link and Ui from non-uk sub domain requests(NBO-3253)
// ---------------------------------------------------------------- check if partner link is not available in footer
Then(~/^He cannot see partner link in footer because it is not available in regions different than UK$/) { ->
    assert !epointsHomePage.footerModule.recognitionAndRewards.isDisplayed()
}

// 6.1 //  -- EPOINTS INT - for non-uk sub-domains remove the unwanted functionality from the account area(NBO-3253)
// ---------------------------------------------------------------- check if user account area elements were removed
Given(~/^My epoints page is opened in one of new regions$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    random = 3//func.returnRandomValue(availableRegionsURLQA.size())
    browser.go(availableRegionsURLQA[random])
    func.clearCookiesAndStorage()
    epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
    epointsHomePage.headerModule.clickOnUserNameLabel() //to close menu and make below function generic
    epointsHomePage.goToUserMyAccountPage()
    at EpointsMyAccountPage
    epointsMyAccountPage = page
}
Then(~/^He cannot see get section link because it is not available in regions different than UK$/) { ->
    assert !epointsMyAccountPage.userPanelYourTopSitesText.isDisplayed()
    assert !epointsMyAccountPage.userPanelYourTopSitesElemntTextBasic.isDisplayed()
    assert !epointsMyAccountPage.userPanelYourTopSitesElementValueBasic.isDisplayed()
}
Then(~/^He cannot see you top sites section because it is not available in regions different than UK$/) { ->
    assert !epointsMyAccountPage.whereGotEpointsTable.isDisplayed()
}
Then(~/^He cannot see buy epoints link because it is not available in regions different than UK$/) { ->
    assert epointsMyAccountPage.userProfileMenuModule.buyEpointsSubheaderOption.text().equals("header.account-sub.ecards")
    // it is last option without translation, if buy epoints available this will fail
}

// 6.2 //  -- EPOINTS INT - for non-uk sub-domains remove the unwanted functionality from the account area(NBO-3253)
// ------------------------------------------------------- check if /buy-epoints page cannot be opened by direct url
When(~/^User try to reach purchase epoints page by using \/buy-epoints url$/) { ->
    browser.go(availableRegionsURLQA[random] + envVar.purchaseEpointsURL)
}

// 7.1 //  - EPOINTS INT - use a different About epoints page format for non-uk sub domain users on desktop and mobile(NBO-3254)
// ------------------------------------------------------------------- check content of about us page of each region

Given(~/^About us page of chosen region '(.+?)' is opened$/) { String region ->
    to EpointsHomePage
    epointsHomePage = page
    if (region == 'NL') {
        browser.go(availableRegionsURLQA[3] + envVar.aboutEpointsURL)
    } else if (region == 'SE') {
        browser.go(availableRegionsURLQA[0] + envVar.aboutEpointsURL)
    } else if (region == 'DK') {
        browser.go(availableRegionsURLQA[2] + envVar.aboutEpointsURL)
    } else if (region == 'FI') {
        browser.go(availableRegionsURLQA[1] + envVar.aboutEpointsURL)
    } else if (region == 'NO') {
        browser.go(availableRegionsURLQA[4] + envVar.aboutEpointsURL)
    }
}
Then(~/^He will see that about us page of chosen region '(.+?)' has proper content$/) { String region ->
    at AboutEpointsPage
    aboutEpointsPage = page
    if (region == 'NL') {

    } else if (region == 'SE') {

    } else if (region == 'DK') {

    } else if (region == 'FI') {

    } else if (region == 'NO') {

    }
}

// 9.1 //  -- EPOINTS INT - for non-uk sub-domains remove the unwanted functionality from the account area(NBO-3253)
// --------------------------------------------------------------- check if proper user account sections were hidden
Given(~/^Profile page is opened in one of new regions$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    random = 3//func.returnRandomValue(availableRegionsURLQA.size())
    browser.go(availableRegionsURLQA[random])
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
    epointsHomePage.headerModule.clickOnUserNameLabel() //to close menu and make below function generic
    epointsHomePage.goToUserProfilePage(false)
    at ProfilePage
    profilePage = page
}
Then(~/^He cannot see edit email button because it is not available in regions different than UK$/) { ->
    assert !profilePage.emailEditCancelButton.isDisplayed()
}
Then(~/^He cannot see personal details section because it is not available in regions different than UK$/) { ->
    assert !profilePage.changePersonalDetailsBlock.isDisplayed()
}
Then(~/^He cannot see contact details section because it is not available in regions different than UK$/) { ->
    assert !profilePage.changeContactDetailsBlock.isDisplayed()
}
Then(~/^He can see change your password section$/) { ->
    assert profilePage.changePasswordBlock.isDisplayed()
    assert profilePage.passwordEditCancelButton.isDisplayed()
}

// 10.1 //  ------------- EPOINTS INT - create specific home page format for non-uk sub-domains on DESKTOP(NBO-3397)
// ----------------------------------------------------------------------------------- new regions home page content
Then(~/^He will see sign in module with email and password input fields$/) { ->
    waitFor { epointsHomePage.newRegionsSignInButton.isDisplayed() }
    assert epointsHomePage.newRegionsSignInButton.isDisplayed()
    assert epointsHomePage.newRegionsSignInEmailInputField.isDisplayed()
    assert epointsHomePage.newRegionsSignInPasswordInputField.isDisplayed()
    assert epointsHomePage.newRegionsJoinLink.isDisplayed()
}
Then(~/^He will see currency image section$/) { ->
    waitFor { epointsHomePage.newRegionsCurrecnySection.isDisplayed() }
    assert epointsHomePage.newRegionsCurrecnySection.isDisplayed()
}
Then(~/^He will see catalogue image section$/) { ->
    waitFor { epointsHomePage.newRegionCatalogueSection.isDisplayed() }
    assert epointsHomePage.newRegionCatalogueSection.isDisplayed()
}
Then(~/^He will see spend image section$/) { ->
    waitFor { epointsHomePage.newRegionSpendSection.isDisplayed() }
    assert epointsHomePage.newRegionSpendSection.isDisplayed()
}