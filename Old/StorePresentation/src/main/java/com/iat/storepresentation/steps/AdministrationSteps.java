package com.iat.storepresentation.Steps;

import com.iat.storepresentation.Database.JDBC;
import com.iat.storepresentation.Executors.IExecutor;
import com.iat.storepresentation.Executors.SeleniumExecutor;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: kbaranowski
 * Date: 07.01.14
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
public class AdministrationSteps {

    private IExecutor executor;
    private JDBC jdbc;

    public AdministrationSteps(SeleniumExecutor executor) {
        this.executor = executor;
    }

    public void go_sleep(int milisec) throws Throwable {
        Thread.sleep(milisec);
    }

    @Before
    public void set_up() {
        executor.start();
        jdbc = new JDBC("points_manager");
    }

    @After
    public void tear_down() throws SQLException {
        jdbc.close();
        executor.stop();
    }

    @Then("^Administration action - delete user from db '(.+)'$")
    public void Administration_action_delete_user_from_db(String emailToDelete) throws Throwable {
        jdbc.execute_delete_update_query(jdbc.return_proper_query("deleteRequest", emailToDelete));
        jdbc.execute_delete_update_query(jdbc.return_proper_query("deletePoints", emailToDelete));
        jdbc.execute_delete_update_query(jdbc.return_proper_query("deleteUserToken", emailToDelete));
        jdbc.execute_delete_update_query(jdbc.return_proper_query("deleteUserId", emailToDelete));
        jdbc.execute_delete_update_query(jdbc.return_proper_query("deleteUser", emailToDelete));
    }

    @Then("^Administration action - delete facebook flag from db '(.+)'$")
    public void Administration_action_delete_facebook_flag_from_db(String emailToDelete) throws Throwable {
        jdbc.execute_delete_update_query(jdbc.return_proper_query("deleteFacebookFlag", emailToDelete));
    }

    @Then("^Administration action - delete points history from request and points tables for '(.+)'$")
    public void Administration_action_delete_points_history_from_request_and_points_tables_for_iat_epoints_test_gmail_com(String emailToDelete) throws Throwable {
        //delete rows from Request table
        jdbc.execute_delete_update_query("DELETE FROM points_manager.Request WHERE userId='" + jdbc.return_proper_db_value(jdbc.return_proper_query("returnUserKey", emailToDelete), 1) + "' AND pointsId='" + jdbc.return_proper_db_value("SELECT id FROM points_manager.Points WHERE userId='" + jdbc.return_proper_db_value(jdbc.return_proper_query("returnId", emailToDelete), 1) + "' AND activityInfo = 'User referred a friend who joined epoints.com'", 1) + "'");
        //delete rows from Points Table
        jdbc.execute_delete_update_query("DELETE FROM points_manager.Points WHERE userId ='" + jdbc.return_proper_db_value(jdbc.return_proper_query("returnId", emailToDelete), 1) + "' AND activityInfo = 'User referred a friend who joined epoints.com'");
    }

    @Then("^Administration action - restore email in DB '(.+)' '(.+)'$")
    public void Administration_action_restore_email_in_DB(String oldEmail, String newEmail) throws Throwable {
        jdbc.execute_delete_update_query("UPDATE points_manager.User SET email='" + oldEmail + "' WHERE email='" + newEmail + "'");
    }

    @Then("^Administration action - remove favourites from DB$")//for email wybitnietestowy@gmail.com
    public void Then_Administration_action_remove_favourites_from_DB() throws Throwable {
        jdbc.execute_delete_update_query("DELETE FROM points_manager.UserInterests WHERE userId = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId","emailwybitnietestowy@gmail.com"),1)+"'");
        executor.return_driver().navigate().refresh();
    }

    @Then("^Administration action - set number of points in points manager for '(.+)'$")//for email wybitnietestowy@gmail.com
    public void Administration_action_Set_number_of_points_in_points_manager(String emailToPointsUpdate) throws Throwable {
        jdbc.execute_delete_update_query("Update points_manager.User SET confirmed='1000000000' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPointsUpdate),1)+"'");
    }

    @Given("^Administration action - set personal data for '(.+)'$")
    public void Administration_action_set_personal_data_for_emailwybitnietestowy_gmail_com(String emailToPersonalDataUpdate) throws Throwable {
    // Account details
    // Personal details
        jdbc.execute_delete_update_query("Update points_manager.User SET firstName = 'Krzysztof' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPersonalDataUpdate),1)+"'");
        jdbc.execute_delete_update_query("Update points_manager.User SET lastName = 'Baranowski' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPersonalDataUpdate),1)+"'");
        jdbc.execute_delete_update_query("Update points_manager.User SET title = 'Mr' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPersonalDataUpdate),1)+"'");
        jdbc.execute_delete_update_query("Update points_manager.User SET gender = 'MALE' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPersonalDataUpdate),1)+"'");
        jdbc.execute_delete_update_query("Update points_manager.User SET birthDate = '1989-06-08 00:00:00' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPersonalDataUpdate),1)+"'");
    // Contact details
        jdbc.execute_delete_update_query("Update points_manager.User SET mobileNumber = '695805680' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPersonalDataUpdate),1)+"'");
        jdbc.execute_delete_update_query("Update points_manager.User SET houseNumberOrName = '70' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPersonalDataUpdate),1)+"'");
        jdbc.execute_delete_update_query("Update points_manager.User SET street = 'Glowna' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPersonalDataUpdate),1)+"'");
        jdbc.execute_delete_update_query("Update points_manager.User SET townOrCity = 'Krasowice' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPersonalDataUpdate),1)+"'");
        jdbc.execute_delete_update_query("Update points_manager.User SET county = 'Opolskie' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPersonalDataUpdate),1)+"'");
        jdbc.execute_delete_update_query("Update points_manager.User SET country = 'Poland' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPersonalDataUpdate),1)+"'");
        jdbc.execute_delete_update_query("Update points_manager.User SET postcode = '46-100' WHERE id = '"+jdbc.return_proper_db_value(jdbc.return_proper_query("returnId",emailToPersonalDataUpdate),1)+"'");
    }

}
