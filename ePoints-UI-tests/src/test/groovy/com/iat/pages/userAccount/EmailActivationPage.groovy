package com.iat.pages.userAccount

import com.iat.pages.AbstractPage

class EmailActivationPage extends AbstractPage {

    static url = 'mail/activate/token'
    static at = {
        waitFor() {
            title.contains('Email change | epoints')
        }
    }

    static content = {

        pageContent { $('.MailActivatePage') }
        accountDashboardButton { pageContent.find('.btn-grey') }
        earnMoreEpointsButton { pageContent.find('.btn-primary') }
    }

}