package com.iat.stepdefs

class envVariables {
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //main users data
    String testUserSurname = 'Baranowski'
    String gmailTestEmail = 'emailwybitnietestowy@gmail.com'
    String gmailTestEmailPassword = 'Delete777'

    String specsaverUser = 'Michelle.Sanders@Specsavers.com'
    String specsaversPassword = 'JztQBZUgSS'

    String paypalUser = 'gwidel-buyer@pgs-soft.com'
    String paypalPassword = 'khA7$3a0p1kQ'
    String paypalUserName = 'test buyer'

    String testUserEmailNotExisting = 'emailWhichNotExistInDatabase@gmail.com'
    String testUserEmailNotActivated = 'inactiveuser@gmail.com'
    String testUserEmailInvalid = 'invalidEmailAddress'
    String wrongPassword = 'wrongPassword'

    String specsaversPartnerId = '100000'

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //epoints home page link
    //QA
    String epointsLink = 'https://qa.epoints.com/'
    //Stag
    //String epointsLink = 'https://stag.epoints.com/'
    //local
    //String epointsLink = 'http://10.10.30.246:8911/'
    //live
    //String epointsLink = 'https://www.epoints.com/'
    //window tags
    String changePasswordTag = 'Reset your password | epoints'
    String transitionPageTitle = 'Sending to retailer | epoints'
    String joinPageTag = 'Join epoints | epoints'
    String joinPageConfirmationTag = 'Confirm your email address | epoints'
    String forgotPasswordPageTag = 'Forgotten your password | epoints'
    String azPageTitle = 'Get epoints from thousands of retailers | epoints'
    String createPasswordTitle = 'Complete your epoints profile | epoints'
    String allDonePageTitle = 'You\'re officially an epoints member | epoints'
    String rewardsPageTitle = 'Start spending your epoints | epoints'
    String cookiePolicyPageTitle = 'Cookie policy | epoints'
    String privacyPolicyPageTitle = 'Privacy policy | epoints'
    String termsAndConditionsPageTitle = 'Terms & Conditions | epoints'
    String contactUsPageTitle = 'Contact us | epoints'
    String faqPageTitle = 'FAQ | epoints'
    String aboutEpointsPageTitle = 'About epoints | epoints'
    String partnersPageTitle = 'Partner with Us | epoints'
    String emailChangeConfirmationPageTitle = 'Your email has been changed | epoints'
    String specsaversPartnerPageTitle = 'Case studies | epoints'

    String returnIndividualRedemptionPageTitleText(String redemptionName) {
        return "Spend your epoints on " + redemptionName + " | epoints"
    }
    String myAccountRewardsHistoryPageTitle = 'Rewards history | epoints'
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //window urls
    String videojugURL = 'http://www.videojug.com/'
    String changePasswordURL = 'reset'
    String deeplinkBabyworldURL = '/deeplink/babyworld'
    String specsaversURL = '/admin/specsavers'
    String spendURL = 'rewards'
    String browseRewardURL = 'spend/list'
    String getPageURL = 'points/online'
    String shopOnlineURL = 'points/online'
    String playGamesURL = 'points/play'
    String watchVideosURL = 'points/watch'
    String goInStoreURL = 'points/instore'
    String inviteFriendURL = 'points/friends'
    String bigdlURL = 'web.bigdls.com'
    String bigdlPromoURL = 'https://www.bigdl.com/deals/'
    String myAccountURL = 'my-account'
    String myAccountRewardsHistoryURL = 'my-account/rewards'
    String purchaseEpointsURL = 'my-account/buy-epoints'
    String joinURL = 'join'
    String forgotPasswordURL = 'forgot'
    String aboutUsURL = 'about'
    String aboutEpointsURL = 'about'
    String partnersURL = 'partners'
    String partnersSpecsaversURL = 'partners/specsavers'
    String partnersSpecsaversImportURL = 'partners/specsavers/import'
    String partnersSpecsaversReportingURL = 'admin/specsavers/reports'
    String partnersSpecsaversReportingAwardedURL = 'admin/specsavers/reports/epoints-awarded'
    String partnersSpecsaversReportingRedeemedURL = 'admin/specsavers/reports/epoints-redeemed'
    String cookiePolicyURL = 'cookie-policy'
    String privacyPolicyURL = 'privacy'
    String termsAndConditionsURL = 'terms'
    String contactUsURL = 'contact-us'
    String faqURL = 'faq'
    String yourOnlineChoicesURL = 'http://www.youronlinechoices.eu/'
    String allaboutcookiesURL = 'http://www.allaboutcookies.org/'
    String googlePrivacyURL = 'https://www.google.com/policies/technologies/ads/'
    String epointsLiveURL = 'https://www.epoints.com/'
    String emailChangeConfirmationPageURL = 'mail/activate/'
    String chekoutRewardsURL = 'checkout/rewards'
    String chekoutSummaryURL = 'checkout/summary'
    String chekoutDeliveryURL = 'checkout/delivery'
    String chekoutCompleteURL = 'checkout/complete'

    String returnJoinOkURL(String email) {
        return epointsLink + 'join/ok?email=' + email
    }
    String epointsYoutubeVideoURL = 'https://www.youtube.com/watch?v=VDvXZ12dEEE'
    String externalSpecsaversURL = 'http://www.specsavers.com/'
    String voucherPageURL = 'vouchers'
    String signInPageURL = 'sign-in'
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //page used texts
    //transition page
    String transitionInformationModalHeader = 'Collect epoints as you shop and get free rewards!'
    String transitionPageModalDoNotShowInformation = 'Don\'t show me this again'
    String transitionPageEmailAddressLabel = 'Email address'
    String transitionPagePasswordLabel = 'Password'
    String transitionPageLoginEmailAlert = 'Email address is required'
    String transitionPageLoginIncorrectEmailAlert = 'Email address is invalid'
    String transitionPageLoginPasswordAlert = 'Password is required'
    String transitionPageLoginAuthorizationFailedAlert = 'Authorization failed.'
    String transitionPageJoinInformation = 'Don\'t have an account? Join now'
    String transitionPageContinueAnywayInformation = 'You will not earn epoints if you continue anyway. Sign in to earn epoints'
    //deeplink generator
    public String informationAboutNotFoundMerchant = 'Invalid request, merchant not found'
    //az page
    String returnInformationAboutNotFoundRetailer(String retailer) {
        return "Sorry! We can't find " + retailer + ". Try again or browse the A-Z stores"
    }

