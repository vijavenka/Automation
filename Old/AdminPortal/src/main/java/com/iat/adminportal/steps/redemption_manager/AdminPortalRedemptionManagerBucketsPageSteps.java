package com.iat.adminportal.steps.redemption_manager;

import com.iat.adminportal.page_navigations.redemption_manager.AdminPortalRedemptionBucketsPageNavigation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AdminPortalRedemptionManagerBucketsPageSteps {

    private AdminPortalRedemptionBucketsPageNavigation redemNavi = new AdminPortalRedemptionBucketsPageNavigation();
    private String recordID;
    private String recordStatus;

    public AdminPortalRedemptionManagerBucketsPageSteps() {

    }

    @Then("^Bucket List screen is opened properly$")
    public void Bucket_Manager_List_screen_is_opened_properly() throws Throwable {
        redemNavi.check_if_BucketsPage_opened();

    }

    //*************************************************************************************
    //TS2_AP_Bucket_List_Screen   SCENARIO 1***********************************************

    @When("^He looks at the Bucket List Table$")
    public void He_looks_at_the_Bucket_List_Table() throws Throwable {
        redemNavi.checkIfBucketTableVisible();
    }

    @Then("^Table of buckets have proper columns$")
    public void Table_of_buckets_have_proper_columns() throws Throwable {
        redemNavi.checkNameHeader();
        redemNavi.checkTagHeader();
        redemNavi.checkItemCountHeader();
        redemNavi.checkOrderHeader();
        redemNavi.checkPublishHeader();
        redemNavi.checkDeleteHeader();
        redemNavi.checkEditHeader();
    }

    @Then("^Edit option should be available for each bucket$")
    public void Edit_option_should_be_available_for_each_bucket() throws Throwable {
        // Express the Regexp above with the code you wish you had
        redemNavi.checkEditOption();
    }

    @Then("^Publish/Unpublish option should be available for each bucket$")
    public void Publish_Unpublish_option_should_be_available_for_each_bucket() throws Throwable {
        redemNavi.checkPublishOption();

    }

    @Then("^Delete option should be available for each bucket \\(except Default Home Bucket\\)$")
    public void Delete_option_should_be_available_for_each_bucket_except_Default_Home_Bucket() throws Throwable {
        redemNavi.checkDeleteOption();

    }

    @Then("^Create New option should be available for create new bucket$")
    public void Create_New_option_should_be_available_for_create_new_bucket() throws Throwable {
        redemNavi.checkCreateNewBtn();

    }

    @Then("^Update order option should be available for change buckets order$")
    public void Update_order_option_should_be_available_for_change_buckets_order() throws Throwable {
        // Express the Regexp above with the code you wish you had
        redemNavi.checkUpdateOrderBtn();
    }

    //*************************************************************************************

    //*************************************************************************************
    //TS2_AP_Bucket_List_Screen   SCENARIO 2***********************************************

    @When("^User clicks on \"([^\"]*)\" header$")
    public void User_clicks_on_header(String header) throws Throwable {
        redemNavi.clickHeader(header);
    }

    @Then("^Buckets in table should be sorted by \"([^\"]*)\" column$")
    public void Buckets_in_table_should_be_sorted_by_column(String column) throws Throwable {
        redemNavi.checkIfSorted(column);

    }

    //*************************************************************************************
    //TS2_AP_Bucket_List_Screen   SCENARIO 3***********************************************

    @When("^User clicks on 'Default Home Bucket' link in NAME column$")
    public void User_clicks_on_Default_Home_Bucket_link_in_NAME_column() throws Throwable {
        redemNavi.clickOnHomeBucketName();
    }

    @Then("^Bucket create/edit screen is opened in view mode$")
    public void Bucket_create_edit_screen_is_opened_in_view_mode() throws Throwable {
        redemNavi.checkIfBucketCreateEditScreenOpened();
        redemNavi.checkIfBucketCreateEditScreenIsInViewingMode();
    }

    //*************************************************************************************
    //TS2_AP_Bucket_List_Screen   SCENARIO 4***********************************************

    @When("^User clicks on 'Home' link in TAG column$")
    public void User_clicks_on_Home_link_in_TAG_column() throws Throwable {
        redemNavi.clickOnHomeBucketTag();
    }

    //*************************************************************************************
    //TS2_AP_Bucket_List_Screen   SCENARIO 5***********************************************

    @When("^User clicks on Home Bucket EDIT option$")
    public void User_clicks_on_Home_Bucket_EDIT_option() throws Throwable {
        redemNavi.clickEditBtn("HOME");
    }

    @Then("^Bucket create/edit screen is opened in edit mode$")
    public void Bucket_create_edit_screen_is_opened_in_edit_mode() throws Throwable {
        redemNavi.checkIfBucketCreateEditScreenOpened();
        redemNavi.checkIfBucketCreateEditScreenIsInEditMode();
    }

    //*************************************************************************************
    //TS2_AP_Bucket_List_Screen   SCENARIO 6***********************************************

    @When("^User clicks on 'Create New' bucket option$")
    public void User_clicks_on_Create_New_bucket_option() throws Throwable {
        redemNavi.clickCreateNew();
    }

    @Then("^Bucket create/edit screen is opened in create new mode$")
    public void Bucket_create_edit_screen_is_opened_in_create_new_mode() throws Throwable {
        redemNavi.checkIfBucketCreateEditScreenOpened();
        redemNavi.checkIfBucketCreateEditScreenIsInCreateMode();
    }

    //*************************************************************************************
    //TS2_AP_Bucket_List_Screen   SCENARIO 7***********************************************

    @When("^User clicks on Home Bucket NO_OF_ITEMS counter$")
    public void User_clicks_on_Home_Bucket_NO_OF_ITEMS_counter() throws Throwable {
        redemNavi.clickOnNoOfItemsLink("HOME");
    }

    @Then("^Redemption Items List Screen is opened$")
    public void Redemption_Items_List_Screen_is_opened() throws Throwable {
        redemNavi.checkIfRedemptionItemListOpened();
    }

    //*************************************************************************************
    //TS3_AP_Delete_Bucket SCENARIO 1******************************************************


    @Given("^Default Home Bucket is available$")
    public void Default_Home_Bucket_is_available() throws Throwable {
        redemNavi.checkIfHomeBucketAvailable();
    }

    @Given("^It is has display order set to (\\d+)$")
    public void It_is_has_display_order_set_to(int order) throws Throwable {
        redemNavi.checkIfProperOrderByName("HOME", order);
    }

    @When("^User tries to delete this bucket$")
    public void User_tries_to_delete_this_bucket() throws Throwable {
        //Nothing to do here
    }

    @Then("^He is not able to do so because 'Delete' button is not available$")
    public void He_is_not_able_to_do_so_because_Delete_button_is_not_available() throws Throwable {
        redemNavi.checkIfHomeBucketHasDeleteButton();
    }

    //*************************************************************************************
    //TS3_AP_Delete_Bucket SCENARIO 2******************************************************

    @Given("^There is more than one Bucket available$")
    public void There_is_more_than_one_Bucket_available() throws Throwable {
        redemNavi.checkThereAreMoreBucketsThanOne();
    }

    @When("^User clicks on the 'Delete' button next to a \"([^\"]*)\"$")
    public void User_clicks_on_the_Delete_button_next_to_a(String name) throws Throwable {
        redemNavi.clickToDeleteChosenBucket(name);
    }

    @Then("^Confirmation pop-up is displayed$")
    public void Confirmation_pop_up_is_displayed() throws Throwable {
        redemNavi.checkIfConfirmationPopUpDisplayed();
    }

    @Then("^User is asked whether he wants to delete this bucket or not$")
    public void User_is_asked_whether_he_wants_to_delete_this_bucket_or_not() throws Throwable {
        redemNavi.checkIfProperConfirmationMessageDisplayed("Are you sure you want to delete this bucket?");
    }

    @Then("^'Yes' and 'No' buttons are available$")
    public void _Yes_and_No_buttons_are_available() throws Throwable {
        redemNavi.checkIfYesButtonDisplayed();
        redemNavi.checkIfNoButtonDisplayed();
    }

    @Then("^'No' button is set as a default$")
    public void _No_button_is_set_as_a_default() throws Throwable {
        redemNavi.checkIfNoButtonIsDefault();
    }

    //*************************************************************************************
    //TS3_AP_Delete_Bucket SCENARIO 3******************************************************

    @When("^User clicks on \"([^\"]*)\"$")
    public void User_clicks_on(String button) throws Throwable {
        button = button.toLowerCase();
        if (button.equals("x")) redemNavi.clickOnXButton();
        else if (button.equals("no")) redemNavi.clickOnNoButton();

    }

    @Then("^User stays on the Bucket Manager List$")
    public void User_stays_on_the_Bucket_Manager_List() throws Throwable {
        redemNavi.check_if_BucketsPage_opened();
    }

    @Then("^\"([^\"]*)\" is still displayed$")
    public void is_still_displayed(String name) throws Throwable {
        redemNavi.checkIfBucketStillOnTheList(name);
    }

    @Then("^'Delete' button is still present next to a chosen \"([^\"]*)\".$")
    public void _Delete_button_is_still_present_next_to_a_chosen_(String name) throws Throwable {
        redemNavi.checkIfDeleteButtonDisplayedForBucket(name);
    }

    //*************************************************************************************
    //TS3_AP_Delete_Bucket SCENARIO 4******************************************************

    @When("^User confirms to delete the bucket$")
    public void User_confirms_to_delete_the_bucket() throws Throwable {
        redemNavi.clickOnYesButton();
    }

    @Then("^\"([^\"]*)\" is no longer displayed on the Bucket Manager$")
    public void is_no_longer_displayed_on_the_Bucket_Manager(String name) throws Throwable {
        redemNavi.checkIfBucketDisappearedFromTheList(name);
    }


}
