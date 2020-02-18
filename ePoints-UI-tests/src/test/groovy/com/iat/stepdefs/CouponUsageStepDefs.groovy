package com.iat.stepdefs

import com.iat.Config
import com.iat.pages.CouponUsagePage
import com.iat.pages.FourOFourPage
import com.iat.pages.home.EpointsHomePage
import com.iat.stepdefs.utils.Functions
import geb.Browser

import static cucumber.api.groovy.EN.*

CouponUsagePage couponUsagePage = new CouponUsagePage()
FourOFourPage forOFourPage = new FourOFourPage()

def epointsHomePage = new EpointsHomePage()
def envVar = new envVariables()
def browser = new Browser()
def func = new Functions()

// 1.1 //  -------------------------------------------------------- RETAILER CASH IN PAGE - required login functionality
// ------------------------------------------------------------------------------------- page opened by not allowed user
Given(~/^User with a certain coupon usage access is signed in to epoints$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    func.clearCookiesAndStorage()
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.signInUserToEpoints(Config.epointsCouponUsageUser, Config.epointsCouponUsageUserPassword)
}
When(~/^Not 'News project group' member tries open 'coupon usage' page$/) { ->
    go('/coupon-usage')
}
Then(~/^User will be moved to '404' page$/) { ->
    waitFor { at FourOFourPage }
}

// 1.2 //  -------------------------------------------------------- RETAILER CASH IN PAGE - required login functionality
/// ---------------------------------------------------------------------------------------- page opened by allowed user
When(~/^'News project group' member tries open 'coupon usage' page$/) { ->
    go('/coupon-usage')
}
Then(~/^User will be moved to 'coupon usage' page$/) { ->
    at CouponUsagePage
}
Then(~/^It contains cashed points statistics$/) { ->

}
Then(~/^It contains 'cash out' module$/) { ->

}
Then(~/^It contains coupons redemption breakdown table wit columns '(.+?)'$/) { String columnNames ->

}
Then(~/^It contains pagination module$/) { ->

}