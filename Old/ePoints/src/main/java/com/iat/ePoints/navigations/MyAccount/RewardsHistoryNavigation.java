package com.iat.ePoints.Navigations.MyAccount;

/**
 * Created with IntelliJ IDEA.
 * User: miwanczyk
 * Date: 20.06.13
 * Time: 22:17
 * To change this template use File | Settings | File Templates.
 */


import com.iat.ePoints.Database.JDBC;
import com.iat.ePoints.Executors.IExecutor;
import com.iat.ePoints.Locators.Checkout.DeliveryDetailsLocators;
import com.iat.ePoints.Locators.MyAccount.MyProfileLocators;
import com.iat.ePoints.Locators.Spend.ManageItemPageLocators;
import com.iat.ePoints.Navigations.AbstractPage;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

public class RewardsHistoryNavigation extends AbstractPage {

    private MyProfileLocators locators_profile = new MyProfileLocators();
    private ManageItemPageLocators locators_itempage = new ManageItemPageLocators();
    private DeliveryDetailsLocators locators_delivery = new DeliveryDetailsLocators();
    private MyActivityNavigation activityNavi = new MyActivityNavigation(executor);

    public RewardsHistoryNavigation(IExecutor executor) {
        super(executor, "");
    }

    public void open() {
        super.open();
    }

    // scenario: Checking empty rewards history list////////////////////////////////////////////////////////////////////
    public void checkIfRewardHistoryPageIsEmpty() {
        assertFalse("Date of bouhght is visible but should not", executor.is_element_present(locators_profile.basicDateTable));
        assertFalse("Picture of bouhght is visible but should not", executor.is_element_present(locators_profile.basicRewardPictureTable));
        assertFalse("Description of bouhght is visible but should not", executor.is_element_present(locators_profile.basicRewardDescriptionTable));
        assertFalse("Epoints price of bouhght is visible but should not", executor.is_element_present(locators_profile.epointsUsed));
        assertFalse("Delivery details of bouhght is visible but should not", executor.is_element_present(locators_profile.basicDeliveryDetailsTable));
        assertFalse("Contact is visible but should not", executor.is_element_present(locators_profile.basicContactUsTable));

        assertTrue("No rewards information is invisible", executor.is_element_present(locators_profile.noRewardsInformation));
    }

    // scenario: Checking empty rewards history list////////////////////////////////////////////////////////////////////

    private List<WebElement> rows;
    private List<WebElement> elements;

    public void checkVisibilityOfTableWithBoughtProducts() {
        rows = executor.getWebElementsList(locators_profile.basicTableRowLocator);
        assertTrue("Table with products is invisible", executor.is_element_present(locators_profile.basicTableRowLocator));
    }

    public void checkVisibilityOfRewardDate() {
        rows = executor.getWebElementsList(locators_profile.basicTableRowLocator);
        elements = executor.getWebElementsList(locators_profile.basicDateTable);
        assertEquals("Reward date elements number is no correct", rows.size(), elements.size());
    }

    public void checkVisibilityOfProductImage() {
        rows = executor.getWebElementsList(locators_profile.basicTableRowLocator);
        elements = executor.getWebElementsList(locators_profile.basicRewardPictureTable);
        assertEquals("Picture elements number is no correct", rows.size(), elements.size());
    }

    public void checkVisibilityOfProductTitle() {
        rows = executor.getWebElementsList(locators_profile.basicTableRowLocator);
        elements = executor.getWebElementsList(locators_profile.basicRewardDescriptionTable);
        assertEquals("Title elements number is no correct", rows.size(), elements.size());
    }

    public void checkVisibilityOfePointsUsed() {
        rows = executor.getWebElementsList(locators_profile.basicTableRowLocator);
        elements = executor.getWebElementsList(locators_profile.epointsUsed);
        assertEquals("epoints needed elements number is no correct", rows.size(), elements.size());
    }

    public void checkVisibilityOfDeliveryDetails() {
        rows = executor.getWebElementsList(locators_profile.basicTableRowLocator);
        elements = executor.getWebElementsList(locators_profile.basicDeliveryDetailsTable);
        assertEquals("Delivery details elements number is no correct", rows.size(), elements.size());
    }

    public void checkVisibilityOfContactUsLink() {
        rows = executor.getWebElementsList(locators_profile.basicTableRowLocator);
        elements = executor.getWebElementsList(locators_profile.basicContactUsTable);
        assertEquals("Contact links number is no correct", rows.size(), elements.size());
    }

    // scenario: Checking if in reward history module data are properly written (product name, delivery details)////////
    private String productName;
    private String nameLastName;
    private String streetNameHouseNo;
    private String cityName;
    private String countyName;
    private String countryName;
    private String postCode;

    public void rememberProductName() {
        productName = executor.getText(locators_itempage.basketSmallWindowProductNameAndLink);
    }

    public void rememberDeliveryDetails() {
        nameLastName = executor.getText(locators_delivery.firstNameLastName);
        streetNameHouseNo = executor.getText(locators_delivery.streetHouse);
        cityName = executor.getText(locators_delivery.town);
        countyName = executor.getText(locators_delivery.county);
        countryName = executor.getText(locators_delivery.country);
        postCode = executor.getText(locators_delivery.postcode);
    }

