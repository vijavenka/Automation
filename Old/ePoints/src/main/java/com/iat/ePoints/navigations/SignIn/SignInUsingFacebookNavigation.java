package com.iat.ePoints.Navigations.SignIn;

import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.MyAccount.MyProfileLocators;
import com.iat.ePoints.Locators.SignIn.LoginPageLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 03.01.14
 * Time: 09:22
 * To change this template use File | Settings | File Templates.
 */
public class SignInUsingFacebookNavigation extends AbstractPage {

    private LoginPageLocators locators_login = new LoginPageLocators();
    private MyProfileLocators locators_myAccount = new MyProfileLocators();

    public SignInUsingFacebookNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // 1 USER SIGN IN - handle NON-verified accounts for first time Facebook sign ups(RD-660) //////////////////////////
    public void enterEmailConectedWitFacebookAccount(String email) {
        executor.send_keys(locators_login.email_fld, email);
    }

    // 2 USER SIGN IN - handle NON-successful first time sign in via Facebook //////////////////////////////////////////
    public void checkCorrectnessOfFbDetails(String name, String lastName, String gender, String birthDate, String email) {
        assertEquals("Name is not correct", executor.getValue(locators_myAccount.nameReadOnlyAndTextField), name);
        assertEquals("Last name is no correct", executor.getValue(locators_myAccount.lastNameReadOnlyAndTextField), lastName);
        assertNotEquals("Gender is no correct", executor.getValue(locators_myAccount.genderReadonlyAndDDL), gender);
        assertNotEquals("Birth date is no correct", executor.getValue(locators_myAccount.dateOfBirthReadonlyAndTextField), birthDate);
        assertEquals("email is no correct", executor.getText(locators_myAccount.userEmailAddress), email);
    }

    // 4 USER SIGN IN - handle successful first time sign in via Facebook (RD-658) /////////////////////////////////////
    public void getFacebookIdAndCompareItWithDatabase() throws SQLException {
        JDBC jdbc = new JDBC("points_manager");
        String fbId = (executor.return_driver().manage().getCookieNamed("fbUserID").getValue());
        assertEquals("Id in Database is different from id send by facebook", jdbc.return_proper_db_value("SELECT * FROM points_manager.UserId WHERE user_id='" + jdbc.return_proper_db_value("SELECT id FROM points_manager.User WHERE email='iat.epoints.test@gmail.com'", 1) + "' AND userIdType='FACEBOOK'", 4), fbId);
        jdbc.close();
    }

}
