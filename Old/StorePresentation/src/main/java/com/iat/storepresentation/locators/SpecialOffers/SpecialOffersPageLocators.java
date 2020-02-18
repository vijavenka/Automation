package com.iat.storepresentation.Locators.SpecialOffers;

import com.iat.storepresentation.Locators.Locator;
import com.iat.storepresentation.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.03.14
 * Time: 09:42
 * To change this template use File | Settings | File Templates.
 */
public class SpecialOffersPageLocators {

    public Locator headerSpecialOffersPage = new Locator(LocatorType.XPATH, "//h1[contains(text(),'Special Offers')]");
    // Special offers cards Locators
    public Locator cardTitleSpecialOffersPage = new Locator(LocatorType.XPATH, "//h3[@class='title']");
    public Locator cardDescriptionSpecialOffersPage = new Locator(LocatorType.XPATH, "//div[@class='description']");
    public Locator cardExpiryDateSpecialOffersPage = new Locator(LocatorType.XPATH, "//footer[@class='muted']");
    public Locator cardViewButtonSpecialOffersPage = new Locator(LocatorType.XPATH, "//a[@class='btn view-button branded']");
    public Locator cardImageSpecialOffersPage = new Locator(LocatorType.XPATH, "//a[@class='offer-image']");
    // filters Locators
    public Locator filterContainerSpecialOffersPage = new Locator(LocatorType.XPATH, "//div[@class='special-offers-filters']");
    public String[] savigOptions = {"76-100", "51-75.999", "25-50.999"};
    public Locator percentageSavingDDLSpecialOffersPage = new Locator(LocatorType.XPATH, "//select[@class='discount-dropdown']");//value 76-100, 51-75.999, 25-50.999
    public String[] departmentsSpecialOffersPage = {"Books","Fashion","Home & Garden","Health & Beauty","Baby & Child","Sports & Outdoors","Computer & Office","Music, Film & Gaming","Toys & Games","Electronics"};
    public Locator departmentDDlSpecialOffersPage = new Locator(LocatorType.XPATH, "//select[@class='departments-dropdown']");
    public Locator categorySpecialOffersPage = new Locator(LocatorType.XPATH, "//div[@class='categories-list']//label");
    public Locator showMeButtonSpecialOffersPage = new Locator(LocatorType.XPATH, "//a[contains(text(),'Show me')]");

}
