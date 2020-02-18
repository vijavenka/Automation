package pages.Epoints_pages
import geb.Page
import modules.Epoints_modules.getSection.azPageModule
import modules.Epoints_modules.getSection.transitionModule
import modules.Epoints_modules.getSection.voucherPageModule
import modules.Epoints_modules.joinLoginSection.completeAccountPageModule
import modules.Epoints_modules.joinLoginSection.signInModule
import modules.Epoints_modules.otherSections.deeplinkCreatorModule
import modules.Epoints_modules.spendSection.basketModule
import modules.Epoints_modules.spendSection.browseRewardsPageModule
import modules.Epoints_modules.spendSection.individualRedemptionPageModule
import modules.Epoints_modules.getSection.watchVideosPageModule
import modules.Epoints_modules.getSection.goInstorePageModule
import modules.Epoints_modules.joinLoginSection.joinModule
import modules.Epoints_modules.getSection.playGamesPageModule
import modules.Epoints_modules.joinLoginSection.forgotPasswordPageModule
import modules.Epoints_modules.joinLoginSection.changePasswordPageModule
import modules.Epoints_modules.spendSection.spendPageModule
import modules.Epoints_modules.otherSections.specsaversPageModule
import modules.Epoints_modules.staticPagesFooterSection.cookiePolicyPageModule
import modules.Epoints_modules.staticPagesFooterSection.privacyPolicyPageModule
import modules.Epoints_modules.staticPagesFooterSection.termsAndConditionsPageModule
import modules.Epoints_modules.staticPagesFooterSection.contactUsPageModule
import modules.Epoints_modules.staticPagesFooterSection.faqPageModule
import modules.Epoints_modules.staticPagesFooterSection.aboutEpointsPageMode
import modules.Epoints_modules.staticPagesFooterSection.partnersPageModule
import modules.Epoints_modules.yourAccountSection.profileTab
import modules.Epoints_modules.yourAccountSection.emailChangedConfirmationPage
import modules.Epoints_modules.getSection.inviteFriendPageModule
import modules.Epoints_modules.yourAccountSection.purchaseEpointsTab
import modules.Epoints_modules.otherSections.paypalModule

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 12.06.14
 * Time: 11:06
 * To change this template use File | Settings | File Templates.
 */

//TODO make consistent agular/not angular locator names

