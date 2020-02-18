package com.iat.adminportal.locators.brand_manager;

import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 14.03.14
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
public class BrandManagerLocators {
    public Locator searcghFieldBrandManager = new Locator(LocatorType.ID, "searchkeyword");
    public Locator searchButtonBrandManager = new Locator(LocatorType.ID, "searchbtn");
    public Locator resultNameBrandManager = new Locator(LocatorType.XPATH, "//td[@class='name sorting_1']//a[@class='brandview']");
    public Locator resultSynonimBrandManager = new Locator(LocatorType.XPATH, "//td[@class='']");
    public Locator resultSynonimColumnBrandManager = new Locator(LocatorType.XPATH, "//th[@aria-label='Synonyms']");
}
