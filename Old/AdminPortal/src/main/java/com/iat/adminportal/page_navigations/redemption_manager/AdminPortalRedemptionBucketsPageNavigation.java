package com.iat.adminportal.page_navigations.redemption_manager;

import com.iat.adminportal.locators.redemption_manager.AdminPortalRedemptionManagerBucketsPageLocators;
import com.iat.adminportal.page_navigations.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdminPortalRedemptionBucketsPageNavigation extends AbstractPage {

    protected AdminPortalRedemptionManagerBucketsPageLocators RMlocators = new AdminPortalRedemptionManagerBucketsPageLocators();


    public AdminPortalRedemptionBucketsPageNavigation() {
        super("");
    }

    public void open() {
        super.open();
    }

    public void check_if_BucketsPage_opened() {
        assertTrue("Voucher Manager page not opened properly", executor.check_element_contains_text(RMlocators.pageTitleH1, "Bucket Manager"));
        assertFalse("Bucket Manager page blocked by Processing Box", executor.is_element_visible(RMlocators.configTableProcessing));
    }

    public void checkIfBucketTableVisible() {
        assertTrue("Bucket Manager table is not displayed.", executor.is_element_present(RMlocators.bucketListTable));
    }

    public void checkNameHeader() {
        assertTrue("Name header is not displayed", executor.check_element_contains_text(RMlocators.nameHeader, "Name"));
    }

    public void checkTagHeader() {
        assertTrue("Name header is not displayed", executor.check_element_contains_text(RMlocators.tagHeader, "Tag"));
    }

    public void checkItemCountHeader() {
        assertTrue("Name header is not displayed", executor.check_element_contains_text(RMlocators.itemsCountHeader, "Number Of Items"));
    }

    public void checkOrderHeader() {
        assertTrue("Name header is not displayed", executor.check_element_contains_text(RMlocators.orderHeader, "Display Order"));
    }

    public void checkPublishHeader() {
        assertTrue("Name header is not displayed", executor.check_element_contains_text(RMlocators.publishHeader, "Publish"));
    }

    public void checkDeleteHeader() {
        assertTrue("Name header is not displayed", executor.check_element_contains_text(RMlocators.deleteHeader, "Delete"));
    }

    public void checkEditHeader() {
        assertTrue("Name header is not displayed", executor.check_element_contains_text(RMlocators.editHeader, "Edit"));
    }

    public void checkEditOption() {
        List<WebElement> rows = executor.get_elements(RMlocators.editList);
        for (WebElement element : rows) {
            assertTrue("Edit button is not displayed.", element.getText().equals("EDIT"));
        }
    }

    public void checkPublishOption() {
        List<WebElement> rows = executor.get_elements(RMlocators.publishList);
        for (WebElement element : rows) {
            String published = element.findElement(By.tagName("a")).getText();
            assertTrue("Publish/Unpublish button is not displayed.", published.equals("PUBLISH") || published.equals("UNPUBLISH"));
        }
    }

    public void checkDeleteOption() {
        List<WebElement> rows = executor.get_elements(RMlocators.deleteList);
        int it = 1;
        boolean check = true;
        for (WebElement element : rows) {
            if (!element.getText().equals("DELETE"))
                if (!executor.check_element_contains_text(RMlocators.getOrderForElement(it), "0")) check = false;
            it++;
        }
        assertTrue("Delete button is not displayed.", check);
    }

    public void checkCreateNewBtn() {
        assertTrue("Create new button is not available", executor.is_element_present(RMlocators.createNewBtn));
    }

    public void checkUpdateOrderBtn() {
        assertTrue("Create new button is not available", executor.is_element_present(RMlocators.updateOrderBtn));
    }

    public void clickHeader(String header) {
        header = header.toLowerCase();
        switch (header) {
            case "name":
                executor.click(RMlocators.nameHeader);
                break;
            case "tag":
                executor.click(RMlocators.tagHeader);
                break;
            case "number of items":
                executor.click(RMlocators.itemsCountHeader);
                break;
            case "display order":
                executor.click(RMlocators.orderHeader);
                break;
            case "publish":
                executor.click(RMlocators.publishHeader);
                break;
            default:
                break;
        }

    }

    public void checkIfSorted(String column) {
        column = column.toLowerCase();
        String sortOpt;
        switch (column) {
            case "name":
                sortOpt = executor.getClass(RMlocators.nameHeader);
                break;
            case "tag":
                sortOpt = executor.getClass(RMlocators.tagHeader);
                break;
            case "number of items":
                sortOpt = executor.getClass(RMlocators.itemsCountHeader);
                break;
            case "display order":
                sortOpt = executor.getClass(RMlocators.orderHeader);
                break;
            case "publish":
                sortOpt = executor.getClass(RMlocators.publishHeader);
                break;
            default:
                sortOpt = "";
                break;
        }
        assertTrue("Column was not sorted", sortOpt.contains("_asc") || sortOpt.contains("_desc"));
    }

    public void checkIfHomeBucketAvailable() {
        assertTrue("Home Bucket is not available.", executor.is_element_present(RMlocators.homeBcktLink));
    }

    public void clickOnHomeBucketName() {
        executor.click(RMlocators.homeBcktLink);
    }

    public void checkIfBucketCreateEditScreenOpened() {
        executor.waitUntilElementAppears(RMlocators.bcktCreateEdit);
        assertTrue("Bucket Create/Edit screen was not opened", executor.is_element_present(RMlocators.bcktCreateEdit));
    }

    public void checkIfBucketCreateEditScreenIsInViewingMode() {
        assertTrue("Bucket Create/Edit screen was not opened in viewing mode", executor.check_element_contains_text(RMlocators.bcktCreateEditStatus, "Viewing"));
    }

    public void checkIfBucketCreateEditScreenIsInEditMode() {
        assertTrue("Bucket Create/Edit screen was not opened in edit mode", executor.check_element_contains_text(RMlocators.bcktCreateEditStatus, "Edit"));
    }

    public void checkIfBucketCreateEditScreenIsInCreateMode() {
        assertTrue("Bucket Create/Edit screen was not opened in create new mode", executor.check_element_contains_text(RMlocators.bcktCreateEditStatus, "Create New"));
    }

    public void clickOnHomeBucketTag() {
        executor.click(RMlocators.homeBcktTag);
    }

    public void clickEditBtn(String name) {
        executor.click(RMlocators.getEditBtn(name));
    }

    public void clickOnNoOfItemsLink(String name) {
        executor.click(RMlocators.getBucketCount(name));
    }

    public void clickCreateNew() {
        executor.click(RMlocators.createNewBtn);
    }

    public void checkIfRedemptionItemListOpened() {
        executor.waitUntilElementAppears(RMlocators.redemptionItemsList);
        assertTrue("Redemption Item List was not displayed screen was not opened", executor.is_element_present(RMlocators.redemptionItemsList));
    }

    public void checkIfProperOrderByName(String name, int expectedOrder) {
        int actualOrder = Integer.parseInt(executor.getText(RMlocators.getBucketOrder(name)));
        assertTrue("Bucket doesn't have proper order", actualOrder == expectedOrder);
    }

    public void checkIfHomeBucketHasDeleteButton() {
        assertFalse("Home bucket has delete button and it shouldn't", executor.check_element_contains_text(RMlocators.homeBcktDelete, "DELETE"));
    }

    public int getNumberOfBuckets() {
        return executor.get_elements(RMlocators.tableElements).size();
    }

    public void checkThereAreMoreBucketsThanOne() {
        assertTrue("There is only a Home Bucket available", getNumberOfBuckets() > 1);
    }

    public void clickToDeleteChosenBucket(String name) {
        executor.click(RMlocators.getDeleteBtn(name));
    }

    public void checkIfConfirmationPopUpDisplayed() {
        assertTrue("Confirmation pop-up was not displayed", executor.is_element_present(RMlocators.popUpConfirmDelete));
    }

    public void checkIfProperConfirmationMessageDisplayed(String expectedMessage) {
        String actualMessage = executor.getText(RMlocators.popUpText);
        assertTrue("Confirmation message is improper.", actualMessage.equals(expectedMessage));
    }

    public void checkIfYesButtonDisplayed() {
        assertTrue("Yes button is not displayed on the popUp", executor.is_element_present(RMlocators.popUpYesBtn));
    }

    public void checkIfNoButtonDisplayed() {
        assertTrue("No button is not displayed on the popUp", executor.is_element_present(RMlocators.popUpNoBtn));
    }

    public void checkIfNoButtonIsDefault() {
        assertTrue("No button is not displayed on the popUp", executor.getClass(RMlocators.popUpStateOfNoBtn).contains("ui-state-focus"));
    }

    public void clickOnXButton() {
        executor.click(RMlocators.popUpXBtn);
    }

    public void clickOnNoButton() {
        executor.click(RMlocators.popUpNoBtn);
    }

    public void clickOnYesButton() {
        executor.click(RMlocators.popUpYesBtn);
    }

    public void checkIfBucketStillOnTheList(String name) {
        assertTrue("Bucket is not displayed on the list.", executor.is_element_present(RMlocators.getBucketName(name)));
    }

    public void checkIfBucketDisappearedFromTheList(String name) {
        executor.waitUntilElementDisappears(RMlocators.getBucketName(name));
        assertFalse("Bucket is still displayed on the list.", executor.is_element_present(RMlocators.getBucketName(name)));
    }

    public void checkIfDeleteButtonDisplayedForBucket(String name) {
        assertTrue("Delete button is not displayed for the bucket.", executor.is_element_present(RMlocators.getDeleteBtn(name)));
    }
}
