package com.iat.storepresentation.Locators.DepartmentCategory;

import com.iat.storepresentation.Locators.Locator;
import com.iat.storepresentation.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.03.14
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
public class DepartmentCategoryLocators {
//Departmen category Locators
    public Locator departmentCategoryPageTitle = new Locator(LocatorType.XPATH, "//h1[@class='page-title']");
    public Locator departmentCategorySubCategoryName = new Locator(LocatorType.XPATH, "//h4[@class='title']/a");
// Bradcrumbs Locators
    public Locator searchBreadcrumbLevel1 = new Locator(LocatorType.XPATH, "//div[@class='category-breadcrumbs']//li[@class='parent']//a");
    public Locator searchBreadcrumbLevel2 = new Locator(LocatorType.XPATH, "//div[@class='category-breadcrumbs']//li[2][@class='parent']//a");
    public Locator searchBreadcrumbLevel3 = new Locator(LocatorType.XPATH, "//div[@class='category-breadcrumbs']//li//h5");
// Category page Locators
    public Locator catgoryPageTitle = new Locator(LocatorType.XPATH, "//h1[@class='page-title update-keyword']");
}
