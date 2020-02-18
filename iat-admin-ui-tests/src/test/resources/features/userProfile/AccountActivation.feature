Feature: Account activation, set password page
  As a platform user
  I want to have set password page
  So that I can set password on newly created account

  @Regression @AccountActivation
  @deleteCreatedAdminAfterTest
  Scenario: User profile - activate account, follow account confirmation link
    Given New admin account is already created and account activation link sent
    When User follows the account confirmation link
    Then He is shown a set password page
    And He is informed that he has to set up a password in order to start using IAT Admin
    And The form contains password and confirm password fields
    And Activate button is available

  @Regression @AccountActivation
  @deleteCreatedAdminAfterTest
  Scenario: User profile - activate account, set password page, passwords do not match
    Given New admin account is already created and account activation link sent
    And User follows the account confirmation link
    When He provides new password confirmation different than new password on set password form
    Then He is informed that both passwords do not match on set password form

  @Regression @AccountActivation
  @deleteCreatedAdminAfterTest
  Scenario: User profile - activate account, set password page, correct password creation
    Given New admin account is already created and account activation link sent
    And User follows the account confirmation link
    When He provides correct new password data on set new password form
    And Confirms creation of password
    Then He is informed that password was created
    And New admin is able to login into system with already set password

  @Regression @AccountActivation
  Scenario: User profile - activate account, set password page, invalid token
    When User follows the account confirmation link which has invalid token
    Then Information about invalid token will be shown
    And Set password form will not be available