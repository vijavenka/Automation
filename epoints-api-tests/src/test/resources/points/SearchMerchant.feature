Feature: Epoints API: Points -> search/merchant
  As an epoints user
  todo

  @Regression @PositiveCase @merchantsSearch
  Scenario Outline: Points - merchants/departments/prefixes list
    Given Epoints API is responding to requests
    When Merchants details with corresponding filters (departments; prefixes) are requested '<params>'
    Then Merchants,Departments,Prefixes details response data is same as in contract
    And Returned data is correct according to keyword search, page size and page number, department and prefix '<params>'

  @qa @stag @prod
    Examples:
      #keyword, page, pageSize, department, prefixes
      | params                         |
      | null;2;5;null;null;null        |
      | null;null;30;null;null;null    |
      | fifty;null;null;null;null;null |
      | null;null;null;null;T;null     |

  @qa @stag
    Examples:
      #keyword, page, pageSize, department, prefixes
      | params                               |
      | null;null;null;electronics;null;null |


  @qa @stag @prod
  @Regression @PositiveCase
  Scenario: Points - departments undecorated with categories
    When Epoints API is responding to requests
    Then Departments undecorated with categories call response match schema

  @qa @stag @prod
  @Regression @PositiveCase
  Scenario: Points - merchants request on about us page
    When Epoints API is responding to requests
    Then Merchants about us page call response match schema