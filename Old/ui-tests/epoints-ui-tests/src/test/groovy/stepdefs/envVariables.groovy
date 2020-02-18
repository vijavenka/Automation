package stepdefs
/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 18.09.14
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */
class envVariables {
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //main users data
    String testUserEmail = 'emailwybitnietestowy@gmail.com'
    String testUserPassword = 'Delete777'
    String testUserName = 'Krzysztof'
    String testUserSurname = 'Baranowski'
    String testUserCompanyName = 'KB Software'

    String specsaverUser = 'Michelle.Sanders@Specsavers.com'
    String specsaversPassword = 'JztQBZUgSS'

    String paypalUser = 'gwidel-buyer@pgs-soft.com'
    String paypalPassword = 'khA7$3a0p1kQ'
    String paypalUserName = 'test buyer'


    String testUserEmailNotExisting = 'emailWhichNotExistInDatabase@gmail.com'
    String testUserEmailNotActivated = 'inactiveuser@gmail.com'
    String testUserEmailInvalid = 'invalidEmailAddress'

    String specsaversPartnerId = '100000'
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //transition page link fo John Lewis used in transition page tests
    //QA
        String transitionLink = 'https://qa.epoints.com/transition?type=merchant&merchantId=b772bd02-94c4-4eff-a4d4-ab5f7f3d8d93&referrerUrl=http%3a%2f%2fqa.epoints.com%2f'
    //STAG
        //String transitionLink = 'https://stag.epoints.com/transition?url=%2fclickout%2fmerchant%3fid%3d7ca0113b-6925-4a9c-8d10-b3c3c0512ef9&referrerUrl=http%3a%2f%2fqa.epoints.com%2f'
    //local
        //String transitionLink = 'http://10.10.30.246:8911/transition?url=%2fclickout%2fmerchant%3fid%3d7ca0113b-6925-4a9c-8d10-b3c3c0512ef9&referrerUrl=http%3a%2f%2fqa.epoints.com%2f'
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
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //Database connection variables - uncomment proper according to tested environment
    //QA ePoints ///////////////////////////////////////////////////////////////////////////////////////////////////////
    public String dbUrlEP = "jdbc:mysql://test-proxy-qa-01.iatlimited.com:3308";
    public String userEP = "admin";
    public String passEP = "qGZ7zmhsu8Eb";
    //QA admin ///////////////////////////////////////////////////////////////////////
    public String dbUrlAD = "jdbc:mysql://test-proxy-qa-01.iatlimited.com:3307";
    public String userAD = "admin";
    public String passAD = "U70FXi1wdDgip3m";
    //QA new admin ///////////////////////////////////////////////////////////////////////
    public String dbUrlAD2 = "jdbc:mysql://test-proxy-qa-01.iatlimited.com:3309";
    public String userAD2 = "admin";
    public String passAD2 = "V2fPqKvDED0AHwt";
    //Stag epoints /////////////////////////////////////////////////////////////////////////////////////////////////////
    //public String dbUrlEP = "jdbc:mysql://test-proxy-stag-01.iatlimited.com:3308";
    //public String userEP = "admin";
    //public String passEP = "JLNp5SbtaGkHOVS";
    //Stag admin ////////////////////////////////////////////////////////////////////
    //public String dbUrlAD = "jdbc:mysql://test-proxy-stag-01.iatlimited.com:3307";
    //public String userAD = "admin";
    //public String passAD = "V2fPqKvDED0AHwt";
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //window tags
        String changePasswordTag = 'Reset your password | epoints'
        String transitionPageTag = 'Sending to retailer | epoints'
        String joinPageTag = 'Join epoints | epoints'
        String joinPageConfirmationTag = 'Confirm your email address | epoints'
        String forgotPasswordPageTag = 'Forgotten your password | epoints'
        String azPageTitle = 'Get epoints from thousands of retailers | epoints'
        String createPasswordTitle = 'Complete your epoints profile | epoints'
        String allDonePageTitle = 'You\'re officially an epoints member | epoints'
        String spendPageTitle = 'Start spending your epoints | epoints'
        String browseRewardsPageTitle = 'Find your epoints reward | epoints'
        String cookiePolicyPageTitle = 'Cookie policy | epoints'
        String privacyPolicyPageTitle = 'Privacy policy | epoints'
        String termsAndConditionsPageTitle = 'Terms & Conditions | epoints'
        String contactUsPageTitle = 'epoints support : Submit a request for assistance'
        String faqPageTitle = 'epoints support'
        String aboutEpointsPageTitle = 'About epoints | epoints'
        String partnersPageTitle = 'Partner with Us | epoints'
        String emailChangeConfirmationPageTitle = 'Your email has been changed | epoints'
        String specsaversPartnerPageTitle = 'Case studies | epoints'
        public String returnIndividualRedemptionPageTitleText(String redemptionName){
            return "Spend your epoints on "+redemptionName+" | epoints"
        }
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //window urls
        String videojugURL = 'http://www.videojug.com/'
        String changePasswordURL = 'reset'
        String deeplinkBabyworldURL = '/deeplink/babyworld'
        String specsaversURL = '/admin/specsavers'
        String spendURL = 'spend'
        String browseRewardURL = 'spend/list'
        String getPageURL = 'get/online'
        String shopOnlineURL = 'get/online'
        String playGamesURL = 'get/play'
        String watchVideosURL = 'get/watch'
        String goInStoreURL = 'get/instore'
        String inviteFriendURL = 'get/friends'
        String bigdlURL = 'web.bigdls.com'
        String bigdlPromoURL = 'https://www.bigdl.com/'
        String myAccountURL = 'my-account'
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
        String contactUsURL = 'http://help.epoints.com/anonymous_requests/new'
        String faqURL = 'http://help.epoints.com/home'
        String yourOnlineChoicesURL = 'http://www.youronlinechoices.eu/'
        String allaboutcookiesURL = 'http://www.allaboutcookies.org/'
        String googlePrivacyURL = 'http://www.google.com/policies/technologies/ads/'
        String epointsLiveURL = 'https://www.epoints.com/'
        String supportPageURL = 'http://help.epoints.com/home'
        String emailChangeConfirmationPageURL = 'mail/activate/'
        String chekoutRewardsURL = 'checkout/rewards'
        String chekoutSummaryURL = 'checkout/summary'
        String chekoutDeliveryURL = 'checkout/delivery'
        String chekoutCompleteURL = 'checkout/complete'
        public String returnJoinOkURL(String email){
            return epointsLink+'join/ok?email='+email
        }
        String epointsYoutubeVideoURL = 'https://www.youtube.com/watch?v=VDvXZ12dEEE'
        String externalSpecsaversURL = 'http://www.specsavers.com/'
        String voucherPageURL = 'vouchers'
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    //page used texts
        //transition page
        String transitionInformationModalHeader = 'Collect epoints as you shop\n' +
                'and get free rewards!'
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
        public String returnInformationAboutNotFoundMerchant(String merchant){
            return "Merchant with name '"+merchant+"' not found"
        }
        //az page
        public String returnInformationAboutNotFoundRetailer(String retailer){
            return "Sorry! We can't find "+retailer+". Try again or browse the A-Z of stores"
        }
        public String returnEpointsTooltipInformation(String epointsValue){
            return "You will earn "+epointsValue+" epoints per £ spent"
        }
        String azFacetName = 'Stores A-Z'
        String departmentFacetName = 'Departments'
        String recentlyVisitedRetailersHeader = 'Recently visited retailers'
        String favouritesTooltipInfoNotLogged = 'You need to sign in to add your favourite retailer'
        String favouritesTooltipInfoLogged = 'Add to my favourites'
        String voucherTooltipInfo = ' voucher available for this store'
        String[] azPageSortOptions =  ["Relevance", "A-Z", "Z-A", "epoints low to high", "epoints high to low"]
        //sign in/join
        String joinConfirmationMessage = 'Please check your inbox and click the confirmation button.'
        String loginModalHeader = 'Sign in'
        //voucher page
        String departmentFacetNameOnVouchers = 'Choose department'
        String merchantFacetNameOnVouchers = 'Merchant'
        String categoryFacetNameOnVouchers = 'Category'
        String threeCategoriesSelectedBreadcrumbText = 'Categories: 3 selected'
        String threeMerchantsSelectedBreadcrumbText = 'Merchants: 3 selected'
        String voucherCodeHelpText = 'enter this code at the checkout'
        String[] voucherPageSortOptions =  ["Relevance", "Expiring soonest", "Expiring latest"]
        String goToretailerButtonText = 'Go to retailer'
        String openSiteButtonText = 'Open site'
        //share windows
        String twitterURL = 'https://twitter.com'
        String facebookURL = 'https://www.facebook.com'
        String googleURL = 'https://plus.google.com'
        // create password page
        String completePageHeader = 'Complete your account'
        String[] completePageAlerts =["Password is required","Passwords do not match","First Name is required","Last Name is required"]
        String[] completePageLabels =["Password *","Confirm password *","First name *","Last name *", "Gender"]
        String allDoneViewHeader = 'All done'
        //spend page
        public String returnSpendPageHeaderText(String pointsNumber){
            return "We now have "+pointsNumber+" rewards!"
        }
        String departmentsTabName = 'Browse by Departments'
        String rewardsByEpointsTabName = 'Rewards by epoints total'
        String[] departments = ["Books","Fashion","Home & Garden"," Health & Beauty","Baby & Child","Sports & Outdoors","Computer & Office","Music Film & Gaming","Toys & Games","Electronics","Gifts","Travel"]
        //individual redemption page
        String basketModuleTitle = 'Receive this item for'
        public String returnBasketPreviewQuantityInformation(String quantity){
            if(quantity.equals('1')) {
                return quantity + " reward selected"
            }else{
                return quantity + " rewards selected"
            }
        }
        public String returnBasketPreviewContentQuantityInformation(String quantity){
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
        //join section
        String joinHeader = 'Join epoints'
        String joinMainText = 'Just pop your email address in the box below and you can start earning epoints.'
        String joinEmailAddressLabel = 'Email address'
        String joinTellMeMoreModalText = 'epoints rewards you for things you’re doing online already. So every time you shop with one of our 2,000 retailers or interact with some of our partner websites you can get epoints. You can then spend those epoints on treats from a choice of more than one million products!We never stop adding new retailers and partners, so you\'ll always find something that\'s right for you.'
        String joinEmailAddressIsRequiredAlert = 'Email address is required'
        String joinEmailAddressIsInvalidAlert = 'Email address is invalid'
        String joinUsernameAlreadyTakenError = 'Username already taken'
        public String returnJoinOkTextOveral(String quantity){
            return 'You\'ve got '+quantity+' epoints so far'
        }
        public String returnJoinSignOutModalText(String email){
            return 'Account not verified If you sign out now, you will need to follow the link in the verification email before you can log in again. Resend verification email to '+email+' Ok Sign outCancel Escape or Close'
        }
        public String returnJoinSignOutModalTextAngular(String email){
            return 'Account not verified If you sign out now, you will need to follow the link in the verification email before you can log in again. Resend verification email to '+email+' Ok Sign out Cancel'
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
        String instore1SectionHeader  = 'What epoints can I get?'
        String instore1SectionDescription = 'bigDL will reward you 10 bonus epoints just for downloading this free app. Every time you shop at any of the retailers listed just follow the simple steps and you will pick up epoints.'
        String instore2SectionHeader = 'How does it work?'
        String instore2SectionDescription = 'Download bigDL from either Google Play or Apple Store and register using the same email address associated with your epoints account. Every time you shop at any of the retailers listed within bigDL follow the steps within the app to collect epoints.'
        String instore3SectionHeader = 'What is bigDL?'
        String instore3SectionDescription = 'bigDL is the easiest way to save money on your favourite brands. This clever app finds hundreds of deals on the high street with up to 80% off in shops near you.'
        //play games page
        String clumsyBirdGetText = 'Get epoints for playing Clumsy Bird'
        public String returnClumsyBirdInstructionText(String gapsNumber, earnValue ){
            return 'Tap, click or hit space to move the bird up, let go and it drops. Fly through the gaps to progress. Get '+earnValue+' when you fly through '+gapsNumber+' gaps.'
        }
        String signInToearnEpointsInfo = 'Sign in to earn epoints!'
        //forgot password page
        String forgotPasswordHeader = 'Forgot your password?'
        String forgotPasswordMainText = 'Enter the email address associated with your epoints account below, we\'ll send you a link to reset your password.'
        String forgotPasswordEmailLabel = 'Email address *'
        String forgotPasswordEmailIsRequiredAlert = 'Email is required'
        String forgotPasswordUserDoesNotExistsAlert = 'User with given email doesn\'t exist'
        String forgotPasswordEmailSentAlert = 'Email sent! Follow the link in your email.'
        //change password page
        String changePasswordSuccessAlert = 'Password successfuly changed!'
        String changePasswordHeader = 'Change your password'
        String changePasswordPasswordLabel = 'Password *'
        String changePasswordConfirmPasswordLabel = 'Confirm password *'
        String changePasswordisRequiredAlert = 'Password is required'
        String changePasswordDoNotMatchAlert = 'Passwords do not match'
        String changePasswordInvalidToken = 'Confirmation failedYour confirmation link is invalid.'
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
        String specsaversAuthorizationAlert = 'Authorization failed'
        String specsaversAwardPointsTab = 'Award points'
        String specsaversImportTab = 'Import'
        String specsaversSelectBranchesLabel = 'Select branches'
        String specsaversReasonForAwardingLabel = 'Reason for awarding points'
        String specsaversPointsToAwardLabel = 'Points to award'
        String specSaversPickBranchesIsRequiredAlert = 'Select branches is required'
        String specsaversReasonForAwardingPointsIsRequiredAlert = 'Reason for awarding points is required'
        String specsaversPointsToAwardIsRequiredAlert = 'Points to award is required'
        String specsaversPointsToAwardIsInvalidAlert = 'Points to award is invalid'
        String specsaversPickBranchesPlaceholder = 'Pick branches'
        String specsaversReasonForAwardingPlaceholder = 'Pick or type reason for awarding points'
        String specsaversConfirmModalHeader = 'Please confirm'
        public String returnSpecsaversConfirmModalMainText(String poundsValue, String numberOfBranches, String pointsTotal){
            return 'Are you sure you want to award'+poundsValue+' to '+numberOfBranches+' branches totaling '+pointsTotal+' points?'
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
        //cookie policy page
        String cookiePolicyPageContent = 'Cookie PolicyWhat is a cookie?Cookies help improve your experience when using a website by storing files or pieces of information on your computer (or other internet enabled devices such as smartphones or tablets).Cookies help us make the content seen on the site more relevant to your needs by remembering individuals when they revisit the site.So that we can ensure that we award epoints on your purchases we will track all online purchases made through our sites and our partners\' sites.Most importantly, it tells us if you\'ve made a transaction and which retailer\'s site you have clicked through to get there. The participating retailer, at a later date, will then send us the specifics relating to the value of your purchase and the points earned.When you\'re on our sites we can speed up your future experiences and activities by using cookies.To help us improve the content and structure on our sites we can collect, using cookies, aggregated anonymous statistics about how people actually use our sites. From this information there is no way of identifying you personally.The standard cookie will contain information such as which website the cookie has come from, how long it will remain on your device (it\'s "lifetime") and a value - which is usually just a unique number that has been randomly generated.The 2 different types of cookies we use:Persistent cookies - For a duration of time which is specified by the cookie, persistent cookies will remain on your device. Each time you access our site it\'s the cookie which will be used to display your epoints balance and name. When you return to our website, occasionally, persistent cookies may monitor your use of the website and also recognise you as a unique visitor. The benefits of this to you is that we can then offer promotions which match your preferred interests and also help us tailor the content of certain areas of the site directly to you. Each time a user visits the website that created a particular cookie then persistent cookies may be activated. Session cookies - these cookies are used during a browser session in order to allow website operators to link the actions of a user. This is to avoid you having to re-enter information it can just be carried across the site. So to avoid you having to re-enter information it means that epoints can carry information across pages of the site. This information can then be used to help improve the structure of the site and helps to build statistics which increase our understanding of how users use the site. The start and finish of a browsing session can be defined by a user opening and closing the window. Once a browsing window has been closed then all session cookies will be removed and deleted, as they are only created temporarily. If you would like to find out more information about cookies and how they are used then please visit: www.youronlinechoices.eu or www.allaboutcookies.org. Managing cookiesIt is very important to us that when you visit our websites you have clear sight and control over the cookies that are being used. If you would like to prevent your browser accepting new cookies, disable cookies entirely or receive a notification when you receive a new cookie then the Help menu on the menu bar of most browsers will be able to enforce this. By changing the add-ons settings or visiting the manufacturer’s website you can delete or disable similar data which has been used by browser add-ons (for example Flash cookies). Our recommendation, however, is to leave them turned on as without cookies you would not be able to enjoy some essential features of the website. Blocking or rejecting cookies, for instance, will mean that you are no longer able to collect epoints, add items to your basket or go through the checkout process. However, do remember to sign off a shared computer when you have finished if you do leave your cookies turned on.Your informationThe only rare occasions when we would give out your information to anyone outside the epoints group of companies is when we have your consent; if the law requires us to do so; if a company who provides a service to us needs it; or to any successors within our business. All appropriate security measures are always taken whenever any of your personal information is transferred to any countries outside the European Economic Area. This policy is purely for the epoints group of companies however, any of our  sites may contain links to other sites outside our control. When using these sites the operators may collect information from you; their policy may differ from ours and therefore they may use that information in accordance with their policy. Applies toThis cookie policy covers are any websites third party platforms branded pages and applications accessed or used through such websites or third party platforms, which are operated by or on or behalf of the epoints group of companies. The holding company of the epoints group of companies is Instant Access Technologies Limited a company registered in England and Wales with registered number 04585101 and with registered office at Dane End House, Munden Road, Dane End, Ware, Hertfordshire, SG12 0LR. All references to \'our\', \'us\', and \'we\' within this policy are references to Instant Access Technologies Limited, and its subsidiaries and associated companies. You agree to our use of cookies in line with this Cookie policy by using any of our sites. You can, however, change your browser settings accordingly if you do not agree to the way we use cookies; or not use our sites entirely. It may, however, have an impact on your experience of the site if you decide to disable the cookies that we use.'
        //privacy policy page
        String privacyPolicyPageContent = 'Privacy PolicyData protection information At epoints Rewards Limited your trust in the correct handling of your data is highly important to us and we value and adhere to data protection standards according to the data protection laws in the UK. The collection, processing and usage of your personal information on the websites of the Programme will always be carried out according to the requirements of the applicable data protection laws. Below we set out the nature, extent and purpose of collection, processing and use of your personal data. From time to time, many of our rights and obligations under this Privacy Policy will be carried out by Instant Access Technologies Limited, a company incorporated in England and Wales with its trading address at 27 Paul Street, London, EC2A 4JU and with its registered office at Dane End House, Dane End, Near Ware, Herts SG12 0LR and with company number 4585101.Terms defined in the Terms and Conditions shall have the same meanings when used in this Privacy Policy. Personal DataPersonal data is information about an identifiable individual for example, name, address, date of birth and email address.We also may collect the following data through your access to the Website: the Internet protocol ("IP") address used to connect your computer to the Internet, computer and connection information such as browser type and version, operating system, platform, the full Uniform Resource Locators ("URL"), clickstream to, through and from our software, including date and time, cookie number and pages you viewed or searched for.Collection of personal dataWe collect personal data during registration to the Programme, during use of the Programme and the Website, upon entry into a prize draw and upon redemption of ePoints. We also collect personal data when you make a purchase from our website.Processing and use of personal dataWe process and use your personal data to administer, manage and improve the Programme, in particular we may process and use your personal data as follows:Send members newsletters as noted below To compile Member profiles and add usage data such as IP address, time and type of an email opening and page request information, to Member profiles. Statistical data from external sources such as socio-demographic data may be added to Member profilesTo administer prize drawsTo group Members into behavioural databasesTo manage any other services offered under the ProgrammeTo enable third party suppliers, including but not limited to Instant Access Technologies Limited noted above, to track your activity on the Programme and assist us with our other rights and obligations under this Privacy Policy andWith your consent, to pass your personal data to the Partner that you first visited to register at the Programme for their internal business and marketing purposesTo process an order for products made through the website, including contacting a credit or debit card issuer to carry out security checks, and dealing with any complaintsWe shall not otherwise disclose or transfer your personal data to any third party without your consent.Please note that we are not responsible for the processing of your personal data when you transact or otherwise enter into any relationship with a Retailer or Redemption Company as part of the Programme.Marketing NewslettersYour personal data can be used by us to send you information about current events on the Programme and to send you newsletters and personalised advertisements via email, SMS, voice and/or post.With your consent, your personal data that is passed on to a Partner can be processed and used by a particular Partner to send you newsletters and personalised advertisements via email and/or post.If at any time you wish to unsubscribe to communications about the Programme, please contact us.What About Third Party Advertisers?Our software includes third party URLs and our Website enables third parties to serve their advertisements on our Website. Often, these third parties, called "ad networks" or "network advertisers", employ cookies and other technologies (such as web beacons and Javascript) to measure the effectiveness of their advertisements and to show you advertisements that are more relevant and interesting to you. We do not provide any Member information to them, and we do not have access to or control over cookies that may be placed by these third parties. Google, as a third party vendor, uses cookies to serve a advertisements on our Website. Google\'s use of the DART cookie enables it to serve advertisements to users based on their visit to certain pages of our Website. You may opt out of the use of the DART cookie by visiting the Google ad and content network privacy policy at the following URL http://www.google.com/privacy_ads.html Some of our advertising partners may also use cookies and web beacons on our Website. Our advertising partners include Google Adsense and Microsoft Atlas. You should consult the respective privacy policies of these third party advertising partners for more detailed information on their practices as well as for instructions about how to opt-out of certain practices. This Privacy Policy does not apply to, and we cannot control the activities of, such other advertisers or web sites.'
        //terms and conditions page
        String termsAndConditionsPageContent = 'Terms and ConditionsYour agreementepoints is a reward programme operated by EPOINTS REWARDS LIMITED a company incorporated in England and Wales with its registered office at 52-54 High Holborn, WC1V 6RL with company registration number 07453502By visiting this Website and participating in the epoints programme, you agree to be bound by and to comply with these Terms and Conditions, our Privacy Policy and any other rules noted on this Website.We may from time to time change these Terms and Conditions. We will always endeavour to notify you of any major changes, but we recommend that you check this page from time to time for updates. Earning and redeeming epoints in the Programme will constitute acceptance of the revised Terms and Conditions.DefinitionsFor the purposes of these Terms and Conditions:"epoints" means the reward points that can be collected and redeemed in accordance with these Terms and Conditions;"Member", or "you" means any individual registered on the Programme;"Partner" means any third party that enables a Member to register with the Programme; "Programme" means the reward programme operated under these Terms and Conditions where Members can earn epoints by purchasing specified goods and/or services from Retailers or by specified online interactions on the Website;"Redemption Company" means any third party that supplies goods, services, vouchers, discounts or other items to a Member following the redemption of epoints;"Retailers" means any third party that sells goods to Members that generate epoints for such Members through the Programme;"we, us, our" means epoints Rewards Limited; and"Website" means the website located at epoints.comRegistration to the ProgrammeIn order to participate in the Programme, you must be aged 18 or over, hold a valid and operational email account and have a valid UK postal address. You can register with us or one of our Partners. By registering with the Programme, you represent and warrant that:you have the legal capacity to agree to these Terms and Conditions;you are using your real identity to register;all information that you have provided is accurate and up-to-date; andyou will update as necessary all the information you have provided.If we believe that you have provided any information that is inaccurate or not up-to-date, we may refuse to register you or otherwise suspend or terminate your access to the Programme. You should keep your account information accurate and up-to-date.Subject to Condition 6 above, on receipt of your registration detail, we shall set up an epoints account to record epoints earned and redeemed by you. You may only collect and redeem epoints through your own registered account. Additional Members that you may want to introduce to the Programme must register their details with us and start their own membership account. A Member cannot transfer epoints earned to another Member, either by sale, barter or otherwise.You are responsible for maintaining the confidentiality of your epoints account, and any user name and/or password. You are also responsible for all activities that occur under your account and accordingly, you shall immediately notify us of any unauthorized use of your account.We can take any action we consider appropriate if we have reason to believe that you are abusing the Programme, including withholding or reusing epoints previously credited to your account.Earning and Redeeming Pointsepoints can be earned:by Members who are logged on to the Website, search for a product then link to the relevant Retailer’s website and complete a purchase of that product. You cannot collect epoints if you visit the Retailer’s website directly, nor if you purchase a product not shown on our Website. The number of points to be earned from a product will only be shown on our Website; andby the appropriate use of epoints rewards and Partner mobile applications. If you earn epoints by uploading content and you subsequently remove that content, you may lose the epoints earned.We, our Partners and Retailers all reserve the right to amend the number of epoints rewarded for interactions included in the Programme.epoints are awarded by the Programme, and not by Partners or Retailers. epoints cannot be redeemed until credited to a Member account. Retailers take different periods of time to notify us when orders for qualifying purchases are completed, but in general, epoints will be awarded to a Member account 30 days from the completion of a qualifying purchase. During this period epoints will be displayed as pending in the Member’s account balance. Pending epoints can take up to 72 hours before being displayed within the Member’s account balance. epoints awarded to Members for the specified online interactions will usually be awarded within 48 hours and will be subject to our Partners’ or Retailers’ terms and conditions.All purchases from Retailers and items offered by Redemption Companies are subject to availability and to all applicable legal rules and the terms and conditions (including booking requirements, cancellation restrictions, return conditions, warranties and limitations of liability) of the Retailer or Redemption Company as the case may be. We are not responsible for the inaccurate communication of epoints collected through a Partner or Retailer site. Where epoints are redeemed for a voucher carrying a specified cash discount or benefit, the voucher may have an expiry date specified on it and will be subject to any other terms and conditions stated on the voucher or otherwise imposed by the Redemption Company.Transactions on which epoints are earned or redeemed are made directly with the relevant Retailer and the Redemption Company. Retailers and Redemption Companies are independent third parties and we are not acting as their principal, agent and/or broker. Any relationship with any Retailer and/or Redemption Company through the Programme for the purchase of goods, services or other items is solely between you and the Retailer or Redemption Company in question. We are not liable for any costs, claims, expenses, losses or damages that you may incur arising in any way as a result of or in connection with the purchase of goods, services or other items from a Retailer or Redemption Company or as a result of any reliance placed by you on the content of the Website. In particular, we are not responsible and shall have no liability for any loss, theft or damage to any goods sent by a Redemption Company.The allocation of epoints for disputed and/or cancelled or returned items will be dealt with on a case by case basis at support@epoints.com and is entirely at our discretion.We reserve the right to close an epoints account if a Member has not earned any epoints on their account during any 2 year period. If a Member closes their account, all epoints within that account shall be lost.Redeemed epoints are deducted at the point where an order is confirmed by the Member and cannot be used again. If a transaction on which epoints have been issued or redeemed is cancelled we will reverse the associated epoints movement once instructed by the relevant Redemption Company.Points for email invites are for personal contacts only not for uploading mailing lists.The ProgrammeWe will use reasonable endeavours to email Members from time to time with details of the epoints that they have earned.We may suspend or terminate the Programme but will give as much notice as we reasonably can before we do so. If this happens all Member accounts will be suspended or terminated. If we sell or transfer the Programme to another company we may transfer all of our rights and obligations under these rules without any further consent and may disclose or transfer all information we hold about Members to a prospective or actual new owner. Such a disclosure will be communicated to Members within 30 days of such a transaction being effected by law.Prize drawsFrom time to time, we may run free prize draws on the Website. Entry into a prize draw is governed by the Rules of Entry.Use of the WebsiteAll rights in all material and content (including, but not limited to, text, images, web pages, look and feel, design and compilation) in the Website are owned by us or our licensors. You may view the Website and you may print hard copies of material and content from the Website for your lawful, personal, non-commercial use. To the extent permitted by law, all other copying, whether in electronic, hard copy or other format, is prohibited and may breach intellectual property laws and other laws worldwide. Furthermore, you are not entitled to reproduce, transmit, publicly perform, distribute, adapt, translate, modify, bundle, merge, share or make available to any person, or create derivative works of such material, or use it for commercial purposes, without our prior written consent. All other rights are reserved.You acknowledge and agree that we have no responsibility for the information provided by sites to which you may link from the Website. Links to other sites do not constitute an endorsement by or association with us of such sites or the content, products, advertising or other materials presented on such sites. We have no control over these sites and do not edit or monitor them. We are not responsible or liable, directly or indirectly, for any damage, loss or cost caused or alleged to be caused by or in connection with use of or reliance on any such content, goods or services available on such sites.Whilst we endeavour to ensure that the Website is normally available 24 hours a day, we shall not be liable if for any reason the Website is unavailable at any time. Access to the Website may be suspended temporarily and without notice in the case of system failure, maintenance or repair or for any other reasonable cause.The Website is provided "as is" and your use is at your own risk. We, our officers, directors, employees and agents disclaim, to the fullest extent permitted by law, all express and implied warranties, including, without limitation, any warranties of quality, fitness for a particular purpose, performance, title and non-infringement. We do not warrant that the Website will be free from viruses, available, accessible, error-free, uninterrupted or that the contents will be accurate. If you are dissatisfied with the Website or the Programme, your sole remedy is to discontinue using the Website and the Programme.Governing lawThese Terms and Conditions including the Privacy Policy and any other rules noted within these Terms and Conditions shall be governed by English law and the courts of England and Wales shall have exclusive jurisdiction.'
        String termsAndConditionssupportLink = 'mailto:support@epoints.com?subject=epoints%20queries%20on%20disputed,%20cancelled%20or%20returned%20item'
        //contact us page
        String contactUsPageMainText = 'Submit a request for assistance Fields marked with an asterisk (*) are mandatory. You\'ll be notified when our staff answers your request.'
        String contactUsPageHeader = 'Submit a request'
        String contactUsPageEmailLabel = 'Your email address *'
        String contactUsPageSubjectLabel = 'Subject *'
        String contactUsPageDescriptionLabel = 'Description *'
        String contactUsPageAttachementsLabel = 'Attachment(s)'
        //faq page
        String faqHeader = 'Welcome to epoints customer service Here you can find the answers to any of your questions, as well as links to various areas of the site and information for contacting the epoints team.'
        String faqSearchLabel = 'FAQs'
        //about  epoints page
        String aboutEpointsFirstSectionHeader = 'What is epoints?'
        String aboutEpointsFirstSectionText = 'epoints is a free loyalty scheme that rewards you for shopping online and on the High Street. Sign-up takes seconds and once you’ve registered you can earn epoints from thousands of retailers including John Lewis, Tesco and Mothercare. You’ll also earn epoints via the bigDL app which shows you hundreds of local deals and offers for all of your favourite brands on the High Street.'
        String aboutEpointsSecondSectionHeader = 'So much choice'
        String aboutEpointsSecondSectionText = 'Of course there are other loyalty programs out there but only epoints gives you such a vast range of options. epoints members are not tied to just one brand and you don\'t have to worry about remembering a card! epoints members have access to more than 2,000 retailers and can choose from over 2 million rewards.'
        String aboutEpointsThirdSectionHeader = 'Hassle-free rewards'
        String aboutEpointsThirdSectionText = 'Every £1 you spend guarantees you at least 1 epoint and you only need 100 epoints to claim your first free reward! Plus as you get 50 epoints for becoming a member, you’ll be rewarded in no time. Choose from 2 million epoints rewards, including restaurant and cinema vouchers, books, spa days and more!'
        String aboutEpointsThirdSectionText2 = 'The only question is – do you spoil yourself, or treat the family?'
        String aboutEpointsFourthSectionHeader = 'Get involved'
        String aboutEpointsFourthSectionText = 'We want our members to help shape the future of epoints so give us your feedback and we’ll give you even more epoints. The more comments you share with us, the more epoints you get and the better the epoints experience will become.'
        String aboutEpointsFourthSectionText2 = 'Want to find out more? Watch the epoints video'
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
        String zonePickerUK  = 'United Kingdom'
        String zonePickerIreland = 'Ireland'
        //cookie panel
        String cookiePanelText = 'Welcome to epoints.We use cookies on this website to ensure you get the best and most relevant experience.If you continue we assume you consent to receive all cookies on the epoints website.'
        //your account profile tab email row
        String profileTabCurrentEmailLabel = 'Email address'
        String profileTabNewEmailLabel = 'New email address'
        String profileTabRetypeNewEmailLabel = 'Re-enter new email address'
        String profileTabEmailIsInvalidAlert = 'Email address is invalid'
        String profileTabEmailIsNotMatchAlert = 'Emails do not match'
        String profileTabSuccessAlert = '×Follow confirmation link in your email to verify it.'
        String profileTabEmailsMatchAlert = 'Emails match!'
        //your account profile tab password row
        String profileTabPasswordSetDateLabel = 'Password'
        String profileTabPasswordSetDateText = 'Password was set on'
        String profileTabCurrentPasswordLabel = 'Current password'
        String profileTabNewPasswordLabel = 'New password'
        String profileTabConfirmPasswordLabel = 'Confirm new password'
        String profileTabCurrentPasswordIsRequiredAlert = 'Password is required'
        String profileTabNewPasswordIsRequiredAlert = 'Password is required'
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
        String personalDetailsInvalidDateFormatAlert = 'Invalid date format. Use dd/MM/yyyy format!'
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
        //email address changed confirmation page
        public String returnEmailAddressChangeConfirmationPageText(email){
            return 'Email address changed Thank you. Your epoints account email address has now been changed to '+ email +' You will now need to use this address when you sign in to epoints. You can see this change in the my profile page of your account. Account dashboard Earn more epoints'
        }
        //your account purchase epoints
        public String returnSuccessfullPaymentInfo(epointsValue){
            return "Success!You've bought "+epointsValue+" epoints"
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
        public String returnTotalEpointsNeededInfo(pointsValue){
            return 'Total epoints needed' + pointsValue
        }
        String deliveryDetailsHeader = 'Delivery details'
        public String returnNotEnoughPointsAlert(pointsValue){
            return '!You are still '+pointsValue+'away from these rewards'
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
        String deliveryCoutryLabel = 'Country'
        String deliveryPostcodeLabel = 'Postcode'

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
        public String returnUniqueInvitationLink(referrerId){
            return "epoints.com/join?referrer="+referrerId+""
        }
        String inviteFriendOrSimplyText = 'or simplyshare your invitation and Post it on your timeline'
        String inviteFriendFacebookPost = 'Join today and become the latest epoints member - enjoy a lifetime of rewards'
        //specsavers bulk upload
        String bulkUploadDropBoxText = 'Drop .xls or .xlsx here or click to upload'
        String bulkUploadAlertSuccess = 'Importing finished. Epoints awarded successfully.'
        String bulkUploadWrongFormatAlert = 'File extension not allowed. Upload .xls or .xlsx file.'
        String bulkUploadMissingNameAlert = 'Some rows are incorrect. Error details: Row 2 Store name - Empty field'
        String bulkUploadMissingEmailAlert = 'Some rows are incorrect. Error details: Row 2 Store Email - Empty field'
        String bulkUploadMissingReasonAlert = 'Some rows are incorrect. Error details: Row 2 Reason (255 char max) - Empty field'
        String bulkUploadMissingAmountAlert = 'Some rows are incorrect. Error details: Row 2 Amount (£) - Empty field'
        String bulkUploadMissingAdminAlert = 'Some rows are incorrect.Error details:Row 2 Administrator - Empty field'
        String bulkUploadWrongEmailAlert = 'Some rows are incorrect.Error details:Row 2 Store Email - Such email was not registered for specsavers'
        String bulkUploadTooLowAmountAlert = 'Some rows are incorrect.Error details:Row 2 Amount (£) - Amount needs to be number greater then 1'
        String bulkUploadWrongHeaderAlert = 'Column names are wrong. Proper order and names of column names are: Store name, Store Email, Reason (255 char max), Amount (£), Administrator.'
}