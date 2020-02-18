package com.iat.ePoints.Locators.Get;

import com.iat.ePoints.Locators.IPageLocators;
import com.iat.ePoints.Locators.Locator;
import com.iat.ePoints.Locators.LocatorType;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 18.06.13
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
public class GetLandingPageLocators implements IPageLocators {

    public Locator earnEpointsMenu = new Locator(LocatorType.XPATH, "//a[text()='Get']");
    public Locator getEpointsMenu = new Locator(LocatorType.XPATH, "//a[text()='Get']");
    public Locator shopsAZsubmenu = new Locator(LocatorType.XPATH, "//a[text()='Stores A-Z']");
    public Locator priceComparisonSubmenu = new Locator(LocatorType.XPATH, "//a[contains(text(), 'Price comparison')]");
    public Locator vouchersSubmenu = new Locator(LocatorType.XPATH, "//a[contains(text(), 'Vouchers')]");
    public Locator specialOffersSubmenu = new Locator(LocatorType.XPATH, "//a[contains(text(), 'Special offers')]");
    public Locator dailyDealsSubmenu = new Locator(LocatorType.XPATH, "//a[contains(text(), 'Daily deals')]");
//Landing Page
//How to get epoints block
    public Locator sectionZeroTitle = new Locator(LocatorType.XPATH, "//div[@class='section section-1']//h1[contains(text(),'how to get epoints')]");
    public Locator shopingInfo = new Locator(LocatorType.XPATH, "//div[@class='section section-1']//div[@class='info info-1']//h2[contains(text(),'Shopping')]");
    public Locator doingInfo = new Locator(LocatorType.XPATH, "//div[@class='section section-1']//div[@class='info info-2']//h2[contains(text(),'Doing')]");
    public Locator lovingInfo = new Locator(LocatorType.XPATH, "//div[@class='section section-1']//div[@class='info info-3']//h2[contains(text(),'Loving')]");
//From more than 2,00 online stores block
    public Locator firstSectionArrowDown = new Locator(LocatorType.XPATH, "//div[@class='section section-1']//div[@class='scroll-down']");
    public Locator sectionOneTitle = new Locator(LocatorType.XPATH, "//div[@class='section section-2']//h2[contains(text(),'from more than 2,000 online stores')]");
    public Locator sectionOneSearcherTextfield = new Locator(LocatorType.XPATH, "//input[@id='inputStore']");
    public Locator sectionOneSearchButton = new Locator(LocatorType.XPATH, "//button[@class='btn btn-yellow store-search']");
    //May change
    public Locator retailerGetPageBasic = new Locator(LocatorType.XPATH, "//a[@class='retailerLink']");
    public Locator azOfaLLStoresButton = new Locator(LocatorType.XPATH, "//div[@class='section section-2']//a[@href='/retailers']");
//Free epoints block
    public Locator secondSectionArrowDown = new Locator(LocatorType.XPATH, "//div[@class='section section-2']//div[@class='scroll-down']");
    public Locator freeEpointsLogo = new Locator(LocatorType.XPATH, "//div[contains(text(),'FREE')]");
    public Locator registeringViaEpoints = new Locator(LocatorType.XPATH, "//div[@class='section section-3']//a[contains(text(),'Registering via epoints')]");
    public Locator completingYourProfile = new Locator(LocatorType.XPATH, "//div[@class='section section-3']//a[contains(text(),'Completing your profile')]");
    public Locator likingOnFacebook = new Locator(LocatorType.XPATH, "//div[@class='section section-3']//a[contains(text(),'Liking on facebook')]");
    public Locator followingUsOnTweeter = new Locator(LocatorType.XPATH, "//div[@class='section section-3']//a[contains(text(),'Following us on Twitter')]");
    public Locator visitingEpointsEveryDay = new Locator(LocatorType.XPATH, "//div[@class='section section-3']//a[contains(text(),'Signing in to epoints every day')]");
    public Locator recommendingEpoints = new Locator(LocatorType.XPATH, "//div[@class='section section-3']//a[contains(text(),'Recommending epoints')]");
    public Locator signingEveryDay = new Locator(LocatorType.XPATH, "//a[contains(text(),'Signing in to epoints every day')]");
//For doing what you do already block
    public Locator thirdSectionArrowDown = new Locator(LocatorType.XPATH, "//div[@class='section section-3']//div[@class='scroll-down']");
    public Locator sectionThreeTitle = new Locator(LocatorType.XPATH, "//div[@class='section section-4']//h2[contains(text(),'for doing what you do already')]");
    public Locator activityOneVideos = new Locator(LocatorType.XPATH, "//div[@class='activity activity-1']//h3[contains(text(),'Watch videos')]");
    public Locator activityTwoCommenting = new Locator(LocatorType.XPATH, "//div[@class='activity activity-2']//h3[contains(text(),'Commenting')]");
    public Locator activityThreeFollowing = new Locator(LocatorType.XPATH, "//div[@class='activity activity-3']//h3[contains(text(),'Like and follow')]");
    public Locator activityFourRegistering = new Locator(LocatorType.XPATH, "//div[@class='activity activity-4']//h3[contains(text(),'Registering')]");
    public Locator activityFiveDailyVisits = new Locator(LocatorType.XPATH, "//div[@class='activity activity-5']//h3[contains(text(),'Daily visits')]");
//Top tips for you block
    public Locator fourthSectionArrowDown = new Locator(LocatorType.XPATH, "//div[@class='section section-4']//div[@class='scroll-down']");
    public Locator sectionFourTitle = new Locator(LocatorType.XPATH, "//div[@class='section section-5']//h2[contains(text(),'top tips for you')]");
    public Locator boxOneStrongText = new Locator(LocatorType.XPATH, "//div[@class='box box-1']//strong[contains(text(),'Shop via epoints.')]");
    public Locator boxTwoStrongText = new Locator(LocatorType.XPATH, "//div[@class='box box-2']//strong[contains(text(),'epoints logo')]");
    public Locator boxThreeStrongText = new Locator(LocatorType.XPATH, "//div[@class='box box-3']//strong[contains(text(),'Keep in touch.')]");
    public Locator boxFourStrongText = new Locator(LocatorType.XPATH, "//div[@class='box box-4']//strong[contains(text(),'x2, x3, x4...signs.')]");
    public Locator boxFiveStrongText = new Locator(LocatorType.XPATH, "//div[@class='box box-5']//strong[contains(text(),'free epoints')]");
    public Locator boxSixStrongText = new Locator(LocatorType.XPATH, "//div[@class='box box-contact']//h3[contains(text(),'Your feedback')]");
    public Locator contactUsButton = new Locator(LocatorType.XPATH, "//div[@class='box box-contact']//a[@class='btn btn-yellow']");
//Sections Locators
    public Locator sectionOne = new Locator(LocatorType.XPATH, "//div[@class='section section-1']");
    public Locator sectionTwo = new Locator(LocatorType.XPATH, "//div[@class='section section-2']");
    public Locator sectionThree = new Locator(LocatorType.XPATH, "//div[@class='section section-3']");
    public Locator sectionFour = new Locator(LocatorType.XPATH, "//div[@class='section section-4']");
    public Locator sectionFive = new Locator(LocatorType.XPATH, "//div[@class='section section-5']");
}