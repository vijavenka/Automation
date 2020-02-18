package com.iat.ePoints.Navigations.MyAccount;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */

import com.iat.ePoints.EnvironmentVariables;
import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.HeaderLocators;
import com.iat.ePoints.Locators.MyAccount.MyProfileLocators;
import com.iat.ePoints.Navigations.AbstractPage;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;

public class MyProfileNavigation extends AbstractPage {

    private MyProfileLocators locators_myprofile = new MyProfileLocators();
    private HeaderLocators locators_headers = new HeaderLocators();
    private EnvironmentVariables envVariables = new EnvironmentVariables();
    String env = envVariables.baseUrl;

    public MyProfileNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    public void goToMyProfileSite() {
        executor.click(locators_headers.myAccount);
    }

    public void checkVisibilityOfAccountSection() {
        assertTrue("my Account Section is not visible", executor.is_element_present(locators_myprofile.myAccountSection));
    }

    public void clickProfileLinkInDashboard() throws InterruptedException {
        Thread.sleep(2000);
        executor.click(locators_myprofile.myProfile);
    }

    public void checkVisibilityOfAccountDetailsSection() {
        assertTrue("Account details section Section is not visible", executor.is_element_present(locators_myprofile.accountDetailsSection));
    }

    public void checkContentOfAccountDetailsSection() {
        assertTrue("Section title is invisible", executor.is_element_present(locators_myprofile.accountDetailsTableSectionTitle));
        assertTrue("Email address is invisible", executor.is_element_present(locators_myprofile.userEmailAddress));
        assertTrue("Password set information is invisible", executor.is_element_present(locators_myprofile.userPassword));
        assertTrue("Email address edit button is invisible", executor.is_element_present(locators_myprofile.userEmailAddressEditButton));
        assertTrue("Password edit button nis invisible", executor.is_element_present(locators_myprofile.userPasswordEditButton));
    }

    public void checkVisibilityOfPersonalDetailsSection() {
        assertTrue("Personal details section Section is not visible", executor.is_element_present(locators_myprofile.personalDetailsSection));
    }

    public void checkContentOfPersonalDetailsSection() {
        assertTrue("Section title is invisible", executor.is_element_present(locators_myprofile.personalDetailsTableSectionTitle));
        assertTrue("Name is invisible", executor.is_element_present(locators_myprofile.nameReadOnlyAndTextField));
        assertTrue("Last name is invisible", executor.is_element_present(locators_myprofile.lastNameReadOnlyAndTextField));
        assertTrue("title is invisible", executor.is_element_present(locators_myprofile.titleReadonlyAndDDL));
        assertTrue("Gender is invisible", executor.is_element_present(locators_myprofile.genderReadonlyAndDDL));
        assertTrue("Date of birth is invisible", executor.is_element_present(locators_myprofile.dateOfBirthReadonlyAndTextField));
        assertTrue("Personal data edit button is invisible", executor.is_element_present(locators_myprofile.userPersonalDetailsEditbutton));
    }

    public void checkVisibilityOfContactDetailsSection() {
        assertTrue("Personal details section Section is not visible", executor.is_element_present(locators_myprofile.contactDetailsSection));
    }

    public void checkContentOfContactDetailsSection() {
        assertTrue("Section title is invisible", executor.is_element_present(locators_myprofile.contactDetailsTableSectionTitle));
        assertTrue("Phone number is invisible", executor.is_element_present(locators_myprofile.contactNumberReadOnlyAndTextField));
        assertTrue("House number is invisible", executor.is_element_present(locators_myprofile.houseNumberReadOnlyAndTextField));
        assertTrue("Street name is invisible", executor.is_element_present(locators_myprofile.streetNameReadOnlyAndTextField));
        assertTrue("City name is invisible", executor.is_element_present(locators_myprofile.cityNameReadOnlyAndTextField));
        assertTrue("County name is invisible", executor.is_element_present(locators_myprofile.countyNameReadOnlyAndTextField));
        assertTrue("Country name is invisible", executor.is_element_present(locators_myprofile.countrynameReadOnlyAndTextField));
        assertTrue("Post code is invisible", executor.is_element_present(locators_myprofile.postcodeReadOnlyAndTextField));
        assertTrue("User contact details edit button is invisible", executor.is_element_present(locators_myprofile.userContactDetailsEditbutton));
    }


