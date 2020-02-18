Feature: United epoints home page
  As an united epoints user
  I want to have home page
  So that I could get basic information about epoints, login, join etc.

  @Regression @UnitedHomeJoinPage
  Scenario: United home join page content
    When Epoints united join home page is opened by not logged user
    Then Epoints united introduction section is available
    And Redemption offers block is available with 5 not clickable products in it
    And Brands section is available with 5 ot clickable brands in it
    And United join page contains footer with correct options
    And United join page contains header with correct options

  @Regression @UnitedHomeJoinPage
  Scenario Outline: United home - sign in button with redirection
    Given Epoints united join home page is opened by not logged user
    When User click sign in header option
    And User sign in with credentials '<email>', '<password>'
    Then United home page is opened
    And United header options are displayed
    And Epoints logo is displayed

    Examples:
      | email      | password           |
      | unitedUser | unitedUserPassword |

  #todo header should be in some abstract class (since it is common module) and pretty much all page classes should extends such abstract class
  @Regression @UnitedHomeJoinPage
  @deleteUserAfterTest
  Scenario: Home page, join united by email and UnitedId
    Given Epoints united join home page is opened by not logged user
    When User provide united email address 'random' into email input field
    And User provide united id 'random' into id input field
    And User clicks "Join now" button
    Then Joined user is in United perspective

  @Regression @UnitedHomeJoinPage
  Scenario: Home page, join epoints by email, duplicated email - alert
    Given Epoints united join home page is opened by not logged user
    When User provide united email address 'unitedUser' into email input field
    And User provide united id 'random' into id input field
    And User clicks "Join now" button
    Then Information about duplicated email will be displayed

  @Regression @UnitedHomeJoinPage
  Scenario: Home page, join epoints by email wrong external id format - alert
    Given Epoints united join home page is opened by not logged user
    When User provide united email address 'random' into email input field
    And User provide united id '1asd223' into id input field
    And User clicks "Join now" button
    Then Information about wrong external id format will be displayed

  @Regression @UnitedHomeJoinPage
  @deleteUserAfterTest
  Scenario: Home page, join united by email and UnitedId - email exists in epoints
    Given Join page is opened
    And User enter new email address 'mailtotestmerg@test'
    And User click join now button on join page
    And Epoints united join home page is opened by not logged user
    When User provide united email address 'mailtotestmerg@test' into email input field
    And User provide united id 'random' into id input field
    And User clicks "Join now" button
    Then Joined user is in United perspective