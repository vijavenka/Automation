package com.iat.adminportal.locators.redemption_manager;

import com.iat.adminportal.locators.IPageLocators;
import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

public class AdminPortalRedemptionManagerBucketsPageLocators implements IPageLocators {

    public Locator pageTitleH1 = new Locator(LocatorType.XPATH, "//h1");

    public Locator configTableProcessing = new Locator(LocatorType.XPATH, "//div[@id='bucketsTable_processing' and contains(@style,'visibility: hidden')]");

    public Locator createNewBtn = new Locator(LocatorType.XPATH, ".//*[@id='bucketAdd']");

    public Locator updateOrderBtn = new Locator(LocatorType.XPATH, ".//*[@id='bucketUpdateOrder']");

    public Locator bucketListTable = new Locator(LocatorType.XPATH, ".//*[@id='bucketsTable_wrapper']");

    public Locator tableElements = new Locator(LocatorType.XPATH, ".//*[@id='bucketsTable']/tbody/tr");

    public Locator noOfItemsList = new Locator(LocatorType.XPATH, ".//*/td[@Class='itemsCount']");

    public Locator publishList = new Locator(LocatorType.XPATH, ".//*/td[@Class='published']");

    public Locator deleteList = new Locator(LocatorType.XPATH, ".//*/tr/td[6]");

    public Locator editList = new Locator(LocatorType.XPATH, ".//*/tr/td[7]");

    public Locator nameHeader = new Locator(LocatorType.XPATH, ".//*/th[text()='Name']");

    public Locator tagHeader = new Locator(LocatorType.XPATH, ".//*/th[text()='Tag']");

    public Locator itemsCountHeader = new Locator(LocatorType.XPATH, ".//*/th[text()='Number Of Items']");

    public Locator orderHeader = new Locator(LocatorType.XPATH, ".//*/th[text()='Display Order']");

    public Locator publishHeader = new Locator(LocatorType.XPATH, ".//*/th[text()='Publish']");

    public Locator deleteHeader = new Locator(LocatorType.XPATH, ".//*/th[6]");

    public Locator editHeader = new Locator(LocatorType.XPATH, ".//*/th[7]");

    public Locator getBucketName(String name) {
        String path = ".//*/td/a[text()='" + name + "']";
        return new Locator(LocatorType.XPATH, path);
    }


    public Locator getOrderForElement(int index) {
        String path = ".//*/tr[" + Integer.toString(index) + "]/td[@class='orderId']";
        return new Locator(LocatorType.XPATH, path);
    }

    public Locator getBucketCount(String name) {
        String path = ".//*/td/a[text()='" + name + "']/../..//td[contains(@class,'itemsCount']/a";
        return new Locator(LocatorType.XPATH, path);
    }

    public Locator getBucketOrder(String name) {
        String path = ".//*/a[text()='" + name + "']/../../td[contains(@class,'orderId')]";
        return new Locator(LocatorType.XPATH, path);
    }

    public Locator getPublishStatus(String name) {
        String path = ".//*/a[text()='" + name + "']/../../td[contains(@class,'published']";
        return new Locator(LocatorType.XPATH, path);
    }

    public Locator getDeleteBtn(String name) {
        String path = ".//*/td/a[text()='" + name + "']/../../td[6]/a";
        return new Locator(LocatorType.XPATH, path);
    }

    public Locator getEditBtn(String name) {
        String path = ".//*/td/a[text()='" + name + "']/../../td[7]/a";
        return new Locator(LocatorType.XPATH, path);
    }

    public Locator homeBcktLink = new Locator(LocatorType.XPATH, ".//a[text()='Default Home Bucket']");

    public Locator bcktCreateEdit = new Locator(LocatorType.XPATH, ".//label/span[text()='Bucket Manager']");

    public Locator homeBcktDelete = new Locator(LocatorType.XPATH, ".//*/td/a[text()='HOME']/../../td[6]");

    public Locator bcktCreateEditStatus = new Locator(LocatorType.XPATH, ".//label/span[@class='option']");

    public Locator homeBcktTag = new Locator(LocatorType.XPATH, ".//a[@class='bucketView' and text()='HOME']");

    public Locator redemptionItemsList = new Locator(LocatorType.XPATH, ".//*[@id='content']/div/h1[contains(text(),'Redemption items for bucket')]");

    public Locator popUpConfirmDelete = new Locator(LocatorType.XPATH, "html/body/div[contains(@class,'ui-dialog')]");

    public Locator popUpText = new Locator(LocatorType.XPATH, ".//*[@id='wl_dialog']");

    public Locator popUpYesBtn = new Locator(LocatorType.XPATH, ".//div/button/span[text()='Yes']");

    public Locator popUpNoBtn = new Locator(LocatorType.XPATH, ".//div/button/span[text()='No']");

    public Locator popUpStateOfNoBtn = new Locator(LocatorType.XPATH, ".//div/button/span[text()='No']/..");

    public Locator popUpXBtn = new Locator(LocatorType.XPATH, ".//a/span[text()='close']");
}