    String returnEpointsTooltipInformation(String epointsValue) {
        return "You will earn " + epointsValue + " epoints per "
    }
    String azFacetName = 'Stores A-Z'
    String departmentFacetName = 'Departments'
    String recentlyVisitedRetailersHeader = 'Recently visited retailers'
    String favouritesTooltipInfoNotLogged = 'You need to sign in to add your favourite retailer'
    String favouritesTooltipInfoLogged = 'Add to my favourites'
    String favouritesTooltipInfoLogged2 = 'Remove from  my favourites'
    String voucherTooltipInfo = ' voucher available for this store'
    String[] azPageSortOptions = ["Relevance", "A-Z", "Z-A", "epoints low to high", "epoints high to low"]
    //sign in/Join
    String joinConfirmationMessage = 'Please check your inbox and click the confirmation button.'
    String loginModalHeader = 'Sign in'
    //voucher page
    String departmentFacetNameOnVouchers = 'Choose Department'
    String merchantFacetNameOnVouchers = 'Popular Retailers'
    String categoryFacetNameOnVouchers = 'Category'
    String threeCategoriesSelectedBreadcrumbText = 'Categories: 3 selected'
    String threeMerchantsSelectedBreadcrumbText = 'Merchants: 3 selected'
    String voucherCodeHelpText = 'Get voucher code'
    String[] voucherPageSortOptions = ["Relevance", "Expiring soonest", "Expiring latest"]
    String goToretailerButtonText = 'Go to retailer'
    String openSiteButtonText = 'Open site'
    //share windows
    String twitterURL = 'https://twitter.com'
    String facebookURL = 'https://www.facebook.com'
    String googleURL = 'https://plus.google.com'
    // create password page
    String completePageHeader = 'Complete your account'
    String[] completePageAlerts = ["Password is required", "Confirm password is required", "Passwords are different", "First name is required", "Last name is required"]
    String[] completePageLabels = ["Password", "Confirm password", "First name", "Last name", "Gender"]
    String allDoneViewHeader = 'All done'
    String confirmationFailedMessage = 'Confirmation failed'
    String confirmationLinkInvalidMessage = 'Your confirmation link is invalid.'
    //spend page
    String returnSpendPageHeaderText(String pointsNumber) {
        return "We now have " + pointsNumber + " rewards!"
    }
    String departmentsTabName = 'Browse by Departments'
    String rewardsByEpointsTabName = 'Rewards by epoints total'
    String[] departments = ["Books", "Fashion", "Home & Garden", " Health & Beauty", "Baby & Child", "Sports & Outdoors", "Computer & Office", "Music Film & Gaming", "Toys & Games", "Electronics", "Gifts", "Travel"]
    //individual redemption page
    String basketModuleTitle = 'Get this reward for'

    String returnBasketPreviewQuantityInformation(String quantity) {
        if (quantity.equals('1')) {
            return quantity + " reward selected"
        } else {
            return quantity + " rewards selected"
        }
    }

    String returnBasketPreviewContentQuantityInformation(String quantity) {
        return "Quantity " + quantity
    }
    String itemsCounterTextForLoggedUser = 'items available for you to claim now'
    String indRedemptionSelectModuleRewardSelectedText = 'Reward selected view all your selected rewards'
    String spendPageBanner1ColumnText = 'over 1 million products to choose from'
    String spendPageBanner2ColumnText = 'all prices include delivery and fees'
    String spendPageBanner3ColumnText = 'delivered to all UK and Irish addresses'
    //home page
    String getEpointsSectionTitle = 'get epoints for shopping online'
    String getEpointsSectionText = "one reward across1000's of online storesjust sign into epointsand start shopping"
    String getEpointsInStoreSectionTitle = 'get epoints for shopping in-store'
    String getEpointsInStoreSectionText = "available across hundredsof high street retailersyou can now build up yourepoints in your local shoplearn more"
    String getEpointsForPlayingGamesSectionTitle = 'get epoints for playing games'
    String getEpointsForPlayingGamesSectionText = 'that\'s right, we give youepoints for playing gamesworks on mobile or pc'
    String getEpointsVideosSectionTitle = 'get epoints for watching videos'
    String getEpointsVideosSectionText = "with hundreds of greatvideos to choose fromyou'll be spoiled for choice."
    String spendEpointsEasilySectionTitle = 'spend all your epoints easily'
    String spendEpointsEasilySectionText = "over 1 million products all prices include delivery and fees delivered to all UK and Irish"
    String joinModuleEmailPlaceholder = 'Enter your email'
    String signInModulePasswordPlaceholder = 'Enter your password'
    String joinModuleButtonText = 'Join for free'
    String signInModuleButtonText = 'Sign in'
    //sign in section
    String signInPanelEmailLabel = 'Email address'
    String signInPanelEmailPlaceholder = 'Enter your email address'
    String signInPanelEmailPlaceholderNotAngular = 'email'
    String signInPanelPasswordLabel = 'Password'
    String signInPanelPasswordPlaceholder = 'Enter your password'
    String signInPanelPasswordPlaceholderNotAngular = 'password'
    String signInPanelEmailRequiredAlert = 'Email address is required'
    String signInPanelPasswordRequiredAlert = 'Password is required'
    String signInPanelEmailInvalid = 'Email address is invalid'
    String signInPanelAuthorizationFailed = 'Authorization failed.'
    String signInPanelFacebookButonPlaceholder = 'Sign in with facebook'
    //Join section
    String joinHeader = 'Join epoints'
    String joinMainText = 'Just pop your email address in the box below and you can start earning epoints.'
    String joinEmailAddressLabel = 'Email address'
    String joinTellMeMoreModalText = 'epoints rewards you for things you’re doing online already. So every time you shop with one of our 2,000 retailers or interact with some of our partner websites you can get epoints. You can then spend those epoints on treats from a choice of more than one million products!We never stop adding new retailers and partners, so you\'ll always find something that\'s right for you.'
    String joinEmailAddressIsRequiredAlert = 'Email address is required'
    String joinEmailAddressIsInvalidAlert = 'Email address is invalid'
    String joinUsernameAlreadyTakenError = 'User already registered.'