class epointsPage extends Page {
    static url = '/'
    static at = { waitFor{ title.contains('| epoints') } }
    static content = {
        //cookie panel
        cookiePanel(required: false){ $('.CookiesInfoMessage') }
        cookiePanelText(required: false){ cookiePanel.find('.CookiesInfoMessage-text') }
        cookiePanelAcceptButton(required: false){ cookiePanel.find('.btn-yellow') }
        cookiePanelFindOutMoreButton(required: false){ cookiePanel.find('.btn-grey') }
        cookiePanelCloseButton(required: false){ cookiePanel.find('.CookiesInfoMessage-close') }

        //header angular
        topNavAboutUsButton{ $('.MainHeader-content').find('a', text: 'About epoints') }
        topNavFAQButton{ $('.MainHeader-content').find('a', text: 'FAQ') }
        topNavJoinNowButton{ $('.MainHeader-content').find('a', text: 'Join Now') }
        topNavSignInButton(required: false){ $('.sign-in__btn') }

        topNavHiUsername(required: false){ $('.MainHeader-content').find('strong', text: contains('Hi')) }
        topNavYourAccountButton(required: false){ $('.MainHeader-content').find('a', text: 'Your Account') }
        yourEpointsCounter(required: false){ $('.balance-widget__confirmed__value') }
        pendingEpointsCounter(required: false){ $('.balance-widget__pending__value') }
        topnavSignOutButton(required: false){ $('.MainHeader-content').find('a', text: 'Sign out') }

        topNavZonePickerFlag{ $('.ZonePicker__flag') }
        topNavZonePickerPanel{ $('.ZonePicker') }
        topNavZonePickerCountryBasic{ $('.ZonePicker__country') }
        topNavZonePickerPopupHeader{ topNavZonePickerPanel.find('h3') }
        topNavZonePickerPopupText{ topNavZonePickerPanel.find('p') }
        topNavZonePickerPopupCloseButton{ topNavZonePickerPanel.find('.ZonePicker__closeInfo').find('a') }
        //

        joinInputField(required: false){ $('.HomePageJoin-buttonsRow').find('#email') }
        signInInputField(required: false){ $('.HomePageJoin-buttonsRow').find('#password') }
        joinForFreeButton(required: false){ $('.HomePageJoin-buttonsRow').find('.HomePageJoin-joinButton') }
        signInButton(required: false){ $('.HomePageJoin-buttonsRow').find('.HomePageJoin-joinButton') }
        signInWithFacebookButton(required: false){ $('.HomePageJoin-buttonsRow').find('.btn-facebook') }
        get50EpointsInformation(required: false){ $('.HomePageJoin-descriptionRow') }

        merchantLogoBasic{ $('.section-content.not-animated.animated').find('.retailerLink') }

        videoSplashScreen{ $('.HomeSection-videoSplashScreen') }
        videoPlayButton{ $('.HomeSection-videoPlay') }

        //navbar angular
        homeButton{ $('.MainNav').find('li',0) }
            myEpointsButton{ $('#myAccountSubMenu').find('li',0) }
            profileButton{ $('#myAccountSubMenu').find('li',2) }
            activityButton{ $('#myAccountSubMenu').find('li',4) }
            rewardsHistoryButton{ $('#myAccountSubMenu').find('li',6) }
            purchaseEpointsButton{ $('#myAccountSubMenu').find('li',8) }
        getButton{ $('.MainNav').find('li',1) }
            shopOnlineButtonAngular{ $('.SubHeader-list').find('li',0) }
            playGamesButtonAngular{ $('.SubHeader-list').find('li',1) }
            watchVideosButtonAngular{ $('.SubHeader-list').find('li',2) }
            goInStoreButtonAngular{ $('.SubHeader-list').find('li',3) }
            inviteFriendsButton{ $('.SubHeader-list').find('li',4) }
        spendButton{ $('.MainNav').find('li',2).find('a') }

        partnerWithUS{ $('.SubHeader-list').find('li',0) }
        caseStudies{ $('.SubHeader-list').find('li',1) }
        //

        //get epoints for shopping online
        getEpointsOnlineSectionArrowDown{ $('.NextSectionButton',0) }
        getEpointsOnlineSectionTitle{ $('.HomeSection-header',0) }
        getEpointsOnlineSectionText{ $('.HomeSection-message',0) }
        getEpointsOnlineSectionLink{ $('.HomeSection-link',0) }
        getEpointsOnlineSectionRetailerCardBasic{ $('.HomeSection-spinner').find('.merchant-logo') }
        getEpointsOnlineSectionRetailerCardNameBasic{ $('.HomeSection-spinner').find('.merchant-logo-wrapper') }

        //get epoints for shopping in-store section
        getEpointsInStoreSectionArrowDown{ $('.NextSectionButton',1) }
        getEpointsInStoreSectionTitle{ $('.HomeSection-header',1) }
        getEpointsInStoreSectionText{ $('.HomeSection-message',1) }
        getEpointsInStoreSectionLink{ $('.HomeSection-link',1).find('strong') }
        getEpointsInStoreSectionImages{ $('.HomeSection-images') }

        //get epoints for playing games
        getEpointsForPlayingGamesSectionArrowDown{ $('.NextSectionButton',2) }
        getEpointsForPlayingGamesSectionTitle{ $('.HomeSection-header',2) }
        getEpointsForPlayingGamesSectionText{ $('.HomeSection-message',2) }
        getEpointsForPlayingGamesSectionLink{ $('.HomeSection-link',2).find('strong') }
        getEpointsForPlayingGamesSectionImages{ $('.HomeSection-images',1) }
        getEpointsForPlayingGamesSectionBird{ $('.HomeSection-bird.u-dynamicBackgroundAdded') }

        //get epoints for watching videos
        getEpointsVideosSectionArrowDown{ $('.NextSectionButton',3) }
        getEpointsVideosSectionTitle{ $('.HomeSection-header',3) }
        getEpointsVideosSectionText{ $('.HomeSection-message',3) }
        getEpointsVideosSectionLink{ $('.HomeSection-link',3).find('strong') }
        getEpointsVideosSectionImages{ $('.HomeSection-images',2) }

        //spend all your epoints easily
        spendEpointsEasilySectionArrowDown{ $('.NextSectionButton',4) }
        spendEpointsEasilySectionTitle{ $('.HomeSection-header',4) }
        spendEpointsEasilySectionText{ $('.HomeSection-images-descriptions') }
        spendEpointsEasilySectionImages{ $('.HomeSection-images-image') }

        //footer
        partnerLink{ $('.MainFooter-menu-listItem').find('a', text: 'Partners') }
        cookiesLink{ $('.MainFooter-menu-listItem').find('a', text: 'Cookies') }
        contactUsLink{ $('.MainFooter-menu-listItem').find('a', text: 'Contact Us') }
        privacyPolicyLink{ $('.MainFooter-menu-listItem').find('a', text: 'Privacy Policy') }
        termsAndConditionsLink{ $('.MainFooter-menu-listItem').find('a', text: 'Terms & Conditions') }
        mobileSiteLink{ $('.MainFooter-menu-listItem').find('a', text: 'Mobile site') }

        //modules
        deeplinkCreator{ module deeplinkCreatorModule }
        transitionPage{ module transitionModule }
        signModule{ module signInModule }
        azPage{ module azPageModule }
        voucherPage{ module voucherPageModule }
        completeAccountPageModule{ module completeAccountPageModule }
        spendPage{ module spendPageModule }
        browseRewardsPage{ module browseRewardsPageModule }
        basket{ module basketModule }
        indRedemptionPage{ module individualRedemptionPageModule }
        playGamesPage{ module playGamesPageModule }
        watchVideosPage{ module watchVideosPageModule  }
        goInstorePage{ module goInstorePageModule }
        joinModule{ module joinModule }
        forgotPasswordPage{ module forgotPasswordPageModule }
        changePasswordPage{ module changePasswordPageModule }
        specsaversPage{ module specsaversPageModule }
        cookiePage{ module cookiePolicyPageModule }
        privacyPage{ module privacyPolicyPageModule }
        termsPage{ module termsAndConditionsPageModule }
        contactUsPage{ module contactUsPageModule }
        faqPage{ module faqPageModule }
        aboutEpointsPage{ module aboutEpointsPageMode }
        partnersPage{ module partnersPageModule }
        userProfileTab{ module profileTab }
        purchaseEpointsTab{ module purchaseEpointsTab }
        emailChangeConfirmationPage{ module emailChangedConfirmationPage }
        inviteFriendPage{ module inviteFriendPageModule }
        paypal{ module paypalModule }
    }
        //cookie panel
        def clickAcceptButtonOnCookiePanel(){ waitFor{ cookiePanelAcceptButton.isDisplayed() }; cookiePanelAcceptButton.click() }
        def clickFindOutMoreButtonOnCookiePanel(){ waitFor{ cookiePanelFindOutMoreButton.isDisplayed() }; cookiePanelFindOutMoreButton.click() }

