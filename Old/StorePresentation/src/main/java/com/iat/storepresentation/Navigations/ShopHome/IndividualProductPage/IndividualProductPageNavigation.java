package com.iat.storepresentation.Navigations.ShopHome.IndividualProductPage;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.ShopHome.HomePageLocators;
import com.iat.storepresentation.Locators.ShopHome.IndividualProductPage.IndividualProductLocators;
import com.iat.storepresentation.Navigations.AbstractPage;
import com.iat.storepresentation.Navigations.ShopHome.HomePageNavigation;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 06.03.14
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class IndividualProductPageNavigation extends AbstractPage{

    private IndividualProductLocators locators_indProductPage = new IndividualProductLocators();
    private HomePageLocators locators_home = new HomePageLocators();
    private HomePageNavigation homeNavi = new HomePageNavigation(executor);


    public IndividualProductPageNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public void clickBackToPreviousPageButton(){
        executor.click(locators_indProductPage.backToPreviousPageLinkProductPage);
    }

    String retailerName;
    public void openProductPageOfChosenProduct() throws InterruptedException {
        List<WebElement> retailerNames = executor.getWebElementsList(locators_home.basicProductFromRetailer);
        List<WebElement> productTitles = executor.getWebElementsList(locators_home.basicProductProductTitle);
        productName = executor.getText(productTitles.get(randomProduct));
        if(executor.getText(retailerNames.get(randomProduct)).contains("retailers")){
            executor.click(productTitles.get(randomProduct));
            if(executor.is_element_present(locators_home.basicMainProductTitleSplit)){
                executor.click(locators_home.basicMainProductTitleSplit);
            }
            Thread.sleep(1000);
            assertTrue("Individual product page was not opened", executor.return_driver().getTitle().contains("shop"));
            retailerName = executor.getText(locators_indProductPage.retailerLinkProductPage);
            System.out.println(retailerName+"1");

        }else{
            retailerName = executor.getText(retailerNames.get(randomProduct)).replace("from ","");
            executor.click(productTitles.get(randomProduct));
            if(executor.is_element_present(locators_home.basicMainProductTitleSplit)){
                executor.click(locators_home.basicMainProductTitleSplit);
            }
            Thread.sleep(1000);
            System.out.println(retailerName+"2");
            assertTrue("Proper product page was not opened", executor.return_driver().getTitle().contains(productName) && executor.return_driver().getCurrentUrl().contains("/product"));
        }
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - product individual page compare with product card
    String productPrise;
    // TODO epoints reward is no mandatory
    String productEpointsReward;
    String productName;
    int randomProduct = executor.return_random_value(15);
    public void saveNamePriceEpointsOfChosenProduct(){
        productPrise = executor.getText(executor.getWebElementsList(locators_home.basicProductNewPrice).get(randomProduct));
        productEpointsReward = executor.getText(executor.getWebElementsList(locators_home.basicProductEpointsEarned).get(randomProduct));
        productName = executor.getText(executor.getWebElementsList(locators_home.basicProductProductTitle).get(randomProduct));
    }

    public void checkVisibilityOfAllProductPageElements(){
        assertTrue("Back to previous page link is no visible " + productName, executor.is_element_present(locators_indProductPage.backToPreviousPageLinkProductPage));
        assertTrue("Product name is no visible " + productName, executor.is_element_present(locators_indProductPage.pageTitleProductNameProductPage));
        assertTrue("Product image is no visible " + productName, executor.is_element_present(locators_indProductPage.productImageProductPage));
        assertTrue("Price is no visible " + productName, executor.is_element_present(locators_indProductPage.priceValueProductPage));
        assertTrue("Retailer link is no visible " + productName, executor.is_element_present(locators_indProductPage.retailerLinkProductPage));
        //Product rating is no required on WLS
        //assertTrue("Product rating is no visible " + productName, executor.is_element_present(locators_indProductPage.ratingProductPage));
        assertTrue("Buy button is no visible " + productName, executor.is_element_present(locators_indProductPage.buyFromStoreButtonProductPage));
        // epoints values are not mandatory
        assertTrue("Epoints value is no visible " + productName, executor.is_element_present(locators_indProductPage.epointsValueProductPage));
        assertTrue("Description is no visible " + productName, executor.is_element_present(locators_indProductPage.descripionProductPage));
        assertTrue("Footer no visible " + productName, executor.is_element_present(locators_indProductPage.footerProductPage));
        assertTrue("Comparison table is no visible " + productName, executor.is_element_present(locators_indProductPage.comparisonTableProductPage));
    }

    public void compareIndividualProductValuesFromProductCard(){
        assertEquals("Names are not the same " + productName, productName , executor.getText(locators_indProductPage.pageTitleProductNameProductPage));
        assertTrue("Prises are not the same " + productName, executor.getText(locators_indProductPage.priceValueProductPage).contains(productPrise));
        assertEquals("Epoints values are not the same " + productName, productEpointsReward , executor.getText(locators_indProductPage.epointsValueProductPage).replace(",",""));
        if(retailerName.contains("retailers")){
            retailerName = executor.getText(locators_indProductPage.retailerLinkProductPage);
        }else{
            assertEquals("Retailer names are not the same" + productName, retailerName , executor.getText(locators_indProductPage.retailerLinkProductPage));
        }
    }

    // FRONT END REFACTOR - add epoints search solution to WLS Home pages (Rd-2112) - clickout from individual product page
    public void clickBuyButtonOnIndividualproductPage(){
        executor.click(locators_indProductPage.buyFromStoreButtonProductPage);
        executor.handleMultipleWindows("Purchase from " + retailerName + " | epoints");
    }

    public void checkIfClickoutWasReported(String ifSigned,String Email) throws SQLException, InterruptedException {
        homeNavi.checkIfClickoutWasReported(ifSigned, Email, retailerName);
    }
}