    String returnJoinOkTextOveral(String quantity) {
        return 'You\'ve got ' + quantity + ' epoints so far'
    }

    String returnJoinSignOutModalText(String email) {
        return 'Account not verified If you sign out now, you will need to follow the link in the verification email before you can log in again. Resend verification email to ' + email + ' Ok Sign outCancel Escape or Close'
    }

    String returnJoinSignOutModalTextAngular(String email) {
        return 'Account not verified If you sign out now, you will need to follow the link in the verification email before you can log in again. Resend verification email to ' + email + ' Ok Sign out Cancel'
    }
    String resendEmailConfirmationSuccessAlert = 'Email re-sent!'
    //watch video page
    String watchVideoMainHeader = 'Get epoints for watching videos'
    String watchVideoMainDescription = 'Ever spent 30 seconds watching a viral video and laughed your socks off? What would be more fun? Being rewarded with epoints in the process, that’s what. That’s why we have teamed up with the fantastic video website videojug.com so you can now earn epoints for simply watching videos.'
    String watchVideo1SectionHeader = 'What epoints can I get?'
    String watchVideo1SectionDescription = 'You’ll receive 20 epoints for creating an account on Videojug.com and a further 10 epoints if you opt-in to their newsletter to which keeps you connected to the hottest videos. All this before you even start getting rewarded for watching great videos.'
    String watchVideo2SectionHeader = 'How does it work?'
    String watchVideo2SectionDescription = 'Register on Videojug.com by clicking on the top right of their website. Be sure to use the same email address associated with your epoints account and the extra epoints will be added straight away.Once you’re registered all you need to do now is watch videos and earn epoints for every 3rd video you watch – simple.'
    String watchVideo3SectionHeader = 'What is videojug?'
    String watchVideo3SectionDescription = 'Videjug.com is one of the biggest and best websites for great video content. They have been around since 2006 and have millions of people visit their website each month to watch fun and interesting videos. What are you waiting for?'
    //instore page
    String instoreMainHeader = 'Get epoints for shopping in-store'
    String instoreMainDescription = 'Ever thought it would be nice to earn reward points across all the shops that you buy from both on web and out on the high street? Well we thought that would be nice so now epoints is available to collect in over 70,000 high street stores thanks to a great new app called bigDL.'
    String instore1SectionHeader = 'What epoints can I get?'
    String instore1SectionDescription = 'bigDL will reward you 10 bonus epoints just for downloading this free app. Every time you shop at any of the retailers listed just follow the simple steps and you will pick up epoints.'
    String instore2SectionHeader = 'How does it work?'
    String instore2SectionDescription = 'Download bigDL from either Google Play or Apple Store and register using the same email address associated with your epoints account. Every time you shop at any of the retailers listed within bigDL follow the steps within the app to collect epoints.'
    String instore3SectionHeader = 'What is bigDL?'
    String instore3SectionDescription = 'bigDL is the easiest way to save money on your favourite brands. This clever app finds hundreds of deals on the high street with up to 80% off in shops near you.'
    //play games page
    String clumsyBirdGetText = 'Get epoints for playing Clumsy Bird'

    String returnClumsyBirdInstructionText(String gapsNumber, earnValue) {
        return 'Tap, click or hit space to move the bird up, let go and it drops. Fly through the gaps to progress. Get ' + earnValue + ' when you fly through ' + gapsNumber + ' gaps.'
    }
    String signInToearnEpointsInfo = 'Sign in to earn epoints!'
    //forgot password page
    String forgotPasswordHeader = 'Forgot your password?'
    String forgotPasswordMainText = 'Enter the email address associated with your epoints account below, we\'ll send you a link to reset your password.'
    String forgotPasswordEmailLabel = 'Email address'
    String forgotPasswordEmailIsInvalidAlert = 'Email is invalid'
    String forgotPasswordEmailIsRequiredAlert = 'Email is required'
    String forgotPasswordUserDoesNotExistsAlert = 'User with given email doesn\'t exist.'
    String forgotPasswordEmailSentHeader = 'Email sent!'
    String forgotPasswordEmailSentDescription = 'Follow the link in your email.'
    //change password page
    String changePasswordSuccessAlert = 'Password changed!'//'Password successfuly changed!'
    String changePasswordHeader = 'Change your password'
    String changePasswordPasswordLabel = 'Password'
    String changePasswordConfirmPasswordLabel = 'Confirm password'
    String changePasswordisRequiredAlert = 'Password is required'
    String changeConfirmPasswordisRequiredAlert = 'Confirm password is required'
    String changePasswordDoNotMatchAlert = 'Passwords do not match'
    String changePasswordInvalidToken = 'Invalid token.'
    //specsavers page
    String specsaversLoginHeader = 'Specsavers Control Panel'
    String specsaversEmailLabel = 'Email address'
    String specsaversPasswordLabel = 'Password'
    String specsaversEmailPlaceholder = 'Enter your email address'
    String specsaversPasswordPlaceholder = 'Enter your password'
    String specsaversEmailIsRequiredAlert = 'Email address is required'
    String specsaversPasswordIsRequireAlert = 'Password is required'
    String specsaversEmailIsInvalidAlert = 'Email address is invalid'
    String specsaversControlPanelHeader = 'Specsavers Control Panel'
    String specsaversAuthorizationAlert = 'Authorization failed.'
    String specsaversAwardPointsTab = 'Award points'
    String specsaversImportTab = 'Import'
    String specsaversSelectBranchesLabel = 'Select branches'
    String specsaversReasonForAwardingLabel = 'Reason for awarding points'
    String specsaversPointsToAwardLabel = 'Points to award'
    String specSaversPickBranchesIsRequiredAlert = 'Select branches is required'
    String specSaversUseSpecificTagIsRequiredAlert = 'Use specific tag is required'
    String specsaversReasonForAwardingPointsIsRequiredAlert = 'Reason for awarding points is required'
    String specsaversPointsToAwardIsRequiredAlert = 'Points to award is required'
    String specsaversPointsToAwardIsInvalidAlert = 'Points to award is invalid'
    String specsaversPickBranchesPlaceholder = 'Pick branches'
    String specsaversReasonForAwardingPlaceholder = 'Pick or type reason for awarding points'
    String specsaversConfirmModalHeader = 'Please confirm'

