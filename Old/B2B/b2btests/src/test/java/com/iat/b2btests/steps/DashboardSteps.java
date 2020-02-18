package com.iat.b2btests.steps;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.iat.b2btests.actions.GenericActions;
import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.executors.SeleniumExecutor;
import com.iat.b2btests.locators.DashboardLocators;
import com.iat.b2btests.locators.Locator;
import com.iat.b2btests.locators.LocatorType;
import com.iat.b2btests.locators.WizardGenericStepLocators;
import com.iat.b2btests.navigations.DashboardNavigation;
import com.iat.b2btests.navigations.WizardGenericNavigation;
import com.iat.b2btests.navigations.WizardStep0Navigation;
import com.steadystate.css.parser.ParseException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class DashboardSteps {

	private String shopName, hostName;
	
	private IExecutor executor;
	private GenericActions genAct;
	
	private DashboardNavigation dashboardNav;
	private WizardGenericNavigation wizardGenerivNav;
	private WizardStep0Navigation step0Nav;
	
	private WizardStep0Steps wizardStep0;
	
	protected WizardGenericStepLocators wizardGenericLocators = new WizardGenericStepLocators();
	protected DashboardLocators dashboardLocators = new DashboardLocators();
	
	public DashboardSteps(SeleniumExecutor executor) {
		this.executor = executor;
		
		genAct = new GenericActions(executor);
		
		dashboardNav = new DashboardNavigation(executor);
		wizardGenerivNav = new WizardGenericNavigation(executor);
		step0Nav = new WizardStep0Navigation(executor);
		
		wizardStep0 = new WizardStep0Steps(executor);
	}
	
	@Given("^client has some campaign\\(s\\) set$")
	public void WG_client_has_some_campaign_s_set_within_Webgains() {
		assertTrue(executor.isPresent(dashboardLocators.card_div));
	}
	
	@Given("^he has campaigns without shops configured for yet$")
	public void he_has_campaigns_without_shops_configured_for_yet() {
		assertTrue(executor.isPresent(dashboardLocators.newShop_card));
	}
	
	@Given("^he has campaigns with shops configurations started already \\(step (\\d+)\\)$")
	public void he_has_campaigns_with_shops_configurations_started_already(int stepNo) {
		if(stepNo <= 0) {
			// click Create shop button 
			dashboardNav.clickCreateButton();
			
			// check if step0 displayed
			wizardStep0.he_is_taken_to_Wizard_Step_screen(0);
		
			//enter shop name
			shopName = "math." + genAct.serialize() + ".pl";
			step0Nav.enterShopName(shopName);
			
			//enter host name
			hostName = "math" + genAct.serialize();
			step0Nav.enterHostName(hostName);
			
			// click Build button
			step0Nav.clickBuildShopButton();
			
		}
		
		if(stepNo == 0) {
			wizardGenerivNav.clickDashboardButton();
		}
		
		assertTrue(executor.isPresent(dashboardLocators.startedShop_card));
	}
	
	@Given("^he has campaigns with shops configurations stopped at step 4$")
	public void he_has_campaigns_with_shops_configurations_stopped_at_step_4() {
		assertTrue(executor.isPresent(dashboardLocators.startedShopStep4_card));
	}
	
	@Given("^he has campaigns with shops configurations completed already$")
	public void he_has_campaigns_with_shops_configurations_completed_already() {
		assertTrue(executor.isPresent(dashboardLocators.completedShop_card));
	}
	
	@Given("^Dashboard screen is displayed$")
	@When("^Dashboard screen loads completely$")
	public void Dashboard_screen_loads_completely() {
		assertTrue(executor.isPresent(dashboardLocators.card_div));
	}
	
	@Given("^he can see 'Create new shop' card$")
	public void he_can_see_create_new_shop_card() {
		assertTrue(executor.isPresent(dashboardLocators.blankShop_card));
	}
	
	@When("^he clicks 'Create new shop' button$")
	public void clicks_create_new_shop_button() {
		dashboardNav.clickCreateButton();
	}
	
	@When("^he clicks 'Shop Manager' button$")
	public void clicks_shop_manager_button() {
		dashboardNav.clickManagerButton();
	}
	
	@When("^he clicks 'Continue' button on card with configuration started already$")
	public void clicks_continue_button_on_started_card() {
		dashboardNav.clickContinueButtonCardWName(shopName);
	}
	
	@Then("^he can see Dashboard screen displayed$")
	public void he_can_see_Dashboard_screen_displayed() {

	}

	@Then("^Dasboard header is displayed$")
	public void Dasboard_header_is_displayed() {
		assertTrue(executor.isPresent(dashboardLocators.header_h3));
	}

	@Then("^grid/listview options are available$")
	public void grid_listview_options_are_available() {
	   // to be updated once functionality appears
	}

	@Then("^one card per each campaign is displayed$")
	public void one_card_per_each_campaign_is_displayed() {
		assertTrue(genAct.isUniqueTextInElements(dashboardLocators.cardUrl_div));
	}
	
	@Then("^he can see NOT STARTED shop configurations \\(cards\\)$")
	public void he_can_see_NOT_STARTED_shop_configurations_cards() {
		List<WebElement> newShops = executor.get_elements(dashboardLocators.newShop_card);
		assertTrue(newShops.size() > 0);
	}
	
	@Then("^he can see STARTED/NOT COMPLETED shop configurations \\(cards\\)$")
	public void he_can_see_STARTED_NOT_COMPLETED_shop_configurations_cards() {
		List<WebElement> newShops = executor.get_elements(dashboardLocators.startedShop_card);
		assertTrue(newShops.size() > 0);
	}
	
	@Given("^he can see COMPLETED shops \\(cards\\)$")
	@Then("^he can see COMPLETED shop configurations \\(cards\\)$")
	public void he_can_see_COMPLETED_shop_configurations_cards() {
		List<WebElement> newShops = executor.get_elements(dashboardLocators.completedShop_card);
		assertTrue(newShops.size() > 0);
	}
	
	@Then("^every NOT STARTED shop card includes Site/campaign name, campaign ID, 'Add a shop' button$")
	public void every_NOT_STARTED_shop_card_includes() {
		List<WebElement> newShops = executor.get_elements(dashboardLocators.newShop_card);
		
		for( int i = 0; i < newShops.size(); i++ ) {
			// header
			assertTrue(executor.isPresent(new Locator(LocatorType.XPATH, "//div[@data-status='NEW'][" + (i+1) + "]//div[@class='wls-shop-name']")));
			
			// id
			assertTrue(executor.isPresent(new Locator(LocatorType.XPATH, "//div[@data-status='NEW'][" + (i+1) + "]//span[@class='wls-shop-id']")));
			
			// button
			assertTrue(executor.isPresent(new Locator(LocatorType.XPATH, "//div[@data-status='NEW'][" + (i+1) + "]//a[contains(@class, 'wls-goto-shop-creator')]")));
		}
	}
	
	@Then("^every STARTED/NOT COMPLETED shop card includes Site/campaign name, campaign ID, Started D&T, current step, address, 'Continue' button$")
	public void every_STARTED_NOT_COMPLETED_shop_card_includes() throws ParseException {
		
		List<String> startedNotCompleted = Arrays.asList("CREATED", "CATEGORIES", "NAVIGATION", "BOOSTING");
		
		for(Iterator<String> x = startedNotCompleted.iterator(); x.hasNext(); ) {
		    String status = x.next();
		    List<WebElement> startedShops = executor.get_elements(new Locator(LocatorType.XPATH, "//div[@data-status='" + status + "']"));
			
			for( int i = 0; i < startedShops.size(); i++ ) {
				// header
				assertTrue(executor.isPresent(new Locator(LocatorType.XPATH, "//div[@data-status='" + status + "'][" + (i+1) + "]//div[@class='wls-shop-name']")));
				
				// id
				//assertTrue(executor.isPresent(new Locator(LocatorType.XPATH, "//div[@data-status='" + status + "'][" + (i+1) + "]//span[@class='wls-shop-id']")));
				
				// status
				assertTrue(executor.getText(new Locator(LocatorType.XPATH, "//div[@data-status='" + status + "'][" + (i+1) + "]//ul/li[1]")).contains("Started"));
	
				// date
				String vdate = executor.getText(new Locator(LocatorType.XPATH, "//div[@data-status='" + status + "'][" + (i+1) + "]//ul/li[1]/div"));
				assertTrue(genAct.validateDate(vdate));
				
				// current step
				assertTrue(executor.getText(new Locator(LocatorType.XPATH, "//div[@data-status='" + status + "'][" + (i+1) + "]//ul/li[2]")).matches("^Currently at step [1-6] of [1-6]$"));
				
				// address
				assertTrue(executor.getText(new Locator(LocatorType.XPATH, "//div[@data-status='" + status + "'][" + (i+1) + "]//ul/li[3]")).contains("Address"));
				
				String vUrl = executor.getText(new Locator(LocatorType.XPATH, "//div[@data-status='" + status + "'][" + (i+1) + "]//ul/li[3]/div"));
				assertTrue(genAct.validateUrl(vUrl));
				
				// button
				assertTrue(executor.isPresent(new Locator(LocatorType.XPATH, "//div[@data-status='" + status + "'][" + (i+1) + "]//a[contains(@class, 'wls-goto-shop-wizard')]")));
			}
		}
	}
	
	@Then("^every COMPLETED shop card includes Site/campaign name, campaign ID, Started D&T, current step, address, 'Shop Manager' button$")
	public void every_COMPLETED_shop_card_includes() throws ParseException {
	    List<WebElement> completedShops = executor.get_elements(new Locator(LocatorType.XPATH, "//div[@data-status='BRANDING']"));
		
		for( int i = 0; i < completedShops.size(); i++ ) {
			// header
			assertTrue(executor.isPresent(new Locator(LocatorType.XPATH, "//div[@data-status='BRANDING'][" + (i+1) + "]//div[@class='wls-shop-name']")));
			
			// id
			assertTrue(executor.isPresent(new Locator(LocatorType.XPATH, "//div[@data-status='BRANDING'][" + (i+1) + "]//span[@class='wls-shop-id']")));
			
			// status
			assertTrue(executor.getText(new Locator(LocatorType.XPATH, "//div[@data-status='BRANDING'][" + (i+1) + "]//ul/li[1]")).contains("Started"));

			// date
			String vdate = executor.getText(new Locator(LocatorType.XPATH, "//div[@data-status='BRANDING'][" + (i+1) + "]//ul/li[1]/div"));
			assertTrue(genAct.validateDate(vdate));
			
			// current step
			assertTrue(executor.getText(new Locator(LocatorType.XPATH, "//div[@data-status='BRANDING'][" + (i+1) + "]//ul/li[2]")).matches("Configuration finished"));
			
			// address
			assertTrue(executor.getText(new Locator(LocatorType.XPATH, "//div[@data-status='BRANDING'][" + (i+1) + "]//ul/li[3]")).contains("Address"));
			
			String vUrl = executor.getText(new Locator(LocatorType.XPATH, "//div[@data-status='BRANDING'][" + (i+1) + "]//ul/li[3]/div"));
			assertTrue(genAct.validateUrl(vUrl));
			assertTrue(executor.isPresent(new Locator(LocatorType.XPATH, "//div[@data-status='BRANDING'][" + (i+1) + "]//ul/li[3]/div/a[@href='" + vUrl + "']")));
			
			// button
			assertTrue(executor.isPresent(new Locator(LocatorType.XPATH, "//div[@data-status='BRANDING'][" + (i+1) + "]//a[contains(@class, 'wls-goto-shop-manager')]")));
		}
	}
	
	@Then("^he is taken to Shop Wizard, last not saved step \\(step (\\d+)\\)$")
	public void he_can_see_COMPLETED_shop_configurations_cards(int stepNo) {
		wizardStep0.he_is_taken_to_Wizard_Step_screen(stepNo);
	}	
	
}
