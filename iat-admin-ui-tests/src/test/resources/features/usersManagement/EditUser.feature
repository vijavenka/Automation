Feature: Edit user
  As an administrator
  I want to have interface for user edit
  So that I will be able change data of chosen user

  Background:
    Given User with a certain users management permissions is signed in to iat
    And Edit user page is opened


  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - delete user popup
    When User clicks "Delete" button on edit user page
    Then Delete user confirmation modal is displayed with "Delete" and "Cancel" options
    #just for closing popup
    And He clicks "Cancel" button on delete user confirmation popup

  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - delete user popup, cancel button click
    When User clicks "Delete" button on edit user page
    And He clicks "Cancel" button on delete user confirmation popup
    Then Delete edit user confirmation popup will be closed
    And User will not be deleted

  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - delete user popup, delete button click
    When User clicks "Delete" button on edit user page
    And He clicks "Delete" button on delete user confirmation popup
    Then Delete edit user confirmation popup will be closed
    And User will be soft deleted

  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - general view
    Then Admin is presented with edit user interface
    And Edit page contains delete user button
    And Edit page contains employee number field
    And Edit page contains name field
    And Edit page contains date of birth field
    And Edit page contains email field
    And Edit page contains gender checkboxes
    And Edit page contains start date checkboxes
    And Edit page contains user role checkboxes
    And Edit page contains department tree
    And Edit page contains cancel and save button

  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - cancel button click, confirmation modal yes
    When User clicks "Cancel" button on edit user page
    Then Confirmation popup will be displayed
    And User can confirm cancel of user editing
    And He is re-directed to "Browse users" interface

  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - cancel button click, confirmation modal no
    When User clicks "Cancel" button on edit user page
    Then Confirmation popup will be displayed
    And User can cancel leaving the edit user page
    And He stays on edit user page

  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - edit all fields of not verified user on epoints side
    And User which will be edited is not verified on epoints side
    When Admin edit all fields of chosen user with email
    And User clicks "Save" button on edit user page
    Then Edited data will be saved
    And New data will be visible on browse user page
    And Edited data will be visible on user edit page

  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - edit all fields of verified user on epoints side
    And User which will be edited is verified on epoints side
    When Admin edit all fields of chosen user with email
    And User clicks "Save" button on edit user page
    And User choose if also epoints email has to be updated 'true' on popup
    Then Edited data will be saved
    And New data will be visible on browse user page
    And Edited data will be visible on user edit page

  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - edit all fields apart of email, check if email change confirmation popup is not displayed
    And User which will be edited is verified on epoints side
    When Admin edit all fields of chosen user without email
    And User clicks "Save" button on edit user page
    Then Edited data will be saved
    And New data will be visible on browse user page
    And Edited data will be visible on user edit page

  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - edit only email field, check error message for duplicated email during update
    When Admin edit only email field of chosen user with existing in epoints email
    And User clicks "Save" button on edit user page
    Then Information about user already exists will be displayed on edit user page

  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - enable user popup, delete button click and confirm
    When User clicks "Delete" button on edit user page
    And He clicks "Delete" button on delete user confirmation popup
    And Delete edit user confirmation popup will be closed
    And User will be soft deleted
    When User edit previously deleted user
    And User clicks "Enable user" button on edit user page
    And He clicks "Enable" button on delete user confirmation popup
    Then User will be reenabled

  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - enable user popup, delete button click and cancel
    When User clicks "Delete" button on edit user page
    And He clicks "Delete" button on delete user confirmation popup
    And Delete edit user confirmation popup will be closed
    And User will be soft deleted
    When User edit previously deleted user
    And User clicks "Enable user" button on edit user page
    And He clicks "Cancel" button on delete user confirmation popup
    Then Delete edit user confirmation popup will be closed
    And User will not be reenabled

  @Regression @EditUser
  @createNewUserBeforeTest @deleteCreatedUserAfterTestPermanently
  Scenario: Edit users - change user email, validate email change history availability
    And Email change history is not available
    When Admin edit chosen user email
    And User clicks "Save" button on edit user page
    And Edited data will be saved
    And Admin back on edit page of user whose email was changed
    Then Email change history is available
    And Email change history shows 1 previous email
    And Email change history displays user uuid