    String returnSpecsaversConfirmModalMainText(String poundsValue, String numberOfBranches, String pointsTotal) {
        return 'Are you sure you want to award' + poundsValue + ' to ' + numberOfBranches + ' branches totaling ' + pointsTotal + ' points?'
    }
    //specsavers reporting
    String specsaversReportsTabStartDateLabel = 'Start date'
    String specsaversReportsTabEndDateLabel = 'End date'
    String specsaversReportsTabOverviewSection = 'Overview'
    String specsaversReportsTabAwardedSection = 'epoints awarded'
    String specsaversReportsTabRedeemedSection = 'epoints redeemed'
    String specsaversReportsAwardedDateLabel = 'Date / time'
    String specsaversReportsAwardedEpointsLabel = 'epoints'
    String specsaversReportsAwardedValueLabel = 'Value'
    String specsaversReportsAwardedIssuedByLabel = 'Issued by'
    String specsaversReportsAwardedAwardedToLabel = 'Awarded to'
    String specsaversReportsAwardedIncentiveLabel = 'Incentive'
    String specsaversReportsAwardedReasonLabel = 'Reason'
    String specsaversReportsRedeemedDateLabel = 'Date / time'
    String specsaversReportsRedeemedEpointsLabel = 'epoints'
    String specsaversReportsRedeemedValueLabel = 'Value'
    String specsaversReportsRedeemedSpentByLabel = 'Spent by'
    String specsaversReportsRedeemedRedemptionItemLabel = 'Redemption item'
    String specsaversReportsRedeemedQuantityLabel = 'Quantity'
    String specsaversReportsRedeemedUrlLabel = 'Url'
    String specsaversReportsOverviewEPAwardedLabel = 'epoints awarded'
    String specsaversReportsOverviewAcAwardedLabel = 'Accounts awarded to'
    String specsaversReportsOverviewValueOfEPLabel = 'Value of epoints awarded'
    String specsaversReportsOverviewEPRedeemedLabel = 'epoints redeemed'
    String specsaversReportsOverviewNumberOfRedLabel = 'Number of redemptions'
    String specsaversReportsOverviewValueOFRedLabel = 'Value of redemptions'
    String specsaversReportsCalendarALert = 'Please specify correct date range. Recommended date format is DD-MM-YYYY.'
    //specsavers store
    String specsaversStoreHeader = 'Add new store'
    String specsaversStoreNameLabel = 'Name'
    String specsaversStoreEmailLabel = 'Email'
    String specsaversStoreInvalidEmailAlert = 'Email format is invalid'
    String specsaversStoreEmailAlreadyInUseAlert = 'Email already used.'
    String specsaversStoreNameAlreadyInUseAlert = 'Name for store already used in this region.'
    String newStoreName = 'newStoreNameAutomatedTest'
    String newStoreEmail = 'newStoreEmailAutomatedTest'
    String termsAndConditionsPageContent = 'TermsandConditionsYouragreementepointsisarewardprogrammeoperatedbyEPOINTSREWARDSLIMITEDacompanyincorporatedinEnglandandWaleswithitsregisteredofficeat52-54HighHolborn,WC1V6RLwithcompanyregistrationnumber07453502ByvisitingthisWebsiteandparticipatingintheepointsprogramme,youagreetobeboundbyandtocomplywiththeseTermsandConditions,ourPrivacyPolicyandanyotherrulesnotedonthisWebsite.WemayfromtimetotimechangetheseTermsandConditions.Wewillalwaysendeavourtonotifyyouofanymajorchanges,butwerecommendthatyoucheckthispagefromtimetotimeforupdates.EarningandredeemingepointsintheProgrammewillconstituteacceptanceoftherevisedTermsandConditions.DefinitionsForthepurposesoftheseTermsandConditions:"epoints"meanstherewardpointsthatcanbecollectedandredeemedinaccordancewiththeseTermsandConditions;"Member",or"you"meansanyindividualregisteredontheProgramme;"Partner"meansanythirdpartythatenablesaMembertoregisterwiththeProgramme;"Programme"meanstherewardprogrammeoperatedundertheseTermsandConditionswhereMemberscanearnepointsbypurchasingspecifiedgoodsand/orservicesfromRetailersorbyspecifiedonlineinteractionsontheWebsite;"RedemptionCompany"meansanythirdpartythatsuppliesgoods,services,vouchers,discountsorotheritemstoaMemberfollowingtheredemptionofepoints;"Retailers"meansanythirdpartythatsellsgoodstoMembersthatgenerateepointsforsuchMembersthroughtheProgramme;"we,us,our"meansepointsRewardsLimited;and"Website"meansthewebsitelocatedatepoints.comRegistrationtotheProgrammeInordertoparticipateintheProgramme,youmustbeaged18orover,holdavalidandoperationalemailaccountandhaveavalidUKpostaladdress.YoucanregisterwithusoroneofourPartners.ByregisteringwiththeProgramme,yourepresentandwarrantthat:youhavethelegalcapacitytoagreetotheseTermsandConditions;youareusingyourrealidentitytoregister;allinformationthatyouhaveprovidedisaccurateandup-to-date;andyouwillupdateasnecessaryalltheinformationyouhaveprovided.Ifwebelievethatyouhaveprovidedanyinformationthatisinaccurateornotup-to-date,wemayrefusetoregisteryouorotherwisesuspendorterminateyouraccesstotheProgramme.Youshouldkeepyouraccountinformationaccurateandup-to-date.SubjecttoCondition6above,onreceiptofyourregistrationdetail,weshallsetupanepointsaccounttorecordepointsearnedandredeemedbyyou.Youmayonlycollectandredeemepointsthroughyourownregisteredaccount.AdditionalMembersthatyoumaywanttointroducetotheProgrammemustregistertheirdetailswithusandstarttheirownmembershipaccount.AMembercannottransferepointsearnedtoanotherMember,eitherbysale,barterorotherwise.Youareresponsibleformaintainingtheconfidentialityofyourepointsaccount,andanyusernameand/orpassword.Youarealsoresponsibleforallactivitiesthatoccurunderyouraccountandaccordingly,youshallimmediatelynotifyusofanyunauthorizeduseofyouraccount.WecantakeanyactionweconsiderappropriateifwehavereasontobelievethatyouareabusingtheProgramme,includingwithholdingorreusingepointspreviouslycreditedtoyouraccount.EarningandRedeemingPointsepointscanbeearned:byMemberswhoareloggedontotheWebsite,searchforaproductthenlinktotherelevantRetailer’swebsiteandcompleteapurchaseofthatproduct.YoucannotcollectepointsifyouvisittheRetailer’swebsitedirectly,norifyoupurchaseaproductnotshownonourWebsite.ThenumberofpointstobeearnedfromaproductwillonlybeshownonourWebsite;andbytheappropriateuseofepointsrewardsandPartnermobileapplications.Ifyouearnepointsbyuploadingcontentandyousubsequentlyremovethatcontent,youmaylosetheepointsearned.We,ourPartnersandRetailersallreservetherighttoamendthenumberofepointsrewardedforinteractionsincludedintheProgramme.epointsareawardedbytheProgramme,andnotbyPartnersorRetailers.epointscannotberedeemeduntilcreditedtoaMemberaccount.Retailerstakedifferentperiodsoftimetonotifyuswhenordersforqualifyingpurchasesarecompleted,butingeneral,epointswillbeawardedtoaMemberaccount30daysfromthecompletionofaqualifyingpurchase.DuringthisperiodepointswillbedisplayedaspendingintheMember’saccountbalance.Pendingepointscantakeupto72hoursbeforebeingdisplayedwithintheMember’saccountbalance.epointsawardedtoMembersforthespecifiedonlineinteractionswillusuallybeawardedwithin48hoursandwillbesubjecttoourPartners’orRetailers’termsandconditions.AllpurchasesfromRetailersanditemsofferedbyRedemptionCompaniesaresubjecttoavailabilityandtoallapplicablelegalrulesandthetermsandconditions(includingbookingrequirements,cancellationrestrictions,returnconditions,warrantiesandlimitationsofliability)oftheRetailerorRedemptionCompanyasthecasemaybe.WearenotresponsiblefortheinaccuratecommunicationofepointscollectedthroughaPartnerorRetailersite.Whereepointsareredeemedforavouchercarryingaspecifiedcashdiscountorbenefit,thevouchermayhaveanexpirydatespecifiedonitandwillbesubjecttoanyothertermsandconditionsstatedonthevoucherorotherwiseimposedbytheRedemptionCompany.TransactionsonwhichepointsareearnedorredeemedaremadedirectlywiththerelevantRetailerandtheRedemptionCompany.RetailersandRedemptionCompaniesareindependentthirdpartiesandwearenotactingastheirprincipal,agentand/orbroker.AnyrelationshipwithanyRetailerand/orRedemptionCompanythroughtheProgrammeforthepurchaseofgoods,servicesorotheritemsissolelybetweenyouandtheRetailerorRedemptionCompanyinquestion.Wearenotliableforanycosts,claims,expenses,lossesordamagesthatyoumayincurarisinginanywayasaresultoforinconnectionwiththepurchaseofgoods,servicesorotheritemsfromaRetailerorRedemptionCompanyorasaresultofanyrelianceplacedbyyouonthecontentoftheWebsite.Inparticular,wearenotresponsibleandshallhavenoliabilityforanyloss,theftordamagetoanygoodssentbyaRedemptionCompany.Theallocationofepointsfordisputedand/orcancelledorreturneditemswillbedealtwithonacasebycasebasisatsupport@epoints.comandisentirelyatourdiscretion.WereservetherighttocloseanepointsaccountifaMemberhasnotearnedanyepointsontheiraccountduringany2yearperiod.IfaMemberclosestheiraccount,allepointswithinthataccountshallbelost.RedeemedepointsaredeductedatthepointwhereanorderisconfirmedbytheMemberandcannotbeusedagain.IfatransactiononwhichepointshavebeenissuedorredeemediscancelledwewillreversetheassociatedepointsmovementonceinstructedbytherelevantRedemptionCompany.Pointsforemailinvitesareforpersonalcontactsonlynotforuploadingmailinglists.TheProgrammeWewillusereasonableendeavourstoemailMembersfromtimetotimewithdetailsoftheepointsthattheyhaveearned.WemaysuspendorterminatetheProgrammebutwillgiveasmuchnoticeaswereasonablycanbeforewedoso.IfthishappensallMemberaccountswillbesuspendedorterminated.IfwesellortransfertheProgrammetoanothercompanywemaytransferallofourrightsandobligationsundertheseruleswithoutanyfurtherconsentandmaydiscloseortransferallinformationweholdaboutMemberstoaprospectiveoractualnewowner.SuchadisclosurewillbecommunicatedtoMemberswithin30daysofsuchatransactionbeingeffectedbylaw.PrizedrawsFromtimetotime,wemayrunfreeprizedrawsontheWebsite.EntryintoaprizedrawisgovernedbytheRulesofEntry.UseoftheWebsiteAllrightsinallmaterialandcontent(including,butnotlimitedto,text,images,webpages,lookandfeel,designandcompilation)intheWebsiteareownedbyusorourlicensors.YoumayviewtheWebsiteandyoumayprinthardcopiesofmaterialandcontentfromtheWebsiteforyourlawful,personal,non-commercialuse.Totheextentpermittedbylaw,allothercopying,whetherinelectronic,hardcopyorotherformat,isprohibitedandmaybreachintellectualpropertylawsandotherlawsworldwide.Furthermore,youarenotentitledtoreproduce,transmit,publiclyperform,distribute,adapt,translate,modify,bundle,merge,shareormakeavailabletoanyperson,orcreatederivativeworksofsuchmaterial,oruseitforcommercialpurposes,withoutourpriorwrittenconsent.Allotherrightsarereserved.YouacknowledgeandagreethatwehavenoresponsibilityfortheinformationprovidedbysitestowhichyoumaylinkfromtheWebsite.Linkstoothersitesdonotconstituteanendorsementbyorassociationwithusofsuchsitesorthecontent,products,advertisingorothermaterialspresentedonsuchsites.Wehavenocontroloverthesesitesanddonoteditormonitorthem.Wearenotresponsibleorliable,directlyorindirectly,foranydamage,lossorcostcausedorallegedtobecausedbyorinconnectionwithuseoforrelianceonanysuchcontent,goodsorservicesavailableonsuchsites.WhilstweendeavourtoensurethattheWebsiteisnormallyavailable24hoursaday,weshallnotbeliableifforanyreasontheWebsiteisunavailableatanytime.AccesstotheWebsitemaybesuspendedtemporarilyandwithoutnoticeinthecaseofsystemfailure,maintenanceorrepairorforanyotherreasonablecause.TheWebsiteisprovided"asis"andyouruseisatyourownrisk.We,ourofficers,directors,employeesandagentsdisclaim,tothefullestextentpermittedbylaw,allexpressandimpliedwarranties,including,withoutlimitation,anywarrantiesofquality,fitnessforaparticularpurpose,performance,titleandnon-infringement.WedonotwarrantthattheWebsitewillbefreefromviruses,available,accessible,error-free,uninterruptedorthatthecontentswillbeaccurate.IfyouaredissatisfiedwiththeWebsiteortheProgramme,yoursoleremedyistodiscontinueusingtheWebsiteandtheProgramme.GoverninglawTheseTermsandConditionsincludingthePrivacyPolicyandanyotherrulesnotedwithintheseTermsandConditionsshallbegovernedbyEnglishlawandthecourtsofEnglandandWalesshallhaveexclusivejurisdiction.ItistheresponsibilitytotheMembertodeclarerewardstotherelevanttax/anyotherauthorities.'
    String termsAndConditionssupportLink = 'mailto:support@epoints.com?subject=epoints%20queries%20on%20disputed,%20cancelled%20or%20returned%20item'
    //contact us page
    String contactUsPageHeader = 'Submit a request'
    String contactUsPageEmailLabel = 'Your email address'
    String contactUsPageSubjectLabel = 'Subject'
    String contactUsPageDescriptionLabel = 'Description'
    String contactUsPageDescriptionHelpText = 'Please enter the details of your request. A member of our support staff will respond as soon as possible.'
    //faq page
    String faqHeader = 'Frequently asked questions'
    //about  epoints page
    String aboutEpointsFirstSectionHeader = 'What is epoints?'
    String aboutEpointsFirstSectionText = 'epoints is a rewards scheme where you can collect the epoints currency via a range of simple everyday activities. Shop online and across the high street at your favourite retailers, play games, invite friends, interact with us on social media and more. epoints can be spent on the widest range of rewards in the world.'
    String aboutEpointsSecondSectionHeader = 'Collect anywhere'
    String aboutEpointsSecondSectionText = 'Unlike with many other reward schemes, epoints members are not tied to just one brand and you don\'t have to worry about multiple cards and logins. You can collect epoints across 1000s of online retailers and 85,000+ physical stores, via the bigdl app.'
    String aboutEpointsThirdSectionHeader = 'Spend on anything'
    String aboutEpointsThirdSectionText = 'Spend epoints on the widest range of rewards in the world. From vouchers and experiences to beauty and electronics, there\'s something for everyone. You\'ll get 50 epoints just for signing up, at least 1 epoints per £1 spent at your favourite retailers and you\'ll even get epoints for logging in. With rewards from just under 250 epoints, your first reward is never far away.'
    String aboutEpointsFourthSectionHeader = 'Get started'
    String aboutEpointsFourthSectionText = 'Sign up today and collect 50 epoints towards your first reward.'
    String aboutEpointsFourthSectionText2 = 'Sign up'
    //partners page
    String partnerPagePartnerWithUsText = 'Partner with UsWant to incentive your user base, reward loyalty and monetize your site? You might want to think about integrating an epoints store and/or Rewards on your website or blog. Some of the benefits includeHigher page impressionsHigher click-through rateLower bounce rateHigher returning visitorsHigh level of revenueTop brand association'
    String partnePageSubmitRequestText = 'Submit a request for assistanceFields marked with an asterisk * are mandatory. You\'ll be notified when our staff answers your request.'
    String partnerPageFormHeader = 'Business Details'
    String partnerPageFirstNameLabel = 'First Name'
    String partnerPageSurnameLabel = 'Surname'
    String partnerPageCompanyNameLabel = 'Company Name'
    String partnerPageEamilLabel = 'Email'
    String partnerPageWebsiteAddressLabel = 'Website Address'
    String partnerPageAlertSuccessText = 'Thank you for registration! We will respond to you quickly.'
    String partnerPageEmailIsInvalidAlert = 'Email is invalid'
    String partnerPageSurnameIsRequiredAlert = 'Surname is required'
    String partnerPageCompanyNameIsRequiredAlert = 'Company Name is required'
    String partnerPageEmailIsRequiredAlert = 'Email is required'
    // zone picker
    String zonePickerPanelHeader = 'Select your country'
    String zonePickerUK = 'United Kingdom'
    String zonePickerIreland = 'Ireland'
    //cookie panel
    String cookiePanelText = 'Welcome to epoints.We use cookies on this website to ensure you get the best and most relevant experience.If you continue we assume you consent to receive all cookies on the epoints website.'
    //your account profile tab email row
    String profileTabCurrentEmailLabel = 'Email address'
    String profileTabNewEmailLabel = 'New email address'
    String profileTabRetypeNewEmailLabel = 'Re-enter new email address'
    String profileTabEmailIsInvalidAlert = 'Email address is invalid'
    String profileTabEmailIsRequiredAlert = 'Email field is required'
    String profileTabEmailConfirmationIsRequiredAlert = 'Confirm email field is required'
    String profileTabEmailIsNotMatchAlert = 'Emails do not match'
    String profileTabSuccessAlert = '×Follow confirmation link in your email to verify it.'
    String profileTabEmailsMatchAlert = 'Emails match'
    String profileTabNewEmailHasBeDifferentInfo = 'New email has to differ from existing one'
    String profileTabEmailUserInfo = 'Email already used.'

