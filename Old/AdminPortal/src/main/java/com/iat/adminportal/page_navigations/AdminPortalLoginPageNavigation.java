package com.iat.adminportal.page_navigations;

import com.iat.adminportal.locators.AdminPortalLoginPageLocators;

public class AdminPortalLoginPageNavigation extends AbstractPage {

    protected AdminPortalLoginPageLocators locators = new AdminPortalLoginPageLocators();

    public AdminPortalLoginPageNavigation() {
        super("/");
    }

    public void open() {
        super.open();
        executor.is_element_present(locators.login_box);
    }

    public void enterLogin(String login) {
        executor.check(locators.user_name);
        executor.send_keys(locators.user_name, login);
    }

    public void enterPassword(String password) {
        executor.check(locators.password);
        executor.send_keys(locators.password, password);
    }

    public void clickLoginButton() {
//        System.out.print(executor.getText(locators.login_button));
        executor.click(locators.login_button);
    }

    public boolean checkLoginSuccess() {
        return executor.check_element_contains_text(locators.welcome_sign, "Welcome");
    }

    public boolean checkLogoutSuccess() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return executor.check_element_contains_text(locators.login_alert_div, "Logout Success!");
    }

    public boolean checkLoginFailure() {
        return executor.check_element_contains_text(locators.login_alert_div, "Login Failure!");
    }

    public boolean checkLoginPage() {
        if (executor.is_element_present(locators.user_name) &&
                executor.is_element_present(locators.password)) {
            return true;
        } else {
            return false;
        }
    }
}
