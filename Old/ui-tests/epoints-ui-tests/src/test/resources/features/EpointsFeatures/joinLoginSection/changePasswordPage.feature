Feature: Epoints change password page
  As an epoints user
  I want to have change password page
  So that I could set up new password after using reset password module

  # // 1.1 //  ------------------------------------------------------------------------------------ Change password page
  # ------------------------------------------------------------------------------------------------------- page content
  @changePasswordPage @joinSignin
  Scenario: Change password page - page content
    Given Change password page is opened
    When User look on change password page
    Then He can see that it contains all needed elements

  # // 1.2 //  ------------------------------------------------------------------------------------ Change password page
  # ----------------------------------------------------------------------------------------------- no input data alerts
  @changePasswordPage @joinSignin
  Scenario: Change password page - no input data alerts
    Given Change password page is opened
    When User click change button without filling input fields
    Then Password is required and passwords do not match alerts will be displayed

  # // 1.3 //  ------------------------------------------------------------------------------------ Change password page
  # -------------------------------------------------------------------------------------- password do notch match alert
  @changePasswordPage @joinSignin
  Scenario: Change password page - password do notch match alert
    Given Change password page is opened
    When Enter two different passwords into input fields
    And Click change password button
    Then Then passwords do not match alerts will be displayed
    And Password strenght element will be displayed

  # // 1.4 //  ------------------------------------------------------------------------------------ Change password page
  # ------------------------------------------------------------------------------------------------------ invalid token
  @changePasswordPage @joinSignin
  Scenario: Change password page - invalid token
    Given Change password page is opened with expired token
    When User look on change password page
    Then He will see confirmation failed message