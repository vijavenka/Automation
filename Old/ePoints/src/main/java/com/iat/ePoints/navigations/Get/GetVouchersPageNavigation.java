package com.iat.ePoints.Navigations.Get;

import com.iat.ePoints.EnvironmentVariables;
import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Get.*;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 12.02.14
 * Time: 14:59
 * To change this template use File | Settings | File Templates.
 */
public class GetVouchersPageNavigation extends AbstractPage{

    private GetVouchersPageLocators locators_voucher = new GetVouchersPageLocators();
    private HeaderLocators locators_header = new HeaderLocators();
    private GetLandingPageLocators locators_landingPage = new GetLandingPageLocators();
    private GetTransitionPageLocators locator_transition = new GetTransitionPageLocators();
    //for pagination component Locators
    private GetStoresAZPageLocators locator_AZpage = new GetStoresAZPageLocators();
    //for facet component Locators
    private GetShopPageLocators locator_shopPage = new GetShopPageLocators();
    private EnvironmentVariables envVariables = new EnvironmentVariables();

    public GetVouchersPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // EPOINTS - add VOUCHERS interfaces to epoints.com (RD-1140) - check content of Voucher Page
    public void openVoucherPage() throws InterruptedException {
        executor.click(locators_header.getPoints);
        executor.click(locators_landingPage.vouchersSubmenu);
        Thread.sleep(4000);
    }

    public void checkVisibilityOfPaginationCOmponentAndFacets() throws InterruptedException {
        Thread.sleep(6000); //TODO - - -
        assertTrue("Top pagination grider is not visible", executor.is_element_present(locator_AZpage.mainPaginationGirder));
        assertTrue("Bottom pagination component is not visible", executor.is_element_present(locator_AZpage.mainBottomPaginationComponent));
        assertTrue("Facet component is not visible", executor.is_element_present(locator_shopPage.basicFacetComponent));
    }

    public void forAllVouchersCheckVisibilityOfElementsLikeExpiryDateNameETC(){
        List<WebElement> voucherContainer = executor.getWebElementsList(locators_voucher.voucherContainerVoucherPage);
        List<WebElement> voucherImage = executor.getWebElementsList(locators_voucher.voucherImageVoucherPage);
        List<WebElement> voucherRetailer = executor.getWebElementsList(locators_voucher.voucherRetailerVoucherPage);
        List<WebElement> voucherName = executor.getWebElementsList(locators_voucher.voucherNameVoucherPage);
        List<WebElement> voucherDescription = executor.getWebElementsList(locators_voucher.voucherDescriptionVoucherPage);
        List<WebElement> voucherButton = executor.getWebElementsList(locators_voucher.getVoucherButtonVoucherPage);
        List<WebElement> voucherExpiryDate = executor.getWebElementsList(locators_voucher.voucherExpireVoucherPage);

        assertTrue("Voucher elements number is improper ", /*voucherContainer.size() == voucherImage.size() &&*/
                                                                                   voucherContainer.size() == voucherRetailer.size() &&
                                                                                   voucherContainer.size() == voucherName.size() &&
                                                                                   voucherContainer.size() == voucherDescription.size() &&
                                                                                   voucherContainer.size() == voucherButton.size() &&
                                                                                   voucherContainer.size() == voucherExpiryDate.size());
    }