    public void clickEmailEditButton() throws InterruptedException {
        executor.click(locators_myprofile.userEmailAddressEditButton);
        Thread.sleep(2000);// has to stay
    }

    public void enterNewEmail(String newEmail) {
        executor.send_keys(locators_myprofile.newEmailAddressTextfield, newEmail);
    }

    public void repeatNewEmail(String newEmail) {
        executor.send_keys(locators_myprofile.repeatNewEmailAddressTextField, newEmail);
    }

    public void clickSaveNewEmailButton() throws InterruptedException {
        executor.click(locators_myprofile.SaveNewEmailAddressButton);
        Thread.sleep(2000);// has to stay
    }

    public void checkIfSuccesAlertIsVisibleAfterEmailChanging() {
        assertTrue("Success alert is not visible", executor.is_element_present(locators_myprofile.changingEmailSuccessAlert));
    }

    public void checkNewEmailInDB(String oldEmail, String newEmail) throws InterruptedException, SQLException {
        Thread.sleep(2000);//has to stay
        JDBC jdbc = new JDBC("points_manager");
        assertEquals("Original email is incorrect in new email row", jdbc.return_proper_db_value("SELECT originalEmail FROM points_manager.User WHERE email = '" + newEmail + "'", 1), oldEmail);
        jdbc.close();
    }


    public void clickPasswordEditButton() throws InterruptedException {
        executor.click(locators_myprofile.userPasswordEditButton);
        Thread.sleep(2000);// has to stay
    }

    public void enterOldPassword(String oldPassword) {
        executor.send_keys(locators_myprofile.currentPasswordTwextField, oldPassword);
    }

    public void enterNewPassword(String newPassword) {
        executor.send_keys(locators_myprofile.newPasswordTwextField, newPassword);
    }

    public void repeatNewPassword(String repeatNewPassword) {
        executor.send_keys(locators_myprofile.repeatNewPasswordTwextField, repeatNewPassword);
    }

    public void clickSaveNewPasswordButton() throws InterruptedException {
        executor.click(locators_myprofile.saveNewPasswordButton);
        Thread.sleep(2000);// has to stay
    }

    public void checkIfSuccesAlertIsVisibleAfterPasswordChanging() {
        assertTrue("Success alert is not visible", executor.is_element_present(locators_myprofile.changingPasswordSuccessAlert));
    }


    public void clickPersonalDataEditButton() throws InterruptedException {
        executor.click(locators_myprofile.userPersonalDetailsEditbutton);
        Thread.sleep(2000);// has to stay
    }

    public void clearAllPersonalDataTextfields() throws InterruptedException {
        Thread.sleep(2000);// has to stay
        executor.clear(locators_myprofile.nameReadOnlyAndTextField);
        executor.clear(locators_myprofile.lastNameReadOnlyAndTextField);
        executor.clear(locators_myprofile.dateOfBirthReadonlyAndTextField);
    }

    public void enterNewFirstName(String Name) {
        executor.send_keys(locators_myprofile.nameReadOnlyAndTextField, Name);
    }

    public void enterNewLastName(String LastName) {
        executor.send_keys(locators_myprofile.lastNameReadOnlyAndTextField, LastName);
    }

    public void chooseNewTitle(String Title) {
        executor.selectOptionByValue(locators_myprofile.titleReadonlyAndDDL, Title);
    }

    public void chooseNewGender(String Gender) {
        executor.selectOptionByValue(locators_myprofile.genderReadonlyAndDDL, Gender);
    }

    public void enterNewDateOfBirth(String DateOfBirth) {
        executor.send_keys(locators_myprofile.dateOfBirthReadonlyAndTextField, DateOfBirth);
    }

    public void clickSaveNewPersonalDetailsDataButton() throws InterruptedException {
        executor.click(locators_myprofile.saveNewPersonalDataButton);
        Thread.sleep(2000);// has to stay
    }

    public void checkIfSuccesAlertIsVisibleAfterPersonalDataChanging() {
        assertTrue("Success alert is invisible", executor.is_element_present(locators_myprofile.changingPersonlaDataSuccessAlert));
    }

    public void logoutFromUserAccount() {
        executor.click(locators_headers.SignOut);
    }

