package com.iat.adminportal.page_navigations.store_categories_manager;

import com.iat.adminportal.locators.AdminPortalHomePageLocators;
import com.iat.adminportal.locators.store_categories_manager.StoreCategoriesManagerLocators;
import com.iat.adminportal.page_navigations.AbstractPage;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 18.04.14
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class StoreCategoriesManagerNavigation extends AbstractPage {

    private StoreCategoriesManagerLocators locator_categories = new StoreCategoriesManagerLocators();
    protected AdminPortalHomePageLocators locator_home = new AdminPortalHomePageLocators();

    public StoreCategoriesManagerNavigation() {
        super("");
    }

    public void open() {
        super.open();
    }
}
