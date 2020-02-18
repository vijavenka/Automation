package com.iat.storepresentation.Locators.ShopHome;

import com.iat.storepresentation.Locators.Locator;
import com.iat.storepresentation.Locators.LocatorType;

public class HomePageLocators {
    // Department/categoryDDL
    public Locator departmentDDL = new Locator(LocatorType.XPATH, "//a[@class='dropdown-toggle branded']");
    public String[] departments = {"Books","Fashion","Home & Garden","Health & Beauty","Baby & Child","Sports & Outdoors","Computer & Office","Music, Film & Gaming","Toys & Games","Electronics"};
    public Locator searchDepartmentOptionBooks = new Locator(LocatorType.XPATH, "//a[@href='/books']");
    public Locator searchDepartmentOptionFashion = new Locator(LocatorType.XPATH, "//a[@href='/fashion']");
    public Locator searchDepartmentOptionHomeAndGarden = new Locator(LocatorType.XPATH, "//a[@href='/home-and-garden']");
    public Locator searchDepartmentOptionHealthAndBeauty = new Locator(LocatorType.XPATH, "//a[@href='/health-and-beauty']");
    public Locator searchDepartmentOptionBabyAndChild = new Locator(LocatorType.XPATH, "//a[@href='/baby-and-child']");
    public Locator searchDepartmentOptionSportAndOutdors = new Locator(LocatorType.XPATH, "//a[@href='/sports-and-outdoors']");
    public Locator searchDepartmentOptionComputerAndOffice = new Locator(LocatorType.XPATH, "//a[@href='/computer-and-office']");
    public Locator searchDepartmentOptionBooksFilmAndGaming = new Locator(LocatorType.XPATH, "//a[@href='/music-film-and-gaming']");
    public Locator searchDepartmentOptionToysAndGames = new Locator(LocatorType.XPATH, "//a[@href='/toys-and-games']");
    public Locator searchDepartmentOptionElectronics = new Locator(LocatorType.XPATH, "//a[@href='/electronics']");
    public Locator[] DepartmentsDDL ={searchDepartmentOptionBooks,searchDepartmentOptionFashion,searchDepartmentOptionHomeAndGarden,searchDepartmentOptionHealthAndBeauty,searchDepartmentOptionBabyAndChild,searchDepartmentOptionSportAndOutdors,searchDepartmentOptionComputerAndOffice,searchDepartmentOptionBooksFilmAndGaming,searchDepartmentOptionToysAndGames,searchDepartmentOptionElectronics};

