package modules.Epoints_modules.staticPagesFooterSection

import geb.Module

/**
 * Created by kbaranowski on 2015-02-23.
 */

class contactUsPageModule extends Module{
    static content = {
        customerServiceNavbar{ $('#top-menu') }
        homeHeaderLink{ customerServiceNavbar.find('.tab_home') }
        faqHeaderLink{ customerServiceNavbar.find('.tab_forums') }
        requestHeaderLink{ customerServiceNavbar.find('.tab_new') }

        mainPageText{ $('.side-box-content') }
        ticketForm{ $('#ticketform') }
        submitHeader{ ticketForm.find('h2') }
        emailLabel{ ticketForm.find('h3',0) }
        emaiInputField{ ticketForm.find('#email_address') }
        subjectLabel{ ticketForm.find('h3',1) }
        subjectInputField{ ticketForm.find('#ticket_subject') }
        descriptionLabel{ ticketForm.find('h3',3) }
        descriptionInPutField{ ticketForm.find('#comment_value') }
        attachmentsLabel{ $('#attachment-friends').find('h3') }
        attachmentsLink{ $('#attachment-friends').find('#attach_link') }
        submitButton{ $('#submit-button') }
    }
}