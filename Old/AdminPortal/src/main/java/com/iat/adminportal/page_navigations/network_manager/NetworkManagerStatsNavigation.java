package com.iat.adminportal.page_navigations.network_manager;

import com.iat.adminportal.Database.JDBC;
import com.iat.adminportal.domain.UserDetails;
import com.iat.adminportal.locators.network_manager.AdminPortalNetworkManagerHomeLocators;
import com.iat.adminportal.locators.network_manager.NetworkManagerStatsLocators;
import com.iat.adminportal.page_navigations.AbstractPage;
import com.iat.adminportal.repository.impl.UserRepositoryImpl;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 10.04.14
 * Time: 12:08
 * To change this template use File | Settings | File Templates.
 */
public class NetworkManagerStatsNavigation extends AbstractPage {

    protected NetworkManagerStatsLocators locators_networkStats = new NetworkManagerStatsLocators();
    protected AdminPortalNetworkManagerHomeLocators locator_networkHome = new AdminPortalNetworkManagerHomeLocators();

    public NetworkManagerStatsNavigation() {
        super("");
    }

    public void open() {
        super.open();
    }

    // ADMIN MANAGER - cross channel sales reporting view in network manager (RD-3019) - columns availability
    public void openAffiliateWindowStatsPage() {
        List<WebElement> networkNames = executor.get_elements(locator_networkHome.tableContentName);
        int networkPosition = 0;
        for (WebElement network : networkNames) {
            if (executor.getText(network).equals("Affiliate Window")) {
                break;
            }
            networkPosition++;
        }
        List<WebElement> statsButtons = executor.get_elements(locator_networkHome.tableContentStatsButton);
        executor.click(statsButtons.get(networkPosition));
    }

    public void addProperColumn(String columnName) throws InterruptedException {
        executor.moveMouseToWebElement(locators_networkStats.toggleColumns);
        switch (columnName) {
            case "P1":
                executor.click(locators_networkStats.toggleColumnP1);
                Thread.sleep(500);
                break;
            case "P2":
                executor.click(locators_networkStats.toggleColumnP2);
                Thread.sleep(500);
                break;
            case "P3":
                executor.click(locators_networkStats.toggleColumnP3);
                Thread.sleep(500);
                break;
            case "P4":
                executor.click(locators_networkStats.toggleColumnP4);
                Thread.sleep(500);
                break;
        }
    }

    public void checkIfProperColumnIsVisible(String columnName, boolean isVisible) {
        switch (columnName) {
            case "P1":
                if (isVisible) {
                    assertTrue("Column P1 is invisible", executor.is_element_present(locators_networkStats.p1Sorting));
                } else {
                    assertFalse("Column P1 is visible but should not", executor.is_element_present(locators_networkStats.p1Sorting));
                }
                break;
            case "P2":
                if (isVisible) {
                    assertTrue("Column P2 is invisible", executor.is_element_present(locators_networkStats.p2Sorting));
                } else {
                    assertFalse("Column P2 is visible but should not", executor.is_element_present(locators_networkStats.p2Sorting));
                }
                break;
            case "P3":
                if (isVisible) {
                    assertTrue("Column P3 is invisible", executor.is_element_present(locators_networkStats.p3Sorting));
                } else {
                    assertFalse("Column P3 is visible but should not", executor.is_element_present(locators_networkStats.p3Sorting));
                }
                break;
            case "P4":
                if (isVisible) {
                    assertTrue("Column P4 is invisible", executor.is_element_present(locators_networkStats.p4Sorting));
                } else {
                    assertFalse("Column P4 is visible but should not", executor.is_element_present(locators_networkStats.p4Sorting));
                }
                break;
        }
    }

    // ADMIN MANAGER - cross channel sales reporting view in network manager (RD-3019) - columns content correctness
    String p1Parameter = Integer.toString(executor.return_random_value(1000000));
    String p2Parameter = Integer.toString(executor.return_random_value(1000000));
    String p3Parameter = Integer.toString(executor.return_random_value(1000000));
    String p4Parameter = Integer.toString(executor.return_random_value(1000000));

    public void setNewDataInLastestClickout() throws SQLException {
        JDBC jdbc = new JDBC("admin_portal");
        UserDetails userDetails = new UserRepositoryImpl().findByEmail("epoints_test_do_not_remove@iat.test");
        String userId = userDetails.getUuid();
        jdbc.execute_delete_update_query("Update affiliate_manager.Clickout SET p1Parameter = '" + p1Parameter + "' WHERE userId = '" + userId + "' ORDER BY id DESC");
        jdbc.execute_delete_update_query("Update affiliate_manager.Clickout SET p2Parameter = '" + p2Parameter + "' WHERE userId = '" + userId + "' ORDER BY id DESC");
        jdbc.execute_delete_update_query("Update affiliate_manager.Clickout SET p3Parameter = '" + p3Parameter + "' WHERE userId = '" + userId + "' ORDER BY id DESC");
        jdbc.execute_delete_update_query("Update affiliate_manager.Clickout SET p4Parameter = '" + p4Parameter + "' WHERE userId = '" + userId + "' ORDER BY id DESC");
        jdbc.close();
    }

    public void checkCorrectnessOfPColumns() throws SQLException, InterruptedException {
        Thread.sleep(3000);
        UserDetails userDetails = new UserRepositoryImpl().findByEmail("epoints_test_do_not_remove@iat.test");
        String userId = userDetails.getUuid();
        System.out.println(userId);
        executor.send_keys(locators_networkStats.userID, userId);
        Thread.sleep(1000);
        executor.click(locators_networkStats.searchButton);
        Thread.sleep(3000);
        assertEquals("P1 are not equals", p1Parameter, executor.getText(locators_networkStats.p1TableContent));
        assertEquals("P2 are not equals", p2Parameter, executor.getText(locators_networkStats.p2TableContent));
        assertEquals("P3 are not equals", p3Parameter, executor.getText(locators_networkStats.p3TableContent));
        assertEquals("P4 are not equals", p4Parameter, executor.getText(locators_networkStats.p4TableContent));
    }
}
