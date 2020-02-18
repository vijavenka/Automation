Feature: Edit reasons and values management
  As an administrator
  I want to have reason administration panel
  So that I will be able to create, delete and see reasons used in future ecards points allocation

  @Regression @Reasons
  @setAvailablePointsForAdmin
  Scenario: Reasons - check content of create reason form
    Given User with reasons management permissions is logged into iat admin
    And Reasons page is opened
    When Add reason page will be clicked
    Then Add reason form will be displayed
    And Reason input is displayed
    And Manager to user limit setting is displayed
    And User to user limit setting is displayed
    And Cancel button is displayed
    And Disabled save button is displayed

  @Regression @Reasons
  Scenario: Reasons - cancel button click on add reason page
    Given User with reasons management permissions is logged into iat admin
    And Add reasons page is opened
    When User click cancel reason creation button
    Then Reason page will be opened

  @Regression @Reasons
  Scenario: Reasons - create new reason
    Given User with reasons management permissions is logged into iat admin
    And Add reasons page is opened
    When User provide unique reason name
    And Click save new reason button
    Then Reason page will be opened
    And New reason will be available on reason list
    And New reason limits are same as global values

  @Regression @Reasons
  Scenario: Reasons - create new reason with not unique reason name
    Given User with reasons management permissions is logged into iat admin
    And Add reasons page is opened
    When User provide not unique reason name
    And Click save new reason button
    Then Alert about reason uniqueness requirement will be displayed
    And New reason will not be created

  @Regression @Reasons
  Scenario Outline: Reasons - check content of reasons list
    Given User with reasons management permissions is logged into iat admin
    When Reasons page is opened
    Then Reasons table will be displayed
    And Reason table has proper columns '<columnsLabels>'

    Examples:
      | columnsLabels                                                   |
      | Reason name,Manager to user,MIN,MAX,User to user,MIN,MAX,Action |

  @Regression @Reasons
  Scenario: Reasons - delete reason, cancel button on confirmation popup
    Given User with reasons management permissions is logged into iat admin
    And Reasons page is opened
    And User click delete reason button
    When Cancel button will be clicked on confirmation popup
    Then Popup will be closed
    And Reason will not be deleted

  @Regression @Reasons
  Scenario: Reasons - delete created reason
    Given User with reasons management permissions is logged into iat admin
    And Add reasons page is opened
    And User provide unique reason name
    And Click save new reason button
    And User click delete reason button
    When Delete button will be clicked on confirmation popup
    Then Popup will be closed
    And Reason will be deleted from reasons list

  @Regression @Reasons
  Scenario Outline: Reasons - global reason settings page content
    Given User with reasons management permissions is logged into iat admin
    And Reasons page is opened
    When User click edit reason config button
    Then Reasons global settings page will be opened
    And Global settings page has needed input fields '<inputLabels>'
    And Cancel button is displayed on reasons settings page
    And Save button is displayed on reasons settings page

    Examples:
      | inputLabels                                                               |
      | Manager to user min,Manager to user max,User to user min,User to user max |

  @Regression @Reasons
  Scenario: Reasons - global reason settings page, cancel button click
    Given User with reasons management permissions is logged into iat admin
    And Reasons page is opened
    And User click edit reason config button
    When Cancel button will be clicked on reasons global config page
    Then User will return to reasons page

  @Regression @Reasons
  Scenario: Reasons - global reason settings page, correct global setting save
    Given User with reasons management permissions is logged into iat admin
    And Reasons page is opened
    And User click edit reason config button
    When User provide new global settings values
    And User click save button on reasons global config page
    And User click yes on global settings save confirmation popup
    Then User will return to reasons page
    And New reasons global settings values will be visible in reasons config area

  @Regression @Reasons
  Scenario: Reasons - global reason settings page min values grater than max
    Given User with reasons management permissions is logged into iat admin
    And Reasons page is opened
    And User click edit reason config button
    When User provide new global settings values minimums greater than maximums
    And User click save button on reasons global config page
    Then Alert that maximum cannot be lower than minimum will be displayed

    #//TODO probably one bigger case needed when new global settings overwrite existing reasons values - global and user defined

  @Regression @Reasons
  Scenario: Reasons - create new reason, define values input check
    Given User with reasons management permissions is logged into iat admin
    And Add reasons page is opened
    When User click define option for both manager to user and user to manager fields
    Then Inputs to provide minimum and maximum values will be displayed

  # assumption is that this test depends on values se in Reasons - global reason settings page, correct global setting save test
  # to simplify logic and given steps
  @Regression @Reasons
  Scenario: Reasons - create new reason with defined limits values
    Given User with reasons management permissions is logged into iat admin
    And Add reasons page is opened
    And User provide unique reason name
    When User click define option for both manager to user and user to manager fields
    And And provide values which not exceeds global settings values
    And Click save new reason button
    Then User will return to reasons page
    And New reason will be available on list with proper limits values

  @Regression @Reasons
  Scenario: Reasons - create new reason with defined limits values which exceeds global values
    Given User with reasons management permissions is logged into iat admin
    And Add reasons page is opened
    And User provide unique reason name
    When User click define option for both manager to user and user to manager fields
    And And provide values which exceeds global settings values
    And Click save new reason button
    Then Alert that global reason values were exceeded

  @Regression @Reasons
  Scenario: Reasons - create new reason with min values grater than max
    Given User with reasons management permissions is logged into iat admin
    And Add reasons page is opened
    And User provide unique reason name
    When User click define option for both manager to user and user to manager fields
    And And provide new reason values minimums greater than maximums
    And Click save new reason button
    Then Alert that maximum cannot be lower than minimum will be displayed on add reason page

  @Regression @Reasons
  @setAvailablePointsForAdmin
  Scenario: Reasons - possibility of deleting last reason
    Given User with reasons management permissions is logged into iat admin
    And Reasons page is opened
    When User delete all reasons apart of last one
    Then Delete reason button wil be hidden
    And User will not be able to delete last reason