package modules.Epoints_modules.staticPagesFooterSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-24.
 */

class partnersPageModule extends Module{
    static content = {
        partnerWithUsText{ $('.col-md-6.col-sm-6',0) }
        submitRequestText{ $('.box',1) }
        partnersForm{ $('#partnersForm') }
        formHeader{ partnersForm.find('h2') }
        firstNameLabel{ partnersForm.find('label', for: 'form_name') }
        firstNameInputField{ partnersForm.find('#form_name') }
        surnameLabel{ partnersForm.find('label', for: 'form_surname') }
        surnameInputField{ partnersForm.find('#form_surname') }
        companyNameLabel{ partnersForm.find('label', for: 'form_company_name') }
        companyNameInputField{ partnersForm.find('#form_company_name') }
        emailLabel{ partnersForm.find('label', for: 'form_email') }
        emailInputField{ partnersForm.find('#form_email') }
        websiteAddressLabel{ partnersForm.find('label', for: 'form_website') }
        websiteInputField{ partnersForm.find('#form_website') }
        submitButton{ partnersForm.find('.btn-green') }
        validErrorEmail{ partnersForm.find('.valid-error',2) }
        validErrorCompanyName{ partnersForm.find('.valid-error',1) }
        validErrorSurname{ partnersForm.find('.valid-error',0) }
        alertSuccess{ $('.alert.alert-success') }

        //specavers partner page
        specsaversMainText{ $('.row') }
        specsaversImage1{ $('.section-image').find('img',0) }
        specsaversImage2{ $('.section-image').find('img',1) }
        specsaversAboutLink{ $('a', text: 'about Specsavers') }
    }
    def enterFirstName(firstName){ waitFor{ firstNameInputField.isDisplayed() }; firstNameInputField.value(firstName) }
    def enterSurname(surname){ waitFor{ surnameInputField.isDisplayed() }; surnameInputField.value(surname) }
    def enterCompanyName(companyName){ waitFor{ companyNameInputField.isDisplayed() }; companyNameInputField.value(companyName) }
    def enterEmail(email){ waitFor{ emailInputField.isDisplayed() }; emailInputField.value(email) }
    def enterWebsiteAddress(websiteAddress){ waitFor{ websiteInputField.isDisplayed() }; websiteInputField.value(websiteAddress) }
    def filPartnersForm(firstName,surname,companyName,email,websiteAddress){ enterFirstName(firstName); enterSurname(surname); enterCompanyName(companyName); enterEmail(email); enterWebsiteAddress(websiteAddress) }
    def filPartnersFormMandatoryFields(surname,companyName,email){ enterSurname(surname); enterCompanyName(companyName); enterEmail(email) }
    def clickSubmitButton(){ waitFor{ submitButton.isDisplayed() }; submitButton.click() }

    //specavers partner page
    def clickAboutSpecsaversLink(){ waitFor{ specsaversAboutLink.isDisplayed() }; specsaversAboutLink.click() }
}