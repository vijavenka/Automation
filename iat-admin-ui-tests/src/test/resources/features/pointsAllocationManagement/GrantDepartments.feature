Feature: Recognise department
  As an administrator
  I want to have Recognise departments page
  So that I will be able to allocate points to chosen departments

  #//TODO before allocation tests we need to get information about available points and set proper value

  @Regression @RecogniseDepartments
  @setAvailablePointsForAdmin
  Scenario: Recognise departments - Recognise department page content
    Given User with points department allocation permissions is logged into iat admin
    When Recognise department page is opened
    Then The whole departments tree is displayed
    And Available points to allocate is available
    And History button is available
    And Reason input is available
    And Points Allocation summary is available
    And Pounds Allocation summary is available
    And He is able to browse through all departments in the tree

  @Regression @RecogniseDepartments
  @setAvailablePointsForAdmin
  Scenario: Recognise departments - department points allocation, total summary
    Given User with points department allocation permissions is logged into iat admin
    And Recognise department page is opened
    When User clicks allocate next to a chosen department
    And Chooses 1 or more points
    Then The number total points to allocate is increased
    And The total is also displayed as a pound value
    And Proper points value appears next to a department

  @Regression @RecogniseDepartments
  @setAvailablePointsForAdmin
  Scenario: Recognise departments - department points allocation, total summary when cancel button clicked
    Given User with points department allocation permissions is logged into iat admin
    And Recognise department page is opened
    And There are some points added to chosen department
    When User clicks cancel next to department points
    Then The number total points to allocate is decreased
    And Allocate button appears next to chosen department

  @Regression @RecogniseDepartments
  @setAvailablePointsForAdmin
  Scenario: Recognise departments - department points allocation, allocated points exceeds available
    Given User with points department allocation permissions is logged into iat admin
    And Recognise department page is opened
    And Message field is filled properly
    When User chose to allocate more points than available
    Then Save button stay in inactive state

  @Regression @RecogniseDepartments
  @setAvailablePointsForAdmin
  Scenario: Recognise departments - department points allocation, allocated points not exceeds available
    Given User with points department allocation permissions is logged into iat admin
    And Recognise department page is opened
    And Message field is filled properly
    When User chose to allocate proper number of points
    And User will click save button
    Then Confirm dialog box is displayed
    And He can either confirm or cancel the allocation

  @Regression @RecogniseDepartments
  @setAvailablePointsForAdmin
  Scenario: Recognise departments - department points allocation, confirmation popup cancel button
    Given User with points department allocation permissions is logged into iat admin
    And Recognise department page is opened
    And Message field is filled properly
    When User chose to allocate proper number of points
    And User will click save button
    And He clicks cancel button on department points allocation confirmation popup
    Then No points are allocated
    And Admin can edit provided data

  @Regression @RecogniseDepartments
  @setAvailablePointsForAdmin
  Scenario: Recognise departments - department points allocation, confirmation popup save button
    Given User with points department allocation permissions is logged into iat admin
    And Recognise department page is opened
    And Message field is filled properly
    When User chose to allocate proper number of points
    And User will click save button
    And He clicks confirm button on department points allocation confirmation popup
    Then Virtual points are granted to proper departments
    And Confirmation info is displayed