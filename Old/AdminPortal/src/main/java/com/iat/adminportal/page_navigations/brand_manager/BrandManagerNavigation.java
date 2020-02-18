package com.iat.adminportal.page_navigations.brand_manager;

import com.iat.adminportal.locators.brand_manager.BrandManagerLocators;
import com.iat.adminportal.page_navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 14.03.14
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
public class BrandManagerNavigation extends AbstractPage {


    private BrandManagerLocators locators_brand = new BrandManagerLocators();

    public BrandManagerNavigation() {
        super("");
    }

    public void open() {
        super.open();
    }

    // BRAND MANAGER - extend search functionality to include synonyms (RD-1549).
    public void checkIfSynonymsColumnIsAvailable() {
        executor.is_element_present(locators_brand.resultSynonimColumnBrandManager);
    }

    public void typePhraseIntoSearch(String phrase) {
        executor.send_keys(locators_brand.searcghFieldBrandManager, phrase);
    }

    public void clickSearchButton() {
        executor.click(locators_brand.searchButtonBrandManager);
    }

    public void checkIfWereFoundProperBrands(String phrase) {
        List<WebElement> results = executor.get_elements(locators_brand.resultNameBrandManager);
        for (WebElement result : results) {
            assertTrue("Result name is not connected with given phrase " + phrase + " found " + executor.getText(result), executor.getText(result).contains(phrase));
        }
    }


}