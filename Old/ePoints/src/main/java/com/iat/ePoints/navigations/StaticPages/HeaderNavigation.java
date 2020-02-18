package com.iat.ePoints.Navigations.StaticPages;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import static org.junit.Assert.assertTrue;

public class HeaderNavigation extends AbstractPage {

    private HeaderLocators locators_header = new HeaderLocators();

    public HeaderNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }


    public void checkAboutLinkInPageHeader(){
        assertTrue("About epoints link in header is not available", executor.is_element_present(locators_header.aboutEpoints));
    }

    public void clickInAboutEpointsHeaderLink(){
        executor.click(locators_header.aboutEpoints);
    }
}
