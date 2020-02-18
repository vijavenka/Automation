package com.iat.ePoints.Navigations.StaticPages;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Locators.StaticPages.FAQLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 28.11.13
 * Time: 09:56
 * To change this template use File | Settings | File Templates.
 */
public class FAQNavigation extends AbstractPage {

    HeaderLocators locators_header = new HeaderLocators();
    FAQLocators locators_faq = new FAQLocators();


    public FAQNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // Checking if hyperlink to Terms & Conditions page is available

    public void checkContentOfHeaderStaticPagesLinks() {
        assertTrue("About epoints reference is not available", executor.is_element_present(locators_header.aboutEpoints));
        assertTrue("FAQ reference is not available", executor.is_element_present(locators_header.FAQ));
        assertTrue("BlogSteps reference is not available", executor.is_element_present(locators_header.Blog));
    }

    public void clickOnFAQLink() throws InterruptedException {
        executor.click(locators_header.FAQ);
        Thread.sleep(3000);
    }

    public void checkIfFAQlinkWorksPropery() {
        assertTrue("Welcome title is no visible", executor.is_element_present(locators_faq.wecomeToCustomerServicreTitle));
    }

    // Checking if FAQ page content is correct

    public void checkContentOfFAQPage() {
        assertTrue("Home reference is no visible", executor.is_element_present(locators_faq.homeRefernece));
        assertTrue("FAQs reference is no visible", executor.is_element_present(locators_faq.faqsRefernece));
        assertTrue("Submit a request referance is no visible", executor.is_element_present(locators_faq.submitArequestRefernece));
        assertTrue("Search text field is no visible", executor.is_element_present(locators_faq.searcherTextBox));
        assertTrue("Search button is no visible", executor.is_element_present(locators_faq.searcherButton));
        assertTrue("Overview reference is no visible", executor.is_element_present(locators_faq.faqOverview));
        assertTrue("Recent reference is no visible", executor.is_element_present(locators_faq.faqRecent));

    }

    public void checkIfFAQQuestionsAreVisible() {
        assertTrue("Any of questions is no visible", executor.is_element_present(locators_faq.basicQuestionLocator));
        assertTrue("Any of categories is no visible", executor.is_element_present(locators_faq.basicCategoryLocator));
    }

    //Checking if FAQ chosen question is properly linked ///////////////////////////////////////////////////////////////
    public void clickChosenQuestionReference() {
        executor.click(locators_faq.specificQuestionLocator);
    }

    public void checkIfAnswearIsCorrect() {
        assertTrue("Answear for chosen question is incorrect", executor.is_element_present(locators_faq.specificAnswerLocator));
    }

    //Checking if FAQ chosen category is properly linked and it is possible to navigate to add new article page ////////
    public void openChosenCategoryPage() throws InterruptedException {
        executor.click(locators_faq.specificCategoryLocator);
        Thread.sleep(2000);
    }

    public void checkContentOfChosenCategoryPage() {
        assertTrue("Home reference is no visible", executor.is_element_present(locators_faq.homeRefernece));
        assertTrue("FAQs reference is no visible", executor.is_element_present(locators_faq.faqsRefernece));
        assertTrue("Submit a request referance is no visible", executor.is_element_present(locators_faq.submitArequestRefernece));
        assertTrue("Search text field is no visible", executor.is_element_present(locators_faq.categorySearcherTextBox));
        assertTrue("Search button is no visible", executor.is_element_present(locators_faq.categorySearcherButton));
        assertTrue("Overview reference is no visible", executor.is_element_present(locators_faq.faqOverview));
        assertTrue("Recent reference is no visible", executor.is_element_present(locators_faq.faqRecent));
    }

    public void clickAddNewArticleButton() {
        executor.click(locators_faq.addArticleButton);
    }

    public void checkContenOfAddNewArticllaPage() {
        assertTrue("Email textfield is no visible", executor.is_element_present(locators_faq.emailTextfield));
        assertTrue("Password textfield is no visible", executor.is_element_present(locators_faq.passworTextField));
        assertTrue("Stay logged in checkbox is no visible", executor.is_element_present(locators_faq.stayLoggedIncheckbox));
        assertTrue("I am Agent Reference in no visible", executor.is_element_present(locators_faq.iAmAgentReference));
        assertTrue("Forgot password reference is no visible", executor.is_element_present(locators_faq.forgotMyPasswordReference));
        assertTrue("Sign up reference is no visible", executor.is_element_present(locators_faq.signUpReference));
        assertTrue("Sign in reference is no visible", executor.is_element_present(locators_faq.signInButton));
    }

    //Checking if searcher works properly //////////////////////////////////////////////////////////////////////////////
    public void enterPhraseIntoSearcher(String Phrase) {
        executor.send_keys(locators_faq.searcherTextBox, Phrase);
    }

    public void clickSearchButton() throws InterruptedException {
        executor.click(locators_faq.searcherButton);
        Thread.sleep(3000);
    }

