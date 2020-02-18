package com.iat.b2btests.navigations;

import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.locators.DashboardLocators;
import com.iat.b2btests.locators.Locator;
import com.iat.b2btests.locators.LocatorType;

public class DashboardNavigation extends AbstractPage {
	
	protected DashboardLocators dashboardLocators = new DashboardLocators(); 
	
	public DashboardNavigation(IExecutor executor) {
		super(executor, "");
	}
	
	public void open() {
		super.open();
		executor.isPresent(dashboardLocators.card_div);
	}
	
	public void clickAddButton() {
		executor.click(dashboardLocators.newShopAdd_button);
	}
	
	public void clickCreateButton() {
		executor.click(dashboardLocators.blankShopAdd_button);
	}
	
	public void clickManagerButton() {
		executor.click(dashboardLocators.completedShopManager_button);
	}
	
	public void clickContinueButtonStep4() {
		executor.click(dashboardLocators.startedShopStep4Continue_link);
	}
	
	public void clickContinueButtonCardWName(String ShopName) {
		executor.click(new Locator(LocatorType.XPATH, "//div[contains(text(), '" + ShopName + "')]/parent::h3/parent::div[@class='pricing-head']/following-sibling::div/a[contains(@class, 'wls-goto-shop-wizard')]"));
	}
	
}
