Feature: Epoints join module
  As an epoints user
  I want to have join module
  So that I could create new epoints account

  # // 1.1 //  ------------------------------------------------------------------------------------ Joining into epoints
  # ------------------------------------------------------------------------------------------------------- page content
  @joinPage @joinSignin
  Scenario: Joining into epoints - page content
    Given Not registered user open epoints page clear
    When User click join now button in navbar
    Then Join page will be opened
    And Join page has proper content

  # // 1.2 //  ------------------------------------------------------------------------------------ Joining into epoints
  # -------------------------------------------------------------------------------------------------- tell me more link
  @joinPage @joinSignin
  Scenario: Joining into epoints - tell me more link
    Given Join page is opened
    When User click tell me more link
    Then Modal window with proper information will be displayed
    When User click close button on tell me more modal
    Then Tell me more modal will be closed

  # // 1.3 //  ------------------------------------------------------------------------------------ Joining into epoints
  # ------------------------------------------------------------------------------- checking mandatory fields validation
  @joinPage @joinSignin
  Scenario: Joining into epoints - checking mandatory fields validation
    Given Join page is opened
    When Email field will be not populated
    And User click join now button on join page
    Then Email address is required message will be displayed

  # // 1.4 //  ------------------------------------------------------------------------------------ Joining into epoints
  # ---------------------------------------------------------------------------------- checking fields length validation
  @joinPage @joinSignin
  Scenario: Joining into epoints - checking fields length validation
    Given Join page is opened
    And User can enter email consisted of 254 signs
    When User try to add more signs to email
    Then It will be impossible

  # // 1.5 //  ------------------------------------------------------------------------------------ Joining into epoints
  # --------------------------------------------------------------------------- checking validation for duplicated email
  @joinPage @joinSignin
  Scenario: Joining into epoints - checking validation for duplicated email
    Given Join page is opened
    When User enter email which is already used
    And User click join now button on join page
    Then Username already taken message will be displayed

  # // 1.6 //  ------------------------------------------------------------------------------------ Joining into epoints
  # -------------------------------------------------------------------- checking validation for incorrect email address
  @joinPage @joinSignin
  Scenario: Joining into epoints - checking validation for incorrect email address
    Given Join page is opened
    When User enter invalid email address
    And User click join now button on join page
    Then Email address is invalid message will be shown

  # // 1.7 //  ------------------------------------------------------------------------------------ Joining into epoints
  # ------------------------------------------------------------------------------------- checking if account is created
  @joinPage @joinSignin
  Scenario: Joining into epoints - checking if account is created
    Given Join page is opened
    When User enter new email address
    And User click join now button on join page
    Then New account will be created
    And New account should be unverified
    And Email submitted page should be opened
    And On new account should be 50 points

  # // 1.8 //  ------------------------------------------------------------------------------------ Joining into epoints
  # ----------------------------------------------------------------- checking sign out of non verified account/sign out
  @joinPage @joinSignin
  Scenario: Joining into epoints - checking sign out of non verified account/sign out
    Given Join page is opened clear
    Given New account is created
    When User click sign out button before account will be verified
    Then Modal warning window will be opened
    And Modal warning window has proper content
    When User click ok sign out button on modal warning window
    Then He will be properly sign out from created account

  # // 1.9 //  ------------------------------------------------------------------------------------ Joining into epoints
  # -------------------------------------------------------------- checking sign out of non verified account/resend link
  @joinPage @joinSignin
  Scenario: Joining into epoints - checking sign out of non verified account/resend link
    Given Join page is opened clear
    Given New account is  created
    When User click sign out button before account will be verified
    Then Modal warning window will be opened
    When User click resend confirmation link on warning modal
    Then Confirmation link will be sent ane success message displayed

  # // 1.10 //  ----------------------------------------------------------------------------------- Joining into epoints
  # ------------------------------------------------------------------------ join here button on sign in popup - angular
  @joinPage @joinSignin
  Scenario: Joining into epoints - join here button on sign in popup
    Given Not registered user open epoints page clear
    Given Sign in popup is displayed
    When User click join here link on sign in popup
    Then He will be redirected to join page

  # // 1.11 //  ----------------------------------------------------------------------------------- Joining into epoints
  # ------------------------------------------------------------------- resend email confirmation/epoints sections links
  @joinPage  @joinSignin
  Scenario: Joining into epoints - resend email confirmation/epoints sections links
    Given Join page is opened clear
    Given New account is registered
    When User click resend confirmation link
    Then Resend confirmation link success alert will be displayed
    And Bottom epoints links works properly

  # // 1.12 //  ----------------------------------------------------------------------------------- Joining into epoints
  # ------------------------------------------------------------------------------------------------ joining by facebook
  @userIsLogoutFromFacebook @joinPage @joinSignin
  Scenario Outline: Joining into epoints - joining by facebook
    Given Join page is opened clear
    Given Proper user is deleted from points manager '<fbEmail>'
    When User use join by facebook option with correct data '<fbEmail>' '<fbPassword>'
    Then New epoints facebook account will be properly created '<fbEmail>' '<name>'
    And User account should be verified '<fbEmail>'
    And On new account should be 50 points '<fbEmail>'
    And All data stored in user account section will be properly downloaded from facebook account '<name>' '<lastName>' '<gender>' '<dateOfBirth>' '<fbEmail>' '<dateOfBirthDBFormat>'
    And User will be recognized as facebook user '<fbEmail>'

    Examples:
      |name|lastName|gender|dateOfBirth|fbEmail|fbPassword|dateOfBirthDBFormat|
      |Daniel|Porebski|MALE|02/03/1972|emailwybitnietestowy3@gmail.com|Delete777|1972-03-02 00:00:00.0|

  #Scenario Outline: Delete user
    #Given Proper user is deleted from points manager '<fbEmail>'

    #Examples:
      #|fbEmail|
      #|qaqaqaqa073@gmail.com|