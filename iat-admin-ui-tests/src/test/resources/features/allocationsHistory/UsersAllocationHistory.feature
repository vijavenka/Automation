Feature: Users history
  As an administrator
  I want to have users history page
  So that I will be able look through historical users points allocation

  # @setManagerThresholdToNoneBefore has to be changed to @setManagerThresholdTo50Before when approval process will be enabled
  @Regression @UsersHistory
  @setManagerThresholdToNoneBefore
  Scenario Outline: History allocation - users allocation tables content
    Given Admin with users awarding history permissions is logged into iat admin
    When Users award history page is opened
    Then He sees the table with breakdown points awarded to users
    And The users award data is sorted by date desc
    And Correct users award table columns are available '<columnsNames>'
    And Each of the users award table columns is sortable
    And Totals are displayed at the bottom of the users award table
    And Pagination component is available on users award history page
    And Export data to file button is available on users award history page

    Examples:
      | columnsNames                                                                          |
      | ID,Date send,Send from,Environment Used,Send to,Reason,Points awarded,Value of points |
      #Approval status - colum need to be added when approval process will be enabled

  @Regression @UsersHistory
  Scenario: History allocation - pagination
    Given Admin with users awarding history permissions is logged into iat admin
    When Users award history page is opened
    When User changes the pages on users award history page
    And Moves around with pagination on users award history page
    Then Chosen page results are displayed on users award history page
    And Sorting settings are applied to consecutive pages

  @Regression @UsersHistory
  @setAvailablePointsForAdmin
  Scenario: History allocation - points breakdown blocks
    Given Admin with users awarding history permissions is logged into iat admin
    When Users award history page is opened
#    And Items per page is set to high value
    Then He sees blocks with points breakdown on users award history page
    And They include number of sent or received by admins department ecards
    And They include number of allocated by admins department points
    And They include number of allocated by admins department money

#  @Regression @UsersHistory
#  @setAvailablePointsForAdmin
#  Scenario: History allocation - approval status column
#    Given Admin with users awarding history permissions is logged into iat admin
#    When Users award history page is opened
#    Then He can see an approval status column
#    And It's filled with approved/declined status for ecards with epoints and it's empty for ecard with no epoints
#
#  @Regression @UsersHistory
#  Scenario Outline: Approval column filtering
#    Given Admin with users awarding history permissions is logged into iat admin
#    When Users award history page is opened
#    And There is some users points allocation history data available in the table
#    When User chooses one of the available fields, specific to users points allocation history table '<filterName>'
#    And User types a keyword '<keyword>' in the search field on users history allocation page
#    And User clicks search button on users points allocation history page
#    Then Users award data is properly filtered
#    And The users awards totals values are changed to sum only the filtered data
#    And The url reflects chosen filters on users points allocation history page
#
#    Examples:
#      | filterName      | keyword  |
#      | Approval status | rejected |
#      | Approval status | approved |

  @Regression @UsersHistory
  Scenario Outline: Filters - keyword search/input field
    Given Admin with users awarding history permissions is logged into iat admin
    When Users award history page is opened
    And There is some user points allocation history data available in the table
    When User chooses one of the available fields, specific to users points allocation history table '<filterName>'
    And User types a keyword in the search field on users history allocation page
    And User clicks search button on users points allocation history page
    Then Users award data is properly filtered
    And The users awards totals values are changed to sum only the filtered data
    And The url reflects chosen filters on users points allocation history page

    Examples:
      | filterName          |
      | Points amount       |
      | Value               |
      | Receiver            |
      | Sender              |
      | Sender department   |
      | Receiver department |

  @Regression @UsersHistory
  Scenario: Filter  - date range filter
    Given Admin with users awarding history permissions is logged into iat admin
    When Users award history page is opened
    And There is some user points allocation history data available in the table
    When User selects the date range on users points award history page
    And User clicks search button on users points allocation history page
    Then Data is properly filtered by the date on users points award history page
    And The users awards totals values are changed to sum only the filtered data
    And The url reflects chosen date range on users points award history page

  @Regression @UsersHistory
  Scenario: Filter  - clear button
    Given Admin with users awarding history permissions is logged into iat admin
    When Users award history page is opened
    And There is some user points allocation history data available in the table
    And User selects the date range on users points award history page
    And User clicks search button on users points allocation history page
    When User click clear filters button on users points award history page
    Then The initial users awards data set is displayed
    And Basic users award history url is displayed again
    And The totals show the sum of all users points award records

  @Regression @UsersHistory
  Scenario Outline: Filter  - columns sorting
    Given Admin with users awarding history permissions is logged into iat admin
    When Users award history page is opened
    When User use '<sortOption>' sorting option for user awards history table '<column>'
    Then User awards history results will be correctly sorted according to selected column sort option '<column>', '<sortOption>'

    Examples:
      | column | sortOption |
      | ID     | ascending  |
#      | ID              | descending |
#      | Date send       | ascending  |
#      | Date send       | descending |
#      | Send from       | ascending  |
#      | Send from       | descending |
#      | Send to         | ascending  |
#      | Send to         | descending |
#      | Reason          | ascending  |
#      | Reason          | descending |
#      | Points awarded  | ascending  |
#      | Points awarded  | descending |
#      | Value of points | ascending  |
#      | Value of points | descending |
##      | Approval status | ascending  |
##      | Approval status | descending |

  @Regression @UsersHistory
  Scenario: History allocation - user award export
    Given Admin with users awarding history permissions is logged into iat admin
    And User has no recent download notifications
    When Users award history page is opened
    When Export button will be clicked on users award history page
    Then Notification about 'Export is in progress.' is displayed over users award history page
    And Notification about 'Export is in progress.' can be closed on users award history page
    And After some time file ready to be downloaded will appear in notification area
    And File name will be correct - 'users_allocation_history'