    //your account profile tab password row
    String profileTabPasswordSetDateLabel = 'Password'
    String profileTabPasswordSetDateText = 'Password was set on'
    String profileTabCurrentPasswordLabel = 'Current password'
    String profileTabNewPasswordLabel = 'New password'
    String profileTabConfirmPasswordLabel = 'Confirm new password'
    String profileTabCurrentPasswordIsRequiredAlert = 'Current password field is required'
    String profileTabNewPasswordIsRequiredAlert = 'New password field is required'
    String profileTabNewPasswordConfirmationIsRequiredAlert = 'Confirm password field is required'
    String profileTabPasswordsDoNotMatchAlert = 'Passwords do not match'
    String profileTabWrongCurrentPasswordMessage = '×Given password doesn\'t match your existing password'
    String profileTabSuccessPasswordAlert = '×Password successfully changed!'
    String profileTabPasswordsMatchAlert = 'Passwords match!'
    //your account profile tab personal details row
    String personalDetailsTabFirstNameLabel = 'First name'
    String personalDetailsTabLastNameLabel = 'Last name'
    String personalDetailsTabTitleLabel = 'Title'
    String personalDetailsTabGenderLabel = 'Gender'
    String personalDetailsTabDateOfBirthLabel = 'Date of birth'
    String personalDetailsInvalidDateFormatAlert = 'Required format: dd/mm/yyyy'
    String personalDetailsSuccessAlert = 'Data successfully changed!'
    //your account contact details tab
    String contactDetailsContactNumberLabel = 'Contact number'
    String contactDetailsAddressLabel = 'Address'
    String contactDetailsHouseLabel = 'House number or name'
    String contactDetailsStreetLabel = 'Street'
    String contactDetailsTownLabel = 'Town / City'
    String contactDetailsCountyLabel = 'County'
    String contactDetailsCountryLabel = 'Country'
    String contactDetailsPostcodeLabel = 'Postcode'
    //your account purchase epoints tab
    String purchaseEpointsHeader = 'Convert real money to epoints value'
    String purchaseEpointsMainFormText = 'How many epoints do you want to purchase?'
    String purchaseEpointsValueFieldLabel = 'Amount'
    String purchaseEpointsVATFieldLabel = 'VAT 20%'
    String purchaseEpointsFeeFieldLabel = 'Fee 5%'
    String purchaseEpointsTotalFieldLabel = 'Total'
    String purchaseEpointsMainFormTextSecondScreen = 'Check the details of you order. Press confirm button to open online payment.'
    // rewards history page on user account
    String rewardsHistoryPageHeader = 'Rewards history'
    String rewardsHistoryPageColumnLabelDate = 'Date'
    String rewardsHistoryPageColumnLabelReward = 'Reward'
    String rewardsHistoryPageColumnLabelDD = 'Delivery Details'
    String noRewardsYetInfo = 'No rewards yet.'
    //email address changed confirmation page
    String returnEmailAddressChangeConfirmationPageText(email) {
        return 'Email address changed Thank you. Your epoints account email address has now been changed to ' + email + ' You will now need to use this address when you sign in to epoints. You can see this change in the my profile page of your account. Account dashboard Earn more epoints'
    }
    //your account purchase epoints
    String returnSuccessfullPaymentInfo(epointsValue) {
        return "Success!You've bought " + epointsValue + " epoints"
    }
    String failurePaymentInfo = 'Payment failure Something went wrong with your payment.Please try again or contact us.'
    String redirectPaymentInfo = 'You\'ve been redirected to PayPal payments. Click here to open the window manually.'
    String paymentDetailsHeader = 'Your payment details:'
    String paymentDetailsDateLabel = 'Date'
    String paymentDetailsPaymentStatusLabel = 'Payment status'
    String paymentDetailsPayerNameLabel = 'Payer name'
    String paymentDetailsPayerEmailLabel = 'Payer email'
    String paymentDetailsCurrencyLabel = 'Currency'
    String paymentDetailsValueLabel = 'Value'
    String paymentDetailsFeeLabel = 'Fee'
    String paymentDetailsTaxLabel = 'VAT'
    String paymentDetailsTotalLabel = 'Total'
    //checkout selected rewards
    String checkoutStep1 = 'Selected rewards'
    String checkoutStep2 = 'Delivery details'
    String checkoutStep3 = 'Order summary'
    String checkoutStep4 = 'Complete'
    String selectedRewardsHeader = 'Selected rewards'

