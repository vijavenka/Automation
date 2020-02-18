package com.iat.b2btests.actions;

import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.locators.ILocator;
import com.iat.b2btests.navigations.AbstractPage;

public class DashboardActions extends AbstractPage {
	
	public DashboardActions(IExecutor executor) {
		super(executor, "");
	}
	
	public String getShopInfo(ILocator locator) {
		String shopName = executor.getText(locator);
		return shopName;		
	}
	
}
