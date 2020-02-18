package com.iat.stepdefs.homePageNavigation

import com.iat.Config
import com.iat.pages.JoinConfirmationPage
import com.iat.pages.SignInPage
import com.iat.pages.home.EpointsHomePage
import com.iat.pages.home.UnitedJoinLandingPage
import com.iat.stepdefs.utils.DataExchanger
import com.iat.stepdefs.utils.Functions

import static cucumber.api.groovy.EN.*
import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.*

def epointsHomePage = new EpointsHomePage()
def unitedHomeJoinPage = new UnitedJoinLandingPage()
def func = new Functions()
def DataExchanger dataExchanger = DataExchanger.getInstance()
def SignInPage signInPage
def JoinConfirmationPage joinConfirmationPage = new JoinConfirmationPage()

String newAccountEmailAddress, unitedId

Given(~/^Epoints united join home page is opened by not logged user$/) { ->
    to UnitedJoinLandingPage
    unitedHomeJoinPage = page
    func.clearCookiesAndStorage()
}

Given(~/^United join page contains header with correct options$/) { ->
    waitFor { unitedHomeJoinPage.epointsLogo.isDisplayed() }
    assertThat("Missing join header button", unitedHomeJoinPage.joinButton.isDisplayed())
    assertThat("Missing sign in header button", unitedHomeJoinPage.signInButton.isDisplayed())
}

Then(~/^Epoints united introduction section is available$/) { ->
    waitFor { unitedHomeJoinPage.descriptionSectionBlockWithImages.isDisplayed() }
    assertThat("Missing home page image in description section", unitedHomeJoinPage.descriptionSectionBlockImage.size(), is(2))
    assertThat("Wrong first image description", unitedHomeJoinPage.descriptionSectionBlockDescription[0].text(), is("Earn epoints for purchasing incentivised products at United Wholesale depots"))
    assertThat("Wrong second image description", unitedHomeJoinPage.descriptionSectionBlockDescription[1].text(), is("Spend epoints on a bespoke reward range from shop fitting specialist Vertex"))

    assertThat("Missing faq q 1", unitedHomeJoinPage.descriptionSectionFaq.text(), containsString("What are epoints?"))
    assertThat("Missing faq q 2", unitedHomeJoinPage.descriptionSectionFaq.text(), containsString("How can I collect epoints in United Wholesale depots?"))
    assertThat("Missing faq q 3", unitedHomeJoinPage.descriptionSectionFaq.text(), containsString("Won't it take ages to earn enough epoints to get a reward?"))
    assertThat("Missing faq q 4", unitedHomeJoinPage.descriptionSectionFaq.text(), containsString("What can I spend my epoints on?"))

}

Given(~/^Brands section is available with (\d+) ot clickable brands in it$/) { int arg1 ->
    waitFor { unitedHomeJoinPage.promotedBrandSectionDescription.isDisplayed() }
    assertThat("Missing brands section title", unitedHomeJoinPage.promotedBrandSectionDescription.text(), containsString("Shop as you normally would and you'll collect epoints in depot"))
    assertThat("Missing brands bar title", unitedHomeJoinPage.promotedBrandsSectionTitle.text(), containsString("Leading participating brands include..."))
    assertThat("Wrong number of displayed brand images!", unitedHomeJoinPage.promotedBrandImage.size(), is(6))
}

Given(~/^Redemption offers block is available with (\d+) not clickable products in it$/) { int brandsNumber ->
    assertThat(unitedHomeJoinPage.redemptionsBlock.size(), is(5))
    assertThat(unitedHomeJoinPage.redemptionsBlock.largerCards.size(), is(1))

    def redemptionBlock = unitedHomeJoinPage.redemptionsBlock

    assertThat(redemptionBlock.getCategoryNames(), everyItem(not(isEmptyOrNullString())))
    assert !redemptionBlock.getImages()*.isDisplayed().contains(false)
    assertThat(redemptionBlock.getTitles(), everyItem(not(isEmptyOrNullString())))
    assertThat(redemptionBlock.getCosts(), everyItem(not(isEmptyOrNullString())))
    assert !redemptionBlock.getAddToBasketButtons()*.isDisplayed().contains(true)
}

Given(~/^United join page contains footer with correct options$/) { ->
    unitedHomeJoinPage.footerModule.checkAvailabilityOfFooterElements()
}

