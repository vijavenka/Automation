Feature: Epoints sign in module
  As an epoints user
  I want to have sign in module
  So that I could sign in to existing account

  # // 1.1 //  -------------------------------------------------------------------------------------- Login into epoints
  # ---------------------------------------------------------------------------------------------- sign in panel content
  @Regression @SignIn
  Scenario: Login into epoints - complete account page content
    Given Not registered user open ePoints.com
    When User click in sign in option
    Then Authentication panel is shown
    And Email label is available
    And Email input field is available
    And Password label is available
    And Password input field is available
    And Forgot password link is available
    And Sign In button is available
    And Join here Link is available
    And Sign in using facebook button is available
    And Close Link is available

  # // 1.2 //  -------------------------------------------------------------------------------------- Login into epoints
  # --------------------------------------------------------------------------------------------- required fields alerts
  @Regression @SignIn
  Scenario: Login into epoints - required fields alerts
    Given Not registered user open ePoints.com
    Given Sign in panel is opened
    When User enter some phrases into input fields and delete them
    Then Fields should be marked
    And Fields required alerts will be displayed

  # // 1.3 //  -------------------------------------------------------------------------------------- Login into epoints
  # ---------------------------------------------------------------------------------- sign in into not existing account
  @Regression @SignIn
  Scenario: Login into epoints - sign in into not existing account
    Given Not registered user open ePoints.com
    Given Sign in panel is opened
    When User fill email address using email which is not in the database
    And Fill Password field with random data
    And Click On sign in button
    Then Warning authentication system error should shown

  # // 1.4 //  -------------------------------------------------------------------------------------- Login into epoints
  # --------------------------------------------------------------- sign in into existing account which is not activated
  @Regression @SignIn
  Scenario: Login into epoints - sign in into existing account which is not activated
    Given Not registered user open ePoints.com
    Given Sign in panel is opened
    When User fill email address using email which is not activated
    And Fill Password field with random data
    And Click On sign in button
    Then Warning authentication system error should shown

  # // 1.5 //  -------------------------------------------------------------------------------------- Login into epoints
  # ----------------------------------------------------------------- sign in into existing account with random password
  @Regression @SignIn
  Scenario: Login into epoints - sign in into existing account with random password
    Given Not registered user open ePoints.com
    Given Sign in panel is opened
    When User enter correct email address
    And Fill Password field with random data
    And Click On sign in button
    Then Warning authentication system error should shown

  # // 1.6 //  -------------------------------------------------------------------------------------- Login into epoints
  # ---------------------------------------------------------------------------------------------------- correct sign in
  @Regression @SignIn
  Scenario: Login into epoints - correct sign in
    Given Not registered user open ePoints.com
    Given Sign in panel is opened
    When User fill correct data into sign in form
    And Click On sign in button
    Then He will be correctly logged in
    And Points counter will be displayed
    And Navigation options for logged user will be displayed

  # // 1.7 //  -------------------------------------------------------------------------------------- Login into epoints
  # ---------------------------------------------------------------------------------------------------- sign out option
  @Regression @SignIn
  Scenario: Login into epoints - sign out option
    Given Registered user open ePoints.com
    When User click on sign out button
    Then He will be correctly logged out
    And Points counter will not be displayed
    And Navigation options for not logged user will be displayed

  # // 1.8 //  -------------------------------------------------------------------------------------- Login into epoints
  # ---------------------------------------------------- sign in when user is provide by cookie and is not authenticated
  @Regression @SignIn
  Scenario: Login into epoints - sign in when user is provide by cookie and is not authenticated
    Given Identified user open ePoints.com
    When User tries to reach his profile page
    Then Login page with all needed elements will be displayed
    When User fill correct data into sign in modal
    And Click on sign in button in modal window
    Then User will be redirected to your account area

  # // 1.9 //  ------------------------------------------------------------------------------------- Login into epoints
  # ------------------------------------------------------------- checking Sign out option for user identified by cookie
  @Regression @SignIn
  Scenario: Login into epoints - checking Sign out option for user identified by cookie
    Given Identified user open ePoints.com
    When User click on sign out button
    Then He will be correctly logged out

  # // 1.10 //  ------------------------------------------------------------------------------------- Login into epoints
  # ------------------------------------------------------------------------------------------------------------- via FB
  @Regression @SignIn
  @userIsLogoutFromFacebook
  Scenario: Login into epoints - correct sign in by facebook
    Given Not registered user open ePoints.com
    When User is signing in via FB account emailwybitnietestowy3@gmail.com:Delete777
    Then He will be correctly logged in
    And Points counter will be displayed
    And Navigation options for logged user will be displayed

  Scenario Outline: PD-808 epoints, deactivated user should not able to login
    Given In epoints deactivated '<user>' logs in
    Then It will throw error message
    And It wont login

    Examples:
      | user |
      | normal |
      | business |
      | facebook |

  Scenario Outline: PD-808 epoints, active user should able to login
    Given In epoints active '<user>' logs in
    Then Without error message it should login

    Examples:
      | user |
      | normal |
      | business |
      | facebook |