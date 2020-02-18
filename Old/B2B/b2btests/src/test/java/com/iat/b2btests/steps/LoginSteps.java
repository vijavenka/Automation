package com.iat.b2btests.steps;

import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.executors.SeleniumExecutor;
import com.iat.b2btests.locators.DashboardLocators;
import com.iat.b2btests.navigations.LoginNavigation;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;


public class LoginSteps {

	private IExecutor executor;
	
	private LoginNavigation loginNav;
	protected DashboardLocators dashboardLocators = new DashboardLocators();
		
	public LoginSteps(SeleniumExecutor executor) {
		this.executor = executor;

		loginNav = new LoginNavigation(executor);
	}
	
	@Before
	public void set_up() {
		executor.start();
	}
	
	@After
	public void tear_down() {
		executor.stop();
	}
	
	@Given("^Client logs in as ([^\']*)$")
	public void client_logs_in_as(String user) {
		switch(user) {
		case "superuser":
			loginNav.login("super");
			break;
		case "webgains user":
			loginNav.login("webgains");
			break;
		}
	}
	
	
	
	
}
