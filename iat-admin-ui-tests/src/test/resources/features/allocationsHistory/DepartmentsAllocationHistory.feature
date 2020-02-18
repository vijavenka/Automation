Feature: Departments history
  As an administrator
  I want to have departments history page
  So that I will be able look through historical departments points allocation

  @Regression @DepartmentsHistory
  Scenario Outline: History allocation - department allocation tables content
    Given User with department allocation history permissions is logged into iat admin
    When Departments history page is opened
    Then He sees the table with breakdown of virtual points awarded to departments
    And The departments allocation data is sorted by date desc
    And Correct departments allocation table columns are available '<columnsNames>'
    And Each of the departments allocation table columns is sortable
    And Totals are displayed at the bottom of the departments allocation table
    And Pagination component is available on departments history page
    And Export data to file button is available on departments history page

    Examples:
      | columnsNames                                                                 |
      | ID,Date send,From,Department name,Who,Comment,Points awarded,Value of points |

  @Regression @DepartmentsHistory
  Scenario: History allocation - pagination
    Given User with department allocation history permissions is logged into iat admin
    And Departments history page is opened
    When User changes the pages on departments history page
    And Moves around with pagination on departments history page
    Then Chosen page results are displayed on departments history page
    And Sorting settings are applied to consecutive pages

  @Regression @DepartmentsHistory
  @setAvailablePointsForAdmin
  Scenario: History allocation - points breakdown blocks
    Given User with department allocation history permissions is logged into iat admin
    When Departments history page is opened
    And Items per page is set to high value
    Then He sees blocks with points breakdown on departments history page
    And They include number allocations done by department admins department
    And They include number of allocated by department admins department points
    And They include number of allocated by department admins department money

  @Regression @DepartmentsHistory
  Scenario Outline: Filters - keyword search/input field
    Given User with department allocation history permissions is logged into iat admin
    And Departments history page is opened
    And There is some department points allocation history data available in the table
    When User chooses one of the available fields, specific to department points allocation history table '<filterName>'
    And User types a keyword in the search field on department history allocation page
    And User clicks search button on department points allocation history page
    Then Department Allocation data is properly filtered
    And The department allocations totals values are changed to sum only the filtered data
    And The url reflects chosen filters on department points allocation history page

    Examples:
      | filterName    |
      | Comment text  |
      | Department    |
      | From          |
      | Who allocated |
      | Value         |
      | Points amount |

  @Regression @DepartmentsHistory
  Scenario: Filter  - date range filter
    Given User with department allocation history permissions is logged into iat admin
    And Departments history page is opened
    And There is some department points allocation history data available in the table
    When User selects the date range on department points allocation history page
    And User clicks search button on department points allocation history page
    Then Data is properly filtered by the date on department points allocation history page
    And The department allocations totals values are changed to sum only the filtered data
    And The url reflects chosen date range on department points allocation history page

  @Regression @DepartmentsHistory
  Scenario: Filter  - clear button
    Given User with department allocation history permissions is logged into iat admin
    And Departments history page is opened
    And There is some department points allocation history data available in the table
    And User selects the date range on department points allocation history page
    And User clicks search button on department points allocation history page
    When User click clear filters button on department points allocation history page
    Then The initial department allocation data set is displayed
    And Basic department allocation history url is displayed again
    And The totals show the sum of all departments points allocation records

  @Regression @DepartmentsHistory
  Scenario Outline: Filter  - columns sorting
    Given User with department allocation history permissions is logged into iat admin
    And Departments history page is opened
    When User use '<sortOption>' sorting option for department allocations history table '<column>'
    Then Department allocations history results will be correctly sorted according to selected column sort option '<column>', '<sortOption>'

    Examples:
      | column | sortOption |
      | ID     | ascending  |
#      | ID              | descending |
#      | Date send       | ascending  |
#      | Date send       | descending |
#      | From            | ascending  |
#      | From            | descending |
#      | Department name | ascending  |
#      | Department name | descending |
#      | Who             | ascending  |
#      | Who             | descending |
#      | Comment         | ascending  |
#      | Comment         | descending |
#      | Points awarded  | ascending  |
#      | Points awarded  | descending |
#      | Value of points | ascending  |
#      | Value of points | descending |

  @Regression @DepartmentsHistory
  Scenario: History allocation - department allocation export
    Given User with department allocation history permissions is logged into iat admin
    And User has no recent download notifications
    And Departments history page is opened
    When Export button will be clicked on departments history page
    Then Notification about 'Export is in progress.' is displayed over departments history page
    And Notification about 'Export is in progress.' can be closed on departments history page
    And After some time file ready to be downloaded will appear in notification area
    And File name will be correct - 'departments_allocation_history'