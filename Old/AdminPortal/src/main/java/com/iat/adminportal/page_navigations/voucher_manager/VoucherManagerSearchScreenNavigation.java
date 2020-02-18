package com.iat.adminportal.page_navigations.voucher_manager;

import com.iat.adminportal.Database.JDBC;
import com.iat.adminportal.locators.AdminPortalHomePageLocators;
import com.iat.adminportal.locators.voucher_manager.VoucherManagerLandingPageLocators;
import com.iat.adminportal.locators.voucher_manager.VoucherManagerSearchScreenLocators;
import com.iat.adminportal.page_navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VoucherManagerSearchScreenNavigation extends AbstractPage {

    protected VoucherManagerSearchScreenLocators VMSearchScreenLocators = new VoucherManagerSearchScreenLocators();
    protected AdminPortalHomePageLocators locators_home = new AdminPortalHomePageLocators();
    protected VoucherManagerLandingPageLocators locators_vLanding = new VoucherManagerLandingPageLocators();


    public VoucherManagerSearchScreenNavigation() {
        super("");
    }

    public void open() {
        super.open();
    }

    public void check_if_voucherSearchPage_opened() {
        assertTrue("Voucher Search page not opened properly", executor.check_element_contains_text(VMSearchScreenLocators.pageTitleH1, "Vouchers"));
        assertFalse("Voucher Search page blocked by Processing Box", executor.is_element_visible(VMSearchScreenLocators.searchTableProcessing));
    }

    // Voucher Edit screens in the Admin Portal should show Merchant + Network (RD-3872).
    public void openVoucherManagerTab() {
        executor.click(locators_home.voucher_man_button);
    }

    public void openVoucherManagerListView() {
        List<WebElement> created = executor.get_elements(locators_vLanding.createdBasicElement);
        for (WebElement createdElement : created) {
            if (!(executor.getText(createdElement).equals("0"))) {
                executor.click(createdElement);
                break;
            }
        }
    }

    public void clickEditButtonOfFirstVoucher() {
        executor.click(VMSearchScreenLocators.editBasicElement);
    }

    public void expandMerchantsList() {
        executor.click(VMSearchScreenLocators.merchantNameDDLExpand);
    }

    public void checkAffiliateNetworkAssignment() throws SQLException {
        JDBC jdbc = new JDBC("admin_portal");
        List<WebElement> merchantNetworks = executor.get_elements(VMSearchScreenLocators.merchantNameDDLBasicOption);
        int loop = 0;
        for (WebElement mN : merchantNetworks) {
            loop++;
            String merchantNetwork = executor.getText(mN);
            String merchantName = merchantNetwork.substring(0, merchantNetwork.lastIndexOf('[') - 1);
            String networkName = merchantNetwork.substring(merchantNetwork.lastIndexOf('[') + 1, merchantNetwork.lastIndexOf(']'));
            System.out.println(merchantName + "---" + networkName);
            assertEquals("Affiliate network is incorrect for merchant - " + merchantName + " network - " + networkName, jdbc.return_proper_db_value("SELECT name FROM admin_ui.AffiliateNetwork WHERE id ='" + jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name = \"" + merchantName + "\"", 1) + "'", 1), networkName);
            if (loop > 50) {
                break;
            }
        }
        jdbc.close();
    }

    // Voucher Manager - search functionality for merchants (RD-3926)
    String assignedMerchantName;

    public void findMerchantAssignedToSomeVoucherInDB() throws SQLException {
        JDBC jdbc = new JDBC("admin_portal");
        jdbc.get_all_query_content("SELECT merchantName from product_ingest.Voucher WHERE merchantName is not null");
        assignedMerchantName = jdbc.get_value_of_proper_row(true, 1);
        jdbc.close();
    }

    public void searchVouchersWithSpecifiedMerchantName() throws InterruptedException {
        executor.selectOptionByText(VMSearchScreenLocators.networkDropDown, "All");
        executor.selectOptionByText(VMSearchScreenLocators.statusDropDown, "All");
        executor.send_keys(VMSearchScreenLocators.searchTextBox, assignedMerchantName);
        executor.click(VMSearchScreenLocators.searchButton);
        Thread.sleep(2000);
    }

    public void checkIfSomeResultsWereFound() {
        List<WebElement> resultMerchantNames = executor.get_elements(VMSearchScreenLocators.merchantBasicElement);
        assertTrue("No results found, but some should be found", resultMerchantNames.size() > 0);
    }

    public void forEachFoundVoucherCheckIfHasAssignedProperMerchant() throws InterruptedException {
        List<WebElement> resultEditButtons = executor.get_elements(VMSearchScreenLocators.editBasicElement);
        for (int i = 0; i < resultEditButtons.size(); i++) {
            List<WebElement> currentResultEditButtons = executor.get_elements(VMSearchScreenLocators.editBasicElement);
            executor.click(currentResultEditButtons.get(i));
            String voucherMerchantName = executor.getText(VMSearchScreenLocators.merchantNameDDLExpand);
            assertTrue("Voucher has incorrect merchantName, should be: " + assignedMerchantName + " , but is: " + voucherMerchantName, voucherMerchantName.contains(assignedMerchantName));
            executor.click(VMSearchScreenLocators.voucherEditModalCancelButton);
            Thread.sleep(1000);
        }

    }

    //EPOINTS - VOUCHER MAN. IMPR. - allow decimal point in £ off (NBO-17)
    String voucherUniqueTitle;

    public void enterUniqueVoucherTitle() {
        executor.clear(VMSearchScreenLocators.voucherEditModalTitleInputField);
        voucherUniqueTitle = "uniqueVoucherTitle_" + executor.return_random_value(1000000);
        executor.send_keys(VMSearchScreenLocators.voucherEditModalTitleInputField, voucherUniqueTitle);
    }

    public void enterOffValue(String value) {
        executor.send_keys(VMSearchScreenLocators.voucherEditModalOffInputField, value);
    }

    public void selectOffType(String offType) {
        if (offType.equals("percentage")) {
            executor.selectOptionByValue(VMSearchScreenLocators.voucherEditModalOffInputDDL, "percentOff");
        } else if (offType.equals("pound")) {
            executor.selectOptionByValue(VMSearchScreenLocators.voucherEditModalOffInputDDL, "poundOff");
        }
    }

    public void saveEditedVoucher() {
        executor.click(VMSearchScreenLocators.voucherEditModalSaveButton);
    }

    public void checkIfNoAlertsAreDisplayed() {
        List<WebElement> alerts = executor.get_elements(VMSearchScreenLocators.voucherEditModalAlertBasic);
        assert alerts.size() == 0;
    }

    public void checkIfDecimalOffValueWasProperlyStored(String value) throws SQLException {
        JDBC jdbc = new JDBC("admin_portal");
        assert jdbc.return_proper_db_value("SELECT valueOff FROM product_ingest.Voucher WHERE title = '" + voucherUniqueTitle + "'", 1).contains(value);
        jdbc.close();
    }
}