        //header angular
        def clickSignInButton(){ waitFor(10){ topNavSignInButton.isDisplayed() }; topNavSignInButton.click() }
        def clickJoinNowButton(){ waitFor{ topNavJoinNowButton.isDisplayed() }; topNavJoinNowButton.click() }
        def clickYourAccountButton(){ waitFor{ topNavYourAccountButton.isDisplayed() }; topNavYourAccountButton.click() }
        def clikcSignOutButton(){ waitFor{ topnavSignOutButton.isDisplayed() }; topnavSignOutButton.click() }
        def clickVideoPlayerPlayButton(){ waitFor{ videoPlayButton.isDisplayed() }; videoPlayButton.click() }
        def epandZonePickerPanel(){ waitFor{ topNavZonePickerFlag[0].isDisplayed() }; topNavZonePickerFlag[0].click() }
        def clickCloseZonePickerButton(){ waitFor{ topNavZonePickerPopupCloseButton.isDisplayed() }; topNavZonePickerPopupCloseButton.click() }
        def choseUKzone(){ waitFor{ topNavZonePickerFlag[1].isDisplayed() }; topNavZonePickerFlag[1].click() }
        def choseIrelandZone(){ waitFor{ topNavZonePickerFlag[2].isDisplayed() }; topNavZonePickerFlag[2].click() }
        //

