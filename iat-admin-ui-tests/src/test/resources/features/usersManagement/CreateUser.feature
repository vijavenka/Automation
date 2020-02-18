Feature: Create user
  As an administrator
  I want to have interface for user creation
  So that I will be able create company epoints user or admin manager

  @Regression @AddEmployee
  Scenario: Create user - general view
    Given User with a certain users management permissions is signed in to iat
    When Add employee page is opened
    Then Admin is presented with create user interface
    And It contains two tabs, "Insert manually" and "Bulk upload"
    And It contains employee number field
    And It contains name field
    And It contains gender checkboxes
    And It contains company start date checkboxes
    And It contains date of birth field
    And It contains email field
    And It contains user role checkboxes
    And It contains department tree
    And It contains cancel and save button

  @Regression @AddEmployee
  Scenario: Create user - cancel button clicked
    Given User with a certain users management permissions is signed in to iat
    And Add employee page is opened
    When He clicks on "Cancel" button on add employee page
    Then He is re-directed to "Browse users" interface

  @Regression @AddEmployee
  Scenario Outline: Create user - add and create new
    Given User with a certain users management permissions is signed in to iat
    And Add employee page is opened
    And He filled the form with proper data to create user '<newUserData>'
    When He clicks on "Save" button
    Then Admin user stays on the create user form
    And The form is cleared
    And Admin user is informed about the success
    Then The new user is added and available on browse users page

    Examples:
      | newUserData                                                                            |
      | employeeId;automatedtestemail;FirstName LastName;MALE;27-12-1986;USER;ADMIN;27-12-2014 |

  @Regression @AddEmployee
  Scenario Outline: Create user - error info displaying
    Given User with a certain users management permissions is signed in to iat
    And Add employee page is opened
    And He filled the form with proper data to create user '<newUserData>'
    When He clicks on "Save" button
    Then Information about user already exists will be displayed

    Examples:
      | newUserData                                                                               |
      | employeeId;emailfortest@wp.pl;FirstName LastName;MALE;27-12-1986;MANAGER;ADMIN;27-12-2014 |

  @Regression @AddEmployee
  Scenario Outline: Create user - possible user roles to be created according to admin permissions
    Given <Username> is logged in to IAT Admin platform
    And Add employee page is opened
    When He wants to add a user of chosen type
    Then He can only create users within his permission scope '<username>'

    Examples:
      | Username   |
      | manager    |
      | admin      |
      | superadmin |

  @Regression
  Scenario: Adding an active user in partner2
      Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
      And Identified an active user and logged out
      And IAT Admin user is logged in with credentials 'api_test_super_admin_2@api.iat.admin.pl,P@ssw0rd'
      And By adding the same user
      Then It should throw proper error message

  @Regression
  Scenario: Adding an in-active user in partner2
      Given IAT Admin user is logged in with credentials 'api_test_super_admin_1@api.iat.admin.pl,P@ssw0rd'
      And Identified an in-active user
      And IAT Admin user is logged in with credentials 'api_test_super_admin_2@api.iat.admin.pl,P@ssw0rd'
      And Adding the same user
      Then It should allow us to create the user
      And In partner1, it shouldn't allow us to re-active the user
