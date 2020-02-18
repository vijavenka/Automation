package com.iat.stepdefs.homePageNavigation

import com.iat.Config
import com.iat.pages.SignInPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.points.LeadRetailerPage
import com.iat.pages.points.PointsPage

//import com.iat.pages.prizesSection.PrizesPage
import com.iat.pages.rewards.EpointsRedemptionPage
import com.iat.repository.impl.SolrRepositoryImpl
import com.iat.stepdefs.envVariables
import com.iat.stepdefs.utils.DataExchanger
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

def browser = new Browser()
def envVar = new envVariables()
def epointsHomePage = new EpointsHomePage()
def epointsRedemptionPage = new EpointsRedemptionPage()
//def prizesPage = new PrizesPage()
def func = new Functions()

Given(~/^Epoints home page is opened by (.+?) (.+?) user with cookie panel (.+?)$/) { String userMainPartner, String loginState, String cookiePanelVisibility ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()

    if (cookiePanelVisibility.contains("not")) {
        epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    }

    if (!loginState.contains("not") || loginState.contains("identified") || loginState.contains("recognized")) {

        if (userMainPartner.contains("epoints")) {
            epointsHomePage.signInUserToEpoints(Config.epointsUser, Config.epointsUserPassword)
        }

        if (userMainPartner.contains("united")) {
            epointsHomePage.signInUserToEpoints(Config.unitedUser, Config.unitedUserPassword)
        }

        if (loginState.contains("identified") || loginState.contains("recognized")) {
            func.clearParticularCookieAndRefreshPage('SESSION')
        }
    }
}

Then(~/^Merchants section is available with correct heading and description$/) { ->
    waitFor { epointsHomePage.ourRetailersSectionBanner.text() == "Our retailers" }

    assertThat(epointsHomePage.retailerBlock.title, is("Earn epoints quickly and easily"))
    assertThat(epointsHomePage.retailerBlock.description, is("Simply find a retailer, click to their site and shop to earn epoints."))
    assertThat("seemore link should be displayed!", epointsHomePage.retailerBlock.seeMoreLink.isDisplayed())
    assertThat(epointsHomePage.retailerBlock.seeMoreLink.text().trim(), is("1500 more retailers"))
    assertThat(epointsHomePage.retailerBlock.seeMoreLink.@href, containsString("/points/online"))
    waitFor { epointsHomePage.retailerBlock.retailerCards.size() == 6 }
    assertThat(epointsHomePage.retailerBlock.retailerCards.size(), is(6))
}

Then(~/^Lead merchants section is available with correct heading and description$/) { ->
    waitFor { epointsHomePage.ourRetailersSectionBanner.text() == "Our retailers" }

    assertThat(epointsHomePage.retailerBlockSpecial.title, is("Special offers"))
    assertThat(epointsHomePage.retailerBlockSpecial.description, is("Quickly boost your epoints balance with our special offers."))
    assertThat("seemore link should be displayed!", epointsHomePage.retailerBlockSpecial.seeMoreLink.isDisplayed())
    assertThat(epointsHomePage.retailerBlockSpecial.seeMoreLink.text().trim(), is("more offers"))
    assertThat(epointsHomePage.retailerBlockSpecial.seeMoreLink.@href, containsString("/points/online/specials"))
    waitFor { epointsHomePage.retailerBlockSpecial.retailerCards.size() == 5 }
    assertThat(epointsHomePage.retailerBlockSpecial.retailerCards.size(), is(5))

    assertThat(epointsHomePage.retailerBlockSpecial.retailerCards*.specialText, everyItem(is("Earn up to")))
    assertThat(epointsHomePage.retailerBlockSpecial.retailerCards*.specialPoints, everyItem(is(greaterThan(0))))
}

//Then(~/^He will be redirected to prizes page$/) { ->
//    at PrizesPage
//    prizesPage = page
//}

Given(~/^Redemption offers block banner (.+?) displayed$/) { String displayed ->
    boolean isDisplayed = !displayed.contains("not")
    assertThat("Rewards section banner availability is incorrect", epointsHomePage.rewardsSectionBanner.isDisplayed(), is(isDisplayed))
    assertThat("Wrong rewards banner text", epointsHomePage.rewardsSectionBanner.text(), is("Find the perfect treat from the world's largest reward range"))
}