    public void checkIfResultscontainGivenPhrase(String Phrase) {
        List<WebElement> Titles = executor.getWebElementsList(locators_faq.basicSearcherResultTitle);
        for (WebElement title : Titles) {
            assertTrue("Title does not contain needed phrase", executor.getAttributeTitle(title).contains(Phrase));
        }
    }

    //Checking if submit a request forms is available and has proper content ///////////////////////////////////////////
    public void clickSubmitARequestRefernce() {
        executor.click(locators_faq.submitArequestRefernece);
    }

    public void checkContentOfSubmitARequestPage() {
        assertTrue("'Your email address' is no visible", executor.is_element_present(locators_faq.emailSectionTitle));
        assertTrue("Email textfield is no visible", executor.is_element_present(locators_faq.emailSectionTextfield));
        assertTrue("'Subject' is no visible", executor.is_element_present(locators_faq.subjectSectionTitle));
        assertTrue("Subject textfiels is no visible", executor.is_element_present(locators_faq.subjectSectionTextfield));
        assertTrue("'Description' is no visible", executor.is_element_present(locators_faq.descriptionSectionTitle));
        assertTrue("Description textarea is no visible", executor.is_element_present(locators_faq.descriptionSectionTextfield));
        assertTrue("'Attachment(s)' is no visible", executor.is_element_present(locators_faq.attachementSectionTitle));
        assertTrue("Attachment reference is no visible", executor.is_element_present(locators_faq.attachFileReference));
    }

    //Scenario Outline: Checking if submit a request forms is available and has proper content /////////////////////////
    public void clickSubmitRequestButton() {
        executor.click(locators_faq.submitRequestButton);
    }

    public void checkVisibilityOfErrorAlert() {
        assertTrue("There is no Email alert part", executor.getText(locators_faq.requestAlertMessage).contains("Description: cannot be blank"));
        assertTrue("There is no Subject alert part", executor.getText(locators_faq.requestAlertMessage).contains("Subject: cannot be blank"));
        assertTrue("There is no description alert part", executor.getText(locators_faq.requestAlertMessage).contains("Email: cannot be blank"));
    }

    public void enterAllRequestData(String Email, String Subject, String Description) {
        executor.send_keys(locators_faq.emailSectionTextfield, Email);
        executor.send_keys(locators_faq.subjectSectionTextfield, Subject);
        executor.send_keys(locators_faq.descriptionSectionTextfield, Description);
    }

    public void checkVisibiltyOfSuccesAlert() {
        assertTrue("Succes alert is no visible", executor.is_element_present(locators_faq.successAlertMessage));
    }

    //Checking if retrieving password module has proper content and is usable //////////////////////////////////////////
    public void clickOnForgotMyPasswordReference() {
        executor.click(locators_faq.forgotMyPasswordReference);
    }

    public void clickSubmitEmailAddressButton() {
        executor.click(locators_faq.submitRetrievingPassword);
    }

    public void checkIfErrorAlertIsVisibleInRetrievingPasswordModule() {
        assertTrue("Error alert is no visible", executor.is_element_present(locators_faq.errorRetrievePassswordAlert));
    }

    public void enterEmailIntoProperTextfield() {
        executor.send_keys(locators_faq.emailRetrievingPasswordTextField, "emailwybitnietestowy@gmail.com");
    }

    public void checkVisibilityOfSuccessAlertInRetrievingPasswordModule() {
        assertTrue("Success alert is no visible", executor.is_element_present(locators_faq.succesRetrievePassswordAlert));
    }

    //Checking if Sign-up to epoints support form has proper content ///////////////////////////////////////////////////
    public void clickSignUpReference() {
        executor.click(locators_faq.signUpReference);
    }

    public void checkContenOfSugnUpToepointsSupportPage() {
        assertTrue("Name text field is no visible", executor.is_element_present(locators_faq.fullNametextField));
        assertTrue("Email text field is no visible", executor.is_element_present(locators_faq.emailAdressTextfield));
        assertTrue("Capcha image is no visible", executor.is_element_present(locators_faq.twoWordsCapcha));
        assertTrue("Refresh capcha reference is no visible", executor.is_element_present(locators_faq.twoNewWordsReference));
        assertTrue("Rewrite capcha text field is no visible", executor.is_element_present(locators_faq.capachaValidationTextField));
        assertTrue("Sign me up button is no visible", executor.is_element_present(locators_faq.signMeUpButton));
        assertTrue("Cancel reference is no visible", executor.is_element_present(locators_faq.cancelReference));
    }

    //Checking if Sign-up to epoints support form works properly ///////////////////////////////////////////////////////
    public void enterAllnededDataInignUpToEpointsSupportForm(String Name, String Email, String Capcha) {
        executor.send_keys(locators_faq.fullNametextField, Name);
        executor.send_keys(locators_faq.emailAdressTextfield, Email);
        executor.send_keys(locators_faq.capachaValidationTextField, Capcha);
    }

    public void checkVisibilityOfCapchaErrorAlert() {
        assertTrue("Capcha error alert is no visible", executor.is_element_present(locators_faq.capchaErrorAlert));
    }

    public void clickSignMeUpButton() throws InterruptedException {
        executor.click(locators_faq.signMeUpButton);
        Thread.sleep(2000);
    }

}
