package com.iat.pages.points.voucherPageModules

import geb.Module

class VoucherModule extends Module {

    static content = {

        voucherMerchantName { $('.VoucherCard-info').find('h3') }
        voucherName { $('.VoucherCard-info').find('h4') }
        voucherDescription { $('.VoucherCard-info').find('p') }
        voucherImage { $('.VoucherCard-image').find('img') }
        getVoucherCodeButton { $('.VoucherCard-getCodeButton') }
        getVoucherCodeButtonDescription { getVoucherCodeButton.find('span') }
        voucherExpiryDate { $('.VoucherCard-expiryDate') }
        voucherShareLabel { $('.VoucherCard-socialShare') }
        voucherShareFacebook { $('.facebook-share') }
        voucherShareTwitter { $('.twitter-share') }
        voucherShareGoogle { $('.googleplus-share') }
        voucherCode(required: false) { $('.VoucherCard-codeCell').find('.VoucherCard-codeValue') }
        voucherCodeHelpText { $('.VoucherCard-codeCell').find('span') }
        openSiteButton(required: false) { $('.VoucherCard-openSite') }
    }

    //voucher cards
    def clickShareFacebookOption() {
        waitFor { voucherShareFacebook.isDisplayed() }; voucherShareFacebook.click()
    }

    def clickShareTwitterOption() {
        waitFor { voucherShareTwitter.isDisplayed() }; voucherShareTwitter.click()
    }

    def clickShareGoogleOption() {
        waitFor { voucherShareGoogle.isDisplayed() }; voucherShareGoogle.click()
    }

    def clickGetVoucherCodeButton() {
        waitFor { getVoucherCodeButton.isDisplayed() }; getVoucherCodeButton.click()
    }

}