    public void compareAllDataInRewardsHistory() {
        executor.return_driver().navigate().refresh();
        assertEquals("Product names are no equal", productName, executor.getText(locators_profile.basicRewardDescriptionTable).replace("1 x ", ""));
        assertTrue("Firs and last names are no equal", executor.getText(locators_profile.basicDeliveryDetailsTable).contains(nameLastName));
        String[] houseAndStreet = streetNameHouseNo.split(" ");
        assertTrue("Street name are no equal", executor.getText(locators_profile.basicDeliveryDetailsTable).contains(houseAndStreet[0]));
        assertTrue("House number are no equal", executor.getText(locators_profile.basicDeliveryDetailsTable).contains(houseAndStreet[1]));
        //there is no city
        //assertTrue("City names are no equal",executor.getText(locators_profile.basicDeliveryDetailsTable).contains(cityName));
        assertTrue("County names are no equal", executor.getText(locators_profile.basicDeliveryDetailsTable).contains(countyName));
        assertTrue("Country names are no equal", executor.getText(locators_profile.basicDeliveryDetailsTable).contains(countryName));
        assertTrue("Post code values are no equal", executor.getText(locators_profile.basicDeliveryDetailsTable).contains(postCode));
    }

    // scenario: Checking if detailed page of bought product can be reached correctly///////////////////////////////////
    public void clickChosenProductNameAndRememberItsName() {
        List<WebElement> items = executor.getWebElementsList(locators_profile.basicRewardDescriptionTable);
        int which = executor.return_random_value(items.size());
        productName = executor.getText(items.get(which)).replace("1 x ", "");
        executor.click(items.get(which));
    }

    public void checkPageTitleAndProductName() {
        assertEquals("Page title is incorrect", executor.return_driver().getTitle(), "Spend your epoints on " + productName + " | epoints");
    }

    // Checking rewards history list content
    public void compareHistryListContentWithDB(String userEmail) throws SQLException, ParseException, InterruptedException {
        Thread.sleep(5000);
        int rowNumber = executor.getWebElementsList(locators_profile.basicRowLocator).size();

        JDBC jdbc = new JDBC("points_manager");
        jdbc.get_all_query_content("SELECT * FROM points_manager.Points WHERE status='REDEEMED' and userId='"+jdbc.return_proper_db_value("SELECT id FROM points_manager.User WHERE email='"+userEmail+"'",1)+"' ORDER BY id DESC");
        List<WebElement> dates = executor.getWebElementsList(locators_profile.basicDateTable);
        List<WebElement> descriptions = executor.getWebElementsList(locators_profile.basicRewardDescriptionTable);
        List<WebElement> addresses = executor.getWebElementsList(locators_profile.basicDeliveryDetailsTable);
        for(int i=0; i<rowNumber-1; i++){
        /*
            System.out.println(jdbc.get_value_of_proper_row(true,2));
            System.out.println(activityNavi.changeDatesFormats(executor.getText(dates.get(i)),"UI->DB"));
            System.out.println("-----");
            System.out.println(jdbc.get_value_of_proper_row(false,4));
            System.out.println(executor.getText(descriptions.get(i)));
        */
            assertTrue("Date is inproper for row " + i, jdbc.get_value_of_proper_row(true,2).contains(activityNavi.changeDatesFormats(executor.getText(dates.get(i)),"UI->DB")));
            assertEquals("Redemption product name is inproper for row " + i, jdbc.get_value_of_proper_row(false,4).replace(" ",""), executor.getText(descriptions.get(i)).replace(" ",""));

            //TODO resolve this by joins now it is very slow 2m for test
            assertTrue("Addres does not contains proper user name and last name for row " + i, executor.getText(addresses.get(i)).contains(jdbc.return_proper_db_value("SELECT name FROM points_manager.RedemptionOrder WHERE id='"+jdbc.return_proper_db_value("SELECT orderId FROM points_manager.Product WHERE id='"+jdbc.get_value_of_proper_row(false,13)+"'",1)+"'",1)));
            assertTrue("Addres does not contains proper house number for row " + i, executor.getText(addresses.get(i)).contains(jdbc.return_proper_db_value("SELECT address1 FROM points_manager.RedemptionOrder WHERE id='"+jdbc.return_proper_db_value("SELECT orderId FROM points_manager.Product WHERE id='"+jdbc.get_value_of_proper_row(false,13)+"'",1)+"'",1)));
            assertTrue("Addres does not contains proper street name for row " + i, executor.getText(addresses.get(i)).contains(jdbc.return_proper_db_value("SELECT address2 FROM points_manager.RedemptionOrder WHERE id='"+jdbc.return_proper_db_value("SELECT orderId FROM points_manager.Product WHERE id='"+jdbc.get_value_of_proper_row(false,13)+"'",1)+"'",1)));
            assertTrue("Addres does not contains proper county name for row " + i, executor.getText(addresses.get(i)).contains(jdbc.return_proper_db_value("SELECT county FROM points_manager.RedemptionOrder WHERE id='"+jdbc.return_proper_db_value("SELECT orderId FROM points_manager.Product WHERE id='"+jdbc.get_value_of_proper_row(false,13)+"'",1)+"'",1)));
            assertTrue("Addres does not contains proper country name for row " + i, executor.getText(addresses.get(i)).contains(jdbc.return_proper_db_value("SELECT country FROM points_manager.RedemptionOrder WHERE id='"+jdbc.return_proper_db_value("SELECT orderId FROM points_manager.Product WHERE id='"+jdbc.get_value_of_proper_row(false,13)+"'",1)+"'",1)));
            assertTrue("Addres does not contains proper postcode for row " + i, executor.getText(addresses.get(i)).contains(jdbc.return_proper_db_value("SELECT postcode FROM points_manager.RedemptionOrder WHERE id='"+jdbc.return_proper_db_value("SELECT orderId FROM points_manager.Product WHERE id='"+jdbc.get_value_of_proper_row(false,13)+"'",1)+"'",1)));
        }
        jdbc.close();
    }
}


