Then(~/^Redemption offers block is available with (\d+) products in it$/) { int redemptionNumber ->
    waitFor { epointsHomePage.redemptionsBlock.isDisplayed() }
    assertThat(epointsHomePage.redemptionsBlock.redemptions.size(), is(4))
    assertThat(epointsHomePage.redemptionsBlock.largerCards.size(), is(1))
}

Given(~/^How does it work block is available$/) { ->
    assertThat("How does it work section is not available", epointsHomePage.howDoesItWorkSection.isDisplayed())
    assertThat("How does it work title is not correct", epointsHomePage.howDoesItWorkSectionBanner.text(), is('How it works'))
    assertThat("How does it work question 1 is not correct", epointsHomePage.hdiwQuestions[0].text(), is('What are epoints?'))
    assertThat("How does it work question 2 is not correct", epointsHomePage.hdiwQuestions[1].text(), is("Isn't this just cashback?"))
    assertThat("How does it work question 3 is not correct", epointsHomePage.hdiwQuestions[2].text(), is("Won't it take ages to earn enough epoints to get a reward?"))
    assertThat("How does it work question 4 is not correct", epointsHomePage.hdiwQuestions[3].text(), is('What can I spend my epoints on?'))
    assertThat("Wrong number of \"steps\" elements", epointsHomePage.hdiwSteps.size(), is(2))
    assertThat("Join section is not available", epointsHomePage.hditJoinSection.isDisplayed())
}

Then(~/^Redemption offers block cards include fields: category, image, title, epointsValue, originalEpointsValue\(optional\), add to basket button$/) {
    ->

    def redemptionBlock = epointsHomePage.redemptionsBlock

    assert !redemptionBlock.getCategoryNames().contains("")
    assert !redemptionBlock.getImages()*.isDisplayed().contains(false)
    assert !redemptionBlock.getTitles().contains("")
    assert !redemptionBlock.getCosts().contains("")
    assert !redemptionBlock.getAddToBasketButtons()*.isDisplayed().contains(false)
}

Then(~/^Redemption offers "([^"]*)" link is redirecting user$/) { String linkLabel ->
    epointsHomePage.clickRedemptionOffersSeeMoreLink()
    assert browser.getCurrentUrl().contains("/rewards")
}

def redemptionTitle
When(~/^User clicks on chosen redemption card$/) { ->
    def random = func.returnRandomValue(epointsHomePage.redemptionsBlock.redemptions.size())
    redemptionTitle = epointsHomePage.redemptionsBlock.redemptions[random].title
    epointsHomePage.clickRandomRedemptionCard(random)
}

Then(~/^He will be redirected to single redemption page of selected redemption$/) { ->
    at EpointsRedemptionPage
    epointsRedemptionPage = page
    assert epointsRedemptionPage.header.text() == redemptionTitle
}

When(~/^User looks on top of page$/) { ->
    //leave empty
}
Then(~/^He will see cookie panel with proper content$/) { ->
    waitFor { epointsHomePage.cookiePanelModule.cookiePanel.isDisplayed() }
    assert epointsHomePage.cookiePanelModule.cookiePanelText.text().replace("\n", "").replace(" ", "") == envVar.cookiePanelText.replace(" ", "")
    assert epointsHomePage.cookiePanelModule.cookiePanelAcceptButton.isDisplayed()
    assert epointsHomePage.cookiePanelModule.cookiePanelFindOutMoreButton.isDisplayed()
    assert epointsHomePage.cookiePanelModule.cookiePanelCloseButton.isDisplayed()
}

When(~/^User click accept button on cookie panel$/) { ->
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
}
Then(~/^Cookie panel will be closed$/) { ->
    waitFor { !epointsHomePage.cookiePanelModule.cookiePanel.isDisplayed() }
    assert !epointsHomePage.cookiePanelModule.cookiePanel.isDisplayed()
}

