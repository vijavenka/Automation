package com.iat.stepdefs.pointsSection

import com.iat.pages.home.EpointsHomePage
import com.iat.pages.points.GoInstorePage
import com.iat.stepdefs.envVariables

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def goInstorePage = new GoInstorePage()

def envVar = new envVariables()

// 1.1 //  ----------------------------------------- PARTNER PAGE - create partner page for BDL for desktop(NBO-344)
// ---------------------------------------------------------------------------------------------------- page content
Given(~/^Instore page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.headerModule.clickOnPointsButton()
    epointsHomePage.headerModule.clickPointsSubNavigationOption("Go in-store")
    at GoInstorePage
    goInstorePage = page
}
When(~/^user look on instore page$/) { ->
    //leave empty
}
Then(~/^He will see proper instore header text$/) { ->
    waitFor { goInstorePage.mainHeader.isDisplayed() }
    waitFor { goInstorePage.mainDescription.isDisplayed() }
    assert goInstorePage.mainHeader.text() == envVar.instoreMainHeader
    assert goInstorePage.mainDescription.text() == envVar.instoreMainDescription
}
Then(~/^Three instore images will be displayed$/) { ->
    waitFor { goInstorePage.firstSectionPicture.isDisplayed() }
    waitFor { goInstorePage.secondSectionPicture.isDisplayed() }
    waitFor { goInstorePage.thirdSectionPicture.isDisplayed() }
    assert goInstorePage.firstSectionPicture.isDisplayed()
    assert goInstorePage.secondSectionPicture.isDisplayed()
    assert goInstorePage.thirdSectionPicture.isDisplayed()
}
Then(~/^Three instore section descriptions will be displayed$/) { ->
    waitFor { goInstorePage.firstSectionHeader.isDisplayed() }
    waitFor { goInstorePage.firstSectionText.isDisplayed() }
    waitFor { goInstorePage.secondSectionHeader.isDisplayed() }
    waitFor { goInstorePage.secondSectionText.isDisplayed() }
    waitFor { goInstorePage.thirdSectionHeader.isDisplayed() }
    waitFor { goInstorePage.thirdSectionText.isDisplayed() }
    assert goInstorePage.firstSectionHeader.text() == envVar.instore1SectionHeader
    assert goInstorePage.firstSectionText.text() == envVar.instore1SectionDescription
    assert goInstorePage.secondSectionHeader.text() == envVar.instore2SectionHeader
    assert goInstorePage.secondSectionText.text().replace("\n", "") == envVar.instore2SectionDescription
    assert goInstorePage.thirdSectionHeader.text() == envVar.instore3SectionHeader
    assert goInstorePage.thirdSectionText.text() == envVar.instore3SectionDescription
}
Then(~/^Two app links will be displayed$/) { ->
    waitFor { goInstorePage.googlePlayLink.isDisplayed() }
    waitFor { goInstorePage.appStoreLink.isDisplayed() }
    assert goInstorePage.googlePlayLink.isDisplayed()
    assert goInstorePage.appStoreLink.isDisplayed()
}

// 1.2 //  ----------------------------------------- PARTNER PAGE - create partner page for BDL for desktop(NBO-344)
// ---------------------------------------------------------------------------------------------------- android link
When(~/^User click android app link$/) { ->
    browser.withNewWindow({ goInstorePage.clickGooglePlayLink() }, wait: true) {
        waitFor { browser.getTitle().contains('Vouchers, Discount Codes-bigDL – Aplikacje na Androida w Google Play') }
        assert browser.getTitle().contains('Vouchers, Discount Codes-bigDL – Aplikacje na Androida w Google Play')
    }
}
Then(~/^Google play store will be opened in new window$/) { ->
    //assertions done in previous step
}
// 1.3 //  ----------------------------------------- PARTNER PAGE - create partner page for BDL for desktop(NBO-344)
// -------------------------------------------------------------------------------------------------- app store link
When(~/^User click app store link$/) { ->
    browser.withNewWindow({ goInstorePage.clickAppStoreLink() }, wait: true) {
        waitFor {
            browser.getTitle() == 'Vouchers, Rewards, Deals - bigDL on the App Store'
        }
        assert browser.getTitle() == 'Vouchers, Rewards, Deals - bigDL on the App Store'
    }
}
Then(~/^iTunes will be opened in new window$/) { ->
    //assertions done in previous step
}

// 2.1 //  -------------------------------------------- EPOINTS DESKTOP - update the in-store partner page (NBO-752)
// ------------------------------------------------------------------------------------------------------ bigdl link
When(~/^User click bigdl text link$/) { ->
    browser.withNewWindow({ goInstorePage.clickBigdlLink() }, wait: true) {
        waitFor(15) { browser.currentUrl == envVar.bigdlPromoURL }
        assert browser.currentUrl == envVar.bigdlPromoURL
    }
}
Then(~/^bigDl page will be opened in new page$/) { ->
    //assertions done in previous step
}

// 2.2 //  -------------------------------------------- EPOINTS DESKTOP - update the in-store partner page (NBO-752)
// ----------------------------------------------------------------------------------------------- android text link
When(~/^User click android app text link$/) { ->
    browser.withNewWindow({ goInstorePage.clickGooglePlayTextLink() }, wait: true) {
        waitFor { browser.getTitle().contains('Vouchers, Discount Codes-bigDL – Aplikacje na Androida w Google Play') }
        assert browser.getTitle().contains('Vouchers, Discount Codes-bigDL – Aplikacje na Androida w Google Play')
    }
}

// 2.3 //  -------------------------------------------- EPOINTS DESKTOP - update the in-store partner page (NBO-752)
// ------------------------------------------------------------------------------------------------ app store text link
When(~/^User click app store text link$/) { ->
    browser.withNewWindow({ goInstorePage.clickAppStoreTextLink() }, wait: true) {
        waitFor {
            browser.getTitle() == 'Vouchers, Rewards, Deals - bigDL on the App Store'
        }
        assert browser.getTitle() == 'Vouchers, Rewards, Deals - bigDL on the App Store'
    }
}
