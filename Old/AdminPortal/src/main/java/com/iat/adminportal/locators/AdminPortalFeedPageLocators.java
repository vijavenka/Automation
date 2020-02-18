package com.iat.adminportal.locators;

public class AdminPortalFeedPageLocators implements IPageLocators {

    public Locator column_headers = new Locator(LocatorType.XPATH, "//table/thead/tr/th");

    public Locator search_input = new Locator(LocatorType.XPATH, "//input[@id='searchkeyword']");
    public Locator search_button = new Locator(LocatorType.XPATH, "//button[@id='searchbtn']");

    //columns
    public Locator nameTableContent = new Locator(LocatorType.XPATH, "//table[@id='feedsTable']//td[@class='name']");
    //columns

    public Locator list_view_createnew_button = new Locator(LocatorType.XPATH, "//a[@id='feed_add')]");
    public Locator list_view_table_info = new Locator(LocatorType.XPATH, "//div[contains(@id, 'Table_info')]");
    public Locator list_view_pager_select = new Locator(LocatorType.XPATH, "//div[contains(@id, 'Table_length')]/label/select");
    public Locator list_view_pager_next_button = new Locator(LocatorType.XPATH, "//a[contains(@id, 'Table_next')]");

    public Locator table_1st_row = new Locator(LocatorType.XPATH, "//table/tbody/tr[1]");
    public Locator table_1st_row_activate_button = new Locator(LocatorType.XPATH, "//table/tbody/tr[1]/td[4]/span/a[contains(., 'Activate')]");
    public Locator table_1st_row_delete_button = new Locator(LocatorType.XPATH, "//table/tbody/tr[1]/td[5]/a");
    public Locator table_1st_row_deleted_text = new Locator(LocatorType.XPATH, "//table/tbody/tr[1]/td[5]/span[contains(., 'Deleted')]");
    public Locator table_1st_row_run_button = new Locator(LocatorType.XPATH, "//table/tbody/tr[1]/td[6]/a");
    public Locator table_1st_row_status_wbutton = new Locator(LocatorType.XPATH, "//table/tbody/tr[1]/td[4]/span/span[1]");
    public Locator table_1st_row_status_wobutton = new Locator(LocatorType.XPATH, "//table/tbody/tr[1]/td[4]/span");

    public Locator table_2nd_row = new Locator(LocatorType.XPATH, "//table/tbody/tr[2]");

    public Locator processing_box = new Locator(LocatorType.XPATH, "//div[@id='feedsTable_processing']");

    public Locator delete_confirm = new Locator(LocatorType.XPATH, "//div[@role='dialog']/div[contains(.,'Please confirm')]");
    public Locator delete_confirm_NO_button = new Locator(LocatorType.XPATH, "//div[@role='dialog']/div/div[@class='ui-dialog-buttonset']/button[contains(.,'No')]");
    public Locator delete_confirm_X_button = new Locator(LocatorType.XPATH, "//div[@role='dialog']/div[1]/a");
    public Locator delete_confirm_YES_button = new Locator(LocatorType.XPATH, "//div[@role='dialog']/div/div[@class='ui-dialog-buttonset']/button[contains(.,'Yes')]");

    public Locator details_step1_name_input = new Locator(LocatorType.XPATH, "//input[@id='name']");
    public Locator details_step1_description_input = new Locator(LocatorType.XPATH, "//input[@id='description']");
    public Locator details_step1_shortname_input = new Locator(LocatorType.XPATH, "//input[@id='shortName']");
    public Locator details_step1_locationurl_input = new Locator(LocatorType.XPATH, "//input[@id='locationURL']");
    public Locator details_step1_type_select = new Locator(LocatorType.XPATH, "//select[@name='type']");
    public Locator details_step1_country_select = new Locator(LocatorType.XPATH, "//select[@name='country']");
    public Locator details_step1_merchant_select = new Locator(LocatorType.XPATH, "//select[@name='merchantId']");
    public Locator details_step1_categories_div = new Locator(LocatorType.XPATH, "//div[@id='categoriesTree']");
    public Locator details_step1_network_select = new Locator(LocatorType.XPATH, "//select[@name='network']");
    public Locator details_step1_feedgenerator_select = new Locator(LocatorType.XPATH, "//select[@name='feedGenerator']");
    public Locator details_step1_superfeed_input = new Locator(LocatorType.XPATH, "//input[@id='superFeed1']");
    public Locator details_step1_prodpropname_input = new Locator(LocatorType.XPATH, "//input[@id='productPropertyName']");
    public Locator details_step1_prodtitle_input = new Locator(LocatorType.XPATH, "//input[@id='productTitleField']");
    public Locator details_step1_contentype_select = new Locator(LocatorType.XPATH, "//select[@name='contentType']");
    public Locator details_step1_pullmethod_select = new Locator(LocatorType.XPATH, "//select[@name='pullMethod']");
    public Locator details_step1_scheduletype_select = new Locator(LocatorType.XPATH, "//select[@name='scheduleType']");
    // add pre-processing stages locator(s)
    public Locator details_next_button = new Locator(LocatorType.XPATH, "//button[contains(.,'Next')]");
    public Locator details_save_button = new Locator(LocatorType.XPATH, "//button[contains(.,'Save')]");
    public Locator details_cancel_button = new Locator(LocatorType.XPATH, "//button[contains(.,'Cancel')]");

}