    public void validateCorrectnessOfAllDataInUserPersonalDetails(String Name, String LastName, String Title, String Gender, String DateOfBirth) {
        assertEquals("Names are not equals - entered Name " + Name + " returned Name " + executor.getValue(locators_myprofile.nameReadOnlyAndTextField),
                Name, executor.getValue(locators_myprofile.nameReadOnlyAndTextField));
        assertEquals("Last names are not equals - entered Last Name " + LastName + " returned Last Name " + executor.getValue(locators_myprofile.lastNameReadOnlyAndTextField),
                LastName, executor.getValue(locators_myprofile.lastNameReadOnlyAndTextField));
        assertEquals("Titles are not equals - entered Title " + Title + " returned Title " + executor.getValue(locators_myprofile.titleReadonlyAndDDL),
                Title, executor.getValue(locators_myprofile.titleReadonlyAndDDL));
        assertEquals("Genders are not equals - entered Gender " + Gender + " returned Gender " + executor.getValue(locators_myprofile.genderReadonlyAndDDL),
                Gender, executor.getValue(locators_myprofile.genderReadonlyAndDDL));
        assertEquals("Birth dates are not equals - entered Birth Date " + DateOfBirth + " returned Birth Date " + executor.getValue(locators_myprofile.dateOfBirthReadonlyAndTextField),
                DateOfBirth, executor.getValue(locators_myprofile.dateOfBirthReadonlyAndTextField));
    }

    public void validateCorrectnessOfAllPersonalDataInDB(String Name, String LastName, String Title, String Gender, String DateOfBirth, String Email) throws ParseException {
        JDBC jdbc = new JDBC("points_manager");

        assertEquals("Names are not equals - entered Name " + Name + " returned Name " + jdbc.return_proper_db_value("SELECT firstName FROM points_manager.User WHERE email='" + Email + "'", 1),
                Name, jdbc.return_proper_db_value("SELECT firstName FROM points_manager.User WHERE email='" + Email + "'", 1));
        assertEquals("Last names are not equals - entered Last Name " + LastName + " returned Last Name " + jdbc.return_proper_db_value("SELECT lastName FROM points_manager.User WHERE email='" + Email + "'", 1),
                LastName, jdbc.return_proper_db_value("SELECT lastName FROM points_manager.User WHERE email='" + Email + "'", 1));
        assertEquals("Titles are not equals - entered Title " + Title + " returned title " + jdbc.return_proper_db_value("SELECT title FROM points_manager.User WHERE email='" + Email + "'", 1),
                Title, jdbc.return_proper_db_value("SELECT title FROM points_manager.User WHERE email='" + Email + "'", 1));
        assertEquals("Genders are not equals - entered Gender " + Gender + " returned Gender " + jdbc.return_proper_db_value("SELECT gender FROM points_manager.User WHERE email='" + Email + "'", 1),
                Gender, jdbc.return_proper_db_value("SELECT gender FROM points_manager.User WHERE email='" + Email + "'", 1));
        //Convert date needed in epoints to format stored in DB
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat nt = new SimpleDateFormat("yyyy-MM-dd");
        String BD = nt.format(ft.parse(DateOfBirth.replace("/", "-")));
        assertTrue("Birth dates are not equals - entered Birth Date " + BD + " returned Birth Date " + jdbc.return_proper_db_value("SELECT birthDate FROM points_manager.User WHERE email='" + Email + "'", 1),
                jdbc.return_proper_db_value("SELECT birthDate FROM points_manager.User WHERE email='" + Email + "'", 1).contains(BD));
    }


    public void clickContactDetailsEditButton() throws InterruptedException {
        executor.click(locators_myprofile.userContactDetailsEditbutton);
        Thread.sleep(2000);// has to stay
    }

    public void clearAllContactsDetailsfields() throws InterruptedException {
        Thread.sleep(2000);// has to stay
        executor.clear(locators_myprofile.contactNumberReadOnlyAndTextField);
        executor.clear(locators_myprofile.houseNumberReadOnlyAndTextField);
        executor.clear(locators_myprofile.streetNameReadOnlyAndTextField);
        executor.clear(locators_myprofile.cityNameReadOnlyAndTextField);
        executor.clear(locators_myprofile.countyNameReadOnlyAndTextField);
        executor.clear(locators_myprofile.countrynameReadOnlyAndTextField);
        executor.clear(locators_myprofile.postcodeReadOnlyAndTextField);
    }

