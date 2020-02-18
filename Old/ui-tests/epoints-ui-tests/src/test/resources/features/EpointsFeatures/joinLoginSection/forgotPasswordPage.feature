Feature: Epoints forgot password page
  As an epoints user
  I want to forgot password page
  So that I could create new password when old one will be forgotten

  # // 1.1 //  ----------------------------------------------------------------------------------------- Forgot password
  # ------------------------------------------------------------------------------------ page availability popup angular
  @forgotPasswordPage @joinSignin
  Scenario: Forgot password - page availability popup angular
    Given Epoints page is opened
    When User click sign in button in main navbar
    And User click forgot password link
    Then Forgot password page will be opened
    And Forgot password page has proper content

  # // 1.2 //  ----------------------------------------------------------------------------------------- Forgot password
  # ------------------------------------------------------------------------------------ page availability modal angular
  @forgotPasswordPage @joinSignin
  Scenario: Forgot password - page availability modal angular
    Given Identified user open ePoints page clear
    Given Sign in modal is opened
    And User click forgot password link in modal window
    Then Forgot password page will be opened
    And Forgot password page has proper content

  # // 1.3 //  ----------------------------------------------------------------------------------------- Forgot password
  # ----------------------------------------------------------- check if forgot your password page returns proper alerts
  @forgotPasswordPage @joinSignin
  Scenario: Forgot password - check if forgot your password page returns proper alerts
    Given Epoints page is opened clear
    Given Forgot password page is opened
    When User try to restore password without entering email
    Then Email is required alert should appear
    When User enter incorrect email
    Then Email does not exist alert should appear

  # // 1.4 //  ----------------------------------------------------------------------------------------- Forgot password
  # ------------------------------------------------------- check if password restoration link is created and is working
  @forgotPasswordPage @joinSignin
  Scenario Outline: Forgot password - check if password restoration link is created and is working
    Given Epoints page is opened clear
    Given Forgot password page is opened
    When User enter correct email
    And Click send restoration link button
    Then Success alert should be shown
    And Link sent on user email should works
    And User can create new password '<password>'
    And User can check that new password works '<password>'

    Examples:
      |password|
      |Delete778|
      |Delete777|