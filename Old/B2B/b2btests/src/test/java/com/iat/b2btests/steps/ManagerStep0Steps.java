package com.iat.b2btests.steps;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.executors.SeleniumExecutor;
import com.iat.b2btests.locators.DashboardLocators;
import com.iat.b2btests.locators.WizardGenericStepLocators;
import com.iat.b2btests.locators.WizardStep0Locators;

import cucumber.api.java.en.Then;

public class ManagerStep0Steps {

	public String shopName, shopUrl;
	
	private IExecutor executor;
		
	protected DashboardLocators dashboardLocators = new DashboardLocators();
	protected WizardGenericStepLocators stepsLocators = new WizardGenericStepLocators();
	protected WizardStep0Locators step1Locators = new WizardStep0Locators();
		
	public ManagerStep0Steps(SeleniumExecutor executor) {
		this.executor = executor;
		
	}
	
	
	@Then("^he is taken to Manager '([^\']*)' screen$")
	public void he_is_taken_to_manager_screen(String step) {
		switch (step) {
		case "Summary":
			assertTrue(executor.getUrl().contains("manager/page/summary"));
			break;
		default:
			fail("Not redirected to desired manager step");
		}
	}
}