    public void enterNewPhoneNumber(String Phone) {
        executor.send_keys(locators_myprofile.contactNumberReadOnlyAndTextField, Phone);
    }

    public void enterNewHouseNumber(String HouseNumber) {
        executor.send_keys(locators_myprofile.houseNumberReadOnlyAndTextField, HouseNumber);
    }

    public void enterNewStreetName(String Street) {
        executor.send_keys(locators_myprofile.streetNameReadOnlyAndTextField, Street);
    }

    public void enterNewCityName(String City) {
        executor.send_keys(locators_myprofile.cityNameReadOnlyAndTextField, City);
    }

    public void enterNewCountyName(String County) {
        executor.send_keys(locators_myprofile.countyNameReadOnlyAndTextField, County);
    }

    public void enterNewCountryName(String Country) {
        executor.send_keys(locators_myprofile.countrynameReadOnlyAndTextField, Country);
    }

    public void enterNewPostcode(String Postcode) {
        executor.send_keys(locators_myprofile.postcodeReadOnlyAndTextField, Postcode);
    }

    public void clickSaveNewContactDetailsDataButton() throws InterruptedException {
        executor.click(locators_myprofile.saveNewContactDataButton);
        Thread.sleep(2000);// has to stay
    }

    public void checkIfSuccesAlertIsVisibleAfterContactDetailsChanging() {
        assertTrue("Succes alert is invisible", executor.is_element_present(locators_myprofile.changingContactDataSuccessAlert));
    }

    public void validateCorrectnessOfAllDataInUsercontactDetails(String Phone, String HouseNumber, String Street, String City, String County, String Country, String Postcode) {
        assertEquals("Phones are not equals - entered Phone number " + Phone + " returned Phone number " + executor.getValue(locators_myprofile.contactNumberReadOnlyAndTextField),
                Phone, executor.getValue(locators_myprofile.contactNumberReadOnlyAndTextField));
        assertEquals("House numbers are not equals - entered  " + HouseNumber + " returned House number " + executor.getValue(locators_myprofile.houseNumberReadOnlyAndTextField),
                HouseNumber, executor.getValue(locators_myprofile.houseNumberReadOnlyAndTextField));
        assertEquals("Cities are not equals - entered  City name" + City + " returned City name " + executor.getValue(locators_myprofile.cityNameReadOnlyAndTextField),
                City, executor.getValue(locators_myprofile.cityNameReadOnlyAndTextField));
        assertEquals("Couties are not equals - entered County " + County + " returned County " + executor.getValue(locators_myprofile.countyNameReadOnlyAndTextField),
                County, executor.getValue(locators_myprofile.countyNameReadOnlyAndTextField));
        assertEquals("Countries are not equals - entered Country " + Country + " returned " + executor.getValue(locators_myprofile.countrynameReadOnlyAndTextField),
                Country, executor.getValue(locators_myprofile.countrynameReadOnlyAndTextField));
        assertEquals("Postcodes are not equals - entered Postcode " + Postcode + " returned Postcode " + executor.getValue(locators_myprofile.postcodeReadOnlyAndTextField),
                Postcode, executor.getValue(locators_myprofile.postcodeReadOnlyAndTextField));
        assertEquals("Streets are not equals - entered Street " + Street + " returned Street " + executor.getValue(locators_myprofile.streetNameReadOnlyAndTextField),
                Street, executor.getValue(locators_myprofile.streetNameReadOnlyAndTextField));
    }