    String returnTotalEpointsNeededInfo(pointsValue) {
        return 'Total epoints needed' + pointsValue
    }
    String deliveryDetailsHeader = 'Delivery details'

    String returnNotEnoughPointsAlert(pointsValue) {
        return '!You are still ' + pointsValue + 'away from these rewards'
    }
    String removeRemptonPopupQuestion = 'Are you sure you want to remove all items from your basket?'
    String noProductInBasketInformation = 'You currently do not have any rewards selected.'
    //checkout delivery details
    String deliveryNameLabel = 'First name'
    String deliveryLastNameLabel = 'Last name'
    String deliveryHouseNumberLabel = 'House number or name'
    String deliveryStreetLabel = 'Street'
    String deliveryTownLabel = 'Town / City'
    String deliveryCountyLabel = 'County'
    String deliveryPostcodeLabel = 'Postcode'
    String deliveryCoutryLabel = 'Country'
    String deliveryContactLabel = 'Contact number'

    String deliveryNameRequiredAlert = 'First name is required'
    String deliveryLastNameRequiredAlert = 'Last name is required'
    String deliveryHouseNumberRequiredAlert = 'House number or name is required'
    String deliveryStreetRequiredAlert = 'Street is required'
    String deliveryTownRequiredAlert = 'Town / City is required'
    String deliveryCountyRequiredAlert = 'County is required'
    String deliveryPostcodeRequiredAlert = 'Postcode is required'
    String UKPostcode = 'DN55 1PT'
    //checkout order summary
    String orderSummaryHeader = 'Order summary'
    //complete order
    String completeOrderHeader = 'Complete'
    String completeThankYouText = 'Thank You. Your order is now being processed and will be with you soon!If you want to find out more about deliveries have a look at our FAQs.'
    //specsavers partner page
    String specsaversPartnerPageMainText = 'Give epoints to reward performanceOrganisations large and small offer incentives to reward their staff and use a variety of prizes from cash, to cupcakes, to ipads. A key challenge is delivering a reward that is easy to administer, simple and attractive enough for the recipient.Specsavers, have chosen epoints to further improve their incentive programmes. Jill Clark, Specsavers Director Customer Services says, “Before switching to epoints we had numerous incentives available for our Partners who run Specsavers stores in the UK and Ireland. We conducted some research and found Partners wanted their teams to be able to choose their own rewards and that could be used for any occasion.” Matt Norbury, epoints Founder says, “The partnership with Specsavers made perfect sense as they were looking for a reward mechanism that was flexible to be used for different incentives but that is simple to operate.”What do stores get?Specsavers, like any successful organisation understand what measures within the business drive success but unlike most organisations have defined the exact level of reward to allocate to each measure. Everything from financial to personal development targets will be included ensuring store staff are rewarded for the things that matter in a meaningful way.How does it work?Specsavers manage the allocation of epoints from a central administration portal so they can reward store teams in real time based upon each incentive campaign. epoints hit store accounts instantly so Partners and their teams can browse the millions of items available within the ‘spend’ section of epoints.com. The epoints redemption range is one of the biggest in the world where items can be delivered direct to store.About SpecsaversSpecsavers is a partnership of almost 2,000 locally owned and run businesses in Europe and across the globe, all committed to delivering high quality, affordable optical and hearing care.Our expert opticians carry out professional eye tests using the latest optical equipment. Our range of glasses features an incredible variety of styles, including the latest designs from some of Europe’s top fashion houses, and all our frames come with top quality PENTAX lenses. We\'re renowned for offering incredible value for money, including special offers like two for one glasses.Many of our stores also provide a hearing service with fully-trained audiologists offering comprehensive hearing tests and the latest digital hearing aids. Just choose your country to find out more about Specsavers.'
    //invite friend
    String inviteFriendBoldText = 'Invite friendsand you get 50'
    String inviteFriendJoinText = 'Join now and start collecting points. Already registered? Sign in'
    String inviteFriendHowItWorks = 'How it works?Simply invite your friends, family and whoever else you want by Facebook. You will get 50 epoints when the friend completes registration. This will be capped to 10 per month. They can refer as many friendsas they like in a month but will only get 50 points for the first 10 that complete registration. You can always check how many of your friends joined.'
    String inviteFriendCopyInvitationLinkText = 'copy your unique invitation link and share it with your friends'

