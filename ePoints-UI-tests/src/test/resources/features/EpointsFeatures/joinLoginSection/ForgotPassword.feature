Feature: Epoints forgot password page
  As an epoints user
  I want to forgot password page
  So that I could create new password when old one will be forgotten

  # // 1.1 //  ----------------------------------------------------------------------------------------- Forgot password
  # ------------------------------------------------------------------------------------ page availability popup angular
  @Regression @ForgotPasswordPage
  Scenario: Forgot password - page availability popup angular
    Given Epoints page is opened
    When User click sign in button in main navbar
    And User click forgot password link
    Then Forgot password page will be opened
    And Forgot password page has proper content

  # // 1.2 //  ----------------------------------------------------------------------------------------- Forgot password
  # ------------------------------------------------------------------------------------- page availability sign in page
  @Regression @ForgotPasswordPage
  Scenario: Forgot password - page availability modal angular
    Given Identified epoints user open ePoints page clear
    Given Sign in page is opened
    And User click forgot password link on sign in page
    Then Forgot password page will be opened
    And Forgot password page has proper content

  # // 1.3 //  ----------------------------------------------------------------------------------------- Forgot password
  # ----------------------------------------------------------- check if forgot your password page returns proper alerts
  @Regression @ForgotPasswordPage
  Scenario: Forgot password - check if forgot your password page returns proper alerts
    Given Epoints page is opened
    Given Forgot password page is opened
    When User type invalid email
    Then Email is invalid alert should be displayed
    When User delete entered email
    Then Email is required alert should appear
    When User enter incorrect email
    Then Email does not exist alert should appear

  # // 1.4 //  ----------------------------------------------------------------------------------------- Forgot password
  # ------------------------------------------------------- check if password restoration link is created and is working
  @Regression @ForgotPasswordPage
  Scenario Outline: Forgot password - check if password restoration link is created and is working
    Given Epoints page is opened
    Given Forgot password page is opened
    When User enter correct email
    And Click send restoration link button
    Then Email sent info should be displayed
    And Link sent on user email should works
    And User can create new password '<password>'
    And User can check that new password works '<password>'

    Examples:
      | password  |
      | Delete778 |
      | testing   |