package com.iat.adminportal.page_navigations.voucher_manager;

import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;
import com.iat.adminportal.locators.voucher_manager.VoucherManagerFeedConfigurationPageLocators;
import com.iat.adminportal.locators.voucher_manager.VoucherManagerLandingPageLocators;
import com.iat.adminportal.page_navigations.AbstractPage;
import cucumber.api.DataTable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VoucherManagerLandingPageNavigation extends AbstractPage {

    protected VoucherManagerLandingPageLocators adminVMLocators = new VoucherManagerLandingPageLocators();
    protected VoucherManagerFeedConfigurationPageLocators configVMLocators = new VoucherManagerFeedConfigurationPageLocators();

    public VoucherManagerLandingPageNavigation() {
        super("");
    }

    public void open() {
        super.open();
    }

    public void check_if_voucherPage_opened() {
        assertTrue("Voucher Manager page not opened properly", executor.check_element_contains_text(adminVMLocators.pageTitleH1, "Voucher Manager"));
        assertFalse("Voucher Manager page blocked by Processing Box", executor.is_element_visible(adminVMLocators.configTableProcessing));
    }

    public void clickOnAddNewFeedConfiguration() {
        executor.click(adminVMLocators.add_feed_configuration_btn);
        assertFalse("Voucher Manager page blocked by Processing Box", executor.is_element_visible(adminVMLocators.configTableProcessing));
    }


    public void checkIfRawVoucherStatisticsAvailable() {
        assertTrue("Raw Voucher Statistics section not available", executor.is_element_present(adminVMLocators.rawVoucherStatisticsSectionContainer));
        assertTrue("Raw Voucher Statistics section title not available", executor.is_element_present(adminVMLocators.rawVoucherStatisticsSectionTitle));

    }

    public void checkIfRawVoucherStatisticsIncludeCreated() {
        assertTrue("Raw Voucher Statistics CREATED stats not available", executor.check_element_contains_text(adminVMLocators.rawVoucherStatisticsCreated, "Created vouchers to process"));

        Locator newLocator = new Locator(LocatorType.XPATH, adminVMLocators.rawVoucherStatisticsCreated.get_body() + "/a");

        assertTrue("Raw Voucher Statistics CREATED link not available", executor.is_element_present(newLocator));

    }

    public void checkIfRawVoucherStatisticsIncludeUpdated() {
        assertTrue("Raw Voucher Statistics UPDATED stats not available", executor.check_element_contains_text(adminVMLocators.rawVoucherStatisticsUpdated, "Updated vouchers to process"));

        Locator newLocator = new Locator(LocatorType.XPATH, adminVMLocators.rawVoucherStatisticsUpdated.get_body() + "/a");

        assertTrue("Raw Voucher Statistics UPDATED link not available", executor.is_element_present(newLocator));

    }

    public void checkIfRawVoucherStatisticsIncludeDeleted() {
        assertTrue("Raw Voucher Statistics DELETED stats not available", executor.check_element_contains_text(adminVMLocators.rawVoucherStatisticsDeleted, "Deleted vouchers"));

        Locator newLocator = new Locator(LocatorType.XPATH, adminVMLocators.rawVoucherStatisticsDeleted.get_body() + "/a");

        assertTrue("Raw Voucher Statistics DELETED link not available", executor.is_element_present(newLocator));
    }

    public void checkIfEditedVoucherStatisticsAvailable() {
        assertTrue("Edited Voucher Statistics section not available", executor.is_element_present(adminVMLocators.editedVoucherStatisticsSectionContainer));
        assertTrue("Edited Voucher Statistics section title not available", executor.is_element_present(adminVMLocators.editedVoucherStatisticsSectionTitle));

    }

    public void checkIfEditedVoucherStatisticsIncludeEdited() {
        assertTrue("Edited Voucher Statistics EDITED stats not available", executor.check_element_contains_text(adminVMLocators.editedVoucherStatisticsEdited, "Edited Vouchers Ready to be promoted"));

        Locator newLocator = new Locator(LocatorType.XPATH, adminVMLocators.editedVoucherStatisticsEdited.get_body() + "/a");

        assertTrue("Edited Voucher Statistics EDITED link not available", executor.is_element_present(newLocator));

    }

    public void checkIfRawVoucherStatisticsIncludePartiallyEdited() {
        assertTrue("Edited Voucher Statistics PARTIALLY_EDITED stats not available", executor.check_element_contains_text(adminVMLocators.editedVoucherStatisticsPartiallyEdited, "partially edited"));

        Locator newLocator = new Locator(LocatorType.XPATH, adminVMLocators.editedVoucherStatisticsPartiallyEdited.get_body() + "/a");

        assertTrue("Edited Voucher Statistics PARTIALLY_EDITED link not available", executor.is_element_present(newLocator));

    }

    public void checkVoucherFeedConfigurationTableAvailability() {
        assertTrue("Voucher Feed Configuration table is not available", executor.is_element_present(adminVMLocators.VoucherConfigTable));
    }

    public void checkVoucherFeedConfigurationTableHeaders(DataTable table) {
        List<String> headersList = new ArrayList<>();
        headersList = executor.getTableHeaders();

        for (int i = 0; i < table.raw().size(); i++) {

            assertTrue("Column " + table.raw().get(i).get(0) + " is missing", table.raw().get(i).get(0).equalsIgnoreCase(headersList.get(i)));

        }
    }

    /*
     * column No. 1 - network name, 3 - created, 4 - updated, 5 - Total, 6 - deleted, 7 - edited
     */
    public Locator getLinkFromTable(int rowNo, int columnNo) {
        Locator newLocator = new Locator(LocatorType.XPATH, "//tbody/tr[" + rowNo + "]/td[" + columnNo + "]/a");

        String columnName = "";
        switch (columnNo) {
            case 1:
                columnName = "Network";
                break;
            case 2:
                columnName = "Status";
                break;
            case 3:
                columnName = "Created";
                break;
            case 4:
                columnName = "Updated";
                break;
            case 5:
                columnName = "Total";
                break;
            case 6:
                columnName = "Deleted";
                break;
            case 7:
                columnName = "Edited";
                break;
            case 8:
                columnName = "Promote";
                break;
            case 10:
                columnName = "Edit";
                break;
            default:
        }
        assertTrue("Link in row: " + rowNo + " column " + columnName + " is not available", executor.is_element_present(newLocator));

        return newLocator;
    }

    public void checkIfcountersAreLinks() {
        getLinkFromTable(1, 3); //new Locator(LocatorType.XPATH, "//tbody/tr[1]/td[3]/a");
        getLinkFromTable(1, 4);// "//tbody/tr[1]/td[4]/a");
        getLinkFromTable(1, 5); // "//tbody/tr[1]/td[5]/a");
        getLinkFromTable(1, 6); //"//tbody/tr[1]/td[6]/a");
        getLinkFromTable(1, 7); // "//tbody/tr[1]/td[7]/a");
    }

    public void checkFeedIsActive(int rowNo) {
        Locator newLocator = new Locator(LocatorType.XPATH, "//tbody/tr[" + rowNo + "]/td[9]/span/span");
        assertTrue("Feed Configuration for: " + executor.getText(getLinkFromTable(rowNo, 1)) + " is deactivated", executor.get_element(newLocator).getAttribute("class").equalsIgnoreCase("active"));
    }

    public void checkIfPromoteButtonsAvailable() {
//        checkFeedIsActive(1);
//        getLinkFromTable(1,8);

        //check if any of promote button is displayed
        assertTrue("No any promote button is available", executor.get_elements(adminVMLocators.promoteButtonsCollection) != null);
    }

    public void checkIfActive_deactive_ButtonsAvailable() {
        assertTrue("No any active/deactive button is available", executor.get_elements(adminVMLocators.active_deactive_ButtonsCollection) != null);
    }

    public void checkIfPromoteAllButtonAvailable() {
        Locator newLocator = new Locator(LocatorType.XPATH, "//tfoot/tr/th[7]/a");
        assertTrue("Promote All button is not available", executor.is_element_present(newLocator));
    }

    public void checkIfAddNewFeedConfigurationButtonAvailable() {
        assertTrue("Add New Configuration button is not available", executor.is_element_present(adminVMLocators.add_feed_configuration_btn));
    }


    public void clickInRawStatisticsCreatedLink() {
        assertTrue("Raw statistics created link not available", executor.is_element_present(adminVMLocators.rawVoucherStatisticsCreatedLink));
        executor.click(adminVMLocators.rawVoucherStatisticsCreatedLink);
    }
}
