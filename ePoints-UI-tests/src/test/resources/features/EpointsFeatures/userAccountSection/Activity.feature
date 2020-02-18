Feature: Epoints activity page profile tab
  As an epoints user
  I want to have activity section in your account page profile tab
  So that I could get detailed view of my points balance

  # // 1.1 //  ------------------------------------------------------------------------- Your account - activity section
  # ------------------------------------------------------------------------------------------------------- page content
  @Regression @Activity
  Scenario Outline: Your account - activity section - page content
    Given Activity page is opened in '<partner>' context
    And Table contains three tabs 'Current balance;Pending;Decline'
    And Balance table contains columns 'Date;Activity;Site;In;Out;Balance'
    And Pagination option are available
    And Sort by filter is available

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.2 //  ------------------------------------------------------------------------- Your account - activity section
  # ---------------------------------------------------------------------------------------------- items per page module
  # assumption user activity has more than 100 rows
  @Regression @Activity
  Scenario Outline: Your account - activity section - items per page module
    Given Activity page is opened in '<partner>' context
    When User select number of activity rows which should be displayed in table '<rowsNumber>'
    Then Wanted number of activity rows will be displayed '<rowsNumber>'

    Examples:
      | rowsNumber | partner |
      | 20         | epoints |
      | 40         | epoints |
      | 100        | epoints |
      #|            |         |
      | 20         | united  |
      | 40         | united  |
      | 100        | united  |

  # // 1.3 //  ------------------------------------------------------------------------- Your account - activity section
  # --------------------------------------------------------------------------------------------------------- pagination
  @Regression @Activity
  Scenario Outline: Your account - activity section - pagination
    Given Activity page is opened in '<partner>' context
    When User click next button on activity page
    Then Second activity rows page will be displayed
    And User can go to any page using page number buttons

    Examples:
      | partner |
      | epoints |
      | united  |

  # // 1.4 //  ------------------------------------------------------------------------- Your account - activity section
  # --------------------------------------------------------------------------------------------------------------- sort
  @Regression @Activity
  Scenario Outline: Your account - activity section - sort
    Given Activity page is opened in '<partner>' context
    When User select wanted activity sort option '<sort>'
    Then Activity rows will be sorted according to chosen sort option '<sort>'

    Examples:
      | sort         | partner |
      | Recent first | epoints |
      | Recent last  | epoints |
      #|              |         |
      | Recent first | united  |
      | Recent last  | united  |

  @Regression @Activity
  Scenario Outline: Your account - activity section - tabs count values
    When Activity page is opened in '<partner>' context
    Then Each tab: 'Current balance;Pending;Decline' contains correct count value for '<partner>' context

    Examples:
      | partner |
      | epoints |
      | united  |