Feature: Epoints complete account page
  As an epoints user
  I want to have complete account page
  So that I could set up new password, name last name and select gender after registration

  # // 1.1 //  ------------------------------------ EPOINTS - add gender selection to epoints registration flow(RD-4228)
  # -------------------------------------------------------------------------------------- complete account page content
  @completeAccountPage @joinSignin
  Scenario: EPOINTS - add gender selection to epoints registration flow(RD-4228) - complete account page content
    Given Complete account page is opened
    When User look at complete account page
    Then Password field is available
    And Confirm password field is available
    And First name field is available
    And Last name field is available
    And Gender select options are available
    And Done option is available

  # // 1.2 //  ------------------------------------ EPOINTS - add gender selection to epoints registration flow(RD-4228)
  # --------------------------------------------------------------------------------------- complete account page alerts
  @completeAccountPage @joinSignin
  Scenario: EPOINTS - add gender selection to epoints registration flow(RD-4228) - complete account page alerts
    Given Complete account page is opened clear
    When Done button will be pressed before filling any data
    Then All create password alerts will be shown
    When User Enter two different passwords name and last name
    And Press done button
    Then Only confirmation alert will stay visible

  # // 1.3 //  ------------------------------------ EPOINTS - add gender selection to epoints registration flow(RD-4228)
  # ------------------------------------------------------------------- complete account page, proper account completion
  @completeAccountPage @joinSignin
  Scenario: EPOINTS - add gender selection to epoints registration flow(RD-4228) - complete account page, proper account completion
    Given Complete account page is opened clear
    When User correctly fill all needed data
    And User select gender
    And Press done button
    Then User will be redirected to 'All done' screen
    And Account data should be updated

  # // 1.4 //  ----------------------------------------------------------------------------------- Complete account page
  # ------------------------------------------------------------------------------------------------------ invalid token
  @completeAccountPage @joinSignin
  Scenario: Complete account page - invalid token
    Given Complete account page is opened with expired token
    When User look at opened page
    Then He will see confirmation failed message