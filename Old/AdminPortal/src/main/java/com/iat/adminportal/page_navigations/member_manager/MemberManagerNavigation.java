package com.iat.adminportal.page_navigations.member_manager;

import com.iat.adminportal.Database.JDBC;
import com.iat.adminportal.EnvironmentVariables;
import com.iat.adminportal.domain.UserDetails;
import com.iat.adminportal.locators.AdminPortalHomePageLocators;
import com.iat.adminportal.locators.member_manager.MemberManagerLocators;
import com.iat.adminportal.page_navigations.AbstractPage;
import com.iat.adminportal.repository.impl.UserRepositoryImpl;
import com.iat.adminportal.steps.utils.Utils;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.iat.adminportal.steps.utils.DateTimeUtil.convertToDate;
import static com.iat.adminportal.steps.utils.DateTimeUtil.diffBetweenDates;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 26.03.14
 * Time: 08:47
 * To change this template use File | Settings | File Templates.
 */
public class MemberManagerNavigation extends AbstractPage {
    private MemberManagerLocators locator_member = new MemberManagerLocators();
    protected AdminPortalHomePageLocators locator_home = new AdminPortalHomePageLocators();

    public MemberManagerNavigation() {
        super("");
    }

    private Utils utils = new Utils();

    public void open() {
        super.open();
    }

    // MEMBER MANAGER - main page
    public void checkIfMemberManagerTabiIsAvailable() {
        executor.is_element_present(locator_home.member_man_button);
    }

    public void checkIfMemberManagerPageIsOpened() {
        assertEquals("Page title is incorrect", "Member Manager", executor.getText(locator_member.pageTitle));
    }

    public void checkIfMemberManagerMainPageHasAllNeededElements() {
        assertTrue("Search DDL is not visible", executor.is_element_present(locator_member.searchTypeDDL));
        assertTrue("Search test field is not visible", executor.is_element_present(locator_member.searchField));
        assertTrue("Search button is not visible", executor.is_element_present(locator_member.searchButton));
        assertTrue("Personal Tab is not visible", executor.is_element_present(locator_member.personalDetailsTab));
        assertTrue("Points tab is not visible", executor.is_element_present(locator_member.pointsHistoryTab));
        assertTrue("Redemption history tab is not visible", executor.is_element_present(locator_member.redemptionHistoryTab));
        assertTrue("Clickout history is not visible", executor.is_element_present(locator_member.clickoutHistoryTab));
    }

    // MEMBER MANAGER - create personnel details tab (RD-2035) - by Email
    public void typeTextIntoSearch(String email) {
        executor.send_keys(locator_member.searchField, email);
    }

    public void typeUUIDIntoSearch(String email) throws SQLException {
        executor.send_keys(locator_member.searchField, new UserRepositoryImpl().findByEmail(email).getUuid());
    }

    public void selectDDLsearchOption(boolean isEmail) {
        if (isEmail) {
            executor.selectOptionByValue(locator_member.searchTypeDDL, "email");
        } else if (!isEmail) {
            executor.selectOptionByValue(locator_member.searchTypeDDL, "uuid");
        }
    }

    public void pressSearchButton() {
        executor.click(locator_member.searchButton);
    }

    public void selectTab(String tab) throws InterruptedException {
        switch (tab) {
            case "Personal Details":
                Thread.sleep(500);
                executor.click(locator_member.personalDetailsTab);
                break;
            case "Points History":
                Thread.sleep(500);
                executor.click(locator_member.pointsHistoryTab);
                break;
            case "Redemptions History":
                Thread.sleep(500);
                executor.click(locator_member.redemptionHistoryTab);
                break;
            case "Clickout History":
                Thread.sleep(500);
                executor.click(locator_member.clickoutHistoryTab);
                break;
        }
    }

    public void checkIfSomeResultsWereDisplayed() {
        assertTrue("Results were not found", executor.is_element_present(locator_member.personalSectionBasicResult));
    }

