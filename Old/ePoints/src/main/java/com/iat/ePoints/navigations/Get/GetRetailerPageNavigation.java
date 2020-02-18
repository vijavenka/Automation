package com.iat.ePoints.Navigations.Get;

import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.EnvironmentVariables;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Get.GetLandingPageLocators;
import com.iat.ePoints.Locators.Get.GetRetailerPageLocators;
import com.iat.ePoints.Locators.Get.GetStoresAZPageLocators;
import com.iat.ePoints.Locators.Get.GetVouchersPageLocators;
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
 * Date: 17.01.14
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class GetRetailerPageNavigation extends AbstractPage {

    private EnvironmentVariables envVariables = new EnvironmentVariables();
    private GetRetailerPageLocators locattors_retailer = new GetRetailerPageLocators();
    private HeaderLocators locators_header = new HeaderLocators();
    private GetLandingPageLocators locators_getPage = new GetLandingPageLocators();
    private GetStoresAZPageLocators locators_AZpage = new GetStoresAZPageLocators();
    private GetVouchersPageLocators locators_voucherPage = new GetVouchersPageLocators();
    String environmentDomain = envVariables.baseUrl;

    public GetRetailerPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // Check content of retailer page //////////////////////////////////////////////////////////////////////////////////
    public void checkContentOfTopSection() throws InterruptedException, SQLException {
        Thread.sleep(5000);
        JDBC jdbc = new JDBC("affiliate_manager");
        assertTrue("Retailer name is incorrect", executor.getText(locattors_retailer.retailerName).contains(retailerName));
        assertTrue("Points multiplier is incorrect", executor.getText(locattors_retailer.epointsMultiplier).contains(jdbc.return_proper_db_value("SELECT epointsMultiplier FROM admin_ui.Merchant WHERE name='" + executor.getText(locattors_retailer.retailerName) + "'", 1)));

        assertTrue("Go to retailer button is no available", executor.is_element_present(locattors_retailer.goToRetailerButton));
        assertTrue("Retailer logo is no available", executor.is_element_present(locattors_retailer.retailerImage));
        Thread.sleep(2000);
        assertTrue("Local trust pilot is not available", executor.is_element_present(locattors_retailer.localTrustPilotViev));
        assertTrue("External trust pilot link is not available", executor.is_element_present(locattors_retailer.externalTrustPilotLink));
        jdbc.close();
    }

    public void checkContentOfDeliveryDetails() throws SQLException {
        JDBC jdbc = new JDBC("affiliate_manager");
        assertTrue("Delivery details section is no visible", executor.is_element_present(locattors_retailer.deliveryDetailsBoxContent));
        assertEquals("Delivery box has improper name", executor.getText(locattors_retailer.deliveryDetailsBoxName), "Delivery details");
        if(executor.getText(locattors_retailer.deliveryDetailsBoxContent).length()>40){
        assertEquals("Delivery details box has improper content", executor.getText(locattors_retailer.deliveryDetailsBoxContent).trim().substring(0, 40), jdbc.return_proper_db_value("SELECT deliveryCostsDescription FROM admin_ui.Merchant WHERE name='" + executor.getText(locattors_retailer.retailerName) + "'", 1).trim().substring(0, 40));
        }else{
            assertEquals("Delivery details box has improper content", executor.getText(locattors_retailer.deliveryDetailsBoxContent), jdbc.return_proper_db_value("SELECT deliveryCostsDescription FROM admin_ui.Merchant WHERE name='" + executor.getText(locattors_retailer.retailerName) + "'", 1));
        }
            jdbc.close();
    }

    // RETAILER PAGE ON EPOINTS - Trust Pilot integration on new retailer page(RD-1158) ////////////////////////////////
    int retailerNoumber = executor.return_random_value(8);
    String retailerName;

    public void checkIfTrustPilotIsAvailable() throws InterruptedException {
        Thread.sleep(4000);
        assertTrue("Local trust pilot is not available", executor.is_element_present(locattors_retailer.localTrustPilotViev));
        assertTrue("External trust pilot link is not available", executor.is_element_present(locattors_retailer.externalTrustPilotLink));
    }

    public void checkIfModalTrustPilotIsAvailable() throws InterruptedException {
        executor.click(locattors_retailer.localTrustPilotViev);
        Thread.sleep(5000);
        assertTrue("Modal trust pilot is no visible", executor.is_element_present(locattors_retailer.modalTrustPilotContainer));
        assertTrue("Retailer name is not visible", executor.is_element_present(locattors_retailer.modalTrustPilotRetailerName));
        assertTrue("Retailer name is incorrect", executor.getText(locattors_retailer.modalTrustPilotRetailerName).contains(retailerName));
        assertTrue("Comment section is no visible", executor.is_element_present(locattors_retailer.modalTrustPilotCommentSection));
        assertTrue("Rate section is no visible", executor.is_element_present(locattors_retailer.modalTrustPilotRateSection));
        assertTrue("Close button is no available", executor.is_element_present(locattors_retailer.modalTrustPilotCloseButton));
        executor.click(locattors_retailer.modalTrustPilotCloseButton);
        assertFalse("Trust pilot should be closed", executor.is_element_present(locattors_retailer.modalTrustPilotContainer));
    }

    // RETAILER PAGE ON EPOINTS - VOUCHER block logic(RD-1160) voucher exists //////////////////////////////////////////
    String voucherId;
    String voucherName;
    String voucherDescription;
    String voucherExpiryDate;
    String voucherMerchantId;
    String voucherMerchantName;
    String voucherCode;

    public void openRetailerPageWithPromotedVoucher() throws InterruptedException, SQLException {
        JDBC jdbc = new JDBC("affiliate_manager");
        //Open Retailer Page
        //System.out.println(environmentDomain+"/retailer/"+voucherMerchantName+"/"+jdbc.return_proper_db_value("SELECT merchantId FROM product_ingest_vouchers.Voucher WHERE promoted ='1' ORDER BY createdDate DESC",1));

        // this change is introduced only because of differences in DB structure between QA and local
        String voucherDB;
        if(envVariables.baseUrl.contains("qa")){
          voucherDB = "product_ingest.Voucher";
        }else{
          voucherDB = "product_ingest_vouchers.Voucher";
        }

        executor.return_driver().get(environmentDomain+"/retailer/voucherMerchantName/"+jdbc.return_proper_db_value("SELECT merchantId FROM "+voucherDB+" WHERE promoted ='1' ORDER BY createdDate DESC",1));
         Thread.sleep(8000);
        voucherId = jdbc.return_proper_db_value("SELECT id FROM "+voucherDB+" WHERE description ='"+executor.getText(locattors_retailer.voucherDescription)+"' AND status='promoted'",1);
        voucherDescription = jdbc.return_proper_db_value("SELECT description FROM "+voucherDB+" WHERE id = '"+voucherId+"'",1);
        voucherExpiryDate = jdbc.return_proper_db_value("SELECT expiryDate FROM "+voucherDB+" WHERE id = '"+voucherId+"'",1);
        voucherMerchantId = jdbc.return_proper_db_value("SELECT merchantId FROM "+voucherDB+" WHERE id = '"+voucherId+"'",1);
        voucherMerchantName = jdbc.return_proper_db_value("SELECT merchantName FROM "+voucherDB+" WHERE id = '"+voucherId+"'",1);
        voucherName = jdbc.return_proper_db_value("SELECT title FROM "+voucherDB+" WHERE id = '"+voucherId+"'",1);
        voucherCode = jdbc.return_proper_db_value("SELECT code FROM "+voucherDB+" WHERE id = '"+voucherId+"'",1);
        jdbc.close();
    }

    public void checkIfVoucherHasProperElementsAndContent() throws InterruptedException, ParseException {
        Thread.sleep(2000);
        //Static elements
        assertTrue("Voucher container is no visible", executor.is_element_present(locattors_retailer.vouchersContainer));
        assertTrue("Left arrow is no visible", executor.is_element_present(locattors_retailer.vouchersShowingPaginationLeftArrow));
        assertTrue("Right arrow is no visible", executor.is_element_present(locattors_retailer.vouchersShowingPaginationRightArrow));
        //Dynamic elements
        //White signs were remover using .replace(" ","") because in UI elements has additional white sign comparing to Database
        //voucher title is no required
        //if(!voucherName.equals("")){
            //assertEquals("Voucher title is incorrect", voucherName.replace(" ",""), executor.getText(locattors_retailer.voucherName).replace(" ", ""));
        //}
        if(!voucherDescription.equals("")){
            assertEquals("Voucher description is incorrect", voucherDescription.replace(" ",""), executor.getText(locattors_retailer.voucherDescription).replace(" ", ""));
        }
        if(!voucherExpiryDate.equals("")){
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat nt = new SimpleDateFormat("dd-MM-yyyy");
            assertEquals("Expiry date is incorrect", "expires on " + nt.format(ft.parse(voucherExpiryDate.substring(0, 10))).replace("-", "/"), executor.getText(locattors_retailer.voucherExpiresDate));
        }
    }

    public void clickGetVoucherCodeButton() throws InterruptedException {
        Thread.sleep(5000);
        executor.click(locattors_retailer.voucherButtonGet);
        Thread.sleep(2000);
    }

    public void checkIfUserWasRedirectedToRetailerPage(){
        executor.handleMultipleWindows("Sending to retailer");
    }

    public void checkIfVoucherCodeIsDisplayedAndIsCorrect(){
        executor.handleMultipleWindows("| epoints");
        assertTrue("Voucher code is not displayed", executor.is_element_present(locattors_retailer.voucherCode));
        assertTrue("Open site button is no visible", executor.is_element_present(locattors_retailer.voucherOpenSiteButton));
        assertEquals("Voucher is incorrect", voucherCode, executor.getText(locattors_retailer.voucherCode));
    }

    // RETAILER PAGE ON EPOINTS - VOUCHER block logic(RD-1160) voucher not exists //////////////////////////////////////
    public void openRetailerPageWithoutAnyVoucherSpecialsCategories(){
        //Open Retailer Page
        //TODO  retailer may have vouchers in future and then this has to be changed
        executor.return_driver().get(environmentDomain+"/retailer/adnams-cellar-kitchen/f3721654-dc99-4939-a62c-8ceb23650448");
    }

    public void checkIfVoucherBlockIsInvisible() throws InterruptedException {
        Thread.sleep(8000);
        assertFalse("Voucher block is visible but should not", executor.is_element_present(locattors_retailer.vouchersContainer));
    }

    // RETAILER PAGE ON EPOINTS - VOUCHER block logic( RD-1160) see all button
    public void clickSeeAllVouchersButton(){
        voucherMerchantName = executor.getText(locattors_retailer.retailerName);
        executor.click(locattors_retailer.voucherSeeAllButton);
    }

    public void checkIfUserWasRedirectedToVoucherPage(){
        assertTrue("Voucher page has improper url or was not opened", executor.return_driver().getCurrentUrl().contains("/vouchers"));
    }

    public void checkAllVouchersRetailerName(){
        List<WebElement> voucherRetailerNames = executor.getWebElementsList(locators_voucherPage.voucherRetailerVoucherPage);
        for(WebElement retailerName : voucherRetailerNames){
            assertTrue("Voucher Page do not have only voucher from retailer "+retailerName, executor.getText(retailerName).contains(voucherMerchantName));
        }
    }

    // RETAILER PAGE ON EPOINTS - SPECIAL OFFER block logic ////////////////////////////////////////////////////////////
    public void openRetailerCardWithSpecialOffersAndCategoriesBlock() throws InterruptedException {
        executor.click(locators_header.getPoints);
        executor.send_keys(locators_AZpage.searcherTextfield, "John Lewis");
        retailerName = "John Lewis";
        Thread.sleep(1000);
        executor.click(locators_AZpage.searcherButton);
        Thread.sleep(1000);
        executor.moveMouseToWebElement(locators_AZpage.basicAlphabetRetailerLocator);
        executor.click(locators_AZpage.retailerCardRetailerPageButtonAlphabeticOnly);
        Thread.sleep(8000);
    }

    public void checkSpecialOfferBlockVisibility(){
        assertTrue("Special offer block is no visible", executor.is_element_present(locattors_retailer.specialOffersContainer));
    }

    public void checkContentOfSpecialffersBlock(){
        assertTrue("Showing element is no visible", executor.is_element_present(locattors_retailer.specialOffersShowingOf));
        assertTrue("Right navigation arrow is no visible", executor.is_element_present(locattors_retailer.specialOffersArrowRight));
        assertTrue("Left navigation arrow is no visible", executor.is_element_present(locattors_retailer.specialOffersArrowLeft));
        assertTrue("Category DDL is no visible", executor.is_element_present(locattors_retailer.specialOffersSelectCategory));

        List<WebElement> temp;
        temp = executor.getWebElementsList(locattors_retailer.specialOffersProductImage);
        assertEquals("Number of product images is no correct", temp.size(), 3); //three objects are presented in special offers block
        temp = executor.getWebElementsList(locattors_retailer.specialOffersPercentageSaving);
        assertEquals("Number of percentage savings is no correct", temp.size(), 3);
        temp = executor.getWebElementsList(locattors_retailer.specialOffersEpointsEarned);
        assertEquals("Number of epoints earned is no correct", temp.size(), 3);
        temp = executor.getWebElementsList(locattors_retailer.specialOffersProductTitle);
        assertEquals("Number of Product titles is no correct", temp.size(), 3);
        temp = executor.getWebElementsList(locattors_retailer.specialOffersOldPrice);
        assertEquals("Number of old prices is no correct", temp.size(), 3);
        temp = executor.getWebElementsList(locattors_retailer.specialOffersNewPrice);
        assertEquals("Number of new prices is no correct", temp.size(), 3);
        temp = executor.getWebElementsList(locattors_retailer.specialOffersFromRetailer);
        assertEquals("Number of Retailer names is no correct", temp.size(), 3);
        temp = executor.getWebElementsList(locattors_retailer.specialOffersBuyButton);
        assertEquals("Number of Buy buttons is no correct", temp.size(), 3);
    }
    public void checkOrderOfOffer(){
        List<WebElement> percentage = executor.getWebElementsList(locattors_retailer.specialOffersPercentageSaving);
        assertTrue("Order Of offer 1-2 are incorrect", Integer.parseInt(executor.getText(percentage.get(0)).replace("% OFF",""))>=Integer.parseInt(executor.getText(percentage.get(1)).replace("% OFF","")));
        assertTrue("Order Of offer 2-3 are incorrect", Integer.parseInt(executor.getText(percentage.get(1)).replace("% OFF",""))>=Integer.parseInt(executor.getText(percentage.get(2)).replace("% OFF","")));
    }

    public void changeCategory(){
        executor.selectOptionByValue(locattors_retailer.specialOffersSelectCategory,"Womens Dresses");
    }

    String firstRetailer;
    public void useNavigationComponent(String direction){
        firstRetailer = executor.getText(locattors_retailer.specialOffersProductTitle);
        if(direction.equals("right")){
            executor.click(locattors_retailer.specialOffersArrowRight);
        }else if(direction.equals("left")){
            executor.click(locattors_retailer.specialOffersArrowLeft);
        }
    }

    public void checkIfOfferWereChanged(){
        assertNotEquals("First offer does not change", firstRetailer, executor.getText(locattors_retailer.specialOffersProductTitle));
    }

    // RETAILER PAGE ON EPOINTS - PRODUCT CATEGORIES logic block (RD-1162) (RD-1360) ///////////////////////////////////
    public void checkVisibilityOfCategoriesBlock(boolean isVisible) throws InterruptedException {
        Thread.sleep(6000);
        if(isVisible){
            assertTrue("Category block is no visible", executor.is_element_present(locattors_retailer.productCategoriesContainer));
        }else if(!isVisible){
            assertFalse("Category block is visible but should not", executor.is_element_present(locattors_retailer.productCategoriesContainer));
        }
    }

    public void checkContentOfCategoriesBlock() throws InterruptedException {
        List<WebElement> mainCategories = executor.getWebElementsList(locattors_retailer.productCategoriesMainCategories);
        List<WebElement> subCategories = executor.getWebElementsList(locattors_retailer.productCategoriesSubCategories);
        int categoriesExpand = mainCategories.size();
        int subCategoriesExpand = subCategories.size();
        //it must be checked few possibilities
        if(mainCategories.size()==1){
            assertTrue("There is more then three of subcategories", subCategories.size()<=3);
            assertFalse("See more button should be not visible", executor.is_element_present(locattors_retailer.productCategoriesSeeMoreButton));
        }else if(mainCategories.size()==2){
            assertTrue("There is more then six of subcategories", subCategories.size()<=6);
            assertFalse("See more button should be not visible", executor.is_element_present(locattors_retailer.productCategoriesSeeMoreButton));
        }else if(mainCategories.size()==3){
            assertTrue("There is more then nine of subcategories", subCategories.size()<=9);
            assertFalse("See more button should be not visible", executor.is_element_present(locattors_retailer.productCategoriesSeeMoreButton));
        }
        //if see more button is visible it means that there is more of categories
        if(executor.is_element_present(locattors_retailer.productCategoriesSeeMoreButton)){
            executor.click(locattors_retailer.productCategoriesSeeMoreButton);
            Thread.sleep(1000);
            assertTrue("After expanding there should be more categories", executor.getWebElementsList(locattors_retailer.productCategoriesMainCategories).size() > 3);
            assertTrue("After expanding there should be more sub categories", executor.getWebElementsList(locattors_retailer.productCategoriesSubCategories).size() > 3);
            assertTrue("There is to many subcategories", subCategoriesExpand<=categoriesExpand*3);
        }
    }

    // RETAILER PAGE ON EPOINTS - SIMILIAR RETAILERS block logic (Rd-1163) - special offer block available /////////////
    public void checkContentOfSimilarRetailersBlock(){
        assertTrue("Similar block header is no visible", executor.is_element_present(locattors_retailer.similarRetailersContainer));
        assertTrue("Any retailer card is no visible", executor.is_element_present(locattors_retailer.similarRetailersBasicRetailerCard));
        executor.moveMouseToWebElement(locattors_retailer.similarRetailersBasicRetailerCard);
        assertTrue("Retailer card name is no visible", executor.is_element_present(locattors_retailer.similarRetailersBasicRetailerName));
        assertTrue("Retailer card info and offers button is no visible", executor.is_element_present(locattors_retailer.similarRetailersBasicInfoOffersButton));
        assertTrue("Retailer card visit retailer button is no visible", executor.is_element_present(locattors_retailer.similarRetailersBasicVisitRetailerButton));
    }

    public void goToAnotherRetailerPage() throws InterruptedException {
        Thread.sleep(2000);
        executor.moveMouseToWebElement(locattors_retailer.similarRetailersBasicRetailerCard);
        retailerName = executor.getText(locattors_retailer.similarRetailersBasicRetailerName);
        executor.click(locattors_retailer.similarRetailersBasicInfoOffersButton);
    }

    public void checkIfuserWereProperlyRedirectedToAnotherRetailerPage(){
        assertEquals("Chosen retailer page name is incorrect", retailerName + ": Offers, Vouchers and Reviews | epoints", executor.return_driver().getTitle());
    }

    public void goToExternalRetailerPage() throws InterruptedException {
        for(int i=0;i<=10;i++){
            Thread.sleep(1000);
            executor.moveMouseToWebElement(locattors_retailer.similarRetailersBasicRetailerCard);
            if(executor.getText(locattors_retailer.similarRetailersBasicRetailerName).equals(retailerName)){
                executor.click(locattors_retailer.similarRetailersBasicVisitRetailerButton);
                break;
            }else{
                executor.click(locattors_retailer.similarRetailersArrowRight);
            }
            if(i==10){
                assertTrue("Chosen before similar retailer was not fount now, something is wrong",false);
            }
        }
    }

    public void checkIfUserWereProperlyRedirectedToExternalRetailerPage(){
        executor.handleMultipleWindows("Sending to retailer | epoints");
        assertEquals("Transition page was not opened", executor.return_driver().getTitle(), "Sending to retailer | epoints");
    }

    // RETAILER PAGE ON EPOINTS - SIMILIAR RETAILERS block logic (Rd-1163) - special offer block not available /////////
    public void checkSimilarRetailersBlockVisibility(boolean specialOffersAvaliability){
        if(specialOffersAvaliability){
            assertTrue("Special offers block is no available",executor.is_element_present(locattors_retailer.similarRetailersContainer));
        }else if(!specialOffersAvaliability){
            assertFalse("Special offers block is available but should not", executor.is_element_present(locattors_retailer.similarRetailersContainer));
        }
    }

    // Check if show all retailer button works correctly ///////////////////////////////////////////////////////////////
    public void checkVisibilityOfAllRetailersButton(){
        assertTrue("Show all retailers button is no visible", executor.is_element_present(locattors_retailer.showAllRetailersButton));
    }

    public void clickShowAllRetailersButton(){
        executor.click(locattors_retailer.showAllRetailersButton);
    }

    public void checkIfUserWasRedirectedToAZPage(){
        assertEquals("User was not redirected to AZ page", "Get epoints from thousands of retailers | epoints", executor.return_driver().getTitle());
    }

    // RETAILER PAGE ON EPOINTS - required update to special offer block (RD-1520) /////////////////////////////////////
    public void checkIfProperNumberOfTabsIsAvaliable(){
        assertEquals("Number of tabs is no correct", "showing 1 of 34", executor.getText(locattors_retailer.specialOffersShowingOf));
    }

    public void goThroughAllTabsAndCheckSaving() throws InterruptedException {
        for(int i=0; i<34; i++){
            assertTrue("Saving is below 50%" ,Integer.parseInt(executor.getText(locattors_retailer.specialOffersPercentageSaving).replace("% OFF",""))>=50);
            executor.click(locattors_retailer.specialOffersArrowRight);
            Thread.sleep(200);
        }
    }

    public void clickSeeAllSpecialOffersButton(){
        executor.click(locattors_retailer.specialOffersSeeAll);
    }

    public void checkIfUserIsRedirectedToOffersPage(){
        assertTrue("offers URL is incorrect", executor.return_driver().getCurrentUrl().contains("/search"));
    }


    //  Retailer page -  all products from retailer block - block should be visible case ///////////////////////////////
    public void checkVisibilityOfAllProductsBlock(boolean isVisible){
        if(isVisible){
            assertTrue("All product from retailer block is no visible",executor.is_element_present(locattors_retailer.allProductsFromRetailerContainer));
        }else if(!isVisible){
            assertFalse("All product from retailer block is visible but should not", executor.is_element_present(locattors_retailer.allProductsFromRetailerContainer));
        }
    }
    String productsNumber;
    public void rememberProductsNumberDisplayedInAllProductsBlock(){
        productsNumber = executor.getText(locattors_retailer.allProductsFromRetailerProductsNumber).replace(" products","");
    }

    public void clickAllProductLink(){
        executor.click(locattors_retailer.allProductsFromRetailerLink);
    }

    public void checkIfUserWasRedirectedToShopPage(){
        assertEquals("Page name is incorrect", "Price comparison search | epoints", executor.return_driver().getTitle());
    }

    public void compareProductsNumberFromAllProductsBlockAndShopPage(){
        assertEquals("Points values are different", productsNumber, executor.getText(locators_AZpage.totalRetailersNumber).replace("out of ",""));
    }

}