    // Epoints login box Locators
        public Locator loginBox = new Locator(LocatorType.XPATH, "//div[contains(@class, 'content')]");
        public Locator emailAdress = new Locator(LocatorType.XPATH, "//input[contains(@type, 'email')]");
        public Locator password = new Locator(LocatorType.XPATH, "//input[contains(@type, 'password')]");
        public Locator signInButton = new Locator(LocatorType.XPATH, "//button[contains(@id, 'signInToEpoints')]");
        public Locator forgotPasswordLink = new Locator(LocatorType.XPATH, "//a[@class='forgotPass link']");
        public Locator startHereLink = new Locator(LocatorType.XPATH, "//a[@class='link']");
        public Locator loginUsingFacebookCredentials = new Locator(LocatorType.XPATH, "//span[@class='btn-social-content']");
        public Locator closeLoginPanel = new Locator(LocatorType.XPATH, "//a[@class='close-modal-link link']");
        // Facebook login window Locators
            public Locator facebookLoginOption = new Locator(LocatorType.XPATH, "//span[contains(text(),'Sign in with facebook')]");
            public Locator facebookJoinOption = new Locator(LocatorType.XPATH, "//span[contains(text(),'Sign in with facebook')]");
            // New window name to switch function Facebook
                public Locator facebookLoginEmailTextfield = new Locator(LocatorType.XPATH, "//input[@id='email']");
                public Locator facebookLoginPasswordTextField = new Locator(LocatorType.XPATH, "//input[@id='pass']");
                public Locator facebookLoginDoNotLogoutMeCheckbox = new Locator(LocatorType.XPATH, "//input[@id='persist_box']");
                public Locator facebookLoginButton = new Locator(LocatorType.XPATH, "//label[@id='loginbutton']");
                public Locator facebookCancelButton = new Locator(LocatorType.XPATH, "//input[@id='u_0_2']");
    // Epoints pastille user logout
        public Locator signInToEpoints = new Locator(LocatorType.XPATH, "//span[@class='points']//a[@class='signInToEpoints']");
        public Locator joinEpoints = new Locator(LocatorType.XPATH, "//div[@class='userBalance']//a[@href='/join']");
    // Epoints pastille user login
        public Locator epointsPastille = new Locator(LocatorType.XPATH, "//div[@class='userBalance']");
        public Locator epointsPastillePointsNumber = new Locator(LocatorType.XPATH, "//div[@class='userBalance']//span[@class='points']//strong");
        public Locator epointsPastilleExpandMenu = new Locator(LocatorType.XPATH, "//div[@class='userBalance']//i[@class='icon-arrow']");
            public Locator epointsPastilleSignOut = new Locator(LocatorType.XPATH, "//div[@class='userBalance']//a[@class='signOutFromEpoints']");
            public Locator epointsPastilleMyEpoints = new Locator(LocatorType.XPATH, "//div[@class='userBalance']//a[@href='/my-account']");
            public Locator epointsPastilleGetEpoints = new Locator(LocatorType.XPATH, "//div[@class='userBalance']//a[@href='/get-epoints']");
            public Locator epointsPastilleSpendEpoints = new Locator(LocatorType.XPATH, "//div[@class='userBalance']//a[@href='/spend']");
            public Locator epointsPastillePendingEpoints = new Locator(LocatorType.XPATH, "//div[@class='userBalance']//a[@href='/my-account#type=pending;tab=ePointsStatement']");
    // Header Locators
        public Locator signInButtonHeader = new Locator(LocatorType.XPATH, "//a[@class='signInToEpoints']");
        public Locator shopHome = new Locator(LocatorType.XPATH, "//ul[@class='inline-list']//a[@href='/']");
        public Locator azPageHome = new Locator(LocatorType.XPATH, "//a[contains(text(),'A-Z Retailers')]");
        public Locator vouchers = new Locator(LocatorType.XPATH, "//ul[@class='inline-list']//a[@href='/vouchers#type=voucher;sort=expiryDate|asc;pageSize=40']");
        public Locator dailyDelas = new Locator(LocatorType.XPATH, "//ul[@class='inline-list']//a[@href='/daily-deals#type=dailydeal;sort=expiryDate|asc;pageSize=40']");
        public Locator specialOffers = new Locator(LocatorType.XPATH, "//ul[@class='inline-list']//a[@href='/offers']");
        public Locator hiUserName = new Locator(LocatorType.XPATH, "//span[@class='greetings']");
        public Locator yourAccount = new Locator(LocatorType.XPATH, "//a[@href='/my-account']");
        public Locator signOut = new Locator(LocatorType.XPATH, "//a[@class='signOutFromEpoints']");
        public Locator aboutUs = new Locator(LocatorType.XPATH, "//a[@href='/about']");
        public Locator howItWorks = new Locator(LocatorType.XPATH, "//a[contains(text(),'How it works')]");
    // Footer Locators
        public Locator FAQ = new Locator(LocatorType.XPATH, "//a[contains(text(),'FAQ')]");
        public Locator contactUs = new Locator(LocatorType.XPATH, "//a[contains(text(),'Contact Us')]");
        public Locator cookiePolicy = new Locator(LocatorType.XPATH, "//a[contains(text(),'Cookie Policy')]");
        public Locator termsAndConditions = new Locator(LocatorType.XPATH, "//a[contains(text(),'Terms & Conditions')]");
        public Locator privacyPolicy = new Locator(LocatorType.XPATH, "//a[contains(text(),'Privacy Policy')]");
        public Locator facebookIcon = new Locator(LocatorType.XPATH, "//img[@alt='facebook']");
        public Locator twitterIcon = new Locator(LocatorType.XPATH, "//img[@alt='twitter']");
        public Locator mobileSite = new Locator(LocatorType.XPATH, "//a[@class='switch-device']");
    // Searchers Locators
        public Locator searchInputField = new Locator(LocatorType.XPATH, "//input[@id='main-search-input']");
        public Locator searchDepartmentDDL = new Locator(LocatorType.XPATH, "//select[@class='search-select searchDropdownBox']");
        public Locator searchButton = new Locator(LocatorType.XPATH, "//button[@class='search-submit btn branded']");
    // Vouchers, special offers, daily deals three big
        public Locator vouchersLink = new Locator(LocatorType.XPATH, "//div[@class='top-nav-left']//li[2]//a");// //span[contains(text(),'Vouchers')]
        public Locator specialOffersSelectDiscount = new Locator(LocatorType.XPATH, "//span[contains(text(),'select discount')]");
        public Locator dealyDealsSelectlocation = new Locator(LocatorType.XPATH, "//span[contains(text(),'select location')]");
        public Locator dealyDealsSpecifiedLocationlocation = new Locator(LocatorType.XPATH, "    //ul[@class='dropdown-menu dropdown-locations']//li[@class='dropdown-location']//a");
    // Popular brands, our picks, 30% off, 50% off, 80% off
        public Locator popularBrands = new Locator(LocatorType.XPATH, "//li[@data-searchtype='popular']");
        public Locator ourPicks = new Locator(LocatorType.XPATH, "//li[@data-searchtype='ourPicks']");
        public Locator thirtyOff = new Locator(LocatorType.XPATH, "//li[@data-searchtype='30off']");
        public Locator fiftyOff = new Locator(LocatorType.XPATH, "//li[@data-searchtype='50off']");
        public Locator eightyOff = new Locator(LocatorType.XPATH, "//li[@data-searchtype='80off']");
    // Product card Locators, very similar Locators were presented in GetReetailerPage class (special offers section)
        public Locator basicProductContainer = new Locator(LocatorType.XPATH, "//div[@class='module-container product-card']");
        public Locator basicProductImage = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='image']");
        public Locator basicProductPercentageSaving = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='percent-saving']");
        public Locator basicProductEpointsEarned = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='epoints-earned']");
        public Locator basicProductProductTitle = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='title']");
        public Locator basicProductOldPrice = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='price-old ']");
        public Locator basicProductNewPrice = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='price']");
        public Locator basicProductFromRetailer = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//div[@class='price-from-retailer']");
        public Locator basicProductBuyButton = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//a[@class='buy-button']");
        public Locator basicProductBuyButtonHref = new Locator(LocatorType.XPATH, "//div[@class='product-card-view']//a");
    // Product split view
        public Locator basicMainProductTitleSplit = new Locator(LocatorType.XPATH, "//div[@class='productinfo-title']//a[@class='product-page-trigger']");
        public Locator productInfoCardContainerSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']");
        public Locator closeButtonSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='close-button']");
        public Locator naviPrevSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='table-column navi-prev ']");
        public Locator naviNextSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='table-column navi-next ']");
        public Locator productImageSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='image-container']");
        public Locator informationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//li[contains(text(),'Information')]");
        public Locator priceComparisonTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//li[contains(text(),'Price Comparison')]");
        public Locator productDescriptionInformationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//p[@class='description more'] ");
        public Locator retailerNameInformationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//td[@class='merchant-cell']");
        public Locator ratingInformationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='rating-modal']");
        public Locator priceInformationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='price'] ");
        public Locator priceWithDeliveryInformationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='price-with-delivery']");
        public Locator delivaryInformationTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='delivery-price']");
        public Locator epointsEarnedInformationTabSplit = new Locator(LocatorType.XPATH, "//span[@class='epoints-earned']");
        public Locator buyFromStoreButtonSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//a[@class='clickout-trigger branded']");
        public Locator facebookIconSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//span[@class='icon-facebook-squared']");
        public Locator twitterIconSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//span[@class='icon-twitter']");
        public Locator googlePlusIconSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//span[@class='icon-googleplus']");
        public Locator comparisonTablePriceComparisonTabSplit = new Locator(LocatorType.XPATH, "//div[@class='product-info-card']//div[@class='productInfo-comparison-table']");

    // Frames
    public Locator balanceIframe = new Locator(LocatorType.XPATH, "//iframe[@id='balanceIframe']");
    public Locator headerIframe = new Locator(LocatorType.XPATH, "//iframe[@id='headerIframe']");

}
