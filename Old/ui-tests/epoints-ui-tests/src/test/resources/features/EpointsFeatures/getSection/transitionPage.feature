Feature: Epoints transition page
  As an epoints user
  I want to have transition page
  So that I could decide if I want to go to retailer page as logged user or not, page has some information and option to create epoints account

  # // 1.1 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # -------------------------------------------------------------------- not logged information modal content, no button
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - not logged information modal content, no button
    Given User is opening transition page clear
    Then He will be presented with transition information modal for not logged users
    When User click no button
    Then He will be directly taken to retailer page

  # // 1.2 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # ------------------------------------------------------------------ not logged information modal content, join button
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - not logged information modal content, join button
    Given User is opening transition page
    Then He will be presented with transition information modal for not logged users
    When User click join button
    Then Information modal will disappear
    And Transition page with all login forms will be presented

  # // 1.3 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # --------------------------------------------------------- not logged information modal content, do not show checkbox
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - not logged information modal content, do not show checkbox
    Given User is opening transition page
    Then He will be presented with transition information modal for not logged users
    When User refresh page
    Then Information modal will be still displayed
    When User check do not show me this again checkbox
    And User click join button
    And User refresh page
    Then Information modal will not be displayed

  # // 1.4 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # ------------------------------------------------------------------- not logged information modal content, close link
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - not logged information modal content, close link
    Given User is opening transition page clear
    Then He will be presented with transition information modal for not logged users
    When User click close link
    Then Information modal will not be displayed

  # // 1.5 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # -------------------------------------------------------------------- logged information modal content, got it button
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - logged information modal content, got it button
    Given Epoints page is opened and user is logged
    When User go to transition page
    Then He will be presented with transition information modal for logged users
    When User click got it button
    Then He will be directly taken to retailer page

  # // 1.6 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # ------------------------------------------------------------- logged information modal content, do not show checkbox
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - logged information modal content, do not show checkbox
    Given Epoints page is opened and user is logged clear
    When User go to transition page
    Then He will be presented with transition information modal for logged users
    When User refresh page
    Then Information modal will be still displayed
    When User check do not show me this again checkbox
    And User click got it button
    And User go to transition page
    Then Information modal will not be displayed

  # // 1.7 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # --------------------------------------------------------------- logged information modal content, close cross button
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - logged information modal content, close cross button
    Given Epoints page is opened and user is logged clear
    When User go to transition page
    Then He will be presented with transition information modal for logged users
    When User click close cross button on information modal
    Then Information modal will not be displayed
    And He will be directly taken to retailer page

  # // 1.8 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # ------------------------------------------------------------------------------- not logged , transition page content
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - not logged, transition page content
    Given User is opening clear transition page without any modal clear
    When He look at the transition page
    Then He can see that it has all needed elements

  # // 1.9 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # --------------------------------------------------------------------------------- not logged, continue anyway button
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - not logged, continue anyway button
    Given User is opening clear transition page without any modal
    When User use continue anyway button
    Then He will be directly taken to retailer page

  # // 1.10 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # ------------------------------------------------------------------------------------------------ login, login alerts
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - login, login alerts
    Given User is opening clear transition page without any modal
    When User click sign in button without filling any data
    Then Signing input fields alerts will be displayed
    When User enter incorrect email address in signing form
    And User click sign in button
    Then Email address is invalid alert will be displayed
    When User enter credentials of user which is not in database
    And User click sign in button
    Then Authorization failed alert will be displayed

  # // 1.11 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # ---------------------------------------------------------------------------------- login, correct login into epoints
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - login, correct login into epoints
    Given User is opening clear transition page without any modal
    When User enter correct login data
    And User click sign in button
    Then He will be directly taken to retailer page
    When User return to epoints page
    Then He will be logged in

  # // 1.12 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # ---------------------------------------------- join, join to epoints and continue to retailer on confirmation screen
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - join, join to epoints and continue to retailer on confirmation screen
    Given User is opening clear transition page without any modal clear
    When User click join now button
    Then He will e redirected to epoints join page
    When User correctly fill email input field
    And Click join now button on epoints join page
    Then Under confirmation screen will be displayed continue to retailer button
    When User click continue anyway button
    Then He will be redirected to retailer page

  # // 1.13 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # ----------------------------------------------------------------------------------------------- forgot password link
  @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - forgot password link
    Given User is opening clear transition page without any modal clear
    When User use forgot password link
    Then New tab with forgot password form will be opened

  # // 1.14 //  -------------------------------------- EPOINTS TRANSITION - update the UI of the transition page(RD-4175)
  # ---------------------------------------------------------------------------------------------- sign in with facebook
  @userIsLogoutFromFacebook @transitionPage @getSection
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - sign in with facebook
    Given User is opening clear transition page without any modal clear
    When User will sign in with facebook
    Then He will be directly taken to retailer page
    When User return to epoints page
    Then He will be logged in

  # // 2.1 //  ------------------------------------ DESKTOP - TRANSITION PAGE - update UI for Ireland clickouts(NBO-548)
  # ------------------------------------------------------------------------------------------ displaying of UK currency
  @transitionPage @getSection
  Scenario: DESKTOP - TRANSITION PAGE - update UI for Ireland clickouts(NBO-548) - displaying of UK currency
    Given Epoints page is opened and user is logged clear
    Given UK zone is chosen
    When User go to transition page from az page
    Then He can see that currency is properly set for UK

  # // 2.2 //  ------------------------------------ DESKTOP - TRANSITION PAGE - update UI for Ireland clickouts(NBO-548)
  # ------------------------------------------------------------------------------------- displaying of Ireland currency
  @transitionPage @getSection
  Scenario: DESKTOP - TRANSITION PAGE - update UI for Ireland clickouts(NBO-548) - displaying of Ireland currency
    Given Epoints page is opened and user is logged clear
    Given Ireland zone is chosen
    When User go to transition page from az page
    Then He can see that currency is properly set for Ireland