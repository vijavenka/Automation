package com.iat.b2btests.navigations;

import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.locators.ILocator;
import com.iat.b2btests.locators.WizardGenericStepLocators;
import com.iat.b2btests.locators.WizardStep4Locators;

public class WizardStep4Navigation extends AbstractPage {
	
	static String PROJECT_PATH = System.getProperty("user.dir");
	
	protected WizardGenericStepLocators stepsLocators = new WizardGenericStepLocators();
	protected WizardStep4Locators step4Locators = new WizardStep4Locators(); 
	
	public WizardStep4Navigation(IExecutor executor) {
		super(executor, "");
	}
	
	
	public void clickResetColors() {
		executor.waitUntilElementAppears(step4Locators.coloursReset_link);
		executor.click(step4Locators.coloursReset_link);
	}
	
	public void uploadLogo(String imageName) {
		executor.waitUntilElementAppears(step4Locators.logoFile_input);
		executor.send_keys(step4Locators.logoFile_input, PROJECT_PATH.replace("\\", "\\\\") + "\\uploads\\" + imageName);
		
		executor.waitUntilElementAppears(step4Locators.logoPreview_img);
	}
	
	public void clickTab(String tabName) {
		switch(tabName) {
		case "headerNo":
			executor.click(step4Locators.headerTabNoHeader_link);
			break;
		case "headerImg":
			executor.click(step4Locators.headerTabImage_link);
			executor.waitUntilElementAppears(step4Locators.headerImgFile_input);
			break;
		case "headerHtml":
			executor.click(step4Locators.headerTabHTML_link);
			executor.waitUntilElementAppears(step4Locators.headerHTML_textarea);
			break;
		case "headerLink":
			executor.click(step4Locators.headerTabLink_link);
			executor.waitUntilElementAppears(step4Locators.headerLink_input);
			break;
		case "footerNo":
			executor.click(step4Locators.footerTabNoFooter_link);
			break;
		case "footerImg":
			executor.click(step4Locators.footerTabImage_link);
			executor.waitUntilElementAppears(step4Locators.footerImgFile_input);
			break;
		case "footerHtml":
			executor.click(step4Locators.footerTabHTML_link);
			executor.waitUntilElementAppears(step4Locators.footerHTML_textarea);
			break;
		case "footerLink":
			executor.click(step4Locators.footerTabLink_link);
			executor.waitUntilElementAppears(step4Locators.footerLink_input);
			break;
		}
	}
	
	public void uploadImage(ILocator locator, String imageName) {
		executor.waitUntilElementAppears(locator);
		executor.send_keys(locator, PROJECT_PATH.replace("\\", "\\\\") + "\\uploads\\" + imageName);
		
		if(locator == step4Locators.headerImgFile_input) {
			executor.waitUntilElementAppears(step4Locators.headerImgPreview_img);
		}
		else if (locator == step4Locators.footerImgFile_input) {
			executor.waitUntilElementAppears(step4Locators.footerImgPreview_img);
		}
	}
	
	public void enterHtml(String section, String code) {
		switch(section) {
		case "header":
			executor.clear_textarea(step4Locators.headerHTML_textarea);
			executor.send_keys(step4Locators.headerHTML_textarea, code);
			break;
		case "footer":
			executor.clear_textarea(step4Locators.footerHTML_textarea);
			executor.send_keys(step4Locators.footerHTML_textarea, code);
			break;
		}
	}
	
	public void enterLink(String section, String code) {
		switch(section) {
		case "header":
			executor.clear_textarea(step4Locators.headerLink_input);
			executor.send_keys(step4Locators.headerLink_input, code);
			break;
		case "footer":
			executor.clear_textarea(step4Locators.footerLink_input);
			executor.send_keys(step4Locators.footerLink_input, code);
			break;
		}
	}
	
}
