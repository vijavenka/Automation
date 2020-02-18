package com.iat.ePoints.Navigations.Registration;

import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Locators.HomePageLocators;
import com.iat.ePoints.Locators.MyAccount.MyProfileLocators;
import com.iat.ePoints.Locators.Registration.RegisterPageLocators;
import com.iat.ePoints.Locators.SignIn.LoginPageLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 02.01.14
 * Time: 14:39
 * To change this template use File | Settings | File Templates.
 */
public class JoinUsingFacebookNavigation extends AbstractPage {

    private LoginPageLocators locators_login = new LoginPageLocators();
    private HeaderLocators locators_header = new HeaderLocators();
    private MyProfileLocators locators_myAccount = new MyProfileLocators();
    private RegisterPageLocators locators_registration = new RegisterPageLocators();
    private HomePageLocators locators_home = new HomePageLocators();
    private JDBC jdbc = new JDBC("points_manager");

    public JoinUsingFacebookNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // 1 Check if Join interface has proper facebook option ////////////////////////////////////////////////////////////
    public void checkIfFacebookOptionIsAvailableOnMainPage() {
        assertTrue("Register via facebook button is no available", executor.is_element_present(locators_home.register_via_facebook_button));
    }

    public void checkVisibilityOfSignInWithFacebookButton() {
        assertTrue("Facebook join option is no available", executor.is_element_present(locators_login.facebookJoinOption));
    }

    public void clickSignInWithFacebookButton() throws InterruptedException {
        executor.click(locators_login.facebookJoinOption);

        //switch to properbrowse window
        executor.handleMultipleWindows("Facebook");
    }

    public void checkContentOfFacebbokLoginWindow() {
        assertTrue("Email text field is no available", executor.is_element_present(locators_login.facebookLoginEmailTextfield));
        assertTrue("Password text field is no available", executor.is_element_present(locators_login.facebookLoginPasswordTextField));
        assertTrue("Do not log out me checkbox is no available", executor.is_element_present(locators_login.facebookLoginDoNotLogoutMeCheckbox));
        assertTrue("SignIn button is no available", executor.is_element_present(locators_login.facebookLoginButton));
        assertTrue("Cancel button is no available", executor.is_element_present(locators_login.facebookCancelButton));
    }

    // 2 Check if user can join using facebook and all needed data are properly download to epoints ////////////////////
    public void enterFacebokCredentials(String email, String password) throws InterruptedException {
        executor.send_keys(locators_login.facebookLoginEmailTextfield, email);
        executor.send_keys(locators_login.facebookLoginPasswordTextField, password);
        executor.click(locators_login.facebookLoginButton);
        //switch to proper browse window
        executor.handleMultipleWindows("epoints");
        Thread.sleep(4000);//has to stay
        executor.return_driver().navigate().refresh();
    }

    public void checkIfUserHas50Points(String pointsNumber) {
        assertEquals("User received different number of points than 50", pointsNumber, executor.getText(locators_header.pointsCounter));
    }

    public void checkIfConfirmationEmailMessageIsInvisible() {
        assertFalse("Confirmation email message is visible but should not", executor.is_element_present(locators_registration.checkYourEmailMessage));
    }

    //TODO delete this after consultation now it is not displayed
    public void checkVisibilityOfCongratulationMessage() {
        //assertTrue("Well done page is not visible", executor.is_element_present(locators_registration.allDoneMessage));
    }

    public void checkIfUserAvatarIsVisible() {
        assertTrue("User avatar is not visible", executor.is_element_present(locators_login.facebookAvatar));
    }

    public void goToUserProfile() throws InterruptedException {
        executor.click(locators_header.myAccount);
        Thread.sleep(2000);
        executor.click(locators_myAccount.myProfile);
    }

    public void checkCorrectnessOfFbDetails(String name, String lastName, String gender, String birthDate, String email) {
        assertEquals("Name is not correct", executor.getValue(locators_myAccount.nameReadOnlyAndTextField), name);
        assertEquals("Last name is no correct", executor.getValue(locators_myAccount.lastNameReadOnlyAndTextField), lastName);
        assertEquals("Gender is no correct", executor.getValue(locators_myAccount.genderReadonlyAndDDL), gender);
        assertEquals("Birth date is no correct", executor.getValue(locators_myAccount.dateOfBirthReadonlyAndTextField), birthDate);
        assertEquals("email is no correct", executor.getText(locators_myAccount.userEmailAddress), email);
    }

    public void checkIfUserDataAreProperlyStoredInDb(String name, String lastname, String gender, String birthDate, String email) {
        assertEquals("Name is different in db", jdbc.return_proper_db_value(jdbc.return_proper_query("returnUser", email), 8), name);
        assertEquals("Last name is different in db", jdbc.return_proper_db_value(jdbc.return_proper_query("returnUser", email), 12), lastname);
        assertEquals("Gender is different in db", jdbc.return_proper_db_value(jdbc.return_proper_query("returnUser", email), 9), gender);
        assertEquals("birthDate is different in db", jdbc.return_proper_db_value(jdbc.return_proper_query("returnUser", email), 3), birthDate);
        assertEquals("email is different in db", jdbc.return_proper_db_value(jdbc.return_proper_query("returnUser", email), 7), email);
    }

    public void checkIfUserWasRecognizedAsFacebookUser() {
        assertTrue("User was not recognised as facebook user", jdbc.return_proper_db_value("SELECT * FROM points_manager.UserId WHERE user_id='" + jdbc.return_proper_db_value("SELECT id FROM points_manager.User WHERE email='emailwybitnietestowy@gmail.com'", 1) + "' AND userIdType='FACEBOOK'", 4).length() == 15);
    }

    public void closeDBConnection () throws SQLException {
        jdbc.close();
    }
}
