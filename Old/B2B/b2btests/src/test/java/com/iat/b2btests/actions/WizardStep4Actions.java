package com.iat.b2btests.actions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebElement;

import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.locators.ILocator;
import com.iat.b2btests.locators.WizardStep4Locators;
import com.iat.b2btests.navigations.AbstractPage;


public class WizardStep4Actions extends AbstractPage {
	
	protected WizardStep4Locators step4Locators = new WizardStep4Locators();
	
	public WizardStep4Actions(IExecutor executor) {
		super(executor, "");
	}
	
	
	public void pickColorFromPicker(String startColor, ILocator locator) {
		WebElement element = executor.get_element(locator);
		
		// enter starting colour code
		element.clear();
		element.sendKeys(startColor);
		
		executor.actionMoveByOffset(step4Locators.coloursPickerCurrentColor_i, 50, 50);
		
				
		
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
