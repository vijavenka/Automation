package com.iat.b2btests.locators;

public class LoginLocators implements IPageLocators {
	
	public Locator user_input = new Locator(LocatorType.XPATH, "//input[@name='email']");
	public Locator pass_input = new Locator(LocatorType.XPATH, "//input[@name='password']");
	public Locator login_button = new Locator(LocatorType.XPATH, "//button[@type='submit']");
	
}
