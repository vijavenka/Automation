package com.iat.adminportal.locators.store_categories_manager;

import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 18.04.14
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */
public class StoreCategoriesManagerLocators {
    public Locator basicCategoryPublishedTree = new Locator(LocatorType.XPATH, "//li//a[@class='published']");
    public Locator basicCategoryUnpublishedTree = new Locator(LocatorType.XPATH, "//li//a[@class='unpublished']");
    //menu locators
    public Locator viewMenuOption = new Locator(LocatorType.XPATH, "//a[@rel='viewNode']");
    public Locator editMenuOption = new Locator(LocatorType.XPATH, "//a[@rel='editNode']");
    public Locator removeMenuOption = new Locator(LocatorType.XPATH, "//a[@rel='removeNode']");
    public Locator unpublishMenuOption = new Locator(LocatorType.XPATH, "//a[@rel='unpublishNode']");
    public Locator addChildCategoryMenuOption = new Locator(LocatorType.XPATH, "//a[@rel='addNode']");

    public Locator plusMinusTreeIcon = new Locator(LocatorType.XPATH, "//ins[@class='jstree-icon']");

    //add category modal window
    public Locator typeDDL = new Locator(LocatorType.ID, "type");    //value=: Home_page/Department/Category
    public Locator title = new Locator(LocatorType.ID, "title");
    public Locator displayTitle = new Locator(LocatorType.ID, "displayTitle");
    public Locator contentQuery = new Locator(LocatorType.ID, "contentQuery");
    public Locator tags = new Locator(LocatorType.XPATH, "//input[@class='select2-input text']");
    public Locator relateCategoriesButton = new Locator(LocatorType.XPATH, "//a[@class='btn']");
    public Locator curatedInterface = new Locator(LocatorType.ID, "contentQuery");
}
