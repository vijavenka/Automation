package com.iat.adminportal.page_navigations;

import com.iat.adminportal.locators.AdminPortalFeedPageLocators;
import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

public class AdminPortalFeedManagerNavigation extends AbstractPage {

    protected AdminPortalFeedPageLocators locators = new AdminPortalFeedPageLocators();
    private AdminPortalCommons commons;

    public AdminPortalFeedManagerNavigation() {
        super("");
    }

    public void open() {
        super.open();
    }

    public void searchRecordDumb(String phrase) {
        executor.check(locators.search_input);
        executor.send_keys(locators.search_input, phrase);
        executor.click(locators.search_button);
        executor.waitUntilElementDisappears(locators.processing_box);
    }

    public boolean checkSearchSuccessful(String phrase) {
        if (executor.check_element_contains_text(locators.table_1st_row, phrase)
                && !executor.is_element_present(locators.table_2nd_row)
                ) {
            return true;
        } else {
            return false;
        }
    }

    public String getFeedStatus() {
        if (checkDeleteButton()) {
            return executor.getText(locators.table_1st_row_status_wbutton);
        } else {
            return executor.getText(locators.table_1st_row_status_wobutton);
        }
    }

    public boolean checkDeleteButton() {
        if (executor.is_element_present(locators.table_1st_row_delete_button)
                && !executor.is_element_present(locators.table_1st_row_deleted_text)) {
            return true;
        } else if (!executor.is_element_present(locators.table_1st_row_delete_button)
                && executor.is_element_present(locators.table_1st_row_deleted_text)) {
            return false;
        } else {
            return false;
        }
    }

    public boolean checksActivateButtonExists() {
        if (executor.is_element_present(locators.table_1st_row_activate_button)) {
            return true;
        } else {
            return false;
        }
    }

    public void clickCreateNewFeedButton() {
        executor.click(locators.list_view_createnew_button);
    }

    public void clicksDeleteOnFeed1stRow() {
        if (checkDeleteButton()) {
            executor.click(locators.table_1st_row_delete_button);
        }
    }

    public boolean checkDeleteConfirmation() {
        if (executor.is_element_present(locators.delete_confirm)) {
            return true;
        } else {
            return false;
        }
    }

    public void confirmAction(String action) {
        Locator element = null;
        switch (action) {
            case "No":
                element = locators.delete_confirm_NO_button;
                break;
            case "X":
                element = locators.delete_confirm_X_button;
                break;
            case "Yes":
                element = locators.delete_confirm_YES_button;
                break;
            default:
                break;
        }
        executor.click(element);
        executor.waitUntilElementDisappears(locators.delete_confirm);
    }

    public boolean checkRunButton() {
        if (executor.is_element_present(locators.table_1st_row_run_button)) {
            return true;
        } else {
            return false;
        }
    }

    public String lookForRecord4Deletion() {
        commons = new AdminPortalCommons();
        String recordID = null;
        float allRecords = commons.getListViewNumberOfAllRecords().get(2);
        float recordsDisplayed = commons.getListViewNumberOfRecordsDisplayed();
        int numOfPages = (int) Math.ceil(allRecords / recordsDisplayed);

        for (int x = 1; x <= numOfPages; x++) {
            int row = 1;
            while (executor.is_element_present(new Locator(LocatorType.XPATH, "//table/tbody/tr[" + row + "]"))) {
                Locator element = new Locator(LocatorType.XPATH, "//table/tbody/tr[" + row + "]/td[5]/a");
                if (executor.is_element_present(element)) {
                    recordID = executor.getText(new Locator(LocatorType.XPATH, "//table/tbody/tr[" + row + "]/td[1]"));
                    break;
                } else {
                    row++;
                }
            }
            if (recordID == null) {
                if (executor.is_element_present(locators.list_view_pager_next_button)) {
                    executor.click(locators.list_view_pager_next_button);
                } else {
                    System.out.println("No qualifying records found");
                    recordID = null;
                }
            }
        }
        return recordID;
    }

    public void checkFeedDetailsStep1EditMode() {

    }
}
