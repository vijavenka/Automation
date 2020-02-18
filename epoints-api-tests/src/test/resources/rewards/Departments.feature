Feature: Epoints API: Rewards -> departments
  As ePoints user
  I want to be able to get info about available departments and number of products within

  @Regression @PositiveCase
  Scenario Outline: Rewards - departments validation based on search
    Given Epoints API is responding to requests
    When Department call will be requested for businessId '<businessId>'
    Then Number of returned departments will be correct '<departmentsNumber>'
    And Search results count for each department will match values returned by departments and solr call for '<businessId>'

  @qa @stag
    Examples:
      | businessId | departmentsNumber |
      | null       | 19                |
      | united     | 19                |

  @Regression @PositiveCase @prod
  Scenario: Rewards - departments similarity across partners united/epoints
    Given Epoints API is responding to requests
    When Department call will be requested for united and epoints
    Then Apart of products number data should be the same