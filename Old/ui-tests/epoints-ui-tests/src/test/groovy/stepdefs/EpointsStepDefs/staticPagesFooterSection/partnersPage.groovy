package stepdefs.EpointsStepDefs.staticPagesFooterSection

import geb.Browser
import pages.Epoints_pages.epointsPage
import stepdefs.Functions
import stepdefs.envVariables

import static cucumber.api.groovy.EN.*

/**
 * Created by kbaranowski on 2015-02-24.
 */

def epoints = new epointsPage()
def func = new Functions()
def browser = new Browser()
def envVar = new envVariables()

    // 1.1 //  ------------------------------------------------------------------------------------------- Partners page
    // ---------------------------------------------------------------------------------------------------- page content
    Given(~/^Partners page is opened$/) { ->
        to epointsPage
        epoints = page
        epoints.  clickPartnerPageLink()
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.partnersURL }
        waitFor{ browser.title == envVar.partnersPageTitle }
        assert browser.currentUrl == envVar.epointsLink + envVar.partnersURL
        assert browser.title == envVar.partnersPageTitle
    }
    When(~/^User look on partners page$/) { ->
        //leave it empty
    }
    Then(~/^He will se proper information for partners$/) { ->
        assert epoints.partnersPage.partnerWithUsText.text().replace("\n","").replace(" ","") == envVar.partnerPagePartnerWithUsText.replace(" ","")
        assert epoints.partnersPage.submitRequestText.text().replace("\n","").replace(" ","") == envVar.partnePageSubmitRequestText.replace(" ","")
    }
    Then(~/^Partner form will be displayed$/) { ->
        assert epoints.partnersPage.formHeader.text() == envVar.partnerPageFormHeader
        assert epoints.partnersPage.firstNameLabel.text() == envVar.partnerPageFirstNameLabel
        assert epoints.partnersPage.surnameLabel.text() == envVar.partnerPageSurnameLabel
        assert epoints.partnersPage.companyNameLabel.text() == envVar.partnerPageCompanyNameLabel
        assert epoints.partnersPage.emailLabel.text() == envVar.partnerPageEamilLabel
        assert epoints.partnersPage.websiteAddressLabel.text() == envVar.partnerPageWebsiteAddressLabel
        assert epoints.partnersPage.firstNameInputField.isDisplayed()
        assert epoints.partnersPage.surnameInputField.isDisplayed()
        assert epoints.partnersPage.companyNameInputField.isDisplayed()
        assert epoints.partnersPage.emailInputField.isDisplayed()
        assert epoints.partnersPage.websiteInputField.isDisplayed()
        assert epoints.partnersPage.submitButton.isDisplayed()
    }

    // 1.2 //  ------------------------------------------------------------------------------------------- Partners page
    // ------------------------------------------------------------------------------- partners form alert email invalid
    When(~/^User enter invalid email value$/) { ->
        epoints.partnersPage.enterEmail(envVar.testUserEmailInvalid)
    }
    Then(~/^Email is invalid alert will be displayed$/) { ->
        waitFor{ epoints.partnersPage.validErrorEmail.isDisplayed() }
        Thread.sleep(1000)
        assert epoints.partnersPage.validErrorEmail.text() == envVar.partnerPageEmailIsInvalidAlert
    }

    // 1.3 //  ------------------------------------------------------------------------------------------- Partners page
    // ---------------------------------------------------------------------------- partners form fields required alerts
    When(~/^User enter mandatory data and delete it$/) { ->
        epoints.partnersPage.filPartnersFormMandatoryFields(envVar.testUserSurname, envVar.testUserCompanyName, envVar.testUserEmail)
        epoints.partnersPage.filPartnersFormMandatoryFields("","","")
    }
    Then(~/^Under each mandatory field required alert will be displayed$/) { ->
        waitFor{ epoints.partnersPage.validErrorEmail.isDisplayed() }
        waitFor{ epoints.partnersPage.validErrorCompanyName.isDisplayed() }
        waitFor{ epoints.partnersPage.validErrorSurname.isDisplayed() }
        assert epoints.partnersPage.validErrorEmail.text() == envVar.forgotPasswordEmailIsRequiredAlert
        assert epoints.partnersPage.validErrorCompanyName.text() == envVar.partnerPageCompanyNameIsRequiredAlert
        assert epoints.partnersPage.validErrorSurname.text() == envVar.partnerPageSurnameIsRequiredAlert
    }

    // 1.4 //  ------------------------------------------------------------------------------------------- Partners page
    // ----------------------------------------------------------------------------- sending form, only mandatory fields
    When(~/^User fill mandatory fields with his own proper data$/) { ->
        //waitFor{  epoints.partnersPage.submitButton.attr('disabled') == 'disabled' }
        //assert epoints.partnersPage.submitButton.attr('disabled') == 'disabled'
        epoints.partnersPage.enterSurname(envVar.testUserSurname)
        epoints.partnersPage.enterCompanyName(envVar.testUserName)
        epoints.partnersPage.enterEmail(envVar.testUserEmail)
    }
    When(~/^Click submit partners form button$/) { ->
        //waitFor{ epoints.partnersPage.submitButton.attr('disabled') == 'disabled' }
        //assert epoints.partnersPage.submitButton.attr('disabled') == 'disabled'
        epoints.partnersPage.clickSubmitButton()
    }
    Then(~/^Form will be sent and success alert will be displayed$/) { ->
        waitFor(10){ epoints.partnersPage.alertSuccess.isDisplayed() }
        assert epoints.partnersPage.alertSuccess.text() == envVar.partnerPageAlertSuccessText
    }

    // 1.5 //  ------------------------------------------------------------------------------------------- Partners page
    // ---------------------------------------------------------------------------------------- sending form, all fields
    When(~/^User fill all fields with his own proper data$/) { ->
        epoints.partnersPage.filPartnersForm(envVar.testUserName, envVar.testUserSurname, envVar.testUserCompanyName, envVar.testUserEmail, envVar.testUserEmail)
    }

    // 1.6.1 //  ------------------------------------------------- SPECSAVERS - create partner page for DESKTOP(NBO-746)
    // ------------------------------------------------------------------------------------------------- specsavers page
    Given(~/^Specsavers partner page is opened$/) { ->
        epoints.clickCaseStudiesButtonAngular()
    }
    When(~/^User look on specsavers partner page$/) { ->
        waitFor{ browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversURL }
        waitFor{ browser.getTitle() == envVar.specsaversPartnerPageTitle }
        assert browser.currentUrl == envVar.epointsLink + envVar.partnersSpecsaversURL
        assert  browser.getTitle() == envVar.specsaversPartnerPageTitle
    }
    Then(~/^He will see proper text on specsavers partner page$/) { ->
        assert epoints.partnersPage.specsaversMainText.text().replace('\n','').replace(' ','') == envVar.specsaversPartnerPageMainText.replace(' ','')
    }
    Then(~/^He will see proper images on specsavers partner page$/) { ->
        waitFor{  epoints.partnersPage.specsaversImage1.isDisplayed() }
        waitFor{  epoints.partnersPage.specsaversImage2.isDisplayed() }
        assert epoints.partnersPage.specsaversImage1.isDisplayed()
        assert epoints.partnersPage.specsaversImage2.isDisplayed()
    }

    // 1.6.2 //  ------------------------------------------------- SPECSAVERS - create partner page for DESKTOP(NBO-746)
    // --------------------------------------------------------------------------------------------------- about us link
    //Update ------------------------------------- SPECAVERS - update copy on partner page on desktop & mobile(NBO-1054)
    When(~/^User click about specsavers link$/) { ->
        browser.withNewWindow({ epoints.partnersPage.clickAboutSpecsaversLink() }, close:true){
            waitFor{ browser.currentUrl == envVar.externalSpecsaversURL }
            assert browser.currentUrl == envVar.externalSpecsaversURL
        }
    }
    Then(~/^He will be redirected to external specsavers page$/) { ->
        // assertion done in previous step
    }