Feature: Login for IAT Admin
  As a platform owner
  I want to have authorization functionality in Admin tool
  So that unauthorized access to platform will be limited

  Background: User is on IAT admin login page
    Given User goes to IAT admin login page

  @Regression @Login
  @setManagerThresholdToNoneBefore
  Scenario: IAT admin login - general view
    Then Login page has inputs for login and password
    And "Sign in" button is available
    And "Forgot your password" link is available

  @Regression @Login
  Scenario Outline: IAT admin login - incorrect username or password
    When He enters '<Username>' and '<Password>' for sign in
    Then Alert "Username or password invalid." will be displayed

    Examples:
      | Username                            | Password      |
      | wrongUsername                       | password      |
      | ui_automated_tests_superadmin@wp.pl | wrongPassword |

  @Regression @Login
  Scenario Outline: IAT admin login - different roles
    When He enters '<Username>' and '<Password>' for sign in
    Then He should be logged in
    And Role type '<RoleType>' of user is displayed on the top bar
    And User name '<Username>' is displayed on the top bar

    Examples:
      | Username                            | Password | RoleType   |
      | ui_automated_tests_manager@wp.pl    | password | MANAGER    |
      | ui_automated_tests_admin@wp.pl      | password | ADMIN      |
      | ui_automated_tests_superadmin@wp.pl | password | SUPERADMIN |
      | ui_automated_tests_uberadmin@wp.pl  | password | ADMIN      |