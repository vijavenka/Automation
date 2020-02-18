Feature: Epoints transition page
  As an epoints user
  I want to have transition page
  So that I could decide if I want to go to retailer page as logged user or not, page has some information and option to create epoints account

  @Regression @TransitionPage
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - not logged, transition page content
    Given Not logged user is opening transition page
    Then He can see that it has all needed elements

  @Regression @TransitionPage
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - not logged, continue anyway button
    Given Not logged user is opening transition page
    When User use continue anyway button
    Then He will be directly taken to retailer page

  @Regression @TransitionPage
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - login, login alerts
    Given Not logged user is opening transition page
    When User enter sign in data and remove them
    Then Signing input fields alerts will be displayed
    When User enter incorrect email address in signing form
    And User click sign in button
    Then Email address is invalid alert will be displayed
    When User enter credentials of user which is not in database
    And User click sign in button
    Then Authorization failed alert will be displayed

  @Regression @TransitionPage
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - login, correct login into epoints
    Given Not logged user is opening transition page
    When User enter correct login data
    And User click sign in button
    Then He will be directly taken to retailer page
    When User return to epoints page
    Then He will be logged in

  @Regression @TransitionPage
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - join, join to epoints and continue to retailer on confirmation screen
    Given Not logged user is opening transition page
    When User click join now button
    Then He will e redirected to epoints join page
    When User correctly fill email input field
    And Click join now button on epoints join page
    Then Under confirmation screen will be displayed continue to retailer button
    When User click continue anyway button
    Then He will be redirected to retailer page

  @Regression @TransitionPage
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - forgot password link
    Given Not logged user is opening transition page
    When User use forgot password link
    Then Forgot password page will be opened

  @userIsLogoutFromFacebook @Regression @TransitionPage
  Scenario: WLS REMOVAL - update transition page desktop(RD-3994) - sign in with facebook
    Given Not logged user is opening transition page
    When User will sign in with facebook
    Then He will be directly taken to retailer page
    When User return to epoints page
    Then He will be logged in

  @Regression @TransitionPage
  Scenario Outline: DESKTOP - TRANSITION PAGE - update UI for Ireland clickouts(NBO-548) - displaying of UK currency
    Given Points page is opened by logged user
    Given Proper zone is set '<zone>'
    When User go to '<zone>' transition page from points page
    Then He can see that currency is properly set for '<zone>'

    Examples:
      | zone |
      | UK   |
      | IE   |