    // EPOINTS - add VOUCHERS interfaces to epoints.com (RD-1140) - check possibility of using voucher, db validate, voucher code and clickout
    String voucherDescriptionFromVouchersPage;
    String voucherRetailerName;
    String voucherTitle;
    String voucherExpiryDate;
    String voucherCode;
    int randomVoucher;
    public void chooseVoucher() throws SQLException {
        List<WebElement> voucherButton = executor.getWebElementsList(locators_voucher.getVoucherButtonVoucherPage);
        List<WebElement> voucherDescription = executor.getWebElementsList(locators_voucher.voucherDescriptionVoucherPage);
        randomVoucher = executor.return_random_value(voucherButton.size());
        voucherDescriptionFromVouchersPage = executor.getText(voucherDescription.get(randomVoucher));
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

        voucherExpiryDate = jdbc.return_proper_db_value("SELECT expiryDate FROM "+voucherDB+" WHERE description = \""+voucherDescriptionFromVouchersPage+"\" AND status = 'promoted'",1);
        voucherRetailerName = jdbc.return_proper_db_value("SELECT merchantName FROM "+voucherDB+" WHERE description = \""+voucherDescriptionFromVouchersPage+"\" AND status = 'promoted'",1);
        voucherTitle = jdbc.return_proper_db_value("SELECT title FROM "+voucherDB+" WHERE description = \""+voucherDescriptionFromVouchersPage+"\" AND status = 'promoted'",1);
        voucherCode = jdbc.return_proper_db_value("SELECT code FROM "+voucherDB+" WHERE description = \""+voucherDescriptionFromVouchersPage+"\" AND status = 'promoted'",1);
        jdbc.close();
    }

    public void compareBasicInformationWithDB() throws ParseException, InterruptedException {
        List<WebElement> voucherExpiryDates = executor.getWebElementsList(locators_voucher.voucherExpireVoucherPage);
        List<WebElement> voucherRetailers = executor.getWebElementsList(locators_voucher.voucherRetailerVoucherPage);
        //List<WebElement> voucherNames = executor.getWebElementsList(locators_voucher.voucherNameVoucherPage);
        Thread.sleep(1000);
        System.out.println(voucherRetailerName);
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
        List<WebElement> voucherButton = executor.getWebElementsList(locators_voucher.getVoucherButtonVoucherPage);
        executor.click(voucherButton.get(randomVoucher));
        Thread.sleep(1000);
        executor.handleMultipleWindows("Sending to retailer");
        assertEquals("Transition page was not opened", executor.return_driver().getTitle(), "Sending to retailer | epoints");
    }

    public void returnToVoucherPageForVoucherCodeAndCompareItWithDB() throws InterruptedException {
        executor.handleMultipleWindows("epoints");
        List<WebElement> voucherCodes = executor.getWebElementsList(locators_voucher.voucherCodeVoucherPage);
        Thread.sleep(1000);
        assertEquals("Voucher code is incorrect for description " + voucherDescriptionFromVouchersPage  , voucherCode, executor.getText(voucherCodes.get(randomVoucher)));
    }

    /*public void returnToTransitionPageAndClickContinueAnyway(){
        executor.handleMultipleWindows("Sending to retailer");
        executor.click(locator_transition.ContinueAnywayLink);
    }*/

