Feature: Recognise partners
  As an administrator
  I want to have Recognise partners page
  So that I will be able to allocate points to chosen partners

  @Regression @RecognisePartners
  Scenario: Recognise partners - Recognise partner page content
    Given User with points partner allocation permissions is logged into iat admin
    When Recognise partner page is opened
    Then The whole partners tree is displayed
    And Partners search input field is available
    And Partner allocation history button is available
    And Partner allocation reason input is available
    And Partner points Allocation summary is available
    And Partner pounds allocation summary is available
    And He is able to browse through all partners in the tree

  @Regression @RecognisePartners
  Scenario: Recognise partners - partners points allocation, total summary
    Given User with points partner allocation permissions is logged into iat admin
    And Recognise partner page is opened
    When User clicks allocate next to a chosen partner
    And Chooses 1 or more points for partner allocation
    Then The number total points to allocate partner is increased
    And The total value is also displayed as a pound value
    And Proper points value appears next to a partner

  @Regression @RecognisePartners
  Scenario: Recognise partners - partners points allocation, total summary when cancel button clicked
    Given User with points partner allocation permissions is logged into iat admin
    And Recognise partner page is opened
    And There are some points added to chosen partner
    When User clicks cancel next to partner points
    Then The number total points to partner allocate is decreased
    And Allocate button appears next to chosen partner

  @Regression @RecognisePartners
  Scenario: Recognise partners - partners points allocation, allocated points not exceeds available
    Given User with points partner allocation permissions is logged into iat admin
    And Recognise partner page is opened
    And Message field is filled properly for partner allocation
    When User chose to allocate proper number of points to partner
    And User will click save button on partner allocation page
    Then Partner allocation confirm dialog box is displayed
    And He can either confirm or cancel the partner allocation

  @Regression @RecognisePartners
  Scenario: Recognise partners - partners points allocation, confirmation popup cancel button
    Given User with points partner allocation permissions is logged into iat admin
    And Recognise partner page is opened
    And Message field is filled properly for partner allocation
    When User chose to allocate proper number of points to partner
    And  User will click save button on partner allocation page
    And He clicks cancel button on partner points allocation confirmation popup
    Then No points are allocated to partner
    And Admin can edit provided data

  @Regression @RecognisePartners
  Scenario: Recognise partners - partners points allocation, confirmation popup save button
    Given User with points partner allocation permissions is logged into iat admin
    And Recognise partner page is opened
    And Message field is filled properly for partner allocation
    When User chose to allocate proper number of points to partner
    And User will click save button on partner allocation page
    And He clicks confirm button on partner points allocation confirmation popup
    Then Virtual points are granted to proper partners
    And Partner allocation confirmation info is displayed

  @Regression @RecognisePartners
  Scenario: Recognise partners - partner search
    Given User with points partner allocation permissions is logged into iat admin
    And Recognise partner page is opened
    When He types phrase into partner search input field
    Then Only connected partners are displayed