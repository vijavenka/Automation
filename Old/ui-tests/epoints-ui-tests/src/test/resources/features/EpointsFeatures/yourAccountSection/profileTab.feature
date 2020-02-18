Feature: Epoints your account page
  As an epoints user
  I want to have profile section in your account page
  So that I could set or change personal information, email, password

  # // 1.1 //  -------------------------------------------------------------------------- Your account - profile section
  # -------------------------------------------------------------------------------------------------- email row content
  @profileTab @userAccount
  Scenario: Your account - profile section - email row content
    Given Profile page is opened
    And Email address is properly displayed
    And Changing email fields are hidden
    When User click edit email button
    Then Changing email fields will be displayed

  # // 1.2 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------------- fields required alerts
  @profileTab @userAccount
  Scenario: Your account - profile section - fields required alerts
    Given Profile page is opened
    Given Email edit form is opened
    When User click save new email button without filling and data
    Then Two fields are required alerts will be displayed

  # // 1.3 //  -------------------------------------------------------------------------- Your account - profile section
  # ------------------------------------------------------------------------------------------ emails do not match alert
  @profileTab @userAccount
  Scenario: Your account - profile section - fields required alerts
    Given Profile page is opened
    Given Email edit form is opened
    When User fill email change form with two different emails
    And Click save new email button
    Then Emails do not match alert will be displayed

  # // 1.4 //  -------------------------------------------------------------------------- Your account - profile section
  # ------------------------------------------------------------------------------------------------------ cancel button
  @profileTab @userAccount
  Scenario: Your account - profile section - cancel button
    Given Profile page is opened
    Given Email edit form is opened
    When User click cancel setting new email button
    Then Changing email fields are hidden

  # // 1.5 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------------- email properly changed
  @profileTab @userAccount
  Scenario Outline: Your account - profile section - email properly changed
    Given User is logged and profile page is opened with data '<oldEmail>' '<password>'
    Given Email edit form is opened
    When User fill change email form with proper data '<newEmail>'
    And Click save new email button
    Then Email properly changed alert will be displayed
    When User will follow new email confirmation link
    Then Email will be properly changed
    And Email confirmation page has proper content '<newEmail>'

    Examples:
      |oldEmail|newEmail|password|
      |emailwybitnietestowy@gmail.com|emailwybitnietestowyxxx@gmail.com|Delete777|
      |emailwybitnietestowyxxx@gmail.com|emailwybitnietestowy@gmail.com|Delete777|

  # // 2.1 //  -------------------------------------------------------------------------- Your account - profile section
  # ----------------------------------------------------------------------------------------------- password row content
  @profileTab @userAccount
  Scenario: Your account - profile section - password row content
    Given Profile page is opened
    And Current password set data is properly displayed
    And Changing password fields are hidden
    When User click edit password button
    Then Changing password fields will be displayed

  # // 2.2 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------------- fields required alerts
  @profileTab @userAccount
  Scenario: Your account - profile section - fields required alerts
    Given Profile page is opened
    Given Password edit form is opened
    When User click save new password button without filling and data
    Then Three fields are required alerts will be displayed

  # // 2.3 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------- passwords do not match alert
  @profileTab @userAccount
  Scenario: Your account - profile section - passwords do not match alert
    Given Profile page is opened
    Given Password edit form is opened
    When User fill password change form with two different password
    And Click save new password button
    Then passwords do not match alert will be displayed


  # // 2.4 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------- wrong current password alert
  @profileTab @userAccount
  Scenario: Your account - profile section - wrong current password alert
    Given Profile page is opened
    Given Password edit form is opened
    When User fill password change form with wrong current password
    And Click save new password button
    Then Wrong current password alert will be displayed

  # // 2.5 //  -------------------------------------------------------------------------- Your account - profile section
  # ------------------------------------------------------------------------------------------------------ cancel button
  @profileTab @userAccount
  Scenario: Your account - profile section - cancel button
    Given Profile page is opened
    Given Password edit form is opened
    When User click cancel setting new password button
    Then Changing password fields are hidden

  # // 2.6 //  -------------------------------------------------------------------------- Your account - profile section
  # ------------------------------------------------------------------------------------------ password properly changed
  @profileTab @userAccount
  Scenario Outline: Your account - profile section - password properly changed
    Given User is logged and profile page is opened with data '<email>' '<oldPassword>'
    Given Password edit form is opened
    When User fill change password form with proper data '<oldPassword>' '<newPassword>'
    And Click save new password button
    Then Password properly changed alert will be displayed

      Examples:
        |oldPassword|newPassword|email|
        |Delete777|Delete888|emailwybitnietestowy@gmail.com|
        |Delete888|Delete777|emailwybitnietestowy@gmail.com|

  # // 3.1 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------- personal details row content
  @profileTab @userAccount
  Scenario: Your account - profile section - personal details row content
    Given Profile page is opened
    And User personal data are properly displayed
    When User click edit personal details button
    Then User personal data are properly displayed
    And Cancel button will be displayed in personal details module
    And Save button will be displayed in personal details module

  # // 3.2 //  -------------------------------------------------------------------------- Your account - profile section
  # ---------------------------------------------------------------------------- personal details fields required alerts
  @profileTab @userAccount
  Scenario: Your account - profile section - personal details fields required alerts
    Given Profile page is opened
    Given User click edit personal details button
    When User provide date of birth in wrong format
    And Click save personal details button
    Then Invalid date format alert will be displayed

  # // 3.3 //  -------------------------------------------------------------------------- Your account - profile section
  # ------------------------------------------------------------------------------------- personal details cancel button
  @profileTab @userAccount
  Scenario: Your account - profile section - personal details cancel button
    Given Profile page is opened
    Given User click edit personal details button
    When User click cancel setting new personal details button
    Then Edit view of personal details will be closed

  # // 3.4 //  -------------------------------------------------------------------------- Your account - profile section
  # ---------------------------------------------------------------------------------- personal details properly changed
  @setDefaultPersonalAndContactDataAfter @profileTab @userAccount
  Scenario: Your account - profile section - personal details properly changed
    Given Profile page is opened
    Given User click edit personal details button
    When User provide new personal details data
    And Click save personal details button
    Then New personal details data will be properly saved


  # // 4.1 //  -------------------------------------------------------------------------- Your account - profile section
  # --------------------------------------------------------------------------------------- contact details  row content
  @profileTab @userAccount
  Scenario: Your account - profile section - contact details  row content
    Given Profile page is opened
    And User contact data are properly displayed
    When User click edit contact details button
    Then User contact data are properly displayed
    And Cancel button will be displayed in contact details module
    And Save button will be displayed in contact details module
    And Find address button will be displayed in contact details module