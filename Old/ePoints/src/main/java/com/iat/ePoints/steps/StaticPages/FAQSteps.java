package com.iat.ePoints.Steps.StaticPages;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Executors.SeleniumExecutor;
import com.iat.ePoints.Navigations.StaticPages.FAQNavigation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 26.11.13
 * Time: 18:14
 * To change this template use File | Settings | File Templates.
 */
public class FAQSteps {

    private IExecutor executor;
    private FAQNavigation faqNavi;

    public FAQSteps(SeleniumExecutor executor) {
        this.executor = executor;

    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        faqNavi = new FAQNavigation(executor);
    }

    @After
    public void tear_down() {
        executor.stop();
    }

    // scenario 1 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @When("^Check content of Header for FAQ link$")
    public void Check_content_of_Header_for_FAQ_link() throws Throwable {
        faqNavi.checkContentOfHeaderStaticPagesLinks();
    }

    @Then("^FAQ hyperlink should be available in header$")
    public void FAQ_hyperlink_should_be_available_in_header() throws Throwable {
        faqNavi.clickOnFAQLink();
    }

    @Then("^Should link to FAQ page$")
    public void Should_link_to_FAQ_page() throws Throwable {
        faqNavi.checkIfFAQlinkWorksPropery();
    }

    // scenario 2 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Given("^FAQ page is opened$")
    public void FAQ_page_is_opened() throws Throwable {
        faqNavi.clickOnFAQLink();
    }

    @When("^Check content of page FAQ$")
    public void Check_content_of_page_FAQ() throws Throwable {
        faqNavi.checkContentOfFAQPage();
    }

    @Then("^Frequently asked categories and questions should be displayed$")
    public void Information_about_the_frequently_asked_categories_andquestions_should_be_displayed() throws Throwable {
        faqNavi.checkIfFAQQuestionsAreVisible();
    }

    // scenario 3 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Click on chosen question name$")
    public void Click_on_chosen_question_name() throws Throwable {
        faqNavi.clickChosenQuestionReference();
    }

    @Then("^Proper answer for the question should be visible$")
    public void proper_answer_for_the_question_should_be_visible() throws Throwable {
        faqNavi.checkIfAnswearIsCorrect();
    }

    // scenario 4 //////////////////////////////////////////////////////////////////////////////////////////////////////


    @Then("^Open Chosen category page$")
    public void Open_Chosen_category_page() throws Throwable {
        faqNavi.openChosenCategoryPage();
    }

    @Then("^Check content of chosen category page$")
    public void Check_content_of_chosen_category_page() throws Throwable {
        faqNavi.checkContentOfChosenCategoryPage();
    }

    @Then("^Go to adding new article page$")
    public void Go_to_adding_new_article_page() throws Throwable {
        faqNavi.clickAddNewArticleButton();
    }

    @Then("^Check content of add article page$")
    public void Check_content_of_add_article_page() throws Throwable {
        faqNavi.checkContenOfAddNewArticllaPage();
    }

    // scenario 5 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Searcher can be used with '(.+)'$")
    public void Searcher_text_field_is_available(String Phrase) throws Throwable {
        faqNavi.enterPhraseIntoSearcher(Phrase);
    }

    @Then("^Searcher button is pushable$")
    public void Searcher_button_is_pushable() throws Throwable {
        faqNavi.clickSearchButton();
    }

    @Then("^Results should contain question with '(.+)'$")
    public void Results_should_contain_question_with_entered_phrase(String Phrase) throws Throwable {
        faqNavi.checkIfResultscontainGivenPhrase(Phrase);
    }

    // scenario 6 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Submit a request form should be opened$")
    public void Submit_a_request_form_should_be_opened() throws Throwable {
        faqNavi.clickSubmitARequestRefernce();
    }

    @Then("^Submit a request page should has proper content$")
    public void Submit_a_request_page_should_has_proper_content() throws Throwable {
        faqNavi.checkContentOfSubmitARequestPage();
    }

    // scenario 7 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Alerts should be displayed after submitting request without filling any data$")
    public void Alerts_should_be_displayed_after_submitting_request_without_filling_any_data() throws Throwable {
        faqNavi.clickSubmitRequestButton();
        faqNavi.checkVisibilityOfErrorAlert();
    }

    @Then("^All text fields should be editable using '(.+)' '(.+)' '(.+)'$")
    public void All_text_fields_should_be_editable(String Email, String Subject, String Description) throws Throwable {
        faqNavi.enterAllRequestData(Email, Subject, Description);
    }

    @Then("^After submitting request success alert should appear$")
    public void After_submitting_request_success_alert_should_appear() throws Throwable {
        faqNavi.clickSubmitRequestButton();
        faqNavi.checkVisibiltyOfSuccesAlert();
    }

    // scenario 8 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Forgot my password module should be opened$")
    public void forgot_my_password_module_should_be_opened() throws Throwable {
        faqNavi.clickOnForgotMyPasswordReference();
    }

    @Then("^Alert should be displayed after submitting request without filling any data$")
    public void Alert_should_be_displayed_after_submitting_request_without_filling_any_data() throws Throwable {
        faqNavi.clickSubmitEmailAddressButton();
        faqNavi.checkIfErrorAlertIsVisibleInRetrievingPasswordModule();
    }

    @Then("^After filling email and submitting success alert should be visible$")
    public void After_filling_email_and_submitting_success_alert_should_be_visible() throws Throwable {
        faqNavi.enterEmailIntoProperTextfield();
        faqNavi.clickSubmitEmailAddressButton();
        faqNavi.checkVisibilityOfSuccessAlertInRetrievingPasswordModule();
    }

    // scenario 9 //////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Sign-up Form should be opened$")
    public void Sign_up_Form_should_be_opened() throws Throwable {
        faqNavi.clickSignUpReference();
    }

    @Then("^Sing-up to epoints support page should has proper content$")
    public void Sing_up_to_epoints_support_page_should_has_proper_content() throws Throwable {
        faqNavi.checkContenOfSugnUpToepointsSupportPage();
    }

    // scenario 10 /////////////////////////////////////////////////////////////////////////////////////////////////////

    @Then("^Needed data should be filled using '(.+)' '(.+)' '(.+)'$")
    public void Needed_data_should_be_filled(String Name, String Email, String Capcha) throws Throwable {
        faqNavi.enterAllnededDataInignUpToEpointsSupportForm(Name, Email, Capcha);
        go_sleep(1000);
        faqNavi.clickSignMeUpButton();
        go_sleep(5000);
    }

    @Then("^After singing in verification alert should appear because of wrong capcha code$")
    public void After_singing_in_verification_alert_should_appear_because_of_wrong_capcha_code() throws Throwable {
        faqNavi.checkVisibilityOfCapchaErrorAlert();
    }

}