    public void checkIfClickoutWasReported(String ifSigned, String Email) throws InterruptedException, SQLException {
        // this function body is a copy of function from transition page navigation class, it is only a little bit changed
        String CurrentDate = executor.returnCurrentDate();
        Thread.sleep(10000);
        JDBC jdbc = new JDBC("affiliate_manager");
        assertTrue("In db there is no clickout with current date and hour, but should be", jdbc.return_proper_db_value("SELECT createdDate FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1).contains(CurrentDate));
        assertEquals("Clickout has improper merchant name", jdbc.return_proper_db_value("SELECT merchant FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), voucherRetailerName);
        assertEquals("Clickout has improper merchantId", jdbc.return_proper_db_value("SELECT merchantId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + voucherRetailerName + "'", 1));
        assertEquals("Clickout has improper publisher", jdbc.return_proper_db_value("SELECT publisher FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), "epoints");
        assertEquals("Clickout has improper publisherId", jdbc.return_proper_db_value("SELECT publisherId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), "ePoints.com");
        assertEquals("Clickout has improper affiliate networkId", jdbc.return_proper_db_value("SELECT affiliateNetworkId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + voucherRetailerName + "'", 1));
        assertEquals("Clickout has improper affiliate network name", jdbc.return_proper_db_value("SELECT affiliateNetwork FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT name FROM admin_ui.AffiliateNetwork WHERE id='" + jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + voucherRetailerName + "'", 1) + "'", 1));
        if (ifSigned.equals("user_sign_in")) {
            String temp = jdbc.return_proper_db_value("SELECT userId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1);
            jdbc = new JDBC("points_manager");
            assertEquals("Clickout has improper UserId", temp, jdbc.return_proper_db_value("SELECT userKey FROM points_manager.User WHERE email = '"+Email+"'",1));
        }
        jdbc.close();
    }

    // EPOINTS - add VOUCHER individual product interface to epoints (RD-1805) - check displaying chosen voucher on individual voucher page
    String individualVoucherLink;
    public void openIndividualVoucherPageUsingShareOption() throws InterruptedException {
        List<WebElement> shareLinks = executor.getWebElementsList(locators_voucher.voucherShareTwitterPage);
        List<WebElement> voucherRetailer = executor.getWebElementsList(locators_voucher.voucherRetailerVoucherPage);
        List<WebElement> voucherDescriptions = executor.getWebElementsList(locators_voucher.voucherDescriptionVoucherPage);
        List<WebElement> voucherExpiryDates = executor.getWebElementsList(locators_voucher.voucherExpireVoucherPage);
        randomVoucher = executor.return_random_value(voucherExpiryDates.size());

        voucherDescriptionFromVouchersPage = executor.getText(voucherDescriptions.get(randomVoucher));
        voucherRetailerName = executor.getText(voucherRetailer.get(randomVoucher));
        voucherExpiryDate = executor.getText(voucherExpiryDates.get(randomVoucher));

        executor.click(shareLinks.get(randomVoucher));
        executor.handleMultipleWindows("tweet");
        executor.return_driver().navigate().refresh();
        individualVoucherLink = executor.getText(locators_voucher.voucherLink);
        executor.return_driver().get(individualVoucherLink);
        Thread.sleep(2000);
    }

    public void checkContentOfChosenVoucherAndItsValues(){
        // static elements
        assertTrue("Highlighted chosen voucher is no visible", executor.is_element_present(locators_voucher.chosenVoucherContainerVoucherPage));
        assertTrue("Chosen voucher image is no visible", executor.is_element_present(locators_voucher.chosenVoucherImageVoucherPage));
        assertTrue("Chosen voucher get button is no visible", executor.is_element_present(locators_voucher.getChosenVoucherButtonVoucherPage));
        // dynamic elements
        assertEquals("Description is not the same as on voucher card", voucherDescriptionFromVouchersPage,executor.getText(locators_voucher.chosenVoucherDescriptionVoucherPage));
        assertEquals("Retailer name is not the same as on voucher card", voucherRetailerName,executor.getText(locators_voucher.chosenVoucherRetailerVoucherPage).replace(":",""));
        assertEquals("Expiry date is not the same as on voucher card", voucherExpiryDate,executor.getText(locators_voucher.chosenVoucherExpireVoucherPage));

        if(!executor.is_element_present(locators_voucher.voucherContainerVoucherPage)){
            assertFalse("Additiona voucher section should be disabled", executor.is_element_present(locators_voucher.moreVouchersSections));
        }
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

        executor.click(locators_voucher.getChosenVoucherButtonVoucherPage);
        assertTrue("Voucher code is invisible", executor.is_element_present(locators_voucher.chosenVoucherCodeVoucherPage));
        assertEquals("Voucher code is different than in Database", executor.getText(locators_voucher.chosenVoucherCodeVoucherPage), jdbc.return_proper_db_value("SELECT code FROM "+voucherDB+" WHERE description=\"" + voucherDescriptionFromVouchersPage + "\" AND status = 'promoted'", 1));
        jdbc.close();
    }

    // EPOINTS - add VOUCHER individual product interface to epoints (RD-1805) - check reporting of clickout from individual voucher page
    public void clickGetVoucherCodeButton(){
        executor.click(locators_voucher.getChosenVoucherButtonVoucherPage);
    }
}
