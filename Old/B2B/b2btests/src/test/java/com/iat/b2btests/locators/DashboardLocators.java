package com.iat.b2btests.locators;

public class DashboardLocators implements IPageLocators {
	
	public Locator card_div = new Locator(LocatorType.XPATH, "//div[@class='wls-shopCard' and not(@data-status='BLANK')]");
	public Locator header_h3 = new Locator(LocatorType.XPATH, "//h3[@class='page-title' and contains(.,'Shop Dashboard')]");
	public Locator cardUrl_div = new Locator(LocatorType.XPATH, "//div[contains(@class, 'wls-store-domain')]");
	
	public Locator blankShop_card = new Locator(LocatorType.XPATH, "//div[@data-status='BLANK']");
	public Locator blankShopAdd_button = new Locator(LocatorType.XPATH, "//div[@data-status='BLANK']//a[contains(@class,'wls-goto-shop-creator')]");
	
	public Locator newShop_card = new Locator(LocatorType.XPATH, "//div[@data-status='NEW']");
	public Locator newShopName_div = new Locator(LocatorType.XPATH, "//div[@data-status='NEW']//div[@class='wls-shop-name']");
	public Locator newShopAdd_button = new Locator(LocatorType.XPATH, "//div[@data-status='NEW']//a[contains(@class,'wls-goto-shop-creator')]");
	
	public Locator startedShop_card = new Locator(LocatorType.XPATH, "//div[@data-status and not(@data-status='NEW') and not(@data-status='COMPLETE') and not(@data-status='BLANK')]");
	
	public Locator startedShopStep4_card = new Locator(LocatorType.XPATH, "//div[@data-status='BOOSTING']");
	public Locator startedShopStep4Name_div = new Locator(LocatorType.XPATH, "//div[@data-status='BOOSTING']//div[@class='wls-shop-name']");
	public Locator startedShopStep4Url_div = new Locator(LocatorType.XPATH, "//div[@data-status='BOOSTING']//div[contains(@class, 'wls-store-domain')]");
	public Locator startedShopStep4Continue_link = new Locator(LocatorType.XPATH, "//div[@data-status='BOOSTING']//a[contains(@class, 'wls-goto-shop-wizard')]");
	
	public Locator completedShop_card = new Locator(LocatorType.XPATH, "//div[@data-status='COMPLETE']");	
	public Locator completedShopUrl_div = new Locator(LocatorType.XPATH, "//div[@data-status='COMPLETE']//div[contains(@class, 'wls-store-domain')]");
	public Locator completedShopManager_button = new Locator(LocatorType.XPATH, "//div[@data-status='COMPLETE']//a[contains(@class,'wls-goto-shop-manager')]");
	
}
