package com.iat.b2btests.locators;

public class WizardGenericStepLocators implements IPageLocators {
	
	public Locator stepTitle_h3 = new Locator(LocatorType.XPATH, "//h3[@class='page-title']");
	public Locator shopName_span = new Locator(LocatorType.XPATH, "//span[contains(@class, 'wls-shop-name')]");
	public Locator shopUrl_span = new Locator(LocatorType.XPATH, "//span[contains(@class, 'wls-shop-domain')]");
	public Locator stepInfo_div = new Locator(LocatorType.XPATH, "//div[@class='well']");
	
	public Locator steps_div = new Locator(LocatorType.XPATH, "//div[@class='navbar-inner']");
	public Locator steps1Active_div = new Locator(LocatorType.XPATH, "//li[@data-stepnumber='1' and contains(@class, 'active')]");
	public Locator steps2Active_div = new Locator(LocatorType.XPATH, "//li[@data-stepnumber='2' and contains(@class, 'active')]");
	public Locator steps3Active_div = new Locator(LocatorType.XPATH, "//li[@data-stepnumber='3' and contains(@class, 'active')]");
	public Locator steps4Active_div = new Locator(LocatorType.XPATH, "//li[@data-stepnumber='4' and contains(@class, 'active')]");
	public Locator steps5Active_div = new Locator(LocatorType.XPATH, "//li[@data-stepnumber='5' and contains(@class, 'active')]");
	public Locator steps1_link = new Locator(LocatorType.XPATH, "//li[@data-stepnumber='1']/a");
	public Locator steps2_link = new Locator(LocatorType.XPATH, "//li[@data-stepnumber='2']/a");
	public Locator steps3_link = new Locator(LocatorType.XPATH, "//li[@data-stepnumber='3']/a");
	public Locator steps4_link = new Locator(LocatorType.XPATH, "//li[@data-stepnumber='4']/a");
	public Locator steps5_link = new Locator(LocatorType.XPATH, "//li[@data-stepnumber='5']/a");
	
	public Locator stepDashboard_link = new Locator(LocatorType.XPATH, "//a[contains(@class, 'wls-return-to-dashboard')]");
	public Locator stepPreview_link = new Locator(LocatorType.XPATH, "//a[contains(@class, 'wls-preview')]");
	public Locator stepContinue_link = new Locator(LocatorType.XPATH, "//a[contains(@class, 'wls-save-and-continue')]");
}
