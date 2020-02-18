package com.iat.b2btests.navigations;

import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.locators.WizardGenericStepLocators;

public class WizardGenericNavigation extends AbstractPage {
	
	protected WizardGenericStepLocators stepsLocators = new WizardGenericStepLocators();
	
	public WizardGenericNavigation(IExecutor executor) {
		super(executor, "");
	}
	
	
	public void waitForStepToLoad() {
		executor.waitUntilElementAppears(stepsLocators.steps_div);
		executor.waitUntilElementAppears(stepsLocators.stepDashboard_link);
	}
	
	public void clickDashboardButton() {
		executor.click(stepsLocators.stepDashboard_link);
	}
	
	public void clickSaveContinue() {
		executor.waitUntilElementAppears(stepsLocators.stepContinue_link);
		executor.click(stepsLocators.stepContinue_link);
		waitForStepToLoad();
	}
	
	public void clickRibbonStep4() {
		executor.waitAbsolute(1000);
		executor.waitUntilElementAppears(stepsLocators.steps_div);
		executor.waitUntilElementAppears(stepsLocators.steps4_link);
		executor.click(stepsLocators.steps4_link);
		waitForStepToLoad();
	}	
}
