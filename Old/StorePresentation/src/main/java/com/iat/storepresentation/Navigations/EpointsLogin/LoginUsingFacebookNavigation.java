package com.iat.storepresentation.Navigations.EpointsLogin;

import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Locators.ShopHome.HomePageLocators;
import com.iat.storepresentation.Locators.TransitionPage.TransitionPageLocators;
import com.iat.storepresentation.Navigations.AbstractPage;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 08.01.14
 * Time: 12:27
 * To change this template use File | Settings | File Templates.
 */
public class LoginUsingFacebookNavigation extends AbstractPage {

    private HomePageLocators locators_home = new HomePageLocators();
    private TransitionPageLocators locators_transition = new TransitionPageLocators();


    public LoginUsingFacebookNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }
    // USER SIGN IN - add Facebook option to sign in interface for WLS (RD-657) ////////////////////////////////////////
    public void openLoginPanel() throws InterruptedException {
        Thread.sleep(4000);
        executor.return_driver().switchTo().defaultContent();
        executor.return_driver().switchTo().frame(executor.get_element(locators_home.balanceIframe));
        executor.click(locators_home.signInToEpoints);

    }

    public void checkIfFacebookLoginOptionIsAvaliable() throws InterruptedException {
        Thread.sleep(4000);
        executor.return_driver().switchTo().defaultContent();
        executor.return_driver().switchTo().frame(2);
        assertTrue("Login using Facebook credentials is no available", executor.is_element_present(locators_home.loginUsingFacebookCredentials));
    }

    public void closeLoginPanel(){
        executor.return_driver().switchTo().defaultContent();
        executor.click(locators_home.closeLoginPanel);
    }

    public void clickBuyButton(){
        executor.click(locators_home.basicProductBuyButton);
    }

    public void checkIfFacebookLoginIsAvailableonTransitionPage() throws InterruptedException {
        Thread.sleep(4000);
        executor.handleMultipleWindows("Purchase from");
        executor.return_driver().switchTo().frame(1);
        assertTrue("Login using Facebook credentials is no available",executor.is_element_present(locators_transition.signInWithFacebook));
    }

    public void enterLoginCrenentials(String email,String  password) throws InterruptedException {
        Thread.sleep(4000);
        executor.return_driver().switchTo().defaultContent();
        executor.return_driver().switchTo().frame(2);
        executor.send_keys(locators_home.emailAdress,email);
        executor.send_keys(locators_home.password, password);
    }

    public void clickSignInButtonInLoginPanel(){
        executor.click(locators_home.signInButton);
    }

    public void checkIfUserIsCorrectlyLoggedIn(){
        executor.return_driver().switchTo().defaultContent();
        executor.return_driver().switchTo().frame(executor.get_element(locators_home.headerIframe));
        assertTrue("Welcome username is invisible", executor.is_element_present(locators_home.hiUserName));
    }

    public void clickSignOutButton(){
        executor.click(locators_home.signOut);
    }


    public void clickSignInWithFacebookButton() throws InterruptedException {
        Thread.sleep(4000);
        executor.return_driver().switchTo().defaultContent();
        executor.return_driver().switchTo().frame(2);
        executor.click(locators_home.loginUsingFacebookCredentials);

        //switch to properbrowse window
        executor.return_driver().switchTo().defaultContent();
        executor.handleMultipleWindows("Facebook");
    }

    public void enterFacebokCredentials(String email, String password) throws InterruptedException {
        executor.send_keys(locators_home.facebookLoginEmailTextfield, email);
        executor.send_keys(locators_home.facebookLoginPasswordTextField, password);
        executor.click(locators_home.facebookLoginButton);


        //switch to properbrowse window
        executor.handleMultipleWindows("Home of Store");
        Thread.sleep(5000);
    }

}
