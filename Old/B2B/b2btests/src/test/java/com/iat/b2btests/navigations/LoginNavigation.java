package com.iat.b2btests.navigations;

import com.iat.b2btests.executors.IExecutor;
import com.iat.b2btests.locators.LoginLocators;

public class LoginNavigation extends AbstractPage {
	
	protected LoginLocators loginLocators = new LoginLocators(); 
	
	public LoginNavigation(IExecutor executor) {
		super(executor, "");
	}
	
	public void login(String user) {
		super.open();
		executor.isPresent(loginLocators.login_button);
		
		switch(user) {
		case "super":
			executor.send_keys(loginLocators.user_input, "superuser");
			break;
		case "webgains":
			executor.send_keys(loginLocators.user_input, "iatuser");
			break;
		}
		executor.send_keys(loginLocators.pass_input, "muppetshow");
		executor.click(loginLocators.login_button);
		
	}
	
}
