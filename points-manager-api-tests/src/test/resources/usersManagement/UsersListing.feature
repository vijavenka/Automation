Feature: Users - list and sort
  As an API client
  I want to be able to list and sort user available in the system
  So that I will be able to get users data as a list, sort it and return needed number of users details.

  @UsersListing @PositiveCase
  @Regression
  @qa @stag @prod
  Scenario: Return users - check default limit, sorting and correctness of contract
    Given Points Manager API is responding to requests
    When User search will be used without any parameter
    Then Users search request response data has proper structure
    And 25 users details data should be returned according to default limit
    And Returned user details data should be sorted by default in firstName descending order

  @UsersListing @PositiveCase
  @Regression
  Scenario Outline: Return users - check working of limit/sort parameters
    Given Points Manager API is responding to requests
    When User search will be used with set '<limit>', '<sortField>' and '<sortOrder>' with '<offset>'
    Then Users search request response data has proper structure
    And '<limit>' users details data should be returned according to set limit
    And Returned user details data should be sorted by '<sortField>' in '<sortOrder>' order

  @qa @stag @prod
    Examples:
      | limit | sortField | sortOrder  | offset |
      | 20    | firstName | descending | 700    |
      | 40    | firstName | ascending  | 700    |

  @UsersListing @PositiveCase
  Scenario Outline: Return users - check working of limit/sort parameters
    Given Points Manager API is responding to requests
    When User search will be used with set '<limit>', '<sortField>' and '<sortOrder>' with '<offset>'
    Then Users search request response data has proper structure
    And '<limit>' users details data should be returned according to set limit
    And Returned user details data should be sorted by '<sortField>' in '<sortOrder>' order

  @qa @stag @prod
    Examples:
      | limit | sortField | sortOrder  | offset |
      | 2     | lastName  | ascending  | 700    |
      | 17    | lastName  | descending | 700    |
      | 99    | email     | ascending  | 700    |
      | 120   | email     | descending | 700    |

  @UsersListing @NegativeCase
  Scenario Outline: Return users - check system response in case of not allowed parameter values
    Given Points Manager API is responding to requests
    When Users list will be pulled with provided data '<limit>', '<sortField>', '<sortOrder>', '<offset>', '<expResponseCode>', '<expErrorCode>', '<expErrorMsg>'
    Then Users list data should not be returned for provided data

  @qa @stag @prod
    Examples:
      | limit | sortField | sortOrder  | offset | expResponseCode | expErrorCode  | expErrorMsg   |
      | -1    | firstName | descending | 700    | 500             | not_specified | not_specified |
      | 10    | wrong     | descending | 700    | 400             | not_specified | not_specified |
      | 10    | firstName | wrong      | 700    | 400             | not_specified | not_specified |
      | 10    | firstName | descending | -1     | 500             | not_specified | not_specified |