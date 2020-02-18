package com.iat.pages.home.modules

import geb.Module

import static org.hamcrest.MatcherAssert.assertThat

class FooterModule extends Module {

    static content = {
        //Footer Links of Epoints Home Page
        aboutEpointsLink(wait: true) { $('.MainFooter-menu-listItem').find('a', text: 'About epoints') }
        faqLink(wait: true) { $('.MainFooter-menu-listItem').find('a', text: 'FAQ') }
        recognitionAndRewards(wait: true, required: false) {
            $('.MainFooter-menu-listItem').find('a', text: 'Recognition and Rewards')
        }
        cookiesLink(wait: true) { $('.MainFooter-menu-listItem').find('a', text: 'Cookies') }
        contactUsLink(wait: true) { $('.MainFooter-menu-listItem').find('a', text: 'Contact Us') }
        privacyPolicyLink(wait: true) { $('.MainFooter-menu-listItem').find('a', text: 'Privacy Policy') }
        termsAndConditionsLink(wait: true) { $('.MainFooter-menu-listItem').find('a', text: 'Terms & Conditions') }
        mobileSiteLink(wait: true) { $('.MainFooter-menu-listItem').find('a', text: 'Mobile site') }
        facebookFooterWidget(wait: true, required: false) { $('.facebookLikeWidget') }
        twitterFooterWidget(wait: true, required: false) { $('.twitterFollowWidget') }
    }

    //Atomic operation on Footer of Epoints
    def clickAboutEpointsLink() {
        waitFor {
            aboutEpointsLink.isDisplayed()
        }
        aboutEpointsLink.click()
    }

    def clickFAQLink() {
        waitFor {
            faqLink.isDisplayed()
        }
        faqLink.click()
    }

    def clickPartnerPageLink() {
        waitFor {
            partnerLink.isDisplayed()
        }
        partnerLink.click()
    }

    def clickCookiePolicyLink() {
        waitFor {
            cookiesLink.isDisplayed()
        }
        cookiesLink.click()
    }

    def clickPrivacyPolicyLink() {
        waitFor {
            privacyPolicyLink.isDisplayed()
        }
        privacyPolicyLink.click()
    }

    def clickTermsAndConditionsLink() {
        waitFor { termsAndConditionsLink.isDisplayed() }
        termsAndConditionsLink.click()
    }

    def clickContactUsLink() {
        waitFor { contactUsLink.isDisplayed() }
        contactUsLink.click()
    }

    def checkAvailabilityOfFooterElements() {
        assertThat("Missing about footer link", aboutEpointsLink.isDisplayed())
        assertThat("Missing faq footer link", faqLink.isDisplayed())
        assertThat("Missing recognition and rewards footer link", recognitionAndRewards.isDisplayed())
        assertThat("Missing cookies footer link", cookiesLink.isDisplayed())
        assertThat("Missing contact us footer link", contactUsLink.isDisplayed())
        assertThat("Missing pp footer link", privacyPolicyLink.isDisplayed())
        assertThat("Missing t&c footer link", termsAndConditionsLink.isDisplayed())
        assertThat("Missing mobile version footer link", mobileSiteLink.isDisplayed())
        assertThat("Missing facebook footer widget", facebookFooterWidget.isDisplayed())
        assertThat("Missing twitter footer widget", twitterFooterWidget.isDisplayed())
    }

}