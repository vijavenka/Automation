Feature: Partners history
  As an administrator
  I want to have partners history page
  So that I will be able look through historical partners points allocation

  @Regression @PartnersHistory
  Scenario Outline: History allocation - partner allocation tables content
    Given User with partner allocation history permissions is logged into iat admin
    When Partners history page is opened
    Then He sees the table with breakdown of virtual points awarded to partners
    And The partners allocation data is sorted by date desc
    And Correct partners allocation table columns are available '<columnsNames>'
    And Each of the partners allocation table columns is sortable
    And Totals are displayed at the bottom of the partners allocation table
    And Pagination component is available
    And Export data to file button is available on partners history page

    Examples:
      | columnsNames                                                      |
      | Date send,Partner name,Who,Comment,Points awarded,Value of points |

  @Regression @PartnersHistory
  Scenario: History allocation - pagination
    Given User with partner allocation history permissions is logged into iat admin
    And Partners history page is opened
    When User changes the pages on partners history page
    And Moves around with pagination on partners history page
    Then Chosen page results are displayed on partners history page
    And Sorting settings are applied to consecutive pages

  @Regression @PartnersHistory
  @setAvailablePointsForAdmin
  Scenario: History allocation - points breakdown blocks
    Given User with partner allocation history permissions is logged into iat admin
    When Partners history page is opened
    And Items per page is set to high value
    Then He sees blocks with points breakdown on partners history page
    And They include number allocations done by company
    And They include number of allocated by company points
    And They include number of allocated by company money

  @Regression @PartnersHistory
  Scenario Outline: Filters - keyword search/input field
    Given User with partner allocation history permissions is logged into iat admin
    And Partners history page is opened
    And There is some partner points allocation history data available in the table
    When User chooses one of the available fields, specific to partner points allocation history table '<filterName>'
    And User types a keyword in the search field on partner history allocation page
    And User clicks search button on partner points allocation history page
    Then Partner Allocation data is properly filtered
    And The partner allocations totals values are changed to sum only the filtered data
    And The url reflects chosen filters on partner points allocation history page

    Examples:
      | filterName    |
      | Comment text  |
      | Who allocated |
      | Partner       |
      | Value         |
      | Points amount |

  @Regression @PartnersHistory
  Scenario: Filter  - date range filter
    Given User with partner allocation history permissions is logged into iat admin
    And Partners history page is opened
    And There is some partner points allocation history data available in the table
    When User selects the date range on partner points allocation history page
    And User clicks search button on partner points allocation history page
    Then Data is properly filtered by the date on partner points allocation history page
    And The partner allocations totals values are changed to sum only the filtered data
    And The url reflects chosen date range on partner points allocation history page

  @Regression @PartnersHistory
  Scenario: Filter  - clear button
    Given User with partner allocation history permissions is logged into iat admin
    And Partners history page is opened
    And There is some partner points allocation history data available in the table
    And User selects the date range on partner points allocation history page
    And User clicks search button on partner points allocation history page
    When User click clear filters button on partner points allocation history page
    Then The initial partner allocation data set is displayed
    And Basic partner allocation history url is displayed again
    And The totals show the sum of all partners points allocation records

  @Regression @PartnersHistory
  Scenario Outline: Filter  - columns sorting
    Given User with partner allocation history permissions is logged into iat admin
    And Partners history page is opened
    When User use '<sortOption>' sorting option for partner allocations history table '<column>'
    Then Partner allocations history results will be correctly sorted according to selected column sort option '<column>', '<sortOption>'

    Examples:
      | column    | sortOption |
      | Date send | ascending  |
#      | Date send       | descending |
#      | Partner name    | ascending  |
#      | Partner name    | descending |
#      | Who             | ascending  |
#      | Who             | descending |
#      | Comment         | ascending  |
#      | Comment         | descending |
#      | Points awarded  | ascending  |
#      | Points awarded  | descending |
#      | Value of points | ascending  |
#      | Value of points | descending |

  @Regression @PartnersHistory
  Scenario: History allocation - partner allocation export
    Given User with partner allocation history permissions is logged into iat admin
    And User has no recent download notifications
    And Partners history page is opened
    When Export button will be clicked on partners history page
    Then Notification about 'Export is in progress.' is displayed over partners history page
    And Notification about 'Export is in progress.' can be closed on partners history page
    And After some time file ready to be downloaded will appear in notification area
    And File name will be correct - 'partners_allocation_history'