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

    // 1.1 //  ------------------------------ MOBILE PARTNER PAGE - create partner page for VIDEOJUG for mobile(NBO-345)
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Watch videos page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.clickGetButton()
        epoints.clickWatchVideoButtonAngular()
    }
    When(~/^user look on watch videos page$/) { ->
        //leave empty
    }
    Then(~/^He will see proper header text$/) { ->
        waitFor{ epoints.watchVideosPage.mainHeader.isDisplayed() }
        waitFor{ epoints.watchVideosPage.mainDescription.isDisplayed() }
        assert epoints.watchVideosPage.mainHeader.text() == envVar.watchVideoMainHeader
        assert epoints.watchVideosPage.mainDescription.text() == envVar.watchVideoMainDescription
    }
    Then(~/^Three images will be displayed$/) { ->
        waitFor{ epoints.watchVideosPage.firstSectionPicture.isDisplayed() }
        waitFor{ epoints.watchVideosPage.secondSectionPicture.isDisplayed() }
        waitFor{ epoints.watchVideosPage.thirdSectionPicture.isDisplayed() }
        assert epoints.watchVideosPage.firstSectionPicture.isDisplayed()
        assert epoints.watchVideosPage.secondSectionPicture.isDisplayed()
        assert epoints.watchVideosPage.thirdSectionPicture.isDisplayed()
    }
    Then(~/^Three section descriptions will be displayed$/) { ->
        waitFor{ epoints.watchVideosPage.firstSectionHeader.isDisplayed() }
        waitFor{ epoints.watchVideosPage.firstSectionText.isDisplayed() }
        waitFor{ epoints.watchVideosPage.secondSectionHeader.isDisplayed() }
        waitFor{ epoints.watchVideosPage.secondSectionText.isDisplayed() }
        waitFor{ epoints.watchVideosPage.thirdSectionHeader.isDisplayed() }
        waitFor{ epoints.watchVideosPage.thirdSectionText.isDisplayed() }
        assert epoints.watchVideosPage.firstSectionHeader.text() == envVar.watchVideo1SectionHeader
        assert epoints.watchVideosPage.firstSectionText.text() == envVar.watchVideo1SectionDescription
        assert epoints.watchVideosPage.secondSectionHeader.text() == envVar.watchVideo2SectionHeader
        assert epoints.watchVideosPage.secondSectionText.text().replace("\n", "") == envVar.watchVideo2SectionDescription
        assert epoints.watchVideosPage.thirdSectionHeader.text() == envVar.watchVideo3SectionHeader
        assert epoints.watchVideosPage.thirdSectionText.text() == envVar.watchVideo3SectionDescription
    }

    // 1.2 //  ------------------------------ MOBILE PARTNER PAGE - create partner page for VIDEOJUG for mobile(NBO-345)
    // --------------------------------------------------------------------------------------------------- videojug link
    When(~/^User click one of videojug link$/) { ->
        browser.withNewWindow({ epoints.watchVideosPage.clickVideojugLink(func.returnRandomValue(2)) }, close:true){
            waitFor{ browser.currentUrl == envVar.videojugURL }
            assert browser.currentUrl == envVar.videojugURL
        }
    }
    Then(~/^He will be redirected to videojug page$/) { ->
        //done in previous step
    }