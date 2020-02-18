package com.iat.adminportal.locators.voucher_manager;

import com.iat.adminportal.locators.IPageLocators;
import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

public class VoucherManagerFeedConfigurationPageLocators implements IPageLocators {

    public Locator create_new_form = new Locator(LocatorType.XPATH, "//div[@id='fancybox']/form[@id='voucherConfigForm']");

    public Locator title_panel_label_step_1 = new Locator(LocatorType.XPATH, "//span[@class='type' and contains(text(),'Voucher Manager - Screen 1 of 2')]");
    public Locator title_panel_label_create = new Locator(LocatorType.XPATH, "//span[@class='option' and contains(text(),'Create New')]");

    //Fields and Drop-Downs
    public Locator name_field_label = new Locator(LocatorType.XPATH, "//label[contains(@for, 'name') and contains(text(), 'Name:')]");
    public Locator name_field_input = new Locator(LocatorType.XPATH, "//input[@id='name' and contains(@type, 'text')]");

    public Locator short_name_label = new Locator(LocatorType.XPATH, "//label[contains(@for, 'shortName') and contains(text(), 'Short Name:')]");
    public Locator short_name_input = new Locator(LocatorType.XPATH, "//input[@id='shortName' and contains(@type, 'text')]");

    public Locator location_url_label = new Locator(LocatorType.XPATH, "//label[contains(@for, 'locationURL') and contains(text(), 'Location URL:')]");
    public Locator location_url_input = new Locator(LocatorType.XPATH, "//textarea[@id='locationURL' and contains(@name, 'locationURL')]");

    public Locator affiliate_network_label = new Locator(LocatorType.XPATH, "//label[contains(@for, 'network') and contains(text(), 'Affiliate Network:')]");
    public Locator affiliate_network_dropdown = new Locator(LocatorType.XPATH, "//select[contains(@name, 'networkCode')]");

    public Locator product_property_label = new Locator(LocatorType.XPATH, "//label[contains(@for, 'productPropertyName') and contains(text(), 'Product Property Name:')]");
    public Locator product_property_input = new Locator(LocatorType.XPATH, "//input[@id='productPropertyName' and contains(@type, 'text')]");

    public Locator content_type_label = new Locator(LocatorType.XPATH, "//label[contains(@for, 'contentType') and contains(text(), 'Content Type:')]");
    public Locator content_type_dropdown = new Locator(LocatorType.XPATH, "//select[contains(@name, 'contentType')]");

    public Locator pull_method_label = new Locator(LocatorType.XPATH, "//label[@for='pullMethod' and contains(text(),'Pull Method:')]");
    public Locator pull_method_dropdown = new Locator(LocatorType.XPATH, "//select[@name='pullMethod']");

    public Locator PullMethod_opt_dropD = new Locator(LocatorType.XPATH, "//select[@name='pullMethod']/option");
    // first selection //select[@name='pullMethod']/option[1]

    public Locator CronTiming_label = new Locator(LocatorType.XPATH, "//label[@for='scheduleTiming' and contains(text(),'Cron timing:')]");
    public Locator CronTiming_textbox = new Locator(LocatorType.XPATH, ".//*[@id='scheduleTiming']");

    public Locator preProcessingStages_label = new Locator(LocatorType.XPATH, "//label[@for='preprocessingStages' and contains(text(),'Pre-processing Stages:')]");
    public Locator preProcessingStage_area = new Locator(LocatorType.XPATH, "//div[@class='comboselectbox']");

    public Locator PreProcessingStages_L_panel = new Locator(LocatorType.XPATH, ".//ul[@class='comboselect']");

    public Locator getPreProcessingStages_R_panel = new Locator(LocatorType.XPATH, ".//ul[@class='comboselect ui-sortable']");

    public Locator getPreProcessingStages_moveToLeft = new Locator(LocatorType.XPATH, ".//a[@class='remove btn']");

    public Locator getPreProcessingStages_moveToLeftAll = new Locator(LocatorType.XPATH, ".//a[@class='removeall btn']");

    public Locator getPreProcessingStages_moveToRight = new Locator(LocatorType.XPATH, "//a[@class='add btn']");

    public Locator getPreProcessingStages_moveToRightAll = new Locator(LocatorType.XPATH, "//a[@class='addall btn']");

    public Locator Save_btn = new Locator(LocatorType.XPATH, "//button[@class='edit']submit");

    public Locator Cancel_btn = new Locator(LocatorType.XPATH, "//button[@class='cancel']");

    public Locator Next_btn = new Locator(LocatorType.XPATH, "//button[@class='next']");

    public Locator Edit_btn = new Locator(LocatorType.XPATH, "//button[@class='edit']");
}
