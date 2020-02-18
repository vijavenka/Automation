package com.iat.storepresentation.Navigations.TransitionPage;

import com.iat.storepresentation.Database.JDBC;
import com.iat.storepresentation.EnvironmentVariables;
import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.TransitionPage.TransitionPageLocators;
import com.iat.storepresentation.Navigations.AbstractPage;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.04.14
 * Time: 09:41
 * To change this template use File | Settings | File Templates.
 */
public class DeeplinksNavigation extends AbstractPage{

    private TransitionPageLocators locators_transition = new TransitionPageLocators();
    private EnvironmentVariables envVariables = new EnvironmentVariables();

    public DeeplinksNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // AFFILIATE MANAGER - create deeplinks outside of SOLR for WLS (RD-2774)
    String retailerName;
    public void openDeeplinkTransitionPage(){
        open();
        executor.open("transition?name=Boohoo.com&multiplier=4&referrerUrl=https://qa.epoints.com/&deeplink=http%3A%2F%2Fwww.awin1.com%2Fpclick.php%3Fp%3D3056040637%26a%3D95617%26m%3D2319");
        retailerName = "Boohoo.com";
    }

    public void checkIfClickoutWasReported(String ifSigned, String Email) throws InterruptedException, SQLException {
        // this function body is a copy of function from transition page navigation class, it is only a little bit changed
        Thread.sleep(2000);
        String CurrentDate = executor.returnCurrentDate();
        JDBC jdbc = new JDBC("affiliate_manager");
        assertTrue("In db there is no clickout with current date and hour, but should be", jdbc.return_proper_db_value("SELECT createdDate FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1).contains(CurrentDate));
        assertEquals("Clickout has improper merchant name", jdbc.return_proper_db_value("SELECT merchant FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), retailerName);
        assertEquals("Clickout has improper merchantId", jdbc.return_proper_db_value("SELECT merchantId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1));
        assertEquals("Clickout has improper publisher", jdbc.return_proper_db_value("SELECT publisher FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), envVariables.StoreName);
        assertEquals("Clickout has improper publisherId", jdbc.return_proper_db_value("SELECT publisherId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), "52e78e50e4b0fa73f3603da9");
        assertEquals("Clickout has improper affiliate networkId", jdbc.return_proper_db_value("SELECT affiliateNetworkId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1));
        assertEquals("Clickout has improper affiliate network name", jdbc.return_proper_db_value("SELECT affiliateNetwork FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT name FROM admin_ui.AffiliateNetwork WHERE id='" + jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1) + "'", 1));
        if (ifSigned.equals("user_sign_in")) {
            String temp = jdbc.return_proper_db_value("SELECT userId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1);
            jdbc = new JDBC("points_manager");
            assertEquals("Clickout has improper UserId", temp, jdbc.return_proper_db_value("SELECT userKey FROM points_manager.User WHERE email = '"+Email+"'",1));
        }
        jdbc.close();
    }
}
