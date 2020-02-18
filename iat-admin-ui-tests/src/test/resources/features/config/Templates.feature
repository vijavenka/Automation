Feature: Templates management
  As an administrator
  I want to have templates administration panel
  So that I will be able to create, delete and see templates used in future ecards points user to user awards

  @Regression @Templates
  @setAvailablePointsForAdmin
  Scenario: Templates - check content of create template form
    Given User with reasons management permissions is logged into iat admin
    And Templates page is opened
    When User click add custom template button
    Then Add custom template page wil be opened
    And Add custom template page contains new template name input field
    And Add custom template page contains drag and drop upload image field
    And Add custom template page contains cancel button
    And Add custom template page contains disabled save button

  @Regression @Templates
  Scenario: Templates - add new template
    Given User with reasons management permissions is logged into iat admin
    And Templates page is opened
    And User click add custom template button
    When User provide unique template name
    And User add new image file
    And User click save button on add template page
    Then Templates page will be opened
    And New template will be displayed on custom template list

  @Regression @Templates
  Scenario: Templates - edit existing template
    Given User with reasons management permissions is logged into iat admin
    And Templates page is opened
    And List of custom templates is displayed
    When User click on edit button of chosen custom template
    And User provide unique template name
    And User click save button on add template page
    Then Templates page will be opened
    And Template name will be updated

  @Regression @Templates
  Scenario: Templates - add new template page, cancel button use
    Given User with reasons management permissions is logged into iat admin
    And Templates page is opened
    And User click add custom template button
    When User clicks cancel button on add template page
    Then Templates page will be opened

  @Regression @Templates
  Scenario: Templates - list of templates
    Given User with reasons management permissions is logged into iat admin
    When Templates page is opened
    Then List of custom templates is displayed
    And Each template card contains name
    And Each template card contains image
    And Each template card contains remove button
    And Each template card contains edit button
    And List of default templates is displayed
    And Each of default templates contains only image and name

  @Regression @Templates
  Scenario: Templates - delete confirmation cancel button
    Given User with reasons management permissions is logged into iat admin
    And Templates page is opened
    And List of custom templates is displayed
    And User click on remove button of chosen custom template
    When User click cancel button on template remove confirmation popup
    Then Template remove confirmation popup will be closed
    And Template will not be removed

  @Regression @Templates
  Scenario: Templates - delete confirmation delete button
    Given User with reasons management permissions is logged into iat admin
    And Templates page is opened
    And List of custom templates is displayed
    And User click on remove button of chosen custom template
    When User click delete button on template remove confirmation popup
    Then Template remove confirmation popup will be closed
    And Template will be removed

  #//TODO late probably also list of templates should be validates during awarding
  @Regression @Templates
  Scenario Outline: Templates - global template settings change
    Given User with reasons management permissions is logged into iat admin
    And Templates page is opened
    When User change global template settings '<globalTemplatesSettings>'
    And User click save global templates settings button
    Then Default templates will be gray out or not according to '<globalTemplatesSettings>'

    Examples:
      | globalTemplatesSettings |
      | defaultAndCustom        |
      | customOnly              |