When(~/^User click find out more button$/) { ->
    epointsHomePage.cookiePanelModule.clickFindOutMoreButtonOnCookiePanel()
}
Then(~/^He will be redirected to cookie policy page$/) { ->
    waitFor { browser.currentUrl == envVar.epointsLink + envVar.cookiePolicyURL }
    waitFor { browser.title == envVar.cookiePolicyPageTitle }
    assert browser.currentUrl == envVar.epointsLink + envVar.cookiePolicyURL
    assert browser.title == envVar.cookiePolicyPageTitle
}
Then(~/^([^"]*) merchant ([^"]*) redirected to transition$/) { String merchantName, String willBe ->
    if (willBe.contains("not"))
        assertThat(epointsHomePage.retailerBlock.retailerCard(merchantName).url, containsString("/points/"))
    else
        assertThat(epointsHomePage.retailerBlock.retailerCard(merchantName).url, containsString("/transition?type=merchant"))
}
Then(~/^All the lead merchants will be redirected to their details page$/) { ->
    assertThat(epointsHomePage.retailerBlockSpecial.retailerCards*.url, everyItem(containsString("/points/")))
}
Then(~/^([^"]*) home page is opened$/) { String partner ->
    at EpointsHomePage
    epointsHomePage = page
    if (partner.toLowerCase() == "united") {
        assertThat("Invalid current account!", epointsHomePage.accountSwitcher.currentAccount, is("United"))
        assertThat("Invalid current page!", browser.getCurrentUrl(), endsWith("epoints.com/united"))
    } else if (partner.toLowerCase() == "epoints") {
        assertThat("Invalid current account!", epointsHomePage.accountSwitcher.currentAccount, is("epoints"))
        assertThat("Invalid current page!", browser.getCurrentUrl(), endsWith("epoints.com/"))
    }
}
Then(~/^([^"]*) header options are displayed$/) { String partner ->
    if (partner.toLowerCase() == "united") {
        assertThat("Invalid home button url!", epointsHomePage.headerModule.headerHomeButton.@href, endsWith("epoints.com/united"))
        //assertThat("Invalid points button url!", epointsHomePage.headerModule.headerPointsButton.@href, endsWith("epoints.com/united/points/online"))
        assertThat("Invalid reward button url!", epointsHomePage.headerModule.headerRewardsSectionButton.@href, endsWith("epoints.com/united/rewards"))
        assertThat("Invalid rewards button text!", epointsHomePage.headerModule.headerRewardsSectionButton.text().trim(), is("United rewards"))
    } else if (partner.toLowerCase() == "epoints") {
        assertThat("Invalid home button url!", epointsHomePage.headerModule.headerHomeButton.@href, endsWith("epoints.com/"))
        assertThat("Invalid points button url!", epointsHomePage.headerModule.headerPointsButton.@href, endsWith("epoints.com/points/online"))
        assertThat("Invalid reward button url!", epointsHomePage.headerModule.headerRewardsSectionButton.@href, endsWith("epoints.com/rewards"))
        assertThat("Invalid rewards button text!", epointsHomePage.headerModule.headerRewardsSectionButton.text().trim(), is("Rewards"))
    }
}
Then(~/^([^"]*) logo is displayed$/) { String partner ->
    assertThat(epointsHomePage.headerModule.logoLink, is(epointsHomePage.headerModule.headerHomeButton.@href))
//    if (partner.toLowerCase() == "united")
//        assertThat("Invalid logo text!", epointsHomePage.headerModule.logoText, is("United"))
    if (partner.toLowerCase() == "epoints")
        assertThat("Epoints logo should have no text!", epointsHomePage.headerModule.logoText, isEmptyOrNullString())
}
When(~/^User switch to ([^"]*) account$/) { String partner ->
    if (partner.toLowerCase() == "united") {
        epointsHomePage.accountSwitcher.unitedSwitcher.click()
        waitFor { epointsHomePage.accountSwitcher.currentAccount == "United" }
    } else if (partner.toLowerCase() == "epoints") {
        epointsHomePage.accountSwitcher.epointsSwitcher.click()
        waitFor { epointsHomePage.accountSwitcher.currentAccount == "epoints" }
    }
    sleep(1000)
}
When(~/^User opens subpage ([^"]*)$/) { String url ->
    if (url.contains('$REWARD')) {
        if (url.contains("united"))
            url = new SolrRepositoryImpl().getUrlOfAnyProduct("UNITED")
        else
            url = new SolrRepositoryImpl().getUrlOfAnyProduct("")
        DataExchanger.getInstance().setProductUrl(url)
    }
    go url
    sleep(1000)
}
Then(~/^After submitting ([^"]*) login data the subpage ([^"]*) is opened$/) { String partner, String subUrl ->
    String currentUrl = browser.currentUrl
    SignInPage signInPage = page
    switch (partner.toLowerCase()) {
        case "united":
            signInPage.enterLoginDataLoginPage(Config.unitedUser, Config.unitedUserPassword)
            break
        case "epoints":
        case "proper":
            signInPage.enterLoginDataLoginPage(Config.epointsUser, Config.epointsUserPassword)
            break
    }
    signInPage.clickSignInButtonLoginPage()

    sleep(1000)
    waitFor { browser.currentUrl != currentUrl }
    if (subUrl.contains('$REWARD')) subUrl = DataExchanger.getInstance().getProductUrl()
    assertThat(browser.currentUrl, is(browser.baseUrl + subUrl))
}
Then(~/^User is redirect to the subpage ([^"]*)$/) { String subUrl ->
    sleep(1000)
    if (subUrl.contains('$REWARD')) subUrl = DataExchanger.getInstance().getProductUrl()
    assertThat(browser.currentUrl, is(browser.baseUrl + subUrl))
}
Then(~/^Home page's banner is available with correct carousel elements$/) { ->
    assertThat("All carosuel elements should be available!", epointsHomePage.carouselBanner.isDisplayed())
}
Then(~/^Banner's carousel can be navigated via (.+?)$/) { String navMethod ->
    def carousel = epointsHomePage.carouselBanner
    int howManyBanners = carousel.size()

    switch (navMethod) {
        case "arrows":
            //selecting first banner to stop autoplay
            carousel.selectBanner(0)
            for (int i = 1; i < howManyBanners; i++) {
                carousel.rightArrow.click()
                waitFor { carousel.whichDotIsBigger() == i }
                assertThat("Incorrect banner is displayed!", carousel.whichBannerIsActive(), is(i))
            }
            //check if clicking right arrow on last banner will move user to first banner
            carousel.rightArrow.click()
            waitFor { carousel.whichDotIsBigger() == 0 }
            assertThat("Incorrect banner is displayed!", carousel.whichBannerIsActive(), is(0))
            //check if clicking left arrow on first banner will move user to last banner
            carousel.leftArrow.click()
            waitFor { carousel.whichDotIsBigger() == howManyBanners - 1 }
            assertThat("Incorrect banner is displayed!", carousel.whichBannerIsActive(), is(howManyBanners - 1))
            for (int i = howManyBanners - 2; i > -1; i--) {
                carousel.leftArrow.click()
                waitFor { carousel.whichDotIsBigger() == i }
                assertThat("Incorrect banner is displayed!", carousel.whichBannerIsActive(), is(i))
            }
            break
        case "dots":
            howManyBanners.times {
                carousel.selectBanner(it)
                assertThat("Incorrect banner is displayed!", carousel.whichBannerIsActive(), is(it))
            }
            //some random clicks
            20.times {
                int index = carousel.selectRandomBanner(howManyBanners)
                assertThat("Incorrect banner is displayed!", carousel.whichBannerIsActive(), is(index))
            }
            break
    }
}

When(~/^User clicks on chosen merchant card$/) { ->
    sleep(1000)
    waitFor { epointsHomePage.retailerBlock.retailerCards[0].isDisplayed() }
    epointsHomePage.retailerBlock.retailerCards[0].click()
}

When(~/^User clicks on "1 500 more retailers" link$/) { ->
    epointsHomePage.retailerBlock.clickSeeMoreLink()
}

When(~/^User click on chosen lead merchant card$/) { ->
    sleep(1000)
    waitFor { epointsHomePage.retailerBlockSpecial.retailerCards[0].isDisplayed() }
    epointsHomePage.retailerBlockSpecial.retailerCards[0].click()
}

Then(~/^He will be redirected to lead merchant page regardless of login state$/) { ->
    at LeadRetailerPage
}

Then(~/^He will be redirected to \/points\/online page$/) { ->
   at PointsPage
}