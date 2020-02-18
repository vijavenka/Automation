Feature: Dashboard
  As an administrator
  I want to have Dashboard page
  So that I will be able to monitoring needed user/manager activities presented on graphs

  @Regression @Dashboard
  Scenario: Dashboard page - general view
    Given User with Dashboard management permissions is logged into iat admin
    When User open Dashboard page
    Then Login number graph is displayed
    And Reasons usage graph is displayed
    And Unique login number graph is displayed
    And Total user to user ecards sent graph is displayed
    And Unique users sending ecards graph is displayed
    And Number of ecards opened graph is displayed
    And Filter component is displayed
    And Export to file button is available on hr peer to peer Dashboard page

  @Regression @Dashboard
  Scenario Outline: Dashboard page - date selector
    Given User with Dashboard management permissions is logged into iat admin
    And  User open Dashboard page
    When he clicks on the range drop-down in filters section
    Then the drop-down is opened
    And It shows predefined date range options '<RangeOptions>'

    Examples:
      | RangeOptions                                                                  |
      | Today,Yesterday,This Week,Last Week,This Month,Last Month,This Year,Last Year |

  @Regression @Dashboard
  Scenario Outline: Dashboard page - date selector filter - choose option
    Given User with Dashboard management permissions is logged into iat admin
    And  User open Dashboard page
    When He selects one of the predefined options from the range drop-down '<RangeOption>'
    Then From/to fields are filled with the chosen range '<RangeOption>'

    Examples:
      | RangeOption |
      | Today       |

  @Regression @Dashboard
  Scenario: Dashboard page - date range filter
    Given User with Dashboard management permissions is logged into iat admin
    And  User open Dashboard page
    When He fills from/to fields with the proper date range
    Then Charts are refreshed to show the data only from the chosen range
    And The selection is persisted throughout the session

  @Regression @Dashboard
  Scenario: Dashboard page - returning user
    Given User with Dashboard management permissions is logged into iat admin
    And  User open Dashboard page
    And Filtering is already done
    When User logs out
    And User with Dashboard management permissions is logged into iat admin
    And  User open Dashboard page
    Then He can see default Dashboard page view back again

  @Regression @Dashboard
  Scenario: Dashboard page - Dashboard export
    Given User with Dashboard management permissions is logged into iat admin
    And User has no recent download notifications
    And  User open Dashboard page
    When Export button will be clicked on Dashboard page
    Then Notification about 'Export is in progress.' is displayed over Dashboard page
    And Notification about 'Export is in progress.' can be closed on Dashboard page
    And After some time file ready to be downloaded will appear in notification area
    And File name will be correct - 'overall_numbers'