package com.iat.storepresentation.Navigations.ShopHome;

import com.iat.storepresentation.EnvironmentVariables;
import com.iat.storepresentation.Database.JDBC;
import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.ShopHome.HomePageLocators;
import com.iat.storepresentation.Locators.ShopHome.IndividualProductPage.IndividualProductLocators;
import com.iat.storepresentation.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 06.03.14
 * Time: 14:29
 * To change this template use File | Settings | File Templates.
 */
public class HomePageNavigation extends AbstractPage {

    private HomePageLocators locators_home = new HomePageLocators();
    private IndividualProductLocators locators_indProductPage = new IndividualProductLocators();
    private EnvironmentVariables envVariables = new EnvironmentVariables();


    public HomePageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - content of product cards
    public void viewAllProductOnCurrentPageAndCheckTheirContent(){
        List<WebElement> productCard = executor.getWebElementsList(locators_home.basicProductContainer);
        List<WebElement> productPicture = executor.getWebElementsList(locators_home.basicProductImage);
        // TODO ep rewards are not mandatory
        //List<WebElement> productEpReward = executor.getWebElementsList(locators_shop.basicProductEpointsEarned);
        List<WebElement> productTitle = executor.getWebElementsList(locators_home.basicProductProductTitle);
        List<WebElement> productPrice = executor.getWebElementsList(locators_home.basicProductNewPrice);
        List<WebElement> productRetailer = executor.getWebElementsList(locators_home.basicProductFromRetailer);
        List<WebElement> productBuyButton = executor.getWebElementsList(locators_home.basicProductBuyButton);
        /*System.out.println(productCard.size());
        System.out.println(productPicture.size());
        System.out.println(productTitle.size());
        System.out.println(productPrice.size());
        System.out.println(productRetailer.size());
        System.out.println(productBuyButton.size());*/
        assertTrue("Product element number should be the same", (productCard.size()/*+productPicture.size()*//*+productEpReward.size()*/+productTitle.size()+productPrice.size()+productRetailer.size()+productBuyButton.size())/productCard.size() == 5);
        for(int i=0; i<productCard.size(); i++){
            //assertTrue("Picture is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(productPicture.get(i)));
            // assertTrue("Ep reward is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(productEpReward.get(i)));
            assertTrue("Title is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(productTitle.get(i)));
            assertTrue("Price is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(productPrice.get(i)));
            assertTrue("Retailer name is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(productRetailer.get(i)));
            assertTrue("Buy button is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(productBuyButton.get(i)));
        }
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - split view
    public void checkContentOfEachProductSplitView() throws InterruptedException {
        List<WebElement> productCard = executor.getWebElementsList(locators_home.basicProductContainer);
        List<WebElement> productEpReward = executor.getWebElementsList(locators_home.basicProductEpointsEarned);
        List<WebElement> productTitle = executor.getWebElementsList(locators_home.basicProductProductTitle);
        List<WebElement> productPrice = executor.getWebElementsList(locators_home.basicProductNewPrice);
        List<WebElement> productRetailer = executor.getWebElementsList(locators_home.basicProductFromRetailer);

        executor.click(productTitle.get(0));
        Thread.sleep(2000);
        for(int i=0; i<productCard.size()-1; i++){
            assertTrue("Split view is no available for "+ productTitle.get(i), executor.is_element_present(locators_home.productInfoCardContainerSplit));
            executor.click(locators_home.informationTabSplit);
            // compare values from product card
            assertEquals("Product names are not equals for " + executor.getText(productTitle.get(i)), executor.getText(productTitle.get(i)), executor.getText(locators_home.basicMainProductTitleSplit));

            // TODO ep rewards are not mandatory
            //assertEquals("Ep rewards are not equals for "+ executor.getText(productTitle.get(i)), executor.getText(productEpReward.get(i)), "+" + Integer.toString(highestValue));
            assertEquals("Product prices are not equals for "+ executor.getText(productPrice.get(i)), executor.getText(productPrice.get(i)).replace(" ",""), executor.getText(locators_home.priceInformationTabSplit));
            //below condition means that product has more than one retailer and we cannot compare directly retailer names
            if(!executor.getText(productRetailer.get(i)).contains("retailers")){
                assertEquals("Product retailers are not equals for "+ executor.getText(productRetailer.get(i)), executor.getText(productRetailer.get(i)), "from " + executor.getText(locators_home.retailerNameInformationTabSplit));
            }

            //static elements
            assertTrue("Close button is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_home.closeButtonSplit));
            assertTrue("Product picture is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_home.productImageSplit));
            assertTrue("Description is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_home.productDescriptionInformationTabSplit));
            //assertTrue("Rating is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_home.ratingInformationTabSplit));
            assertTrue("Delivery information is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_home.delivaryInformationTabSplit));
            assertTrue("Buy button is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_home.buyFromStoreButtonSplit));
            assertTrue("Facebook icon is no visible for " + executor.getText(productTitle.get(i)), executor.is_element_present(locators_home.facebookIconSplit));
            assertTrue("Twitter icon is no visible for " + executor.getText(productTitle.get(i)),executor.is_element_present(locators_home.twitterIconSplit));
            assertTrue("G+ icon is no visible for " + executor.getText(productTitle.get(i)),executor.is_element_present(locators_home.googlePlusIconSplit));
            executor.click(locators_home.naviNextSplit);
            if(i>5){
                break;
            }
        }
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - clickout
    String productRetailerName = null;
    public void clickBuyButtonOfChosenMerchant(boolean ifOnSplit) throws InterruptedException {
        List<WebElement> buyButtons = executor.getWebElementsList(locators_home.basicProductBuyButton);
        System.out.println(buyButtons.size());
        List<WebElement> retailerNames = executor.getWebElementsList(locators_home.basicProductFromRetailer);
        int random = executor.return_random_value(buyButtons.size());
        if(executor.getText(retailerNames.get(random)).contains("retailers")){
            List<WebElement> productNames = executor.getWebElementsList(locators_home.basicProductProductTitle);
            executor.click(productNames.get(random));
            productRetailerName = executor.getText(locators_indProductPage.retailerLinkProductPage);
            executor.return_driver().navigate().back();
        }else{
            productRetailerName = executor.getText(retailerNames.get(random)).replace("from ","");
        }
        if(!ifOnSplit){
            executor.click(buyButtons.get(random));
        }else{
            List<WebElement> productNames = executor.getWebElementsList(locators_home.basicProductProductTitle);
            executor.click(productNames.get(random));
            executor.click(locators_home.buyFromStoreButtonSplit);
        }
        executor.handleMultipleWindows("Purchase from " + productRetailerName);
    }

    public void checkIfClickoutWasReported(String ifSigned, String Email, String retailer) throws InterruptedException, SQLException {
        // this function body is a copy of function from transition page navigation class, it is only a little bit changed
        String CurrentDate = executor.returnCurrentDate();
        String retailerName;
        if(retailer.equals("null")){
            retailerName = productRetailerName;
        }else{
            retailerName = retailer;
        }
        Thread.sleep(10000);
        JDBC jdbc = new JDBC("affiliate_manager");
        assertTrue("In db there is no clickout with current date and hour, but should be", jdbc.return_proper_db_value("SELECT createdDate FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1).contains(CurrentDate));
        assertEquals("Clickout has improper merchant name", jdbc.return_proper_db_value("SELECT merchant FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), retailerName);
        assertEquals("Clickout has improper merchantId", jdbc.return_proper_db_value("SELECT merchantId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT id FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1));
        assertEquals("Clickout has improper publisher", jdbc.return_proper_db_value("SELECT publisher FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), envVariables.StoreName);
        //assertEquals("Clickout has improper publisherId", jdbc.return_proper_db_value("SELECT publisherId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), "52e78e50e4b0fa73f3603da9");
        assertEquals("Clickout has improper affiliate networkId", jdbc.return_proper_db_value("SELECT affiliateNetworkId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1));
        assertEquals("Clickout has improper affiliate network name", jdbc.return_proper_db_value("SELECT affiliateNetwork FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1), jdbc.return_proper_db_value("SELECT name FROM admin_ui.AffiliateNetwork WHERE id='" + jdbc.return_proper_db_value("SELECT network_id FROM admin_ui.Merchant WHERE name='" + retailerName + "'", 1) + "'", 1));
        if (ifSigned.equals("user_sign_in")) {
            String temp = jdbc.return_proper_db_value("SELECT userId FROM affiliate_manager.Clickout WHERE updateStatusDate LIKE '%" + CurrentDate + "%' ORDER BY createdDate DESC", 1);
            jdbc = new JDBC("points_manager");
            assertEquals("Clickout has improper UserId", temp, jdbc.return_proper_db_value("SELECT userKey FROM points_manager.User WHERE email = '"+Email+"'",1));
        }
        jdbc.close();
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - product individual page compare with product card

    public void checkIfUserWasCorrectlyRedirectedToHomePage(){
        assertEquals("User was redirected to wrong page", envVariables.baseUrl, executor.return_driver().getCurrentUrl());
    }
}
