package com.iat.storepresentation.Navigations.RetailerPage;

import com.iat.storepresentation.Database.JDBC;
import com.iat.storepresentation.EnvironmentVariables;
import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.AZPage.AZPageLocators;
import com.iat.storepresentation.Locators.RetailePage.RetailerPageLocators;
import com.iat.storepresentation.Locators.SearchComponent.SearchPageLocators;
import com.iat.storepresentation.Locators.ShopHome.HomePageLocators;
import com.iat.storepresentation.Locators.Vouchers.VoucherPageLocators;
import com.iat.storepresentation.Navigations.AbstractPage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 09.03.14
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
public class RetailerPageNavigation extends AbstractPage{

    private HomePageLocators locators_home = new HomePageLocators();
    private RetailerPageLocators locators_retailer = new RetailerPageLocators();
    private AZPageLocators locators_AZ = new AZPageLocators();
    private EnvironmentVariables envVariables = new EnvironmentVariables();
    private VoucherPageLocators locators_voucher = new VoucherPageLocators();
    private SearchPageLocators locators_search = new SearchPageLocators();


    public RetailerPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - checking page content
    String retailerName;
    public void checkContentOfTopSection() throws InterruptedException, SQLException {
        JDBC jdbc = new JDBC("affiliate_manager");
        assertTrue("Retailer name is incorrect", executor.getText(locators_retailer.retailerName).contains(retailerName));
        assertTrue("Points multiplier is incorrect", executor.getText(locators_retailer.epointsMultiplier).contains(jdbc.return_proper_db_value("SELECT epointsMultiplier FROM admin_ui.Merchant WHERE name='" + executor.getText(locators_retailer.retailerName) + "'", 1)));

        assertTrue("Go to retailer button is no available", executor.is_element_present(locators_retailer.goToRetailerButton));
        assertTrue("Retailer logo is no available", executor.is_element_present(locators_retailer.retailerImage));
        Thread.sleep(2000);
        assertTrue("Local trust pilot is not available", executor.is_element_present(locators_retailer.localTrustPilotViev));
        assertTrue("External trust pilot link is not available", executor.is_element_present(locators_retailer.externalTrustPilotLink));
        jdbc.close();
    }

    public void checkContentOfDeliveryDetails() throws SQLException {
        JDBC jdbc = new JDBC("affiliate_manager");
        assertTrue("Delivery details section is no visible", executor.is_element_present(locators_retailer.deliveryDetailsBoxContent));
        assertEquals("Delivery box has improper name", executor.getText(locators_retailer.deliveryDetailsBoxName), "Delivery details");
        if(executor.getText(locators_retailer.deliveryDetailsBoxContent).length()>40){
            assertEquals("Delivery details box has improper content", executor.getText(locators_retailer.deliveryDetailsBoxContent).trim().substring(0, 40), jdbc.return_proper_db_value("SELECT deliveryCostsDescription FROM admin_ui.Merchant WHERE name='" + executor.getText(locators_retailer.retailerName) + "'", 1).trim().substring(0, 40));
        }else{
            assertEquals("Delivery details box has improper content", executor.getText(locators_retailer.deliveryDetailsBoxContent), jdbc.return_proper_db_value("SELECT deliveryCostsDescription FROM admin_ui.Merchant WHERE name='" + executor.getText(locators_retailer.retailerName) + "'", 1));
        }
        jdbc.close();
    }

    public void openRetailerPage() throws InterruptedException {
        executor.click(locators_home.azPageHome);
        executor.send_keys(locators_AZ.searcherTextfield, "John Lewis");
        retailerName="John Lewis";
        executor.click(locators_AZ.searcherButton);
        executor.moveMouseToWebElement(locators_AZ.basicAlphabetRetailerLocator);
        executor.click(locators_AZ.retailerCardRetailerPageButtonAlphabeticOnly);
        Thread.sleep(8000);
    }

    // RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - trust pilot
    public void checkIfTrustPilotIsAvailable() throws InterruptedException {
        Thread.sleep(4000);
        assertTrue("Local trust pilot is not available", executor.is_element_present(locators_retailer.localTrustPilotViev));
        assertTrue("External trust pilot link is not available", executor.is_element_present(locators_retailer.externalTrustPilotLink));
    }

    public void checkIfModalTrustPilotIsAvailable() throws InterruptedException {
        executor.click(locators_retailer.localTrustPilotViev);
        Thread.sleep(2000);
        assertTrue("Modal trust pilot is no visible", executor.is_element_present(locators_retailer.modalTrustPilotContainer));
        assertTrue("Retailer name is not visible", executor.is_element_present(locators_retailer.modalTrustPilotRetailerName));
        assertTrue("Retailer name is incorrect", executor.getText(locators_retailer.modalTrustPilotRetailerName).contains(retailerName));
        assertTrue("Comment section is no visible", executor.is_element_present(locators_retailer.modalTrustPilotCommentSection));
        assertTrue("Rate section is no visible", executor.is_element_present(locators_retailer.modalTrustPilotRateSection));
        assertTrue("Close button is no available", executor.is_element_present(locators_retailer.modalTrustPilotCloseButton));
        executor.click(locators_retailer.modalTrustPilotCloseButton);
        assertFalse("Trust pilot should be closed", executor.is_element_present(locators_retailer.modalTrustPilotContainer));
    }

    // RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - vouchers on retailer page
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
        if(envVariables.baseUrl.contains("store-presentation-web-qa.iatlimited.com")){
            voucherDB = "product_ingest.Voucher";
        }else{
            voucherDB = "product_ingest_vouchers.Voucher";
        }