    String returnUniqueInvitationLink(referrerId) {
        return "epoints.com/join?referrer=" + referrerId + "&accessType=sharing"
    }
    String inviteFriendOrSimplyText = 'or simplyshare your invitation and Post it on your timeline'
    String inviteFriendFacebookPost = 'Join today and become the latest epoints member - enjoy a lifetime of rewards'
    //specsavers bulk upload
    String bulkUploadDropBoxText = 'Drop .xls or .xlsx here or click to upload'
    String bulkUploadAlertSuccess = 'Importing finished. Epoints awarded successfully.'
    String bulkUploadWrongFormatAlert = 'File extension not allowed. Upload .xls or .xlsx file.'
    String bulkUploadMissingNameAlert = 'Some rows are incorrect,pleasefixitandtryagain. Error details: Row 2 Store name - Empty field'
    String bulkUploadMissingEmailAlert = 'Some rows are incorrect,pleasefixitandtryagain. Error details: Row 2 Store Email - Empty field'
    String bulkUploadMissingReasonAlert = 'Some rows are incorrect,pleasefixitandtryagain. Error details: Row 2 Reason (255 char max) - Empty field'
    String bulkUploadMissingAmountAlert = 'Some rows are incorrect,pleasefixitandtryagain. Error details: Row 2 Amount - Empty field'
    String bulkUploadMissingAdminAlert = 'Some rows are incorrect,pleasefixitandtryagain.Error details:Row 2 Administrator - Empty field'
    String bulkUploadWrongEmailAlert = 'Some rows are incorrect,pleasefixitandtryagain.Error details:Row 2 Store Email - Such email was not registered for specsavers UK'
    String bulkUploadTooLowAmountAlert = 'Some rows are incorrect, please fix it and try again.Error details:Row 2Amount - Amount needs to be number greater or equals to 1'
    String bulkUploadWrongHeaderAlert = 'Column names are wrong. Proper order and names of column names are: Store name, Store Email, Reason (255 char max), Amount, Administrator, Tag.'
    String errorPageText = 'oops!Something went wrong thereWe can\'t seem to find the item you are looking for.Common problemsIf you typed in a URL, check there are no typos.If you clicked on a link, it may be broken and we\'ll look into that.Try something else?Our homepage is always a good place to start.'
}