        //navbar angular
        def clickLogoOfChosenRetailer(number){ waitFor{ merchantLogoBasic.isDisplayed() }; merchantLogoBasic[number].click() }
            def clickProfileButton(){ waitFor{ profileButton.isDisplayed() }; profileButton.click() }
            def clickPurchaseEpointsButton(){ purchaseEpointsButton.isDisplayed(); purchaseEpointsButton.click() }
        def clickGetButton(){ waitFor{ getButton.isDisplayed() }; getButton.click() }
            def clickShopOnlineButtonAngular(){ waitFor{ shopOnlineButtonAngular.isDisplayed() }; shopOnlineButtonAngular.click() }
            def clickPlayGamesButtonAngular(){ waitFor{ playGamesButtonAngular.isDisplayed() }; playGamesButtonAngular.click() }
            def clickWatchVideoButtonAngular(){ waitFor{ watchVideosButtonAngular.isDisplayed() }; watchVideosButtonAngular.click() }
            def clickGoInStoreButtonAngular(){ waitFor{ goInStoreButtonAngular.isDisplayed() }; goInStoreButtonAngular.click() }
            def clickInviteFriendButton(){ waitFor{ inviteFriendsButton.isDisplayed() }; inviteFriendsButton.click() }
        def clickSpendButton(){ waitFor{ spendButton.isDisplayed() }; spendButton.click() }

        def clickCaseStudiesButtonAngular(){ waitFor{ caseStudies.isDisplayed() }; caseStudies.click() }
        //

        //get epoints for shopping online
        def clickOnlineStoresLinkOnGetEpointsSection(){ waitFor{ getEpointsOnlineSectionLink }; getEpointsOnlineSectionLink.click() }
        def clickChosenRetailerCard(number){ waitFor{ getEpointsOnlineSectionRetailerCardBasic[number].isDisplayed() }; getEpointsOnlineSectionRetailerCardBasic[number].click() }
        //get epoints for shopping in-store section
        def clickLearnMoreLinkOnGetEpointsInStoreSection(){ waitFor{ getEpointsInStoreSectionLink.isDisplayed() }; getEpointsInStoreSectionLink.click()  }
        def clickImageOnGetEpointsInStoreSection(){ waitFor{ getEpointsInStoreSectionImages.isDisplayed() }; getEpointsInStoreSectionImages.click() }
        //get epoints for playing games
        def clickPlayingGamesLinkOnGetEpointsForPlayingGamesSection(){ waitFor{ getEpointsForPlayingGamesSectionLink.isDisplayed() }; getEpointsForPlayingGamesSectionLink.click() }
        def clickClumyBirdImageOnGetEpointsForPlayingGamesSection(){ waitFor{ getEpointsForPlayingGamesSectionImages.isDisplayed() }; getEpointsForPlayingGamesSectionImages.click() }
        //get epoints for watching videos
        def clickGreatvideosLinkOnGetEpointsVideosSection(){ waitFor{ getEpointsVideosSectionLink.isDisplayed() }; getEpointsVideosSectionLink.click()  }
        def clickImageOnGetEpointsVidoeSection(){ waitFor{ getEpointsVideosSectionImages.isDisplayed() }; getEpointsVideosSectionImages.click() }
        //spend all your epoints easily
        def clickImageLinkOnSpendEpointsEasilySection(){ waitFor{ spendEpointsEasilySectionImages.isDisplayed() }; spendEpointsEasilySectionImages.click() }

        //footer
        def clickPartnerPageLink(){ waitFor{ partnerLink.isDisplayed() }; partnerLink.click() }
        def clickCookiePolicyLink(){ waitFor{ cookiesLink.isDisplayed() }; cookiesLink.click() }
        def clickPrivacyPolicyLink(){ waitFor{ privacyPolicyLink.isDisplayed() }; privacyPolicyLink.click() }
        def clickTermsAndConditionsLink(){ waitFor{ termsAndConditionsLink.isDisplayed() }; termsAndConditionsLink.click() }
        def clickContactUsLink(){ waitFor{ contactUsLink.isDisplayed() }; contactUsLink.click() }
        def clickFAQLink(){ waitFor{ topNavFAQButton.isDisplayed() }; topNavFAQButton.click() }
        def clickAboutEpointsLink(){ waitFor{ topNavAboutUsButton.isDisplayed() }; topNavAboutUsButton.click() }
}