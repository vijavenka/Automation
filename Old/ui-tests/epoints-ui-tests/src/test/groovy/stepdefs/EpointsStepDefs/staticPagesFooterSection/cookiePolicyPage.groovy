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

    // 1.1 //  -------------------------------------------------------------------------------------- Cookie policy page
    // ---------------------------------------------------------------------------------------------------- page content
    // Update ---------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
    Given(~/^Cookie policy page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.clickCookiePolicyLink()
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.cookiePolicyURL }
        waitFor{ browser.title == envVar.cookiePolicyPageTitle }
        assert browser.currentUrl == envVar.epointsLink + envVar.cookiePolicyURL
        assert browser.title == envVar.cookiePolicyPageTitle

    }
    When(~/^User looks on cookie policy page$/) { ->
        //leave empty
    }
    Then(~/^He can see that cookie policy page contains proper information$/) { ->
        waitFor{ epoints.cookiePage.pageContent.isDisplayed() }
        assert epoints.cookiePage.pageContent.text().replace("\n","").replace(" ","") == envVar.cookiePolicyPageContent.replace(" ","")
    }

    // 1.2 //  -------------------------------------------------------------------------------------- Cookie policy page
    // ------------------------------------------------------------------------------------------ youronlinechoices link
    // Update ---------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
    When(~/^User click youronlinechoices link$/) { ->
        browser.withNewWindow({ epoints.cookiePage.clickYourOnlineChoicesLink() }, close:true){
            waitFor{ browser.currentUrl == envVar.yourOnlineChoicesURL }
            assert browser.currentUrl == envVar.yourOnlineChoicesURL
        }
    }
    Then(~/^He will be redirected to youronlinechoices page$/) { ->
        //done in previous step
    }

    // 1.3 //  -------------------------------------------------------------------------------------- Cookie policy page
    // -------------------------------------------------------------------------------------------- allaboutcookies link
    // Update ---------------------------------------------------- EPOINTS - complete Angular for static pages(NBO-1437)
    When(~/^User click allaboutcookies link$/) { ->
        browser.withNewWindow({ epoints.cookiePage.clickAllaboutcookiesLink() }, close:true){
            waitFor{ browser.currentUrl == envVar.allaboutcookiesURL }
            assert browser.currentUrl == envVar.allaboutcookiesURL
        }
    }
    Then(~/^He will be redirected to allaboutcookies page$/) { ->
        //done in previous step
    }