    public void comparePersonalResultsWithDB(String emailOrUUID, String searchField) throws SQLException, ParseException {
        UserDetails userDetails = null;
        switch (searchField) {
            case "email":
                userDetails = new UserRepositoryImpl().findByEmail(emailOrUUID);
                break;
            case "uuid":
                userDetails = new UserRepositoryImpl().findById(emailOrUUID);
                break;
        }

        List<WebElement> results = executor.get_elements(locator_member.personalSectionBasicResult);

        //find epoints group in user details
        int epointsGroupId = -1;
        for (int i = 0; i < userDetails.getUserGroups().size(); i++) {
            if (userDetails.getUserGroups().get(i).getPartnerId() == EnvironmentVariables.getEpointsPartnerId()) {
                epointsGroupId = i;
                break;
            }
        }
        assertTrue("Epoints group not found", epointsGroupId != -1);

        //find created date
        Date createdDate = utils.findUserCreatedDate(userDetails);

        assertTrue("Registration date is incorrect", diffBetweenDates(createdDate, convertToDate(executor.getText(results.get(2)), "MM/dd/yyyy h:mm a"), Calendar.HOUR) <= 2);
        assertEquals("Emails are not equals", executor.getText(results.get(3)), userDetails.getEmail());
        assertEquals("UUID's are not equals", executor.getText(results.get(4)), userDetails.getUuid());
        assertEquals("First names are not equals", executor.getText(results.get(5)), userDetails.getFirstName());
        assertEquals("Last names are not equals", executor.getText(results.get(6)), userDetails.getLastName());
        assertEquals("Genders are not equals", executor.getText(results.get(7)), userDetails.getGender().toLowerCase());

        assertEquals("Streets are not equals", executor.getText(results.get(9)), userDetails.getStreet());
        assertEquals("House numbers are not equals", executor.getText(results.get(10)), userDetails.getHouse());
        assertEquals("Post codes are not equals", executor.getText(results.get(11)), userDetails.getPostcode());
        assertEquals("Towns are not equals", executor.getText(results.get(12)), userDetails.getCity());
        assertEquals("Countries are not equals", executor.getText(results.get(13)), userDetails.getCountry());
        assertEquals("Mobiles are not equals", executor.getText(results.get(14)), userDetails.getMobileNumber());

        String isUnsubscribed;
        if (userDetails.isUnsubscribed()) {
            isUnsubscribed = "Yes";
        } else {
            isUnsubscribed = "No";
        }
        assertEquals("Unsubscribes are not equals", executor.getText(results.get(15)), isUnsubscribed);

        String isVerified;
        if (userDetails.getVerified().equals("true")) {
            isVerified = "Yes";
        } else {
            isVerified = "No";
        }
        assertEquals("Verifies are not equals", executor.getText(results.get(16)), isVerified);

        String isDeleted;
        if (!userDetails.getUserGroups().get(epointsGroupId).getStatus().equals("deleted")) {
            isDeleted = "No";
        } else {
            isDeleted = "Yes";
        }
        assertEquals("Actives are not equals", executor.getText(results.get(17)), isDeleted);

        assertEquals("Titles are not equals", executor.getText(results.get(18)), userDetails.getTitle());
        assertEquals("Names are not equals", executor.getText(results.get(19)), userDetails.getRegistrationSiteName());
    }

    private String returnInformationFromDB(String query) throws SQLException {
        String information = null;
        JDBC jdbc = new JDBC("points_manager");
        if (jdbc.return_proper_db_value(query, 1) == null || jdbc.return_proper_db_value(query, 1).equals("")) {
            information = "Not specified";
        } else {
            information = jdbc.return_proper_db_value(query, 1);
        }
        jdbc.close();
        return information;
    }

