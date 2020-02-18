Feature: Epoints change/reset password page
  As an epoints user
  I want to have change password page
  So that I could set up new password after using reset password module

  # // 1.1 //  ------------------------------------------------------------------------------------ Change password page
  # ------------------------------------------------------------------------------------------------------- page content
  @Regression @ResetPasswordPage
  Scenario: Change password page - page content
    Given Change password page is opened
    When User look on change password page
    Then He can see that it contains all needed elements

  # // 1.2 //  ------------------------------------------------------------------------------------ Change password page
  # ----------------------------------------------------------------------------------------------- no input data alerts
  @Regression @ResetPasswordPage
  Scenario: Change password page - no input data alerts
    Given Change password page is opened
    When User enter password and password confirmation
    And User delete entered new password data
    Then Password is required and confirm password is required alerts will be displayed

  # // 1.3 //  ------------------------------------------------------------------------------------ Change password page
  # -------------------------------------------------------------------------------------- password do notch match alert
  @Regression @ResetPasswordPage
  Scenario: Change password page - password do notch match alert
    Given Change password page is opened
    When Enter two different passwords into input fields
    Then Then passwords do not match alerts will be displayed
    And Password strength element will be displayed

  # // 1.4 //  ------------------------------------------------------------------------------------ Change password page
  # ------------------------------------------------------------------------------------------------------ invalid token
  @Regression @ResetPasswordPage
  Scenario: Change password page - invalid token
    Given Change password page is opened with expired token
    When User enter password and password confirmation
    And Click change password button
    Then He will see invalid token message