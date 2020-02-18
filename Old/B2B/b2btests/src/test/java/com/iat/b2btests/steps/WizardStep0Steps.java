package com.iat.b2btests.steps;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.iat.b2btests.actions.DashboardActions;
import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.executors.SeleniumExecutor;
import com.iat.b2btests.locators.DashboardLocators;
import com.iat.b2btests.locators.WizardGenericStepLocators;
import com.iat.b2btests.locators.WizardStep0Locators;
import com.iat.b2btests.navigations.DashboardNavigation;
import com.iat.b2btests.navigations.WizardStep0Navigation;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WizardStep0Steps {

	public String shopName, shopUrl;
	
	private IExecutor executor;
	private DashboardActions dbdAct;
	private DashboardNavigation dashboardNav;
	private WizardStep0Navigation step0Nav;
	
	protected DashboardLocators dashboardLocators = new DashboardLocators();
	protected WizardGenericStepLocators stepsLocators = new WizardGenericStepLocators();
	protected WizardStep0Locators step1Locators = new WizardStep0Locators();
		
	public WizardStep0Steps(SeleniumExecutor executor) {
		this.executor = executor;
		
		dbdAct = new DashboardActions(executor);
		
		dashboardNav = new DashboardNavigation(executor);
		step0Nav = new WizardStep0Navigation(executor);
	}
	
	@Given("^he knows existing shop URL address$")
	public void he_knows_existing_shop_URL_address() {
	    shopUrl = dbdAct.getShopInfo(dashboardLocators.completedShopUrl_div);
	}
	
	@Given("^he clicked 'Add a shop' button$")
	@When("^he clicks 'Add a shop' button$")
	public void clicks_Add_a_shop_button() {
		shopName = dbdAct.getShopInfo(dashboardLocators.newShopName_div);
		dashboardNav.clickAddButton();
	}
	
	@Given("^he has been taken to Wizard Step (\\d+) screen$")
	@Then("^he is taken to Wizard Step (\\d+) screen$")
	public void he_is_taken_to_Wizard_Step_screen(int stepNo) {
		switch (stepNo) {
		case 0:
			assertTrue(executor.getUrl().contains("#shop/create"));
			break;
		case 1:
			assertTrue(executor.getUrl().contains("wizard/step/categories"));
			break;
		case 4:
			assertTrue(executor.getUrl().contains("wizard/step/branding"));
			break;
		case 5:
			assertTrue(executor.getUrl().contains("wizard/step/ready"));
			break;
		default:
			fail("Not redirected to desired step");
		}
	}
	
	@Then("^screen includes: header, info text, shop address, shop url field, 'Build your shop' button$")
	public void screen_includes_header_info_text_shop_address_shop_url_field_Build_your_shop_button() {
		assertTrue(executor.getText(step1Locators.pageTitle_h3).equals("Add a shop"));
		assertTrue(executor.getText(step1Locators.step0Caption_div).equals("Shop address"));
		assertTrue(executor.isPresent(step1Locators.step0Info_div));
		assertTrue(executor.isPresent(step1Locators.step0Subdomain_input));
		assertTrue(executor.getText(step1Locators.step0SiteName_div).contains(shopName));
		assertTrue(executor.isPresent(step1Locators.step0Continue_button));
	}
	
	@When("^he clicks on step (\\d+) inactive button in wizard steps ribbon$")
	public void he_clicks_ribbon_inactive_button(int inButton) {
		switch (inButton) {
		case 1:
			executor.click(stepsLocators.steps1_link);
			break;
		case 2:
			executor.click(stepsLocators.steps2_link);
			break;
		case 3:
			executor.click(stepsLocators.steps3_link);
			break;
		case 4:
			executor.click(stepsLocators.steps4_link);
			break;
		case 5:
			executor.click(stepsLocators.steps5_link);
			break;
		}
	}
	
	@When("^he clears sub-domain field$")
	public void he_clears_subdomain_field() {
	    executor.clear_textarea(step1Locators.step0Subdomain_input);
	}
	
	@When("^he clicks 'Build your shop' button$")
	public void he_clicks_Build_your_shop_button() {
		step0Nav.clickBuildShopButton();
	}
	
	@When("^he provides incorrect epoints.com subdomain \\(([^']*)\\)$")
	public void he_provides_incorrect_epoints_com_subdomain_incorrect_subdomain(String subdomain) {
		executor.clear_textarea(step1Locators.step0Subdomain_input);
		executor.send_keys(step1Locators.step0Subdomain_input, subdomain);
	}
	
	@When("^he provides duplicated epoints.com sub-domain$")
	public void he_provides_duplicated_epoints_com_sub_domain() {
		executor.clear_textarea(step1Locators.step0Subdomain_input);
		executor.send_keys(step1Locators.step0Subdomain_input, shopUrl);
	}

	@Then("^appropriate warning message is displayed$")
	public void appropriate_warning_message_is_displayed() {
	    executor.isVisible(step1Locators.step0Warning_div);
	    
	    // TODO: check message when fixed
	}
	
	@Then("^shop configuration is NOT created in mongo db$")
	public void shop_configuration_is_NOT_created_in_mongo_db() {
	    // TODO: to be checked later
	}
	
	@Then("^default sub-domain is correctly saved and displayed in dashboard$")
	public void default_sub_domain_is_correctly_saved_and_displayed_in_dashboard() {
	    
	}
}
