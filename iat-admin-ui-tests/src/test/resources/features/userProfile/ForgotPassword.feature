Feature: Forgot password
  As a platform user
  I want to have forgot password page
  So that I can retrieve password to my IAT admin account

  Background: User is on IAT admin login page
    Given User goes to IAT admin login page

  @Regression @ForgotPassword
  Scenario: Forgot password - follow link
    When He clicks on the "forgot password" link
    Then He is re-directed to forgot password page
    And He is informed about the change password process
    And The field to provide an email address is displayed
    And There are two buttons "back" & "send"
    And Send button is disabled when email input is empty

  @Regression @ForgotPassword
  Scenario: Forgot password - back button
    Given User is on the forgot password page
    When He clicks on the "back" button on forgot password page
    Then He is re-directed to IAT Admin sign in page

  @Regression @ForgotPassword
  Scenario: Forgot password - not existing in system email provided
    Given User is on the forgot password page
    When He provide email which not exists in the system
    Then And he click "send" button
    Then He is informed about an error

  @Regression @ForgotPassword
  Scenario: Forgot password - existing in system email provided
    Given User is on the forgot password page
    When He provide correct email which exists in the system
    Then And he click "send" button
    Then He is informed that password restoration link was sent

  @Regression @ForgotPassword
  Scenario: Forgot password/changing password - set new password page, follow link
    Given Password change link is already sent
    When User follow password change link
    Then He is re-directed to change password interface
    And It contains new password and confirm password fields
    And Two buttons back & change

  @Regression @ForgotPassword
  Scenario: Forgot password/changing password - set new password page, back button
    Given Password change link is already sent
    And User follow password change link
    When He clicks on the back button on set new password page
    Then He is re-directed to IAT Admin sign in page

  @Regression @ForgotPassword
  Scenario: Forgot password/changing password - set new password page, passwords do not match
    Given Password change link is already sent
    And User follow password change link
    When He provides new password confirmation different than new password on set new password form
    Then He is informed that both passwords do not match on set new password form

  @Regression @ForgotPassword
  Scenario Outline: Forgot password/changing password - set new password page, new password set
    Given Password change link is already sent
    And User follow password change link
    When He provides new password data '<oldPassword>', '<newPassword>' on set new password form
    And Confirms password change on set new password form
    Then He is informed that password was changed on set new password form
    And He is able to login into system with new password '<newPassword>'

    Examples:
      | oldPassword | newPassword |
      | password    | password1   |
      | password1   | password    |

  @Regression @ForgotPassword
  Scenario: Forgot password/changing password - set new password page, invalid token
    When User follow password change link and its token is invalid
    Then Information about invalid token will be displayed
    And Set new password form will not be available