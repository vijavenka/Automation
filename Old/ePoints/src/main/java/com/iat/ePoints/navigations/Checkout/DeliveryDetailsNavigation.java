package com.iat.ePoints.Navigations.Checkout;

import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Checkout.DeliveryDetailsLocators;
import com.iat.ePoints.Locators.MyAccount.MyProfileLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.junit.Assert;

import java.util.Random;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 05.12.13
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */
public class DeliveryDetailsNavigation extends AbstractPage {

    protected DeliveryDetailsLocators locators_delivery = new DeliveryDetailsLocators();
    protected MyProfileLocators locators_myprofile = new MyProfileLocators();


    public DeliveryDetailsNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    //Scenario: User opens delivery details page////////////////////////////////////////////////////////////////////////
    public void authenticateUser() throws InterruptedException {
        //if(executor.is_element_present(locators_delivery.signInWindow)){
        executor.clear(locators_delivery.emailField);
        executor.clear(locators_delivery.passwordField);
        executor.send_keys(locators_delivery.emailField, "emailwybitnietestowy@gmail.com");
        executor.send_keys(locators_delivery.passwordField, "Delete777");
        executor.click(locators_delivery.signInButton);
        Thread.sleep(2000);
        //}
    }

    public void checkContentOfDeliveryDetailsPage() throws InterruptedException {
        Thread.sleep(2000);//TODO - - -
        assertTrue("Page title is invisible", executor.is_element_present(locators_delivery.selectedRewardsTitle));
        assertTrue("Addresses form is invisible", executor.is_element_present(locators_delivery.addressesForm));
        assertTrue("Points form is invisible", executor.is_element_present(locators_delivery.pointsForm));
        assertTrue("Delivery information is invisible", executor.is_element_present(locators_delivery.deliveryInfo));


    }

    public void clickBackButton() {
        Random rand = new Random();
        switch (rand.nextInt(2) + 1) {
            case 1:
                executor.click(locators_delivery.back_btn);
                break;
            case 2:
                executor.click(locators_delivery.changeSelectionEdit);
                break;
        }
    }

    //Scenario:e User is sure that address from user account is properly copied in delivery details/////////////////////

    public void checkIfUserContactDataWasCopiedProperly(String Name, String LastName, String HouseNumber, String Street, String City, String County, String Country, String PostCode) {
        assertEquals("Name and last name is no correct", executor.getText(locators_delivery.firstNameLastName), Name + " " + LastName);
        assertEquals("Street name and House name is no correct", executor.getText(locators_delivery.streetHouse), HouseNumber + " " + Street);
        assertEquals("City name is no correct", executor.getText(locators_delivery.town), City);
        assertEquals("County name is incorrect", executor.getText(locators_delivery.county), County);
        assertEquals("Country name is incorrect", executor.getText(locators_delivery.country), Country);
        assertEquals("Postcode is incorrect", executor.getText(locators_delivery.postcode), PostCode);
    }

    //Scenario: User can add new address and replace his account contact data in delivery details section///////////////
    public void clickAddNewAddressButton() {
        executor.click(locators_delivery.adANewAddress);
    }

    public void enterNeededData(String Name, String LastName, String HouseNumber, String Street, String City, String County, String Country, String PostCode) {
        executor.click(locators_delivery.enterAddressManualy);

        executor.send_keys(locators_delivery.firstNameAddNew, Name);
        executor.send_keys(locators_delivery.lastNameAddNew, LastName);
        executor.send_keys(locators_delivery.streetNameAddNew, Street);
        executor.send_keys(locators_delivery.houseNoAddNew, HouseNumber);
        executor.send_keys(locators_delivery.townAddNew, City);
        executor.send_keys(locators_delivery.countyAddNew, County);
        executor.send_keys(locators_delivery.countryAddNew, Country);
        executor.send_keys(locators_delivery.postcodeAddNew, PostCode);

        executor.click(locators_delivery.useAsContact);

    }

    public void confirmChanges() {
        executor.click(locators_delivery.next_btn);
    }

    public void checkCorrectnessOfNeededDetails(String Name, String LastName, String HouseNumber, String Street, String City, String County, String Country, String Postcode) {
        Assert.assertEquals("Names are not equals - entered Name " + Name + " returned Name " + executor.getValue(locators_myprofile.nameReadOnlyAndTextField),
                Name, executor.getValue(locators_myprofile.nameReadOnlyAndTextField));
        Assert.assertEquals("Last names are not equals - entered Last Name " + LastName + " returned Last Name " + executor.getValue(locators_myprofile.lastNameReadOnlyAndTextField),
                LastName, executor.getValue(locators_myprofile.lastNameReadOnlyAndTextField));
        Assert.assertEquals("House numbers are not equals - entered  " + HouseNumber + " returned House number " + executor.getValue(locators_myprofile.houseNumberReadOnlyAndTextField),
                HouseNumber, executor.getValue(locators_myprofile.houseNumberReadOnlyAndTextField));
        Assert.assertEquals("Cities are not equals - entered  City name" + City + " returned City name " + executor.getValue(locators_myprofile.cityNameReadOnlyAndTextField),
                City, executor.getValue(locators_myprofile.cityNameReadOnlyAndTextField));
        Assert.assertEquals("Couties are not equals - entered County " + County + " returned County " + executor.getValue(locators_myprofile.countyNameReadOnlyAndTextField),
                County, executor.getValue(locators_myprofile.countyNameReadOnlyAndTextField));
        Assert.assertEquals("Countries are not equals - entered Country " + Country + " returned " + executor.getValue(locators_myprofile.countrynameReadOnlyAndTextField),
                Country, executor.getValue(locators_myprofile.countrynameReadOnlyAndTextField));
        Assert.assertEquals("Postcodes are not equals - entered Postcode " + Postcode + " returned Postcode " + executor.getValue(locators_myprofile.postcodeReadOnlyAndTextField),
                Postcode, executor.getValue(locators_myprofile.postcodeReadOnlyAndTextField));
        Assert.assertEquals("Streets are not equals - entered Street " + Street + " returned Street " + executor.getValue(locators_myprofile.streetNameReadOnlyAndTextField),
                Street, executor.getValue(locators_myprofile.streetNameReadOnlyAndTextField));
    }

    //
    public void clickNextButton() {
        executor.click(locators_delivery.next_btn);
    }

    public void checkIfProperPage() {
        assertTrue("Page title is invisible", executor.is_element_present(locators_delivery.selectedRewardsTitle));
    }
}
