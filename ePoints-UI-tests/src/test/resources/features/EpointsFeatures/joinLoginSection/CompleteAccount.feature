Feature: Epoints complete account page
  As an epoints user
  I want to have complete account page
  So that I could set up new password, name last name and select gender after registration

  # // 1.1 //  ------------------------------------ EPOINTS - add gender selection to epoints registration flow(RD-4228)
  # -------------------------------------------------------------------------------------- complete account page content
  @Regression @CompleteAccountPage
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
  @Regression @CompleteAccountPage
  Scenario: EPOINTS - add gender selection to epoints registration flow(RD-4228) - complete account page alerts
    Given Complete account page is opened
    When User Enter all needed complete data
    And User delete all entered previous complete data
    Then All complete account section alerts will be shown
    When User Enter two different passwords name and last name
    And Press done button
    Then Only password confirmation alert will stay visible

       #TODO to be checked
  # // 1.3 //  ------------------------------------ EPOINTS - add gender selection to epoints registration flow(RD-4228)
  # ------------------------------------------------------------------- complete account page, proper account completion
  @Regression @CompleteAccountPage
  Scenario: EPOINTS - add gender selection to epoints registration flow(RD-4228) - complete account page, proper account completion
    Given Complete account page is opened
    When User correctly fill all needed data
    And User select gender
    And Press done button
    Then User will be redirected to join finished page
    And Account data should be updated

  # // 1.4 //  ----------------------------------------------------------------------------------- Complete account page
  # ------------------------------------------------------------------------------------------------------ invalid token
  @Regression @CompleteAccountPage
  Scenario: Complete account page - invalid token
    Given Complete account page is opened with expired token
    When User look at opened page
    Then He will see invalid token message on account confirmation page

  @Regression @CompleteAccountPage
  @deleteCreatedUserAfterTest
  Scenario Outline: Complete account page - pre-filled personal data after manual added user in admin interface
    Given User has been added manually in admin interface with given informations '<firstName>','<lastName>','<gender>'
    When He looks at complete account page
    Then Form is filled in with his personal informations

    Examples:
      | firstName | lastName | gender |
      | firstName | lastName | MALE   |
      | firstName | lastName | FEMALE |
      | firstName |          | MALE   |
    #//TODO empty fields, we need changes in createUser json