    public void validateCorrectnessOfAllContactDataInDB(String Phone, String HouseNumber, String Street, String City, String County, String Country, String Postcode, String Email) {
        JDBC jdbc = new JDBC("points_manager");

        assertEquals("Phones are not equals - entered Phone number " + Phone + " returned Phone number " + jdbc.return_proper_db_value("SELECT mobileNumber FROM points_manager.User WHERE email='" + Email + "'", 1),
                Phone, jdbc.return_proper_db_value("SELECT mobileNumber FROM points_manager.User WHERE email='" + Email + "'", 1));
        assertEquals("House numbers are not equals - entered  " + HouseNumber + " returned House number " + jdbc.return_proper_db_value("SELECT houseNumberOrName FROM points_manager.User WHERE email='" + Email + "'", 1),
                HouseNumber, jdbc.return_proper_db_value("SELECT houseNumberOrName FROM points_manager.User WHERE email='" + Email + "'", 1));
        assertEquals("Cities are not equals - entered  City name" + City + " returned City name " + jdbc.return_proper_db_value("SELECT townOrCity FROM points_manager.User WHERE email='" + Email + "'", 1),
                City, jdbc.return_proper_db_value("SELECT townOrCity FROM points_manager.User WHERE email='" + Email + "'", 1));
        assertEquals("Couties are not equals - entered County " + County + " returned County " + jdbc.return_proper_db_value("SELECT county FROM points_manager.User WHERE email='" + Email + "'", 1),
                County, jdbc.return_proper_db_value("SELECT county FROM points_manager.User WHERE email='" + Email + "'", 1));
        assertEquals("Countries are not equals - entered Country " + Country + " returned " + jdbc.return_proper_db_value("SELECT country FROM points_manager.User WHERE email='" + Email + "'", 1),
                Country, jdbc.return_proper_db_value("SELECT country FROM points_manager.User WHERE email='" + Email + "'", 1));
        assertEquals("Postcodes are not equals - entered Postcode " + Postcode + " returned Postcode " + jdbc.return_proper_db_value("SELECT postcode FROM points_manager.User WHERE email='" + Email + "'", 1),
                Postcode, jdbc.return_proper_db_value("SELECT postcode FROM points_manager.User WHERE email='" + Email + "'", 1));
        assertEquals("Streets are not equals - entered Street " + Street + " returned Street " + jdbc.return_proper_db_value("SELECT street FROM points_manager.User WHERE email='" + Email + "'", 1),
                Street, jdbc.return_proper_db_value("SELECT street FROM points_manager.User WHERE email='" + Email + "'", 1));
    }


    public void clikCancelEmailEditionButton(){
        executor.click(locators_myprofile.cancelNewEmailCreationButton);
    }

    public void checkVisibilityOfSaveEmailButton() throws InterruptedException {
        Thread.sleep(2000);// has to stay
        assertFalse("Save new email button is visible but shouldn't", executor.is_element_present(locators_myprofile.SaveNewEmailAddressButton));
    }

    public void clikCancelPasswordEditionButton(){
        executor.click(locators_myprofile.cancelPaswordChanging);
    }

    public void checkVisibilityOfSavePasswordButton() throws InterruptedException {
        Thread.sleep(2000);// has to stay
        assertFalse("Save new password button is visible but shouldn't", executor.is_element_present(locators_myprofile.saveNewPasswordButton));
    }

    public void clikCancelPersonalDetailsEditionButton(){
        executor.click(locators_myprofile.cancelSavingNewPersonalDataButton);
    }

    public void checkVisibilityOfSavePersonalDetailsButton() throws InterruptedException {
        Thread.sleep(2000);// has to stay
        assertFalse("Save new personal details button is visible but shouldn't", executor.is_element_present(locators_myprofile.saveNewPersonalDataButton));
    }

    public void clikCancelContactDetailsEditionButton(){
        executor.click(locators_myprofile.cancelSavingNewContactButton);
    }

    public void checkVisibilityOfSaveContactDetailsButton() throws InterruptedException {
        Thread.sleep(2000);// has to stay
        assertFalse("Save new contact details button is visible but shouldn't", executor.is_element_present(locators_myprofile.saveNewContactDataButton));
    }

    // 7 allow new FACEBOOK users to create epoints password (RD-1110) /////////////////////////////////////////////////
    public void clickPasswordEditButtonNotSpecified(){
        executor.click(locators_myprofile.userPasswordEditButtonGrey);
    }

    // 8 EPOINTS - facebook registered user changes email address (RD-1111) ////////////////////////////////////////////
    public void followConfirmationLinkCreatedUsingToken(String userEmail) throws InterruptedException {
        Thread.sleep(2000);
        JDBC jdbc = new JDBC("points_manager");
        //Create proper link
        String firstLinkPart = env + "/mail/activate/";
        //Read proper token from DB
        String tokenValue = jdbc.return_proper_db_value("SELECT tokenValue FROM points_manager.UserToken WHERE user_id='" + jdbc.return_proper_db_value(jdbc.return_proper_query("returnId", userEmail), 1) + "' AND tokenType = 'EMAIL_CHANGE' ORDER BY id DESC", 1);
        System.out.println(tokenValue);
        //Just use such created link
        executor.return_driver().get(firstLinkPart + tokenValue);
    }
}