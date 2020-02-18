package com.iat.b2btests.steps;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.iat.b2btests.actions.DashboardActions;
import com.iat.b2btests.actions.WizardStep4Actions;
import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.executors.SeleniumExecutor;
import com.iat.b2btests.locators.DashboardLocators;
import com.iat.b2btests.locators.WizardGenericStepLocators;
import com.iat.b2btests.locators.WizardStep4Locators;
import com.iat.b2btests.messages.WizardStepsMessages;
import com.iat.b2btests.navigations.DashboardNavigation;
import com.iat.b2btests.navigations.WizardGenericNavigation;
import com.iat.b2btests.navigations.WizardStep4Navigation;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WizardStep4Steps {

	public String shopName, shopUrl, previewUrl;
	
	private IExecutor executor;
	private DashboardActions dbdAct;
	private WizardStep4Actions step4Act;
	
	private DashboardNavigation dashboardNav;
	private WizardGenericNavigation genericNav;
	private WizardStep4Navigation step4Nav;
	
	private WizardStepsMessages texts = new WizardStepsMessages();
	
	protected DashboardLocators dashboardLocators = new DashboardLocators();
	protected WizardGenericStepLocators stepsLocators = new WizardGenericStepLocators();
	protected WizardStep4Locators step4Locators = new WizardStep4Locators();
		
	public WizardStep4Steps(SeleniumExecutor executor) {
		this.executor = executor;
		
		dbdAct = new DashboardActions(executor);
		step4Act = new WizardStep4Actions(executor);
		dashboardNav = new DashboardNavigation(executor);
		genericNav = new WizardGenericNavigation(executor);
		step4Nav = new WizardStep4Navigation(executor);
	}
	
	@Given("^he clicked 'Continue' button to get to step 4$")
	@When("^he clicks 'Continue' button to get to step 4$")
	public void he_clicks_Continue_button_to_get_to_step_4() {
		shopName = dbdAct.getShopInfo(dashboardLocators.startedShopStep4Name_div);
		shopUrl = dbdAct.getShopInfo(dashboardLocators.startedShopStep4Url_div);
		dashboardNav.clickContinueButtonStep4();
	}
	
	@When("^he clicks colour fields and picks random colours from colour picker$")
	public void he_clicks_colour_fields_and_picks_random_colours_from_colour_picker() {
		step4Act.pickColorFromPicker("#ffffff", step4Locators.coloursMain_input);
		// to be finished here		
	}	
	
	@When("^he resets colors to default settings$")
	public void he_resets_colors_to_default_settings() {
		step4Nav.clickResetColors();
	}
	
	@When("^he enters '([^\']*)' color code manually$")
	public void he_enters_color_code_manually(String colorCode) {
		switch (colorCode) {
		case "main":
			executor.clear_textarea(step4Locators.coloursMain_input);
			executor.send_keys(step4Locators.coloursMain_input, "#99b6ff");
			executor.click(step4Locators.coloursMain_input);
			break;
		case "font":
			executor.clear_textarea(step4Locators.coloursFont_input);
			executor.send_keys(step4Locators.coloursFont_input, "#185409");
			executor.click(step4Locators.coloursFont_input);
			executor.click(step4Locators.coloursMain_input);
			executor.click(step4Locators.coloursMain_input);
			break;
		default:
			fail("No color field provided");
		}
	}
	
	@When("^he doesn't make any changes in the step$")
	public void he_doesnt_make_any_changes_in_the_step() {
		// nothing here
	}
	
	@When("^he clicks 'Save and continue' button$")
	public void he_clicks_Save_and_continue_button() {
		genericNav.clickSaveContinue();
	}
	
	@When("^he uploads acceptable image as shop logo$")
	public void he_uploads_acceptable_image_as_shop_logo() {
		step4Nav.uploadLogo("acceptable.gif");
	}
	
	@When("he opens '([^\']*)' tab in '([^\']*)' section$")
	public void he_opens_tab_in_section(String tab, String section) {
		switch(section) {
		case "Custom Header":
			switch(tab) {
			case "No header":
				step4Nav.clickTab("headerNo");
				break;
			case "Header image":
				step4Nav.clickTab("headerImg");
				break;
			case "Paste HTML":
				step4Nav.clickTab("headerHtml");
				break;
			case "Server link":
				step4Nav.clickTab("headerLink");
				break;
			}
			break;
		case "Custom Footer":
			switch(tab) {
			case "No header":
				step4Nav.clickTab("footerNo");
				break;
			case "Footer image":
				step4Nav.clickTab("footerImg");
				break;
			case "Paste HTML":
				step4Nav.clickTab("footerHtml");
				break;
			case "Server link":
				step4Nav.clickTab("footerLink");
				break;
			}
			break;
		}
	}
	
	@When("he provides ([^\']*) HTML code$")
	public void he_provides_HTML_code(String section) {
		switch(section) {
		case "header":
			step4Nav.enterHtml("header", "sample HTML code for header");
			break;
		case "footer":
			step4Nav.enterHtml("footer", "sample HTML code for footer");
			break;
		}
	}
	
	@When("he provides ([^\']*) server link$")
	public void he_provides_server_link(String section) {
		switch(section) {
		case "header":
			step4Nav.enterLink("header", "http://serverlink.com/header");
			break;
		case "footer":
			step4Nav.enterLink("footer", "http://serverlink.com/footer");
			break;
		}
	}
	
	@When("^he uploads acceptable ([^\']*) image$")
	public void he_uploads_acceptable_header_image(String section) {
		switch(section) {
		case "header":
			step4Nav.uploadImage(step4Locators.headerImgFile_input, "headerImg_ivillage.png");
			break;
		case "footer":
			step4Nav.uploadImage(step4Locators.footerImgFile_input, "footerImg_ivillage.png");
			break;
		}
	}
	
	@Then("^screen 4 includes: header, info text, shop address, shop url, wizard steps$")
	public void screen_4_includes() {
		assertTrue(executor.getText(stepsLocators.stepTitle_h3).equals("Shop Wizard"));
		assertTrue(executor.getText(stepsLocators.shopName_span).equals(shopName));
		assertTrue(executor.getText(stepsLocators.shopUrl_span).equals(shopUrl));
		assertTrue(executor.getText(stepsLocators.stepInfo_div).equals(texts.step4_info));
		assertTrue(executor.isPresent(stepsLocators.steps_div));
		assertTrue(executor.isPresent(stepsLocators.steps4Active_div));		
	}
	
	@Then("^screen 4 includes: colours section with main/font colour fields, preview, reset button$")
	public void screen_4_includes_colours() {
		assertTrue(executor.isPresent(step4Locators.coloursMain_input));
		assertTrue(executor.isPresent(step4Locators.coloursFont_input));
		assertTrue(executor.isPresent(step4Locators.coloursPreview_div));
		assertTrue(executor.isPresent(step4Locators.coloursReset_link));		
	}
	
	@Then("^screen 4 includes: logo image upload facility$")
	public void screen_4_includes_logo() {
		assertTrue(executor.isPresent(step4Locators.logoFile_input));		
	}
	
	@Then("^screen 4 includes: return to dashboard, live preview, save and continue buttons$")
	public void screen_4_includes_buttons() {
		assertTrue(executor.isPresent(stepsLocators.stepDashboard_link));
		assertTrue(executor.isPresent(stepsLocators.stepPreview_link));
		assertTrue(executor.isPresent(stepsLocators.stepContinue_link));		
	}
	
	@Then("^screen 4 includes: no header, header image, header html, header link sections with corresponding fields$")
	public void screen_4_includes_header_section() {
		assertTrue(executor.isPresent(step4Locators.headerTabNoHeader_link));
		assertTrue(executor.isPresent(step4Locators.headerTabImage_link));
		step4Nav.clickTab("headerImg");
		assertTrue(executor.isPresent(step4Locators.headerImgFile_input));
		assertTrue(executor.isPresent(step4Locators.headerTabHTML_link));
		step4Nav.clickTab("headerHtml");
		assertTrue(executor.isPresent(step4Locators.headerHTML_textarea));
		assertTrue(executor.isPresent(step4Locators.headerTabLink_link));
		step4Nav.clickTab("headerLink");
		assertTrue(executor.isPresent(step4Locators.headerLink_input));
	}
	
	@Then("^screen 4 includes: no footer, footer image, footer html, footer link sections with corresponding fields$")
	public void screen_4_includes_footer_section() {
		assertTrue(executor.isPresent(step4Locators.footerTabNoFooter_link));
		assertTrue(executor.isPresent(step4Locators.footerTabImage_link));
		step4Nav.clickTab("footerImg");
		assertTrue(executor.isPresent(step4Locators.footerImgFile_input));
		assertTrue(executor.isPresent(step4Locators.footerTabHTML_link));
		step4Nav.clickTab("footerHtml");
		assertTrue(executor.isPresent(step4Locators.footerHTML_textarea));
		assertTrue(executor.isPresent(step4Locators.footerTabLink_link));
		step4Nav.clickTab("footerLink");
		assertTrue(executor.isPresent(step4Locators.footerLink_input));
	}
	
	@Then("^he is able to enter any color code for each of the properties$")
	public void he_is_able_to_enter_any_color_code_for_each_of_the_properties() {
		assertTrue(executor.getValue(step4Locators.coloursMain_input).equals("#99b6ff"));
		assertTrue(executor.getValue(step4Locators.coloursFont_input).equals("#185409"));
	}
	
	@Then("^selected colors are reflected on live preview image$")
	public void selected_colors_are_reflected_on_live_preview_image() {
		assertTrue(executor.isPresent(step4Locators.coloursPreviewChanged_div));		
	}
	
	@Then("^original colors are reflected on live preview image$")
	public void default_colors_are_reflected_on_live_preview_image() {
		assertTrue(executor.isPresent(step4Locators.coloursPreviewDefault_div));		
	}
	
	@Then("^([^\']*) colors are saved correctly after step is saved$")
	public void colors_configuration_is_saved(String colorsType) {
		genericNav.clickSaveContinue();
		genericNav.clickRibbonStep4();
		
		switch(colorsType) {
		case "changed":
			this.he_is_able_to_enter_any_color_code_for_each_of_the_properties();
			break;
		case "original":
			this.color_fields_values_are_set_to_default();
			break;
		default:
			fail("Color fields not saved correctly");
		}
	}
	
	@Then("^color fields values are set to default$")
	public void color_fields_values_are_set_to_default() {
		assertTrue(executor.getValue(step4Locators.coloursMain_input).equals("#f5f5f5"));
		assertTrue(executor.getValue(step4Locators.coloursFont_input).equals("#000000"));
	}
	
	@Then("^image preview is displayed in 'Brand logo' section$")
	public void logo_preview_is_displayed() {
		assertTrue(executor.isPresent(step4Locators.logoPreview_img));
		previewUrl = executor.getAttribute(step4Locators.logoPreview_img, "src");
	}
	
	@Then("^([^\']*) image preview is displayed$")
	public void preview_is_displayed(String section) {
		switch(section) {
		case "header":
			assertTrue(executor.isPresent(step4Locators.headerImgPreview_img));
			previewUrl = executor.getAttribute(step4Locators.headerImgPreview_img, "src");
			break;
		case "footer":
			assertTrue(executor.isPresent(step4Locators.footerImgPreview_img));
			previewUrl = executor.getAttribute(step4Locators.footerImgPreview_img, "src");
			break;
		}
	}
	
	@Then("^logo is saved correctly after step is saved$")
	public void logo_is_saved_correctly_after_step_is_saved() {
		genericNav.clickSaveContinue();
		genericNav.clickRibbonStep4();
		
		assertTrue(executor.isPresent(step4Locators.logoPreview_img));
		assertTrue(executor.getAttribute(step4Locators.logoPreview_img, "src").equals(previewUrl));
	}
	
	@Then("^([^\']*) image is saved correctly after step is saved$")
	public void logo_is_saved_correctly_after_step_is_saved(String section) {
		genericNav.clickSaveContinue();
		genericNav.clickRibbonStep4();
		
		switch(section) {
		case "header":
			assertTrue(executor.isPresent(step4Locators.headerTabImageSelected_link));
			assertTrue(executor.isPresent(step4Locators.headerImgPreview_img));
			assertTrue(executor.getAttribute(step4Locators.headerImgPreview_img, "src").equals(previewUrl));
			break;
		case "footer":
			assertTrue(executor.isPresent(step4Locators.footerTabImageSelected_link));
			assertTrue(executor.isPresent(step4Locators.footerImgPreview_img));
			assertTrue(executor.getAttribute(step4Locators.footerImgPreview_img, "src").equals(previewUrl));
			break;
		}
	}
	
	@Then("^([^\']*) HTML code is saved correctly after step is saved")
	public void html_is_saved_correctly(String section) {
		genericNav.clickSaveContinue();
		genericNav.clickRibbonStep4();
		
		switch(section) {
		case "header":
			assertTrue(executor.isPresent(step4Locators.headerTabHTMLSelected_link));
			assertTrue(executor.getValue(step4Locators.headerHTML_textarea).equals("sample HTML code for header"));
			break;
		case "footer":
			step4Nav.clickTab("footerHtml");
			assertTrue(executor.isPresent(step4Locators.footerTabHTMLSelected_link));
			assertTrue(executor.getValue(step4Locators.footerHTML_textarea).equals("sample HTML code for footer"));
			break;
		}
	}
	
	@Then("^([^\']*) server link is saved correctly after step is saved")
	public void link_is_saved_correctly(String section) {
		genericNav.clickSaveContinue();
		genericNav.clickRibbonStep4();
		
		switch(section) {
		case "header":
			assertTrue(executor.isPresent(step4Locators.headerTabLinkSelected_link));
			assertTrue(executor.getValue(step4Locators.headerLink_input).equals("http://serverlink.com/header"));
			break;
		case "footer":
			assertTrue(executor.isPresent(step4Locators.footerTabLinkSelected_link));
			assertTrue(executor.getValue(step4Locators.footerLink_input).equals("http://serverlink.com/footer"));
			break;
		}
	}	
}
