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

    // 1.1 //  ------------------------------------------------------------------------------------- Privacy policy page
    // ---------------------------------------------------------------------------------------------------- page content
    // Update ---------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
    Given(~/^Privacy policy page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.clickPrivacyPolicyLink()
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.privacyPolicyURL}
        waitFor{ browser.title == envVar.privacyPolicyPageTitle }
        assert browser.currentUrl == envVar.epointsLink + envVar.privacyPolicyURL
        assert browser.title == envVar.privacyPolicyPageTitle
    }
    When(~/^User looks on privacy policy page$/) { ->
        //leave empty
    }
    Then(~/^He can see that privacy policy page contains proper information$/) { ->
        waitFor{ epoints.privacyPage.pageContent.isDisplayed() }
        assert epoints.privacyPage.pageContent.text().replace("\n","").replace(" ","") == envVar.privacyPolicyPageContent.replace(" ","")
    }

    // 1.2 //  ------------------------------------------------------------------------------------- Privacy policy page
    // ---------------------------------------------------------------------------------------------- google policy link
    // Update ---------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
    When(~/^User click google policy link$/) { ->
        browser.withNewWindow({ epoints.privacyPage.clickGooglePrivacyLink() }, close:true){
            waitFor{ browser.currentUrl == envVar.googlePrivacyURL }
            assert browser.currentUrl == envVar.googlePrivacyURL
        }
    }
    Then(~/^He will be redirected to google privacy policy page$/) { ->
        //done in previous step
    }