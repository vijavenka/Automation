package stepdefs.EpointsStepDefs.staticPagesFooterSection

import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-02-23.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

    // 1.1 //  -------------------------------------------------------------------------------------- About epoints page
    // ---------------------------------------------------------------------------------------- What is epoints? section
    // Update ---------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)

    Given(~/^About epoints page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.clickAboutEpointsLink()
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.aboutEpointsURL }
        waitFor{ browser.title == envVar.aboutEpointsPageTitle }
        assert browser.currentUrl == envVar.epointsLink + envVar.aboutEpointsURL
        assert browser.title == envVar.aboutEpointsPageTitle
    }
    When(~/^User click 'What is epoints\?' link$/) { ->
        epoints.aboutEpointsPage.clickWhatIsEpointsLink()
    }
    Then(~/^Page will be scrolled to 'What is epoints\?' section$/) { ->
        //leave empty
    }
    Then(~/^'What is epoints\?' section has proper content$/) { ->
        assert epoints.aboutEpointsPage.firstSectionHeader.text() == envVar.aboutEpointsFirstSectionHeader
        assert epoints.aboutEpointsPage.firstSectionPicture.isDisplayed()
        assert epoints.aboutEpointsPage.firstSectionText.text() == envVar.aboutEpointsFirstSectionText
        assert epoints.aboutEpointsPage.firstSectionBackToTopLink.isDisplayed()
    }

    // 1.2 //  -------------------------------------------------------------------------------------- About epoints page
    // ---------------------------------------------------------------------------------- So much choice section content
    // Update ---------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
    When(~/^User click 'So much choice' link$/) { ->
        epoints.aboutEpointsPage.clickSoMuchChoiceLink()
    }
    Then(~/^Page will be scrolled to 'So much choice' section$/) { ->
        //leave empty
    }
    Then(~/^'So much choice' section has proper content$/) { ->
        waitFor{ epoints.aboutEpointsPage.secondSectionRetailerCardBasic.isDisplayed() }
        assert epoints.aboutEpointsPage.secondSectionHeader.text() == envVar.aboutEpointsSecondSectionHeader
        assert epoints.aboutEpointsPage.secondSectionRetailerCardBasic.isDisplayed()
        assert epoints.aboutEpointsPage.secondSectionText.text() == envVar.aboutEpointsSecondSectionText
        assert epoints.aboutEpointsPage.secondSectionPreviousArrow.isDisplayed()
        assert epoints.aboutEpointsPage.secondSectionNextArrow.isDisplayed()
        assert epoints.aboutEpointsPage.secondSectionBackToTopLink.isDisplayed()
    }

    // 1.3 //  -------------------------------------------------------------------------------------- About epoints page
    // ---------------------------------------------------------------------------- So much choice section retailer link
    // Update ---------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
    When(~/^User click one of retailer card$/) { ->
        random = 0
        waitFor{ epoints.aboutEpointsPage.secondSectionRetailerCardBasic.isDisplayed() }
        while(!epoints.aboutEpointsPage.secondSectionRetailerCardBasic[random].isDisplayed()){
            random = func.returnRandomValue(epoints.aboutEpointsPage.secondSectionRetailerCardBasic.size())
        }
        browser.withNewWindow({ epoints.aboutEpointsPage.clickChosenRetailerCard(random) }, close:true){
            assert browser.getTitle().contains(envVar.transitionPageTag)
            waitFor{ epoints.transitionPage.informationModal.isDisplayed() }
            waitFor{ epoints.transitionPage.informationModalNoButton.isDisplayed() }
            waitFor{ epoints.transitionPage.informationModalJoinButton.isDisplayed() }
            assert epoints.transitionPage.informationModal.isDisplayed()
            assert epoints.transitionPage.informationModalNoButton.isDisplayed()
            assert epoints.transitionPage.informationModalJoinButton.isDisplayed()
        }
    }

    // 1.4 //  -------------------------------------------------------------------------------------- About epoints page
    // ------------------------------------------------------------------------------------- Hassle-free rewards content
    // Update ---------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
    When(~/^User click 'Hassle-free rewards' link$/) { ->
        epoints.aboutEpointsPage.clickHassleFreeRewardsLink()
    }
    Then(~/^Page will be scrolled to 'Hassle-free rewards' section$/) { ->
        //leave empty
    }
    Then(~/^'Hassle-free rewards' section has proper content$/) { ->
        assert epoints.aboutEpointsPage.thirdSectionHeader.text() == envVar.aboutEpointsThirdSectionHeader
        assert epoints.aboutEpointsPage.thirdSectionPicture.isDisplayed()
        assert epoints.aboutEpointsPage.thirdSectionText.text() == envVar.aboutEpointsThirdSectionText
        assert epoints.aboutEpointsPage.thirdSectionText2.text() == envVar.aboutEpointsThirdSectionText2
        assert epoints.aboutEpointsPage.thirdSectionBackToTopLink.isDisplayed()
    }

    // 1.5 //  -------------------------------------------------------------------------------------- About epoints page
    // ------------------------------------------------------------------------------------ Get involved section content
    // Update ---------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
    When(~/^User click 'Get involved' link$/) { ->
        epoints.aboutEpointsPage.clickGetInvolvedLink()
    }
    Then(~/^Page will be scrolled to 'Get involved' section$/) { ->
        //leave empty
    }
    Then(~/^'Get involved' section has proper content$/) { ->
        assert epoints.aboutEpointsPage.fourthSectionHeader.text() == envVar.aboutEpointsFourthSectionHeader
        assert epoints.aboutEpointsPage.fourthSectionText.text() == envVar.aboutEpointsFourthSectionText
        assert epoints.aboutEpointsPage.fourthSectionText2.text() ==envVar.aboutEpointsFourthSectionText2
        assert epoints.aboutEpointsPage.fourthSectionBackToTopLink.isDisplayed()
        assert epoints.aboutEpointsPage.fourthSectionGetInTouchButton.isDisplayed()
        assert epoints.aboutEpointsPage.fourthSectionWatchLink.isDisplayed()
    }

    // 1.6 //  -------------------------------------------------------------------------------------- About epoints page
    // ------------------------------------------------------------------------------- Get involved section youtube link
    // Update ---------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
    When(~/^User click youtube link$/) { ->
        browser.withNewWindow({ epoints.aboutEpointsPage.clickYoutubeEpointsVideoLink() }, close:true){
            waitFor{ browser.currentUrl == envVar.epointsYoutubeVideoURL }
            assert browser.currentUrl == envVar.epointsYoutubeVideoURL
        }
    }
    Then(~/^He will be redirected to youtube epoints video page$/) { ->
        //done in previous step
    }

    // 1.7 //  -------------------------------------------------------------------------------------- About epoints page
    // ---------------------------------------------------------------------- Get involved section submit a request link
    // Update ---------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
    When(~/^User click get in touch button$/) { ->
        //browser.withNewWindow({ epoints.aboutEpointsPage.clickGetInTouchButton() }, close:true){ //TODO should be opened in new tab
            epoints.aboutEpointsPage.clickGetInTouchButton()
            waitFor{ browser.currentUrl == envVar.contactUsURL }
            assert browser.currentUrl == envVar.contactUsURL
        //}
    }
    Then(~/^He will be redirected to submit a request page$/) { ->
        //done in previous step
    }