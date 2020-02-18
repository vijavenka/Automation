package com.iat.adminportal.steps;

import com.iat.adminportal.page_navigations.AdminPortalLoginPageNavigation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertTrue;

public class AdminPortalLoginSteps {

    private AdminPortalLoginPageNavigation loginPage;

    public AdminPortalLoginSteps() {

    }

    @Given("^User is on login page$")
    public void adminUserIsOnLoginPage() {
        loginPage = new AdminPortalLoginPageNavigation();
        loginPage.open();
    }

    @Given("^Admin user is successfully logged in$")
    public void adminUserIsLoggedOn() {
        loginPage = new AdminPortalLoginPageNavigation();
        loginPage.open();
        loginPage.enterLogin("i.superuser");
        loginPage.enterPassword("theMightyBlues");
        loginPage.clickLoginButton();
        assertTrue(loginPage.checkLoginSuccess());
    }

    @Given("^Log in to admin portal as user '([^']*)' with password '([^']*)'$")
    public void LoginToAdminPortal(String login, String password) {
        adminUserIsOnLoginPage();
        loginPage.enterLogin(login);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        assertTrue("Not Logged In", loginPage.checkLoginSuccess());

    }

    @When("^He enters '([^']*)' as login$")
    public void adminEntersLogin(String login) {
        loginPage.enterLogin(login);
    }

    @When("^He enters '([^']*)' as password$")
    public void adminEntersPassword(String password) {
        loginPage.enterPassword(password);
    }

    @And("^He clicks on login button$")
    public void adminClicksOnLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("^Admin user is correctly logged in$")
    public void adminCorrectlyLoggedIn() {
        assertTrue(loginPage.checkLoginSuccess());
    }

    @Then("^Login is unsuccessful$")
    public void adminNotLoggedIn() {
        assertTrue(loginPage.checkLoginFailure());
    }

    @Then("^Admin user is redirected to login page$")
    public void adminUserIsRedirectedToLoginPage() {
        loginPage = new AdminPortalLoginPageNavigation();
        assertTrue(loginPage.checkLoginPage());
    }
}