        executor.return_driver().get(envVariables.baseUrl+"retailer/voucherMerchantName/"+jdbc.return_proper_db_value("SELECT merchantId FROM "+voucherDB+" WHERE promoted ='1' ORDER BY createdDate DESC",1));
        Thread.sleep(8000);
        voucherId = jdbc.return_proper_db_value("SELECT id FROM "+voucherDB+" WHERE description ='"+executor.getText(locators_retailer.voucherDescription)+"'",1);
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
        assertTrue("Voucher container is no visible", executor.is_element_present(locators_retailer.vouchersContainer));
        assertTrue("Left arrow is no visible", executor.is_element_present(locators_retailer.vouchersShowingPaginationLeftArrow));
        assertTrue("Right arrow is no visible", executor.is_element_present(locators_retailer.vouchersShowingPaginationRightArrow));
        //Dynamic elements
        //White signs were remover using .replace(" ","") because in UI elements has additional white sign comparing to Database
        //voucher title is no required
        //if(!voucherName.equals("")){
        //assertEquals("Voucher title is incorrect", voucherName.replace(" ",""), executor.getText(locators_retailer.voucherName).replace(" ", ""));
        //}
        if(!voucherDescription.equals("")){
            assertEquals("Voucher description is incorrect", voucherDescription.replace(" ",""), executor.getText(locators_retailer.voucherDescription).replace(" ", ""));
        }
        if(!voucherExpiryDate.equals("")){
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat nt = new SimpleDateFormat("dd-MM-yyyy");
            assertEquals("Expiry date is incorrect", "expires on " + nt.format(ft.parse(voucherExpiryDate.substring(0, 10))).replace("-", "/"), executor.getText(locators_retailer.voucherExpiresDate));
        }
    }

    public void clickGetVoucherCodeButton(){
        executor.click(locators_retailer.voucherButtonGet);
    }

    public void checkIfUserWasRedirectedToRetailerPage() throws InterruptedException {
        Thread.sleep(5000);
        executor.handleMultipleWindows(voucherMerchantName);
    }

    public void checkIfVoucherCodeIsDisplayedAndIsCorrect(){
        executor.handleMultipleWindows("Offers, Vouchers and Reviews");
        assertTrue("Voucher code is not displayed", executor.is_element_present(locators_retailer.voucherCode));
        assertTrue("Open site button is no visible", executor.is_element_present(locators_retailer.voucherOpenSiteButton));
        assertEquals("Voucher is incorrect", voucherCode, executor.getText(locators_retailer.voucherCode));
    }

    // RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - vouchers no exists
    public void openRetailerPageWithoutAnyVoucherSpecialsCategories(){
        //Open Retailer Page
        //TODO  retailer may have vouchers in future and then this has to be changed
        executor.return_driver().get(envVariables.baseUrl+"retailer/adnams-cellar-kitchen/f3721654-dc99-4939-a62c-8ceb23650448");
    }

    public void checkIfVoucherBlockIsInvisible(){
        Assert.assertFalse("Voucher block is visible but should not", executor.is_element_present(locators_retailer.vouchersContainer));
    }

    // RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - vouchers see all button
    public void clickSeeAllVouchersButton(){
        voucherMerchantName = executor.getText(locators_retailer.retailerName);
        executor.click(locators_retailer.voucherSeeAllButton);
    }

    public void checkIfUserWasRedirectedToVoucherPage(){
        assertTrue("Voucher page has improper url or was not opened", executor.return_driver().getCurrentUrl().contains("/vouchers"));
    }

    public void checkAllVouchersRetailerName(){
        List<WebElement> voucherRetailerNames = executor.getWebElementsList(locators_voucher.voucherRetailerVoucherPage);
        for(WebElement retailerName : voucherRetailerNames){
            assertTrue("Voucher Page do not have only voucher from retailer "+retailerName, executor.getText(retailerName).contains(voucherMerchantName));
        }
    }

    // RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - special offers block
    public void checkSpecialOfferBlockVisibility(){
        assertTrue("Special offer block is no visible", executor.is_element_present(locators_retailer.specialOffersContainer));
    }

    public void checkContentOfSpecialffersBlock(){
        assertTrue("Showing element is no visible", executor.is_element_present(locators_retailer.specialOffersShowingOf));
        assertTrue("Right navigation arrow is no visible", executor.is_element_present(locators_retailer.specialOffersArrowRight));
        assertTrue("Left navigation arrow is no visible", executor.is_element_present(locators_retailer.specialOffersArrowLeft));
        assertTrue("Category DDL is no visible", executor.is_element_present(locators_retailer.specialOffersSelectCategory));

        List<WebElement> temp;
        temp = executor.getWebElementsList(locators_retailer.specialOffersProductImage);
        assertEquals("Number of product images is no correct", temp.size(), 3); //three objects are presented in special offers block
        //temp = executor.getWebElementsList(locators_retailer.specialOffersPercentageSaving);
        //assertEquals("Number of percentage savings is no correct", temp.size(), 3);
        temp = executor.getWebElementsList(locators_retailer.specialOffersEpointsEarned);
        assertEquals("Number of epoints earned is no correct", temp.size(), 3);
        temp = executor.getWebElementsList(locators_retailer.specialOffersProductTitle);
        assertEquals("Number of Product titles is no correct", temp.size(), 3);
        //temp = executor.getWebElementsList(locators_retailer.specialOffersOldPrice);
        //assertEquals("Number of old prices is no correct", temp.size(), 3);
        temp = executor.getWebElementsList(locators_retailer.specialOffersNewPrice);
        assertEquals("Number of new prices is no correct", temp.size(), 3);
        temp = executor.getWebElementsList(locators_retailer.specialOffersFromRetailer);
        assertEquals("Number of Retailer names is no correct", temp.size(), 3);
        temp = executor.getWebElementsList(locators_retailer.specialOffersBuyButton);
        assertEquals("Number of Buy buttons is no correct", temp.size(), 3);
    }
    public void checkOrderOfOffer(){
        List<WebElement> percentage = executor.getWebElementsList(locators_retailer.specialOffersPercentageSaving);
        assertTrue("Order Of offer 1-2 are incorrect", Integer.parseInt(executor.getText(percentage.get(0)).replace("% OFF",""))>=Integer.parseInt(executor.getText(percentage.get(1)).replace("% OFF","")));
        assertTrue("Order Of offer 2-3 are incorrect", Integer.parseInt(executor.getText(percentage.get(1)).replace("% OFF",""))>=Integer.parseInt(executor.getText(percentage.get(2)).replace("% OFF","")));
    }

    public void changeCategory(){
        executor.selectOptionByValue(locators_retailer.specialOffersSelectCategory,"Womens Dresses");
    }

    String firstRetailer;
    public void useNavigationComponent(String direction){
        firstRetailer = executor.getText(locators_retailer.specialOffersProductTitle);
        if(direction.equals("right")){
            executor.click(locators_retailer.specialOffersArrowRight);
        }else if(direction.equals("left")){
            executor.click(locators_retailer.specialOffersArrowLeft);
        }
    }

    public void checkIfOfferWereChanged(){
        assertNotEquals("First offer does not change", firstRetailer, executor.getText(locators_retailer.specialOffersProductTitle));
    }

    // RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - category block
    public void checkVisibilityOfCategoriesBlock(boolean isVisible) throws InterruptedException {
        Thread.sleep(4000);
        if(isVisible){
            assertTrue("Category block is no visible", executor.is_element_present(locators_retailer.productCategoriesContainer));
        }else if(!isVisible){
            Assert.assertFalse("Category block is visible but should not", executor.is_element_present(locators_retailer.productCategoriesContainer));
        }
    }

    public void checkContentOfCategoriesBlock() throws InterruptedException {
        List<WebElement> mainCategories = executor.getWebElementsList(locators_retailer.productCategoriesMainCategories);
        List<WebElement> subCategories = executor.getWebElementsList(locators_retailer.productCategoriesSubCategories);
        int categoriesExpand = mainCategories.size();
        int subCategoriesExpand = subCategories.size();
        //it must be checked few possibilities
        if(mainCategories.size()==1){
            assertTrue("There is more then three of subcategories", subCategories.size()<=3);
            Assert.assertFalse("See more button should be not visible", executor.is_element_present(locators_retailer.productCategoriesSeeMoreButton));
        }else if(mainCategories.size()==2){
            assertTrue("There is more then six of subcategories", subCategories.size()<=6);
            Assert.assertFalse("See more button should be not visible", executor.is_element_present(locators_retailer.productCategoriesSeeMoreButton));
        }else if(mainCategories.size()==3){
            assertTrue("There is more then nine of subcategories", subCategories.size()<=9);
            Assert.assertFalse("See more button should be not visible", executor.is_element_present(locators_retailer.productCategoriesSeeMoreButton));
        }
        //if see more button is visible it means that there is more of categories
        if(executor.is_element_present(locators_retailer.productCategoriesSeeMoreButton)){
            executor.click(locators_retailer.productCategoriesSeeMoreButton);
            Thread.sleep(1000);
            assertTrue("After expanding there should be more categories", executor.getWebElementsList(locators_retailer.productCategoriesMainCategories).size() > 3);
            assertTrue("After expanding there should be more sub categories", executor.getWebElementsList(locators_retailer.productCategoriesSubCategories).size() > 3);
            assertTrue("There is to many subcategories", subCategoriesExpand<=categoriesExpand*3);
        }
    }

    // RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - similar retailers block
    public void checkSimilarRetailersBlockVisibility(boolean specialOffersAvaliability){
        if(specialOffersAvaliability){
            assertTrue("Special offers block is no available",executor.is_element_present(locators_retailer.similarRetailersContainer));
        }else if(!specialOffersAvaliability){
            Assert.assertFalse("Special offers block is available but should not", executor.is_element_present(locators_retailer.similarRetailersContainer));
        }
    }
    
    public void checkContentOfSimilarRetailersBlock(){
        assertTrue("Similar block header is no visible", executor.is_element_present(locators_retailer.similarRetailersContainer));
        assertTrue("Any retailer card is no visible", executor.is_element_present(locators_retailer.similarRetailersBasicRetailerCard));
        executor.moveMouseToWebElement(locators_retailer.similarRetailersBasicRetailerCard);
        //assertTrue("Retailer card name is no visible", executor.is_element_present(locators_retailer.similarRetailersBasicRetailerName));
        assertTrue("Retailer card info and offers button is no visible", executor.is_element_present(locators_retailer.similarRetailersBasicInfoOffersButton));
        assertTrue("Retailer card visit retailer button is no visible", executor.is_element_present(locators_retailer.similarRetailersBasicVisitRetailerButton));
    }

    public void goToAnotherRetailerPage() throws InterruptedException {
        Thread.sleep(2000);
        executor.moveMouseToWebElement(locators_retailer.similarRetailersBasicRetailerCard);
        retailerName = executor.getText(locators_retailer.similarRetailersBasicRetailerName);
        executor.click(locators_retailer.similarRetailersBasicInfoOffersButton);
    }

    public void checkIfuserWereProperlyRedirectedToAnotherRetailerPage(){
        assertEquals("Chosen retailer page name is incorrect", executor.return_driver().getTitle().contains(retailerName + ": Offers, Vouchers and Reviews"));
    }

    public void goToExternalRetailerPage() throws InterruptedException {
        for(int i=0;i<=10;i++){
            Thread.sleep(1000);
            executor.moveMouseToWebElement(locators_retailer.similarRetailersBasicRetailerCard);
            if(executor.getText(locators_retailer.similarRetailersBasicRetailerName).equals(retailerName)){
                executor.click(locators_retailer.similarRetailersBasicVisitRetailerButton);
                break;
            }else{
                executor.click(locators_retailer.similarRetailersArrowRight);
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

    // RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - update to special offers block
    public void checkIfProperNumberOfTabsIsAvaliable(){
        assertEquals("Number of tabs is no correct", "showing 1 of 34", executor.getText(locators_retailer.specialOffersShowingOf));
    }

    public void goThroughAllTabsAndCheckSaving() throws InterruptedException {
        for(int i=0; i<34; i++){
            assertTrue("Saving is below 50%" ,Integer.parseInt(executor.getText(locators_retailer.specialOffersPercentageSaving).replace("% OFF",""))>=50);
            executor.click(locators_retailer.specialOffersArrowRight);
            Thread.sleep(200);
        }
    }

    public void clickSeeAllSpecialOffersButton(){
        executor.click(locators_retailer.specialOffersSeeAll);
    }

    public void checkIfUserIsRedirectedToOffersPage(){
        assertTrue("offers URL is incorrect", executor.return_driver().getCurrentUrl().contains("/search"));
    }


    // RETAILER PAGE ON WLS - add retailer page interface (RD-1166) - update to special offers block all product from retailer visible
    public void checkVisibilityOfAllProductsBlock(boolean isVisible){
        if(isVisible){
            assertTrue("All product from retailer block is no visible",executor.is_element_present(locators_retailer.allProductsFromRetailerContainer));
        }else if(!isVisible){
            Assert.assertFalse("All product from retailer block is visible but should not", executor.is_element_present(locators_retailer.allProductsFromRetailerContainer));
        }
    }
    String productsNumber;
    public void rememberProductsNumberDisplayedInAllProductsBlock(){
        productsNumber = executor.getText(locators_retailer.allProductsFromRetailerProductsNumber).replace(" products","");
    }

    public void clickAllProductLink(){
        executor.click(locators_retailer.allProductsFromRetailerLink);
    }

    public void checkIfUserWasRedirectedToShopPage(){
        assertTrue("Page name is incorrect", executor.return_driver().getTitle().contains("Search results"));
    }

    public void compareProductsNumberFromAllProductsBlockAndShopPage(){
        assertEquals("Points values are different", productsNumber, executor.getText(locators_search.totalRetailersNumber).replace("out of ",""));
    }

}
