package stepdefs.EpointsStepDefs.getSection
import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-01-29.
 */

def epoints = new epointsPage()
def envVar = new envVariables()
def func = new Functions()
def browser = new Browser()

    // 1.1 //  ----------------------------------------- PARTNER PAGE - create partner page for BDL for desktop(NBO-344)
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Instore page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.clickGetButton()
        epoints.clickGoInStoreButtonAngular()
    }
    When(~/^user look on instore page$/) { ->
        //leave empty
    }
    Then(~/^He will see proper instore header text$/) { ->
        waitFor{ epoints.goInstorePage.mainHeader.isDisplayed() }
        waitFor{ epoints.goInstorePage.mainDescription.isDisplayed() }
        assert epoints.goInstorePage.mainHeader.text() == envVar.instoreMainHeader
        assert epoints.goInstorePage.mainDescription.text() == envVar.instoreMainDescription
    }
    Then(~/^Three instore images will be displayed$/) { ->
        waitFor{ epoints.goInstorePage.firstSectionPicture.isDisplayed() }
        waitFor{ epoints.goInstorePage.secondSectionPicture.isDisplayed() }
        waitFor{ epoints.goInstorePage.thirdSectionPicture.isDisplayed() }
        assert epoints.goInstorePage.firstSectionPicture.isDisplayed()
        assert epoints.goInstorePage.secondSectionPicture.isDisplayed()
        assert epoints.goInstorePage.thirdSectionPicture.isDisplayed()
    }
    Then(~/^Three instore section descriptions will be displayed$/) { ->
        waitFor{ epoints.goInstorePage.firstSectionHeader.isDisplayed() }
        waitFor{ epoints.goInstorePage.firstSectionText.isDisplayed() }
        waitFor{ epoints.goInstorePage.secondSectionHeader.isDisplayed() }
        waitFor{ epoints.goInstorePage.secondSectionText.isDisplayed() }
        waitFor{ epoints.goInstorePage.thirdSectionHeader.isDisplayed() }
        waitFor{ epoints.goInstorePage.thirdSectionText.isDisplayed() }
        assert epoints.goInstorePage.firstSectionHeader.text() == envVar.instore1SectionHeader
        assert epoints.goInstorePage.firstSectionText.text() == envVar.instore1SectionDescription
        assert epoints.goInstorePage.secondSectionHeader.text() == envVar.instore2SectionHeader
        assert epoints.goInstorePage.secondSectionText.text().replace("\n", "") == envVar.instore2SectionDescription
        assert epoints.goInstorePage.thirdSectionHeader.text() == envVar.instore3SectionHeader
        assert epoints.goInstorePage.thirdSectionText.text() == envVar.instore3SectionDescription
    }
    Then(~/^Two app links will be displayed$/) { ->
        waitFor{ epoints.goInstorePage.googlePlayLink.isDisplayed() }
        waitFor{ epoints.goInstorePage.appStoreLink.isDisplayed() }
        assert epoints.goInstorePage.googlePlayLink.isDisplayed()
        assert epoints.goInstorePage.appStoreLink.isDisplayed()
    }

    // 1.2 //  ----------------------------------------- PARTNER PAGE - create partner page for BDL for desktop(NBO-344)
    // ---------------------------------------------------------------------------------------------------- android link
    When(~/^User click android app link$/) { ->
        browser.withNewWindow({ epoints.goInstorePage.clickGooglePlayLink() }) {
            waitFor { browser.getTitle().contains('Big Deals Local') }
            assert browser.getTitle().contains('Big Deals Local')
        }
    }
    Then(~/^Google play store will be opened in new window$/) { ->
        //assertions done in previous step
    }
    // 1.3 //  ----------------------------------------- PARTNER PAGE - create partner page for BDL for desktop(NBO-344)
    // -------------------------------------------------------------------------------------------------- app store link
    When(~/^User click app store link$/) { ->
        browser.withNewWindow({ epoints.goInstorePage.clickAppStoreLink() }) {
            waitFor{ (browser.getTitle().contains('Apple') || browser.getTitle().contains('iTunes')) }
            assert (browser.getTitle().contains('Apple') || browser.getTitle().contains('iTunes'))
        }
    }
    Then(~/^iTunes will be opened in new window$/) { ->
        //assertions done in previous step
    }

    // 2.1 //  -------------------------------------------- EPOINTS DESKTOP - update the in-store partner page (NBO-752)
    // ------------------------------------------------------------------------------------------------------ bigdl link
    When(~/^User click bigdl text link$/) { ->
        browser.withNewWindow({ epoints.goInstorePage.clickBigdlLink() }) {
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
        browser.withNewWindow({ epoints.goInstorePage.clickGooglePlayTextLink() }) {
            waitFor { browser.getTitle().contains('Big Deals Local') }
            assert browser.getTitle().contains('Big Deals Local')
        }
    }

    // 2.3 //  -------------------------------------------- EPOINTS DESKTOP - update the in-store partner page (NBO-752)
    // ------------------------------------------------------------------------------------------------ app store text link
    When(~/^User click app store text link$/) { ->
        browser.withNewWindow({ epoints.goInstorePage.clickAppStoreTextLink() }) {
            waitFor{ (browser.getTitle().contains('Apple') || browser.getTitle().contains('iTunes')) }
            assert (browser.getTitle().contains('Apple') || browser.getTitle().contains('iTunes'))
        }
    }
