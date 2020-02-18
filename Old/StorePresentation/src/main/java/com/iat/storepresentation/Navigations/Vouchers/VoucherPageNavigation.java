package com.iat.storepresentation.Navigations.Vouchers;

import com.iat.storepresentation.EnvironmentVariables;
import com.iat.storepresentation.Database.JDBC;
import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.SearchComponent.SearchPageLocators;
import com.iat.storepresentation.Locators.ShopHome.HomePageLocators;
import com.iat.storepresentation.Locators.ShopHome.IndividualProductPage.IndividualProductLocators;
import com.iat.storepresentation.Locators.TransitionPage.TransitionPageLocators;
import com.iat.storepresentation.Locators.Vouchers.VoucherPageLocators;
import com.iat.storepresentation.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.03.14
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class VoucherPageNavigation extends AbstractPage{

    private HomePageLocators locators_home = new HomePageLocators();
    private IndividualProductLocators locators_indProductPage = new IndividualProductLocators();
    private VoucherPageLocators locators_vouchers = new VoucherPageLocators();
    private SearchPageLocators locators_search = new SearchPageLocators();
    private TransitionPageLocators locators_transition = new TransitionPageLocators();
    private EnvironmentVariables envVariables = new EnvironmentVariables();


    public VoucherPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Vouchers pages (RD-2091) - check content of Voucher Page
    public void openVoucherPage() throws InterruptedException {
        executor.click(locators_home.vouchersLink);
    }

    public void checkVisibilityOfPaginationCOmponentAndFacets(){
        assertTrue("Top pagination grider is not visible", executor.is_element_present(locators_search.mainPaginationGirder));
        assertTrue("Bottom pagination component is not visible", executor.is_element_present(locators_search.mainBottomPaginationComponent));
        assertTrue("Facet component is not visible", executor.is_element_present(locators_search.basicFacetComponent));
    }

    public void forAllVouchersCheckVisibilityOfElementsLikeExpiryDateNameETC(){
        List<WebElement> voucherContainer = executor.getWebElementsList(locators_vouchers.voucherContainerVoucherPage);
        List<WebElement> voucherImage = executor.getWebElementsList(locators_vouchers.voucherImageVoucherPage);
        List<WebElement> voucherRetailer = executor.getWebElementsList(locators_vouchers.voucherRetailerVoucherPage);
        List<WebElement> voucherName = executor.getWebElementsList(locators_vouchers.voucherNameVoucherPage);
        List<WebElement> voucherDescription = executor.getWebElementsList(locators_vouchers.voucherDescriptionVoucherPage);
        List<WebElement> voucherButton = executor.getWebElementsList(locators_vouchers.getVoucherButtonVoucherPage);
        List<WebElement> voucherExpiryDate = executor.getWebElementsList(locators_vouchers.voucherExpireVoucherPage);

        /*System.out.println(voucherContainer.size());
        System.out.println(voucherImage.size());
        System.out.println(voucherRetailer.size());
        System.out.println(voucherName.size());
        System.out.println(voucherDescription.size());
        System.out.println(voucherButton.size());
        System.out.println(voucherExpiryDate.size());*/

        assertTrue("Voucher elements number is improper for voucher description "+ voucherDescription, /*voucherContainer.size() == voucherImage.size() &&*/
                                                                                    voucherContainer.size() == voucherRetailer.size() &&
                                                                                    voucherContainer.size() == voucherName.size() &&
                                                                                    voucherContainer.size() == voucherDescription.size() &&
                                                                                    voucherContainer.size() == voucherButton.size() &&
                                                                                    voucherContainer.size() == voucherExpiryDate.size());
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Vouchers pages (RD-2091) - check content of Voucher Page
    String voucherDescriptionFromVouchersPage;
    String voucherRetailerName;
    String voucherTitle;
    String voucherExpiryDate;
    String voucherCode;
    int randomVoucher;
    public void chooseVoucher() throws SQLException {
        List<WebElement> voucherButton = executor.getWebElementsList(locators_vouchers.getVoucherButtonVoucherPage);
        List<WebElement> voucherDescription = executor.getWebElementsList(locators_vouchers.voucherDescriptionVoucherPage);
        randomVoucher = executor.return_random_value(voucherButton.size());
        String voucherDescriptionFromVouchersPage = executor.getText(voucherDescription.get(randomVoucher));
        System.out.println(voucherDescriptionFromVouchersPage);
        JDBC jdbc = new JDBC("affiliate_manager");
        //Open Retailer Page

        // this change is introduced only because of differences in DB structure between QA and local
        String voucherDB;
        if(envVariables.baseUrl.contains("qa")){
            voucherDB = "product_ingest.Voucher";
        }else{
            voucherDB = "product_ingest_vouchers.Voucher";
        }

        voucherExpiryDate = jdbc.return_proper_db_value("SELECT expiryDate FROM "+voucherDB+" WHERE description = \""+voucherDescriptionFromVouchersPage+"\"",1);
        voucherRetailerName = jdbc.return_proper_db_value("SELECT merchantName FROM "+voucherDB+" WHERE description = \""+voucherDescriptionFromVouchersPage+"\"",1);
        voucherTitle = jdbc.return_proper_db_value("SELECT title FROM "+voucherDB+" WHERE description = \""+voucherDescriptionFromVouchersPage+"\"",1);
        voucherCode = jdbc.return_proper_db_value("SELECT code FROM "+voucherDB+" WHERE description = \""+voucherDescriptionFromVouchersPage+"\"",1);
        jdbc.close();
    }

    public void compareBasicInformationWithDB() throws ParseException, InterruptedException {
        List<WebElement> voucherExpiryDates = executor.getWebElementsList(locators_vouchers.voucherExpireVoucherPage);
        List<WebElement> voucherRetailers = executor.getWebElementsList(locators_vouchers.voucherRetailerVoucherPage);
        List<WebElement> voucherNames = executor.getWebElementsList(locators_vouchers.voucherNameVoucherPage);
        Thread.sleep(1000);
        if(!voucherRetailerName.equals("")){
            assertEquals("Voucher retailer names are not the same comparing with DB", voucherRetailerName, executor.getText(voucherRetailers.get(randomVoucher)));
        }
        //Voucher name is no required
        //if(!executor.getText(voucherNames.get(randomVoucher)).equals("")){
        //assertEquals("Voucher titles are not the same comparing with DB", voucherTitle, executor.getText(voucherNames.get(randomVoucher)));
        //}
        if(!voucherExpiryDate.equals("")){
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat nt = new SimpleDateFormat("dd-MM-yyyy");
            assertEquals("Voucher expiry dates are not the same comparing with DB", "expires on " + nt.format(ft.parse(voucherExpiryDate.substring(0, 10))).replace("-", "/"), executor.getText(voucherExpiryDates.get(randomVoucher)));
        }
    }

    public void clickGetVoucherCodeAndCheckIfTransitionPagePageWasOpened() throws InterruptedException {
        List<WebElement> voucherButton = executor.getWebElementsList(locators_vouchers.getVoucherButtonVoucherPage);
        executor.click(voucherButton.get(randomVoucher));
        Thread.sleep(5000);
        executor.handleMultipleWindows("Sending");
        assertTrue("Transition page was not opened", executor.return_driver().getCurrentUrl().contains("transition"));
    }

    public void returnToVoucherPageForVoucherCodeAndCompareItWithDB() throws InterruptedException {
        executor.handleMultipleWindows("Vouchers");
        List<WebElement> voucherCodes = executor.getWebElementsList(locators_vouchers.voucherCodeVoucherPage);
        Thread.sleep(1000);
        assertEquals("Voucher code is incorrect for description " + voucherDescriptionFromVouchersPage  , voucherCode, executor.getText(voucherCodes.get(randomVoucher)));
    }

    public void returnToTransitionPageAndClickContinueAnyway(){
        executor.handleMultipleWindows("Sending to retailer");
        executor.click(locators_transition.continueAnywayButton);
    }

    public void checkIfClickoutWasReported(String ifSigned, String Email) throws InterruptedException, SQLException {
        // this function body is a copy of function from transition page navigation class, it is only a little bit changed
        String CurrentDate = executor.returnCurrentDate();
        Thread.sleep(10000);
        JDBC jdbc = new JDBC("affiliate_manager");
        assertTrue("In db there is no clickout with current date and hour, but should be", jdbc.return_proper_db_value("SELECT createdDate FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1).contains(CurrentDate));
        assertEquals("Clickout has improper merchant name", jdbc.return_proper_db_value("SELECT merchant FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), voucherRetailerName);
        assertEquals("Clickout has improper merchantId", jdbc.return_proper_db_value("SELECT merchantId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + voucherRetailerName + "'", 1));
        assertEquals("Clickout has improper publisher", jdbc.return_proper_db_value("SELECT publisher FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), envVariables.StoreName);
        assertEquals("Clickout has improper publisherId", jdbc.return_proper_db_value("SELECT publisherId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), "525e54ede4b0dbd2decff9a7");
        assertEquals("Clickout has improper affiliate networkId", jdbc.return_proper_db_value("SELECT affiliateNetworkId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + voucherRetailerName + "'", 1));
        assertEquals("Clickout has improper affiliate network name", jdbc.return_proper_db_value("SELECT affiliateNetwork FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT name FROM admin_ui.AffiliateNetwork WHERE id='" + jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + voucherRetailerName + "'", 1) + "'", 1));
        if (ifSigned.equals("user_sign_in")) {
            String temp = jdbc.return_proper_db_value("SELECT userId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1);
            jdbc = new JDBC("points_manager");
            assertEquals("Clickout has improper UserId", temp, jdbc.return_proper_db_value("SELECT userKey FROM points_manager.User WHERE email = '"+Email+"'",1));
        }
        jdbc.close();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Vouchers pages (RD-2091) - check displaying chosen voucher on individual voucher page
    String individualVoucherLink;
    public void openIndividualVoucherPageUsingShareOption() throws InterruptedException {
        List<WebElement> shareLinks = executor.getWebElementsList(locators_vouchers.voucherShareTwitterPage);
        List<WebElement> voucherRetailer = executor.getWebElementsList(locators_vouchers.voucherRetailerVoucherPage);
        List<WebElement> voucherDescriptions = executor.getWebElementsList(locators_vouchers.voucherDescriptionVoucherPage);
        List<WebElement> voucherExpiryDates = executor.getWebElementsList(locators_vouchers.voucherExpireVoucherPage);
        randomVoucher = executor.return_random_value(voucherExpiryDates.size());

        voucherDescriptionFromVouchersPage = executor.getText(voucherDescriptions.get(randomVoucher));
        voucherRetailerName = executor.getText(voucherRetailer.get(randomVoucher));
        voucherExpiryDate = executor.getText(voucherExpiryDates.get(randomVoucher));

        executor.click(shareLinks.get(randomVoucher));
        executor.handleMultipleWindows("tweet");
        executor.return_driver().navigate().refresh();
        individualVoucherLink = executor.getText(locators_vouchers.voucherLink);
        executor.return_driver().get(individualVoucherLink);
        Thread.sleep(2000);
    }

    public void checkContentOfChosenVoucherAndItsValues(){
        // static elements
        assertTrue("Highlighted chosen voucher is no visible", executor.is_element_present(locators_vouchers.chosenVoucherContainerVoucherPage));
        assertTrue("Chosen voucher get button is no visible", executor.is_element_present(locators_vouchers.getChosenVoucherButtonVoucherPage));
        // dynamic elements
        assertEquals("Description is not the same as on voucher card", voucherDescriptionFromVouchersPage,executor.getText(locators_vouchers.chosenVoucherDescriptionVoucherPage));
        assertEquals("Retailer name is not the same as on voucher card", voucherRetailerName,executor.getText(locators_vouchers.chosenVoucherRetailerVoucherPage).replace(":",""));
    }

    public void checkIfCodeIsAvailableWhenUserCLickGetVoucherCode() throws SQLException {
        JDBC jdbc = new JDBC("affiliate_manager");

        // this change is introduced only because of differences in DB structure between QA and local
        String voucherDB;
        if(envVariables.baseUrl.contains("qa")){
            voucherDB = "product_ingest.Voucher";
        }else{
            voucherDB = "product_ingest_vouchers.Voucher";
        }

        executor.click(locators_vouchers.getChosenVoucherButtonVoucherPage);
        assertTrue("Voucher code is invisible", executor.is_element_present(locators_vouchers.chosenVoucherCodeVoucherPage));
        assertEquals("Voucher code is different than in Database", executor.getText(locators_vouchers.chosenVoucherCodeVoucherPage), jdbc.return_proper_db_value("SELECT code FROM "+voucherDB+" WHERE description=\"" + voucherDescriptionFromVouchersPage + "\"", 1));
        jdbc.close();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Vouchers pages (RD-2091) - check reporting of clickout from individual voucher page
    public void clickGetVoucherCodeButton() throws InterruptedException {
        executor.click(locators_vouchers.getChosenVoucherButtonVoucherPage);
        Thread.sleep(2000);
    }

}
