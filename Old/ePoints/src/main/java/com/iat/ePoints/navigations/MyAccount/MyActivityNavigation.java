package com.iat.ePoints.Navigations.MyAccount;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */

import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.MyAccount.MyProfileLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyActivityNavigation extends AbstractPage {

    private MyProfileLocators locators_myprofile = new MyProfileLocators();

    public MyActivityNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public void clickActivityReference() {
        executor.click(locators_myprofile.myActivity);
    }

    public void checkIfActivityPageHasProperContent() {
        assertTrue("Current balance reference is no visoble", executor.is_element_present(locators_myprofile.activityTableHeadItemCurrentBalance));
        assertTrue("Pending reference is no visible", executor.is_element_present(locators_myprofile.pendingTab));
        assertTrue("Declined reference is no visible", executor.is_element_present(locators_myprofile.declinedTab));
        assertTrue("Items per page is no visible", executor.is_element_present(locators_myprofile.activityTableHeadItemsPerPage));
        assertTrue("20 items per page is no visible", executor.is_element_present(locators_myprofile.activityTableHeadItems20));
        assertTrue("40 items per page is no visible", executor.is_element_present(locators_myprofile.activityTableHeadItems40));
        assertTrue("100 items per page is no visible", executor.is_element_present(locators_myprofile.activityTableHeadItems100));
        assertTrue("Sort by is no visible", executor.is_element_present(locators_myprofile.activityTableHeadItemsSortBy));
        assertTrue("Sort by DDL is no visible", executor.is_element_present(locators_myprofile.activityTableHeadItemsSortByDDL));

        assertEquals("Expected table header Date returned table header" + executor.getText(locators_myprofile.return_activityTableNames_element(1)), "Date", executor.getText(locators_myprofile.return_activityTableNames_element(1)));
        assertEquals("Expected table header Activity returned table header" + executor.getText(locators_myprofile.return_activityTableNames_element(3)), "Activity", executor.getText(locators_myprofile.return_activityTableNames_element(3)));
        assertEquals("Expected table header Site returned table header" + executor.getText(locators_myprofile.return_activityTableNames_element(4)), "Site", executor.getText(locators_myprofile.return_activityTableNames_element(4)));
        assertEquals("Expected table header In returned table header" + executor.getText(locators_myprofile.return_activityTableNames_element(5)), "In", executor.getText(locators_myprofile.return_activityTableNames_element(5)));
        assertEquals("Expected table header Out returned table header" + executor.getText(locators_myprofile.return_activityTableNames_element(6)), "Out", executor.getText(locators_myprofile.return_activityTableNames_element(6)));
        assertEquals("Expected table header Balance returned table header" + executor.getText(locators_myprofile.return_activityTableNames_element(7)), "Balance", executor.getText(locators_myprofile.return_activityTableNames_element(7)));
    }

    // Check current balance page content
    public void checkNumberOfCurrentBalance(String userEmail) throws SQLException {
        JDBC jdbc = new JDBC("points_manager");
        int confirmed = Integer.parseInt(jdbc.return_proper_db_value("SELECT count(*) FROM points_manager.Points WHERE userId ='" + jdbc.return_proper_db_value("SELECT id FROM points_manager.User WHERE email='" + userEmail + "'", 1) + "' and status = 'CONFIRMED'", 1));
        int redeemed = Integer.parseInt(jdbc.return_proper_db_value("SELECT count(*) FROM points_manager.Points WHERE userId ='"+jdbc.return_proper_db_value("SELECT id FROM points_manager.User WHERE email='"+userEmail+"'",1)+"' and status = 'REDEEMED'",1));
        assertEquals("Number of current balance products is incorrect", executor.getText(locators_myprofile.activityTableHeadItemCurrentBalance).replace("(","").replace(")",""), Integer.toString(confirmed+redeemed));
        jdbc.close();
    }

    public void checkContenOfCurrentBalaceForCurrentPage(String userEmail) throws SQLException, ParseException {
        JDBC jdbc = new JDBC("points_manager");

        jdbc.get_all_query_content("SELECT * FROM points_manager.Points WHERE (status = 'CONFIRMED' OR status ='REDEEMED') AND userId ='"+jdbc.return_proper_db_value("SELECT id FROM points_manager.User WHERE email ='"+userEmail+"'",1)+"' ORDER BY id DESC");

        int rowsNumber = executor.getWebElementsList(locators_myprofile.basicRowLocator).size()-1; // -1 because of header row
        String activityName;
        for(int i=1; i<=rowsNumber; i++){
            if(executor.getText(locators_myprofile.return_basicActivityTableContent_element(i, 3)).equals("logged into epoints")){
                activityName = "User logged in";
                //continue;
            }else{
                activityName =  executor.getText(locators_myprofile.return_basicActivityTableContent_element(i, 3));
            }
            
            assertEquals("Activity name is incorrect for " + activityName, activityName, jdbc.get_value_of_proper_row(true,4));
            assertEquals("Balance is incorrect for " + activityName, executor.getText(locators_myprofile.return_basicActivityTableContent_element(i,7)).replace(",",""), jdbc.get_value_of_proper_row(false, 12));
            assertTrue("Date is incorrect", jdbc.get_value_of_proper_row(false,2).contains(changeDatesFormats(executor.getText(locators_myprofile.return_basicActivityTableContent_element(i,1)),"UI->DB")));

            if(executor.getText(locators_myprofile.return_basicActivityTableContent_element(i,5)).equals("")){
                assertEquals("Delta is incorrect for " + activityName, executor.getText(locators_myprofile.return_basicActivityTableContent_element(i,6)).replace(",",""),  jdbc.get_value_of_proper_row(false, 5));
                assertEquals("Delta is incorrect for " + activityName,"REDEEMED",  jdbc.get_value_of_proper_row(false,8));
            }else{
                assertEquals("Delta is incorrect for " + activityName, executor.getText(locators_myprofile.return_basicActivityTableContent_element(i,5)).replace(",", ""),  jdbc.get_value_of_proper_row(false,5));
                assertEquals("Delta is incorrect for " + activityName,"CONFIRMED",  jdbc.get_value_of_proper_row(false,8));
            }
        }
        jdbc.close();
    }
    public String changeDatesFormats(String dateFrom, String direction) throws ParseException {

        final String dateUIFormat = "dd MMM yy";
        final String dateDBFormat = "yyyy-MM-dd";

        String DBdateAfterFormatting = null;
        if(direction.equals("DB->UI")){
            SimpleDateFormat sdf = new SimpleDateFormat(dateDBFormat);
            Date d = sdf.parse(dateFrom);
            d= new Date(d.getTime());
            sdf.applyPattern(dateUIFormat);
            DBdateAfterFormatting = sdf.format(d);
        }else if(direction.equals("UI->DB")){
            SimpleDateFormat sdf = new SimpleDateFormat(dateUIFormat, Locale.ENGLISH);
            Date d = sdf.parse(dateFrom);
            d= new Date(d.getTime());
            sdf.applyPattern(dateDBFormat);
            DBdateAfterFormatting = sdf.format(d);
        }
        return DBdateAfterFormatting;
    }

    // Check pending page content
    public void openPendingTab() throws InterruptedException {
        Thread.sleep(2000);
        executor.click(locators_myprofile.pendingTab);
    }

    public void checkNumberOfPending(String userEmail) throws SQLException {
        JDBC jdbc = new JDBC("points_manager");
        int pending = Integer.parseInt(jdbc.return_proper_db_value("SELECT count(*) FROM points_manager.Points WHERE userId ='" + jdbc.return_proper_db_value("SELECT id FROM points_manager.User WHERE email='" + userEmail + "'", 1) + "' and status = 'PENDING'", 1));
        assertEquals("Number of pending products is incorrect", executor.getText(locators_myprofile.activityTableHeadItemPending).replace("(","").replace(")",""), Integer.toString(pending));
        jdbc.close();
    }

    public void checkContenOfPendingForCurrentPage(String userEmail) throws SQLException, ParseException {
        JDBC jdbc = new JDBC("points_manager");

        jdbc.get_all_query_content("SELECT * FROM points_manager.Points WHERE status = 'PENDING' AND userId ='"+jdbc.return_proper_db_value("SELECT id FROM points_manager.User WHERE email ='"+userEmail+"'",1)+"' ORDER BY id DESC");

        int rowsNumber = executor.getWebElementsList(locators_myprofile.basicRowLocator).size()-1; //-1 because of header row
        String activityName;
        for(int i=1; i<=rowsNumber; i++){
            activityName =  executor.getText(locators_myprofile.return_basicActivityTableContent_element(i, 3));

            assertEquals("Activity name is incorrect for " + activityName, activityName, jdbc.get_value_of_proper_row(true, 4));
            assertTrue("Date is incorrect for " + activityName, jdbc.get_value_of_proper_row(false,2).contains(changeDatesFormats(executor.getText(locators_myprofile.return_basicActivityTableContent_element(i,1)),"UI->DB")));
            assertEquals("Pending points is incorrect for " + activityName, executor.getText(locators_myprofile.return_basicActivityTableContent_element(i,5)).replace(",",""),  jdbc.get_value_of_proper_row(false, 5));
            assertTrue("Date expected to receive is incorrect for " + activityName, jdbc.get_value_of_proper_row(false,16).contains(changeDatesFormats(executor.getText(locators_myprofile.return_basicActivityTableContent_element(i,6)),"UI->DB")));
        }
        jdbc.close();
    }

    //  Check declined page content
    public void openDeclinedTab() throws InterruptedException {
        Thread.sleep(2000);
        executor.click(locators_myprofile.declinedTab);
    }

    public void checkNumberOfDeclined(String userEmail) throws SQLException {
        JDBC jdbc = new JDBC("points_manager");
        int declined = Integer.parseInt(jdbc.return_proper_db_value("SELECT count(*) FROM points_manager.Points WHERE userId ='" + jdbc.return_proper_db_value("SELECT id FROM points_manager.User WHERE email='" + userEmail + "'", 1) + "' and status = 'DECLINED'", 1));
        assertEquals("Number of declined products is incorrect", executor.getText(locators_myprofile.activityTableHeadItemPending).replace("(","").replace(")",""), Integer.toString(declined));
        jdbc.close();
    }

    public void checkContenOfDeclinedForCurrentPage(String userEmail) throws SQLException, ParseException {
        JDBC jdbc = new JDBC("points_manager");

        jdbc.get_all_query_content("SELECT * FROM points_manager.Points WHERE status = 'DECLINED' AND userId ='"+jdbc.return_proper_db_value("SELECT id FROM points_manager.User WHERE email ='"+userEmail+"'",1)+"' ORDER BY id DESC");

        int rowsNumber = executor.getWebElementsList(locators_myprofile.basicRowLocator).size()-1; //-1 because of header row
        String activityName;
        for(int i=1; i<=rowsNumber; i++){
            activityName =  executor.getText(locators_myprofile.return_basicActivityTableContent_element(i, 3));

            assertEquals("Activity name is incorrect for " + activityName, activityName, jdbc.get_value_of_proper_row(true, 4));
            assertTrue("Date is incorrect for " + activityName, jdbc.get_value_of_proper_row(false,2).contains(changeDatesFormats(executor.getText(locators_myprofile.return_basicActivityTableContent_element(i,1)),"UI->DB")));
            assertEquals("Declined points is incorrect for " + activityName, executor.getText(locators_myprofile.return_basicActivityTableContent_element(i,6)).replace(",",""),  jdbc.get_value_of_proper_row(false, 5));
            assertEquals("Reason is incorrect for " + activityName, jdbc.get_value_of_proper_row(false,7), executor.getText(locators_myprofile.return_basicActivityTableContent_element(i,5)));
        }
        jdbc.close();
    }
}


