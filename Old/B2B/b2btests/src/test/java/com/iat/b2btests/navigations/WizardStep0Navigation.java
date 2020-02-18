package com.iat.b2btests.navigations;

import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.locators.WizardGenericStepLocators;
import com.iat.b2btests.locators.WizardStep0Locators;

public class WizardStep0Navigation extends AbstractPage {
	
	static String PROJECT_PATH = System.getProperty("user.dir");
	
	protected WizardGenericStepLocators stepsLocators = new WizardGenericStepLocators();
	protected WizardStep0Locators step0Locators = new WizardStep0Locators(); 
	
	public WizardStep0Navigation(IExecutor executor) {
		super(executor, "");
	}
	
	public void enterShopName(String shopName) {
		executor.send_keys(step0Locators.step0ShopName_input, shopName);
	}
	
	public void enterHostName(String hostName) {
		executor.send_keys(step0Locators.step0HostName_input, hostName);
	}
	
	public void clickBuildShopButton() {
		executor.click(step0Locators.step0Continue_button);
	}
	
}
