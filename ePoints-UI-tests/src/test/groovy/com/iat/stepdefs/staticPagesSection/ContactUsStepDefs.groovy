package com.iat.stepdefs.staticPagesSection

import com.iat.pages.home.EpointsHomePage
import com.iat.pages.home.footerStaticPages.ContactUsPage
import com.iat.stepdefs.envVariables
import geb.Browser

import static cucumber.api.groovy.EN.*

def epointsHomePage = new EpointsHomePage()
def contactUsPage = new ContactUsPage()

def browser = new Browser()
def envVar = new envVariables()

// 1.1 //  ----------------------------------------------------------------------------------------- Contact us page
// ---------------------------------------------------------------------------------------------------- page content
Given(~/^Contact us page is opened$/) { ->
    to EpointsHomePage
    epointsHomePage = page
    epointsHomePage.cookiePanelModule.clickAcceptButtonOnCookiePanel()
    epointsHomePage.footerModule.clickContactUsLink()
    at ContactUsPage
    contactUsPage = page
}
When(~/^User look at contact us page$/) { ->
    //leave empty
}
Then(~/^He will see that contact us page contains all needed elements to submit a request$/) { ->
    assert contactUsPage.submitHeader.text() == envVar.contactUsPageHeader
    assert contactUsPage.emailLabel.text() == envVar.contactUsPageEmailLabel
    assert contactUsPage.subjectLabel.text() == envVar.contactUsPageSubjectLabel
    assert contactUsPage.descriptionLabel.text() == envVar.contactUsPageDescriptionLabel
    assert contactUsPage.emaiInputField.isDisplayed()
    assert contactUsPage.subjectInputField.isDisplayed()
    assert contactUsPage.descriptionInPutField.isDisplayed()
    assert contactUsPage.descriptionHelpText.text() == envVar.contactUsPageDescriptionHelpText
    assert contactUsPage.submitButton.isDisplayed()
    assert contactUsPage.requiredMark.size() == 3
}