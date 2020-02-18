package com.iat.adminportal.locators.member_manager;

import com.iat.adminportal.locators.Locator;
import com.iat.adminportal.locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 26.03.14
 * Time: 08:48
 * To change this template use File | Settings | File Templates.
 */
public class MemberManagerLocators {

    public Locator pageTitle = new Locator(LocatorType.XPATH, "//section[@id='content']//h1");
    // member search
    public Locator searchTypeDDL = new Locator(LocatorType.NAME, "type");
    public Locator searchField = new Locator(LocatorType.XPATH, "//input[@type='text']");
    public Locator searchButton = new Locator(LocatorType.XPATH, "//button[@type='submit']");
    //tabs
    public Locator resultsNumberInfo = new Locator(LocatorType.XPATH, "//div[@class='dataTables_info']");
    public Locator personalDetailsTab = new Locator(LocatorType.XPATH, "//li[@data-tab='personal']");
    public Locator pointsHistoryTab = new Locator(LocatorType.XPATH, "//li[@data-tab='points']");
    public Locator redemptionHistoryTab = new Locator(LocatorType.XPATH, "//li[@data-tab='redemptions']");
    public Locator clickoutHistoryTab = new Locator(LocatorType.XPATH, "//li[@data-tab='clickout']");
    //results personal details
    public Locator personalSectionBasicResult = new Locator(LocatorType.XPATH, "//fieldset//section//div");
    public Locator personalSectionDeactivateCheckbox = new Locator(LocatorType.XPATH, "//div[@id='uniform-userDeactivated']//input");
    //results clickouts history
    public Locator displyedNumberDDL = new Locator(LocatorType.XPATH, "//label//select");
    public Locator basicContententTableRowLocator = new Locator(LocatorType.XPATH, "//tr");

    public Locator returnRowContentBasicLocator(int whichRow) {
        Locator basicContententTableLocator = new Locator(LocatorType.XPATH, "//tr[" + Integer.toString(whichRow) + "]//td"); // will be indexed to find proper content
        return basicContententTableLocator;
    }

    //results redemptions history
    public Locator basicContententTableRowLocatorRH = new Locator(LocatorType.XPATH, "//tr");

    public Locator returnRowContentBasicLocatorRH(int whichRow) {
        Locator basicContententTableLocator = new Locator(LocatorType.XPATH, "//tr[" + Integer.toString(whichRow) + "]//td"); // will be indexed to find proper content
        return basicContententTableLocator;
    }

    //results points history
    public Locator confirmedEpoints = new Locator(LocatorType.XPATH, "//fieldset//section[1]//div");
    public Locator pendingEpoints = new Locator(LocatorType.XPATH, "//fieldset//section[2]//div");
    public Locator ePointsToDate = new Locator(LocatorType.XPATH, "//fieldset//section[3]//div");

    public Locator returnRowContentBasicLocatorPH(int whichRow) {
        Locator basicContententTableLocator = new Locator(LocatorType.XPATH, "//tr[" + Integer.toString(whichRow) + "]//td"); // will be indexed to find proper content
        return basicContententTableLocator;
    }
}
