package com.iat.ePoints.Locators.StaticPages;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */
public class AboutUsLocators implements IPageLocators {
    public Locator aboutPageCheck = new Locator(LocatorType.XPATH, "//h1[@class='page-title update-keyword']");
    public Locator aboutEpointsReference = new Locator(LocatorType.XPATH, "//a[@href='/about']");
//Four titles main in about epoints page
    public Locator rewardsTitle = new Locator(LocatorType.XPATH, "//div[@id='rewards']");
    public Locator choicesTile = new Locator(LocatorType.XPATH, "//div[@id='choices']");
    public Locator valueTitle = new Locator(LocatorType.XPATH, "//div[@id='value']");
    public Locator missionTitle = new Locator(LocatorType.XPATH, "//div[@id='mission']");

    public Locator getInTouchButton = new Locator(LocatorType.XPATH, "//a[@class='btn btn-yellow tell-us']");
    public Locator backToTopReference = new Locator(LocatorType.XPATH, "//a[@class='scroll-up link']");

    public Locator retailerNameBasic = new Locator(LocatorType.XPATH, "//div[@id='retailersContainer']//li//div[@class='retailerCard-hover']");


}