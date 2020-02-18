package com.iat.storepresentation.Locators.ShopHome.IndividualProductPage;

import com.iat.storepresentation.Locators.Locator;
import com.iat.storepresentation.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 06.03.14
 * Time: 17:12
 * To change this template use File | Settings | File Templates.
 */
public class IndividualProductLocators {

    // Product individual page
    public Locator backToPreviousPageLinkProductPage = new Locator(LocatorType.XPATH, "//a[@class='back-page']");
    public Locator pageTitleProductNameProductPage = new Locator(LocatorType.XPATH, "//h3[@class='product-title']");
    public Locator productImageProductPage = new Locator(LocatorType.XPATH, "//div[@class='photo-container']");
    public Locator priceValueProductPage = new Locator(LocatorType.XPATH, "//div[@class='bestprice-label']");
    public Locator retailerLinkProductPage = new Locator(LocatorType.XPATH, "//a[@class='retailer-name']");
    public Locator ratingProductPage = new Locator(LocatorType.XPATH, "//div[@class='rating-modal']");
    public Locator buyFromStoreButtonProductPage = new Locator(LocatorType.XPATH, "//a[@class='bestprice-buy branded']//div[@class='vertical-center']");
    public Locator epointsValueProductPage = new Locator(LocatorType.XPATH, "//span[@class='epoints-earned']");
    public Locator descripionProductPage = new Locator(LocatorType.XPATH, "//p[@class='description more']   ");
    public Locator footerProductPage = new Locator(LocatorType.XPATH, "//div[@class='footer-menu']");
    public Locator comparisonTableProductPage = new Locator(LocatorType.XPATH, "//div[@class='productcard-comparison-table']");

}
