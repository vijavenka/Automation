package com.iat.ePoints.Locators.StaticPages;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 28.11.13
 * Time: 10:01
 * To change this template use File | Settings | File Templates.
 */
public class FAQLocators {

    public Locator wecomeToCustomerServicreTitle = new Locator(LocatorType.XPATH, "//div[@class='introductory_display_texts']//h2[contains(text(),'Welcome to epoints customer service')]");
    //faq header
    public Locator homeRefernece = new Locator(LocatorType.XPATH, "//a[@href='/home']");
    public Locator faqsRefernece = new Locator(LocatorType.XPATH, "//a[@href='/forums']");
    public Locator submitArequestRefernece = new Locator(LocatorType.XPATH, "//a[@href='/anonymous_requests/new']");
        public Locator emailSectionTitle = new Locator(LocatorType.XPATH, "//h3[contains(text(),'Your email address')]");
            public Locator emailSectionTextfield = new Locator(LocatorType.XPATH, "//input[@id='email_address']");
        public Locator subjectSectionTitle = new Locator(LocatorType.XPATH, "//h3[contains(text(),'Subject')]");
            public Locator subjectSectionTextfield = new Locator(LocatorType.XPATH, "//input[@id='ticket_subject']");
        public Locator descriptionSectionTitle = new Locator(LocatorType.XPATH, "//h3[contains(text(),'Description')]");
            public Locator descriptionSectionTextfield = new Locator(LocatorType.XPATH, "//textarea[@id='comment_value']");
        public Locator attachementSectionTitle = new Locator(LocatorType.XPATH, "//h3[contains(text(),'Attachment(s)')]");
            public Locator attachFileReference = new Locator(LocatorType.XPATH, "//span[@id='attach_link']");

        public Locator submitRequestButton = new Locator(LocatorType.XPATH, "//input[@id='submit-button']");
        public Locator requestAlertMessage = new Locator(LocatorType.XPATH, "//div[@id='error']");
        public Locator successAlertMessage = new Locator(LocatorType.XPATH, " //div[@id='flash_messages']");
//Searcher
    public Locator searcherTextBox = new Locator(LocatorType.XPATH, "//input[@id='suggestions_query']");
    public Locator searcherButton = new Locator(LocatorType.XPATH, "//input[@id='suggestion_submit']");
        public Locator basicSearcherResultTitle = new Locator(LocatorType.XPATH, "//div[@id='topic_search_result']//a[@title]");
//Overview recent
    public Locator faqOverview = new Locator(LocatorType.XPATH, "//a[@id='forum_nav_overview']");
    public Locator faqRecent = new Locator(LocatorType.XPATH, "//a[@id='forum_nav_recent']");
//Epoints explained
    public Locator basicCategoryLocator = new Locator(LocatorType.XPATH, "//div[@class='category']//span");
        public Locator specificCategoryLocator = new Locator(LocatorType.XPATH, "//div[@class='category']//span[contains(text(),'get epoints')]");
        public Locator addArticleButton = new Locator(LocatorType.XPATH, "//a[@class='button small']");
        //login into agent account
        public Locator emailTextfield = new Locator(LocatorType.XPATH, "//input[@id='user_email']");
        public Locator passworTextField = new Locator(LocatorType.XPATH, "//input[@id='user_password']");
        public Locator stayLoggedIncheckbox = new Locator(LocatorType.XPATH, "//input[@id='remember_me']");
        public Locator iAmAgentReference = new Locator(LocatorType.XPATH, "//a[@class='multiple_options role agent']");
        public Locator forgotMyPasswordReference = new Locator(LocatorType.XPATH, "//a[@href='/access/help']");
            public Locator emailRetrievingPasswordTextField = new Locator(LocatorType.XPATH, "//input[@class='text wide reset_password_email']");
            public Locator submitRetrievingPassword = new Locator(LocatorType.XPATH, "//input[@class='button primary']");
            public Locator succesRetrievePassswordAlert = new Locator(LocatorType.XPATH, "//div[contains(text(), 'If you are an existing user in this help desk, we will send you an email right away containing a link to reset your password.')]");
            public Locator errorRetrievePassswordAlert = new Locator(LocatorType.XPATH, "//div[contains(text(), 'Please provide a valid email address')]");
        public Locator signUpReference = new Locator(LocatorType.XPATH, "//a[@href='/registration']");
        public Locator categorySearcherTextBox = new Locator(LocatorType.XPATH, "//input[@id='query']");
        public Locator categorySearcherButton = new Locator(LocatorType.XPATH, "//input[@id='buttonsubmit']");
            public Locator fullNametextField = new Locator(LocatorType.XPATH, "//input[@id='user_name']");
            public Locator emailAdressTextfield = new Locator(LocatorType.XPATH, "//input[@id='user_email']");
            public Locator twoWordsCapcha = new Locator(LocatorType.XPATH, "//div[@id='recaptcha_image']");
            public Locator twoNewWordsReference = new Locator(LocatorType.XPATH, "//a[contains(text(),'Two other words please')]");
            public Locator capachaValidationTextField = new Locator(LocatorType.XPATH, "//input[@id='recaptcha_response_field']");
            public Locator signMeUpButton = new Locator(LocatorType.XPATH, "//input[@class='button primary']");
                public Locator capchaErrorAlert = new Locator(LocatorType.XPATH, "//div[contains(text(),'Verification failed. Please try a new set of words.')]");
            public Locator cancelReference = new Locator(LocatorType.XPATH, "//a[contains(text(),'cancel')]");
        public Locator signInButton = new Locator(LocatorType.XPATH, "//input[@class='button primary']");
            public Locator wrongLoginError = new Locator(LocatorType.XPATH, "//div[@id='error']");

    public Locator basicQuestionLocator = new Locator(LocatorType.XPATH, "//a[@title]");
        public Locator specificQuestionLocator = new Locator(LocatorType.XPATH, "//a[contains(text(),'Is it free to sign up?')]");
        public Locator specificAnswerLocator = new Locator(LocatorType.XPATH, "//div[@class='user_formatted header_section clearfix']//p[contains(text(),'Yes, it certainly is free.')]");
    //add article module
}