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

    // 1.1 //  ------------------------------------------------------------------------------------ Terms and conditions
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Terms and conditions page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.clickTermsAndConditionsLink()
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.termsAndConditionsURL }
        waitFor{ browser.title == envVar.termsAndConditionsPageTitle }
        assert browser.currentUrl == envVar.epointsLink + envVar.termsAndConditionsURL
        assert browser.title == envVar.termsAndConditionsPageTitle
    }
    When(~/^User looks on terms and conditions page$/) { ->
        //leave empty
    }
    Then(~/^He can see that terms and conditions page contains proper information$/) { ->
        waitFor{ epoints.termsPage.pageContent.isDisplayed() }
        assert epoints.termsPage.pageContent.text().replace("\n","").replace(" ","") == envVar.termsAndConditionsPageContent.replace(" ","")
    }

    // 1.2 //  ------------------------------------------------------------------------------------ Terms and conditions
    // ---------------------------------------------------------------------------------------------------- epoints link
    When(~/^User use epoints link$/) { ->
        browser.withNewWindow({ epoints.termsPage.clickEpointsLink() }, close:true){
            epoints.termsPage.clickEpointsLink()
            waitFor{ browser.currentUrl == envVar.epointsLiveURL }
            assert browser.currentUrl == envVar.epointsLiveURL
        }
    }
    Then(~/^He will be redirected to epoints home page$/) { ->
        //done in previous step
    }

    // 1.3 //  ------------------------------------------------------------------------------------ Terms and conditions
    // ---------------------------------------------------------------------------------------------------- support link
    When(~/^User use support link$/) { ->
        //leave empty only link href will be checked
    }
    Then(~/^Email tool will be opened$/) { ->
        assert epoints.termsPage.supportLink.attr('href') == envVar.termsAndConditionssupportLink
    }