When(~/^User click sign in header option$/) { ->
    unitedHomeJoinPage.clickSignInButton()
    at SignInPage
    signInPage = page
}

When(~/^User sign in with credentials '(.+?)', '(.+?)'$/) { String email, String password ->
    if (email == "unitedUser") {
        email = Config.unitedUser
        password = Config.unitedUserPassword
    }
    signInPage.signInWithCredentials(email, password)
}

When(~/^User provide united email address '(.+?)' into email input field$/) { String email ->
    newAccountEmailAddress = func.returnEpochOfCurrentDay()
    if (email == "random")
        newAccountEmailAddress = "newautomatedtestaccount$newAccountEmailAddress@gmail.com"
    else if (email == "unitedUser")
        newAccountEmailAddress = Config.unitedUser
    else
        newAccountEmailAddress = email
    dataExchanger.setEmail(newAccountEmailAddress)
    unitedHomeJoinPage.joinSectionTop.enterEmail(newAccountEmailAddress)
}

When(~/^User provide united id '(.+?)' into id input field$/) { String externalId ->
    unitedId = externalId
    if (externalId == "random")
        unitedId = func.returnRandomValue(7000, 7999)
    unitedHomeJoinPage.joinSectionTop.enterUnitedId(unitedId)
}

When(~/^User clicks "Join now" button$/) { ->
    sleep(1000)
    unitedHomeJoinPage.joinSectionTop.clickJoinButton()
}

Then(~/^Joined user is in (.+?) perspective$/) { String partner ->
    at JoinConfirmationPage
    joinConfirmationPage = page
    assertThat(joinConfirmationPage.headerModule.headerUserNameLabel.text(), containsString(newAccountEmailAddress))

    if (partner.toLowerCase() == "united") {
        assertThat("Invalid home button url!", joinConfirmationPage.headerModule.headerHomeButton.@href, endsWith("epoints.com/united"))
        assertThat("Invalid points button url!", joinConfirmationPage.headerModule.headerPointsButton.@href, endsWith("epoints.com/united/points"))
        assertThat("Invalid reward button url!", joinConfirmationPage.headerModule.headerRewardsSectionButton.@href, endsWith("epoints.com/united/rewards"))
        assertThat("Invalid rewards button text!", joinConfirmationPage.headerModule.headerRewardsSectionButton.text().trim(), is("United rewards"))
    } else if (partner.toLowerCase() == "epoints") {
        assertThat("Invalid home button url!", joinConfirmationPage.headerModule.headerHomeButton.@href, endsWith("epoints.com/"))
        assertThat("Invalid points button url!", joinConfirmationPage.headerModule.headerPointsButton.@href, endsWith("epoints.com/points/online"))
        assertThat("Invalid reward button url!", joinConfirmationPage.headerModule.headerRewardsSectionButton.@href, endsWith("epoints.com/rewards"))
        assertThat("Invalid rewards button text!", joinConfirmationPage.headerModule.headerRewardsSectionButton.text().trim(), is("Rewards"))
    }

    joinConfirmationPage.headerModule.logo.click()
    at EpointsHomePage
    epointsHomePage = page

    if (partner.toLowerCase() == "united")
        assertThat(epointsHomePage.accountSwitcher.currentAccount, is("United"))
    else if (partner.toLowerCase() == "epoints")
        assertThat(epointsHomePage.accountSwitcher.currentAccount, is("epoints"))
}

Then(~/^Information about (.+?) will be displayed$/) { String alertType ->
    //TODO some problem with 'duplicated email' scenario right now we are redirected to confirmation page....
    waitFor {
        unitedHomeJoinPage.joinSectionTop.alert.isDisplayed()
        !unitedHomeJoinPage.joinSectionTop.alert.text().isEmpty()
    }
    sleep(1000)
    if (alertType == 'duplicated email')
        assertThat("Invalid alert!", unitedHomeJoinPage.joinSectionTop.alert.text(), is("User already belongs to United but with different United ID and it is inactive"))
    else if (alertType == 'wrong external id format')
        assertThat("Invalid alert!", unitedHomeJoinPage.joinSectionTop.alert.text(), is("Parameter United ID has to be value in range [1-99999]"))
}