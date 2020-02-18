Feature: Change password
  As an administrator
  I want to have change password page
  So that I will be able to change my account password to IAT administration system

  @Regression @ChangingPassword
  @resetAdminPasswordBefore
  Scenario: User profile - change password component availability
    Given User with a certain users management permissions is signed in to iat with defaultProfileTestUser, defaultProfileTestPassword
    And Change password page is opened
    Then He can see a section to change a password
    And It contains "current password","new password" and "confirm password" fields
    And It contains "Confirm" button

  @Regression @ChangingPassword
  Scenario: User profile - change password component, wrong current password
    Given User with a certain users management permissions is signed in to iat with defaultProfileTestUser, defaultProfileTestPassword
    And Change password page is opened
    When He provides improper current password
    And Confirms password change
    Then He is informed that provided current password is incorrect

  @Regression @ChangingPassword
  Scenario: User profile - change password component, passwords do not match
    Given User with a certain users management permissions is signed in to iat with defaultProfileTestUser, defaultProfileTestPassword
    And Change password page is opened
    When He provides new password confirmation different than new password
    Then He is informed that both passwords do not match

  @Regression @ChangingPassword
  Scenario: User profile - change password component, passwords to short
    Given User with a certain users management permissions is signed in to iat with defaultProfileTestUser, defaultProfileTestPassword
    And Change password page is opened
    When He provides to short new password, below 6 signs
    Then He is informed that new password is to short

  @Regression @ChangingPassword
  Scenario Outline: User profile - change password component, correct password change
    Given Admin is logged into system with password <oldPassword>
    And Change password page is opened
    When He provides new password data '<oldPassword>', '<newPassword>'
    And Confirms password change
    Then He is informed that password was changed
    And He is able to login into system with new password '<newPassword>' next time

    Examples:
      | oldPassword | newPassword |
      | password    | password1   |
      | password1   | password    |