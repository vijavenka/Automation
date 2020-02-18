package com.iat.b2btests.locators;

public class WizardStep0Locators implements IPageLocators {
	
	public Locator pageTitle_h3 = new Locator(LocatorType.XPATH, "//h3[@class='page-title']");
	public Locator step0Info_div = new Locator(LocatorType.XPATH, "//div[@class='well']");
	public Locator step0Caption_div = new Locator(LocatorType.XPATH, "//div[@class='caption']");
	public Locator step0SiteName_div = new Locator(LocatorType.XPATH, "//div[contains(@class, 'fuelux')]/form/div[1]");
	public Locator step0Subdomain_input = new Locator(LocatorType.XPATH, "//input[contains(@class, 'wls-input-hostname')]");
	
	public Locator step0ShopName_input = new Locator(LocatorType.XPATH, "//input[contains(@class, 'wls-input-shopname')]");
	public Locator step0HostName_input = new Locator(LocatorType.XPATH, "//input[contains(@class, 'wls-input-hostname')]");
	
	public Locator step0Continue_button = new Locator(LocatorType.XPATH, "//span[contains(@class, 'wls-save-and-continue')]");
	
	public Locator step0Warning_div = new Locator(LocatorType.XPATH, "//div[contains(@class, 'alert-error')]");
	
}