    // MEMBER MANAGER - create personnel details tab (RD-2035) - by UUID
    private String changeDatesFormats(String dateFrom, String direction) throws ParseException {

        final String dateUIFormat = "M/d/yyyy h:mm a";
        final String dateDBFormat = "yyyy-MM-dd HH:mm";
        //TODO on qa dates are shifted by 2
        String DBdateAfterFormatting = null;
        if (direction.equals("DB->UI")) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateDBFormat);
            Date d = sdf.parse(dateFrom);
            d = new Date(d.getTime());
            sdf.applyPattern(dateUIFormat);
            DBdateAfterFormatting = sdf.format(d);
        } else if (direction.equals("UI->DB")) {
            SimpleDateFormat sdf = new SimpleDateFormat(dateUIFormat);
            Date d = sdf.parse(dateFrom);
            d = new Date(d.getTime());
            sdf.applyPattern(dateDBFormat);
            DBdateAfterFormatting = sdf.format(d);
        }
        return DBdateAfterFormatting;

    }

    // MEMBER MANAGER - create click out history tab (RD-2065) - by Email
    public void checkIfSomeResultsWereDisplayedOnClickoutHistoryTab() {
        assertTrue("No elements was found", executor.is_element_present(locator_member.returnRowContentBasicLocator(1)));
    }

    public void setDisplayedResultsPerPage() throws InterruptedException {
        Thread.sleep(500);
        executor.selectOptionByValue(locator_member.displyedNumberDDL, "100");
    }

    public void compareClickoutResultsWithDB(String emailOrUUID, String searchField) throws SQLException, ParseException {

        String uuid = null;
        switch (searchField) {
            case "email":
                uuid = new UserRepositoryImpl().findByEmail(emailOrUUID).getUuid();
                break;
            case "uuid":
                uuid = new UserRepositoryImpl().findById(emailOrUUID).getUuid();
                break;
        }

        String createdDateFormUI;
        //TODO change that formatting
        int resultsNumber = Integer.parseInt(executor.getText(locator_member.resultsNumberInfo).substring(executor.getText(locator_member.resultsNumberInfo).lastIndexOf("of")).replace(" entries", "").replace("of ", ""));
        if (resultsNumber > 100) {
            resultsNumber = 100;
        }
        JDBC jdbc = new JDBC("admin_portal");
        for (int i = 1; i <= resultsNumber; i++) {

            List<WebElement> particularRowContent = executor.get_elements(locator_member.returnRowContentBasicLocator(i));
            createdDateFormUI = changeDatesFormats(executor.getText(particularRowContent.get(0)), "UI->DB");
            assertTrue("Created dates are not equals for " + i, jdbc.return_proper_db_value("SELECT createdDate FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1).contains(createdDateFormUI));

            if (executor.getText(particularRowContent.get(1)).equals("-")) {
                assertTrue("Confirmed dates are not equals for " + i, jdbc.return_proper_db_value("SELECT confirmedDate FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1) == null);
            } else {
                assertTrue("Confirmed dates are not equals for " + i, jdbc.return_proper_db_value("SELECT confirmedDate FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1).contains(changeDatesFormats(executor.getText(particularRowContent.get(1)), "UI->DB")));
            }

            if (executor.getText(particularRowContent.get(2)).equals("-")) {
                assertTrue("Declided dates are not equals for " + i, jdbc.return_proper_db_value("SELECT declineDate FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1) == null);
            } else {
                assertTrue("Declined dates are not equals for " + i, jdbc.return_proper_db_value("SELECT declineDate FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1).contains(changeDatesFormats(executor.getText(particularRowContent.get(2)), "UI->DB")));
            }

            if (executor.getText(particularRowContent.get(3)).equals("-")) {
                assertTrue("Transaction dates are not equals for " + i, jdbc.return_proper_db_value("SELECT networkTransactionDate FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1) == null);
            } else {
                assertTrue("transaction dates are not equals for " + i, jdbc.return_proper_db_value("SELECT networkTransactionDate FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1).contains(changeDatesFormats(executor.getText(particularRowContent.get(3)), "UI->DB")));
            }

            assertEquals("Publishers dates are not equals for " + i, jdbc.return_proper_db_value("SELECT publisher FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1), executor.getText(particularRowContent.get(4)));
            assertEquals("Merchants are not equals for " + i, jdbc.return_proper_db_value("SELECT merchant FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1), executor.getText(particularRowContent.get(5)));
            assertEquals("Clikout types dates are not equals for " + i, jdbc.return_proper_db_value("SELECT clickoutType FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1).toLowerCase(), executor.getText(particularRowContent.get(6)));
            assertEquals("Statuses dates are not equals for " + i, jdbc.return_proper_db_value("SELECT clickoutStatus FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1).toLowerCase(), executor.getText(particularRowContent.get(7)));
            assertEquals("epoints dates are not equals for " + i, jdbc.return_proper_db_value("SELECT epoints FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1), executor.getText(particularRowContent.get(8)));

            if (executor.getText(particularRowContent.get(9)).equals("")) {
                assertTrue("Transaction dates are not equals for " + i, jdbc.return_proper_db_value("SELECT saleAmount FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1) == null);
            } else {
                assertEquals("transaction dates are not equals for " + i, jdbc.return_proper_db_value("SELECT saleAmount FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1), executor.getText(particularRowContent.get(9)));
            }

            if (executor.getText(particularRowContent.get(10)).equals("")) {
                assertTrue("Transaction dates are not equals for " + i, jdbc.return_proper_db_value("SELECT commissionAmount FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1) == null);
            } else {
                assertEquals("transaction dates are not equals for " + i, jdbc.return_proper_db_value("SELECT commissionAmount FROM affiliate_manager.Clickout WHERE userId='" + uuid + "' AND createdDate LIKE '" + createdDateFormUI + "%'", 1), executor.getText(particularRowContent.get(10)));
            }

            if (i > 5) {
                break;
            }
        }
        jdbc.close();
    }

    // MEMBER MANAGER - create click out history tab (RD-2065) - by UUID

    // MEMBER MANAGER - create redemption history tab (RD-2055) - by Email
    public void checkIfSomeResultsWereDisplayedOnRedemptionsHistoryTab() {
        assertTrue("No elements was found", executor.is_element_present(locator_member.returnRowContentBasicLocatorRH(1)));
    }

    public void compareRedemptionsResultsWithDB(String emailOrUUID, String searchField) throws SQLException, ParseException, InterruptedException {

        String uuid = null;
        switch (searchField) {
            case "email":
                uuid = new UserRepositoryImpl().findByEmail(emailOrUUID).getUuid();
                break;
            case "uuid":
                uuid = new UserRepositoryImpl().findById(emailOrUUID).getUuid();
                break;
        }

        int resultsNumber = Integer.parseInt(executor.getText(locator_member.resultsNumberInfo).substring(executor.getText(locator_member.resultsNumberInfo).lastIndexOf("of")).replace(" entries", "").replace("of ", ""));
        if (resultsNumber > 50) {
            resultsNumber = 50;
        }

        String createdDateFormUI;
        List<WebElement> particularRowContent;
        JDBC jdbc = new JDBC("points_manager");
        String userID = jdbc.return_proper_db_value("SELECT id FROM points_manager.User WHERE userKey='" + uuid + "'", 1);
        for (int i = 1; i < resultsNumber; i++) {
            particularRowContent = executor.get_elements(locator_member.returnRowContentBasicLocatorRH(i));
            createdDateFormUI = changeDatesFormats(executor.getText(particularRowContent.get(0)), "UI->DB");

            assertTrue("Dates are not the same for " + i, jdbc.return_proper_db_value("SELECT createdAt FROM points_manager.Product WHERE id = '" + jdbc.return_proper_db_value("SELECT productId FROM points_manager.Points WHERE userId = '" + userID + "' AND createdAt LIKE '" + createdDateFormUI + "%' AND activityInfo LIKE \"%" + executor.getText(particularRowContent.get(2)) + "\"", 1) + "'", 1).contains(createdDateFormUI));
            assertTrue("Product link is incorrect for " + i, executor.getText(particularRowContent.get(1)).contains(jdbc.return_proper_db_value("SELECT productId FROM points_manager.Product WHERE id = '" + jdbc.return_proper_db_value("SELECT productId FROM points_manager.Points WHERE userId = '" + userID + "' AND createdAt LIKE '" + createdDateFormUI + "%' AND activityInfo LIKE \"%" + executor.getText(particularRowContent.get(2)) + "\"", 1) + "'", 1)));
            assertEquals("Titles are not the same for " + i, jdbc.return_proper_db_value("SELECT title FROM points_manager.Product WHERE id = '" + jdbc.return_proper_db_value("SELECT productId FROM points_manager.Points WHERE userId = '" + userID + "' AND createdAt LIKE '" + createdDateFormUI + "%' AND activityInfo LIKE \"%" + executor.getText(particularRowContent.get(2)) + "\"", 1) + "'", 1), executor.getText(particularRowContent.get(2)));
            if (!executor.getText(particularRowContent.get(3)).equals("")) {
                assertEquals("Merchants are not the same", jdbc.return_proper_db_value("SELECT merchantName FROM points_manager.Product WHERE id = '" + jdbc.return_proper_db_value("SELECT productId FROM points_manager.Points WHERE userId = '" + userID + "' AND createdAt LIKE '" + createdDateFormUI + "%' AND activityInfo LIKE \"%" + executor.getText(particularRowContent.get(2)) + "\"", 1) + "'", 1), executor.getText(particularRowContent.get(3)));
            }
            assertEquals("Epoints are not the same for " + i, jdbc.return_proper_db_value("SELECT numPoints FROM points_manager.Product WHERE id = '" + jdbc.return_proper_db_value("SELECT productId FROM points_manager.Points WHERE userId = '" + userID + "' AND createdAt LIKE '" + createdDateFormUI + "%' AND activityInfo LIKE \"%" + executor.getText(particularRowContent.get(2)) + "\"", 1) + "' ", 1), executor.getText(particularRowContent.get(4)).replace(",", ""));
            assertEquals("Quantities are not the same for " + i, jdbc.return_proper_db_value("SELECT quantity FROM points_manager.Product WHERE id = '" + jdbc.return_proper_db_value("SELECT productId FROM points_manager.Points WHERE userId = '" + userID + "' AND createdAt LIKE '" + createdDateFormUI + "%' AND activityInfo LIKE \"%" + executor.getText(particularRowContent.get(2)) + "\"", 1) + "'", 1), executor.getText(particularRowContent.get(5)));

            if (i > 5) {
                break;
            }
        }
        jdbc.close();
    }

    // MEMBER MANAGER - create points history tab (RD-2045) - by Email
    public void checkIfSomeResultsWereDisplayedOnpointsHistoryTab() {
        assertTrue("No elements was found", executor.is_element_present(locator_member.returnRowContentBasicLocatorPH(1)));
    }

    public void comparePointsResultsWithDB(String emailOrUUID, String searchField) throws SQLException, ParseException {

        String uuid = null;
        switch (searchField) {
            case "email":
                uuid = new UserRepositoryImpl().findByEmail(emailOrUUID).getUuid();
                break;
            case "uuid":
                uuid = new UserRepositoryImpl().findById(emailOrUUID).getUuid();
                break;
        }

        int resultsNumber = Integer.parseInt(executor.getText(locator_member.resultsNumberInfo).substring(executor.getText(locator_member.resultsNumberInfo).lastIndexOf("of")).replace(" entries", "").replace("of ", ""));
        if (resultsNumber > 50) {
            resultsNumber = 50;
        }

        String createdDateFormUI;
        String tagKey;
        String userId;
        String tagId;
        JDBC jdbc = new JDBC("points_manager");
        userId = jdbc.return_proper_db_value("SELECT id FROM points_manager.User WHERE userKey='" + uuid + "'", 1);
        assertEquals("Confirmed epoints are not equals", executor.getText(locator_member.confirmedEpoints).replace(",", ""), jdbc.return_proper_db_value("SELECT confirmed FROM points_manager.User WHERE userKey='" + uuid + "'", 1));
        assertEquals("Pending epoints are not equals", executor.getText(locator_member.pendingEpoints).replace(",", ""), jdbc.return_proper_db_value("SELECT pending FROM points_manager.User WHERE userKey='" + uuid + "'", 1));
        assertEquals("Epoints to date are not equals", executor.getText(locator_member.ePointsToDate).replace(",", ""), Integer.toString(Integer.parseInt(jdbc.return_proper_db_value("SELECT confirmed FROM points_manager.User WHERE userKey='" + uuid + "'", 1)) + Integer.parseInt(jdbc.return_proper_db_value("SELECT redeemed FROM points_manager.User WHERE userKey='" + uuid + "'", 1))));

        for (int i = 1; i < resultsNumber; i++) {
            System.out.println(i);

            List<WebElement> particularRowContent = executor.get_elements(locator_member.returnRowContentBasicLocatorRH(i));
            createdDateFormUI = changeDatesFormats(executor.getText(particularRowContent.get(0)), "UI->DB");
            assertTrue("Created at dates are not the same for " + i, jdbc.return_proper_db_value("SELECT createdAt FROM points_manager.Points WHERE userId = '" + userId + "' AND createdAt LIKE '" + createdDateFormUI + "%'", 1).contains(createdDateFormUI));
            if (executor.getText(particularRowContent.get(1)).equals("-")) {
                assertTrue("In this situation activity info should be refer a friend for " + i, executor.getText(particularRowContent.get(2)).contains("referred a friend") || executor.getText(particularRowContent.get(2)).contains("Reward user for payment by credit card") || executor.getText(particularRowContent.get(2)).contains("reason") || executor.getText(particularRowContent.get(2)).contains("epoints refunded"));
            } else {
                assertTrue("Auto confirmed dates are not the same for " + i, jdbc.return_proper_db_value("SELECT autoConfirmDate FROM points_manager.Points WHERE userId = '" + userId + "' AND createdAt LIKE '" + createdDateFormUI + "%'", 1).contains(changeDatesFormats(executor.getText(particularRowContent.get(1)), "UI->DB")));
            }
            //
            //TODO activity info can crash because of jmeter script which add incorrect reason text as number not information in format - Reward user for payment by credit card
            if (executor.getText(particularRowContent.get(4)).contains("|")) { //logon case
                tagKey = executor.getText(particularRowContent.get(4)).substring(executor.getText(particularRowContent.get(4)).lastIndexOf("|")).replace("|", "");
                assertEquals("Activity info are not the same for " + i, executor.getText(particularRowContent.get(2)), jdbc.return_proper_db_value("SELECT description FROM points_manager.Tag WHERE tagKey = '" + tagKey + "'", 1));
            } else if (executor.getText(particularRowContent.get(4)).contains("ECARD")) { //ecard case
                tagId = jdbc.return_proper_db_value("SELECT tagId FROM points_manager.Points WHERE userId = '" + userId + "' AND createdAt LIKE '" + createdDateFormUI + "%'", 1);
                assertEquals("Activity info are not the same for " + i, executor.getText(particularRowContent.get(2)), jdbc.return_proper_db_value("SELECT description FROM points_manager.Tag WHERE id = '" + tagId + "'", 1));
            } else if (executor.getText(particularRowContent.get(4)).contains("Refund")) { //refund case
                tagKey = executor.getText(particularRowContent.get(4));
                assertEquals("Activity info are not the same for " + i, executor.getText(particularRowContent.get(2)), jdbc.return_proper_db_value("SELECT description FROM points_manager.Tag WHERE tagKey = '" + tagKey + "'", 1));
            } else {
                assertEquals("Activity info are not the same for " + i, executor.getText(particularRowContent.get(2)), jdbc.return_proper_db_value("SELECT activityInfo FROM points_manager.Points WHERE userId = '" + userId + "' AND createdAt LIKE '" + createdDateFormUI + "%'", 1));
            }
            assertEquals("Deltas are not the same for " + i, executor.getText(particularRowContent.get(3)), jdbc.return_proper_db_value("SELECT delta FROM points_manager.Points WHERE userId = '" + userId + "' AND createdAt LIKE '" + createdDateFormUI + "%'", 1));
            assertEquals("External transaction ids are not the same", executor.getText(particularRowContent.get(4)), jdbc.return_proper_db_value("SELECT externalTransactionId FROM points_manager.Points WHERE userId = '" + userId + "' AND createdAt LIKE '" + createdDateFormUI + "%'", 1));

            if (executor.getText(particularRowContent.get(4)).contains("ECARD") || executor.getText(particularRowContent.get(4)).contains("Refund")) { //ecard case and refund case - no on behalf of in that case
                assertTrue("Sites are not the same for " + i, jdbc.return_proper_db_value("SELECT onBehalfOfId FROM points_manager.Points WHERE userId = '" + userId + "' AND createdAt LIKE '" + createdDateFormUI + "%'", 1) == null);
            } else {
                assertTrue("Sites are not the same for " + i, jdbc.return_proper_db_value("SELECT onBehalfOfId FROM points_manager.Points WHERE userId = '" + userId + "' AND createdAt LIKE '" + createdDateFormUI + "%'", 1).contains(executor.getText(particularRowContent.get(5))));
            }

            assertEquals("Statuses transaction ids are not the same for " + i, executor.getText(particularRowContent.get(6)), jdbc.return_proper_db_value("SELECT status FROM points_manager.Points WHERE userId = '" + userId + "' AND createdAt LIKE '" + createdDateFormUI + "%'", 1).toLowerCase());

            if (i > 5) {
                break;
            }
        }
        jdbc.close();
    }

    // MEMBER MANAGER - create points history tab (RD-2045) - by UUID

    // EPOINTS - add deactivate user option to member manager (NBO-1).
    public void clickUserDeactivatedCheckboxIfNeeded(String email) throws SQLException, SQLException, InterruptedException {
        if (new UserRepositoryImpl().findByEmail(email).getActive().equals("true")) {
            clickUserDeactivatedCheckbox();
        }
    }

    public void clickUserDeactivatedCheckbox() throws InterruptedException {
        executor.click(locator_member.personalSectionDeactivateCheckbox);
        Thread.sleep(1000);
    }

    public void checkIfActiveFlagWasProperlySet(String activeFlag, String email) throws SQLException {
        assertTrue("Active flag was not properly set", new UserRepositoryImpl().findByEmail(email).getActive().equals(activeFlag));
    }
}