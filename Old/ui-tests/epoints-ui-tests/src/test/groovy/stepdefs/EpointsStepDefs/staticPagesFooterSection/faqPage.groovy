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

    // 1.1 //  ------------------------------------------------------------------------------------------------ FAQ page
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^FAQ page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.clickFAQLink()
        waitFor{ browser.currentUrl == envVar.faqURL }
        waitFor{ browser.title == envVar.faqPageTitle }
        assert browser.currentUrl == envVar.faqURL
        assert browser.title == envVar.faqPageTitle
    }
    When(~/^User look on faq page$/) { ->
        //leave empty
    }
    Then(~/^He will see that it contains proper search$/) { ->
        assert epoints.faqPage.header.text().replace("\n","").replace(" ","") == envVar.faqHeader.replace(" ","")
        assert epoints.faqPage.searchLabel.text() == envVar.faqSearchLabel
        assert epoints.faqPage.searchInputField.isDisplayed()
        assert epoints.faqPage.searchButton.isDisplayed()

        assert epoints.contactUsPage.homeHeaderLink.isDisplayed()
        assert epoints.contactUsPage.faqHeaderLink.isDisplayed()
        assert epoints.contactUsPage.requestHeaderLink.isDisplayed()
    }
    Then(~/^Section with frequently asked questions$/) { ->
        assert epoints.faqPage.overviewLink.isDisplayed()
        assert epoints.faqPage.recentLink.isDisplayed()
        assert epoints.faqPage.questionSection.isDisplayed()
    }