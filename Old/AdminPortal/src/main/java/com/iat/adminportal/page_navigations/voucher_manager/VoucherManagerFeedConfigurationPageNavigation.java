package com.iat.adminportal.page_navigations.voucher_manager;

import com.iat.adminportal.locators.voucher_manager.VoucherManagerFeedConfigurationPageLocators;
import com.iat.adminportal.page_navigations.AbstractPage;
import cucumber.api.DataTable;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class VoucherManagerFeedConfigurationPageNavigation extends AbstractPage {

    protected VoucherManagerFeedConfigurationPageLocators locators = new VoucherManagerFeedConfigurationPageLocators();


    public VoucherManagerFeedConfigurationPageNavigation() {
        super("");
    }

    public void open() {
        super.open();
    }

    public void checkIfCreateViewOpened() {
//        Thread.sleep(1000);
        executor.waitUntilElementAppears(locators.create_new_form);
        assertTrue("Create new screen not opened!", executor.is_element_visible(locators.create_new_form));
    }


    public void checkIfLabelsAreAvailable() {
        assertTrue("Label for first step is incorrect!!!", executor.is_element_present(locators.title_panel_label_step_1));
        assertTrue("Label of new form is incorrect!!!", executor.is_element_present(locators.title_panel_label_create));
    }

    public void checkIfNameFieldIsAvailable() {
        assertTrue("Name label is not available!!!", executor.is_element_present(locators.name_field_label));
        assertTrue("Name input is not available!!!", executor.is_element_present(locators.name_field_input));
        //TODO:Start checking for extra properties like REQUIRED, CLASS, MAXLENGHT etc.
    }

    public void checkIfShortNameFieldIfAvailable() {
        assertTrue("ShortName label is not available!!!", executor.is_element_present(locators.short_name_label));
        assertTrue("ShortName input is not available!!!", executor.is_element_present(locators.short_name_input));
        //TODO:Start checking for extra properties like REQUIRED, CLASS, MAXLENGHT etc.
    }

    public void checkIfLocationURLFieldIfAvailable() {
        assertTrue("Location URL label is not available", executor.is_element_present(locators.location_url_label));
        assertTrue("Location URL textarea is not available", executor.is_element_present(locators.location_url_input));
        //TODO:Start checking for extra properties like REQUIRED, CLASS, MAXLENGHT etc.
    }

    public void checkIfAffiliateNetworkDropDownIsAvailable() {
        assertTrue("Affiliate Network drop down label is not available", executor.is_element_present(locators.affiliate_network_label));
        assertTrue("Affiliate Network drop down field is not available", executor.is_element_present(locators.affiliate_network_dropdown));
        //TODO:Check options on drop down list, values, and list opening
        //TODO:Start checking for extra properties like REQUIRED, CLASS, MAXLENGHT etc.
    }

    public void checkIfProductPropertyNameFieldIsAvailable() {
        assertTrue("Product Property Name label is not available", executor.is_element_present(locators.product_property_label));
        assertTrue("Product Property Name input is not available", executor.is_element_present(locators.product_property_input));
        //TODO:Start checking for extra properties like REQUIRED, CLASS, MAXLENGHT etc.
    }

    public void checkIfContentTypeDropDownIsAvailable(DataTable contentTypes) {
        assertTrue("ContentType label is not available", executor.is_element_present(locators.content_type_label));
        assertTrue("ContentType element is not available", executor.is_element_present(locators.content_type_dropdown));

        List<WebElement> currentHeaders = executor.getAllOptions(locators.content_type_dropdown);
        for (int i = 0; i < contentTypes.raw().size(); i++) {
            assertTrue("ContentType option-" + i + " " + currentHeaders.get(i).getText() + "is not equal " + contentTypes.raw().get(i).get(0), currentHeaders.get(i).getText().equalsIgnoreCase(contentTypes.raw().get(i).get(0)));
        }
    }

//    public void check_if_ContentType_DD_Content_is_Proper(){
//        executor.click(locators.content_type_dropdown);
//
//
//        Locator newLocator = new Locator(LocatorType.XPATH, locators.content_type_dropdown.get_body()+"[2]");
////        System.out.println(executor.getText(newLocator));
//        assertTrue("Content Type value [CSV_WITH_HEADER] is not available ", executor.check_element_contains_text(newLocator, "CSV_WITH_HEADER"));
//
//        newLocator = new Locator(LocatorType.XPATH, locators.content_type_dropdown.get_body()+"[3]");
//        assertTrue("Content Type value [XML] is not available ", executor.check_element_contains_text(newLocator, "XML"));
//
//        newLocator = new Locator(LocatorType.XPATH, locators.content_type_dropdown.get_body()+"[4]");
//        assertTrue("Content Type value [JSON] is not available ", executor.check_element_contains_text(newLocator, "JSON"));
//
//        newLocator = new Locator(LocatorType.XPATH, locators.content_type_dropdown.get_body()+"[5]");
//        assertTrue("Content Type value [TAB_DELIMITED] is not available ", executor.check_element_contains_text(newLocator, "TAB_DELIMITED"));
//
//        //to close dropDown
//        executor.click(locators.content_type_dropdown);
//    }

    public void check_if_PullMethod_DropDown_available(DataTable pullMethod) {
        assertTrue("Pull Method label is not available", executor.is_element_present(locators.pull_method_label));
        assertTrue("Pull Method dropDown field is not available", executor.is_element_present(locators.pull_method_dropdown));

        List<WebElement> currentHeaders = executor.getAllOptions(locators.pull_method_dropdown);
        for (int i = 0; i < pullMethod.raw().size(); i++) {
            assertTrue("Pull Method option-" + i + " " + currentHeaders.get(i).getText() + "is not equal " + pullMethod.raw().get(i).get(0), currentHeaders.get(i).getText().equalsIgnoreCase(pullMethod.raw().get(i).get(0)));
        }
    }

//    public void check_if_PullMethod_DD_Content_is_Proper(){
//        executor.click(locators.content_type_dropdown);
//
//        Locator newLocator = new Locator(LocatorType.XPATH, locators.PullMethod_opt_dropD.get_body()+"[2]");
////        System.out.println(executor.getText(newLocator));
//        assertTrue("Pull Method value [HTTP_REST_LIKE] is not available ", executor.check_element_contains_text(newLocator, "HTTP_REST_LIKE"));
//
//        newLocator = new Locator(LocatorType.XPATH, locators.PullMethod_opt_dropD.get_body()+"[3]");
//        assertTrue("Pull Method value [HTTP_BASIC_AUTH_REST_LIKE] is not available ", executor.check_element_contains_text(newLocator, "HTTP_BASIC_AUTH_REST_LIKE"));
//
//        newLocator = new Locator(LocatorType.XPATH, locators.PullMethod_opt_dropD.get_body()+"[4]");
//        assertTrue("Pull Method value [FTP] is not available ", executor.check_element_contains_text(newLocator, "FTP"));
//
//        newLocator = new Locator(LocatorType.XPATH, locators.PullMethod_opt_dropD.get_body()+"[5]");
//        assertTrue("Pull Method value [HTTP_EXTRA_HEADERS_REST_LIKE] is not available ", executor.check_element_contains_text(newLocator, "HTTP_EXTRA_HEADERS_REST_LIKE"));
//
//        newLocator = new Locator(LocatorType.XPATH, locators.PullMethod_opt_dropD.get_body()+"[6]");
//        assertTrue("Pull Method value [AMAZON_SIGNED_HTTP_REST_LIKE] is not available ", executor.check_element_contains_text(newLocator, "AMAZON_SIGNED_HTTP_REST_LIKE"));
//
//        //to close dropDown
//        executor.click(locators.content_type_dropdown);
//    }

    public void check_if_Cron_Timing_field_available() {
        assertTrue("Cron Timing label is not available", executor.is_element_present(locators.CronTiming_label));
        assertTrue("Cron Timing field is not available", executor.is_element_present(locators.CronTiming_textbox));
    }

//    public void check_if_Pre_PROCESSING_COMPONENT_available(){
//
//        assertTrue("Pre-Processing panel (L) is not available", executor.is_element_present(locators.PreProcessingStages_L_panel));
//
//        assertTrue("Pre-Processing panel (P) is not available", executor.is_element_present(locators.getPreProcessingStages_R_panel));
//
//        assertTrue("Pre-Processing panel add btn is not available", executor.is_element_present(locators.getPreProcessingStages_moveToRight));
//
//        assertTrue("Pre-Processing panel add all btn is not available", executor.is_element_present(locators.getPreProcessingStages_moveToRightAll));
//
//        assertTrue("Pre-Processing panel remove btn is not available", executor.is_element_present(locators.getPreProcessingStages_moveToLeft));
//
//        assertTrue("Pre-Processing panel remove all btn is not available", executor.is_element_present(locators.getPreProcessingStages_moveToLeftAll));
//    }

    public void check_if_preprocessingstages_section_available(DataTable preProcessingValues) {
        assertTrue("Pre-processing Stages label is not available", executor.is_element_present(locators.preProcessingStages_label));
        assertTrue("Pre-processing Stages area is not available", executor.is_element_present(locators.preProcessingStage_area));
    }

}
