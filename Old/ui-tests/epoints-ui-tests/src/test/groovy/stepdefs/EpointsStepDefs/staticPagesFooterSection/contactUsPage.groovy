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

    // 1.1 //  ----------------------------------------------------------------------------------------- Contact us page
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Contact us page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.clickContactUsLink()
        waitFor{ browser.currentUrl == envVar.contactUsURL }
        waitFor{ browser.title == envVar.contactUsPageTitle }
        assert browser.currentUrl == envVar.contactUsURL
        assert browser.title == envVar.contactUsPageTitle
    }
    When(~/^User look at contact us page$/) { ->
        //leave empty
    }
    Then(~/^He will see that contact us page contains all needed elements to submit a request$/) { ->
        assert epoints.contactUsPage.homeHeaderLink.isDisplayed()
        assert epoints.contactUsPage.faqHeaderLink.isDisplayed()
        assert epoints.contactUsPage.requestHeaderLink.isDisplayed()
        assert epoints.contactUsPage.requestHeaderLink.hasClass('active')
        assert epoints.contactUsPage.mainPageText.text().replace("\n","").replace(" ","") == envVar.contactUsPageMainText.replace(" ","")
        assert epoints.contactUsPage.submitHeader.text() == envVar.contactUsPageHeader
        assert epoints.contactUsPage.emailLabel.text() == envVar.contactUsPageEmailLabel
        assert epoints.contactUsPage.subjectLabel.text() == envVar.contactUsPageSubjectLabel
        assert epoints.contactUsPage.descriptionLabel.text() == envVar.contactUsPageDescriptionLabel
        assert epoints.contactUsPage.attachmentsLabel.text() == envVar.contactUsPageAttachementsLabel
        assert epoints.contactUsPage.emaiInputField.isDisplayed()
        assert epoints.contactUsPage.subjectInputField.isDisplayed()
        assert epoints.contactUsPage.descriptionInPutField.isDisplayed()
        assert epoints.contactUsPage.attachmentsLink.isDisplayed()
        assert epoints.contactUsPage.submitButton.isDisplayed()
    }