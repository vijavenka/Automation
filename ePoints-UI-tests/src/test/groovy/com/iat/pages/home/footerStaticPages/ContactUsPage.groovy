package com.iat.pages.home.footerStaticPages

import com.iat.pages.AbstractPage

class ContactUsPage extends AbstractPage {

    static url = '/contact-us'
    static at = { waitFor { getTitle().contains('Contact us | epoints') } }

    static content = {

        submitHeader(wait: true) { $('h1') }
        contactForm(wait: true) { $('.ContactForm') }
        emailLabel(wait: true) { contactForm.find('label', for: 'email') }
        emaiInputField(wait: true) { contactForm.find('#email') }
        subjectLabel(wait: true) { contactForm.find('label', for: 'subject') }
        subjectInputField(wait: true) { contactForm.find('#subject') }
        descriptionLabel(wait: true) { contactForm.find('label', for: 'description') }
        descriptionInPutField(wait: true) { contactForm.find('#description') }
        descriptionHelpText(wait: true) { contactForm.find('p') }
        submitButton(wait: true) { contactForm.find('.btn-green') }
        requiredMark(wait: true) { contactForm.find('.require-mark') }
    }

}