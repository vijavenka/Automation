package com.iat.ePoints.Locators.Get;

import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 29.01.14
 * Time: 09:46
 * To change this template use File | Settings | File Templates.
 */
public class GetPriceComparisonPageLocators {

// Main price comparison page Locators
    public Locator  searchTeaxtField= new Locator(LocatorType.XPATH, "//input[@class='input']");
    public Locator  searchDepartmentDDLDownArrow= new Locator(LocatorType.XPATH, "//i[@class='dropdown-icon icon-chevron-down']");
    public Locator  searchDepartmentDDL= new Locator(LocatorType.XPATH, "//li[@class='option']");
    public Locator  searchDepartmentDDLOptions = new Locator(LocatorType.XPATH, "//li[@class='option']"); //not selected
    public Locator  searchButton= new Locator(LocatorType.XPATH, " //button[contains(text(),'Search')]");

    public String[] departments = {"Books","Fashion","Home & Garden","Health & Beauty","Baby & Child","Sports & Outdoors","Computer & Office","Music, Film & Gaming","Toys & Games","Electronics"};

    public Locator  basicDepartmentNotActive= new Locator(LocatorType.XPATH, "//div[@class='category']");
    public Locator returnCategoryLocator(int categoryNumber){
        Locator  basicDepartmentLink = null;
            basicDepartmentLink = new Locator(LocatorType.XPATH, "//div[@class='mainContent grid-row categories-section']//div[@data-seo-title='"+departments[categoryNumber].toLowerCase().replace(" ","-").replace("&","and").replace(",","")+"']");
        return basicDepartmentLink;
    }
    // below locator is used only with returnCategoryLocator, maybe in future there will be better way to reach those objects

    public Locator  basicDepartmentCardActive= new Locator(LocatorType.XPATH, "//div[@class='category active']//div[@class='department-card']");
    public Locator  basicDepartmentCrdActiveName = new Locator(LocatorType.XPATH, "//div[@class='category active']//div[@class='department-card']//h4//a");

    public Locator  basicDepartmentCategoryLink= new Locator(LocatorType.XPATH, "//ul//li//a");
    public Locator  basicDepartmentCategoryLargeActive = new Locator(LocatorType.XPATH, "//div[@class='category active']//div[@class='department-card']//div[@class='show-grid']//div//h5");
    public Locator  basicDepartmentCategorySmallActive = new Locator(LocatorType.XPATH, "//div[@class='category active']//div[@class='department-card']//div[@class='show-grid']//div//ul//li");
    public Locator  basicDepartmentImage= new Locator(LocatorType.XPATH, "//div[@class='mainContent grid-row categories-section']//a[@class='category']//img");
    public Locator  basicDepartmentCardClose = new Locator(LocatorType.XPATH, "//div[@class='category active']//div[@class='department-card']//div[@class='close-button']");

}
